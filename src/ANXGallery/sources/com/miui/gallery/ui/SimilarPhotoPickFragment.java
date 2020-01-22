package com.miui.gallery.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CompoundButton;
import com.miui.gallery.R;
import com.miui.gallery.adapter.CleanerPhotoPickBaseAdapter;
import com.miui.gallery.adapter.SimilarPhotoPickAdapter;
import com.miui.gallery.cleaner.BaseScanner;
import com.miui.gallery.cleaner.ScanResult;
import com.miui.gallery.cleaner.SimilarScanner;
import com.miui.gallery.ui.CleanerPhotoPickFragment;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.widget.LoadMoreLayout;
import com.miui.gallery.widget.editwrapper.SimpleMultiChoiceModeListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.ArrayList;
import java.util.Iterator;
import miui.widget.SlidingButton;

public class SimilarPhotoPickFragment extends CleanerPhotoPickFragment {
    /* access modifiers changed from: private */
    public ArrayList<Long> mCheckedHeaderGroupId = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<Long> mClusterGroupId;
    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        public void onChanged() {
            if (SimilarPhotoPickFragment.this.mIsFirstLoadFinish && SimilarPhotoPickFragment.this.mAdapter.getCount() > 0) {
                SimilarPhotoPickFragment similarPhotoPickFragment = SimilarPhotoPickFragment.this;
                similarPhotoPickFragment.mIsFirstLoadFinish = false;
                if (similarPhotoPickFragment.mKeepClearCheckBox.isChecked()) {
                    SimilarPhotoPickFragment.this.mGridViewWrapper.setAllItemsCheckState(true);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public ArrayList<Integer> mGroupItemCount;
    /* access modifiers changed from: private */
    public ArrayList<Integer> mGroupStartPos;
    /* access modifiers changed from: private */
    public boolean mIsScrolling = false;
    /* access modifiers changed from: private */
    public SlidingButton mKeepClearCheckBox;
    private CompoundButton.OnCheckedChangeListener mKeepClearCheckListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SimilarPhotoPickFragment.this.mGridViewWrapper.setAllItemsCheckState(z);
            if (!z) {
                GallerySamplingStatHelper.recordCountEvent("cleaner", "similar_keep_clear_cancel");
            }
        }
    };
    /* access modifiers changed from: private */
    public LoadMoreLayout mLoadMoreLayout;
    private SimpleMultiChoiceModeListener mMultiChoiceModeListener = new SimpleMultiChoiceModeListener() {
        public void onAllItemsCheckedStateChanged(ActionMode actionMode, boolean z) {
            SimilarPhotoPickFragment.this.mCheckedHeaderGroupId.clear();
            SimilarPhotoPickFragment.this.keepClearPhotos();
            SimilarPhotoPickFragment.this.onItemSelectedChanged();
        }

        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
            SimilarPhotoPickFragment.this.onItemSelectedChanged();
        }
    };
    /* access modifiers changed from: private */
    public boolean mNeedResetLoader;
    private BaseScanner.OnScanResultUpdateListener mOnScanResultUpdateListener = new BaseScanner.OnScanResultUpdateListener() {
        public void onUpdate(int i, long j, ScanResult scanResult) {
            if (SimilarPhotoPickFragment.this.getActivity() == null || i != 3) {
                return;
            }
            if (SimilarPhotoPickFragment.this.mIsScrolling) {
                boolean unused = SimilarPhotoPickFragment.this.mNeedResetLoader = true;
            } else {
                SimilarPhotoPickFragment.this.restartLoader();
            }
        }
    };
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (SimilarPhotoPickFragment.this.getActivity() != null) {
                if (i == 0) {
                    boolean unused = SimilarPhotoPickFragment.this.mIsScrolling = false;
                    if (SimilarPhotoPickFragment.this.mNeedResetLoader) {
                        SimilarPhotoPickFragment.this.restartLoader();
                        return;
                    }
                    return;
                }
                boolean unused2 = SimilarPhotoPickFragment.this.mIsScrolling = true;
            }
        }
    };
    private SimilarPhotoPickLoaderCallback mSimilarPhotoPickLoaderCallbacks;

    private class SimilarPhotoPickLoaderCallback implements LoaderManager.LoaderCallbacks {
        public CursorLoader mLoader;

        private SimilarPhotoPickLoaderCallback() {
        }

        /* JADX WARNING: type inference failed for: r2v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader onCreateLoader(int i, Bundle bundle) {
            this.mLoader = new CursorLoader(SimilarPhotoPickFragment.this.mActivity);
            SimilarPhotoPickFragment.this.configLoader(this.mLoader);
            return this.mLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            Cursor cursor = (Cursor) obj;
            ArrayList arrayList = new ArrayList(cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(Long.valueOf(cursor.getLong(0)));
                } while (cursor.moveToNext());
            }
            ArrayList arrayList2 = new ArrayList(SimilarPhotoPickFragment.this.mGroupItemCount);
            ArrayList arrayList3 = new ArrayList(SimilarPhotoPickFragment.this.mGroupStartPos);
            ArrayList arrayList4 = new ArrayList(SimilarPhotoPickFragment.this.mClusterGroupId);
            ArrayList arrayList5 = new ArrayList(SimilarPhotoPickFragment.this.mScanResultIds);
            Iterator it = arrayList5.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (!arrayList.contains(Long.valueOf(((Long) it.next()).longValue()))) {
                    it.remove();
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < arrayList3.size() && ((Integer) arrayList3.get(i2)).intValue() <= i) {
                        i3 = i2;
                        i2++;
                    }
                    for (int i4 = i3 + 1; i4 < arrayList3.size(); i4++) {
                        int intValue = ((Integer) arrayList3.get(i4)).intValue() - 1;
                        if (intValue < 0) {
                            intValue = 0;
                        }
                        arrayList3.set(i4, Integer.valueOf(intValue));
                    }
                    if (((Integer) arrayList2.get(i3)).intValue() <= 1) {
                        arrayList2.remove(i3);
                        arrayList4.remove(i3);
                        arrayList3.remove(i3);
                    } else {
                        arrayList2.set(i3, Integer.valueOf(((Integer) arrayList2.get(i3)).intValue() - 1));
                    }
                } else {
                    i++;
                }
            }
            if (cursor.getCount() == arrayList5.size()) {
                SortCursor sortCursor = new SortCursor(cursor, arrayList5);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("extra_timeline_item_count_in_group", arrayList2);
                bundle.putIntegerArrayList("extra_timeline_group_start_pos", arrayList3);
                bundle.putLongArray("extra_timeline_group_ids", MiscUtil.ListToArray(arrayList4));
                sortCursor.setExtras(bundle);
                SimilarPhotoPickFragment.this.getAdapter().swapCursor(sortCursor);
                if (arrayList5.size() > 0) {
                    SimilarPhotoPickFragment.this.keepClearPhotos();
                }
            }
            if (SimilarPhotoPickFragment.this.getAdapter().getCount() == 0) {
                SimilarPhotoPickFragment.this.setEmptyViewVisibility(0);
            } else {
                SimilarPhotoPickFragment.this.setEmptyViewVisibility(8);
            }
            if (SimilarPhotoPickFragment.this.mScanner.isLoadingValid()) {
                SimilarPhotoPickFragment.this.mLoadMoreLayout.startLoad();
            } else {
                SimilarPhotoPickFragment.this.mLoadMoreLayout.setLoadComplete();
            }
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private class SortCursor extends CursorWrapper {
        private int mPos = 0;
        private ArrayList<Long> mResults;
        private int[] sortList = new int[getCount()];

        public SortCursor(Cursor cursor, ArrayList<Long> arrayList) {
            super(cursor);
            this.mResults = arrayList;
            initSortList();
        }

        private void initSortList() {
            Cursor wrappedCursor = getWrappedCursor();
            for (int i = 0; i < getCount(); i++) {
                if (wrappedCursor.moveToPosition(i)) {
                    long j = wrappedCursor.getLong(0);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= this.mResults.size() || i2 >= getCount()) {
                            break;
                        } else if (j == this.mResults.get(i2).longValue()) {
                            this.sortList[i2] = i;
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
        }

        public int getPosition() {
            return this.mPos;
        }

        public boolean move(int i) {
            return moveToPosition(this.mPos + i);
        }

        public boolean moveToFirst() {
            return moveToPosition(0);
        }

        public boolean moveToLast() {
            return moveToPosition(getCount() - 1);
        }

        public boolean moveToNext() {
            return moveToPosition(this.mPos + 1);
        }

        public boolean moveToPosition(int i) {
            if (i < 0 || i >= this.sortList.length) {
                if (i < 0) {
                    this.mPos = -1;
                }
                int[] iArr = this.sortList;
                if (i >= iArr.length) {
                    this.mPos = iArr.length;
                }
                return getWrappedCursor().moveToPosition(i);
            }
            this.mPos = i;
            return getWrappedCursor().moveToPosition(this.sortList[i]);
        }

        public boolean moveToPrevious() {
            return moveToPosition(this.mPos - 1);
        }
    }

    public SimilarPhotoPickFragment() {
        super(3);
    }

    /* access modifiers changed from: private */
    public void keepClearPhotos() {
        ArrayList<Integer> headerPositions = ((SimilarPhotoPickAdapter) this.mAdapter).getHeaderPositions();
        long[] groupIds = ((SimilarPhotoPickAdapter) this.mAdapter).getGroupIds();
        if (MiscUtil.isValid(headerPositions) && groupIds != null) {
            for (int i = 0; i < headerPositions.size(); i++) {
                if (!this.mCheckedHeaderGroupId.contains(Long.valueOf(groupIds[i]))) {
                    this.mGridViewWrapper.setItemChecked(headerPositions.get(i).intValue(), false);
                    this.mCheckedHeaderGroupId.add(Long.valueOf(groupIds[i]));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void restartLoader() {
        Loader loader;
        resetScanResult();
        if (isAdded() && (loader = getLoaderManager().getLoader(1)) != null) {
            configLoader((CursorLoader) loader);
            loader.forceLoad();
        }
        this.mNeedResetLoader = false;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public CleanerPhotoPickBaseAdapter createAdapter() {
        return new SimilarPhotoPickAdapter(this.mActivity);
    }

    /* access modifiers changed from: protected */
    public CleanerPhotoPickFragment.DeleteMessage getDeleteMessage() {
        return new CleanerPhotoPickFragment.DeleteMessage.Builder().setDupType(0).setReason(48).setCleanerSubEvent("cleaner_similar_used").build();
    }

    /* access modifiers changed from: protected */
    public int getLayoutSource() {
        return R.layout.similar_photo_pick_layout;
    }

    /* access modifiers changed from: protected */
    public LoaderManager.LoaderCallbacks getLoaderCallback() {
        if (this.mSimilarPhotoPickLoaderCallbacks == null) {
            this.mSimilarPhotoPickLoaderCallbacks = new SimilarPhotoPickLoaderCallback();
        }
        return this.mSimilarPhotoPickLoaderCallbacks;
    }

    public String getPageName() {
        return "cleaner_similar_photo_pick";
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.mAlbumDetailGridView.setNumColumns(getResources().getInteger(R.integer.thumbnail_similar_photo_pick_columns_land));
        } else {
            this.mAlbumDetailGridView.setNumColumns(getResources().getInteger(R.integer.thumbnail_similar_photo_pick_columns));
        }
        this.mAlbumDetailGridView.setSelection(this.mAlbumDetailGridView.getFirstVisiblePosition());
    }

    public void onDestroy() {
        super.onDestroy();
        ((SimilarScanner) this.mScanner).removeSingleItemGroups();
        this.mScanner.removeListener(this.mOnScanResultUpdateListener);
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        this.mActivity.getActionBar().setTitle(R.string.cleaner_similar_title);
        this.mActivity.getActionBar().getCustomView().findViewById(R.id.do_select).setVisibility(8);
        this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
        this.mGridViewWrapper.setAdapter(this.mAdapter);
        this.mGridViewWrapper.setMultiChoiceModeListener(this.mMultiChoiceModeListener);
        ((StickyGridHeadersGridView) this.mAlbumDetailGridView).setHeadersIgnorePadding(true);
        this.mAlbumDetailGridView.setOnScrollListener(this.mOnScrollListener);
        this.mLoadMoreLayout = (LoadMoreLayout) layoutInflater.inflate(R.layout.load_more_layout, this.mAlbumDetailGridView, false);
        ((StickyGridHeadersGridView) this.mAlbumDetailGridView).addFooterView(this.mLoadMoreLayout);
        this.mKeepClearCheckBox = onInflateView.findViewById(R.id.keep_clear_check_box);
        this.mKeepClearCheckBox.setOnPerformCheckedChangeListener(this.mKeepClearCheckListener);
        this.mKeepClearCheckBox.setEnabled(true);
        this.mKeepClearCheckBox.setChecked(true);
        this.mScanner.addListener(this.mOnScanResultUpdateListener);
        resetScanResult();
        return onInflateView;
    }

    public void onItemSelectedChanged() {
        boolean z = false;
        if (this.mAdapter.getCount() > 0) {
            Button button = this.mDeleteButton;
            if (this.mGridViewWrapper.getCheckedItemCount() > 0) {
                z = true;
            }
            button.setEnabled(z);
            return;
        }
        this.mDeleteButton.setEnabled(false);
    }

    public void onResume() {
        super.onResume();
        if (!this.mIsScrolling && this.mNeedResetLoader) {
            restartLoader();
        }
    }

    /* access modifiers changed from: protected */
    public void recordCancelSelectAllEvent() {
    }

    /* access modifiers changed from: protected */
    public void resetCheckState() {
        super.resetCheckState();
        this.mKeepClearCheckBox.setChecked(false);
    }

    /* access modifiers changed from: protected */
    public void resetScanResult() {
        super.resetScanResult();
        this.mGroupStartPos = ((SimilarScanner) this.mScanner).getGroupStartPos();
        this.mGroupItemCount = ((SimilarScanner) this.mScanner).getGroupItemCount();
        this.mClusterGroupId = ((SimilarScanner) this.mScanner).getClusterGroupId();
    }
}
