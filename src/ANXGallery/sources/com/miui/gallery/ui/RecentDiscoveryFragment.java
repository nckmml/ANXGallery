package com.miui.gallery.ui;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.CheckableAdapter;
import com.miui.gallery.adapter.RecentDiscoveryAdapter;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.ui.RecentDiscoveryFragment;
import com.miui.gallery.util.AlbumsCursorHelper;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.ShareAlbumsCursorHelper;
import com.miui.gallery.util.SoundUtils;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.editwrapper.EditableListViewWrapperDeprecated;
import com.miui.gallery.widget.editwrapper.MultiChoiceModeListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RecentDiscoveryFragment extends BaseMediaFragment {
    protected long mAlbumId;
    protected String mAlbumName;
    private MultiChoiceModeListener mChoiceModeListener = new MultiChoiceModeListener() {
        private MenuItem mAddToAlbum;
        private MenuItem mCreativity;
        private MenuItem mDelete;
        private ActionMode mMode;
        private MenuItem mSend;

        private boolean isMenuItemVisible(MenuItem menuItem) {
            return menuItem != null && menuItem.isVisible();
        }

        private void updateMenuState() {
            if (RecentDiscoveryFragment.this.mGridViewWrapper.getCheckedItemCount() < 1) {
                if (isMenuItemVisible(this.mDelete)) {
                    this.mDelete.setEnabled(false);
                }
                if (isMenuItemVisible(this.mAddToAlbum)) {
                    this.mAddToAlbum.setEnabled(false);
                }
                if (isMenuItemVisible(this.mCreativity)) {
                    this.mCreativity.setEnabled(false);
                }
                if (isMenuItemVisible(this.mSend)) {
                    this.mSend.setEnabled(false);
                    return;
                }
                return;
            }
            if (isMenuItemVisible(this.mDelete)) {
                this.mDelete.setEnabled(true);
            }
            if (isMenuItemVisible(this.mAddToAlbum)) {
                this.mAddToAlbum.setEnabled(true);
            }
            if (isMenuItemVisible(this.mCreativity)) {
                this.mCreativity.setEnabled(true);
            }
            if (isMenuItemVisible(this.mSend)) {
                this.mSend.setEnabled(true);
            }
        }

        public /* synthetic */ void lambda$onActionItemClicked$24$RecentDiscoveryFragment$2(long[] jArr, boolean z) {
            this.mMode.finish();
        }

        /* JADX WARNING: type inference failed for: r2v4, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r5v11, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            final ActionMode actionMode2 = actionMode;
            switch (menuItem.getItemId()) {
                case R.id.action_produce:
                    MediaAndAlbumOperations.doProduceCreation(RecentDiscoveryFragment.this.mActivity, new MediaAndAlbumOperations.OnCompleteListener() {
                        public void onComplete(long[] jArr) {
                            List<CheckableAdapter.CheckedItem> checkedItems = RecentDiscoveryFragment.this.mGridViewWrapper.getCheckedItems();
                            int i = 0;
                            for (int i2 = 0; i2 < checkedItems.size(); i2++) {
                                if (ImageFeatureCacheManager.getInstance().isBestImage(checkedItems.get(i2).getId(), false, false, (List<Long>) null)) {
                                    i++;
                                }
                            }
                            HashMap hashMap = new HashMap();
                            hashMap.put("count", Integer.valueOf(checkedItems.size()));
                            hashMap.put("best_image_count", Integer.valueOf(i));
                            GallerySamplingStatHelper.recordCountEvent("recent_album", "produce", hashMap);
                            actionMode2.finish();
                        }
                    }, RecentDiscoveryFragment.this.mGridViewWrapper.getCheckedItems());
                    break;
                case R.id.action_send:
                    SparseBooleanArray checkedItemPositions = RecentDiscoveryFragment.this.mGridViewWrapper.getCheckedItemPositions();
                    ArrayList arrayList = new ArrayList(checkedItemPositions.size());
                    ArrayList arrayList2 = new ArrayList(checkedItemPositions.size());
                    int i = Integer.MAX_VALUE;
                    for (int i2 = 0; i2 < checkedItemPositions.size(); i2++) {
                        int keyAt = checkedItemPositions.keyAt(i2);
                        if (checkedItemPositions.get(keyAt)) {
                            arrayList.add(Integer.valueOf(keyAt));
                            arrayList2.add(Long.valueOf(RecentDiscoveryFragment.this.getAdapter().getItemKey(keyAt)));
                            if (keyAt < i) {
                                i = keyAt;
                            }
                        }
                    }
                    int[] iArr = new int[arrayList.size()];
                    long[] jArr = new long[arrayList2.size()];
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
                        jArr[i3] = ((Long) arrayList2.get(i3)).longValue();
                    }
                    if (i == Integer.MAX_VALUE) {
                        i = 0;
                    }
                    if (iArr.length > 0 && RecentDiscoveryFragment.this.getAdapter().getCount() > 0) {
                        ImageLoadParams imageLoadParams = new ImageLoadParams(RecentDiscoveryFragment.this.getAdapter().getItemKey(i), RecentDiscoveryFragment.this.getAdapter().getLocalPath(i), Config.ThumbConfig.get().sMicroRecentTargetSize, RecentDiscoveryFragment.this.getAdapter().getItemDecodeRectF(i), i, RecentDiscoveryFragment.this.getAdapter().getMimeType(i), RecentDiscoveryFragment.this.getAdapter().getItemSecretKey(i), RecentDiscoveryFragment.this.getAdapter().getFileLength(i));
                        RecentDiscoveryFragment recentDiscoveryFragment = RecentDiscoveryFragment.this;
                        IntentUtil.gotoPreviewSelectPage(recentDiscoveryFragment, recentDiscoveryFragment.getUri(), i, RecentDiscoveryFragment.this.getAdapter().getCount(), RecentDiscoveryFragment.this.getSelection(), RecentDiscoveryFragment.this.getSelectionArgs(), RecentDiscoveryFragment.this.getSortOrder(), imageLoadParams, jArr, iArr);
                    }
                    actionMode.finish();
                    GallerySamplingStatHelper.recordCountEvent("recent_album", "send");
                    break;
                case R.id.add_to_album:
                    MediaAndAlbumOperations.addToAlbum(RecentDiscoveryFragment.this.mActivity, new MediaAndAlbumOperations.OnAddAlbumListener() {
                        public final void onComplete(long[] jArr, boolean z) {
                            RecentDiscoveryFragment.AnonymousClass2.this.lambda$onActionItemClicked$24$RecentDiscoveryFragment$2(jArr, z);
                        }
                    }, 0, true, true, RecentDiscoveryFragment.this.mGridViewWrapper.isCheckedItemContainVideo(), RecentDiscoveryFragment.this.mGridViewWrapper.getCheckedItemIds());
                    GallerySamplingStatHelper.recordCountEvent("recent_album", "add_to_album");
                    break;
                case R.id.delete:
                    RecentDiscoveryFragment.this.doDelete(actionMode2);
                    GallerySamplingStatHelper.recordCountEvent("recent_album", "delete_photo");
                    break;
                default:
                    return false;
            }
            SparseBooleanArray checkedItemPositions2 = RecentDiscoveryFragment.this.mGridViewWrapper.getCheckedItemPositions();
            ArrayList arrayList3 = new ArrayList();
            for (int i4 = 0; i4 < checkedItemPositions2.size(); i4++) {
                int keyAt2 = checkedItemPositions2.keyAt(i4);
                if (checkedItemPositions2.get(keyAt2)) {
                    arrayList3.add(Integer.valueOf(keyAt2));
                }
            }
            RecentDiscoveryAdapter adapter = RecentDiscoveryFragment.this.getAdapter();
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                long dateModified = adapter.getDateModified(((Integer) it.next()).intValue());
                if (dateModified < RecentDiscoveryFragment.this.mOldestDateModified) {
                    long unused = RecentDiscoveryFragment.this.mOldestDateModified = dateModified;
                }
            }
            return true;
        }

        public void onAllItemsCheckedStateChanged(ActionMode actionMode, boolean z) {
            updateMenuState();
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mMode = actionMode;
            actionMode.getMenuInflater().inflate(BuildUtil.isMiui10(RecentDiscoveryFragment.this.mActivity) ? R.menu.v15_home_page_menu : R.menu.v18_home_page_menu, menu);
            this.mAddToAlbum = menu.findItem(R.id.add_to_album);
            this.mCreativity = menu.findItem(R.id.action_produce);
            this.mDelete = menu.findItem(R.id.delete);
            MenuItem menuItem = this.mCreativity;
            if (menuItem != null) {
                menuItem.setVisible(true);
            }
            MenuItem menuItem2 = this.mDelete;
            if (menuItem2 != null) {
                menuItem2.setVisible(true);
            }
            this.mSend = menu.findItem(R.id.action_send);
            GallerySamplingStatHelper.recordCountEvent("recent_album", "action_mode_create");
            GallerySamplingStatHelper.recordNumericPropertyEvent("best_image", "best_image_count", (long) ImageFeatureCacheManager.getInstance().getBestImageCount(false));
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
        }

        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
            updateMenuState();
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
    };
    protected View mEmptyView;
    protected StickyGridHeadersGridView mGridView;
    protected EditableListViewWrapperDeprecated mGridViewWrapper;
    protected LoaderManager.LoaderCallbacks mLoaderCallbacks;
    /* access modifiers changed from: private */
    public long mOldestDateModified = Long.MAX_VALUE;
    /* access modifiers changed from: private */
    public RecentDiscoveryAdapter mRecentDiscoveryAdapter;

    private class RecentDiscoveryLoaderCallback implements LoaderManager.LoaderCallbacks {
        private RecentDiscoveryLoaderCallback() {
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(RecentDiscoveryFragment.this.mActivity);
            if (i == 1) {
                cursorLoader.setUri(RecentDiscoveryFragment.this.getUri());
                cursorLoader.setProjection(RecentDiscoveryAdapter.PROJECTION);
                cursorLoader.setSortOrder(RecentDiscoveryFragment.this.getSortOrder());
            } else if (i == 2) {
                cursorLoader.setUri(GalleryContract.Album.URI_SHARE_ALL);
                cursorLoader.setProjection(ShareAlbumsCursorHelper.SHARED_ALBUM_PROJECTION);
                cursorLoader.setSelection("count > 0");
            } else if (i == 3) {
                cursorLoader.setUri(GalleryContract.Album.URI_NO_COVERS.buildUpon().appendQueryParameter("join_share", "true").build());
                cursorLoader.setProjection(AlbumsCursorHelper.ALL_ALBUMS_PROJECTION);
            }
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            int id = loader.getId();
            if (id == 1) {
                RecentDiscoveryFragment.this.getAdapter().swapCursor((Cursor) obj);
                if (RecentDiscoveryFragment.this.getAdapter().getCount() == 0) {
                    RecentDiscoveryFragment.this.setEmptyViewVisibility(0);
                } else {
                    RecentDiscoveryFragment.this.setEmptyViewVisibility(8);
                }
            } else if (id == 2) {
                RecentDiscoveryFragment.this.getAdapter().setShareAlbums((Cursor) obj);
            } else if (id == 3) {
                RecentDiscoveryFragment.this.getAdapter().setAllAlbums((Cursor) obj);
            }
        }

        public void onLoaderReset(Loader loader) {
            int id = loader.getId();
            if (id == 1) {
                RecentDiscoveryFragment.this.getAdapter().changeCursor((Cursor) null);
                RecentDiscoveryFragment.this.setEmptyViewVisibility(0);
            } else if (id == 2) {
                RecentDiscoveryFragment.this.getAdapter().resetShareAlbums();
            } else if (id == 3) {
                RecentDiscoveryFragment.this.getAdapter().setAllAlbums((Cursor) null);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void doDelete(final ActionMode actionMode) {
        long[] checkedItemIds = this.mGridViewWrapper.getCheckedItemIds();
        if (checkedItemIds == null || checkedItemIds.length <= 0) {
            ToastUtils.makeText((Context) this.mActivity, (CharSequence) getString(R.string.delete_other_shared_images));
            actionMode.finish();
            return;
        }
        MediaAndAlbumOperations.delete(this.mActivity, "RecentDiscoveryFragmentDeleteMediaDialogFragment", new DeletionTask.OnDeletionCompleteListener() {
            /* JADX WARNING: type inference failed for: r7v4, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r6v3, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
            public void onDeleted(int i, long[] jArr) {
                if (RecentDiscoveryFragment.this.mActivity != null) {
                    ToastUtils.makeText((Context) RecentDiscoveryFragment.this.mActivity, (CharSequence) RecentDiscoveryFragment.this.getResources().getQuantityString(R.plurals.delete_finish_format, i, new Object[]{Integer.valueOf(i)}));
                    if (i > 0) {
                        SoundUtils.playSoundForOperation(RecentDiscoveryFragment.this.mActivity, 0);
                    }
                    actionMode.finish();
                }
            }
        }, this.mAlbumId, this.mAlbumName, getDupType(), 29, checkedItemIds);
    }

    private void recordOldestOperatedPicture() {
        if (this.mOldestDateModified < Long.MAX_VALUE) {
            HashMap hashMap = new HashMap();
            hashMap.put("days_ago", String.valueOf((System.currentTimeMillis() - this.mOldestDateModified) / 86400000));
            GallerySamplingStatHelper.recordCountEvent("recent_album", "recent_album_oldest_operated_picture", hashMap);
        }
    }

    private void recordPageEnterSource() {
        Uri data = this.mActivity.getIntent().getData();
        if (data != null) {
            String queryParameter = data.getQueryParameter("source");
            HashMap hashMap = new HashMap();
            if (queryParameter == null) {
                queryParameter = "unknown";
            }
            hashMap.put("source", queryParameter);
            GallerySamplingStatHelper.recordCountEvent("recent_album", "recent_album_enter_source", hashMap);
        }
    }

    private void updateConfiguration(Configuration configuration) {
        this.mGridView.setNumColumns(configuration.orientation == 2 ? Config.ThumbConfig.get().sMicroThumbRecentColumnsLand : Config.ThumbConfig.get().sMicroThumbRecentColumnsPortrait);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public RecentDiscoveryAdapter getAdapter() {
        if (this.mRecentDiscoveryAdapter == null) {
            this.mRecentDiscoveryAdapter = new RecentDiscoveryAdapter(this.mActivity);
        }
        return this.mRecentDiscoveryAdapter;
    }

    /* access modifiers changed from: protected */
    public int getDupType() {
        return getUri().getBooleanQueryParameter("remove_duplicate_items", false) ? 2 : 0;
    }

    /* access modifiers changed from: protected */
    public AdapterView.OnItemClickListener getGridViewOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i;
                RecentDiscoveryAdapter adapter = RecentDiscoveryFragment.this.getAdapter();
                ImageLoadParams imageLoadParams = new ImageLoadParams(adapter.getItemKey(i2), adapter.getLocalPath(i2), Config.ThumbConfig.get().sMicroRecentTargetSize, adapter.getItemDecodeRectF(i2), i, adapter.getMimeType(i2), adapter.getItemSecretKey(i2), adapter.getFileLength(i2));
                RecentDiscoveryFragment recentDiscoveryFragment = RecentDiscoveryFragment.this;
                IntentUtil.gotoPhotoPage(recentDiscoveryFragment, adapterView, recentDiscoveryFragment.getUri(), i, adapter.getCount(), RecentDiscoveryFragment.this.getSelection(), RecentDiscoveryFragment.this.getSelectionArgs(), RecentDiscoveryFragment.this.getSortOrder(), imageLoadParams, RecentDiscoveryFragment.this.mAlbumId, RecentDiscoveryFragment.this.mAlbumName, RecentDiscoveryFragment.this.getSupportOperationMask(), !RecentDiscoveryFragment.this.mRecentDiscoveryAdapter.supportFoldBurstItems());
                long dateModified = adapter.getDateModified(i2);
                if (dateModified < RecentDiscoveryFragment.this.mOldestDateModified) {
                    long unused = RecentDiscoveryFragment.this.mOldestDateModified = dateModified;
                }
                GallerySamplingStatHelper.recordNumericPropertyEvent(RecentDiscoveryFragment.this.getPageName(), "click_micro_thumb", (long) i2);
            }
        };
    }

    /* access modifiers changed from: protected */
    public Loader getLoader() {
        return null;
    }

    /* access modifiers changed from: protected */
    public LoaderManager.LoaderCallbacks getLoaderCallback() {
        if (this.mLoaderCallbacks == null) {
            this.mLoaderCallbacks = new RecentDiscoveryLoaderCallback();
        }
        return this.mLoaderCallbacks;
    }

    /* access modifiers changed from: protected */
    public List<Loader> getLoaders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getLoaderManager().getLoader(1));
        arrayList.add(getLoaderManager().getLoader(3));
        arrayList.add(getLoaderManager().getLoader(2));
        return arrayList;
    }

    public String getPageName() {
        return "album_recent";
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String[] getSelectionArgs() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSortOrder() {
        return "dateModified DESC";
    }

    /* access modifiers changed from: protected */
    public int getSupportOperationMask() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return GalleryContract.RecentDiscoveredMedia.URI.buildUpon().appendQueryParameter("generate_headers", String.valueOf(true)).build();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getLoaderManager().initLoader(1, (Bundle) null, getLoaderCallback());
        getLoaderManager().initLoader(2, (Bundle) null, getLoaderCallback());
        getLoaderManager().initLoader(3, (Bundle) null, getLoaderCallback());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateConfiguration(configuration);
        this.mGridView.setSelection(this.mGridView.getFirstVisiblePosition());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        recordPageEnterSource();
    }

    public void onDestroy() {
        recordOldestOperatedPicture();
        RecentDiscoveryAdapter recentDiscoveryAdapter = this.mRecentDiscoveryAdapter;
        if (recentDiscoveryAdapter != null) {
            recentDiscoveryAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.album_recent, viewGroup, false);
        this.mAlbumName = getResources().getString(R.string.album_name_recent_discovery);
        this.mAlbumId = 2147483644;
        this.mGridView = (StickyGridHeadersGridView) inflate.findViewById(R.id.grid);
        this.mEmptyView = inflate.findViewById(16908292);
        this.mGridViewWrapper = new EditableListViewWrapperDeprecated(this.mGridView);
        this.mGridViewWrapper.setEmptyView(this.mEmptyView);
        this.mGridViewWrapper.setAdapter(getAdapter());
        this.mGridViewWrapper.setOnItemClickListener(getGridViewOnItemClickListener());
        this.mGridViewWrapper.setChoiceMode(3);
        this.mGridViewWrapper.setMultiChoiceModeListener(this.mChoiceModeListener);
        this.mGridView.setHeadersIgnorePadding(true);
        setEmptyViewVisibility(8);
        updateConfiguration(getResources().getConfiguration());
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void setEmptyViewVisibility(int i) {
        View view = this.mEmptyView;
        if (view != null) {
            view.setVisibility(i);
        }
    }
}
