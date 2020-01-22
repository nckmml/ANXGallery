.class public Lcom/miui/gallery/ui/PhotoViewNavMapBridge;
.super Ljava/lang/Object;
.source "PhotoViewNavMapBridge.java"

# interfaces
.implements Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;
.implements Luk/co/senab/photoview/PhotoViewAttacher$OnMatrixChangedListener;
.implements Luk/co/senab/photoview/PhotoViewAttacher$OnRotateListener;


# instance fields
.field private mDecelerateFactor:F

.field private mLastMoveDirection:I

.field private mLongSide:I

.field private mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

.field private mNavMapMarginBottom:I

.field private mPhotoView:Luk/co/senab/photoview/PhotoView;

.field private mScaleRatio:F

.field private mTempRect:Landroid/graphics/RectF;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/high16 v0, 0x3f800000    # 1.0f

    iput v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mDecelerateFactor:F

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLastMoveDirection:I

    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMapMarginBottom:I

    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mTempRect:Landroid/graphics/RectF;

    return-void
.end method


# virtual methods
.method public connect(Luk/co/senab/photoview/PhotoView;Lcom/miui/gallery/widget/MiniNavMap;I)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    iput-object p2, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    iput p3, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLongSide:I

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/MiniNavMap;->getPreferredFrameSize()I

    move-result p1

    int-to-float p1, p1

    const/high16 p2, 0x3f800000    # 1.0f

    mul-float/2addr p1, p2

    int-to-float p2, p3

    div-float/2addr p1, p2

    iput p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mScaleRatio:F

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {p1, p0}, Luk/co/senab/photoview/PhotoView;->addOnMatrixChangeListener(Luk/co/senab/photoview/PhotoViewAttacher$OnMatrixChangedListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {p1, p0}, Luk/co/senab/photoview/PhotoView;->addOnRotateListener(Luk/co/senab/photoview/PhotoViewAttacher$OnRotateListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {p1, p0}, Lcom/miui/gallery/widget/MiniNavMap;->setOnGestureListener(Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;)V

    return-void
.end method

.method public disconnect()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0, p0}, Luk/co/senab/photoview/PhotoView;->removeOnMatrixChangeListener(Luk/co/senab/photoview/PhotoViewAttacher$OnMatrixChangedListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0, p0}, Luk/co/senab/photoview/PhotoView;->removeOnRotateListener(Luk/co/senab/photoview/PhotoViewAttacher$OnRotateListener;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-eqz v0, :cond_1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/MiniNavMap;->setOnGestureListener(Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    :cond_1
    return-void
.end method

.method public getNavMapMarginBottom(Landroid/content/Context;)I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMapMarginBottom:I

    if-gez v0, :cond_0

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f0604b6

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMapMarginBottom:I

    :cond_0
    iget p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMapMarginBottom:I

    return p1
.end method

.method public onMatrixChanged(Landroid/graphics/RectF;)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-eqz v0, :cond_4

    if-eqz p1, :cond_4

    invoke-virtual {v0}, Lcom/miui/gallery/widget/MiniNavMap;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_2

    :cond_0
    invoke-virtual {p1}, Landroid/graphics/RectF;->width()F

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/RectF;->height()F

    move-result v1

    iget v2, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLongSide:I

    int-to-float v2, v2

    iget v3, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mScaleRatio:F

    mul-float/2addr v2, v3

    invoke-static {v0, v1}, Ljava/lang/Math;->min(FF)F

    move-result v3

    mul-float/2addr v3, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->max(FF)F

    move-result v4

    div-float/2addr v3, v4

    cmpl-float v4, v0, v1

    if-lez v4, :cond_1

    move v4, v2

    goto :goto_0

    :cond_1
    move v4, v3

    :goto_0
    cmpl-float v1, v1, v0

    if-lez v1, :cond_2

    goto :goto_1

    :cond_2
    move v2, v3

    :goto_1
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-static {v4}, Ljava/lang/Math;->round(F)I

    move-result v3

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v5

    invoke-virtual {v1, v3, v5}, Lcom/miui/gallery/widget/MiniNavMap;->setFrameSize(II)Z

    move-result v1

    if-eqz v1, :cond_3

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v1}, Luk/co/senab/photoview/PhotoView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {p0, v1}, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->getNavMapMarginBottom(Landroid/content/Context;)I

    move-result v1

    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    new-instance v5, Landroid/graphics/Rect;

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v2

    mul-int/lit8 v1, v1, 0x2

    add-int/2addr v2, v1

    const/4 v1, 0x0

    invoke-direct {v5, v1, v1, v1, v2}, Landroid/graphics/Rect;-><init>(IIII)V

    invoke-virtual {v3, v5}, Luk/co/senab/photoview/PhotoView;->setDraggableViewportInsets(Landroid/graphics/Rect;)V

    :cond_3
    div-float/2addr v4, v0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getViewPort()Landroid/graphics/Rect;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mTempRect:Landroid/graphics/RectF;

    invoke-virtual {v1, p1}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mTempRect:Landroid/graphics/RectF;

    iget v2, v0, Landroid/graphics/Rect;->left:I

    int-to-float v2, v2

    iget v3, v0, Landroid/graphics/Rect;->top:I

    int-to-float v3, v3

    iget v5, v0, Landroid/graphics/Rect;->right:I

    int-to-float v5, v5

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    int-to-float v0, v0

    invoke-virtual {v1, v2, v3, v5, v0}, Landroid/graphics/RectF;->intersect(FFFF)Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mTempRect:Landroid/graphics/RectF;

    iget v1, p1, Landroid/graphics/RectF;->left:F

    neg-float v1, v1

    mul-float/2addr v1, v4

    iget v2, p1, Landroid/graphics/RectF;->top:F

    neg-float v2, v2

    mul-float/2addr v2, v4

    iget v3, p1, Landroid/graphics/RectF;->left:F

    neg-float v3, v3

    iget-object v5, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mTempRect:Landroid/graphics/RectF;

    invoke-virtual {v5}, Landroid/graphics/RectF;->width()F

    move-result v5

    add-float/2addr v3, v5

    mul-float/2addr v3, v4

    iget p1, p1, Landroid/graphics/RectF;->top:F

    neg-float p1, p1

    iget-object v5, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mTempRect:Landroid/graphics/RectF;

    invoke-virtual {v5}, Landroid/graphics/RectF;->height()F

    move-result v5

    add-float/2addr p1, v5

    mul-float/2addr p1, v4

    invoke-virtual {v0, v1, v2, v3, p1}, Landroid/graphics/RectF;->set(FFFF)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mTempRect:Landroid/graphics/RectF;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/MiniNavMap;->updateHighlightRect(Landroid/graphics/RectF;)V

    :cond_4
    :goto_2
    return-void
.end method

.method public onMove(FF)Z
    .locals 8

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    const/4 v1, 0x0

    if-eqz v0, :cond_9

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-nez v0, :cond_0

    goto/16 :goto_3

    :cond_0
    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result v0

    invoke-static {p2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    cmpl-float v3, v0, v2

    const/4 v4, 0x1

    const/4 v5, -0x1

    const/high16 v6, 0x3f800000    # 1.0f

    const/4 v7, 0x0

    if-lez v3, :cond_3

    iget v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLastMoveDirection:I

    if-ne v1, v5, :cond_1

    invoke-static {v0, v6}, Ljava/lang/Float;->compare(FF)I

    move-result v0

    if-gtz v0, :cond_2

    cmpl-float v0, v2, v7

    if-lez v0, :cond_2

    move p1, v7

    goto :goto_0

    :cond_1
    invoke-static {v2, v6}, Ljava/lang/Float;->compare(FF)I

    move-result v0

    if-gtz v0, :cond_2

    cmpl-float v0, v2, v7

    if-lez v0, :cond_2

    move p2, v7

    :cond_2
    :goto_0
    iput v4, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLastMoveDirection:I

    goto :goto_2

    :cond_3
    cmpl-float v3, v2, v0

    if-lez v3, :cond_6

    iget v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLastMoveDirection:I

    if-ne v1, v4, :cond_4

    invoke-static {v2, v6}, Ljava/lang/Float;->compare(FF)I

    move-result v1

    if-gtz v1, :cond_5

    cmpl-float v0, v0, v7

    if-lez v0, :cond_5

    move p2, v7

    goto :goto_1

    :cond_4
    invoke-static {v0, v6}, Ljava/lang/Float;->compare(FF)I

    move-result v1

    if-gtz v1, :cond_5

    cmpl-float v0, v0, v7

    if-lez v0, :cond_5

    move p1, v7

    :cond_5
    :goto_1
    iput v5, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLastMoveDirection:I

    goto :goto_2

    :cond_6
    iput v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mLastMoveDirection:I

    :goto_2
    iget v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mScaleRatio:F

    cmpl-float v1, v0, v7

    if-lez v1, :cond_7

    div-float/2addr p1, v0

    :cond_7
    iget v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mScaleRatio:F

    cmpl-float v1, v0, v7

    if-lez v1, :cond_8

    div-float/2addr p2, v0

    :cond_8
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    iget v1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mDecelerateFactor:F

    mul-float/2addr p1, v1

    mul-float/2addr p2, v1

    invoke-virtual {v0, p1, p2}, Luk/co/senab/photoview/PhotoView;->translateBy(FF)Z

    move-result p1

    return p1

    :cond_9
    :goto_3
    return v1
.end method

.method public onMoveDone()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->requestAlignBounds()V

    :cond_0
    return-void
.end method

.method public onRotate(FFFF)V
    .locals 0

    return-void
.end method

.method public onRotateBegin(F)V
    .locals 0

    return-void
.end method

.method public onRotateEnd(F)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    if-eqz p1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Luk/co/senab/photoview/PhotoView;->getDisplayRect()Landroid/graphics/RectF;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->onMatrixChanged(Landroid/graphics/RectF;)V

    :cond_1
    :goto_0
    return-void
.end method
