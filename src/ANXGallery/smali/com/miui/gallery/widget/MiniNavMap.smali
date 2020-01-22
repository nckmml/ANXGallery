.class public Lcom/miui/gallery/widget/MiniNavMap;
.super Landroid/view/View;
.source "MiniNavMap.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;
    }
.end annotation


# instance fields
.field private mFrame:Landroid/graphics/RectF;

.field private mFrameBorderColor:I

.field private mFrameBorderWidth:I

.field private mFrameFillColor:I

.field private mGestureDetector:Lcom/miui/gallery/view/GestureDetector;

.field private mHighlight:Landroid/graphics/RectF;

.field private mHighlightFillColor:I

.field private mHighlightScaleFactor:F

.field private mHighlightStrokeColor:I

.field private mHighlightStrokeWidth:I

.field private mOnGestureListener:Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;

.field private mPaint:Landroid/graphics/Paint;

.field private mPreferredFrameSize:I

.field private mRequestedHeight:I

.field private mRequestedWidth:I

.field private mRoundedCornerRadius:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/widget/MiniNavMap;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/gallery/widget/MiniNavMap;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 9

    invoke-direct {p0, p1, p2, p3}, Landroid/view/View;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    const v0, 0x2e55c4ff

    iput v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameFillColor:I

    const v1, -0x27000001

    iput v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderColor:I

    const/4 v2, 0x2

    iput v2, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderWidth:I

    iput v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightFillColor:I

    iput v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightStrokeColor:I

    const/4 v3, 0x5

    iput v3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightStrokeWidth:I

    const/4 v4, 0x3

    iput v4, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRoundedCornerRadius:I

    const/16 v5, 0x16d

    iput v5, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPreferredFrameSize:I

    const/high16 v6, 0x3f800000    # 1.0f

    iput v6, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightScaleFactor:F

    new-instance v7, Landroid/graphics/RectF;

    invoke-direct {v7}, Landroid/graphics/RectF;-><init>()V

    iput-object v7, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrame:Landroid/graphics/RectF;

    new-instance v7, Landroid/graphics/RectF;

    invoke-direct {v7}, Landroid/graphics/RectF;-><init>()V

    iput-object v7, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    sget-object v7, Lcom/miui/gallery/R$styleable;->MiniNavMap:[I

    const/4 v8, 0x0

    invoke-virtual {p1, p2, v7, p3, v8}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/res/TypedArray;->getIndexCount()I

    move-result p2

    :goto_0
    if-ge v8, p2, :cond_0

    invoke-virtual {p1, v8}, Landroid/content/res/TypedArray;->getIndex(I)I

    move-result p3

    packed-switch p3, :pswitch_data_0

    goto :goto_1

    :pswitch_0
    invoke-virtual {p1, p3, v4}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRoundedCornerRadius:I

    goto :goto_1

    :pswitch_1
    invoke-virtual {p1, p3, v5}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPreferredFrameSize:I

    goto :goto_1

    :pswitch_2
    invoke-virtual {p1, p3, v6}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightScaleFactor:F

    goto :goto_1

    :pswitch_3
    invoke-virtual {p1, p3, v0}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightFillColor:I

    goto :goto_1

    :pswitch_4
    invoke-virtual {p1, p3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightStrokeWidth:I

    goto :goto_1

    :pswitch_5
    const v7, -0xa53401

    invoke-virtual {p1, p3, v7}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightStrokeColor:I

    goto :goto_1

    :pswitch_6
    const v7, 0x2effffff

    invoke-virtual {p1, p3, v7}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameFillColor:I

    goto :goto_1

    :pswitch_7
    invoke-virtual {p1, p3, v2}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderWidth:I

    goto :goto_1

    :pswitch_8
    invoke-virtual {p1, p3, v1}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderColor:I

    :goto_1
    add-int/lit8 v8, v8, 0x1

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Landroid/content/res/TypedArray;->recycle()V

    invoke-direct {p0}, Lcom/miui/gallery/widget/MiniNavMap;->init()V

    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method static synthetic access$000(Lcom/miui/gallery/widget/MiniNavMap;)Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mOnGestureListener:Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;

    return-object p0
.end method

.method private ensurePaint()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    if-nez v0, :cond_0

    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setDither(Z)V

    :cond_0
    return-void
.end method

.method private init()V
    .locals 3

    new-instance v0, Lcom/miui/gallery/view/GestureDetector;

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getContext()Landroid/content/Context;

    move-result-object v1

    new-instance v2, Lcom/miui/gallery/widget/MiniNavMap$1;

    invoke-direct {v2, p0}, Lcom/miui/gallery/widget/MiniNavMap$1;-><init>(Lcom/miui/gallery/widget/MiniNavMap;)V

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/view/GestureDetector;-><init>(Landroid/content/Context;Lcom/miui/gallery/view/GestureDetector$OnGestureListener;)V

    iput-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mGestureDetector:Lcom/miui/gallery/view/GestureDetector;

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mGestureDetector:Lcom/miui/gallery/view/GestureDetector;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Lcom/miui/gallery/view/GestureDetector;->setTouchSlop(I)V

    return-void
.end method


# virtual methods
.method public getPreferredFrameSize()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPreferredFrameSize:I

    return v0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 4

    invoke-super {p0, p1}, Landroid/view/View;->onDraw(Landroid/graphics/Canvas;)V

    invoke-direct {p0}, Lcom/miui/gallery/widget/MiniNavMap;->ensurePaint()V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->FILL:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameFillColor:I

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrame:Landroid/graphics/RectF;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRoundedCornerRadius:I

    int-to-float v2, v1

    int-to-float v1, v1

    iget-object v3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v1, v3}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderColor:I

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderWidth:I

    int-to-float v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrame:Landroid/graphics/RectF;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRoundedCornerRadius:I

    int-to-float v2, v1

    int-to-float v1, v1

    iget-object v3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v1, v3}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrame:Landroid/graphics/RectF;

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getWidth()I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getHeight()I

    move-result v2

    int-to-float v2, v2

    const/4 v3, 0x0

    invoke-virtual {v0, v3, v3, v1, v2}, Landroid/graphics/RectF;->intersects(FFFF)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->FILL:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightFillColor:I

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRoundedCornerRadius:I

    int-to-float v2, v1

    int-to-float v1, v1

    iget-object v3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v1, v3}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightStrokeColor:I

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightStrokeWidth:I

    int-to-float v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRoundedCornerRadius:I

    int-to-float v2, v1

    int-to-float v1, v1

    iget-object v3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2, v1, v3}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    invoke-virtual {p1}, Landroid/graphics/RectF;->toShortString()Ljava/lang/String;

    move-result-object p1

    const-string v0, "MiniNavMap"

    const-string v1, "invalid highlight size: %s"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_0
    return-void
.end method

.method protected onMeasure(II)V
    .locals 0

    iget p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRequestedWidth:I

    iget p2, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRequestedHeight:I

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/widget/MiniNavMap;->setMeasuredDimension(II)V

    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mGestureDetector:Lcom/miui/gallery/view/GestureDetector;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/view/GestureDetector;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result p1

    const/4 v1, 0x1

    if-nez v0, :cond_1

    const/4 v2, 0x3

    if-eq p1, v2, :cond_0

    if-ne p1, v1, :cond_1

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mOnGestureListener:Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;

    if-eqz p1, :cond_1

    invoke-interface {p1}, Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;->onMoveDone()V

    move v0, v1

    :cond_1
    return v0
.end method

.method public setFrameSize(II)Z
    .locals 6

    iget v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRequestedWidth:I

    if-ne p1, v0, :cond_0

    iget v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRequestedHeight:I

    if-ne p2, v0, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    iput p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRequestedWidth:I

    iput p2, p0, Lcom/miui/gallery/widget/MiniNavMap;->mRequestedHeight:I

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrame:Landroid/graphics/RectF;

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderWidth:I

    int-to-float v2, v1

    const/high16 v3, 0x3f000000    # 0.5f

    mul-float/2addr v2, v3

    int-to-float v4, v1

    mul-float/2addr v4, v3

    int-to-float p1, p1

    int-to-float v5, v1

    mul-float/2addr v5, v3

    sub-float/2addr p1, v5

    int-to-float p2, p2

    int-to-float v1, v1

    mul-float/2addr v1, v3

    sub-float/2addr p2, v1

    invoke-virtual {v0, v2, v4, p1, p2}, Landroid/graphics/RectF;->set(FFFF)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->requestLayout()V

    const/4 p1, 0x1

    return p1
.end method

.method public setOnGestureListener(Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mOnGestureListener:Lcom/miui/gallery/widget/MiniNavMap$OnGestureListener;

    return-void
.end method

.method public updateHighlightRect(Landroid/graphics/RectF;)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    invoke-virtual {v0, p1}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    invoke-virtual {p1}, Landroid/graphics/RectF;->width()F

    move-result v0

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightScaleFactor:F

    const/high16 v2, 0x3f800000    # 1.0f

    sub-float/2addr v1, v2

    mul-float/2addr v0, v1

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v0, v1

    iget-object v3, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrame:Landroid/graphics/RectF;

    invoke-virtual {v3}, Landroid/graphics/RectF;->width()F

    move-result v3

    invoke-virtual {p1}, Landroid/graphics/RectF;->width()F

    move-result v4

    sub-float/2addr v3, v4

    const/4 v4, 0x0

    invoke-static {v3, v4}, Ljava/lang/Math;->max(FF)F

    move-result v3

    div-float/2addr v3, v1

    invoke-static {v0, v3}, Ljava/lang/Math;->min(FF)F

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/RectF;->height()F

    move-result v3

    iget v5, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightScaleFactor:F

    sub-float/2addr v5, v2

    mul-float/2addr v3, v5

    div-float/2addr v3, v1

    iget-object v2, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrame:Landroid/graphics/RectF;

    invoke-virtual {v2}, Landroid/graphics/RectF;->height()F

    move-result v2

    invoke-virtual {p1}, Landroid/graphics/RectF;->height()F

    move-result p1

    sub-float/2addr v2, p1

    invoke-static {v2, v4}, Ljava/lang/Math;->max(FF)F

    move-result p1

    div-float/2addr p1, v1

    invoke-static {v3, p1}, Ljava/lang/Math;->min(FF)F

    move-result p1

    iget-object v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    neg-float v0, v0

    neg-float p1, p1

    invoke-virtual {v1, v0, p1}, Landroid/graphics/RectF;->inset(FF)V

    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget p1, p1, Landroid/graphics/RectF;->left:F

    cmpg-float p1, p1, v4

    if-gez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget v0, p1, Landroid/graphics/RectF;->left:F

    neg-float v0, v0

    invoke-virtual {p1, v0, v4}, Landroid/graphics/RectF;->offset(FF)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget p1, p1, Landroid/graphics/RectF;->right:F

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getWidth()I

    move-result v0

    int-to-float v0, v0

    cmpl-float p1, p1, v0

    if-lez p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getWidth()I

    move-result v0

    int-to-float v0, v0

    iget-object v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->right:F

    sub-float/2addr v0, v1

    invoke-virtual {p1, v0, v4}, Landroid/graphics/RectF;->offset(FF)V

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget p1, p1, Landroid/graphics/RectF;->top:F

    cmpg-float p1, p1, v4

    if-gez p1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget v0, p1, Landroid/graphics/RectF;->top:F

    neg-float v0, v0

    invoke-virtual {p1, v4, v0}, Landroid/graphics/RectF;->offset(FF)V

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget p1, p1, Landroid/graphics/RectF;->bottom:F

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getHeight()I

    move-result v0

    int-to-float v0, v0

    cmpl-float p1, p1, v0

    if-lez p1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getHeight()I

    move-result v0

    int-to-float v0, v0

    iget-object v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    iget v1, v1, Landroid/graphics/RectF;->bottom:F

    sub-float/2addr v0, v1

    invoke-virtual {p1, v4, v0}, Landroid/graphics/RectF;->offset(FF)V

    :cond_3
    iget p1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mFrameBorderWidth:I

    int-to-float p1, p1

    const/high16 v0, 0x3f000000    # 0.5f

    iget v1, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlightStrokeWidth:I

    int-to-float v1, v1

    mul-float/2addr v1, v0

    add-float/2addr p1, v1

    iget-object v0, p0, Lcom/miui/gallery/widget/MiniNavMap;->mHighlight:Landroid/graphics/RectF;

    invoke-virtual {v0, p1, p1}, Landroid/graphics/RectF;->inset(FF)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->getVisibility()I

    move-result p1

    if-nez p1, :cond_4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/MiniNavMap;->invalidate()V

    :cond_4
    return-void
.end method
