package com.miui.gallery.util;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncRequest;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.miui.account.AccountHelper;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.cloud.SyncConditionManager;
import com.miui.gallery.cloud.UpDownloadManager;
import com.miui.gallery.cloud.base.SyncConstants;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.policy.IPolicy;
import com.miui.gallery.cloud.policy.SyncPolicyManager;
import com.miui.gallery.cloud.syncstate.SyncStateManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GalleryIntent;
import com.miui.gallery.util.deprecated.Preference;
import java.util.HashMap;
import miui.cloud.sdk.CloudResolverCompat;
import miui.gallery.support.SyncCompat;

public class SyncUtil {
    /* access modifiers changed from: private */
    public static void doRequestSync(Context context, SyncRequest syncRequest) {
        Context applicationContext = context.getApplicationContext();
        if (isGalleryCloudSyncable(applicationContext)) {
            if (isSyncPause(applicationContext)) {
                SyncLog.d("SyncUtil", "sync has pause");
                return;
            }
            SyncType wrapSyncType = wrapSyncType(syncRequest.getSyncType());
            SyncRequest build = new SyncRequest.Builder().cloneFrom(syncRequest).setSyncType(wrapSyncType).build();
            SyncLog.d("SyncUtil", "requestSync: request[%s] %s", build, TextUtils.join("\n\t", Thread.currentThread().getStackTrace()));
            if (AccountHelper.getXiaomiAccount(applicationContext) == null) {
                SyncLog.w("SyncUtil", "account is null");
                return;
            }
            android.content.SyncRequest packSyncParams = packSyncParams(applicationContext, build);
            if (packSyncParams != null) {
                ContentResolver.requestSync(packSyncParams);
                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(new Intent("com.miui.gallery.SYNC_COMMAND_DISPATCHED"));
                GalleryPreferences.Sync.markRequestStartTimeIfNone(wrapSyncType);
                return;
            }
            SyncLog.e("SyncUtil", "sync request null");
        }
    }

    public static boolean existXiaomiAccount(Context context) {
        if (context != null) {
            return AccountHelper.getXiaomiAccount(context) != null;
        }
        Log.e("SyncUtil", "existXiaomiAccount context null");
        return false;
    }

    public static long getResumeTime(Context context) {
        if (context == null) {
            Log.e("SyncUtil", "getResumeTime context null");
            return -1;
        }
        Account account = AccountCache.getAccount();
        if (account != null) {
            return CloudResolverCompat.getResumeTime(context.getApplicationContext(), account, "com.miui.gallery.cloud.provider");
        }
        Log.e("SyncUtil", "getResumeTime account null");
        return -1;
    }

    public static boolean isGalleryCloudSyncable(Context context) {
        if (context == null) {
            Log.e("SyncUtil", "isGalleryCloudSyncable context null");
            return false;
        }
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(context);
        return xiaomiAccount != null && ContentResolver.getMasterSyncAutomatically() && ContentResolver.getSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider");
    }

    private static boolean isMetaDataRequest() {
        return !Preference.sIsFirstSynced();
    }

    public static boolean isSyncPause(Context context) {
        if (context == null) {
            Log.e("SyncUtil", "isSyncPause context null");
            return false;
        }
        Account account = AccountCache.getAccount();
        if (account != null) {
            return CloudResolverCompat.isSyncPaused(context, account, "com.miui.gallery.cloud.provider");
        }
        Log.e("SyncUtil", "isSyncPause account null");
        return false;
    }

    public static android.content.SyncRequest packSyncParams(Context context, SyncRequest syncRequest) {
        boolean z;
        boolean z2;
        if (context == null) {
            Log.e("SyncUtil", "packSyncParams context null");
            return null;
        }
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(context);
        if (xiaomiAccount == null) {
            return null;
        }
        SyncRequest.Builder syncOnce = new SyncRequest.Builder().setSyncAdapter(xiaomiAccount, "com.miui.gallery.cloud.provider").syncOnce();
        IPolicy policy = SyncPolicyManager.getInstance().getPolicy(syncRequest.getSyncType());
        if (policy == null || !policy.isEnable()) {
            Log.w("SyncUtil", "no policy for %s", syncRequest.getSyncType());
            policy = SyncPolicyManager.getInstance().getPolicy(SyncType.NORMAL);
        }
        boolean z3 = false;
        boolean z4 = policy != null && policy.isEnable();
        if (!z4) {
            Log.w("SyncUtil", "policy not valid %s", policy);
        }
        boolean isManual = syncRequest.isManual();
        boolean z5 = syncRequest.getSyncType().isForce() || syncRequest.isManual();
        if (isMetaDataRequest()) {
            z = false;
            z2 = true;
        } else if (z4) {
            z3 = policy.isDisallowMetered();
            z = policy.isRequireCharging();
            z2 = policy.isIgnoreBatteryLow();
        } else {
            z2 = false;
            z = false;
            z3 = true;
        }
        syncOnce.setDisallowMetered(z3).setExpedited(z5).setManual(isManual);
        if (z) {
            SyncCompat.setRequiresCharging(syncOnce, z);
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("sync_no_delay", !syncRequest.isDelayUpload());
        bundle.putString("sync_type", syncRequest.getSyncType().getIdentifier());
        bundle.putLong("sync_reason", syncRequest.getSyncReason());
        if (!z3) {
            bundle.putBoolean("micloud_ignore_wifi_settings", true);
        }
        if (z2) {
            bundle.putBoolean("micloud_ignore_battery_low", true);
        }
        if (isManual) {
            bundle.putBoolean(SyncConstants.Sync.EXTRA_SYNC_FORCE, true);
            bundle.putBoolean("force", true);
        }
        syncOnce.setExtras(bundle);
        return syncOnce.build();
    }

    public static void pauseSync(Context context, long j) {
        if (context == null) {
            Log.e("SyncUtil", "pauseSync context null");
            return;
        }
        Account account = AccountCache.getAccount();
        if (account == null) {
            Log.e("SyncUtil", "pauseSync account null");
            return;
        }
        CloudResolverCompat.pauseSync(context.getApplicationContext(), account, "com.miui.gallery.cloud.provider", j);
        stopSync(context.getApplicationContext());
    }

    public static void requestSync(Context context, com.miui.gallery.cloud.base.SyncRequest syncRequest) {
        if (context == null) {
            Log.e("SyncUtil", "requestSync context null");
        } else if (!AgreementsUtils.isNetworkingAgreementAccepted()) {
            Log.w("SyncUtil", "networking agreement hasn't accepted");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            requestSyncInWorkThread(context, syncRequest);
        } else {
            doRequestSync(context, syncRequest);
        }
    }

    private static void requestSyncInWorkThread(Context context, final com.miui.gallery.cloud.base.SyncRequest syncRequest) {
        final Context applicationContext = context.getApplicationContext();
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<Object>() {
            public Object run(ThreadPool.JobContext jobContext) {
                SyncUtil.doRequestSync(applicationContext, syncRequest);
                return null;
            }
        });
    }

    public static void resumeSync(Context context) {
        if (context == null) {
            Log.e("SyncUtil", "resumeSync context null");
            return;
        }
        Account account = AccountCache.getAccount();
        if (account == null) {
            Log.e("SyncUtil", "pauseSync account null");
        } else {
            CloudResolverCompat.resumeSync(context.getApplicationContext(), account, "com.miui.gallery.cloud.provider");
        }
    }

    public static void setBackupOnlyWifi(Context context, boolean z) {
        Account xiaomiAccount;
        if (context == null) {
            Log.e("SyncUtil", "setBackupOnlyWifi context null");
            return;
        }
        GalleryPreferences.Sync.setBackupOnlyInWifi(z);
        if (!z && (xiaomiAccount = AccountHelper.getXiaomiAccount(context)) != null && !ContentResolver.isSyncActive(xiaomiAccount, "com.miui.gallery.cloud.provider") && ContentResolver.isSyncPending(xiaomiAccount, "com.miui.gallery.cloud.provider")) {
            SyncType wrapSyncType = wrapSyncType(SyncStateManager.getInstance().getSyncType());
            if (SyncConditionManager.checkIgnoreCancel(5, wrapSyncType) == 0) {
                long syncReason = SyncStateManager.getInstance().getSyncReason();
                if (syncReason == 0) {
                    syncReason = 33;
                }
                SyncLog.d("SyncUtil", "not back only wifi, sync pending, but condition ok, request reason[%s]", (Object) Long.toBinaryString(syncReason));
                requestSync(context, new SyncRequest.Builder().setSyncType(wrapSyncType).setSyncReason(syncReason).build());
            }
        }
    }

    public static boolean setSyncAutomatically(Context context, boolean z) {
        if (context == null) {
            Log.e("SyncUtil", "switchBackup context null");
            return false;
        }
        Account xiaomiAccount = AccountHelper.getXiaomiAccount(context);
        if (!z) {
            stopSync(context);
        } else if (!PrivacyAgreementUtils.isCloudServiceAgreementEnable(context) && IntentUtil.startCloudMainPage(context)) {
            return false;
        } else {
            if (xiaomiAccount == null) {
                IntentUtil.guideToLoginXiaomiAccount(context, GalleryIntent.CloudGuideSource.AUTOBACKUP);
                return false;
            }
        }
        statSyncEnabledEvent(z);
        ContentResolver.setSyncAutomatically(xiaomiAccount, "com.miui.gallery.cloud.provider", z);
        return true;
    }

    public static void statSyncEnabledEvent(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("state", Boolean.toString(z));
        GallerySamplingStatHelper.recordCountEvent("micloud_sync", "sync_enabled", hashMap);
    }

    public static void stopSync(Context context) {
        if (context == null) {
            Log.e("SyncUtil", "stopSync context null");
            return;
        }
        Account account = AccountCache.getAccount();
        if (account == null) {
            Log.e("SyncUtil", "stopSync account null");
            return;
        }
        ContentResolver.cancelSync(account, "com.miui.gallery.cloud.provider");
        SyncConditionManager.setCancelledForAllBackground(true);
        UpDownloadManager.cancelAllBackgroundPriority(true, true);
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.miui.gallery.SYNC_COMMAND_DISPATCHED"));
    }

    public static SyncType unpackSyncType(Bundle bundle) {
        if (bundle != null) {
            if (bundle.containsKey("sync_type")) {
                return SyncType.fromIdentifier(bundle.getString("sync_type"));
            }
            if (bundle.getBoolean("micloud_ignore_wifi_settings", false)) {
                return SyncType.GPRS_FORCE;
            }
            if (bundle.getBoolean("micloud_ignore_battery_low", false)) {
                return SyncType.POWER_FORCE;
            }
        }
        return SyncType.NORMAL;
    }

    public static SyncType wrapSyncType(SyncType syncType) {
        return (syncType == SyncType.NORMAL || syncType == SyncType.UNKNOW || syncType == SyncType.BACKGROUND) ? MiscUtil.isAppProcessInForeground() ? SyncType.NORMAL : SyncType.BACKGROUND : syncType;
    }
}
