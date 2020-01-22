package com.miui.gallery.util;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

public class BitmapUtils extends BaseBitmapUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final HashSet<String> REGION_DECODER_SUPPORTED_MIMETYPES = new HashSet<>();

    /* renamed from: com.miui.gallery.util.BitmapUtils$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 2;
            $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 3;
            $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGBA_F16.ordinal()] = 4;
            $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 5;
        }
    }

    public static class BitmapDimension {
        public int height;
        public int width;
    }

    static {
        REGION_DECODER_SUPPORTED_MIMETYPES.add("image/png");
        REGION_DECODER_SUPPORTED_MIMETYPES.add("image/jpeg");
        REGION_DECODER_SUPPORTED_MIMETYPES.add("image/jpg");
    }

    private BitmapUtils() {
    }

    public static int computeSampleSizeSmaller(int i, int i2, int i3, int i4) {
        if (i <= 0 || i2 <= 0 || i3 <= 0 || i4 <= 0) {
            return 1;
        }
        int i5 = i2 / i > i4 / i3 ? i / i3 : i2 / i4;
        if (i5 <= 1) {
            return 1;
        }
        return i5 <= 8 ? Utils.prevPowerOf2(i5) : (i5 / 8) * 8;
    }

    public static int computeThumbNailSampleSize(int i, int i2, int i3, int i4) {
        return reviseSampleSize((int) Math.floor((double) (1.0f / computeThumbNailScaleSize(i, i2, i3, i4))));
    }

    public static float computeThumbNailScaleSize(int i, int i2, int i3, int i4) {
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        return Math.min((((float) Math.min(i3, i4)) * 1.0f) / ((float) min), (((float) Math.max(i3, i4)) * 1.0f) / ((float) max));
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i, int i2) {
        Bitmap bitmap2;
        if (!isValid(bitmap) || i <= 0 || i2 <= 0) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > i) {
                if (height > i2) {
                    float f = (float) width;
                    float f2 = (float) height;
                    float f3 = (float) i;
                    float f4 = (float) i2;
                    float f5 = f / f2 > f3 / f4 ? f4 / f2 : f3 / f;
                    Matrix matrix = new Matrix();
                    matrix.setScale(f5, f5);
                    bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                    return bitmap2;
                }
            }
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
            return bitmap2;
        } catch (Exception e) {
            Log.e("BitmapUtils", "createScaledBitmap occur error.\n", e);
            return null;
        }
    }

    public static Bitmap fitBitmapToScreen(Bitmap bitmap, int i, int i2, boolean z) {
        return resizeBitmapByScale(bitmap, computeThumbNailScaleSize(bitmap.getWidth(), bitmap.getHeight(), i, i2), z);
    }

    public static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        return config == null ? Bitmap.Config.ARGB_8888 : config;
    }

    public static short getConfigSize(Bitmap.Config config) {
        int i = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()];
        short s = 1;
        if (i != 1) {
            s = 2;
            if (i != 2) {
                if (i == 3) {
                    return 4;
                }
                if (i != 4) {
                    return i != 5 ? (short) 0 : 2;
                }
                return 8;
            }
        }
        return s;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f, int i, int i2) {
        Bitmap bitmap2 = null;
        try {
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i2);
            Paint paint = new Paint();
            bitmap2 = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawRoundRect(rectF, f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rectF, paint);
            return bitmap2;
        } catch (OutOfMemoryError e) {
            Log.e("BitmapUtils", "getRoundedCornerBitmap() failed OOM %s", e);
            return bitmap2;
        } catch (Exception e2) {
            Log.e("BitmapUtils", "getRoundedCornerBitmap() failed %s", e2);
            return bitmap2;
        }
    }

    public static boolean isBitmapInScreen(int i, int i2, int i3, int i4) {
        return Math.min(i, i2) <= Math.min(i3, i4) && Math.max(i, i2) <= Math.max(i3, i4);
    }

    public static boolean isRotationSupported(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.equals("image/jpeg") || lowerCase.equals("image/png") || lowerCase.equals("image/bmp");
    }

    public static boolean isSupportedByRegionDecoder(String str) {
        return REGION_DECODER_SUPPORTED_MIMETYPES.contains(str);
    }

    public static boolean isValid(Bitmap bitmap) {
        return bitmap != null && !bitmap.isRecycled();
    }

    public static boolean isValid(BitmapRegionDecoder bitmapRegionDecoder) {
        return bitmapRegionDecoder != null && !bitmapRegionDecoder.isRecycled();
    }

    public static Bitmap resizeBitmapByScale(Bitmap bitmap, float f, boolean z) {
        int round = Math.round(((float) bitmap.getWidth()) * f);
        int round2 = Math.round(((float) bitmap.getHeight()) * f);
        if (round == bitmap.getWidth() && round2 == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap safeCreateBitmap = safeCreateBitmap(round, round2, getConfig(bitmap));
        if (safeCreateBitmap != null) {
            Canvas canvas = new Canvas(safeCreateBitmap);
            canvas.scale(f, f);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(2));
            if (z) {
                bitmap.recycle();
            }
        }
        return safeCreateBitmap;
    }

    private static int reviseSampleSize(int i) {
        if (i <= 1) {
            i = 1;
        }
        return i <= 8 ? Utils.prevPowerOf2(i) : (i / 8) * 8;
    }

    public static Bitmap safeCreateBitmap(int i, int i2, Bitmap.Config config) {
        try {
            return Bitmap.createBitmap(i, i2, config);
        } catch (OutOfMemoryError e) {
            Log.e("BitmapUtils", "safeCreateBitmap() failed OOM: %s", e);
            return null;
        } catch (Exception e2) {
            Log.e("BitmapUtils", "safeCreateBitmap() failed: %s", e2);
            return null;
        }
    }

    public static Bitmap safeCreateBitmap(Bitmap bitmap, int i, int i2, int i3, int i4) {
        try {
            return Bitmap.createBitmap(bitmap, i, i2, i3, i4);
        } catch (OutOfMemoryError e) {
            Log.e("BitmapUtils", "safeCreateBitmap() failed OOM %s", e);
            return null;
        }
    }

    public static Bitmap safeCreateBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, Matrix matrix, boolean z, Bitmap.Config config) {
        Bitmap bitmap2;
        RectF rectF;
        Paint paint;
        if (!isValid(bitmap)) {
            return null;
        }
        if (!bitmap.isMutable() && bitmap.getConfig() == config && i == 0 && i2 == 0 && i3 == bitmap.getWidth() && i4 == bitmap.getHeight() && (matrix == null || matrix.isIdentity())) {
            return bitmap;
        }
        Rect rect = new Rect(i, i2, i + i3, i2 + i4);
        RectF rectF2 = new RectF(0.0f, 0.0f, (float) i3, (float) i4);
        boolean z2 = true;
        if (matrix == null || matrix.isIdentity()) {
            bitmap2 = Bitmap.createBitmap(i3, i4, config);
            rectF = null;
            paint = null;
        } else {
            boolean z3 = !matrix.rectStaysRect();
            rectF = new RectF();
            matrix.mapRect(rectF, rectF2);
            bitmap2 = Bitmap.createBitmap(Math.round(rectF.width()), Math.round(rectF.height()), config);
            paint = new Paint();
            paint.setFilterBitmap(z);
            if (z3) {
                paint.setAntiAlias(true);
            }
        }
        bitmap2.setDensity(bitmap.getDensity());
        if ((bitmap2.getConfig() != Bitmap.Config.ARGB_8888 || !bitmap2.hasAlpha()) && !bitmap.isPremultiplied()) {
            z2 = false;
        }
        bitmap2.setPremultiplied(z2);
        Canvas canvas = new Canvas(bitmap2);
        if (rectF != null) {
            canvas.translate(-rectF.left, -rectF.top);
            canvas.concat(matrix);
        }
        canvas.drawBitmap(bitmap, rect, rectF2, paint);
        canvas.setBitmap((Bitmap) null);
        return bitmap2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002a A[SYNTHETIC, Splitter:B:21:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0037 A[SYNTHETIC, Splitter:B:29:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x003d A[SYNTHETIC, Splitter:B:33:0x003d] */
    public static BitmapRegionDecoder safeCreateBitmapRegionDecoder(String str, boolean z, byte[] bArr) {
        InputStream inputStream;
        BitmapRegionDecoder bitmapRegionDecoder = null;
        try {
            inputStream = createInputStream(str, bArr);
            try {
                bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, z);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Log.e("BitmapUtils", "close inputStream failed %s", e);
                    }
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
                Log.e("BitmapUtils", "safeCreateBitmapRegionDecoder() failed OOM %s", e);
                if (inputStream != null) {
                    inputStream.close();
                }
                return bitmapRegionDecoder;
            } catch (Exception e3) {
                e = e3;
                try {
                    Log.e("BitmapUtils", "safeCreateBitmapRegionDecoder() failed %s", e);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return bitmapRegionDecoder;
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            Log.e("BitmapUtils", "close inputStream failed %s", e4);
                        }
                    }
                    throw th;
                }
            }
        } catch (OutOfMemoryError e5) {
            e = e5;
            inputStream = null;
            Log.e("BitmapUtils", "safeCreateBitmapRegionDecoder() failed OOM %s", e);
            if (inputStream != null) {
            }
            return bitmapRegionDecoder;
        } catch (Exception e6) {
            e = e6;
            inputStream = null;
            Log.e("BitmapUtils", "safeCreateBitmapRegionDecoder() failed %s", e);
            if (inputStream != null) {
            }
            return bitmapRegionDecoder;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            if (inputStream != null) {
            }
            throw th;
        }
        return bitmapRegionDecoder;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002a A[SYNTHETIC, Splitter:B:21:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0037 A[SYNTHETIC, Splitter:B:29:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x003d A[SYNTHETIC, Splitter:B:33:0x003d] */
    public static Bitmap safeDecodeBitmap(ContentResolver contentResolver, Uri uri) {
        InputStream inputStream;
        Bitmap bitmap = null;
        try {
            inputStream = contentResolver.openInputStream(uri);
            try {
                bitmap = BitmapFactory.decodeStream(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Log.e("BitmapUtils", "close inputStream failed %s", e);
                    }
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
                Log.e("BitmapUtils", "safeDecodeBitmap() failed OOM %s", e);
                if (inputStream != null) {
                    inputStream.close();
                }
                return bitmap;
            } catch (Exception e3) {
                e = e3;
                try {
                    Log.e("BitmapUtils", "safeDecodeBitmap() failed %s", e);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return bitmap;
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            Log.e("BitmapUtils", "close inputStream failed %s", e4);
                        }
                    }
                    throw th;
                }
            }
        } catch (OutOfMemoryError e5) {
            e = e5;
            inputStream = null;
            Log.e("BitmapUtils", "safeDecodeBitmap() failed OOM %s", e);
            if (inputStream != null) {
            }
            return bitmap;
        } catch (Exception e6) {
            e = e6;
            inputStream = null;
            Log.e("BitmapUtils", "safeDecodeBitmap() failed %s", e);
            if (inputStream != null) {
            }
            return bitmap;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            if (inputStream != null) {
            }
            throw th;
        }
        return bitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002a A[SYNTHETIC, Splitter:B:21:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0037 A[SYNTHETIC, Splitter:B:29:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x003d A[SYNTHETIC, Splitter:B:33:0x003d] */
    public static Bitmap safeDecodeBitmap(String str, BitmapFactory.Options options, byte[] bArr) {
        InputStream inputStream;
        Bitmap bitmap = null;
        try {
            inputStream = createInputStream(str, bArr);
            try {
                bitmap = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Log.e("BitmapUtils", "close inputStream failed %s", e);
                    }
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
                Log.e("BitmapUtils", "safeDecodeBitmap() failed OOM %s", e);
                if (inputStream != null) {
                    inputStream.close();
                }
                return bitmap;
            } catch (Exception e3) {
                e = e3;
                try {
                    Log.e("BitmapUtils", "safeDecodeBitmap() failed %s", e);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return bitmap;
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            Log.e("BitmapUtils", "close inputStream failed %s", e4);
                        }
                    }
                    throw th;
                }
            }
        } catch (OutOfMemoryError e5) {
            e = e5;
            inputStream = null;
            Log.e("BitmapUtils", "safeDecodeBitmap() failed OOM %s", e);
            if (inputStream != null) {
            }
            return bitmap;
        } catch (Exception e6) {
            e = e6;
            inputStream = null;
            Log.e("BitmapUtils", "safeDecodeBitmap() failed %s", e);
            if (inputStream != null) {
            }
            return bitmap;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            if (inputStream != null) {
            }
            throw th;
        }
        return bitmap;
    }

    public static Bitmap safeDecodeRegion(BitmapRegionDecoder bitmapRegionDecoder, Rect rect, BitmapFactory.Options options) {
        try {
            return bitmapRegionDecoder.decodeRegion(rect, options);
        } catch (OutOfMemoryError e) {
            Log.e("BitmapUtils", "safeDecodeRegion() failed OOM %s", e);
            return null;
        } catch (Exception e2) {
            Log.e("BitmapUtils", "safeDecodeRegion() failed %s", e2);
            return null;
        }
    }
}
