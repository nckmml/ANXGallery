package com.miui.gallery.model;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.MediaAndAlbumOperations;
import com.miui.gallery.util.PhotoOperationsUtil;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.miui.gallery.util.uil.CloudUriAdapter;
import com.nexstreaming.nexeditorsdk.nexEngine;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class CloudItem extends BaseDataItem {
    private static final String[] DETAIL_INFO_PROJECTION = {"fileName", "exifModel", "exifMake", "exifFNumber", "exifFocalLength", "exifISOSpeedRatings", "exifExposureTime", "exifFlash", "exifOrientation"};
    private transient OnCompleteListenerWrapper mAddToFavoritesListener;
    private String mCreatorId;
    private boolean mHasFace;
    private long mId;
    private int mIsFavorite;
    private boolean mIsShareItem;
    private boolean mIsSynced;
    private int mOrientation = 0;
    private transient OnCompleteListenerWrapper mRemoveFromFavoritesListener;
    private String mServerId;
    private String mSha1;

    private static class OnCompleteListenerWrapper implements MediaAndAlbumOperations.OnCompleteListener {
        private final WeakReference<CloudItem> mCloudItemRef;
        private final int mOperationType;
        /* access modifiers changed from: private */
        public final WeakReference<MediaAndAlbumOperations.OnCompleteListener> mWrappedRef;

        public OnCompleteListenerWrapper(MediaAndAlbumOperations.OnCompleteListener onCompleteListener, CloudItem cloudItem, int i) {
            this.mWrappedRef = new WeakReference<>(onCompleteListener);
            this.mCloudItemRef = new WeakReference<>(cloudItem);
            this.mOperationType = i;
        }

        public void onComplete(long[] jArr) {
            CloudItem cloudItem;
            if (jArr != null) {
                int i = 0;
                if (jArr[0] > 0 && (cloudItem = (CloudItem) this.mCloudItemRef.get()) != null) {
                    if (this.mOperationType == 1) {
                        i = 1;
                    }
                    cloudItem.setIsFavorite(i);
                }
            }
            MediaAndAlbumOperations.OnCompleteListener onCompleteListener = (MediaAndAlbumOperations.OnCompleteListener) this.mWrappedRef.get();
            if (onCompleteListener != null) {
                onCompleteListener.onComplete(jArr);
            }
        }
    }

    private boolean canDelete() {
        return isOwner() || isMine();
    }

    private boolean isCreatorEqualsCurrentAccount() {
        return TextUtils.equals(GalleryCloudUtils.getAccountName(), this.mCreatorId);
    }

    private boolean isCreatorFromAlbumOwner() {
        return TextUtils.isEmpty(this.mCreatorId) && this.mIsSynced;
    }

    private boolean isOwner() {
        return !isShare();
    }

    private boolean needDownloadOrigin() {
        if (!isBurstItem()) {
            return TextUtils.isEmpty(getOriginalPath());
        }
        for (BaseDataItem originalPath : getBurstGroup()) {
            if (TextUtils.isEmpty(originalPath.getOriginalPath())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOriginalFileExist() {
        return super.checkOriginalFileExist() && new File(getOriginalPath()).length() >= getSize();
    }

    public PhotoDetailInfo getDetailInfo(Context context) {
        final PhotoDetailInfo detailInfo = super.getDetailInfo(context);
        String originalPath = getOriginalPath();
        boolean isFileExist = FileUtils.isFileExist(originalPath);
        if (!isFileExist) {
            originalPath = getThumnailPath();
            if (!FileUtils.isFileExist(originalPath)) {
                originalPath = null;
            }
        }
        if (!isFileExist) {
            if (isVideo() || isGif()) {
                detailInfo.removeDetail(200);
                detailInfo.addDetail(8, context.getString(R.string.tip_not_download));
            } else if (!TextUtils.isEmpty(originalPath)) {
                detailInfo.addDetail(200, originalPath);
                detailInfo.addDetail(2, FileUtils.getFileName(originalPath));
                detailInfo.addDetail(3, Long.valueOf(new File(originalPath).length()));
                try {
                    BitmapFactory.Options bitmapSize = miui.graphics.BitmapFactory.getBitmapSize(originalPath);
                    detailInfo.addDetail(4, Integer.valueOf(bitmapSize.outWidth));
                    detailInfo.addDetail(5, Integer.valueOf(bitmapSize.outHeight));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (isSecret()) {
            detailInfo.removeDetail(200);
        }
        return (PhotoDetailInfo) SafeDBUtil.safeQuery(context, isShare() ? GalleryContract.ShareImage.SHARE_URI : GalleryContract.Cloud.CLOUD_URI, DETAIL_INFO_PROJECTION, "_id=?", new String[]{String.valueOf(isShare() ? ShareMediaManager.getOriginalMediaId(getId()) : getId())}, (String) null, new SafeDBUtil.QueryHandler() {
            public Object handle(Cursor cursor) {
                if (cursor == null || !cursor.moveToFirst()) {
                    return null;
                }
                if (TextUtils.isEmpty((String) detailInfo.getDetail(2)) || CloudItem.this.isSecret()) {
                    detailInfo.addDetail(2, cursor.getString(0));
                }
                detailInfo.addDetail(101, cursor.getString(1));
                detailInfo.addDetail(100, cursor.getString(2));
                detailInfo.addDetail(105, cursor.getString(3));
                String string = cursor.getString(4);
                if (!TextUtils.isEmpty(string)) {
                    detailInfo.addDetail(103, PhotoDetailInfo.wrapFocalLength(string));
                }
                detailInfo.addDetail(108, cursor.getString(5));
                detailInfo.addDetail(107, cursor.getString(6));
                detailInfo.addDetail(102, cursor.getString(7));
                detailInfo.addDetail(10, cursor.getString(8));
                return detailInfo;
            }
        });
    }

    public Uri getDownloadUri() {
        return CloudUriAdapter.getDownloadUri(getId());
    }

    public FavoriteInfo getFavoriteInfo(boolean z) {
        FavoriteInfo favoriteInfo = new FavoriteInfo();
        boolean z2 = true;
        favoriteInfo.setFavoriteUsable(!isShare() && !isSecret());
        if (this.mIsFavorite <= 0) {
            z2 = false;
        }
        favoriteInfo.setFavorite(z2);
        return favoriteInfo;
    }

    public long getId() {
        return this.mId;
    }

    public String getMicroPath() {
        return TextUtils.isEmpty(super.getMicroPath()) ? StorageUtils.getPriorMicroThumbnailPath(this.mSha1) : super.getMicroPath();
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public String getServerId() {
        return this.mServerId;
    }

    public String getSha1() {
        return this.mSha1;
    }

    public boolean hasFace() {
        return this.mHasFace;
    }

    public int initSupportOperations() {
        String pathDisplayBetter = getPathDisplayBetter();
        int videoSupportedOperations = (isVideo() ? PhotoOperationsUtil.getVideoSupportedOperations(pathDisplayBetter) : PhotoOperationsUtil.getImageSupportedOperations(pathDisplayBetter, this.mMimeType, this.mLatitude, this.mLongitude)) | nexEngine.ExportHEVCMainTierLevel6 | 1;
        if (!isGif()) {
            videoSupportedOperations |= 512;
        }
        int i = needDownloadOrigin() ? videoSupportedOperations | 256 : videoSupportedOperations & -257;
        if (isShare()) {
            int i2 = i & -8388609 & -16777217 & -513;
            return !canDelete() ? i2 & -2 : i2;
        } else if (isSecret()) {
            int i3 = i & -8388609 & -1048577 & -33 & -2049 & -4097 & -4194305;
            if (isVideo()) {
                i3 &= -513;
            }
            return i3 | nexEngine.ExportHEVCMainTierLevel62;
        } else {
            int i4 = i & -16777217;
            return ApplicationHelper.isSecretAlbumFeatureOpen() ? (!isVideo() || Config.SecretAlbumConfig.isVideoSupported()) ? i4 | nexEngine.ExportHEVCHighTierLevel61 : i4 : i4 & -8388609;
        }
    }

    public boolean isEverSynced() {
        return !TextUtils.isEmpty(this.mServerId);
    }

    public boolean isMine() {
        return isShare() ? isCreatorEqualsCurrentAccount() || !this.mIsSynced : isCreatorFromAlbumOwner() || isCreatorEqualsCurrentAccount() || !this.mIsSynced;
    }

    public boolean isSecret() {
        return super.isSecret() || this.mLocalGroupId == -1000;
    }

    public boolean isShare() {
        return this.mIsShareItem;
    }

    public boolean isSynced() {
        return this.mIsSynced;
    }

    /* access modifiers changed from: protected */
    public void onLoadCache() {
        super.onLoadCache();
        String originalPath = getOriginalPath();
        if (!TextUtils.isEmpty(originalPath) && isEverSynced() && new File(originalPath).length() < getSize()) {
            setFilePath((String) null);
        }
        String thumnailPath = getThumnailPath();
        if (!TextUtils.isEmpty(thumnailPath) && !FileUtils.isFileExist(thumnailPath)) {
            setThumbPath((String) null);
        }
    }

    public CloudItem setCreatorId(String str) {
        this.mCreatorId = str;
        return this;
    }

    public BaseDataItem setFilePath(String str) {
        super.setFilePath(str);
        if (!needDownloadOrigin()) {
            removeSupportOperation(256);
        }
        return this;
    }

    public void setHasFace(boolean z) {
        this.mHasFace = z;
    }

    public CloudItem setId(long j) {
        this.mId = j;
        return this;
    }

    public CloudItem setIsFavorite(int i) {
        this.mIsFavorite = i;
        return this;
    }

    public CloudItem setOrientation(int i) {
        this.mOrientation = i;
        return this;
    }

    public CloudItem setServerId(String str) {
        this.mServerId = str;
        return this;
    }

    public CloudItem setSha1(String str) {
        this.mSha1 = str;
        return this;
    }

    public CloudItem setShare(boolean z) {
        this.mIsShareItem = z;
        return this;
    }

    public CloudItem setSynced(boolean z) {
        this.mIsSynced = z;
        return this;
    }

    public MediaAndAlbumOperations.OnCompleteListener wrapAddToFavoritesListener(MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
        OnCompleteListenerWrapper onCompleteListenerWrapper = this.mAddToFavoritesListener;
        if (onCompleteListenerWrapper == null || !Objects.equals(onCompleteListener, onCompleteListenerWrapper.mWrappedRef.get())) {
            this.mAddToFavoritesListener = new OnCompleteListenerWrapper(onCompleteListener, this, 1);
        }
        return this.mAddToFavoritesListener;
    }

    public MediaAndAlbumOperations.OnCompleteListener wrapRemoveFromFavoritesListener(MediaAndAlbumOperations.OnCompleteListener onCompleteListener) {
        OnCompleteListenerWrapper onCompleteListenerWrapper = this.mRemoveFromFavoritesListener;
        if (onCompleteListenerWrapper == null || !Objects.equals(onCompleteListener, onCompleteListenerWrapper.mWrappedRef.get())) {
            this.mRemoveFromFavoritesListener = new OnCompleteListenerWrapper(onCompleteListener, this, 2);
        }
        return this.mRemoveFromFavoritesListener;
    }
}
