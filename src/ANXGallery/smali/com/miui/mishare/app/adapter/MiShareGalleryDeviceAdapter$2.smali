.class Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;
.super Ljava/lang/Object;
.source "MiShareGalleryDeviceAdapter.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->showCancelConfirm(Lcom/miui/mishare/app/model/MiShareDevice;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

.field final synthetic val$device:Lcom/miui/mishare/app/model/MiShareDevice;


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    iput-object p2, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;->val$device:Lcom/miui/mishare/app/model/MiShareDevice;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 0

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;->val$device:Lcom/miui/mishare/app/model/MiShareDevice;

    const/4 p2, 0x1

    iput p2, p1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->notifyDataSetChanged()V

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$500(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$500(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;->val$device:Lcom/miui/mishare/app/model/MiShareDevice;

    invoke-interface {p1, p2}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;->onCancelTask(Lcom/miui/mishare/app/model/MiShareDevice;)V

    :cond_0
    return-void
.end method
