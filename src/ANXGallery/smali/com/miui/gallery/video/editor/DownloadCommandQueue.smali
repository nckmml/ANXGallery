.class public Lcom/miui/gallery/video/editor/DownloadCommandQueue;
.super Ljava/lang/Object;
.source "DownloadCommandQueue.java"

# interfaces
.implements Lmiui/util/concurrent/Queue;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lmiui/util/concurrent/Queue<",
        "Lcom/miui/gallery/video/editor/DownloadCommand;",
        ">;"
    }
.end annotation


# instance fields
.field private mCommands:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/editor/DownloadCommand;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    return-void
.end method


# virtual methods
.method public clear()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    const/4 v0, 0x0

    return v0
.end method

.method public get()Lcom/miui/gallery/video/editor/DownloadCommand;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-lez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    const/4 v1, 0x0

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/video/editor/DownloadCommand;

    return-object v0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public get(I)Lcom/miui/gallery/video/editor/DownloadCommand;
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->isEmpty()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    :cond_0
    if-ltz p1, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-le p1, v0, :cond_1

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/video/editor/DownloadCommand;

    return-object p1

    :cond_2
    :goto_0
    return-object v1
.end method

.method public bridge synthetic get()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->get()Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v0

    return-object v0
.end method

.method public getCapacity()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    return v0
.end method

.method public isEmpty()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    return v0
.end method

.method public put(Lcom/miui/gallery/video/editor/DownloadCommand;)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v1, 0x5

    if-lt v0, v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    move-result p1

    return p1

    :cond_1
    :goto_0
    const-string p1, "DownloadCommandQueue"

    const-string v0, "the queue add fail."

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 p1, 0x0

    return p1
.end method

.method public bridge synthetic put(Ljava/lang/Object;)Z
    .locals 0

    check-cast p1, Lcom/miui/gallery/video/editor/DownloadCommand;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->put(Lcom/miui/gallery/video/editor/DownloadCommand;)Z

    move-result p1

    return p1
.end method

.method public remove(Lmiui/util/concurrent/Queue$Predicate;)I
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/concurrent/Queue$Predicate<",
            "Lcom/miui/gallery/video/editor/DownloadCommand;",
            ">;)I"
        }
    .end annotation

    const/4 p1, 0x0

    return p1
.end method

.method public remove(Lcom/miui/gallery/video/editor/DownloadCommand;)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->mCommands:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(Ljava/lang/Object;)Z

    move-result p1

    return p1
.end method

.method public bridge synthetic remove(Ljava/lang/Object;)Z
    .locals 0

    check-cast p1, Lcom/miui/gallery/video/editor/DownloadCommand;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->remove(Lcom/miui/gallery/video/editor/DownloadCommand;)Z

    move-result p1

    return p1
.end method
