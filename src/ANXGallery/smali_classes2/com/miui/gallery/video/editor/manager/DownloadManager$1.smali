.class Lcom/miui/gallery/video/editor/manager/DownloadManager$1;
.super Ljava/lang/Object;
.source "DownloadManager.java"

# interfaces
.implements Lcom/miui/gallery/net/base/ResponseListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/video/editor/manager/DownloadManager;->download(Lcom/miui/gallery/video/editor/DownloadCommand;Lcom/miui/gallery/net/download/Request$Listener;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/manager/DownloadManager;

.field final synthetic val$listener:Lcom/miui/gallery/net/download/Request$Listener;

.field final synthetic val$path:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/manager/DownloadManager;Ljava/lang/String;Lcom/miui/gallery/net/download/Request$Listener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/manager/DownloadManager$1;->this$0:Lcom/miui/gallery/video/editor/manager/DownloadManager;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/manager/DownloadManager$1;->val$path:Ljava/lang/String;

    iput-object p3, p0, Lcom/miui/gallery/video/editor/manager/DownloadManager$1;->val$listener:Lcom/miui/gallery/net/download/Request$Listener;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public varargs onResponse([Ljava/lang/Object;)V
    .locals 4

    const/4 v0, 0x0

    aget-object p1, p1, v0

    check-cast p1, Lcom/miui/gallery/net/resource/DownloadInfo;

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    iget-object v2, p1, Lcom/miui/gallery/net/resource/DownloadInfo;->url:Ljava/lang/String;

    aput-object v2, v1, v0

    iget-object v0, p1, Lcom/miui/gallery/net/resource/DownloadInfo;->sha1:Ljava/lang/String;

    const/4 v2, 0x1

    aput-object v0, v1, v2

    const-string v0, "download %s, %s"

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "DownloadManager"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Ljava/io/File;

    iget-object v2, p0, Lcom/miui/gallery/video/editor/manager/DownloadManager$1;->val$path:Ljava/lang/String;

    invoke-direct {v0, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    const-string v3, "the file already exist and its path is : %s"

    invoke-static {v1, v3, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    :cond_0
    new-instance v1, Lcom/miui/gallery/net/download/Request;

    iget-object v2, p1, Lcom/miui/gallery/net/resource/DownloadInfo;->url:Ljava/lang/String;

    invoke-static {v2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Lcom/miui/gallery/net/download/Request;-><init>(Landroid/net/Uri;Ljava/io/File;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/manager/DownloadManager$1;->this$0:Lcom/miui/gallery/video/editor/manager/DownloadManager;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/manager/DownloadManager;->access$000(Lcom/miui/gallery/video/editor/manager/DownloadManager;)Ljava/util/List;

    move-result-object v0

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v0, Lcom/miui/gallery/net/download/Verifier$Sha1;

    iget-object p1, p1, Lcom/miui/gallery/net/resource/DownloadInfo;->sha1:Ljava/lang/String;

    invoke-direct {v0, p1}, Lcom/miui/gallery/net/download/Verifier$Sha1;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Lcom/miui/gallery/net/download/Request;->setVerifier(Lcom/miui/gallery/net/download/Verifier;)V

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result p1

    invoke-virtual {v1, p1}, Lcom/miui/gallery/net/download/Request;->setAllowedOverMetered(Z)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/manager/DownloadManager$1;->val$listener:Lcom/miui/gallery/net/download/Request$Listener;

    invoke-virtual {v1, p1}, Lcom/miui/gallery/net/download/Request;->setListener(Lcom/miui/gallery/net/download/Request$Listener;)V

    sget-object p1, Lcom/miui/gallery/net/download/GalleryDownloadManager;->INSTANCE:Lcom/miui/gallery/net/download/GalleryDownloadManager;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/net/download/GalleryDownloadManager;->enqueue(Lcom/miui/gallery/net/download/Request;)Z

    return-void
.end method

.method public onResponseError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;)V
    .locals 1

    iget-object p3, p0, Lcom/miui/gallery/video/editor/manager/DownloadManager$1;->val$listener:Lcom/miui/gallery/net/download/Request$Listener;

    const/4 v0, -0x1

    invoke-interface {p3, v0}, Lcom/miui/gallery/net/download/Request$Listener;->onComplete(I)V

    invoke-virtual {p1}, Lcom/miui/gallery/net/base/ErrorCode;->name()Ljava/lang/String;

    move-result-object p1

    const-string p3, "DownloadManager"

    const-string v0, "errorMessage:%s,errorCode.name:5s"

    invoke-static {p3, v0, p2, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method
