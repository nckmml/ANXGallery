.class public Lcom/miui/gallery/util/thread/ThreadManager;
.super Lcom/miui/gallery/threadpool/ThreadManager;
.source "ThreadManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/util/thread/ThreadManager$ThreadFactory;
    }
.end annotation


# static fields
.field private static DEFAULT_REJECT_EDEXECUTION_HANDLER:Ljava/util/concurrent/RejectedExecutionHandler;

.field private static volatile sInnerPool:Lcom/miui/gallery/threadpool/ThreadPool;

.field private static sInnerPoolLock:Ljava/lang/Object;

.field private static volatile sRightNowPool:Lcom/miui/gallery/threadpool/ThreadPool;

.field private static sRightNowPoolLock:Ljava/lang/Object;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/thread/ThreadManager;->sRightNowPoolLock:Ljava/lang/Object;

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/thread/ThreadManager;->sInnerPoolLock:Ljava/lang/Object;

    new-instance v0, Lcom/miui/gallery/util/thread/ThreadManager$1;

    invoke-direct {v0}, Lcom/miui/gallery/util/thread/ThreadManager$1;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/thread/ThreadManager;->DEFAULT_REJECT_EDEXECUTION_HANDLER:Ljava/util/concurrent/RejectedExecutionHandler;

    return-void
.end method

.method public static execute(ILjava/lang/Runnable;)V
    .locals 7

    const/16 v0, 0x1f

    if-eq p0, v0, :cond_3

    const/16 v0, 0x2f

    if-eq p0, v0, :cond_0

    const/16 v0, 0x3f

    if-eq p0, v0, :cond_0

    goto :goto_2

    :cond_0
    sget-object p0, Lcom/miui/gallery/util/thread/ThreadManager;->sInnerPool:Lcom/miui/gallery/threadpool/ThreadPool;

    if-nez p0, :cond_2

    sget-object p0, Lcom/miui/gallery/util/thread/ThreadManager;->sInnerPoolLock:Ljava/lang/Object;

    monitor-enter p0

    :try_start_0
    sget-object v0, Lcom/miui/gallery/util/thread/ThreadManager;->sInnerPool:Lcom/miui/gallery/threadpool/ThreadPool;

    if-nez v0, :cond_1

    new-instance v0, Lcom/miui/gallery/threadpool/ThreadPool;

    invoke-static {}, Lcom/miui/gallery/util/thread/ThreadConfig;->getSuggestThreadCoreSize()I

    move-result v2

    invoke-static {}, Lcom/miui/gallery/util/thread/ThreadConfig;->getSuggestThreadMaxSize()I

    move-result v3

    new-instance v4, Lcom/miui/gallery/util/thread/ThreadManager$ThreadFactory;

    const/16 v1, 0xa

    invoke-direct {v4, v1}, Lcom/miui/gallery/util/thread/ThreadManager$ThreadFactory;-><init>(I)V

    const/16 v5, 0x40

    sget-object v6, Lcom/miui/gallery/util/thread/ThreadManager;->DEFAULT_REJECT_EDEXECUTION_HANDLER:Ljava/util/concurrent/RejectedExecutionHandler;

    move-object v1, v0

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/threadpool/ThreadPool;-><init>(IILjava/util/concurrent/ThreadFactory;ILjava/util/concurrent/RejectedExecutionHandler;)V

    sput-object v0, Lcom/miui/gallery/util/thread/ThreadManager;->sInnerPool:Lcom/miui/gallery/threadpool/ThreadPool;

    :cond_1
    monitor-exit p0

    goto :goto_0

    :catchall_0
    move-exception p1

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1

    :cond_2
    :goto_0
    sget-object p0, Lcom/miui/gallery/util/thread/ThreadManager;->sInnerPool:Lcom/miui/gallery/threadpool/ThreadPool;

    invoke-virtual {p0}, Lcom/miui/gallery/threadpool/ThreadPool;->asExecutorService()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/concurrent/ExecutorService;->execute(Ljava/lang/Runnable;)V

    goto :goto_2

    :cond_3
    sget-object p0, Lcom/miui/gallery/util/thread/ThreadManager;->sRightNowPool:Lcom/miui/gallery/threadpool/ThreadPool;

    if-nez p0, :cond_5

    sget-object p0, Lcom/miui/gallery/util/thread/ThreadManager;->sRightNowPoolLock:Ljava/lang/Object;

    monitor-enter p0

    :try_start_1
    sget-object v0, Lcom/miui/gallery/util/thread/ThreadManager;->sRightNowPool:Lcom/miui/gallery/threadpool/ThreadPool;

    if-nez v0, :cond_4

    new-instance v0, Lcom/miui/gallery/threadpool/ThreadPool;

    invoke-static {}, Lcom/miui/gallery/util/thread/ThreadConfig;->getSuggestThreadCoreSize()I

    move-result v2

    invoke-static {}, Lcom/miui/gallery/util/thread/ThreadConfig;->getSuggestThreadMaxSize()I

    move-result v3

    new-instance v4, Lcom/miui/gallery/util/thread/ThreadManager$ThreadFactory;

    const/4 v1, 0x0

    invoke-direct {v4, v1}, Lcom/miui/gallery/util/thread/ThreadManager$ThreadFactory;-><init>(I)V

    const/16 v5, 0x20

    sget-object v6, Lcom/miui/gallery/util/thread/ThreadManager;->DEFAULT_REJECT_EDEXECUTION_HANDLER:Ljava/util/concurrent/RejectedExecutionHandler;

    move-object v1, v0

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/threadpool/ThreadPool;-><init>(IILjava/util/concurrent/ThreadFactory;ILjava/util/concurrent/RejectedExecutionHandler;)V

    sput-object v0, Lcom/miui/gallery/util/thread/ThreadManager;->sRightNowPool:Lcom/miui/gallery/threadpool/ThreadPool;

    :cond_4
    monitor-exit p0

    goto :goto_1

    :catchall_1
    move-exception p1

    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    throw p1

    :cond_5
    :goto_1
    sget-object p0, Lcom/miui/gallery/util/thread/ThreadManager;->sRightNowPool:Lcom/miui/gallery/threadpool/ThreadPool;

    invoke-virtual {p0}, Lcom/miui/gallery/threadpool/ThreadPool;->asExecutorService()Ljava/util/concurrent/ExecutorService;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/concurrent/ExecutorService;->execute(Ljava/lang/Runnable;)V

    :goto_2
    return-void
.end method
