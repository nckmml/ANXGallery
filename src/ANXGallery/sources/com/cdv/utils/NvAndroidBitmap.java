package com.cdv.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class NvAndroidBitmap {
    public static final int ASPECT_RATIO_MODE_IGNORE = 0;
    public static final int ASPECT_RATIO_MODE_KEEP = 1;
    public static final int ASPECT_RATIO_MODE_KEEP_EXPANDING = 2;
    private static final String TAG = "NvAndroidBitmap";

    public static class ImageInfo {
        int height;
        String mimeType;
        int orientation;
        int width;
    }

    public static Bitmap convertBitmapToRGBA(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
            return bitmap;
        }
        try {
            return bitmap.copy(Bitmap.Config.ARGB_8888, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createBitmap(Context context, String str, Size size, int i, boolean z) {
        ImageInfo imageInfo = getImageInfo(context, str);
        if (imageInfo == null) {
            return null;
        }
        try {
            Size size2 = new Size(imageInfo.width, imageInfo.height);
            InputStream tryCreateInputStreamFromImageFilePath = tryCreateInputStreamFromImageFilePath(context, str);
            if (tryCreateInputStreamFromImageFilePath == null) {
                return createBitmap(str, (InputStream) null, size2, size, i, z);
            }
            Bitmap createBitmap = createBitmap((String) null, tryCreateInputStreamFromImageFilePath, size2, size, i, z);
            tryCreateInputStreamFromImageFilePath.close();
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap createBitmap(String str, InputStream inputStream, Size size, Size size2, int i, boolean z) {
        Size size3;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (!(size2 == null || size == null)) {
                if (size2.getWidth() != size.getWidth() || size2.getHeight() != size.getHeight()) {
                    if (i != 0) {
                        double width = ((double) size.getWidth()) / ((double) size.getHeight());
                        double width2 = ((double) size2.getWidth()) / ((double) size2.getHeight());
                        size3 = i == 1 ? width >= width2 ? new Size(size2.getWidth(), (int) ((((double) size2.getWidth()) / width) + 0.5d)) : new Size((int) ((((double) size2.getHeight()) * width) + 0.5d), size2.getHeight()) : width >= width2 ? new Size((int) ((((double) size2.getHeight()) * width) + 0.5d), size2.getHeight()) : new Size(size2.getWidth(), (int) ((((double) size2.getWidth()) / width) + 0.5d));
                    } else {
                        size3 = size2;
                    }
                    options.inSampleSize = (int) (1.0f / Math.min(Math.max(((float) size3.getWidth()) / ((float) size.getWidth()), ((float) size3.getHeight()) / ((float) size.getHeight())), 1.0f));
                    Bitmap decodeFile = str != null ? BitmapFactory.decodeFile(str, options) : BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                    if (decodeFile == null) {
                        return null;
                    }
                    return (decodeFile.getWidth() > size3.getWidth() || decodeFile.getHeight() > size3.getHeight()) ? Bitmap.createScaledBitmap(decodeFile, size3.getWidth(), size3.getHeight(), z) : decodeFile;
                }
            }
            return str != null ? BitmapFactory.decodeFile(str, options) : BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap createBitmap(byte[] bArr, Size size, int i, boolean z) {
        ImageInfo imageInfo = getImageInfo(bArr);
        if (imageInfo == null) {
            return null;
        }
        try {
            return createBitmap((String) null, new ByteArrayInputStream(bArr), new Size(imageInfo.width, imageInfo.height), size, i, z);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createBitmapRegion(Context context, String str, Rect rect) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            InputStream tryCreateInputStreamFromImageFilePath = tryCreateInputStreamFromImageFilePath(context, str);
            if (tryCreateInputStreamFromImageFilePath == null) {
                return BitmapRegionDecoder.newInstance(str, false).decodeRegion(rect, options);
            }
            Bitmap decodeRegion = BitmapRegionDecoder.newInstance(tryCreateInputStreamFromImageFilePath, false).decodeRegion(rect, options);
            tryCreateInputStreamFromImageFilePath.close();
            return decodeRegion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createBitmapRegion(byte[] bArr, Rect rect) {
        try {
            return BitmapRegionDecoder.newInstance(bArr, 0, bArr.length, false).decodeRegion(rect, new BitmapFactory.Options());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createRgbaBitmap(int i, int i2) {
        try {
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createRotatedBitmap(Context context, String str, Size size, int i, boolean z) {
        Bitmap bitmap;
        ImageInfo imageInfo = getImageInfo(context, str);
        if (imageInfo == null) {
            return null;
        }
        try {
            Size size2 = new Size(imageInfo.width, imageInfo.height);
            InputStream tryCreateInputStreamFromImageFilePath = tryCreateInputStreamFromImageFilePath(context, str);
            if (tryCreateInputStreamFromImageFilePath != null) {
                bitmap = createBitmap((String) null, tryCreateInputStreamFromImageFilePath, size2, size, i, z);
                tryCreateInputStreamFromImageFilePath.close();
            } else {
                bitmap = createBitmap(str, (InputStream) null, size2, size, i, z);
            }
            if (bitmap == null) {
                return null;
            }
            return imageInfo.orientation == 1 ? bitmap : transformBitmap(bitmap, imageInfo.orientation);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createRotatedBitmap(byte[] bArr, Size size, int i, boolean z) {
        ImageInfo imageInfo = getImageInfo(bArr);
        if (imageInfo == null) {
            return null;
        }
        try {
            Bitmap createBitmap = createBitmap((String) null, new ByteArrayInputStream(bArr), new Size(imageInfo.width, imageInfo.height), size, i, z);
            if (createBitmap == null) {
                return null;
            }
            return imageInfo.orientation == 1 ? createBitmap : transformBitmap(createBitmap, imageInfo.orientation);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageInfo getImageInfo(Context context, String str) {
        ImageInfo imageInfo;
        if (str != null && !str.isEmpty()) {
            try {
                InputStream tryCreateInputStreamFromImageFilePath = tryCreateInputStreamFromImageFilePath(context, str);
                if (tryCreateInputStreamFromImageFilePath != null) {
                    imageInfo = getImageInfo(tryCreateInputStreamFromImageFilePath);
                    tryCreateInputStreamFromImageFilePath.close();
                    if (imageInfo == null) {
                        return null;
                    }
                } else {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(str, options);
                    if (options.outMimeType != null && options.outWidth >= 0) {
                        if (options.outHeight >= 0) {
                            ImageInfo imageInfo2 = new ImageInfo();
                            imageInfo2.mimeType = options.outMimeType;
                            imageInfo2.width = options.outWidth;
                            imageInfo2.height = options.outHeight;
                            imageInfo2.orientation = 1;
                            imageInfo = imageInfo2;
                        }
                    }
                    Log.e(TAG, "Failed to get image information for " + str);
                    return null;
                }
                if (imageInfo.mimeType.equals("image/jpeg")) {
                    InputStream tryCreateInputStreamFromImageFilePath2 = tryCreateInputStreamFromImageFilePath(context, str);
                    if (tryCreateInputStreamFromImageFilePath2 != null) {
                        imageInfo.orientation = getImageRotation(tryCreateInputStreamFromImageFilePath2);
                        tryCreateInputStreamFromImageFilePath2.close();
                    } else {
                        imageInfo.orientation = getImageRotation(str);
                    }
                }
                return imageInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static ImageInfo getImageInfo(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, (Rect) null, options);
            if (options.outMimeType != null && options.outWidth >= 0) {
                if (options.outHeight >= 0) {
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.mimeType = options.outMimeType;
                    imageInfo.width = options.outWidth;
                    imageInfo.height = options.outHeight;
                    imageInfo.orientation = 1;
                    return imageInfo;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageInfo getImageInfo(byte[] bArr) {
        ImageInfo imageInfo;
        if (bArr == null || (imageInfo = getImageInfo((InputStream) new ByteArrayInputStream(bArr))) == null) {
            return null;
        }
        if (imageInfo.mimeType.equals("image/jpeg")) {
            imageInfo.orientation = getImageRotation((InputStream) new ByteArrayInputStream(bArr));
        }
        return imageInfo;
    }

    private static int getImageRotation(InputStream inputStream) {
        if (Build.VERSION.SDK_INT < 24) {
            return 1;
        }
        try {
            return new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    private static int getImageRotation(String str) {
        try {
            return new ExifInterface(str).getAttributeInt("Orientation", 1);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate((float) i);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveBitmapToFile(Bitmap bitmap, int i, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
            if (str.endsWith(".png")) {
                compressFormat = Bitmap.CompressFormat.PNG;
            }
            return bitmap.compress(compressFormat, i, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Bitmap transformBitmap(Bitmap bitmap, int i) throws Exception {
        Matrix matrix = new Matrix();
        switch (i) {
            case 2:
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.postRotate(180.0f);
                break;
            case 4:
                matrix.postScale(1.0f, -1.0f);
                break;
            case 5:
                matrix.postScale(-1.0f, 1.0f);
                matrix.postRotate(270.0f);
                break;
            case 6:
                matrix.postRotate(90.0f);
                break;
            case 7:
                matrix.postScale(-1.0f, 1.0f);
                matrix.postRotate(90.0f);
                break;
            case 8:
                matrix.postRotate(270.0f);
                break;
            default:
                return bitmap;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    private static InputStream tryCreateInputStreamFromImageFilePath(Context context, String str) {
        try {
            if (str.startsWith("assets:/")) {
                if (context == null) {
                    return null;
                }
                return context.getAssets().open(str.substring(8));
            } else if (!str.startsWith("content://") || context == null) {
                return null;
            } else {
                return context.getContentResolver().openInputStream(Uri.parse(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
