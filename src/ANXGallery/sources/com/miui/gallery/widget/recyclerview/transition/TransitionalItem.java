package com.miui.gallery.widget.recyclerview.transition;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface TransitionalItem {
    Drawable getTransitDrawable();

    RectF getTransitFrame();

    long getTransitId();

    String getTransitPath();

    ImageView.ScaleType getTransitScaleType();
}
