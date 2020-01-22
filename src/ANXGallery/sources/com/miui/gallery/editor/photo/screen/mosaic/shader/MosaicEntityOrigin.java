package com.miui.gallery.editor.photo.screen.mosaic.shader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Shader;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntity;

public class MosaicEntityOrigin extends MosaicEntity {
    public MosaicEntityOrigin(String str, String str2) {
        super(str, str2, MosaicEntity.TYPE.ORIGIN);
    }

    public MosaicShaderHolder generateShader(Bitmap bitmap, float f, Matrix matrix) {
        BitmapShader bitmapShader = new BitmapShader((Bitmap) null, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        return new MosaicShaderHolder(bitmapShader, MosaicEntity.TYPE.ORIGIN);
    }
}
