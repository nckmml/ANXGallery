package com.miui.gallery.ui;

import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.cloudcontrol.strategies.AlbumsStrategy;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;

/* compiled from: AlbumDetailFragment */
class MenuStatusManager {
    private String mAlbumServerId;
    private boolean mAlbumUnwriteable;
    private boolean mAutoUpload;
    private Context mContext;
    private boolean mIsManualRenameRestricted;
    private boolean mIsOtherShareAlbum;
    private String mLocalPath;
    private boolean mShowInOtherAlbums;
    private boolean mShowInPhotosTab;

    MenuStatusManager(Context context, String str, long j, boolean z, boolean z2, String str2) {
        this.mContext = context;
        this.mAlbumServerId = str;
        this.mLocalPath = str2;
        boolean z3 = true;
        this.mAutoUpload = (1 & j) != 0;
        this.mShowInPhotosTab = (4 & j) != 0;
        this.mShowInOtherAlbums = (j & 64) != 0;
        this.mAlbumUnwriteable = z;
        this.mIsOtherShareAlbum = z2;
        if (!isSystemAlbum(str) && !z2 && !z && !isManualRenameRestricted(str2)) {
            z3 = false;
        }
        this.mIsManualRenameRestricted = z3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r3 = com.miui.gallery.cloudcontrol.CloudControlStrategyHelper.getAlbumByPath(com.miui.gallery.util.StorageUtils.ensureCommonRelativePath(r3));
     */
    private boolean isManualRenameRestricted(String str) {
        AlbumsStrategy.Album albumByPath;
        return !TextUtils.isEmpty(str) && albumByPath != null && albumByPath.getAttributes() != null && albumByPath.getAttributes().isManualRenameRestricted();
    }

    private boolean isRawAlbum() {
        return "DCIM/Camera/Raw".equalsIgnoreCase(this.mLocalPath);
    }

    private boolean isSystemAlbum(String str) {
        return isSystemAlbumButNotScreenshots(str) || String.valueOf(2).equals(str);
    }

    private boolean isSystemAlbumButNotScreenshots(String str) {
        return String.valueOf(-2147483646).equals(str) || String.valueOf(-2147483647L).equals(str) || String.valueOf(1).equals(str) || String.valueOf(1000).equals(str) || String.valueOf(-2147483645).equals(str) || String.valueOf(-2147483643).equals(str) || String.valueOf(-2147483642).equals(str) || String.valueOf(-2147483644).equals(str);
    }

    private boolean isVirtualAlbum(String str) {
        return String.valueOf(-2147483646).equals(str) || String.valueOf(-2147483647L).equals(str) || String.valueOf(1000).equals(str) || String.valueOf(-2147483645).equals(str) || String.valueOf(-2147483643).equals(str) || String.valueOf(-2147483642).equals(str);
    }

    /* access modifiers changed from: package-private */
    public boolean canAddPhotos() {
        return !isVirtualAlbum(this.mAlbumServerId) && !this.mAlbumUnwriteable;
    }

    /* access modifiers changed from: package-private */
    public boolean canDisableAutoUpload() {
        if (isSystemAlbumButNotScreenshots(this.mAlbumServerId) || this.mAlbumUnwriteable || this.mIsOtherShareAlbum || !SyncUtil.isGalleryCloudSyncable(this.mContext)) {
            return false;
        }
        return this.mAutoUpload;
    }

    /* access modifiers changed from: package-private */
    public boolean canDisableShowInOtherAlbums() {
        if (isSystemAlbum(this.mAlbumServerId) || isRawAlbum() || this.mIsOtherShareAlbum || this.mAlbumUnwriteable) {
            return false;
        }
        return this.mShowInOtherAlbums;
    }

    /* access modifiers changed from: package-private */
    public boolean canDisableShowInPhotosTab() {
        if (isSystemAlbumButNotScreenshots(this.mAlbumServerId)) {
            return false;
        }
        return this.mShowInPhotosTab;
    }

    /* access modifiers changed from: package-private */
    public boolean canEnableAutoUpload() {
        if (isSystemAlbumButNotScreenshots(this.mAlbumServerId) || this.mAlbumUnwriteable || this.mIsOtherShareAlbum) {
            return false;
        }
        if (!SyncUtil.isGalleryCloudSyncable(this.mContext)) {
            return true;
        }
        return !this.mAutoUpload;
    }

    /* access modifiers changed from: package-private */
    public boolean canEnableShowInOtherAlbums() {
        if (isSystemAlbum(this.mAlbumServerId) || isRawAlbum() || this.mIsOtherShareAlbum || this.mAlbumUnwriteable) {
            return false;
        }
        return !this.mShowInOtherAlbums;
    }

    /* access modifiers changed from: package-private */
    public boolean canEnableShowInPhotosTab() {
        if (isSystemAlbumButNotScreenshots(this.mAlbumServerId) || this.mIsOtherShareAlbum || this.mAlbumUnwriteable) {
            return false;
        }
        return !this.mShowInPhotosTab;
    }

    /* access modifiers changed from: package-private */
    public boolean canRename() {
        return !isSystemAlbum(this.mAlbumServerId) && !this.mAlbumUnwriteable && !this.mIsOtherShareAlbum && !this.mIsManualRenameRestricted;
    }

    /* access modifiers changed from: package-private */
    public boolean canShare() {
        return !isSystemAlbum(this.mAlbumServerId) && !isRawAlbum() && !this.mAlbumUnwriteable && ApplicationHelper.supportShare();
    }

    /* access modifiers changed from: package-private */
    public boolean canShowInPhotosTab() {
        return this.mShowInPhotosTab;
    }

    /* access modifiers changed from: protected */
    public boolean isUnWriteable() {
        return this.mAlbumUnwriteable;
    }

    /* access modifiers changed from: package-private */
    public void onAutoUploadStatusChanged(boolean z) {
        this.mAutoUpload = z;
    }

    /* access modifiers changed from: package-private */
    public void onShowInOtherAlbumsStatusChanged(boolean z) {
        this.mShowInOtherAlbums = z;
    }

    /* access modifiers changed from: package-private */
    public void onShowInPhotosTabStatusChanged(boolean z) {
        this.mShowInPhotosTab = z;
    }
}
