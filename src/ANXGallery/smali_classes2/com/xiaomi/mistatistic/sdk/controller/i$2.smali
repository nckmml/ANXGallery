.class Lcom/xiaomi/mistatistic/sdk/controller/i$2;
.super Landroid/os/Handler;
.source "HttpEventController.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/xiaomi/mistatistic/sdk/controller/i;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic a:Lcom/xiaomi/mistatistic/sdk/controller/i;


# direct methods
.method constructor <init>(Lcom/xiaomi/mistatistic/sdk/controller/i;Landroid/os/Looper;)V
    .locals 0

    iput-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/i$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/i;

    invoke-direct {p0, p2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 7

    iget p1, p1, Landroid/os/Message;->what:I

    const/16 v0, 0x3ff

    if-eq p1, v0, :cond_0

    goto :goto_2

    :cond_0
    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/i$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/i;

    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/i;->c()Z

    move-result p1

    const-string v0, "HttpEventController handleMessage exception"

    const-string v1, "HEC"

    if-eqz p1, :cond_2

    :try_start_0
    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/i$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/i;

    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/i;->b()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v2

    if-lez v2, :cond_3

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_3

    add-int/lit8 v4, v3, 0x1e

    if-lt v4, v2, :cond_1

    invoke-interface {p1, v3, v2}, Ljava/util/List;->subList(II)Ljava/util/List;

    move-result-object v3

    goto :goto_1

    :cond_1
    invoke-interface {p1, v3, v4}, Ljava/util/List;->subList(II)Ljava/util/List;

    move-result-object v3

    :goto_1
    iget-object v5, p0, Lcom/xiaomi/mistatistic/sdk/controller/i$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/i;

    new-instance v6, Lcom/xiaomi/mistatistic/sdk/controller/i$2$1;

    invoke-direct {v6, p0, v3}, Lcom/xiaomi/mistatistic/sdk/controller/i$2$1;-><init>(Lcom/xiaomi/mistatistic/sdk/controller/i$2;Ljava/util/List;)V

    invoke-static {v5, v3, v6}, Lcom/xiaomi/mistatistic/sdk/controller/i;->a(Lcom/xiaomi/mistatistic/sdk/controller/i;Ljava/util/List;Lcom/xiaomi/mistatistic/sdk/controller/l$b;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move v3, v4

    goto :goto_0

    :catch_0
    move-exception p1

    invoke-static {v1, v0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_2

    :cond_2
    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/i$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/i;

    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/i;->d()Z

    move-result p1

    if-eqz p1, :cond_3

    new-instance p1, Lorg/json/JSONArray;

    invoke-direct {p1}, Lorg/json/JSONArray;-><init>()V

    :try_start_1
    iget-object v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/i$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/i;

    invoke-virtual {p1}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object p1

    new-instance v3, Lcom/xiaomi/mistatistic/sdk/controller/i$2$2;

    invoke-direct {v3, p0}, Lcom/xiaomi/mistatistic/sdk/controller/i$2$2;-><init>(Lcom/xiaomi/mistatistic/sdk/controller/i$2;)V

    invoke-static {v2, p1, v3}, Lcom/xiaomi/mistatistic/sdk/controller/i;->a(Lcom/xiaomi/mistatistic/sdk/controller/i;Ljava/lang/String;Lcom/xiaomi/mistatistic/sdk/controller/l$b;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_2

    :catch_1
    move-exception p1

    invoke-static {v1, v0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_3
    :goto_2
    return-void
.end method
