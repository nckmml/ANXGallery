.class Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;
.super Ljava/lang/Object;
.source "PhotoPageVideoItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageVideoItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "VideoSubtitleManager"
.end annotation


# instance fields
.field private mBaseRect:Landroid/graphics/RectF;

.field private mLayoutHeight:I

.field private mLayoutParams:Landroid/widget/RelativeLayout$LayoutParams;

.field private mTextView:Landroid/widget/TextView;

.field private mVideoRect:Landroid/graphics/RectF;

.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;Lcom/miui/gallery/ui/PhotoPageVideoItem$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V

    return-void
.end method

.method private addTextView()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    return-void

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->getBaseVideoRect()Landroid/graphics/RectF;

    move-result-object v0

    if-nez v0, :cond_1

    return-void

    :cond_1
    new-instance v1, Landroid/widget/TextView;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v2}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    const/high16 v2, 0x3f000000    # 0.5f

    const/high16 v3, -0x80000000

    const/high16 v4, 0x3f800000    # 1.0f

    invoke-virtual {v1, v2, v4, v4, v3}, Landroid/widget/TextView;->setShadowLayer(FFFI)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setGravity(I)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    const/4 v2, 0x2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setMaxLines(I)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    sget-object v2, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setEllipsize(Landroid/text/TextUtils$TruncateAt;)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    const/4 v2, -0x1

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setTextColor(I)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->updateTextViewStyle(Landroid/graphics/RectF;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->updateTextViewLayout(Landroid/graphics/RectF;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutParams:Landroid/widget/RelativeLayout$LayoutParams;

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mCheckManager:Lcom/miui/gallery/ui/PhotoPageItem$CheckManager;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mCheckManager:Lcom/miui/gallery/ui/PhotoPageItem$CheckManager;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageItem$CheckManager;->inAction()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->onActionModeChanged(Z)V

    :cond_2
    return-void
.end method

.method private getBaseVideoRect()Landroid/graphics/RectF;
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return-object v0

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mVideoRect:Landroid/graphics/RectF;

    if-nez v1, :cond_1

    new-instance v1, Landroid/graphics/RectF;

    invoke-direct {v1}, Landroid/graphics/RectF;-><init>()V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mVideoRect:Landroid/graphics/RectF;

    :cond_1
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mVideoRect:Landroid/graphics/RectF;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    int-to-float v0, v0

    const/4 v3, 0x0

    invoke-virtual {v1, v3, v3, v2, v0}, Landroid/graphics/RectF;->set(FFFF)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getBaseMatrix()Landroid/graphics/Matrix;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mVideoRect:Landroid/graphics/RectF;

    invoke-virtual {v0, v1}, Landroid/graphics/Matrix;->mapRect(Landroid/graphics/RectF;)Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mVideoRect:Landroid/graphics/RectF;

    return-object v0
.end method

.method private removeTextView(Ljava/lang/String;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const-string v0, "VideoSubtitleManager"

    const-string v1, "removeTextView %s"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->removeView(Landroid/view/View;)V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    return-void
.end method

.method private updateTextViewLayout(Landroid/graphics/RectF;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutParams:Landroid/widget/RelativeLayout$LayoutParams;

    if-nez v0, :cond_0

    new-instance v0, Landroid/widget/RelativeLayout$LayoutParams;

    const/4 v1, -0x2

    invoke-direct {v0, v1, v1}, Landroid/widget/RelativeLayout$LayoutParams;-><init>(II)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutParams:Landroid/widget/RelativeLayout$LayoutParams;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutParams:Landroid/widget/RelativeLayout$LayoutParams;

    const/16 v1, 0xe

    invoke-virtual {v0, v1}, Landroid/widget/RelativeLayout$LayoutParams;->addRule(I)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutParams:Landroid/widget/RelativeLayout$LayoutParams;

    const/16 v1, 0xc

    invoke-virtual {v0, v1}, Landroid/widget/RelativeLayout$LayoutParams;->addRule(I)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getHeight()I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutHeight:I

    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutHeight:I

    int-to-float v0, v0

    iget v1, p1, Landroid/graphics/RectF;->bottom:F

    sub-float/2addr v0, v1

    float-to-int v0, v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mLayoutParams:Landroid/widget/RelativeLayout$LayoutParams;

    const/4 v2, 0x0

    invoke-virtual {v1, v2, v2, v2, v0}, Landroid/widget/RelativeLayout$LayoutParams;->setMargins(IIII)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {p1}, Landroid/graphics/RectF;->width()F

    move-result p1

    float-to-int p1, p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setMaxWidth(I)V

    return-void
.end method

.method private updateTextViewStyle(Landroid/graphics/RectF;)V
    .locals 6

    invoke-virtual {p1}, Landroid/graphics/RectF;->width()F

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/RectF;->height()F

    move-result p1

    div-float v1, p1, v0

    const v2, 0x3fd9999a    # 1.7f

    cmpl-float v1, v1, v2

    if-ltz v1, :cond_0

    const/16 v2, 0x28

    goto :goto_0

    :cond_0
    const/16 v2, 0x24

    :goto_0
    if-ltz v1, :cond_1

    const v3, 0x3e555555

    goto :goto_1

    :cond_1
    const v3, 0x3e749f4a

    :goto_1
    mul-float/2addr v3, v0

    float-to-int v3, v3

    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v4}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v4

    iget v4, v4, Landroid/content/res/Configuration;->orientation:I

    const/4 v5, 0x2

    if-ne v4, v5, :cond_3

    if-ltz v1, :cond_2

    const v1, 0x3d5097b4

    goto :goto_2

    :cond_2
    const v1, 0x3d82d82e

    :goto_2
    mul-float/2addr v1, p1

    float-to-int p1, v1

    const/high16 v1, 0x40000000    # 2.0f

    goto :goto_4

    :cond_3
    if-ltz v1, :cond_4

    const p1, 0x3d86a315

    goto :goto_3

    :cond_4
    const p1, 0x3cf2b9d6

    :goto_3
    mul-float/2addr p1, v0

    float-to-int p1, p1

    if-ltz v1, :cond_5

    const/high16 v1, 0x41100000    # 9.0f

    goto :goto_4

    :cond_5
    const/high16 v1, 0x40400000    # 3.0f

    :goto_4
    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    const/4 v5, 0x0

    invoke-virtual {v4, v3, v5, v3, p1}, Landroid/widget/TextView;->setPadding(IIII)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    int-to-float v2, v2

    mul-float/2addr v0, v2

    const/high16 v2, 0x44870000    # 1080.0f

    div-float/2addr v0, v2

    invoke-virtual {p1, v5, v0}, Landroid/widget/TextView;->setTextSize(IF)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    const/high16 v0, 0x3f800000    # 1.0f

    invoke-virtual {p1, v1, v0}, Landroid/widget/TextView;->setLineSpacing(FF)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    const v0, 0x3d19999a    # 0.0375f

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setLetterSpacing(F)V

    return-void
.end method


# virtual methods
.method onActionModeChanged(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    const/4 p1, 0x4

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setVisibility(I)V

    :cond_1
    return-void
.end method

.method onExit()V
    .locals 1

    const-string v0, "onExit"

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->removeTextView(Ljava/lang/String;)V

    return-void
.end method

.method onMatrixChanged(Landroid/graphics/RectF;)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    if-eqz v0, :cond_4

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->getBaseVideoRect()Landroid/graphics/RectF;

    move-result-object v0

    if-nez v0, :cond_1

    return-void

    :cond_1
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mBaseRect:Landroid/graphics/RectF;

    invoke-virtual {v0, v1}, Landroid/graphics/RectF;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mBaseRect:Landroid/graphics/RectF;

    if-nez p1, :cond_2

    new-instance p1, Landroid/graphics/RectF;

    invoke-direct {p1}, Landroid/graphics/RectF;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mBaseRect:Landroid/graphics/RectF;

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mBaseRect:Landroid/graphics/RectF;

    invoke-virtual {p1, v0}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->updateTextViewStyle(Landroid/graphics/RectF;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->updateTextViewLayout(Landroid/graphics/RectF;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {p1}, Landroid/widget/TextView;->requestLayout()V

    goto :goto_0

    :cond_3
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getWidth()I

    move-result v0

    if-eqz v0, :cond_4

    if-eqz p1, :cond_4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getScale()F

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {v1}, Landroid/widget/TextView;->getWidth()I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {v2}, Landroid/widget/TextView;->getHeight()I

    move-result v2

    int-to-float v2, v2

    mul-float/2addr v2, v0

    float-to-int v2, v2

    invoke-virtual {p1}, Landroid/graphics/RectF;->width()F

    move-result v3

    int-to-float v1, v1

    sub-float/2addr v3, v1

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v3, v1

    iget v1, p1, Landroid/graphics/RectF;->left:F

    add-float/2addr v3, v1

    float-to-int v1, v3

    invoke-virtual {p1}, Landroid/graphics/RectF;->height()F

    move-result v3

    int-to-float v2, v2

    sub-float/2addr v3, v2

    iget p1, p1, Landroid/graphics/RectF;->top:F

    add-float/2addr v3, p1

    float-to-int p1, v3

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {v2}, Landroid/widget/TextView;->getLeft()I

    move-result v3

    sub-int/2addr v1, v3

    int-to-float v1, v1

    invoke-virtual {v2, v1}, Landroid/widget/TextView;->setTranslationX(F)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {v1}, Landroid/widget/TextView;->getTop()I

    move-result v2

    sub-int/2addr p1, v2

    int-to-float p1, p1

    invoke-virtual {v1, p1}, Landroid/widget/TextView;->setTranslationY(F)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setScaleX(F)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setScaleY(F)V

    :cond_4
    :goto_0
    return-void
.end method

.method onUnSelected(Z)V
    .locals 0

    if-nez p1, :cond_0

    const-string p1, "onUnSelected"

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->removeTextView(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method release()V
    .locals 1

    const-string v0, "release"

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->removeTextView(Ljava/lang/String;)V

    return-void
.end method

.method update(Ljava/lang/String;Landroid/graphics/Typeface;)V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->addTextView()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    if-eqz v0, :cond_1

    if-eqz p2, :cond_0

    invoke-virtual {v0, p2}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;)V

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->mTextView:Landroid/widget/TextView;

    invoke-virtual {p2, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :cond_1
    return-void
.end method
