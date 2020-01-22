.class public abstract Lcom/miui/gallery/cloud/thread/BaseTask;
.super Ljava/lang/Object;
.source "BaseTask.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/lang/Runnable;"
    }
.end annotation


# instance fields
.field protected final TAG:Ljava/lang/String;

.field private volatile isRunning:Z

.field private final mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

.field private mHasInterruptted:Ljava/util/concurrent/atomic/AtomicBoolean;


# direct methods
.method public constructor <init>(IIIILcom/miui/gallery/cloud/thread/RequestCommandQueue$OnItemChangedListener;)V
    .locals 6

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->isRunning:Z

    new-instance v1, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v1, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mHasInterruptted:Ljava/util/concurrent/atomic/AtomicBoolean;

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v3

    aput-object v3, v2, v0

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const/4 v0, 0x1

    aput-object p1, v2, v0

    const-string p1, "%s#%s"

    invoke-static {v1, p1, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    new-instance p1, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    iget-object v5, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    move-object v0, p1

    move v1, p2

    move v2, p3

    move v3, p4

    move-object v4, p5

    invoke-direct/range {v0 .. v5}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;-><init>(IIILcom/miui/gallery/cloud/thread/RequestCommandQueue$OnItemChangedListener;Ljava/lang/String;)V

    iput-object p1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    return-void
.end method


# virtual methods
.method public cancelAll(IZ)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {v0, p1, p2}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->cancelAll(IZ)V

    return-void
.end method

.method public getPendingSize()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->getPengdingSize()I

    move-result v0

    return v0
.end method

.method protected abstract handle(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;)Lcom/miui/gallery/cloud/base/GallerySyncResult;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Landroid/accounts/Account;",
            "Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;",
            "Ljava/util/List<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;)",
            "Lcom/miui/gallery/cloud/base/GallerySyncResult<",
            "TT;>;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation
.end method

.method public hasDelayedItem()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->hasDelayedItem()Z

    move-result v0

    return v0
.end method

.method public invoke(Ljava/util/List;ZZ)[I
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;ZZ)[I"
        }
    .end annotation

    invoke-static {}, Lcom/miui/gallery/cloud/AccountCache;->getAccount()Landroid/accounts/Account;

    move-result-object v0

    const/4 v1, 0x2

    if-nez v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    const-string p2, "invoke: no account"

    invoke-static {p1, p2}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    new-array p1, v1, [I

    fill-array-data p1, :array_0

    return-object p1

    :cond_0
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v2

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-nez v2, :cond_5

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v2

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/miui/gallery/cloud/RequestCloudItem;

    new-instance v6, Lcom/miui/gallery/cloud/thread/RequestCommand;

    invoke-direct {v6, v0, v5}, Lcom/miui/gallery/cloud/thread/RequestCommand;-><init>(Landroid/accounts/Account;Lcom/miui/gallery/cloud/RequestCloudItem;)V

    invoke-interface {v2, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {p1, v2, p3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->put(Ljava/util/List;Z)I

    move-result p1

    if-eqz p2, :cond_4

    iget-object p2, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {p2, v2}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->interruptIfNotExecuting(Ljava/util/List;)Ljava/util/List;

    move-result-object p2

    if-eqz p2, :cond_2

    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p2

    goto :goto_1

    :cond_2
    move p2, v4

    :goto_1
    iget-object p3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mHasInterruptted:Ljava/util/concurrent/atomic/AtomicBoolean;

    if-lez p2, :cond_3

    move v0, v3

    goto :goto_2

    :cond_3
    move v0, v4

    :goto_2
    invoke-virtual {p3, v4, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    goto :goto_3

    :cond_4
    move p2, v4

    goto :goto_3

    :cond_5
    move p1, v4

    move p2, p1

    :goto_3
    new-array p3, v1, [I

    aput p1, p3, v4

    aput p2, p3, v3

    return-object p3

    nop

    :array_0
    .array-data 4
        0x0
        0x0
    .end array-data
.end method

.method protected needContinue(Lcom/miui/gallery/cloud/base/GallerySyncResult;)Z
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/cloud/base/GallerySyncResult<",
            "TT;>;)Z"
        }
    .end annotation

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Thread;->isInterrupted()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    :cond_0
    iget-object v0, p1, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    const/4 v3, 0x1

    if-eq v0, v2, :cond_4

    iget-object v0, p1, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->NOT_CONTINUE_ERROR:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-eq v0, v2, :cond_4

    iget-object p1, p1, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v0, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CANCEL:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne p1, v0, :cond_1

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {p1}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->getPengdingSize()I

    move-result p1

    if-gtz p1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mHasInterruptted:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {p1, v3, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/thread/BaseTask;->resumeInterruptted()I

    move-result p1

    if-lez p1, :cond_2

    move v1, v3

    :cond_2
    return v1

    :cond_3
    return v3

    :cond_4
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {p1, v3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->cancelAll(Z)V

    return v1
.end method

.method protected onPerformSync()Lcom/miui/gallery/cloud/base/GallerySyncResult;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/gallery/cloud/base/GallerySyncResult<",
            "TT;>;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v1

    new-instance v2, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    invoke-direct {v2}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;-><init>()V

    :try_start_0
    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-virtual {v3, v0}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->pollToExecute(Ljava/util/List;)J

    move-result-wide v3

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_3

    const-wide/16 v5, 0x0

    cmp-long v5, v3, v5

    if-lez v5, :cond_0

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v5

    monitor-enter v5
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    :try_start_1
    iget-object v6, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "wait for "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v7}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v6

    invoke-virtual {v6, v3, v4}, Ljava/lang/Object;->wait(J)V

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    const-string v4, "wait time out or notified"

    invoke-static {v3, v4}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v3

    goto :goto_1

    :catch_0
    :try_start_2
    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    const-string v4, "resume from waiting by interuptted"

    invoke-static {v3, v4}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    monitor-exit v5

    goto :goto_2

    :goto_1
    monitor-exit v5
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    throw v3

    :cond_0
    :goto_2
    sget-object v3, Lcom/miui/gallery/cloud/base/GallerySyncCode;->OK:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v2, v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v3

    invoke-virtual {v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2
    :try_end_3
    .catch Ljava/lang/InterruptedException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_3
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-static {v3}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey(Lcom/miui/gallery/cloud/RequestCloudItem;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->removeFromExecuting(Ljava/lang/String;)V

    goto :goto_3

    :cond_1
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_4
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey()Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v5

    iget-wide v7, v1, Lcom/miui/gallery/cloud/thread/RequestCommand;->mInvokeTime:J

    sub-long/2addr v5, v7

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v5, "execute: %s, invoke~finish cost=%s"

    invoke-static {v3, v5, v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_4

    :cond_2
    return-object v2

    :cond_3
    :try_start_4
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_5
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v4, v4, Lcom/miui/gallery/cloud/thread/RequestCommand;->mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;

    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_5

    :cond_4
    const/4 v3, 0x0

    invoke-interface {v0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v4, v3, Lcom/miui/gallery/cloud/thread/RequestCommand;->mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;

    invoke-static {v4}, Lcom/miui/gallery/cloud/SyncConditionManager;->checkForItem(Lcom/miui/gallery/cloud/RequestItemBase;)I

    move-result v4

    const/4 v5, 0x2

    if-ne v4, v5, :cond_7

    sget-object v3, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v2, v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v3

    invoke-virtual {v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2
    :try_end_4
    .catch Ljava/lang/InterruptedException; {:try_start_4 .. :try_end_4} :catch_1
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_6
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_5

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-static {v3}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey(Lcom/miui/gallery/cloud/RequestCloudItem;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->removeFromExecuting(Ljava/lang/String;)V

    goto :goto_6

    :cond_5
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_7
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey()Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v5

    iget-wide v7, v1, Lcom/miui/gallery/cloud/thread/RequestCommand;->mInvokeTime:J

    sub-long/2addr v5, v7

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v5, "execute: %s, invoke~finish cost=%s"

    invoke-static {v3, v5, v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_7

    :cond_6
    return-object v2

    :cond_7
    :try_start_5
    iget-object v3, v3, Lcom/miui/gallery/cloud/thread/RequestCommand;->mAccount:Landroid/accounts/Account;

    invoke-static {}, Lcom/miui/gallery/cloud/AccountCache;->getAccountInfo()Lcom/miui/gallery/cloud/AccountCache$AccountInfo;

    move-result-object v4

    if-eqz v4, :cond_a

    iget-object v5, v4, Lcom/miui/gallery/cloud/AccountCache$AccountInfo;->mAccount:Landroid/accounts/Account;

    invoke-virtual {v5, v3}, Landroid/accounts/Account;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_a

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v3

    iget-object v5, v4, Lcom/miui/gallery/cloud/AccountCache$AccountInfo;->mAccount:Landroid/accounts/Account;

    iget-object v4, v4, Lcom/miui/gallery/cloud/AccountCache$AccountInfo;->mToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    invoke-virtual {p0, v3, v5, v4, v1}, Lcom/miui/gallery/cloud/thread/BaseTask;->handle(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2
    :try_end_5
    .catch Ljava/lang/InterruptedException; {:try_start_5 .. :try_end_5} :catch_1
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_8
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_8

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-static {v3}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey(Lcom/miui/gallery/cloud/RequestCloudItem;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->removeFromExecuting(Ljava/lang/String;)V

    goto :goto_8

    :cond_8
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_9
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_9

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey()Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v5

    iget-wide v7, v1, Lcom/miui/gallery/cloud/thread/RequestCommand;->mInvokeTime:J

    sub-long/2addr v5, v7

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v5, "execute: %s, invoke~finish cost=%s"

    invoke-static {v3, v5, v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_9

    :cond_9
    return-object v2

    :cond_a
    if-eqz v4, :cond_b

    :try_start_6
    iget-object v5, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "execute: account is changed. old="

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", current="

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v3, v4, Lcom/miui/gallery/cloud/AccountCache$AccountInfo;->mAccount:Landroid/accounts/Account;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v5, v3}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_a

    :cond_b
    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    const-string v4, "execute: account is null"

    invoke-static {v3, v4}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_6
    .catch Ljava/lang/InterruptedException; {:try_start_6 .. :try_end_6} :catch_1
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    :goto_a
    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_b
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_c

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-static {v3}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey(Lcom/miui/gallery/cloud/RequestCloudItem;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->removeFromExecuting(Ljava/lang/String;)V

    goto :goto_b

    :cond_c
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_c
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_d

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey()Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v5

    iget-wide v7, v1, Lcom/miui/gallery/cloud/thread/RequestCommand;->mInvokeTime:J

    sub-long/2addr v5, v7

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v5, "execute: %s, invoke~finish cost=%s"

    invoke-static {v3, v5, v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_c

    :cond_d
    sget-object v0, Lcom/miui/gallery/cloud/base/GallerySyncCode;->UNKNOWN:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v2, v0}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    return-object v0

    :catchall_1
    move-exception v2

    goto :goto_f

    :catch_1
    move-exception v3

    :try_start_7
    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    const-string v5, "interruptted"

    invoke-static {v4, v5, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    sget-object v4, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CANCEL:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v2, v4}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v2

    invoke-virtual {v2, v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setException(Ljava/lang/Exception;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_1

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_d
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_e

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-static {v3}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey(Lcom/miui/gallery/cloud/RequestCloudItem;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->removeFromExecuting(Ljava/lang/String;)V

    goto :goto_d

    :cond_e
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_e
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_f

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey()Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v5

    iget-wide v7, v1, Lcom/miui/gallery/cloud/thread/RequestCommand;->mInvokeTime:J

    sub-long/2addr v5, v7

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v5, "execute: %s, invoke~finish cost=%s"

    invoke-static {v3, v5, v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_e

    :cond_f
    return-object v2

    :goto_f
    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_10
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_10

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->mCommandQueue:Lcom/miui/gallery/cloud/thread/RequestCommandQueue;

    invoke-static {v3}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey(Lcom/miui/gallery/cloud/RequestCloudItem;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4, v3}, Lcom/miui/gallery/cloud/thread/RequestCommandQueue;->removeFromExecuting(Ljava/lang/String;)V

    goto :goto_10

    :cond_10
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_11
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_11

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cloud/thread/RequestCommand;

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-virtual {v1}, Lcom/miui/gallery/cloud/thread/RequestCommand;->getKey()Ljava/lang/String;

    move-result-object v4

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v5

    iget-wide v7, v1, Lcom/miui/gallery/cloud/thread/RequestCommand;->mInvokeTime:J

    sub-long/2addr v5, v7

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v5, "execute: %s, invoke~finish cost=%s"

    invoke-static {v3, v5, v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_11

    :cond_11
    throw v2
.end method

.method protected abstract onPostExecute()V
.end method

.method protected abstract onPreExecute()V
.end method

.method protected resumeInterruptted()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public run()V
    .locals 5

    const-string v0, "thread end %s"

    iget-object v1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-static {v2}, Ljava/lang/System;->identityHashCode(Ljava/lang/Object;)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const-string v3, "thread start %s"

    invoke-static {v1, v3, v2}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->isRunning:Z

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/thread/BaseTask;->onPreExecute()V

    :cond_0
    const/4 v1, 0x0

    :try_start_0
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Thread;->isInterrupted()Z

    move-result v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v2, :cond_1

    const/4 v2, 0x0

    :try_start_1
    invoke-virtual {p0}, Lcom/miui/gallery/cloud/thread/BaseTask;->onPerformSync()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catch_0
    move-exception v3

    :try_start_2
    iget-object v4, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-static {v4, v3}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_0
    if-eqz v2, :cond_1

    invoke-virtual {p0, v2}, Lcom/miui/gallery/cloud/thread/BaseTask;->needContinue(Lcom/miui/gallery/cloud/base/GallerySyncResult;)Z

    move-result v2
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-nez v2, :cond_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/cloud/thread/BaseTask;->onPostExecute()V

    iget-object v2, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v3

    invoke-static {v3}, Ljava/lang/System;->identityHashCode(Ljava/lang/Object;)I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-static {v2, v0, v3}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iput-boolean v1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->isRunning:Z

    return-void

    :catchall_0
    move-exception v2

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/thread/BaseTask;->onPostExecute()V

    iget-object v3, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->TAG:Ljava/lang/String;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v4

    invoke-static {v4}, Ljava/lang/System;->identityHashCode(Ljava/lang/Object;)I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v3, v0, v4}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iput-boolean v1, p0, Lcom/miui/gallery/cloud/thread/BaseTask;->isRunning:Z

    throw v2
.end method
