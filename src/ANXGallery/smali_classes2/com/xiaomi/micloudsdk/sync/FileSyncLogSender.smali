.class Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;
.super Lcom/xiaomi/micloudsdk/sync/SyncLogSender;
.source "FileSyncLogSender.java"


# instance fields
.field private final mAuthority:Ljava/lang/String;

.field private mRemoteLogger:Lmiui/cloud/sync/IRemoteFileSyncLogger;

.field private final mSyncProvider:Landroid/content/ContentProviderClient;


# direct methods
.method public constructor <init>(Landroid/content/ContentProviderClient;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Lcom/xiaomi/micloudsdk/sync/SyncLogSender;-><init>()V

    if-eqz p1, :cond_0

    iput-object p1, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mSyncProvider:Landroid/content/ContentProviderClient;

    iput-object p2, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mAuthority:Ljava/lang/String;

    return-void

    :cond_0
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "Must give a valid provider client."

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method


# virtual methods
.method openSyncLog()V
    .locals 5

    const-string v0, "SyncLogSender"

    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    iget-object v2, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mAuthority:Ljava/lang/String;

    const-string v3, "sync_log_key_authority"

    invoke-virtual {v1, v3, v2}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v2, 0x0

    :try_start_0
    iget-object v3, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mSyncProvider:Landroid/content/ContentProviderClient;

    const-string v4, "OPEN_SYNC_LOG"

    invoke-virtual {v3, v4, v2, v1}, Landroid/content/ContentProviderClient;->call(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object v1

    const-string v3, "sync_log_key_remote_logger"

    invoke-virtual {v1, v3}, Landroid/os/Bundle;->getBinder(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v2
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const-string v1, "open sync log: server is died."

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :goto_0
    if-nez v2, :cond_0

    const-string v1, "open sync log: binder is null."

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v0, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mSyncProvider:Landroid/content/ContentProviderClient;

    invoke-virtual {v0}, Landroid/content/ContentProviderClient;->release()Z

    goto :goto_1

    :cond_0
    invoke-static {v2}, Lmiui/cloud/sync/IRemoteFileSyncLogger$Stub;->asInterface(Landroid/os/IBinder;)Lmiui/cloud/sync/IRemoteFileSyncLogger;

    move-result-object v0

    iput-object v0, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mRemoteLogger:Lmiui/cloud/sync/IRemoteFileSyncLogger;

    :goto_1
    return-void
.end method

.method release()V
    .locals 6

    const-string v0, "SyncLogSender"

    iget-object v1, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mRemoteLogger:Lmiui/cloud/sync/IRemoteFileSyncLogger;

    if-nez v1, :cond_0

    return-void

    :cond_0
    new-instance v1, Ljava/util/concurrent/CountDownLatch;

    const/4 v2, 0x1

    invoke-direct {v1, v2}, Ljava/util/concurrent/CountDownLatch;-><init>(I)V

    const/4 v2, 0x0

    :try_start_0
    iget-object v3, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mRemoteLogger:Lmiui/cloud/sync/IRemoteFileSyncLogger;

    new-instance v4, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender$1;

    invoke-direct {v4, p0, v2, v1}, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender$1;-><init>(Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;Landroid/os/Handler;Ljava/util/concurrent/CountDownLatch;)V

    invoke-interface {v3, v4}, Lmiui/cloud/sync/IRemoteFileSyncLogger;->release(Landroid/os/ResultReceiver;)V

    const-wide/16 v3, 0x2710

    sget-object v5, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v1, v3, v4, v5}, Ljava/util/concurrent/CountDownLatch;->await(JLjava/util/concurrent/TimeUnit;)Z

    move-result v1

    if-nez v1, :cond_1

    const-string v1, "Too long to wait for releasing provider. Release now."

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const-string v1, "Release is interrupted by cancel sync."

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Thread;->interrupt()V

    goto :goto_0

    :catch_1
    const-string v1, "Remote sync provider is died! Release now."

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mSyncProvider:Landroid/content/ContentProviderClient;

    invoke-virtual {v0}, Landroid/content/ContentProviderClient;->release()Z

    iput-object v2, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mRemoteLogger:Lmiui/cloud/sync/IRemoteFileSyncLogger;

    return-void
.end method

.method sendLog(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    iget-object v0, p0, Lcom/xiaomi/micloudsdk/sync/FileSyncLogSender;->mRemoteLogger:Lmiui/cloud/sync/IRemoteFileSyncLogger;

    if-nez v0, :cond_0

    invoke-static {p1, p2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :cond_0
    :try_start_0
    invoke-interface {v0, p1, p2}, Lmiui/cloud/sync/IRemoteFileSyncLogger;->syncLog(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const-string v0, "SyncLogSender"

    const-string v1, "send log: server is died."

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static {p1, p2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    :goto_0
    return-void
.end method
