package com.miui.gallery.cloud.syncstate;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.control.UploadInfo;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deprecated.Preference;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class SyncMonitor {
    private AtomicInteger isSyncStarted;
    private Context mContext;
    private Worker mWorker;

    private static final class Singleton {
        /* access modifiers changed from: private */
        public static final SyncMonitor INSTANCE = new SyncMonitor();
    }

    public static class UploadInfoAnalyzer {
        /* access modifiers changed from: private */
        public long mAvgSpeed;
        /* access modifiers changed from: private */
        public List<UploadInfo> mInfos = new LinkedList();
        private ReadWriteLock mLock = new ReentrantReadWriteLock();
        /* access modifiers changed from: private */
        public long mMaxSpeed;
        /* access modifiers changed from: private */
        public long mRecentSpeed;

        interface Func<T> {
            T doFunc();
        }

        /* access modifiers changed from: private */
        public long calculateSpeed(long j, long j2) {
            return (long) ((((float) j) * 1000.0f) / ((float) j2));
        }

        /* access modifiers changed from: private */
        public void clear() {
            safeRun(new Func<Void>() {
                public Void doFunc() {
                    UploadInfoAnalyzer uploadInfoAnalyzer = UploadInfoAnalyzer.this;
                    long unused = uploadInfoAnalyzer.mMaxSpeed = uploadInfoAnalyzer.mAvgSpeed = uploadInfoAnalyzer.mRecentSpeed = 0;
                    UploadInfoAnalyzer.this.mInfos.clear();
                    return null;
                }
            }, true);
        }

        private boolean isValidInfo(UploadInfo uploadInfo) {
            return uploadInfo != null && uploadInfo.size > 0 && uploadInfo.timely > 0;
        }

        private void lock(boolean z) {
            (z ? this.mLock.writeLock() : this.mLock.readLock()).lock();
        }

        private void unlock(boolean z) {
            (z ? this.mLock.writeLock() : this.mLock.readLock()).unlock();
        }

        public void addUploadInfo(final UploadInfo uploadInfo) {
            if (isValidInfo(uploadInfo)) {
                safeRun(new Func<Void>() {
                    public Void doFunc() {
                        UploadInfoAnalyzer uploadInfoAnalyzer = UploadInfoAnalyzer.this;
                        long unused = uploadInfoAnalyzer.mRecentSpeed = uploadInfoAnalyzer.calculateSpeed(uploadInfo.size, uploadInfo.timely);
                        if (UploadInfoAnalyzer.this.mRecentSpeed > UploadInfoAnalyzer.this.mMaxSpeed) {
                            UploadInfoAnalyzer uploadInfoAnalyzer2 = UploadInfoAnalyzer.this;
                            long unused2 = uploadInfoAnalyzer2.mMaxSpeed = uploadInfoAnalyzer2.mRecentSpeed;
                        }
                        UploadInfoAnalyzer.this.mInfos.add(uploadInfo);
                        long j = 0;
                        long j2 = 0;
                        for (UploadInfo uploadInfo : UploadInfoAnalyzer.this.mInfos) {
                            j += uploadInfo.size;
                            j2 += uploadInfo.timely;
                        }
                        UploadInfoAnalyzer uploadInfoAnalyzer3 = UploadInfoAnalyzer.this;
                        long unused3 = uploadInfoAnalyzer3.mAvgSpeed = uploadInfoAnalyzer3.calculateSpeed(j, j2);
                        return null;
                    }
                }, true);
            }
        }

        public long getAvgSpeed() {
            return ((Long) safeRun(new Func<Long>() {
                public Long doFunc() {
                    return Long.valueOf(UploadInfoAnalyzer.this.mAvgSpeed);
                }
            }, false)).longValue();
        }

        public long getRecentSpeed() {
            return ((Long) safeRun(new Func<Long>() {
                public Long doFunc() {
                    return Long.valueOf(UploadInfoAnalyzer.this.mRecentSpeed);
                }
            }, false)).longValue();
        }

        public long getUploadTraffic() {
            return ((Long) safeRun(new Func<Long>() {
                public Long doFunc() {
                    long j = 0;
                    for (UploadInfo uploadInfo : UploadInfoAnalyzer.this.mInfos) {
                        j += uploadInfo.size;
                    }
                    return Long.valueOf(j);
                }
            }, false)).longValue();
        }

        public <T> T safeRun(Func<T> func, boolean z) {
            lock(z);
            try {
                return func.doFunc();
            } finally {
                unlock(z);
            }
        }
    }

    private static final class Worker implements Handler.Callback {
        private static int TRAFFIC_STEP_INIT = 1;
        private final Object LOCK;
        private boolean isFirstBackground;
        private long mBackgroundStartTime;
        private Context mContext;
        private Handler mHandler;
        private long mLastCheckTime;
        private long mSyncStartTime;
        private Throwable mSyncThrowable;
        private int mTrafficSteps;
        private UploadInfoAnalyzer mUploadInfoAnalyzer;

        private class SyncInfo {
            final long reason;
            final SyncType type;

            private SyncInfo(SyncType syncType, long j) {
                this.type = syncType;
                this.reason = j;
            }
        }

        private Worker(Context context) {
            this.LOCK = new Object();
            this.mTrafficSteps = TRAFFIC_STEP_INIT;
            this.isFirstBackground = true;
            this.mContext = context;
        }

        private void check() {
            Log.d("SyncMonitor", "check");
            SyncType syncType = SyncStateManager.getInstance().getSyncType();
            if (syncType == null || !syncType.isForce()) {
                if (needMonitorTraffic() && NetworkUtils.isActiveNetworkMetered()) {
                    long uploadTraffic = getAnalyzer().getUploadTraffic();
                    if (uploadTraffic > ((long) this.mTrafficSteps) * getTrafficStepValue()) {
                        Log.w("SyncMonitor", "upload traffic %s", Long.valueOf(uploadTraffic));
                        this.mTrafficSteps++;
                    }
                }
                if (needMonitorSyncTime()) {
                    long syncUpperLimitTime = getSyncUpperLimitTime();
                    if (syncUpperLimitTime > 0 && System.currentTimeMillis() - this.mSyncStartTime > syncUpperLimitTime) {
                        Log.w("SyncMonitor", "sync time has reached upper limit");
                    }
                }
                if (!needMonitorBackground()) {
                    return;
                }
                if (!isCharging(this.mContext)) {
                    if (!MiscUtil.isAppProcessInForeground()) {
                        if (this.isFirstBackground) {
                            this.isFirstBackground = false;
                            statSyncInBackground();
                        }
                        Log.d("SyncMonitor", "sync type %s, app not in foreground", (Object) syncType);
                        if (this.mBackgroundStartTime == 0) {
                            Log.d("SyncMonitor", "monitor process changes to background");
                            this.mBackgroundStartTime = System.currentTimeMillis();
                            return;
                        }
                        long backgroundBufferTime = getBackgroundBufferTime();
                        if (backgroundBufferTime < 0 || System.currentTimeMillis() - this.mBackgroundStartTime < backgroundBufferTime) {
                            Log.d("SyncMonitor", "monitor background in buffer time %s", (Object) Long.valueOf(backgroundBufferTime));
                        } else if (getBackgroundUpperLimitTime() < 0) {
                            Log.d("SyncMonitor", "monitor background in upper limit time %s", (Object) Long.valueOf(getBackgroundUpperLimitTime()));
                        } else {
                            long[] dirtySize = SyncStateUtil.getDirtySize(this.mContext);
                            long j = dirtySize[0] + dirtySize[1];
                            long min = Math.min(getAnalyzer().getAvgSpeed(), getAnalyzer().getRecentSpeed());
                            if (min > 0) {
                                long j2 = (long) ((((float) j) * 1000.0f) / ((float) min));
                                Log.d("SyncMonitor", "need upload size %s, speed %s, predicted time %s", Long.valueOf(j), Long.valueOf(min), Long.valueOf(j2));
                                long currentTimeMillis = j2 + (System.currentTimeMillis() - this.mBackgroundStartTime);
                                Log.d("SyncMonitor", "predicted upper time %s, limit time %s", Long.valueOf(currentTimeMillis), Long.valueOf(getBackgroundUpperLimitTime()));
                                if (currentTimeMillis < getBackgroundUpperLimitTime()) {
                                    Log.d("SyncMonitor", "monitor background in upper limit time");
                                    return;
                                }
                            }
                            Log.w("SyncMonitor", "background sync time has reached upper limit, change sync policy");
                            SyncUtil.stopSync(this.mContext);
                            SyncUtil.requestSync(this.mContext, new SyncRequest.Builder().setSyncType(SyncType.BACKGROUND).setSyncReason(Long.MAX_VALUE).setDelayUpload(false).build());
                            statPolicyChanged();
                        }
                    } else if (this.mBackgroundStartTime > 0) {
                        Log.d("SyncMonitor", "monitor app focused, reset background start time");
                        this.mBackgroundStartTime = 0;
                    }
                } else if (this.mBackgroundStartTime > 0) {
                    Log.d("SyncMonitor", "monitor charging, reset background start time");
                    this.mBackgroundStartTime = 0;
                }
            } else {
                Log.d("SyncMonitor", "sync type %s, ignore monitor", (Object) syncType);
            }
        }

        private UploadInfoAnalyzer getAnalyzer() {
            if (this.mUploadInfoAnalyzer == null) {
                this.mUploadInfoAnalyzer = new UploadInfoAnalyzer();
            }
            return this.mUploadInfoAnalyzer;
        }

        private static long getBackgroundBufferTime() {
            return CloudControlStrategyHelper.getSyncStrategy().getMonitorBackBufferTime();
        }

        private static long getBackgroundUpperLimitTime() {
            return CloudControlStrategyHelper.getSyncStrategy().getMonitorBackUpperTime();
        }

        private static long getCheckInterval() {
            return CloudControlStrategyHelper.getSyncStrategy().getMonitorCheckInterval();
        }

        private Handler getHandler() {
            Handler handler;
            synchronized (this.LOCK) {
                if (this.mHandler == null) {
                    HandlerThread handlerThread = new HandlerThread("sync_monitor", 10);
                    handlerThread.start();
                    this.mHandler = new Handler(handlerThread.getLooper(), this);
                }
                handler = this.mHandler;
            }
            return handler;
        }

        private static long getSyncUpperLimitTime() {
            return CloudControlStrategyHelper.getSyncStrategy().getMonitorSyncUpperTime();
        }

        private static long getTrafficStepValue() {
            return CloudControlStrategyHelper.getSyncStrategy().getMonitorTrafficStep();
        }

        private void handleAppBackground() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:41:0x010c  */
        /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
        private void handleAppForeground() {
            int activePullTimes;
            Account account = AccountCache.getAccount();
            if (account != null && SyncUtil.isGalleryCloudSyncable(this.mContext)) {
                boolean isSyncActive = SyncStateUtil.isSyncActive();
                boolean isUploading = SyncStateUtil.isUploading();
                boolean isSyncPending = SyncStateUtil.isSyncPending();
                long j = Long.MAX_VALUE;
                if (SyncStateUtil.getDirtyCount(this.mContext).getSyncableCount() > 0) {
                    if (isSyncActive || isUploading) {
                        if (isUploading) {
                            SyncType syncType = SyncStateManager.getInstance().getSyncType();
                            long syncReason = SyncStateManager.getInstance().getSyncReason();
                            SyncStateManager.getInstance().setSyncType(SyncType.NORMAL, false);
                            Log.d("SyncMonitor", "uploading: swap sync type, cur type[%s], reason[%s]", syncType, Long.toBinaryString(syncReason));
                            if (isSyncPending) {
                                Log.d("SyncMonitor", "uploading, has pending sync, need request");
                            }
                        } else if (SyncStateManager.getInstance().containsReason(32)) {
                            SyncType syncType2 = SyncStateManager.getInstance().getSyncType();
                            long syncReason2 = SyncStateManager.getInstance().getSyncReason();
                            SyncStateManager.getInstance().setSyncType(SyncType.NORMAL, false);
                            Log.d("SyncMonitor", "sync active: swap sync type, cur type[%s], reason[%s]", syncType2, Long.toBinaryString(syncReason2));
                        } else {
                            Log.d("SyncMonitor", "sync active, won't upload, need request");
                        }
                        j = 33;
                        if (j == 0) {
                            SyncUtil.requestSync(this.mContext, new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(j).setDelayUpload(false).build());
                            Log.d("SyncMonitor", "app focused, request sync with reason[%s]", (Object) Long.toBinaryString(j));
                            return;
                        }
                        return;
                    }
                    if (isSyncPending) {
                        ContentResolver.cancelSync(account, "com.miui.gallery.cloud.provider");
                        Log.d("SyncMonitor", "sync pending: cancel existing, request again.");
                    } else {
                        Log.d("SyncMonitor", "no request: request again.");
                    }
                    if (j == 0) {
                    }
                } else if (!isSyncActive && !isSyncPending) {
                    if (!Preference.sIsFirstSynced()) {
                        Log.d("SyncMonitor", "Hasn't synced first, need request");
                    } else {
                        long lastSyncTimestamp = GalleryPreferences.Sync.getLastSyncTimestamp();
                        long currentTimeMillis = System.currentTimeMillis() - lastSyncTimestamp;
                        if (currentTimeMillis < 0 || currentTimeMillis > CloudControlStrategyHelper.getSyncStrategy().getIntervalForActivePull()) {
                            Log.d("SyncMonitor", "pull actively");
                            j = 1;
                            if (lastSyncTimestamp > 0 && !GalleryDateUtils.isSameDate(new Date(System.currentTimeMillis()), new Date(lastSyncTimestamp)) && (activePullTimes = GalleryPreferences.Sync.getActivePullTimes()) > 0) {
                                statActivePullTimes(activePullTimes);
                                GalleryPreferences.Sync.clearActivePullTimes();
                            }
                            GalleryPreferences.Sync.increaseActivePullTimes();
                        }
                    }
                    if (j == 0) {
                    }
                }
                j = 0;
                if (j == 0) {
                }
            }
        }

        private void handleNetworkingAccepted() {
            if (!Preference.sIsFirstSynced() && SyncUtil.isGalleryCloudSyncable(this.mContext)) {
                Log.d("SyncMonitor", "request sync after networking accepted");
                SyncUtil.requestSync(this.mContext, new SyncRequest.Builder().setSyncType(SyncType.POWER_FORCE).setSyncReason(Long.MAX_VALUE).build());
            }
        }

        private void handleNetworkingDenied() {
        }

        private void handleSyncStart(SyncInfo syncInfo) {
            this.mSyncThrowable = null;
            this.mSyncStartTime = System.currentTimeMillis();
            statSyncStart();
            SyncStateManager.getInstance().setSyncType(syncInfo.type, false);
            SyncStateManager.getInstance().mergeReason(syncInfo.reason);
        }

        private void handleSyncStartAppend(SyncInfo syncInfo) {
            SyncStateManager.getInstance().setSyncType(syncInfo.type, false);
            SyncStateManager.getInstance().mergeReason(syncInfo.reason);
        }

        private void handleSyncStop() {
            Log.d("SyncMonitor", "sync time %s", (Object) Long.valueOf(System.currentTimeMillis() - this.mSyncStartTime));
            this.mLastCheckTime = 0;
            this.mSyncStartTime = 0;
            this.mTrafficSteps = TRAFFIC_STEP_INIT;
            this.mBackgroundStartTime = 0;
            this.isFirstBackground = true;
            UploadInfoAnalyzer uploadInfoAnalyzer = this.mUploadInfoAnalyzer;
            if (uploadInfoAnalyzer != null) {
                uploadInfoAnalyzer.clear();
            }
            SyncStateManager.getInstance().setSyncType(SyncType.UNKNOW, true);
            SyncStateManager.getInstance().unmergeReason(Long.MAX_VALUE);
        }

        private void handleSyncThrowable(Throwable th) {
            this.mSyncThrowable = th;
        }

        private void handleUploadInfo(UploadInfo uploadInfo) {
            getAnalyzer().addUploadInfo(uploadInfo);
            tryCheck();
        }

        private static boolean isCharging(Context context) {
            long currentTimeMillis = System.currentTimeMillis();
            int intExtra = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
            Log.d("SyncMonitor", "judge charging state cost %s", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return intExtra == 2 || intExtra == 5;
        }

        private static String messageDesc(int i) {
            switch (i) {
                case 1:
                    return "RECORD_SYNC_START";
                case 2:
                    return "RECORD_UPLOAD_INFO";
                case 3:
                    return "RECORD_THROWABLE";
                case 4:
                    return "RECORD_SYNC_STOP";
                case 5:
                    return "RECORD_APP_FOREGROUND";
                case 6:
                    return "RECORD_APP_BACKGROUND";
                case 7:
                    return "RECORD_NETWORKING_ACCEPTED";
                case 8:
                    return "RECORD_NETWORKING_DENIED";
                case 9:
                    return "MSG_ON_HANDLE_REASON";
                case 10:
                    return "MSG_ON_SYNC_START_APPEND";
                default:
                    return "UNKNOWN";
            }
        }

        private static boolean needMonitorBackground() {
            return CloudControlStrategyHelper.getSyncStrategy().isMonitorBackground();
        }

        private static boolean needMonitorSyncTime() {
            return CloudControlStrategyHelper.getSyncStrategy().isMonitorSyncTime();
        }

        private static boolean needMonitorTraffic() {
            return CloudControlStrategyHelper.getSyncStrategy().isMonitorTraffic();
        }

        private void statActivePullTimes(int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("times", String.valueOf(i));
            GallerySamplingStatHelper.recordCountEvent("sync_monitor", "sync_active_pull_times", hashMap);
        }

        private void statPolicyChanged() {
            long[] dirtySize = SyncStateUtil.getDirtySize(this.mContext);
            HashMap hashMap = new HashMap();
            hashMap.put("dirty", String.valueOf(dirtySize[0] + dirtySize[1]));
            GallerySamplingStatHelper.recordCountEvent("sync_monitor", "monitor_sync_policy_changed_dirty", hashMap);
        }

        private void statSyncInBackground() {
            GallerySamplingStatHelper.recordCountEvent("sync_monitor", "monitor_sync_in_background");
            long[] dirtySize = SyncStateUtil.getDirtySize(this.mContext);
            long j = dirtySize[0] + dirtySize[1];
            long min = Math.min(getAnalyzer().getAvgSpeed(), getAnalyzer().getRecentSpeed());
            if (min > 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("predictedTime", String.valueOf((long) ((((float) j) * 1.0f) / ((float) min))));
                GallerySamplingStatHelper.recordCountEvent("sync_monitor", "monitor_background_predicted_time", hashMap);
            }
        }

        private void statSyncStart() {
            GallerySamplingStatHelper.recordCountEvent("sync_monitor", "monitor_sync_start");
        }

        private void tryCheck() {
            if (System.currentTimeMillis() - this.mLastCheckTime >= getCheckInterval()) {
                this.mLastCheckTime = System.currentTimeMillis();
                check();
            }
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    handleSyncStart((SyncInfo) message.obj);
                    break;
                case 2:
                    handleUploadInfo((UploadInfo) message.obj);
                    break;
                case 3:
                    handleSyncThrowable((Throwable) message.obj);
                    break;
                case 4:
                    handleSyncStop();
                    break;
                case 5:
                    handleAppForeground();
                    break;
                case 6:
                    handleAppBackground();
                    break;
                case 7:
                    handleNetworkingAccepted();
                    break;
                case 8:
                    handleNetworkingDenied();
                    break;
                case 10:
                    handleSyncStartAppend((SyncInfo) message.obj);
                    break;
            }
            Log.d("SyncMonitor", messageDesc(message.what));
            return true;
        }

        public void onHandleReason(long j) {
            getHandler().obtainMessage(9, Long.valueOf(j)).sendToTarget();
        }

        public void onNetworkingAcceptedToggled(boolean z) {
            if (z) {
                getHandler().obtainMessage(7).sendToTarget();
            } else {
                getHandler().obtainMessage(8).sendToTarget();
            }
        }

        public void onProcessLifecycleToggled(boolean z) {
            if (z) {
                getHandler().obtainMessage(5).sendToTarget();
            } else {
                getHandler().obtainMessage(6).sendToTarget();
            }
        }

        public void onSyncEnd() {
            getHandler().obtainMessage(4).sendToTarget();
        }

        public void onSyncStart(SyncType syncType, long j) {
            getHandler().obtainMessage(1, new SyncInfo(syncType, j)).sendToTarget();
        }

        public void onSyncStartAppend(SyncType syncType, long j) {
            getHandler().obtainMessage(10, new SyncInfo(syncType, j)).sendToTarget();
        }

        public void onSyncThrowable(Throwable th) {
            getHandler().obtainMessage(3, th).sendToTarget();
        }

        public void onUpload(UploadInfo uploadInfo) {
            getHandler().obtainMessage(2, uploadInfo).sendToTarget();
        }
    }

    private SyncMonitor() {
        this.isSyncStarted = new AtomicInteger();
        this.mContext = StaticContext.sGetAndroidContext();
        this.mWorker = new Worker(this.mContext);
        monitorProcessLifecycle();
        monitorNetworkingAccepted();
    }

    public static final SyncMonitor getInstance() {
        return Singleton.INSTANCE;
    }

    private boolean isEnable() {
        return CloudControlStrategyHelper.getSyncStrategy().isMonitorEnable();
    }

    private void monitorNetworkingAccepted() {
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                SyncMonitor.this.onNetworkingAcceptedToggled(AgreementsUtils.isNetworkingAgreementAccepted());
            }
        }, new IntentFilter("com.miui.gallery.action.CTA_CHANGED"));
    }

    private void monitorProcessLifecycle() {
        ThreadManager.getMainHandler().post(new Runnable() {
            public void run() {
                ProcessLifecycleOwner.get().getLifecycle().addObserver(new DefaultLifecycleObserver() {
                    public void onStart(LifecycleOwner lifecycleOwner) {
                        if (lifecycleOwner == ProcessLifecycleOwner.get()) {
                            SyncMonitor.this.onProcessLifecycleToggled(true);
                        }
                    }

                    public void onStop(LifecycleOwner lifecycleOwner) {
                        if (lifecycleOwner == ProcessLifecycleOwner.get()) {
                            SyncMonitor.this.onProcessLifecycleToggled(false);
                        }
                    }
                });
            }
        });
    }

    public void onHandleReason(long j) {
        if (isEnable()) {
            Log.d("SyncMonitor", "monitor handle reason %s", (Object) Long.toBinaryString(j));
            this.mWorker.onHandleReason(j);
        }
    }

    public void onNetworkingAcceptedToggled(boolean z) {
        if (isEnable()) {
            this.mWorker.onNetworkingAcceptedToggled(z);
        }
    }

    public void onProcessLifecycleToggled(boolean z) {
        if (isEnable()) {
            this.mWorker.onProcessLifecycleToggled(z);
        }
    }

    public void onSyncEnd() {
        if (isEnable()) {
            if (this.isSyncStarted.decrementAndGet() == 0) {
                Log.d("SyncMonitor", "monitor sync stop");
                this.mWorker.onSyncEnd();
                return;
            }
            Log.d("SyncMonitor", "sync hasn't stopped");
        }
    }

    public void onSyncStart(SyncType syncType, long j) {
        if (isEnable()) {
            if (this.isSyncStarted.incrementAndGet() == 1) {
                Log.d("SyncMonitor", "monitor sync start, syncType %s, reason %s", syncType, Long.toBinaryString(j));
                this.mWorker.onSyncStart(syncType, j);
                return;
            }
            Log.d("SyncMonitor", "sync has started, append syncType %s, reason %s", syncType, Long.toBinaryString(j));
            this.mWorker.onSyncStartAppend(syncType, j);
        }
    }

    public void onSyncThrowable(Throwable th) {
        if (isEnable()) {
            this.mWorker.onSyncThrowable(th);
        }
    }

    public void onUpload(UploadInfo uploadInfo) {
        if (isEnable()) {
            this.mWorker.onUpload(uploadInfo);
        }
    }
}
