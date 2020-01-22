package com.miui.gallery.util.uil;

import android.graphics.Bitmap;
import com.miui.gallery.BaseConfig;
import com.miui.gallery.Config;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ReusedBitmapCache;

public class PhotoReusedBitCache extends ReusedBitmapCache {
    private static PhotoReusedBitCache sInstance;

    private PhotoReusedBitCache() {
    }

    public static synchronized PhotoReusedBitCache getInstance() {
        PhotoReusedBitCache photoReusedBitCache;
        synchronized (PhotoReusedBitCache.class) {
            if (sInstance == null) {
                sInstance = new PhotoReusedBitCache();
            }
            photoReusedBitCache = sInstance;
        }
        return photoReusedBitCache;
    }

    /* access modifiers changed from: protected */
    public Bitmap.Config getConfig() {
        return Config.BigPhotoConfig.getBitmapConfig();
    }

    /* access modifiers changed from: protected */
    public int getMaxCount() {
        return 3;
    }

    public synchronized void initFirstCacheBitmap() {
        if (isInBitmapSupport()) {
            ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                public Void run(ThreadPool.JobContext jobContext) {
                    Log.i("PhotoReusedBitCache", "init first cache bitmap");
                    PhotoReusedBitCache.this.clear();
                    try {
                        int screenWidth = (int) (((float) BaseConfig.ScreenConfig.getScreenWidth()) * 1.5f);
                        PhotoReusedBitCache.this.put(Bitmap.createBitmap(screenWidth, (screenWidth * 4) / 3, PhotoReusedBitCache.this.getConfig()));
                        return null;
                    } catch (OutOfMemoryError unused) {
                        Log.e("PhotoReusedBitCache", "create first cache bitmap oom");
                        return null;
                    }
                }
            });
        }
    }
}
