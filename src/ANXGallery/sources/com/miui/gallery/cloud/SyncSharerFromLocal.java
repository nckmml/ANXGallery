package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import com.google.common.collect.Lists;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.DBItem;
import com.miui.gallery.data.DBShareImage;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.SyncLog;
import java.util.ArrayList;
import java.util.Locale;

public class SyncSharerFromLocal extends SyncFromLocalBase {
    private ArrayList<RequestCloudItem> mCopyImageToSharerItems;
    private ArrayList<RequestCloudItem> mDeleteShareImageItems;
    private long mLargestElapsedTime;
    private ArrayList<RequestCloudItem> mManualCreateSharerImageItems;
    private ArrayList<RequestCloudItem> mManualCreateSharerVideoItems;
    private ArrayList<RequestCloudItem> mMoveImageToSharerItems;
    private int mPhotosToBeSynced;
    private int mVideosToBeSynced;

    public SyncSharerFromLocal(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) {
        super(context, account, galleryExtendedAuthToken);
    }

    /* access modifiers changed from: protected */
    public DBImage generateDBImage(Cursor cursor) {
        return new DBShareImage(cursor);
    }

    /* access modifiers changed from: protected */
    public Uri getBaseUri() {
        return GalleryCloudUtils.SHARE_IMAGE_URI;
    }

    /* access modifiers changed from: protected */
    public String getSelectionClause() {
        return DatabaseUtils.concatenateWhere(super.getSelectionClause(), String.format(Locale.US, "((serverType = 1 AND size < %s) OR (serverType = 2 AND size < %s))", new Object[]{Long.valueOf(CloudUtils.getMaxImageSizeLimit()), Long.valueOf(CloudUtils.getMaxVideoSizeLimit())}));
    }

    /* access modifiers changed from: protected */
    public void handleRequestCloudItemList() throws Exception {
        if (this.mManualCreateSharerVideoItems.size() > 0) {
            SyncLog.v("SyncSharerFromLocal", "start upload manual create videos");
            UpDownloadManager.dispatchList(this.mManualCreateSharerVideoItems);
        }
        if (this.mManualCreateSharerImageItems.size() > 0) {
            SyncLog.v("SyncSharerFromLocal", "start upload manual create images");
            UpDownloadManager.dispatchList(this.mManualCreateSharerImageItems);
        }
        if (this.mCopyImageToSharerItems.size() > 0) {
            SyncLog.v("SyncSharerFromLocal", "start copy image items");
            GallerySyncResult<T> doOperation = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mCopyImageToSharerItems, new CopyImageToSharerAlbum(this.mContext));
            if (doOperation != null && doOperation.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mMoveImageToSharerItems.size() > 0) {
            SyncLog.v("SyncSharerFromLocal", "start move image items");
            GallerySyncResult<T> doOperation2 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mMoveImageToSharerItems, new MoveItemToSharerAlbum(this.mContext));
            if (doOperation2 != null && doOperation2.code == GallerySyncCode.CONDITION_INTERRUPTED) {
                return;
            }
        }
        if (this.mDeleteShareImageItems.size() > 0) {
            SyncLog.v("SyncSharerFromLocal", "start delete image items");
            GallerySyncResult<T> doOperation3 = RetryOperation.doOperation(this.mContext, this.mAccount, this.mExtendedAuthToken, this.mDeleteShareImageItems, new DeleteCloudItem(this.mContext));
            if (doOperation3 == null || doOperation3.code == GallerySyncCode.CONDITION_INTERRUPTED) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initRequestCloudItemList() {
        this.mManualCreateSharerImageItems = new ArrayList<>();
        this.mCopyImageToSharerItems = new ArrayList<>();
        this.mDeleteShareImageItems = new ArrayList<>();
        this.mManualCreateSharerVideoItems = Lists.newArrayList();
        this.mMoveImageToSharerItems = Lists.newArrayList();
    }

    /* access modifiers changed from: protected */
    public void putToRequestCloudItemList(DBItem dBItem) {
        DBImage dBImage = (DBImage) dBItem;
        int localFlag = dBImage.getLocalFlag();
        if (localFlag == 2) {
            this.mDeleteShareImageItems.add(new RequestCloudItem(0, dBImage));
        } else if (localFlag == 11) {
            SyncLog.d("SyncSharerFromLocal", "don't handle move from flag.");
        } else if (localFlag != 5) {
            if (localFlag != 6) {
                if (localFlag == 8) {
                    int serverType = dBImage.getServerType();
                    if (serverType == 1) {
                        this.mManualCreateSharerImageItems.add(new RequestCloudItem(5, dBImage));
                        this.mPhotosToBeSynced++;
                        this.mLargestElapsedTime = Math.max(this.mLargestElapsedTime, System.currentTimeMillis() - dBImage.getDateModified());
                        return;
                    } else if (serverType == 2) {
                        this.mManualCreateSharerVideoItems.add(new RequestCloudItem(3, dBImage));
                        this.mVideosToBeSynced++;
                        this.mLargestElapsedTime = Math.max(this.mLargestElapsedTime, System.currentTimeMillis() - dBImage.getDateModified());
                        return;
                    } else {
                        return;
                    }
                } else if (localFlag != 9) {
                    SyncLog.e("SyncSharerFromLocal", "unsupport local flag %d", Integer.valueOf(dBImage.getLocalFlag()));
                    return;
                }
            }
            this.mCopyImageToSharerItems.add(new RequestCloudItem(0, dBImage));
        } else if (dBImage.isItemType()) {
            this.mMoveImageToSharerItems.add(new RequestCloudItem(0, dBImage));
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
                GallerySamplingStatHelper.recordNumericPropertyEvent("items_to_be_synced", "sharer_photos_to_be_synced", (long) i);
            }
            int i2 = this.mVideosToBeSynced;
            if (i2 > 0) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("items_to_be_synced", "sharer_videos_to_be_synced", (long) i2);
            }
            long j = this.mLargestElapsedTime;
            if (j > 0) {
                GallerySamplingStatHelper.recordNumericPropertyEvent("items_to_be_synced", "sharer_largest_elapsed_time", j);
            }
        }
    }
}
