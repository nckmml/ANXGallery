package com.miui.gallery.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.GLUtils;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.util.Utils;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DecodeUtils {
    private static final GifCvtJpegCache GIF_CVT_JPEG_CACHE = new GifCvtJpegCache(GalleryUtils.getInternalCacheDir(), 5);
    public static final Object LOCK = new Object();

    public static class GalleryOptions extends BitmapFactory.Options {
        public Uri uri;

        public ParcelFileDescriptor getFD() {
            try {
                return GalleryApp.sGetAndroidContext().getContentResolver().openFileDescriptor(this.uri, "r");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static Bitmap considerOrientation(Bitmap bitmap, ExifUtil.ExifInfo exifInfo) {
        if (bitmap == null || bitmap.isRecycled() || exifInfo == null) {
            return bitmap;
        }
        boolean z = false;
        Matrix matrix = new Matrix();
        if (exifInfo.flip) {
            matrix.postScale(-1.0f, 1.0f);
            z = true;
        }
        if (exifInfo.rotation != 0) {
            matrix.postRotate((float) exifInfo.rotation);
            z = true;
        }
        if (z) {
            Bitmap safeCreateBitmap = BitmapUtils.safeCreateBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true, bitmap.getConfig());
            if (safeCreateBitmap != bitmap) {
                bitmap.recycle();
            }
            return safeCreateBitmap;
        }
        return bitmap;
    }

    public static Bitmap ensureGLCompatibleBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.getConfig() != null) {
            return bitmap;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        bitmap.recycle();
        return copy;
    }

    public static Bitmap requestDecodeThumbNail(FileDescriptor fileDescriptor, GalleryOptions galleryOptions, BitmapUtils.BitmapDimension bitmapDimension, String str) {
        Bitmap tryDecodeAndFit;
        if (galleryOptions == null) {
            galleryOptions = new GalleryOptions();
        }
        galleryOptions.inJustDecodeBounds = true;
        GalleryUtils.safeDecodeFileDescriptor(fileDescriptor, (Rect) null, galleryOptions);
        int i = galleryOptions.outWidth;
        int i2 = galleryOptions.outHeight;
        if (bitmapDimension != null) {
            bitmapDimension.width = i;
            bitmapDimension.height = i2;
        }
        DisplayMetrics displayMetrics = GalleryApp.sGetAndroidContext().getResources().getDisplayMetrics();
        int i3 = displayMetrics.widthPixels;
        int i4 = displayMetrics.heightPixels;
        galleryOptions.inSampleSize = BitmapUtils.computeThumbNailSampleSize(i, i2, i3, i4);
        galleryOptions.inJustDecodeBounds = false;
        galleryOptions.inPurgeable = false;
        galleryOptions.inInputShareable = false;
        if (!ScreenUtils.needOptimizeForLowMemory()) {
            return tryDecodeAndFit(galleryOptions, i3, i4, str);
        }
        synchronized (LOCK) {
            tryDecodeAndFit = tryDecodeAndFit(galleryOptions, i3, i4, str);
        }
        return tryDecodeAndFit;
    }

    public static Bitmap requestDecodeThumbNail(String str, GalleryOptions galleryOptions) {
        return requestDecodeThumbNail(str, galleryOptions, (BitmapUtils.BitmapDimension) null);
    }

    public static Bitmap requestDecodeThumbNail(String str, GalleryOptions galleryOptions, BitmapUtils.BitmapDimension bitmapDimension) {
        FileInputStream fileInputStream;
        Bitmap bitmap = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                bitmap = requestDecodeThumbNail(fileInputStream.getFD(), galleryOptions, bitmapDimension, str);
            } catch (FileNotFoundException e) {
                e = e;
                e.printStackTrace();
                Utils.closeSilently((Closeable) fileInputStream);
                return bitmap;
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                Utils.closeSilently((Closeable) fileInputStream);
                return bitmap;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            fileInputStream = null;
            e.printStackTrace();
            Utils.closeSilently((Closeable) fileInputStream);
            return bitmap;
        } catch (IOException e4) {
            e = e4;
            fileInputStream = null;
            e.printStackTrace();
            Utils.closeSilently((Closeable) fileInputStream);
            return bitmap;
        } catch (Throwable th) {
            th = th;
            Utils.closeSilently((Closeable) fileInputStream);
            throw th;
        }
        Utils.closeSilently((Closeable) fileInputStream);
        return bitmap;
    }

    private static Bitmap tryDecodeAndFit(GalleryOptions galleryOptions, int i, int i2, String str) {
        FileInputStream fileInputStream;
        OutOfMemoryError e;
        Throwable th;
        FileDescriptor fileDescriptor;
        Bitmap bitmap = null;
        FileInputStream fileInputStream2 = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        for (int i3 = 0; i3 < 3 && bitmap == null; i3++) {
            if (str != null) {
                try {
                    fileInputStream = new FileInputStream(str);
                    try {
                        fileDescriptor = fileInputStream.getFD();
                    } catch (OutOfMemoryError e2) {
                        e = e2;
                        Log.e("DecodeService", "Decode and fit is out of memory: " + e.toString());
                        galleryOptions.inSampleSize *= 2;
                        GalleryUtils.closeStream(fileInputStream);
                        Utils.closeSilently(parcelFileDescriptor);
                        fileInputStream2 = fileInputStream;
                    } catch (Throwable th2) {
                        th = th2;
                        Log.e("DecodeService", "unkown exception in decode and fit:" + th.toString());
                        galleryOptions.inSampleSize *= 2;
                        GalleryUtils.closeStream(fileInputStream);
                        Utils.closeSilently(parcelFileDescriptor);
                        fileInputStream2 = fileInputStream;
                    }
                } catch (OutOfMemoryError e3) {
                    OutOfMemoryError outOfMemoryError = e3;
                    fileInputStream = fileInputStream2;
                    e = outOfMemoryError;
                    Log.e("DecodeService", "Decode and fit is out of memory: " + e.toString());
                    galleryOptions.inSampleSize *= 2;
                    GalleryUtils.closeStream(fileInputStream);
                    Utils.closeSilently(parcelFileDescriptor);
                    fileInputStream2 = fileInputStream;
                } catch (Throwable th3) {
                    th = th3;
                    galleryOptions.inSampleSize *= 2;
                    GalleryUtils.closeStream(fileInputStream);
                    Utils.closeSilently(parcelFileDescriptor);
                    throw th;
                }
            } else {
                parcelFileDescriptor = galleryOptions.getFD();
                if (parcelFileDescriptor != null) {
                    fileInputStream = fileInputStream2;
                    fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                } else {
                    fileInputStream = fileInputStream2;
                    fileDescriptor = null;
                }
            }
            bitmap = GalleryUtils.safeDecodeFileDescriptor(fileDescriptor, (Rect) null, galleryOptions);
            if (bitmap != null) {
                try {
                    GLUtils.getType(bitmap);
                } catch (IllegalArgumentException e4) {
                    e4.printStackTrace();
                    Log.w("DecodeService", "decoded bitmap type error, IllegalArgumentException:" + e4.getMessage());
                    bitmap = ensureGLCompatibleBitmap(bitmap);
                    if (bitmap == null) {
                    }
                }
                if (!BitmapUtils.isBitmapInScreen(bitmap.getWidth(), bitmap.getHeight(), i, i2)) {
                    bitmap = BitmapUtils.fitBitmapToScreen(bitmap, i, i2, true);
                }
            }
            galleryOptions.inSampleSize *= 2;
            GalleryUtils.closeStream(fileInputStream);
            Utils.closeSilently(parcelFileDescriptor);
            fileInputStream2 = fileInputStream;
        }
        return considerOrientation(bitmap, ExifUtil.parseRotationInfo(str, (byte[]) null));
    }
}
