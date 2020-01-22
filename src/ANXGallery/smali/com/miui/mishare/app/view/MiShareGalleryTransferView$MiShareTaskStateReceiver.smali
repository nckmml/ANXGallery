.class final Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;
.super Landroid/content/BroadcastReceiver;
.source "MiShareGalleryTransferView.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/mishare/app/view/MiShareGalleryTransferView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "MiShareTaskStateReceiver"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;


# direct methods
.method private constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 2

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object p1

    const-string v0, "com.miui.mishare.connectivity.TASK_STATE"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const-string p1, "device_id"

    invoke-virtual {p2, p1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x1

    const-string v1, "state"

    invoke-virtual {p2, v1, v0}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result p2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->setDeviceState(Ljava/lang/String;I)V

    :cond_0
    return-void
.end method
