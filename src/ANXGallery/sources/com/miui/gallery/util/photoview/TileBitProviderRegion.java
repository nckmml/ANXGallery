package com.miui.gallery.util.photoview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import com.miui.gallery.Config;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.Log;

class TileBitProviderRegion implements TileBitProvider {
    private int mImageHeight;
    private int mImageWidth;
    private boolean mIsFlip;
    private Object mLock = new Object();
    private BitmapRegionDecoder mRegionDecoder;
    private int mRotation;

    public TileBitProviderRegion(String str, byte[] bArr) {
        this.mRegionDecoder = BitmapUtils.safeCreateBitmapRegionDecoder(str, false, bArr);
        if (BitmapUtils.isValid(this.mRegionDecoder)) {
            this.mImageWidth = this.mRegionDecoder.getWidth();
            this.mImageHeight = this.mRegionDecoder.getHeight();
            ExifUtil.ExifInfo parseRotationInfo = ExifUtil.parseRotationInfo(str, bArr);
            if (parseRotationInfo == null) {
                this.mRotation = 0;
                this.mIsFlip = false;
            } else {
                this.mRotation = parseRotationInfo.rotation;
                this.mIsFlip = parseRotationInfo.flip;
            }
        }
        Log.i("TileBitProviderRegion", "create");
    }

    public int getImageHeight() {
        return this.mImageHeight;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public int getParallelism() {
        return 1;
    }

    public int getRotation() {
        return this.mRotation;
    }

    public TileBit getTileBit(Rect rect, int i) {
        Bitmap safeDecodeRegion;
        if (rect == null) {
            return null;
        }
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
        synchronized (this.mLock) {
            safeDecodeRegion = BitmapUtils.isValid(this.mRegionDecoder) ? BitmapUtils.safeDecodeRegion(this.mRegionDecoder, rect2, options) : null;
        }
        if (safeDecodeRegion == null) {
            synchronized (this.mLock) {
                if (BitmapUtils.isValid(options.inBitmap)) {
                    Log.w("TileBitProviderRegion", "fail in decoding region use inBitmap [width %d, height %d]", Integer.valueOf(options.inBitmap.getWidth()), Integer.valueOf(options.inBitmap.getHeight()));
                    options.inBitmap.recycle();
                    options.inBitmap = null;
                    safeDecodeRegion = BitmapUtils.safeDecodeRegion(this.mRegionDecoder, rect2, options);
                } else {
                    Log.w("TileBitProviderRegion", "fail in decoding region %s", rect.toString());
                }
            }
        }
        if (BitmapUtils.isValid(safeDecodeRegion)) {
            return new TileBit(safeDecodeRegion, rect2.width() / i, rect2.height() / i);
        }
        return null;
    }

    public boolean isFlip() {
        return this.mIsFlip;
    }

    public void release() {
        synchronized (this.mLock) {
            if (BitmapUtils.isValid(this.mRegionDecoder)) {
                this.mRegionDecoder.recycle();
            }
            this.mRegionDecoder = null;
            Log.i("TileBitProviderRegion", "release");
        }
    }
}
