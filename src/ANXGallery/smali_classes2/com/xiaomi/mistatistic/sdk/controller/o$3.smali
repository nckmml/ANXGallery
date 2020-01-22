.class Lcom/xiaomi/mistatistic/sdk/controller/o$3;
.super Ljava/lang/Object;
.source "RemoteDataUploadManager.java"

# interfaces
.implements Lcom/xiaomi/mistatistic/sdk/controller/e$a;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/xiaomi/mistatistic/sdk/controller/o;->a(JJLjava/lang/String;Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic a:Z

.field final synthetic b:J

.field final synthetic c:J

.field final synthetic d:Ljava/lang/String;

.field final synthetic e:Lcom/xiaomi/mistatistic/sdk/controller/o;


# direct methods
.method constructor <init>(Lcom/xiaomi/mistatistic/sdk/controller/o;ZJJLjava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->e:Lcom/xiaomi/mistatistic/sdk/controller/o;

    iput-boolean p2, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->a:Z

    iput-wide p3, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->b:J

    iput-wide p5, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->c:J

    iput-object p7, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->d:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public execute()V
    .locals 7

    const-string v0, "RDUM"

    :try_start_0
    new-instance v1, Lcom/xiaomi/mistatistic/sdk/controller/h;

    invoke-direct {v1}, Lcom/xiaomi/mistatistic/sdk/controller/h;-><init>()V

    iget-boolean v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->a:Z

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Ljava/lang/Boolean;)V

    iget-wide v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->b:J

    iget-wide v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->c:J

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/o;->d()I

    move-result v6

    invoke-virtual/range {v1 .. v6}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(JJI)V

    const-string v1, "d"

    iget-object v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/o$3;->d:Ljava/lang/String;

    invoke-static {v1, v2}, Lcom/xiaomi/mistatistic/sdk/controller/n;->a(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/n;->a()V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v1

    const-string v2, "doDeleting exception: "

    invoke-static {v0, v2, v1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_0
    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/o;->c()Ljava/util/concurrent/atomic/AtomicBoolean;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/o;->c()Ljava/util/concurrent/atomic/AtomicBoolean;

    move-result-object v3

    invoke-virtual {v3}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v3

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    aput-object v3, v1, v2

    const-string v2, "deleting done and uploading job is finished, set Uploading %b"

    invoke-static {v2, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
