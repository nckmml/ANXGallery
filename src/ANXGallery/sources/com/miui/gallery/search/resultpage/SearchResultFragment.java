package com.miui.gallery.search.resultpage;

import android.app.Activity;
import android.content.Context;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.search.StatusHandleHelper;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.context.SearchContext;
import com.miui.gallery.search.core.display.BaseSuggestionAdapter;
import com.miui.gallery.search.core.display.DefaultActionClickListener;
import com.miui.gallery.search.core.display.FullSpanDelegate;
import com.miui.gallery.search.core.display.OnActionClickListener;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.widget.recyclerview.GridLayoutInnerPaddingItemDecoration;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends SearchResultFragmentBase {
    private String mDefaultTitle;
    protected ErrorViewAdapter mErrorViewAdapter = null;
    private String mFrom;
    private String mPageName;
    /* access modifiers changed from: private */
    public BaseSuggestionAdapter<SuggestionCursor> mResultAdapter;
    private RecyclerView mResultView;

    protected class ErrorViewAdapter extends StatusHandleHelper.AbstractErrorViewAdapter {
        public ErrorViewAdapter(Context context) {
            super(context);
        }

        public void addFooterView(View view) {
            SearchResultFragment.this.mResultAdapter.addFooterView(view);
        }

        public void addHeaderView(View view) {
            SearchResultFragment.this.mResultAdapter.addHeaderView(view);
        }

        /* access modifiers changed from: protected */
        public View getInfoItemView(View view, int i, StatusHandleHelper.InfoViewPosition infoViewPosition) {
            return infoViewPosition == StatusHandleHelper.InfoViewPosition.FOOTER ? this.mInflater.inflate(R.layout.search_item_error_footer_layout, (ViewGroup) null) : super.getInfoItemView(view, i, infoViewPosition);
        }

        public boolean isEmptyDataView() {
            return SearchResultFragment.this.mResultAdapter.isEmpty();
        }

        public boolean isLoading() {
            return SearchResultFragment.this.mResultAdapter.isLoading();
        }

        public void removeFooterView(View view) {
            SearchResultFragment.this.mResultAdapter.removeFooterView(view);
        }

        public void removeHeaderView(View view) {
            SearchResultFragment.this.mResultAdapter.removeHeaderView(view);
        }

        public void requestRetry() {
            SearchResultFragment.this.doRetry();
        }
    }

    private class GridSpanLookUp extends GridLayoutManager.SpanSizeLookup {
        private final FullSpanDelegate mFullSpanDelegate;
        private final GridLayoutManager mLayoutManager;

        public GridSpanLookUp(GridLayoutManager gridLayoutManager, FullSpanDelegate fullSpanDelegate) {
            this.mLayoutManager = gridLayoutManager;
            this.mFullSpanDelegate = fullSpanDelegate;
        }

        public int getSpanSize(int i) {
            if (this.mFullSpanDelegate.isFullSpan(i)) {
                return this.mLayoutManager.getSpanCount();
            }
            return 1;
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private OnActionClickListener genFeedbackTaskModeActionClickListener() {
        return new DefaultActionClickListener(this.mActivity) {
            /* access modifiers changed from: protected */
            public void handleUri(Activity activity, Uri uri, Bundle bundle) {
                super.handleUri(activity, uri.buildUpon().appendQueryParameter("inFeedbackTaskMode", String.valueOf(SearchResultFragment.this.mInFeedbackTaskMode)).build(), bundle);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void changeFilterData(QueryInfo queryInfo, SuggestionCursor suggestionCursor) {
    }

    /* access modifiers changed from: protected */
    public void changeSuggestions(QueryInfo queryInfo, SuggestionCursor suggestionCursor) {
        BaseSuggestionAdapter<SuggestionCursor> baseSuggestionAdapter = this.mResultAdapter;
        if (baseSuggestionAdapter != null) {
            baseSuggestionAdapter.changeSuggestions(queryInfo, suggestionCursor);
        }
    }

    /* access modifiers changed from: protected */
    public void closeLoadMore() {
        this.mResultAdapter.closeLoadMore();
    }

    /* access modifiers changed from: protected */
    public String getDefaultTitle() {
        return this.mDefaultTitle;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public StatusHandleHelper.AbstractErrorViewAdapter getErrorViewAdapter() {
        if (this.mErrorViewAdapter == null) {
            this.mErrorViewAdapter = new ErrorViewAdapter(this.mActivity);
        }
        return this.mErrorViewAdapter;
    }

    /* access modifiers changed from: protected */
    public String getFrom() {
        return this.mFrom;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.search_result_fragment;
    }

    /* access modifiers changed from: protected */
    public Loader getLoader() {
        return null;
    }

    /* access modifiers changed from: protected */
    public List<Loader> getLoaders() {
        ArrayList arrayList = new ArrayList();
        Loader loader = getLoaderManager().getLoader(1);
        if (loader != null) {
            arrayList.add(loader);
        }
        Loader loader2 = getLoaderManager().getLoader(2);
        if (loader2 != null) {
            arrayList.add(loader2);
        }
        return arrayList;
    }

    public String getPageName() {
        return this.mPageName;
    }

    /* access modifiers changed from: protected */
    public View getResultView() {
        return this.mResultView;
    }

    /* access modifiers changed from: protected */
    public boolean moreThanOnePage() {
        return this.mResultView.getLayoutManager().findViewByPosition(0) == null || this.mResultView.getLayoutManager().findViewByPosition(this.mResultAdapter.getItemCount() - 1) == null;
    }

    /* JADX WARNING: type inference failed for: r11v5, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    /* JADX WARNING: type inference failed for: r10v9, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void onInflateView(View view, Bundle bundle, Uri uri) {
        this.mResultView = (RecyclerView) view.findViewById(R.id.result_view);
        this.mResultView.setItemAnimator((RecyclerView.ItemAnimator) null);
        if ("locationList".equals(uri.getQueryParameter(nexExportFormat.TAG_FORMAT_TYPE))) {
            this.mFrom = "from_location_list";
            this.mDefaultTitle = getString(R.string.search_title_location);
            this.mPageName = "search_location_list";
        } else if ("tagList".equals(uri.getQueryParameter(nexExportFormat.TAG_FORMAT_TYPE))) {
            this.mFrom = "from_tag_list";
            this.mDefaultTitle = getString(R.string.search_title_tag);
            this.mPageName = "search_tag_list";
        } else {
            this.mFrom = "from_image_result";
            this.mDefaultTitle = super.getDefaultTitle();
            this.mPageName = "search_result";
        }
        this.mResultAdapter = new BaseSuggestionAdapter<>(this.mActivity, SearchContext.getInstance().getSuggestionViewFactory(), this.mFrom);
        this.mResultAdapter.setOnLoadMoreListener(this);
        this.mResultView.setAdapter(this.mResultAdapter);
        if (this.mInFeedbackTaskMode) {
            this.mResultAdapter.setActionClickListener(genFeedbackTaskModeActionClickListener());
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mActivity, getResources().getInteger(R.integer.search_result_page_column_count));
        gridLayoutManager.setSpanSizeLookup(new GridSpanLookUp(gridLayoutManager, this.mResultAdapter));
        this.mResultView.setLayoutManager(gridLayoutManager);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.result_page_horizontal_padding);
        this.mResultView.addItemDecoration(new GridLayoutInnerPaddingItemDecoration(dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.result_page_top_padding), dimensionPixelSize, 0, 0, 0, true, gridLayoutManager));
    }

    /* access modifiers changed from: protected */
    public void onLoadComplete() {
        this.mResultAdapter.closeLoadMore();
        this.mStatusHandleHelper.refreshInfoViews();
        ThreadManager.getMainHandler().post(new Runnable() {
            public void run() {
                if (SearchResultFragment.this.moreThanOnePage()) {
                    SearchResultFragment.this.mResultAdapter.showLoadCompleteView();
                    SearchResultFragment.this.mStatusHandleHelper.refreshInfoViews();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void openLoadMore() {
        this.mResultAdapter.openLoadMore(false);
    }

    /* access modifiers changed from: protected */
    public void restartFilterLoader(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void restartSectionDataLoader(Bundle bundle) {
        if (getActivity() != null && getLoaderManager() != null) {
            getLoaderManager().restartLoader(2, bundle, this.mSectionDataLoaderCallback);
        }
    }

    /* access modifiers changed from: protected */
    public void restartSectionLoader(Bundle bundle) {
        if (getActivity() != null && getLoaderManager() != null) {
            getLoaderManager().restartLoader(1, bundle, this.mSectionsLoaderCallback);
        }
    }

    /* access modifiers changed from: protected */
    public boolean usePersistentResponse() {
        return true;
    }
}
