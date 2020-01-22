package com.miui.gallery.editor.photo.screen.mosaic.shader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Shader;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MosaicEntityBitmap extends MosaicEntity {
    private String mResourcePath;
    private Shader.TileMode mTileMode;

    public MosaicEntityBitmap(String str, String str2, String str3, Shader.TileMode tileMode) {
        super(str, str2, MosaicEntity.TYPE.BITMAP);
        this.mResourcePath = str3;
        this.mTileMode = tileMode;
    }

    public MosaicShaderHolder generateShader(Bitmap bitmap, float f, Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float f2 = fArr[0];
        Bitmap loadImageSync = ImageLoader.getInstance().loadImageSync(this.mResourcePath);
        Shader.TileMode tileMode = this.mTileMode;
        BitmapShader bitmapShader = new BitmapShader(loadImageSync, tileMode, tileMode);
        Matrix matrix2 = new Matrix();
        float f3 = 0.8f / f2;
        matrix2.postScale(f3, f3);
        bitmapShader.setLocalMatrix(matrix2);
        return new MosaicShaderHolder(bitmapShader, MosaicEntity.TYPE.BITMAP);
    }
}
