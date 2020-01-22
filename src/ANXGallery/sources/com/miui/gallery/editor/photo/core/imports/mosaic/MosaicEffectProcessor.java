package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicGLEntity;
import com.miui.gallery.editor.photo.core.imports.mosaic.shader.GLTextureSizeShader;
import com.miui.gallery.editor.photo.widgets.glview.GLFBOManager;
import com.miui.gallery.editor.photo.widgets.glview.shader.GLShaderGroup;
import com.miui.gallery.editor.photo.widgets.glview.shader.GLTextureShader;
import com.miui.gallery.util.ScreenUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

class MosaicEffectProcessor {
    private final int mOriginTextureHeight;
    private final int mOriginTextureWidth;
    private float[] mTextureCood = new float[8];
    private final int mViewHeight;
    private final int mViewWidth;

    /* renamed from: com.miui.gallery.editor.photo.core.imports.mosaic.MosaicEffectProcessor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE = new int[MosaicGLEntity.TYPE.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[MosaicGLEntity.TYPE.ORIGIN.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[MosaicGLEntity.TYPE.RESOURCE.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[MosaicGLEntity.TYPE.EFFECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    MosaicEffectProcessor(int i, int i2, int i3, int i4) {
        this.mOriginTextureWidth = i;
        this.mOriginTextureHeight = i2;
        this.mViewWidth = i3;
        this.mViewHeight = i4;
    }

    private Bitmap changeBitmapSize(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        float screenWidth = ((float) this.mOriginTextureWidth) / ((float) ScreenUtils.getScreenWidth());
        if (screenWidth >= 1.0f) {
            return bitmap;
        }
        matrix.postScale(screenWidth, screenWidth);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    private void drawEffect(GLFBOManager gLFBOManager, MosaicGLEffectEntity mosaicGLEffectEntity, GLTextureShader gLTextureShader, int i, float f, float f2) {
        GLTextureSizeShader generateSpecificShader = mosaicGLEffectEntity.generateSpecificShader(this.mOriginTextureWidth, this.mOriginTextureHeight, this.mViewWidth, this.mViewHeight);
        if (generateSpecificShader != null) {
            generateSpecificShader.setScale(f);
            generateSpecificShader.setMosaicScale(f2);
            if (generateSpecificShader instanceof GLShaderGroup) {
                int effectedTexture = ((GLShaderGroup) generateSpecificShader).getEffectedTexture(i);
                gLFBOManager.bind();
                GLES20.glClear(16640);
                gLTextureShader.draw(effectedTexture);
                gLFBOManager.unBind();
            } else {
                gLFBOManager.bind();
                GLES20.glClear(16640);
                generateSpecificShader.drawFBO(i);
                gLFBOManager.unBind();
            }
            generateSpecificShader.destroy();
        }
    }

    private void drawOrigin(GLTextureShader gLTextureShader, int i) {
        gLTextureShader.draw(i);
    }

    private void drawResource(MosaicGLResourceEntity mosaicGLResourceEntity, GLTextureShader gLTextureShader, float f, float f2, float f3) {
        String str = mosaicGLResourceEntity.mTileMode;
        String str2 = mosaicGLResourceEntity.mResourcePath;
        int i = 10497;
        if (str.equals("EDGE")) {
            i = 33071;
        } else {
            boolean equals = str.equals("REPEAT");
        }
        Bitmap loadImageSync = ImageLoader.getInstance().loadImageSync(str2);
        if (loadImageSync != null && loadImageSync.getWidth() > 0 && loadImageSync.getHeight() > 0) {
            Bitmap changeBitmapSize = changeBitmapSize(loadImageSync);
            float width = ((float) this.mOriginTextureWidth) / ((float) changeBitmapSize.getWidth());
            getTextureCood(this.mTextureCood, width / f, (((((float) this.mOriginTextureHeight) / ((float) changeBitmapSize.getHeight())) / f) * f2) / f3);
            int loadTexture = loadTexture(changeBitmapSize, i);
            gLTextureShader.draw(loadTexture, getGLPosition(f2), this.mTextureCood);
            GLES20.glDeleteTextures(1, new int[]{loadTexture}, 0);
        }
    }

    private float[] getGLPosition(float f) {
        float f2 = -f;
        return new float[]{-1.0f, f2, 1.0f, f2, -1.0f, f, 1.0f, f};
    }

    private static void getTextureCood(float[] fArr, float f, float f2) {
        fArr[0] = 0.0f;
        fArr[1] = f2;
        fArr[2] = f;
        fArr[3] = f2;
        fArr[4] = 0.0f;
        fArr[5] = 0.0f;
        fArr[6] = f;
        fArr[7] = 0.0f;
    }

    private static int loadTexture(Bitmap bitmap, int i) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        float f = (float) i;
        GLES20.glTexParameterf(3553, 10242, f);
        GLES20.glTexParameterf(3553, 10243, f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        bitmap.recycle();
        return iArr[0];
    }

    public void draw(GLFBOManager gLFBOManager, MosaicGLEntity mosaicGLEntity, int i, int i2, GLTextureShader gLTextureShader) {
        draw(gLFBOManager, mosaicGLEntity, i, i2, gLTextureShader, 1.0f, 1.0f, 1.0f);
    }

    public void draw(GLFBOManager gLFBOManager, MosaicGLEntity mosaicGLEntity, int i, int i2, GLTextureShader gLTextureShader, float f, float f2, float f3) {
        MosaicGLEntity mosaicGLEntity2 = mosaicGLEntity;
        if (mosaicGLEntity2 != null) {
            int i3 = AnonymousClass1.$SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE[mosaicGLEntity2.type.ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    gLFBOManager.bind();
                    GLES20.glClear(16640);
                    drawResource((MosaicGLResourceEntity) mosaicGLEntity2, gLTextureShader, f, f2, f3);
                    gLFBOManager.unBind();
                } else if (i3 == 3) {
                    drawEffect(gLFBOManager, (MosaicGLEffectEntity) mosaicGLEntity2, gLTextureShader, i, f, f3);
                }
                return;
            }
            gLFBOManager.bind();
            GLES20.glClear(16640);
            drawOrigin(gLTextureShader, i2);
            gLFBOManager.unBind();
        }
    }
}
