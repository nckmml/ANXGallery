package com.miui.gallery.cloud.thread;

import android.accounts.Account;
import android.content.Context;
import com.miui.gallery.cloud.CreateOwnerImage;
import com.miui.gallery.cloud.CreateShareImage;
import com.miui.gallery.cloud.GalleryMiCloudServerException;
import com.miui.gallery.cloud.RequestCloudItem;
import com.miui.gallery.cloud.RetryOperation;
import com.miui.gallery.cloud.ServerErrorCode;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.control.BatteryMonitor;
import com.miui.gallery.cloud.syncstate.SyncMonitor;
import com.miui.gallery.cloud.syncstate.SyncStateManager;
import com.miui.gallery.cloud.thread.RequestCommandQueue;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.deviceprovider.UploadStatusController;
import java.util.HashMap;
import java.util.List;

public class BackUploadTask extends BaseSyncLockTask<DBImage> {
    private long mStartTime;
    private int mUploadCount;

    public BackUploadTask(int i, int i2, int i3, int i4, RequestCommandQueue.OnItemChangedListener onItemChangedListener) {
        super(i, i2, i3, i4, onItemChangedListener);
    }

    private long getSyncReason() {
        return SyncStateManager.getInstance().getSyncReason();
    }

    private SyncType getSyncType() {
        SyncType syncType = SyncStateManager.getInstance().getSyncType();
        if (syncType != SyncType.UNKNOW) {
            return syncType;
        }
        SyncLog.e(this.TAG, "sync type shouldn't be UNKNOW");
        return SyncType.NORMAL;
    }

    private void statUpload() {
        HashMap hashMap = new HashMap();
        hashMap.put("cost", String.valueOf((long) Math.round((((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / 1000.0f)));
        hashMap.put("count", String.valueOf(this.mUploadCount));
        GallerySamplingStatHelper.recordCountEvent("Sync", "sync_upload_time_total", hashMap);
    }

    /* access modifiers changed from: protected */
    public GallerySyncResult<DBImage> handle(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken, List<RequestCloudItem> list) {
        if (list.isEmpty()) {
            return new GallerySyncResult.Builder().setCode(GallerySyncCode.OK).build();
        }
        this.mUploadCount += list.size();
        return RetryOperation.doOperation(context, account, galleryExtendedAuthToken, list, list.get(0).dbCloud.isShareItem() ? new CreateShareImage(context) : new CreateOwnerImage(context));
    }

    /* access modifiers changed from: protected */
    public GallerySyncResult<DBImage> onPerformSync() throws Throwable {
        try {
            return super.onPerformSync();
        } catch (GalleryMiCloudServerException e) {
            SyncLog.e(this.TAG, (Throwable) e);
            if (ServerErrorCode.MiCloudServerExceptionHandler.handleMiCloudException(e.getCloudServerException())) {
                SyncLog.e(this.TAG, "throw Cloud server exception status code %d", Integer.valueOf(e.getStatusCode()));
            } else {
                SyncLog.d(this.TAG, "no retry");
            }
            return new GallerySyncResult.Builder().setCode(GallerySyncCode.OK).build();
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute() {
        UploadStatusController.getInstance().endUpload();
        SyncMonitor.getInstance().onSyncEnd();
        BatteryMonitor.getInstance().end();
        releaseLock();
        statUpload();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        this.mStartTime = System.currentTimeMillis();
        this.mUploadCount = 0;
        UploadStatusController.getInstance().startUpload();
        SyncMonitor.getInstance().onSyncStart(getSyncType(), getSyncReason());
        BatteryMonitor.getInstance().start();
        acquireLock();
    }
}
