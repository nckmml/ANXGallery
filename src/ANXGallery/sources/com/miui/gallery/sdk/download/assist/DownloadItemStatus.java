package com.miui.gallery.sdk.download.assist;

import com.miui.gallery.sdk.SyncStatus;

public class DownloadItemStatus {
    private final DownloadedItem mItem;
    private final SyncStatus mSyncStatus;

    public DownloadItemStatus(SyncStatus syncStatus, DownloadedItem downloadedItem) {
        this.mSyncStatus = syncStatus;
        this.mItem = downloadedItem;
    }

    public String getDownloadedPath() {
        DownloadedItem downloadedItem = this.mItem;
        if (downloadedItem != null) {
            return downloadedItem.getFilePath();
        }
        return null;
    }

    public SyncStatus getStatus() {
        return this.mSyncStatus;
    }

    public boolean isDownloading() {
        return this.mSyncStatus == SyncStatus.STATUS_INIT || this.mSyncStatus == SyncStatus.STATUS_INTERRUPT;
    }
}
