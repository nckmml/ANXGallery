.class Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;
.super Ljava/lang/Object;
.source "MiShareGalleryTransferView.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;->onStateChanged(I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;

.field final synthetic val$newState:I


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;I)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;

    iput p2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;->val$newState:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;

    iget-object v1, v1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-static {v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$100(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)I

    move-result v1

    iget v2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;->val$newState:I

    invoke-static {v0, v1, v2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$200(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;II)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;->this$1:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;

    iget-object v0, v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;->this$0:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    iget v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1$1;->val$newState:I

    invoke-static {v0, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->access$102(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;I)I

    return-void
.end method
