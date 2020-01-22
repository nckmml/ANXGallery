.class public Lcom/miui/gallery/movie/ui/adapter/EditAdapter;
.super Lcom/miui/gallery/movie/ui/adapter/BaseAdapter;
.source "EditAdapter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/movie/ui/adapter/EditAdapter$OnActionListener;,
        Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/movie/ui/adapter/BaseAdapter<",
        "Lcom/miui/gallery/movie/entity/ImageEntity;",
        ">;"
    }
.end annotation


# static fields
.field private static final ITEM_ADD:Lcom/miui/gallery/movie/entity/ImageEntity;


# instance fields
.field private mCallback:Landroidx/recyclerview/widget/ItemTouchHelper$Callback;

.field private final mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

.field private mOnActionListener:Lcom/miui/gallery/movie/ui/adapter/EditAdapter$OnActionListener;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/miui/gallery/movie/entity/ImageEntity;

    const-string v1, "ITEM_ADD"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/movie/entity/ImageEntity;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    sput-object v0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->ITEM_ADD:Lcom/miui/gallery/movie/entity/ImageEntity;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/ui/adapter/BaseAdapter;-><init>(Landroid/content/Context;)V

    new-instance p1, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$1;-><init>(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;)V

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mCallback:Landroidx/recyclerview/widget/ItemTouchHelper$Callback;

    const/4 p1, -0x1

    iput p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mSelectedItemPosition:I

    new-instance p1, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    invoke-direct {p1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;-><init>()V

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->considerExifParams(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    sget-object v1, Landroid/graphics/Bitmap$Config;->RGB_565:Landroid/graphics/Bitmap$Config;

    invoke-virtual {p1, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->bitmapConfig(Landroid/graphics/Bitmap$Config;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    sget-object v1, Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;->EXACTLY:Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;

    invoke-virtual {p1, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->imageScaleType(Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    invoke-virtual {p1, v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->resetViewBeforeLoading(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    invoke-virtual {p1, v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->cacheInMemory(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->build()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;Landroid/view/View;Z)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->doScaleAnimal(Landroid/view/View;Z)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;)Lcom/miui/gallery/movie/ui/adapter/EditAdapter$OnActionListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mOnActionListener:Lcom/miui/gallery/movie/ui/adapter/EditAdapter$OnActionListener;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;II)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->swapSelectItem(II)V

    return-void
.end method

.method private doScaleAnimal(Landroid/view/View;Z)V
    .locals 10

    new-instance v0, Landroid/animation/ObjectAnimator;

    invoke-direct {v0}, Landroid/animation/ObjectAnimator;-><init>()V

    sget-object v1, Landroid/view/View;->SCALE_X:Landroid/util/Property;

    const/4 v2, 0x2

    new-array v3, v2, [F

    invoke-virtual {p1}, Landroid/view/View;->getScaleX()F

    move-result v4

    const/4 v5, 0x0

    aput v4, v3, v5

    const v4, 0x3fab851f    # 1.34f

    const/high16 v6, 0x3f800000    # 1.0f

    if-eqz p2, :cond_0

    move v7, v4

    goto :goto_0

    :cond_0
    move v7, v6

    :goto_0
    const/4 v8, 0x1

    aput v7, v3, v8

    invoke-static {v1, v3}, Landroid/animation/PropertyValuesHolder;->ofFloat(Landroid/util/Property;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v1

    sget-object v3, Landroid/view/View;->SCALE_Y:Landroid/util/Property;

    new-array v7, v2, [F

    invoke-virtual {p1}, Landroid/view/View;->getScaleY()F

    move-result v9

    aput v9, v7, v5

    if-eqz p2, :cond_1

    goto :goto_1

    :cond_1
    move v4, v6

    :goto_1
    aput v4, v7, v8

    invoke-static {v3, v7}, Landroid/animation/PropertyValuesHolder;->ofFloat(Landroid/util/Property;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object p2

    invoke-virtual {v0, p1}, Landroid/animation/ObjectAnimator;->setTarget(Ljava/lang/Object;)V

    new-array p1, v2, [Landroid/animation/PropertyValuesHolder;

    aput-object v1, p1, v5

    aput-object p2, p1, v8

    invoke-virtual {v0, p1}, Landroid/animation/ObjectAnimator;->setValues([Landroid/animation/PropertyValuesHolder;)V

    new-instance p1, Lmiui/view/animation/CubicEaseOutInterpolator;

    invoke-direct {p1}, Lmiui/view/animation/CubicEaseOutInterpolator;-><init>()V

    invoke-virtual {v0, p1}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    const-wide/16 p1, 0xc8

    invoke-virtual {v0, p1, p2}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->start()V

    return-void
.end method

.method private swapSelectItem(II)V
    .locals 1

    iget v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mSelectedItemPosition:I

    if-ne v0, p1, :cond_0

    iput p2, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mSelectedItemPosition:I

    goto :goto_0

    :cond_0
    iget v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mSelectedItemPosition:I

    if-ne v0, p2, :cond_1

    iput p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mSelectedItemPosition:I

    :cond_1
    :goto_0
    return-void
.end method


# virtual methods
.method public getCallback()Landroidx/recyclerview/widget/ItemTouchHelper$Callback;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mCallback:Landroidx/recyclerview/widget/ItemTouchHelper$Callback;

    return-object v0
.end method

.method protected bridge synthetic getHolder(Landroid/view/View;)Lcom/miui/gallery/movie/ui/adapter/BaseAdapter$BaseHolder;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->getHolder(Landroid/view/View;)Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;

    move-result-object p1

    return-object p1
.end method

.method protected getHolder(Landroid/view/View;)Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;
    .locals 1

    new-instance v0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter$EditHolder;-><init>(Lcom/miui/gallery/movie/ui/adapter/EditAdapter;Landroid/view/View;)V

    return-object v0
.end method

.method public getItemCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mList:Ljava/util/List;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    invoke-super {p0}, Lcom/miui/gallery/movie/ui/adapter/BaseAdapter;->getItemCount()I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public getItemData(I)Lcom/miui/gallery/movie/entity/ImageEntity;
    .locals 1

    if-gez p1, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-lt p1, v0, :cond_1

    sget-object p1, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->ITEM_ADD:Lcom/miui/gallery/movie/entity/ImageEntity;

    return-object p1

    :cond_1
    invoke-super {p0, p1}, Lcom/miui/gallery/movie/ui/adapter/BaseAdapter;->getItemData(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/movie/entity/ImageEntity;

    return-object p1
.end method

.method public bridge synthetic getItemData(I)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->getItemData(I)Lcom/miui/gallery/movie/entity/ImageEntity;

    move-result-object p1

    return-object p1
.end method

.method protected getLayoutId()I
    .locals 1

    sget v0, Lcom/miui/gallery/movie/R$layout;->movie_layout_edit_item:I

    return v0
.end method

.method public getListSize()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mList:Ljava/util/List;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    :goto_0
    return v0
.end method

.method public isAddItem(I)Z
    .locals 1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->getItemData(I)Lcom/miui/gallery/movie/entity/ImageEntity;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p1, Lcom/miui/gallery/movie/entity/ImageEntity;->image:Ljava/lang/String;

    const-string v0, "ITEM_ADD"

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method public setOnActionListener(Lcom/miui/gallery/movie/ui/adapter/EditAdapter$OnActionListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/EditAdapter;->mOnActionListener:Lcom/miui/gallery/movie/ui/adapter/EditAdapter$OnActionListener;

    return-void
.end method
