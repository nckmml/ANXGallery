package com.miui.gallery.util;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseBitmapUtils {
    public static byte[] compressBitmapAsPng(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] compressToBytes(Bitmap bitmap, int i) {
        if (!isValidate(bitmap)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(65536);
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static InputStream createInputStream(String str, byte[] bArr) throws IOException {
        return bArr != null ? CryptoUtil.getDecryptCipherInputStream(str, bArr) : new FileInputStream(str);
    }

    public static boolean isValidate(Bitmap bitmap) {
        return bitmap != null && !bitmap.isRecycled();
    }

    public static void recycleSilently(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                bitmap.recycle();
            } catch (Exception e) {
                Log.w("BaseBitmapUtils", "unable recycle bitmap %s", e);
            }
        }
    }
}
