package com.miui.gallery.widget.stickyheader;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;

public class StickyGridLayoutManager extends GridLayoutManager {
    private OnLayoutChangedListener mLayoutChangedListener;

    interface OnLayoutChangedListener {
        void onSpanCountChanged(int i, int i2);

        void onSpanSizeLookupChanged(GridLayoutManager.SpanSizeLookup spanSizeLookup, GridLayoutManager.SpanSizeLookup spanSizeLookup2);
    }

    public StickyGridLayoutManager(Context context, int i) {
        super(context, i);
    }

    public StickyGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setLayoutChangedListener(OnLayoutChangedListener onLayoutChangedListener) {
        this.mLayoutChangedListener = onLayoutChangedListener;
    }

    public void setSpanCount(int i) {
        int spanCount = getSpanCount();
        super.setSpanCount(i);
        OnLayoutChangedListener onLayoutChangedListener = this.mLayoutChangedListener;
        if (onLayoutChangedListener != null && spanCount != i) {
            onLayoutChangedListener.onSpanCountChanged(spanCount, i);
        }
    }

    public void setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        GridLayoutManager.SpanSizeLookup spanSizeLookup2 = getSpanSizeLookup();
        super.setSpanSizeLookup(spanSizeLookup);
        OnLayoutChangedListener onLayoutChangedListener = this.mLayoutChangedListener;
        if (onLayoutChangedListener != null && spanSizeLookup2 != spanSizeLookup) {
            onLayoutChangedListener.onSpanSizeLookupChanged(spanSizeLookup2, spanSizeLookup);
        }
    }
}
