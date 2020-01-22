package com.miui.gallery.search.resultpage;

import android.app.ActionBar;
import android.content.Context;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.suggestion.BaseSuggestionSection;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionSection;
import com.miui.gallery.search.feedback.SearchFeedbackHelper;
import com.miui.gallery.ui.CheckableView;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.ArrayList;
import miui.gallery.support.MiuiSdkCompat;

public class SearchFeedbackLikelyResultFragment extends SearchImageResultFragmentBase implements View.OnClickListener {
    /* access modifiers changed from: private */
    public LongSparseArray<Integer> mCheckedIds = new LongSparseArray<>();
    private View mContentView;
    private TextView mReportButton;
    private Button mSelectAllBtn;

    private class LikelyResultAdapter extends ImageResultSimpleAdapter {
        public LikelyResultAdapter(Context context) {
            super(context);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            if (view2 instanceof CheckableView) {
                final CheckableView checkableView = (CheckableView) view2;
                checkableView.setCheckable(true);
                checkableView.setChecked(SearchFeedbackLikelyResultFragment.this.isChecked(i));
                checkableView.setCheckableListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean access$100 = SearchFeedbackLikelyResultFragment.this.isChecked(i);
                        if (access$100) {
                            SearchFeedbackLikelyResultFragment.this.mCheckedIds.delete(SearchFeedbackLikelyResultFragment.this.mAdapter.getItemId(i));
                        } else {
                            SearchFeedbackLikelyResultFragment.this.mCheckedIds.put(SearchFeedbackLikelyResultFragment.this.mAdapter.getItemId(i), Integer.valueOf(i));
                        }
                        checkableView.setChecked(!access$100);
                        SearchFeedbackLikelyResultFragment.this.updateReportButtonState();
                    }
                });
            }
            return view2;
        }
    }

    private int getCheckedItemCount() {
        return this.mCheckedIds.size();
    }

    private SparseBooleanArray getCheckedPositions() {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(this.mAdapter.getCount());
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            if (isChecked(i)) {
                sparseBooleanArray.put(i, true);
            }
        }
        return sparseBooleanArray;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r1v9, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private void initTopBar() {
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.search_feedback_likely_result_page_title, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.home_text)).setText(String.format(getString(R.string.search_feedback_false_negative_title), new Object[]{this.mQueryText}));
        inflate.findViewById(R.id.home_arrow).setOnClickListener(this);
        this.mSelectAllBtn = (Button) inflate.findViewById(R.id.select_mode_button);
        MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mSelectAllBtn, 0);
        this.mSelectAllBtn.setOnClickListener(this);
        this.mSelectAllBtn.setEnabled(false);
        this.mActivity.getActionBar().setDisplayOptions(16);
        this.mActivity.getActionBar().setCustomView(inflate, new ActionBar.LayoutParams(-1, -1, 19));
    }

    /* access modifiers changed from: private */
    public boolean isChecked(int i) {
        return this.mCheckedIds.indexOfKey(this.mAdapter.getItemId(i)) >= 0;
    }

    private boolean isCheckedAll() {
        int checkedItemCount = getCheckedItemCount();
        return checkedItemCount > 0 && checkedItemCount == this.mAdapter.getCount();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    private void report() {
        ArrayList<String> checkedServerIdList = getCheckedServerIdList(getCheckedPositions());
        if (checkedServerIdList.size() > 0) {
            SearchFeedbackHelper.reportFalseNegativeImages(this.mActivity, this.mQueryText, this.mInFeedbackTaskMode, checkedServerIdList, new SearchFeedbackHelper.OnFeedbackCompleteListener() {
                public void onComplete(int i) {
                    if (i > 0) {
                        SearchFeedbackLikelyResultFragment.this.mActivity.setResult(-1);
                        SearchFeedbackLikelyResultFragment.this.finish();
                    }
                }
            });
        }
    }

    private void toggleSelectAll() {
        if (isCheckedAll()) {
            this.mCheckedIds.clear();
        } else {
            for (int i = 0; i < this.mAdapter.getCount(); i++) {
                this.mCheckedIds.put(this.mAdapter.getItemId(i), Integer.valueOf(i));
            }
        }
        this.mAdapter.notifyDataSetChanged();
        updateReportButtonState();
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void updateReportButtonState() {
        boolean z = true;
        this.mReportButton.setEnabled(getCheckedItemCount() > 0);
        Button button = this.mSelectAllBtn;
        if (this.mAdapter.getCount() <= 0) {
            z = false;
        }
        button.setEnabled(z);
        MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mSelectAllBtn, isCheckedAll() ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void changeFilterData(QueryInfo queryInfo, SuggestionCursor suggestionCursor) {
    }

    /* access modifiers changed from: protected */
    public void changeSuggestions(QueryInfo queryInfo, SuggestionCursor suggestionCursor) {
        super.changeSuggestions(queryInfo, suggestionCursor);
        updateReportButtonState();
    }

    /* access modifiers changed from: protected */
    public String getFrom() {
        return "from_likely_image_result";
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.search_likely_result_fragment;
    }

    /* access modifiers changed from: protected */
    public Loader getLoader() {
        Loader loader = getLoaderManager().getLoader(1);
        if (loader != null) {
            return loader;
        }
        return null;
    }

    public String getPageName() {
        return "search_likely_image_result";
    }

    /* access modifiers changed from: protected */
    public View getResultView() {
        return this.mContentView;
    }

    /* access modifiers changed from: protected */
    public QueryInfo.Builder getSectionDataQueryInfoBuilder() {
        QueryInfo.Builder sectionDataQueryInfoBuilder = super.getSectionDataQueryInfoBuilder();
        sectionDataQueryInfoBuilder.setSearchType(SearchConstants.SearchType.SEARCH_TYPE_FEEDBACK_LIKELY_RESULT);
        return sectionDataQueryInfoBuilder;
    }

    /* access modifiers changed from: protected */
    public QueryInfo.Builder initResultQueryInfoBuilder(Uri uri) {
        QueryInfo.Builder initResultQueryInfoBuilder = super.initResultQueryInfoBuilder(uri);
        initResultQueryInfoBuilder.setSearchType(SearchConstants.SearchType.SEARCH_TYPE_FEEDBACK_LIKELY_RESULT);
        return initResultQueryInfoBuilder;
    }

    public void onClick(View view) {
        if (view != null) {
            int id = view.getId();
            if (id == R.id.home_arrow) {
                finish();
            } else if (id == R.id.report_button) {
                report();
            } else if (id == R.id.select_mode_button) {
                toggleSelectAll();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void onInflateView(View view, Bundle bundle, Uri uri) {
        bind((StickyGridHeadersGridView) view.findViewById(R.id.grid), new LikelyResultAdapter(this.mActivity));
        this.mGridView.setAdapter((ListAdapter) this.mAdapter);
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SearchFeedbackLikelyResultFragment.this.goToPhotoPage(adapterView, i, "from_likely_image_result");
                GallerySamplingStatHelper.recordNumericPropertyEvent(SearchFeedbackLikelyResultFragment.this.getPageName(), "click_micro_thumb", (long) i);
            }
        });
        this.mContentView = view.findViewById(R.id.content_container);
        this.mReportButton = (TextView) view.findViewById(R.id.report_button);
        this.mReportButton.setOnClickListener(this);
        initTopBar();
    }

    public void onLoadMoreRequested() {
        if (!this.mAdapter.isEmpty()) {
            closeLoadMore();
        } else {
            super.onLoadMoreRequested();
        }
    }

    /* access modifiers changed from: protected */
    public void restartFilterLoader(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void restartSectionDataLoader(Bundle bundle) {
        if (getActivity() != null && getLoaderManager() != null) {
            getLoaderManager().restartLoader(1, bundle, this.mSectionDataLoaderCallback);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void restartSectionLoader(Bundle bundle) {
        this.mSectionDataResultHelper = new SearchImageResultHelper((Context) this.mActivity, this.mResultSectionQueryInfo, false, (SuggestionSection) new BaseSuggestionSection(this.mResultSectionQueryInfo, SearchConstants.SectionType.SECTION_TYPE_IMAGE_LIST, (SuggestionCursor) null, this.mActivity.getIntent().getData().toString(), (String) null, (String) null, (Suggestion) null));
        openLoadMore();
        onLoadMoreRequested();
    }

    /* access modifiers changed from: protected */
    public void updateTitle(String str) {
    }
}
