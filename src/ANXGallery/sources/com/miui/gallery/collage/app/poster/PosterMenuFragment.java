package com.miui.gallery.collage.app.poster;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.collage.app.common.AbstractPosterFragment;
import com.miui.gallery.collage.app.common.CollageMenuFragment;
import com.miui.gallery.collage.core.CollagePresenter;
import com.miui.gallery.collage.core.poster.PosterModel;
import com.miui.gallery.collage.core.poster.PosterPresenter;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PosterMenuFragment extends CollageMenuFragment<PosterPresenter, AbstractPosterFragment> {
    private boolean mDataInit = false;
    /* access modifiers changed from: private */
    public boolean mDataReady = false;
    private CollagePresenter.DataLoadListener mInitDataLoadListener = new CollagePresenter.DataLoadListener() {
        public void onDataLoad() {
            boolean unused = PosterMenuFragment.this.mDataReady = true;
            PosterMenuFragment.this.reloadData();
            PosterMenuFragment.this.notifyDataInit();
        }
    };
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            PosterMenuFragment.this.mPosterMenuAdapter.setSelection(i);
            PosterMenuFragment.this.onSelectModel(i);
            return true;
        }
    };
    /* access modifiers changed from: private */
    public PosterMenuAdapter mPosterMenuAdapter;
    private List<PosterModel> mPosterModels = new ArrayList();
    private SimpleRecyclerView mRecyclerView;
    private boolean mViewReady = false;

    /* access modifiers changed from: private */
    public void notifyDataInit() {
        if (this.mViewReady && this.mDataReady && !this.mDataInit) {
            onSelectModel(0);
            this.mDataInit = true;
            this.mPosterMenuAdapter.setOnItemClickListener(this.mOnItemClickListener);
        }
    }

    /* access modifiers changed from: private */
    public void onSelectModel(int i) {
        if (this.mPosterModels.size() != 0) {
            PosterModel posterModel = this.mPosterModels.get(i);
            ((AbstractPosterFragment) getRenderFragment()).onSelectModel(posterModel, ((PosterPresenter) this.mPresenter).getPosterCollageLayout(posterModel));
        }
    }

    /* access modifiers changed from: private */
    public void reloadData() {
        this.mPosterModels.clear();
        List<PosterModel> posters = ((PosterPresenter) this.mPresenter).getPosters();
        if (posters != null) {
            this.mPosterModels.addAll(posters);
        }
        this.mPosterMenuAdapter.notifyDataSetChanged();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((PosterPresenter) this.mPresenter).loadDataFromResourceAsync(this.mInitDataLoadListener);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRecyclerView = (SimpleRecyclerView) layoutInflater.inflate(R.layout.collage_poster_menu, viewGroup, false);
        return this.mRecyclerView;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("PosterMenuFragment", "onDestroy");
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mPosterMenuAdapter = new PosterMenuAdapter(getActivity(), this.mPosterModels, new Selectable.Selector(getResources().getDrawable(R.drawable.collage_item_background_selector)));
        this.mPosterMenuAdapter.setImageCount(((PosterPresenter) this.mPresenter).getImageCount());
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity(), 0, false);
        scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(getActivity()));
        this.mRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(getResources(), R.dimen.collage_menu_item_margin, 0));
        this.mRecyclerView.setAdapter(this.mPosterMenuAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mViewReady = true;
        notifyDataInit();
    }
}
