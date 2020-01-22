.class Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;
.super Ljava/lang/Object;
.source "AddToAlbumDialogFragment.java"

# interfaces
.implements Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/AddToAlbumDialogFragment;->showCopyOrMoveDialog(J)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

.field final synthetic val$id:J


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/AddToAlbumDialogFragment;J)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

    iput-wide p2, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->val$id:J

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onOperationSelected(I)V
    .locals 3

    const/4 v0, 0x1

    if-eq p1, v0, :cond_1

    const/4 v1, 0x2

    if-eq p1, v1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment;->access$700(Lcom/miui/gallery/ui/AddToAlbumDialogFragment;)Lcom/miui/gallery/ui/AddToAlbumDialogFragment$OnAlbumSelectedListener;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment;->access$700(Lcom/miui/gallery/ui/AddToAlbumDialogFragment;)Lcom/miui/gallery/ui/AddToAlbumDialogFragment$OnAlbumSelectedListener;

    move-result-object p1

    iget-wide v1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->val$id:J

    invoke-interface {p1, v1, v2, v0}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$OnAlbumSelectedListener;->onAlbumSelected(JZ)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment;->access$800(Lcom/miui/gallery/ui/AddToAlbumDialogFragment;Z)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment;->access$700(Lcom/miui/gallery/ui/AddToAlbumDialogFragment;)Lcom/miui/gallery/ui/AddToAlbumDialogFragment$OnAlbumSelectedListener;

    move-result-object p1

    const/4 v0, 0x0

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment;->access$700(Lcom/miui/gallery/ui/AddToAlbumDialogFragment;)Lcom/miui/gallery/ui/AddToAlbumDialogFragment$OnAlbumSelectedListener;

    move-result-object p1

    iget-wide v1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->val$id:J

    invoke-interface {p1, v1, v2, v0}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$OnAlbumSelectedListener;->onAlbumSelected(JZ)V

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/ui/AddToAlbumDialogFragment$2;->this$0:Lcom/miui/gallery/ui/AddToAlbumDialogFragment;

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/AddToAlbumDialogFragment;->access$800(Lcom/miui/gallery/ui/AddToAlbumDialogFragment;Z)V

    :cond_3
    :goto_0
    return-void
.end method
