package com.miui.gallery.video.editor.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.miui.gallery.R;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.video.editor.Filter;
import com.miui.gallery.video.editor.adapter.FilterRecyclerViewAdapter;
import com.miui.gallery.video.editor.manager.FilterAdjustManager;
import com.miui.gallery.video.editor.util.ToolsUtil;
import com.miui.gallery.video.editor.widget.SingleChoiceRecyclerView;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilterView extends LinearLayout {
    private FilterRecyclerViewAdapter mAdapter;
    private ArrayList<Filter> mFilters = new ArrayList<>();
    /* access modifiers changed from: private */
    public OnItemSelectedListener mItemSelectedListener;
    private ScrollLinearLayoutManager mLinearLayoutManager;
    /* access modifiers changed from: private */
    public SingleChoiceRecyclerView mSingleChoiceRecycleView;

    private class MyFilterItemSelectChangeListener implements SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter.ItemSelectChangeListener {
        private MyFilterItemSelectChangeListener() {
        }

        public boolean onItemSelect(SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter singleChoiceRecyclerViewAdapter, int i, boolean z) {
            if (FilterView.this.mItemSelectedListener == null || !z) {
                return false;
            }
            ScrollLinearLayoutManager.onItemClick(FilterView.this.mSingleChoiceRecycleView, i);
            FilterView.this.mSingleChoiceRecycleView.smoothScrollToPosition(i);
            singleChoiceRecyclerViewAdapter.setSelectedItemPosition(i);
            singleChoiceRecyclerViewAdapter.clearLastSelectedPostion();
            return FilterView.this.mItemSelectedListener.onItemSelect(singleChoiceRecyclerViewAdapter, i, z);
        }
    }

    public interface OnItemSelectedListener {
        boolean onItemSelect(SingleChoiceRecyclerView.SingleChoiceRecyclerViewAdapter singleChoiceRecyclerViewAdapter, int i, boolean z);
    }

    public FilterView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.video_editor_filterview, this);
        initData();
        initRecyclerView(context);
    }

    private void initRecyclerView(Context context) {
        this.mSingleChoiceRecycleView = (SingleChoiceRecyclerView) findViewById(R.id.recycler_view);
        this.mLinearLayoutManager = new ScrollLinearLayoutManager(context, 0, false);
        this.mLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(context));
        this.mSingleChoiceRecycleView.setLayoutManager(this.mLinearLayoutManager);
        this.mAdapter = new FilterRecyclerViewAdapter(context, this.mFilters);
        this.mAdapter.setItemSelectChangeListener(new MyFilterItemSelectChangeListener());
        this.mSingleChoiceRecycleView.setAdapter(this.mAdapter);
        this.mSingleChoiceRecycleView.addItemDecoration(new SimpleRecyclerView.BlankDivider(getResources(), R.dimen.editor_menu_filter_item_gap, 0));
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mSingleChoiceRecycleView);
    }

    public List<String> getFilterCurrentEffect() {
        Filter selectedFilter = getSelectedFilter();
        if (selectedFilter == null) {
            return null;
        }
        return Arrays.asList(new String[]{selectedFilter.getLabel()});
    }

    public Filter getSelectedFilter() {
        FilterRecyclerViewAdapter filterRecyclerViewAdapter = this.mAdapter;
        if (filterRecyclerViewAdapter == null) {
            return null;
        }
        return this.mAdapter.getFilter(filterRecyclerViewAdapter.getSelectedItemPosition());
    }

    public int getSelectedItemPosition() {
        FilterRecyclerViewAdapter filterRecyclerViewAdapter = this.mAdapter;
        if (filterRecyclerViewAdapter != null) {
            return filterRecyclerViewAdapter.getSelectedItemPosition();
        }
        return 0;
    }

    public void initData() {
        this.mFilters = FilterAdjustManager.getFilterData();
    }

    public void setItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void updateSelectedItemPosition(int i) {
        FilterRecyclerViewAdapter filterRecyclerViewAdapter = this.mAdapter;
        if (filterRecyclerViewAdapter != null) {
            if (i != -1) {
                filterRecyclerViewAdapter.setSelectedItemPosition(i);
            } else if (ToolsUtil.isRTLDirection()) {
                Collections.reverse(this.mFilters);
                FilterRecyclerViewAdapter filterRecyclerViewAdapter2 = this.mAdapter;
                filterRecyclerViewAdapter2.setSelectedItemPosition(filterRecyclerViewAdapter2.getItemCount() - 1);
            } else {
                this.mAdapter.setSelectedItemPosition(0);
            }
        }
    }
}
