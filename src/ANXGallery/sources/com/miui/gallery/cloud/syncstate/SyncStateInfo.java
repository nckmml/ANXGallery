package com.miui.gallery.cloud.syncstate;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.cloud.SpaceFullHandler;
import com.miui.gallery.cloud.SyncConditionManager;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.control.BatteryMonitor;
import com.miui.gallery.cloud.syncstate.SyncStateUtil;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deprecated.Preference;
import com.miui.gallery.util.deviceprovider.UploadStatusController;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SyncStateInfo {
    private long mCloudSpaceRemainingSize;
    private long mCloudSpaceTotalSize;
    private DirtyCount mDirtyCount = new DirtyCount();
    private long mDirtySize;
    private long mHandledReason = 0;
    private int mImageSyncedCount = -1;
    private volatile boolean mInited;
    private volatile boolean mIsBatteryLow;
    private volatile boolean mIsCloudSpaceFull;
    /* access modifiers changed from: private */
    public volatile boolean mIsDirtyChanged;
    private volatile boolean mIsLocalSpaceFull;
    private volatile boolean mIsMediaChanged;
    /* access modifiers changed from: private */
    public volatile boolean mIsSyncedChanged;
    private Object mLock = new Object();
    /* access modifiers changed from: private */
    public final List<OnSyncStateChangeObserver> mObservable = new LinkedList();
    private Runnable mRefreshTimeRunnable;
    private long mResumeTime = -1;
    private long mSyncReason = 0;
    /* access modifiers changed from: private */
    public SyncStatus mSyncStatus = SyncStatus.UNAVAILABLE;
    /* access modifiers changed from: private */
    public SyncType mSyncType = SyncType.UNKNOW;
    private Runnable mUpdateRunnable = new Runnable() {
        public void run() {
            SyncStateInfo.this.updateSyncStatus(GalleryApp.sGetAndroidContext());
        }
    };
    private AsyncTask<Context, Void, SyncStatus> mUpdateTask;
    private int mVideoSyncedCount = -1;

    SyncStateInfo() {
    }

    private boolean checkIsPendingMetaData(Account account) {
        return SyncStateUtil.isSyncPending();
    }

    private boolean checkIsPendingUpload(Account account) {
        return checkIsPendingMetaData(account) || UploadStatusController.getInstance().isPending();
    }

    private boolean checkIsSyncing(Account account) {
        return (SyncStateUtil.isSyncActive() && (containsReason(32) || containsReason(512))) || SyncStateUtil.isUploading();
    }

    private boolean checkIsSyncingMetaData(Account account) {
        return SyncStateUtil.isSyncActive() && containsReason(1);
    }

    /* access modifiers changed from: private */
    public void doRefreshTime() {
        invalidate();
    }

    private void init(Context context) {
        boolean z = true;
        if (this.mInited) {
            synchronized (this.mObservable) {
                if (this.mObservable.size() > 0) {
                    return;
                }
            }
        } else {
            this.mInited = true;
        }
        this.mIsCloudSpaceFull = SpaceFullHandler.isOwnerSpaceFull();
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.DEVICE_STORAGE_LOW"));
        this.mIsLocalSpaceFull = registerReceiver != null ? "android.intent.action.DEVICE_STORAGE_LOW".equals(registerReceiver.getAction()) : false;
        Intent registerReceiver2 = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver2 == null) {
            z = GalleryPreferences.Sync.getPowerCanSync();
        } else if (BatteryMonitor.isPowerCanSync(context, registerReceiver2)) {
            z = false;
        }
        this.mIsBatteryLow = z;
    }

    private boolean isMeetPullCondition() {
        return SyncConditionManager.checkIgnoreCancel(1, SyncUtil.wrapSyncType(this.mSyncType)) == 0;
    }

    private boolean isMeetPushCondition() {
        return SyncConditionManager.checkIgnoreCancel(4, SyncUtil.wrapSyncType(this.mSyncType)) == 0;
    }

    /* access modifiers changed from: private */
    public void notifyObservers() {
        ThreadManager.getMainHandler().post(new Runnable() {
            public void run() {
                synchronized (SyncStateInfo.this.mObservable) {
                    for (OnSyncStateChangeObserver onSyncStateChanged : SyncStateInfo.this.mObservable) {
                        onSyncStateChanged.onSyncStateChanged(SyncStateInfo.this);
                    }
                }
            }
        });
    }

    private void startRefreshTime() {
        if (this.mRefreshTimeRunnable == null) {
            this.mRefreshTimeRunnable = new Runnable() {
                public void run() {
                    SyncStateInfo.this.doRefreshTime();
                }
            };
        }
        stopRefreshTime();
        synchronized (this.mObservable) {
            if (this.mObservable.size() > 0) {
                ThreadManager.getMainHandler().postDelayed(this.mRefreshTimeRunnable, 60000);
            }
        }
    }

    private void stopRefreshTime() {
        if (this.mRefreshTimeRunnable != null) {
            ThreadManager.getMainHandler().removeCallbacks(this.mRefreshTimeRunnable);
        }
    }

    /* access modifiers changed from: private */
    public void triggerSync(Context context) {
        Log.d("SyncStateInfo", "triggerSync");
        SyncUtil.requestSync(context, new SyncRequest.Builder().setSyncType(this.mSyncType).setSyncReason(Long.MAX_VALUE).setDelayUpload(false).build());
    }

    private void updateDirtyCount(Context context) {
        DirtyCount dirtyCount = SyncStateUtil.getDirtyCount(context);
        if (!Objects.equals(dirtyCount, this.mDirtyCount)) {
            this.mDirtyCount = dirtyCount;
            this.mIsDirtyChanged = true;
            updateDirtySize(context);
        }
    }

    private void updateDirtySize(Context context) {
        long[] dirtySize = SyncStateUtil.getDirtySize(context);
        this.mDirtySize = dirtySize[0] + dirtySize[1];
    }

    /* access modifiers changed from: private */
    public void updateSyncStatus(Context context) {
        AsyncTask<Context, Void, SyncStatus> asyncTask = this.mUpdateTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        this.mUpdateTask = new AsyncTask<Context, Void, SyncStatus>() {
            private boolean isOuterConditionLimit(SyncStatus syncStatus) {
                return syncStatus == SyncStatus.DISCONNECTED || syncStatus == SyncStatus.NO_WIFI || syncStatus == SyncStatus.BATTERY_LOW || syncStatus == SyncStatus.SYSTEM_SPACE_LOW || syncStatus == SyncStatus.CLOUD_SPACE_FULL || syncStatus == SyncStatus.SYNC_PAUSE;
            }

            private boolean needNotifyObservers(SyncStatus syncStatus) {
                return SyncStateInfo.this.mSyncStatus != syncStatus || SyncStateInfo.this.mSyncStatus == SyncStatus.SYNC_PAUSE || SyncStateInfo.this.mIsDirtyChanged || SyncStateInfo.this.mIsSyncedChanged;
            }

            private boolean needTriggerSync(Context context, SyncStatus syncStatus) {
                if (syncStatus != SyncStatus.UNKNOWN_ERROR || !isOuterConditionLimit(SyncStateInfo.this.mSyncStatus)) {
                    return false;
                }
                Log.d("SyncStateInfo", "condition -> ok, trigger sync");
                return true;
            }

            /* access modifiers changed from: protected */
            public SyncStatus doInBackground(Context[] contextArr) {
                Context context = contextArr[0];
                SyncStatus access$200 = SyncStateInfo.this.updateSyncStatusInternal(context);
                if (needTriggerSync(context, access$200)) {
                    SyncStateInfo.this.triggerSync(context);
                }
                return access$200;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(SyncStatus syncStatus) {
                Log.i("SyncStateInfo", "update status old: %s, new: %s, syncType: %s, mIsDirtyChanged: %s, mIsSyncedChanged: %s", SyncStateInfo.this.mSyncStatus, syncStatus, SyncStateInfo.this.mSyncType, Boolean.valueOf(SyncStateInfo.this.mIsDirtyChanged), Boolean.valueOf(SyncStateInfo.this.mIsSyncedChanged));
                if (needNotifyObservers(syncStatus)) {
                    SyncStatus unused = SyncStateInfo.this.mSyncStatus = syncStatus;
                    boolean unused2 = SyncStateInfo.this.mIsDirtyChanged = false;
                    boolean unused3 = SyncStateInfo.this.mIsSyncedChanged = false;
                    SyncStateInfo.this.notifyObservers();
                }
            }
        };
        this.mUpdateTask.execute(new Context[]{context.getApplicationContext()});
    }

    /* access modifiers changed from: private */
    public SyncStatus updateSyncStatusInternal(Context context) {
        boolean z = this.mIsMediaChanged;
        this.mIsMediaChanged = false;
        init(context);
        if (!GalleryPreferences.CTA.canConnectNetwork()) {
            return SyncStatus.CTA_NOT_ALLOW;
        }
        Account account = AccountCache.getAccount();
        if (account == null) {
            return SyncStatus.NO_ACCOUNT;
        }
        if (!SyncStateUtil.isMasterSyncAutomatically()) {
            return SyncStatus.MASTER_SYNC_OFF;
        }
        if (!SyncStateUtil.isSyncAutomatically()) {
            return SyncStatus.SYNC_OFF;
        }
        long resumeTime = SyncUtil.getResumeTime(context);
        if (resumeTime > System.currentTimeMillis()) {
            Log.i("SyncStateInfo", "start refresh time");
            this.mResumeTime = resumeTime;
            startRefreshTime();
            if (z) {
                updateDirtyCount(context);
            }
            if (this.mDirtyCount.getSyncableCount() > 0) {
                return SyncStatus.SYNC_PAUSE;
            }
        } else {
            Log.i("SyncStateInfo", "stop refresh time");
            this.mResumeTime = -1;
            stopRefreshTime();
        }
        if (Preference.sIsFirstSynced()) {
            if (z) {
                updateDirtyCount(context);
            }
            if (this.mDirtyCount.getSyncableCount() == 0) {
                if (this.mDirtyCount.getOversizedCount() > 0) {
                    return SyncStatus.SYNCED_WITH_OVERSIZED_FILE;
                }
                if (this.mSyncStatus != SyncStatus.SYNCED || z) {
                    updateSyncedCount(context);
                }
                return SyncStatus.SYNCED;
            } else if (checkIsSyncing(account) && isMeetPushCondition()) {
                return SyncStatus.SYNCING;
            } else {
                if (this.mIsLocalSpaceFull) {
                    return SyncStatus.SYSTEM_SPACE_LOW;
                }
                if (checkIsPendingUpload(account) && isMeetPushCondition()) {
                    return SyncStatus.SYNC_PENDING;
                }
                if (!NetworkUtils.isNetworkConnected()) {
                    return SyncStatus.DISCONNECTED;
                }
                if (SpaceFullHandler.isOwnerSpaceFull()) {
                    SyncStateUtil.CloudSpaceInfo cloudSpaceInfo = SyncStateUtil.getCloudSpaceInfo(context);
                    this.mCloudSpaceTotalSize = cloudSpaceInfo.getTotal();
                    this.mCloudSpaceRemainingSize = this.mCloudSpaceTotalSize - cloudSpaceInfo.getUsed();
                    return SyncStatus.CLOUD_SPACE_FULL;
                }
                if (NetworkUtils.isActiveNetworkMetered()) {
                    if (this.mSyncType == SyncType.GPRS_FORCE) {
                        return SyncStatus.SYNC_ERROR;
                    }
                    if (GalleryPreferences.Sync.getBackupOnlyInWifi()) {
                        return SyncStatus.NO_WIFI;
                    }
                    if (this.mSyncStatus == SyncStatus.NO_WIFI) {
                        triggerSync(context);
                    }
                }
                if (this.mIsBatteryLow) {
                    return this.mSyncType.isForce() ? SyncStatus.SYNC_ERROR : SyncStatus.BATTERY_LOW;
                }
            }
        } else if (checkIsSyncingMetaData(account)) {
            updateSyncedCount(context);
            return SyncStatus.SYNCING_METADATA;
        } else if (this.mIsLocalSpaceFull) {
            return SyncStatus.SYSTEM_SPACE_LOW;
        } else {
            if (checkIsPendingMetaData(account) && isMeetPullCondition()) {
                if (this.mImageSyncedCount == -1 && this.mVideoSyncedCount == -1) {
                    updateSyncedCount(context);
                }
                return SyncStatus.SYNC_META_PENDING;
            } else if (!NetworkUtils.isNetworkConnected()) {
                return SyncStatus.DISCONNECTED;
            } else {
                if (this.mIsBatteryLow && !this.mSyncType.isForce()) {
                    return SyncStatus.BATTERY_LOW;
                }
            }
        }
        return SyncStatus.UNKNOWN_ERROR;
    }

    private void updateSyncedCount(Context context) {
        int[] syncedCount = SyncStateUtil.getSyncedCount(context);
        int i = syncedCount[0];
        int i2 = syncedCount[1];
        if (i != this.mImageSyncedCount || i2 != this.mVideoSyncedCount) {
            this.mIsSyncedChanged = true;
            this.mImageSyncedCount = i;
            this.mVideoSyncedCount = i2;
        }
    }

    public boolean containsReason(long j) {
        long j2 = this.mSyncReason;
        return (j | j2) == j2;
    }

    public long getCloudSpaceRemainingSize() {
        return this.mCloudSpaceRemainingSize;
    }

    public long getCloudSpaceTotalSize() {
        return this.mCloudSpaceTotalSize;
    }

    public int[] getDirtyCount() {
        return new int[]{this.mDirtyCount.getTotalImageCount(), this.mDirtyCount.getTotalVideoCount()};
    }

    public long getDirtySize() {
        return this.mDirtySize;
    }

    public long getResumeInterval() {
        return Math.max(0, this.mResumeTime - System.currentTimeMillis());
    }

    public long getSyncReason() {
        return this.mSyncReason;
    }

    public SyncStatus getSyncStatus() {
        return this.mSyncStatus;
    }

    public SyncType getSyncType() {
        return this.mSyncType;
    }

    public int[] getSyncedCount() {
        return new int[]{this.mImageSyncedCount, this.mVideoSyncedCount};
    }

    /* access modifiers changed from: package-private */
    public void invalidate() {
        if (ThreadManager.getMainHandler().hasCallbacksCompat(this.mUpdateRunnable)) {
            Log.d("SyncStateInfo", "has pending runnable, ignore");
        } else {
            ThreadManager.getMainHandler().postDelayed(this.mUpdateRunnable, 300);
        }
    }

    /* access modifiers changed from: package-private */
    public void onSyncCommandDispatched() {
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void registerObserver(OnSyncStateChangeObserver onSyncStateChangeObserver) {
        if (onSyncStateChangeObserver != null) {
            synchronized (this.mObservable) {
                if (!this.mObservable.contains(onSyncStateChangeObserver)) {
                    this.mObservable.add(onSyncStateChangeObserver);
                } else {
                    throw new IllegalStateException("Observer " + onSyncStateChangeObserver + " is already registered.");
                }
            }
            if (this.mSyncStatus != SyncStatus.UNAVAILABLE) {
                onSyncStateChangeObserver.onSyncStateChanged(this);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The observer is null.");
    }

    /* access modifiers changed from: package-private */
    public void setIsBatteryLow(boolean z) {
        if (this.mIsBatteryLow != z) {
            invalidate();
        }
        this.mIsBatteryLow = z;
    }

    /* access modifiers changed from: package-private */
    public void setIsLocalSpaceFull(boolean z) {
        if (this.mIsLocalSpaceFull != z) {
            invalidate();
        }
        this.mIsLocalSpaceFull = z;
    }

    /* access modifiers changed from: package-private */
    public void setSyncReason(long j) {
        synchronized (this.mLock) {
            if (this.mSyncReason != j) {
                Log.d("SyncStateInfo", "setSyncReason old: %s, new: %s", Long.toBinaryString(this.mSyncReason), Long.toBinaryString(j));
                this.mSyncReason = j;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSyncType(SyncType syncType) {
        synchronized (this.mLock) {
            if (this.mSyncType != syncType) {
                Log.d("SyncStateInfo", "setSyncType old: %s, new: %s", this.mSyncType, syncType);
                this.mSyncType = syncType;
                invalidate();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void triggerMediaChanged() {
        this.mIsMediaChanged = true;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void unregisterObserver(OnSyncStateChangeObserver onSyncStateChangeObserver) {
        if (onSyncStateChangeObserver != null) {
            synchronized (this.mObservable) {
                int indexOf = this.mObservable.indexOf(onSyncStateChangeObserver);
                if (indexOf != -1) {
                    this.mObservable.remove(indexOf);
                } else {
                    throw new IllegalStateException("Observer " + onSyncStateChangeObserver + " was not registered.");
                }
            }
            return;
        }
        throw new IllegalArgumentException("The observer is null.");
    }
}
