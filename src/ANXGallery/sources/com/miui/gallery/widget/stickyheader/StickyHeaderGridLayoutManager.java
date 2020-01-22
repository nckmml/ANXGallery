package com.miui.gallery.widget.stickyheader;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.stickyheader.core.HeaderViewHolder;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderRecycler;

public class StickyHeaderGridLayoutManager<VH extends HeaderViewHolder> extends StickyHeaderLinearLayoutManager<VH> {
    public StickyHeaderGridLayoutManager(RecyclerView recyclerView) {
        super(recyclerView);
    }

    private void assertLayoutManager() {
        if (!(this.mRecyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            throw new IllegalArgumentException("the layoutManager of RecyclerView should be GridLayoutManager");
        }
    }

    private int getFirstItemPositionOfSpan(RecyclerView.ViewHolder viewHolder) {
        if (((GridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams()).getSpanIndex() == 0) {
            return viewHolder.getAdapterPosition();
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) this.mRecyclerView.getLayoutManager();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
        int spanCount = gridLayoutManager.getSpanCount();
        int adapterPosition = viewHolder.getAdapterPosition();
        while (true) {
            adapterPosition--;
            if (adapterPosition <= -1) {
                return -1;
            }
            if (spanSizeLookup.getSpanIndex(adapterPosition, spanCount) == 0) {
                return adapterPosition;
            }
        }
    }

    public boolean hasStickyHeader(StickyHeaderRecycler stickyHeaderRecycler, RecyclerView.ViewHolder viewHolder) {
        assertLayoutManager();
        return ((GridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams()).getSpanIndex() == 0 && super.hasStickyHeader(stickyHeaderRecycler, viewHolder);
    }

    public boolean needOffsetForHeader(RecyclerView.ViewHolder viewHolder) {
        if (!isReady()) {
            return false;
        }
        if (viewHolder == null || viewHolder.getAdapterPosition() == -1) {
            Log.w("StickyHeaderGridLayoutManager", "the holder is invalid");
            return false;
        }
        assertLayoutManager();
        int firstItemPositionOfSpan = getFirstItemPositionOfSpan(viewHolder);
        if (firstItemPositionOfSpan != -1) {
            return hasDifferentHeader(firstItemPositionOfSpan, firstItemPositionOfSpan - 1);
        }
        Log.w("StickyHeaderGridLayoutManager", "invalid position");
        return false;
    }
}
