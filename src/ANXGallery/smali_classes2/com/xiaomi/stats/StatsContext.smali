.class public Lcom/xiaomi/stats/StatsContext;
.super Ljava/lang/Object;
.source "StatsContext.java"

# interfaces
.implements Lcom/xiaomi/smack/ConnectionListener;


# instance fields
.field private accumulatedChannelDuration:J

.field private accumulatedNetworkDuration:J

.field private channelConnectedTime:J

.field connection:Lcom/xiaomi/smack/Connection;

.field private connectionPoint:Ljava/lang/String;

.field private exception:Ljava/lang/Exception;

.field private mRxBytes:J

.field private mTxBytes:J

.field private networkConnectedTime:J

.field pushService:Lcom/xiaomi/push/service/XMPushService;

.field private reason:I


# direct methods
.method constructor <init>(Lcom/xiaomi/push/service/XMPushService;)V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->mTxBytes:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->mRxBytes:J

    iput-object p1, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    const-string p1, ""

    iput-object p1, p0, Lcom/xiaomi/stats/StatsContext;->connectionPoint:Ljava/lang/String;

    invoke-direct {p0}, Lcom/xiaomi/stats/StatsContext;->resetChannelStats()V

    invoke-static {}, Landroid/os/Process;->myUid()I

    move-result p1

    invoke-static {p1}, Landroid/net/TrafficStats;->getUidRxBytes(I)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->mRxBytes:J

    invoke-static {p1}, Landroid/net/TrafficStats;->getUidTxBytes(I)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->mTxBytes:J

    return-void
.end method

.method private resetChannelStats()V
    .locals 3

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iget-object v2, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v2}, Lcom/xiaomi/channel/commonutils/network/Network;->hasNetwork(Landroid/content/Context;)Z

    move-result v2

    if-eqz v2, :cond_0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    :cond_0
    iget-object v2, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-virtual {v2}, Lcom/xiaomi/push/service/XMPushService;->isConnected()Z

    move-result v2

    if-eqz v2, :cond_1

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    :cond_1
    return-void
.end method

.method private declared-synchronized statsChannelDuration()V
    .locals 5

    monitor-enter p0

    :try_start_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "stat connpt = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/xiaomi/stats/StatsContext;->connectionPoint:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " netDuration = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, " ChannelDuration = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, " channelConnectedTime = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    new-instance v0, Lcom/xiaomi/push/thrift/StatsEvent;

    invoke-direct {v0}, Lcom/xiaomi/push/thrift/StatsEvent;-><init>()V

    const/4 v1, 0x0

    iput-byte v1, v0, Lcom/xiaomi/push/thrift/StatsEvent;->chid:B

    sget-object v1, Lcom/xiaomi/push/thrift/ChannelStatsType;->CHANNEL_ONLINE_RATE:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v1}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setType(I)Lcom/xiaomi/push/thrift/StatsEvent;

    iget-object v1, p0, Lcom/xiaomi/stats/StatsContext;->connectionPoint:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setConnpt(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvent;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    const-wide/16 v3, 0x3e8

    div-long/2addr v1, v3

    long-to-int v1, v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setTime(I)Lcom/xiaomi/push/thrift/StatsEvent;

    iget-wide v1, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    div-long/2addr v1, v3

    long-to-int v1, v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setValue(I)Lcom/xiaomi/push/thrift/StatsEvent;

    iget-wide v1, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    div-long/2addr v1, v3

    long-to-int v1, v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setSubvalue(I)Lcom/xiaomi/push/thrift/StatsEvent;

    invoke-static {}, Lcom/xiaomi/stats/StatsHandler;->getInstance()Lcom/xiaomi/stats/StatsHandler;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/xiaomi/stats/StatsHandler;->add(Lcom/xiaomi/push/thrift/StatsEvent;)V

    invoke-direct {p0}, Lcom/xiaomi/stats/StatsContext;->resetChannelStats()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method


# virtual methods
.method public connectionClosed(Lcom/xiaomi/smack/Connection;ILjava/lang/Exception;)V
    .locals 4

    iget v0, p0, Lcom/xiaomi/stats/StatsContext;->reason:I

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/xiaomi/stats/StatsContext;->exception:Ljava/lang/Exception;

    if-nez v0, :cond_0

    iput p2, p0, Lcom/xiaomi/stats/StatsContext;->reason:I

    iput-object p3, p0, Lcom/xiaomi/stats/StatsContext;->exception:Ljava/lang/Exception;

    invoke-virtual {p1}, Lcom/xiaomi/smack/Connection;->getHost()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, p3}, Lcom/xiaomi/stats/StatsHelper;->connectionDown(Ljava/lang/String;Ljava/lang/Exception;)V

    :cond_0
    const/16 p3, 0x16

    if-ne p2, p3, :cond_2

    iget-wide p2, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    const-wide/16 v0, 0x0

    cmp-long p2, p2, v0

    if-eqz p2, :cond_2

    invoke-virtual {p1}, Lcom/xiaomi/smack/Connection;->getLastPingRecv()J

    move-result-wide p1

    iget-wide v2, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    sub-long/2addr p1, v2

    cmp-long p3, p1, v0

    if-gez p3, :cond_1

    move-wide p1, v0

    :cond_1
    invoke-static {}, Lcom/xiaomi/smack/SmackConfiguration;->getPingInteval()I

    move-result p3

    div-int/lit8 p3, p3, 0x2

    int-to-long v2, p3

    add-long/2addr p1, v2

    iget-wide v2, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    add-long/2addr v2, p1

    iput-wide v2, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    :cond_2
    invoke-virtual {p0}, Lcom/xiaomi/stats/StatsContext;->statsChannelIfNeed()V

    invoke-static {}, Landroid/os/Process;->myUid()I

    move-result p1

    invoke-static {p1}, Landroid/net/TrafficStats;->getUidRxBytes(I)J

    move-result-wide p2

    invoke-static {p1}, Landroid/net/TrafficStats;->getUidTxBytes(I)J

    move-result-wide v0

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Stats rx="

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lcom/xiaomi/stats/StatsContext;->mRxBytes:J

    sub-long v2, p2, v2

    invoke-virtual {p1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", tx="

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lcom/xiaomi/stats/StatsContext;->mTxBytes:J

    sub-long v2, v0, v2

    invoke-virtual {p1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    iput-wide p2, p0, Lcom/xiaomi/stats/StatsContext;->mRxBytes:J

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->mTxBytes:J

    return-void
.end method

.method public connectionStarted(Lcom/xiaomi/smack/Connection;)V
    .locals 2

    const/4 v0, 0x0

    iput v0, p0, Lcom/xiaomi/stats/StatsContext;->reason:I

    const/4 v1, 0x0

    iput-object v1, p0, Lcom/xiaomi/stats/StatsContext;->exception:Ljava/lang/Exception;

    iput-object p1, p0, Lcom/xiaomi/stats/StatsContext;->connection:Lcom/xiaomi/smack/Connection;

    iget-object p1, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {p1}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/xiaomi/stats/StatsContext;->connectionPoint:Ljava/lang/String;

    sget-object p1, Lcom/xiaomi/push/thrift/ChannelStatsType;->CONN_SUCCESS:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {p1}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result p1

    invoke-static {v0, p1}, Lcom/xiaomi/stats/StatsHelper;->trackStart(II)V

    return-void
.end method

.method getCaughtException()Ljava/lang/Exception;
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/stats/StatsContext;->exception:Ljava/lang/Exception;

    return-object v0
.end method

.method public reconnectionFailed(Lcom/xiaomi/smack/Connection;Ljava/lang/Exception;)V
    .locals 3

    iget-object p2, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {p2}, Lcom/xiaomi/channel/commonutils/network/Network;->hasNetwork(Landroid/content/Context;)Z

    move-result p2

    const/4 v0, 0x0

    sget-object v1, Lcom/xiaomi/push/thrift/ChannelStatsType;->CHANNEL_CON_FAIL:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v1}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v1

    const/4 v2, 0x1

    invoke-virtual {p1}, Lcom/xiaomi/smack/Connection;->getHost()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, v1, v2, p1, p2}, Lcom/xiaomi/stats/StatsHelper;->stats(IIILjava/lang/String;I)V

    invoke-virtual {p0}, Lcom/xiaomi/stats/StatsContext;->statsChannelIfNeed()V

    return-void
.end method

.method public reconnectionSuccessful(Lcom/xiaomi/smack/Connection;)V
    .locals 3

    invoke-virtual {p0}, Lcom/xiaomi/stats/StatsContext;->statsChannelIfNeed()V

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    sget-object v0, Lcom/xiaomi/push/thrift/ChannelStatsType;->CONN_SUCCESS:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v0}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v0

    invoke-virtual {p1}, Lcom/xiaomi/smack/Connection;->getHost()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1}, Lcom/xiaomi/smack/Connection;->getConnTryTimes()I

    move-result p1

    const/4 v2, 0x0

    invoke-static {v2, v0, v1, p1}, Lcom/xiaomi/stats/StatsHelper;->trackEnd(IILjava/lang/String;I)V

    return-void
.end method

.method public declared-synchronized statsChannelIfNeed()V
    .locals 10

    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v0, :cond_0

    monitor-exit p0

    return-void

    :cond_0
    :try_start_1
    iget-object v0, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v1}, Lcom/xiaomi/channel/commonutils/network/Network;->hasNetwork(Landroid/content/Context;)Z

    move-result v1

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v2

    iget-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    const-wide/16 v6, 0x0

    cmp-long v4, v4, v6

    if-lez v4, :cond_1

    iget-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    iget-wide v8, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    sub-long v8, v2, v8

    add-long/2addr v4, v8

    iput-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    iput-wide v6, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    :cond_1
    iget-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    cmp-long v4, v4, v6

    if-eqz v4, :cond_2

    iget-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    iget-wide v8, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    sub-long v8, v2, v8

    add-long/2addr v4, v8

    iput-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedChannelDuration:J

    iput-wide v6, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J

    :cond_2
    if-eqz v1, :cond_7

    iget-object v1, p0, Lcom/xiaomi/stats/StatsContext;->connectionPoint:Ljava/lang/String;

    invoke-static {v1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_3

    iget-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    const-wide/16 v8, 0x7530

    cmp-long v1, v4, v8

    if-gtz v1, :cond_4

    :cond_3
    iget-wide v4, p0, Lcom/xiaomi/stats/StatsContext;->accumulatedNetworkDuration:J

    const-wide/32 v8, 0x5265c0

    cmp-long v1, v4, v8

    if-lez v1, :cond_5

    :cond_4
    invoke-direct {p0}, Lcom/xiaomi/stats/StatsContext;->statsChannelDuration()V

    :cond_5
    iput-object v0, p0, Lcom/xiaomi/stats/StatsContext;->connectionPoint:Ljava/lang/String;

    iget-wide v0, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    cmp-long v0, v0, v6

    if-nez v0, :cond_6

    iput-wide v2, p0, Lcom/xiaomi/stats/StatsContext;->networkConnectedTime:J

    :cond_6
    iget-object v0, p0, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-virtual {v0}, Lcom/xiaomi/push/service/XMPushService;->isConnected()Z

    move-result v0

    if-eqz v0, :cond_7

    iput-wide v2, p0, Lcom/xiaomi/stats/StatsContext;->channelConnectedTime:J
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :cond_7
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method
