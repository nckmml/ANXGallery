package com.xiaomi.micloudsdk.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.nexstreaming.nexeditorsdk.nexProject;
import com.xiaomi.micloudsdk.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.exception.SyncLocalException;
import com.xiaomi.micloudsdk.stat.MiCloudErrorStatReason;
import com.xiaomi.micloudsdk.sync.SyncLogInfo;
import com.xiaomi.micloudsdk.sync.utils.LegacySyncRecordUtils;
import com.xiaomi.micloudsdk.sync.utils.SyncExceptionUtils;
import com.xiaomi.micloudsdk.sync.utils.SyncTimeUtils;
import com.xiaomi.micloudsdk.utils.MiCloudConstants;
import com.xiaomi.micloudsdk.utils.ReflectUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import micloud.compat.v18.sync.SyncAdapterBaseInjector;

public abstract class SyncAdapterBase extends AbstractThreadedSyncAdapter {
    protected boolean isForceSync;
    protected boolean isIgnoreBackoff;
    protected boolean isIgnoreBatteryLow;
    protected boolean isIgnoreSettings;
    protected boolean isIgnoreTemperature;
    protected boolean isIgnoreWifiSettings;
    protected boolean isManualSync;
    protected Account mAccount;
    protected final String mAuthType;
    protected String mAuthority;
    protected Context mContext;
    protected ExtendedAuthToken mExtToken;
    protected String mExtTokenStr;
    protected String[] mNumbers = new String[2];
    protected ContentResolver mResolver;
    private SyncLogSender mSyncLogSender;
    protected SyncResult mSyncResult;
    protected String[] mTickets = new String[2];

    static class ResultMessage {
        private final Object miSyncResult;
        private final Field resultMessageField;

        ResultMessage(Object obj, Field field) {
            this.miSyncResult = obj;
            this.resultMessageField = field;
        }

        public String get() {
            try {
                return (String) this.resultMessageField.get(this.miSyncResult);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("get resultMessageField: Please file a bug to CloudService!!", e);
            }
        }

        public void set(String str) {
            try {
                this.resultMessageField.set(this.miSyncResult, str);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("set miSyncResult: Please file a bug to CloudService!!", e);
            }
        }
    }

    public SyncAdapterBase(Context context, boolean z, String str) {
        super(context, z);
        this.mContext = context;
        this.mResolver = context.getContentResolver();
        this.mAuthType = str;
    }

    private void acquireLogger(String str) {
        this.mSyncLogSender = SyncLogSenderFactory.newSyncLogSender(this.mContext, str);
        this.mSyncLogSender.openSyncLog();
    }

    private String getExtTokenStr(Context context, Account account, String str) {
        for (int i = 0; i < 3; i++) {
            if (i != 0) {
                try {
                    Thread.sleep(1000);
                } catch (OperationCanceledException e) {
                    Log.e("MiCloudSyncAdapterBase", "onPerformSync getExtTokenStr OperationCanceledException: ", e);
                } catch (AuthenticatorException e2) {
                    Log.e("MiCloudSyncAdapterBase", "onPerformSync getExtTokenStr AuthenticatorException: ", e2);
                } catch (IOException e3) {
                    Log.e("MiCloudSyncAdapterBase", "onPerformSync getExtTokenStr IOException: ", e3);
                } catch (InterruptedException e4) {
                    Log.e("MiCloudSyncAdapterBase", "onPerformSync getExtTokenStr InterruptedException: ", e4);
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            Log.v("MiCloudSyncAdapterBase", "onPerformSync: getting auth token. authority: " + str);
            AccountManagerFuture<Bundle> authToken = AccountManager.get(context).getAuthToken(account, this.mAuthType, true, (AccountManagerCallback) null, (Handler) null);
            if (authToken == null) {
                Log.e("MiCloudSyncAdapterBase", "onPerformSync: getExtTokenStr null future.");
            } else {
                Bundle result = authToken.getResult();
                if (result == null) {
                    Log.e("MiCloudSyncAdapterBase", "onPerformSync: getExtTokenStr null future result.");
                } else {
                    String string = result.getString("authtoken");
                    if (!TextUtils.isEmpty(string)) {
                        return string;
                    }
                    Log.e("MiCloudSyncAdapterBase", "onPerformSync: getExtTokenStr future result textEmpty.");
                }
            }
        }
        return null;
    }

    private static String getExtraErrorMsgForResultOrNullIfFieldNotExists(SyncResult syncResult) {
        ResultMessage resultMessageOrNullIfFieldNotExists = getResultMessageOrNullIfFieldNotExists(syncResult);
        if (resultMessageOrNullIfFieldNotExists == null) {
            return null;
        }
        return resultMessageOrNullIfFieldNotExists.get();
    }

    private static ResultMessage getResultMessageOrNullIfFieldNotExists(SyncResult syncResult) {
        Field field = ReflectUtils.getField(syncResult.getClass(), "miSyncResult");
        if (field == null) {
            return null;
        }
        try {
            Object obj = field.get(syncResult);
            Field field2 = ReflectUtils.getField(obj.getClass(), "resultMessage");
            if (field2 != null) {
                return new ResultMessage(obj, field2);
            }
            throw new RuntimeException("resultMessageField is null: Please file a bug to CloudService!!");
        } catch (IllegalAccessException unused) {
            throw new RuntimeException("get miSyncResultField: Please file a bug to CloudService!!");
        }
    }

    private static void internalSetAuthError(SyncResult syncResult) {
        syncResult.stats.numAuthExceptions++;
    }

    private static void internalSetCTAError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "permission_error");
    }

    private static void internalSetCloudStorageFullError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "cloud_storage_full");
    }

    private static void internalSetGDPRError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "gdpr_error");
    }

    private static void internalSetPauseSyncError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "pause_limit");
    }

    private static void internalSetPermissionLimit(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "permission_limit");
    }

    private static void internalSetRequestError(SyncResult syncResult) {
        syncResult.stats.numIoExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "request_error");
    }

    private static void internalSetSecureSpaceLimitError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "secure_space_limit");
    }

    private static void internalSetSimActivatedError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "sim_activated_error");
    }

    private static void internalSetSyncSwitchOffError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "switch_off");
    }

    private static void internalSetUnknownError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
    }

    private static void internalSetWlanOnlyError(SyncResult syncResult) {
        syncResult.stats.numConflictDetectedExceptions++;
        setExtraErrorMsgForResultIfFieldExists(syncResult, "wlan_only");
    }

    private static void invalidTokenAndReSyncNowIfFirstTimeFailed(Context context, Account account, String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        if (bundle2.getBoolean("token_invalid_retry", false)) {
            Log.e("MiCloudSyncAdapterBase", "Already retry with invalid token but still failed");
            return;
        }
        bundle2.putBoolean("token_invalid_retry", true);
        Log.w("MiCloudSyncAdapterBase", "AuthToken expired. Invalid and retry now");
        AccountManager.get(context).invalidateAuthToken(account.type, str2);
        ContentResolver.requestSync(account, str, bundle2);
    }

    private void releaseLogger() {
        this.mSyncLogSender.release();
        this.mSyncLogSender = null;
    }

    private static void setExtraErrorMsgForResultIfFieldExists(SyncResult syncResult, String str) {
        ResultMessage resultMessageOrNullIfFieldNotExists = getResultMessageOrNullIfFieldNotExists(syncResult);
        if (resultMessageOrNullIfFieldNotExists != null) {
            resultMessageOrNullIfFieldNotExists.set(str);
        }
    }

    private static void setSyncResultByLocalException(SyncLocalException syncLocalException, SyncResult syncResult) {
        setSyncResultByLocalExceptionCode(syncLocalException.getErrorCode(), syncResult);
    }

    private static void setSyncResultByLocalExceptionCode(int i, SyncResult syncResult) {
        switch (i) {
            case 1000:
                internalSetCTAError(syncResult);
                return;
            case 1001:
                internalSetSimActivatedError(syncResult);
                return;
            case 1002:
                internalSetPermissionLimit(syncResult);
                return;
            case 1003:
                internalSetSecureSpaceLimitError(syncResult);
                return;
            default:
                switch (i) {
                    case nexProject.kAutoThemeTransitionDuration:
                        internalSetGDPRError(syncResult);
                        return;
                    case 2001:
                        internalSetCloudStorageFullError(syncResult);
                        return;
                    case 2002:
                        internalSetWlanOnlyError(syncResult);
                        return;
                    default:
                        internalSetUnknownError(syncResult);
                        return;
                }
        }
    }

    private static void setSyncResultByServerException(CloudServerException cloudServerException, SyncResult syncResult) {
        int i = cloudServerException.statusCode;
        if (i == -10001) {
            setSyncResultByLocalExceptionCode(cloudServerException.code, syncResult);
        } else if (i == 403 || i == 406 || i == 400) {
            internalSetRequestError(syncResult);
        } else if (i == 401) {
            internalSetAuthError(syncResult);
        } else if (cloudServerException.code == 52003) {
            internalSetGDPRError(syncResult);
        } else if (cloudServerException.is5xxServerException()) {
            internalSetRequestError(syncResult);
        } else {
            internalSetUnknownError(syncResult);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void onPerformMiCloudSync(Bundle bundle) throws CloudServerException, SyncLocalException;

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x02b3  */
    public void onPerformSync(Account account, Bundle bundle, String str, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        boolean z;
        int i;
        long j;
        String str2;
        int syncSwitchOffErrorLegacyCode;
        int authTokenErrorLegacyCode;
        Account account2 = account;
        Bundle bundle2 = bundle;
        String str3 = str;
        SyncResult syncResult2 = syncResult;
        Log.d("MiCloudSyncAdapterBase", "check_sync_error_refactor");
        this.mAccount = account2;
        this.mAuthority = str3;
        this.mSyncResult = syncResult2;
        long currentTimeMillis = System.currentTimeMillis();
        String str4 = "SyncLog[" + str3 + "]";
        Log.d("MiCloudSyncAdapterBase", "onPerformSync: ---sync start---");
        Log.d("MiCloudSyncAdapterBase", "authority: " + str3 + ", " + "extras: " + bundle.toString());
        this.isForceSync = bundle2.getBoolean(MiCloudConstants.SYNC.SYNC_EXTRAS_FORCE, false);
        this.isIgnoreTemperature = bundle2.getBoolean("micloud_ignore_temperature", false);
        this.isIgnoreWifiSettings = bundle2.getBoolean("micloud_ignore_wifi_settings", false);
        this.isIgnoreBatteryLow = bundle2.getBoolean("micloud_ignore_battery_low", false);
        this.isManualSync = bundle2.getBoolean("force", false);
        this.isIgnoreBackoff = bundle2.getBoolean("ignore_backoff", false);
        this.isIgnoreSettings = bundle2.getBoolean("ignore_settings", false);
        boolean z2 = this.isForceSync || this.isManualSync || this.isIgnoreSettings;
        SyncStatUtil.statOnSyncStart(this.mContext, z2);
        acquireLogger(str3);
        syncLog(str4, new SyncLogInfo.SyncStartLogInfo(str3, currentTimeMillis, bundle2).generateLogInfoString());
        SyncDataInfo generateCurrentSyncDataInfo = SyncDataInfo.generateCurrentSyncDataInfo(this.mContext, str3);
        syncLog(str4, "Before Sync: " + generateCurrentSyncDataInfo.toString());
        if (!SyncAdapterBaseInjector.isGdprPermissionGranted(this.mContext, syncResult2)) {
            Log.i("MiCloudSyncAdapterBase", "Gdpr Permission deny: " + str3);
            internalSetGDPRError(syncResult);
            syncSwitchOffErrorLegacyCode = nexProject.kAutoThemeTransitionDuration;
            str2 = "gdpr_deny";
        } else if (!ContentResolver.getMasterSyncAutomatically() || !ContentResolver.getSyncAutomatically(account2, str3)) {
            Log.e("MiCloudSyncAdapterBase", "onPerformSync: sync switch off. do not sync!!");
            internalSetSyncSwitchOffError(syncResult);
            syncSwitchOffErrorLegacyCode = LegacySyncRecordUtils.getSyncSwitchOffErrorLegacyCode();
            str2 = "switch_off";
        } else if (MiCloudResolver.isSyncPausing(this.mContext, this.mAccount, this.mAuthority)) {
            Log.e("MiCloudSyncAdapterBase", "onPerformSync: sync is set to pause. do not sync!!");
            internalSetPauseSyncError(syncResult);
            syncSwitchOffErrorLegacyCode = LegacySyncRecordUtils.getPauseLimitErrorLegacyCode();
            str2 = "pause_limit";
        } else {
            this.mExtTokenStr = getExtTokenStr(this.mContext, this.mAccount, this.mAuthority);
            if (TextUtils.isEmpty(this.mExtTokenStr)) {
                Log.e("MiCloudSyncAdapterBase", "onPerformSync: Tried 3 times, but get null token.");
                internalSetAuthError(syncResult);
                authTokenErrorLegacyCode = LegacySyncRecordUtils.getAuthTokenErrorLegacyCode();
            } else {
                this.mExtToken = ExtendedAuthToken.parse(this.mExtTokenStr);
                onTransformExtAuthToken(this.mExtToken);
                if (this.mExtToken == null) {
                    Log.e("MiCloudSyncAdapterBase", "onPerformSync: Cannot parse ext token");
                    internalSetAuthError(syncResult);
                    authTokenErrorLegacyCode = LegacySyncRecordUtils.getAuthTokenErrorLegacyCode();
                    invalidTokenAndReSyncNowIfFirstTimeFailed(this.mContext, account2, str3, this.mExtTokenStr, bundle2);
                } else {
                    try {
                        onPerformMiCloudSync(bundle2);
                        if (syncResult.hasError()) {
                            syncSwitchOffErrorLegacyCode = LegacySyncRecordUtils.getErrorCodeBySyncResult(syncResult);
                            str2 = MiCloudErrorStatReason.getSyncResultErrorReasonForStats(syncResult);
                            Log.w("MiCloudSyncAdapterBase", "onPerformSync: Error without throw CloudException: " + SyncExceptionUtils.getErrorStringForLog(syncResult));
                        } else {
                            Log.d("MiCloudSyncAdapterBase", "onPerformSync: No error found. ---Success---");
                            str2 = "no_error";
                            j = 0;
                            i = 0;
                            z = false;
                            SyncDataInfo generateCurrentSyncDataInfo2 = SyncDataInfo.generateCurrentSyncDataInfo(this.mContext, str3);
                            syncLog(str4, "After Sync: " + generateCurrentSyncDataInfo2.toString());
                            long currentTimeMillis2 = System.currentTimeMillis();
                            long j2 = j;
                            syncLog(str4, new SyncLogInfo.SyncEndLogInfo(str, z ^ true, currentTimeMillis, currentTimeMillis2, getExtraErrorMsgForResultOrNullIfFieldNotExists(syncResult)).generateLogInfoString());
                            int i2 = i;
                            SyncResult syncResult3 = syncResult2;
                            SyncStatUtil.statOnSyncEnd(this.mContext, z2, !z, str2, str, (long) generateCurrentSyncDataInfo.getUnSyncedDataCount(), (long) generateCurrentSyncDataInfo2.getUnSyncedDataCount(), currentTimeMillis, currentTimeMillis2);
                            if (z) {
                                LegacySyncRecordUtils.recordSyncResult(this.mContext, str, i2);
                            } else {
                                LegacySyncRecordUtils.recordSyncResultSuccess(this.mContext, str);
                            }
                            syncResult3.delayUntil = Math.max(syncResult3.delayUntil, 0);
                            syncResult3.delayUntil = Math.max(syncResult3.delayUntil, SyncTimeUtils.getGuardDelayUntilInSec(this.mContext, this.mAuthority));
                            if (z) {
                                syncResult3.delayUntil = Math.max(syncResult3.delayUntil, j2);
                            }
                            releaseLogger();
                        }
                    } catch (CloudServerException e) {
                        CloudServerException cloudServerException = e;
                        setSyncResultByServerException(cloudServerException, syncResult2);
                        int errorCodeByServerException = LegacySyncRecordUtils.getErrorCodeByServerException(cloudServerException);
                        String serverExceptionReasonForStats = MiCloudErrorStatReason.getServerExceptionReasonForStats(cloudServerException);
                        Log.e("MiCloudSyncAdapterBase", "onPerformSync server exception, reason: " + serverExceptionReasonForStats);
                        if (cloudServerException.statusCode == 401) {
                            invalidTokenAndReSyncNowIfFirstTimeFailed(this.mContext, account2, str3, this.mExtTokenStr, bundle2);
                        }
                        j = cloudServerException.is5xxServerException() ? Math.min(cloudServerException.get5xxServerExceptionRetryTime() / 1000, 1800) : 0;
                        z = true;
                        i = errorCodeByServerException;
                        str2 = serverExceptionReasonForStats;
                    } catch (SyncLocalException e2) {
                        SyncLocalException syncLocalException = e2;
                        setSyncResultByLocalException(syncLocalException, syncResult2);
                        syncSwitchOffErrorLegacyCode = LegacySyncRecordUtils.getErrorCodeByLocalException(syncLocalException);
                        str2 = MiCloudErrorStatReason.getLocalExceptionReasonForStats(syncLocalException);
                        Log.e("MiCloudSyncAdapterBase", "onPerformSync local exception, reason: " + str2);
                    }
                }
            }
            i = authTokenErrorLegacyCode;
            str2 = "auth_token_error";
            j = 0;
            z = true;
            SyncDataInfo generateCurrentSyncDataInfo22 = SyncDataInfo.generateCurrentSyncDataInfo(this.mContext, str3);
            syncLog(str4, "After Sync: " + generateCurrentSyncDataInfo22.toString());
            long currentTimeMillis22 = System.currentTimeMillis();
            long j22 = j;
            syncLog(str4, new SyncLogInfo.SyncEndLogInfo(str, z ^ true, currentTimeMillis, currentTimeMillis22, getExtraErrorMsgForResultOrNullIfFieldNotExists(syncResult)).generateLogInfoString());
            int i22 = i;
            SyncResult syncResult32 = syncResult2;
            SyncStatUtil.statOnSyncEnd(this.mContext, z2, !z, str2, str, (long) generateCurrentSyncDataInfo.getUnSyncedDataCount(), (long) generateCurrentSyncDataInfo22.getUnSyncedDataCount(), currentTimeMillis, currentTimeMillis22);
            if (z) {
            }
            syncResult32.delayUntil = Math.max(syncResult32.delayUntil, 0);
            syncResult32.delayUntil = Math.max(syncResult32.delayUntil, SyncTimeUtils.getGuardDelayUntilInSec(this.mContext, this.mAuthority));
            if (z) {
            }
            releaseLogger();
        }
        i = syncSwitchOffErrorLegacyCode;
        j = 0;
        z = true;
        SyncDataInfo generateCurrentSyncDataInfo222 = SyncDataInfo.generateCurrentSyncDataInfo(this.mContext, str3);
        syncLog(str4, "After Sync: " + generateCurrentSyncDataInfo222.toString());
        long currentTimeMillis222 = System.currentTimeMillis();
        long j222 = j;
        syncLog(str4, new SyncLogInfo.SyncEndLogInfo(str, z ^ true, currentTimeMillis, currentTimeMillis222, getExtraErrorMsgForResultOrNullIfFieldNotExists(syncResult)).generateLogInfoString());
        int i222 = i;
        SyncResult syncResult322 = syncResult2;
        SyncStatUtil.statOnSyncEnd(this.mContext, z2, !z, str2, str, (long) generateCurrentSyncDataInfo.getUnSyncedDataCount(), (long) generateCurrentSyncDataInfo222.getUnSyncedDataCount(), currentTimeMillis, currentTimeMillis222);
        if (z) {
        }
        syncResult322.delayUntil = Math.max(syncResult322.delayUntil, 0);
        syncResult322.delayUntil = Math.max(syncResult322.delayUntil, SyncTimeUtils.getGuardDelayUntilInSec(this.mContext, this.mAuthority));
        if (z) {
        }
        releaseLogger();
    }

    /* access modifiers changed from: protected */
    public void onTransformExtAuthToken(ExtendedAuthToken extendedAuthToken) {
    }

    /* access modifiers changed from: protected */
    public void syncLog(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("tag or msg is null!");
        }
        this.mSyncLogSender.sendLog(str, str2);
    }
}
