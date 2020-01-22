package com.miui.gallery.search.resultpage;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.StatusHandleHelper;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.display.RequestLoadMoreListener;
import com.miui.gallery.search.core.query.QueryLoader;
import com.miui.gallery.search.core.result.SuggestionResult;
import com.miui.gallery.search.core.resultprocessor.SectionedResultProcessor;
import com.miui.gallery.search.core.suggestion.GroupedSuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionSection;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.search.utils.SearchUtils;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.ui.BaseMediaFragment;

public abstract class SearchResultFragmentBase extends BaseMediaFragment implements RequestLoadMoreListener {
    protected LoaderManager.LoaderCallbacks<SuggestionResult> mFilterLoaderCallback = new LoaderManager.LoaderCallbacks<SuggestionResult>() {
        /* JADX WARNING: type inference failed for: r0v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader<SuggestionResult> onCreateLoader(int i, Bundle bundle) {
            QueryInfo.Builder buildFilterListQueryInfoBuilder;
            if (SearchResultFragmentBase.this.mSectionDataResultHelper == null || (buildFilterListQueryInfoBuilder = SearchResultFragmentBase.this.mSectionDataResultHelper.buildFilterListQueryInfoBuilder()) == null) {
                return null;
            }
            buildFilterListQueryInfoBuilder.setAppendSerialInfo(true);
            if (SearchResultFragmentBase.this.shouldUserPersistentResponse(bundle)) {
                buildFilterListQueryInfoBuilder.addParam("use_persistent_response", String.valueOf(true));
            }
            return new QueryLoader(SearchResultFragmentBase.this.mActivity, buildFilterListQueryInfoBuilder.build(), SearchResultFragmentBase.this.mSectionDataResultHelper.getFilterResultProcessor());
        }

        public void onLoadFinished(Loader<SuggestionResult> loader, SuggestionResult suggestionResult) {
            if (suggestionResult != null) {
                SearchResultFragmentBase.this.changeFilterData(suggestionResult.getQueryInfo(), suggestionResult.getData());
            } else {
                SearchLog.w("SearchResultFragmentBase", "Loader %s load finished, got no data available", Integer.valueOf(loader.getId()));
            }
        }

        public void onLoaderReset(Loader<SuggestionResult> loader) {
        }
    };
    protected boolean mInFeedbackTaskMode = false;
    /* access modifiers changed from: private */
    public long mLastInvalidTime;
    protected String mQueryText;
    protected QueryInfo mResultSectionQueryInfo;
    protected LoaderManager.LoaderCallbacks<SuggestionResult> mSectionDataLoaderCallback = new LoaderManager.LoaderCallbacks<SuggestionResult>() {
        /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader<SuggestionResult> onCreateLoader(int i, Bundle bundle) {
            QueryInfo.Builder sectionDataQueryInfoBuilder;
            if (SearchResultFragmentBase.this.mSectionDataResultHelper == null || (sectionDataQueryInfoBuilder = SearchResultFragmentBase.this.getSectionDataQueryInfoBuilder()) == null) {
                SearchResultFragmentBase.this.closeLoadMore();
                return null;
            }
            sectionDataQueryInfoBuilder.setAppendSerialInfo(true);
            if (SearchResultFragmentBase.this.shouldUserPersistentResponse(bundle)) {
                sectionDataQueryInfoBuilder.addParam("use_persistent_response", String.valueOf(true));
            }
            return new QueryLoader(SearchResultFragmentBase.this.mActivity, sectionDataQueryInfoBuilder.build(), SearchResultFragmentBase.this.mSectionDataResultHelper.getDataListResultProcessor(), false, SearchResultFragmentBase.this.receiveResultUpdates(), false);
        }

        public void onLoadFinished(Loader<SuggestionResult> loader, SuggestionResult suggestionResult) {
            int i;
            int i2 = 0;
            if (SearchResultFragmentBase.this.mSectionDataResultHelper.isInvalid()) {
                if (Math.abs(SystemClock.elapsedRealtime() - SearchResultFragmentBase.this.mLastInvalidTime) < 3000) {
                    if (suggestionResult != null) {
                        i = SearchUtils.getErrorStatus(suggestionResult);
                        SearchResultFragmentBase searchResultFragmentBase = SearchResultFragmentBase.this;
                        searchResultFragmentBase.changeSuggestions(searchResultFragmentBase.mResultSectionQueryInfo, suggestionResult.getData());
                    } else {
                        SearchResultFragmentBase.this.changeSuggestions((QueryInfo) null, (SuggestionCursor) null);
                        i = 11;
                    }
                    i2 = i;
                    SearchResultFragmentBase.this.closeLoadMore();
                    SearchStatUtils.reportEvent(SearchResultFragmentBase.this.getFrom(), "result_invalid_hash", "Query", SearchResultFragmentBase.this.mResultSectionQueryInfo.toString(), "Time", String.valueOf(System.currentTimeMillis()));
                } else {
                    if (suggestionResult != null) {
                        SearchResultFragmentBase searchResultFragmentBase2 = SearchResultFragmentBase.this;
                        searchResultFragmentBase2.changeSuggestions(searchResultFragmentBase2.mResultSectionQueryInfo, suggestionResult.getData());
                    } else {
                        SearchLog.w("SearchResultFragmentBase", "Loader %s load finished, invalid hash code got no data available", Integer.valueOf(loader.getId()));
                    }
                    Bundle bundle = new Bundle(1);
                    bundle.putBoolean("force_requery", true);
                    SearchResultFragmentBase.this.restartSectionDataLoader(bundle);
                }
                long unused = SearchResultFragmentBase.this.mLastInvalidTime = SystemClock.elapsedRealtime();
                SearchResultFragmentBase.this.mStatusHandleHelper.updateResultStatus(i2);
                return;
            }
            if (suggestionResult != null) {
                SearchResultFragmentBase searchResultFragmentBase3 = SearchResultFragmentBase.this;
                searchResultFragmentBase3.changeSuggestions(searchResultFragmentBase3.mResultSectionQueryInfo, suggestionResult.getData());
                i2 = SearchUtils.getErrorStatus(suggestionResult);
            } else {
                SearchLog.w("SearchResultFragmentBase", "Loader %s load finished, got no data available", Integer.valueOf(loader.getId()));
            }
            if (SearchResultFragmentBase.this.mSectionDataResultHelper.isLoadCompleted()) {
                SearchResultFragmentBase.this.onLoadComplete();
            } else if (!SearchConstants.isErrorStatus(i2)) {
                SearchResultFragmentBase.this.openLoadMore();
                if (SearchResultFragmentBase.this.getErrorViewAdapter().isEmptyDataView() && !SearchResultFragmentBase.this.mSectionDataResultHelper.isInvalid()) {
                    SearchResultFragmentBase.this.onLoadMoreRequested();
                }
            } else {
                SearchResultFragmentBase.this.closeLoadMore();
            }
            SearchResultFragmentBase.this.mStatusHandleHelper.updateResultStatus(i2);
        }

        public void onLoaderReset(Loader<SuggestionResult> loader) {
        }
    };
    protected SearchResultHelper mSectionDataResultHelper;
    /* access modifiers changed from: private */
    public SectionedResultProcessor mSectionProcessor = new SectionedResultProcessor();
    protected LoaderManager.LoaderCallbacks<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> mSectionsLoaderCallback = new LoaderManager.LoaderCallbacks<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>>() {
        /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> onCreateLoader(int i, Bundle bundle) {
            if (SearchResultFragmentBase.this.mResultSectionQueryInfo == null) {
                return null;
            }
            QueryInfo.Builder appendSerialInfo = new QueryInfo.Builder().cloneFrom(SearchResultFragmentBase.this.mResultSectionQueryInfo).setAppendSerialInfo(true);
            if (SearchResultFragmentBase.this.shouldUserPersistentResponse(bundle)) {
                appendSerialInfo.addParam("use_persistent_response", String.valueOf(true));
            }
            return new QueryLoader(SearchResultFragmentBase.this.mActivity, appendSerialInfo.build(), SearchResultFragmentBase.this.mSectionProcessor, false, SearchResultFragmentBase.this.receiveResultUpdates(), false);
        }

        public void onLoadFinished(Loader<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> loader, SuggestionResult<GroupedSuggestionCursor<SuggestionSection>> suggestionResult) {
            int i;
            if (suggestionResult != null) {
                if (suggestionResult.getData() == null || suggestionResult.getData().getGroupCount() <= 0) {
                    SearchResultFragmentBase.this.closeLoadMore();
                    SearchResultFragmentBase.this.mSectionDataResultHelper = null;
                } else {
                    SearchResultFragmentBase searchResultFragmentBase = SearchResultFragmentBase.this;
                    searchResultFragmentBase.mSectionDataResultHelper = searchResultFragmentBase.createSearchResultHelper(searchResultFragmentBase.mResultSectionQueryInfo, suggestionResult.getData());
                    SearchResultFragmentBase.this.openLoadMore();
                    SearchResultFragmentBase.this.onLoadMoreRequested();
                    if (SearchResultFragmentBase.this.mSectionDataResultHelper.needLoadFilterList()) {
                        SearchResultFragmentBase.this.restartFilterLoader((Bundle) null);
                    }
                }
                i = SearchUtils.getErrorStatus(suggestionResult);
            } else {
                SearchLog.w("SearchResultFragmentBase", "Loader %s load finished, got no data available", Integer.valueOf(loader.getId()));
                i = 0;
            }
            SearchResultFragmentBase.this.mStatusHandleHelper.updateResultStatus(i);
            if (i == 13) {
                AIAlbumStatusHelper.requestOpenCloudControlSearch(SearchResultFragmentBase.this.getFrom());
            }
        }

        public void onLoaderReset(Loader<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> loader) {
            SearchResultFragmentBase.this.changeSuggestions((QueryInfo) null, (SuggestionCursor) null);
            SearchResultFragmentBase.this.closeLoadMore();
            SearchResultFragmentBase.this.mStatusHandleHelper.updateResultStatus(-1);
            SearchResultFragmentBase.this.mSectionDataResultHelper = null;
        }
    };
    protected StatusHandleHelper mStatusHandleHelper = new StatusHandleHelper();
    protected boolean mSupportFeedback = false;

    /* access modifiers changed from: private */
    public boolean shouldUserPersistentResponse(Bundle bundle) {
        if (usePersistentResponse()) {
            return bundle == null || !bundle.getBoolean("force_requery", false);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void changeFilterData(QueryInfo queryInfo, SuggestionCursor suggestionCursor);

    /* access modifiers changed from: protected */
    public abstract void changeSuggestions(QueryInfo queryInfo, SuggestionCursor suggestionCursor);

    /* access modifiers changed from: protected */
    public abstract void closeLoadMore();

    /* access modifiers changed from: protected */
    public SearchResultHelper createSearchResultHelper(QueryInfo queryInfo, GroupedSuggestionCursor<SuggestionSection> groupedSuggestionCursor) {
        return new SearchResultHelper(queryInfo, groupedSuggestionCursor);
    }

    public void doRetry() {
        if (SearchConstants.isErrorStatus(this.mStatusHandleHelper.getResultStatus())) {
            openLoadMore();
            this.mStatusHandleHelper.refreshInfoViews();
            SearchResultHelper searchResultHelper = this.mSectionDataResultHelper;
            if (searchResultHelper == null) {
                restartSectionLoader((Bundle) null);
                return;
            }
            if (searchResultHelper.canLoadNextPage()) {
                restartSectionDataLoader((Bundle) null);
            } else {
                closeLoadMore();
            }
            if (this.mSectionDataResultHelper.needLoadFilterList()) {
                restartFilterLoader((Bundle) null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getDefaultTitle() {
        return getString(R.string.search_image_result_title);
    }

    /* access modifiers changed from: protected */
    public abstract StatusHandleHelper.AbstractErrorViewAdapter getErrorViewAdapter();

    /* access modifiers changed from: protected */
    public abstract String getFrom();

    /* access modifiers changed from: protected */
    public abstract int getLayoutResource();

    /* access modifiers changed from: protected */
    public abstract View getResultView();

    /* access modifiers changed from: protected */
    public QueryInfo.Builder getSectionDataQueryInfoBuilder() {
        return this.mSectionDataResultHelper.buildDataListQueryInfo();
    }

    /* access modifiers changed from: protected */
    public QueryInfo.Builder initResultQueryInfoBuilder(Uri uri) {
        QueryInfo.Builder builder = new QueryInfo.Builder(SearchConstants.SearchType.SEARCH_TYPE_RESULT);
        for (String next : uri.getQueryParameterNames()) {
            if (next.equals("title")) {
                this.mQueryText = uri.getQueryParameter(next);
            } else if (next.equals("inFeedbackTaskMode")) {
                this.mInFeedbackTaskMode = uri.getBooleanQueryParameter(next, false);
            } else if (next.equals("supportFeedback")) {
                this.mSupportFeedback = uri.getBooleanQueryParameter(next, false);
            } else {
                builder.addParam(next, uri.getQueryParameter(next));
            }
        }
        return builder;
    }

    /* access modifiers changed from: protected */
    public void initStatusHandlerHelper(View view) {
        this.mStatusHandleHelper.init(getResultView(), view.findViewById(R.id.info_view), view.findViewById(R.id.loading_view), view.findViewById(R.id.empty_view), getErrorViewAdapter());
    }

    /* access modifiers changed from: protected */
    public abstract boolean moreThanOnePage();

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SearchStatUtils.createNewSerial(getFrom());
        restartSectionLoader((Bundle) null);
        openLoadMore();
        this.mStatusHandleHelper.refreshInfoViews();
    }

    public void onDestroy() {
        super.onDestroy();
        SearchStatUtils.onCompleteSerial(getFrom());
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutResource(), viewGroup, false);
        Uri data = this.mActivity.getIntent().getData();
        this.mResultSectionQueryInfo = initResultQueryInfoBuilder(data).build();
        onInflateView(inflate, bundle, data);
        updateTitle(data.getQueryParameter("title"));
        initStatusHandlerHelper(inflate);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public abstract void onInflateView(View view, Bundle bundle, Uri uri);

    /* access modifiers changed from: protected */
    public abstract void onLoadComplete();

    public void onLoadMoreRequested() {
        SearchResultHelper searchResultHelper = this.mSectionDataResultHelper;
        if (searchResultHelper == null || !searchResultHelper.canLoadNextPage()) {
            closeLoadMore();
        } else {
            restartSectionDataLoader((Bundle) null);
        }
    }

    public void onResume() {
        super.onResume();
        doRetry();
    }

    /* access modifiers changed from: protected */
    public abstract void openLoadMore();

    /* access modifiers changed from: protected */
    public boolean receiveResultUpdates() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void restartFilterLoader(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void restartSectionDataLoader(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void restartSectionLoader(Bundle bundle);

    /* access modifiers changed from: protected */
    public boolean supportFeedback() {
        return this.mSupportFeedback;
    }

    /* access modifiers changed from: protected */
    public void updateTitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mActivity.getActionBar().setTitle(str);
        } else {
            this.mActivity.getActionBar().setTitle(getDefaultTitle());
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean usePersistentResponse();
}
