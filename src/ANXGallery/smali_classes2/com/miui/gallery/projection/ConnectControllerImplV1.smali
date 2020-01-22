.class public Lcom/miui/gallery/projection/ConnectControllerImplV1;
.super Lcom/miui/gallery/projection/ConnectController;
.source "ConnectControllerImplV1.java"

# interfaces
.implements Lcom/miui/gallery/projection/DeviceListController$OnItemClickListener;


# instance fields
.field private mFoundDevices:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mWidget:Lcom/miui/gallery/projection/DeviceListController;


# direct methods
.method protected constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;-><init>()V

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mFoundDevices:Ljava/util/HashMap;

    new-instance v0, Lcom/miui/gallery/projection/DeviceListController;

    invoke-direct {v0}, Lcom/miui/gallery/projection/DeviceListController;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/projection/DeviceListController;->setOnItemClickListener(Lcom/miui/gallery/projection/DeviceListController$OnItemClickListener;)V

    return-void
.end method


# virtual methods
.method public attachActivity(Landroid/app/Activity;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    if-eqz v0, :cond_0

    new-instance v1, Ljava/lang/ref/WeakReference;

    invoke-direct {v1, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/DeviceListController;->attachActivity(Ljava/lang/ref/WeakReference;)V

    :cond_0
    return-void
.end method

.method protected chooseDevice()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    invoke-virtual {v0}, Lcom/miui/gallery/projection/DeviceListController;->show()V

    const/4 v0, 0x1

    return v0
.end method

.method public detachActivity(Landroid/app/Activity;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    if-eqz v0, :cond_0

    new-instance v1, Ljava/lang/ref/WeakReference;

    invoke-direct {v1, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/DeviceListController;->detachActivity(Ljava/lang/ref/WeakReference;)V

    :cond_0
    return-void
.end method

.method public doOnConnectedFail()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/projection/ConnectController;->doOnConnectedFail()V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    invoke-virtual {v0}, Lcom/miui/gallery/projection/DeviceListController;->removeActive()V

    return-void
.end method

.method protected doOnDeviceFound(Ljava/lang/String;Ljava/lang/String;Lcom/milink/api/v1/type/DeviceType;)V
    .locals 1

    sget-object v0, Lcom/milink/api/v1/type/DeviceType;->TV:Lcom/milink/api/v1/type/DeviceType;

    if-eq p3, v0, :cond_0

    return-void

    :cond_0
    iget-object p3, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mFoundDevices:Ljava/util/HashMap;

    invoke-virtual {p3, p2, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "project_ConnectControllerV1"

    const-string p3, "onDevicesAdded %s"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectControllerImplV1;->getConnectedDevice()Ljava/lang/String;

    move-result-object p3

    invoke-static {p2, p3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p3

    invoke-virtual {p1, p2, p3}, Lcom/miui/gallery/projection/DeviceListController;->addNewDevice(Ljava/lang/String;Z)V

    return-void
.end method

.method protected doOnDeviceLost(Ljava/lang/String;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mFoundDevices:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mFoundDevices:Ljava/util/HashMap;

    invoke-virtual {v2, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    :goto_0
    if-eqz v1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mFoundDevices:Ljava/util/HashMap;

    invoke-virtual {p1, v1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "project_ConnectControllerV1"

    const-string v0, "onDeviceRemoved %s"

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/projection/DeviceListController;->removeDevice(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object p1

    const v0, 0x7f1006d8

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectControllerImplV1;->closeService()V

    :cond_2
    return-void
.end method

.method protected doOnOpen()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mFoundDevices:Ljava/util/HashMap;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/util/HashMap;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Lcom/miui/gallery/projection/DeviceListController;->addNewDevice(Ljava/lang/String;Z)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public onItemClicked(Ljava/lang/String;)V
    .locals 2

    const-string v0, "project_ConnectControllerV1"

    const-string v1, "device %s selected"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mFoundDevices:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/projection/ConnectControllerImplV1;->setToConnectDevice(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectControllerImplV1;->mWidget:Lcom/miui/gallery/projection/DeviceListController;

    invoke-virtual {p1}, Lcom/miui/gallery/projection/DeviceListController;->dismiss()V

    return-void
.end method
