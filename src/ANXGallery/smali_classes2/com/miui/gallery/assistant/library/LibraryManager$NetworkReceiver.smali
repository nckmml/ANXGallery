.class Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;
.super Landroid/content/BroadcastReceiver;
.source "LibraryManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/assistant/library/LibraryManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "NetworkReceiver"
.end annotation


# instance fields
.field private mIsNetConnected:Z

.field private mIsWifiConnected:Z

.field final synthetic this$0:Lcom/miui/gallery/assistant/library/LibraryManager;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/assistant/library/LibraryManager;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->this$0:Lcom/miui/gallery/assistant/library/LibraryManager;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isNetworkConnected()Z

    move-result p1

    iput-boolean p1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsNetConnected:Z

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsWifiConnected:Z

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 5

    const/4 p1, 0x0

    const-string v0, "noConnectivity"

    invoke-virtual {p2, v0, p1}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result p2

    const/4 v0, 0x1

    xor-int/2addr p2, v0

    iget-boolean v1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsNetConnected:Z

    if-eq v1, p2, :cond_0

    invoke-static {}, Lcom/miui/gallery/assistant/library/LibraryManager;->access$800()Ljava/lang/String;

    move-result-object v1

    iget-boolean v2, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsNetConnected:Z

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    const-string v4, "NetworkReceiver lastConnect: %s, netConnect: %s"

    invoke-static {v1, v4, v2, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iput-boolean p2, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsNetConnected:Z

    move p2, v0

    goto :goto_0

    :cond_0
    move p2, p1

    :goto_0
    iget-boolean v1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsNetConnected:Z

    if-eqz v1, :cond_1

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v1

    if-nez v1, :cond_1

    move p1, v0

    :cond_1
    iget-boolean v1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsWifiConnected:Z

    if-eq v1, p1, :cond_2

    invoke-static {}, Lcom/miui/gallery/assistant/library/LibraryManager;->access$800()Ljava/lang/String;

    move-result-object p2

    iget-boolean v1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsWifiConnected:Z

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    const-string v3, "NetworkReceiver lastWifiConnect: %s, wifiConnect: %s"

    invoke-static {p2, v3, v1, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iput-boolean p1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsWifiConnected:Z

    move p2, v0

    :cond_2
    if-eqz p2, :cond_3

    iget-boolean p1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->mIsWifiConnected:Z

    if-eqz p1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->this$0:Lcom/miui/gallery/assistant/library/LibraryManager;

    invoke-static {p1}, Lcom/miui/gallery/assistant/library/LibraryManager;->access$600(Lcom/miui/gallery/assistant/library/LibraryManager;)Z

    move-result p1

    if-eqz p1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/assistant/library/LibraryManager$NetworkReceiver;->this$0:Lcom/miui/gallery/assistant/library/LibraryManager;

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/miui/gallery/assistant/library/LibraryManager;->access$1200(Lcom/miui/gallery/assistant/library/LibraryManager;Landroid/content/Context;)V

    :cond_3
    return-void
.end method
