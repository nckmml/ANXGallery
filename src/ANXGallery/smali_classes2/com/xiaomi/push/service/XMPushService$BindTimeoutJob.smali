.class Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;
.super Lcom/xiaomi/push/service/XMPushService$Job;
.source "XMPushService.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/xiaomi/push/service/XMPushService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "BindTimeoutJob"
.end annotation


# instance fields
.field private final mLoginInfo:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;


# direct methods
.method public constructor <init>(Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;)V
    .locals 1

    const/16 v0, 0xc

    invoke-direct {p0, v0}, Lcom/xiaomi/push/service/XMPushService$Job;-><init>(I)V

    iput-object p1, p0, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;->mLoginInfo:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    return-void
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 1

    instance-of v0, p1, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    check-cast p1, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;

    iget-object p1, p1, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;->mLoginInfo:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    iget-object p1, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->chid:Ljava/lang/String;

    iget-object v0, p0, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;->mLoginInfo:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    iget-object v0, v0, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->chid:Ljava/lang/String;

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    return p1
.end method

.method public getDesc()Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "bind time out. chid="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;->mLoginInfo:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    iget-object v1, v1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->chid:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public hashCode()I
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;->mLoginInfo:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    iget-object v0, v0, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->chid:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v0

    return v0
.end method

.method public process()V
    .locals 6

    iget-object v0, p0, Lcom/xiaomi/push/service/XMPushService$BindTimeoutJob;->mLoginInfo:Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    sget-object v1, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->unbind:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    const/4 v2, 0x1

    const/16 v3, 0x15

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-virtual/range {v0 .. v5}, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->setStatus(Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;IILjava/lang/String;Ljava/lang/String;)V

    return-void
.end method
