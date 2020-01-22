package com.miui.gallery.activity.facebaby;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.baby.BabyInfo;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.GalleryUtils;

public class SharerBabyAlbumSettingActivity extends BabyAlbumSettingActivityBase {
    private String mAccountName;
    private String mAlbumId;
    /* access modifiers changed from: private */
    public Future mRefreshFuture;
    /* access modifiers changed from: private */
    public String mSelfRelationText;
    private Future mSyncFuture;

    /* access modifiers changed from: private */
    public BabyInfo getBabyInfoFromDB() {
        return (BabyInfo) GalleryUtils.safeQuery(GalleryCloudUtils.SHARE_ALBUM_URI, new String[]{"babyInfoJson"}, "_id = ?", new String[]{Long.toString(this.mBabyAlbumLocalId.longValue())}, (String) null, new GalleryUtils.QueryHandler<BabyInfo>() {
            public BabyInfo handle(Cursor cursor) {
                if (cursor == null || !cursor.moveToNext()) {
                    return null;
                }
                return BabyInfo.fromJSON(cursor.getString(0));
            }
        });
    }

    private String getEntry(String str, int i, int i2) {
        String[] stringArray = getResources().getStringArray(i);
        String[] stringArray2 = getResources().getStringArray(i2);
        for (int i3 = 0; i3 < stringArray.length; i3++) {
            if (TextUtils.equals(stringArray[i3], str)) {
                return stringArray2[i3];
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public String getSelfRelationText() {
        if (TextUtils.isEmpty(this.mAlbumId)) {
            this.mAlbumId = CloudUtils.getDBShareAlbumByLocalId(String.valueOf(this.mBabyAlbumLocalId)).getAlbumId();
        }
        if (TextUtils.isEmpty(this.mAccountName)) {
            this.mAccountName = GalleryCloudUtils.getAccountName();
        }
        return (String) GalleryUtils.safeQuery(GalleryCloudUtils.SHARE_USER_URI, new String[]{"relationText"}, String.format("%s=? AND %s=?", new Object[]{"albumId", "userId"}), new String[]{this.mAlbumId, this.mAccountName}, (String) null, new GalleryUtils.QueryHandler<String>() {
            public String handle(Cursor cursor) {
                return (cursor == null || !cursor.moveToNext()) ? "" : cursor.getString(0);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshSelfRelationText(final boolean z) {
        Future future = this.mRefreshFuture;
        if (future == null || future.isCancelled()) {
            this.mRefreshFuture = ThreadManager.getMiscPool().submit(new ThreadPool.Job<String>() {
                public String run(ThreadPool.JobContext jobContext) {
                    return SharerBabyAlbumSettingActivity.this.getSelfRelationText();
                }
            }, new FutureHandler<String>() {
                public void onPostExecute(Future<String> future) {
                    if (!future.isCancelled()) {
                        String str = future.get();
                        if (TextUtils.isEmpty(str)) {
                            if (z) {
                                SharerBabyAlbumSettingActivity.this.syncShareInfoFromServer();
                            }
                        } else if (!TextUtils.equals(SharerBabyAlbumSettingActivity.this.mSelfRelationText, str)) {
                            String unused = SharerBabyAlbumSettingActivity.this.mSelfRelationText = str;
                            SharerBabyAlbumSettingActivity.this.mRelationPre.setValue(str);
                        }
                    }
                    Future unused2 = SharerBabyAlbumSettingActivity.this.mRefreshFuture = null;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void syncShareInfoFromServer() {
        if (!TextUtils.isEmpty(this.mAlbumId)) {
            this.mSyncFuture = AlbumShareUIManager.syncUserListForAlbumAsync(this.mAlbumId, true, new AlbumShareUIManager.OnCompletionListener<Void, Void>() {
                public void onCompletion(Void voidR, Void voidR2, int i, boolean z) {
                    if (i == 0 && !z) {
                        SharerBabyAlbumSettingActivity.this.refreshSelfRelationText(false);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public Intent getIntentToAutoUploadPage() {
        Intent intentToAutoUploadPage = super.getIntentToAutoUploadPage();
        intentToAutoUploadPage.putExtra("allow_to_reassociate", true);
        return intentToAutoUploadPage;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceResourceId() {
        return R.xml.sharer_baby_album_preferences;
    }

    /* access modifiers changed from: protected */
    public void justSaveInfo2DbByJson() {
        if (!this.mHaveSaveBabyInfo) {
            saveBabyInfo();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 0) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1 && intent != null) {
            BabyInfo babyInfo = (BabyInfo) intent.getParcelableExtra("baby-info");
            this.mPeopleId = babyInfo.mPeopleId;
            this.mIsAutoupdate = Boolean.valueOf(babyInfo.mAutoupdate);
            this.mFaceAlbumLocalId = Long.valueOf(intent.getLongExtra("associate_people_face_local_id", -1));
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        refreshSelfRelationText(true);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Future future = this.mRefreshFuture;
        if (future != null) {
            future.cancel();
            this.mRefreshFuture = null;
        }
        Future future2 = this.mSyncFuture;
        if (future2 != null) {
            future2.cancel();
            this.mSyncFuture = null;
        }
        super.onDestroy();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.facebaby.BabyAlbumSettingActivityBase, com.miui.gallery.activity.facebaby.SharerBabyAlbumSettingActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        GallerySamplingStatHelper.recordPageEnd(this, "album_baby_share_setting");
        saveBabyInfo();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.facebaby.BabyAlbumSettingActivityBase, com.miui.gallery.activity.facebaby.SharerBabyAlbumSettingActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        GallerySamplingStatHelper.recordPageStart(this, "album_baby_share_setting");
    }

    /* access modifiers changed from: protected */
    public void saveBabyInfo() {
        if (babyInfoChanged()) {
            final BabyInfo babyInfo = toBabyInfo(this.mPeopleId, 0);
            ThreadManager.getMiscPool().submit(new ThreadPool.Job<Void>() {
                public Void run(ThreadPool.JobContext jobContext) {
                    BabyInfo access$000 = SharerBabyAlbumSettingActivity.this.getBabyInfoFromDB();
                    if (access$000 != null) {
                        access$000.mAutoupdate = SharerBabyAlbumSettingActivity.this.mIsAutoupdate.booleanValue();
                        access$000.mPeopleId = SharerBabyAlbumSettingActivity.this.mPeopleId;
                    } else {
                        access$000 = babyInfo;
                    }
                    String transformToEditedColumnsElement = GalleryCloudUtils.transformToEditedColumnsElement(25);
                    Object[] objArr = new Object[12];
                    objArr[0] = "shareAlbum";
                    objArr[1] = "babyInfoJson";
                    String str = "";
                    objArr[2] = access$000 == null ? str : DatabaseUtils.sqlEscapeString(access$000.toJSON());
                    objArr[3] = "peopleId";
                    if (access$000 != null) {
                        str = access$000.mPeopleId;
                    }
                    objArr[4] = str;
                    objArr[5] = "editedColumns";
                    objArr[6] = "editedColumns";
                    objArr[7] = transformToEditedColumnsElement;
                    objArr[8] = transformToEditedColumnsElement;
                    objArr[9] = transformToEditedColumnsElement;
                    objArr[10] = "_id";
                    objArr[11] = SharerBabyAlbumSettingActivity.this.mBabyAlbumLocalId;
                    GalleryUtils.safeExec(String.format("update %s set %s=%s, %s='%s', %s=coalesce(replace(%s, '%s', '') || '%s', '%s') where %s=%s", objArr));
                    GalleryApp.sGetAndroidContext().getContentResolver().notifyChange(GalleryContract.Album.URI, (ContentObserver) null, true);
                    return null;
                }
            });
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("baby-info", babyInfo);
            intent.putExtras(bundle);
            setResult(12, intent);
            this.mHaveSaveBabyInfo = true;
        }
    }

    /* access modifiers changed from: protected */
    public void setPreferencesValue() {
        super.setPreferencesValue();
        this.mBabyNicknamePre.setValue(this.mNickName);
        this.mBabySexPre.setValue(getEntry(this.mSex, R.array.baby_sexy_value, R.array.baby_sexy));
        this.mRelationPre.setValue(this.mSelfRelationText);
        this.mChooseDate.setValue(this.mBirthday);
    }
}
