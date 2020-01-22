package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.PointF;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;

public abstract class SmoothScrollerController extends LinearSmoothScroller {
    public SmoothScrollerController(Context context) {
        super(context);
    }

    public PointF computeScrollVectorForPosition(int i) {
        return getLayoutManager() instanceof LinearLayoutManager ? ((LinearLayoutManager) getLayoutManager()).computeScrollVectorForPosition(i) : new PointF();
    }
}
