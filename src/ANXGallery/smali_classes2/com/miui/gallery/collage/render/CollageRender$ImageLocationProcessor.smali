.class public Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;
.super Ljava/lang/Object;
.source "CollageRender.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/collage/render/CollageRender;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ImageLocationProcessor"
.end annotation


# instance fields
.field private mClipArray:[F

.field private mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

.field private mMatrix:Landroid/graphics/Matrix;

.field private mPathRectF:Landroid/graphics/RectF;

.field private mPathRegion:Landroid/graphics/Region;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    new-instance v0, Landroid/graphics/Region;

    invoke-direct {v0}, Landroid/graphics/Region;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    new-instance v0, Landroid/graphics/Matrix;

    invoke-direct {v0}, Landroid/graphics/Matrix;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mMatrix:Landroid/graphics/Matrix;

    return-void
.end method

.method private enableMargin(FFFZ)V
    .locals 22

    move-object/from16 v0, p0

    const/4 v1, 0x0

    cmpl-float v2, p1, v1

    if-nez v2, :cond_0

    return-void

    :cond_0
    iget-object v2, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v2, v2, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->mPathForClip:Landroid/graphics/Path;

    sget-object v3, Lcom/miui/gallery/collage/render/CollageRender$1;->$SwitchMap$com$miui$gallery$collage$ClipType:[I

    iget-object v4, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v4, v4, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->mClipType:Lcom/miui/gallery/collage/ClipType;

    invoke-virtual {v4}, Lcom/miui/gallery/collage/ClipType;->ordinal()I

    move-result v4

    aget v3, v3, v4

    const/4 v5, 0x2

    const/4 v6, 0x0

    const/4 v7, 0x1

    if-eq v3, v7, :cond_9

    if-eq v3, v5, :cond_1

    goto/16 :goto_6

    :cond_1
    new-instance v3, Landroid/graphics/Path;

    invoke-direct {v3}, Landroid/graphics/Path;-><init>()V

    new-instance v8, Landroid/graphics/RectF;

    invoke-direct {v8}, Landroid/graphics/RectF;-><init>()V

    new-instance v9, Landroid/graphics/Region;

    invoke-direct {v9}, Landroid/graphics/Region;-><init>()V

    move v10, v6

    :goto_0
    iget-object v11, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mClipArray:[F

    array-length v12, v11

    if-ge v10, v12, :cond_8

    new-array v12, v5, [F

    new-array v13, v5, [F

    aget v14, v11, v10

    aput v14, v12, v6

    add-int/lit8 v14, v10, 0x1

    aget v14, v11, v14

    aput v14, v12, v7

    array-length v14, v11

    sub-int/2addr v14, v5

    if-ne v10, v14, :cond_2

    aget v14, v11, v6

    aput v14, v13, v6

    aget v11, v11, v7

    aput v11, v13, v7

    goto :goto_1

    :cond_2
    add-int/lit8 v14, v10, 0x2

    aget v14, v11, v14

    aput v14, v13, v6

    add-int/lit8 v14, v10, 0x3

    aget v11, v11, v14

    aput v11, v13, v7

    :goto_1
    aget v11, v12, v6

    aget v14, v12, v7

    aget v15, v13, v6

    aget v4, v13, v7

    cmpl-float v16, v11, v15

    if-nez v16, :cond_4

    cmpl-float v16, v11, v1

    if-eqz v16, :cond_3

    cmpl-float v16, v11, p2

    if-nez v16, :cond_5

    :cond_3
    :goto_2
    move/from16 v16, v7

    goto :goto_3

    :cond_4
    cmpl-float v16, v14, v4

    if-nez v16, :cond_5

    cmpl-float v16, v14, v1

    if-eqz v16, :cond_3

    cmpl-float v16, v14, p3

    if-nez v16, :cond_5

    goto :goto_2

    :cond_5
    move/from16 v16, v6

    :goto_3
    if-eqz v16, :cond_7

    if-eqz p4, :cond_6

    move/from16 v16, v1

    goto :goto_4

    :cond_6
    const/high16 v16, 0x40000000    # 2.0f

    mul-float v16, v16, p1

    :goto_4
    move/from16 v1, v16

    goto :goto_5

    :cond_7
    move/from16 v1, p1

    :goto_5
    aget v17, v13, v7

    aget v18, v12, v7

    sub-float v5, v17, v18

    move-object/from16 v18, v8

    float-to-double v7, v5

    aget v5, v13, v6

    aget v19, v12, v6

    sub-float v5, v5, v19

    move-object/from16 v19, v9

    move/from16 v20, v10

    float-to-double v9, v5

    invoke-static {v7, v8, v9, v10}, Ljava/lang/Math;->atan2(DD)D

    move-result-wide v7

    invoke-static {v7, v8}, Ljava/lang/Math;->toDegrees(D)D

    move-result-wide v7

    const/4 v5, 0x5

    new-array v5, v5, [Ljava/lang/Object;

    aget v9, v12, v6

    invoke-static {v9}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v9

    aput-object v9, v5, v6

    const/4 v9, 0x1

    aget v10, v12, v9

    invoke-static {v10}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v10

    aput-object v10, v5, v9

    aget v10, v13, v6

    invoke-static {v10}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v10

    const/16 v17, 0x2

    aput-object v10, v5, v17

    aget v10, v13, v9

    invoke-static {v10}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v9

    const/4 v10, 0x3

    aput-object v9, v5, v10

    const/4 v9, 0x4

    invoke-static {v7, v8}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v10

    aput-object v10, v5, v9

    const-string v9, "CollageRender"

    const-string v10, "xStart:%f yStart:%f xEnd:%f yEnd:%f degree\uff1a%f"

    invoke-static {v9, v10, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

    iget-object v5, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mMatrix:Landroid/graphics/Matrix;

    invoke-virtual {v5}, Landroid/graphics/Matrix;->reset()V

    iget-object v5, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mMatrix:Landroid/graphics/Matrix;

    move/from16 v21, v11

    neg-double v10, v7

    double-to-float v10, v10

    invoke-virtual {v5, v10}, Landroid/graphics/Matrix;->postRotate(F)Z

    iget-object v5, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mMatrix:Landroid/graphics/Matrix;

    neg-float v1, v1

    const/4 v10, 0x0

    invoke-virtual {v5, v10, v1}, Landroid/graphics/Matrix;->postTranslate(FF)Z

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mMatrix:Landroid/graphics/Matrix;

    double-to-float v5, v7

    invoke-virtual {v1, v5}, Landroid/graphics/Matrix;->postRotate(F)Z

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mMatrix:Landroid/graphics/Matrix;

    invoke-virtual {v1, v12}, Landroid/graphics/Matrix;->mapPoints([F)V

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mMatrix:Landroid/graphics/Matrix;

    invoke-virtual {v1, v13}, Landroid/graphics/Matrix;->mapPoints([F)V

    aget v1, v12, v6

    invoke-static {v1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    const/4 v5, 0x1

    aget v7, v12, v5

    invoke-static {v7}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v7

    const-string v8, "xStart:%f yStart:%f"

    invoke-static {v9, v8, v1, v7}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-virtual {v3}, Landroid/graphics/Path;->reset()V

    move/from16 v1, v21

    invoke-virtual {v3, v1, v14}, Landroid/graphics/Path;->moveTo(FF)V

    invoke-virtual {v3, v15, v4}, Landroid/graphics/Path;->lineTo(FF)V

    aget v1, v13, v6

    aget v4, v13, v5

    invoke-virtual {v3, v1, v4}, Landroid/graphics/Path;->lineTo(FF)V

    aget v1, v12, v6

    aget v4, v12, v5

    invoke-virtual {v3, v1, v4}, Landroid/graphics/Path;->lineTo(FF)V

    invoke-virtual {v3}, Landroid/graphics/Path;->close()V

    move-object/from16 v1, v18

    invoke-virtual {v3, v1, v5}, Landroid/graphics/Path;->computeBounds(Landroid/graphics/RectF;Z)V

    invoke-virtual/range {v19 .. v19}, Landroid/graphics/Region;->setEmpty()V

    new-instance v4, Landroid/graphics/Region;

    iget v5, v1, Landroid/graphics/RectF;->left:F

    float-to-int v5, v5

    iget v7, v1, Landroid/graphics/RectF;->top:F

    float-to-int v7, v7

    iget v8, v1, Landroid/graphics/RectF;->right:F

    float-to-int v8, v8

    iget v9, v1, Landroid/graphics/RectF;->bottom:F

    float-to-int v9, v9

    invoke-direct {v4, v5, v7, v8, v9}, Landroid/graphics/Region;-><init>(IIII)V

    move-object/from16 v5, v19

    invoke-virtual {v5, v3, v4}, Landroid/graphics/Region;->setPath(Landroid/graphics/Path;Landroid/graphics/Region;)Z

    iget-object v4, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    sget-object v7, Landroid/graphics/Region$Op;->DIFFERENCE:Landroid/graphics/Region$Op;

    invoke-virtual {v4, v5, v7}, Landroid/graphics/Region;->op(Landroid/graphics/Region;Landroid/graphics/Region$Op;)Z

    add-int/lit8 v4, v20, 0x2

    move-object v8, v1

    move-object v9, v5

    move v1, v10

    const/4 v5, 0x2

    const/4 v7, 0x1

    move v10, v4

    goto/16 :goto_0

    :cond_8
    invoke-virtual {v2}, Landroid/graphics/Path;->reset()V

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    invoke-virtual {v1, v2}, Landroid/graphics/Region;->getBoundaryPath(Landroid/graphics/Path;)Z

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    const/4 v3, 0x1

    invoke-virtual {v2, v1, v3}, Landroid/graphics/Path;->computeBounds(Landroid/graphics/RectF;Z)V

    goto :goto_6

    :cond_9
    move v3, v7

    invoke-virtual {v2}, Landroid/graphics/Path;->reset()V

    new-instance v1, Landroid/graphics/RectF;

    iget-object v4, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mClipArray:[F

    aget v5, v4, v6

    add-float v5, v5, p1

    aget v6, v4, v3

    add-float v6, v6, p1

    const/4 v3, 0x2

    aget v3, v4, v3

    sub-float v3, v3, p1

    const/4 v7, 0x3

    aget v4, v4, v7

    sub-float v4, v4, p1

    invoke-direct {v1, v5, v6, v3, v4}, Landroid/graphics/RectF;-><init>(FFFF)V

    sget-object v3, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v2, v1, v3}, Landroid/graphics/Path;->addOval(Landroid/graphics/RectF;Landroid/graphics/Path$Direction;)V

    invoke-static/range {p1 .. p1}, Ljava/lang/Math;->round(F)I

    move-result v1

    int-to-float v1, v1

    invoke-static/range {p1 .. p1}, Ljava/lang/Math;->round(F)I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {v2, v1, v3}, Landroid/graphics/Path;->offset(FF)V

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    const/4 v3, 0x1

    invoke-virtual {v2, v1, v3}, Landroid/graphics/Path;->computeBounds(Landroid/graphics/RectF;Z)V

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    invoke-virtual {v1}, Landroid/graphics/Region;->setEmpty()V

    iget-object v1, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    new-instance v3, Landroid/graphics/Region;

    iget-object v4, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v4, v4, Landroid/graphics/RectF;->left:F

    float-to-int v4, v4

    iget-object v5, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v5, v5, Landroid/graphics/RectF;->top:F

    float-to-int v5, v5

    iget-object v6, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v6, v6, Landroid/graphics/RectF;->right:F

    float-to-int v6, v6

    iget-object v7, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v7, v7, Landroid/graphics/RectF;->bottom:F

    float-to-int v7, v7

    invoke-direct {v3, v4, v5, v6, v7}, Landroid/graphics/Region;-><init>(IIII)V

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Region;->setPath(Landroid/graphics/Path;Landroid/graphics/Region;)Z

    :goto_6
    return-void
.end method

.method private generateClipArrayBySize(FF)V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v0, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->mClipArray:[F

    array-length v1, v0

    new-array v1, v1, [F

    iput-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mClipArray:[F

    const/4 v1, 0x0

    :goto_0
    array-length v2, v0

    if-ge v1, v2, :cond_0

    iget-object v2, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mClipArray:[F

    aget v3, v0, v1

    mul-float/2addr v3, p1

    aput v3, v2, v1

    add-int/lit8 v3, v1, 0x1

    aget v4, v0, v3

    mul-float/2addr v4, p2

    aput v4, v2, v3

    add-int/lit8 v1, v1, 0x2

    goto :goto_0

    :cond_0
    return-void
.end method

.method private generateLayoutSizeByRect()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->left:F

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->access$002(Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;I)I

    iget-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->top:F

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->access$102(Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;I)I

    iget-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->right:F

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->access$202(Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;I)I

    iget-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->bottom:F

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->access$302(Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;I)I

    iget-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v0, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->mPathRegion:Landroid/graphics/Region;

    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    invoke-virtual {v0, v1}, Landroid/graphics/Region;->set(Landroid/graphics/Region;)Z

    return-void
.end method

.method private generatePath()V
    .locals 8

    iget-object v0, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v0, v0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->mPathForClip:Landroid/graphics/Path;

    invoke-virtual {v0}, Landroid/graphics/Path;->reset()V

    sget-object v1, Lcom/miui/gallery/collage/render/CollageRender$1;->$SwitchMap$com$miui$gallery$collage$ClipType:[I

    iget-object v2, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    iget-object v2, v2, Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;->mClipType:Lcom/miui/gallery/collage/ClipType;

    invoke-virtual {v2}, Lcom/miui/gallery/collage/ClipType;->ordinal()I

    move-result v2

    aget v1, v1, v2

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eq v1, v3, :cond_2

    :goto_0
    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mClipArray:[F

    array-length v4, v1

    if-ge v2, v4, :cond_1

    aget v4, v1, v2

    add-int/lit8 v5, v2, 0x1

    aget v1, v1, v5

    invoke-virtual {v0}, Landroid/graphics/Path;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_0

    invoke-virtual {v0, v4, v1}, Landroid/graphics/Path;->moveTo(FF)V

    goto :goto_1

    :cond_0
    invoke-virtual {v0, v4, v1}, Landroid/graphics/Path;->lineTo(FF)V

    :goto_1
    add-int/lit8 v2, v2, 0x2

    goto :goto_0

    :cond_1
    invoke-virtual {v0}, Landroid/graphics/Path;->close()V

    goto :goto_2

    :cond_2
    new-instance v1, Landroid/graphics/RectF;

    iget-object v4, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mClipArray:[F

    aget v2, v4, v2

    aget v5, v4, v3

    const/4 v6, 0x2

    aget v6, v4, v6

    const/4 v7, 0x3

    aget v4, v4, v7

    invoke-direct {v1, v2, v5, v6, v4}, Landroid/graphics/RectF;-><init>(FFFF)V

    sget-object v2, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v0, v1, v2}, Landroid/graphics/Path;->addOval(Landroid/graphics/RectF;Landroid/graphics/Path$Direction;)V

    :goto_2
    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    invoke-virtual {v0, v1, v3}, Landroid/graphics/Path;->computeBounds(Landroid/graphics/RectF;Z)V

    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    invoke-virtual {v1}, Landroid/graphics/Region;->setEmpty()V

    iget-object v1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRegion:Landroid/graphics/Region;

    new-instance v2, Landroid/graphics/Region;

    iget-object v3, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v3, v3, Landroid/graphics/RectF;->left:F

    float-to-int v3, v3

    iget-object v4, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v4, v4, Landroid/graphics/RectF;->top:F

    float-to-int v4, v4

    iget-object v5, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v5, v5, Landroid/graphics/RectF;->right:F

    float-to-int v5, v5

    iget-object v6, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mPathRectF:Landroid/graphics/RectF;

    iget v6, v6, Landroid/graphics/RectF;->bottom:F

    float-to-int v6, v6

    invoke-direct {v2, v3, v4, v5, v6}, Landroid/graphics/Region;-><init>(IIII)V

    invoke-virtual {v1, v0, v2}, Landroid/graphics/Region;->setPath(Landroid/graphics/Path;Landroid/graphics/Region;)Z

    return-void
.end method


# virtual methods
.method public processorImageLocation(Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;FFFZ)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->mImageLocation:Lcom/miui/gallery/collage/render/CollageRender$ImageLocation;

    const/4 p1, 0x0

    cmpl-float p1, p4, p1

    if-lez p1, :cond_0

    invoke-direct {p0, p2, p3}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->generateClipArrayBySize(FF)V

    invoke-direct {p0}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->generatePath()V

    invoke-direct {p0, p4, p2, p3, p5}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->enableMargin(FFFZ)V

    goto :goto_0

    :cond_0
    invoke-direct {p0, p2, p3}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->generateClipArrayBySize(FF)V

    invoke-direct {p0}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->generatePath()V

    :goto_0
    invoke-direct {p0}, Lcom/miui/gallery/collage/render/CollageRender$ImageLocationProcessor;->generateLayoutSizeByRect()V

    return-void
.end method
