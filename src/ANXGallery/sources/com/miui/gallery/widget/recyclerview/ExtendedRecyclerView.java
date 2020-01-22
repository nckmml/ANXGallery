package com.miui.gallery.widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.util.Numbers;
import com.miui.gallery.widget.TwoStageDrawer;

public class ExtendedRecyclerView extends RelativeLayout implements TwoStageDrawer.ScrollableView {
    protected boolean mClipToPadding;
    protected ViewStub mEmpty;
    protected int mEmptyId;
    protected View mEmptyView;
    protected int mMainContentId;
    protected boolean mManualInflateEmptyView;
    private final RecyclerViewDataObserver mObserver = new RecyclerViewDataObserver();
    protected int mPadding;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;
    protected ViewStub mProgress;
    protected int mProgressId;
    protected View mProgressView;
    protected RecyclerView mRecycler;
    protected int mScrollbarStyle;

    private class RecyclerViewDataObserver extends RecyclerView.AdapterDataObserver {
        private RecyclerViewDataObserver() {
        }

        private void update() {
            if (ExtendedRecyclerView.this.mRecycler.getAdapter() == null || ExtendedRecyclerView.this.mRecycler.getAdapter().getItemCount() <= 0) {
                ExtendedRecyclerView.this.showEmpty();
            } else {
                ExtendedRecyclerView.this.showRecycler();
            }
        }

        public void onChanged() {
            super.onChanged();
            update();
        }

        public void onItemRangeChanged(int i, int i2) {
            super.onItemRangeChanged(i, i2);
            update();
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            update();
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            super.onItemRangeMoved(i, i2, i3);
            update();
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            update();
        }
    }

    public ExtendedRecyclerView(Context context) {
        super(context);
        initView();
    }

    public ExtendedRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttrs(attributeSet);
        initView();
    }

    public ExtendedRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(attributeSet);
        initView();
    }

    private void initView() {
        if (!isInEditMode()) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.mMainContentId, this);
            this.mProgress = (ViewStub) inflate.findViewById(R.id.progress);
            ViewStub viewStub = this.mProgress;
            if (viewStub != null) {
                viewStub.setLayoutResource(this.mProgressId);
                this.mProgressView = this.mProgress.inflate();
                this.mEmpty = (ViewStub) inflate.findViewById(R.id.empty);
                ViewStub viewStub2 = this.mEmpty;
                if (viewStub2 != null) {
                    int i = this.mEmptyId;
                    if (i != 0 && !this.mManualInflateEmptyView) {
                        viewStub2.setLayoutResource(i);
                        this.mEmptyView = this.mEmpty.inflate();
                    }
                    initRecyclerView(inflate);
                    return;
                }
                throw new RuntimeException("layout_main_content must contains empty stub!");
            }
            throw new RuntimeException("layout_main_content must contains progress stub!");
        }
    }

    private void setAdapterInternal(RecyclerView.Adapter adapter, boolean z, boolean z2) {
        RecyclerView.Adapter adapter2 = this.mRecycler.getAdapter();
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.mObserver);
        }
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mObserver);
        }
        if (z) {
            this.mRecycler.swapAdapter(adapter, z2);
        } else {
            this.mRecycler.setAdapter(adapter);
        }
        if (adapter == null || adapter.getItemCount() <= 0) {
            showEmpty();
        } else {
            showRecycler();
        }
    }

    private void setEmptyVisibility(int i) {
        if (this.mEmptyView != null) {
            this.mEmpty.setVisibility(i);
        }
    }

    /* access modifiers changed from: private */
    public void showEmpty() {
        this.mRecycler.setVisibility(8);
        setEmptyVisibility(0);
        this.mProgress.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showRecycler() {
        this.mRecycler.setVisibility(0);
        setEmptyVisibility(8);
        this.mProgress.setVisibility(8);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.mRecycler.addItemDecoration(itemDecoration);
    }

    public boolean canScrollDown() {
        return this.mRecycler.canScrollVertically(-1);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return this.mRecycler.getLayoutManager();
    }

    public RecyclerView getRecycler() {
        return this.mRecycler;
    }

    public void inflateEmptyView() {
        int i;
        if (this.mEmptyView == null && (i = this.mEmptyId) != 0) {
            this.mEmpty.setLayoutResource(i);
            this.mEmptyView = this.mEmpty.inflate();
        }
    }

    /* access modifiers changed from: protected */
    public void initAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ExtendedRecyclerView);
        try {
            this.mClipToPadding = obtainStyledAttributes.getBoolean(4, false);
            this.mManualInflateEmptyView = obtainStyledAttributes.getBoolean(3, false);
            this.mPadding = (int) obtainStyledAttributes.getDimension(5, -1.0f);
            this.mPaddingTop = (int) obtainStyledAttributes.getDimension(9, 0.0f);
            this.mPaddingBottom = (int) obtainStyledAttributes.getDimension(6, 0.0f);
            this.mPaddingLeft = (int) obtainStyledAttributes.getDimension(7, 0.0f);
            this.mPaddingRight = (int) obtainStyledAttributes.getDimension(8, 0.0f);
            this.mScrollbarStyle = obtainStyledAttributes.getInt(10, -1);
            this.mEmptyId = obtainStyledAttributes.getResourceId(0, 0);
            this.mProgressId = obtainStyledAttributes.getResourceId(2, R.layout.layout_progress);
            this.mMainContentId = obtainStyledAttributes.getResourceId(1, R.layout.extended_recyclerview);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void initRecyclerView(View view) {
        View findViewById = view.findViewById(R.id.recycler);
        if (findViewById == null || !(findViewById instanceof RecyclerView)) {
            throw new IllegalArgumentException("ExtendedRecyclerView only works with a RecyclerView!");
        }
        this.mRecycler = (RecyclerView) findViewById;
        this.mRecycler.setClipToPadding(this.mClipToPadding);
        if (!Numbers.equals(Float.valueOf((float) this.mPadding), Float.valueOf(-1.0f))) {
            RecyclerView recyclerView = this.mRecycler;
            int i = this.mPadding;
            recyclerView.setPadding(i, i, i, i);
        } else {
            this.mRecycler.setPadding(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
        }
        int i2 = this.mScrollbarStyle;
        if (i2 == -1) {
            return;
        }
        if (i2 == 16777216 || i2 == 0 || i2 == 50331648 || i2 == 33554432) {
            this.mRecycler.setScrollBarStyle(this.mScrollbarStyle);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        setAdapterInternal(adapter, false, true);
    }

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.mRecycler.setItemAnimator(itemAnimator);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mRecycler.setLayoutManager(layoutManager);
    }

    public void smoothScrollToPosition(int i) {
        this.mRecycler.smoothScrollToPosition(i);
    }
}
