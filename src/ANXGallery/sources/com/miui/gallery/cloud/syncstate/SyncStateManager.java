package com.miui.gallery.cloud.syncstate;

import android.content.Context;
import com.miui.gallery.cloud.SyncConditionManager;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.util.Log;

public class SyncStateManager {
    private SyncStateInfo mSyncStateInfo;
    private SyncStateObserver mSyncStateObserver;

    private static class Singleton {
        /* access modifiers changed from: private */
        public static SyncStateManager INSTANCE = new SyncStateManager();
    }

    private SyncStateManager() {
        this.mSyncStateInfo = new SyncStateInfo();
        this.mSyncStateInfo.invalidate();
        this.mSyncStateObserver = new SyncStateObserver();
    }

    public static SyncStateManager getInstance() {
        return Singleton.INSTANCE;
    }

    public boolean containsReason(long j) {
        return this.mSyncStateInfo.containsReason(j);
    }

    public long getSyncReason() {
        return this.mSyncStateInfo.getSyncReason();
    }

    public SyncStateInfo getSyncState() {
        return this.mSyncStateInfo;
    }

    public SyncType getSyncType() {
        SyncType syncType;
        synchronized (this.mSyncStateInfo) {
            syncType = this.mSyncStateInfo.getSyncType();
        }
        return syncType;
    }

    /* access modifiers changed from: package-private */
    public void mergeReason(long j) {
        this.mSyncStateInfo.setSyncReason(j | this.mSyncStateInfo.getSyncReason());
    }

    /* access modifiers changed from: package-private */
    public void onSyncCommandDispatched() {
        this.mSyncStateInfo.onSyncCommandDispatched();
    }

    public void registerSyncStateObserver(Context context, OnSyncStateChangeObserver onSyncStateChangeObserver) {
        this.mSyncStateInfo.registerObserver(onSyncStateChangeObserver);
        this.mSyncStateObserver.register(context);
        updateSyncStatus();
    }

    /* access modifiers changed from: package-private */
    public void setIsBatteryLow(boolean z) {
        this.mSyncStateInfo.setIsBatteryLow(z);
    }

    /* access modifiers changed from: package-private */
    public void setIsLocalSpaceFull(boolean z) {
        this.mSyncStateInfo.setIsLocalSpaceFull(z);
    }

    /* access modifiers changed from: package-private */
    public void setSyncType(SyncType syncType, boolean z) {
        Log.i("SyncStateManager", "setSyncType old: %s, new: %s, force: %s", getSyncType(), syncType, Boolean.valueOf(z));
        if (z || syncType.isForce()) {
            this.mSyncStateInfo.setSyncType(syncType);
            return;
        }
        SyncType syncType2 = getSyncType();
        if (syncType2.isForce()) {
            if (SyncConditionManager.checkIgnoreCancel(4, syncType) == 0) {
                this.mSyncStateInfo.setSyncType(syncType);
            }
        } else if (SyncType.compare(syncType, syncType2) > 0) {
            this.mSyncStateInfo.setSyncType(syncType);
        }
    }

    /* access modifiers changed from: package-private */
    public void triggerMediaChanged() {
        this.mSyncStateInfo.triggerMediaChanged();
    }

    /* access modifiers changed from: package-private */
    public void unmergeReason(long j) {
        this.mSyncStateInfo.setSyncReason((~j) & this.mSyncStateInfo.getSyncReason());
    }

    public void unregisterSyncStateObserver(Context context, OnSyncStateChangeObserver onSyncStateChangeObserver) {
        this.mSyncStateInfo.unregisterObserver(onSyncStateChangeObserver);
        this.mSyncStateObserver.unregister(context);
    }

    /* access modifiers changed from: package-private */
    public void updateSyncStatus() {
        this.mSyncStateInfo.invalidate();
    }
}
