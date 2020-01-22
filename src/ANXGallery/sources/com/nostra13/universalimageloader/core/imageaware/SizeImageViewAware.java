package com.nostra13.universalimageloader.core.imageaware;

import android.widget.ImageView;

public class SizeImageViewAware extends ImageViewAware {
    private int mHeight;
    private int mWidth;

    public SizeImageViewAware(ImageView imageView, int i, int i2) {
        super(imageView);
        this.mWidth = i;
        this.mHeight = i2;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }
}
