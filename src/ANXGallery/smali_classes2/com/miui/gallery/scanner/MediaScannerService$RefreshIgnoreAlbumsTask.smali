.class Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;
.super Lcom/miui/gallery/scanner/ScanTask;
.source "MediaScannerService.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/scanner/MediaScannerService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "RefreshIgnoreAlbumsTask"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/scanner/MediaScannerService;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/scanner/MediaScannerService;ZI)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;->this$0:Lcom/miui/gallery/scanner/MediaScannerService;

    invoke-direct {p0, p3, p2}, Lcom/miui/gallery/scanner/ScanTask;-><init>(IZ)V

    invoke-virtual {p0}, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "MediaScannerService"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method protected canRunningInBackground()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public bridge synthetic run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;->run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;

    move-result-object p1

    return-object p1
.end method

.method public run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;->this$0:Lcom/miui/gallery/scanner/MediaScannerService;

    invoke-static {v0, p1}, Lcom/miui/gallery/scanner/MediaScanner;->refreshIgnoreList(Landroid/content/Context;Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)V

    const/4 p1, 0x0

    return-object p1
.end method

.method public toString()Ljava/lang/String;
    .locals 4

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p0}, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;->getPriority()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const/4 v3, 0x0

    aput-object v2, v1, v3

    invoke-virtual {p0}, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;->isForeground()Z

    move-result v2

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    const/4 v3, 0x1

    aput-object v2, v1, v3

    const-string v2, "RefreshIgnoreAlbumsTask priority [%d], foreground [%s]"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
