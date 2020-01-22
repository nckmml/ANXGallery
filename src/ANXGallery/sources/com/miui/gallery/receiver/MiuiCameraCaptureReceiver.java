package com.miui.gallery.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.internal.CompatHandler;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.activity.ExternalPhotoPageActivity;
import com.miui.gallery.data.LocationManager;
import com.miui.gallery.permission.core.PermissionUtils;
import com.miui.gallery.scanner.MediaScannerUtil;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.ProcessingMediaHelper;
import com.miui.os.Rom;
import java.util.HashMap;

public class MiuiCameraCaptureReceiver extends BroadcastReceiver {
    private static long sLastPreloadTime;
    private static PreloadBigPhotoRunnable sPreloadBigPhotoRunnable = new PreloadBigPhotoRunnable();

    private static class PreloadBigPhotoRunnable implements Runnable {
        private String mFilePath;
        /* access modifiers changed from: private */
        public boolean mIsTempFile;
        /* access modifiers changed from: private */
        public Uri mMediaStoreUri;

        private PreloadBigPhotoRunnable() {
            this.mMediaStoreUri = null;
        }

        /* access modifiers changed from: private */
        public boolean isCanceled(String str) {
            return !TextUtils.equals(this.mFilePath, str);
        }

        public void run() {
            if (!TextUtils.isEmpty(this.mFilePath) || this.mMediaStoreUri != null) {
                final String str = this.mFilePath;
                ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                    public Void run(ThreadPool.JobContext jobContext) {
                        if (PreloadBigPhotoRunnable.this.isCanceled(str)) {
                            return null;
                        }
                        if (!PermissionUtils.checkPermission(GalleryApp.sGetAndroidContext(), "android.permission.READ_EXTERNAL_STORAGE")) {
                            Log.w("MiuiCameraCaptureReceiver", "Can't access external storage, relate permission is ungranted");
                            return null;
                        }
                        Uri access$200 = PreloadBigPhotoRunnable.this.mMediaStoreUri;
                        if (access$200 == null) {
                            access$200 = MediaStoreUtils.getFileMediaUri(str);
                        }
                        if (!PreloadBigPhotoRunnable.this.isCanceled(str) && access$200 != null) {
                            ExternalPhotoPageActivity.preloadThumbnail(access$200.toString(), PreloadBigPhotoRunnable.this.mIsTempFile);
                        }
                        return null;
                    }
                });
            }
        }

        public void setData(String str, Uri uri, boolean z) {
            this.mFilePath = str;
            this.mMediaStoreUri = uri;
            this.mIsTempFile = z;
        }
    }

    private Uri parseMediaStoreUri(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("extra_media_store_uri");
        if (uri != null) {
            return uri;
        }
        String stringExtra = intent.getStringExtra("extra_file_path");
        long longExtra = intent.getLongExtra("extra_media_store_id", -1);
        if (longExtra > 0) {
            return MediaStoreUtils.getFileMediaUri(longExtra, FileMimeUtil.getMimeType(stringExtra));
        }
        return null;
    }

    public void onReceive(final Context context, final Intent intent) {
        String action = intent.getAction();
        if ("com.miui.gallery.SAVE_TO_CLOUD".equals(action)) {
            final String stringExtra = intent.getStringExtra("extra_file_path");
            Uri parseMediaStoreUri = parseMediaStoreUri(intent);
            long parseId = parseMediaStoreUri == null ? -1 : ContentUris.parseId(parseMediaStoreUri);
            boolean booleanExtra = intent.getBooleanExtra("extra_is_temp_file", false);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            if (intent.hasExtra("extra_is_temp_file")) {
                if (booleanExtra) {
                    ProcessingMediaHelper.getInstance().addProcessingItem(parseId, stringExtra);
                } else {
                    ProcessingMediaHelper.getInstance().removeProcessingItem(parseId, stringExtra);
                }
            }
            if (!TextUtils.isEmpty(stringExtra)) {
                Log.d("MiuiCameraCaptureReceiver", "ACTION_SAVE_TO_CLOUD start, path: [%s] mediaId: [%d], temp: [%b]", stringExtra, Long.valueOf(parseId), Boolean.valueOf(booleanExtra));
                ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                    public Void run(ThreadPool.JobContext jobContext) {
                        long uptimeMillis = SystemClock.uptimeMillis();
                        LocationManager.getInstance().recordMediaLocation(stringExtra, intent);
                        MediaScannerUtil.scanSingleFile(context, stringExtra, 0, true);
                        if (!Rom.IS_DEV) {
                            return null;
                        }
                        HashMap hashMap = new HashMap(1);
                        hashMap.put("recordLocationAndTriggerScan", String.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
                        GallerySamplingStatHelper.recordCountEvent("media_scanner", "camera_capture_receiver_time_consuming", hashMap);
                        return null;
                    }
                });
                CompatHandler mainHandler = ThreadManager.getMainHandler();
                mainHandler.removeCallbacks(sPreloadBigPhotoRunnable);
                long j = 0;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime - sLastPreloadTime < 400) {
                    j = 400;
                }
                sLastPreloadTime = elapsedRealtime;
                sPreloadBigPhotoRunnable.setData(stringExtra, parseMediaStoreUri, booleanExtra);
                mainHandler.postDelayed(sPreloadBigPhotoRunnable, j);
            }
        } else if ("com.miui.gallery.DELETE_FROM_CLOUD".equals(action)) {
            final String stringExtra2 = intent.getStringExtra("extra_file_path");
            if (!TextUtils.isEmpty(stringExtra2)) {
                Log.d("MiuiCameraCaptureReceiver", "ACTION_DELETE_FROM_CLOUD start %s", (Object) stringExtra2);
                ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                    public Void run(ThreadPool.JobContext jobContext) {
                        long uptimeMillis = SystemClock.uptimeMillis();
                        MediaScannerUtil.scanSingleFile(context, stringExtra2, 0, true);
                        if (!Rom.IS_DEV) {
                            return null;
                        }
                        HashMap hashMap = new HashMap(1);
                        hashMap.put("triggerScan", String.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
                        GallerySamplingStatHelper.recordCountEvent("media_scanner", "camera_capture_receiver_time_consuming", hashMap);
                        return null;
                    }
                });
            }
        }
    }
}
