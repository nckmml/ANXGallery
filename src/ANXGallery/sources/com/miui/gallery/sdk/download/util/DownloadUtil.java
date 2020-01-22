package com.miui.gallery.sdk.download.util;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.DownloadPathHelper;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.cloud.SyncConditionManager;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.LocalUbifocus;
import com.miui.gallery.data.UbiIndexMapper;
import com.miui.gallery.error.core.ErrorCode;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.sdk.download.DownloadOptions;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.sdk.download.ImageDownloader;
import com.miui.gallery.sdk.download.assist.DownloadFailReason;
import com.miui.gallery.sdk.download.assist.DownloadItem;
import com.miui.gallery.sdk.download.assist.RequestItem;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.uil.CloudUriAdapter;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class DownloadUtil {
    private static final int[] DOWNLOAD_FILE_PRIORITY = {9, 10, 11};

    public static boolean canDownloadStatus(DBImage dBImage) {
        return dBImage != null && "custom".equals(dBImage.getServerStatus()) && dBImage.getLocalFlag() == 0;
    }

    public static String checkAndReturnValidFilePathForType(DBImage dBImage, DownloadType downloadType) {
        if (dBImage == null || downloadType == null) {
            return null;
        }
        if (isMicro(downloadType)) {
            return checkAndReturnValidMicroFilePath(dBImage, downloadType);
        }
        if (isThumbnail(downloadType)) {
            return checkAndReturnValidThumbnailFilePath(dBImage, downloadType);
        }
        if (isOrigin(downloadType)) {
            return checkAndReturnValidOriginalFilePath(dBImage, downloadType);
        }
        throw new UnsupportedOperationException("bad checktype, checktype=" + downloadType);
    }

    public static String checkAndReturnValidMicroFilePath(DBImage dBImage, DownloadType downloadType) {
        String microThumbnailFile = new File(dBImage.getMicroThumbnailFile()).exists() ? dBImage.getMicroThumbnailFile() : DownloadPathHelper.getFilePathForRead(StorageUtils.getMicroThumbnailDirectories(GalleryApp.sGetAndroidContext()), getDownloadFileName(dBImage, downloadType));
        if (TextUtils.isEmpty(microThumbnailFile)) {
            return null;
        }
        boolean z = true;
        if (!microThumbnailFile.equalsIgnoreCase(dBImage.getMicroThumbnailFile()) && (z = updateImageColumnValue(dBImage, "microthumbfile", microThumbnailFile))) {
            dBImage.setMicroThumbFile(microThumbnailFile);
            GalleryApp.sGetAndroidContext().getContentResolver().notifyChange(GalleryContract.Album.URI, (ContentObserver) null, false);
        }
        if (z) {
            return microThumbnailFile;
        }
        return null;
    }

    public static String checkAndReturnValidOriginalFilePath(DBImage dBImage, DownloadType downloadType) {
        String localFile = new File(dBImage.getLocalFile()).exists() ? dBImage.getLocalFile() : DownloadPathHelper.getFilePathForRead(DownloadPathHelper.getDownloadFolderRelativePath(dBImage), getDownloadFileName(dBImage, downloadType));
        if (TextUtils.isEmpty(localFile)) {
            return null;
        }
        boolean z = true;
        if (!dBImage.isSecretItem()) {
            z = isOriginalFileValid(localFile, dBImage);
        }
        if (z && !localFile.equalsIgnoreCase(dBImage.getLocalFile()) && (z = updateImageColumnValue(dBImage, "localFile", localFile))) {
            dBImage.setLocalFile(localFile);
        }
        if (z) {
            return localFile;
        }
        return null;
    }

    public static String checkAndReturnValidThumbnailFilePath(DBImage dBImage, DownloadType downloadType) {
        String thumbnailFile = new File(dBImage.getThumbnailFile()).exists() ? dBImage.getThumbnailFile() : DownloadPathHelper.getFilePathForRead(DownloadPathHelper.getDownloadFolderRelativePath(dBImage), getDownloadFileName(dBImage, downloadType));
        if (TextUtils.isEmpty(thumbnailFile)) {
            return null;
        }
        boolean z = true;
        if (!dBImage.isSecretItem()) {
            z = isThumbnailFileValid(thumbnailFile, dBImage);
        }
        if (z && !thumbnailFile.equalsIgnoreCase(dBImage.getThumbnailFile()) && (z = updateImageColumnValue(dBImage, "thumbnailFile", thumbnailFile))) {
            dBImage.setThumbnailFile(thumbnailFile);
        }
        if (z) {
            return thumbnailFile;
        }
        return null;
    }

    public static DownloadFailReason checkCondition(DownloadItem downloadItem) {
        if (!GalleryPreferences.CTA.canConnectNetwork()) {
            return new DownloadFailReason(ErrorCode.NO_CTA_PERMISSION, "no cta permission", (Throwable) null);
        }
        if (!NetworkUtils.isNetworkConnected()) {
            return new DownloadFailReason(ErrorCode.NO_NETWORK, "no network", (Throwable) null);
        }
        if (downloadItem.isRequireWLAN() && NetworkUtils.isActiveNetworkMetered()) {
            return new DownloadFailReason(ErrorCode.NO_WIFI_CONNECTED, "no wifi", (Throwable) null);
        }
        if (downloadItem.isRequirePower() && !GalleryPreferences.Sync.getPowerCanSync()) {
            return new DownloadFailReason(ErrorCode.POWER_LOW, "power low", (Throwable) null);
        }
        if (downloadItem.isRequireCharging() && !GalleryPreferences.Sync.getIsPlugged()) {
            return new DownloadFailReason(ErrorCode.NO_CHARGING, "not charging", (Throwable) null);
        }
        if (!downloadItem.isRequireDeviceStorage() || !GalleryPreferences.Sync.isDeviceStorageLow()) {
            return null;
        }
        return new DownloadFailReason(ErrorCode.STORAGE_LOW, "storage low", (Throwable) null);
    }

    public static ErrorCode ensureDownloadFolder(DBImage dBImage, DownloadType downloadType) {
        if (dBImage == null || downloadType == null) {
            return ErrorCode.UNKNOWN;
        }
        return ensureFolder(getDownloadFolderPath(dBImage, downloadType), isMicro(downloadType) || dBImage.isSecretItem());
    }

    public static ErrorCode ensureDownloadTempFolder(DBImage dBImage, DownloadType downloadType) {
        return (dBImage == null || downloadType == null) ? ErrorCode.UNKNOWN : ensureFolder(getDownloadTempFolderPath(dBImage, downloadType), true);
    }

    private static ErrorCode ensureFolder(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return ErrorCode.UNKNOWN;
        }
        if (FileUtils.createFolder(str, z)) {
            return ErrorCode.NO_ERROR;
        }
        if (StorageUtils.isInPrimaryStorage(str)) {
            return ErrorCode.PRIMARY_STORAGE_WRITE_ERROR;
        }
        if (StorageUtils.isInSecondaryStorage(str)) {
            return ErrorCode.SECONDARY_STORAGE_WRITE_ERROR;
        }
        Log.w("DownloadUtil", "Failed to create folder under unknown volume %s", str);
        return ErrorCode.UNKNOWN;
    }

    public static String generateKey(Uri uri, DownloadType downloadType) {
        if (uri == null || downloadType == null) {
            return null;
        }
        return uri.buildUpon().appendQueryParameter(nexExportFormat.TAG_FORMAT_TYPE, downloadType.name()).build().toString();
    }

    public static String getDownloadFileName(DBImage dBImage, DownloadType downloadType) {
        if (dBImage == null || downloadType == null) {
            return null;
        }
        if (isMicro(downloadType)) {
            return dBImage.isSecretItem() ? dBImage.getSha1ThumbnailSA() : dBImage.getSha1Thumbnail();
        }
        if (isThumbnail(downloadType)) {
            return dBImage.isSecretItem() ? dBImage.getSha1ThumbnailSA() : DownloadPathHelper.getThumbnailDownloadFileNameNotSecret(dBImage);
        }
        if (isOrigin(downloadType)) {
            return dBImage.isSecretItem() ? dBImage.getEncodedFileName() : DownloadPathHelper.getOriginDownloadFileNameNotSecret(dBImage);
        }
        throw new UnsupportedOperationException("bad checktype, checktype=" + downloadType);
    }

    public static String getDownloadFilePath(DBImage dBImage, DownloadType downloadType) {
        if (dBImage == null || downloadType == null) {
            return null;
        }
        String downloadFileName = getDownloadFileName(dBImage, downloadType);
        if (isOrigin(downloadType) && (dBImage.isUbiFocus() || dBImage.isSubUbiFocus())) {
            downloadFileName = LocalUbifocus.createInnerFileName(downloadFileName, UbiIndexMapper.cloudToLocal(dBImage.getSubUbiIndex(), dBImage.getSubUbiImageCount() + 1), dBImage.getSubUbiImageCount() + 1);
        }
        String concat = FileUtils.concat(getDownloadFolderPath(dBImage, downloadType), downloadFileName);
        if ((!isThumbnail(downloadType) && !isOrigin(downloadType)) || !new File(concat).exists() || isOriginalFileValid(concat, dBImage) || isThumbnailFileValid(concat, dBImage)) {
            return concat;
        }
        String format = String.format("%s_%s.%s", new Object[]{FileUtils.getFileNameWithoutExtension(downloadFileName), Long.valueOf(System.currentTimeMillis()), FileUtils.getExtension(downloadFileName)});
        String concat2 = FileUtils.concat(getDownloadFolderPath(dBImage, downloadType), format);
        Log.d("DownloadUtil", "There exist a file with same name that doesn't belong to image [%s], so we rename current to %s", dBImage, format);
        return concat2;
    }

    public static String getDownloadFolderPath(DBImage dBImage, DownloadType downloadType) {
        if (dBImage == null || downloadType == null) {
            return null;
        }
        if (isMicro(downloadType)) {
            return StorageUtils.getPriorMicroThumbnailDirectory();
        }
        if (isThumbnail(downloadType) || isOrigin(downloadType)) {
            return DownloadPathHelper.getDownloadFolderPath(dBImage);
        }
        return null;
    }

    public static String getDownloadTempFilePath(DBImage dBImage, DownloadType downloadType) {
        if (!(dBImage == null || downloadType == null)) {
            if (isMicro(downloadType)) {
                String microThumbnailTempDirectory = StorageUtils.getMicroThumbnailTempDirectory();
                return FileUtils.concat(microThumbnailTempDirectory, dBImage.getFileName() + ".temp");
            } else if (isThumbnail(downloadType)) {
                String thumbnailTempDirectory = StorageUtils.getThumbnailTempDirectory();
                return FileUtils.concat(thumbnailTempDirectory, dBImage.getSha1() + "." + dBImage.getId());
            } else if (isOrigin(downloadType)) {
                String originTempDirectory = StorageUtils.getOriginTempDirectory();
                return FileUtils.concat(originTempDirectory, dBImage.getSha1() + "." + dBImage.getId());
            }
        }
        return null;
    }

    public static String getDownloadTempFolderPath(DBImage dBImage, DownloadType downloadType) {
        if (!(dBImage == null || downloadType == null)) {
            if (isMicro(downloadType)) {
                return StorageUtils.getMicroThumbnailTempDirectory();
            }
            if (isThumbnail(downloadType)) {
                return StorageUtils.getThumbnailTempDirectory();
            }
            if (isOrigin(downloadType)) {
                return StorageUtils.getOriginTempDirectory();
            }
        }
        return null;
    }

    public static boolean isMicro(DownloadType downloadType) {
        return downloadType == DownloadType.MICRO || downloadType == DownloadType.MICRO_BATCH;
    }

    public static boolean isNotSyncedStatus(DBImage dBImage) {
        return (dBImage == null || dBImage.getLocalFlag() == 11 || dBImage.getLocalFlag() == 2 || dBImage.getLocalFlag() == -1) ? false : true;
    }

    public static boolean isOrigin(DownloadType downloadType) {
        return downloadType == DownloadType.ORIGIN || downloadType == DownloadType.ORIGIN_FORCE || downloadType == DownloadType.ORIGIN_BATCH;
    }

    public static boolean isOriginalFileValid(String str, DBImage dBImage) {
        return isOriginalFileValid(str, dBImage, false);
    }

    public static boolean isOriginalFileValid(String str, DBImage dBImage, boolean z) {
        String str2 = str;
        DBImage dBImage2 = dBImage;
        if (TextUtils.isEmpty(str)) {
            Log.d("DownloadUtil", "Empty original file path for %s", (Object) dBImage2);
            return false;
        }
        File file = new File(str2);
        if (!file.exists() || !file.isFile()) {
            Log.d("DownloadUtil", "File [%s] doesn't exist or is not a file %s", str2, dBImage2);
            return false;
        }
        long length = file.length();
        if (length < dBImage.getSize()) {
            Log.d("DownloadUtil", "File [%s] size [%d] is smaller than expected [%d] for image %s", str2, Long.valueOf(length), Long.valueOf(dBImage.getSize()), dBImage2);
            if (z) {
                HashMap hashMap = new HashMap();
                hashMap.put("Params", String.format(Locale.US, "ServerId:%s, expectedSize:%d, realSize:%d", new Object[]{dBImage.getServerId(), Long.valueOf(dBImage.getSize()), Long.valueOf(length)}));
                GallerySamplingStatHelper.recordErrorEvent(String.format("file_download_%s", new Object[]{"origin"}), "file_download_wrong_size", hashMap);
            }
            return false;
        }
        String sha1 = FileUtils.getSha1(file);
        if (sha1 != null && sha1.equalsIgnoreCase(dBImage.getSha1())) {
            return true;
        }
        Object[] objArr = new Object[4];
        objArr[0] = str2;
        objArr[1] = sha1 == null ? "null" : sha1;
        objArr[2] = dBImage.getSha1();
        objArr[3] = dBImage2;
        Log.d("DownloadUtil", "File [%s]'s sha1 [%s] is different from [%s], %s", objArr);
        if (z) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("Params", String.format("ServerId:%s, expectedSha1:%s, realSha1:%s", new Object[]{dBImage.getServerId(), dBImage.getSha1(), sha1}));
            GallerySamplingStatHelper.recordErrorEvent(String.format("file_download_%s", new Object[]{"origin"}), "file_download_wrong_sha1", hashMap2);
        }
        return false;
    }

    public static boolean isThumbnail(DownloadType downloadType) {
        return downloadType == DownloadType.THUMBNAIL || downloadType == DownloadType.THUMBNAIL_BATCH;
    }

    public static boolean isThumbnailFileValid(String str, DBImage dBImage) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals(dBImage.getSha1(), ExifUtil.getUserCommentSha1(str));
    }

    public static void persistDownloadStatus(DBImage dBImage, int i) {
        Log.i("DownloadUtil", "persistDownloadStatus id %s, status %d", dBImage.getId(), Integer.valueOf(i));
        if (i == 1) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("downloadFileTime", Long.valueOf(System.currentTimeMillis()));
            SafeDBUtil.safeUpdate(GalleryApp.sGetAndroidContext(), dBImage.getBaseUri(), contentValues, String.format(Locale.US, "(%s) AND (%s is null OR %s=%d)", new Object[]{"_id=?", "downloadFileStatus", "downloadFileStatus", 0}), new String[]{dBImage.getId()});
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("downloadFileStatus", Integer.valueOf(i));
        SafeDBUtil.safeUpdate(GalleryApp.sGetAndroidContext(), dBImage.getBaseUri(), contentValues2, "_id=?", new String[]{dBImage.getId()});
    }

    public static void persistDownloadStatus(RequestItem requestItem) {
        int status = requestItem.mDownloadItem.getStatus();
        DBImage dBImage = requestItem.mDBItem;
        if (dBImage != null) {
            int i = 0;
            if (status == 0) {
                i = 1;
            } else if (status != 1) {
                if (status == 2) {
                    i = 2;
                } else if (status == 3) {
                    i = 3;
                }
            }
            persistDownloadStatus(dBImage, i);
        }
    }

    public static int resumeInterrupted() {
        for (int check : DOWNLOAD_FILE_PRIORITY) {
            if (SyncConditionManager.check(check) != 0) {
                return 0;
            }
        }
        Uri[] uriArr = {GalleryContract.Cloud.CLOUD_URI, GalleryContract.ShareImage.SHARE_URI};
        final ArrayList<DownloadItem> arrayList = new ArrayList<>();
        String[] strArr = {"_id", "serverType"};
        int length = uriArr.length;
        for (int i = 0; i < length; i++) {
            Uri uri = uriArr[i];
            final boolean z = uri == GalleryContract.ShareImage.SHARE_URI;
            SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), uri, strArr, "(downloadFileStatus=? OR downloadFileStatus=?) AND (localFlag = 0 AND serverStatus = 'custom')", new String[]{String.valueOf(2), String.valueOf(1)}, "downloadFileTime DESC", new SafeDBUtil.QueryHandler() {
                public Object handle(Cursor cursor) {
                    if (cursor == null) {
                        return null;
                    }
                    while (cursor.moveToNext()) {
                        long convertToMediaId = z ? ShareMediaManager.convertToMediaId(cursor.getLong(0)) : cursor.getLong(0);
                        cursor.getInt(1);
                        arrayList.add(new DownloadItem.Builder().setUri(CloudUriAdapter.getDownloadUri(convertToMediaId)).setType(DownloadType.ORIGIN).build());
                    }
                    return null;
                }
            });
        }
        DownloadOptions build = new DownloadOptions.Builder().setRequireWLAN(true).build();
        for (DownloadItem downloadItem : arrayList) {
            Log.i("DownloadUtil", "resume item %s", (Object) downloadItem);
            ImageDownloader.getInstance().load(downloadItem.getUri(), downloadItem.getType(), build);
        }
        return arrayList.size();
    }

    public static void statDownloadError(RequestItem requestItem, String str, DownloadFailReason downloadFailReason) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", String.valueOf(false));
        String str2 = "unknown";
        hashMap.put("media_type", requestItem.mDBItem != null ? MiscUtil.serverType2Text(requestItem.mDBItem.getServerType()) : str2);
        if (requestItem.mDownloadItem != null) {
            str2 = String.valueOf(requestItem.mDownloadItem.getType().name());
        }
        hashMap.put(GalleryPreferences.PrefKeys.SYNC_DOWNLOAD_TYPE, str2);
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("url", str);
        }
        hashMap.put("code", String.valueOf(downloadFailReason.getCode()));
        hashMap.put("desc", String.valueOf(downloadFailReason.getDesc()));
        if (downloadFailReason.getCause() != null) {
            hashMap.put("throwable", downloadFailReason.getCause().getMessage());
            NetworkInfo activeNetworkInfo = NetworkUtils.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                hashMap.put("netInfo", activeNetworkInfo.getTypeName() + "_" + activeNetworkInfo.getExtraInfo());
            }
        }
        GallerySamplingStatHelper.recordCountEvent("file_download", "file_download_status_v2", hashMap);
    }

    public static void statDownloadRetryTimes(RequestItem requestItem, int i, int i2) {
        if (requestItem.mDownloadItem != null && requestItem.mDBItem != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(GalleryPreferences.PrefKeys.SYNC_DOWNLOAD_TYPE, String.valueOf(requestItem.mDownloadItem.getType().name()));
            hashMap.put("media_type", MiscUtil.serverType2Text(requestItem.mDBItem.getServerType()));
            NetworkInfo activeNetworkInfo = NetworkUtils.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                hashMap.put("net_type", String.valueOf(activeNetworkInfo.getTypeName()));
            }
            hashMap.put("times", String.valueOf(i));
            GallerySamplingStatHelper.recordCountEvent("file_download", "file_download_retry_times_v2", hashMap);
        }
    }

    public static void statDownloadSuccess(RequestItem requestItem, long j, long j2) {
        if (requestItem.mDownloadItem != null && requestItem.mDBItem != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("status", String.valueOf(true));
            hashMap.put(GalleryPreferences.PrefKeys.SYNC_DOWNLOAD_TYPE, String.valueOf(requestItem.mDownloadItem.getType().name()));
            hashMap.put("size", String.format(Locale.US, "%.2f", new Object[]{Float.valueOf((((float) j2) * 1.0f) / 1024.0f)}));
            hashMap.put("media_type", MiscUtil.serverType2Text(requestItem.mDBItem.getServerType()));
            hashMap.put("costs", String.format(Locale.US, "%.2f", new Object[]{Float.valueOf((((float) (System.currentTimeMillis() - j)) * 1.0f) / 1000.0f)}));
            NetworkInfo activeNetworkInfo = NetworkUtils.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                hashMap.put("net_type", String.valueOf(activeNetworkInfo.getTypeName()));
            }
            GallerySamplingStatHelper.recordCountEvent("file_download", "file_download_status_v2", hashMap);
        }
    }

    private static boolean updateImageColumnValue(DBImage dBImage, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, str2);
        return CloudUtils.updateToLocalDB(dBImage.getBaseUri(), contentValues, dBImage.getId()) >= 1;
    }
}
