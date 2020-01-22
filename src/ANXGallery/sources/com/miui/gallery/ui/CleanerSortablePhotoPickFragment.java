package com.miui.gallery.ui;

import android.content.CursorLoader;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumDetailTimeLineAdapter;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.widget.SortByHeader;

public abstract class CleanerSortablePhotoPickFragment extends CleanerPhotoPickFragment {
    public SortByHeader.SortBy mCurrentSortBy = SortByHeader.SortBy.DATE;
    private View.OnClickListener mOnSortByClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            Loader loader = CleanerSortablePhotoPickFragment.this.getLoaderManager().getLoader(1);
            int id = view.getId();
            if (id == R.id.datetime_container) {
                CleanerSortablePhotoPickFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.DATE);
                GallerySamplingStatHelper.recordCountEvent("cleaner", "video_sort_by_date");
            } else if (id == R.id.size_container) {
                GallerySamplingStatHelper.recordCountEvent("cleaner", "video_sort_by_size");
                CleanerSortablePhotoPickFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.SIZE);
            }
            loader.forceLoad();
            CleanerSortablePhotoPickFragment.this.mSortByHeader.updateCurrentSortView(CleanerSortablePhotoPickFragment.this.mCurrentSortBy, CleanerSortablePhotoPickFragment.this.getSortByIndicatorResource());
        }
    };
    public SortByHeader mSortByHeader;
    public String mSortOrder = " DESC ";

    /* renamed from: com.miui.gallery.ui.CleanerSortablePhotoPickFragment$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy = new int[SortByHeader.SortBy.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[SortByHeader.SortBy.NAME.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[SortByHeader.SortBy.SIZE.ordinal()] = 2;
        }
    }

    CleanerSortablePhotoPickFragment(int i) {
        super(i);
    }

    /* access modifiers changed from: private */
    public void configLoader(CursorLoader cursorLoader, SortByHeader.SortBy sortBy) {
        cursorLoader.setUri(getUri(sortBy));
        cursorLoader.setProjection(AlbumDetailTimeLineAdapter.PROJECTION);
        cursorLoader.setSelection(getSelection());
        cursorLoader.setSelectionArgs(getSelectionArgs());
        cursorLoader.setSortOrder(configOrderBy(sortBy));
    }

    private String configOrderBy(SortByHeader.SortBy sortBy) {
        String sortByString = getSortByString(sortBy);
        String str = " DESC ";
        if (this.mCurrentSortBy == sortBy) {
            if (TextUtils.equals(this.mSortOrder, str)) {
                str = " ASC ";
            }
            this.mSortOrder = str;
        } else {
            this.mSortOrder = str;
            this.mCurrentSortBy = sortBy;
        }
        String str2 = sortByString + this.mSortOrder;
        onSortByChanged();
        return str2;
    }

    /* access modifiers changed from: private */
    public int getSortByIndicatorResource() {
        return TextUtils.equals(this.mSortOrder, " ASC ") ? R.drawable.sort_by_item_arrow_up : R.drawable.sort_by_item_arrow_down;
    }

    private String getSortByString(SortByHeader.SortBy sortBy) {
        int i = AnonymousClass2.$SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[sortBy.ordinal()];
        return i != 1 ? i != 2 ? "alias_sort_time" : "size" : "title";
    }

    private void onSortByChanged() {
        getAdapter().setCurrentSortBy(this.mCurrentSortBy);
        this.mGridViewWrapper.clearChoices();
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return getUri(this.mCurrentSortBy);
    }

    /* access modifiers changed from: protected */
    public Uri getUri(SortByHeader.SortBy sortBy) {
        Uri build = GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().build();
        return sortBy == SortByHeader.SortBy.DATE ? build.buildUpon().appendQueryParameter("generate_headers", String.valueOf(true)).build() : build;
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        this.mSortByHeader = (SortByHeader) onInflateView.findViewById(R.id.sortby_header);
        this.mSortByHeader.setOnSortByClickListener(this.mOnSortByClickListener);
        this.mSortByHeader.updateCurrentSortView(this.mCurrentSortBy, getSortByIndicatorResource());
        return onInflateView;
    }
}
