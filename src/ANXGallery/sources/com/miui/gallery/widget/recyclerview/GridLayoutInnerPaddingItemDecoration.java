package com.miui.gallery.widget.recyclerview;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridLayoutInnerPaddingItemDecoration extends RecyclerView.ItemDecoration {
    private final int[] mEdgeMargins;
    private boolean mFullSpanIgnorePadding = false;
    private final int mItemHorizontalPadding;
    private final int mItemVerticalPadding;
    private final GridLayoutManager mLayoutManager;
    private Rect mRecycleRect;

    public GridLayoutInnerPaddingItemDecoration(int i, int i2, int i3, int i4, int i5, int i6, boolean z, GridLayoutManager gridLayoutManager) {
        this.mEdgeMargins = new int[]{i, i2, i3, i4};
        this.mItemHorizontalPadding = i5;
        this.mItemVerticalPadding = i6;
        this.mRecycleRect = new Rect();
        this.mFullSpanIgnorePadding = z;
        this.mLayoutManager = gridLayoutManager;
    }

    private boolean isLastLine(int i, int i2, int i3, int i4, int i5, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        if (i < i5 - i4) {
            return false;
        }
        int i6 = i5 - 1;
        if (i == i6) {
            return true;
        }
        int i7 = i2 + i3;
        for (int i8 = i + 1; i8 <= i6; i8++) {
            i7 += spanSizeLookup == null ? 1 : spanSizeLookup.getSpanSize(i8);
            if (i7 > i4) {
                return false;
            }
        }
        return true;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int spanCount = this.mLayoutManager.getSpanCount();
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanSize = layoutParams.getSpanSize();
        int spanIndex = layoutParams.getSpanIndex();
        int i = 0;
        boolean z = spanSize == spanCount && this.mFullSpanIgnorePadding;
        this.mRecycleRect.left = (!z && spanIndex == 0) ? this.mEdgeMargins[0] : 0;
        this.mRecycleRect.right = z ? 0 : spanIndex + spanSize < spanCount ? this.mItemHorizontalPadding : this.mEdgeMargins[2];
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        this.mRecycleRect.top = (!z && childAdapterPosition < spanCount && spanIndex == childAdapterPosition) ? this.mEdgeMargins[1] : 0;
        boolean isLastLine = isLastLine(childAdapterPosition, spanSize, spanIndex, spanCount, recyclerView.getAdapter().getItemCount(), this.mLayoutManager.getSpanSizeLookup());
        Rect rect2 = this.mRecycleRect;
        if (!z) {
            i = isLastLine ? this.mEdgeMargins[3] : this.mItemVerticalPadding;
        }
        rect2.bottom = i;
        rect.set(this.mRecycleRect);
    }
}
