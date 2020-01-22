.class public Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;
.super Lcom/miui/gallery/adapter/itemmodel/base/BaseViewHolder;
.source "HiddenAlbumItemModel.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "HiddenAlbumVH"
.end annotation


# instance fields
.field public mBtnCancelHidden:Landroid/widget/Button;

.field public mIvAlbumPhoto:Lcom/miui/gallery/widget/ForegroundImageView;

.field private mTvAlbumPhotoNumber:Landroid/widget/TextView;

.field private mTvAlbumTitle:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 1

    invoke-direct {p0, p1}, Lcom/miui/gallery/adapter/itemmodel/base/BaseViewHolder;-><init>(Landroid/view/View;)V

    const v0, 0x7f090380

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mTvAlbumTitle:Landroid/widget/TextView;

    const v0, 0x7f090381

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mTvAlbumPhotoNumber:Landroid/widget/TextView;

    const v0, 0x7f090089

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mBtnCancelHidden:Landroid/widget/Button;

    const v0, 0x7f0901c3

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/widget/ForegroundImageView;

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mIvAlbumPhoto:Lcom/miui/gallery/widget/ForegroundImageView;

    iget-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mIvAlbumPhoto:Lcom/miui/gallery/widget/ForegroundImageView;

    const v0, 0x7f07006a

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/ForegroundImageView;->setForegroundResource(I)V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mTvAlbumTitle:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;->mTvAlbumPhotoNumber:Landroid/widget/TextView;

    return-object p0
.end method
