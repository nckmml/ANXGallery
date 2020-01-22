package com.miui.gallery.listener;

import android.view.View;

public abstract class SingleClickListener implements View.OnClickListener {
    private int mId = -1;
    private long mLastClickTime = 0;

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        int id = view.getId();
        if (this.mId != id) {
            this.mId = id;
            this.mLastClickTime = currentTimeMillis;
            onSingleClick(view);
        } else if (currentTimeMillis - this.mLastClickTime > 1000) {
            this.mLastClickTime = currentTimeMillis;
            onSingleClick(view);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void onSingleClick(View view);
}
