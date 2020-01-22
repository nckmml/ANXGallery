.class Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;
.super Ljava/lang/Object;
.source "ResourceDownloadManager.java"

# interfaces
.implements Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;


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

    iput-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onFail()V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$100()Ljava/lang/String;

    move-result-object v0

    const-string v1, "template install fail!"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$300(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$400(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$300(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$500(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V

    return-void
.end method

.method public onSuccess()V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$100()Ljava/lang/String;

    move-result-object v0

    const-string v1, "template install success!"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$300(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$800(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$300(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$500(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V

    return-void
.end method
