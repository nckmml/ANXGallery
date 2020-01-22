package com.miui.gallery.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumDetailSimpleAdapter;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;

public abstract class PhotoListFragmentBase extends BaseMediaFragment {
    protected GridView mAlbumDetailGridView;
    protected LoaderManager.LoaderCallbacks mAlbumDetailLoaderCallback;
    protected long mAlbumId = -1;
    protected String mAlbumName;
    protected View mEmptyView;

    private class PhotoListLoaderCallback implements LoaderManager.LoaderCallbacks {
        private PhotoListLoaderCallback() {
        }

        /* JADX WARNING: type inference failed for: r2v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(PhotoListFragmentBase.this.mActivity);
            PhotoListFragmentBase.this.configLoader(cursorLoader);
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            Cursor cursor = (Cursor) obj;
            PhotoListFragmentBase.this.mayDoAdditionalWork(cursor);
            PhotoListFragmentBase.this.getAdapter().swapCursor(cursor);
            if (PhotoListFragmentBase.this.getAdapter().getCount() == 0) {
                PhotoListFragmentBase.this.setEmptyViewVisibility(0);
            } else {
                PhotoListFragmentBase.this.setEmptyViewVisibility(8);
            }
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    /* access modifiers changed from: protected */
    public void configLoader(CursorLoader cursorLoader) {
        cursorLoader.setUri(getUri());
        cursorLoader.setProjection(getProjection());
        cursorLoader.setSelection(getSelection());
        cursorLoader.setSelectionArgs(getSelectionArgs());
        cursorLoader.setSortOrder(getCurrentSortOrder());
    }

    /* access modifiers changed from: protected */
    public abstract AlbumDetailSimpleAdapter getAdapter();

    /* access modifiers changed from: protected */
    public String getCurrentSortOrder() {
        return "alias_sort_time DESC ";
    }

    /* access modifiers changed from: protected */
    public AdapterView.OnItemClickListener getGridViewOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i;
                AlbumDetailSimpleAdapter viewAdapter = PhotoListFragmentBase.this.getViewAdapter();
                ImageLoadParams imageLoadParams = new ImageLoadParams(viewAdapter.getItemKey(i2), viewAdapter.getLocalPath(i2), Config.ThumbConfig.get().sMicroTargetSize, viewAdapter.getItemDecodeRectF(i2), i, viewAdapter.getMimeType(i2), viewAdapter.getItemSecretKey(i2), viewAdapter.getFileLength(i2));
                PhotoListFragmentBase photoListFragmentBase = PhotoListFragmentBase.this;
                IntentUtil.gotoPhotoPage((BaseMediaFragment) photoListFragmentBase, (ViewGroup) adapterView, photoListFragmentBase.getUri(), i, viewAdapter.getCount(), PhotoListFragmentBase.this.getSelection(), PhotoListFragmentBase.this.getSelectionArgs(), PhotoListFragmentBase.this.getCurrentSortOrder(), imageLoadParams, PhotoListFragmentBase.this.mAlbumId, PhotoListFragmentBase.this.mAlbumName, PhotoListFragmentBase.this.getSupportOperationMask(), !PhotoListFragmentBase.this.getAdapter().supportFoldBurstItems(), PhotoListFragmentBase.this.isPreviewMode());
                GallerySamplingStatHelper.recordNumericPropertyEvent(PhotoListFragmentBase.this.getPageName(), "click_micro_thumb", (long) i);
            }
        };
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutSource();

    /* access modifiers changed from: protected */
    public Loader getLoader() {
        return getLoaderManager().getLoader(1);
    }

    /* access modifiers changed from: protected */
    public LoaderManager.LoaderCallbacks getLoaderCallback() {
        if (this.mAlbumDetailLoaderCallback == null) {
            this.mAlbumDetailLoaderCallback = new PhotoListLoaderCallback();
        }
        return this.mAlbumDetailLoaderCallback;
    }

    /* access modifiers changed from: protected */
    public String[] getProjection() {
        return AlbumDetailSimpleAdapter.PROJECTION;
    }

    /* access modifiers changed from: protected */
    public abstract String getSelection();

    /* access modifiers changed from: protected */
    public abstract String[] getSelectionArgs();

    /* access modifiers changed from: protected */
    public int getSupportOperationMask() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public abstract Uri getUri();

    /* access modifiers changed from: protected */
    public AlbumDetailSimpleAdapter getViewAdapter() {
        return getAdapter();
    }

    public boolean isPreviewMode() {
        return getActivity() != null && getActivity().getIntent().getBooleanExtra("photo_preview_mode", false);
    }

    /* access modifiers changed from: protected */
    public void mayDoAdditionalWork(Cursor cursor) {
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getLoaderManager().initLoader(1, (Bundle) null, getLoaderCallback());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int firstVisiblePosition = this.mAlbumDetailGridView.getFirstVisiblePosition();
        if (configuration.orientation == 2) {
            this.mAlbumDetailGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbColumnsLand);
        } else {
            this.mAlbumDetailGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbColumnsPortrait);
        }
        this.mAlbumDetailGridView.setSelection(firstVisiblePosition);
    }

    public void onDestroy() {
        AlbumDetailSimpleAdapter viewAdapter = getViewAdapter();
        if (viewAdapter != null) {
            viewAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutSource(), viewGroup, false);
        this.mAlbumDetailGridView = (GridView) inflate.findViewById(R.id.grid);
        this.mEmptyView = inflate.findViewById(16908292);
        setEmptyViewVisibility(8);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        getAdapter().updateGalleryCloudSyncableState();
    }

    /* access modifiers changed from: protected */
    public void setEmptyViewVisibility(int i) {
        View view = this.mEmptyView;
        if (view != null) {
            view.setVisibility(i);
        }
    }
}
