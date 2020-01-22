package com.miui.gallery.util.photoview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import com.miui.gallery.Config;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class ParallelTileBitProvider implements TileBitProvider {
    private final int mDecoderNumber;
    private boolean[] mDecoderUsedMarkers;
    private BitmapRegionDecoder[] mDecoders;
    private int mImageHeight;
    private int mImageWidth;
    private boolean mIsFlip;
    private volatile boolean mIsReleasing;
    private int mRotation;
    private final Semaphore mSemaphore;

    public ParallelTileBitProvider(String str, byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            BitmapRegionDecoder safeCreateBitmapRegionDecoder = BitmapUtils.safeCreateBitmapRegionDecoder(str, true, bArr);
            if (BitmapUtils.isValid(safeCreateBitmapRegionDecoder)) {
                arrayList.add(safeCreateBitmapRegionDecoder);
            }
        }
        this.mDecoderNumber = arrayList.size();
        int i3 = this.mDecoderNumber;
        this.mDecoders = new BitmapRegionDecoder[i3];
        this.mDecoderUsedMarkers = new boolean[i3];
        this.mSemaphore = new Semaphore(i3, true);
        if (arrayList.size() > 0) {
            BitmapRegionDecoder bitmapRegionDecoder = (BitmapRegionDecoder) arrayList.get(0);
            this.mImageWidth = bitmapRegionDecoder.getWidth();
            this.mImageHeight = bitmapRegionDecoder.getHeight();
            ExifUtil.ExifInfo parseRotationInfo = ExifUtil.parseRotationInfo(str, bArr);
            if (parseRotationInfo == null) {
                this.mRotation = 0;
                this.mIsFlip = false;
            } else {
                this.mRotation = parseRotationInfo.rotation;
                this.mIsFlip = parseRotationInfo.flip;
            }
            arrayList.toArray(this.mDecoders);
        }
        Log.i("ParallelTileBitProvider", "create");
    }

    private BitmapRegionDecoder acquireDecoder() throws InterruptedException {
        this.mSemaphore.acquire();
        return getNextAvailableDecoder();
    }

    private synchronized BitmapRegionDecoder getNextAvailableDecoder() {
        for (int i = 0; i < this.mDecoderNumber; i++) {
            if (!this.mDecoderUsedMarkers[i]) {
                this.mDecoderUsedMarkers[i] = true;
                return this.mDecoders[i];
            }
        }
        return null;
    }

    private synchronized boolean markAsUnused(BitmapRegionDecoder bitmapRegionDecoder) {
        int i = 0;
        while (i < this.mDecoderNumber) {
            if (bitmapRegionDecoder != this.mDecoders[i]) {
                i++;
            } else if (!this.mDecoderUsedMarkers[i]) {
                return false;
            } else {
                this.mDecoderUsedMarkers[i] = false;
                return true;
            }
        }
        return false;
    }

    private void releaseDecoder(BitmapRegionDecoder bitmapRegionDecoder) {
        if (markAsUnused(bitmapRegionDecoder)) {
            this.mSemaphore.release();
        }
    }

    public int getImageHeight() {
        return this.mImageHeight;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public int getParallelism() {
        return this.mDecoderNumber;
    }

    public int getRotation() {
        return this.mRotation;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a6  */
    public TileBit getTileBit(Rect rect, int i) {
        Bitmap bitmap;
        Bitmap bitmap2;
        if (rect != null && this.mDecoderNumber > 0 && !this.mIsReleasing) {
            Rect rect2 = new Rect(0, 0, this.mImageWidth, this.mImageHeight);
            if (!rect2.intersect(rect)) {
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Config.TileConfig.getBitmapConfig();
            options.inSampleSize = i;
            options.outWidth = rect2.width();
            options.outHeight = rect2.height();
            options.inBitmap = TileReusedBitCache.getInstance().get(options);
            try {
                System.currentTimeMillis();
                BitmapRegionDecoder acquireDecoder = acquireDecoder();
                if (BitmapUtils.isValid(acquireDecoder)) {
                    bitmap2 = BitmapUtils.safeDecodeRegion(acquireDecoder, rect2, options);
                    if (bitmap2 == null) {
                        try {
                            if (BitmapUtils.isValid(options.inBitmap)) {
                                Log.w("ParallelTileBitProvider", "fail in decoding region use inBitmap [width %d, height %d]", Integer.valueOf(options.inBitmap.getWidth()), Integer.valueOf(options.inBitmap.getHeight()));
                                options.inBitmap.recycle();
                                options.inBitmap = null;
                                bitmap = BitmapUtils.safeDecodeRegion(acquireDecoder, rect2, options);
                            } else {
                                Log.w("ParallelTileBitProvider", "fail in decoding region %s", rect.toString());
                            }
                        } catch (InterruptedException e) {
                            e = e;
                            Log.w("ParallelTileBitProvider", (Throwable) e);
                            bitmap = bitmap2;
                            if (BitmapUtils.isValid(bitmap)) {
                            }
                            return null;
                        }
                    }
                    bitmap = bitmap2;
                } else {
                    bitmap = null;
                }
                try {
                    releaseDecoder(acquireDecoder);
                } catch (InterruptedException e2) {
                    bitmap2 = bitmap;
                    e = e2;
                }
            } catch (InterruptedException e3) {
                e = e3;
                bitmap2 = null;
                Log.w("ParallelTileBitProvider", (Throwable) e);
                bitmap = bitmap2;
                if (BitmapUtils.isValid(bitmap)) {
                }
                return null;
            }
            if (BitmapUtils.isValid(bitmap)) {
                return new TileBit(bitmap, rect2.width() / i, rect2.height() / i);
            }
        }
        return null;
    }

    public boolean isFlip() {
        return this.mIsFlip;
    }

    public void release() {
        Log.i("ParallelTileBitProvider", "start release");
        long currentTimeMillis = System.currentTimeMillis();
        this.mIsReleasing = true;
        int i = this.mDecoderNumber;
        if (i > 0) {
            this.mSemaphore.acquireUninterruptibly(i);
            for (BitmapRegionDecoder bitmapRegionDecoder : this.mDecoders) {
                if (BitmapUtils.isValid(bitmapRegionDecoder)) {
                    bitmapRegionDecoder.recycle();
                    Log.d("ParallelTileBitProvider", "release decoder [%s]", (Object) Integer.toHexString(bitmapRegionDecoder.hashCode()));
                }
            }
        }
        this.mDecoders = null;
        Log.i("ParallelTileBitProvider", "release done, costs: %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }
}
