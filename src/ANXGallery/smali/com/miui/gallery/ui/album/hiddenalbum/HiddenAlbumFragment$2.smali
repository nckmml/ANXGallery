.class Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;
.super Lcom/miui/epoxy/eventhook/OnClickEventHook;
.source "HiddenAlbumFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->addEventHook(Ljava/util/List;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/epoxy/eventhook/OnClickEventHook<",
        "Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;Ljava/lang/Class;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    invoke-direct {p0, p2}, Lcom/miui/epoxy/eventhook/OnClickEventHook;-><init>(Ljava/lang/Class;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic onBindMany(Lcom/miui/epoxy/EpoxyViewHolder;)Ljava/util/List;
    .locals 0

    check-cast p1, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;->onBindMany(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;)Ljava/util/List;

    move-result-object p1

    return-object p1
.end method

.method public onBindMany(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;)Ljava/util/List;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;",
            ")",
            "Ljava/util/List<",
            "+",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    const/4 v1, 0x2

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    iget-object v1, p1, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mBtnCancelHidden:Landroid/widget/Button;

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object p1, p1, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mIvAlbumPhoto:Lcom/miui/gallery/widget/ForegroundImageView;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object v0
.end method

.method public bridge synthetic onClick(Landroid/view/View;Lcom/miui/epoxy/EpoxyViewHolder;ILcom/miui/epoxy/EpoxyModel;)V
    .locals 0

    check-cast p2, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;

    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;->onClick(Landroid/view/View;Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;ILcom/miui/epoxy/EpoxyModel;)V

    return-void
.end method

.method public onClick(Landroid/view/View;Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;ILcom/miui/epoxy/EpoxyModel;)V
    .locals 0

    instance-of p2, p4, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;

    if-eqz p2, :cond_1

    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p2

    const p3, 0x7f090089

    if-ne p2, p3, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    check-cast p4, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;

    invoke-static {p1, p4}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->access$000(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    const p2, 0x7f0901c3

    if-ne p1, p2, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object p1

    check-cast p4, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;

    invoke-virtual {p4}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->getItemData()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;

    invoke-virtual {p2}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getSource()Lcom/miui/gallery/model/Album;

    move-result-object p2

    const/16 p3, 0x3e9

    const/4 p4, -0x1

    invoke-static {p1, p2, p3, p4}, Lcom/miui/gallery/util/IntentUtil;->gotoAlbumDetailPage(Landroid/content/Context;Lcom/miui/gallery/model/Album;II)V

    :cond_1
    :goto_0
    return-void
.end method
