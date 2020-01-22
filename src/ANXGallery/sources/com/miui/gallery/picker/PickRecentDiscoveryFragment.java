package com.miui.gallery.picker;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.RecentDiscoveryAdapter;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.picker.PickAlbumDetailFragmentBase;
import com.miui.gallery.picker.helper.CursorUtils;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.picker.helper.PickerItemHolder;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.Checkable;
import com.miui.gallery.ui.MicroThumbGridItem;
import com.miui.gallery.util.AlbumsCursorHelper;
import com.miui.gallery.util.StringUtils;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

public class PickRecentDiscoveryFragment extends PickAlbumDetailFragmentBase {
    /* access modifiers changed from: private */
    public PickRecentAlbumTimelineAdapter mAdapter;
    /* access modifiers changed from: private */
    public PickAlbumDetailFragmentBase.AlbumItemCheckListener mAlbumItemCheckListener;
    private RecentDiscoveryLoaderCallback mLoaderCallback;

    private class PickRecentAlbumTimelineAdapter extends RecentDiscoveryAdapter {
        public PickRecentAlbumTimelineAdapter(Context context) {
            super(context, SyncStateDisplay.DisplayScene.SCENE_NONE, true);
        }

        public void doBindData(View view, Context context, Cursor cursor) {
            super.doBindData(view, context, cursor);
            PickRecentDiscoveryFragment.this.bindCheckState(view, cursor);
            PickerItemHolder.bindView(cursor.getPosition(), view, this, PickRecentDiscoveryFragment.this.mAlbumItemCheckListener);
        }
    }

    private class RecentDiscoveryLoaderCallback implements LoaderManager.LoaderCallbacks {
        private RecentDiscoveryLoaderCallback() {
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(PickRecentDiscoveryFragment.this.mActivity);
            if (i == 1) {
                cursorLoader.setUri(PickRecentDiscoveryFragment.this.getUri());
                cursorLoader.setProjection(RecentDiscoveryAdapter.PROJECTION);
                cursorLoader.setSelection(PickRecentDiscoveryFragment.this.getSelection());
                cursorLoader.setSelectionArgs(PickRecentDiscoveryFragment.this.getSelectionArgs());
                cursorLoader.setSortOrder(PickRecentDiscoveryFragment.this.getSortOrder());
            } else if (i == 2) {
                cursorLoader.setUri(GalleryContract.Album.URI_ALL);
                cursorLoader.setProjection(AlbumsCursorHelper.ALL_ALBUMS_PROJECTION);
                cursorLoader.setSortOrder("sortBy ASC ");
            }
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            int id = loader.getId();
            if (id == 1) {
                PickRecentDiscoveryFragment.this.mAdapter.swapCursor((Cursor) obj);
                PickRecentDiscoveryFragment.this.copy2Pick();
            } else if (id == 2) {
                PickRecentDiscoveryFragment.this.mAdapter.setAllAlbums((Cursor) obj);
            }
        }

        public void onLoaderReset(Loader loader) {
            int id = loader.getId();
            if (id == 1) {
                PickRecentDiscoveryFragment.this.mAdapter.changeCursor((Cursor) null);
            } else if (id == 2) {
                PickRecentDiscoveryFragment.this.mAdapter.setAllAlbums((Cursor) null);
            }
        }
    }

    public PickRecentDiscoveryFragment() {
        super("recent");
    }

    /* access modifiers changed from: private */
    public String getSortOrder() {
        return "dateModified DESC";
    }

    private void updateConfiguration(Configuration configuration) {
        this.mAlbumDetailGridView.setNumColumns(configuration.orientation == 2 ? Config.ThumbConfig.get().sMicroThumbRecentColumnsLand : Config.ThumbConfig.get().sMicroThumbRecentColumnsPortrait);
    }

    /* access modifiers changed from: protected */
    public void bindCheckState(View view, Cursor cursor) {
        if (this.mPicker.getMode() != Picker.Mode.SINGLE) {
            Checkable checkable = (Checkable) view;
            checkable.setCheckable(true);
            checkable.setChecked(this.mPicker.contains(CursorUtils.getSha1(cursor)));
        } else if (view instanceof MicroThumbGridItem) {
            ((MicroThumbGridItem) view).setSimilarMarkEnable(true);
        }
    }

    /* access modifiers changed from: protected */
    public String getPageName() {
        return "picker_recent_album";
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        StringBuilder sb = new StringBuilder();
        if (getPicker().getMediaType() != Picker.MediaType.ALL) {
            sb.append("serverType");
            sb.append(" =? ");
        }
        if (StringUtils.isValid(getPicker().getFilterMimeTypes())) {
            sb.append(" AND ");
            sb.append(getFilterSelectionWithMimeType(getPicker().getFilterMimeTypes()));
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String[] getSelectionArgs() {
        if (getPicker().getMediaType() == Picker.MediaType.IMAGE) {
            return new String[]{String.valueOf(1)};
        } else if (getPicker().getMediaType() != Picker.MediaType.VIDEO) {
            return null;
        } else {
            return new String[]{String.valueOf(2)};
        }
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return GalleryContract.RecentDiscoveredMedia.URI.buildUpon().appendQueryParameter("generate_headers", String.valueOf(true)).appendQueryParameter("remove_processing_items", String.valueOf(true)).appendQueryParameter("remove_duplicate_items", String.valueOf(true)).build();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mLoaderCallback = new RecentDiscoveryLoaderCallback();
        getLoaderManager().initLoader(1, (Bundle) null, this.mLoaderCallback);
        getLoaderManager().initLoader(2, (Bundle) null, this.mLoaderCallback);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateConfiguration(configuration);
        this.mAlbumDetailGridView.setSelection(this.mAlbumDetailGridView.getFirstVisiblePosition());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAdapter = new PickRecentAlbumTimelineAdapter(this.mActivity);
        this.mAdapter.setShareAlbums((Cursor) null);
    }

    public void onDestroy() {
        PickRecentAlbumTimelineAdapter pickRecentAlbumTimelineAdapter = this.mAdapter;
        if (pickRecentAlbumTimelineAdapter != null) {
            pickRecentAlbumTimelineAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.album_recent, viewGroup, false);
        this.mAlbumDetailGridView = (StickyGridHeadersGridView) inflate.findViewById(R.id.grid);
        this.mAlbumDetailGridView.setHeadersIgnorePadding(true);
        this.mAlbumDetailGridView.setOnItemClickListener(getGridViewOnItemClickListener());
        this.mAlbumDetailGridView.setAdapter((ListAdapter) this.mAdapter);
        this.mAlbumItemCheckListener = new PickAlbumDetailFragmentBase.AlbumItemCheckListener(this.mPicker);
        initialSelections();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public boolean supportFoldBurstItems() {
        return this.mAdapter.supportFoldBurstItems();
    }
}
