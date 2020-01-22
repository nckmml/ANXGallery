.class public Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;
.super Lcom/miui/gallery/movie/ui/adapter/BaseAdapter$BaseHolder;
.source "EditAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/movie/ui/adapter/EditAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "EditHolder"
.end annotation


# instance fields
.field protected mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

.field protected mImageView:Landroid/widget/ImageView;

.field protected mImageViewAdd:Landroid/widget/ImageView;

.field final synthetic this$0:Lcom/miui/gallery/movie/ui/adapter/EditAdapter;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;Landroid/view/View;)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->this$0:Lcom/miui/gallery/movie/ui/adapter/EditAdapter;

    invoke-direct {p0, p2}, Lcom/miui/gallery/movie/ui/adapter/BaseAdapter$BaseHolder;-><init>(Landroid/view/View;)V

    sget p1, Lcom/miui/gallery/movie/R$id;->item_iv:I

    invoke-virtual {p2, p1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageView:Landroid/widget/ImageView;

    sget p1, Lcom/miui/gallery/movie/R$id;->item_iv_add:I

    invoke-virtual {p2, p1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageViewAdd:Landroid/widget/ImageView;

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageView:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    sget p2, Lcom/miui/gallery/movie/R$dimen;->photo_movie_edit_edit_item_width:I

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getDimension(I)F

    move-result p1

    float-to-int p1, p1

    new-instance p2, Lcom/nostra13/universalimageloader/core/imageaware/SizeImageViewAware;

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageView:Landroid/widget/ImageView;

    invoke-direct {p2, v0, p1, p1}, Lcom/nostra13/universalimageloader/core/imageaware/SizeImageViewAware;-><init>(Landroid/widget/ImageView;II)V

    iput-object p2, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    return-void
.end method


# virtual methods
.method public bindView(I)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->this$0:Lcom/miui/gallery/movie/ui/adapter/EditAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->isAddItem(I)Z

    move-result v0

    const/16 v1, 0x8

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageView:Landroid/widget/ImageView;

    invoke-virtual {p1, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageViewAdd:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageView:Landroid/widget/ImageView;

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageViewAdd:Landroid/widget/ImageView;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    sget-object v0, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->FILE:Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->this$0:Lcom/miui/gallery/movie/ui/adapter/EditAdapter;

    invoke-virtual {v1, p1}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->getItemData(I)Lcom/miui/gallery/movie/entity/ImageEntity;

    move-result-object p1

    iget-object p1, p1, Lcom/miui/gallery/movie/entity/ImageEntity;->image:Ljava/lang/String;

    invoke-virtual {v0, p1}, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->wrap(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;->this$0:Lcom/miui/gallery/movie/ui/adapter/EditAdapter;

    invoke-static {v2}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->access$000(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object v2

    invoke-virtual {v0, p1, v1, v2}, Lcom/nostra13/universalimageloader/core/ImageLoader;->displayImage(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;)V

    :goto_0
    return-void
.end method
