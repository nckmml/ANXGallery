package com.miui.gallery.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import com.nexstreaming.nexeditorsdk.nexClip;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class Bitmaps {
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r5 == null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000b, code lost:
        if (r5 != null) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        r5.recycle();
     */
    public static Bitmap copyBitmap(Bitmap bitmap) {
        Bitmap bitmap2 = null;
        if (bitmap == null) {
            return null;
        }
        try {
            bitmap2 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        } catch (OutOfMemoryError e) {
            Log.e("Graphics", "copyBitmap error:" + e.toString());
        } catch (Throwable th) {
            if (bitmap != null) {
                bitmap.recycle();
            }
            throw th;
        }
    }

    public static Bitmap copyBitmapInCaseOfRecycle(Bitmap bitmap) {
        try {
            return bitmap.copy(Bitmap.Config.ARGB_8888, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options) {
        try {
            return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        } finally {
            IoUtils.close(inputStream);
        }
    }

    public static Bitmap decodeUri(Context context, Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
        InputStream inputStream;
        try {
            inputStream = IoUtils.openInputStream(context, uri);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                IoUtils.close(inputStream);
                return decodeStream;
            } catch (Throwable th) {
                th = th;
                IoUtils.close(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IoUtils.close(inputStream);
            throw th;
        }
    }

    public static Bitmap joinExif(Bitmap bitmap, int i, BitmapFactory.Options options) {
        Bitmap bitmap2;
        if (i == 0) {
            return bitmap;
        }
        if (bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.preRotate((float) i);
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
        } else {
            bitmap2 = null;
        }
        if (!(options == null || i % nexClip.kClip_Rotate_180 == 0)) {
            int i2 = options.outWidth;
            options.outWidth = options.outHeight;
            options.outHeight = i2;
        }
        return bitmap2;
    }

    public static ExifInterface readExif(Context context, Uri uri) {
        InputStream openInputStream = IoUtils.openInputStream("Graphics", context, uri);
        if (openInputStream == null) {
            return null;
        }
        try {
            return ExifUtil.sSupportExifCreator.create(openInputStream);
        } finally {
            IoUtils.close("Graphics", openInputStream);
        }
    }

    public static Bitmap setConfig(Bitmap bitmap) {
        if (bitmap == null || bitmap.getConfig() != null) {
            return bitmap;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmap.recycle();
        return copy;
    }
}
