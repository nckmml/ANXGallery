package com.miui.gallery.editor.photo.screen.mosaic.shader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Shader;
import com.miui.gallery.editor.photo.screen.mosaic.shader.MosaicEntity;
import com.miui.gallery.util.Log;
import java.lang.reflect.Array;

public class MosaicEntityTriangle extends MosaicEntityRely {
    public MosaicEntityTriangle(String str, String str2) {
        super(str, str2, MosaicEntity.TYPE.TRIANGLE);
    }

    public MosaicShaderHolder generateShader(Bitmap bitmap, float f, Matrix matrix) {
        int i;
        Bitmap bitmap2 = bitmap;
        int round = Math.round(36.0f * f);
        if (bitmap.getWidth() < round || bitmap.getHeight() < round) {
            return new MosaicShaderHolder(new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP), MosaicEntity.TYPE.TRIANGLE);
        }
        long currentTimeMillis = System.currentTimeMillis();
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{(bitmap.getHeight() / round) + 1, (bitmap.getWidth() / round) + 1});
        int[][] iArr2 = (int[][]) Array.newInstance(int.class, new int[]{(bitmap.getHeight() / round) + 1, (bitmap.getWidth() / round) + 1});
        Log.d("MosaicEntityTriangle", "allocate cache costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        int i2 = 0;
        for (int i3 = 0; i3 < bitmap.getHeight(); i3 += round) {
            int i4 = 0;
            for (int i5 = 0; i5 < bitmap.getWidth(); i5 += round) {
                int i6 = round / 4;
                int i7 = i5 + i6;
                if (i7 >= bitmap.getWidth()) {
                    i7 = (bitmap.getWidth() + i5) / 2;
                }
                int i8 = i6 + i3;
                if (i8 >= bitmap.getHeight()) {
                    i8 = (bitmap.getHeight() + i3) / 2;
                }
                iArr[i2][i4] = bitmap2.getPixel(i7, i8);
                int i9 = (round * 3) / 4;
                int i10 = i5 + i9;
                if (i10 >= bitmap.getWidth()) {
                    i10 = (bitmap.getWidth() + i5) / 2;
                }
                int i11 = i9 + i3;
                if (i11 >= bitmap.getHeight()) {
                    i11 = (bitmap.getHeight() + i3) / 2;
                }
                iArr2[i2][i4] = bitmap2.getPixel(i10, i11);
                i4++;
            }
            i2++;
        }
        Log.d("MosaicEntityTriangle", "init cache costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        int[] iArr3 = new int[(bitmap.getWidth() * bitmap.getHeight())];
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < iArr3.length; i14 = i) {
            int i15 = 0;
            int i16 = 0;
            i = i14;
            int i17 = 0;
            while (i17 < bitmap.getWidth()) {
                if (i12 < round - i15) {
                    iArr3[i] = iArr[i13][i16];
                } else {
                    iArr3[i] = iArr2[i13][i16];
                }
                i15++;
                if (i15 == round) {
                    i16++;
                    i15 = 0;
                }
                i17++;
                i++;
            }
            i12++;
            if (i12 == round) {
                i13++;
                i12 = 0;
            }
        }
        Log.d("MosaicEntityTriangle", "generate pixels costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        Bitmap createBitmap = Bitmap.createBitmap(iArr3, bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Log.d("MosaicEntityTriangle", "finish costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return new MosaicShaderHolder(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP), MosaicEntity.TYPE.TRIANGLE);
    }
}
