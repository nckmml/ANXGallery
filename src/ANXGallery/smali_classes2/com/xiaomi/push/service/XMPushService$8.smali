.class Lcom/xiaomi/push/service/XMPushService$8;
.super Ljava/lang/Object;
.source "XMPushService.java"

# interfaces
.implements Lcom/xiaomi/push/service/MIPushAccountUtils$PushAccountChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/xiaomi/push/service/XMPushService;->postOnCreate()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/xiaomi/push/service/XMPushService;

.field final synthetic val$job:Lcom/xiaomi/push/service/XMPushService$Job;


# direct methods
.method constructor <init>(Lcom/xiaomi/push/service/XMPushService;Lcom/xiaomi/push/service/XMPushService$Job;)V
    .locals 0

    iput-object p1, p0, Lcom/xiaomi/push/service/XMPushService$8;->this$0:Lcom/xiaomi/push/service/XMPushService;

    iput-object p2, p0, Lcom/xiaomi/push/service/XMPushService$8;->val$job:Lcom/xiaomi/push/service/XMPushService$Job;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onChange()V
    .locals 2

    iget-object v0, p0, Lcom/xiaomi/push/service/XMPushService$8;->this$0:Lcom/xiaomi/push/service/XMPushService;

    iget-object v1, p0, Lcom/xiaomi/push/service/XMPushService$8;->val$job:Lcom/xiaomi/push/service/XMPushService$Job;

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/service/XMPushService;->executeJob(Lcom/xiaomi/push/service/XMPushService$Job;)V

    return-void
.end method
