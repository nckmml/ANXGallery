package com.miui.gallery.util.photoview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.miui.gallery.Config;
import com.miui.gallery.util.ReusedBitmapCache;

public class TileReusedBitCache extends ReusedBitmapCache {
    private static TileReusedBitCache sInstance;

    private TileReusedBitCache() {
    }

    public static synchronized TileReusedBitCache getInstance() {
        TileReusedBitCache tileReusedBitCache;
        synchronized (TileReusedBitCache.class) {
            if (sInstance == null) {
                sInstance = new TileReusedBitCache();
            }
            tileReusedBitCache = sInstance;
        }
        return tileReusedBitCache;
    }

    /* access modifiers changed from: protected */
    public boolean canUseForInBitmap(Bitmap bitmap, BitmapFactory.Options options) {
        if (!isSupportBytesCount()) {
            return bitmap.getWidth() == options.outWidth && bitmap.getHeight() == options.outHeight && options.inSampleSize == 1;
        }
        int convertToPowerOf2 = convertToPowerOf2(options.inSampleSize);
        int i = options.outWidth / convertToPowerOf2;
        int i2 = options.outHeight / convertToPowerOf2;
        return bitmap.getWidth() == i && bitmap.getHeight() == i2 && (i * i2) * getBytesPerPixel(bitmap.getConfig()) == bitmap.getAllocationByteCount();
    }

    /* access modifiers changed from: protected */
    public Bitmap.Config getConfig() {
        return Config.TileConfig.getBitmapConfig();
    }

    /* access modifiers changed from: protected */
    public int getMaxCount() {
        return Config.TileConfig.getMaxCacheCount();
    }

    /* access modifiers changed from: protected */
    public boolean needMutable() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean needRecycle() {
        return true;
    }
}
