.class Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3$1;
.super Ljava/lang/Object;
.source "MiShareGalleryTransferView.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;->onTaskIdChanged(Lcom/miui/mishare/MiShareTask;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;

.field final synthetic val$task:Lcom/miui/mishare/MiShareTask;


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;Lcom/miui/mishare/MiShareTask;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;

    iput-object p2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3$1;->val$task:Lcom/miui/mishare/MiShareTask;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3$1;->val$task:Lcom/miui/mishare/MiShareTask;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->replaceTaskId(Lcom/miui/mishare/MiShareTask;)V

    return-void
.end method
