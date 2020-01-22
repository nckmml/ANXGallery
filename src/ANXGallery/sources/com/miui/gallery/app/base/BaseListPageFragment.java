package com.miui.gallery.app.base;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.epoxy.BaseEpoxyAdapter;
import com.miui.epoxy.EpoxyModel;
import com.miui.gallery.R;
import com.miui.gallery.adapter.SimpleEpoxyAdapter;
import com.miui.gallery.adapter.itemmodel.DefaultEmptyPageItemModel;
import com.miui.gallery.base_optimization.mvp.presenter.IPresenter;
import com.miui.gallery.widget.EmptyPage;
import java.util.List;

public abstract class BaseListPageFragment<T, P extends IPresenter> extends BaseFragment<P> {
    protected BaseEpoxyAdapter<T> mAdapter;
    protected EpoxyModel mCurrentEmptyPageModel;
    protected EmptyPage.EmptyConfig mEmptyConfig;
    protected View mLoadingView;
    protected RecyclerView mRecyleView;

    private void initSelf(boolean z) {
        this.mRecyleView.setAdapter(this.mAdapter);
        if (z) {
            addItemDecoration(getRecycleViewDecoration());
        }
    }

    public void addDatas(List<T> list) {
        if (list != null) {
            initSelf(false);
            if (list.isEmpty()) {
                this.mAdapter.checkEmptyView();
                return;
            }
            goneProgress();
            this.mAdapter.addDataList(list);
        }
    }

    /* access modifiers changed from: protected */
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        RecyclerView recyclerView = this.mRecyleView;
        if (recyclerView != null && itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);
        }
    }

    /* access modifiers changed from: protected */
    public RecyclerView findRecycleView() {
        return (RecyclerView) findViewById(R.id.recyclerViewList);
    }

    /* access modifiers changed from: protected */
    public EmptyPage.EmptyConfig getDefaultEmptyConfig() {
        return this.mEmptyConfig;
    }

    public int getLayoutId() {
        return R.layout.sample_list_page;
    }

    /* access modifiers changed from: protected */
    public RecyclerView.ItemDecoration getRecycleViewDecoration() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void goneProgress() {
        View view = this.mLoadingView;
        if (view != null && view.getVisibility() == 0) {
            this.mLoadingView.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void initRecycleView(RecyclerView recyclerView);

    public void initView(View view, Bundle bundle, View view2) {
        this.mRecyleView = findRecycleView();
        if (this.mRecyleView != null) {
            this.mAdapter = new SimpleEpoxyAdapter();
            this.mEmptyConfig = new EmptyPage.EmptyConfig().setBackground(R.drawable.window_background).setIcon(R.drawable.empty_page_common).disableActionButton();
            initRecycleView(this.mRecyleView);
            RecyclerView.LayoutManager layoutManager = this.mRecyleView.getLayoutManager();
            if (layoutManager != null && GridLayoutManager.class.isInstance(layoutManager)) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(this.mAdapter.getSpanSizeLookup(gridLayoutManager.getSpanCount()));
                return;
            }
            return;
        }
        throw new IllegalStateException("can't find recycleView from layout");
    }

    /* access modifiers changed from: protected */
    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.mRecyleView.setPadding(i, i2, i3, i4);
    }

    public void setDatas(List<T> list) {
        initSelf(true);
        if (list == null || list.isEmpty()) {
            this.mAdapter.checkEmptyView();
            return;
        }
        this.mAdapter.clearData();
        addDatas(list);
    }

    public void setEmptyPage(EmptyPage.EmptyConfig emptyConfig) {
        if (emptyConfig != null) {
            this.mCurrentEmptyPageModel = new DefaultEmptyPageItemModel(emptyConfig);
            this.mAdapter.setEmptyViewModel(this.mCurrentEmptyPageModel);
        }
    }

    public void updateDatas(List<T> list) {
        if (this.mAdapter != null && list != null && !list.isEmpty()) {
            this.mAdapter.updateDataList(list);
        }
    }
}
