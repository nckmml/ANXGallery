.class public Lcom/miui/gallery/card/core/LayoutParamsHelper;
.super Ljava/lang/Object;
.source "LayoutParamsHelper.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;
    }
.end annotation


# instance fields
.field private mItemMaxHeight:I

.field private mItemMinHeight:I

.field private mLayoutSizes:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0605f6

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelOffset(I)I

    move-result v1

    iput v1, p0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMinHeight:I

    const v1, 0x7f0605f5

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelOffset(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMaxHeight:I

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    return-void
.end method

.method private getImageSizeGuaranteed(Ljava/util/List;III)Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;",
            ">;III)",
            "Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;"
        }
    .end annotation

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_0

    if-ltz p2, :cond_0

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    if-ge p2, v0, :cond_0

    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    if-nez p1, :cond_1

    new-instance p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    const/4 p2, 0x0

    invoke-direct {p1, p2, p2}, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;-><init>(II)V

    :cond_1
    iget p2, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    if-lez p2, :cond_2

    iget p2, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    if-gtz p2, :cond_3

    :cond_2
    sub-int/2addr p3, p4

    div-int/lit8 p3, p3, 0x2

    iput p3, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    iget p2, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    iput p2, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    :cond_3
    return-object p1
.end method

.method private getRatio(Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;)F
    .locals 4

    const/high16 v0, 0x3f800000    # 1.0f

    :try_start_0
    iget v1, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    int-to-float v1, v1

    iget p1, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    int-to-float p1, p1

    div-float p1, v1, p1

    const/4 v1, 0x0

    :try_start_1
    invoke-static {p1, v1}, Ljava/lang/Float;->compare(FF)I

    move-result v1
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    if-nez v1, :cond_0

    move p1, v0

    :cond_0
    return p1

    :catch_0
    move-exception v0

    move-object v3, v0

    move v0, p1

    move-object p1, v3

    goto :goto_0

    :catch_1
    move-exception p1

    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "getRatio error:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v1, "LayoutParamsHelper"

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return v0
.end method


# virtual methods
.method public getLayoutSize(I)Lcom/nostra13/universalimageloader/core/assist/ImageSize;
    .locals 3

    if-ltz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    new-instance v0, Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    iget-object v1, p0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v1, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    iget v1, v1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    iget-object v2, p0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    iget p1, p1, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    invoke-direct {v0, v1, p1}, Lcom/nostra13/universalimageloader/core/assist/ImageSize;-><init>(II)V

    return-object v0

    :cond_0
    const/4 p1, 0x0

    return-object p1
.end method

.method public updateLayoutSizes(Ljava/util/List;II)V
    .locals 17
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;",
            ">;II)V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move/from16 v2, p2

    move/from16 v3, p3

    iget-object v4, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v4}, Ljava/util/List;->clear()V

    invoke-static/range {p1 .. p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v4

    if-nez v4, :cond_0

    return-void

    :cond_0
    const/4 v4, 0x0

    move v5, v4

    :goto_0
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->size()I

    move-result v6

    if-ge v4, v6, :cond_b

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Row index:"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    const-string v7, "LayoutParamsHelper"

    invoke-static {v7, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    add-int/lit8 v5, v5, 0x1

    new-instance v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    invoke-direct {v6}, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;-><init>()V

    new-instance v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    invoke-direct {v8}, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;-><init>()V

    new-instance v9, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    invoke-direct {v9}, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;-><init>()V

    invoke-direct {v0, v1, v4, v2, v3}, Lcom/miui/gallery/card/core/LayoutParamsHelper;->getImageSizeGuaranteed(Ljava/util/List;III)Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    move-result-object v10

    iput v2, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    iget v11, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    int-to-float v11, v11

    invoke-direct {v0, v10}, Lcom/miui/gallery/card/core/LayoutParamsHelper;->getRatio(Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;)F

    move-result v12

    div-float/2addr v11, v12

    float-to-int v11, v11

    iput v11, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iget v11, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    int-to-float v11, v11

    iget v12, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMinHeight:I

    int-to-float v13, v12

    const/high16 v14, 0x3f800000    # 1.0f

    mul-float/2addr v13, v14

    cmpg-float v11, v11, v13

    if-gez v11, :cond_1

    int-to-float v8, v12

    mul-float/2addr v8, v14

    float-to-int v8, v8

    iput v8, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iget-object v8, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v8, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {v7, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_1
    add-int/lit8 v4, v4, 0x1

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->size()I

    move-result v11

    if-ne v4, v11, :cond_3

    iget v1, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iget v2, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMaxHeight:I

    if-le v1, v2, :cond_2

    iput v2, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    :cond_2
    iget-object v1, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v1, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {v7, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    goto/16 :goto_4

    :cond_3
    invoke-direct {v0, v1, v4, v2, v3}, Lcom/miui/gallery/card/core/LayoutParamsHelper;->getImageSizeGuaranteed(Ljava/util/List;III)Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    move-result-object v11

    invoke-direct {v0, v10}, Lcom/miui/gallery/card/core/LayoutParamsHelper;->getRatio(Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;)F

    move-result v10

    invoke-direct {v0, v11}, Lcom/miui/gallery/card/core/LayoutParamsHelper;->getRatio(Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;)F

    move-result v11

    sub-int v12, v2, v3

    int-to-float v12, v12

    add-float v13, v10, v11

    div-float/2addr v12, v13

    float-to-int v12, v12

    int-to-float v14, v12

    iget v15, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMinHeight:I

    int-to-float v15, v15

    const v16, 0x3f8ccccd    # 1.1f

    mul-float v15, v15, v16

    cmpg-float v15, v14, v15

    if-gez v15, :cond_5

    iget v8, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iget v9, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMaxHeight:I

    if-le v8, v9, :cond_4

    goto :goto_1

    :cond_4
    iget v9, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    :goto_1
    iput v9, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iget-object v8, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v8, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v4, v4, -0x1

    invoke-static {v7, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    goto/16 :goto_3

    :cond_5
    iput v12, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    mul-float v15, v14, v10

    float-to-int v15, v15

    iput v15, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    iput v12, v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    mul-float/2addr v14, v11

    float-to-int v12, v14

    iput v12, v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    add-int/lit8 v4, v4, 0x1

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->size()I

    move-result v12

    if-ne v4, v12, :cond_7

    iget v1, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iget v2, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMaxHeight:I

    if-le v1, v2, :cond_6

    iput v2, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iput v2, v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    :cond_6
    iget-object v1, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v1, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v1, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {v7, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {v7, v8}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_4

    :cond_7
    invoke-direct {v0, v1, v4, v2, v3}, Lcom/miui/gallery/card/core/LayoutParamsHelper;->getImageSizeGuaranteed(Ljava/util/List;III)Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;

    move-result-object v12

    invoke-direct {v0, v12}, Lcom/miui/gallery/card/core/LayoutParamsHelper;->getRatio(Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;)F

    move-result v12

    mul-int/lit8 v14, v3, 0x2

    sub-int v14, v2, v14

    int-to-float v14, v14

    add-float/2addr v13, v12

    div-float/2addr v14, v13

    float-to-int v13, v14

    int-to-float v14, v13

    iget v15, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMinHeight:I

    int-to-float v15, v15

    const v16, 0x3f99999a    # 1.2f

    mul-float v15, v15, v16

    cmpg-float v15, v14, v15

    if-gez v15, :cond_9

    iget v9, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iget v10, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMaxHeight:I

    if-le v9, v10, :cond_8

    iput v10, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iput v10, v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    :cond_8
    iget-object v9, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v9, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v9, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v9, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v4, v4, -0x1

    invoke-static {v7, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {v7, v8}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_3

    :cond_9
    iget v15, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mItemMaxHeight:I

    if-le v13, v15, :cond_a

    iput v15, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iput v15, v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iput v15, v9, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    goto :goto_2

    :cond_a
    iput v13, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iput v13, v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    iput v13, v9, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mHeight:I

    :goto_2
    mul-float/2addr v10, v14

    float-to-int v10, v10

    iput v10, v6, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    mul-float/2addr v11, v14

    float-to-int v10, v11

    iput v10, v8, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    mul-float/2addr v14, v12

    float-to-int v10, v14

    iput v10, v9, Lcom/miui/gallery/card/core/LayoutParamsHelper$Size;->mWidth:I

    iget-object v10, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v10, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v10, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v10, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object v10, v0, Lcom/miui/gallery/card/core/LayoutParamsHelper;->mLayoutSizes:Ljava/util/List;

    invoke-interface {v10, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {v7, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {v7, v8}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {v7, v9}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/Object;)V

    :goto_3
    add-int/lit8 v4, v4, 0x1

    goto/16 :goto_0

    :cond_b
    :goto_4
    return-void
.end method
