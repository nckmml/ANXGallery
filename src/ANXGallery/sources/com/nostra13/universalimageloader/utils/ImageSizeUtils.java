package com.nostra13.universalimageloader.utils;

import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Looper;
import com.miui.gallery.BaseConfig;
import com.miui.gallery.util.Log;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public final class ImageSizeUtils {
    private static ImageSize maxBitmapSize;
    private static Object sLock = new Object();

    /* renamed from: com.nostra13.universalimageloader.utils.ImageSizeUtils$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$nostra13$universalimageloader$core$assist$ViewScaleType = new int[ViewScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$nostra13$universalimageloader$core$assist$ViewScaleType[ViewScaleType.FIT_INSIDE.ordinal()] = 1;
            $SwitchMap$com$nostra13$universalimageloader$core$assist$ViewScaleType[ViewScaleType.CROP.ordinal()] = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0059  */
    public static int computeImageSampleSize(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int i2;
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        int width2 = imageSize2.getWidth();
        int height2 = imageSize2.getHeight();
        int i3 = AnonymousClass1.$SwitchMap$com$nostra13$universalimageloader$core$assist$ViewScaleType[viewScaleType.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                i = 1;
            } else if (z) {
                int i4 = width / 2;
                int i5 = height / 2;
                i2 = 1;
                while (i4 / i2 > width2 && i5 / i2 > height2) {
                    i2 *= 2;
                }
            } else {
                i = Math.min(width / width2, height / height2);
            }
            if (i < 1) {
                i = 1;
            }
            return considerMaxTextureSize(width, height, i, z);
        } else if (z) {
            int i6 = width / 2;
            int i7 = height / 2;
            int i8 = 1;
            while (true) {
                if (i6 / i2 <= width2 && i7 / i2 <= height2) {
                    break;
                }
                i8 = i2 * 2;
            }
        } else {
            i = Math.max(width / width2, height / height2);
            if (i < 1) {
            }
            return considerMaxTextureSize(width, height, i, z);
        }
        i = i2;
        if (i < 1) {
        }
        return considerMaxTextureSize(width, height, i, z);
    }

    public static float computeImageScale(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int max = Math.max(imageSize.getWidth(), imageSize.getHeight());
        int min = Math.min(imageSize.getWidth(), imageSize.getHeight());
        int max2 = Math.max(imageSize2.getWidth(), imageSize2.getHeight());
        int min2 = Math.min(imageSize2.getWidth(), imageSize2.getHeight());
        float f = (float) max;
        float f2 = f / ((float) max2);
        float f3 = (float) min;
        float f4 = f3 / ((float) min2);
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f2 < f4) && (viewScaleType != ViewScaleType.CROP || f2 >= f4)) {
            max2 = (int) (f / f4);
        } else {
            min2 = (int) (f3 / f2);
        }
        if ((z || max2 >= max || min2 >= min) && (!z || max2 == max || min2 == min)) {
            return 1.0f;
        }
        return ((float) max2) / f;
    }

    public static int computeMinImageSampleSize(ImageSize imageSize, Bitmap.Config config) {
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        ImageSize maxBitmapSize2 = getMaxBitmapSize();
        return considerCanvasMaxBitmapSize(imageSize, Math.max((int) Math.ceil((double) (((float) width) / ((float) maxBitmapSize2.getWidth()))), (int) Math.ceil((double) (((float) height) / ((float) maxBitmapSize2.getHeight())))), config);
    }

    private static int considerCanvasMaxBitmapSize(ImageSize imageSize, int i, Bitmap.Config config) {
        int maxCanvasBitmapSize = getMaxCanvasBitmapSize();
        if (maxCanvasBitmapSize > 0) {
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }
            while ((imageSize.getWidth() / i) * (imageSize.getHeight() / i) * getBytesPerPixel(config) > maxCanvasBitmapSize) {
                i++;
            }
        }
        return i;
    }

    private static int considerMaxTextureSize(int i, int i2, int i3, boolean z) {
        ImageSize maxBitmapSize2 = getMaxBitmapSize();
        int width = maxBitmapSize2.getWidth();
        int height = maxBitmapSize2.getHeight();
        while (true) {
            if (i / i3 <= width && i2 / i3 <= height) {
                return i3;
            }
            i3 = z ? i3 * 2 : i3 + 1;
        }
    }

    public static ImageSize defineTargetSizeForView(ImageAware imageAware, ImageSize imageSize) {
        int width = imageAware.getWidth();
        if (width <= 0) {
            width = imageSize.getWidth();
        }
        int height = imageAware.getHeight();
        if (height <= 0) {
            height = imageSize.getHeight();
        }
        return new ImageSize(width, height);
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        }
        if (config == Bitmap.Config.RGB_565 || config == Bitmap.Config.ARGB_4444) {
            return 2;
        }
        if (config == Bitmap.Config.ALPHA_8) {
        }
        return 1;
    }

    private static ImageSize getMaxBitmapSize() {
        if (maxBitmapSize == null) {
            synchronized (sLock) {
                if (maxBitmapSize == null) {
                    int maxTextureSizeInternal = getMaxTextureSizeInternal();
                    Log.d("ImageSizeUtils", "maxTextureSize: %d", (Object) Integer.valueOf(maxTextureSizeInternal));
                    int max = Math.max(Math.max(maxTextureSizeInternal, 2048), Math.max(BaseConfig.ScreenConfig.getScreenWidth(), BaseConfig.ScreenConfig.getScreenHeight()));
                    maxBitmapSize = new ImageSize(max, max);
                }
            }
        }
        return maxBitmapSize;
    }

    private static int getMaxCanvasBitmapSize() {
        int i = Build.VERSION.SDK_INT;
        return 104857600;
    }

    public static int getMaxTextureSize() {
        return getMaxBitmapSize().getWidth();
    }

    private static int getMaxTextureSizeInternal() {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        if (iArr[0] > 0) {
            Log.d("ImageSizeUtils", "got GL_MAX_TEXTURE_SIZE without GLContext %d", (Object) Integer.valueOf(iArr[0]));
            return iArr[0];
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.e("ImageSizeUtils", "call in main thread, skip");
            return 0;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
            int[] iArr2 = new int[2];
            EGL14.eglInitialize(eglGetDisplay, iArr2, 0, iArr2, 1);
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr3 = new int[1];
            EGLConfig[] eGLConfigArr2 = eGLConfigArr;
            EGL14.eglChooseConfig(eglGetDisplay, new int[]{12351, 12430, 12329, 0, 12352, 4, 12339, 1, 12344}, 0, eGLConfigArr, 0, 1, iArr3, 0);
            if (iArr3[0] == 0) {
                Log.e("ImageSizeUtils", "no config found");
                return 0;
            }
            EGLConfig eGLConfig = eGLConfigArr2[0];
            EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 64, 12374, 64, 12344}, 0);
            EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
            EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
            GLES20.glGetIntegerv(3379, iArr, 0);
            EGL14.eglMakeCurrent(eglGetDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            EGL14.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
            EGL14.eglDestroyContext(eglGetDisplay, eglCreateContext);
            EGL14.eglTerminate(eglGetDisplay);
            Log.d("ImageSizeUtils", "get GL_MAX_TEXTURE_SIZE cost %s", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return iArr[0];
        }
    }
}
