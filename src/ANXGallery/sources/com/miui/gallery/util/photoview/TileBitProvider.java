package com.miui.gallery.util.photoview;

import android.graphics.Rect;

public interface TileBitProvider {
    int getImageHeight();

    int getImageWidth();

    int getParallelism();

    int getRotation();

    TileBit getTileBit(Rect rect, int i);

    boolean isFlip();

    void release();
}
