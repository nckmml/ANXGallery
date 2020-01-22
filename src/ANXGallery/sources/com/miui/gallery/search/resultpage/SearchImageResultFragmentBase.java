package com.miui.gallery.search.resultpage;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.StatusHandleHelper;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.display.RequestLoadMoreListener;
import com.miui.gallery.search.core.suggestion.RankInfo;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.search.utils.SearchUtils;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.IntentUtil;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.ArrayList;

public abstract class SearchImageResultFragmentBase extends SearchResultFragmentBase implements RequestLoadMoreListener {
    protected ImageResultSimpleAdapter mAdapter;
    protected int mColumnCount = 0;
    private ErrorViewAdapter mErrorViewAdapter = null;
    protected StickyGridHeadersGridView mGridView;
    /* access modifiers changed from: private */
    public View mLoadCompleteFooter;

    private class ErrorViewAdapter extends StatusHandleHelper.AbstractErrorViewAdapter {
        public ErrorViewAdapter(Context context) {
            super(context);
        }

        public void addFooterView(View view) {
            SearchImageResultFragmentBase.this.mGridView.addFooterView(view);
        }

        public void addHeaderView(View view) {
            SearchImageResultFragmentBase.this.mGridView.addHeaderView(view);
        }

        /* access modifiers changed from: protected */
        public View getInfoItemView(View view, int i, StatusHandleHelper.InfoViewPosition infoViewPosition) {
            return infoViewPosition == StatusHandleHelper.InfoViewPosition.FOOTER ? this.mInflater.inflate(R.layout.search_item_error_footer_layout, (ViewGroup) null) : super.getInfoItemView(view, i, infoViewPosition);
        }

        public boolean isEmptyDataView() {
            return SearchImageResultFragmentBase.this.mAdapter.getCount() <= 0;
        }

        public boolean isLoading() {
            return SearchImageResultFragmentBase.this.mAdapter.isLoading();
        }

        public void removeFooterView(View view) {
            SearchImageResultFragmentBase.this.mGridView.removeFooterView(view);
        }

        public void removeHeaderView(View view) {
            SearchImageResultFragmentBase.this.mGridView.removeHeaderView(view);
        }

        public void requestRetry() {
            SearchImageResultFragmentBase.this.doRetry();
        }
    }

    /* access modifiers changed from: protected */
    public final void bind(StickyGridHeadersGridView stickyGridHeadersGridView, ImageResultSimpleAdapter imageResultSimpleAdapter) {
        this.mGridView = stickyGridHeadersGridView;
        this.mAdapter = imageResultSimpleAdapter;
        this.mAdapter.setRequestLoadMoreListener(this);
    }

    /* access modifiers changed from: protected */
    public void changeSuggestions(QueryInfo queryInfo, SuggestionCursor suggestionCursor) {
        int i;
        RankInfo rankInfo = SearchUtils.getRankInfo(suggestionCursor);
        boolean isHorizontalDocumentStyle = SearchConstants.isHorizontalDocumentStyle(rankInfo);
        if (isHorizontalDocumentStyle) {
            Config.ThumbConfig.get().getClass();
            i = 2;
        } else {
            i = Config.ThumbConfig.get().sMicroThumbColumnsPortrait;
        }
        int dimensionPixelSize = isHorizontalDocumentStyle ? getResources().getDimensionPixelSize(R.dimen.micro_thumb_document_spacing) : getResources().getDimensionPixelSize(R.dimen.micro_thumb_horizontal_spacing);
        if (this.mColumnCount != i) {
            this.mColumnCount = i;
            this.mGridView.setNumColumns(this.mColumnCount);
            this.mGridView.setVerticalSpacing(dimensionPixelSize);
            this.mGridView.setHorizontalSpacing(dimensionPixelSize);
        }
        this.mAdapter.changeSuggestions(queryInfo, rankInfo, suggestionCursor);
    }

    /* access modifiers changed from: protected */
    public void closeLoadMore() {
        this.mAdapter.closeLoadMore();
        this.mStatusHandleHelper.refreshInfoViews();
    }

    /* access modifiers changed from: protected */
    public ArrayList<String> getCheckedServerIdList(SparseBooleanArray sparseBooleanArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (sparseBooleanArray != null && sparseBooleanArray.size() > 0) {
            for (int i = 0; i < sparseBooleanArray.size(); i++) {
                if (sparseBooleanArray.valueAt(i)) {
                    arrayList.add(this.mAdapter.getServerId(sparseBooleanArray.keyAt(i)));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String getCheckedServerIds(SparseBooleanArray sparseBooleanArray) {
        return TextUtils.join(",", getCheckedServerIdList(sparseBooleanArray));
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
    public String getImageIds() {
        ImageResultSimpleAdapter imageResultSimpleAdapter = this.mAdapter;
        if (imageResultSimpleAdapter != null) {
            return imageResultSimpleAdapter.getImageIds();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public ImageSize getMicroThumbnailSize() {
        ImageResultSimpleAdapter imageResultSimpleAdapter = this.mAdapter;
        return imageResultSimpleAdapter != null ? imageResultSimpleAdapter.getMicroThumbnailSize() : Config.ThumbConfig.get().sMicroTargetSize;
    }

    /* access modifiers changed from: protected */
    public String getOrder() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Uri getPhotoPageDataLoaderUri() {
        return GalleryContract.SearchResultPhoto.URI;
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String[] getSelectionArguments() {
        String imageIds = getImageIds();
        if (!TextUtils.isEmpty(imageIds)) {
            return imageIds.split(",");
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void goToPhotoPage(AdapterView<?> adapterView, int i, String str) {
        AdapterView<?> adapterView2 = adapterView;
        int i2 = i;
        IntentUtil.gotoPhotoPage(this, adapterView2, getPhotoPageDataLoaderUri(), i2, this.mAdapter.getCount(), getSelection(), getSelectionArguments(), getOrder(), new ImageLoadParams(this.mAdapter.getItemKey(i), this.mAdapter.getLocalPath(i), getMicroThumbnailSize(), this.mAdapter.getItemDecodeRectF(i), i, this.mAdapter.getMimeType(i), this.mAdapter.getFileLength(i)), true);
        SearchStatUtils.reportEvent(str, "client_image_operation_open_photo_page", "serverIds", this.mAdapter.getServerId(i), "queryText", this.mQueryText);
    }

    /* access modifiers changed from: protected */
    public boolean moreThanOnePage() {
        int i;
        int firstVisiblePosition = this.mGridView.getFirstVisiblePosition();
        int lastVisiblePosition = this.mGridView.getLastVisiblePosition();
        while (true) {
            if (lastVisiblePosition <= firstVisiblePosition) {
                i = Reader.READ_DONE;
                break;
            } else if (this.mGridView.getItemIndexByItemPosition(lastVisiblePosition) > 0) {
                i = this.mGridView.getItemIndexByItemPosition(lastVisiblePosition);
                break;
            } else {
                lastVisiblePosition--;
            }
        }
        return firstVisiblePosition > 0 || i < this.mAdapter.getCount() - 1;
    }

    public void onDestroy() {
        ImageResultSimpleAdapter imageResultSimpleAdapter = this.mAdapter;
        if (imageResultSimpleAdapter != null) {
            imageResultSimpleAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onLoadComplete() {
        this.mAdapter.loadComplete();
        this.mStatusHandleHelper.refreshInfoViews();
        ThreadManager.getMainHandler().post(new Runnable() {
            /* JADX WARNING: type inference failed for: r1v3, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
            public void run() {
                if (SearchImageResultFragmentBase.this.moreThanOnePage()) {
                    if (SearchImageResultFragmentBase.this.mLoadCompleteFooter == null) {
                        SearchImageResultFragmentBase searchImageResultFragmentBase = SearchImageResultFragmentBase.this;
                        View unused = searchImageResultFragmentBase.mLoadCompleteFooter = LayoutInflater.from(searchImageResultFragmentBase.mActivity).inflate(R.layout.search_item_load_complete_layout, SearchImageResultFragmentBase.this.mGridView, false);
                        SearchImageResultFragmentBase.this.mGridView.addFooterView(SearchImageResultFragmentBase.this.mLoadCompleteFooter);
                    }
                    SearchImageResultFragmentBase.this.mStatusHandleHelper.refreshInfoViews();
                } else if (SearchImageResultFragmentBase.this.mLoadCompleteFooter != null) {
                    SearchImageResultFragmentBase.this.mGridView.removeFooterView(SearchImageResultFragmentBase.this.mLoadCompleteFooter);
                    View unused2 = SearchImageResultFragmentBase.this.mLoadCompleteFooter = null;
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void openLoadMore() {
        this.mAdapter.openLoadMore();
    }

    /* access modifiers changed from: protected */
    public boolean supportFeedback() {
        return this.mInFeedbackTaskMode || super.supportFeedback();
    }

    /* access modifiers changed from: protected */
    public boolean usePersistentResponse() {
        return true;
    }
}
