package com.miui.gallery.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.core.SdkHelper;
import com.miui.gallery.activity.facebaby.BabyLockWallpaperSettingActivity;
import com.miui.gallery.adapter.AlbumDetailSimpleAdapter;
import com.miui.gallery.adapter.AlbumDetailTimeLineAdapter;
import com.miui.gallery.adapter.BabyAlbumDetailTimeLineAdapter;
import com.miui.gallery.cloud.baby.BabyAlbumUtils;
import com.miui.gallery.cloud.baby.BabyInfo;
import com.miui.gallery.model.SendToCloudFolderItem;
import com.miui.gallery.picker.PickGalleryActivity;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.ShareAlbumManager;
import com.miui.gallery.provider.deprecated.ThumbnailInfo;
import com.miui.gallery.ui.BabyAlbumDetailPageScrollView;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.DialogUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.baby.BabyAlbumRecommendationFinder;
import com.miui.gallery.util.baby.BabyFaceFinder;
import com.miui.gallery.util.baby.CopyFaceAlbumItemsToBabyAlbumTask;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.gallery.widget.SortByHeader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import java.util.ArrayList;
import miui.R;

public class BabyAlbumDetailFragment extends AlbumDetailFragmentBase {
    /* access modifiers changed from: private */
    public boolean isStickyGridViewAtBottom = false;
    /* access modifiers changed from: private */
    public boolean isStickyGridViewAtTop = true;
    /* access modifiers changed from: private */
    public ActionBarHelper mActionHelper = new ActionBarHelper();
    /* access modifiers changed from: private */
    public BabyAlbumDetailTimeLineAdapter mBabyAlbumAdapter;
    /* access modifiers changed from: private */
    public String mBabyAlbumPeopleServerId;
    /* access modifiers changed from: private */
    public BabyInfo mBabyInfo;
    /* access modifiers changed from: private */
    public BabyAlbumDetailPageScrollView mContentView;
    /* access modifiers changed from: private */
    public View mGotoPickHeaderBackgroundMaskView;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public boolean mIsNightMode;
    private MyRecommendationPhotoHelper mMyRecommendationPhotoHelper = new MyRecommendationPhotoHelper();
    /* access modifiers changed from: private */
    public View mRecommendFacePhoto2ThisAlbumView;
    private boolean mShowInPhotosTab;
    private ThumbnailInfo mThumbnailInfo;

    class ActionBarHelper {
        private final int[] ATTRS = {R.attr.actionBarBackground};
        private Drawable mDefaultActionBarBg;
        private ImageView mHomeIcon;
        private TextView mHomeText;
        /* access modifiers changed from: private */
        public boolean mIsShowTranslucentStatusBar = true;

        ActionBarHelper() {
        }

        /* access modifiers changed from: private */
        public View inflateTitle() {
            TypedArray obtainStyledAttributes = BabyAlbumDetailFragment.this.mActivity.obtainStyledAttributes(this.ATTRS);
            this.mDefaultActionBarBg = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            View inflate = BabyAlbumDetailFragment.this.mActivity.getLayoutInflater().inflate(com.miui.gallery.R.layout.baby_album_page_title, (ViewGroup) null);
            inflate.setLayoutParams(new ActionBar.LayoutParams(-1, -1));
            this.mHomeIcon = (ImageView) inflate.findViewById(com.miui.gallery.R.id.home_arrow);
            this.mHomeIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BabyAlbumDetailFragment.this.mActivity.finish();
                }
            });
            this.mHomeText = (TextView) inflate.findViewById(com.miui.gallery.R.id.home_text);
            this.mHomeText.setText(BabyAlbumDetailFragment.this.mAlbumName);
            BabyAlbumDetailFragment.this.mActivity.getActionBar().setDisplayOptions(16, 16);
            BabyAlbumDetailFragment.this.mActivity.getActionBar().setCustomView(inflate);
            return inflate;
        }

        private void refreshHomeIconAndColor() {
            if (this.mIsShowTranslucentStatusBar || BabyAlbumDetailFragment.this.mIsNightMode) {
                this.mHomeIcon.setImageResource(R.drawable.action_bar_back_dark);
                this.mHomeText.setTextColor(BabyAlbumDetailFragment.this.mActivity.getResources().getColor(com.miui.gallery.R.color.action_bar_title_text_color_dark));
                return;
            }
            ImageView imageView = this.mHomeIcon;
            if (imageView != null) {
                imageView.setImageResource(R.drawable.action_bar_back_light);
                this.mHomeText.setTextColor(BabyAlbumDetailFragment.this.mActivity.getResources().getColor(com.miui.gallery.R.color.action_bar_title_text_color_light));
            }
        }

        private void refreshTopBar(boolean z) {
            this.mIsShowTranslucentStatusBar = z;
            refreshHomeIconAndColor();
            BabyAlbumDetailFragment.this.mActivity.getActionBar().setBackgroundDrawable(getTopBarBackground());
        }

        /* access modifiers changed from: private */
        public void setNormalStyleActionBar() {
            refreshTopBar(false);
        }

        /* access modifiers changed from: private */
        public void setNullStyleActionBar() {
            refreshTopBar(true);
        }

        public Drawable getTopBarBackground() {
            if (this.mIsShowTranslucentStatusBar) {
                return null;
            }
            return this.mDefaultActionBarBg;
        }
    }

    public class MyOnScrollListener implements AbsListView.OnScrollListener {
        public MyOnScrollListener() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i2 <= 0 || i != 0 || absListView.getChildAt(i).getTop() < 0) {
                boolean unused = BabyAlbumDetailFragment.this.isStickyGridViewAtTop = false;
            } else {
                boolean unused2 = BabyAlbumDetailFragment.this.isStickyGridViewAtTop = true;
            }
            if (i <= 0 || i + i2 != i3) {
                boolean unused3 = BabyAlbumDetailFragment.this.isStickyGridViewAtBottom = false;
            } else {
                boolean unused4 = BabyAlbumDetailFragment.this.isStickyGridViewAtBottom = true;
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    public class MyRecommendationPhotoHelper {
        private SparseArray<Boolean> mAllBabyAlbumPhoto;
        private BabyAlbumRecommendationFinder mBabyAlbumRecommendationFinder;
        private BabyFaceFinder mBabyFaceFinder;
        private BabyAlbumRecommendationFinder.RecommendationDatas mRecommendPhotoDatas;

        public MyRecommendationPhotoHelper() {
        }

        /* access modifiers changed from: private */
        public void disappearRecommendationView() {
            BabyAlbumDetailFragment.this.mRecommendFacePhoto2ThisAlbumView.setVisibility(8);
        }

        private void displayRecommendationView() {
            BabyAlbumDetailFragment.this.mRecommendFacePhoto2ThisAlbumView.setVisibility(0);
        }

        /* JADX WARNING: type inference failed for: r2v9, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x007a  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0083  */
        public void onBabyPicturesPicked(Intent intent) {
            ArrayList arrayList = (ArrayList) intent.getSerializableExtra("pick-result-data");
            if (arrayList != null && !arrayList.isEmpty()) {
                final boolean z = true;
                Cursor query = BabyAlbumDetailFragment.this.mActivity.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, new String[]{"*"}, String.format("_id IN (%s)", new Object[]{TextUtils.join(",", arrayList)}), (String[]) null, (String) null);
                if (BabyAlbumDetailFragment.this.mBabyInfo != null && !BabyAlbumDetailFragment.this.mBabyInfo.mAutoupdate) {
                    BabyAlbumRecommendationFinder.RecommendationDatas recommendationDatas = this.mRecommendPhotoDatas;
                    if (!(recommendationDatas == null || recommendationDatas.totalFaceCountInFaceAlbum == arrayList.size())) {
                        if (!GalleryPreferences.Baby.getHasShowAutoUpdateTipWithoutSelectingAll(BabyAlbumDetailFragment.this.mAlbumName)) {
                            GalleryPreferences.Baby.setHasShowAutoUpdateTipWithoutSelectingAll(BabyAlbumDetailFragment.this.mAlbumName);
                        }
                    }
                    AnonymousClass5 r15 = new CopyFaceAlbumItemsToBabyAlbumTask.ProgressEndListener() {
                        public void onProgressEnd(int i) {
                            if (z) {
                                DialogUtil.showConfirmAlertWithCancel(BabyAlbumDetailFragment.this.getActivity(), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        BabyAlbumDetailFragment.this.mBabyInfo.mAutoupdate = true;
                                        BabyAlbumUtils.saveBabyInfo(Long.toString(BabyAlbumDetailFragment.this.getOriginalAlbumId()), BabyAlbumDetailFragment.this.mBabyInfo, BabyAlbumDetailFragment.this.mIsOtherShareAlbum);
                                        MyRecommendationPhotoHelper.this.disappearRecommendationView();
                                    }
                                }, (DialogInterface.OnClickListener) null, BabyAlbumDetailFragment.this.mActivity.getString(com.miui.gallery.R.string.add_automatically_alert_title), BabyAlbumDetailFragment.this.mActivity.getString(com.miui.gallery.R.string.add_automatically_alert_msg, new Object[]{BabyAlbumDetailFragment.this.mBabyInfo.mNickName}), BabyAlbumDetailFragment.this.mActivity.getString(17039370), 17039360);
                            }
                        }
                    };
                    CopyFaceAlbumItemsToBabyAlbumTask instance = CopyFaceAlbumItemsToBabyAlbumTask.instance(BabyAlbumDetailFragment.this.mActivity, query, new SendToCloudFolderItem(0, String.valueOf(!BabyAlbumDetailFragment.this.mIsOtherShareAlbum ? ShareAlbumManager.getOriginalAlbumId(BabyAlbumDetailFragment.this.mAlbumId) : BabyAlbumDetailFragment.this.mAlbumId), BabyAlbumDetailFragment.this.mIsOtherShareAlbum, BabyAlbumDetailFragment.this.mAlbumName, (BabyInfo) null), 0, com.miui.gallery.R.string.adding);
                    instance.setProgressFinishListener(r15);
                    instance.execute(new Void[0]);
                }
                z = false;
                AnonymousClass5 r152 = new CopyFaceAlbumItemsToBabyAlbumTask.ProgressEndListener() {
                    public void onProgressEnd(int i) {
                        if (z) {
                            DialogUtil.showConfirmAlertWithCancel(BabyAlbumDetailFragment.this.getActivity(), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    BabyAlbumDetailFragment.this.mBabyInfo.mAutoupdate = true;
                                    BabyAlbumUtils.saveBabyInfo(Long.toString(BabyAlbumDetailFragment.this.getOriginalAlbumId()), BabyAlbumDetailFragment.this.mBabyInfo, BabyAlbumDetailFragment.this.mIsOtherShareAlbum);
                                    MyRecommendationPhotoHelper.this.disappearRecommendationView();
                                }
                            }, (DialogInterface.OnClickListener) null, BabyAlbumDetailFragment.this.mActivity.getString(com.miui.gallery.R.string.add_automatically_alert_title), BabyAlbumDetailFragment.this.mActivity.getString(com.miui.gallery.R.string.add_automatically_alert_msg, new Object[]{BabyAlbumDetailFragment.this.mBabyInfo.mNickName}), BabyAlbumDetailFragment.this.mActivity.getString(17039370), 17039360);
                        }
                    }
                };
                CopyFaceAlbumItemsToBabyAlbumTask instance2 = CopyFaceAlbumItemsToBabyAlbumTask.instance(BabyAlbumDetailFragment.this.mActivity, query, new SendToCloudFolderItem(0, String.valueOf(!BabyAlbumDetailFragment.this.mIsOtherShareAlbum ? ShareAlbumManager.getOriginalAlbumId(BabyAlbumDetailFragment.this.mAlbumId) : BabyAlbumDetailFragment.this.mAlbumId), BabyAlbumDetailFragment.this.mIsOtherShareAlbum, BabyAlbumDetailFragment.this.mAlbumName, (BabyInfo) null), 0, com.miui.gallery.R.string.adding);
                instance2.setProgressFinishListener(r152);
                instance2.execute(new Void[0]);
            }
        }

        /* JADX WARNING: type inference failed for: r1v9, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
        /* access modifiers changed from: private */
        public void onSaveChoosedPeopleAndGo2ChooseFace(Intent intent) {
            String stringExtra = intent.getStringExtra("local_id_of_album");
            if (!TextUtils.isEmpty(stringExtra)) {
                BabyAlbumDetailFragment.this.mBabyInfo.mPeopleId = intent.getStringExtra("server_id_of_album");
                String valueOf = String.valueOf(BabyAlbumDetailFragment.this.mIsOtherShareAlbum ? ShareAlbumManager.getOriginalAlbumId(BabyAlbumDetailFragment.this.mAlbumId) : BabyAlbumDetailFragment.this.mAlbumId);
                BabyAlbumUtils.saveBabyInfo(valueOf, BabyAlbumDetailFragment.this.mBabyInfo, BabyAlbumDetailFragment.this.mIsOtherShareAlbum);
                ContentValues contentValues = new ContentValues();
                contentValues.put("peopleId", BabyAlbumDetailFragment.this.mBabyInfo.mPeopleId);
                BabyAlbumDetailFragment.this.mActivity.getContentResolver().update(BabyAlbumDetailFragment.this.mIsOtherShareAlbum ? GalleryContract.ShareAlbum.OTHER_SHARE_URI : GalleryContract.Cloud.CLOUD_URI, contentValues, String.format("%s=%s", new Object[]{"_id", valueOf}), (String[]) null);
                IntentUtil.pickFace(BabyAlbumDetailFragment.this.mActivity, BabyAlbumDetailFragment.this.mBabyInfo.mNickName, BabyAlbumDetailFragment.this.mBabyInfo.mPeopleId, stringExtra, (ArrayList<Long>) null, -1, false);
            }
        }

        /* access modifiers changed from: private */
        public void refreshRecommandBar(final BabyAlbumRecommendationFinder.RecommendationDatas recommendationDatas) {
            int i;
            int i2;
            if (!recommendationDatas.hasNewRecommendation()) {
                disappearRecommendationView();
                return;
            }
            this.mRecommendPhotoDatas = recommendationDatas;
            if (!(GalleryPreferences.Baby.getLastClickBabyPhotosRecommandationTime(String.valueOf(BabyAlbumDetailFragment.this.mAlbumId)) == 0)) {
                i2 = com.miui.gallery.R.plurals.baby_album_sharer_recommand_new_pictures;
                i = recommendationDatas.getRecommendationSize();
            } else {
                i2 = com.miui.gallery.R.plurals.baby_album_sharer_recommand_old_pictures;
                i = recommendationDatas.getRecommendationSize();
            }
            String quantityString = BabyAlbumDetailFragment.this.mActivity.getResources().getQuantityString(i2, i, new Object[]{Integer.valueOf(i), BabyAlbumDetailFragment.this.mBabyInfo.mNickName});
            BabyAlbumDetailFragment.this.mRecommendFacePhoto2ThisAlbumView.setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
                public void onClick(View view) {
                    IntentUtil.pickFace(BabyAlbumDetailFragment.this.mActivity, BabyAlbumDetailFragment.this.mBabyInfo.mNickName, recommendationDatas.peopleServerId, recommendationDatas.peopleLocalId, recommendationDatas.ids, -1, false);
                    GalleryPreferences.Baby.setLastClickBabyPhotosRecommandationTime(String.valueOf(BabyAlbumDetailFragment.this.mAlbumId));
                }
            });
            ((TextView) BabyAlbumDetailFragment.this.mRecommendFacePhoto2ThisAlbumView).setText(quantityString);
            displayRecommendationView();
        }

        /* access modifiers changed from: private */
        public void refreshRecommandBar(Boolean bool) {
            if (!bool.booleanValue()) {
                disappearRecommendationView();
                return;
            }
            String string = BabyAlbumDetailFragment.this.mActivity.getString(com.miui.gallery.R.string.baby_album_sharer_recommand_face, new Object[]{BabyAlbumDetailFragment.this.mBabyInfo.mNickName});
            BabyAlbumDetailFragment.this.mRecommendFacePhoto2ThisAlbumView.setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: type inference failed for: r5v3, types: [com.miui.gallery.activity.BaseActivity, android.app.Activity] */
                public void onClick(View view) {
                    IntentUtil.pickPeople(BabyAlbumDetailFragment.this.mActivity, BabyAlbumDetailFragment.this.mActivity.getString(com.miui.gallery.R.string.choose_some_people, new Object[]{BabyAlbumDetailFragment.this.mBabyInfo.mNickName}));
                    GalleryPreferences.Baby.setLastClickPeopleRecommandationTime(String.valueOf(BabyAlbumDetailFragment.this.mAlbumId));
                }
            });
            ((TextView) BabyAlbumDetailFragment.this.mRecommendFacePhoto2ThisAlbumView).setText(string);
            displayRecommendationView();
        }

        /* access modifiers changed from: private */
        public void seeIfHasRecommendationPhotoFromFaceAlbum(Cursor cursor) {
            if (BabyAlbumDetailFragment.this.mBabyInfo != null) {
                if (BabyAlbumDetailFragment.this.mIsOtherShareAlbum && TextUtils.isEmpty(BabyAlbumDetailFragment.this.mBabyInfo.mPeopleId)) {
                    if (this.mBabyFaceFinder == null) {
                        this.mBabyFaceFinder = new BabyFaceFinder();
                        this.mBabyFaceFinder.setBabyAlbumsFoundListener(new BabyFaceFinder.BabyAlbumsFoundListener() {
                            public void onBabyAlbumsFound(final Boolean bool) {
                                BabyAlbumDetailFragment.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        if (BabyAlbumDetailFragment.this.mActivity != null && !BabyAlbumDetailFragment.this.mActivity.isFinishing()) {
                                            MyRecommendationPhotoHelper.this.refreshRecommandBar(bool);
                                        }
                                    }
                                });
                            }
                        });
                    }
                    this.mBabyFaceFinder.startFindFace(String.valueOf(BabyAlbumDetailFragment.this.mAlbumId));
                } else if (TextUtils.isEmpty(BabyAlbumDetailFragment.this.mBabyAlbumPeopleServerId) || BabyAlbumDetailFragment.this.mBabyInfo.mAutoupdate) {
                    disappearRecommendationView();
                } else {
                    if (this.mBabyAlbumRecommendationFinder == null) {
                        this.mBabyAlbumRecommendationFinder = new BabyAlbumRecommendationFinder(BabyAlbumDetailFragment.this.mBabyAlbumPeopleServerId);
                        this.mBabyAlbumRecommendationFinder.setRecommendationFoundListener(new BabyAlbumRecommendationFinder.RecommendationFoundListener() {
                            public void onRecommendationFound(final BabyAlbumRecommendationFinder.RecommendationDatas recommendationDatas) {
                                BabyAlbumDetailFragment.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        if (BabyAlbumDetailFragment.this.mActivity != null && !BabyAlbumDetailFragment.this.mActivity.isFinishing()) {
                                            MyRecommendationPhotoHelper.this.refreshRecommandBar(recommendationDatas);
                                        }
                                    }
                                });
                            }
                        });
                    }
                    this.mAllBabyAlbumPhoto = new SparseArray<>();
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        String string = cursor.getString(cursor.getColumnIndex("title"));
                        if (!TextUtils.isEmpty(string)) {
                            this.mAllBabyAlbumPhoto.append(string.hashCode(), true);
                        }
                        cursor.moveToNext();
                    }
                    this.mBabyAlbumRecommendationFinder.findRecommendation(this.mAllBabyAlbumPhoto, String.valueOf(BabyAlbumDetailFragment.this.mAlbumId));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public long getOriginalAlbumId() {
        return this.mIsOtherShareAlbum ? ShareAlbumManager.getOriginalAlbumId(this.mAlbumId) : this.mAlbumId;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void gotoPickHeaderBackground() {
        Intent intent = new Intent(this.mActivity, PickGalleryActivity.class);
        intent.putExtra("pick-upper-bound", 1);
        intent.putExtra("pick-need-id", true);
        startActivityForResult(intent, 18);
    }

    private void intialFaceHeader(BabyAlbumDetailFaceHeaderItem babyAlbumDetailFaceHeaderItem, BabyInfo babyInfo, ThumbnailInfo thumbnailInfo, String str, String str2) {
        this.mBabyAlbumAdapter.setAlbumId(getOriginalAlbumId());
        this.mBabyAlbumAdapter.setPeopleServerId(str2);
        this.mBabyAlbumAdapter.setFaceHeader(babyInfo, thumbnailInfo, str, babyAlbumDetailFaceHeaderItem, this.mGotoPickHeaderBackgroundMaskView, new View.OnClickListener() {
            public void onClick(View view) {
                if (!BabyAlbumDetailFragment.this.mIsOtherShareAlbum && BabyAlbumDetailFragment.this.mActionHelper.mIsShowTranslucentStatusBar) {
                    BabyAlbumDetailFragment.this.showMenuDialog();
                }
            }
        }, this.mIsOtherShareAlbum);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    private void setAsBabyLockWallpaper() {
        Intent intent = new Intent(this.mActivity, BabyLockWallpaperSettingActivity.class);
        intent.putExtra("album_checked_by_default", String.valueOf(getOriginalAlbumId()));
        intent.putExtra("is_other_shared", this.mIsOtherShareAlbum);
        startActivity(intent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r0 = r2.mThumbnailInfo;
     */
    private void setBabyAlbumBg(final long j) {
        final ThumbnailInfo thumbnailInfo;
        if (j >= 0 && thumbnailInfo != null) {
            new AsyncTask<Void, Void, String>() {
                /* access modifiers changed from: protected */
                public String doInBackground(Void... voidArr) {
                    return thumbnailInfo.setBgImage(j);
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        BabyAlbumDetailFragment.this.mBabyAlbumAdapter.rebindHeaderPic(str);
                    }
                }
            }.execute(new Void[0]);
        }
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void showMenuDialog() {
        new AlertDialog.Builder(this.mActivity).setSingleChoiceItems(new String[]{this.mActivity.getString(com.miui.gallery.R.string.baby_album_change_background)}, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (i == 0) {
                    BabyAlbumDetailFragment.this.gotoPickHeaderBackground();
                    GallerySamplingStatHelper.recordCountEvent("baby", "baby_change_head_background");
                    return;
                }
                throw new IllegalStateException("unknown item clicked: " + i);
            }
        }).create().show();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public AlbumDetailTimeLineAdapter getAdapter() {
        if (this.mBabyAlbumAdapter == null) {
            this.mBabyAlbumAdapter = new BabyAlbumDetailTimeLineAdapter(this.mActivity);
            if (isOthersShareAlbum()) {
                this.mBabyAlbumAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.OTHER_SHARE_BABY);
            } else {
                this.mBabyAlbumAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.BABY);
            }
        }
        return this.mBabyAlbumAdapter;
    }

    /* access modifiers changed from: protected */
    public String getCreatorIdByPosition(int i) {
        return this.mBabyAlbumAdapter.getCreatorId(i);
    }

    /* access modifiers changed from: protected */
    public String getCurrentSortOrder() {
        return "alias_create_time DESC ";
    }

    /* access modifiers changed from: protected */
    public int getLayoutSource() {
        return com.miui.gallery.R.layout.baby_album_detail;
    }

    public String getPageName() {
        return "baby";
    }

    /* access modifiers changed from: protected */
    public Uri getUri() {
        return getUri(SortByHeader.SortBy.DATE);
    }

    /* access modifiers changed from: protected */
    public void mayDoAdditionalWork(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            if (cursor.getCount() == 1) {
                cursor.moveToNext();
            } else {
                cursor.move(2);
            }
            this.mBabyAlbumAdapter.firstBindHeaderPic(cursor);
            this.mMyRecommendationPhotoHelper.seeIfHasRecommendationPhotoFromFaceAlbum(cursor);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* access modifiers changed from: protected */
    public boolean needEnableAutoUpload() {
        return !SyncUtil.isGalleryCloudSyncable(this.mActivity);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList arrayList;
        if (i != 12) {
            if (i != 14) {
                if (i != 18) {
                    if (i != 31) {
                        super.onActivityResult(i, i2, intent);
                    } else if (i2 == -1 && intent != null) {
                        this.mMyRecommendationPhotoHelper.onBabyPicturesPicked(intent);
                    }
                } else if (i2 == -1 && intent != null && (arrayList = (ArrayList) intent.getSerializableExtra("pick-result-data")) != null && !arrayList.isEmpty()) {
                    setBabyAlbumBg(((Long) arrayList.get(0)).longValue());
                    this.mGotoPickHeaderBackgroundMaskView.setVisibility(8);
                }
            } else if (i2 == -1 && intent != null) {
                this.mMyRecommendationPhotoHelper.onSaveChoosedPeopleAndGo2ChooseFace(intent);
            }
        } else if (intent != null) {
            this.mThumbnailInfo = new ThumbnailInfo(getOriginalAlbumId(), this.mIsOtherShareAlbum, intent.getStringExtra("thumbnail_info_of_baby"));
            this.mBabyAlbumAdapter.resetBabyInfoAndThumbnailInfo((BabyInfo) intent.getExtras().getParcelable("baby-info"), this.mThumbnailInfo);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(BuildUtil.isMiui10(this.mActivity) ? com.miui.gallery.R.menu.v15_baby_album : com.miui.gallery.R.menu.v18_baby_album, menu);
        if (!ApplicationHelper.supportShare()) {
            menu.findItem(com.miui.gallery.R.id.menu_share).setVisible(false);
        }
        MenuItem findItem = menu.findItem(com.miui.gallery.R.id.menu_set_as_baby_lock_wallpaper);
        if (findItem == null) {
            return;
        }
        if (!SdkHelper.IS_MIUI || !BuildUtil.isDefaultLockStyle()) {
            findItem.setVisible(false);
        } else {
            findItem.setVisible(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onEnterActionMode() {
        if (this.mShowInPhotosTab) {
            ImageSelectionTipFragment.showImageSelectionTipDialogIfNecessary(getActivity());
        }
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        this.mIsNightMode = MiscUtil.isNightMode(getActivity());
        Intent intent = this.mActivity.getIntent();
        this.mShowInPhotosTab = (intent.getLongExtra("attributes", 0) & 4) != 0;
        this.mContentView = (BabyAlbumDetailPageScrollView) onInflateView.findViewById(com.miui.gallery.R.id.content);
        this.mRecommendFacePhoto2ThisAlbumView = onInflateView.findViewById(com.miui.gallery.R.id.recommend_face_photo_to_this_album);
        this.mBabyAlbumPeopleServerId = intent.getStringExtra("people_id");
        this.mAlbumDetailGridViewWrapper.setAdapter(getAdapter());
        this.mGotoPickHeaderBackgroundMaskView = onInflateView.findViewById(com.miui.gallery.R.id.goto_pick_header_background_mask);
        this.mThumbnailInfo = new ThumbnailInfo(getOriginalAlbumId(), this.mIsOtherShareAlbum, intent.getStringExtra("thumbnail_info_of_baby"));
        this.mBabyInfo = BabyInfo.fromJSON(intent.getStringExtra("baby_info"));
        intialFaceHeader((BabyAlbumDetailFaceHeaderItem) onInflateView.findViewById(com.miui.gallery.R.id.face_header_item), this.mBabyInfo, this.mThumbnailInfo, intent.getStringExtra("baby_sharer_info"), this.mBabyAlbumPeopleServerId);
        this.mAlbumDetailGridViewWrapper.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true, new MyOnScrollListener()));
        this.mContentView.setInnerScollerMessager(new BabyAlbumDetailPageScrollView.InnerScollerStateChangedMessager() {
            public boolean disappearFloatingView() {
                if (BabyAlbumDetailFragment.this.mGotoPickHeaderBackgroundMaskView.getVisibility() != 0) {
                    return false;
                }
                BabyAlbumDetailFragment.this.mGotoPickHeaderBackgroundMaskView.setVisibility(8);
                return true;
            }

            public View getRecommendFaceButton() {
                return BabyAlbumDetailFragment.this.mRecommendFacePhoto2ThisAlbumView;
            }

            public boolean isScoll2Top() {
                return BabyAlbumDetailFragment.this.isStickyGridViewAtTop;
            }

            public void setNormalActionBarBackground() {
                BabyAlbumDetailFragment.this.mActionHelper.setNormalStyleActionBar();
            }

            public void setNullActionBarBackground() {
                BabyAlbumDetailFragment.this.mActionHelper.setNullStyleActionBar();
            }
        });
        View unused = this.mActionHelper.inflateTitle();
        this.mActionHelper.setNullStyleActionBar();
        this.mContentView.post(new Runnable() {
            public void run() {
                BabyAlbumDetailFragment.this.mContentView.setTopViewHeight(BabyAlbumDetailFragment.this.mActivity.getActionBar().getHeight());
            }
        });
        return onInflateView;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r14v6, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case com.miui.gallery.R.id.menu_baby_info:
                this.mBabyAlbumAdapter.gotoBabyInfoSettingPage(this.mIsOtherShareAlbum);
                GallerySamplingStatHelper.recordCountEvent("baby", "baby_edit_baby_info");
                return true;
            case com.miui.gallery.R.id.menu_send_shortcut:
                Bitmap faceImageOfFaceHeaderItem = this.mBabyAlbumAdapter.getFaceImageOfFaceHeaderItem();
                Intent intent = this.mActivity.getIntent();
                IntentUtil.createShortCutForBabyAlbum(this.mActivity, this.mIsOtherShareAlbum, this.mAlbumId, this.mAlbumName, faceImageOfFaceHeaderItem, intent.getStringExtra("people_id"), intent.getStringExtra("thumbnail_info_of_baby"), intent.getStringExtra("baby_info"), intent.getStringExtra("baby_sharer_info"));
                ToastUtils.makeText((Context) this.mActivity, (CharSequence) this.mActivity.getString(com.miui.gallery.R.string.success_create_quick_icon_on_desk));
                GallerySamplingStatHelper.recordCountEvent("baby", "baby_send_shortcut");
                return true;
            case com.miui.gallery.R.id.menu_set_as_baby_lock_wallpaper:
                setAsBabyLockWallpaper();
                GallerySamplingStatHelper.recordCountEvent("baby", "baby_set_lock_wallpaper");
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
