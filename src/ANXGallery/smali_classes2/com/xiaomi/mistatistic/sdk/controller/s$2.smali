.class Lcom/xiaomi/mistatistic/sdk/controller/s$2;
.super Ljava/lang/Object;
.source "UploadPolicyEngine.java"

# interfaces
.implements Lcom/xiaomi/mistatistic/sdk/controller/e$a;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/xiaomi/mistatistic/sdk/controller/s;->b()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic a:Lcom/xiaomi/mistatistic/sdk/controller/s;


# direct methods
.method constructor <init>(Lcom/xiaomi/mistatistic/sdk/controller/s;)V
    .locals 0

    iput-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public execute()V
    .locals 6

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    const-string v2, "upload_policy"

    const/4 v3, 0x0

    invoke-static {v0, v2, v3}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;I)I

    move-result v2

    invoke-static {v1, v2}, Lcom/xiaomi/mistatistic/sdk/controller/s;->a(Lcom/xiaomi/mistatistic/sdk/controller/s;I)I

    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-static {v1}, Lcom/xiaomi/mistatistic/sdk/controller/s;->h(Lcom/xiaomi/mistatistic/sdk/controller/s;)I

    move-result v1

    const-string v2, "UPE"

    if-nez v1, :cond_0

    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    const-wide/32 v3, 0x15f90

    const-string v5, "upload_interval"

    invoke-static {v0, v5, v3, v4}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;J)J

    move-result-wide v3

    invoke-static {v1, v3, v4}, Lcom/xiaomi/mistatistic/sdk/controller/s;->a(Lcom/xiaomi/mistatistic/sdk/controller/s;J)J

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Upload policy is UPLOAD_POLICY_INTERVAL and interval is "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-static {v3}, Lcom/xiaomi/mistatistic/sdk/controller/s;->i(Lcom/xiaomi/mistatistic/sdk/controller/s;)J

    move-result-wide v3

    invoke-virtual {v1, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v2, v1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->c(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-static {v1}, Lcom/xiaomi/mistatistic/sdk/controller/s;->j(Lcom/xiaomi/mistatistic/sdk/controller/s;)V

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-static {v1}, Lcom/xiaomi/mistatistic/sdk/controller/s;->h(Lcom/xiaomi/mistatistic/sdk/controller/s;)I

    move-result v1

    const/4 v3, 0x1

    if-ne v1, v3, :cond_1

    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    const-wide/16 v3, 0xc00

    const-string v5, "upload_size"

    invoke-static {v0, v5, v3, v4}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;J)J

    move-result-wide v3

    invoke-static {v1, v3, v4}, Lcom/xiaomi/mistatistic/sdk/controller/s;->b(Lcom/xiaomi/mistatistic/sdk/controller/s;J)J

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Upload policy is UPLOAD_POLICY_BATCH and size is "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-static {v3}, Lcom/xiaomi/mistatistic/sdk/controller/s;->k(Lcom/xiaomi/mistatistic/sdk/controller/s;)J

    move-result-wide v3

    invoke-virtual {v1, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v2, v1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->c(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    :goto_0
    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    const/16 v3, 0x1f

    const-string v4, "upload_network"

    invoke-static {v0, v4, v3}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;I)I

    move-result v0

    invoke-static {v1, v0}, Lcom/xiaomi/mistatistic/sdk/controller/s;->b(Lcom/xiaomi/mistatistic/sdk/controller/s;I)I

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Upload network is "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-static {v1}, Lcom/xiaomi/mistatistic/sdk/controller/s;->l(Lcom/xiaomi/mistatistic/sdk/controller/s;)I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v0}, Lcom/xiaomi/mistatistic/sdk/controller/j;->c(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/xiaomi/mistatistic/sdk/controller/s$2;->a:Lcom/xiaomi/mistatistic/sdk/controller/s;

    invoke-static {v0}, Lcom/xiaomi/mistatistic/sdk/controller/s;->m(Lcom/xiaomi/mistatistic/sdk/controller/s;)V

    return-void
.end method
