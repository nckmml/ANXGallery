.class Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;
.super Ljava/lang/Object;
.source "MiShareGalleryTransferView.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->onDeviceLost(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

.field final synthetic val$deviceId:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iput-object p2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;->val$deviceId:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;->val$deviceId:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->removeDevice(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->notifyDataSetChanged()V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->devicesEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$400(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    :cond_0
    return-void
.end method
