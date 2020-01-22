package com.miui.gallery.picker;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumDetailSimpleAdapter;
import com.miui.gallery.adapter.AlbumDetailTimeLineAdapter;
import com.miui.gallery.adapter.SyncStateDisplay;
import com.miui.gallery.picker.PickAlbumDetailFragmentBase;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.picker.helper.PickerItemHolder;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.util.StringUtils;
import com.miui.gallery.widget.SortByHeader;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

public class PickAlbumDetailFragment extends PickAlbumDetailFragmentBase {
    private static final String SELECTION_ONLY_LOCAL = (" AND " + InternalContract.Cloud.ALIAS_LOCAL_MEDIA);
    private AlbumDetailLoaderCallback mAlbumDetailLoaderCallback;
    /* access modifiers changed from: private */
    public PickAlbumDetailSimpleAdapter mAlbumDetailSimpleAdapter;
    /* access modifiers changed from: private */
    public PickAlbumDetailTimeLineAdapter mAlbumDetailTimeLineAdapter;
    private long mAlbumId;
    /* access modifiers changed from: private */
    public PickAlbumDetailFragmentBase.AlbumItemCheckListener mAlbumItemCheckListener;
    /* access modifiers changed from: private */
    public SortByHeader.SortBy mCurrentSortBy = SortByHeader.SortBy.NONE;
    private boolean mIsOtherSharedAlbum;
    private boolean mIsPanoAlbum;
    private OnSortByClickListener mOnSortByClickListener;
    /* access modifiers changed from: private */
    public SortByHeader mSortByHeader;
    private String mSortOrder = " DESC ";

    /* renamed from: com.miui.gallery.picker.PickAlbumDetailFragment$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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

    private class AlbumDetailLoaderCallback implements LoaderManager.LoaderCallbacks {
        private AlbumDetailLoaderCallback() {
        }

        /* synthetic */ AlbumDetailLoaderCallback(PickAlbumDetailFragment pickAlbumDetailFragment, AnonymousClass1 r2) {
            this();
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(PickAlbumDetailFragment.this.mActivity);
            PickAlbumDetailFragment.this.configLoader(cursorLoader, SortByHeader.SortBy.DATE);
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            if (PickAlbumDetailFragment.this.mCurrentSortBy == SortByHeader.SortBy.DATE) {
                PickAlbumDetailFragment.this.mAlbumDetailGridView.setAdapter((ListAdapter) PickAlbumDetailFragment.this.mAlbumDetailTimeLineAdapter);
                PickAlbumDetailFragment.this.mAlbumDetailTimeLineAdapter.swapCursor((Cursor) obj);
            } else {
                PickAlbumDetailFragment.this.mAlbumDetailGridView.setAdapter((ListAdapter) PickAlbumDetailFragment.this.mAlbumDetailSimpleAdapter);
                PickAlbumDetailFragment.this.mAlbumDetailSimpleAdapter.swapCursor((Cursor) obj);
            }
            PickAlbumDetailFragment.this.mSortByHeader.updateCurrentSortView(PickAlbumDetailFragment.this.mCurrentSortBy, PickAlbumDetailFragment.this.getSortByIndicatorResource());
            PickAlbumDetailFragment.this.copy2Pick();
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private class OnSortByClickListener implements View.OnClickListener {
        private OnSortByClickListener() {
        }

        /* synthetic */ OnSortByClickListener(PickAlbumDetailFragment pickAlbumDetailFragment, AnonymousClass1 r2) {
            this();
        }

        public void onClick(View view) {
            Loader loader = PickAlbumDetailFragment.this.getLoaderManager().getLoader(1);
            int id = view.getId();
            if (id == R.id.datetime_container) {
                PickAlbumDetailFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.DATE);
            } else if (id == R.id.name_container) {
                PickAlbumDetailFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.NAME);
            } else if (id == R.id.size_container) {
                PickAlbumDetailFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.SIZE);
            }
            loader.forceLoad();
        }
    }

    private class PickAlbumDetailSimpleAdapter extends AlbumDetailSimpleAdapter {
        public PickAlbumDetailSimpleAdapter(Context context) {
            super(context, SyncStateDisplay.DisplayScene.SCENE_NONE);
        }

        public void doBindData(View view, Context context, Cursor cursor) {
            super.doBindData(view, context, cursor);
            PickAlbumDetailFragment.this.bindCheckState(view, cursor);
            PickerItemHolder.bindView(cursor.getPosition(), view, this, PickAlbumDetailFragment.this.mAlbumItemCheckListener);
        }
    }

    private class PickAlbumDetailTimeLineAdapter extends AlbumDetailTimeLineAdapter {
        public PickAlbumDetailTimeLineAdapter(Context context) {
            super(context, SyncStateDisplay.DisplayScene.SCENE_NONE);
        }

        public void doBindData(View view, Context context, Cursor cursor) {
            super.doBindData(view, context, cursor);
            PickAlbumDetailFragment.this.bindCheckState(view, cursor);
            PickerItemHolder.bindView(cursor.getPosition(), view, this, PickAlbumDetailFragment.this.mAlbumItemCheckListener);
        }
    }

    public PickAlbumDetailFragment() {
        super("album");
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
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[sortBy.ordinal()];
        return i != 1 ? i != 2 ? "alias_sort_time" : "size" : "title";
    }

    private Uri getUri(SortByHeader.SortBy sortBy) {
        return this.mIsOtherSharedAlbum ? ContentUris.withAppendedId(GalleryContract.Media.URI_OTHER_ALBUM_MEDIA.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).build(), this.mAlbumId) : sortBy == SortByHeader.SortBy.DATE ? GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().appendQueryParameter("generate_headers", String.valueOf(true)).appendQueryParameter("remove_duplicate_items", String.valueOf(true)).appendQueryParameter("remove_processing_items", String.valueOf(true)).build() : GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).appendQueryParameter("remove_processing_items", String.valueOf(true)).build();
    }

    private boolean isOnlyShowLocal() {
        return GalleryPreferences.LocalMode.isOnlyShowLocalPhoto();
    }

    private void onSortByChanged() {
        this.mAlbumDetailSimpleAdapter.setCurrentSortBy(this.mCurrentSortBy);
    }

    /* access modifiers changed from: protected */
    public String getPageName() {
        return "picker_album_detail";
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        if (this.mIsOtherSharedAlbum) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        long j = this.mAlbumId;
        if (j == 2147483647L) {
            sb.append("serverType");
            sb.append(" = ? AND ");
            sb.append("alias_hidden");
            sb.append(" = ?");
        } else if (this.mIsPanoAlbum) {
            sb.append("title");
            sb.append(" LIKE ?  AND ");
            sb.append("alias_hidden");
            sb.append(" = ?");
        } else if (j == 2147483642) {
            sb.append("alias_is_favorite");
            sb.append(" = ? AND ");
            sb.append("alias_hidden");
            sb.append(" = ? AND ");
            sb.append("localGroupId");
            sb.append(" != ?");
            if (getPicker().getMediaType() != Picker.MediaType.ALL) {
                sb.append(" AND ");
                sb.append("serverType");
                sb.append(" = ?");
            }
        } else {
            sb.append("localGroupId = ? ");
            if (getPicker().getMediaType() != Picker.MediaType.ALL) {
                sb.append(" AND ");
                sb.append("serverType =? ");
                if (StringUtils.isValid(getPicker().getFilterMimeTypes())) {
                    sb.append(" AND ");
                    sb.append(getFilterSelectionWithMimeType(getPicker().getFilterMimeTypes()));
                }
            }
        }
        if (isOnlyShowLocal()) {
            sb.append(SELECTION_ONLY_LOCAL);
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String[] getSelectionArgs() {
        long j = this.mAlbumId;
        if (j == 2147483647L) {
            return new String[]{String.valueOf(2), String.valueOf(0)};
        } else if (this.mIsPanoAlbum) {
            return new String[]{"PANO_.*", String.valueOf(0)};
        } else if (this.mIsOtherSharedAlbum) {
            return null;
        } else {
            if (j == 2147483642) {
                Picker.MediaType mediaType = getPicker().getMediaType();
                if (mediaType == Picker.MediaType.ALL) {
                    return new String[]{String.valueOf(1), String.valueOf(0), String.valueOf(-1000)};
                }
                String[] strArr = new String[4];
                strArr[0] = String.valueOf(1);
                strArr[1] = String.valueOf(0);
                strArr[2] = String.valueOf(-1000);
                strArr[3] = mediaType == Picker.MediaType.IMAGE ? String.valueOf(1) : String.valueOf(2);
                return strArr;
            } else if (getPicker().getMediaType() == Picker.MediaType.IMAGE) {
                return new String[]{String.valueOf(this.mAlbumId), String.valueOf(1)};
            } else if (getPicker().getMediaType() == Picker.MediaType.VIDEO) {
                return new String[]{String.valueOf(this.mAlbumId), String.valueOf(2)};
            } else {
                return new String[]{String.valueOf(this.mAlbumId)};
            }
        }
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return getUri(SortByHeader.SortBy.NONE);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mAlbumDetailLoaderCallback = new AlbumDetailLoaderCallback(this, (AnonymousClass1) null);
        getLoaderManager().initLoader(1, (Bundle) null, this.mAlbumDetailLoaderCallback);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlbumDetailTimeLineAdapter = new PickAlbumDetailTimeLineAdapter(this.mActivity);
        this.mAlbumDetailSimpleAdapter = new PickAlbumDetailSimpleAdapter(this.mActivity);
    }

    public void onDestroy() {
        PickAlbumDetailSimpleAdapter pickAlbumDetailSimpleAdapter = this.mAlbumDetailSimpleAdapter;
        if (pickAlbumDetailSimpleAdapter != null) {
            pickAlbumDetailSimpleAdapter.swapCursor((Cursor) null);
        }
        PickAlbumDetailTimeLineAdapter pickAlbumDetailTimeLineAdapter = this.mAlbumDetailTimeLineAdapter;
        if (pickAlbumDetailTimeLineAdapter != null) {
            pickAlbumDetailTimeLineAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.album_detail, viewGroup, false);
        this.mAlbumDetailGridView = (StickyGridHeadersGridView) inflate.findViewById(R.id.grid);
        this.mAlbumDetailGridView.setOnItemClickListener(getGridViewOnItemClickListener());
        this.mAlbumItemCheckListener = new PickAlbumDetailFragmentBase.AlbumItemCheckListener(this.mPicker);
        this.mOnSortByClickListener = new OnSortByClickListener(this, (AnonymousClass1) null);
        this.mSortByHeader = (SortByHeader) inflate.findViewById(R.id.sortby_header);
        this.mSortByHeader.setOnSortByClickListener(this.mOnSortByClickListener);
        initialSelections();
        return inflate;
    }

    public void setAlbumId(long j) {
        this.mAlbumId = j;
    }

    public void setIsOtherSharedAlbum(boolean z) {
        this.mIsOtherSharedAlbum = z;
    }

    public void setIsPanoAlbum(boolean z) {
        this.mIsPanoAlbum = z;
    }

    /* access modifiers changed from: protected */
    public boolean supportFoldBurstItems() {
        PickAlbumDetailSimpleAdapter pickAlbumDetailSimpleAdapter = this.mAlbumDetailSimpleAdapter;
        if (pickAlbumDetailSimpleAdapter != null) {
            return pickAlbumDetailSimpleAdapter.supportFoldBurstItems();
        }
        PickAlbumDetailTimeLineAdapter pickAlbumDetailTimeLineAdapter = this.mAlbumDetailTimeLineAdapter;
        if (pickAlbumDetailTimeLineAdapter == null) {
            return false;
        }
        pickAlbumDetailTimeLineAdapter.supportFoldBurstItems();
        return false;
    }
}
