package com.miui.gallery.share;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListAdapter;
import com.miui.gallery.R;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.baby.BabyInfo;
import com.miui.gallery.share.AlbumShareUIManager;
import com.miui.gallery.share.DBCache;
import com.miui.gallery.share.GridPreferenceBase;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.Comparator;
import java.util.List;
import miui.preference.PreferenceActivity;

public abstract class ShareAlbumBaseActivity extends PreferenceActivity implements GridPreferenceBase.OnItemClickListener {
    protected String mAlbumId;
    protected String mAlbumName;
    private final DBCache.OnDBCacheChangedListener<String, List<CloudUserCacheEntry>> mAlbumUserCacheListener = new DBCache.OnDBCacheChangedListener<String, List<CloudUserCacheEntry>>() {
        public void onDBCacheChanged(DBCache<String, List<CloudUserCacheEntry>> dBCache) {
            ShareAlbumBaseActivity.this.updateSharers();
        }
    };
    protected CloudSharerMediaSet mCloudSingleMediaSet;
    protected String mCreatorId;
    String mPath;
    protected ShareUserAdapterBase mShareUserGridAdapter;
    protected int mSharerCount = 0;
    protected GridPreferenceBase mSharerGridPreference;
    private final DBCache.OnDBCacheChangedListener<String, UserInfo> mUserInfoCacheListener = new DBCache.OnDBCacheChangedListener<String, UserInfo>() {
        public void onDBCacheChanged(DBCache<String, UserInfo> dBCache) {
            ShareAlbumBaseActivity.this.updateSharers();
        }
    };

    public static class UserCacheDescComparator implements Comparator<CloudUserCacheEntry> {
        public int compare(CloudUserCacheEntry cloudUserCacheEntry, CloudUserCacheEntry cloudUserCacheEntry2) {
            int i = (cloudUserCacheEntry.mCreateTime > cloudUserCacheEntry2.mCreateTime ? 1 : (cloudUserCacheEntry.mCreateTime == cloudUserCacheEntry2.mCreateTime ? 0 : -1));
            if (i == 0) {
                return 0;
            }
            return i < 0 ? 1 : -1;
        }
    }

    private void handleOrientationConfiguration(Configuration configuration) {
        if (configuration.orientation == 2) {
            this.mSharerGridPreference.setColumnCountAndWidth(getResources().getInteger(R.integer.share_grid_column_count_landscape), getResources().getDimensionPixelSize(R.dimen.share_user_item_width_landscape));
            return;
        }
        this.mSharerGridPreference.setColumnCountAndWidth(getResources().getInteger(R.integer.share_grid_column_count), getResources().getDimensionPixelSize(R.dimen.share_user_item_width));
    }

    /* access modifiers changed from: protected */
    public abstract ShareUserAdapterBase createShareUserAdapter();

    /* access modifiers changed from: protected */
    public abstract void doSyncFromServer(AlbumShareUIManager.OnCompletionListener<Void, Void> onCompletionListener);

    /* access modifiers changed from: protected */
    public abstract int getContentView();

    /* access modifiers changed from: protected */
    public CloudUserCacheEntry getOwnerEntry(String str) {
        BabyInfo babyInfo = this.mCloudSingleMediaSet.getBabyInfo();
        if (babyInfo == null) {
            return new CloudUserCacheEntry(this.mAlbumId, str, 0, (String) null, (String) null, (String) null, (String) null);
        }
        return new CloudUserCacheEntry(this.mAlbumId, str, 0, babyInfo.mRelation, babyInfo.mRelationText, "normal", (String) null);
    }

    /* access modifiers changed from: protected */
    public abstract String getPageName();

    /* access modifiers changed from: protected */
    public abstract int getPreferencesResourceId();

    /* access modifiers changed from: protected */
    public abstract List<CloudUserCacheEntry> getShareUsers();

    /* access modifiers changed from: protected */
    public abstract CloudUserCache getUserCache();

    /* access modifiers changed from: protected */
    public void initPreferences() {
        this.mSharerGridPreference = (GridPreferenceBase) findPreference("sharer_grid");
        this.mSharerGridPreference.setColumnCountAndWidth(getResources().getInteger(R.integer.share_grid_column_count), getResources().getDimensionPixelSize(R.dimen.share_user_item_width));
        this.mSharerGridPreference.setOnItemClickListener(this);
        this.mShareUserGridAdapter = createShareUserAdapter();
        this.mSharerGridPreference.setAdapter(this.mShareUserGridAdapter);
        handleOrientationConfiguration(getResources().getConfiguration());
    }

    public void onConfigurationChanged(Configuration configuration) {
        ShareAlbumBaseActivity.super.onConfigurationChanged(configuration);
        handleOrientationConfiguration(configuration);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ShareAlbumBaseActivity.super.onCreate(bundle);
        if (bundle == null) {
            bundle = getIntent().getExtras();
        }
        if (bundle != null) {
            this.mPath = bundle.getString("share_path");
        }
        if (TextUtils.isEmpty(this.mPath)) {
            Log.e("ShareAlbumBaseActivity", "album path is invalid:" + this.mPath);
            finish();
            return;
        }
        setContentView(getContentView());
        this.mCloudSingleMediaSet = Path.fromString(this.mPath).getMediaSet();
        this.mAlbumName = this.mCloudSingleMediaSet.getDisplayName();
        this.mAlbumId = this.mCloudSingleMediaSet.getAlbumId();
        if (this.mCloudSingleMediaSet.isSharerAlbum()) {
            this.mCreatorId = this.mCloudSingleMediaSet.getCreatorId();
        } else {
            this.mCreatorId = GalleryCloudUtils.getAccountName();
        }
        addPreferencesFromResource(getPreferencesResourceId());
        initPreferences();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        GridPreferenceBase gridPreferenceBase = this.mSharerGridPreference;
        if (gridPreferenceBase != null) {
            gridPreferenceBase.setAdapter((ListAdapter) null);
        }
        ShareAlbumBaseActivity.super.onDestroy();
    }

    public void onItemClick(GridPreferenceBase gridPreferenceBase, int i) {
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [miui.preference.PreferenceActivity, com.miui.gallery.share.ShareAlbumBaseActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onPause() {
        getUserCache().removeListener(this.mAlbumUserCacheListener);
        UserInfoCache.getInstance().removeListener(this.mUserInfoCacheListener);
        ShareAlbumBaseActivity.super.onPause();
        GallerySamplingStatHelper.recordPageEnd(this, getPageName());
        ImageLoader.getInstance().pause();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [miui.preference.PreferenceActivity, com.miui.gallery.share.ShareAlbumBaseActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        ShareAlbumBaseActivity.super.onResume();
        GallerySamplingStatHelper.recordPageStart(this, getPageName());
        ImageLoader.getInstance().resume();
        getUserCache().addListener(this.mAlbumUserCacheListener);
        UserInfoCache.getInstance().addListener(this.mUserInfoCacheListener);
        updateSharers();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        ShareAlbumBaseActivity.super.onSaveInstanceState(bundle);
        bundle.putString("share_path", this.mPath);
    }

    /* access modifiers changed from: protected */
    public void onUpdateProgressChanged(int i) {
    }

    /* access modifiers changed from: protected */
    public final void syncFromServer() {
        AnonymousClass3 r0 = new AlbumShareUIManager.OnCompletionListener<Void, Void>() {
            public void onCompletion(Void voidR, Void voidR2, int i, boolean z) {
                ShareAlbumBaseActivity.this.onUpdateProgressChanged(0);
            }
        };
        onUpdateProgressChanged(1);
        doSyncFromServer(r0);
    }

    /* access modifiers changed from: protected */
    public void updateSharers() {
        if (CloudUtils.isValidAlbumId(this.mAlbumId)) {
            SystemClock.uptimeMillis();
            List<CloudUserCacheEntry> shareUsers = getShareUsers();
            this.mShareUserGridAdapter.setShareUsers(shareUsers);
            this.mSharerCount = shareUsers.size();
        }
    }
}
