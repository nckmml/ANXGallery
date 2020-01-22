package com.miui.gallery.search.navigationpage;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.search.SearchConfig;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.SearchFragmentBase;
import com.miui.gallery.search.SearchStatusLoader;
import com.miui.gallery.search.StatusHandleHelper;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.context.SearchContext;
import com.miui.gallery.search.core.display.SectionedSuggestionItemDecoration;
import com.miui.gallery.search.core.query.QueryLoader;
import com.miui.gallery.search.core.result.SuggestionResult;
import com.miui.gallery.search.core.resultprocessor.SectionedResultProcessor;
import com.miui.gallery.search.core.suggestion.BaseSuggestionSection;
import com.miui.gallery.search.core.suggestion.GroupedSuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionSection;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.search.utils.SearchUtils;
import java.util.HashSet;

public class NavigationFragment extends SearchFragmentBase {
    /* access modifiers changed from: private */
    public NavigationAdapter mAdapter;
    private LoaderManager.LoaderCallbacks<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> mDataLoaderCallback = new LoaderManager.LoaderCallbacks<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>>() {
        private boolean isDone(SuggestionResult suggestionResult) {
            return (suggestionResult == null || suggestionResult.getResultExtras() == null || !suggestionResult.getResultExtras().getBoolean("is_done", true)) ? false : true;
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> onCreateLoader(int i, Bundle bundle) {
            return new QueryLoader(NavigationFragment.this.mActivity, new QueryInfo.Builder(SearchConstants.SearchType.SEARCH_TYPE_NAVIGATION).setAppendSerialInfo(true).addParam("use_persistent_response", String.valueOf(true)).build(), NavigationFragment.this.mSectionProcessor, true, false, true);
        }

        public void onLoadFinished(Loader<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> loader, SuggestionResult<GroupedSuggestionCursor<SuggestionSection>> suggestionResult) {
            int i = 0;
            if (suggestionResult != null) {
                if (suggestionResult.isEmpty()) {
                    SearchLog.d("NavigationFragment", "Loader %s load finished, got empty result", Integer.valueOf(loader.getId()));
                } else {
                    SearchLog.d("NavigationFragment", "Loader %s load finished, got %s results", Integer.valueOf(loader.getId()), Integer.valueOf(suggestionResult.getData().getCount()));
                }
                NavigationFragment.this.mAdapter.changeSuggestions(((QueryLoader) loader).getQueryInfo(), suggestionResult.getData(), false);
                i = SearchUtils.getErrorStatus(suggestionResult);
            } else {
                SearchLog.w("NavigationFragment", "Loader %s load finished, got no data available", Integer.valueOf(loader.getId()));
            }
            boolean unused = NavigationFragment.this.mIsLoading = !isDone(suggestionResult);
            NavigationFragment.this.mStatusHandleHelper.updateResultStatus(i);
        }

        public void onLoaderReset(Loader<SuggestionResult<GroupedSuggestionCursor<SuggestionSection>>> loader) {
            if (NavigationFragment.this.mAdapter != null) {
                NavigationFragment.this.mAdapter.changeSuggestions((QueryInfo) null, null, false);
                NavigationFragment.this.mStatusHandleHelper.updateResultStatus(-1);
            }
            boolean unused = NavigationFragment.this.mIsLoading = false;
        }
    };
    private RecyclerView mDataView;
    /* access modifiers changed from: private */
    public boolean mIsLoading;
    /* access modifiers changed from: private */
    public SectionedResultProcessor mSectionProcessor = new SectionedResultProcessor() {
        /* access modifiers changed from: protected */
        public BaseSuggestionSection getIndependentSection(HashSet<String> hashSet, SuggestionSection suggestionSection) {
            BaseSuggestionSection independentSection = super.getIndependentSection(hashSet, suggestionSection);
            int i = AnonymousClass5.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[independentSection.getSectionType().ordinal()];
            if ((i == 1 || i == 2) && independentSection.moveToMore() == null) {
                independentSection.setSectionMoreItem(SearchConfig.get().getDefaultMoreItem(GalleryApp.sGetAndroidContext(), independentSection.getSectionType(), false));
            }
            return independentSection;
        }
    };
    /* access modifiers changed from: private */
    public StatusHandleHelper mStatusHandleHelper = new StatusHandleHelper();
    private LoaderManager.LoaderCallbacks<Integer> mStatusLoaderCallback = new LoaderManager.LoaderCallbacks<Integer>() {
        /* JADX WARNING: type inference failed for: r3v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader<Integer> onCreateLoader(int i, Bundle bundle) {
            return new SearchStatusLoader(NavigationFragment.this.mActivity, NavigationFragment.this.mStatusReportDelegate);
        }

        public void onLoadFinished(Loader<Integer> loader, Integer num) {
            NavigationFragment.this.mStatusHandleHelper.updateBaseStatus(num.intValue());
        }

        public void onLoaderReset(Loader<Integer> loader) {
            NavigationFragment.this.mStatusHandleHelper.updateBaseStatus(-1);
        }
    };
    /* access modifiers changed from: private */
    public SearchStatusLoader.StatusReportDelegate mStatusReportDelegate = new StatusReportDelegate();

    /* renamed from: com.miui.gallery.search.navigationpage.NavigationFragment$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType = new int[SearchConstants.SectionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[SearchConstants.SectionType.SECTION_TYPE_LOCATION.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[SearchConstants.SectionType.SECTION_TYPE_TAG.ordinal()] = 2;
        }
    }

    private class ErrorViewAdapter extends StatusHandleHelper.AbstractErrorViewAdapter {
        public ErrorViewAdapter(Context context) {
            super(context);
        }

        public void addFooterView(View view) {
            NavigationFragment.this.mAdapter.addFooterView(view);
        }

        public void addHeaderView(View view) {
            NavigationFragment.this.mAdapter.addHeaderView(view);
        }

        public View getInfoView(View view, int i, StatusHandleHelper.InfoViewPosition infoViewPosition) {
            if (!SearchConstants.isErrorStatus(i) || infoViewPosition != StatusHandleHelper.InfoViewPosition.FOOTER) {
                return super.getInfoView(view, i, infoViewPosition);
            }
            return null;
        }

        public boolean isEmptyDataView() {
            return NavigationFragment.this.mAdapter.isEmpty();
        }

        public boolean isLoading() {
            return NavigationFragment.this.mIsLoading && isEmptyDataView();
        }

        public void removeFooterView(View view) {
            NavigationFragment.this.mAdapter.removeFooterView(view);
        }

        public void removeHeaderView(View view) {
            NavigationFragment.this.mAdapter.removeHeaderView(view);
        }

        public void requestRetry() {
            NavigationFragment.this.doRetry();
        }
    }

    private static class StatusReportDelegate implements SearchStatusLoader.StatusReportDelegate {
        private StatusReportDelegate() {
        }

        public boolean shouldReportStatus(int i) {
            return i == 4;
        }
    }

    private void restartDataLoader() {
        getLoaderManager().restartLoader(1, (Bundle) null, this.mDataLoaderCallback);
        this.mIsLoading = true;
        this.mStatusHandleHelper.refreshInfoViews();
    }

    public void doRetry() {
        if (SearchConstants.isErrorStatus(this.mStatusHandleHelper.getResultStatus())) {
            restartDataLoader();
        }
    }

    public String getPageName() {
        return "search_navigation";
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getLoaderManager().initLoader(0, (Bundle) null, this.mStatusLoaderCallback);
        restartDataLoader();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r9v2, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r8v13, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.navigation_fragment, viewGroup, false);
        this.mDataView = (RecyclerView) inflate.findViewById(R.id.data_view);
        this.mDataView.setItemAnimator((RecyclerView.ItemAnimator) null);
        this.mDataView.setLayoutManager(new LinearLayoutManager(this.mActivity, 1, false));
        this.mAdapter = new NavigationAdapter(this.mActivity, SearchContext.getInstance().getSuggestionViewFactory(), "from_navigation");
        this.mDataView.setAdapter(this.mAdapter);
        this.mDataView.addItemDecoration(new SectionedSuggestionItemDecoration(this.mActivity, this.mAdapter, false, 0, true));
        this.mDataView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (NavigationFragment.this.getCallback() != null) {
                    NavigationFragment.this.getCallback().requestIME(false);
                }
                return false;
            }
        });
        this.mStatusHandleHelper.init(this.mDataView, inflate.findViewById(R.id.info_view), inflate.findViewById(R.id.loading_view), inflate.findViewById(R.id.empty_view), new ErrorViewAdapter(this.mActivity));
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getLoaderManager().getLoader(0).forceLoad();
        doRetry();
    }
}
