.class Lcom/xiaomi/mistatistic/sdk/controller/h$1;
.super Ljava/lang/Object;
.source "EventDAO.java"

# interfaces
.implements Landroid/content/ServiceConnection;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/xiaomi/mistatistic/sdk/controller/h;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic a:Lcom/xiaomi/mistatistic/sdk/controller/h;


# direct methods
.method constructor <init>(Lcom/xiaomi/mistatistic/sdk/controller/h;)V
    .locals 0

    iput-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h$1;->a:Lcom/xiaomi/mistatistic/sdk/controller/h;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V
    .locals 1

    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h$1;->a:Lcom/xiaomi/mistatistic/sdk/controller/h;

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Lcom/xiaomi/mistatistic/sdk/controller/h;Z)Z

    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h$1;->a:Lcom/xiaomi/mistatistic/sdk/controller/h;

    invoke-static {p2}, Lcom/xiaomi/mistatistic/sdk/a$a;->a(Landroid/os/IBinder;)Lcom/xiaomi/mistatistic/sdk/a;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Lcom/xiaomi/mistatistic/sdk/controller/h;Lcom/xiaomi/mistatistic/sdk/a;)Lcom/xiaomi/mistatistic/sdk/a;

    return-void
.end method

.method public onServiceDisconnected(Landroid/content/ComponentName;)V
    .locals 1

    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h$1;->a:Lcom/xiaomi/mistatistic/sdk/controller/h;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Lcom/xiaomi/mistatistic/sdk/controller/h;Z)Z

    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h$1;->a:Lcom/xiaomi/mistatistic/sdk/controller/h;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Lcom/xiaomi/mistatistic/sdk/controller/h;Lcom/xiaomi/mistatistic/sdk/a;)Lcom/xiaomi/mistatistic/sdk/a;

    return-void
.end method
