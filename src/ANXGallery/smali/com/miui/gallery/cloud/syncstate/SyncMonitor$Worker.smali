.class final Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;
.super Ljava/lang/Object;
.source "SyncMonitor.java"

# interfaces
.implements Landroid/os/Handler$Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cloud/syncstate/SyncMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1a
    name = "Worker"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;
    }
.end annotation


# static fields
.field private static TRAFFIC_STEP_INIT:I = 0x1


# instance fields
.field private final LOCK:Ljava/lang/Object;

.field private isFirstBackground:Z

.field private mBackgroundStartTime:J

.field private mContext:Landroid/content/Context;

.field private mHandler:Landroid/os/Handler;

.field private mLastCheckTime:J

.field private mSyncStartTime:J

.field private mSyncThrowable:Ljava/lang/Throwable;

.field private mTrafficSteps:I

.field private mUploadInfoAnalyzer:Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->LOCK:Ljava/lang/Object;

    sget v0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->TRAFFIC_STEP_INIT:I

    iput v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mTrafficSteps:I

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->isFirstBackground:Z

    iput-object p1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    return-void
.end method

.method synthetic constructor <init>(Landroid/content/Context;Lcom/miui/gallery/cloud/syncstate/SyncMonitor$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;-><init>(Landroid/content/Context;)V

    return-void
.end method

.method private check()V
    .locals 12

    const-string v0, "SyncMonitor"

    const-string v1, "check"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getSyncType()Lcom/miui/gallery/cloud/base/SyncType;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/base/SyncType;->isForce()Z

    move-result v2

    if-eqz v2, :cond_0

    const-string v2, "sync type %s, ignore monitor"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->needMonitorTraffic()Z

    move-result v2

    const/4 v3, 0x1

    if-eqz v2, :cond_1

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getAnalyzer()Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;->getUploadTraffic()J

    move-result-wide v4

    iget v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mTrafficSteps:I

    int-to-long v6, v2

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getTrafficStepValue()J

    move-result-wide v8

    mul-long/2addr v6, v8

    cmp-long v2, v4, v6

    if-lez v2, :cond_1

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const-string v4, "upload traffic %s"

    invoke-static {v0, v4, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mTrafficSteps:I

    add-int/2addr v2, v3

    iput v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mTrafficSteps:I

    :cond_1
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->needMonitorSyncTime()Z

    move-result v2

    const-wide/16 v4, 0x0

    if-eqz v2, :cond_2

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getSyncUpperLimitTime()J

    move-result-wide v6

    cmp-long v2, v6, v4

    if-lez v2, :cond_2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    iget-wide v10, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mSyncStartTime:J

    sub-long/2addr v8, v10

    cmp-long v2, v8, v6

    if-lez v2, :cond_2

    const-string v2, "sync time has reached upper limit"

    invoke-static {v0, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    :cond_2
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->needMonitorBackground()Z

    move-result v2

    if-eqz v2, :cond_b

    iget-object v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v2}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->isCharging(Landroid/content/Context;)Z

    move-result v2

    if-nez v2, :cond_a

    invoke-static {}, Lcom/miui/gallery/util/MiscUtil;->isAppProcessInForeground()Z

    move-result v2

    if-eqz v2, :cond_3

    iget-wide v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    cmp-long v1, v1, v4

    if-lez v1, :cond_b

    const-string v1, "monitor app focused, reset background start time"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iput-wide v4, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    goto/16 :goto_1

    :cond_3
    iget-boolean v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->isFirstBackground:Z

    const/4 v6, 0x0

    if-eqz v2, :cond_4

    iput-boolean v6, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->isFirstBackground:Z

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->statSyncInBackground()V

    :cond_4
    const-string v2, "sync type %s, app not in foreground"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-wide v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    cmp-long v1, v1, v4

    if-nez v1, :cond_5

    const-string v1, "monitor process changes to background"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    return-void

    :cond_5
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getBackgroundBufferTime()J

    move-result-wide v1

    cmp-long v7, v1, v4

    if-ltz v7, :cond_9

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v7

    iget-wide v9, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    sub-long/2addr v7, v9

    cmp-long v7, v7, v1

    if-gez v7, :cond_6

    goto/16 :goto_0

    :cond_6
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getBackgroundUpperLimitTime()J

    move-result-wide v1

    cmp-long v1, v1, v4

    if-gez v1, :cond_7

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getBackgroundUpperLimitTime()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "monitor background in upper limit time %s"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_7
    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v1}, Lcom/miui/gallery/cloud/syncstate/SyncStateUtil;->getDirtySize(Landroid/content/Context;)[J

    move-result-object v1

    aget-wide v7, v1, v6

    aget-wide v2, v1, v3

    add-long/2addr v7, v2

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getAnalyzer()Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;->getAvgSpeed()J

    move-result-wide v1

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getAnalyzer()Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    move-result-object v3

    invoke-virtual {v3}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;->getRecentSpeed()J

    move-result-wide v9

    invoke-static {v1, v2, v9, v10}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v1

    cmp-long v3, v1, v4

    if-lez v3, :cond_8

    const/high16 v3, 0x447a0000    # 1000.0f

    long-to-float v4, v7

    mul-float/2addr v4, v3

    long-to-float v3, v1

    div-float/2addr v4, v3

    float-to-long v3, v4

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const-string v7, "need upload size %s, speed %s, predicted time %s"

    invoke-static {v0, v7, v5, v1, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    iget-wide v7, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    sub-long/2addr v1, v7

    add-long/2addr v3, v1

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getBackgroundUpperLimitTime()J

    move-result-wide v7

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const-string v5, "predicted upper time %s, limit time %s"

    invoke-static {v0, v5, v1, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getBackgroundUpperLimitTime()J

    move-result-wide v1

    cmp-long v1, v3, v1

    if-gez v1, :cond_8

    const-string v1, "monitor background in upper limit time"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_8
    const-string v1, "background sync time has reached upper limit, change sync policy"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/gallery/util/SyncUtil;->stopSync(Landroid/content/Context;)V

    new-instance v0, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;-><init>()V

    sget-object v1, Lcom/miui/gallery/cloud/base/SyncType;->BACKGROUND:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    const-wide v1, 0x7fffffffffffffffL

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncReason(J)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    invoke-virtual {v0, v6}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setDelayUpload(Z)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->build()Lcom/miui/gallery/cloud/base/SyncRequest;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncUtil;->requestSync(Landroid/content/Context;Lcom/miui/gallery/cloud/base/SyncRequest;)V

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->statPolicyChanged()V

    goto :goto_1

    :cond_9
    :goto_0
    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "monitor background in buffer time %s"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_a
    iget-wide v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    cmp-long v1, v1, v4

    if-lez v1, :cond_b

    const-string v1, "monitor charging, reset background start time"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iput-wide v4, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    :cond_b
    :goto_1
    return-void
.end method

.method private getAnalyzer()Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mUploadInfoAnalyzer:Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mUploadInfoAnalyzer:Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mUploadInfoAnalyzer:Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    return-object v0
.end method

.method private static getBackgroundBufferTime()J
    .locals 2

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->getMonitorBackBufferTime()J

    move-result-wide v0

    return-wide v0
.end method

.method private static getBackgroundUpperLimitTime()J
    .locals 2

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->getMonitorBackUpperTime()J

    move-result-wide v0

    return-wide v0
.end method

.method private static getCheckInterval()J
    .locals 2

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->getMonitorCheckInterval()J

    move-result-wide v0

    return-wide v0
.end method

.method private getHandler()Landroid/os/Handler;
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->LOCK:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mHandler:Landroid/os/Handler;

    if-nez v1, :cond_0

    new-instance v1, Landroid/os/HandlerThread;

    const-string v2, "sync_monitor"

    const/16 v3, 0xa

    invoke-direct {v1, v2, v3}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;I)V

    invoke-virtual {v1}, Landroid/os/HandlerThread;->start()V

    new-instance v2, Landroid/os/Handler;

    invoke-virtual {v1}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v2, v1, p0}, Landroid/os/Handler;-><init>(Landroid/os/Looper;Landroid/os/Handler$Callback;)V

    iput-object v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mHandler:Landroid/os/Handler;

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mHandler:Landroid/os/Handler;

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method private static getSyncUpperLimitTime()J
    .locals 2

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->getMonitorSyncUpperTime()J

    move-result-wide v0

    return-wide v0
.end method

.method private static getTrafficStepValue()J
    .locals 2

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->getMonitorTrafficStep()J

    move-result-wide v0

    return-wide v0
.end method

.method private handleAppBackground()V
    .locals 0

    return-void
.end method

.method private handleAppForeground()V
    .locals 12

    invoke-static {}, Lcom/miui/gallery/cloud/AccountCache;->getAccount()Landroid/accounts/Account;

    move-result-object v0

    if-eqz v0, :cond_a

    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v1}, Lcom/miui/gallery/util/SyncUtil;->isGalleryCloudSyncable(Landroid/content/Context;)Z

    move-result v1

    if-eqz v1, :cond_a

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateUtil;->isSyncActive()Z

    move-result v1

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateUtil;->isUploading()Z

    move-result v2

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateUtil;->isSyncPending()Z

    move-result v3

    iget-object v4, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v4}, Lcom/miui/gallery/cloud/syncstate/SyncStateUtil;->getDirtyCount(Landroid/content/Context;)Lcom/miui/gallery/cloud/syncstate/DirtyCount;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/cloud/syncstate/DirtyCount;->getSyncableCount()I

    move-result v4

    const-wide v5, 0x7fffffffffffffffL

    const/4 v7, 0x0

    const-wide/16 v8, 0x0

    const-string v10, "SyncMonitor"

    if-lez v4, :cond_5

    if-nez v1, :cond_2

    if-eqz v2, :cond_0

    goto :goto_0

    :cond_0
    if-eqz v3, :cond_1

    const-string v1, "com.miui.gallery.cloud.provider"

    invoke-static {v0, v1}, Landroid/content/ContentResolver;->cancelSync(Landroid/accounts/Account;Ljava/lang/String;)V

    const-string v0, "sync pending: cancel existing, request again."

    invoke-static {v10, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto/16 :goto_3

    :cond_1
    const-string v0, "no request: request again."

    invoke-static {v10, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto/16 :goto_3

    :cond_2
    :goto_0
    const-wide/16 v0, 0x21

    if-eqz v2, :cond_3

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getSyncType()Lcom/miui/gallery/cloud/base/SyncType;

    move-result-object v2

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getSyncReason()J

    move-result-wide v4

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v6

    sget-object v11, Lcom/miui/gallery/cloud/base/SyncType;->NORMAL:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {v6, v11, v7}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;Z)V

    invoke-static {v4, v5}, Ljava/lang/Long;->toBinaryString(J)Ljava/lang/String;

    move-result-object v4

    const-string v5, "uploading: swap sync type, cur type[%s], reason[%s]"

    invoke-static {v10, v5, v2, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    if-eqz v3, :cond_9

    const-string v2, "uploading, has pending sync, need request"

    invoke-static {v10, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    :goto_1
    move-wide v5, v0

    goto/16 :goto_3

    :cond_3
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v2

    const-wide/16 v3, 0x20

    invoke-virtual {v2, v3, v4}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->containsReason(J)Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getSyncType()Lcom/miui/gallery/cloud/base/SyncType;

    move-result-object v0

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getSyncReason()J

    move-result-wide v1

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v3

    sget-object v4, Lcom/miui/gallery/cloud/base/SyncType;->NORMAL:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {v3, v4, v7}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;Z)V

    invoke-static {v1, v2}, Ljava/lang/Long;->toBinaryString(J)Ljava/lang/String;

    move-result-object v1

    const-string v2, "sync active: swap sync type, cur type[%s], reason[%s]"

    invoke-static {v10, v2, v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_2

    :cond_4
    const-string v2, "sync active, won\'t upload, need request"

    invoke-static {v10, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    :cond_5
    if-nez v1, :cond_9

    if-nez v3, :cond_9

    invoke-static {}, Lcom/miui/gallery/util/deprecated/Preference;->sIsFirstSynced()Z

    move-result v0

    if-nez v0, :cond_6

    const-string v0, "Hasn\'t synced first, need request"

    invoke-static {v10, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_3

    :cond_6
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Sync;->getLastSyncTimestamp()J

    move-result-wide v0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sub-long/2addr v2, v0

    cmp-long v4, v2, v8

    if-ltz v4, :cond_7

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->getIntervalForActivePull()J

    move-result-wide v4

    cmp-long v2, v2, v4

    if-lez v2, :cond_9

    :cond_7
    const-string v2, "pull actively"

    invoke-static {v10, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const-wide/16 v5, 0x1

    cmp-long v2, v0, v8

    if-lez v2, :cond_8

    new-instance v2, Ljava/util/Date;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-direct {v2, v3, v4}, Ljava/util/Date;-><init>(J)V

    new-instance v3, Ljava/util/Date;

    invoke-direct {v3, v0, v1}, Ljava/util/Date;-><init>(J)V

    invoke-static {v2, v3}, Lcom/miui/gallery/util/GalleryDateUtils;->isSameDate(Ljava/util/Date;Ljava/util/Date;)Z

    move-result v0

    if-nez v0, :cond_8

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Sync;->getActivePullTimes()I

    move-result v0

    if-lez v0, :cond_8

    invoke-direct {p0, v0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->statActivePullTimes(I)V

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Sync;->clearActivePullTimes()V

    :cond_8
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Sync;->increaseActivePullTimes()V

    goto :goto_3

    :cond_9
    :goto_2
    move-wide v5, v8

    :goto_3
    cmp-long v0, v5, v8

    if-eqz v0, :cond_a

    new-instance v0, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;-><init>()V

    sget-object v1, Lcom/miui/gallery/cloud/base/SyncType;->NORMAL:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    invoke-virtual {v0, v5, v6}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncReason(J)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    invoke-virtual {v0, v7}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setDelayUpload(Z)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->build()Lcom/miui/gallery/cloud/base/SyncRequest;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncUtil;->requestSync(Landroid/content/Context;Lcom/miui/gallery/cloud/base/SyncRequest;)V

    invoke-static {v5, v6}, Ljava/lang/Long;->toBinaryString(J)Ljava/lang/String;

    move-result-object v0

    const-string v1, "app focused, request sync with reason[%s]"

    invoke-static {v10, v1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_a
    return-void
.end method

.method private handleNetworkingAccepted()V
    .locals 3

    invoke-static {}, Lcom/miui/gallery/util/deprecated/Preference;->sIsFirstSynced()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/gallery/util/SyncUtil;->isGalleryCloudSyncable(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    const-string v0, "SyncMonitor"

    const-string v1, "request sync after networking accepted"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;-><init>()V

    sget-object v1, Lcom/miui/gallery/cloud/base/SyncType;->POWER_FORCE:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    const-wide v1, 0x7fffffffffffffffL

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncReason(J)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->build()Lcom/miui/gallery/cloud/base/SyncRequest;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncUtil;->requestSync(Landroid/content/Context;Lcom/miui/gallery/cloud/base/SyncRequest;)V

    :cond_0
    return-void
.end method

.method private handleNetworkingDenied()V
    .locals 0

    return-void
.end method

.method private handleSyncStart(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;)V
    .locals 3

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mSyncThrowable:Ljava/lang/Throwable;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mSyncStartTime:J

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->statSyncStart()V

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v0

    iget-object v1, p1, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;->type:Lcom/miui/gallery/cloud/base/SyncType;

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;Z)V

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v0

    iget-wide v1, p1, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;->reason:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->mergeReason(J)V

    return-void
.end method

.method private handleSyncStartAppend(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;)V
    .locals 3

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v0

    iget-object v1, p1, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;->type:Lcom/miui/gallery/cloud/base/SyncType;

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;Z)V

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v0

    iget-wide v1, p1, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;->reason:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->mergeReason(J)V

    return-void
.end method

.method private handleSyncStop()V
    .locals 4

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mSyncStartTime:J

    sub-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "SyncMonitor"

    const-string v2, "sync time %s"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mLastCheckTime:J

    iput-wide v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mSyncStartTime:J

    sget v2, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->TRAFFIC_STEP_INIT:I

    iput v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mTrafficSteps:I

    iput-wide v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mBackgroundStartTime:J

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->isFirstBackground:Z

    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mUploadInfoAnalyzer:Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    if-eqz v1, :cond_0

    invoke-static {v1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;->access$400(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;)V

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v1

    sget-object v2, Lcom/miui/gallery/cloud/base/SyncType;->UNKNOW:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {v1, v2, v0}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;Z)V

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->getInstance()Lcom/miui/gallery/cloud/syncstate/SyncStateManager;

    move-result-object v0

    const-wide v1, 0x7fffffffffffffffL

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/cloud/syncstate/SyncStateManager;->unmergeReason(J)V

    return-void
.end method

.method private handleSyncThrowable(Ljava/lang/Throwable;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mSyncThrowable:Ljava/lang/Throwable;

    return-void
.end method

.method private handleUploadInfo(Lcom/miui/gallery/cloud/control/UploadInfo;)V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getAnalyzer()Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;->addUploadInfo(Lcom/miui/gallery/cloud/control/UploadInfo;)V

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->tryCheck()V

    return-void
.end method

.method private static isCharging(Landroid/content/Context;)Z
    .locals 4

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    new-instance v2, Landroid/content/IntentFilter;

    const-string v3, "android.intent.action.BATTERY_CHANGED"

    invoke-direct {v2, v3}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    const/4 v3, 0x0

    invoke-virtual {p0, v3, v2}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    move-result-object p0

    const-string v2, "status"

    const/4 v3, -0x1

    invoke-virtual {p0, v2, v3}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result p0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sub-long/2addr v2, v0

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "SyncMonitor"

    const-string v2, "judge charging state cost %s"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const/4 v0, 0x2

    if-eq p0, v0, :cond_1

    const/4 v0, 0x5

    if-ne p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p0, 0x1

    :goto_1
    return p0
.end method

.method private static messageDesc(I)Ljava/lang/String;
    .locals 0

    packed-switch p0, :pswitch_data_0

    const-string p0, "UNKNOWN"

    return-object p0

    :pswitch_0
    const-string p0, "MSG_ON_SYNC_START_APPEND"

    return-object p0

    :pswitch_1
    const-string p0, "MSG_ON_HANDLE_REASON"

    return-object p0

    :pswitch_2
    const-string p0, "RECORD_NETWORKING_DENIED"

    return-object p0

    :pswitch_3
    const-string p0, "RECORD_NETWORKING_ACCEPTED"

    return-object p0

    :pswitch_4
    const-string p0, "RECORD_APP_BACKGROUND"

    return-object p0

    :pswitch_5
    const-string p0, "RECORD_APP_FOREGROUND"

    return-object p0

    :pswitch_6
    const-string p0, "RECORD_SYNC_STOP"

    return-object p0

    :pswitch_7
    const-string p0, "RECORD_THROWABLE"

    return-object p0

    :pswitch_8
    const-string p0, "RECORD_UPLOAD_INFO"

    return-object p0

    :pswitch_9
    const-string p0, "RECORD_SYNC_START"

    return-object p0

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private static needMonitorBackground()Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->isMonitorBackground()Z

    move-result v0

    return v0
.end method

.method private static needMonitorSyncTime()Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->isMonitorSyncTime()Z

    move-result v0

    return v0
.end method

.method private static needMonitorTraffic()Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getSyncStrategy()Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/strategies/SyncStrategy;->isMonitorTraffic()Z

    move-result v0

    return v0
.end method

.method private statActivePullTimes(I)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    const-string v1, "times"

    invoke-virtual {v0, v1, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "sync_monitor"

    const-string v1, "sync_active_pull_times"

    invoke-static {p1, v1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private statPolicyChanged()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/gallery/cloud/syncstate/SyncStateUtil;->getDirtySize(Landroid/content/Context;)[J

    move-result-object v0

    const/4 v1, 0x0

    aget-wide v1, v0, v1

    const/4 v3, 0x1

    aget-wide v3, v0, v3

    add-long/2addr v1, v3

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-static {v1, v2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v1

    const-string v2, "dirty"

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "sync_monitor"

    const-string v2, "monitor_sync_policy_changed_dirty"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private statSyncInBackground()V
    .locals 8

    const-string v0, "sync_monitor"

    const-string v1, "monitor_sync_in_background"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mContext:Landroid/content/Context;

    invoke-static {v1}, Lcom/miui/gallery/cloud/syncstate/SyncStateUtil;->getDirtySize(Landroid/content/Context;)[J

    move-result-object v1

    const/4 v2, 0x0

    aget-wide v2, v1, v2

    const/4 v4, 0x1

    aget-wide v4, v1, v4

    add-long/2addr v2, v4

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getAnalyzer()Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;->getAvgSpeed()J

    move-result-wide v4

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getAnalyzer()Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$UploadInfoAnalyzer;->getRecentSpeed()J

    move-result-wide v6

    invoke-static {v4, v5, v6, v7}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v4

    const-wide/16 v6, 0x0

    cmp-long v1, v4, v6

    if-lez v1, :cond_0

    const/high16 v1, 0x3f800000    # 1.0f

    long-to-float v2, v2

    mul-float/2addr v2, v1

    long-to-float v1, v4

    div-float/2addr v2, v1

    float-to-long v1, v2

    new-instance v3, Ljava/util/HashMap;

    invoke-direct {v3}, Ljava/util/HashMap;-><init>()V

    invoke-static {v1, v2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v1

    const-string v2, "predictedTime"

    invoke-virtual {v3, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "monitor_background_predicted_time"

    invoke-static {v0, v1, v3}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    :cond_0
    return-void
.end method

.method private statSyncStart()V
    .locals 2

    const-string v0, "sync_monitor"

    const-string v1, "monitor_sync_start"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private tryCheck()V
    .locals 4

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mLastCheckTime:J

    sub-long/2addr v0, v2

    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getCheckInterval()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-gez v0, :cond_0

    return-void

    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->mLastCheckTime:J

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->check()V

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)Z
    .locals 1

    iget v0, p1, Landroid/os/Message;->what:I

    packed-switch v0, :pswitch_data_0

    :pswitch_0
    goto :goto_0

    :pswitch_1
    iget-object v0, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;

    invoke-direct {p0, v0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleSyncStartAppend(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;)V

    goto :goto_0

    :pswitch_2
    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleNetworkingDenied()V

    goto :goto_0

    :pswitch_3
    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleNetworkingAccepted()V

    goto :goto_0

    :pswitch_4
    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleAppBackground()V

    goto :goto_0

    :pswitch_5
    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleAppForeground()V

    goto :goto_0

    :pswitch_6
    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleSyncStop()V

    goto :goto_0

    :pswitch_7
    iget-object v0, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v0, Ljava/lang/Throwable;

    invoke-direct {p0, v0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleSyncThrowable(Ljava/lang/Throwable;)V

    goto :goto_0

    :pswitch_8
    iget-object v0, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v0, Lcom/miui/gallery/cloud/control/UploadInfo;

    invoke-direct {p0, v0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleUploadInfo(Lcom/miui/gallery/cloud/control/UploadInfo;)V

    goto :goto_0

    :pswitch_9
    iget-object v0, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;

    invoke-direct {p0, v0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->handleSyncStart(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;)V

    :goto_0
    iget p1, p1, Landroid/os/Message;->what:I

    invoke-static {p1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->messageDesc(I)Ljava/lang/String;

    move-result-object p1

    const-string v0, "SyncMonitor"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 p1, 0x1

    return p1

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public onHandleReason(J)V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object v0

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const/16 p2, 0x9

    invoke-virtual {v0, p2, p1}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    return-void
.end method

.method public onNetworkingAcceptedToggled(Z)V
    .locals 1

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object p1

    const/4 v0, 0x7

    invoke-virtual {p1, v0}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object p1

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    :goto_0
    return-void
.end method

.method public onProcessLifecycleToggled(Z)V
    .locals 1

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object p1

    const/4 v0, 0x5

    invoke-virtual {p1, v0}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object p1

    const/4 v0, 0x6

    invoke-virtual {p1, v0}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    :goto_0
    return-void
.end method

.method public onSyncEnd()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    return-void
.end method

.method public onSyncStart(Lcom/miui/gallery/cloud/base/SyncType;J)V
    .locals 8

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v7, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;

    const/4 v6, 0x0

    move-object v1, v7

    move-object v2, p0

    move-object v3, p1

    move-wide v4, p2

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;-><init>(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;Lcom/miui/gallery/cloud/base/SyncType;JLcom/miui/gallery/cloud/syncstate/SyncMonitor$1;)V

    const/4 p1, 0x1

    invoke-virtual {v0, p1, v7}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    return-void
.end method

.method public onSyncStartAppend(Lcom/miui/gallery/cloud/base/SyncType;J)V
    .locals 8

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v7, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;

    const/4 v6, 0x0

    move-object v1, v7

    move-object v2, p0

    move-object v3, p1

    move-wide v4, p2

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;-><init>(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;Lcom/miui/gallery/cloud/base/SyncType;JLcom/miui/gallery/cloud/syncstate/SyncMonitor$1;)V

    const/16 p1, 0xa

    invoke-virtual {v0, p1, v7}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    return-void
.end method

.method public onSyncThrowable(Ljava/lang/Throwable;)V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x3

    invoke-virtual {v0, v1, p1}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    return-void
.end method

.method public onUpload(Lcom/miui/gallery/cloud/control/UploadInfo;)V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;->getHandler()Landroid/os/Handler;

    move-result-object v0

    const/4 v1, 0x2

    invoke-virtual {v0, v1, p1}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object p1

    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    return-void
.end method
