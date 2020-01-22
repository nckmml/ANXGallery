package com.xiaomi.skytransfer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyLibraryLoaderHelper;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyTransferItem;
import com.miui.gallery.editor.photo.app.filter.skytransfer.SkyTransferTempData;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.Log;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class SkyTranFilter {
    private static SkyTranFilter sInstance = new SkyTranFilter();
    private boolean mIsLoaded;
    private Bitmap mLastBitmap;
    private long mNativeObj;
    private Object mSync = new Object();
    private SkyTransferTempData mTransferTempData;

    private byte[] bitmap2RGB(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        byte[] array = allocate.array();
        byte[] array2 = ByteBuffer.allocate((array.length / 4) * 3).array();
        int length = array.length / 4;
        for (int i = 0; i < length; i++) {
            if (z) {
                int i2 = i * 3;
                int i3 = i * 4;
                array2[i2] = array[i3];
                array2[i2 + 1] = array[i3 + 1];
                array2[i2 + 2] = array[i3 + 2];
            } else {
                int i4 = i * 3;
                int i5 = i * 4;
                array2[i4] = array[i5 + 2];
                array2[i4 + 1] = array[i5 + 1];
                array2[i4 + 2] = array[i5];
            }
        }
        return array2;
    }

    private native void bitmapAdjustMoment(long j, Object obj, Object obj2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, boolean z, float f);

    private native void bitmapTransferSky(long j, Object obj, Object obj2, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i5, boolean z, float f, boolean z2);

    private native void freeSkyTransfer(long j);

    private byte[] getByteFromPath(String str) {
        Bitmap safeDecodeBitmap = BitmapUtils.safeDecodeBitmap(str, new BitmapFactory.Options(), (byte[]) null);
        byte[] bitmap2RGB = bitmap2RGB(safeDecodeBitmap, true);
        BitmapUtils.recycleSilently(safeDecodeBitmap);
        return bitmap2RGB;
    }

    public static SkyTranFilter getInstance() {
        return sInstance;
    }

    private native int getSkyTransMode(long j);

    private void init() {
        if (!this.mIsLoaded) {
            try {
                System.load(SkyLibraryLoaderHelper.getInstance().getLibraryDirPath() + "/libsky_transfer_jni.so");
                this.mIsLoaded = true;
            } catch (Error e) {
                Log.e("SkyTranFilter", (Throwable) e);
            }
        }
        if (this.mIsLoaded) {
            this.mNativeObj = newSkyTransfer();
        }
    }

    private native long newSkyTransfer();

    private native void segment(long j, byte[] bArr, int i, int i2, byte[] bArr2, boolean z, boolean z2);

    private boolean transferSky(Bitmap bitmap, Bitmap bitmap2, SkyTransferItem skyTransferItem, SkyTransferTempData skyTransferTempData, boolean z) {
        Object obj;
        SkyTransferItem skyTransferItem2 = skyTransferItem;
        SkyTransferTempData skyTransferTempData2 = skyTransferTempData;
        if (bitmap == null || skyTransferTempData2 == null || skyTransferItem2 == null) {
            return false;
        }
        if (skyTransferTempData2.mSkyMode == 2) {
            Log.w("SkyTranFilter", "transfer sky mode is forbidden");
            return false;
        }
        Object obj2 = this.mSync;
        synchronized (obj2) {
            try {
                Log.d("SkyTranFilter", "transferSky start %d-%d ,%s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), skyTransferItem.toString());
                if (this.mNativeObj <= 0) {
                    Log.w("SkyTranFilter", "transfer sky native obj is null");
                } else {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    Bitmap safeDecodeBitmap = BitmapUtils.safeDecodeBitmap(skyTransferItem.getPath() + File.separator + "background", options, (byte[]) null);
                    int width = safeDecodeBitmap.getWidth();
                    int height = safeDecodeBitmap.getHeight();
                    byte[] bitmap2RGB = bitmap2RGB(safeDecodeBitmap, true);
                    BitmapUtils.recycleSilently(safeDecodeBitmap);
                    byte[] byteFromPath = getByteFromPath(skyTransferItem.getPath() + File.separator + "fore");
                    byte[] byteFromPath2 = getByteFromPath(skyTransferItem.getPath() + File.separator + "whole");
                    byte[] byteFromPath3 = getByteFromPath(skyTransferItem.getPath() + File.separator + "complex");
                    if (!(bitmap2RGB == null || byteFromPath == null || byteFromPath2 == null)) {
                        if (byteFromPath3 != null) {
                            obj = obj2;
                            bitmapTransferSky(this.mNativeObj, bitmap, bitmap2, skyTransferTempData2.mMaskData, skyTransferTempData2.mSegWidth, skyTransferTempData2.mSegHeight, bitmap2RGB, width, height, byteFromPath, byteFromPath2, byteFromPath3, skyTransferItem.getCate(), skyTransferTempData2.mSkyMode == 1, skyTransferItem.getProgress(), z);
                            skyTransferItem.mCanAdjustMoment = true;
                            Log.d("SkyTranFilter", "transferSky end");
                            return true;
                        }
                    }
                    Object obj3 = obj2;
                    Log.w("SkyTranFilter", "res decode failed");
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
        return false;
    }

    public SkyTransferTempData getTransferTempData() {
        return this.mTransferTempData;
    }

    public void release() {
        long j = this.mNativeObj;
        if (j > 0) {
            freeSkyTransfer(j);
        }
        this.mTransferTempData = null;
        this.mNativeObj = 0;
        this.mLastBitmap = null;
    }

    public int segment(Bitmap bitmap) {
        synchronized (this.mSync) {
            if (this.mTransferTempData == null || bitmap != this.mLastBitmap) {
                this.mTransferTempData = null;
                if (this.mNativeObj <= 0) {
                    init();
                }
                Log.d("SkyTranFilter", "segment start");
                if (this.mNativeObj <= 0) {
                    Log.w("SkyTranFilter", "segment mNativeObj is null");
                    return 2;
                } else if (bitmap == null) {
                    Log.w("SkyTranFilter", "segment img is null or category < 0");
                    return 2;
                } else {
                    this.mTransferTempData = new SkyTransferTempData(bitmap);
                    segment(this.mNativeObj, bitmap2RGB(bitmap, true), this.mTransferTempData.mSegWidth, this.mTransferTempData.mSegHeight, this.mTransferTempData.mMaskData, true, false);
                    this.mTransferTempData.mSkyMode = getSkyTransMode(this.mNativeObj);
                    this.mTransferTempData.mCountDownLatch.countDown();
                    Log.d("SkyTranFilter", "segment end: %d", (Object) Integer.valueOf(this.mTransferTempData.mSkyMode));
                    this.mLastBitmap = bitmap;
                    int i = this.mTransferTempData.mSkyMode;
                    return i;
                }
            } else {
                int i2 = this.mTransferTempData.mSkyMode;
                return i2;
            }
        }
    }

    public boolean transferSkyAdjustMoment(Bitmap bitmap, Bitmap bitmap2, SkyTransferItem skyTransferItem) {
        if (bitmap == null || skyTransferItem == null) {
            return false;
        }
        synchronized (this.mSync) {
            if (this.mNativeObj <= 0) {
                Log.v("SkyTranFilter", "transfer sky native obj is null");
                return false;
            }
            Log.d("SkyTranFilter", "transferSkyAdjustMoment start %s", (Object) skyTransferItem.toString());
            byte[] byteFromPath = getByteFromPath(skyTransferItem.getPath() + File.separator + "fore");
            byte[] byteFromPath2 = getByteFromPath(skyTransferItem.getPath() + File.separator + "whole");
            byte[] byteFromPath3 = getByteFromPath(skyTransferItem.getPath() + File.separator + "complex");
            if (!(byteFromPath == null || byteFromPath2 == null)) {
                if (byteFromPath3 != null) {
                    bitmapAdjustMoment(this.mNativeObj, bitmap, bitmap2, byteFromPath, byteFromPath2, byteFromPath3, skyTransferItem.getCate(), this.mTransferTempData.mSkyMode == 1, skyTransferItem.getProgress());
                    Log.d("SkyTranFilter", "transferSkyAdjustMoment end");
                    return true;
                }
            }
            Log.w("SkyTranFilter", "res decode failed");
            return false;
        }
    }

    public void transferSkyForSave(Bitmap bitmap, SkyTransferItem skyTransferItem, SkyTransferTempData skyTransferTempData) {
        transferSky(bitmap, (Bitmap) null, skyTransferItem, skyTransferTempData, true);
    }

    public boolean transferSkyForShow(Bitmap bitmap, Bitmap bitmap2, SkyTransferItem skyTransferItem) {
        return transferSky(bitmap, bitmap2, skyTransferItem, this.mTransferTempData, false);
    }

    public boolean waitSegment() {
        SkyTransferTempData skyTransferTempData = this.mTransferTempData;
        if (skyTransferTempData == null) {
            return false;
        }
        try {
            skyTransferTempData.mCountDownLatch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Log.e("SkyTranFilter", (Throwable) e);
        }
        Log.d("SkyTranFilter", "waitSegment done");
        SkyTransferTempData skyTransferTempData2 = this.mTransferTempData;
        return (skyTransferTempData2 == null || skyTransferTempData2.mSkyMode == 2) ? false : true;
    }
}
