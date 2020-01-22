.class Lcom/miui/gallery/util/photoview/TileDecodeManager$FutureContainer;
.super Ljava/lang/Object;
.source "TileDecodeManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/util/photoview/TileDecodeManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "FutureContainer"
.end annotation


# instance fields
.field mFutures:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/threadpool/Future;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/util/photoview/TileDecodeManager$FutureContainer;->mFutures:Ljava/util/List;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/util/photoview/TileDecodeManager$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/util/photoview/TileDecodeManager$FutureContainer;-><init>()V

    return-void
.end method


# virtual methods
.method public add(Lcom/miui/gallery/threadpool/Future;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/util/photoview/TileDecodeManager$FutureContainer;->mFutures:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public cancel()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/util/photoview/TileDecodeManager$FutureContainer;->mFutures:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/threadpool/Future;

    invoke-interface {v1}, Lcom/miui/gallery/threadpool/Future;->cancel()V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/util/photoview/TileDecodeManager$FutureContainer;->mFutures:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    return-void
.end method
