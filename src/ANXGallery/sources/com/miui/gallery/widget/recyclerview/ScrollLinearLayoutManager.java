package com.miui.gallery.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.ui.SmoothScrollerController;

public class ScrollLinearLayoutManager extends LinearLayoutManager {
    protected SmoothScrollerController mSmoothScrollerController;

    public ScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public ScrollLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public ScrollLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public static void onItemClick(RecyclerView recyclerView, int i) {
        int i2;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null && layoutManager != null && (layoutManager instanceof LinearLayoutManager)) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            int findLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            int findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            if (findLastVisibleItemPosition == i || findLastCompletelyVisibleItemPosition == i) {
                i2 = i + 1;
                if (i2 <= 0 || i2 >= adapter.getItemCount()) {
                    i2 = adapter.getItemCount();
                }
            } else if (findFirstVisibleItemPosition == i || findFirstCompletelyVisibleItemPosition == i) {
                i2 = i - 1;
                if (i2 < 0 || i2 >= adapter.getItemCount()) {
                    i2 = 0;
                }
            } else {
                i2 = -1;
            }
            if (i2 != -1) {
                recyclerView.smoothScrollToPosition(i2);
            }
        }
    }

    public void setSmoothScroller(SmoothScrollerController smoothScrollerController) {
        this.mSmoothScrollerController = smoothScrollerController;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        SmoothScrollerController smoothScrollerController = this.mSmoothScrollerController;
        if (smoothScrollerController != null) {
            smoothScrollerController.setTargetPosition(i);
            startSmoothScroll(this.mSmoothScrollerController);
        }
    }
}
