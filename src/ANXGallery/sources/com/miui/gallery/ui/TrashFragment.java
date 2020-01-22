package com.miui.gallery.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.miui.gallery.R;
import com.miui.gallery.adapter.TrashAdapter;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.widget.HeaderGridView;

public class TrashFragment extends BaseFragment {
    private HeaderGridView mGridView;
    /* access modifiers changed from: private */
    public TrashAdapter mTrashAdapter;
    private TrashedPhotoLoaderCallback mTrashedPhotoLoaderCallback;

    private class TrashedPhotoLoaderCallback implements LoaderManager.LoaderCallbacks {
        private TrashedPhotoLoaderCallback() {
        }

        /* JADX WARNING: type inference failed for: r4v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(TrashFragment.this.mActivity);
            cursorLoader.setUri(GalleryContract.Media.URI.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).build());
            cursorLoader.setProjection(TrashAdapter.PROJECTION);
            cursorLoader.setSortOrder("alias_create_time DESC ");
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            TrashFragment.this.mTrashAdapter.swapCursor((Cursor) obj);
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    public String getPageName() {
        return "trash";
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mTrashedPhotoLoaderCallback = new TrashedPhotoLoaderCallback();
        getLoaderManager().initLoader(1, (Bundle) null, this.mTrashedPhotoLoaderCallback);
    }

    public void onDestroy() {
        TrashAdapter trashAdapter = this.mTrashAdapter;
        if (trashAdapter != null) {
            trashAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.trash, viewGroup, false);
        this.mGridView = (HeaderGridView) inflate.findViewById(R.id.trash_grid);
        this.mTrashAdapter = new TrashAdapter(this.mActivity);
        this.mGridView.setAdapter((ListAdapter) this.mTrashAdapter);
        return inflate;
    }
}
