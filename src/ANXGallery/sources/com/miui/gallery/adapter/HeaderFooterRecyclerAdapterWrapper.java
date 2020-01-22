package com.miui.gallery.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public abstract class HeaderFooterRecyclerAdapterWrapper<ADAPTER extends RecyclerView.Adapter<VH>, VH extends RecyclerView.ViewHolder> extends RecyclerAdapterWrapper<ADAPTER, VH> {
    private ArrayList<View> mFooterViews;
    private ArrayList<View> mHeaderViews;
    private RecyclerView.LayoutManager mLayoutManager;

    private static class HeaderFooterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        private HeaderFooterRecyclerAdapterWrapper mAdapterWrapper;
        private int mSpanCount;
        private GridLayoutManager.SpanSizeLookup mWrapped;

        public HeaderFooterSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup, HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper, int i) {
            this.mWrapped = spanSizeLookup;
            this.mAdapterWrapper = headerFooterRecyclerAdapterWrapper;
            this.mSpanCount = i;
        }

        public int getSpanSize(int i) {
            return (this.mAdapterWrapper.isHeaderPosition(i) || this.mAdapterWrapper.isFooterPosition(i)) ? this.mSpanCount : this.mWrapped.getSpanSize(i);
        }
    }

    public HeaderFooterRecyclerAdapterWrapper(ADAPTER adapter) {
        this(adapter, (List<View>) null, (List<View>) null);
    }

    public HeaderFooterRecyclerAdapterWrapper(ADAPTER adapter, List<View> list, List<View> list2) {
        super(adapter);
        this.mHeaderViews = new ArrayList<>();
        this.mFooterViews = new ArrayList<>();
        if (list != null) {
            this.mHeaderViews.addAll(list);
        }
        if (list2 != null) {
            this.mFooterViews.addAll(list2);
        }
    }

    private int mapPosition2ViewType(int i) {
        return i - 2147483648;
    }

    private int mapViewType2Position(int i) {
        return i - Integer.MIN_VALUE;
    }

    private static void removeFromParentFor(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(view);
            if (view.getParent() != null) {
                viewGroup.endViewTransition(view);
            }
        }
    }

    public void addFooterView(View view) {
        if (view != null) {
            int footersCount = getFootersCount();
            this.mFooterViews.add(view);
            notifyItemInserted(getHeadersCount() + getWrappedAdapterItemCount() + footersCount);
        }
    }

    public void addHeaderView(View view) {
        if (view != null) {
            int headersCount = getHeadersCount();
            this.mHeaderViews.add(view);
            notifyItemInserted(headersCount);
        }
    }

    public int getFootersCount() {
        return this.mFooterViews.size();
    }

    public int getHeadersCount() {
        return this.mHeaderViews.size();
    }

    public int getItemCount() {
        return getWrappedAdapterItemCount() + getHeadersCount() + getFootersCount();
    }

    public long getItemId(int i) {
        if (!isHeaderPosition(i) && !isFooterPosition(i)) {
            return super.getItemId(getWrappedAdapterPosition(i));
        }
        return -1;
    }

    public int getItemViewType(int i) {
        return isHeaderPosition(i) ? mapPosition2ViewType(i) : isFooterPosition(i) ? mapPosition2ViewType(i) : super.getItemViewType(getWrappedAdapterPosition(i));
    }

    public int getWrappedAdapterPosition(int i) {
        if (i < 0 || i >= getItemCount() || isHeaderPosition(i) || isFooterPosition(i)) {
            return -1;
        }
        return i - getHeadersCount();
    }

    public boolean isFooterPosition(int i) {
        return i >= getItemCount() - getFootersCount() && i < getItemCount();
    }

    public boolean isFooterViewType(int i) {
        return isFooterPosition(mapViewType2Position(i));
    }

    public boolean isHeaderPosition(int i) {
        return i >= 0 && i < getHeadersCount();
    }

    public boolean isHeaderViewType(int i) {
        return isHeaderPosition(mapViewType2Position(i));
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mLayoutManager = recyclerView.getLayoutManager();
        RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new HeaderFooterSpanSizeLookup(gridLayoutManager.getSpanSizeLookup(), this, gridLayoutManager.getSpanCount()));
        }
    }

    public void onBindViewHolder(VH vh, int i) {
        if (!isHeaderPosition(i) && !isFooterPosition(i)) {
            super.onBindViewHolder(vh, getWrappedAdapterPosition(i));
        }
    }

    /* access modifiers changed from: protected */
    public abstract VH onCreateHeaderFooterViewHolder(View view);

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = isHeaderViewType(i) ? this.mHeaderViews.get(mapViewType2Position(i)) : isFooterViewType(i) ? this.mFooterViews.get((mapViewType2Position(i) - getWrappedAdapterItemCount()) - getHeadersCount()) : null;
        if (view == null) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        removeFromParentFor(view);
        if (this.mLayoutManager instanceof StaggeredGridLayoutManager) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            StaggeredGridLayoutManager.LayoutParams layoutParams2 = layoutParams != null ? new StaggeredGridLayoutManager.LayoutParams(layoutParams.width, layoutParams.height) : new StaggeredGridLayoutManager.LayoutParams(-1, -2);
            layoutParams2.setFullSpan(true);
            view.setLayoutParams(layoutParams2);
        }
        return onCreateHeaderFooterViewHolder(view);
    }

    /* access modifiers changed from: protected */
    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2) {
        notifyItemRangeChanged(i + getHeadersCount(), i2);
    }

    /* access modifiers changed from: protected */
    public void onHandleWrappedAdapterItemRangeInserted(int i, int i2) {
        notifyItemRangeInserted(i + getHeadersCount(), i2);
    }

    /* access modifiers changed from: protected */
    public void onHandleWrappedAdapterItemRangeMoved(int i, int i2, int i3) {
        notifyItemMoved(i + getHeadersCount(), i2 + getHeadersCount());
    }

    /* access modifiers changed from: protected */
    public void onHandleWrappedAdapterItemRangeRemoved(int i, int i2) {
        notifyItemRangeRemoved(i + getHeadersCount(), i2);
    }

    public boolean removeFooterView(View view) {
        int indexOf;
        if (view == null || (indexOf = this.mFooterViews.indexOf(view)) == -1) {
            return false;
        }
        removeFromParentFor(view);
        this.mFooterViews.remove(indexOf);
        notifyItemRemoved(getHeadersCount() + getWrappedAdapterItemCount() + indexOf);
        return true;
    }

    public boolean removeHeaderView(View view) {
        int indexOf;
        if (view == null || (indexOf = this.mHeaderViews.indexOf(view)) == -1) {
            return false;
        }
        removeFromParentFor(view);
        this.mHeaderViews.remove(indexOf);
        notifyItemRemoved(indexOf);
        return true;
    }
}
