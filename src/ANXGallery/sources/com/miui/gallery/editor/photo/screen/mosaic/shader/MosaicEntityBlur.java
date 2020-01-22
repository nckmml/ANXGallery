package com.miui.gallery.editor.photo.screen.mosaic.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntity;
import com.miui.gallery.util.Log;

public class MosaicEntityBlur extends MosaicEntityRely {
    private RenderScript mRS;
    private ScriptIntrinsicBlur mScriptBlur;

    public MosaicEntityBlur(String str, String str2, Context context) {
        super(str, str2, MosaicEntity.TYPE.BLUR);
        this.mRS = RenderScript.create(context);
        RenderScript renderScript = this.mRS;
        this.mScriptBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
    }

    public MosaicShaderHolder generateShader(Bitmap bitmap, float f, Matrix matrix) {
        Log.d("MosaicEntityRely", "MosaicEntityBlur generateShader.");
        float f2 = f * 15.0f;
        if (f2 > 25.0f) {
            f2 = 25.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Allocation createFromBitmap = Allocation.createFromBitmap(this.mRS, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(this.mRS, createBitmap);
        this.mScriptBlur.setRadius(f2);
        this.mScriptBlur.setInput(createFromBitmap);
        this.mScriptBlur.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        return new MosaicShaderHolder(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP), MosaicEntity.TYPE.BLUR);
    }
}
