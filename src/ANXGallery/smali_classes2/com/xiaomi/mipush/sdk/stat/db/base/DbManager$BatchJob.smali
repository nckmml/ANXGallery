.class public Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BatchJob;
.super Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;
.source "DbManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "BatchJob"
.end annotation


# instance fields
.field private mJobs:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/util/ArrayList;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/ArrayList<",
            "Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0, p1}, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;-><init>(Ljava/lang/String;)V

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BatchJob;->mJobs:Ljava/util/ArrayList;

    iget-object p1, p0, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BatchJob;->mJobs:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    return-void
.end method


# virtual methods
.method public doRun(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iget-object v0, p0, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BatchJob;->mJobs:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;

    if-eqz v1, :cond_0

    invoke-virtual {v1, p1, p2}, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;->doRun(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_0

    :cond_1
    return-void
.end method

.method public final finish(Landroid/content/Context;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;->finish(Landroid/content/Context;)V

    iget-object v0, p0, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BatchJob;->mJobs:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;

    if-eqz v1, :cond_0

    invoke-virtual {v1, p1}, Lcom/xiaomi/mipush/sdk/stat/db/base/DbManager$BaseJob;->finish(Landroid/content/Context;)V

    goto :goto_0

    :cond_1
    return-void
.end method
