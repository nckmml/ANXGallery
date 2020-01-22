package com.miui.gallery.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumDetailSimpleAdapter;
import com.miui.gallery.adapter.CleanerPhotoPickBaseAdapter;
import com.miui.gallery.cleaner.BaseScanner;
import com.miui.gallery.cleaner.ScannerManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.SoundUtils;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.SortByHeader;
import com.miui.gallery.widget.editwrapper.EditableListViewWrapperDeprecated;
import com.miui.gallery.widget.editwrapper.SimpleMultiChoiceModeListener;
import java.util.HashMap;
import java.util.List;
import miui.gallery.support.MiuiSdkCompat;

public abstract class CleanerPhotoPickFragment extends PhotoListFragmentBase {
    public static final int[] DELETE_COUNT_STAGE = {20, 50, 100, 200, 500, 1000};
    public CleanerPhotoPickBaseAdapter mAdapter;
    public DataSetObserver mDataSetObserver = new DataSetObserver() {
        public void onChanged() {
            if (CleanerPhotoPickFragment.this.mIsFirstLoadFinish && CleanerPhotoPickFragment.this.mAdapter.getCount() > 0) {
                CleanerPhotoPickFragment cleanerPhotoPickFragment = CleanerPhotoPickFragment.this;
                cleanerPhotoPickFragment.mIsFirstLoadFinish = false;
                cleanerPhotoPickFragment.mGridViewWrapper.setAllItemsCheckState(true);
            }
            CleanerPhotoPickFragment.this.onItemSelectedChanged();
        }
    };
    public Button mDeleteButton;
    public View.OnClickListener mDeleteButtonClickListener = new View.OnClickListener() {
        /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
            r10 = r9.this$0.getDeleteMessage();
         */
        public void onClick(View view) {
            final DeleteMessage deleteMessage;
            long[] checkedItemIds = CleanerPhotoPickFragment.this.mGridViewWrapper.getCheckedItemIds();
            if (checkedItemIds != null && checkedItemIds.length > 0 && deleteMessage != null) {
                MediaAndAlbumOperations.delete(CleanerPhotoPickFragment.this.mActivity, "CleanerPhotoPickFragmentDeleteMediaDialogFragment", new DeletionTask.OnDeletionCompleteListener() {
                    /* JADX WARNING: type inference failed for: r0v5, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                    /* JADX WARNING: type inference failed for: r0v14, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                    public void onDeleted(int i, long[] jArr) {
                        if (CleanerPhotoPickFragment.this.mActivity != null) {
                            ToastUtils.makeText((Context) CleanerPhotoPickFragment.this.mActivity, (CharSequence) CleanerPhotoPickFragment.this.getResources().getQuantityString(R.plurals.delete_finish_format, i, new Object[]{Integer.valueOf(i)}));
                            if (i > 0) {
                                SoundUtils.playSoundForOperation(CleanerPhotoPickFragment.this.mActivity, 0);
                            }
                            CleanerPhotoPickFragment.this.mScanner.removeItems(jArr);
                            CleanerPhotoPickFragment.this.resetScanResult();
                            CleanerPhotoPickFragment.this.resetCheckState();
                            if (CleanerPhotoPickFragment.this.mScanResultIds.size() <= 0 && !CleanerPhotoPickFragment.this.mScanner.isLoadingValid()) {
                                CleanerPhotoPickFragment.this.mActivity.finish();
                            }
                            HashMap hashMap = new HashMap();
                            hashMap.put("count", GallerySamplingStatHelper.formatValueStage((float) i, CleanerPhotoPickFragment.DELETE_COUNT_STAGE));
                            GallerySamplingStatHelper.recordCountEvent("cleaner", deleteMessage.getCleanerSubEvent(), hashMap);
                        }
                    }
                }, -1, "", deleteMessage.getDupType(), deleteMessage.getReason(), checkedItemIds);
            }
        }
    };
    public EditableListViewWrapperDeprecated mGridViewWrapper;
    public boolean mIsFirstLoadFinish = true;
    public SimpleMultiChoiceModeListener mMultiChoiceModeListener = new SimpleMultiChoiceModeListener() {
        public void onAllItemsCheckedStateChanged(ActionMode actionMode, boolean z) {
            CleanerPhotoPickFragment.this.onItemSelectedChanged();
        }

        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
            CleanerPhotoPickFragment.this.onItemSelectedChanged();
        }
    };
    public List<Long> mScanResultIds;
    public BaseScanner mScanner;
    public Button mSelectButton;
    public View.OnClickListener mSelectListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean z = !CleanerPhotoPickFragment.this.mGridViewWrapper.isAllItemsChecked();
            CleanerPhotoPickFragment.this.mGridViewWrapper.setAllItemsCheckState(z);
            if (!z) {
                CleanerPhotoPickFragment.this.recordCancelSelectAllEvent();
            }
        }
    };
    public int mType;

    public static class DeleteMessage {
        private String mCleanerSubEvent;
        private int mDupType;
        private int mReason;

        public static class Builder {
            private String mCleanerSubEvent;
            private int mDupType = -1;
            private int mReason = -1;

            public DeleteMessage build() {
                int i;
                int i2;
                String str = this.mCleanerSubEvent;
                if (str != null && (i = this.mReason) != -1 && (i2 = this.mDupType) != -1) {
                    return new DeleteMessage(str, i, i2);
                }
                throw new IllegalArgumentException();
            }

            public Builder setCleanerSubEvent(String str) {
                this.mCleanerSubEvent = str;
                return this;
            }

            public Builder setDupType(int i) {
                this.mDupType = i;
                return this;
            }

            public Builder setReason(int i) {
                this.mReason = i;
                return this;
            }
        }

        private DeleteMessage(String str, int i, int i2) {
            this.mCleanerSubEvent = str;
            this.mReason = i;
            this.mDupType = i2;
        }

        public String getCleanerSubEvent() {
            return this.mCleanerSubEvent;
        }

        public int getDupType() {
            return this.mDupType;
        }

        public int getReason() {
            return this.mReason;
        }
    }

    CleanerPhotoPickFragment(int i) {
        this.mType = i;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public CleanerPhotoPickBaseAdapter createAdapter() {
        return new CleanerPhotoPickBaseAdapter(this.mActivity);
    }

    /* access modifiers changed from: protected */
    public AlbumDetailSimpleAdapter getAdapter() {
        return this.mAdapter;
    }

    /* access modifiers changed from: protected */
    public DeleteMessage getDeleteMessage() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        return String.format("%s IN (%s)", new Object[]{"_id", TextUtils.join(",", this.mScanResultIds)});
    }

    /* access modifiers changed from: protected */
    public String[] getSelectionArgs() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().appendQueryParameter("generate_headers", String.valueOf(true)).build();
    }

    /* JADX WARNING: type inference failed for: r3v21, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        this.mAdapter = createAdapter();
        this.mAdapter.setCurrentSortBy(SortByHeader.SortBy.DATE);
        this.mAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.NORMAL);
        this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
        this.mGridViewWrapper = new EditableListViewWrapperDeprecated(this.mAlbumDetailGridView);
        this.mGridViewWrapper.setAdapter(this.mAdapter);
        this.mGridViewWrapper.setEmptyView(this.mEmptyView);
        this.mGridViewWrapper.setOnItemClickListener(getGridViewOnItemClickListener());
        this.mGridViewWrapper.setMultiChoiceModeListener(this.mMultiChoiceModeListener);
        this.mGridViewWrapper.disableScaleImageViewAniWhenInActionMode();
        this.mGridViewWrapper.startChoiceMode();
        this.mDeleteButton = (Button) onInflateView.findViewById(R.id.delete);
        this.mDeleteButton.setOnClickListener(this.mDeleteButtonClickListener);
        this.mSelectButton = (Button) this.mActivity.getActionBar().getCustomView().findViewById(R.id.do_select);
        MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mSelectButton, 0);
        this.mSelectButton.setOnClickListener(this.mSelectListener);
        this.mScanner = ScannerManager.getInstance().getScanner(this.mType);
        resetScanResult();
        return onInflateView;
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onItemSelectedChanged() {
        if (this.mAdapter.getCount() > 0) {
            this.mDeleteButton.setEnabled(this.mGridViewWrapper.getCheckedItemCount() > 0);
            this.mSelectButton.setVisibility(0);
            MiuiSdkCompat.setEditActionModeButton(this.mActivity, this.mSelectButton, this.mGridViewWrapper.isAllItemsChecked() ? 1 : 0);
            return;
        }
        this.mDeleteButton.setEnabled(false);
        this.mSelectButton.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public abstract void recordCancelSelectAllEvent();

    /* access modifiers changed from: protected */
    public void resetCheckState() {
        this.mGridViewWrapper.setAllItemsCheckState(false);
    }

    /* access modifiers changed from: protected */
    public void resetScanResult() {
        this.mScanResultIds = this.mScanner.getScanResultIds();
    }
}
