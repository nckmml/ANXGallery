package com.miui.gallery.search.resultpage;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.miui.gallery.R;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.query.QueryLoader;
import com.miui.gallery.search.core.suggestion.GroupedSuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionSection;
import com.miui.gallery.search.feedback.SearchFeedbackHelper;
import com.miui.gallery.search.resultpage.ImageResultAdapter;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.search.utils.SearchUtils;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.util.ActionURIHandler;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.SoundUtils;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.TwoStageDrawer;
import com.miui.gallery.widget.editwrapper.EditableListViewWrapperDeprecated;
import com.miui.gallery.widget.editwrapper.MultiChoiceModeListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchImageResultFragment extends SearchImageResultFragmentBase {
    private ChoiceModeListener mChoiceModeListener = new ChoiceModeListener();
    /* access modifiers changed from: private */
    public SearchFeedbackLikelyController mFeedbackLikelyController = null;
    private FilterBarController mFilterBarController = null;
    private int mFilterVisibleFilterThreshold = 0;
    private int mFilterVisibleImageThreshold = 0;
    protected EditableListViewWrapperDeprecated mGridViewWrapper;
    private VoiceAssistantReceiver mVoiceAssistantReceiver;

    private class ChoiceModeListener implements MultiChoiceModeListener {
        private MenuItem mAddToAlbum;
        private MenuItem mCreativity;
        private MenuItem mDelete;
        private MenuItem mFeedback;
        /* access modifiers changed from: private */
        public ActionMode mMode;
        private MenuItem mSend;

        private ChoiceModeListener() {
        }

        private void updateMenuState() {
            if (SearchImageResultFragment.this.mGridViewWrapper.getCheckedItemCount() < 1) {
                this.mAddToAlbum.setEnabled(false);
                this.mDelete.setEnabled(false);
                this.mCreativity.setEnabled(false);
                this.mSend.setEnabled(false);
                MenuItem menuItem = this.mFeedback;
                if (menuItem != null) {
                    menuItem.setEnabled(false);
                    return;
                }
                return;
            }
            this.mAddToAlbum.setEnabled(true);
            this.mDelete.setEnabled(true);
            this.mCreativity.setEnabled(true);
            this.mSend.setEnabled(true);
            MenuItem menuItem2 = this.mFeedback;
            if (menuItem2 != null) {
                menuItem2.setEnabled(true);
            }
        }

        public boolean inChoiceMode() {
            return this.mMode != null;
        }

        /* JADX WARNING: type inference failed for: r12v3, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r1v1, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r0v8, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_produce:
                    MediaAndAlbumOperations.doProduceCreation(SearchImageResultFragment.this.mActivity, new MediaAndAlbumOperations.OnCompleteListener() {
                        public void onComplete(long[] jArr) {
                            actionMode.finish();
                        }
                    }, SearchImageResultFragment.this.mGridViewWrapper.getCheckedItems());
                    GallerySamplingStatHelper.recordCountEvent("search", "produce");
                    return true;
                case R.id.action_send:
                    SearchImageResultFragment.this.onSend((String) null, (String) null);
                    return true;
                case R.id.add_to_album:
                    SearchImageResultFragment searchImageResultFragment = SearchImageResultFragment.this;
                    final String checkedServerIds = searchImageResultFragment.getCheckedServerIds(searchImageResultFragment.mGridViewWrapper.getCheckedItemPositions());
                    MediaAndAlbumOperations.addToAlbum(SearchImageResultFragment.this.mActivity, new MediaAndAlbumOperations.OnAddAlbumListener() {
                        public void onComplete(long[] jArr, boolean z) {
                            ChoiceModeListener.this.mMode.finish();
                            if (jArr != null) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("queryText", SearchImageResultFragment.this.mQueryText);
                                hashMap.put("count", String.valueOf(jArr.length));
                                hashMap.put("serverIds", checkedServerIds);
                                SearchStatUtils.reportEvent("from_image_result", "client_image_operation_add_to_album", hashMap);
                            }
                        }
                    }, 0, true, true, SearchImageResultFragment.this.mGridViewWrapper.isCheckedItemContainVideo(), SearchImageResultFragment.this.mGridViewWrapper.getCheckedItemIds());
                    return true;
                case R.id.delete:
                    SearchImageResultFragment searchImageResultFragment2 = SearchImageResultFragment.this;
                    final String checkedServerIds2 = searchImageResultFragment2.getCheckedServerIds(searchImageResultFragment2.mGridViewWrapper.getCheckedItemPositions());
                    MediaAndAlbumOperations.delete(SearchImageResultFragment.this.mActivity, "SearchImageResultFragmentDeleteMediaDialogFragment", new DeletionTask.OnDeletionCompleteListener() {
                        /* JADX WARNING: type inference failed for: r7v6, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r7v11, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                        public void onDeleted(int i, long[] jArr) {
                            if (SearchImageResultFragment.this.mActivity != null) {
                                ToastUtils.makeText((Context) SearchImageResultFragment.this.mActivity, (CharSequence) SearchImageResultFragment.this.getResources().getQuantityString(R.plurals.delete_finish_format, i, new Object[]{Integer.valueOf(i)}));
                                if (i > 0) {
                                    SoundUtils.playSoundForOperation(SearchImageResultFragment.this.mActivity, 0);
                                }
                                actionMode.finish();
                                HashMap hashMap = new HashMap();
                                hashMap.put("queryText", SearchImageResultFragment.this.mQueryText);
                                hashMap.put("count", String.valueOf(i));
                                hashMap.put("serverIds", checkedServerIds2);
                                SearchStatUtils.reportEvent("from_image_result", "client_image_operation_delete", hashMap);
                            }
                        }
                    }, -1, "", 0, 27, SearchImageResultFragment.this.mGridViewWrapper.getCheckedItemIds());
                    return true;
                case R.id.feedback:
                    SearchImageResultFragment searchImageResultFragment3 = SearchImageResultFragment.this;
                    SearchFeedbackHelper.reportFalsePositiveImages(SearchImageResultFragment.this.mActivity, SearchImageResultFragment.this.mQueryText, SearchImageResultFragment.this.mInFeedbackTaskMode, searchImageResultFragment3.getCheckedServerIdList(searchImageResultFragment3.mGridViewWrapper.getCheckedItemPositions()), new SearchFeedbackHelper.OnFeedbackCompleteListener() {
                        public void onComplete(int i) {
                            actionMode.finish();
                            if (i > 0) {
                                SearchImageResultFragment.this.mSectionDataResultHelper.resetCacheInfo();
                                Bundle bundle = new Bundle(1);
                                bundle.putBoolean("force_requery", true);
                                SearchImageResultFragment.this.restartSectionDataLoader(bundle);
                            }
                        }
                    });
                    return true;
                default:
                    return false;
            }
        }

        public void onAllItemsCheckedStateChanged(ActionMode actionMode, boolean z) {
            updateMenuState();
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        /* JADX WARNING: type inference failed for: r0v4, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mMode = actionMode;
            boolean isMiui10 = BuildUtil.isMiui10(SearchImageResultFragment.this.mActivity);
            if (SearchImageResultFragment.this.supportFeedback()) {
                actionMode.getMenuInflater().inflate(isMiui10 ? R.menu.v15_image_result_image_menu : R.menu.v18_image_result_image_menu, menu);
            } else {
                actionMode.getMenuInflater().inflate(BuildUtil.isMiui10(SearchImageResultFragment.this.mActivity) ? R.menu.v15_home_page_menu : R.menu.v18_home_page_menu, menu);
            }
            this.mFeedback = menu.findItem(R.id.feedback);
            this.mAddToAlbum = menu.findItem(R.id.add_to_album);
            this.mDelete = menu.findItem(R.id.delete);
            this.mCreativity = menu.findItem(R.id.action_produce);
            this.mSend = menu.findItem(R.id.action_send);
            if (!SearchImageResultFragment.this.mInFeedbackTaskMode) {
                return true;
            }
            this.mAddToAlbum.setVisible(false);
            this.mDelete.setVisible(false);
            this.mCreativity.setVisible(false);
            this.mSend.setVisible(false);
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mMode = null;
            if (SearchImageResultFragment.this.mFeedbackLikelyController != null) {
                SearchImageResultFragment.this.mFeedbackLikelyController.showLikelyBar();
            }
        }

        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
            updateMenuState();
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            if (SearchImageResultFragment.this.mFeedbackLikelyController == null) {
                return false;
            }
            SearchImageResultFragment.this.mFeedbackLikelyController.hideLikelyBar();
            return false;
        }
    }

    private class SearchFeedbackLikelyController implements LoaderManager.LoaderCallbacks<DataListSourceResult> {
        private Boolean mHasLikelyItems = null;
        private View mLikelyGuide = null;
        private ViewStub mLikelyGuideStub = null;

        public SearchFeedbackLikelyController(ViewStub viewStub) {
            this.mLikelyGuideStub = viewStub;
        }

        public void hideLikelyBar() {
            View view = this.mLikelyGuide;
            if (view != null) {
                view.setVisibility(8);
            }
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader<DataListSourceResult> onCreateLoader(int i, Bundle bundle) {
            return new QueryLoader(SearchImageResultFragment.this.mActivity, new QueryInfo.Builder(SearchConstants.SearchType.SEARCH_TYPE_FEEDBACK_LIKELY_RESULT).addParam("pos", MovieStatUtils.DOWNLOAD_SUCCESS).addParam("num", "1").addParam("tagName", SearchImageResultFragment.this.mQueryText).addParam("inFeedbackTaskMode", String.valueOf(SearchImageResultFragment.this.mInFeedbackTaskMode)).build(), new DataListResultProcessor(), false, SearchImageResultFragment.this.receiveResultUpdates(), false);
        }

        public void onLoadFinished(Loader<DataListSourceResult> loader, DataListSourceResult dataListSourceResult) {
            this.mHasLikelyItems = Boolean.valueOf((dataListSourceResult == null || dataListSourceResult.getData() == null || dataListSourceResult.getData().getCount() <= 0) ? false : true);
            SearchLog.d("SearchImageResultFragment", "On query likely item finished [%s]", this.mHasLikelyItems);
            showLikelyBar();
            SearchImageResultFragment.this.getLoaderManager().destroyLoader(4);
        }

        public void onLoaderReset(Loader<DataListSourceResult> loader) {
        }

        public void showLikelyBar() {
            ViewStub viewStub;
            Boolean bool = this.mHasLikelyItems;
            if (bool == null) {
                this.mHasLikelyItems = false;
                SearchImageResultFragment.this.getLoaderManager().restartLoader(4, (Bundle) null, this);
            } else if (bool.booleanValue()) {
                if (this.mLikelyGuide == null && (viewStub = this.mLikelyGuideStub) != null) {
                    this.mLikelyGuide = viewStub.inflate();
                    this.mLikelyGuide.findViewById(R.id.click_view).setOnClickListener(new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v7, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
                        public void onClick(View view) {
                            Uri.Builder appendQueryParameter = GalleryContract.Search.URI_LIKELY_RESULT_PAGE.buildUpon().appendQueryParameter("title", SearchImageResultFragment.this.mQueryText).appendQueryParameter("tagName", SearchImageResultFragment.this.mQueryText);
                            Bundle bundle = new Bundle(3);
                            bundle.putBoolean("start_activity_for_result", true);
                            bundle.putInt("request_code", 1);
                            bundle.putString("from", "from_image_result");
                            ActionURIHandler.handleUri(SearchImageResultFragment.this.mActivity, appendQueryParameter.build(), bundle);
                        }
                    });
                }
                View view = this.mLikelyGuide;
                if (view != null) {
                    view.setVisibility(0);
                    ((TextView) this.mLikelyGuide.findViewById(R.id.title)).setText(String.format(SearchImageResultFragment.this.getString(R.string.search_feedback_likely_title), new Object[]{SearchImageResultFragment.this.mQueryText}));
                }
            }
        }
    }

    private class VoiceAssistantReceiver extends BroadcastReceiver {
        private VoiceAssistantReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (SearchImageResultFragment.this.mGridViewWrapper.isInActionMode()) {
                SearchImageResultFragment.this.onSend(intent.getStringExtra("assistant_target_package"), intent.getStringExtra("assistant_target_class"));
            }
        }
    }

    /* access modifiers changed from: private */
    public void onSend(String str, String str2) {
        SparseBooleanArray checkedItemPositions = this.mGridViewWrapper.getCheckedItemPositions();
        ArrayList arrayList = new ArrayList(checkedItemPositions.size());
        ArrayList arrayList2 = new ArrayList(checkedItemPositions.size());
        int i = 0;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < checkedItemPositions.size(); i3++) {
            int keyAt = checkedItemPositions.keyAt(i3);
            if (checkedItemPositions.get(keyAt)) {
                arrayList.add(Integer.valueOf(keyAt));
                arrayList2.add(Long.valueOf(this.mAdapter.getItemKey(keyAt)));
                if (keyAt < i2) {
                    i2 = keyAt;
                }
            }
        }
        if (arrayList.size() != 0) {
            int[] iArr = new int[arrayList.size()];
            long[] jArr = new long[arrayList2.size()];
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
                jArr[i4] = ((Long) arrayList2.get(i4)).longValue();
            }
            if (i2 != Integer.MAX_VALUE) {
                i = i2;
            }
            IntentUtil.gotoPreviewSelectPage(this, getPhotoPageDataLoaderUri(), i, this.mAdapter.getCount(), getSelection(), getSelectionArguments(), getOrder(), new ImageLoadParams(this.mAdapter.getItemKey(i), this.mAdapter.getLocalPath(i), getMicroThumbnailSize(), this.mAdapter.getItemDecodeRectF(i), i, this.mAdapter.getMimeType(i), this.mAdapter.getFileLength(i)), jArr, iArr, str, str2);
            this.mGridViewWrapper.stopActionMode();
            HashMap hashMap = new HashMap();
            hashMap.put("queryText", this.mQueryText);
            hashMap.put("count", String.valueOf(checkedItemPositions.size()));
            hashMap.put("serverIds", getCheckedServerIds(checkedItemPositions));
            SearchStatUtils.reportEvent("from_image_result", "client_image_operation_share", hashMap);
        }
    }

    private void restartSectionLoader(boolean z) {
        Bundle bundle;
        if (z) {
            bundle = new Bundle(1);
            bundle.putBoolean("force_requery", true);
        } else {
            bundle = null;
        }
        restartSectionLoader(bundle);
    }

    private void showFilterBar() {
        if (this.mFilterBarController != null) {
            boolean z = true;
            boolean z2 = this.mAdapter != null && this.mAdapter.getCount() >= this.mFilterVisibleImageThreshold;
            boolean z3 = this.mFilterBarController.getFilterDataCount() >= this.mFilterVisibleFilterThreshold;
            FilterBarController filterBarController = this.mFilterBarController;
            if (!z2 || !z3) {
                z = false;
            }
            filterBarController.showFilterBar(z);
        }
    }

    /* access modifiers changed from: protected */
    public void changeFilterData(QueryInfo queryInfo, SuggestionCursor suggestionCursor) {
        if (this.mFilterBarController == null) {
            this.mFilterBarController = new FilterBarController(getActivity(), (TwoStageDrawer) getView(), "from_image_result");
        }
        this.mFilterBarController.swapCursor(queryInfo, suggestionCursor);
        showFilterBar();
    }

    /* access modifiers changed from: protected */
    public void changeSuggestions(QueryInfo queryInfo, SuggestionCursor suggestionCursor) {
        super.changeSuggestions(queryInfo, suggestionCursor);
        showFilterBar();
        if (SearchConstants.isHorizontalDocumentStyle(SearchUtils.getRankInfo(suggestionCursor))) {
            this.mGridViewWrapper.disableScaleImageViewAniWhenInActionMode();
        } else {
            this.mGridViewWrapper.enableScaleImageViewAniWhenInActionMode();
        }
        if (this.mFeedbackLikelyController != null && !this.mChoiceModeListener.inChoiceMode()) {
            this.mFeedbackLikelyController.showLikelyBar();
        }
    }

    /* access modifiers changed from: protected */
    public SearchResultHelper createSearchResultHelper(QueryInfo queryInfo, GroupedSuggestionCursor<SuggestionSection> groupedSuggestionCursor) {
        return new SearchImageResultHelper((Context) getActivity(), queryInfo, false, groupedSuggestionCursor);
    }

    /* access modifiers changed from: protected */
    public String getFrom() {
        return "from_image_result";
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.search_image_result_fragment;
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
        Loader loader3 = getLoaderManager().getLoader(3);
        if (loader3 != null) {
            arrayList.add(loader3);
        }
        return arrayList;
    }

    public String getPageName() {
        return "search_image_result";
    }

    /* access modifiers changed from: protected */
    public View getResultView() {
        return this.mGridView;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            SearchFeedbackLikelyController searchFeedbackLikelyController = this.mFeedbackLikelyController;
            if (searchFeedbackLikelyController != null) {
                searchFeedbackLikelyController.hideLikelyBar();
                this.mFeedbackLikelyController = null;
            }
            restartSectionLoader(true);
        }
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public void onInflateView(View view, Bundle bundle, Uri uri) {
        ImageResultAdapter imageResultAdapter = new ImageResultAdapter(this.mActivity);
        bind((StickyGridHeadersGridView) view.findViewById(R.id.grid), imageResultAdapter);
        imageResultAdapter.setHeaderClickListener(new ImageResultAdapter.OnHeaderItemClickedListener() {
            /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
            public void onHeaderItemClicked(int i) {
                IntentUtil.gotoDailyAlbumDetailPage(SearchImageResultFragment.this.mActivity, ((ImageResultAdapter) SearchImageResultFragment.this.mAdapter).getGroupCreateDate(i));
                SearchStatUtils.reportEvent(SearchImageResultFragment.this.getFrom(), "client_image_operation_jump_to_daily", "queryText", SearchImageResultFragment.this.mQueryText);
            }
        });
        this.mGridViewWrapper = new EditableListViewWrapperDeprecated(this.mGridView);
        this.mGridViewWrapper.setAdapter(this.mAdapter);
        this.mGridViewWrapper.setChoiceMode(3);
        this.mGridViewWrapper.setMultiChoiceModeListener(this.mChoiceModeListener);
        this.mGridViewWrapper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SearchImageResultFragment.this.goToPhotoPage(adapterView, i, "from_image_result");
                GallerySamplingStatHelper.recordNumericPropertyEvent("search", "click_micro_thumb", (long) i);
            }
        });
        this.mFilterVisibleImageThreshold = getResources().getInteger(R.integer.search_filter_visible_image_threshold);
        this.mFilterVisibleFilterThreshold = getResources().getInteger(R.integer.search_filter_visible_filter_threshold);
        if (this.mInFeedbackTaskMode && SearchFeedbackHelper.needToQueryLikelyImagesForFeedbackTask(this.mQueryText)) {
            this.mFeedbackLikelyController = new SearchFeedbackLikelyController((ViewStub) view.findViewById(R.id.likely_results));
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onPause() {
        super.onPause();
        if (this.mVoiceAssistantReceiver != null) {
            LocalBroadcastManager.getInstance(this.mActivity).unregisterReceiver(this.mVoiceAssistantReceiver);
            this.mVoiceAssistantReceiver = null;
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onResume() {
        super.onResume();
        if (this.mVoiceAssistantReceiver == null) {
            this.mVoiceAssistantReceiver = new VoiceAssistantReceiver();
            LocalBroadcastManager.getInstance(this.mActivity).registerReceiver(this.mVoiceAssistantReceiver, new IntentFilter("com.miui.gallery.action.VOICE_ASSISTANT_SELECT_SHARE"));
        }
    }

    /* access modifiers changed from: protected */
    public boolean receiveResultUpdates() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void restartFilterLoader(Bundle bundle) {
        if (!this.mInFeedbackTaskMode && getActivity() != null && getLoaderManager() != null) {
            getLoaderManager().restartLoader(3, bundle, this.mFilterLoaderCallback);
        }
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
    public void updateTitle(String str) {
        if (this.mInFeedbackTaskMode && !TextUtils.isEmpty(str)) {
            str = String.format(getString(R.string.search_feedback_false_positive_title), new Object[]{str});
        }
        super.updateTitle(str);
    }
}
