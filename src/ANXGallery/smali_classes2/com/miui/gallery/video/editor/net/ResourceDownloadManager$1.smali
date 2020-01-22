.class Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;
.super Ljava/lang/Object;
.source "ResourceDownloadManager.java"

# interfaces
.implements Lcom/miui/gallery/ui/ConfirmDialog$ConfirmDialogInterface;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->createDownloadCommand(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

.field final synthetic val$data:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

.field final synthetic val$position:I


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;->val$data:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    iput p3, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;->val$position:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCancel(Landroid/app/DialogFragment;)V
    .locals 0

    invoke-virtual {p1}, Landroid/app/DialogFragment;->dismiss()V

    return-void
.end method

.method public onConfirm(Landroid/app/DialogFragment;)V
    .locals 2

    invoke-virtual {p1}, Landroid/app/DialogFragment;->dismiss()V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;->this$0:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;->val$data:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    iget v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;->val$position:I

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->access$000(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    return-void
.end method
