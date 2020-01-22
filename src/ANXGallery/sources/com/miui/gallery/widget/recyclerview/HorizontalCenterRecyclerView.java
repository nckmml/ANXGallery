package com.miui.gallery.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

public class HorizontalCenterRecyclerView extends SimpleRecyclerView {
    private int mLastWidth;

    public HorizontalCenterRecyclerView(Context context) {
        super(context);
    }

    public HorizontalCenterRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HorizontalCenterRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        super.onLayout(z, i, i2, i3, i4);
        if (getChildCount() > 0) {
            int i6 = 0;
            for (int i7 = 0; i7 < getChildCount(); i7++) {
                i6 += getChildAt(i7).getMeasuredWidth();
            }
            if (this.mLastWidth != i6) {
                this.mLastWidth = i6;
                int measuredWidth = getMeasuredWidth() - i6;
                if (measuredWidth > 0 && getPaddingLeft() != (i5 = measuredWidth / 2)) {
                    setPadding(i5, 0, i5, 0);
                    super.onLayout(z, i, i2, i3, i4);
                }
            }
        }
    }
}
