.class public Lcom/miui/gallery/collage/core/poster/PosterPresenter;
.super Lcom/miui/gallery/collage/core/CollagePresenter;
.source "PosterPresenter.java"


# instance fields
.field private mPosterLayoutSparseArray:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/collage/core/layout/LayoutModel;",
            ">;>;"
        }
    .end annotation
.end field

.field private mPosterSparseArray:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/collage/core/poster/PosterModel;",
            ">;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/collage/core/CollagePresenter;-><init>()V

    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterLayoutSparseArray:Landroid/util/SparseArray;

    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterSparseArray:Landroid/util/SparseArray;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/collage/core/poster/PosterPresenter;)Landroid/util/SparseArray;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterLayoutSparseArray:Landroid/util/SparseArray;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/collage/core/poster/PosterPresenter;)Landroid/util/SparseArray;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterSparseArray:Landroid/util/SparseArray;

    return-object p0
.end method

.method private static getPosterLayoutIndex(Lcom/miui/gallery/collage/core/poster/PosterModel;I)I
    .locals 4

    iget-object p0, p0, Lcom/miui/gallery/collage/core/poster/PosterModel;->collageModels:[I

    array-length v0, p0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_1

    aget v2, p0, v1

    div-int/lit8 v3, v2, 0xa

    if-ne v3, p1, :cond_0

    rem-int/lit8 v2, v2, 0xa

    goto :goto_1

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    const/4 v2, -0x1

    :goto_1
    return v2
.end method


# virtual methods
.method protected createEngine(Landroid/content/Context;Lcom/miui/gallery/collage/BitmapManager;)Lcom/miui/gallery/collage/core/RenderEngine;
    .locals 1

    new-instance v0, Lcom/miui/gallery/collage/core/poster/PosterRenderEngine;

    invoke-direct {v0, p1, p2}, Lcom/miui/gallery/collage/core/poster/PosterRenderEngine;-><init>(Landroid/content/Context;Lcom/miui/gallery/collage/BitmapManager;)V

    return-object v0
.end method

.method public getMenuFragmentTag()Ljava/lang/String;
    .locals 1

    const-string v0, "fragment_poster:menu"

    return-object v0
.end method

.method public getPosterCollageLayout(Lcom/miui/gallery/collage/core/poster/PosterModel;)Lcom/miui/gallery/collage/core/layout/LayoutModel;
    .locals 2

    iget v0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mImageCount:I

    invoke-static {p1, v0}, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->getPosterLayoutIndex(Lcom/miui/gallery/collage/core/poster/PosterModel;I)I

    move-result p1

    const/4 v0, -0x1

    if-eq p1, v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterLayoutSparseArray:Landroid/util/SparseArray;

    iget v1, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mImageCount:I

    invoke-virtual {v0, v1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/collage/core/layout/LayoutModel;

    return-object p1

    :cond_0
    const/4 p1, 0x0

    return-object p1
.end method

.method public getPosters()Ljava/util/List;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/miui/gallery/collage/core/poster/PosterModel;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterSparseArray:Landroid/util/SparseArray;

    iget v1, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mImageCount:I

    invoke-virtual {v0, v1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    return-object v0
.end method

.method public getRenderFragmentTag()Ljava/lang/String;
    .locals 1

    const-string v0, "fragment_poster:render"

    return-object v0
.end method

.method public getTitle()I
    .locals 1

    const v0, 0x7f100343

    return v0
.end method

.method protected hasResourceData()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterLayoutSparseArray:Landroid/util/SparseArray;

    invoke-virtual {v0}, Landroid/util/SparseArray;->size()I

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mPosterSparseArray:Landroid/util/SparseArray;

    invoke-virtual {v0}, Landroid/util/SparseArray;->size()I

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public onCreateDataLoader(Lcom/miui/gallery/collage/core/CollagePresenter$DataLoadListener;)Lcom/miui/gallery/collage/app/common/IDataLoader;
    .locals 3

    new-instance v0, Lcom/miui/gallery/collage/core/poster/PosterDataLoader;

    iget-object v1, p0, Lcom/miui/gallery/collage/core/poster/PosterPresenter;->mViewInterface:Lcom/miui/gallery/collage/core/ViewInterface;

    invoke-interface {v1}, Lcom/miui/gallery/collage/core/ViewInterface;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    new-instance v2, Lcom/miui/gallery/collage/core/poster/PosterPresenter$1;

    invoke-direct {v2, p0, p1}, Lcom/miui/gallery/collage/core/poster/PosterPresenter$1;-><init>(Lcom/miui/gallery/collage/core/poster/PosterPresenter;Lcom/miui/gallery/collage/core/CollagePresenter$DataLoadListener;)V

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/collage/core/poster/PosterDataLoader;-><init>(Landroid/content/res/AssetManager;Lcom/miui/gallery/collage/core/poster/PosterDataLoader$DataLoadListener;)V

    return-object v0
.end method

.method protected onCreateMenuFragment()Lcom/miui/gallery/collage/app/common/CollageMenuFragment;
    .locals 1

    new-instance v0, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;

    invoke-direct {v0}, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;-><init>()V

    return-object v0
.end method

.method protected onCreateRenderFragment()Lcom/miui/gallery/collage/app/common/CollageRenderFragment;
    .locals 1

    new-instance v0, Lcom/miui/gallery/collage/core/poster/PosterFragment;

    invoke-direct {v0}, Lcom/miui/gallery/collage/core/poster/PosterFragment;-><init>()V

    return-object v0
.end method

.method protected onDetach()V
    .locals 0

    return-void
.end method
