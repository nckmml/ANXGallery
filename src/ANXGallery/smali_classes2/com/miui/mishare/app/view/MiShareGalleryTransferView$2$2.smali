.class Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;
.super Ljava/lang/Object;
.source "MiShareGalleryTransferView.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->onDeviceUpdated(Lcom/miui/mishare/RemoteDevice;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

.field final synthetic val$remoteDevice:Lcom/miui/mishare/RemoteDevice;


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;Lcom/miui/mishare/RemoteDevice;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iput-object p2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->val$remoteDevice:Lcom/miui/mishare/RemoteDevice;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 7

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->val$remoteDevice:Lcom/miui/mishare/RemoteDevice;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/mishare/RemoteDevice;->getExtras()Landroid/os/Bundle;

    move-result-object v0

    if-nez v0, :cond_0

    goto/16 :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$600(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroid/view/View;

    move-result-object v0

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->val$remoteDevice:Lcom/miui/mishare/RemoteDevice;

    invoke-virtual {v0}, Lcom/miui/mishare/RemoteDevice;->getExtras()Landroid/os/Bundle;

    move-result-object v0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/os/Bundle;->setClassLoader(Ljava/lang/ClassLoader;)V

    const-string v2, "sgnt"

    invoke-virtual {v0, v2}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v2

    new-instance v3, Lcom/miui/mishare/app/model/MiShareDevice;

    invoke-direct {v3, v2}, Lcom/miui/mishare/app/model/MiShareDevice;-><init>(I)V

    iget-object v2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v2, v2, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$700(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Ljava/util/List;

    move-result-object v2

    iput-object v2, v3, Lcom/miui/mishare/app/model/MiShareDevice;->files:Ljava/util/List;

    iget-object v2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->val$remoteDevice:Lcom/miui/mishare/RemoteDevice;

    iput-object v2, v3, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    invoke-virtual {v2}, Lcom/miui/mishare/RemoteDevice;->getDeviceId()Ljava/lang/String;

    move-result-object v2

    iput-object v2, v3, Lcom/miui/mishare/app/model/MiShareDevice;->deviceId:Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v2, v2, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {v2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v2

    const-string v4, "mc"

    invoke-virtual {v0, v4}, Landroid/os/Bundle;->getByte(Ljava/lang/String;)B

    move-result v5

    const-string v6, "dc"

    invoke-virtual {v0, v6}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v6

    invoke-static {v2, v5, v6}, Lcom/miui/mishare/DeviceModel;->getDeviceName(Landroid/content/Context;BI)Ljava/lang/String;

    move-result-object v2

    iput-object v2, v3, Lcom/miui/mishare/app/model/MiShareDevice;->deviceModelName:Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v2, v2, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {v0, v4}, Landroid/os/Bundle;->getByte(Ljava/lang/String;)B

    move-result v4

    invoke-static {v2, v4}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$800(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;B)I

    move-result v2

    iput v2, v3, Lcom/miui/mishare/app/model/MiShareDevice;->deviceType:I

    const-string v2, "nickname"

    invoke-virtual {v0, v2}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v3, Lcom/miui/mishare/app/model/MiShareDevice;->deviceName:Ljava/lang/String;

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$900(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$1000(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroidx/recyclerview/widget/RecyclerView;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    move-result-object v0

    invoke-virtual {v0, v3}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->addOrUpdateDevice(Lcom/miui/mishare/app/model/MiShareDevice;)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$2;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->notifyDataSetChanged()V

    :cond_1
    :goto_0
    return-void
.end method
