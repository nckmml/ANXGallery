.class Lcom/xiaomi/stats/BindTracker;
.super Ljava/lang/Object;
.source "BindTracker.java"

# interfaces
.implements Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo$ClientStatusListener;


# instance fields
.field private client:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

.field private connection:Lcom/xiaomi/smack/Connection;

.field private pushService:Lcom/xiaomi/push/service/XMPushService;

.field private reason:I

.field private status:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

.field private tracked:Z


# direct methods
.method constructor <init>(Lcom/xiaomi/push/service/XMPushService;Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/xiaomi/stats/BindTracker;->tracked:Z

    iput-object p1, p0, Lcom/xiaomi/stats/BindTracker;->pushService:Lcom/xiaomi/push/service/XMPushService;

    sget-object p1, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->binding:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    iput-object p1, p0, Lcom/xiaomi/stats/BindTracker;->status:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    iput-object p2, p0, Lcom/xiaomi/stats/BindTracker;->client:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    return-void
.end method

.method static synthetic access$000(Lcom/xiaomi/stats/BindTracker;)V
    .locals 0

    invoke-direct {p0}, Lcom/xiaomi/stats/BindTracker;->done()V

    return-void
.end method

.method private done()V
    .locals 4

    invoke-direct {p0}, Lcom/xiaomi/stats/BindTracker;->untrack()V

    iget-boolean v0, p0, Lcom/xiaomi/stats/BindTracker;->tracked:Z

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget v0, p0, Lcom/xiaomi/stats/BindTracker;->reason:I

    const/16 v1, 0xb

    if-ne v0, v1, :cond_1

    return-void

    :cond_1
    invoke-static {}, Lcom/xiaomi/stats/StatsHandler;->getInstance()Lcom/xiaomi/stats/StatsHandler;

    move-result-object v0

    invoke-virtual {v0}, Lcom/xiaomi/stats/StatsHandler;->createStatsEvent()Lcom/xiaomi/push/thrift/StatsEvent;

    move-result-object v0

    sget-object v1, Lcom/xiaomi/stats/BindTracker$2;->$SwitchMap$com$xiaomi$push$service$PushClientsManager$ClientStatus:[I

    iget-object v2, p0, Lcom/xiaomi/stats/BindTracker;->status:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    invoke-virtual {v2}, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->ordinal()I

    move-result v2

    aget v1, v1, v2

    const/4 v2, 0x1

    if-eq v1, v2, :cond_3

    const/4 v3, 0x2

    if-eq v1, v3, :cond_6

    const/4 v3, 0x3

    if-eq v1, v3, :cond_2

    goto :goto_0

    :cond_2
    sget-object v1, Lcom/xiaomi/push/thrift/ChannelStatsType;->BIND_SUCCESS:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v1}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v1

    iput v1, v0, Lcom/xiaomi/push/thrift/StatsEvent;->type:I

    goto :goto_0

    :cond_3
    iget v1, p0, Lcom/xiaomi/stats/BindTracker;->reason:I

    const/16 v3, 0x11

    if-ne v1, v3, :cond_4

    sget-object v1, Lcom/xiaomi/push/thrift/ChannelStatsType;->BIND_TCP_READ_TIMEOUT:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v1}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v1

    iput v1, v0, Lcom/xiaomi/push/thrift/StatsEvent;->type:I

    goto :goto_0

    :cond_4
    const/16 v3, 0x15

    if-ne v1, v3, :cond_5

    sget-object v1, Lcom/xiaomi/push/thrift/ChannelStatsType;->BIND_TIMEOUT:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v1}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v1

    iput v1, v0, Lcom/xiaomi/push/thrift/StatsEvent;->type:I

    goto :goto_0

    :cond_5
    :try_start_0
    invoke-static {}, Lcom/xiaomi/stats/StatsHandler;->getContext()Lcom/xiaomi/stats/StatsContext;

    move-result-object v1

    invoke-virtual {v1}, Lcom/xiaomi/stats/StatsContext;->getCaughtException()Ljava/lang/Exception;

    move-result-object v1

    invoke-static {v1}, Lcom/xiaomi/stats/StatsAnalyser;->fromBind(Ljava/lang/Exception;)Lcom/xiaomi/stats/StatsAnalyser$TypeWraper;

    move-result-object v1

    iget-object v3, v1, Lcom/xiaomi/stats/StatsAnalyser$TypeWraper;->type:Lcom/xiaomi/push/thrift/ChannelStatsType;

    invoke-virtual {v3}, Lcom/xiaomi/push/thrift/ChannelStatsType;->getValue()I

    move-result v3

    iput v3, v0, Lcom/xiaomi/push/thrift/StatsEvent;->type:I

    iget-object v1, v1, Lcom/xiaomi/stats/StatsAnalyser$TypeWraper;->annotation:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setAnnotation(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvent;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const/4 v0, 0x0

    :cond_6
    :goto_0
    if-eqz v0, :cond_7

    iget-object v1, p0, Lcom/xiaomi/stats/BindTracker;->connection:Lcom/xiaomi/smack/Connection;

    invoke-virtual {v1}, Lcom/xiaomi/smack/Connection;->getHost()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setHost(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvent;

    iget-object v1, p0, Lcom/xiaomi/stats/BindTracker;->client:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    iget-object v1, v1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->userId:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setUser(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvent;

    iput v2, v0, Lcom/xiaomi/push/thrift/StatsEvent;->value:I

    :try_start_1
    iget-object v1, p0, Lcom/xiaomi/stats/BindTracker;->client:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    iget-object v1, v1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->chid:Ljava/lang/String;

    invoke-static {v1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v1

    int-to-byte v1, v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/thrift/StatsEvent;->setChid(B)Lcom/xiaomi/push/thrift/StatsEvent;
    :try_end_1
    .catch Ljava/lang/NumberFormatException; {:try_start_1 .. :try_end_1} :catch_1

    :catch_1
    invoke-static {}, Lcom/xiaomi/stats/StatsHandler;->getInstance()Lcom/xiaomi/stats/StatsHandler;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/xiaomi/stats/StatsHandler;->add(Lcom/xiaomi/push/thrift/StatsEvent;)V

    :cond_7
    return-void
.end method

.method private untrack()V
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/stats/BindTracker;->client:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    invoke-virtual {v0, p0}, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->removeClientStatusListener(Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo$ClientStatusListener;)V

    return-void
.end method


# virtual methods
.method public onChange(Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;I)V
    .locals 1

    iget-boolean v0, p0, Lcom/xiaomi/stats/BindTracker;->tracked:Z

    if-nez v0, :cond_0

    sget-object v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->binding:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    if-ne p1, v0, :cond_0

    iput-object p2, p0, Lcom/xiaomi/stats/BindTracker;->status:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    iput p3, p0, Lcom/xiaomi/stats/BindTracker;->reason:I

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/xiaomi/stats/BindTracker;->tracked:Z

    :cond_0
    iget-object p1, p0, Lcom/xiaomi/stats/BindTracker;->pushService:Lcom/xiaomi/push/service/XMPushService;

    new-instance p2, Lcom/xiaomi/stats/BindTracker$1;

    const/4 p3, 0x4

    invoke-direct {p2, p0, p3}, Lcom/xiaomi/stats/BindTracker$1;-><init>(Lcom/xiaomi/stats/BindTracker;I)V

    invoke-virtual {p1, p2}, Lcom/xiaomi/push/service/XMPushService;->executeJob(Lcom/xiaomi/push/service/XMPushService$Job;)V

    return-void
.end method

.method track()V
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/stats/BindTracker;->client:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    invoke-virtual {v0, p0}, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->addClientStatusListener(Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo$ClientStatusListener;)V

    iget-object v0, p0, Lcom/xiaomi/stats/BindTracker;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-virtual {v0}, Lcom/xiaomi/push/service/XMPushService;->getCurrentConnection()Lcom/xiaomi/smack/Connection;

    move-result-object v0

    iput-object v0, p0, Lcom/xiaomi/stats/BindTracker;->connection:Lcom/xiaomi/smack/Connection;

    return-void
.end method
