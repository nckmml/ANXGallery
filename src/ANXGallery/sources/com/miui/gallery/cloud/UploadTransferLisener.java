package com.miui.gallery.cloud;

import com.miui.gallery.util.Log;
import com.xiaomi.opensdk.file.model.MiCloudFileListener;

public class UploadTransferLisener implements MiCloudFileListener {
    private RequestCloudItem mRequestItem;

    UploadTransferLisener(RequestCloudItem requestCloudItem) {
        this.mRequestItem = requestCloudItem;
    }

    public void onDataReceived(long j, long j2) {
        Log.e("CloudGalleryTransferListener", "upload should not received, pos:" + j + ", total:" + j2 + ", item:" + this.mRequestItem.getIdentity());
    }

    public void onDataSended(long j, long j2) {
        if (SyncConditionManager.check(this.mRequestItem.priority) != 0) {
            Log.i("CloudGalleryTransferListener", "net work is changed, interrupt this thread, priority=" + this.mRequestItem.priority + ", item:" + this.mRequestItem.getIdentity());
            Thread.currentThread().interrupt();
        }
    }
}
