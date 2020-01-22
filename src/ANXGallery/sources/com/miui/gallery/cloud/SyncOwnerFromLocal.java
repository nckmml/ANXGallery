package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.text.TextUtils;
import com.google.common.collect.Lists;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.data.DBCloud;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.DBItem;
import com.miui.gallery.provider.ProcessingMediaManager;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SyncLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SyncOwnerFromLocal extends SyncFromLocalBase {
    private ArrayList<RequestCloudItem> mAutoCreateImageItems;
    private ArrayList<RequestCloudItem> mAutoCreateVideoItems;
    private ArrayList<RequestCloudItem> mCopyImageToOwnerItems;
    private ArrayList<RequestCloudItem> mCreateGroupItems;
    private ArrayList<RequestCloudItem> mDeleteGroupItems;
    private ArrayList<RequestCloudItem> mDeleteImageItems;
    private ArrayList<RequestCloudItem> mEditCloudThumbnailInfoItems;
    private ArrayList<RequestCloudItem> mEditFavoriteInfoItems;
    private ArrayList<RequestCloudItem> mEditGeoInfoItems;
    private ArrayList<RequestCloudItem> mEditGroupDescriptionItems;
    private ArrayList<RequestCloudItem> mEditNameItems;
    private ArrayList<RequestCloudItem> mEditedItems;
    private long mLargestElapsedTime;
    private ArrayList<RequestCloudItem> mManualCreateOwnerImageItems;
    private ArrayList<RequestCloudItem> mManualCreateOwnerVideoItems;
    private ArrayList<RequestCloudItem> mMoveImageToOwnerItems;
    private boolean mNoDelay;
    private int mPhotosToBeSynced;
    private ArrayList<RequestCloudItem> mRenameGroupItems;
    private int mVideosToBeSynced;

    public SyncOwnerFromLocal(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, boolean z) {
        super(context, account, galleryExtendedAuthToken);
        this.mNoDelay = z;
    }

    private boolean shouldSyncEditItemName(DBImage dBImage) {
        int localFlag = dBImage.getLocalFlag();
        if (localFlag == 0 || localFlag == 5 || localFlag == 6 || localFlag == 9) {
            return dBImage.getEditedColumns().contains(GalleryCloudUtils.transformToEditedColumnsElement(7));
        }
        return false;
    }

    private boolean shouldSyncEditedDescription(DBImage dBImage) {
        int localFlag = dBImage.getLocalFlag();
        if (localFlag == 0 || localFlag == 10) {
            return dBImage.getEditedColumns().contains(GalleryCloudUtils.transformToEditedColumnsElement(6));
        }
        return false;
    }

    private boolean shouldSyncEditedGeoInfo(DBImage dBImage) {
        int localFlag = dBImage.getLocalFlag();
        if (localFlag == 0 || localFlag == 5 || localFlag == 6 || localFlag == 9) {
            return dBImage.getEditedColumns().contains(GalleryCloudUtils.transformToEditedColumnsElement(70));
        }
        return false;
    }

    private boolean shouldSyncEditedItem(DBImage dBImage) {
        int localFlag = dBImage.getLocalFlag();
        if (localFlag != 0 && localFlag != 5 && localFlag != 6 && localFlag != 9) {
            return false;
        }
        return dBImage.isUbiFocus() && dBImage.getEditedColumns().contains(GalleryCloudUtils.getFocusIndexColumnElement(dBImage.isShareItem()));
    }

    private boolean shouldSyncEditedThumbnailInfo(DBImage dBImage) {
        ThumbnailInfo thumbnailInfo;
        int localFlag = dBImage.getLocalFlag();
        if ((dBImage instanceof DBCloud) && ((localFlag == 0 || localFlag == 10 || localFlag == 8) && dBImage.getEditedColumns().contains(GalleryCloudUtils.transformToEditedColumnsElement(66)) && (thumbnailInfo = ((DBCloud) dBImage).getThumbnailInfo()) != null)) {
            long bgImageLocalId = thumbnailInfo.getBgImageLocalId();
            if (bgImageLocalId == -1) {
                return true;
            }
            DBImage item = CloudUtils.getItem(getBaseUri(), this.mContext, "_id", String.valueOf(bgImageLocalId));
            if (item != null) {
                return 8 != item.getLocalFlag();
            }
        }
        return false;
    }

    private boolean shouldSyncFavoriteInfo(DBImage dBImage) {
        int localFlag = dBImage.getLocalFlag();
        if (localFlag == 0 || localFlag == 5 || localFlag == 6 || localFlag == 9) {
            return dBImage.getEditedColumns().contains(GalleryCloudUtils.transformToEditedColumnsElement(-1));
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public DBImage generateDBImage(Cursor cursor) {
        return new DBCloud(cursor);
    }

    /* access modifiers changed from: protected */
    public Uri getBaseUri() {
        return GalleryCloudUtils.CLOUD_URI;
    }

    /* access modifiers changed from: protected */
    public String getSelectionClause() {
        String format = String.format(Locale.US, "((serverType = 1 AND size < %s) OR (serverType = 2 AND size < %s))", new Object[]{Long.valueOf(CloudUtils.getMaxImageSizeLimit()), Long.valueOf(CloudUtils.getMaxVideoSizeLimit())});
        String concatenateWhere = DatabaseUtils.concatenateWhere(CloudUtils.SELECTION_OWNER_NEED_SYNC, String.format(Locale.US, "(%s OR %s)", new Object[]{"(serverType=0)", format}));
        List<String> queryProcessingMediaPaths = ProcessingMediaManager.queryProcessingMediaPaths();
        if (MiscUtil.isValid(queryProcessingMediaPaths)) {
            StringBuilder sb = new StringBuilder("localFile NOT IN (");
            for (int i = 0; i < queryProcessingMediaPaths.size(); i++) {
                if (!TextUtils.isEmpty(queryProcessingMediaPaths.get(i))) {
                    DatabaseUtils.appendEscapedSQLString(sb, queryProcessingMediaPaths.get(i));
                    if (i != queryProcessingMediaPaths.size() - 1) {
                        sb.append(", ");
                    }
                }
            }
            sb.append(")");
            return DatabaseUtils.concatenateWhere(concatenateWhere, sb.toString());
        }
        return String.format(" (%s) ", new Object[]{concatenateWhere});
    }

    /* access modifiers changed from: protected */
    public void handleRequestCloudItemList() throws Exception {
        if (this.mCreateGroupItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start create group items");
            GallerySyncResult<T> doOperation = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mCreateGroupItems, new CreateGroupItem(this.mContext));
            if (doOperation != null && doOperation.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mManualCreateOwnerVideoItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start upload manual create videos");
            UpDownloadManager.dispatchList(this.mManualCreateOwnerVideoItems);
        }
        if (this.mAutoCreateVideoItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start upload auto create videos");
            UpDownloadManager.dispatchList(this.mAutoCreateVideoItems);
        }
        if (this.mManualCreateOwnerImageItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start upload manual create images");
            UpDownloadManager.dispatchList(this.mManualCreateOwnerImageItems);
        }
        if (this.mAutoCreateImageItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start upload auto create images");
            UpDownloadManager.dispatchList(this.mAutoCreateImageItems);
        }
        if (this.mCopyImageToOwnerItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start copy image items");
            GallerySyncResult<T> doOperation2 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mCopyImageToOwnerItems, new CopyImageToOwnerAlbum(this.mContext));
            if (doOperation2 != null && doOperation2.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mMoveImageToOwnerItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start move image items");
            GallerySyncResult<T> doOperation3 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mMoveImageToOwnerItems, new MoveItemToOwnerAlbum(this.mContext));
            if (doOperation3 != null && doOperation3.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mDeleteImageItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start delete image items");
            GallerySyncResult<T> doOperation4 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mDeleteImageItems, new DeleteCloudItem(this.mContext));
            if (doOperation4 != null && doOperation4.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mDeleteGroupItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start delete group items");
            GallerySyncResult<T> doOperation5 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mDeleteGroupItems, new DeleteCloudItem(this.mContext));
            if (doOperation5 != null && doOperation5.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mRenameGroupItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start rename group items");
            GallerySyncResult<T> doOperation6 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mRenameGroupItems, new RenameCloudItem(this.mContext));
            if (doOperation6 != null && doOperation6.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mEditedItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start edit items");
            GallerySyncResult<T> doOperation7 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mEditedItems, new EditCloudItem(this.mContext));
            if (doOperation7 != null && doOperation7.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mEditCloudThumbnailInfoItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start album thumbnail info");
            GallerySyncResult<T> doOperation8 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mEditCloudThumbnailInfoItems, new EditCloudThumbnailInfo(this.mContext));
            if (doOperation8 != null && doOperation8.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mEditGroupDescriptionItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start edit album description");
            GallerySyncResult<T> doOperation9 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mEditGroupDescriptionItems, new EditGroupDescription(this.mContext));
            if (doOperation9 != null && doOperation9.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mEditGeoInfoItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start edit geo info");
            GallerySyncResult<T> doOperation10 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mEditGeoInfoItems, new EditCloudGeoInfo(this.mContext));
            if (doOperation10 != null && doOperation10.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mEditFavoriteInfoItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start edit favorite info");
            GallerySyncResult<T> doOperation11 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mEditFavoriteInfoItems, new EditCloudFavoriteInfo(this.mContext));
            if (doOperation11 != null && doOperation11.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mEditNameItems.size() > 0) {
            SyncLog.v("SyncOwnerFromLocal", "start edit item name info");
            GallerySyncResult<T> doOperation12 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mEditNameItems, new EditCloudItemName(this.mContext));
            if (doOperation12 == null || doOperation12.code == GallerySyncCode.CONDITION_INTERRUPTED) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initRequestCloudItemList() {
        this.mCreateGroupItems = new ArrayList<>();
        this.mManualCreateOwnerImageItems = new ArrayList<>();
        this.mAutoCreateImageItems = new ArrayList<>();
        this.mCopyImageToOwnerItems = new ArrayList<>();
        this.mMoveImageToOwnerItems = new ArrayList<>();
        this.mDeleteImageItems = new ArrayList<>();
        this.mDeleteGroupItems = new ArrayList<>();
        this.mRenameGroupItems = new ArrayList<>();
        this.mAutoCreateVideoItems = Lists.newArrayList();
        this.mManualCreateOwnerVideoItems = Lists.newArrayList();
        this.mEditedItems = Lists.newArrayList();
        this.mEditCloudThumbnailInfoItems = Lists.newArrayList();
        this.mEditGroupDescriptionItems = Lists.newArrayList();
        this.mEditGeoInfoItems = Lists.newArrayList();
        this.mEditFavoriteInfoItems = Lists.newArrayList();
        this.mEditNameItems = Lists.newArrayList();
    }

    /* access modifiers changed from: protected */
    public void putToRequestCloudItemList(DBItem dBItem) {
        DBImage dBImage = (DBImage) dBItem;
        if (!TextUtils.isEmpty(dBImage.getEditedColumns())) {
            if (dBImage.isItemType()) {
                if (shouldSyncEditedItem(dBImage)) {
                    this.mEditedItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                }
                if (shouldSyncEditedGeoInfo(dBImage)) {
                    this.mEditGeoInfoItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                }
                if (shouldSyncFavoriteInfo(dBImage)) {
                    this.mEditFavoriteInfoItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                }
                if (shouldSyncEditItemName(dBImage)) {
                    this.mEditNameItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                }
            } else {
                if (shouldSyncEditedThumbnailInfo(dBImage)) {
                    this.mEditCloudThumbnailInfoItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                }
                if (shouldSyncEditedDescription(dBImage)) {
                    this.mEditGroupDescriptionItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                }
            }
        }
        switch (dBImage.getLocalFlag()) {
            case 2:
                RequestCloudItem requestCloudItem = new RequestCloudItem(0, dBImage, this.mNoDelay);
                if (requestCloudItem.dbCloud.isItemType()) {
                    this.mDeleteImageItems.add(requestCloudItem);
                    return;
                } else {
                    this.mDeleteGroupItems.add(requestCloudItem);
                    return;
                }
            case 4:
                int serverType = dBImage.getServerType();
                if (serverType == 1) {
                    this.mAutoCreateImageItems.add(new RequestCloudItem(4, dBImage, this.mNoDelay));
                    return;
                } else if (serverType != 2) {
                    SyncLog.e("SyncOwnerFromLocal", "unsupport local flag= %d serverType= %d", Integer.valueOf(dBImage.getLocalFlag()), Integer.valueOf(dBImage.getServerType()));
                    return;
                } else {
                    this.mAutoCreateVideoItems.add(new RequestCloudItem(2, dBImage, this.mNoDelay));
                    return;
                }
            case 5:
                if (dBImage.isItemType()) {
                    this.mMoveImageToOwnerItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                    return;
                }
                return;
            case 6:
            case 9:
                if (dBImage.isItemType()) {
                    this.mCopyImageToOwnerItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                    return;
                }
                return;
            case 7:
                int serverType2 = dBImage.getServerType();
                if (serverType2 == 0) {
                    this.mCreateGroupItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                    return;
                } else if (serverType2 == 1) {
                    this.mAutoCreateImageItems.add(new RequestCloudItem(4, dBImage, this.mNoDelay));
                    this.mPhotosToBeSynced++;
                    this.mLargestElapsedTime = Math.max(this.mLargestElapsedTime, System.currentTimeMillis() - dBImage.getDateModified());
                    return;
                } else if (serverType2 != 2) {
                    SyncLog.e("SyncOwnerFromLocal", "unsupport local flag= %d serverType= %d", Integer.valueOf(dBImage.getLocalFlag()), Integer.valueOf(dBImage.getServerType()));
                    return;
                } else {
                    this.mAutoCreateVideoItems.add(new RequestCloudItem(2, dBImage, this.mNoDelay));
                    this.mVideosToBeSynced++;
                    this.mLargestElapsedTime = Math.max(this.mLargestElapsedTime, System.currentTimeMillis() - dBImage.getDateModified());
                    return;
                }
            case 8:
                int serverType3 = dBImage.getServerType();
                if (serverType3 == 0) {
                    this.mCreateGroupItems.add(new RequestCloudItem(0, dBImage, this.mNoDelay));
                    return;
                } else if (serverType3 == 1) {
                    this.mManualCreateOwnerImageItems.add(new RequestCloudItem(5, dBImage, this.mNoDelay));
                    this.mPhotosToBeSynced++;
                    this.mLargestElapsedTime = Math.max(this.mLargestElapsedTime, System.currentTimeMillis() - dBImage.getDateModified());
                    return;
                } else if (serverType3 != 2) {
                    SyncLog.e("SyncOwnerFromLocal", "unsupport local flag=" + dBImage.getLocalFlag() + ", serverType=" + dBImage.getServerType());
                    return;
                } else {
                    this.mManualCreateOwnerVideoItems.add(new RequestCloudItem(3, dBImage, this.mNoDelay));
                    this.mVideosToBeSynced++;
                    this.mLargestElapsedTime = Math.max(this.mLargestElapsedTime, System.currentTimeMillis() - dBImage.getDateModified());
                    return;
                }
            case 10:
                if (!dBImage.isItemType()) {
                    RequestCloudItem requestCloudItem2 = new RequestCloudItem(0, dBImage, this.mNoDelay);
                    if (TextUtils.isEmpty(dBImage.getServerId())) {
                        this.mCreateGroupItems.add(requestCloudItem2);
                        GallerySamplingStatHelper.recordErrorEvent("Sync", "sync_album_renamed_before_sync", GallerySamplingStatHelper.generatorCommonParams());
                        return;
                    }
                    this.mRenameGroupItems.add(requestCloudItem2);
                    return;
                }
                return;
            case 11:
                SyncLog.d("SyncOwnerFromLocal", "don't handle move from flag.");
                return;
            default:
                if (TextUtils.isEmpty(dBImage.getEditedColumns())) {
                    SyncLog.e("SyncOwnerFromLocal", "unsupport local flag: %d", Integer.valueOf(dBImage.getLocalFlag()));
                    return;
                }
                return;
        }
    }

    public void sync() throws Exception {
        this.mPhotosToBeSynced = 0;
        this.mVideosToBeSynced = 0;
        try {
            super.sync();
        } finally {
            int i = this.mPhotosToBeSynced;
            if (i > 0) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("items_to_be_synced", "owner_photos_to_be_synced", (long) i);
            }
            int i2 = this.mVideosToBeSynced;
            if (i2 > 0) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("items_to_be_synced", "owner_videos_to_be_synced", (long) i2);
            }
            long j = this.mLargestElapsedTime;
            if (j > 0) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("items_to_be_synced", "owner_largest_elapsed_time", j);
            }
        }
    }
}
