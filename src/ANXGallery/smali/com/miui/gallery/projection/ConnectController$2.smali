.class Lcom/miui/gallery/projection/ConnectController$2;
.super Ljava/lang/Object;
.source "ConnectController.java"

# interfaces
.implements Lcom/milink/api/v1/MilinkClientManagerDelegate;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/projection/ConnectController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/projection/ConnectController;


# direct methods
.method constructor <init>(Lcom/miui/gallery/projection/ConnectController;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClose()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "service closed"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public onConnected()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "service onConnected"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/miui/gallery/projection/ConnectController;->access$400(Lcom/miui/gallery/projection/ConnectController;Z)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-virtual {v0}, Lcom/miui/gallery/projection/ConnectController;->doOnConnected()V

    return-void
.end method

.method public onConnectedFailed(Lcom/milink/api/v1/type/ErrorCode;)V
    .locals 1

    const-string p1, "project_IConnectController"

    const-string v0, "service onConnectedFailed"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/gallery/projection/ConnectController;->access$400(Lcom/miui/gallery/projection/ConnectController;Z)V

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-virtual {p1}, Lcom/miui/gallery/projection/ConnectController;->doOnConnectedFail()V

    return-void
.end method

.method public onDeviceFound(Ljava/lang/String;Ljava/lang/String;Lcom/milink/api/v1/type/DeviceType;)V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "service onDeviceFound"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-virtual {v0, p1, p2, p3}, Lcom/miui/gallery/projection/ConnectController;->doOnDeviceFound(Ljava/lang/String;Ljava/lang/String;Lcom/milink/api/v1/type/DeviceType;)V

    return-void
.end method

.method public onDeviceLost(Ljava/lang/String;)V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "service onDeviceLost"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/projection/ConnectController;->doOnDeviceLost(Ljava/lang/String;)V

    return-void
.end method

.method public onDisconnected()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "service onDisconnected"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/gallery/projection/ConnectController;->access$400(Lcom/miui/gallery/projection/ConnectController;Z)V

    return-void
.end method

.method public onLoading()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "loading..."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;->onLoading()V

    :cond_0
    return-void
.end method

.method public onNextAudio(Z)V
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onRequestNextItem: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v0, "project_IConnectController"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public onOpen()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "service openned"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/miui/gallery/projection/ConnectController;->access$100(Lcom/miui/gallery/projection/ConnectController;Z)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$200(Lcom/miui/gallery/projection/ConnectController;)Ljava/util/concurrent/atomic/AtomicBoolean;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$200(Lcom/miui/gallery/projection/ConnectController;)Ljava/util/concurrent/atomic/AtomicBoolean;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$300(Lcom/miui/gallery/projection/ConnectController;)Z

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-virtual {v0}, Lcom/miui/gallery/projection/ConnectController;->doOnOpen()V

    return-void
.end method

.method public onPaused()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "paused"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;->onPaused()V

    :cond_0
    return-void
.end method

.method public onPlaying()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "playing..."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;->onPlaying()V

    :cond_0
    return-void
.end method

.method public onPrevAudio(Z)V
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onRequestPrevItem: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v0, "project_IConnectController"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public onStopped()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "stopped"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController$2;->this$0:Lcom/miui/gallery/projection/ConnectController;

    invoke-static {v0}, Lcom/miui/gallery/projection/ConnectController;->access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;->onStopped()V

    :cond_0
    return-void
.end method

.method public onVolume(I)V
    .locals 0

    return-void
.end method
