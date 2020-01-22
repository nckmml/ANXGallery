package com.miui.gallery.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import com.miui.gallery.activity.ExternalPhotoPageActivity;
import com.miui.gallery.permission.core.PermissionUtils;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;

public class GalleryBackgroundService extends Service {
    private Future<Void> mPreloadFuture;

    public IBinder onBind(Intent intent) {
        Log.d("GalleryBackgroundService", "GalleryBackgroundService onBind");
        if (PermissionUtils.checkPermission((Context) this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            this.mPreloadFuture = ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                public Void run(ThreadPool.JobContext jobContext) {
                    Uri lastMediaUri = MediaStoreUtils.getLastMediaUri();
                    if (lastMediaUri == null) {
                        return null;
                    }
                    ExternalPhotoPageActivity.preloadThumbnail(lastMediaUri.toString());
                    return null;
                }
            });
            return null;
        }
        Log.w("GalleryBackgroundService", "Can't access external storage, relate permission is ungranted");
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.d("GalleryBackgroundService", "GalleryBackgroundService onCreate ");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("GalleryBackgroundService", "GalleryBackgroundService onDestroy");
        Future<Void> future = this.mPreloadFuture;
        if (future != null) {
            future.cancel();
            this.mPreloadFuture = null;
        }
    }
}
