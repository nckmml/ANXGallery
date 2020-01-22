.class public Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;
.super Lcom/miui/gallery/adapter/itemmodel/base/BaseViewHolder;
.source "CloudAlbumItemModel.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "CloudAlbumVH"
.end annotation


# instance fields
.field public mCbBackup:Lmiui/widget/SlidingButton;

.field private mIvAlbumPhoto:Landroid/widget/ImageView;

.field private mTvAlbumPhotoNumber:Landroid/widget/TextView;

.field private mTvAlbumTitle:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/adapter/itemmodel/base/BaseViewHolder;-><init>(Landroid/view/View;)V

    const p1, 0x7f090380

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mTvAlbumTitle:Landroid/widget/TextView;

    const p1, 0x7f090381

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mTvAlbumPhotoNumber:Landroid/widget/TextView;

    const p1, 0x7f090096

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lmiui/widget/SlidingButton;

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mCbBackup:Lmiui/widget/SlidingButton;

    const p1, 0x7f0901c3

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mIvAlbumPhoto:Landroid/widget/ImageView;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mTvAlbumTitle:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mTvAlbumPhotoNumber:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;)Landroid/widget/ImageView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mIvAlbumPhoto:Landroid/widget/ImageView;

    return-object p0
.end method
