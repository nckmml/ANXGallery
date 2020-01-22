.class Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;
.super Landroid/content/BroadcastReceiver;
.source "DownloadObserver.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cloud/download/DownloadObserver;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "NetworkReceiver"
.end annotation


# instance fields
.field private mIsNetConnected:Z

.field private mIsWifiConnected:Z

.field final synthetic this$0:Lcom/miui/gallery/cloud/download/DownloadObserver;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/cloud/download/DownloadObserver;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->this$0:Lcom/miui/gallery/cloud/download/DownloadObserver;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isNetworkConnected()Z

    move-result p1

    iput-boolean p1, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->mIsNetConnected:Z

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->mIsWifiConnected:Z

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 6

    const/4 v0, 0x0

    const-string v1, "noConnectivity"

    invoke-virtual {p2, v1, v0}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result p2

    const/4 v1, 0x1

    xor-int/2addr p2, v1

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->mIsNetConnected:Z

    const-string v3, "DownloadObserver"

    if-eq v2, p2, :cond_0

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v4

    const-string v5, "NetworkReceiver mLastConnect: %s, netConnect: %s"

    invoke-static {v3, v5, v2, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iput-boolean p2, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->mIsNetConnected:Z

    move p2, v1

    goto :goto_0

    :cond_0
    move p2, v0

    :goto_0
    iget-boolean v2, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->mIsNetConnected:Z

    if-eqz v2, :cond_1

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v2

    if-nez v2, :cond_1

    move v0, v1

    :cond_1
    iget-boolean v2, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->mIsWifiConnected:Z

    if-eq v2, v0, :cond_2

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p2

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    const-string v4, "NetworkReceiver mLastWifiConnect: %s, wifiConnect: %s"

    invoke-static {v3, v4, p2, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iput-boolean v0, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->mIsWifiConnected:Z

    move p2, v1

    :cond_2
    if-eqz p2, :cond_3

    iget-object p2, p0, Lcom/miui/gallery/cloud/download/DownloadObserver$NetworkReceiver;->this$0:Lcom/miui/gallery/cloud/download/DownloadObserver;

    invoke-static {p2, p1}, Lcom/miui/gallery/cloud/download/DownloadObserver;->access$600(Lcom/miui/gallery/cloud/download/DownloadObserver;Landroid/content/Context;)V

    :cond_3
    return-void
.end method
