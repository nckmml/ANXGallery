.class Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;
.super Ljava/lang/Object;
.source "CleanerPhotoPickFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/CleanerPhotoPickFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 9

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->getCheckedItemIds()[J

    move-result-object v8

    if-eqz v8, :cond_1

    array-length p1, v8

    if-lez p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->getDeleteMessage()Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;

    move-result-object p1

    if-nez p1, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    new-instance v2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;

    invoke-direct {v2, p0, p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;-><init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;)V

    const-wide/16 v3, -0x1

    invoke-virtual {p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->getDupType()I

    move-result v6

    invoke-virtual {p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->getReason()I

    move-result v7

    const-string v1, "CleanerPhotoPickFragmentDeleteMediaDialogFragment"

    const-string v5, ""

    invoke-static/range {v0 .. v8}, Lcom/miui/gallery/util/MediaAndAlbumOperations;->delete(Landroid/app/Activity;Ljava/lang/String;Lcom/miui/gallery/ui/DeletionTask$OnDeletionCompleteListener;JLjava/lang/String;II[J)V

    :cond_1
    return-void
.end method
