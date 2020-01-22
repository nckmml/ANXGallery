.class Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;
.super Ljava/lang/Object;
.source "ResourceDownloadManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;->onComplete(I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;->this$1:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;->this$1:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;->this$1:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;

    iget-object v1, v1, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-static {v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$300(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$400(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;->this$1:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2$1;->this$1:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;

    iget-object v1, v1, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-static {v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$300(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$500(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V

    return-void
.end method
