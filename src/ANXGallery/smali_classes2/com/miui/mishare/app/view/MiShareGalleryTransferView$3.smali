.class Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;
.super Lcom/miui/mishare/IMiShareTaskStateListener$Stub;
.source "MiShareGalleryTransferView.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/mishare/app/view/MiShareGalleryTransferView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-direct {p0}, Lcom/miui/mishare/IMiShareTaskStateListener$Stub;-><init>()V

    return-void
.end method


# virtual methods
.method public onTaskIdChanged(Lcom/miui/mishare/MiShareTask;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$500(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3$1;

    invoke-direct {v1, p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3$1;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;Lcom/miui/mishare/MiShareTask;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onTaskStateChanged(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    return-void
.end method
