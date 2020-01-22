package com.miui.gallery.scanner;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.google.common.collect.Lists;
import com.miui.extraphoto.sdk.ExtraPhotoSDK;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.strategies.ScannerStrategy;
import com.miui.gallery.data.LocalUbifocus;
import com.miui.gallery.data.LocationManager;
import com.miui.gallery.data.LocationUtil;
import com.miui.gallery.data.UbiIndexMapper;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.ContentProviderBatchOperator;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.provider.RecentDiscoveryMediaManager;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.scanner.MediaScanner;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.Encode;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.SpecialTypeMediaUtils;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.VideoAttrsReader;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import miui.telephony.TelephonyHelper;
import org.json.JSONObject;
import org.keyczar.Keyczar;

public class SaveToCloudUtil {
    private static final String[] CLOUD_PROJECTION = {"_id", "size", "fileName", "serverStatus", "localFlag", "sha1", "localFile", "thumbnailFile", "serverId", "dateTaken"};
    private static final String[] ENSURE_INFO_PROJECTION_IMAGE = {"latitude", "longitude"};
    private static final String[] ENSURE_INFO_PROJECTION_VIDEO = {"latitude", "longitude"};

    private static class CloudEntry {
        long mDateTaken;
        long mID;
        int mLocalFlag;
        String mLocalPath;
        String mName;
        String mServerId;
        String mServerStatus;
        String mSha1;
        long mSize;
        String mThumbnailPath;

        private CloudEntry() {
        }

        /* access modifiers changed from: private */
        public boolean hasSynced() {
            return this.mLocalFlag == 0 || !TextUtils.isEmpty(this.mServerStatus);
        }
    }

    public static class SaveParams {
        private boolean isForceParserPhotoDateTime;
        private final long mAlbumId;
        private final int mAlbumSyncState;
        private final boolean mBulkNotify;
        private long mFileLastModifyTime;
        private final boolean mIsInternalScanRequest;
        private final int mLocalFlag;
        private File mSaveFile;

        public static class Builder {
            private boolean isBulkNotify;
            /* access modifiers changed from: private */
            public boolean isForceParserPhotoDateTime;
            /* access modifiers changed from: private */
            public boolean isInternalScanRequest;
            /* access modifiers changed from: private */
            public int mAlbumAttributes;
            /* access modifiers changed from: private */
            public long mAlbumId;
            private int mAlbumSyncState = -1;
            /* access modifiers changed from: private */
            public long mFileLastModifyTime;
            /* access modifiers changed from: private */
            public int mLocalFlag;
            /* access modifiers changed from: private */
            public File mSaveFile;

            public SaveParams build() {
                return new SaveParams(this);
            }

            public Builder disableBulkNotify() {
                this.isBulkNotify = false;
                return this;
            }

            public Builder enableForceParserPhotoDateTime() {
                this.isForceParserPhotoDateTime = true;
                return this;
            }

            public Builder enableInternalScanRequest() {
                this.isInternalScanRequest = true;
                return this;
            }

            public Builder setAlbumAttributes(int i) {
                this.mAlbumAttributes = i;
                return this;
            }

            public Builder setAlbumId(long j) {
                this.mAlbumId = j;
                return this;
            }

            public Builder setFileLastModifyTime(long j) {
                this.mFileLastModifyTime = j;
                return this;
            }

            public Builder setLocalFlag(int i) {
                this.mLocalFlag = i;
                return this;
            }

            public Builder setSaveFile(File file) {
                this.mSaveFile = file;
                return this;
            }
        }

        private SaveParams(Builder builder) {
            this.mFileLastModifyTime = -1;
            this.mFileLastModifyTime = builder.mFileLastModifyTime;
            this.mAlbumId = builder.mAlbumId;
            this.mLocalFlag = builder.mLocalFlag;
            this.mSaveFile = builder.mSaveFile;
            this.mAlbumSyncState = MediaScanner.AlbumEntry.isSyncable(builder.mAlbumAttributes) ^ true ? 1 : 0;
            this.mBulkNotify = false;
            this.mIsInternalScanRequest = builder.isInternalScanRequest;
            this.mFileLastModifyTime = builder.mFileLastModifyTime;
            this.isForceParserPhotoDateTime = builder.isForceParserPhotoDateTime;
        }

        public SaveParams(File file, long j, int i, boolean z) {
            this.mFileLastModifyTime = -1;
            this.mSaveFile = file;
            this.mAlbumId = j;
            this.mLocalFlag = i;
            this.mAlbumSyncState = -1;
            this.mBulkNotify = false;
            this.mIsInternalScanRequest = z;
        }

        public SaveParams(File file, long j, boolean z, int i, boolean z2, boolean z3) {
            this.mFileLastModifyTime = -1;
            this.mSaveFile = file;
            this.mAlbumId = j;
            this.mAlbumSyncState = z ^ true ? 1 : 0;
            this.mLocalFlag = i;
            this.mBulkNotify = z2;
            this.mIsInternalScanRequest = z3;
        }

        public long getAlbumId() {
            return this.mAlbumId;
        }

        public long getFileLastModifyTime() {
            return this.mFileLastModifyTime;
        }

        public int getLocalFlag() {
            return this.mLocalFlag;
        }

        public File getSaveFile() {
            return this.mSaveFile;
        }

        public boolean isAlbumSyncable() {
            return this.mAlbumSyncState == 0;
        }

        public boolean isBulkNotify() {
            return this.mBulkNotify;
        }

        public boolean isInternalScanRequest() {
            return this.mIsInternalScanRequest;
        }

        public boolean isSaveToSecretAlbum() {
            return this.mAlbumId == -1000;
        }

        public boolean isValidSyncValue() {
            return this.mAlbumSyncState != -1;
        }
    }

    private static class SaveToCloud {
        boolean mIsExifSha1;
        long mLastModify;
        String mMimeType;
        String mName;
        String mPath;
        String mSha1;
        long mSize;
        String mTitle;
        UbifocusEntry mUbiEntry;

        private SaveToCloud() {
        }
    }

    private static class UbifocusEntry {
        int mMainIndexForCloud;
        int mOuterIndexForCloud;
        List<LocalUbifocus.SubFile> mSubFiles;

        private UbifocusEntry() {
        }
    }

    private static int checkFileSize(SaveToCloud saveToCloud) {
        return saveToCloud.mSize <= CloudUtils.getMinFileSizeLimit(saveToCloud.mMimeType) ? -6 : 0;
    }

    private static void ensureLocation(Context context, String str, boolean z, final ContentValues contentValues) {
        SafeDBUtil.safeQuery(context, z ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Video.Media.EXTERNAL_CONTENT_URI, z ? ENSURE_INFO_PROJECTION_IMAGE : ENSURE_INFO_PROJECTION_VIDEO, "_data=?", new String[]{str}, (String) null, new SafeDBUtil.QueryHandler() {
            public Object handle(Cursor cursor) {
                if (cursor != null && cursor.moveToNext()) {
                    double d = cursor.getDouble(0);
                    double d2 = cursor.getDouble(1);
                    if (!MiscUtil.doubleEquals(d, 0.0d) && contentValues.get("exifGPSLatitude") == null) {
                        contentValues.put("exifGPSLatitude", LocationUtil.convertDoubleToLaLon(d));
                    }
                    if (!MiscUtil.doubleEquals(d2, 0.0d) && contentValues.get("exifGPSLongitude") == null) {
                        contentValues.put("exifGPSLongitude", LocationUtil.convertDoubleToLaLon(d2));
                    }
                }
                return null;
            }
        });
    }

    private static ArrayList<CloudEntry> findEntry(Context context, String str, long j, String str2, long j2) {
        SQLiteDatabase readableDatabase = GalleryDBHelper.getInstance().getReadableDatabase();
        AnonymousClass4 r1 = new SafeDBUtil.QueryHandler<ArrayList<CloudEntry>>() {
            public ArrayList<CloudEntry> handle(Cursor cursor) {
                if (cursor != null) {
                    ArrayList<CloudEntry> arrayList = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        arrayList.add(SaveToCloudUtil.toCloudEntry(cursor));
                    }
                    return arrayList;
                }
                throw new IllegalStateException("query cursor is null");
            }
        };
        if (readableDatabase != null) {
            return (ArrayList) SafeDBUtil.safeQuery(readableDatabase, getReadTableName(j2), CLOUD_PROJECTION, "localGroupId=? AND (UPPER(title) = UPPER(?) OR size = ? OR sha1 = ?) AND (serverType=1 OR serverType=2) AND (serverStatus is null OR serverStatus='custom' OR serverStatus='temp') AND localFlag <> 15", new String[]{String.valueOf(j2), str, String.valueOf(j), str2}, (String) null, r1);
        }
        return (ArrayList) SafeDBUtil.safeQuery(context, getReadUri(j2), CLOUD_PROJECTION, "localGroupId=? AND (UPPER(title) = UPPER(?) OR size = ? OR sha1 = ?) AND (serverType=1 OR serverType=2) AND (serverStatus is null OR serverStatus='custom' OR serverStatus='temp') AND localFlag <> 15", new String[]{String.valueOf(j2), str, String.valueOf(j), str2}, (String) null, r1);
    }

    private static ArrayList<CloudEntry> findEntry(Context context, String str, String str2, long j) {
        SQLiteDatabase readableDatabase = GalleryDBHelper.getInstance().getReadableDatabase();
        AnonymousClass3 r8 = new SafeDBUtil.QueryHandler<ArrayList<CloudEntry>>() {
            public ArrayList<CloudEntry> handle(Cursor cursor) {
                if (cursor != null) {
                    ArrayList<CloudEntry> arrayList = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        arrayList.add(SaveToCloudUtil.toCloudEntry(cursor));
                    }
                    return arrayList;
                }
                throw new IllegalStateException("query cursor is null");
            }
        };
        if (readableDatabase != null) {
            return (ArrayList) SafeDBUtil.safeQuery(readableDatabase, getReadTableName(j), CLOUD_PROJECTION, "localGroupId=? AND (UPPER(title) = UPPER(?) OR UPPER(localFile) = UPPER(?) OR UPPER(thumbnailFile) = UPPER(?)) AND (serverType=1 OR serverType=2) AND (serverStatus is null OR serverStatus='custom' OR serverStatus='temp') AND localFlag <> 15", new String[]{String.valueOf(j), str, str2, str2}, (String) null, r8);
        }
        return (ArrayList) SafeDBUtil.safeQuery(context, getReadUri(j), CLOUD_PROJECTION, "localGroupId=? AND (UPPER(title) = UPPER(?) OR UPPER(localFile) = UPPER(?) OR UPPER(thumbnailFile) = UPPER(?)) AND (serverType=1 OR serverType=2) AND (serverStatus is null OR serverStatus='custom' OR serverStatus='temp') AND localFlag <> 15", new String[]{String.valueOf(j), str, str2, str2}, (String) null, r8);
    }

    private static ContentValues genUpdateValues(Context context, SaveToCloud saveToCloud) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("size", Long.valueOf(saveToCloud.mSize));
        contentValues.put("dateModified", Long.valueOf(saveToCloud.mLastModify));
        contentValues.put("sha1", saveToCloud.mSha1);
        if (FileMimeUtil.isImageFromMimeType(saveToCloud.mMimeType)) {
            contentValues.put("serverType", 1);
            putValuesForImage(saveToCloud.mPath, contentValues, saveToCloud.mLastModify);
        } else if (FileMimeUtil.isVideoFromMimeType(saveToCloud.mMimeType)) {
            contentValues.put("serverType", 2);
            putValuesForVideo(context, saveToCloud.mPath, saveToCloud.mSize, contentValues);
        }
        return contentValues;
    }

    public static long getDateTaken(ExifInterface exifInterface, long j) {
        long dateTime = ExifUtil.getDateTime(exifInterface);
        if (dateTime == -1) {
            dateTime = ExifUtil.getGpsDateTime(exifInterface);
        }
        return dateTime == -1 ? j : dateTime;
    }

    public static long getDateTaken(androidx.exifinterface.media.ExifInterface exifInterface, long j) {
        long dateTime = ExifUtil.getDateTime(exifInterface);
        if (dateTime == -1) {
            dateTime = ExifUtil.getGpsDateTime(exifInterface);
        }
        return dateTime == -1 ? j : dateTime;
    }

    private static String getReadTableName(long j) {
        return ShareAlbumManager.isOtherShareAlbumId(j) ? "shareImage" : "cloud";
    }

    private static Uri getReadUri(long j) {
        return ShareAlbumManager.isOtherShareAlbumId(j) ? GalleryContract.ShareImage.SHARE_URI : GalleryContract.Cloud.CLOUD_URI;
    }

    private static Uri getUbiSubUri(long j) {
        return ShareAlbumManager.isOtherShareAlbumId(j) ? GalleryCloudUtils.SHARE_SUB_UBIFOCUS_URI.buildUpon().appendQueryParameter("URI_PARAM_REQUEST_SYNC", String.valueOf(false)).build() : GalleryCloudUtils.OWNER_SUB_UBIFOCUS_URI.buildUpon().appendQueryParameter("URI_PARAM_REQUEST_SYNC", String.valueOf(false)).build();
    }

    private static Uri getWriteUri(SaveParams saveParams) {
        return ShareAlbumManager.isOtherShareAlbumId(saveParams.getAlbumId()) ? GalleryContract.ShareImage.SHARE_URI : saveParams.isBulkNotify() ? saveParams.isValidSyncValue() ? GalleryContract.CloudWriteBulkNotify.CLOUD_WRITE_BULK_NOTIFY_URI.buildUpon().appendQueryParameter("URI_PARAM_REQUEST_SYNC", String.valueOf(saveParams.isAlbumSyncable())).build() : GalleryContract.CloudWriteBulkNotify.CLOUD_WRITE_BULK_NOTIFY_URI : saveParams.isValidSyncValue() ? GalleryContract.Cloud.CLOUD_URI.buildUpon().appendQueryParameter("URI_PARAM_REQUEST_SYNC", String.valueOf(saveParams.isAlbumSyncable())).build() : GalleryContract.Cloud.CLOUD_URI;
    }

    private static long insert(Context context, SaveToCloud saveToCloud, SaveParams saveParams) {
        Log.d("SaveToCloudUtil", "insert %s", (Object) saveToCloud.mPath);
        return saveToCloud.mUbiEntry != null ? insertUbi(context, saveToCloud, saveParams) : insertImmediately(context, saveToCloud, saveParams);
    }

    private static long insert(Context context, SaveToCloud saveToCloud, SaveParams saveParams, MediaBulkInserter mediaBulkInserter) {
        Log.d("SaveToCloudUtil", "bulk insert %s", (Object) saveToCloud.mPath);
        if (saveToCloud.mUbiEntry != null) {
            return insertUbi(context, saveToCloud, saveParams);
        }
        if (mediaBulkInserter == null || ShareAlbumManager.isOtherShareAlbumId(saveParams.getAlbumId())) {
            return insertImmediately(context, saveToCloud, saveParams);
        }
        SystemClock.uptimeMillis();
        insertMedia(context, mediaBulkInserter, toValues(context, saveToCloud, saveParams.getAlbumId(), saveParams.getLocalFlag()));
        return -4;
    }

    private static long insertImmediately(Context context, SaveToCloud saveToCloud, SaveParams saveParams) {
        SystemClock.uptimeMillis();
        ContentValues values = toValues(context, saveToCloud, ShareAlbumManager.isOtherShareAlbumId(saveParams.getAlbumId()) ? ShareAlbumManager.getOriginalAlbumId(saveParams.getAlbumId()) : saveParams.getAlbumId(), saveParams.getLocalFlag());
        SystemClock.uptimeMillis();
        Uri insertMedia = insertMedia(context, getWriteUri(saveParams), values);
        if (insertMedia == null) {
            return -1;
        }
        long parseId = ContentUris.parseId(insertMedia);
        if (!ShareAlbumManager.isOtherShareAlbumId(saveParams.getAlbumId())) {
            SystemClock.uptimeMillis();
            RecentDiscoveryMediaManager.RecentMediaEntry recentMediaEntry = new RecentDiscoveryMediaManager.RecentMediaEntry(saveParams.getAlbumId(), parseId, saveToCloud.mPath, saveToCloud.mLastModify);
            if (saveParams.isInternalScanRequest()) {
                RecentDiscoveryMediaManager.insertToRecentUnchecked(context, recentMediaEntry);
            } else {
                RecentDiscoveryMediaManager.insertToRecent(context, recentMediaEntry);
            }
        }
        return ShareAlbumManager.isOtherShareAlbumId(saveParams.getAlbumId()) ? ShareMediaManager.convertToMediaId(parseId) : parseId;
    }

    private static Uri insertMedia(Context context, Uri uri, ContentValues contentValues) {
        Uri safeInsert = SafeDBUtil.safeInsert(context, uri, contentValues);
        requestThumbnail(context, contentValues);
        return safeInsert;
    }

    private static void insertMedia(Context context, MediaBulkInserter mediaBulkInserter, ContentValues contentValues) {
        mediaBulkInserter.insert(context, contentValues);
        requestThumbnail(context, contentValues);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c7  */
    private static long insertUbi(Context context, SaveToCloud saveToCloud, SaveParams saveParams) {
        String str;
        ContentValues values = toValues(context, saveToCloud, saveParams.getAlbumId(), saveParams.getLocalFlag());
        int size = saveToCloud.mUbiEntry.mSubFiles.size();
        values.put("ubiSubImageCount", Integer.valueOf(size - 1));
        values.put("ubiFocusIndex", Integer.valueOf(saveToCloud.mUbiEntry.mOuterIndexForCloud));
        values.put("ubiSubIndex", Integer.valueOf(saveToCloud.mUbiEntry.mMainIndexForCloud));
        Uri insertMedia = insertMedia(context, getWriteUri(saveParams), values);
        if (insertMedia == null) {
            return -1;
        }
        long parseId = ContentUris.parseId(insertMedia);
        for (LocalUbifocus.SubFile next : saveToCloud.mUbiEntry.mSubFiles) {
            File file = new File(next.getFilePath());
            if (!file.exists()) {
                break;
            } else if (next.mIndex != LocalUbifocus.getMainFileIndex()) {
                saveToCloud.mPath = next.getFilePath();
                saveToCloud.mName = FileUtils.getFileName(saveToCloud.mPath);
                saveToCloud.mTitle = FileUtils.getFileTitle(saveToCloud.mName);
                saveToCloud.mLastModify = file.lastModified();
                saveToCloud.mSize = file.length();
                if (needCheckExifSha1(saveToCloud.mName)) {
                    try {
                        ExifUtil.UserCommentData userCommentData = ExifUtil.getUserCommentData(saveToCloud.mPath);
                        if (userCommentData != null) {
                            str = userCommentData.getSha1();
                            if (!TextUtils.isEmpty(str)) {
                                saveToCloud.mIsExifSha1 = false;
                                str = FileUtils.getSha1(saveToCloud.mPath);
                            } else {
                                saveToCloud.mIsExifSha1 = true;
                            }
                            saveToCloud.mSha1 = str;
                            saveToCloud.mUbiEntry = null;
                            ContentValues values2 = toValues(context, saveToCloud, saveParams.getAlbumId(), saveParams.getLocalFlag());
                            long localToCloud = (long) UbiIndexMapper.localToCloud(next.mIndex, size);
                            values2.put("ubiLocalId", Long.valueOf(parseId));
                            values2.put("ubiSubIndex", Long.valueOf(localToCloud));
                            SafeDBUtil.safeInsert(context, getUbiSubUri(saveParams.getAlbumId()), values2);
                        }
                    } catch (Exception e) {
                        Log.w("SaveToCloudUtil", (Throwable) e);
                    }
                }
                str = null;
                if (!TextUtils.isEmpty(str)) {
                }
                saveToCloud.mSha1 = str;
                saveToCloud.mUbiEntry = null;
                ContentValues values22 = toValues(context, saveToCloud, saveParams.getAlbumId(), saveParams.getLocalFlag());
                long localToCloud2 = (long) UbiIndexMapper.localToCloud(next.mIndex, size);
                values22.put("ubiLocalId", Long.valueOf(parseId));
                values22.put("ubiSubIndex", Long.valueOf(localToCloud2));
                SafeDBUtil.safeInsert(context, getUbiSubUri(saveParams.getAlbumId()), values22);
            }
        }
        return parseId;
    }

    private static boolean maybeThumbnail(SaveToCloud saveToCloud) {
        return needCheckExifSha1(saveToCloud.mName) && saveToCloud.mSize < 1048576;
    }

    private static boolean needCheckExifSha1(String str) {
        return !TextUtils.isEmpty(str) && str.endsWith(".jpg");
    }

    private static SaveToCloud parseSaveToCloud(SaveParams saveParams) {
        if (saveParams == null || saveParams.getSaveFile() == null || !saveParams.getSaveFile().exists()) {
            return null;
        }
        File saveFile = saveParams.getSaveFile();
        String absolutePath = saveFile.getAbsolutePath();
        SaveToCloud saveToCloud = new SaveToCloud();
        saveToCloud.mPath = absolutePath;
        saveToCloud.mName = FileUtils.getFileName(saveToCloud.mPath);
        saveToCloud.mTitle = FileUtils.getFileTitle(saveToCloud.mName);
        saveToCloud.mSize = saveFile.length();
        saveToCloud.mMimeType = FileMimeUtil.getMimeType(saveToCloud.mPath);
        saveToCloud.mLastModify = saveParams.getFileLastModifyTime() != -1 ? saveParams.getFileLastModifyTime() : saveFile.lastModified();
        return saveToCloud;
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0407  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0415  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x046f  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0256 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0258  */
    private static int prepareInsert(Context context, SaveToCloud saveToCloud, SaveParams saveParams, ContentProviderBatchOperator contentProviderBatchOperator) {
        String str;
        CloudEntry cloudEntry;
        ArrayList<CloudEntry> findEntry;
        int i;
        int i2;
        String str2;
        Context context2 = context;
        SaveToCloud saveToCloud2 = saveToCloud;
        int checkFileSize = checkFileSize(saveToCloud);
        if (checkFileSize != 0) {
            return checkFileSize;
        }
        String str3 = saveToCloud2.mPath;
        if (needCheckExifSha1(saveToCloud2.mName)) {
            try {
                ExifUtil.UserCommentData userCommentData = ExifUtil.getUserCommentData(saveToCloud2.mPath);
                str = userCommentData != null ? userCommentData.getSha1() : null;
            } catch (Exception e) {
                Log.w("SaveToCloudUtil", "exif exifSha1 read fail %s", e);
                return -1;
            }
        } else {
            str = null;
        }
        saveToCloud2.mIsExifSha1 = !TextUtils.isEmpty(str);
        if (saveToCloud2.mIsExifSha1) {
            try {
                SystemClock.uptimeMillis();
                Object obj = "sha1";
                String str4 = "media_scanner";
                ArrayList<CloudEntry> findEntry2 = findEntry(context, saveToCloud2.mTitle, saveToCloud2.mSize, str, saveParams.getAlbumId());
                if (MiscUtil.isValid(findEntry2)) {
                    Iterator<CloudEntry> it = findEntry2.iterator();
                    CloudEntry cloudEntry2 = null;
                    while (it.hasNext()) {
                        CloudEntry next = it.next();
                        if (TextUtils.equals(str, next.mSha1)) {
                            if (TextUtils.isEmpty(next.mLocalPath) && TextUtils.isEmpty(next.mThumbnailPath)) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("thumbnailFile", saveToCloud2.mPath);
                                update(context, contentValues, next.mID, saveParams, contentProviderBatchOperator);
                            }
                            return -2;
                        }
                        String fileTitle = FileUtils.getFileTitle(next.mName);
                        if (!saveToCloud2.mTitle.startsWith(fileTitle) && !fileTitle.startsWith(saveToCloud2.mTitle)) {
                            str2 = str4;
                        } else if (TextUtils.equals(FileMimeUtil.getMimeType(saveToCloud2.mName), FileMimeUtil.getMimeType(next.mName))) {
                            str2 = str4;
                            if (saveToCloud2.mSize == next.mSize) {
                                if (TextUtils.isEmpty(next.mThumbnailPath)) {
                                    ContentValues contentValues2 = new ContentValues();
                                    contentValues2.put("thumbnailFile", saveToCloud2.mPath);
                                    update(context, contentValues2, next.mID, saveParams, contentProviderBatchOperator);
                                }
                                return -2;
                            } else if (Math.abs(saveToCloud2.mSize - next.mSize) < 1024) {
                                long dateTime = ExifUtil.getDateTime(ExifUtil.sMediaExifCreator.create(saveToCloud2.mPath));
                                if (dateTime > 0 && dateTime / 1000 == next.mDateTaken / 1000) {
                                    if (TextUtils.isEmpty(next.mThumbnailPath)) {
                                        ContentValues contentValues3 = new ContentValues();
                                        contentValues3.put("thumbnailFile", saveToCloud2.mPath);
                                        update(context, contentValues3, next.mID, saveParams, contentProviderBatchOperator);
                                    }
                                    statFuzzyMatch("thumbnail_size_similar", saveToCloud2.mName, next.mName);
                                    return -2;
                                }
                            }
                        } else {
                            str2 = str4;
                            long dateTime2 = ExifUtil.getDateTime(ExifUtil.sMediaExifCreator.create(saveToCloud2.mPath));
                            if (dateTime2 > 0 && dateTime2 / 1000 == next.mDateTaken / 1000) {
                                if (TextUtils.isEmpty(next.mThumbnailPath)) {
                                    ContentValues contentValues4 = new ContentValues();
                                    contentValues4.put("thumbnailFile", saveToCloud2.mPath);
                                    update(context, contentValues4, next.mID, saveParams, contentProviderBatchOperator);
                                }
                                statFuzzyMatch("thumbnail_date_taken", saveToCloud2.mName, next.mName);
                                return -2;
                            }
                        }
                        if (saveToCloud2.mName.equalsIgnoreCase(next.mName)) {
                            cloudEntry2 = next;
                        }
                        str4 = str2;
                    }
                    String str5 = str4;
                    saveToCloud2.mSha1 = str;
                    if (cloudEntry2 != null) {
                        Map<String, String> generatorCommonParams = GallerySamplingStatHelper.generatorCommonParams();
                        generatorCommonParams.put("albumPath", StorageUtils.getRelativePath(context2, new File(str3).getParent()));
                        generatorCommonParams.put(obj, cloudEntry2.mSha1 + "_" + str);
                        GallerySamplingStatHelper.recordCountEvent(str5, "scanner_rename_conflict_thumbnail", generatorCommonParams);
                        String renameForPhotoConflict = CloudUtils.renameForPhotoConflict(str3);
                        saveToCloud2.mPath = renameForPhotoConflict;
                        saveToCloud2.mName = FileUtils.getFileName(saveToCloud2.mPath);
                        saveToCloud2.mTitle = FileUtils.getFileTitle(saveToCloud2.mName);
                        saveToCloud2.mLastModify = new File(renameForPhotoConflict).lastModified();
                    }
                } else {
                    saveToCloud2.mSha1 = str;
                }
            } catch (Exception e2) {
                Log.w("SaveToCloudUtil", (Throwable) e2);
                return -1;
            }
        } else {
            String str6 = "sha1";
            if (LocalUbifocus.isUbifocusImage(saveToCloud2.mPath)) {
                String str7 = saveToCloud2.mPath;
                ArrayList newArrayList = Lists.newArrayList();
                int ubifocusSubFiles = LocalUbifocus.getUbifocusSubFiles(saveToCloud2.mPath, newArrayList);
                if (newArrayList.size() > 0) {
                    int mainFileIndex = LocalUbifocus.getMainFileIndex();
                    if (ubifocusSubFiles != mainFileIndex) {
                        str7 = ((LocalUbifocus.SubFile) newArrayList.get(mainFileIndex)).getFilePath();
                    }
                    if (!(ubifocusSubFiles == -1 || mainFileIndex == -1)) {
                        i = UbiIndexMapper.localToCloud(ubifocusSubFiles, newArrayList.size());
                        i2 = UbiIndexMapper.localToCloud(mainFileIndex, newArrayList.size());
                        if (new File(str7).exists()) {
                            return -1;
                        }
                        saveToCloud2.mPath = str7;
                        if (!(i == -1 || i2 == -1 || newArrayList.size() <= 0)) {
                            cloudEntry = null;
                            saveToCloud2.mUbiEntry = new UbifocusEntry();
                            saveToCloud2.mUbiEntry.mOuterIndexForCloud = i;
                            saveToCloud2.mUbiEntry.mMainIndexForCloud = i2;
                            saveToCloud2.mUbiEntry.mSubFiles = newArrayList;
                            SystemClock.uptimeMillis();
                            findEntry = findEntry(context2, saveToCloud2.mTitle, saveToCloud2.mPath, saveParams.getAlbumId());
                            if (MiscUtil.isValid(findEntry)) {
                                Iterator<CloudEntry> it2 = findEntry.iterator();
                                while (it2.hasNext()) {
                                    CloudEntry next2 = it2.next();
                                    if (saveToCloud2.mSize != next2.mSize) {
                                        if (TextUtils.isEmpty(saveToCloud2.mSha1)) {
                                            saveToCloud2.mSha1 = FileUtils.getSha1(saveToCloud2.mPath);
                                        }
                                        if (saveToCloud2.mSha1.equalsIgnoreCase(next2.mSha1)) {
                                            if (TextUtils.isEmpty(next2.mLocalPath)) {
                                                ContentValues genUpdateValues = genUpdateValues(context, saveToCloud);
                                                genUpdateValues.put("localFile", saveToCloud2.mPath);
                                                update(context, genUpdateValues, next2.mID, saveParams, contentProviderBatchOperator);
                                            }
                                            return -2;
                                        } else if (saveToCloud2.mPath.equalsIgnoreCase(next2.mLocalPath) && !next2.hasSynced()) {
                                            Log.i("SaveToCloudUtil", "file changed before sync: %s", (Object) saveToCloud2.mPath);
                                            Log.d("SaveToCloudUtil", "original file: sha1 [%s] size [%d]", next2.mSha1, Long.valueOf(next2.mSize));
                                            ContentValues genUpdateValues2 = genUpdateValues(context, saveToCloud);
                                            Log.d("SaveToCloudUtil", "updated values: sha1 [%s] size [%d]", saveToCloud2.mSha1, Long.valueOf(saveToCloud2.mSize));
                                            update(context, genUpdateValues2, next2.mID, saveParams, contentProviderBatchOperator);
                                            HashMap hashMap = new HashMap();
                                            hashMap.put(nexExportFormat.TAG_FORMAT_PATH, saveToCloud2.mPath);
                                            hashMap.put("size_old_new", String.valueOf(next2.mSize + "_" + saveToCloud2.mSize));
                                            GallerySamplingStatHelper.recordCountEvent("media_scanner", "file_content_changed", hashMap);
                                            return -7;
                                        }
                                    } else if (saveToCloud2.mName.equalsIgnoreCase(next2.mName)) {
                                        ContentValues contentValues5 = new ContentValues();
                                        if (TextUtils.isEmpty(next2.mSha1)) {
                                            Log.i("SaveToCloudUtil", "file %s sha1 is null, update sha1", (Object) saveToCloud2.mPath);
                                            saveToCloud2.mSha1 = FileUtils.getSha1(saveToCloud2.mPath);
                                            contentValues5.put(str6, saveToCloud2.mSha1);
                                        }
                                        if (TextUtils.isEmpty(next2.mLocalPath)) {
                                            contentValues5.put("localFile", saveToCloud2.mPath);
                                        }
                                        if (contentValues5.size() > 0) {
                                            update(context, contentValues5, next2.mID, saveParams, contentProviderBatchOperator);
                                        }
                                        return -2;
                                    } else if (saveToCloud2.mPath.equalsIgnoreCase(next2.mLocalPath)) {
                                        statFuzzyMatch("origin_size_path", saveToCloud2.mName, next2.mName);
                                        return -2;
                                    }
                                    if (maybeThumbnail(saveToCloud)) {
                                        if (saveToCloud2.mPath.equalsIgnoreCase(next2.mThumbnailPath)) {
                                            statFuzzyMatch("origin_thumbnail_path", saveToCloud2.mName, next2.mName);
                                            return -2;
                                        }
                                        long dateTime3 = ExifUtil.getDateTime(ExifUtil.sMediaExifCreator.create(saveToCloud2.mPath));
                                        if (dateTime3 > 0 && dateTime3 / 1000 == next2.mDateTaken / 1000) {
                                            statFuzzyMatch("origin_datetaken", saveToCloud2.mName, next2.mName);
                                            return -2;
                                        }
                                    }
                                    if (saveToCloud2.mName.equalsIgnoreCase(next2.mName)) {
                                        cloudEntry = next2;
                                    }
                                }
                            }
                            if (TextUtils.isEmpty(saveToCloud2.mSha1)) {
                                saveToCloud2.mSha1 = FileUtils.getSha1(saveToCloud2.mPath);
                            }
                            if (!saveParams.isInternalScanRequest()) {
                                String parentFolderPath = FileUtils.getParentFolderPath(saveToCloud2.mPath);
                                String relativePath = StorageUtils.getRelativePath(context2, parentFolderPath);
                                ScannerStrategy.StreamFileStrategy streamFileStrategy = CloudControlStrategyHelper.getStreamFileStrategy();
                                if (streamFileStrategy == null || !streamFileStrategy.isStreamFolder(relativePath)) {
                                    if (saveToCloud2.mLastModify - new File(parentFolderPath).lastModified() > 1000) {
                                        Log.w("SaveToCloudUtil", "maybe a stream folder %s", relativePath);
                                        HashMap hashMap2 = new HashMap();
                                        hashMap2.put("folder", relativePath);
                                        GallerySamplingStatHelper.recordErrorEvent("media_scanner", "scanner_stream_file", hashMap2);
                                    }
                                } else if (Math.abs(((System.currentTimeMillis() / 1000) * 1000) - saveToCloud2.mLastModify) < streamFileStrategy.getDelayMilliseconds()) {
                                    Log.w("SaveToCloudUtil", "scanning stream folder %s", relativePath);
                                    return -1;
                                }
                            }
                            if (cloudEntry != null) {
                                Map<String, String> generatorCommonParams2 = GallerySamplingStatHelper.generatorCommonParams();
                                generatorCommonParams2.put("albumPath", StorageUtils.getRelativePath(context2, new File(str3).getParent()));
                                generatorCommonParams2.put(str6, cloudEntry.mSha1 + "_" + saveToCloud2.mSha1);
                                GallerySamplingStatHelper.recordCountEvent("media_scanner", "scanner_rename_conflict_origin", generatorCommonParams2);
                                String renameForPhotoConflict2 = CloudUtils.renameForPhotoConflict(str3);
                                if (saveToCloud2.mUbiEntry != null) {
                                    List<LocalUbifocus.SubFile> ubifocusSubFiles2 = LocalUbifocus.getUbifocusSubFiles(renameForPhotoConflict2);
                                    int mainFileIndex2 = LocalUbifocus.getMainFileIndex();
                                    if (ubifocusSubFiles2 != null && ubifocusSubFiles2.size() > mainFileIndex2) {
                                        saveToCloud2.mPath = ubifocusSubFiles2.get(mainFileIndex2).getFilePath();
                                        File file = new File(saveToCloud2.mPath);
                                        saveToCloud2.mName = FileUtils.getFileName(saveToCloud2.mPath);
                                        saveToCloud2.mTitle = FileUtils.getFileTitle(saveToCloud2.mName);
                                        saveToCloud2.mLastModify = file.lastModified();
                                        saveToCloud2.mUbiEntry.mSubFiles = ubifocusSubFiles2;
                                    }
                                } else {
                                    saveToCloud2.mPath = renameForPhotoConflict2;
                                    saveToCloud2.mName = FileUtils.getFileName(saveToCloud2.mPath);
                                    saveToCloud2.mTitle = FileUtils.getFileTitle(saveToCloud2.mName);
                                    saveToCloud2.mLastModify = new File(renameForPhotoConflict2).lastModified();
                                }
                            }
                            if (saveToCloud2.mLastModify > 0 && (System.currentTimeMillis() - saveToCloud2.mLastModify) / 1000 < 60) {
                                GallerySamplingStatHelper.recordCountEvent("Sync", "sync_photo_insert_in_one_minute", new HashMap());
                            }
                        }
                    }
                }
                i2 = -1;
                i = -1;
                if (new File(str7).exists()) {
                }
            }
            cloudEntry = null;
            try {
                SystemClock.uptimeMillis();
                findEntry = findEntry(context2, saveToCloud2.mTitle, saveToCloud2.mPath, saveParams.getAlbumId());
                if (MiscUtil.isValid(findEntry)) {
                }
                if (TextUtils.isEmpty(saveToCloud2.mSha1)) {
                }
                if (!saveParams.isInternalScanRequest()) {
                }
                if (cloudEntry != null) {
                }
                GallerySamplingStatHelper.recordCountEvent("Sync", "sync_photo_insert_in_one_minute", new HashMap());
            } catch (Exception e3) {
                Log.w("SaveToCloudUtil", (Throwable) e3);
                return -1;
            }
        }
        if (!saveParams.isSaveToSecretAlbum() || saveToCloud2.mUbiEntry != null) {
            return 0;
        }
        tryEncryptFilePathForSecret(saveToCloud);
        return 0;
    }

    private static void putExifIntToContentValues(ExifInterface exifInterface, String str, ContentValues contentValues, String str2) {
        String attribute = exifInterface.getAttribute(str);
        if (attribute != null) {
            try {
                contentValues.put(str2, Integer.valueOf(attribute));
            } catch (NumberFormatException e) {
                Log.w("SaveToCloudUtil", (Throwable) e);
            }
        }
    }

    private static void putValuesForImage(String str, ContentValues contentValues, long j) {
        putValuesForImage(str, contentValues, j, false);
    }

    private static void putValuesForImage(String str, ContentValues contentValues, long j, boolean z) {
        String str2 = str;
        ContentValues contentValues2 = contentValues;
        long j2 = j;
        String str3 = "DateTime";
        try {
            if (FileMimeUtil.hasExif(str)) {
                ExifInterface exifInterface = new ExifInterface(str2);
                int attributeInt = exifInterface.getAttributeInt("ImageWidth", 0);
                String str4 = "SaveToCloudUtil";
                try {
                    int attributeInt2 = exifInterface.getAttributeInt("ImageLength", 0);
                    if (attributeInt <= 0 || attributeInt2 <= 0) {
                        BitmapFactory.Options bitmapSize = miui.graphics.BitmapFactory.getBitmapSize(str);
                        attributeInt = bitmapSize.outWidth;
                        attributeInt2 = bitmapSize.outHeight;
                    }
                    contentValues2.put("exifImageWidth", Integer.valueOf(attributeInt));
                    contentValues2.put("exifImageLength", Integer.valueOf(attributeInt2));
                    contentValues2.put("exifOrientation", Integer.valueOf(exifInterface.getAttributeInt("Orientation", 0)));
                    contentValues2.put("exifMake", exifInterface.getAttribute("Make"));
                    contentValues2.put("exifModel", exifInterface.getAttribute("Model"));
                    putExifIntToContentValues(exifInterface, "Flash", contentValues2, "exifFlash");
                    contentValues2.put("exifGPSLatitudeRef", exifInterface.getAttribute("GPSLatitudeRef"));
                    contentValues2.put("exifGPSLongitudeRef", exifInterface.getAttribute("GPSLongitudeRef"));
                    contentValues2.put("exifExposureTime", exifInterface.getAttribute("ExposureTime"));
                    contentValues2.put("exifFNumber", exifInterface.getAttribute("FNumber"));
                    contentValues2.put("exifISOSpeedRatings", exifInterface.getAttribute("ISOSpeedRatings"));
                    contentValues2.put("exifGPSAltitude", exifInterface.getAttribute("GPSAltitude"));
                    putExifIntToContentValues(exifInterface, "GPSAltitudeRef", contentValues2, "exifGPSAltitudeRef");
                    putExifIntToContentValues(exifInterface, "WhiteBalance", contentValues2, "exifWhiteBalance");
                    contentValues2.put("exifFocalLength", exifInterface.getAttribute("FocalLength"));
                    contentValues2.put("exifGPSProcessingMethod", exifInterface.getAttribute("GPSProcessingMethod"));
                    contentValues2.put("exifGPSLatitude", exifInterface.getAttribute("GPSLatitude"));
                    contentValues2.put("exifGPSLongitude", exifInterface.getAttribute("GPSLongitude"));
                    long dateTaken = getDateTaken(exifInterface, j2);
                    String attribute = exifInterface.getAttribute("GPSTimeStamp");
                    String attribute2 = exifInterface.getAttribute("GPSDateStamp");
                    String attribute3 = exifInterface.getAttribute(str3);
                    if (z) {
                        androidx.exifinterface.media.ExifInterface exifInterface2 = null;
                        if (TextUtils.isEmpty(attribute)) {
                            exifInterface2 = new androidx.exifinterface.media.ExifInterface(str2);
                            attribute = exifInterface2.getAttribute("GPSTimeStamp");
                        }
                        if (TextUtils.isEmpty(attribute2)) {
                            if (exifInterface2 == null) {
                                exifInterface2 = new androidx.exifinterface.media.ExifInterface(str2);
                            }
                            attribute2 = exifInterface2.getAttribute("GPSDateStamp");
                        }
                        if (TextUtils.isEmpty(attribute3)) {
                            if (exifInterface2 == null) {
                                exifInterface2 = new androidx.exifinterface.media.ExifInterface(str2);
                            }
                            attribute3 = exifInterface2.getAttribute(str3);
                        }
                        if (dateTaken == j2) {
                            dateTaken = getDateTaken(exifInterface2, j2);
                        }
                    }
                    contentValues2.put("exifGPSTimeStamp", attribute);
                    contentValues2.put("exifGPSDateStamp", attribute2);
                    contentValues2.put("exifDateTime", attribute3);
                    contentValues2.put("dateTaken", Long.valueOf(dateTaken));
                    contentValues2.put("mixedDateTime", Long.valueOf(dateTaken));
                    long parseFlagsForImage = SpecialTypeMediaUtils.parseFlagsForImage(str);
                    contentValues2.put("specialTypeFlags", Long.valueOf(parseFlagsForImage));
                    ExtraPhotoSDK.sendNewPhotoStatic(parseFlagsForImage);
                    if (!GalleryPreferences.SampleStatistic.hasUploadUserInfo()) {
                        try {
                            String xiaomiComment = ExifUtil.getXiaomiComment(str);
                            if (!TextUtils.isEmpty(xiaomiComment)) {
                                JSONObject jSONObject = new JSONObject(xiaomiComment);
                                if (TextUtils.equals(jSONObject.optString("sensor_type"), "front")) {
                                    jSONObject.put("imeimd5", Encode.MD5Encode(TelephonyHelper.getInstance().getDeviceId().getBytes(Keyczar.DEFAULT_ENCODING)));
                                    String jSONObject2 = jSONObject.toString();
                                    GalleryStatHelper.recordStringPropertyEvent("user_info", "self_shoot", jSONObject2);
                                    GalleryPreferences.SampleStatistic.setUploadedUserInfo(true);
                                    str3 = str4;
                                    try {
                                        Log.d(str3, "upload xiaomi comment %s", (Object) jSONObject2);
                                    } catch (Exception e) {
                                        e = e;
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            str3 = str4;
                            try {
                                Log.w(str3, (Throwable) e);
                            } catch (Throwable th) {
                                th = th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            str3 = str4;
                            try {
                                throw th;
                            } catch (Exception e3) {
                                e = e3;
                                Log.e(str3, "media scanner exif error %s", (Object) e);
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    str3 = str4;
                    Log.e(str3, "media scanner exif error %s", (Object) e);
                }
            } else {
                Object obj = "SaveToCloudUtil";
                BitmapFactory.Options bitmapSize2 = miui.graphics.BitmapFactory.getBitmapSize(str);
                int i = bitmapSize2.outWidth;
                int i2 = bitmapSize2.outHeight;
                contentValues2.put("exifImageWidth", Integer.valueOf(i));
                contentValues2.put("exifImageLength", Integer.valueOf(i2));
                contentValues2.put("exifOrientation", 0);
                contentValues2.put("dateTaken", Long.valueOf(j));
                contentValues2.put("mixedDateTime", Long.valueOf(j));
            }
        } catch (Exception e5) {
            e = e5;
            str3 = "SaveToCloudUtil";
            Log.e(str3, "media scanner exif error %s", (Object) e);
        }
    }

    private static void putValuesForVideo(Context context, String str, long j, ContentValues contentValues) {
        boolean z;
        ContentValues contentValues2 = contentValues;
        try {
            VideoAttrsReader read = VideoAttrsReader.read(str);
            long duration = read.getDuration() / 1000;
            contentValues2.put("duration", Long.valueOf(duration));
            contentValues2.put("exifImageWidth", Integer.valueOf(read.getVideoWidth()));
            contentValues2.put("exifImageLength", Integer.valueOf(read.getVideoHeight()));
            long dateTaken = read.getDateTaken();
            contentValues2.put("dateTaken", Long.valueOf(dateTaken));
            contentValues2.put("mixedDateTime", Long.valueOf(dateTaken));
            double latitude = read.getLatitude();
            double longitude = read.getLongitude();
            boolean z2 = true;
            if (MiscUtil.doubleEquals(latitude, 0.0d) || contentValues2.get("exifGPSLatitude") != null) {
                z = false;
            } else {
                contentValues2.put("exifGPSLatitude", LocationUtil.convertDoubleToLaLon(latitude));
                z = true;
            }
            if (MiscUtil.doubleEquals(longitude, 0.0d) || contentValues2.get("exifGPSLongitude") != null) {
                z2 = false;
            } else {
                contentValues2.put("exifGPSLongitude", LocationUtil.convertDoubleToLaLon(longitude));
            }
            if (z) {
                if (z2) {
                    Context context2 = context;
                    String str2 = str;
                    long parseFlagsForVideo = SpecialTypeMediaUtils.parseFlagsForVideo(str);
                    contentValues2.put("specialTypeFlags", Long.valueOf(parseFlagsForVideo));
                    ExtraPhotoSDK.sendNewPhotoStatic(parseFlagsForVideo);
                    statVideoInfo(context, duration, str, j);
                }
            }
            Context context3 = context;
            String str3 = str;
            ensureLocation(context, str, false, contentValues2);
            long parseFlagsForVideo2 = SpecialTypeMediaUtils.parseFlagsForVideo(str);
            contentValues2.put("specialTypeFlags", Long.valueOf(parseFlagsForVideo2));
            ExtraPhotoSDK.sendNewPhotoStatic(parseFlagsForVideo2);
            statVideoInfo(context, duration, str, j);
        } catch (Exception e) {
            Log.e("SaveToCloudUtil", "media scanner exif video error %s", (Object) e);
        }
    }

    private static void requestSync(Context context) {
        SyncUtil.requestSync(context, new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(33).setDelayUpload(true).build());
    }

    private static void requestThumbnail(Context context, ContentValues contentValues) {
        if (contentValues.getAsInteger("serverType").intValue() == 2) {
            final Context applicationContext = context.getApplicationContext();
            final String asString = contentValues.getAsString("localFile");
            ThreadManager.getWorkHandler().post(new Runnable() {
                public void run() {
                    SaveToCloudUtil.requestThumbnail(applicationContext, asString);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void requestThumbnail(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long mediaStoreId = MediaStoreUtils.getMediaStoreId(str);
        if (mediaStoreId > 0) {
            boolean isVideoFromMimeType = FileMimeUtil.isVideoFromMimeType(FileMimeUtil.getMimeType(str));
            SafeDBUtil.safeQuery(context, (isVideoFromMimeType ? MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI : MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI).buildUpon().appendQueryParameter("blocking", "1").appendQueryParameter("orig_id", String.valueOf(mediaStoreId)).build(), new String[]{"_data"}, isVideoFromMimeType ? "video_id=?" : "image_id=?", new String[]{String.valueOf(mediaStoreId)}, (String) null, (SafeDBUtil.QueryHandler) null);
        }
        Log.d("SaveToCloudUtil", "request thumbnail cost %d", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public static long saveToCloudDB(Context context, SaveParams saveParams) {
        if (saveParams == null) {
            return -3;
        }
        SaveToCloud parseSaveToCloud = parseSaveToCloud(saveParams);
        if (parseSaveToCloud == null) {
            return -1;
        }
        SystemClock.uptimeMillis();
        int prepareInsert = prepareInsert(context, parseSaveToCloud, saveParams, (ContentProviderBatchOperator) null);
        if (-7 == prepareInsert && saveParams.isAlbumSyncable()) {
            requestSync(context);
        }
        return prepareInsert == 0 ? insert(context, parseSaveToCloud, saveParams) : (long) prepareInsert;
    }

    public static long saveToCloudDB(Context context, SaveParams saveParams, MediaBulkInserter mediaBulkInserter, ContentProviderBatchOperator contentProviderBatchOperator) {
        if (saveParams == null) {
            return -3;
        }
        SaveToCloud parseSaveToCloud = parseSaveToCloud(saveParams);
        if (parseSaveToCloud == null) {
            return -1;
        }
        SystemClock.uptimeMillis();
        int prepareInsert = prepareInsert(context, parseSaveToCloud, saveParams, contentProviderBatchOperator);
        if (mediaBulkInserter == null && -7 == prepareInsert && saveParams.isAlbumSyncable()) {
            requestSync(context);
        }
        return prepareInsert == 0 ? insert(context, parseSaveToCloud, saveParams, mediaBulkInserter) : (long) prepareInsert;
    }

    private static void statFuzzyMatch(String str, String str2, String str3) {
        String format = String.format("scanner_fuzzy_match_%s", new Object[]{str});
        HashMap hashMap = new HashMap();
        hashMap.put("name_pair", String.format("%s_%s", new Object[]{str2, str3}));
        GallerySamplingStatHelper.recordCountEvent("media_scanner", format, hashMap);
    }

    private static void statVideoInfo(Context context, long j, String str, long j2) {
        if (j > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("duration", String.valueOf(j));
            hashMap.put(nexExportFormat.TAG_FORMAT_PATH, StorageUtils.getRelativePath(context, str));
            hashMap.put("size", String.format(Locale.US, "%.1fM", new Object[]{Float.valueOf(((float) j2) / 1000000.0f)}));
            GallerySamplingStatHelper.recordCountEvent("media_scanner", "video_duration", hashMap);
        }
    }

    /* access modifiers changed from: private */
    public static CloudEntry toCloudEntry(Cursor cursor) {
        CloudEntry cloudEntry = new CloudEntry();
        cloudEntry.mID = cursor.getLong(0);
        cloudEntry.mSize = cursor.getLong(1);
        cloudEntry.mName = cursor.getString(2);
        cloudEntry.mServerStatus = cursor.getString(3);
        cloudEntry.mLocalFlag = cursor.getInt(4);
        cloudEntry.mSha1 = cursor.getString(5);
        cloudEntry.mLocalPath = cursor.getString(6);
        cloudEntry.mThumbnailPath = cursor.getString(7);
        cloudEntry.mServerId = cursor.getString(8);
        cloudEntry.mDateTaken = cursor.getLong(9);
        return cloudEntry;
    }

    private static ContentValues toValues(Context context, SaveToCloud saveToCloud, long j, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("size", Long.valueOf(saveToCloud.mSize));
        contentValues.put("dateModified", Long.valueOf(saveToCloud.mLastModify));
        contentValues.put("mimeType", saveToCloud.mMimeType);
        contentValues.put("title", saveToCloud.mTitle);
        contentValues.put("fileName", saveToCloud.mName);
        contentValues.put("localFlag", Integer.valueOf(i));
        if (saveToCloud.mIsExifSha1) {
            contentValues.put("thumbnailFile", saveToCloud.mPath);
            contentValues.put("localFile", saveToCloud.mPath);
        } else {
            contentValues.put("localFile", saveToCloud.mPath);
        }
        contentValues.put("sha1", saveToCloud.mSha1);
        contentValues.put("localGroupId", Long.valueOf(j));
        if (FileMimeUtil.isImageFromMimeType(saveToCloud.mMimeType)) {
            contentValues.put("serverType", 1);
            putValuesForImage(saveToCloud.mPath, contentValues, saveToCloud.mLastModify);
        } else if (FileMimeUtil.isVideoFromMimeType(saveToCloud.mMimeType)) {
            contentValues.put("serverType", 2);
            putValuesForVideo(context, saveToCloud.mPath, saveToCloud.mSize, contentValues);
        }
        LocationManager.getInstance().appendExtraGpsInfo(saveToCloud.mPath, contentValues);
        return contentValues;
    }

    private static void tryEncryptFilePathForSecret(SaveToCloud saveToCloud) {
        String encodeFileName = CloudUtils.SecretAlbumUtils.encodeFileName(saveToCloud.mName, FileMimeUtil.isVideoFromMimeType(saveToCloud.mMimeType));
        if (!TextUtils.equals(encodeFileName, saveToCloud.mName)) {
            String str = saveToCloud.mPath;
            File file = new File(FileUtils.getParentFolderPath(str), encodeFileName);
            if (FileUtils.move(new File(str), file)) {
                saveToCloud.mPath = file.getAbsolutePath();
                saveToCloud.mLastModify = file.lastModified();
            }
        }
    }

    private static void update(Context context, ContentValues contentValues, long j, SaveParams saveParams, ContentProviderBatchOperator contentProviderBatchOperator) {
        Uri writeUri = getWriteUri(saveParams);
        if (contentProviderBatchOperator != null) {
            contentProviderBatchOperator.add(context, ContentProviderOperation.newUpdate(writeUri).withSelection("_id=?", new String[]{String.valueOf(j)}).withValues(contentValues).build());
            return;
        }
        SafeDBUtil.safeUpdate(context, writeUri, contentValues, "_id=?", new String[]{String.valueOf(j)});
    }
}
