package com.miui.gallery.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class VideoSeekBar extends SeekBar {
    public VideoSeekBar(Context context) {
        super(context);
    }

    public VideoSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setProgressDrawable(Drawable drawable) {
        boolean z;
        Drawable drawable2;
        boolean z2 = drawable instanceof LayerDrawable;
        LayerDrawable layerDrawable = drawable;
        if (z2) {
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable2.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            int i = 0;
            boolean z3 = false;
            while (i < numberOfLayers) {
                int id = layerDrawable2.getId(i);
                Drawable drawable3 = layerDrawable2.getDrawable(i);
                if ((id == 16908301 || id == 16908303) && (drawable3 instanceof NinePatchDrawable)) {
                    drawable2 = new LevelNinePathDrawable((NinePatchDrawable) drawable3);
                    z = true;
                } else {
                    z = z3;
                    drawable2 = drawable3;
                }
                drawableArr[i] = drawable2;
                i++;
                z3 = z;
            }
            layerDrawable = drawable;
            if (z3) {
                LayerDrawable layerDrawable3 = new LayerDrawable(drawableArr);
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    layerDrawable3.setId(i2, layerDrawable2.getId(i2));
                }
                layerDrawable = layerDrawable3;
            }
        }
        super.setProgressDrawable(layerDrawable);
    }
}
