package com.miui.gallery.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.activity.facebaby.FacePageActivity;
import com.miui.gallery.activity.facebaby.RecommendFacePageActivity;
import com.miui.gallery.adapter.FacePageAdapter;
import com.miui.gallery.cloud.peopleface.FaceDataManager;
import com.miui.gallery.cloud.peopleface.PeopleFace;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.model.PeopleContactInfo;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.deprecated.NormalPeopleFaceMediaSet;
import com.miui.gallery.provider.deprecated.PeopleRecommendMediaSet;
import com.miui.gallery.share.Path;
import com.miui.gallery.share.UIHelper;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.DeletionTask;
import com.miui.gallery.ui.renameface.FaceAlbumHandlerBase;
import com.miui.gallery.ui.renameface.FaceAlbumRenameHandler;
import com.miui.gallery.ui.renameface.InputFaceNameFragment;
import com.miui.gallery.ui.renameface.RemoveFromFaceAlbumHandler;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.SoundUtils;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.baby.FindFace2CreateBabyAlbum;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.gallery.util.face.CheckoutRecommendPeople;
import com.miui.gallery.util.face.PeopleItemBitmapDisplayer;
import com.miui.gallery.widget.ActionMenuItemView;
import com.miui.gallery.widget.PagerGridLayout;
import com.miui.gallery.widget.editwrapper.EditableListViewWrapperDeprecated;
import com.miui.gallery.widget.editwrapper.MultiChoiceModeListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import java.util.ArrayList;
import java.util.HashMap;

public class FacePageFragment extends BaseMediaFragment implements CheckoutRecommendPeople.CheckoutStatusListener, PagerGridLayout.OnPageChangedListener {
    /* access modifiers changed from: private */
    public FacePageAdapter mAdapter;
    /* access modifiers changed from: private */
    public boolean mAddFooterView = false;
    /* access modifiers changed from: private */
    public String mAlbumName;
    /* access modifiers changed from: private */
    public CheckoutRecommendPeople mCheckoutRecommendPeopleTask;
    private Future mCoverRefreshTask;
    private FaceAlbumRenameHandler mFaceAlbumMergeHandler;
    private FaceAlbumRenameHandler mFaceAlbumRenameHandler;
    private DisplayImageOptions mFaceCoverDisplayOptions;
    /* access modifiers changed from: private */
    public View mFaceCoverHeader;
    /* access modifiers changed from: private */
    public String mFaceCoverPath;
    /* access modifiers changed from: private */
    public RectF mFaceCoverRectF;
    private ImageViewAware mFaceCoverViewAware;
    /* access modifiers changed from: private */
    public StickyGridHeadersGridView mFaceGridView;
    /* access modifiers changed from: private */
    public EditableListViewWrapperDeprecated mFaceGridViewWrapper;
    private NormalPeopleFaceMediaSet mFaceMediaSet;
    private ArrayList<NormalPeopleFaceMediaSet> mFaceMediasetsList;
    private FacePagePhotoLoaderCallback mFacePagePhotoLoaderCallback;
    /* access modifiers changed from: private */
    public View mFooterView;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public boolean mHasRequestRecommendFace;
    private ChoiceModeListener mListener;
    /* access modifiers changed from: private */
    public long mLocalIdOfAlbum;
    private Future mNameRefreshTask;
    /* access modifiers changed from: private */
    public PeopleRecommendMediaSet mPeopleRecommendMediaSet;
    /* access modifiers changed from: private */
    public RecommendFaceGroupAdapter mRecommendFaceAdapter;
    private View mRecommendFaceButtonContainer;
    /* access modifiers changed from: private */
    public PagerGridLayout mRecommendFaceGroup;
    /* access modifiers changed from: private */
    public View mRecommendFaceGroupHeader;
    /* access modifiers changed from: private */
    public String mRecommendFaceIds;
    private int mRelationType;
    private RemoveFromFaceAlbumHandler mRemoveFromFaceAlbumHandler;
    /* access modifiers changed from: private */
    public String mServerIdOfAlbum;
    private View mShareButtonContainer;

    private class ChoiceModeListener implements MultiChoiceModeListener {
        /* access modifiers changed from: private */
        public ActionMode mMode;
        private FaceAlbumHandlerBase.FaceAlbumHandlerListener mRemoveFromFaceAlbumListener;

        private ChoiceModeListener() {
            this.mRemoveFromFaceAlbumListener = new FaceAlbumHandlerBase.FaceAlbumHandlerListener() {
                /* access modifiers changed from: private */
                public void doRemove(FaceAlbumHandlerBase.FaceFolderItem faceFolderItem) {
                    NormalPeopleFaceMediaSet.doMoveFacesToAnother(faceFolderItem, FacePageFragment.this.mFaceGridViewWrapper.getCheckedItemIds());
                    ChoiceModeListener.this.mMode.finish();
                    GallerySamplingStatHelper.recordCountEvent("face", "face_remove");
                }

                /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                public void onGetFolderItem(final FaceAlbumHandlerBase.FaceFolderItem faceFolderItem) {
                    String str;
                    Spanned spanned;
                    String str2;
                    if (FacePageFragment.this.mFaceGridViewWrapper.getCheckedItemIds().length > 0) {
                        AnonymousClass1 r2 = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AnonymousClass2.this.doRemove(faceFolderItem);
                            }
                        };
                        AnonymousClass2 r3 = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        };
                        if (faceFolderItem == null) {
                            String string = FacePageFragment.this.mActivity.getString(R.string.remove_from_album_title);
                            str2 = string;
                            str = FacePageFragment.this.mActivity.getString(R.string.operation_remove_face);
                            spanned = Html.fromHtml(FacePageFragment.this.mActivity.getResources().getString(R.string.remove_from_album_message));
                        } else {
                            String string2 = FacePageFragment.this.mActivity.getString(17039370);
                            spanned = Html.fromHtml(FacePageFragment.this.mActivity.getString(R.string.confirm_merge_many_face_albums, new Object[]{faceFolderItem.getName()}));
                            str = string2;
                            str2 = "";
                        }
                        DialogUtil.showConfirmAlertWithCancel(FacePageFragment.this.mActivity, r2, r3, str2, spanned, str, 17039360);
                    }
                }
            };
        }

        private void enableOrDisableMenuItem(boolean z) {
            Menu menu = this.mMode.getMenu();
            menu.findItem(R.id.action_remove_from_face_album).setEnabled(z);
            menu.findItem(R.id.action_delete).setEnabled(z);
            menu.findItem(R.id.action_produce).setEnabled(z);
            menu.findItem(R.id.action_send).setEnabled(z);
        }

        private long[] getSelectedPhotoIds() {
            SparseBooleanArray checkedItemPositions = FacePageFragment.this.mFaceGridViewWrapper.getCheckedItemPositions();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < checkedItemPositions.size(); i++) {
                int keyAt = checkedItemPositions.keyAt(i);
                if (checkedItemPositions.get(keyAt)) {
                    arrayList.add(Long.valueOf(FacePageFragment.this.mAdapter.getItemPhotoId(keyAt)));
                }
            }
            long[] jArr = new long[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                jArr[i2] = ((Long) arrayList.get(i2)).longValue();
            }
            return jArr;
        }

        /* JADX WARNING: type inference failed for: r1v4, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int i = 0;
            switch (menuItem.getItemId()) {
                case R.id.action_delete:
                    FacePageFragment.this.doDelete(this.mMode, getSelectedPhotoIds());
                    return true;
                case R.id.action_produce:
                    final ActionMode actionMode2 = actionMode;
                    MediaAndAlbumOperations.doProduceCreation(FacePageFragment.this.mActivity, new MediaAndAlbumOperations.OnCompleteListener() {
                        public void onComplete(long[] jArr) {
                            actionMode2.finish();
                        }
                    }, FacePageFragment.this.mFaceGridViewWrapper.getCheckedItems());
                    GallerySamplingStatHelper.recordCountEvent("face", "produce");
                    return true;
                case R.id.action_remove_from_face_album:
                    FacePageFragment.this.startRemoveFromFaceAlbum(this.mRemoveFromFaceAlbumListener);
                    return true;
                case R.id.action_send:
                    SparseBooleanArray checkedItemPositions = FacePageFragment.this.mFaceGridViewWrapper.getCheckedItemPositions();
                    ArrayList arrayList = new ArrayList(checkedItemPositions.size());
                    ArrayList arrayList2 = new ArrayList(checkedItemPositions.size());
                    int i2 = Integer.MAX_VALUE;
                    for (int i3 = 0; i3 < checkedItemPositions.size(); i3++) {
                        int keyAt = checkedItemPositions.keyAt(i3);
                        if (checkedItemPositions.get(keyAt)) {
                            arrayList.add(Integer.valueOf(keyAt));
                            arrayList2.add(Long.valueOf(FacePageFragment.this.mAdapter.getItemKey(keyAt)));
                            if (keyAt < i2) {
                                i2 = keyAt;
                            }
                        }
                    }
                    int[] iArr = new int[arrayList.size()];
                    long[] jArr = new long[arrayList2.size()];
                    for (int i4 = 0; i4 < arrayList.size(); i4++) {
                        iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
                        jArr[i4] = ((Long) arrayList2.get(i4)).longValue();
                    }
                    if (i2 != Integer.MAX_VALUE) {
                        i = i2;
                    }
                    IntentUtil.gotoPreviewSelectPage(FacePageFragment.this, GalleryContract.PeopleFace.ONE_PERSON_URI, i, FacePageFragment.this.mAdapter.getCount(), (String) null, FacePageFragment.this.getSelectioinArgs(), FacePageFragment.this.getOrderBy(), new ImageLoadParams(FacePageFragment.this.mAdapter.getItemKey(i), FacePageFragment.this.mAdapter.getLocalPath(i), Config.ThumbConfig.get().sMicroTargetSize, FacePageFragment.this.mAdapter.getItemDecodeRectF(i), i, FacePageFragment.this.mAdapter.getMimeType(i), FacePageFragment.this.mAdapter.getFileLength(i)), jArr, iArr);
                    actionMode.finish();
                    GallerySamplingStatHelper.recordCountEvent("face", "face_send");
                    return true;
                default:
                    return false;
            }
        }

        public void onAllItemsCheckedStateChanged(ActionMode actionMode, boolean z) {
            enableOrDisableMenuItem(FacePageFragment.this.mFaceGridViewWrapper.getCheckedItemCount() > 0);
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.mMode = actionMode;
            actionMode.getMenuInflater().inflate(BuildUtil.isMiui10(FacePageFragment.this.mActivity) ? R.menu.v15_face_page_action_menu : R.menu.v18_face_page_action_menu, menu);
            enableOrDisableMenuItem(false);
            FacePageFragment.this.changeVisibilityOfShareContainer(8);
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mMode = null;
            FacePageFragment.this.changeVisibilityOfShareContainer(0);
        }

        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
            enableOrDisableMenuItem(FacePageFragment.this.mFaceGridViewWrapper.getCheckedItemCount() > 0);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            FacePageFragment.this.changeVisibilityOfShareContainer(8);
            return false;
        }
    }

    private class FacePagePhotoLoaderCallback implements LoaderManager.LoaderCallbacks {
        private FacePagePhotoLoaderCallback() {
        }

        /* JADX WARNING: type inference failed for: r4v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(FacePageFragment.this.mActivity);
            cursorLoader.setUri(GalleryContract.PeopleFace.ONE_PERSON_URI.buildUpon().appendQueryParameter("generate_headers", String.valueOf(true)).build());
            cursorLoader.setProjection(FacePageAdapter.PROJECTION);
            cursorLoader.setSelectionArgs(FacePageFragment.this.getSelectioinArgs());
            cursorLoader.setSortOrder(FacePageFragment.this.getOrderBy());
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            FacePageFragment.this.mAdapter.swapCursor((Cursor) obj);
            FacePageFragment.this.freshFacePhotoCount();
            FacePageFragment.this.refreshFaceCover();
            if (FacePageFragment.this.mAddFooterView) {
                FacePageFragment.this.mFaceGridView.addFooterView(FacePageFragment.this.mFooterView);
                boolean unused = FacePageFragment.this.mAddFooterView = false;
            }
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    private class FaceRecommendPhotoLoaderCallback implements LoaderManager.LoaderCallbacks {
        private FaceRecommendPhotoLoaderCallback() {
        }

        private String getOrderBy() {
            return "dateTaken DESC ";
        }

        private Uri getUri() {
            return GalleryContract.PeopleFace.RECOMMEND_FACES_OF_ONE_PERSON_URI;
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(FacePageFragment.this.getActivity());
            cursorLoader.setUri(getUri());
            cursorLoader.setProjection(RecommendFaceGroupAdapter.PROJECTION);
            cursorLoader.setSelectionArgs(new String[]{FacePageFragment.this.mRecommendFaceIds});
            cursorLoader.setSortOrder(getOrderBy());
            return cursorLoader;
        }

        public void onLoadFinished(Loader loader, Object obj) {
            FacePageFragment.this.mRecommendFaceAdapter.swapCursor((Cursor) obj);
            FacePageFragment.this.mRecommendFaceGroup.setAdapter(FacePageFragment.this.mRecommendFaceAdapter);
        }

        public void onLoaderReset(Loader loader) {
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private void addFaceCoverHeader() {
        this.mFaceCoverHeader = LayoutInflater.from(this.mActivity).inflate(R.layout.face_page_face_cover_header, this.mFaceGridView, false);
        this.mFaceCoverViewAware = new ImageViewAware((ImageView) this.mFaceCoverHeader.findViewById(R.id.face_cover));
        initFaceCoverDisplayOptions();
        displayFaceCover();
        TextView textView = (TextView) this.mFaceCoverHeader.findViewById(R.id.face_name_edit);
        if (TextUtils.isEmpty(getAlbumName())) {
            textView.setText(getString(R.string.face_name_input));
        } else {
            textView.setText(getString(R.string.face_name_edit));
        }
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FacePageFragment.this.showRenameHandler();
            }
        });
        this.mFaceGridView.addHeaderView(this.mFaceCoverHeader);
    }

    private void addHeaderView() {
        addFaceCoverHeader();
        addRecommendGroupHeader();
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private void addRecommendGroupHeader() {
        this.mPeopleRecommendMediaSet = new PeopleRecommendMediaSet(new NormalPeopleFaceMediaSet(this.mLocalIdOfAlbum, getServerIdOfAlbum(), getAlbumName()));
        this.mPeopleRecommendMediaSet.refreshRecommendInfo();
        if (this.mPeopleRecommendMediaSet.getActualNeedRecommendPeopleFacePhotoNumber() <= 0) {
            return;
        }
        if (!GalleryPreferences.Face.isFaceRecommendGroupHidden(getServerIdOfAlbum())) {
            this.mRecommendFaceIds = this.mPeopleRecommendMediaSet.getServerIdsIn();
            this.mRecommendFaceGroupHeader = LayoutInflater.from(this.mActivity).inflate(R.layout.face_page_recommend_group_header, this.mFaceGridView, false);
            this.mFaceGridView.addHeaderView(this.mRecommendFaceGroupHeader);
            GallerySamplingStatHelper.recordCountEvent("face", "face_show_recommend_panel");
            this.mRecommendFaceGroup = (PagerGridLayout) this.mRecommendFaceGroupHeader.findViewById(R.id.face_recommend_group);
            this.mRecommendFaceGroup.setOnPageChangedListener(this);
            this.mRecommendFaceAdapter = new RecommendFaceGroupAdapter(this, this.mServerIdOfAlbum, Long.valueOf(this.mLocalIdOfAlbum)) {
                public int getColumnsCount() {
                    return 4;
                }

                /* access modifiers changed from: protected */
                public int getLayoutId() {
                    return R.layout.recommend_face_cover_item_small;
                }

                public int getRowsCount() {
                    return 1;
                }
            };
            this.mRecommendFaceAdapter.setOnLoadingCompleteListener(new BindImageHelper.OnImageLoadingCompleteListener() {
                public void onLoadingComplete() {
                    FacePageFragment.this.invalidateFaceGridView();
                }

                public void onLoadingFailed() {
                }
            });
            getLoaderManager().initLoader(2, (Bundle) null, new FaceRecommendPhotoLoaderCallback());
            this.mRecommendFaceGroupHeader.findViewById(R.id.confirm_recommend).setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r2v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                public void onClick(View view) {
                    if (GalleryPreferences.Face.isShowRecommendConfirmDialog()) {
                        int mergeFaceCount = FacePageFragment.this.mRecommendFaceAdapter.getMergeFaceCount();
                        GalleryPreferences.Face.setShowRecommendConfirmDialog(false);
                        new AlertDialog.Builder(FacePageFragment.this.mActivity).setPositiveButton(R.string.confirm_hidden_recommend_group, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FacePageFragment.this.changeToNextPage();
                            }
                        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setTitle(FacePageFragment.this.getString(R.string.remind_face_recommend_confirm)).setMessage(FacePageFragment.this.mActivity.getResources().getQuantityString(R.plurals.remind_face_recommend_confirm_text, mergeFaceCount, new Object[]{Integer.valueOf(mergeFaceCount)})).create().show();
                        return;
                    }
                    FacePageFragment.this.changeToNextPage();
                }
            });
            this.mRecommendFaceGroupHeader.findViewById(R.id.face_recommend_hidden).setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                public void onClick(View view) {
                    AlertDialog create = new AlertDialog.Builder(FacePageFragment.this.mActivity).setPositiveButton(R.string.confirm_hidden_recommend_group, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            GalleryPreferences.Face.setFaceRecommendGroupHidden(FacePageFragment.this.getServerIdOfAlbum(), true);
                            FacePageFragment.this.mFaceGridView.removeHeaderView(FacePageFragment.this.mRecommendFaceGroupHeader);
                            FacePageFragment.this.mFaceGridView.addFooterView(FacePageFragment.this.mFooterView);
                            GallerySamplingStatHelper.recordCountEvent("face", "face_close_recommend_panel");
                        }
                    }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setTitle(FacePageFragment.this.getString(R.string.confirm_hidden_recommend_group_title)).setMessage(FacePageFragment.this.getString(R.string.confirm_hidden_recommend_group_message)).create();
                    create.show();
                    create.getButton(-1).setTextColor(FacePageFragment.this.getResources().getColor(R.color.remove_recommend_header_dialog_button));
                }
            });
            return;
        }
        this.mAddFooterView = true;
    }

    private void cancelTask(Future future) {
        if (future != null && !future.isCancelled()) {
            future.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void changeVisibilityOfShareContainer(int i) {
        View view = this.mShareButtonContainer;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private FaceAlbumRenameHandler.ConfirmListener confirmListener() {
        return new FaceAlbumRenameHandler.ConfirmListener() {
            public void onConfirm(final String str, boolean z) {
                if (!z) {
                    FacePageFragment.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (FacePageFragment.this.getActivity() != null) {
                                if (TextUtils.isEmpty(FacePageFragment.this.getAlbumName())) {
                                    ((TextView) FacePageFragment.this.mFaceCoverHeader.findViewById(R.id.face_name_edit)).setText(FacePageFragment.this.getString(R.string.face_name_edit));
                                }
                                String unused = FacePageFragment.this.mAlbumName = str;
                                FacePageFragment.this.setTitle();
                            }
                        }
                    });
                } else {
                    ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                        /* JADX WARNING: type inference failed for: r5v3, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                        /* JADX WARNING: type inference failed for: r1v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
                        public Void run(ThreadPool.JobContext jobContext) {
                            PeopleFace groupByPeopleName = FaceDataManager.getGroupByPeopleName(FacePageFragment.this.mActivity, str);
                            if (groupByPeopleName == null) {
                                return null;
                            }
                            Intent intent = new Intent(FacePageFragment.this.mActivity, FacePageActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("server_id_of_album", groupByPeopleName.serverId);
                            bundle.putString("local_id_of_album", groupByPeopleName.getId());
                            bundle.putString("album_name", str);
                            bundle.putInt("relationType", groupByPeopleName.relationType);
                            RectF[] rectFArr = new RectF[1];
                            bundle.putString("face_album_cover", FaceManager.queryCoverImageOfOnePerson(groupByPeopleName.serverId, rectFArr));
                            bundle.putParcelable("face_position_rect", rectFArr[0]);
                            intent.putExtras(bundle);
                            FacePageFragment.this.mActivity.startActivity(intent);
                            FacePageFragment.this.mActivity.finish();
                            return null;
                        }
                    });
                }
                GallerySamplingStatHelper.recordCountEvent("face", "face_album_rename");
            }
        };
    }

    /* access modifiers changed from: private */
    public void displayFaceCover() {
        if (this.mFaceCoverPath != null) {
            ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(this.mFaceCoverPath), this.mFaceCoverViewAware, this.mFaceCoverDisplayOptions, (ImageSize) null, new SimpleImageLoadingListener() {
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    FacePageFragment.this.invalidateFaceGridView();
                }
            }, (ImageLoadingProgressListener) null, this.mFaceCoverRectF);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void doDelete(final ActionMode actionMode, long[] jArr) {
        MediaAndAlbumOperations.delete(this.mActivity, "FacePageFragmentDeleteMediaDialogFragment", new DeletionTask.OnDeletionCompleteListener() {
            /* JADX WARNING: type inference failed for: r7v7, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
            /* JADX WARNING: type inference failed for: r6v4, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
            public void onDeleted(int i, long[] jArr) {
                if (FacePageFragment.this.mActivity != null) {
                    NormalPeopleFaceMediaSet.ignoreFaces(FacePageFragment.this.mFaceGridViewWrapper.getCheckedItemIds());
                    ToastUtils.makeText((Context) FacePageFragment.this.mActivity, (CharSequence) FacePageFragment.this.getResources().getQuantityString(R.plurals.delete_finish_format, i, new Object[]{Integer.valueOf(i)}));
                    if (i > 0) {
                        SoundUtils.playSoundForOperation(FacePageFragment.this.mActivity, 0);
                    }
                    actionMode.finish();
                    GallerySamplingStatHelper.recordCountEvent("face", "face_delete");
                }
            }
        }, this.mLocalIdOfAlbum, this.mAlbumName, 0, 28, jArr);
    }

    /* access modifiers changed from: private */
    public void freshFacePhotoCount() {
        int count = this.mAdapter.getCount();
        ((TextView) this.mFaceCoverHeader.findViewById(R.id.photo_count)).setText(this.mActivity.getResources().getQuantityString(R.plurals.face_count, count, new Object[]{Integer.valueOf(count)}));
    }

    /* access modifiers changed from: private */
    public String getAlbumName() {
        return (this.mActivity != null && !TextUtils.isEmpty(this.mAlbumName) && !this.mActivity.getString(R.string.people_page_unname).equalsIgnoreCase(this.mAlbumName)) ? this.mAlbumName : "";
    }

    /* access modifiers changed from: private */
    public String getOrderBy() {
        return "dateTaken DESC ";
    }

    /* access modifiers changed from: private */
    public String[] getSelectioinArgs() {
        return new String[]{this.mServerIdOfAlbum, String.valueOf(this.mLocalIdOfAlbum)};
    }

    private void initFaceCoverDisplayOptions() {
        this.mFaceCoverDisplayOptions = new DisplayImageOptions.Builder().cacheThumbnail(true).loadFromMicroCache(true).cacheInMemory(true).considerExifParams(true).showImageOnFail(R.drawable.default_face_cover).showImageForEmptyUri(R.drawable.default_face_cover).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).displayer(new PeopleItemBitmapDisplayer(false)).usingRegionDecoderFace(true).build();
    }

    /* access modifiers changed from: private */
    public void invalidateFaceGridView() {
        StickyGridHeadersGridView stickyGridHeadersGridView = this.mFaceGridView;
        if (stickyGridHeadersGridView != null) {
            stickyGridHeadersGridView.postInvalidate();
        }
    }

    private void recordDisplayModeCountEvent(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("mode", str);
        GallerySamplingStatHelper.recordCountEvent("face", "face_change_display_mode", hashMap);
    }

    /* access modifiers changed from: private */
    public void refreshFaceCover() {
        if (this.mServerIdOfAlbum != null) {
            cancelTask(this.mCoverRefreshTask);
            this.mCoverRefreshTask = ThreadManager.getMiscPool().submit(new ThreadPool.Job<Boolean>() {
                public Boolean run(ThreadPool.JobContext jobContext) {
                    RectF[] rectFArr = new RectF[1];
                    String queryCoverImageOfOnePerson = FaceManager.queryCoverImageOfOnePerson(FacePageFragment.this.mServerIdOfAlbum, rectFArr);
                    if (TextUtils.isEmpty(queryCoverImageOfOnePerson) || queryCoverImageOfOnePerson.equals(FacePageFragment.this.mFaceCoverPath)) {
                        return false;
                    }
                    String unused = FacePageFragment.this.mFaceCoverPath = queryCoverImageOfOnePerson;
                    RectF unused2 = FacePageFragment.this.mFaceCoverRectF = rectFArr[0];
                    return true;
                }
            }, new FutureHandler<Boolean>() {
                public void onPostExecute(Future<Boolean> future) {
                    Boolean bool = future == null ? null : future.get();
                    if (bool != null && bool.booleanValue()) {
                        FacePageFragment.this.displayFaceCover();
                    }
                }
            });
        }
    }

    private void refreshFaceNameIfNeeded() {
        cancelTask(this.mNameRefreshTask);
        this.mNameRefreshTask = ThreadManager.getMiscPool().submit(new ThreadPool.Job<String>() {
            public String run(ThreadPool.JobContext jobContext) {
                return FaceManager.queryPersonName(FacePageFragment.this.mLocalIdOfAlbum);
            }
        }, new FutureHandler<String>() {
            public void onPostExecute(Future<String> future) {
                FacePageFragment facePageFragment;
                int i;
                if (FacePageFragment.this.isAdded() && future != null) {
                    String str = future.get();
                    if (str != null && !str.equals(FacePageFragment.this.mAlbumName)) {
                        String unused = FacePageFragment.this.mAlbumName = str;
                    }
                    TextView textView = (TextView) FacePageFragment.this.mFaceCoverHeader.findViewById(R.id.face_name_edit);
                    if (TextUtils.isEmpty(str)) {
                        facePageFragment = FacePageFragment.this;
                        i = R.string.face_name_input;
                    } else {
                        facePageFragment = FacePageFragment.this;
                        i = R.string.face_name_edit;
                    }
                    textView.setText(facePageFragment.getString(i));
                    FacePageFragment.this.setTitle();
                    FacePageFragment.this.invalidateFaceGridView();
                }
            }
        });
    }

    private void seeIfHasRecommendFace() {
        if (!TextUtils.isEmpty(this.mServerIdOfAlbum)) {
            ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                public Void run(ThreadPool.JobContext jobContext) {
                    if (!FacePageFragment.this.mHasRequestRecommendFace) {
                        FacePageFragment facePageFragment = FacePageFragment.this;
                        CheckoutRecommendPeople unused = facePageFragment.mCheckoutRecommendPeopleTask = new CheckoutRecommendPeople((Handler) null, facePageFragment);
                        FacePageFragment.this.mCheckoutRecommendPeopleTask.getRecommendPeopleFromNet(FacePageFragment.this.getServerIdOfAlbum());
                        boolean unused2 = FacePageFragment.this.mHasRequestRecommendFace = true;
                    }
                    return null;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setTitle() {
        if (this.mActivity != null) {
            this.mActivity.getActionBar().setTitle(getString(R.string.face_album_title, new Object[]{this.mAlbumName}));
        }
        ((TextView) this.mFaceCoverHeader.findViewById(R.id.face_name)).setText(this.mAlbumName);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private boolean shoeIgnoreAlert() {
        DialogUtil.showConfirmAlertWithCancel(this.mActivity, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                final ArrayList arrayList = new ArrayList();
                arrayList.add(Long.valueOf(FacePageFragment.this.mLocalIdOfAlbum));
                ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                    public Void run(ThreadPool.JobContext jobContext) {
                        FaceDataManager.safeIgnorePeopleByIds(arrayList);
                        FacePageFragment.this.mActivity.finish();
                        return null;
                    }
                });
            }
        }, (DialogInterface.OnClickListener) null, "", Html.fromHtml(this.mActivity.getString(R.string.ignore_alert_title)), this.mActivity.getString(17039370), 17039360);
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    private void showMergeHandler() {
        if (this.mFaceAlbumMergeHandler == null) {
            NormalPeopleFaceMediaSet normalPeopleFaceMediaSet = new NormalPeopleFaceMediaSet(this.mLocalIdOfAlbum, this.mServerIdOfAlbum, this.mAlbumName);
            this.mFaceMediasetsList = new ArrayList<>();
            this.mFaceMediasetsList.add(normalPeopleFaceMediaSet);
            this.mFaceAlbumMergeHandler = new FaceAlbumRenameHandler((Activity) this.mActivity, this.mFaceMediasetsList, confirmListener());
        }
        this.mFaceAlbumMergeHandler.show();
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void showRenameHandler() {
        if (this.mFaceAlbumRenameHandler == null) {
            this.mFaceMediaSet = new NormalPeopleFaceMediaSet(this.mLocalIdOfAlbum, getServerIdOfAlbum(), getAlbumName());
            this.mFaceAlbumRenameHandler = new FaceAlbumRenameHandler(this.mActivity, this.mFaceMediaSet, confirmListener(), !PeopleContactInfo.isUnKnownRelation(this.mRelationType));
        }
        this.mFaceAlbumRenameHandler.show();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void startRemoveFromFaceAlbum(FaceAlbumHandlerBase.FaceAlbumHandlerListener faceAlbumHandlerListener) {
        if (this.mRemoveFromFaceAlbumHandler == null) {
            this.mRemoveFromFaceAlbumHandler = new RemoveFromFaceAlbumHandler(this.mActivity, new NormalPeopleFaceMediaSet(this.mLocalIdOfAlbum, getServerIdOfAlbum(), getAlbumName()), faceAlbumHandlerListener);
        }
        this.mRemoveFromFaceAlbumHandler.show();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void toast2CreateBabyAlbumBeforeShare() {
        AnonymousClass4 r0 = new DialogInterface.OnClickListener() {
            /* JADX WARNING: type inference failed for: r5v2, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
            public void onClick(DialogInterface dialogInterface, int i) {
                FindFace2CreateBabyAlbum.gotoFillBabyAlbumInfo(FacePageFragment.this.mActivity, new NormalPeopleFaceMediaSet(FacePageFragment.this.mLocalIdOfAlbum, FacePageFragment.this.getServerIdOfAlbum(), FacePageFragment.this.getAlbumName()), FacePageFragment.this.mAdapter.getFirstFaceServerId());
                GallerySamplingStatHelper.recordCountEvent("face", "face_create_baby_album");
            }
        };
        try {
            new AlertDialog.Builder(this.mActivity).setCancelable(true).setIconAttribute(16843605).setMessage(R.string.begin_share_toast).setPositiveButton(this.mActivity.getString(17039370), r0).setNegativeButton(this.mActivity.getString(17039360), (DialogInterface.OnClickListener) null).setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                }
            }).create().show();
        } catch (Exception unused) {
        }
    }

    public void changeDisplayMode() {
        this.mAdapter.changeDisplayMode();
    }

    public void changeToNextPage() {
        PagerGridLayout pagerGridLayout = this.mRecommendFaceGroup;
        if (pagerGridLayout != null) {
            pagerGridLayout.changeToNextPage();
        }
    }

    public long getIsHasEverNotCreateBabyAlbumFromThis() {
        return FaceManager.queryBabyAlbumByPeopleId(this.mServerIdOfAlbum);
    }

    /* access modifiers changed from: protected */
    public Loader getLoader() {
        return getLoaderManager().getLoader(1);
    }

    public String getPageName() {
        return "face";
    }

    public String getServerIdOfAlbum() {
        return this.mServerIdOfAlbum;
    }

    public boolean isFaceDisplayMode() {
        return this.mAdapter.isFaceDisplayMode();
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    /* JADX WARNING: type inference failed for: r1v13, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle extras = this.mActivity.getIntent().getExtras();
        this.mServerIdOfAlbum = extras.getString("server_id_of_album");
        this.mLocalIdOfAlbum = Long.parseLong(extras.getString("local_id_of_album"));
        this.mFaceCoverPath = extras.getString("face_album_cover");
        this.mFaceCoverRectF = (RectF) extras.getParcelable("face_position_rect");
        this.mAlbumName = extras.getString("album_name");
        this.mFacePagePhotoLoaderCallback = new FacePagePhotoLoaderCallback();
        getLoaderManager().initLoader(1, (Bundle) null, this.mFacePagePhotoLoaderCallback);
        addHeaderView();
        setTitle();
        if (bundle != null) {
            this.mFaceMediaSet = (NormalPeopleFaceMediaSet) bundle.getParcelable("NormalPeopleFaceMediaset");
            if (this.mFaceMediaSet != null) {
                this.mFaceAlbumRenameHandler = new FaceAlbumRenameHandler(this.mActivity, this.mFaceMediaSet, confirmListener(), true ^ PeopleContactInfo.isUnKnownRelation(this.mRelationType));
            }
            this.mFaceMediasetsList = bundle.getParcelableArrayList("NormalPeopleFaceMediasetList");
            if (this.mFaceMediasetsList != null) {
                this.mFaceAlbumMergeHandler = new FaceAlbumRenameHandler((Activity) this.mActivity, this.mFaceMediasetsList, confirmListener());
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v3, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r4v6, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r4v9, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onActivityResult(int i, int i2, Intent intent) {
        PeopleContactInfo peopleContactInfo = null;
        if (i == 16) {
            PeopleContactInfo contactInfo = (intent == null || i2 != -1) ? null : InputFaceNameFragment.getContactInfo(this.mActivity, intent);
            FaceAlbumRenameHandler faceAlbumRenameHandler = this.mFaceAlbumRenameHandler;
            if (faceAlbumRenameHandler != null) {
                faceAlbumRenameHandler.finishWhenGetContact(contactInfo);
            }
            this.mFaceMediaSet = null;
        } else if (i == 17) {
            if (intent != null && i2 == -1) {
                peopleContactInfo = InputFaceNameFragment.getContactInfo(this.mActivity, intent);
            }
            RemoveFromFaceAlbumHandler removeFromFaceAlbumHandler = this.mRemoveFromFaceAlbumHandler;
            if (removeFromFaceAlbumHandler != null) {
                removeFromFaceAlbumHandler.finishWhenGetContact(peopleContactInfo);
            }
        } else if (i == 19) {
            PeopleContactInfo contactInfo2 = (intent == null || i2 != -1) ? null : InputFaceNameFragment.getContactInfo(this.mActivity, intent);
            FaceAlbumRenameHandler faceAlbumRenameHandler2 = this.mFaceAlbumMergeHandler;
            if (faceAlbumRenameHandler2 != null) {
                faceAlbumRenameHandler2.finishWhenGetContact(contactInfo2);
            }
            this.mFaceMediasetsList = null;
        } else if (i != 21) {
            super.onActivityResult(i, i2, intent);
        } else if (intent != null && i2 == -1 && intent.getBooleanExtra("all_faces_confirmed", false)) {
            this.mFaceGridView.removeFooterView(this.mFooterView);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        RecommendFaceGroupAdapter recommendFaceGroupAdapter = this.mRecommendFaceAdapter;
        if (recommendFaceGroupAdapter != null) {
            recommendFaceGroupAdapter.setOnLoadingCompleteListener((BindImageHelper.OnImageLoadingCompleteListener) null);
        }
        cancelTask(this.mNameRefreshTask);
        cancelTask(this.mCoverRefreshTask);
        FacePageAdapter facePageAdapter = this.mAdapter;
        if (facePageAdapter != null) {
            facePageAdapter.swapCursor((Cursor) null);
        }
    }

    public void onFinishCheckoutPeopleFace(int i) {
        this.mPeopleRecommendMediaSet.refreshRecommendInfo();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r4v9, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r0v10, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.face_page, viewGroup, false);
        this.mAdapter = new FacePageAdapter(this.mActivity);
        this.mFaceGridView = (StickyGridHeadersGridView) inflate.findViewById(R.id.grid);
        this.mFaceGridViewWrapper = new EditableListViewWrapperDeprecated(this.mFaceGridView);
        this.mFaceGridViewWrapper.setAdapter(this.mAdapter);
        this.mFaceGridViewWrapper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i;
                FacePageAdapter access$000 = FacePageFragment.this.mAdapter;
                ImageLoadParams imageLoadParams = new ImageLoadParams(access$000.getItemKey(i2), access$000.getLocalPath(i2), Config.ThumbConfig.get().sMicroTargetSize, access$000.getItemDecodeRectF(i2), i, access$000.getMimeType(i2), true, access$000.getFileLength(i2));
                IntentUtil.gotoPhotoPage((BaseMediaFragment) FacePageFragment.this, (ViewGroup) adapterView, GalleryContract.PeopleFace.ONE_PERSON_URI, i, access$000.getCount(), (String) null, FacePageFragment.this.getSelectioinArgs(), FacePageFragment.this.getOrderBy(), imageLoadParams, FacePageFragment.this.mAlbumName, true);
                GallerySamplingStatHelper.recordNumericPropertyEvent(FacePageFragment.this.getPageName(), "click_micro_thumb", (long) i2);
            }
        });
        this.mFaceGridView.setAreHeadersSticky(false);
        this.mFooterView = LayoutInflater.from(this.mActivity).inflate(R.layout.see_more_recommend_face_view, this.mFaceGridView, false);
        this.mRecommendFaceButtonContainer = this.mFooterView.findViewById(R.id.see_more_recommend_face_container);
        this.mRecommendFaceButtonContainer.findViewById(R.id.see_more_recommend_face).setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r0v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
            public void onClick(View view) {
                FacePageFragment.this.mPeopleRecommendMediaSet.refreshRecommendInfo();
                Intent intent = new Intent(FacePageFragment.this.mActivity, RecommendFacePageActivity.class);
                intent.putExtra("server_id_of_album", FacePageFragment.this.getServerIdOfAlbum());
                intent.putExtra("local_id_of_album", FacePageFragment.this.mLocalIdOfAlbum);
                intent.putExtra("album_name", FacePageFragment.this.getAlbumName());
                intent.putExtra("server_ids_of_faces", FacePageFragment.this.mPeopleRecommendMediaSet.getServerIdsIn());
                FacePageFragment.this.startActivityForResult(intent, 21);
                GallerySamplingStatHelper.recordCountEvent("face", "face_enter_recommend");
            }
        });
        this.mRelationType = this.mActivity.getIntent().getIntExtra("relationType", -1);
        if (PeopleContactInfo.isBabyRelation(this.mRelationType) && ApplicationHelper.supportShare()) {
            this.mShareButtonContainer = inflate.findViewById(R.id.share_container);
            this.mShareButtonContainer.setVisibility(0);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) this.mShareButtonContainer.findViewById(R.id.share_button);
            if (BuildUtil.isMiui10(this.mActivity)) {
                actionMenuItemView.setIcon(this.mActivity.getResources().getDrawable(R.drawable.v15_action_button_album_share_light));
            } else {
                actionMenuItemView.setIcon(this.mActivity.getResources().getDrawable(R.drawable.v18_action_button_album_share_light));
            }
            actionMenuItemView.findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r6v4, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
                public void onClick(View view) {
                    long isHasEverNotCreateBabyAlbumFromThis = FacePageFragment.this.getIsHasEverNotCreateBabyAlbumFromThis();
                    if (isHasEverNotCreateBabyAlbumFromThis == -1) {
                        FacePageFragment.this.toast2CreateBabyAlbumBeforeShare();
                        return;
                    }
                    UIHelper.showAlbumShareInfo(FacePageFragment.this.mActivity, new Path(isHasEverNotCreateBabyAlbumFromThis, false, true), 0);
                    GallerySamplingStatHelper.recordCountEvent("face", "face_enter_album_share");
                }
            });
        }
        this.mFaceGridViewWrapper.setChoiceMode(3);
        this.mListener = new ChoiceModeListener();
        this.mFaceGridViewWrapper.setMultiChoiceModeListener(this.mListener);
        return inflate;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_change_mode_to_face:
                changeDisplayMode();
                recordDisplayModeCountEvent("face");
                return true;
            case R.id.action_change_mode_to_photo:
                changeDisplayMode();
                recordDisplayModeCountEvent("photo");
                return true;
            case R.id.action_ignore:
                shoeIgnoreAlert();
                return true;
            case R.id.action_merge_to:
                showMergeHandler();
                return true;
            default:
                return false;
        }
    }

    public void onPageChanged(int i, int i2, boolean z) {
        if (z) {
            this.mFaceGridView.removeHeaderView(this.mRecommendFaceGroupHeader);
            return;
        }
        TextView textView = (TextView) this.mRecommendFaceGroupHeader.findViewById(R.id.face_recommend_group_number);
        if (i2 == 1) {
            this.mRecommendFaceGroupHeader.findViewById(R.id.group_divider).setVisibility(8);
            return;
        }
        textView.setText(getString(R.string.face_recommend_group_number, new Object[]{Integer.valueOf(i + 1), Integer.valueOf(i2)}));
    }

    public void onResume() {
        super.onResume();
        refreshFaceNameIfNeeded();
        seeIfHasRecommendFace();
        PagerGridLayout pagerGridLayout = this.mRecommendFaceGroup;
        if (pagerGridLayout != null) {
            pagerGridLayout.freshCurrentPage();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        NormalPeopleFaceMediaSet normalPeopleFaceMediaSet = this.mFaceMediaSet;
        if (normalPeopleFaceMediaSet != null) {
            bundle.putParcelable("NormalPeopleFaceMediaset", normalPeopleFaceMediaSet);
        }
        ArrayList<NormalPeopleFaceMediaSet> arrayList = this.mFaceMediasetsList;
        if (arrayList != null) {
            bundle.putParcelableArrayList("NormalPeopleFaceMediasetList", arrayList);
        }
    }

    public void onStop() {
        super.onStop();
        CheckoutRecommendPeople checkoutRecommendPeople = this.mCheckoutRecommendPeopleTask;
        if (checkoutRecommendPeople != null) {
            checkoutRecommendPeople.clearListener();
        }
    }
}
