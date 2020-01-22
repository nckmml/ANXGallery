package com.miui.gallery.xmstreaming.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.miui.gallery.util.BaseMiscUtil;
import com.miui.gallery.util.Bitmaps;
import com.miui.gallery.util.ExifUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.xmstreaming.R;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NativeMethodUtils {
    private static final String ASSETS_START = "assets:/";
    private static final String TAG = "NativeMethodUtils";

    private static Bitmap decodeBitmap(InputStream inputStream, BitmapFactory.Options options, int i) {
        return Bitmaps.setConfig(Bitmaps.joinExif(decodeStream(inputStream, options), i, options));
    }

    public static Bitmap decodeBitmap(String str) {
        FileInputStream fileInputStream;
        Log.d("decodeBitmap", "path:%s", (Object) str);
        FileInputStream fileInputStream2 = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            decodeStream(new FileInputStream(str), options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            options.inSampleSize = Integer.highestOneBit(Math.max(Math.max(i2 / ScreenUtils.getScreenHeight(), i / ScreenUtils.getScreenWidth()), 1));
            options.inJustDecodeBounds = false;
            Log.d(TAG, "decode bitmap %d ,%d ,%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(options.inSampleSize));
            fileInputStream = new FileInputStream(str);
            try {
                Bitmap decodeBitmap = decodeBitmap(new FileInputStream(str), options, ExifUtil.getRotationDegrees(ExifUtil.sGallery3DExifCreator.create((InputStream) fileInputStream)));
                if (!(decodeBitmap == null || decodeBitmap.isRecycled() || decodeBitmap.getConfig() == Bitmap.Config.ARGB_8888)) {
                    decodeBitmap = decodeBitmap.copy(Bitmap.Config.ARGB_8888, true);
                }
                BaseMiscUtil.closeSilently(fileInputStream);
                return decodeBitmap;
            } catch (Exception e) {
                e = e;
                try {
                    e.printStackTrace();
                    BaseMiscUtil.closeSilently(fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    BaseMiscUtil.closeSilently(fileInputStream2);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            BaseMiscUtil.closeSilently(fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            BaseMiscUtil.closeSilently(fileInputStream2);
            throw th;
        }
    }

    private static Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options) {
        try {
            return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object getAssets() {
        return StaticContext.sGetAndroidContext().getAssets();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    private static Bitmap getImageWithPathOrAssets(Context context, String str) {
        InputStream inputStream;
        ? r1 = 0;
        try {
            InputStream open = str.startsWith(ASSETS_START) ? context.getResources().getAssets().open(str.replace(ASSETS_START, "")) : new FileInputStream(str);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                BaseMiscUtil.closeSilently(open);
                r1 = decodeStream;
            } catch (IOException e) {
                IOException iOException = e;
                inputStream = open;
                e = iOException;
                try {
                    e.printStackTrace();
                    BaseMiscUtil.closeSilently(inputStream);
                    return r1;
                } catch (Throwable th) {
                    th = th;
                    r1 = inputStream;
                    BaseMiscUtil.closeSilently(r1);
                    throw th;
                }
            } catch (Throwable th2) {
                r1 = open;
                th = th2;
                BaseMiscUtil.closeSilently(r1);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            e.printStackTrace();
            BaseMiscUtil.closeSilently(inputStream);
            return r1;
        } catch (Throwable th3) {
            th = th3;
            BaseMiscUtil.closeSilently(r1);
            throw th;
        }
        return r1;
    }

    public static int loadAssetTexture(String str, int i) {
        Log.d(TAG, "loadAssetTexture:%s", (Object) str);
        return loadTexture(StaticContext.sGetAndroidContext(), str, i);
    }

    private static int loadBitmapTexture(int i, Bitmap bitmap) {
        if (i != 0) {
            GLES20.glBindTexture(3553, i);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
            bitmap.recycle();
        }
        if (i != 0) {
            return i;
        }
        android.util.Log.e("OpenGlUtils", String.format("loadTexture failed", new Object[0]));
        return -1;
    }

    public static int loadTextTexture(String str, String str2, int i, int[] iArr) {
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(Typeface.DEFAULT);
        float dimension = StaticContext.sGetAndroidContext().getResources().getDimension(R.dimen.xm_movie_title);
        float dimension2 = StaticContext.sGetAndroidContext().getResources().getDimension(R.dimen.xm_movie_subtitle);
        textPaint.setTextSize(dimension);
        textPaint.setColor(-1);
        if (Build.VERSION.SDK_INT >= 21) {
            textPaint.setLetterSpacing(0.03f);
        }
        String str3 = str;
        String manageStringLength = manageStringLength(str, (int) dimension);
        String manageString2Length = manageString2Length(str2, (int) dimension2);
        StaticLayout staticLayout = new StaticLayout(manageStringLength, textPaint, ScreenUtils.getScreenWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
        Bitmap createBitmap = Bitmap.createBitmap(staticLayout.getWidth(), staticLayout.getHeight() * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        staticLayout.draw(canvas);
        canvas.translate(0.0f, (float) staticLayout.getHeight());
        textPaint.setTextSize(dimension2);
        new StaticLayout(manageString2Length, textPaint, ScreenUtils.getScreenWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true).draw(canvas);
        iArr[0] = createBitmap.getWidth();
        iArr[1] = createBitmap.getHeight();
        Log.d(TAG, "loadTextTexture %d:%d", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
        return loadBitmapTexture(i, createBitmap);
    }

    private static int loadTexture(Context context, String str, int i) {
        return loadBitmapTexture(i, getImageWithPathOrAssets(context, str));
    }

    private static String manageString2Length(String str, int i) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String trim = str.trim();
        Paint paint = new Paint();
        paint.setTextSize((float) i);
        float screenWidth = ((float) ScreenUtils.getScreenWidth()) - 100.0f;
        float measureText = paint.measureText("😀") - paint.measureText("😀".substring(0, 1));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= trim.length()) {
                i2 = i3;
                break;
            }
            i3 = i2 + 1;
            float measureText2 = paint.measureText(trim.substring(0, i3));
            if (measureText2 < screenWidth) {
                i2 = i3;
            } else if (i2 <= trim.length() - 1 && measureText2 - paint.measureText(trim.substring(0, i2)) <= measureText) {
                i2--;
            }
        }
        return trim.substring(0, i2);
    }

    private static String manageStringLength(String str, int i) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String trim = str.trim();
        Paint paint = new Paint();
        paint.setTextSize((float) i);
        float measureText = paint.measureText("一二三四五六七八九十");
        float measureText2 = paint.measureText("😀") - paint.measureText("😀".substring(0, 1));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= trim.length()) {
                break;
            }
            i3 = i2 + 1;
            float measureText3 = paint.measureText(trim.substring(0, i3));
            if (measureText3 < measureText) {
                i2 = i3;
            } else if (i2 < trim.length() - 1) {
                int i4 = i2 + 2;
                if (paint.measureText(trim.substring(0, i4)) - measureText3 <= measureText2) {
                    i3 = i4;
                }
            } else {
                i3 = trim.length();
            }
        }
        if (i3 == trim.length()) {
            return trim.substring(0, i3);
        }
        return trim.substring(0, i3) + "...";
    }
}
