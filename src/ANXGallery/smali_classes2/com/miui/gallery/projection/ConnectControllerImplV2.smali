.class public Lcom/miui/gallery/projection/ConnectControllerImplV2;
.super Lcom/miui/gallery/projection/ConnectController;
.source "ConnectControllerImplV2.java"

# interfaces
.implements Lcom/milink/api/v1/MiLinkClientScanListCallback;


# instance fields
.field private mChooseTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;-><init>()V

    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mChooseTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

    return-void
.end method


# virtual methods
.method protected chooseDevice()Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    const/4 v1, 0x2

    invoke-virtual {v0, p0, v1}, Lcom/milink/api/v1/MilinkClientManager;->showScanList(Lcom/milink/api/v1/MiLinkClientScanListCallback;I)Lcom/milink/api/v1/type/ReturnCode;

    move-result-object v0

    sget-object v1, Lcom/milink/api/v1/type/ReturnCode;->OK:Lcom/milink/api/v1/type/ReturnCode;

    const/4 v2, 0x1

    if-ne v0, v1, :cond_0

    return v2

    :cond_0
    sget-object v1, Lcom/milink/api/v1/type/ReturnCode;->NotConnected:Lcom/milink/api/v1/type/ReturnCode;

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mChooseTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    :cond_1
    const/4 v0, 0x0

    return v0
.end method

.method protected doOnDeviceFound(Ljava/lang/String;Ljava/lang/String;Lcom/milink/api/v1/type/DeviceType;)V
    .locals 0

    return-void
.end method

.method protected doOnDeviceLost(Ljava/lang/String;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mConnectedDevice:Ljava/util/Map$Entry;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mConnectedDevice:Ljava/util/Map$Entry;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    if-ne v0, p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mConnectedDevice:Ljava/util/Map$Entry;

    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p1

    const-string v0, "project_ConnectControllerV2"

    const-string v1, "onDeviceRemoved %s"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object p1

    const v0, 0x7f1006d8

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectControllerImplV2;->closeService()V

    :cond_0
    return-void
.end method

.method protected doOnOpen()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mChooseTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV2;->mChooseTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectControllerImplV2;->chooseDevice()Z

    :cond_0
    return-void
.end method

.method public onConnectFail(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    return-void
.end method

.method public onConnectSuccess(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    return-void
.end method

.method public onSelectDevice(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-virtual {p0, p2, p1}, Lcom/miui/gallery/projection/ConnectControllerImplV2;->setToConnectDevice(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
