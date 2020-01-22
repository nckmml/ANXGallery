package com.miui.gallery.picker;

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
import android.widget.GridView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.picker.helper.CursorUtils;
import com.miui.gallery.picker.helper.PickableBaseTimeLineAdapterWrapper;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.Checkable;
import com.miui.gallery.util.StringUtils;
import com.miui.gallery.widget.EmptyPage;

public class PickHomePageFragment extends PickerFragment {
    private EmptyPage mEmptyView;
    private GridView mHomeGridView;
    /* access modifiers changed from: private */
    public PickableBaseTimeLineAdapterWrapper mHomePageAdapter;
    private HomePagePhotoLoaderCallback mHomePagePhotoLoaderCallback;

    private class HomePagePhotoLoaderCallback implements LoaderManager.LoaderCallbacks {
        private HomePagePhotoLoaderCallback() {
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(PickHomePageFragment.this.mActivity);
            cursorLoader.setUri(PickHomePageFragment.this.getUri());
            cursorLoader.setProjection(PickerHomePageAdapter.PROJECTION);
            cursorLoader.setSortOrder("alias_sort_time DESC ");
            cursorLoader.setSelection(PickHomePageFragment.this.getSelection());
            cursorLoader.setSelectionArgs(PickHomePageFragment.this.getSelectionArgs());
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            PickHomePageFragment.this.mHomePageAdapter.swapCursor((Cursor) obj);
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private void refreshPickState() {
        int firstVisiblePosition = this.mHomeGridView.getFirstVisiblePosition();
        for (int i = 0; i < this.mHomeGridView.getChildCount(); i++) {
            View childAt = this.mHomeGridView.getChildAt(i);
            if (childAt instanceof Checkable) {
                ((Checkable) childAt).setChecked(this.mPicker.contains(CursorUtils.getSha1((Cursor) this.mHomeGridView.getItemAtPosition(firstVisiblePosition + i))));
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getPageName() {
        return "picker_home";
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        StringBuilder sb = new StringBuilder();
        sb.append("alias_show_in_homepage");
        sb.append(" =1 ");
        if (this.mPicker.getMediaType() != Picker.MediaType.ALL) {
            sb.append(" AND ");
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
        return GalleryContract.Media.URI.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).appendQueryParameter("generate_headers", String.valueOf(true)).appendQueryParameter("remove_processing_items", String.valueOf(true)).build();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mHomePagePhotoLoaderCallback = new HomePagePhotoLoaderCallback();
        getLoaderManager().initLoader(1, (Bundle) null, this.mHomePagePhotoLoaderCallback);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int firstVisiblePosition = this.mHomeGridView.getFirstVisiblePosition();
        if (configuration.orientation == 2) {
            this.mHomeGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbColumnsLand);
        } else {
            this.mHomeGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbColumnsPortrait);
        }
        this.mHomeGridView.setSelection(firstVisiblePosition);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHomePageAdapter = new PickableBaseTimeLineAdapterWrapper(this.mPicker, new PickerHomePageAdapter(this.mActivity, SyncStateDisplay.DisplayScene.SCENE_NONE));
    }

    public void onDestroy() {
        PickableBaseTimeLineAdapterWrapper pickableBaseTimeLineAdapterWrapper = this.mHomePageAdapter;
        if (pickableBaseTimeLineAdapterWrapper != null) {
            pickableBaseTimeLineAdapterWrapper.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.picker_home_page, viewGroup, false);
        this.mHomeGridView = (GridView) inflate.findViewById(R.id.grid);
        this.mHomeGridView.setAdapter(this.mHomePageAdapter);
        this.mHomeGridView.setOnItemClickListener(getGridViewOnItemClickListener());
        this.mEmptyView = (EmptyPage) inflate.findViewById(16908292);
        this.mHomeGridView.setEmptyView(this.mEmptyView);
        this.mEmptyView.setVisibility(8);
        return inflate;
    }

    public void onPermissionsChecked() {
        this.mHomePageAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public boolean onPhotoItemClick(Cursor cursor) {
        if (this.mPicker.getMode() != Picker.Mode.SINGLE) {
            return false;
        }
        this.mPicker.pick(CursorUtils.getSha1(cursor));
        this.mPicker.done();
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean recordPageByDefault() {
        return false;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && isResumed()) {
            refreshPickState();
        }
    }

    /* access modifiers changed from: protected */
    public boolean supportFoldBurstItems() {
        return true;
    }
}
