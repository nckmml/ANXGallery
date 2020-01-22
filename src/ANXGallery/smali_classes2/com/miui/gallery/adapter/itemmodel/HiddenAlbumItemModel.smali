.class public Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;
.super Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;
.source "HiddenAlbumItemModel.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel<",
        "Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;",
        "Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;",
        ">;"
    }
.end annotation


# direct methods
.method public constructor <init>(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V
    .locals 2

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getId()J

    move-result-wide v0

    invoke-direct {p0, v0, v1, p1}, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;-><init>(JLjava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic bindData(Lcom/miui/epoxy/EpoxyViewHolder;Ljava/util/List;)V
    .locals 0

    check-cast p1, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->bindData(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;Ljava/util/List;)V

    return-void
.end method

.method public bindData(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;Ljava/util/List;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;",
            "Ljava/util/List<",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->getItemData()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;

    iget-object v0, p1, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mIvAlbumPhoto:Lcom/miui/gallery/widget/ForegroundImageView;

    invoke-virtual {p2}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getAlbumName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/ForegroundImageView;->setContentDescription(Ljava/lang/CharSequence;)V

    invoke-static {p1}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->access$000(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;)Landroid/widget/TextView;

    move-result-object v0

    invoke-virtual {p2}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getAlbumName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->setText(Landroid/widget/TextView;Ljava/lang/String;)V

    invoke-static {p1}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->access$100(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;)Landroid/widget/TextView;

    move-result-object v0

    invoke-virtual {p2}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getPhotoNumberText()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->setText(Landroid/widget/TextView;Ljava/lang/String;)V

    iget-object p1, p1, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mIvAlbumPhoto:Lcom/miui/gallery/widget/ForegroundImageView;

    invoke-virtual {p2}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getAlbumCoverPath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getAlbumCover()Landroid/net/Uri;

    move-result-object v1

    invoke-static {}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->getInstance()Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;

    move-result-object v2

    invoke-virtual {p2}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getCoverSize()J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->getHiddenAlbumImageOptions(J)Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object p2

    invoke-virtual {p0, p1, v0, v1, p2}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->bindImage(Landroid/widget/ImageView;Ljava/lang/String;Landroid/net/Uri;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;)V

    return-void
.end method

.method public getLayoutRes()I
    .locals 1

    const v0, 0x7f0b002d

    return v0
.end method

.method public getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
            "Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$1;-><init>(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V

    return-object v0
.end method

.method public isContentTheSame(Lcom/miui/epoxy/EpoxyModel;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)Z"
        }
    .end annotation

    const-class v0, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    check-cast p1, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;

    invoke-virtual {p1}, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->getItemData()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;

    invoke-virtual {p0}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->getItemData()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->equals(Ljava/lang/Object;)Z

    move-result p1

    return p1

    :cond_0
    invoke-super {p0, p1}, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->isContentTheSame(Lcom/miui/epoxy/EpoxyModel;)Z

    move-result p1

    return p1
.end method
