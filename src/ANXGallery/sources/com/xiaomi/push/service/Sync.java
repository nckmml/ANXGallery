package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.android.SharedPrefsCompat;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.DebugUtils;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.network.Network;
import java.util.concurrent.ConcurrentHashMap;

public final class Sync implements NetworkListener {
    /* access modifiers changed from: private */
    public static volatile Sync sSync;
    /* access modifiers changed from: private */
    public volatile boolean isSyncing = false;
    Context mContext;
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, SyncJob> mCurJobs = new ConcurrentHashMap<>();
    private long mLastSyncTime;
    /* access modifiers changed from: private */
    public SharedPreferences mPrefs;

    public static abstract class SyncJob implements Runnable {
        String group;
        long period;

        SyncJob(String str, long j) {
            this.group = str;
            this.period = j;
        }

        public void run() {
            if (Sync.sSync != null) {
                Context context = Sync.sSync.mContext;
                if (Network.isConnected(context)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    SharedPreferences access$300 = Sync.sSync.mPrefs;
                    if (currentTimeMillis - access$300.getLong(":ts-" + this.group, 0) > this.period || DebugUtils.isTesting(context)) {
                        SharedPreferences.Editor edit = Sync.sSync.mPrefs.edit();
                        SharedPrefsCompat.apply(edit.putLong(":ts-" + this.group, System.currentTimeMillis()));
                        sync(Sync.sSync);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public abstract void sync(Sync sync);
    }

    private Sync(Context context) {
        this.mContext = context.getApplicationContext();
        this.mPrefs = context.getSharedPreferences("sync", 0);
    }

    public static Sync getInstance(Context context) {
        if (sSync == null) {
            synchronized (Sync.class) {
                if (sSync == null) {
                    sSync = new Sync(context);
                }
            }
        }
        return sSync;
    }

    public String getString(String str, String str2) {
        SharedPreferences sharedPreferences = this.mPrefs;
        return sharedPreferences.getString(str + ":" + str2, "");
    }

    public void onNetwrokAvaible() {
        if (!this.isSyncing) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLastSyncTime >= 3600000) {
                this.mLastSyncTime = currentTimeMillis;
                this.isSyncing = true;
                ScheduledJobManager.getInstance(this.mContext).addOneShootJob((Runnable) new Runnable() {
                    public void run() {
                        try {
                            for (SyncJob run : Sync.this.mCurJobs.values()) {
                                run.run();
                            }
                        } catch (Exception e) {
                            MyLog.w("Sync job exception :" + e.getMessage());
                        }
                        boolean unused = Sync.this.isSyncing = false;
                    }
                }, (int) (Math.random() * 10.0d));
            }
        }
    }

    public void put(String str, String str2, String str3) {
        SharedPreferences.Editor edit = sSync.mPrefs.edit();
        SharedPrefsCompat.apply(edit.putString(str + ":" + str2, str3));
    }

    public void schedSync(SyncJob syncJob) {
        if (this.mCurJobs.putIfAbsent(syncJob.group, syncJob) == null) {
            ScheduledJobManager.getInstance(this.mContext).addOneShootJob((Runnable) syncJob, ((int) (Math.random() * 30.0d)) + 10);
        }
    }
}
