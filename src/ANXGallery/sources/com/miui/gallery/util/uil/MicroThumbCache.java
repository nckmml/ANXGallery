package com.miui.gallery.util.uil;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.SystemClock;
import com.miui.gallery.Config;
import com.miui.gallery.imageloader.GalleryImageLoaderCache;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ReusedBitmapCache;
import com.miui.gallery.util.uil.BlobCache;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import miui.util.Pools;

public class MicroThumbCache implements GalleryImageLoaderCache.IMicroThumbCache {
    /* access modifiers changed from: private */
    public static final int MICRO_THUMBNAIL_SIZE = Config.ThumbConfig.get().sMicroTargetSize.getWidth();
    private static final int THUMB_CONFIG_ORDINAL = Config.ThumbConfig.getThumbConfig().ordinal();
    private static MicroThumbCache sCache;
    private Pools.SoftReferencePool<Bitmap> mBitmapPool;
    private BlobCache mBlobCache;
    private Object mBlobLock = new Object();
    private final File mCacheDir;
    private final int mCacheMaxSize;
    private Pools.SoftReferencePool<BlobCache.LookupRequest> mLookupRequestPool;
    private final int mMaxEntries;

    private MicroThumbCache(File file, int i, int i2) {
        Log.d("MicroThumbCache", "MicroThumbCache cache size %d", (Object) Integer.valueOf(i2));
        this.mCacheDir = file;
        this.mMaxEntries = i;
        this.mCacheMaxSize = i2;
    }

    private synchronized void ensureInitialized() throws IOException {
        if (this.mBlobCache == null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mCacheDir != null && !this.mCacheDir.exists()) {
                FileUtils.createFolder(this.mCacheDir, true);
            }
            try {
                this.mBlobCache = new BlobCache(this.mCacheDir + "/" + "micro_thumbnail_blob", this.mMaxEntries, this.mCacheMaxSize, false, 9);
                this.mLookupRequestPool = Pools.createSoftReferencePool(new Pools.Manager<BlobCache.LookupRequest>() {
                    public BlobCache.LookupRequest createInstance() {
                        return new BlobCache.LookupRequest();
                    }
                }, 32);
                this.mBitmapPool = Pools.createSoftReferencePool(new Pools.Manager<Bitmap>() {
                    public Bitmap createInstance() {
                        return Bitmap.createBitmap(MicroThumbCache.MICRO_THUMBNAIL_SIZE, MicroThumbCache.MICRO_THUMBNAIL_SIZE, Config.ThumbConfig.getThumbConfig());
                    }
                }, 10);
                Log.d("MicroThumbCache", "init cost %s", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            } catch (IOException e) {
                Log.w("MicroThumbCache", "cache creation failed, use dummy", e);
                throw e;
            }
        }
    }

    public static MicroThumbCache getInstance() {
        return sCache;
    }

    public static void init(File file, int i, int i2) {
        sCache = new MicroThumbCache(file, i, i2);
    }

    private boolean isCorrectConfig(Bitmap bitmap) {
        return bitmap != null && bitmap.getConfig() == Config.ThumbConfig.getThumbConfig();
    }

    private boolean isCorrectSize(Bitmap bitmap) {
        return bitmap != null && Math.min(bitmap.getWidth(), bitmap.getHeight()) >= MICRO_THUMBNAIL_SIZE;
    }

    private boolean isReusableBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        Bitmap.Config config = bitmap.getConfig();
        int width = bitmap.getWidth();
        return width == MICRO_THUMBNAIL_SIZE && width == bitmap.getHeight() && THUMB_CONFIG_ORDINAL == config.ordinal();
    }

    public Bitmap getCenterCropBitmap(String str, Bitmap bitmap) {
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        Bitmap bitmap2 = null;
        try {
            ensureInitialized();
            BlobCache.LookupRequest lookupRequest = (BlobCache.LookupRequest) this.mLookupRequestPool.acquire();
            lookupRequest.key = MiscUtil.crc64Long(str.getBytes());
            lookupRequest.length = 0;
            synchronized (this.mBlobLock) {
                try {
                    this.mBlobCache.lookup(lookupRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (lookupRequest.length > 0) {
                ByteBuffer wrap = ByteBuffer.wrap(lookupRequest.buffer, 0, lookupRequest.length);
                if (!isReusableBitmap(bitmap)) {
                    bitmap = (Bitmap) this.mBitmapPool.acquire();
                }
                bitmap2 = bitmap;
                bitmap2.copyPixelsFromBuffer(wrap);
            }
            this.mLookupRequestPool.release(lookupRequest);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        long elapsedRealtimeNanos2 = SystemClock.elapsedRealtimeNanos() - elapsedRealtimeNanos;
        if (bitmap2 != null) {
            LoadMonitor.record(0, elapsedRealtimeNanos2);
        }
        return bitmap2;
    }

    public boolean save(String str, Bitmap bitmap) throws IOException {
        Bitmap bitmap2;
        int i;
        ensureInitialized();
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        }
        ByteBuffer byteBuffer = null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        if (min > 0 && min < (i = MICRO_THUMBNAIL_SIZE)) {
            float f = ((float) i) / ((float) min);
            if (((float) (ReusedBitmapCache.getBytesPerPixel(Config.ThumbConfig.getThumbConfig()) * width * height)) * f * f > 2097152.0f) {
                return false;
            }
            Matrix matrix = new Matrix();
            matrix.postScale(f, f);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        }
        Bitmap bitmap3 = bitmap;
        if (isCorrectSize(bitmap3)) {
            int width2 = bitmap3.getWidth();
            int height2 = bitmap3.getHeight();
            int i2 = MICRO_THUMBNAIL_SIZE;
            int i3 = (width2 - i2) / 2;
            int i4 = (height2 - i2) / 2;
            if (isCorrectConfig(bitmap3)) {
                int i5 = MICRO_THUMBNAIL_SIZE;
                bitmap2 = Bitmap.createBitmap(bitmap3, i3, i4, i5, i5);
            } else {
                Log.d("MicroThumbCache", "is not correct config %s, %s", str, bitmap3.getConfig());
                int i6 = MICRO_THUMBNAIL_SIZE;
                bitmap2 = BitmapUtils.safeCreateBitmap(bitmap3, i3, i4, i6, i6, (Matrix) null, true, Config.ThumbConfig.getThumbConfig());
            }
            bitmap3 = bitmap2;
            if (isCorrectConfig(bitmap3)) {
                byteBuffer = ByteBuffer.allocate(bitmap3.getByteCount());
                bitmap3.copyPixelsToBuffer(byteBuffer);
            }
        }
        if (byteBuffer == null) {
            return true;
        }
        synchronized (this.mBlobLock) {
            Log.d("MicroThumbCache", "save data size %d", (Object) Integer.valueOf(bitmap3.getByteCount()));
            this.mBlobCache.insert(MiscUtil.crc64Long(str.getBytes()), byteBuffer.array());
        }
        return true;
    }
}
