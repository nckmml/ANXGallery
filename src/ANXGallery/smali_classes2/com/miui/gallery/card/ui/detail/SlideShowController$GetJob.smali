.class Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;
.super Ljava/lang/Object;
.source "SlideShowController.java"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/card/ui/detail/SlideShowController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "GetJob"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/threadpool/ThreadPool$Job<",
        "Lcom/miui/gallery/card/ui/detail/SlideShowController$ShowItem;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/card/ui/detail/SlideShowController;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/card/ui/detail/SlideShowController;Lcom/miui/gallery/card/ui/detail/SlideShowController$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;-><init>(Lcom/miui/gallery/card/ui/detail/SlideShowController;)V

    return-void
.end method


# virtual methods
.method public run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Lcom/miui/gallery/card/ui/detail/SlideShowController$ShowItem;
    .locals 6

    const/4 v0, 0x0

    move-object v1, v0

    :cond_0
    :goto_0
    invoke-interface {p1}, Lcom/miui/gallery/threadpool/ThreadPool$JobContext;->isCancelled()Z

    move-result v2

    if-nez v2, :cond_2

    if-nez v1, :cond_2

    :try_start_0
    iget-object v2, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-static {v2}, Lcom/miui/gallery/card/ui/detail/SlideShowController;->access$500(Lcom/miui/gallery/card/ui/detail/SlideShowController;)Ljava/util/concurrent/BlockingQueue;

    move-result-object v2

    const-wide/16 v3, 0x3e8

    sget-object v5, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-interface {v2, v3, v4, v5}, Ljava/util/concurrent/BlockingQueue;->poll(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/card/ui/detail/SlideShowController$ShowItem;
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    move-object v1, v2

    goto :goto_1

    :catch_0
    move-exception v2

    iget-object v3, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-static {v3}, Lcom/miui/gallery/card/ui/detail/SlideShowController;->access$500(Lcom/miui/gallery/card/ui/detail/SlideShowController;)Ljava/util/concurrent/BlockingQueue;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/concurrent/BlockingQueue;->size()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const-string v4, "SlideShowController"

    const-string v5, "poll interrupted, curSize %d"

    invoke-static {v4, v5, v3}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {v2}, Ljava/lang/InterruptedException;->printStackTrace()V

    :goto_1
    if-nez v1, :cond_0

    iget-object v2, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-static {v2}, Lcom/miui/gallery/card/ui/detail/SlideShowController;->access$600(Lcom/miui/gallery/card/ui/detail/SlideShowController;)Ljava/lang/Object;

    move-result-object v2

    monitor-enter v2

    :try_start_1
    iget-object v3, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-static {v3}, Lcom/miui/gallery/card/ui/detail/SlideShowController;->access$700(Lcom/miui/gallery/card/ui/detail/SlideShowController;)I

    move-result v3

    iget-object v4, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-virtual {v4}, Lcom/miui/gallery/card/ui/detail/SlideShowController;->getCount()I

    move-result v4

    if-lt v3, v4, :cond_1

    monitor-exit v2

    return-object v0

    :cond_1
    monitor-exit v2

    goto :goto_0

    :catchall_0
    move-exception p1

    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-static {p1}, Lcom/miui/gallery/card/ui/detail/SlideShowController;->access$500(Lcom/miui/gallery/card/ui/detail/SlideShowController;)Ljava/util/concurrent/BlockingQueue;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/concurrent/BlockingQueue;->size()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string v0, "SlideShowController"

    const-string v2, "getJob cancelled, curSize %d"

    invoke-static {v0, v2, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    if-eqz v1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->this$0:Lcom/miui/gallery/card/ui/detail/SlideShowController;

    invoke-virtual {v1}, Lcom/miui/gallery/card/ui/detail/SlideShowController$ShowItem;->getIndex()I

    move-result v0

    invoke-static {p1, v0}, Lcom/miui/gallery/card/ui/detail/SlideShowController;->access$802(Lcom/miui/gallery/card/ui/detail/SlideShowController;I)I

    :cond_3
    return-object v1
.end method

.method public bridge synthetic run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/card/ui/detail/SlideShowController$GetJob;->run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Lcom/miui/gallery/card/ui/detail/SlideShowController$ShowItem;

    move-result-object p1

    return-object p1
.end method
