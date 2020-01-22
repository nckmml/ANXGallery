.class public Lcom/xiaomi/stats/StatsHandler;
.super Ljava/lang/Object;
.source "StatsHandler.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/xiaomi/stats/StatsHandler$Holder;
    }
.end annotation


# instance fields
.field private allowStatsUpload:Z

.field private context:Lcom/xiaomi/stats/StatsContext;

.field private duration:I

.field private startTime:J

.field private statsContainer:Lcom/xiaomi/channel/commonutils/stats/Stats;

.field private uuid:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/xiaomi/stats/StatsHandler;->allowStatsUpload:Z

    invoke-static {}, Lcom/xiaomi/channel/commonutils/stats/Stats;->instance()Lcom/xiaomi/channel/commonutils/stats/Stats;

    move-result-object v0

    iput-object v0, p0, Lcom/xiaomi/stats/StatsHandler;->statsContainer:Lcom/xiaomi/channel/commonutils/stats/Stats;

    return-void
.end method

.method private from(Lcom/xiaomi/channel/commonutils/stats/Stats$Item;)Lcom/xiaomi/push/thrift/StatsEvent;
    .locals 2

    iget v0, p1, Lcom/xiaomi/channel/commonutils/stats/Stats$Item;->key:I

    if-nez v0, :cond_1

    iget-object v0, p1, Lcom/xiaomi/channel/commonutils/stats/Stats$Item;->obj:Ljava/lang/Object;

    instance-of v0, v0, Lcom/xiaomi/push/thrift/StatsEvent;

    if-eqz v0, :cond_0

    iget-object p1, p1, Lcom/xiaomi/channel/commonutils/stats/Stats$Item;->obj:Ljava/lang/Object;

    check-cast p1, Lcom/xiaomi/push/thrift/StatsEvent;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/xiaomi/stats/StatsHandler;->createStatsEvent()Lcom/xiaomi/push/thrift/StatsEvent;

    move-result-object v0

    sget-object v1, Lcom/xiaomi/push/thrift/ChannelStatsType;->CHANNEL_STATS_COUNTER:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v1}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setType(I)Lcom/xiaomi/push/thrift/StatsEvent;

    iget v1, p1, Lcom/xiaomi/channel/commonutils/stats/Stats$Item;->key:I

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setSubvalue(I)Lcom/xiaomi/push/thrift/StatsEvent;

    iget-object p1, p1, Lcom/xiaomi/channel/commonutils/stats/Stats$Item;->annotation:Ljava/lang/String;

    invoke-virtual {v0, p1}, Lcom/xiaomi/push/thrift/StatsEvent;->setAnnotation(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvent;

    move-object p1, v0

    :goto_0
    return-object p1
.end method

.method public static getContext()Lcom/xiaomi/stats/StatsContext;
    .locals 2

    sget-object v0, Lcom/xiaomi/stats/StatsHandler$Holder;->sStatsHandler:Lcom/xiaomi/stats/StatsHandler;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/xiaomi/stats/StatsHandler$Holder;->sStatsHandler:Lcom/xiaomi/stats/StatsHandler;

    iget-object v1, v1, Lcom/xiaomi/stats/StatsHandler;->context:Lcom/xiaomi/stats/StatsContext;

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public static getInstance()Lcom/xiaomi/stats/StatsHandler;
    .locals 1

    sget-object v0, Lcom/xiaomi/stats/StatsHandler$Holder;->sStatsHandler:Lcom/xiaomi/stats/StatsHandler;

    return-object v0
.end method

.method private retriveStatsEvents(I)Lcom/xiaomi/push/thrift/StatsEvents;
    .locals 7

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    new-instance v1, Lcom/xiaomi/push/thrift/StatsEvents;

    iget-object v2, p0, Lcom/xiaomi/stats/StatsHandler;->uuid:Ljava/lang/String;

    invoke-direct {v1, v2, v0}, Lcom/xiaomi/push/thrift/StatsEvents;-><init>(Ljava/lang/String;Ljava/util/List;)V

    iget-object v2, p0, Lcom/xiaomi/stats/StatsHandler;->context:Lcom/xiaomi/stats/StatsContext;

    iget-object v2, v2, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v2}, Lcom/xiaomi/channel/commonutils/network/Network;->isWIFIConnected(Landroid/content/Context;)Z

    move-result v2

    if-nez v2, :cond_0

    iget-object v2, p0, Lcom/xiaomi/stats/StatsHandler;->context:Lcom/xiaomi/stats/StatsContext;

    iget-object v2, v2, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v2}, Lcom/xiaomi/channel/commonutils/android/DeviceInfo;->getSimOperatorName(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/xiaomi/push/thrift/StatsEvents;->setOperator(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvents;

    :cond_0
    new-instance v2, Lorg/apache/thrift/transport/TMemoryBuffer;

    invoke-direct {v2, p1}, Lorg/apache/thrift/transport/TMemoryBuffer;-><init>(I)V

    new-instance v3, Lorg/apache/thrift/protocol/XmPushTBinaryProtocol$Factory;

    invoke-direct {v3}, Lorg/apache/thrift/protocol/XmPushTBinaryProtocol$Factory;-><init>()V

    invoke-virtual {v3, v2}, Lorg/apache/thrift/protocol/XmPushTBinaryProtocol$Factory;->getProtocol(Lorg/apache/thrift/transport/TTransport;)Lorg/apache/thrift/protocol/TProtocol;

    move-result-object v3

    :try_start_0
    invoke-virtual {v1, v3}, Lcom/xiaomi/push/thrift/StatsEvents;->write(Lorg/apache/thrift/protocol/TProtocol;)V
    :try_end_0
    .catch Lorg/apache/thrift/TException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    iget-object v4, p0, Lcom/xiaomi/stats/StatsHandler;->statsContainer:Lcom/xiaomi/channel/commonutils/stats/Stats;

    invoke-virtual {v4}, Lcom/xiaomi/channel/commonutils/stats/Stats;->getStats()Ljava/util/LinkedList;

    move-result-object v4

    :goto_0
    :try_start_1
    invoke-virtual {v4}, Ljava/util/LinkedList;->size()I

    move-result v5

    if-lez v5, :cond_4

    invoke-virtual {v4}, Ljava/util/LinkedList;->getLast()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/xiaomi/channel/commonutils/stats/Stats$Item;

    invoke-direct {p0, v5}, Lcom/xiaomi/stats/StatsHandler;->from(Lcom/xiaomi/channel/commonutils/stats/Stats$Item;)Lcom/xiaomi/push/thrift/StatsEvent;

    move-result-object v5

    if-eqz v5, :cond_1

    invoke-virtual {v5, v3}, Lcom/xiaomi/push/thrift/StatsEvent;->write(Lorg/apache/thrift/protocol/TProtocol;)V

    :cond_1
    invoke-virtual {v2}, Lorg/apache/thrift/transport/TMemoryBuffer;->length()I

    move-result v6

    if-le v6, p1, :cond_2

    goto :goto_1

    :cond_2
    if-eqz v5, :cond_3

    invoke-interface {v0, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_3
    invoke-virtual {v4}, Ljava/util/LinkedList;->removeLast()Ljava/lang/Object;
    :try_end_1
    .catch Ljava/util/NoSuchElementException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Lorg/apache/thrift/TException; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_0

    :catch_1
    :cond_4
    :goto_1
    return-object v1
.end method

.method private stopStatsIfNeed()V
    .locals 4

    iget-boolean v0, p0, Lcom/xiaomi/stats/StatsHandler;->allowStatsUpload:Z

    if-eqz v0, :cond_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/xiaomi/stats/StatsHandler;->startTime:J

    sub-long/2addr v0, v2

    iget v2, p0, Lcom/xiaomi/stats/StatsHandler;->duration:I

    int-to-long v2, v2

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/xiaomi/stats/StatsHandler;->allowStatsUpload:Z

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsHandler;->startTime:J

    :cond_0
    return-void
.end method


# virtual methods
.method declared-synchronized add(Lcom/xiaomi/push/thrift/StatsEvent;)V
    .locals 1

    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lcom/xiaomi/stats/StatsHandler;->statsContainer:Lcom/xiaomi/channel/commonutils/stats/Stats;

    invoke-virtual {v0, p1}, Lcom/xiaomi/channel/commonutils/stats/Stats;->stat(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit p0

    throw p1
.end method

.method declared-synchronized createStatsEvent()Lcom/xiaomi/push/thrift/StatsEvent;
    .locals 5

    monitor-enter p0

    :try_start_0
    new-instance v0, Lcom/xiaomi/push/thrift/StatsEvent;

    invoke-direct {v0}, Lcom/xiaomi/push/thrift/StatsEvent;-><init>()V

    iget-object v1, p0, Lcom/xiaomi/stats/StatsHandler;->context:Lcom/xiaomi/stats/StatsContext;

    iget-object v1, v1, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v1}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setConnpt(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvent;

    const/4 v1, 0x0

    iput-byte v1, v0, Lcom/xiaomi/push/thrift/StatsEvent;->chid:B

    const/4 v1, 0x1

    iput v1, v0, Lcom/xiaomi/push/thrift/StatsEvent;->value:I

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    const-wide/16 v3, 0x3e8

    div-long/2addr v1, v3

    long-to-int v1, v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setTime(I)Lcom/xiaomi/push/thrift/StatsEvent;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public declared-synchronized init(Lcom/xiaomi/push/service/XMPushService;)V
    .locals 1

    monitor-enter p0

    :try_start_0
    new-instance v0, Lcom/xiaomi/stats/StatsContext;

    invoke-direct {v0, p1}, Lcom/xiaomi/stats/StatsContext;-><init>(Lcom/xiaomi/push/service/XMPushService;)V

    iput-object v0, p0, Lcom/xiaomi/stats/StatsHandler;->context:Lcom/xiaomi/stats/StatsContext;

    const-string p1, ""

    iput-object p1, p0, Lcom/xiaomi/stats/StatsHandler;->uuid:Ljava/lang/String;

    invoke-static {}, Lcom/xiaomi/push/service/ServiceConfig;->getInstance()Lcom/xiaomi/push/service/ServiceConfig;

    move-result-object p1

    new-instance v0, Lcom/xiaomi/stats/StatsHandler$1;

    invoke-direct {v0, p0}, Lcom/xiaomi/stats/StatsHandler$1;-><init>(Lcom/xiaomi/stats/StatsHandler;)V

    invoke-virtual {p1, v0}, Lcom/xiaomi/push/service/ServiceConfig;->addListener(Lcom/xiaomi/push/service/ServiceConfig$Listener;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit p0

    throw p1
.end method

.method public isAllowStats()Z
    .locals 1

    iget-boolean v0, p0, Lcom/xiaomi/stats/StatsHandler;->allowStatsUpload:Z

    return v0
.end method

.method declared-synchronized retriveStatsEvents()Lcom/xiaomi/push/thrift/StatsEvents;
    .locals 2

    monitor-enter p0

    const/4 v0, 0x0

    :try_start_0
    invoke-virtual {p0}, Lcom/xiaomi/stats/StatsHandler;->shouldSendStatsNow()Z

    move-result v1

    if-eqz v1, :cond_1

    const/16 v0, 0x2ee

    iget-object v1, p0, Lcom/xiaomi/stats/StatsHandler;->context:Lcom/xiaomi/stats/StatsContext;

    iget-object v1, v1, Lcom/xiaomi/stats/StatsContext;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v1}, Lcom/xiaomi/channel/commonutils/network/Network;->isWIFIConnected(Landroid/content/Context;)Z

    move-result v1

    if-nez v1, :cond_0

    const/16 v0, 0x177

    :cond_0
    invoke-direct {p0, v0}, Lcom/xiaomi/stats/StatsHandler;->retriveStatsEvents(I)Lcom/xiaomi/push/thrift/StatsEvents;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :cond_1
    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public setDuration(I)V
    .locals 3

    if-lez p1, :cond_2

    mul-int/lit16 p1, p1, 0x3e8

    const v0, 0x240c8400

    if-le p1, v0, :cond_0

    move p1, v0

    :cond_0
    iget v0, p0, Lcom/xiaomi/stats/StatsHandler;->duration:I

    if-ne v0, p1, :cond_1

    iget-boolean v0, p0, Lcom/xiaomi/stats/StatsHandler;->allowStatsUpload:Z

    if-nez v0, :cond_2

    :cond_1
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/xiaomi/stats/StatsHandler;->allowStatsUpload:Z

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/xiaomi/stats/StatsHandler;->startTime:J

    iput p1, p0, Lcom/xiaomi/stats/StatsHandler;->duration:I

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "enable dot duration = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, " start = "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lcom/xiaomi/stats/StatsHandler;->startTime:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    :cond_2
    return-void
.end method

.method shouldSendStatsNow()Z
    .locals 1

    invoke-direct {p0}, Lcom/xiaomi/stats/StatsHandler;->stopStatsIfNeed()V

    iget-boolean v0, p0, Lcom/xiaomi/stats/StatsHandler;->allowStatsUpload:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/xiaomi/stats/StatsHandler;->statsContainer:Lcom/xiaomi/channel/commonutils/stats/Stats;

    invoke-virtual {v0}, Lcom/xiaomi/channel/commonutils/stats/Stats;->getCount()I

    move-result v0

    if-lez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method
