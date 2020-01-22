.class Lcom/xiaomi/micloudsdk/sync/LogcatSyncLogSender;
.super Lcom/xiaomi/micloudsdk/sync/SyncLogSender;
.source "LogcatSyncLogSender.java"


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/xiaomi/micloudsdk/sync/SyncLogSender;-><init>()V

    return-void
.end method


# virtual methods
.method openSyncLog()V
    .locals 0

    return-void
.end method

.method release()V
    .locals 0

    return-void
.end method

.method sendLog(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-static {p1, p2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method
