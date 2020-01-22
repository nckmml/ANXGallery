.class Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;
.super Ljava/lang/Object;
.source "ResourceDownloadManager.java"

# interfaces
.implements Lcom/miui/gallery/net/download/Request$Listener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onComplete(I)V
    .locals 1

    if-nez p1, :cond_0

    invoke-static {}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$100()Ljava/lang/String;

    move-result-object p1

    const-string v0, "the request download success!"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-static {p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$200(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)V

    goto :goto_0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$100()Ljava/lang/String;

    move-result-object p1

    const-string v0, "the request download fail!"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    new-instance p1, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;-><init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;)V

    invoke-static {p1}, Lcom/miui/gallery/threadpool/ThreadManager;->runOnMainThread(Ljava/lang/Runnable;)V

    :goto_0
    return-void
.end method

.method public onProgressUpdate(I)V
    .locals 0

    return-void
.end method

.method public onStart()V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$100()Ljava/lang/String;

    move-result-object v0

    const-string v1, "the request download start! "

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
