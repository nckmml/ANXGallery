.class Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "StickerHolder.java"


# instance fields
.field private mOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

.field private mSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;

.field private mView:Landroid/widget/ImageView;


# direct methods
.method constructor <init>(Landroid/view/View;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/assist/ImageSize;)V
    .locals 1

    invoke-direct {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    const v0, 0x7f0901a8

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;->mView:Landroid/widget/ImageView;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;->mOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    iput-object p3, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;->mSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    return-void
.end method


# virtual methods
.method bind(Lcom/miui/gallery/editor/photo/core/common/model/StickerData;I)V
    .locals 6

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "file://"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p1, Lcom/miui/gallery/editor/photo/core/common/model/StickerData;->icon:Ljava/lang/String;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;->mView:Landroid/widget/ImageView;

    iget-object v3, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;->mOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    iget-object v4, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;->mSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    const/4 v5, 0x0

    invoke-virtual/range {v0 .. v5}, Lcom/nostra13/universalimageloader/core/ImageLoader;->displayImage(Ljava/lang/String;Landroid/widget/ImageView;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/assist/ImageSize;Landroid/graphics/RectF;)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerHolder;->mView:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const/4 v1, 0x1

    new-array v2, v1, [Ljava/lang/Object;

    add-int/2addr p2, v1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    const/4 v1, 0x0

    aput-object p2, v2, v1

    const p2, 0x7f10067f

    invoke-virtual {v0, p2, v2}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setContentDescription(Ljava/lang/CharSequence;)V

    return-void
.end method
