package com.miui.gallery.widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.util.photoview.ScrollableView;
import com.miui.gallery.widget.recyclerview.ItemClickSupport;
import java.util.LinkedList;
import java.util.List;

public class GalleryRecyclerView extends RecyclerView implements ScrollableView, ItemClickSupport.OnItemLongClickListener {
    private boolean isHideViewCalled;
    private ContextMenu.ContextMenuInfo mContextMenuInfo;
    protected FastScroller mCustomFastScroller;
    private View mEmptyView;
    private boolean mEnableCustomFastScroller;
    private int mFastScrollStyle;
    protected ItemClickSupport mItemClickSupport;
    private final RecyclerViewDataObserver mObserver;
    private ItemClickSupport.OnItemLongClickListener mOnItemLongClickListener;
    private List<RecyclerView.OnItemTouchListener> mOnItemTouchListeners;

    public static class RecyclerContextMenuInfo implements ContextMenu.ContextMenuInfo {
        public int position;

        public RecyclerContextMenuInfo(int i) {
            setValues(i);
        }

        public void setValues(int i) {
            this.position = i;
        }
    }

    private class RecyclerViewDataObserver extends RecyclerView.AdapterDataObserver {
        private RecyclerViewDataObserver() {
        }

        public void onChanged() {
            super.onChanged();
            GalleryRecyclerView.this.updateEmptyStatus();
        }

        public void onItemRangeChanged(int i, int i2) {
            super.onItemRangeChanged(i, i2);
            GalleryRecyclerView.this.updateEmptyStatus();
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            super.onItemRangeChanged(i, i2, obj);
            GalleryRecyclerView.this.updateEmptyStatus();
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            GalleryRecyclerView.this.updateEmptyStatus();
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            super.onItemRangeMoved(i, i2, i3);
            GalleryRecyclerView.this.updateEmptyStatus();
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            GalleryRecyclerView.this.updateEmptyStatus();
        }
    }

    public GalleryRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GalleryRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GalleryRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContextMenuInfo = null;
        this.mOnItemTouchListeners = new LinkedList();
        this.isHideViewCalled = false;
        this.mObserver = new RecyclerViewDataObserver();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i, 0);
            this.mEnableCustomFastScroller = obtainStyledAttributes.getBoolean(2, false);
            this.mFastScrollStyle = obtainStyledAttributes.getResourceId(3, 0);
            obtainStyledAttributes.recycle();
        }
        initItemClick();
    }

    private void bringFastScrollerToFront() {
        removeItemDecoration(this.mCustomFastScroller);
        super.addItemDecoration(this.mCustomFastScroller, -1);
    }

    private boolean openContextMenu(int i) {
        if (i >= 0) {
            createContextMenuInfo(i);
        }
        return showContextMenu();
    }

    private void setAdapterInternal(RecyclerView.Adapter adapter, boolean z, boolean z2) {
        RecyclerView.Adapter adapter2 = getAdapter();
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.mObserver);
        }
        adapter.registerAdapterDataObserver(this.mObserver);
        if (z) {
            super.swapAdapter(adapter, z2);
        } else {
            super.setAdapter(adapter);
        }
        updateEmptyStatus();
    }

    /* access modifiers changed from: private */
    public void updateEmptyStatus() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter == null || adapter.getItemCount() == 0) {
            View view = this.mEmptyView;
            if (view != null) {
                view.setVisibility(0);
                super.setVisibility(8);
            } else if (!this.isHideViewCalled) {
                super.setVisibility(0);
            }
        } else {
            View view2 = this.mEmptyView;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            if (!this.isHideViewCalled) {
                super.setVisibility(0);
            }
        }
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration, int i) {
        super.addItemDecoration(itemDecoration, i);
        if (this.mCustomFastScroller != null) {
            bringFastScrollerToFront();
        }
    }

    public void addOnItemTouchListener(RecyclerView.OnItemTouchListener onItemTouchListener) {
        super.addOnItemTouchListener(onItemTouchListener);
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }

    /* access modifiers changed from: protected */
    public void createContextMenuInfo(int i) {
        ContextMenu.ContextMenuInfo contextMenuInfo = this.mContextMenuInfo;
        if (contextMenuInfo == null) {
            this.mContextMenuInfo = new RecyclerContextMenuInfo(i);
        } else {
            ((RecyclerContextMenuInfo) contextMenuInfo).setValues(i);
        }
    }

    public View findChildViewUnderForExternal(float f, float f2) {
        return findChildViewUnder(f, f2);
    }

    public int findFirstVisibleItemPosition() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || layoutManager.getChildCount() <= 0) {
            return -1;
        }
        return getChildAdapterPosition(layoutManager.getChildAt(0));
    }

    public int findLastVisibleItemPosition() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || layoutManager.getChildCount() <= 0) {
            return -1;
        }
        return getChildAdapterPosition(layoutManager.getChildAt(layoutManager.getChildCount() - 1));
    }

    public RecyclerView.ViewHolder findViewHolderForAdapterPositionForExternal(int i) {
        return findViewHolderForAdapterPosition(i);
    }

    public int getChildAdapterPositionForExternal(View view) {
        return getChildAdapterPosition(view);
    }

    /* access modifiers changed from: protected */
    public ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return this.mContextMenuInfo;
    }

    /* access modifiers changed from: package-private */
    public RecyclerView.OnItemTouchListener getItemTouchListenerAt(int i) {
        int size = this.mOnItemTouchListeners.size();
        if (i > -1 && i < size) {
            return this.mOnItemTouchListeners.get(i);
        }
        throw new IndexOutOfBoundsException(i + " is an invalid index for size " + size);
    }

    /* access modifiers changed from: package-private */
    public int getItemTouchListenerCount() {
        return this.mOnItemTouchListeners.size();
    }

    /* access modifiers changed from: protected */
    public void initItemClick() {
        this.mItemClickSupport = ItemClickSupport.addTo(this);
        this.mItemClickSupport.setOnItemLongClickListener(this);
    }

    public boolean onItemLongClick(RecyclerView recyclerView, View view, int i, long j) {
        ItemClickSupport.OnItemLongClickListener onItemLongClickListener = this.mOnItemLongClickListener;
        return (onItemLongClickListener != null ? onItemLongClickListener.onItemLongClick(recyclerView, view, i, j) : false) || openContextMenu(i);
    }

    public void removeOnItemTouchListener(RecyclerView.OnItemTouchListener onItemTouchListener) {
        super.removeOnItemTouchListener(onItemTouchListener);
        this.mOnItemTouchListeners.remove(onItemTouchListener);
    }

    public void scrollToPosition(int i) {
        super.scrollToPosition(i);
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !(layoutManager instanceof LinearLayoutManager)) {
            scrollToPosition(i);
        } else {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, i2);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        setAdapterInternal(adapter, false, true);
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
        if (view != null && view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
        updateEmptyStatus();
    }

    public void setFastScrollEnabled(boolean z) {
        if (this.mEnableCustomFastScroller != z) {
            this.mEnableCustomFastScroller = z;
            if (z) {
                if (this.mCustomFastScroller == null) {
                    this.mCustomFastScroller = new FastScroller(this);
                    this.mCustomFastScroller.setStyle(this.mFastScrollStyle);
                }
                this.mCustomFastScroller.attach();
                return;
            }
            FastScroller fastScroller = this.mCustomFastScroller;
            if (fastScroller != null) {
                fastScroller.detach();
            }
        }
    }

    public void setFastScrollStyle(int i) {
        FastScroller fastScroller = this.mCustomFastScroller;
        if (fastScroller == null) {
            this.mFastScrollStyle = i;
        } else {
            fastScroller.setStyle(i);
        }
    }

    public void setOnItemClickListener(ItemClickSupport.OnItemClickListener onItemClickListener) {
        this.mItemClickSupport.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(ItemClickSupport.OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setVisibility(int i) {
        this.isHideViewCalled = i != 0;
        super.setVisibility(i);
    }

    public void swapAdapter(RecyclerView.Adapter adapter, boolean z) {
        setAdapterInternal(adapter, true, z);
    }

    public void viewToPosition(int i) {
        scrollToPositionWithOffset(i, getHeight() / 2);
    }
}
