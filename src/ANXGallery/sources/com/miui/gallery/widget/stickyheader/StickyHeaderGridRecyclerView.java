package com.miui.gallery.widget.stickyheader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.recyclerview.GridItemSpacingDecoration;
import com.miui.gallery.widget.recyclerview.ItemClickSupport;
import com.miui.gallery.widget.stickyheader.StickyGridLayoutManager;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderAdapter;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderLayoutManager;
import com.miui.gallery.widget.stickyheader.core.StickyHeaderRecyclerView;

public class StickyHeaderGridRecyclerView extends StickyHeaderRecyclerView implements StickyGridLayoutManager.OnLayoutChangedListener {
    protected StickyHeaderGridAdapterWrapper mAdapter;
    /* access modifiers changed from: private */
    public ItemClickSupport.OnItemClickListener mItemClickDelegate;
    /* access modifiers changed from: private */
    public ItemClickSupport.OnItemLongClickListener mItemLongClickDelegate;
    private GridItemSpacingDecoration mItemSpacingDecoration;
    private StickyGridLayoutManager mLayout;
    private StickyHeaderAdapter mStickyAdapterDelegate;

    private class RecycledViewPoolWrapper extends RecyclerView.RecycledViewPool {
        private RecycledViewPoolWrapper() {
        }
    }

    private class ViewCacheExtensionWrapper extends RecyclerView.ViewCacheExtension {
        private final RecyclerView.ViewCacheExtension mWrapped;

        private ViewCacheExtensionWrapper(RecyclerView.ViewCacheExtension viewCacheExtension) {
            this.mWrapped = viewCacheExtension;
        }

        public View getViewForPositionAndType(RecyclerView.Recycler recycler, int i, int i2) {
            return this.mWrapped.getViewForPositionAndType(recycler, StickyHeaderGridRecyclerView.this.mAdapter.decryptPosition(i), StickyHeaderGridRecyclerView.this.mAdapter.decryptViewType(i2));
        }
    }

    public StickyHeaderGridRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StickyHeaderGridRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StickyHeaderGridRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        super.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            public boolean onItemClick(RecyclerView recyclerView, View view, int i, long j, float f, float f2) {
                int i2 = i;
                int decryptPosition = StickyHeaderGridRecyclerView.this.mAdapter.decryptPosition(i);
                if (!StickyHeaderGridRecyclerView.this.mAdapter.isExtraPosition(decryptPosition) && StickyHeaderGridRecyclerView.this.mItemClickDelegate != null) {
                    return StickyHeaderGridRecyclerView.this.mItemClickDelegate.onItemClick(recyclerView, view, decryptPosition, j, f, f2);
                }
                return false;
            }
        });
        super.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            public boolean onItemLongClick(RecyclerView recyclerView, View view, int i, long j) {
                int decryptPosition = StickyHeaderGridRecyclerView.this.mAdapter.decryptPosition(i);
                if (!StickyHeaderGridRecyclerView.this.mAdapter.isExtraPosition(decryptPosition) && StickyHeaderGridRecyclerView.this.mItemLongClickDelegate != null) {
                    return StickyHeaderGridRecyclerView.this.mItemLongClickDelegate.onItemLongClick(recyclerView, view, decryptPosition, j);
                }
                return false;
            }
        });
        setRecycledViewPool(new RecycledViewPoolWrapper());
        this.mItemSpacingDecoration = new StickyHeaderSpacingDecoration(this, false);
        addItemDecoration(this.mItemSpacingDecoration);
    }

    public View findChildViewUnderForExternal(float f, float f2) {
        View findChildViewUnderForExternal = super.findChildViewUnderForExternal(f, f2);
        int childAdapterPosition = getChildAdapterPosition(findChildViewUnderForExternal);
        if (findChildViewUnderForExternal == null) {
            return findChildViewUnderForExternal;
        }
        if (childAdapterPosition != -1) {
            StickyHeaderGridAdapterWrapper stickyHeaderGridAdapterWrapper = this.mAdapter;
            if (stickyHeaderGridAdapterWrapper.isExtraPosition(stickyHeaderGridAdapterWrapper.decryptPosition(childAdapterPosition))) {
                return null;
            }
            return findChildViewUnderForExternal;
        }
        return null;
    }

    public int findFirstVisibleItemPosition() {
        StickyGridLayoutManager stickyGridLayoutManager = this.mLayout;
        if (stickyGridLayoutManager == null || this.mAdapter == null) {
            return -1;
        }
        int findFirstVisibleItemPosition = stickyGridLayoutManager.findFirstVisibleItemPosition();
        int i = -1;
        while (findFirstVisibleItemPosition != -1 && findFirstVisibleItemPosition < this.mAdapter.getItemCount()) {
            i = this.mAdapter.decryptPosition(findFirstVisibleItemPosition);
            if (!this.mAdapter.isExtraPosition(i)) {
                return i;
            }
            findFirstVisibleItemPosition++;
        }
        return i;
    }

    public int findLastVisibleItemPosition() {
        StickyGridLayoutManager stickyGridLayoutManager = this.mLayout;
        if (stickyGridLayoutManager == null || this.mAdapter == null) {
            return -1;
        }
        int findLastVisibleItemPosition = stickyGridLayoutManager.findLastVisibleItemPosition();
        int i = -1;
        while (findLastVisibleItemPosition != -1 && findLastVisibleItemPosition > -1) {
            i = this.mAdapter.decryptPosition(findLastVisibleItemPosition);
            if (!this.mAdapter.isExtraPosition(i)) {
                return i;
            }
            findLastVisibleItemPosition--;
        }
        return i;
    }

    public RecyclerView.ViewHolder findViewHolderForAdapterPositionForExternal(int i) {
        return super.findViewHolderForAdapterPositionForExternal(this.mAdapter.encryptPosition(i));
    }

    public int getChildAdapterPositionForExternal(View view) {
        int childAdapterPositionForExternal = super.getChildAdapterPositionForExternal(view);
        int decryptPosition = this.mAdapter.decryptPosition(childAdapterPositionForExternal);
        if (childAdapterPositionForExternal == -1 || this.mAdapter.isExtraPosition(decryptPosition)) {
            return -1;
        }
        return decryptPosition;
    }

    public void onSpanCountChanged(int i, int i2) {
        StickyHeaderGridAdapterWrapper stickyHeaderGridAdapterWrapper = this.mAdapter;
        if (stickyHeaderGridAdapterWrapper != null) {
            stickyHeaderGridAdapterWrapper.generateData();
        }
        Log.d("StickyHeaderGridRecyclerView", "span count changed from %d to %d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    public void onSpanSizeLookupChanged(GridLayoutManager.SpanSizeLookup spanSizeLookup, GridLayoutManager.SpanSizeLookup spanSizeLookup2) {
        StickyHeaderGridAdapterWrapper stickyHeaderGridAdapterWrapper = this.mAdapter;
        if (stickyHeaderGridAdapterWrapper != null) {
            stickyHeaderGridAdapterWrapper.generateData();
        }
        Log.d("StickyHeaderGridRecyclerView", "span size lookup changed from %d to %d", spanSizeLookup, spanSizeLookup2);
    }

    public void scrollToPosition(int i) {
        StickyHeaderGridAdapterWrapper stickyHeaderGridAdapterWrapper = this.mAdapter;
        if (stickyHeaderGridAdapterWrapper != null) {
            i = stickyHeaderGridAdapterWrapper.encryptPosition(i);
        }
        super.scrollToPosition(i);
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        StickyHeaderGridAdapterWrapper stickyHeaderGridAdapterWrapper = this.mAdapter;
        if (stickyHeaderGridAdapterWrapper != null) {
            i = stickyHeaderGridAdapterWrapper.encryptPosition(i);
        }
        this.mLayout.scrollToPositionWithOffset(i, i2);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = new StickyHeaderGridAdapterWrapper(this, adapter);
        this.mAdapter.setStickyHeaderAdapter(this.mStickyAdapterDelegate);
        if (getStickyHeaderLayoutManager() != null) {
            getStickyHeaderLayoutManager().setStickyHeaderAdapter(this.mAdapter);
        }
        this.mAdapter.setHasStableIds(adapter.hasStableIds());
        super.setStickyHeaderAdapter(this.mAdapter);
        super.setAdapter(this.mAdapter);
    }

    public void setHorizontalSpacing(int i) {
        this.mItemSpacingDecoration.setHorizontalSpacing(i);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StickyGridLayoutManager) {
            this.mLayout = (StickyGridLayoutManager) layoutManager;
            this.mLayout.setLayoutChangedListener(this);
            super.setLayoutManager(layoutManager);
            return;
        }
        throw new IllegalArgumentException("the layout manager must extends StickyGridLayoutManager");
    }

    public void setOnItemClickListener(ItemClickSupport.OnItemClickListener onItemClickListener) {
        this.mItemClickDelegate = onItemClickListener;
    }

    public void setOnItemLongClickListener(ItemClickSupport.OnItemLongClickListener onItemLongClickListener) {
        this.mItemLongClickDelegate = onItemLongClickListener;
    }

    public void setStickyHeaderAdapter(StickyHeaderAdapter stickyHeaderAdapter) {
        this.mStickyAdapterDelegate = stickyHeaderAdapter;
        StickyHeaderGridAdapterWrapper stickyHeaderGridAdapterWrapper = this.mAdapter;
        if (stickyHeaderGridAdapterWrapper != null) {
            stickyHeaderGridAdapterWrapper.setStickyHeaderAdapter(stickyHeaderAdapter);
            super.setStickyHeaderAdapter(this.mAdapter);
        }
    }

    public void setStickyHeaderLayoutManager(StickyHeaderLayoutManager stickyHeaderLayoutManager) {
        if (stickyHeaderLayoutManager != null) {
            stickyHeaderLayoutManager.setStickyHeaderAdapter(this.mAdapter);
        }
        super.setStickyHeaderLayoutManager(stickyHeaderLayoutManager);
    }

    public void setVerticalSpacing(int i) {
        this.mItemSpacingDecoration.setVerticalSpacing(i);
    }

    public void setViewCacheExtension(RecyclerView.ViewCacheExtension viewCacheExtension) {
        super.setViewCacheExtension(new ViewCacheExtensionWrapper(viewCacheExtension));
    }
}
