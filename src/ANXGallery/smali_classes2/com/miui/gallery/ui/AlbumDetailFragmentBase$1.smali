.class Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;
.super Ljava/lang/Object;
.source "AlbumDetailFragmentBase.java"

# interfaces
.implements Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/AlbumDetailFragmentBase;->doAddPhotos([J)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

.field final synthetic val$ids:[J


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/AlbumDetailFragmentBase;[J)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

    iput-object p2, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->val$ids:[J

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onOperationSelected(I)V
    .locals 8

    const/4 v0, 0x1

    if-eq p1, v0, :cond_0

    const/4 v1, 0x2

    if-eq p1, v1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

    iget-object v1, p1, Lcom/miui/gallery/ui/AlbumDetailFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

    iget-wide v2, p1, Lcom/miui/gallery/ui/AlbumDetailFragmentBase;->mAlbumId:J

    iget-object v4, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->val$ids:[J

    const/4 v5, 0x0

    const/4 v6, 0x1

    const/4 v7, 0x0

    invoke-static/range {v1 .. v7}, Lcom/miui/gallery/ui/CopyMoveDialogFragment;->show(Landroid/app/Activity;J[JIZLcom/miui/gallery/util/MediaAndAlbumOperations$OnAddAlbumListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/AlbumDetailFragmentBase;->access$100(Lcom/miui/gallery/ui/AlbumDetailFragmentBase;Z)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

    iget-object v0, p1, Lcom/miui/gallery/ui/AlbumDetailFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

    iget-wide v1, p1, Lcom/miui/gallery/ui/AlbumDetailFragmentBase;->mAlbumId:J

    iget-object v3, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->val$ids:[J

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/ui/CopyMoveDialogFragment;->show(Landroid/app/Activity;J[JIZLcom/miui/gallery/util/MediaAndAlbumOperations$OnAddAlbumListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumDetailFragmentBase$1;->this$0:Lcom/miui/gallery/ui/AlbumDetailFragmentBase;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/AlbumDetailFragmentBase;->access$100(Lcom/miui/gallery/ui/AlbumDetailFragmentBase;Z)V

    :cond_1
    :goto_0
    return-void
.end method
