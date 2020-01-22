package com.miui.gallery.collage.app.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.collage.app.common.AbstractLayoutFragment;
import com.miui.gallery.collage.app.common.CollageMenuFragment;
import com.miui.gallery.collage.core.CollagePresenter;
import com.miui.gallery.collage.core.layout.LayoutModel;
import com.miui.gallery.collage.core.layout.LayoutPresenter;
import com.miui.gallery.collage.render.CollageMargin;
import com.miui.gallery.collage.render.CollageRatio;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LayoutMenuFragment extends CollageMenuFragment<LayoutPresenter, AbstractLayoutFragment> {
    /* access modifiers changed from: private */
    public CollageMargin mCollageMargin = CollageMargin.NONE;
    /* access modifiers changed from: private */
    public CollageRatio mCollageRatio = CollageRatio.RATIO_3_4;
    private boolean mDataInit = false;
    private CollagePresenter.DataLoadListener mDataLoadListener = new CollagePresenter.DataLoadListener() {
        public void onDataLoad() {
            boolean unused = LayoutMenuFragment.this.mDataReady = true;
            LayoutMenuFragment.this.reloadData();
            LayoutMenuFragment.this.notifyDataInit();
        }
    };
    /* access modifiers changed from: private */
    public boolean mDataReady = false;
    /* access modifiers changed from: private */
    public LayoutMenuAdapter mLayoutMenuAdapter;
    private List<LayoutModel> mLayoutModels = new ArrayList();
    private View.OnClickListener mMarginClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            CollageMargin[] values = CollageMargin.values();
            CollageMargin collageMargin = values[(LayoutMenuFragment.this.mCollageMargin.ordinal() + 1) % values.length];
            LayoutMenuFragment.this.mMarginImageView.setImageResource(collageMargin.iconRes);
            ((AbstractLayoutFragment) LayoutMenuFragment.this.getRenderFragment()).onSelectMargin(collageMargin.marginSize);
            CollageMargin unused = LayoutMenuFragment.this.mCollageMargin = collageMargin;
        }
    };
    /* access modifiers changed from: private */
    public ImageView mMarginImageView;
    private View mMarginWrapper;
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            LayoutMenuFragment.this.mLayoutMenuAdapter.setSelection(i);
            LayoutMenuFragment.this.onSelectModel(i);
            return true;
        }
    };
    private View.OnClickListener mRatioClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            CollageRatio[] values = CollageRatio.values();
            CollageRatio collageRatio = values[(LayoutMenuFragment.this.mCollageRatio.ordinal() + 1) % values.length];
            LayoutMenuFragment.this.mRatioImageView.setImageResource(collageRatio.iconRes);
            ((AbstractLayoutFragment) LayoutMenuFragment.this.getRenderFragment()).onSelectRatio(collageRatio.ratio);
            CollageRatio unused = LayoutMenuFragment.this.mCollageRatio = collageRatio;
        }
    };
    /* access modifiers changed from: private */
    public ImageView mRatioImageView;
    private View mRatioWrapper;
    private SimpleRecyclerView mRecyclerView;
    private boolean mViewReady = false;

    /* access modifiers changed from: private */
    public void notifyDataInit() {
        if (this.mViewReady && this.mDataReady && !this.mDataInit) {
            onSelectModel(0);
            this.mLayoutMenuAdapter.setOnItemClickListener(this.mOnItemClickListener);
            this.mMarginWrapper.setOnClickListener(this.mMarginClickListener);
            this.mRatioWrapper.setOnClickListener(this.mRatioClickListener);
            this.mDataInit = true;
        }
    }

    /* access modifiers changed from: private */
    public void onSelectModel(int i) {
        if (this.mLayoutModels.size() != 0) {
            ((AbstractLayoutFragment) getRenderFragment()).onSelectModel(this.mLayoutModels.get(i));
        }
    }

    /* access modifiers changed from: private */
    public void reloadData() {
        this.mLayoutModels.clear();
        List<LayoutModel> layouts = ((LayoutPresenter) this.mPresenter).getLayouts(((LayoutPresenter) this.mPresenter).getImageCount());
        if (layouts != null) {
            this.mLayoutModels.addAll(layouts);
        }
        this.mLayoutMenuAdapter.notifyDataSetChanged();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((LayoutPresenter) this.mPresenter).loadDataFromResourceAsync(this.mDataLoadListener);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.collage_layout_menu, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mMarginWrapper = view.findViewById(R.id.collage_margin_control_wrapper);
        this.mMarginImageView = (ImageView) view.findViewById(R.id.collage_margin_control);
        this.mRatioWrapper = view.findViewById(R.id.collage_ratio_control_wrapper);
        this.mRatioImageView = (ImageView) view.findViewById(R.id.collage_ratio_control);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.collage_layout_list);
        this.mLayoutMenuAdapter = new LayoutMenuAdapter(getActivity(), this.mLayoutModels, new Selectable.Selector(getResources().getDrawable(R.drawable.collage_item_background_selector)));
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity(), 0, false);
        scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(getActivity()));
        this.mRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(getResources(), R.dimen.collage_menu_item_margin, 0));
        this.mRecyclerView.setAdapter(this.mLayoutMenuAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mViewReady = true;
        notifyDataInit();
    }
}
