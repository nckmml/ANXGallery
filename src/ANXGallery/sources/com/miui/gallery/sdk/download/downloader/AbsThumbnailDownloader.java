package com.miui.gallery.sdk.download.downloader;

import android.accounts.Account;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Base64;
import androidx.documentfile.provider.DocumentFile;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.CheckResult;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryMiCloudServerException;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.error.core.ErrorCode;
import com.miui.gallery.error.util.ErrorParseUtil;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.preference.ThumbnailPreference;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.sdk.download.assist.DownloadFailReason;
import com.miui.gallery.sdk.download.assist.DownloadItem;
import com.miui.gallery.sdk.download.assist.DownloadedItem;
import com.miui.gallery.sdk.download.assist.RequestItem;
import com.miui.gallery.sdk.download.util.DownloadUtil;
import com.miui.gallery.util.CryptoUtil;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MiscUtil;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.keyczar.Keyczar;

abstract class AbsThumbnailDownloader implements IDownloader {
    private static final int MAX_TRY_COUNT = CloudControlStrategyHelper.getSyncStrategy().getMaxDownloadTimes();

    AbsThumbnailDownloader() {
    }

    private boolean checkConditionPermitted(DownloadItem downloadItem) {
        DownloadFailReason checkCondition = DownloadUtil.checkCondition(downloadItem);
        if (checkCondition == null) {
            return true;
        }
        if (!downloadItem.isStatusOk()) {
            return false;
        }
        DownloadItem.callbackError(downloadItem, checkCondition);
        return false;
    }

    private boolean checkItemValid(RequestItem requestItem) {
        DBImage dBImage = requestItem.mDBItem;
        if (DownloadUtil.canDownloadStatus(dBImage)) {
            return true;
        }
        if (dBImage == null) {
            fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.PARAMS_ERROR, "dbImage null", (Throwable) null));
        } else if (DownloadUtil.isNotSyncedStatus(dBImage)) {
            fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.NOT_SYNCED, String.format("item invalid server[%s], local[%s]", new Object[]{dBImage.getServerStatus(), Integer.valueOf(dBImage.getLocalFlag())}), (Throwable) null));
        } else {
            fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.PARAMS_ERROR, String.format("item invalid server[%s], local[%s]", new Object[]{dBImage.getServerStatus(), Integer.valueOf(dBImage.getLocalFlag())}), (Throwable) null));
        }
        return false;
    }

    private static HashMap<String, List<RequestItem>> classifyRequest(List<RequestItem> list) {
        HashMap<String, List<RequestItem>> hashMap = new HashMap<>();
        for (RequestItem next : list) {
            String valueOf = next.mDBItem.isShareItem() ? String.valueOf(next.mDBItem.getGroupId()) : "owner";
            List list2 = hashMap.get(valueOf);
            if (list2 == null) {
                list2 = new LinkedList();
                hashMap.put(valueOf, list2);
            }
            list2.add(next);
        }
        return hashMap;
    }

    private void decreaseConnTimeout(DownloadType downloadType) {
        int connTimeout = GalleryPreferences.FileDownload.getConnTimeout(downloadType) - 1000;
        if (GalleryPreferences.FileDownload.setConnTimeout(downloadType, connTimeout)) {
            Log.i(getTag(), "decrease conn timeout %d, type %s", Integer.valueOf(connTimeout), downloadType.name());
        }
    }

    private void deleteTempFile(RequestItem requestItem) {
        if (requestItem.mDBItem != null) {
            String downloadTempFilePath = DownloadUtil.getDownloadTempFilePath(requestItem.mDBItem, requestItem.mDownloadItem.getType());
            MediaFileUtils.deleteFileType(MediaFileUtils.FileType.TEMP, downloadTempFilePath);
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:36:0x008a=Splitter:B:36:0x008a, B:42:0x009b=Splitter:B:42:0x009b, B:48:0x00ac=Splitter:B:48:0x00ac} */
    private String downloadData(RequestItem requestItem, String str) {
        ParcelFileDescriptor parcelFileDescriptor;
        FileOutputStream fileOutputStream;
        File file = new File(DownloadUtil.getDownloadTempFilePath(requestItem.mDBItem, requestItem.mDownloadItem.getType()));
        FileOutputStream fileOutputStream2 = null;
        try {
            if (DocumentProviderUtils.needUseDocumentProvider(file.getAbsolutePath())) {
                DocumentFile documentFile = DocumentProviderUtils.getDocumentFile(GalleryApp.sGetAndroidContext(), file);
                if (documentFile == null) {
                    MiscUtil.closeSilently((Closeable) null);
                    MiscUtil.closeSilently((Closeable) null);
                    return null;
                }
                ParcelFileDescriptor openFileDescriptor = GalleryApp.sGetAndroidContext().getContentResolver().openFileDescriptor(documentFile.getUri(), "w");
                try {
                    parcelFileDescriptor = openFileDescriptor;
                    fileOutputStream = new FileOutputStream(openFileDescriptor.getFileDescriptor());
                } catch (UnsupportedEncodingException e) {
                    e = e;
                    parcelFileDescriptor = openFileDescriptor;
                    fileOutputStream = null;
                    Log.e(getTag(), "download data error.", (Object) e);
                    MiscUtil.closeSilently(fileOutputStream);
                    MiscUtil.closeSilently(parcelFileDescriptor);
                    return null;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    parcelFileDescriptor = openFileDescriptor;
                    fileOutputStream = null;
                    Log.e(getTag(), "download data error.", (Object) e);
                    MiscUtil.closeSilently(fileOutputStream);
                    MiscUtil.closeSilently(parcelFileDescriptor);
                    return null;
                } catch (IOException e3) {
                    e = e3;
                    parcelFileDescriptor = openFileDescriptor;
                    fileOutputStream = null;
                    try {
                        Log.e(getTag(), "download data error.", (Object) e);
                        MiscUtil.closeSilently(fileOutputStream);
                        MiscUtil.closeSilently(parcelFileDescriptor);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream2 = fileOutputStream;
                        MiscUtil.closeSilently(fileOutputStream2);
                        MiscUtil.closeSilently(parcelFileDescriptor);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelFileDescriptor = openFileDescriptor;
                    MiscUtil.closeSilently(fileOutputStream2);
                    MiscUtil.closeSilently(parcelFileDescriptor);
                    throw th;
                }
            } else {
                fileOutputStream = new FileOutputStream(file, false);
                parcelFileDescriptor = null;
            }
            try {
                fileOutputStream.write(Base64.decode(str.getBytes(Keyczar.DEFAULT_ENCODING), 2));
                MiscUtil.closeSilently(fileOutputStream);
                MiscUtil.closeSilently(parcelFileDescriptor);
                return file.getAbsolutePath();
            } catch (UnsupportedEncodingException e4) {
                e = e4;
                Log.e(getTag(), "download data error.", (Object) e);
                MiscUtil.closeSilently(fileOutputStream);
                MiscUtil.closeSilently(parcelFileDescriptor);
                return null;
            } catch (FileNotFoundException e5) {
                e = e5;
                Log.e(getTag(), "download data error.", (Object) e);
                MiscUtil.closeSilently(fileOutputStream);
                MiscUtil.closeSilently(parcelFileDescriptor);
                return null;
            } catch (IOException e6) {
                e = e6;
                Log.e(getTag(), "download data error.", (Object) e);
                MiscUtil.closeSilently(fileOutputStream);
                MiscUtil.closeSilently(parcelFileDescriptor);
                return null;
            }
        } catch (UnsupportedEncodingException e7) {
            e = e7;
            fileOutputStream = null;
            parcelFileDescriptor = null;
            Log.e(getTag(), "download data error.", (Object) e);
            MiscUtil.closeSilently(fileOutputStream);
            MiscUtil.closeSilently(parcelFileDescriptor);
            return null;
        } catch (FileNotFoundException e8) {
            e = e8;
            fileOutputStream = null;
            parcelFileDescriptor = null;
            Log.e(getTag(), "download data error.", (Object) e);
            MiscUtil.closeSilently(fileOutputStream);
            MiscUtil.closeSilently(parcelFileDescriptor);
            return null;
        } catch (IOException e9) {
            e = e9;
            fileOutputStream = null;
            parcelFileDescriptor = null;
            Log.e(getTag(), "download data error.", (Object) e);
            MiscUtil.closeSilently(fileOutputStream);
            MiscUtil.closeSilently(parcelFileDescriptor);
            return null;
        } catch (Throwable th3) {
            th = th3;
            parcelFileDescriptor = null;
            MiscUtil.closeSilently(fileOutputStream2);
            MiscUtil.closeSilently(parcelFileDescriptor);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02f5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0370, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0373, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0374, code lost:
        r3 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0379, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x037a, code lost:
        r3 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0380, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0381, code lost:
        r3 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0387, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0388, code lost:
        r3 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x038b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x038c, code lost:
        r3 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x038f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0390, code lost:
        r21 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0393, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0394, code lost:
        r21 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0398, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x039d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03a2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03a3, code lost:
        r21 = r5;
        r19 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x03b1, code lost:
        r1.fireFailEvent(r2, r3, new com.miui.gallery.sdk.download.assist.DownloadFailReason(com.miui.gallery.error.util.ErrorParseUtil.parseError(r0, r21.getAbsolutePath()), r0.getMessage(), r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x03c6, code lost:
        com.miui.gallery.util.Log.e(getTag(), r0);
        deleteTempFile(r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x03db, code lost:
        r6.disconnect();
        r6 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0417, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0418, code lost:
        r21 = r5;
        r19 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x042d, code lost:
        r19 = r9;
        r1.fireFailEvent(r2, r3, new com.miui.gallery.sdk.download.assist.DownloadFailReason(com.miui.gallery.error.core.ErrorCode.SOCKET_TIMEOUT, java.lang.String.format("socket timeout: %s", new java.lang.Object[]{r3}), r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0446, code lost:
        r19 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0453, code lost:
        r6.disconnect();
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0486, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0487, code lost:
        r21 = r5;
        r19 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x050b, code lost:
        r13.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x011c, code lost:
        com.miui.gallery.util.Log.d(r0, r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0120, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01b6, code lost:
        r21 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01b8, code lost:
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01bc, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bd, code lost:
        r21 = r5;
        r6 = r13;
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01c5, code lost:
        r21 = r5;
        r6 = r13;
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01cc, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01cd, code lost:
        r21 = r5;
        r6 = r13;
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01e6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01e9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01fa A[SYNTHETIC, Splitter:B:100:0x01fa] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0260 A[SYNTHETIC, Splitter:B:110:0x0260] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0271 A[SYNTHETIC, Splitter:B:115:0x0271] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x02f5 A[ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), PHI: r1 r10 r11 r12 
      PHI: (r1v10 com.miui.gallery.sdk.download.downloader.AbsThumbnailDownloader) = (r1v1 com.miui.gallery.sdk.download.downloader.AbsThumbnailDownloader), (r1v1 com.miui.gallery.sdk.download.downloader.AbsThumbnailDownloader), (r1v1 com.miui.gallery.sdk.download.downloader.AbsThumbnailDownloader), (r1v15 com.miui.gallery.sdk.download.downloader.AbsThumbnailDownloader), (r1v15 com.miui.gallery.sdk.download.downloader.AbsThumbnailDownloader), (r1v1 com.miui.gallery.sdk.download.downloader.AbsThumbnailDownloader) binds: [B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r10v14 java.io.FileOutputStream) = (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v19 java.io.FileOutputStream), (r10v19 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream) binds: [B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v17 android.os.ParcelFileDescriptor) = (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v22 android.os.ParcelFileDescriptor), (r11v22 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor) binds: [B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r12v17 java.io.InputStream) = (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream) binds: [B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260] A[DONT_GENERATE, DONT_INLINE], Splitter:B:97:0x01f0] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0370 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:144:0x0312] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0398 A[ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), PHI: r11 r12 r19 
      PHI: (r11v13 android.os.ParcelFileDescriptor) = (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v23 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor) binds: [B:87:0x01d4, B:100:0x01fa, B:107:0x0242, B:108:?, B:91:0x01df, B:57:0x0140, B:60:0x0146, B:61:?, B:31:0x0095, B:34:0x00da, B:35:?, B:36:0x00dd, B:37:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r12v13 java.io.InputStream) = (r12v1 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream) binds: [B:87:0x01d4, B:100:0x01fa, B:107:0x0242, B:108:?, B:91:0x01df, B:57:0x0140, B:60:0x0146, B:61:?, B:31:0x0095, B:34:0x00da, B:35:?, B:36:0x00dd, B:37:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r19v20 int) = (r19v27 int), (r19v27 int), (r19v27 int), (r19v27 int), (r19v27 int), (r19v30 int), (r19v30 int), (r19v30 int), (r19v34 int), (r19v34 int), (r19v34 int), (r19v34 int), (r19v34 int) binds: [B:87:0x01d4, B:100:0x01fa, B:107:0x0242, B:108:?, B:91:0x01df, B:57:0x0140, B:60:0x0146, B:61:?, B:31:0x0095, B:34:0x00da, B:35:?, B:36:0x00dd, B:37:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:31:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x039d A[ExcHandler: all (th java.lang.Throwable), PHI: r10 r11 r12 r13 
      PHI: (r10v11 java.io.FileOutputStream) = (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v19 java.io.FileOutputStream), (r10v19 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream), (r10v1 java.io.FileOutputStream) binds: [B:5:0x002d, B:19:0x005e, B:87:0x01d4, B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260, B:100:0x01fa, B:107:0x0242, B:108:?, B:91:0x01df, B:57:0x0140, B:60:0x0146, B:61:?, B:28:0x0080, B:31:0x0095, B:34:0x00da, B:35:?, B:36:0x00dd, B:37:?, B:9:0x0047] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v11 android.os.ParcelFileDescriptor) = (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v22 android.os.ParcelFileDescriptor), (r11v22 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v23 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor), (r11v1 android.os.ParcelFileDescriptor) binds: [B:5:0x002d, B:19:0x005e, B:87:0x01d4, B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260, B:100:0x01fa, B:107:0x0242, B:108:?, B:91:0x01df, B:57:0x0140, B:60:0x0146, B:61:?, B:28:0x0080, B:31:0x0095, B:34:0x00da, B:35:?, B:36:0x00dd, B:37:?, B:9:0x0047] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r12v11 java.io.InputStream) = (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v18 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream), (r12v1 java.io.InputStream) binds: [B:5:0x002d, B:19:0x005e, B:87:0x01d4, B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260, B:100:0x01fa, B:107:0x0242, B:108:?, B:91:0x01df, B:57:0x0140, B:60:0x0146, B:61:?, B:28:0x0080, B:31:0x0095, B:34:0x00da, B:35:?, B:36:0x00dd, B:37:?, B:9:0x0047] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v19 java.net.HttpURLConnection) = (r13v1 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection) binds: [B:5:0x002d, B:19:0x005e, B:87:0x01d4, B:141:0x02fc, B:142:?, B:97:0x01f0, B:119:0x028f, B:120:?, B:110:0x0260, B:100:0x01fa, B:107:0x0242, B:108:?, B:91:0x01df, B:57:0x0140, B:60:0x0146, B:61:?, B:28:0x0080, B:31:0x0095, B:34:0x00da, B:35:?, B:36:0x00dd, B:37:?, B:9:0x0047] A[DONT_GENERATE, DONT_INLINE], Splitter:B:87:0x01d4] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x03b1 A[Catch:{ all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x03c6 A[Catch:{ all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x03db  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x042d A[Catch:{ all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0446  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0453  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0486 A[ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), PHI: r13 
      PHI: (r13v9 java.net.HttpURLConnection) = (r13v1 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection), (r13v20 java.net.HttpURLConnection) binds: [B:5:0x002d, B:19:0x005e, B:9:0x0047] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x049b A[Catch:{ all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04b4  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x04c1  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x050b  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0284 A[EDGE_INSN: B:238:0x0284->B:117:0x0284 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b5 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:63:0x014f] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01cc A[ExcHandler: ConnectTimeoutException (e org.apache.http.conn.ConnectTimeoutException), Splitter:B:63:0x014f] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01df A[SYNTHETIC, Splitter:B:91:0x01df] */
    private String downloadFile(RequestItem requestItem, String str) {
        File file;
        int i;
        long currentTimeMillis;
        File file2;
        HttpURLConnection httpURLConnection;
        int i2;
        Object[] objArr;
        String str2;
        int i3;
        int i4;
        int i5;
        File file3;
        int i6;
        File file4;
        HttpURLConnection httpURLConnection2;
        HttpURLConnection httpURLConnection3;
        long contentLength;
        long j;
        String str3;
        int i7;
        int i8;
        byte[] bArr;
        int read;
        String tag;
        Object[] objArr2;
        AbsThumbnailDownloader absThumbnailDownloader = this;
        RequestItem requestItem2 = requestItem;
        String str4 = str;
        String str5 = "download %s, origin file %s, cost %d, success %s";
        File file5 = new File(DownloadUtil.getDownloadTempFilePath(requestItem2.mDBItem, requestItem2.mDownloadItem.getType()));
        boolean z = true;
        int i9 = 0;
        FileOutputStream fileOutputStream = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection4 = null;
        while (true) {
            if (!z || i9 >= MAX_TRY_COUNT) {
                file = file5;
                i = i9;
            } else {
                currentTimeMillis = System.currentTimeMillis();
                try {
                    httpURLConnection4 = NetworkUtils.getHttpConn(str4, NetworkUtils.RequestType.GET);
                    int connTimeout = GalleryPreferences.FileDownload.getConnTimeout(requestItem2.mDownloadItem.getType());
                    if (requestItem2.mDBItem.isVideoType()) {
                        connTimeout = GalleryPreferences.FileDownload.clampConnTimeout(connTimeout * 2);
                    }
                    httpURLConnection4.setConnectTimeout(connTimeout);
                    httpURLConnection4.setReadTimeout(connTimeout);
                    httpURLConnection4.connect();
                    int responseCode = httpURLConnection4.getResponseCode();
                    if (responseCode >= 200) {
                        if (responseCode < 300) {
                            i3 = i9;
                            try {
                                contentLength = (long) httpURLConnection4.getContentLength();
                                j = 0;
                                if (contentLength <= 0) {
                                    contentLength = requestItem2.mDBItem.getSize();
                                }
                                try {
                                    inputStream = httpURLConnection4.getInputStream();
                                    if (!DocumentProviderUtils.needUseDocumentProvider(file5.getAbsolutePath())) {
                                        DocumentFile documentFile = DocumentProviderUtils.getDocumentFile(GalleryApp.sGetAndroidContext(), file5);
                                        if (documentFile == null) {
                                            MiscUtil.closeSilently(inputStream);
                                            MiscUtil.closeSilently(fileOutputStream);
                                            MiscUtil.closeSilently(parcelFileDescriptor);
                                            if (httpURLConnection4 != null) {
                                                httpURLConnection4.disconnect();
                                            }
                                            tag = getTag();
                                            objArr2 = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file5.getAbsolutePath()))};
                                        } else {
                                            parcelFileDescriptor = GalleryApp.sGetAndroidContext().getContentResolver().openFileDescriptor(documentFile.getUri(), "w");
                                            fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                                            i7 = 0;
                                        }
                                    } else {
                                        i7 = 0;
                                        fileOutputStream = new FileOutputStream(file5, false);
                                    }
                                    i8 = 8192;
                                    bArr = new byte[8192];
                                    while (true) {
                                        read = inputStream.read(bArr, i7, i8);
                                        if (read >= 0) {
                                            break;
                                        }
                                        try {
                                            fileOutputStream.write(bArr, i7, read);
                                            long j2 = j + ((long) read);
                                            DownloadItem.callbackProgress(requestItem2.mDownloadItem, j2, contentLength);
                                            j = j2;
                                            i8 = 8192;
                                            i7 = 0;
                                        } catch (ConnectTimeoutException e) {
                                            e = e;
                                            absThumbnailDownloader = this;
                                            str4 = str;
                                            file3 = file5;
                                            HttpURLConnection httpURLConnection5 = httpURLConnection4;
                                            absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                                            i4 = i3 + 1;
                                            if (i4 >= MAX_TRY_COUNT) {
                                                i5 = i4;
                                                absThumbnailDownloader.fireFailEvent(requestItem2, str4, new DownloadFailReason(ErrorCode.CONNECT_TIMEOUT, String.format("connect timeout: %s", new Object[]{str4}), e));
                                            } else {
                                                i5 = i4;
                                            }
                                            MiscUtil.closeSilently(inputStream);
                                            MiscUtil.closeSilently(fileOutputStream);
                                            MiscUtil.closeSilently(parcelFileDescriptor);
                                            if (httpURLConnection != null) {
                                                httpURLConnection.disconnect();
                                                httpURLConnection = null;
                                            }
                                            str2 = getTag();
                                            objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                                            Log.d(str2, str5, objArr);
                                            httpURLConnection4 = httpURLConnection;
                                            i9 = i2;
                                            z = true;
                                            file5 = file2;
                                        } catch (SocketTimeoutException e2) {
                                            e = e2;
                                            absThumbnailDownloader = this;
                                            str4 = str;
                                            file4 = file5;
                                            httpURLConnection2 = httpURLConnection4;
                                            absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                                            i6 = i3 + 1;
                                            if (i6 >= MAX_TRY_COUNT) {
                                            }
                                            MiscUtil.closeSilently(inputStream);
                                            MiscUtil.closeSilently(fileOutputStream);
                                            MiscUtil.closeSilently(parcelFileDescriptor);
                                            if (httpURLConnection != null) {
                                            }
                                            str2 = getTag();
                                            objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                                            Log.d(str2, str5, objArr);
                                            httpURLConnection4 = httpURLConnection;
                                            i9 = i2;
                                            z = true;
                                            file5 = file2;
                                        } catch (Throwable th) {
                                            th = th;
                                            file2 = file5;
                                            MiscUtil.closeSilently(inputStream);
                                            MiscUtil.closeSilently(fileOutputStream);
                                            MiscUtil.closeSilently(parcelFileDescriptor);
                                            if (httpURLConnection4 != null) {
                                            }
                                            Log.d(getTag(), str5, requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath())));
                                            throw th;
                                        }
                                    }
                                    fileOutputStream.flush();
                                    absThumbnailDownloader = this;
                                    absThumbnailDownloader.decreaseConnTimeout(requestItem2.mDownloadItem.getType());
                                    DownloadUtil.statDownloadSuccess(requestItem2, currentTimeMillis, contentLength);
                                    MiscUtil.closeSilently(inputStream);
                                    MiscUtil.closeSilently(fileOutputStream);
                                    MiscUtil.closeSilently(parcelFileDescriptor);
                                    if (httpURLConnection4 != null) {
                                        httpURLConnection4.disconnect();
                                    }
                                    Log.d(getTag(), str5, requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file5.getAbsolutePath())));
                                    i = i3 + 1;
                                    file = file5;
                                } catch (FileNotFoundException e3) {
                                    FileNotFoundException fileNotFoundException = e3;
                                    ThumbnailPreference.putThumbnailKey(requestItem2.mDBItem.getSha1());
                                    str3 = str5;
                                    file3 = file5;
                                    try {
                                        DownloadFailReason downloadFailReason = new DownloadFailReason(ErrorCode.THUMBNAIL_BUILD_ERROR, String.format(Locale.US, "thumbnail build error, ret %s", new Object[]{Integer.valueOf(responseCode)}), fileNotFoundException);
                                        str4 = str;
                                        absThumbnailDownloader.fireFailEvent(requestItem2, str4, downloadFailReason);
                                        MiscUtil.closeSilently(inputStream);
                                        MiscUtil.closeSilently(fileOutputStream);
                                        MiscUtil.closeSilently(parcelFileDescriptor);
                                        if (httpURLConnection4 != null) {
                                            httpURLConnection4.disconnect();
                                        }
                                        Log.d(getTag(), str3, requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file3.getAbsolutePath())));
                                        return null;
                                    } catch (ConnectTimeoutException e4) {
                                        e = e4;
                                    } catch (SocketTimeoutException e5) {
                                        e = e5;
                                        str5 = str3;
                                        httpURLConnection2 = httpURLConnection4;
                                        absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                                        i6 = i3 + 1;
                                        if (i6 >= MAX_TRY_COUNT) {
                                        }
                                        MiscUtil.closeSilently(inputStream);
                                        MiscUtil.closeSilently(fileOutputStream);
                                        MiscUtil.closeSilently(parcelFileDescriptor);
                                        if (httpURLConnection != null) {
                                        }
                                        str2 = getTag();
                                        objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                                        Log.d(str2, str5, objArr);
                                        httpURLConnection4 = httpURLConnection;
                                        i9 = i2;
                                        z = true;
                                        file5 = file2;
                                    } catch (Throwable th2) {
                                    }
                                }
                            } catch (ConnectTimeoutException e6) {
                            } catch (SocketTimeoutException e7) {
                                e = e7;
                                str4 = str;
                                file4 = file5;
                                httpURLConnection2 = httpURLConnection4;
                                absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                                i6 = i3 + 1;
                                if (i6 >= MAX_TRY_COUNT) {
                                }
                                MiscUtil.closeSilently(inputStream);
                                MiscUtil.closeSilently(fileOutputStream);
                                MiscUtil.closeSilently(parcelFileDescriptor);
                                if (httpURLConnection != null) {
                                }
                                str2 = getTag();
                                objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                                Log.d(str2, str5, objArr);
                                httpURLConnection4 = httpURLConnection;
                                i9 = i2;
                                z = true;
                                file5 = file2;
                            } catch (Throwable th3) {
                            }
                        }
                    }
                    if (CheckResult.checkKSSThumbnailResult(responseCode) == GallerySyncCode.NOT_RETRY_ERROR) {
                        try {
                            ThumbnailPreference.putThumbnailKey(requestItem2.mDBItem.getSha1());
                            i3 = i9;
                            try {
                                Log.e(getTag(), "thumbnail can't be download forever, just add sha1 to preference %d, %s", Integer.valueOf(responseCode), requestItem2.mDBItem.getSha1());
                                HashMap hashMap = new HashMap();
                                hashMap.put("statusCode", String.valueOf(responseCode));
                                hashMap.put("downloadType", String.valueOf(requestItem2.mDownloadItem.getType()));
                                GallerySamplingStatHelper.recordErrorEvent("Sync", "sync_thumbnail_build_error", hashMap);
                                httpURLConnection3 = null;
                                absThumbnailDownloader.fireFailEvent(requestItem2, str4, new DownloadFailReason(ErrorCode.THUMBNAIL_BUILD_ERROR, String.format("thumbnail can't build: %s", new Object[]{requestItem2.mDBItem.getFileName()}), (Throwable) null));
                                MiscUtil.closeSilently(inputStream);
                                MiscUtil.closeSilently(fileOutputStream);
                                MiscUtil.closeSilently(parcelFileDescriptor);
                                if (httpURLConnection4 != null) {
                                    httpURLConnection4.disconnect();
                                }
                                tag = getTag();
                                objArr2 = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file5.getAbsolutePath()))};
                            } catch (ConnectTimeoutException e8) {
                            } catch (SocketTimeoutException e9) {
                                e = e9;
                                file3 = file5;
                                httpURLConnection2 = httpURLConnection4;
                                absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                                i6 = i3 + 1;
                                if (i6 >= MAX_TRY_COUNT) {
                                }
                                MiscUtil.closeSilently(inputStream);
                                MiscUtil.closeSilently(fileOutputStream);
                                MiscUtil.closeSilently(parcelFileDescriptor);
                                if (httpURLConnection != null) {
                                }
                                str2 = getTag();
                                objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                                Log.d(str2, str5, objArr);
                                httpURLConnection4 = httpURLConnection;
                                i9 = i2;
                                z = true;
                                file5 = file2;
                            } catch (Throwable th32) {
                            }
                        } catch (ConnectTimeoutException e10) {
                            e = e10;
                            i3 = i9;
                        } catch (SocketTimeoutException e11) {
                            e = e11;
                            i3 = i9;
                            file4 = file5;
                            httpURLConnection2 = httpURLConnection4;
                            absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                            i6 = i3 + 1;
                            if (i6 >= MAX_TRY_COUNT) {
                            }
                            MiscUtil.closeSilently(inputStream);
                            MiscUtil.closeSilently(fileOutputStream);
                            MiscUtil.closeSilently(parcelFileDescriptor);
                            if (httpURLConnection != null) {
                            }
                            str2 = getTag();
                            objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                            Log.d(str2, str5, objArr);
                            httpURLConnection4 = httpURLConnection;
                            i9 = i2;
                            z = true;
                            file5 = file2;
                        } catch (Throwable th322) {
                        }
                    } else {
                        i3 = i9;
                        if (CheckResult.isNotRetryCode(responseCode)) {
                            str3 = str5;
                            try {
                                absThumbnailDownloader.fireFailEvent(requestItem2, str4, new DownloadFailReason(ErrorCode.SERVER_ERROR, String.format("server ret: %s", new Object[]{Integer.valueOf(responseCode)}), (Throwable) null));
                                MiscUtil.closeSilently(inputStream);
                                MiscUtil.closeSilently(fileOutputStream);
                                MiscUtil.closeSilently(parcelFileDescriptor);
                                if (httpURLConnection4 != null) {
                                    httpURLConnection4.disconnect();
                                }
                                tag = getTag();
                                objArr2 = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file5.getAbsolutePath()))};
                                str5 = str3;
                            } catch (ConnectTimeoutException e12) {
                            } catch (SocketTimeoutException e13) {
                                e = e13;
                                file4 = file5;
                                httpURLConnection2 = httpURLConnection4;
                                str5 = str3;
                                absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                                i6 = i3 + 1;
                                if (i6 >= MAX_TRY_COUNT) {
                                }
                                MiscUtil.closeSilently(inputStream);
                                MiscUtil.closeSilently(fileOutputStream);
                                MiscUtil.closeSilently(parcelFileDescriptor);
                                if (httpURLConnection != null) {
                                }
                                str2 = getTag();
                                objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                                Log.d(str2, str5, objArr);
                                httpURLConnection4 = httpURLConnection;
                                i9 = i2;
                                z = true;
                                file5 = file2;
                            } catch (Throwable th4) {
                            }
                        }
                        contentLength = (long) httpURLConnection4.getContentLength();
                        j = 0;
                        if (contentLength <= 0) {
                        }
                        inputStream = httpURLConnection4.getInputStream();
                        if (!DocumentProviderUtils.needUseDocumentProvider(file5.getAbsolutePath())) {
                        }
                        i8 = 8192;
                        bArr = new byte[8192];
                        while (true) {
                            read = inputStream.read(bArr, i7, i8);
                            if (read >= 0) {
                            }
                            fileOutputStream.write(bArr, i7, read);
                            long j22 = j + ((long) read);
                            DownloadItem.callbackProgress(requestItem2.mDownloadItem, j22, contentLength);
                            j = j22;
                            i8 = 8192;
                            i7 = 0;
                        }
                        fileOutputStream.flush();
                        absThumbnailDownloader = this;
                        absThumbnailDownloader.decreaseConnTimeout(requestItem2.mDownloadItem.getType());
                        DownloadUtil.statDownloadSuccess(requestItem2, currentTimeMillis, contentLength);
                        MiscUtil.closeSilently(inputStream);
                        MiscUtil.closeSilently(fileOutputStream);
                        MiscUtil.closeSilently(parcelFileDescriptor);
                        if (httpURLConnection4 != null) {
                        }
                        Log.d(getTag(), str5, requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file5.getAbsolutePath())));
                        i = i3 + 1;
                        file = file5;
                    }
                } catch (ConnectTimeoutException e14) {
                } catch (SocketTimeoutException e15) {
                    e = e15;
                    file4 = file5;
                    i3 = i9;
                    httpURLConnection2 = httpURLConnection4;
                    absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
                    i6 = i3 + 1;
                    if (i6 >= MAX_TRY_COUNT) {
                    }
                    MiscUtil.closeSilently(inputStream);
                    MiscUtil.closeSilently(fileOutputStream);
                    MiscUtil.closeSilently(parcelFileDescriptor);
                    if (httpURLConnection != null) {
                    }
                    str2 = getTag();
                    objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
                    Log.d(str2, str5, objArr);
                    httpURLConnection4 = httpURLConnection;
                    i9 = i2;
                    z = true;
                    file5 = file2;
                } catch (Throwable th3222) {
                }
            }
            file5 = file2;
        }
        file = file5;
        i = i9;
        DownloadUtil.statDownloadRetryTimes(requestItem2, i, MAX_TRY_COUNT);
        return file.getAbsolutePath();
        str5 = str3;
        HttpURLConnection httpURLConnection52 = httpURLConnection4;
        absThumbnailDownloader.increaseConnTimeout(requestItem2.mDownloadItem.getType());
        i4 = i3 + 1;
        if (i4 >= MAX_TRY_COUNT) {
        }
        MiscUtil.closeSilently(inputStream);
        MiscUtil.closeSilently(fileOutputStream);
        MiscUtil.closeSilently(parcelFileDescriptor);
        if (httpURLConnection != null) {
        }
        str2 = getTag();
        objArr = new Object[]{requestItem2.mDownloadItem, requestItem2.mDBItem.getFileName(), Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Boolean.valueOf(FileUtils.isFileExist(file2.getAbsolutePath()))};
        Log.d(str2, str5, objArr);
        httpURLConnection4 = httpURLConnection;
        i9 = i2;
        z = true;
        file5 = file2;
    }

    private void downloadInternal(Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, List<RequestItem> list) {
        JSONObject requestUrls = requestUrls(account, galleryExtendedAuthToken, list);
        if (requestUrls != null) {
            doFileDownload(list, requestUrls);
        }
    }

    private void fireFailEvent(RequestItem requestItem, String str, DownloadFailReason downloadFailReason) {
        Log.d(getTag(), "download fail %s", (Object) downloadFailReason);
        if ((downloadFailReason != null ? downloadFailReason.getCause() : null) != null) {
            Log.e(getTag(), downloadFailReason.getCause());
        }
        deleteTempFile(requestItem);
        if (requestItem.mDownloadItem.isStatusOk()) {
            DownloadItem.callbackError(requestItem.mDownloadItem, downloadFailReason);
        }
        DownloadUtil.statDownloadError(requestItem, str, downloadFailReason);
    }

    private String getRequestUrl(RequestItem requestItem, String str) {
        return requestItem.mDBItem.isShareItem() ? HostManager.ShareMedia.getRequestThumbnailUrl() : HostManager.OwnerMedia.getRequestThumbnailUrl();
    }

    private void increaseConnTimeout(DownloadType downloadType) {
        int connTimeout = GalleryPreferences.FileDownload.getConnTimeout(downloadType) + 3000;
        if (GalleryPreferences.FileDownload.setConnTimeout(downloadType, connTimeout)) {
            Log.i(getTag(), "increase conn timeout %d, type %s", Integer.valueOf(connTimeout), downloadType.name());
        }
    }

    private boolean isValidUrl(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void onPostDownload(RequestItem requestItem, String str) {
        if (FileUtils.isFileExist(str)) {
            byte[] bArr = null;
            if (!handleDownloadTempFile(requestItem, str)) {
                fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.WRITE_EXIF_ERROR, "write exif error", (Throwable) null));
                return;
            }
            ErrorCode ensureDownloadFolder = DownloadUtil.ensureDownloadFolder(requestItem.mDBItem, requestItem.mDownloadItem.getType());
            if (ensureDownloadFolder != ErrorCode.NO_ERROR) {
                fireFailEvent(requestItem, (String) null, new DownloadFailReason(ensureDownloadFolder, String.format(Locale.US, "error create folder: %s", new Object[]{DownloadUtil.getDownloadFolderPath(requestItem.mDBItem, requestItem.mDownloadItem.getType())}), (Throwable) null));
            } else if (requestItem.mDownloadItem.isCancelled()) {
                Log.d(getTag(), "downloading for image[%s] is cancelled", (Object) requestItem.mDBItem);
                deleteTempFile(requestItem);
            } else {
                File file = new File(DownloadUtil.getDownloadFilePath(requestItem.mDBItem, requestItem.mDownloadItem.getType()));
                if (requestItem.mDBItem.isSecretItem()) {
                    boolean encryptFile = CryptoUtil.encryptFile(str, file.getAbsolutePath(), requestItem.mDBItem.getSecretKey());
                    MediaFileUtils.deleteFileType(MediaFileUtils.FileType.TEMP, str);
                    if (!encryptFile) {
                        fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.FILE_OPERATE_ERROR, "encrypt error", (Throwable) null));
                        return;
                    }
                } else if (!FileUtils.move(new File(str), file)) {
                    fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.FILE_OPERATE_ERROR, "rename error", (Throwable) null));
                    return;
                }
                DBImage dBItemForUri = requestItem.mDownloadItem.getUriAdapter().getDBItemForUri(requestItem.mDownloadItem.getUri());
                RequestItem requestItem2 = new RequestItem(requestItem.mDownloadItem, dBItemForUri);
                if (!checkItemValid(requestItem2)) {
                    Log.d(getTag(), "DBImage [%s] is invalid after download file for uri [%s] finished", dBItemForUri, requestItem2.mDownloadItem.getUri());
                    MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, file.getAbsolutePath());
                    return;
                }
                updateDataBase(requestItem, file.getAbsolutePath());
                if (requestItem.mDownloadItem.compareAnsSetStatus(0, 3)) {
                    DownloadItem downloadItem = requestItem.mDownloadItem;
                    String absolutePath = file.getAbsolutePath();
                    if (requestItem.mDBItem.isSecretItem()) {
                        bArr = requestItem.mDBItem.getSecretKey();
                    }
                    DownloadItem.callbackSuccess(downloadItem, new DownloadedItem(absolutePath, bArr));
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x012c A[LOOP:2: B:55:0x0126->B:57:0x012c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0157 A[LOOP:3: B:63:0x0151->B:65:0x0157, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x017c A[LOOP:4: B:71:0x0176->B:73:0x017c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01a1 A[LOOP:5: B:79:0x019b->B:81:0x01a1, LOOP_END] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:68:0x0163=Splitter:B:68:0x0163, B:52:0x0115=Splitter:B:52:0x0115, B:60:0x0138=Splitter:B:60:0x0138, B:76:0x0188=Splitter:B:76:0x0188, B:84:0x01ad=Splitter:B:84:0x01ad} */
    private JSONObject requestUrls(Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, List<RequestItem> list) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONObject jSONObject4;
        JSONObject jSONObject5;
        JSONObject jSONObject6;
        DownloadFailReason downloadFailReason;
        StringBuilder sb = new StringBuilder();
        for (RequestItem next : list) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(next.mDBItem.getRequestId());
        }
        String requestUrl = getRequestUrl(list.get(0), account.name);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(nexExportFormat.TAG_FORMAT_WIDTH, Integer.toString(getRequestWidth())));
        arrayList.add(new BasicNameValuePair(nexExportFormat.TAG_FORMAT_HEIGHT, Integer.toString(getRequestHeight())));
        arrayList.add(new BasicNameValuePair("ids", sb.toString()));
        arrayList.add(new BasicNameValuePair("priority", list.get(0).mDownloadItem.getType().isBackground() ? "low" : "high"));
        try {
            JSONObject fromXiaomi = CloudUtils.getFromXiaomi(requestUrl, arrayList, account, galleryExtendedAuthToken, 0, false);
            if (fromXiaomi == null) {
                downloadFailReason = new DownloadFailReason(ErrorCode.SERVER_ERROR, "data empty", (Throwable) null);
            } else if (fromXiaomi.optInt("code") == 0) {
                JSONObject optJSONObject = fromXiaomi.optJSONObject("data");
                jSONObject6 = optJSONObject != null ? optJSONObject.optJSONObject("content") : null;
                if (jSONObject6 == null) {
                    try {
                        downloadFailReason = new DownloadFailReason(ErrorCode.SERVER_ERROR, "data empty", (Throwable) null);
                    } catch (JSONException e) {
                        JSONException jSONException = e;
                        jSONObject = jSONObject6;
                        e = jSONException;
                    } catch (ConnectTimeoutException e2) {
                        ConnectTimeoutException connectTimeoutException = e2;
                        jSONObject2 = jSONObject6;
                        e = connectTimeoutException;
                        DownloadFailReason downloadFailReason2 = new DownloadFailReason(ErrorCode.CONNECT_TIMEOUT, String.format("connect timeout: %s", new Object[]{requestUrl}), e);
                        for (RequestItem fireFailEvent : list) {
                            fireFailEvent(fireFailEvent, (String) null, downloadFailReason2);
                        }
                        return jSONObject2;
                    } catch (SocketTimeoutException e3) {
                        SocketTimeoutException socketTimeoutException = e3;
                        jSONObject3 = jSONObject6;
                        e = socketTimeoutException;
                        DownloadFailReason downloadFailReason3 = new DownloadFailReason(ErrorCode.SOCKET_TIMEOUT, String.format("connect timeout: %s", new Object[]{requestUrl}), e);
                        for (RequestItem fireFailEvent2 : list) {
                            fireFailEvent(fireFailEvent2, (String) null, downloadFailReason3);
                        }
                        return jSONObject3;
                    } catch (GalleryMiCloudServerException e4) {
                        GalleryMiCloudServerException galleryMiCloudServerException = e4;
                        jSONObject4 = jSONObject6;
                        e = galleryMiCloudServerException;
                        DownloadFailReason downloadFailReason4 = new DownloadFailReason(ErrorParseUtil.parseError(e, (String) null), e.getCloudServerException().getMessage(), e.getCloudServerException());
                        for (RequestItem fireFailEvent3 : list) {
                            fireFailEvent(fireFailEvent3, (String) null, downloadFailReason4);
                        }
                        return jSONObject4;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        jSONObject5 = jSONObject6;
                        th = th2;
                        DownloadFailReason downloadFailReason5 = new DownloadFailReason(ErrorParseUtil.parseError(th, (String) null), th.getMessage(), th);
                        for (RequestItem fireFailEvent4 : list) {
                            fireFailEvent(fireFailEvent4, (String) null, downloadFailReason5);
                        }
                        return jSONObject5;
                    }
                } else {
                    downloadFailReason = null;
                }
                if (downloadFailReason != null) {
                    for (RequestItem fireFailEvent5 : list) {
                        fireFailEvent(fireFailEvent5, (String) null, downloadFailReason);
                    }
                }
                return jSONObject6;
            } else {
                downloadFailReason = new DownloadFailReason(ErrorCode.SERVER_ERROR, fromXiaomi.optString("description"), (Throwable) null);
            }
            jSONObject6 = null;
            if (downloadFailReason != null) {
            }
            return jSONObject6;
        } catch (JSONException e5) {
            e = e5;
            jSONObject = null;
            DownloadFailReason downloadFailReason6 = new DownloadFailReason(ErrorCode.DATA_PARSE_ERROR, "parse data error", e);
            for (RequestItem fireFailEvent6 : list) {
                fireFailEvent(fireFailEvent6, (String) null, downloadFailReason6);
            }
            return jSONObject;
        } catch (ConnectTimeoutException e6) {
            e = e6;
            jSONObject2 = null;
            DownloadFailReason downloadFailReason22 = new DownloadFailReason(ErrorCode.CONNECT_TIMEOUT, String.format("connect timeout: %s", new Object[]{requestUrl}), e);
            while (r13.hasNext()) {
            }
            return jSONObject2;
        } catch (SocketTimeoutException e7) {
            e = e7;
            jSONObject3 = null;
            DownloadFailReason downloadFailReason32 = new DownloadFailReason(ErrorCode.SOCKET_TIMEOUT, String.format("connect timeout: %s", new Object[]{requestUrl}), e);
            while (r13.hasNext()) {
            }
            return jSONObject3;
        } catch (GalleryMiCloudServerException e8) {
            e = e8;
            jSONObject4 = null;
            DownloadFailReason downloadFailReason42 = new DownloadFailReason(ErrorParseUtil.parseError(e, (String) null), e.getCloudServerException().getMessage(), e.getCloudServerException());
            while (r13.hasNext()) {
            }
            return jSONObject4;
        } catch (Throwable th3) {
            th = th3;
            jSONObject5 = null;
            DownloadFailReason downloadFailReason52 = new DownloadFailReason(ErrorParseUtil.parseError(th, (String) null), th.getMessage(), th);
            while (r13.hasNext()) {
            }
            return jSONObject5;
        }
    }

    /* access modifiers changed from: protected */
    public abstract String checkAndReturnValidPath(RequestItem requestItem);

    /* access modifiers changed from: protected */
    public void doFileDownload(List<RequestItem> list, JSONObject jSONObject) {
        for (RequestItem next : list) {
            downloadFileItem(next, jSONObject.optJSONObject(next.mDBItem.getRequestId()));
        }
    }

    public void download(Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, List<DownloadItem> list) {
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (DownloadItem next : list) {
                if (!checkConditionPermitted(next)) {
                    Log.d(getTag(), "Download condition not ok type %s", (Object) next.getType());
                } else {
                    DBImage dBItemForUri = next.getUriAdapter().getDBItemForUri(next.getUri());
                    RequestItem requestItem = new RequestItem(next, dBItemForUri);
                    if (!checkItemValid(requestItem)) {
                        Log.d(getTag(), "Invalid dbImage [%s] for download uri [%s]!", dBItemForUri, next.getUri());
                    } else {
                        String checkAndReturnValidPath = checkAndReturnValidPath(requestItem);
                        byte[] bArr = null;
                        if (TextUtils.isEmpty(checkAndReturnValidPath)) {
                            if (ThumbnailPreference.containsThumbnailKey(requestItem.mDBItem.getSha1())) {
                                Log.i(getTag(), "build error sha1 %s", (Object) requestItem.mDBItem.getSha1());
                                fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.THUMBNAIL_BUILD_ERROR, String.format("thumbnail can't build: %s", new Object[]{requestItem.mDBItem.getFileName()}), (Throwable) null));
                            } else {
                                arrayList.add(requestItem);
                            }
                        } else if (next.compareAnsSetStatus(0, 3)) {
                            DownloadItem downloadItem = requestItem.mDownloadItem;
                            if (requestItem.mDBItem.isSecretItem()) {
                                bArr = requestItem.mDBItem.getSecretKey();
                            }
                            DownloadItem.callbackSuccess(downloadItem, new DownloadedItem(checkAndReturnValidPath, bArr));
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                for (Map.Entry next2 : classifyRequest(arrayList).entrySet()) {
                    downloadInternal(account, galleryExtendedAuthToken, (List) next2.getValue());
                    Log.d(getTag(), "download batch %s", next2.getKey());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean downloadFileItem(RequestItem requestItem, JSONObject jSONObject) {
        String str;
        Log.i(getTag(), "download id %s", (Object) requestItem.mDBItem.getId());
        if (Thread.currentThread().isInterrupted()) {
            Log.i(getTag(), "for download interrupt %s", (Object) Boolean.valueOf(Thread.currentThread().isInterrupted()));
            return false;
        } else if (!requestItem.mDownloadItem.isStatusOk()) {
            Log.i(getTag(), "item %s status %s", requestItem.mDownloadItem.getKey(), Integer.valueOf(requestItem.mDownloadItem.getStatus()));
            return false;
        } else {
            String requestId = requestItem.mDBItem.getRequestId();
            byte[] bArr = null;
            if (jSONObject == null) {
                Log.w(getTag(), "id: %s not found in content json", requestId);
                ThumbnailPreference.putThumbnailKey(requestItem.mDBItem.getSha1());
                fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.THUMBNAIL_BUILD_ERROR, String.format(Locale.US, "request url for %s error", new Object[]{requestItem.mDBItem.getServerId()}), (Throwable) null));
                return false;
            }
            ReentrantLock uriLock = requestItem.mDownloadItem.getUriLock();
            if (uriLock.isLocked()) {
                if (shouldWaitUriLock()) {
                    Log.d(getTag(), "%s wait lock", (Object) requestItem.mDownloadItem);
                } else {
                    Log.d(getTag(), "%s is locked, skip download", (Object) requestItem.mDownloadItem);
                    return false;
                }
            }
            uriLock.lock();
            try {
                String checkAndReturnValidPath = checkAndReturnValidPath(requestItem);
                if (!TextUtils.isEmpty(checkAndReturnValidPath)) {
                    Log.d(getTag(), "no need download file: %s, thumb: %s", requestItem.mDBItem.getLocalFile(), requestItem.mDBItem.getThumbnailFile());
                    if (requestItem.mDownloadItem.compareAnsSetStatus(0, 3)) {
                        DownloadItem downloadItem = requestItem.mDownloadItem;
                        if (requestItem.mDBItem.isSecretItem()) {
                            bArr = requestItem.mDBItem.getSecretKey();
                        }
                        DownloadItem.callbackSuccess(downloadItem, new DownloadedItem(checkAndReturnValidPath, bArr));
                    }
                    return true;
                }
                boolean optBoolean = jSONObject.optBoolean("isUrl");
                String optString = jSONObject.optString("data");
                ErrorCode ensureDownloadTempFolder = DownloadUtil.ensureDownloadTempFolder(requestItem.mDBItem, requestItem.mDownloadItem.getType());
                if (ensureDownloadTempFolder != ErrorCode.NO_ERROR) {
                    fireFailEvent(requestItem, (String) null, new DownloadFailReason(ensureDownloadTempFolder, String.format(Locale.US, "error create folder: %s", new Object[]{DownloadUtil.getDownloadTempFolderPath(requestItem.mDBItem, requestItem.mDownloadItem.getType())}), (Throwable) null));
                    uriLock.unlock();
                    return false;
                }
                if (optBoolean) {
                    Uri parse = Uri.parse(optString);
                    if (!isValidUrl(parse)) {
                        ThumbnailPreference.putThumbnailKey(requestItem.mDBItem.getSha1());
                        fireFailEvent(requestItem, (String) null, new DownloadFailReason(ErrorCode.THUMBNAIL_BUILD_ERROR, String.format(Locale.US, "invalid url %s for %s", new Object[]{parse, requestItem.mDBItem.getServerId()}), (Throwable) null));
                        uriLock.unlock();
                        return false;
                    }
                    str = downloadFile(requestItem, parse.toString());
                } else {
                    str = downloadData(requestItem, optString);
                }
                onPostDownload(requestItem, str);
                uriLock.unlock();
                return true;
            } finally {
                uriLock.unlock();
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract int getRequestHeight();

    /* access modifiers changed from: protected */
    public abstract int getRequestWidth();

    /* access modifiers changed from: protected */
    public abstract String getTag();

    /* access modifiers changed from: protected */
    public boolean handleDownloadTempFile(RequestItem requestItem, String str) {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldWaitUriLock() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean updateDataBase(RequestItem requestItem, String str);
}
