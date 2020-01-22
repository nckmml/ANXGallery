package com.miui.gallery.collage.app.stitching;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.collage.app.common.AbstractStitchingFragment;
import com.miui.gallery.collage.app.common.CollageMenuFragment;
import com.miui.gallery.collage.core.CollagePresenter;
import com.miui.gallery.collage.core.stitching.StitchingModel;
import com.miui.gallery.collage.core.stitching.StitchingPresenter;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StitchingMenuFragment extends CollageMenuFragment<StitchingPresenter, AbstractStitchingFragment> {
    private boolean mDataInit = false;
    /* access modifiers changed from: private */
    public boolean mDataReady = false;
    private CollagePresenter.DataLoadListener mInitDataLoadListener = new CollagePresenter.DataLoadListener() {
        public void onDataLoad() {
            boolean unused = StitchingMenuFragment.this.mDataReady = true;
            StitchingMenuFragment.this.reloadData();
            StitchingMenuFragment.this.notifyDataInit();
        }
    };
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            StitchingMenuFragment.this.mStitchingAdapter.setSelection(i);
            StitchingMenuFragment.this.onSelectModel(i);
            return true;
        }
    };
    private SimpleRecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public StitchingAdapter mStitchingAdapter;
    private List<StitchingModel> mStitchingModelList = new ArrayList();
    private boolean mViewReady = false;

    /* access modifiers changed from: private */
    public void notifyDataInit() {
        if (this.mViewReady && this.mDataReady && !this.mDataInit) {
            onSelectModel(0);
            this.mStitchingAdapter.setOnItemClickListener(this.mOnItemClickListener);
            this.mDataInit = true;
        }
    }

    /* access modifiers changed from: private */
    public void onSelectModel(int i) {
        if (this.mStitchingModelList.size() != 0) {
            ((AbstractStitchingFragment) getRenderFragment()).onSelectModel(this.mStitchingModelList.get(i));
        }
    }

    /* access modifiers changed from: private */
    public void reloadData() {
        this.mStitchingModelList.clear();
        List<StitchingModel> stitching = ((StitchingPresenter) this.mPresenter).getStitching();
        if (stitching != null) {
            this.mStitchingModelList.addAll(stitching);
        }
        this.mStitchingAdapter.notifyDataSetChanged();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((StitchingPresenter) this.mPresenter).loadDataFromResourceAsync(this.mInitDataLoadListener);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.collage_stitching_menu, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mRecyclerView = (SimpleRecyclerView) view;
        this.mStitchingAdapter = new StitchingAdapter(getActivity(), this.mStitchingModelList, new Selectable.Selector(getResources().getDrawable(R.drawable.collage_item_background_selector)));
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity(), 0, false);
        scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(getActivity()));
        this.mRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(getResources(), R.dimen.collage_menu_item_margin, 0));
        this.mRecyclerView.setAdapter(this.mStitchingAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mViewReady = true;
        notifyDataInit();
    }
}
