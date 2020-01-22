package com.miui.gallery.widget;

import android.view.View;

public abstract class AntiDoubleClickListener implements View.OnClickListener {
    private long mLastClickTimeMillis;
    private int mMinClickInterval;

    public AntiDoubleClickListener() {
        this(600);
    }

    public AntiDoubleClickListener(int i) {
        this.mMinClickInterval = i;
    }

    public abstract void onAntiDoubleClick(View view);

    public void onClick(View view) {
        if (System.currentTimeMillis() - this.mLastClickTimeMillis >= ((long) this.mMinClickInterval)) {
            this.mLastClickTimeMillis = System.currentTimeMillis();
            onAntiDoubleClick(view);
        }
    }
}
