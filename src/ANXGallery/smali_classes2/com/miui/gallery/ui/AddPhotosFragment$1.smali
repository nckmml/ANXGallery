.class Lcom/miui/gallery/ui/AddPhotosFragment$1;
.super Ljava/lang/Object;
.source "AddPhotosFragment.java"

# interfaces
.implements Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/AddPhotosFragment;->doAddPhotos([J)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/AddPhotosFragment;

.field final synthetic val$ids:[J


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/AddPhotosFragment;[J)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->this$0:Lcom/miui/gallery/ui/AddPhotosFragment;

    iput-object p2, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->val$ids:[J

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onOperationSelected(I)V
    .locals 10

    iget-object v0, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->this$0:Lcom/miui/gallery/ui/AddPhotosFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AddPhotosFragment;->getParentFragment()Landroid/app/Fragment;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    if-eqz v1, :cond_1

    const/4 v1, 0x1

    if-eq p1, v1, :cond_0

    const/4 v2, 0x2

    if-eq p1, v2, :cond_1

    invoke-virtual {v0}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v3

    iget-object p1, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->this$0:Lcom/miui/gallery/ui/AddPhotosFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddPhotosFragment;->access$000(Lcom/miui/gallery/ui/AddPhotosFragment;)J

    move-result-wide v4

    iget-object v6, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->val$ids:[J

    const/4 v7, 0x0

    const/4 v8, 0x1

    const/4 v9, 0x0

    invoke-static/range {v3 .. v9}, Lcom/miui/gallery/ui/CopyMoveDialogFragment;->show(Landroid/app/Activity;J[JIZLcom/miui/gallery/util/MediaAndAlbumOperations$OnAddAlbumListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->this$0:Lcom/miui/gallery/ui/AddPhotosFragment;

    invoke-static {p1, v1}, Lcom/miui/gallery/ui/AddPhotosFragment;->access$100(Lcom/miui/gallery/ui/AddPhotosFragment;Z)V

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    iget-object p1, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->this$0:Lcom/miui/gallery/ui/AddPhotosFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddPhotosFragment;->access$000(Lcom/miui/gallery/ui/AddPhotosFragment;)J

    move-result-wide v3

    iget-object v5, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->val$ids:[J

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    invoke-static/range {v2 .. v8}, Lcom/miui/gallery/ui/CopyMoveDialogFragment;->show(Landroid/app/Activity;J[JIZLcom/miui/gallery/util/MediaAndAlbumOperations$OnAddAlbumListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AddPhotosFragment$1;->this$0:Lcom/miui/gallery/ui/AddPhotosFragment;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/AddPhotosFragment;->access$100(Lcom/miui/gallery/ui/AddPhotosFragment;Z)V

    :cond_1
    :goto_0
    return-void
.end method
