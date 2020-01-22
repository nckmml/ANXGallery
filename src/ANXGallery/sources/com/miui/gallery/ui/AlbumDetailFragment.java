package com.miui.gallery.ui;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumDetailSimpleAdapter;
import com.miui.gallery.adapter.AlbumDetailTimeLineAdapter;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.ui.BaseAlbumOperationDialogFragment;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.widget.ScrollableViewDrawer;
import com.miui.gallery.widget.SortByHeader;
import com.miui.privacy.LockSettingsHelper;

public class AlbumDetailFragment extends AlbumDetailFragmentBase {
    private AlbumDetailSimpleAdapter mAlbumDetailSimpleAdapter;
    private AlbumDetailTimeLineAdapter mAlbumDetailTimelineAdapter;
    /* access modifiers changed from: private */
    public SortByHeader.SortBy mCurrentSortBy = SortByHeader.SortBy.NONE;
    private ScrollableViewDrawer mDrawer;
    private MenuStatusManager mMenuStatusManager;
    private boolean mNeedConfirmPassWord;
    private BaseAlbumOperationDialogFragment.OnAlbumOperationDoneListener mOnAlbumRenamedListener = new BaseAlbumOperationDialogFragment.OnAlbumOperationDoneListener() {
        public void onOperationDone(long j, String str) {
            if (j > 0 && AlbumDetailFragment.this.isAdded()) {
                AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
                albumDetailFragment.mAlbumName = str;
                albumDetailFragment.updateActionBarTitle();
            }
        }
    };
    private OnSortByClickListener mOnSortByClickListener;
    /* access modifiers changed from: private */
    public SortByHeader mSortByHeader;
    private int mSortByHeaderPaddingTop;
    private String mSortOrder = " DESC ";

    /* renamed from: com.miui.gallery.ui.AlbumDetailFragment$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
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

        /* JADX WARNING: type inference failed for: r3v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(AlbumDetailFragment.this.mActivity);
            AlbumDetailFragment.this.configLoader(cursorLoader, SortByHeader.SortBy.DATE);
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            AlbumDetailSimpleAdapter adapter = AlbumDetailFragment.this.getAdapter();
            adapter.swapCursor((Cursor) obj);
            AlbumDetailFragment.this.mAlbumDetailGridViewWrapper.setAdapter(adapter);
            AlbumDetailFragment.this.mAlbumDetailGridViewWrapper.setOnScrollListener(adapter.generateWrapScrollListener((AbsListView.OnScrollListener) null));
            AlbumDetailFragment.this.mSortByHeader.updateCurrentSortView(AlbumDetailFragment.this.mCurrentSortBy, AlbumDetailFragment.this.getSortByIndicatorResource());
            AlbumDetailFragment.this.updateActionBarTitle();
            if (adapter.getCount() == 0) {
                AlbumDetailFragment.this.mEmptyView.setVisibility(0);
            } else {
                AlbumDetailFragment.this.mEmptyView.setVisibility(8);
            }
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private class OnSortByClickListener implements View.OnClickListener {
        private OnSortByClickListener() {
        }

        public void onClick(View view) {
            Loader loader = AlbumDetailFragment.this.getLoaderManager().getLoader(1);
            int id = view.getId();
            if (id == R.id.datetime_container) {
                AlbumDetailFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.DATE);
                GallerySamplingStatHelper.recordCountEvent("album_detail", "sort_by_date");
            } else if (id == R.id.name_container) {
                AlbumDetailFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.NAME);
                GallerySamplingStatHelper.recordCountEvent("album_detail", "sort_by_name");
            } else if (id == R.id.size_container) {
                GallerySamplingStatHelper.recordCountEvent("album_detail", "sort_by_size");
                AlbumDetailFragment.this.configLoader((CursorLoader) loader, SortByHeader.SortBy.SIZE);
            }
            loader.forceLoad();
        }
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
        String str = " ASC ";
        if (this.mCurrentSortBy == sortBy) {
            if (!TextUtils.equals(this.mSortOrder, " DESC ")) {
                str = " DESC ";
            }
            this.mSortOrder = str;
        } else {
            if (sortBy == SortByHeader.SortBy.DATE) {
                this.mSortOrder = " DESC ";
            } else {
                this.mSortOrder = str;
            }
            this.mCurrentSortBy = sortBy;
        }
        String str2 = sortByString + this.mSortOrder;
        onSortByChanged();
        return str2;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private void doChangeShowInPhotosTab(boolean z) {
        MediaAndAlbumOperations.doChangeShowInPhotosTab(this.mActivity, this.mAlbumId, z);
        this.mMenuStatusManager.onShowInPhotosTabStatusChanged(z);
    }

    private void doRename() {
        AlbumRenameDialogFragment.newInstance(this.mAlbumId, this.mAlbumName, this.mOnAlbumRenamedListener).showAllowingStateLoss(getFragmentManager(), "AlbumRenameDialogFragment");
    }

    /* access modifiers changed from: private */
    public int getSortByIndicatorResource() {
        return TextUtils.equals(this.mSortOrder, " ASC ") ? R.drawable.sort_by_item_arrow_up : R.drawable.sort_by_item_arrow_down;
    }

    private String getSortByString(SortByHeader.SortBy sortBy) {
        int i = AnonymousClass3.$SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy[sortBy.ordinal()];
        return i != 1 ? i != 2 ? "alias_sort_time" : "size" : "title";
    }

    private boolean isCameraAlbum() {
        Intent intent = this.mActivity.getIntent();
        if (intent != null) {
            return TextUtils.equals(intent.getStringExtra("album_server_id"), String.valueOf(1));
        }
        return false;
    }

    private boolean isNeedConfirmPassWord() {
        return isSecretAlbum() && this.mNeedConfirmPassWord && !isInPhotoPage();
    }

    private void onSortByChanged() {
        getAdapter().setCurrentSortBy(this.mCurrentSortBy);
        if (this.mCurrentSortBy == SortByHeader.SortBy.DATE) {
            setViewPaddingTop(this.mSortByHeader, this.mSortByHeaderPaddingTop);
            setViewPaddingTop(this.mDrawer, 0);
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.micro_thumb_horizontal_spacing);
        setViewPaddingTop(this.mSortByHeader, this.mSortByHeaderPaddingTop - dimensionPixelSize);
        setViewPaddingTop(this.mDrawer, dimensionPixelSize);
    }

    private void setViewPaddingTop(View view, int i) {
        view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
    }

    private boolean showOptionsMenu() {
        return !isVirtualAlbum() && !this.mMenuStatusManager.isUnWriteable() && !isPreviewMode();
    }

    private boolean sortByChangeable() {
        return !isDailyAlbum();
    }

    /* access modifiers changed from: private */
    public void updateActionBarTitle() {
        this.mActivity.getActionBar().setTitle(this.mAlbumName);
    }

    /* access modifiers changed from: protected */
    public boolean doChangeAutoUpload(boolean z) {
        if (!super.doChangeAutoUpload(z)) {
            return false;
        }
        this.mMenuStatusManager.onAutoUploadStatusChanged(z);
        return true;
    }

    /* access modifiers changed from: protected */
    public void doChangeShowInOtherAlbums(boolean z) {
        super.doChangeShowInOtherAlbums(z);
        this.mMenuStatusManager.onShowInOtherAlbumsStatusChanged(z);
    }

    public void finish() {
        if (this.mActivity != null && !this.mActivity.isFinishing()) {
            this.mActivity.finish();
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r1v7, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public AlbumDetailSimpleAdapter getAdapter() {
        if (this.mCurrentSortBy != SortByHeader.SortBy.DATE || isDailyAlbum()) {
            if (this.mAlbumDetailSimpleAdapter == null) {
                this.mAlbumDetailSimpleAdapter = new AlbumDetailSimpleAdapter(this.mActivity);
                if (this.mIsOtherShareAlbum) {
                    this.mAlbumDetailSimpleAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.OTHER_SHARE);
                } else if (this.mIsScreenshotAlbum) {
                    this.mAlbumDetailSimpleAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.SCREENSHOT);
                } else if (isSecretAlbum()) {
                    this.mAlbumDetailSimpleAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.SECRET);
                } else if (isPanoAlbum()) {
                    this.mAlbumDetailSimpleAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.PANO);
                } else if (isFavoritesAlbum()) {
                    this.mAlbumDetailSimpleAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.FAVORITES);
                }
            }
            return this.mAlbumDetailSimpleAdapter;
        }
        if (this.mAlbumDetailTimelineAdapter == null) {
            this.mAlbumDetailTimelineAdapter = new AlbumDetailTimeLineAdapter(this.mActivity);
            if (this.mIsOtherShareAlbum) {
                this.mAlbumDetailTimelineAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.OTHER_SHARE);
            } else if (this.mIsScreenshotAlbum) {
                this.mAlbumDetailTimelineAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.SCREENSHOT);
            } else if (isSecretAlbum()) {
                this.mAlbumDetailTimelineAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.SECRET);
            } else if (isPanoAlbum()) {
                this.mAlbumDetailTimelineAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.PANO);
            } else if (isFavoritesAlbum()) {
                this.mAlbumDetailTimelineAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.FAVORITES);
            }
        }
        return this.mAlbumDetailTimelineAdapter;
    }

    /* access modifiers changed from: protected */
    public String getCreatorIdByPosition(int i) {
        return getAdapter().getCreatorId(i);
    }

    /* access modifiers changed from: protected */
    public String getCurrentSortOrder() {
        return getSortByString(this.mCurrentSortBy) + this.mSortOrder;
    }

    /* access modifiers changed from: protected */
    public int getLayoutSource() {
        return R.layout.album_detail;
    }

    /* access modifiers changed from: protected */
    public LoaderManager.LoaderCallbacks getLoaderCallback() {
        if (this.mAlbumDetailLoaderCallback == null) {
            this.mAlbumDetailLoaderCallback = new AlbumDetailLoaderCallback();
        }
        return this.mAlbumDetailLoaderCallback;
    }

    public String getPageName() {
        return isSecretAlbum() ? "album_secret" : isFavoritesAlbum() ? "album_favorites" : isCameraAlbum() ? "album_camera" : isVideoAlbum() ? "album_video" : this.mIsScreenshotAlbum ? "album_screenshot" : "album_detail";
    }

    /* access modifiers changed from: protected */
    public AlbumDetailSimpleAdapter getViewAdapter() {
        ListAdapter adapter = this.mAlbumDetailGridViewWrapper.getAdapter();
        return (adapter == null || !(adapter instanceof AlbumDetailSimpleAdapter)) ? super.getViewAdapter() : (AlbumDetailSimpleAdapter) adapter;
    }

    public boolean isPreviewMode() {
        return getActivity() != null && getActivity().getIntent().getIntExtra("extra_from_type", -1) == 1001;
    }

    /* access modifiers changed from: protected */
    public boolean needEnableAutoUpload() {
        return this.mMenuStatusManager.canEnableAutoUpload();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity.setImmersionMenuEnabled(showOptionsMenu());
        if (isSecretAlbum()) {
            this.mActivity.getWindow().addFlags(8192);
        }
        if (bundle != null) {
            this.mCurrentSortBy = (SortByHeader.SortBy) bundle.getSerializable("album_detail_sort_by");
            if (sortByChangeable() && bundle.getBoolean("album_detail_drawer_state", false)) {
                this.mDrawer.openDrawer();
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 27) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 != -1) {
            finish();
        } else {
            this.mNeedConfirmPassWord = false;
        }
    }

    public boolean onBackPressed() {
        if (!isSecretAlbum() || !GalleryPreferences.Secret.isFirstAddSecret()) {
            return false;
        }
        GalleryPreferences.Secret.setIsFirstAddSecret(false);
        SecretTipDialogFragment secretTipDialogFragment = new SecretTipDialogFragment();
        secretTipDialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                AlbumDetailFragment.this.finish();
            }
        });
        secretTipDialogFragment.showAllowingStateLoss(getActivity().getFragmentManager(), "SecretTip");
        return true;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!isPreviewMode()) {
            menuInflater.inflate(R.menu.album_detail_page_options, menu);
        }
    }

    public void onDestroy() {
        AlbumDetailSimpleAdapter albumDetailSimpleAdapter = this.mAlbumDetailSimpleAdapter;
        if (albumDetailSimpleAdapter != null) {
            albumDetailSimpleAdapter.swapCursor((Cursor) null);
        }
        AlbumDetailTimeLineAdapter albumDetailTimeLineAdapter = this.mAlbumDetailTimelineAdapter;
        if (albumDetailTimeLineAdapter != null) {
            albumDetailTimeLineAdapter.swapCursor((Cursor) null);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onEnterActionMode() {
        if (this.mMenuStatusManager.canShowInPhotosTab()) {
            ImageSelectionTipFragment.showImageSelectionTipDialogIfNecessary(getActivity());
        }
        GallerySamplingStatHelper.recordNumericPropertyEvent("best_image", "best_image_count", (long) ImageFeatureCacheManager.getInstance().getBestImageCount(false));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        Intent intent = this.mActivity.getIntent();
        this.mMenuStatusManager = new MenuStatusManager(this.mActivity, intent.getStringExtra("album_server_id"), intent.getLongExtra("attributes", 0), intent.getBooleanExtra("album_unwriteable", false), isOthersShareAlbum(), intent.getStringExtra("album_local_path"));
        this.mOnSortByClickListener = new OnSortByClickListener();
        if (!TextUtils.isEmpty(this.mAlbumName)) {
            updateActionBarTitle();
        }
        this.mDrawer = (ScrollableViewDrawer) onInflateView.findViewById(R.id.drawer);
        this.mSortByHeader = (SortByHeader) onInflateView.findViewById(R.id.sortby_header);
        this.mSortByHeaderPaddingTop = this.mSortByHeader.getPaddingTop();
        this.mSortByHeader.setOnSortByClickListener(this.mOnSortByClickListener);
        if (isPreviewMode()) {
            this.mAlbumDetailGridViewWrapper.setLongClickable(false);
        }
        if (!sortByChangeable()) {
            this.mDrawer.setDragEnabled(false);
        }
        return onInflateView;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (!onBackPressed() && isAdded() && isResumed()) {
                    finish();
                }
                return true;
            case R.id.menu_disable_auto_upload:
                disableAutoUpload();
                GallerySamplingStatHelper.recordCountEvent("album_detail", "auto_upload_disable");
                return true;
            case R.id.menu_enable_auto_upload:
                enableAutoUpload();
                GallerySamplingStatHelper.recordCountEvent("album_detail", "auto_upload_enable");
                return true;
            case R.id.menu_move_to_other_albums:
                moveToOtherAlbums();
                GallerySamplingStatHelper.recordCountEvent("album_detail", "show_in_others_enable");
                return true;
            case R.id.menu_not_show_in_photos_tab:
                GallerySamplingStatHelper.recordCountEvent("album_detail", "show_in_home_disable");
                doChangeShowInPhotosTab(false);
                return true;
            case R.id.menu_remove_from_other_albums:
                removeFromOtherAlbums();
                GallerySamplingStatHelper.recordCountEvent("album_detail", "show_in_others_disable");
                return true;
            case R.id.menu_rename:
                doRename();
                GallerySamplingStatHelper.recordCountEvent("album", "rename_album");
                return true;
            case R.id.menu_show_in_photos_tab:
                doChangeShowInPhotosTab(true);
                GallerySamplingStatHelper.recordCountEvent("album_detail", "show_in_home_enable");
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: protected */
    public void onPhotoPageDestroy(Intent intent) {
        super.onPhotoPageDestroy(intent);
        this.mNeedConfirmPassWord = false;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (menu.size() > 0) {
            MenuItem findItem = menu.findItem(R.id.menu_add_photos);
            MenuItem findItem2 = menu.findItem(R.id.menu_remove_from_other_albums);
            MenuItem findItem3 = menu.findItem(R.id.menu_move_to_other_albums);
            MenuItem findItem4 = menu.findItem(R.id.menu_enable_auto_upload);
            MenuItem findItem5 = menu.findItem(R.id.menu_disable_auto_upload);
            MenuItem findItem6 = menu.findItem(R.id.menu_show_in_photos_tab);
            MenuItem findItem7 = menu.findItem(R.id.menu_not_show_in_photos_tab);
            MenuItem findItem8 = menu.findItem(R.id.menu_share);
            MenuItem findItem9 = menu.findItem(R.id.menu_rename);
            if (SyncUtil.isGalleryCloudSyncable(getActivity())) {
                findItem4.setVisible(this.mMenuStatusManager.canEnableAutoUpload());
                findItem5.setVisible(this.mMenuStatusManager.canDisableAutoUpload());
            } else {
                findItem4.setVisible(false);
                findItem5.setVisible(false);
            }
            findItem2.setVisible(this.mMenuStatusManager.canDisableShowInOtherAlbums());
            findItem3.setVisible(this.mMenuStatusManager.canEnableShowInOtherAlbums());
            findItem.setVisible(this.mMenuStatusManager.canAddPhotos());
            findItem6.setVisible(this.mMenuStatusManager.canEnableShowInPhotosTab());
            findItem7.setVisible(this.mMenuStatusManager.canDisableShowInPhotosTab());
            findItem8.setVisible(this.mMenuStatusManager.canShare());
            findItem9.setVisible(this.mMenuStatusManager.canRename());
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.app.Fragment, com.miui.gallery.ui.AlbumDetailFragment, com.miui.gallery.ui.AlbumDetailFragmentBase] */
    public void onResume() {
        super.onResume();
        if (isNeedConfirmPassWord()) {
            this.mNeedConfirmPassWord = false;
            LockSettingsHelper.startAuthenticatePasswordActivity((Fragment) this, 27);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("album_detail_sort_by", this.mCurrentSortBy);
        bundle.putBoolean("album_detail_drawer_state", this.mDrawer.isDrawerOpen());
    }

    public void onStop() {
        super.onStop();
        this.mNeedConfirmPassWord = true;
    }
}
