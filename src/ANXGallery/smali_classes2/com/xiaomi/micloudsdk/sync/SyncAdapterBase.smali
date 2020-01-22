.class public abstract Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;
.super Landroid/content/AbstractThreadedSyncAdapter;
.source "SyncAdapterBase.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;
    }
.end annotation


# instance fields
.field protected isForceSync:Z

.field protected isIgnoreBackoff:Z

.field protected isIgnoreBatteryLow:Z

.field protected isIgnoreSettings:Z

.field protected isIgnoreTemperature:Z

.field protected isIgnoreWifiSettings:Z

.field protected isManualSync:Z

.field protected mAccount:Landroid/accounts/Account;

.field protected final mAuthType:Ljava/lang/String;

.field protected mAuthority:Ljava/lang/String;

.field protected mContext:Landroid/content/Context;

.field protected mExtToken:Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;

.field protected mExtTokenStr:Ljava/lang/String;

.field protected mNumbers:[Ljava/lang/String;

.field protected mResolver:Landroid/content/ContentResolver;

.field private mSyncLogSender:Lcom/xiaomi/micloudsdk/sync/SyncLogSender;

.field protected mSyncResult:Landroid/content/SyncResult;

.field protected mTickets:[Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/content/Context;ZLjava/lang/String;)V
    .locals 1

    invoke-direct {p0, p1, p2}, Landroid/content/AbstractThreadedSyncAdapter;-><init>(Landroid/content/Context;Z)V

    const/4 p2, 0x2

    new-array v0, p2, [Ljava/lang/String;

    iput-object v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mTickets:[Ljava/lang/String;

    new-array p2, p2, [Ljava/lang/String;

    iput-object p2, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mNumbers:[Ljava/lang/String;

    iput-object p1, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    iput-object p1, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mResolver:Landroid/content/ContentResolver;

    iput-object p3, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAuthType:Ljava/lang/String;

    return-void
.end method

.method private acquireLogger(Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    invoke-static {v0, p1}, Lcom/xiaomi/micloudsdk/sync/SyncLogSenderFactory;->newSyncLogSender(Landroid/content/Context;Ljava/lang/String;)Lcom/xiaomi/micloudsdk/sync/SyncLogSender;

    move-result-object p1

    iput-object p1, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mSyncLogSender:Lcom/xiaomi/micloudsdk/sync/SyncLogSender;

    iget-object p1, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mSyncLogSender:Lcom/xiaomi/micloudsdk/sync/SyncLogSender;

    invoke-virtual {p1}, Lcom/xiaomi/micloudsdk/sync/SyncLogSender;->openSyncLog()V

    return-void
.end method

.method private getExtTokenStr(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)Ljava/lang/String;
    .locals 9

    const/4 v0, 0x0

    :goto_0
    const/4 v1, 0x3

    if-ge v0, v1, :cond_4

    const-string v1, "MiCloudSyncAdapterBase"

    if-eqz v0, :cond_0

    const-wide/16 v2, 0x3e8

    :try_start_0
    invoke-static {v2, v3}, Ljava/lang/Thread;->sleep(J)V

    goto :goto_1

    :catch_0
    move-exception p1

    goto :goto_2

    :catch_1
    move-exception v2

    goto :goto_3

    :catch_2
    move-exception v2

    goto :goto_4

    :catch_3
    move-exception v2

    goto :goto_5

    :cond_0
    :goto_1
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "onPerformSync: getting auth token. authority: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static {p1}, Landroid/accounts/AccountManager;->get(Landroid/content/Context;)Landroid/accounts/AccountManager;

    move-result-object v3

    iget-object v5, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAuthType:Ljava/lang/String;

    const/4 v6, 0x1

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object v4, p2

    invoke-virtual/range {v3 .. v8}, Landroid/accounts/AccountManager;->getAuthToken(Landroid/accounts/Account;Ljava/lang/String;ZLandroid/accounts/AccountManagerCallback;Landroid/os/Handler;)Landroid/accounts/AccountManagerFuture;

    move-result-object v2

    if-nez v2, :cond_1

    const-string v2, "onPerformSync: getExtTokenStr null future."

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_6

    :cond_1
    invoke-interface {v2}, Landroid/accounts/AccountManagerFuture;->getResult()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/os/Bundle;

    if-nez v2, :cond_2

    const-string v2, "onPerformSync: getExtTokenStr null future result."

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_6

    :cond_2
    const-string v3, "authtoken"

    invoke-virtual {v2, v3}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_3

    const-string v2, "onPerformSync: getExtTokenStr future result textEmpty."

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/accounts/OperationCanceledException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Landroid/accounts/AuthenticatorException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_6

    :cond_3
    return-object v2

    :goto_2
    const-string p2, "onPerformSync getExtTokenStr InterruptedException: "

    invoke-static {v1, p2, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Thread;->interrupt()V

    goto :goto_7

    :goto_3
    const-string v3, "onPerformSync getExtTokenStr IOException: "

    invoke-static {v1, v3, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_6

    :goto_4
    const-string v3, "onPerformSync getExtTokenStr AuthenticatorException: "

    invoke-static {v1, v3, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_6

    :goto_5
    const-string v3, "onPerformSync getExtTokenStr OperationCanceledException: "

    invoke-static {v1, v3, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_6
    add-int/lit8 v0, v0, 0x1

    goto/16 :goto_0

    :cond_4
    :goto_7
    const/4 p1, 0x0

    return-object p1
.end method

.method private static getExtraErrorMsgForResultOrNullIfFieldNotExists(Landroid/content/SyncResult;)Ljava/lang/String;
    .locals 0

    invoke-static {p0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->getResultMessageOrNullIfFieldNotExists(Landroid/content/SyncResult;)Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;

    move-result-object p0

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;->get()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static getResultMessageOrNullIfFieldNotExists(Landroid/content/SyncResult;)Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;
    .locals 2

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "miSyncResult"

    invoke-static {v0, v1}, Lcom/xiaomi/micloudsdk/utils/ReflectUtils;->getField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    :try_start_0
    invoke-virtual {v0, p0}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "resultMessage"

    invoke-static {v0, v1}, Lcom/xiaomi/micloudsdk/utils/ReflectUtils;->getField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    if-eqz v0, :cond_1

    new-instance v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;

    invoke-direct {v1, p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;-><init>(Ljava/lang/Object;Ljava/lang/reflect/Field;)V

    return-object v1

    :cond_1
    new-instance p0, Ljava/lang/RuntimeException;

    const-string v0, "resultMessageField is null: Please file a bug to CloudService!!"

    invoke-direct {p0, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_0
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    new-instance p0, Ljava/lang/RuntimeException;

    const-string v0, "get miSyncResultField: Please file a bug to CloudService!!"

    invoke-direct {p0, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method private static internalSetAuthError(Landroid/content/SyncResult;)V
    .locals 4

    iget-object p0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v0, p0, Landroid/content/SyncStats;->numAuthExceptions:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Landroid/content/SyncStats;->numAuthExceptions:J

    return-void
.end method

.method private static internalSetCTAError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "permission_error"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetCloudStorageFullError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "cloud_storage_full"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetGDPRError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "gdpr_error"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetPauseSyncError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "pause_limit"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetPermissionLimit(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "permission_limit"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetRequestError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numIoExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numIoExceptions:J

    const-string v0, "request_error"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetSecureSpaceLimitError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "secure_space_limit"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetSimActivatedError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "sim_activated_error"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetSyncSwitchOffError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "switch_off"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static internalSetUnknownError(Landroid/content/SyncResult;)V
    .locals 4

    iget-object p0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v0, p0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    return-void
.end method

.method private static internalSetWlanOnlyError(Landroid/content/SyncResult;)V
    .locals 5

    iget-object v0, p0, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, v0, Landroid/content/SyncStats;->numConflictDetectedExceptions:J

    const-string v0, "wlan_only"

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V

    return-void
.end method

.method private static invalidTokenAndReSyncNowIfFirstTimeFailed(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 3

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0, p4}, Landroid/os/Bundle;-><init>(Landroid/os/Bundle;)V

    const-string p4, "token_invalid_retry"

    const/4 v1, 0x0

    invoke-virtual {v0, p4, v1}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    const-string v2, "MiCloudSyncAdapterBase"

    if-eqz v1, :cond_0

    const-string p0, "Already retry with invalid token but still failed"

    invoke-static {v2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    :cond_0
    const/4 v1, 0x1

    invoke-virtual {v0, p4, v1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    const-string p4, "AuthToken expired. Invalid and retry now"

    invoke-static {v2, p4}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static {p0}, Landroid/accounts/AccountManager;->get(Landroid/content/Context;)Landroid/accounts/AccountManager;

    move-result-object p0

    iget-object p4, p1, Landroid/accounts/Account;->type:Ljava/lang/String;

    invoke-virtual {p0, p4, p3}, Landroid/accounts/AccountManager;->invalidateAuthToken(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1, p2, v0}, Landroid/content/ContentResolver;->requestSync(Landroid/accounts/Account;Ljava/lang/String;Landroid/os/Bundle;)V

    :goto_0
    return-void
.end method

.method private releaseLogger()V
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mSyncLogSender:Lcom/xiaomi/micloudsdk/sync/SyncLogSender;

    invoke-virtual {v0}, Lcom/xiaomi/micloudsdk/sync/SyncLogSender;->release()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mSyncLogSender:Lcom/xiaomi/micloudsdk/sync/SyncLogSender;

    return-void
.end method

.method private static setExtraErrorMsgForResultIfFieldExists(Landroid/content/SyncResult;Ljava/lang/String;)V
    .locals 0

    invoke-static {p0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->getResultMessageOrNullIfFieldNotExists(Landroid/content/SyncResult;)Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;

    move-result-object p0

    if-nez p0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0, p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase$ResultMessage;->set(Ljava/lang/String;)V

    return-void
.end method

.method private static setSyncResultByLocalException(Lcom/xiaomi/micloudsdk/exception/SyncLocalException;Landroid/content/SyncResult;)V
    .locals 0

    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/exception/SyncLocalException;->getErrorCode()I

    move-result p0

    invoke-static {p0, p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setSyncResultByLocalExceptionCode(ILandroid/content/SyncResult;)V

    return-void
.end method

.method private static setSyncResultByLocalExceptionCode(ILandroid/content/SyncResult;)V
    .locals 0

    packed-switch p0, :pswitch_data_0

    packed-switch p0, :pswitch_data_1

    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetUnknownError(Landroid/content/SyncResult;)V

    goto :goto_0

    :pswitch_0
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetWlanOnlyError(Landroid/content/SyncResult;)V

    goto :goto_0

    :pswitch_1
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetCloudStorageFullError(Landroid/content/SyncResult;)V

    goto :goto_0

    :pswitch_2
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetGDPRError(Landroid/content/SyncResult;)V

    goto :goto_0

    :pswitch_3
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetSecureSpaceLimitError(Landroid/content/SyncResult;)V

    goto :goto_0

    :pswitch_4
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetPermissionLimit(Landroid/content/SyncResult;)V

    goto :goto_0

    :pswitch_5
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetSimActivatedError(Landroid/content/SyncResult;)V

    goto :goto_0

    :pswitch_6
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetCTAError(Landroid/content/SyncResult;)V

    :goto_0
    return-void

    :pswitch_data_0
    .packed-switch 0x3e8
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x7d0
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private static setSyncResultByServerException(Lcom/xiaomi/micloudsdk/exception/CloudServerException;Landroid/content/SyncResult;)V
    .locals 2

    iget v0, p0, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->statusCode:I

    const/16 v1, -0x2711

    if-eq v0, v1, :cond_4

    const/16 v1, 0x193

    if-eq v0, v1, :cond_3

    const/16 v1, 0x196

    if-eq v0, v1, :cond_3

    const/16 v1, 0x190

    if-eq v0, v1, :cond_3

    const/16 v1, 0x191

    if-eq v0, v1, :cond_2

    iget v0, p0, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->code:I

    const v1, 0xcb23

    if-ne v0, v1, :cond_0

    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetGDPRError(Landroid/content/SyncResult;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->is5xxServerException()Z

    move-result p0

    if-eqz p0, :cond_1

    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetRequestError(Landroid/content/SyncResult;)V

    goto :goto_0

    :cond_1
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetUnknownError(Landroid/content/SyncResult;)V

    goto :goto_0

    :cond_2
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetAuthError(Landroid/content/SyncResult;)V

    goto :goto_0

    :cond_3
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetRequestError(Landroid/content/SyncResult;)V

    goto :goto_0

    :cond_4
    iget p0, p0, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->code:I

    invoke-static {p0, p1}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setSyncResultByLocalExceptionCode(ILandroid/content/SyncResult;)V

    :goto_0
    return-void
.end method


# virtual methods
.method protected abstract onPerformMiCloudSync(Landroid/os/Bundle;)V
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/micloudsdk/exception/CloudServerException;,
            Lcom/xiaomi/micloudsdk/exception/SyncLocalException;
        }
    .end annotation
.end method

.method public onPerformSync(Landroid/accounts/Account;Landroid/os/Bundle;Ljava/lang/String;Landroid/content/ContentProviderClient;Landroid/content/SyncResult;)V
    .locals 25

    move-object/from16 v1, p0

    move-object/from16 v2, p1

    move-object/from16 v3, p2

    move-object/from16 v15, p3

    move-object/from16 v13, p5

    const-string v4, "MiCloudSyncAdapterBase"

    const-string v0, "check_sync_error_refactor"

    invoke-static {v4, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iput-object v2, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAccount:Landroid/accounts/Account;

    iput-object v15, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAuthority:Ljava/lang/String;

    iput-object v13, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mSyncResult:Landroid/content/SyncResult;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v11

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "SyncLog["

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "]"

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    const-string v0, "onPerformSync: ---sync start---"

    invoke-static {v4, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "authority: "

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", "

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "extras: "

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual/range {p2 .. p2}, Landroid/os/Bundle;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v4, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    sget-object v0, Lcom/xiaomi/micloudsdk/utils/MiCloudConstants$SYNC;->SYNC_EXTRAS_FORCE:Ljava/lang/String;

    const/4 v5, 0x0

    invoke-virtual {v3, v0, v5}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isForceSync:Z

    const-string v0, "micloud_ignore_temperature"

    invoke-virtual {v3, v0, v5}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isIgnoreTemperature:Z

    const-string v0, "micloud_ignore_wifi_settings"

    invoke-virtual {v3, v0, v5}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isIgnoreWifiSettings:Z

    const-string v0, "micloud_ignore_battery_low"

    invoke-virtual {v3, v0, v5}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isIgnoreBatteryLow:Z

    const-string v0, "force"

    invoke-virtual {v3, v0, v5}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isManualSync:Z

    const-string v0, "ignore_backoff"

    invoke-virtual {v3, v0, v5}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isIgnoreBackoff:Z

    const-string v0, "ignore_settings"

    invoke-virtual {v3, v0, v5}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isIgnoreSettings:Z

    iget-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isForceSync:Z

    if-nez v0, :cond_1

    iget-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isManualSync:Z

    if-nez v0, :cond_1

    iget-boolean v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->isIgnoreSettings:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    move v14, v5

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v14, 0x1

    :goto_1
    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    invoke-static {v0, v14}, Lcom/xiaomi/micloudsdk/sync/SyncStatUtil;->statOnSyncStart(Landroid/content/Context;Z)V

    invoke-direct {v1, v15}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->acquireLogger(Ljava/lang/String;)V

    new-instance v0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncStartLogInfo;

    invoke-direct {v0, v15, v11, v12, v3}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncStartLogInfo;-><init>(Ljava/lang/String;JLandroid/os/Bundle;)V

    invoke-virtual {v0}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo;->generateLogInfoString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v10, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->syncLog(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    invoke-static {v0, v15}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->generateCurrentSyncDataInfo(Landroid/content/Context;Ljava/lang/String;)Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;

    move-result-object v16

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Before Sync: "

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual/range {v16 .. v16}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v10, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->syncLog(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    invoke-static {v0, v13}, Lmicloud/compat/v18/sync/SyncAdapterBaseInjector;->isGdprPermissionGranted(Landroid/content/Context;Landroid/content/SyncResult;)Z

    move-result v0

    const-string v7, "auth_token_error"

    if-nez v0, :cond_2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Gdpr Permission deny: "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v4, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetGDPRError(Landroid/content/SyncResult;)V

    const/16 v5, 0x7d0

    const-string v0, "gdpr_deny"

    :goto_2
    move v9, v5

    :goto_3
    const-wide/16 v7, 0x0

    const/16 v17, 0x1

    goto/16 :goto_7

    :cond_2
    invoke-static {}, Landroid/content/ContentResolver;->getMasterSyncAutomatically()Z

    move-result v0

    if-eqz v0, :cond_a

    invoke-static {v2, v15}, Landroid/content/ContentResolver;->getSyncAutomatically(Landroid/accounts/Account;Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_3

    goto/16 :goto_6

    :cond_3
    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    iget-object v5, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAccount:Landroid/accounts/Account;

    iget-object v6, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAuthority:Ljava/lang/String;

    invoke-static {v0, v5, v6}, Lcom/xiaomi/micloudsdk/sync/MiCloudResolver;->isSyncPausing(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    const-string v0, "onPerformSync: sync is set to pause. do not sync!!"

    invoke-static {v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetPauseSyncError(Landroid/content/SyncResult;)V

    invoke-static {}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->getPauseLimitErrorLegacyCode()I

    move-result v5

    const-string v0, "pause_limit"

    goto :goto_2

    :cond_4
    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    iget-object v5, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAccount:Landroid/accounts/Account;

    iget-object v6, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAuthority:Ljava/lang/String;

    invoke-direct {v1, v0, v5, v6}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->getExtTokenStr(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtTokenStr:Ljava/lang/String;

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtTokenStr:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_5

    const-string v0, "onPerformSync: Tried 3 times, but get null token."

    invoke-static {v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetAuthError(Landroid/content/SyncResult;)V

    invoke-static {}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->getAuthTokenErrorLegacyCode()I

    move-result v5

    :goto_4
    move v9, v5

    move-object v0, v7

    goto :goto_3

    :cond_5
    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtTokenStr:Ljava/lang/String;

    invoke-static {v0}, Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;->parse(Ljava/lang/String;)Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;

    move-result-object v0

    iput-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtToken:Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtToken:Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;

    invoke-virtual {v1, v0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->onTransformExtAuthToken(Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;)V

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtToken:Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;

    if-nez v0, :cond_6

    const-string v0, "onPerformSync: Cannot parse ext token"

    invoke-static {v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetAuthError(Landroid/content/SyncResult;)V

    invoke-static {}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->getAuthTokenErrorLegacyCode()I

    move-result v5

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    iget-object v4, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtTokenStr:Ljava/lang/String;

    invoke-static {v0, v2, v15, v4, v3}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->invalidTokenAndReSyncNowIfFirstTimeFailed(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V

    goto :goto_4

    :cond_6
    :try_start_0
    invoke-virtual {v1, v3}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->onPerformMiCloudSync(Landroid/os/Bundle;)V
    :try_end_0
    .catch Lcom/xiaomi/micloudsdk/exception/CloudServerException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lcom/xiaomi/micloudsdk/exception/SyncLocalException; {:try_start_0 .. :try_end_0} :catch_0

    invoke-virtual/range {p5 .. p5}, Landroid/content/SyncResult;->hasError()Z

    move-result v0

    if-eqz v0, :cond_7

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->getErrorCodeBySyncResult(Landroid/content/SyncResult;)I

    move-result v5

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/stat/MiCloudErrorStatReason;->getSyncResultErrorReasonForStats(Landroid/content/SyncResult;)Ljava/lang/String;

    move-result-object v0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "onPerformSync: Error without throw CloudException: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/utils/SyncExceptionUtils;->getErrorStringForLog(Landroid/content/SyncResult;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v4, v2}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_2

    :cond_7
    const-string v0, "onPerformSync: No error found. ---Success---"

    invoke-static {v4, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const-string v0, "no_error"

    const-wide/16 v7, 0x0

    const/4 v9, 0x0

    const/16 v17, 0x0

    goto/16 :goto_7

    :catch_0
    move-exception v0

    move-object v2, v0

    invoke-static {v2, v13}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setSyncResultByLocalException(Lcom/xiaomi/micloudsdk/exception/SyncLocalException;Landroid/content/SyncResult;)V

    invoke-static {v2}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->getErrorCodeByLocalException(Lcom/xiaomi/micloudsdk/exception/SyncLocalException;)I

    move-result v5

    invoke-static {v2}, Lcom/xiaomi/micloudsdk/stat/MiCloudErrorStatReason;->getLocalExceptionReasonForStats(Lcom/xiaomi/micloudsdk/exception/SyncLocalException;)Ljava/lang/String;

    move-result-object v0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "onPerformSync local exception, reason: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v4, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_2

    :catch_1
    move-exception v0

    move-object v5, v0

    invoke-static {v5, v13}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->setSyncResultByServerException(Lcom/xiaomi/micloudsdk/exception/CloudServerException;Landroid/content/SyncResult;)V

    invoke-static {v5}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->getErrorCodeByServerException(Lcom/xiaomi/micloudsdk/exception/CloudServerException;)I

    move-result v0

    invoke-static {v5}, Lcom/xiaomi/micloudsdk/stat/MiCloudErrorStatReason;->getServerExceptionReasonForStats(Lcom/xiaomi/micloudsdk/exception/CloudServerException;)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "onPerformSync server exception, reason: "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v4, v7}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    iget v4, v5, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->statusCode:I

    const/16 v7, 0x191

    if-ne v4, v7, :cond_8

    iget-object v4, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    iget-object v7, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mExtTokenStr:Ljava/lang/String;

    invoke-static {v4, v2, v15, v7, v3}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->invalidTokenAndReSyncNowIfFirstTimeFailed(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V

    :cond_8
    invoke-virtual {v5}, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->is5xxServerException()Z

    move-result v2

    if-eqz v2, :cond_9

    invoke-virtual {v5}, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->get5xxServerExceptionRetryTime()J

    move-result-wide v2

    const-wide/16 v4, 0x3e8

    div-long/2addr v2, v4

    const-wide/16 v4, 0x708

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v2

    move-wide v8, v2

    goto :goto_5

    :cond_9
    const-wide/16 v8, 0x0

    :goto_5
    move-wide v7, v8

    const/16 v17, 0x1

    move v9, v0

    move-object v0, v6

    goto :goto_7

    :cond_a
    :goto_6
    const-string v0, "onPerformSync: sync switch off. do not sync!!"

    invoke-static {v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->internalSetSyncSwitchOffError(Landroid/content/SyncResult;)V

    invoke-static {}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->getSyncSwitchOffErrorLegacyCode()I

    move-result v5

    const-string v0, "switch_off"

    goto/16 :goto_2

    :goto_7
    iget-object v2, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    invoke-static {v2, v15}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->generateCurrentSyncDataInfo(Landroid/content/Context;Ljava/lang/String;)Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;

    move-result-object v18

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "After Sync: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual/range {v18 .. v18}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v10, v2}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->syncLog(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v19

    new-instance v21, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;

    xor-int/lit8 v4, v17, 0x1

    invoke-static/range {p5 .. p5}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->getExtraErrorMsgForResultOrNullIfFieldNotExists(Landroid/content/SyncResult;)Ljava/lang/String;

    move-result-object v22

    move-object/from16 v2, v21

    move-object/from16 v3, p3

    move-wide v5, v11

    move-wide/from16 v23, v7

    move-wide/from16 v7, v19

    move v15, v9

    move-object/from16 v9, v22

    invoke-direct/range {v2 .. v9}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;-><init>(Ljava/lang/String;ZJJLjava/lang/String;)V

    invoke-virtual/range {v21 .. v21}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo;->generateLogInfoString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v10, v2}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->syncLog(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v2, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    xor-int/lit8 v4, v17, 0x1

    invoke-virtual/range {v16 .. v16}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->getUnSyncedDataCount()I

    move-result v3

    int-to-long v7, v3

    invoke-virtual/range {v18 .. v18}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->getUnSyncedDataCount()I

    move-result v3

    int-to-long v9, v3

    move v3, v14

    move-object v5, v0

    move-object/from16 v6, p3

    move/from16 p1, v15

    move-object v15, v13

    move-wide/from16 v13, v19

    invoke-static/range {v2 .. v14}, Lcom/xiaomi/micloudsdk/sync/SyncStatUtil;->statOnSyncEnd(Landroid/content/Context;ZZLjava/lang/String;Ljava/lang/String;JJJJ)V

    if-eqz v17, :cond_b

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    move/from16 v5, p1

    move-object/from16 v2, p3

    invoke-static {v0, v2, v5}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->recordSyncResult(Landroid/content/Context;Ljava/lang/String;I)V

    goto :goto_8

    :cond_b
    move-object/from16 v2, p3

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    invoke-static {v0, v2}, Lcom/xiaomi/micloudsdk/sync/utils/LegacySyncRecordUtils;->recordSyncResultSuccess(Landroid/content/Context;Ljava/lang/String;)V

    :goto_8
    iget-wide v2, v15, Landroid/content/SyncResult;->delayUntil:J

    const-wide/16 v4, 0x0

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2

    iput-wide v2, v15, Landroid/content/SyncResult;->delayUntil:J

    iget-wide v2, v15, Landroid/content/SyncResult;->delayUntil:J

    iget-object v0, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mContext:Landroid/content/Context;

    iget-object v4, v1, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mAuthority:Ljava/lang/String;

    invoke-static {v0, v4}, Lcom/xiaomi/micloudsdk/sync/utils/SyncTimeUtils;->getGuardDelayUntilInSec(Landroid/content/Context;Ljava/lang/String;)J

    move-result-wide v4

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2

    iput-wide v2, v15, Landroid/content/SyncResult;->delayUntil:J

    if-eqz v17, :cond_c

    iget-wide v2, v15, Landroid/content/SyncResult;->delayUntil:J

    move-wide/from16 v8, v23

    invoke-static {v2, v3, v8, v9}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2

    iput-wide v2, v15, Landroid/content/SyncResult;->delayUntil:J

    :cond_c
    invoke-direct/range {p0 .. p0}, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->releaseLogger()V

    return-void
.end method

.method protected onTransformExtAuthToken(Lcom/xiaomi/micloudsdk/data/ExtendedAuthToken;)V
    .locals 0

    return-void
.end method

.method protected syncLog(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncAdapterBase;->mSyncLogSender:Lcom/xiaomi/micloudsdk/sync/SyncLogSender;

    invoke-virtual {v0, p1, p2}, Lcom/xiaomi/micloudsdk/sync/SyncLogSender;->sendLog(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "tag or msg is null!"

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method
