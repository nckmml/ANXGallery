package com.miui.gallery.ui;

import android.app.LoaderManager;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.SparseLongArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import com.miui.account.AccountHelper;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumPageAdapter;
import com.miui.gallery.adapter.HeaderFooterRecyclerAdapterWrapper;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.model.Album;
import com.miui.gallery.model.OtherAlbum;
import com.miui.gallery.provider.AlbumSnapshotHelper;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.widget.BannerSearchBar;
import com.miui.gallery.threadpool.GallerySchedulers;
import com.miui.gallery.ui.AIAlbumDisplayHelper;
import com.miui.gallery.ui.BaseAlbumOperationDialogFragment;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.widget.DividerTypeProvider;
import com.miui.gallery.widget.TwoStageDrawer;
import com.miui.gallery.widget.recyclerview.BaseViewHolder;
import com.miui.gallery.widget.recyclerview.ExtendedRecyclerView;
import com.miui.gallery.widget.recyclerview.GalleryRecyclerView;
import com.miui.gallery.widget.recyclerview.ItemClickSupport;
import com.miui.gallery.widget.recyclerview.SectionedDividerItemDecoration;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlbumPageFragment extends BaseAlbumPageFragment implements AIAlbumDisplayHelper.DisplayStatusCallback {
    /* access modifiers changed from: private */
    public AlbumPageAdapterWrapper mAlbumPageAdapterWrapper;
    private AlbumPageHeaderController mAlbumPageHeaderController;
    private AlbumPagePhotoLoaderCallback mAlbumPagePhotoLoaderCallback;
    /* access modifiers changed from: private */
    public ExtendedRecyclerView mAlbumRecyclerView;
    /* access modifiers changed from: private */
    public PublishSubject<List<Album>> mAlbumsPublishSubject = PublishSubject.create();
    private BannerSearchBar mBannerSearchBar;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private View mCreateAlbumButton;
    /* access modifiers changed from: private */
    public TwoStageDrawer mDrawer;
    /* access modifiers changed from: private */
    public boolean mHasEnterPrivateEntry;
    private boolean mLoaderInitialized;
    /* access modifiers changed from: private */
    public long mNewlyCreatedAlbumId;
    /* access modifiers changed from: private */
    public BaseAlbumOperationDialogFragment.OnAlbumOperationDoneListener mOnAlbumCreatedListener = new BaseAlbumOperationDialogFragment.OnAlbumOperationDoneListener() {
        /* JADX WARNING: type inference failed for: r5v6, types: [com.miui.gallery.ui.AlbumPageFragment, android.app.Fragment] */
        public void onOperationDone(long j, String str) {
            if (j > 0 && AlbumPageFragment.this.isAdded()) {
                boolean unused = AlbumPageFragment.this.mPendingDisplayCreateAlbum = true;
                long unused2 = AlbumPageFragment.this.mNewlyCreatedAlbumId = j;
                AddPhotosFragment.addPhotos(AlbumPageFragment.this, j);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mPendingDisplayCreateAlbum;
    private TwoStageDrawer.TriggerListener mPrivateEntryTriggerListener = new TwoStageDrawer.TriggerListener() {
        public void onTriggerOpen(TwoStageDrawer twoStageDrawer) {
            if (!AlbumPageFragment.this.mHasEnterPrivateEntry) {
                AlbumPageFragment.this.enterPrivateEntry();
                boolean unused = AlbumPageFragment.this.mHasEnterPrivateEntry = true;
                GallerySamplingStatHelper.recordCountEvent("album", "enter_privacy_mode");
            }
        }

        public void onTriggerSlide(TwoStageDrawer twoStageDrawer, float f) {
        }
    };
    private TopSearchBarController mSearchBarController;
    private AIAlbumDisplayHelper.WeakReferencedAIAlbumDisplayStatusObserver mSearchStatusObserver = new AIAlbumDisplayHelper.WeakReferencedAIAlbumDisplayStatusObserver(this);
    /* access modifiers changed from: private */
    public boolean mShowAIAlbum = false;

    private static class AlbumPageAdapterWrapper extends HeaderFooterRecyclerAdapterWrapper<AlbumPageAdapter, BaseViewHolder> implements DividerTypeProvider, ItemClickSupport.OnItemClickListener {
        public AlbumPageAdapterWrapper(AlbumPageAdapter albumPageAdapter) {
            super(albumPageAdapter);
        }

        private void recordViewAlbum(int i) {
            String albumLocalPath = ((AlbumPageAdapter) this.mWrapped).getAlbumLocalPath(i);
            if (((AlbumPageAdapter) this.mWrapped).isSystemAlbum(i)) {
                HashMap hashMap = new HashMap();
                hashMap.put("album_name", ((AlbumPageAdapter) this.mWrapped).getAlbumName(i));
                hashMap.put("album_server_id", ((AlbumPageAdapter) this.mWrapped).getServerId(i));
                hashMap.put("album_image_count", String.valueOf(((AlbumPageAdapter) this.mWrapped).getAlbumCount(i)));
                GallerySamplingStatHelper.recordCountEvent("album", "view_system_album", hashMap);
            } else if (!TextUtils.isEmpty(albumLocalPath)) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("album_path", albumLocalPath.toLowerCase());
                hashMap2.put("album_attribute", String.valueOf(((AlbumPageAdapter) this.mWrapped).getAttributes(i)));
                hashMap2.put("album_image_count", String.valueOf(((AlbumPageAdapter) this.mWrapped).getAlbumCount(i)));
                hashMap2.put("baby_album", String.valueOf(((AlbumPageAdapter) this.mWrapped).isBabyAlbum(i)));
                GallerySamplingStatHelper.recordCountEvent("album", "view_album", hashMap2);
            } else if (((AlbumPageAdapter) this.mWrapped).isOtherShareAlbum(i)) {
                HashMap hashMap3 = new HashMap();
                hashMap3.put("album_image_count", String.valueOf(((AlbumPageAdapter) this.mWrapped).getAlbumCount(i)));
                hashMap3.put("baby_album", String.valueOf(((AlbumPageAdapter) this.mWrapped).isBabyAlbum(i)));
                GallerySamplingStatHelper.recordCountEvent("album", "view_share_album", hashMap3);
            }
        }

        public int getBottomDividerType(int i) {
            if (i == -1) {
                return 0;
            }
            if (isHeaderPosition(i)) {
                return 3;
            }
            if (isFooterPosition(i)) {
                return 0;
            }
            return ((AlbumPageAdapter) this.mWrapped).getBottomDividerType(i - getHeadersCount());
        }

        public int getTopDividerType(int i) {
            if (i == -1) {
                return 0;
            }
            if (getHeadersCount() > 0 || i != 0) {
                return ((AlbumPageAdapter) this.mWrapped).getTopDividerType(i - getHeadersCount());
            }
            return 3;
        }

        /* access modifiers changed from: protected */
        public BaseViewHolder onCreateHeaderFooterViewHolder(View view) {
            BaseViewHolder baseViewHolder = new BaseViewHolder(view);
            baseViewHolder.setIsRecyclable(false);
            return baseViewHolder;
        }

        public boolean onItemClick(RecyclerView recyclerView, View view, int i, long j, float f, float f2) {
            int i2 = i;
            if (isHeaderPosition(i) || isFooterPosition(i)) {
                return false;
            }
            int wrappedAdapterPosition = getWrappedAdapterPosition(i);
            recordViewAlbum(wrappedAdapterPosition);
            return ((AlbumPageAdapter) this.mWrapped).onItemClick(recyclerView, view, wrappedAdapterPosition, j, f, f2);
        }
    }

    private class AlbumPagePhotoLoaderCallback implements LoaderManager.LoaderCallbacks {
        SoftReference<View> mFooterViewRef;
        SparseBooleanArray mIsManualLoad;
        SparseLongArray mLoaderCreateTime;
        OtherAlbum mOtherAlbumEntity;

        private AlbumPagePhotoLoaderCallback() {
            this.mLoaderCreateTime = new SparseLongArray();
            this.mIsManualLoad = new SparseBooleanArray();
        }

        private void dispatchAlbumUpdates(List<Album> list) {
            SortedList<Album> data = AlbumPageFragment.this.mAlbumPageAdapter.getData();
            if (MiscUtil.isValid(list)) {
                long currentTimeMillis = System.currentTimeMillis();
                data.replaceAll(list);
                Log.d("AlbumPageFragment", "dispatch updates to adapter costs [%d ms], [%d] items", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Integer.valueOf(list.size()));
            } else if (data.size() > 0) {
                data.clear();
            } else {
                AlbumPageFragment.this.mAlbumPageAdapter.notifyDataSetChanged();
            }
            AlbumPageFragment.this.mDrawer.setDragEnabled(MiscUtil.isValid(list));
            AlbumPageFragment.this.updateHeaderAlbumUI();
            updateFooterUI();
        }

        private void statAlbumLoadTime(String str, long j, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("loader", str);
            hashMap.put("costs", String.valueOf(j));
            hashMap.put("count", String.valueOf(i));
            GallerySamplingStatHelper.recordCountEvent("album", "album_load_time", hashMap);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.miui.gallery.ui.AlbumPageListEmptyItem} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.miui.gallery.ui.AlbumPageListEmptyItem} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.miui.gallery.ui.AlbumPageListEmptyItem} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: com.miui.gallery.ui.AlbumPageListEmptyItem} */
        /* JADX WARNING: type inference failed for: r0v10, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
        private void updateFooterUI() {
            int access$1500 = AlbumPageFragment.this.getRecyclerPaddingBottom();
            SoftReference<View> softReference = this.mFooterViewRef;
            View view = softReference != null ? softReference.get() : null;
            RecyclerView.Adapter wrappedAdapter = AlbumPageFragment.this.mAlbumPageAdapterWrapper.getWrappedAdapter();
            boolean z = true;
            if (this.mOtherAlbumEntity != null || wrappedAdapter.getItemCount() > 0) {
                if (view != null) {
                    AlbumPageFragment.this.mAlbumPageAdapterWrapper.removeFooterView(view);
                    if (z) {
                        AlbumPageFragment.this.mRecyclerView.invalidateItemDecorations();
                    }
                    AlbumPageFragment.this.mRecyclerView.setPadding(AlbumPageFragment.this.mRecyclerView.getPaddingLeft(), AlbumPageFragment.this.mRecyclerView.getTop(), AlbumPageFragment.this.mRecyclerView.getPaddingRight(), access$1500);
                }
            } else if (AlbumPageFragment.this.mShowAIAlbum) {
                

                private class OnCreateNewAlbumListener implements View.OnClickListener {
                    private OnCreateNewAlbumListener() {
                    }

                    public void onClick(View view) {
                        AlbumCreatorDialogFragment albumCreatorDialogFragment = new AlbumCreatorDialogFragment();
                        albumCreatorDialogFragment.setOnAlbumOperationDoneListener(AlbumPageFragment.this.mOnAlbumCreatedListener);
                        albumCreatorDialogFragment.showAllowingStateLoss(AlbumPageFragment.this.getFragmentManager(), "AlbumCreatorDialogFragment");
                        GallerySamplingStatHelper.recordCountEvent("album", "create_album");
                    }
                }

                private static class SnapshotFunction implements Function<List<Album>, Integer> {
                    private SnapshotFunction() {
                    }

                    public Integer apply(List<Album> list) throws Exception {
                        return Integer.valueOf(AlbumSnapshotHelper.persist(list));
                    }
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                private void doEnterPrivateEntry() {
                    if (AccountHelper.getXiaomiAccount(this.mActivity) != null) {
                        AuthenticatePrivacyPasswordFragment.startAuthenticatePrivacyPassword(this);
                    }
                }

                /* JADX WARNING: type inference failed for: r3v0, types: [com.miui.gallery.ui.AlbumPageFragment, android.app.Fragment] */
                /* access modifiers changed from: private */
                public void enterPrivateEntry() {
                    Bundle bundle = new Bundle();
                    bundle.putInt("check_login_and_sync", 3);
                    bundle.putSerializable("cloud_guide_source", GalleryIntent.CloudGuideSource.SECRET);
                    LoginAndSyncCheckFragment.checkLoginAndSyncState(this, bundle);
                }

                /* access modifiers changed from: private */
                public String getAlbumQuerySelection(boolean z) {
                    return z ? "classification = 1" : "classification = 0";
                }

                /* access modifiers changed from: private */
                public Uri getAlbumQueryUri(boolean z) {
                    return GalleryContract.Album.URI.buildUpon().appendQueryParameter("join_face", String.valueOf(!z && !this.mShowAIAlbum && AccountCache.getAccount() != null)).appendQueryParameter("join_video", String.valueOf(!z)).appendQueryParameter("join_share", String.valueOf(!z)).appendQueryParameter("join_recent", String.valueOf(!z)).appendQueryParameter("join_favorites", String.valueOf(!z)).appendQueryParameter("exclude_empty_album", "true").appendQueryParameter("fill_covers", String.valueOf(!z)).build();
                }

                /* access modifiers changed from: private */
                public int getRecyclerPaddingBottom() {
                    View view = this.mCreateAlbumButton;
                    int height = (view == null || view.getVisibility() == 8) ? 0 : this.mCreateAlbumButton.getHeight();
                    return height > 0 ? height + (getResources().getDimensionPixelSize(R.dimen.album_fab_margin) * 2) : getResources().getDimensionPixelSize(R.dimen.album_item_placeholder_height);
                }

                private boolean isEmpty() {
                    return this.mAlbumPageAdapter != null && this.mAlbumPageAdapter.getItemCount() == 0;
                }

                /* access modifiers changed from: private */
                public String loaderId2Name(int i) {
                    return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : "other_album" : "people_face_cover" : "share_albums" : "albums" : "albums_snapshot";
                }

                private void onVisibleToUser() {
                    if (isEmpty()) {
                        statAlbumEmpty();
                    }
                }

                /* access modifiers changed from: private */
                public void scrollToNewlyCreatedAlbum(ArrayList<Album> arrayList, SortedList<Album> sortedList) {
                    if (this.mPendingDisplayCreateAlbum) {
                        this.mPendingDisplayCreateAlbum = false;
                        if (arrayList != null) {
                            System.currentTimeMillis();
                            Iterator<Album> it = arrayList.iterator();
                            while (it.hasNext()) {
                                Album next = it.next();
                                if (next.getAlbumId() == this.mNewlyCreatedAlbumId) {
                                    int indexOf = sortedList.indexOf(next);
                                    if (indexOf != -1) {
                                        this.mAlbumRecyclerView.smoothScrollToPosition(indexOf + this.mAlbumPageAdapterWrapper.getHeadersCount());
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }

                /* access modifiers changed from: private */
                public void startToLoadAlbumExtraInfo() {
                    Log.d("AlbumPageFragment", "startToLoadAlbumExtraInfo");
                    getLoaderManager().initLoader(2, (Bundle) null, this.mAlbumPagePhotoLoaderCallback);
                    getLoaderManager().initLoader(3, (Bundle) null, this.mAlbumPagePhotoLoaderCallback);
                    getLoaderManager().initLoader(5, (Bundle) null, this.mAlbumPagePhotoLoaderCallback);
                    if (!this.mShowAIAlbum) {
                        getLoaderManager().initLoader(4, (Bundle) null, this.mAlbumPagePhotoLoaderCallback);
                    }
                    getLoaderManager().destroyLoader(1);
                }

                private void startToLoadAlbums() {
                    Log.d("AlbumPageFragment", "startToLoadAlbums");
                    if (this.mAlbumPagePhotoLoaderCallback != null) {
                        getLoaderManager().initLoader(1, (Bundle) null, this.mAlbumPagePhotoLoaderCallback);
                        this.mLoaderInitialized = true;
                    }
                }

                private void statAlbumEmpty() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("login", String.valueOf(AccountCache.getAccount() != null));
                    GallerySamplingStatHelper.recordCountEvent("album", "album_empty", hashMap);
                }

                /* access modifiers changed from: private */
                public void updateHeaderAlbumUI() {
                    if (SyncUtil.existXiaomiAccount(getActivity()) && this.mShowAIAlbum) {
                        if (this.mAlbumPageHeaderController == null) {
                            this.mAlbumPageHeaderController = new AlbumPageHeaderController(this, this.mRecyclerView);
                        }
                        if (this.mAlbumPageAdapterWrapper.getHeadersCount() == 0) {
                            this.mAlbumPageAdapterWrapper.addHeaderView(this.mAlbumPageHeaderController.getView());
                        }
                    } else if (this.mAlbumPageAdapterWrapper.getHeadersCount() > 0) {
                        this.mAlbumPageAdapterWrapper.removeHeaderView(this.mAlbumPageHeaderController.getView());
                    }
                }

                public String getPageName() {
                    return "album";
                }

                public /* synthetic */ void lambda$onInflateView$43$AlbumPageFragment() {
                    this.mDrawer.openDrawer();
                }

                public /* synthetic */ void lambda$onStatusChanged$44$AlbumPageFragment() {
                    if (!this.mDrawer.isDrawerOpen()) {
                        this.mDrawer.openDrawer();
                    }
                }

                public void onActivityResult(int i, int i2, Intent intent) {
                    if (i2 != -1) {
                        this.mHasEnterPrivateEntry = false;
                    } else if (i == 29) {
                        int intExtra = intent.getIntExtra("check_login_and_sync", -1);
                        if (intExtra == 1 || intExtra == 2) {
                            super.onActivityResult(i, i2, intent);
                            return;
                        } else if (intExtra == 3) {
                            doEnterPrivateEntry();
                        }
                    } else if (i == 36) {
                        Log.d("AlbumPageFragment", "onActivityResult");
                        IntentUtil.enterPrivateAlbum(getActivity());
                    }
                    super.onActivityResult(i, i2, intent);
                }

                public void onCreate(Bundle bundle) {
                    super.onCreate(bundle);
                    this.mLoaderInitialized = false;
                    this.mCompositeDisposable.add(this.mAlbumsPublishSubject.observeOn(GallerySchedulers.misc()).delay(350, TimeUnit.MILLISECONDS, GallerySchedulers.misc()).throttleLatest(3000, TimeUnit.MILLISECONDS, GallerySchedulers.misc(), true).map(new SnapshotFunction()).subscribe());
                }

                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    if (contextMenuInfo != null) {
                        int i = ((GalleryRecyclerView.RecyclerContextMenuInfo) contextMenuInfo).position;
                        if (!this.mAlbumPageAdapterWrapper.isHeaderPosition(i) && !this.mAlbumPageAdapterWrapper.isFooterPosition(i)) {
                            setContextMenuItems(contextMenu, this.mAlbumPageAdapterWrapper.getWrappedAdapterPosition(i));
                        }
                    }
                }

                public void onDestroy() {
                    this.mCompositeDisposable.dispose();
                    super.onDestroy();
                }

                public void onDestroyView() {
                    AIAlbumDisplayHelper.getInstance().unregisterAIAlbumDisplayStatusObserver(this.mSearchStatusObserver);
                    super.onDestroyView();
                }

                /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
                public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
                    View inflate = layoutInflater.inflate(R.layout.album_page, viewGroup, false);
                    this.mAlbumRecyclerView = (ExtendedRecyclerView) inflate.findViewById(R.id.album_list);
                    this.mRecyclerView = (GalleryRecyclerView) this.mAlbumRecyclerView.getRecycler();
                    this.mAlbumPageAdapter = new AlbumPageAdapter(this.mActivity);
                    this.mAlbumPageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                        public void onChanged() {
                            AlbumPageFragment.this.getActivity().closeContextMenu();
                        }
                    });
                    this.mAlbumPageAdapterWrapper = new AlbumPageAdapterWrapper(this.mAlbumPageAdapter);
                    this.mAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    SectionedDividerItemDecoration sectionedDividerItemDecoration = new SectionedDividerItemDecoration(getActivity(), R.drawable.section_divider_bg, R.drawable.item_divider_bg, this.mAlbumPageAdapterWrapper);
                    sectionedDividerItemDecoration.setItemDividerPadding(new Rect(getResources().getDimensionPixelSize(R.dimen.album_cover_container_size_small), 0, 0, 0));
                    this.mAlbumRecyclerView.addItemDecoration(sectionedDividerItemDecoration);
                    this.mAlbumRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
                    this.mAlbumRecyclerView.setAdapter(this.mAlbumPageAdapterWrapper);
                    this.mRecyclerView.setOnItemClickListener(this.mAlbumPageAdapterWrapper);
                    this.mAlbumPagePhotoLoaderCallback = new AlbumPagePhotoLoaderCallback();
                    this.mCreateAlbumButton = inflate.findViewById(R.id.create_album);
                    this.mCreateAlbumButton.setOnClickListener(new OnCreateNewAlbumListener());
                    this.mDrawer = (TwoStageDrawer) inflate;
                    this.mDrawer.setTriggerListener(this.mPrivateEntryTriggerListener);
                    this.mBannerSearchBar = (BannerSearchBar) inflate.findViewById(R.id.album_page_search_bar);
                    SparseBooleanArray registerAIAlbumDisplayStatusObserver = AIAlbumDisplayHelper.getInstance().registerAIAlbumDisplayStatusObserver(this.mSearchStatusObserver);
                    this.mShowAIAlbum = registerAIAlbumDisplayStatusObserver.get(2, false);
                    boolean z = registerAIAlbumDisplayStatusObserver.get(1, false);
                    Log.d("AlbumPageFragment", "Init search bar: %s, ai album: %s", Boolean.valueOf(z), Boolean.valueOf(this.mShowAIAlbum));
                    if (z) {
                        this.mBannerSearchBar.setVisibility(0);
                        if (this.mSearchBarController == null) {
                            this.mSearchBarController = new TopSearchBarController(this, this.mBannerSearchBar, "from_album_page");
                        }
                        this.mDrawer.post(new Runnable() {
                            public final void run() {
                                AlbumPageFragment.this.lambda$onInflateView$43$AlbumPageFragment();
                            }
                        });
                    } else {
                        this.mBannerSearchBar.setVisibility(8);
                    }
                    if (this.mUserFirstVisible && !this.mLoaderInitialized) {
                        startToLoadAlbums();
                    }
                    return inflate;
                }

                public void onPause() {
                    super.onPause();
                    TopSearchBarController topSearchBarController = this.mSearchBarController;
                    if (topSearchBarController != null) {
                        topSearchBarController.onPause();
                    }
                }

                public void onResume() {
                    super.onResume();
                    TopSearchBarController topSearchBarController = this.mSearchBarController;
                    if (topSearchBarController != null) {
                        topSearchBarController.onResume();
                    }
                }

                public void onStart() {
                    super.onStart();
                    this.mHasEnterPrivateEntry = false;
                    if (getUserVisibleHint()) {
                        onVisibleToUser();
                    }
                }

                public void onStatusChanged(SparseBooleanArray sparseBooleanArray) {
                    if (sparseBooleanArray != null) {
                        if (sparseBooleanArray.indexOfKey(1) >= 0) {
                            boolean z = sparseBooleanArray.get(1);
                            Log.d("AlbumPageFragment", "New search bar status %s", (Object) Boolean.valueOf(z));
                            if (z) {
                                if (this.mSearchBarController == null) {
                                    this.mSearchBarController = new TopSearchBarController(this, this.mBannerSearchBar, "from_album_page");
                                }
                                this.mBannerSearchBar.setVisibility(0);
                                this.mSearchBarController.onResume();
                                this.mBannerSearchBar.post(new Runnable() {
                                    public final void run() {
                                        AlbumPageFragment.this.lambda$onStatusChanged$44$AlbumPageFragment();
                                    }
                                });
                            } else {
                                TopSearchBarController topSearchBarController = this.mSearchBarController;
                                if (topSearchBarController != null) {
                                    topSearchBarController.onPause();
                                }
                                this.mBannerSearchBar.setVisibility(8);
                            }
                        }
                        if (sparseBooleanArray.indexOfKey(2) >= 0) {
                            this.mShowAIAlbum = sparseBooleanArray.get(2);
                            Log.d("AlbumPageFragment", "New ai album status %s", (Object) Boolean.valueOf(this.mShowAIAlbum));
                            if (this.mShowAIAlbum) {
                                getLoaderManager().destroyLoader(4);
                            } else {
                                getLoaderManager().restartLoader(4, (Bundle) null, this.mAlbumPagePhotoLoaderCallback);
                            }
                            getLoaderManager().destroyLoader(2);
                            getLoaderManager().initLoader(2, (Bundle) null, this.mAlbumPagePhotoLoaderCallback);
                        }
                    }
                }

                /* access modifiers changed from: protected */
                public boolean recordPageByDefault() {
                    return false;
                }

                public void setUserVisibleHint(boolean z) {
                    super.setUserVisibleHint(z);
                    if (getUserVisibleHint()) {
                        if (!this.mLoaderInitialized && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED) && getView() != null) {
                            startToLoadAlbums();
                        }
                        onVisibleToUser();
                    }
                }
            }
