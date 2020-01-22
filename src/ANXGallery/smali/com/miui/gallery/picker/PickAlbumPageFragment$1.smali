.class Lcom/miui/gallery/picker/PickAlbumPageFragment$1;
.super Ljava/lang/Object;
.source "PickAlbumPageFragment.java"

# interfaces
.implements Lcom/miui/gallery/widget/recyclerview/ItemClickSupport$OnItemClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/picker/PickAlbumPageFragment;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/picker/PickAlbumPageFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(Landroidx/recyclerview/widget/RecyclerView;Landroid/view/View;IJFF)Z
    .locals 1

    new-instance p1, Landroid/content/Intent;

    invoke-direct {p1}, Landroid/content/Intent;-><init>()V

    invoke-static {p4, p5}, Lcom/miui/gallery/adapter/AlbumAdapterBase;->isFaceAlbum(J)Z

    move-result p2

    const-string p6, "album_name"

    const-string p7, "album_id"

    if-eqz p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mActivity:Landroid/app/Activity;

    const-class v0, Lcom/miui/gallery/picker/PickPeopleActivity;

    invoke-virtual {p1, p2, v0}, Landroid/content/Intent;->setClass(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;

    goto :goto_0

    :cond_0
    invoke-static {p4, p5}, Lcom/miui/gallery/adapter/AlbumAdapterBase;->isRecentAlbum(J)Z

    move-result p2

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mActivity:Landroid/app/Activity;

    const-class v0, Lcom/miui/gallery/picker/PickRecentDiscoveryActivity;

    invoke-virtual {p1, p2, v0}, Landroid/content/Intent;->setClass(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;

    invoke-virtual {p1, p7, p4, p5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    invoke-static {p2}, Lcom/miui/gallery/picker/PickAlbumPageFragment;->access$000(Lcom/miui/gallery/picker/PickAlbumPageFragment;)Lcom/miui/gallery/adapter/AlbumAdapterBase;

    move-result-object p2

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/AlbumAdapterBase;->getAlbumName(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p6, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    goto :goto_0

    :cond_1
    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mActivity:Landroid/app/Activity;

    const-class v0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;

    invoke-virtual {p1, p2, v0}, Landroid/content/Intent;->setClass(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;

    :goto_0
    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {p2}, Lcom/miui/gallery/picker/helper/Picker;->getFilterMimeTypes()[Ljava/lang/String;

    move-result-object p2

    const-string v0, "extra_filter_media_type"

    invoke-virtual {p1, v0, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;[Ljava/lang/String;)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    invoke-static {p2}, Lcom/miui/gallery/picker/PickAlbumPageFragment;->access$000(Lcom/miui/gallery/picker/PickAlbumPageFragment;)Lcom/miui/gallery/adapter/AlbumAdapterBase;

    move-result-object p2

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/AlbumAdapterBase;->isOtherShareAlbum(I)Z

    move-result p2

    const-string v0, "other_share_album"

    invoke-virtual {p1, v0, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    invoke-static {p2}, Lcom/miui/gallery/picker/PickAlbumPageFragment;->access$000(Lcom/miui/gallery/picker/PickAlbumPageFragment;)Lcom/miui/gallery/adapter/AlbumAdapterBase;

    move-result-object p2

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/AlbumAdapterBase;->isOwnerShareAlbum(I)Z

    move-result p2

    const-string v0, "owner_share_album"

    invoke-virtual {p1, v0, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    invoke-static {p2}, Lcom/miui/gallery/picker/PickAlbumPageFragment;->access$000(Lcom/miui/gallery/picker/PickAlbumPageFragment;)Lcom/miui/gallery/adapter/AlbumAdapterBase;

    move-result-object p2

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/AlbumAdapterBase;->isPanoAlbum(I)Z

    move-result p2

    const-string v0, "pano_album"

    invoke-virtual {p1, v0, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    invoke-virtual {p1, p7, p4, p5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    invoke-static {p2}, Lcom/miui/gallery/picker/PickAlbumPageFragment;->access$000(Lcom/miui/gallery/picker/PickAlbumPageFragment;)Lcom/miui/gallery/adapter/AlbumAdapterBase;

    move-result-object p2

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/AlbumAdapterBase;->getAlbumName(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p6, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {p2}, Lcom/miui/gallery/picker/helper/Picker;->getMode()Lcom/miui/gallery/picker/helper/Picker$Mode;

    move-result-object p2

    sget-object p3, Lcom/miui/gallery/picker/helper/Picker$Mode;->UNLIMITED:Lcom/miui/gallery/picker/helper/Picker$Mode;

    if-ne p2, p3, :cond_2

    const/4 p2, -0x1

    goto :goto_1

    :cond_2
    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {p2}, Lcom/miui/gallery/picker/helper/Picker;->capacity()I

    move-result p2

    :goto_1
    const-string p3, "pick-upper-bound"

    invoke-virtual {p1, p3, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {p2}, Lcom/miui/gallery/picker/helper/Picker;->baseline()I

    move-result p2

    const-string p3, "pick-lower-bound"

    invoke-virtual {p1, p3, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {p2}, Lcom/miui/gallery/picker/helper/Picker;->getMediaType()Lcom/miui/gallery/picker/helper/Picker$MediaType;

    move-result-object p2

    invoke-virtual {p2}, Lcom/miui/gallery/picker/helper/Picker$MediaType;->ordinal()I

    move-result p2

    const-string p3, "picker_media_type"

    invoke-virtual {p1, p3, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-static {p2}, Lcom/miui/gallery/picker/PickerActivity;->copyPicker(Lcom/miui/gallery/picker/helper/Picker;)Ljava/util/HashSet;

    move-result-object p2

    const-string p3, "picker_result_set"

    invoke-virtual {p1, p3, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickAlbumPageFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {p2}, Lcom/miui/gallery/picker/helper/Picker;->getResultType()Lcom/miui/gallery/picker/helper/Picker$ResultType;

    move-result-object p2

    invoke-virtual {p2}, Lcom/miui/gallery/picker/helper/Picker$ResultType;->ordinal()I

    move-result p2

    const-string p3, "picker_result_type"

    invoke-virtual {p1, p3, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickAlbumPageFragment$1;->this$0:Lcom/miui/gallery/picker/PickAlbumPageFragment;

    const/4 p3, 0x1

    invoke-virtual {p2, p1, p3}, Lcom/miui/gallery/picker/PickAlbumPageFragment;->startActivityForResult(Landroid/content/Intent;I)V

    return p3
.end method
