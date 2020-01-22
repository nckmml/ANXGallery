.class Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;
.super Lcom/miui/gallery/ui/PhotoPageItem$AbsPhotoRectAwareManager;
.source "PhotoPageImageItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageImageItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "HigherDefinitionManager"
.end annotation


# instance fields
.field private mCurActionBarHeight:I

.field private mDefaultMarginTop:I

.field private mDefinitionStateView:Landroid/widget/TextView;

.field private mHRPreviewTipsRes:I

.field private mHandler:Landroid/os/Handler;

.field private mHasHdViewDisplayed:Z

.field private mHideDefinitionRunnable:Ljava/lang/Runnable;

.field private mImageType:Lcom/miui/gallery/model/ImageType;

.field private mIsEverEnterHd:Z

.field private mIsScreenshotReceiverRegistered:Z

.field private mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

.field private mNavMapAnimator:Landroid/animation/ObjectAnimator;

.field private mNavMapMarginBottom:I

.field private mNavMapMarginRight:I

.field private mNavMapTransX:F

.field private mNavMapTransY:F

.field private mOnScaleLevelChangedListener:Luk/co/senab/photoview/PhotoViewAttacher$OnScaleLevelChangedListener;

.field private mParams:Landroid/view/ViewGroup$MarginLayoutParams;

.field private mPhotoViewNavMapBridge:Lcom/miui/gallery/ui/PhotoViewNavMapBridge;

.field private mTakeScreenshotReceiver:Landroid/content/BroadcastReceiver;

.field private mTapForHRPreviewTipsRes:I

.field private mTipsHorizontalMargin:I

.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/ui/PhotoPageImageItem;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem$AbsPhotoRectAwareManager;-><init>(Lcom/miui/gallery/ui/PhotoPageItem;)V

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMainHandler()Lcom/android/internal/CompatHandler;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHandler:Landroid/os/Handler;

    sget-object p1, Lcom/miui/gallery/model/ImageType;->NORMAL:Lcom/miui/gallery/model/ImageType;

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$1;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHideDefinitionRunnable:Ljava/lang/Runnable;

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$5;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$5;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mOnScaleLevelChangedListener:Luk/co/senab/photoview/PhotoViewAttacher$OnScaleLevelChangedListener;

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$6;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$6;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTakeScreenshotReceiver:Landroid/content/BroadcastReceiver;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/PhotoPageImageItem;Lcom/miui/gallery/ui/PhotoPageImageItem$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem;)V

    return-void
.end method

.method static synthetic access$1300(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->hideDefinitionText()V

    return-void
.end method

.method static synthetic access$1400(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$1500(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTipsHorizontalMargin:I

    return p0
.end method

.method static synthetic access$1600(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)Landroid/view/ViewGroup$MarginLayoutParams;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mParams:Landroid/view/ViewGroup$MarginLayoutParams;

    return-object p0
.end method

.method static synthetic access$1700(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->changeToMidScale()V

    return-void
.end method

.method static synthetic access$1800(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->exitHigherDefinition()V

    return-void
.end method

.method static synthetic access$1900(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->changeToHigherDefinition(Z)V

    return-void
.end method

.method static synthetic access$2000(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;ZZ)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->recordEnterExitHighScaleLevel(ZZ)V

    return-void
.end method

.method static synthetic access$2100(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->hideNavMap()V

    return-void
.end method

.method static synthetic access$2200(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->needShowMiniNavMap()Z

    move-result p0

    return p0
.end method

.method static synthetic access$2300(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;ZZ)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->showNavMap(ZZ)V

    return-void
.end method

.method private cancelNavMapAnim()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/widget/MiniNavMap;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->cancel()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {v0}, Lcom/miui/gallery/widget/MiniNavMap;->getTranslationX()F

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapTransX:F

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {v0}, Lcom/miui/gallery/widget/MiniNavMap;->getTranslationY()F

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapTransY:F

    :cond_0
    return-void
.end method

.method private changeToHigherDefinition(Z)V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHasHdViewDisplayed:Z

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    iget v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHRPreviewTipsRes:I

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(I)V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHasHdViewDisplayed:Z

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mIsEverEnterHd:Z

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->showDefinitionText()V

    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->showNavMap(Z)V

    return-void
.end method

.method private changeToMidScale()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTapForHRPreviewTipsRes:I

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(I)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->showDefinitionText()V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->hideNavMap()V

    return-void
.end method

.method private doAdjustLocation(ZZ)V
    .locals 4

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->cancelNavMapAnim()V

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getMenuBarHeight()I

    move-result v1

    int-to-float v1, v1

    goto :goto_0

    :cond_0
    move v1, v0

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->getMaxTranslationY()F

    move-result v2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->getVerticalMargin()I

    move-result v3

    int-to-float v3, v3

    add-float/2addr v1, v3

    invoke-static {v2, v1}, Ljava/lang/Math;->min(FF)F

    move-result v1

    sub-float v1, v0, v1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->getMaxTranslationX()F

    move-result v2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->getHorizontalMargin()I

    move-result v3

    int-to-float v3, v3

    invoke-static {v2, v3}, Ljava/lang/Math;->min(FF)F

    move-result v2

    sub-float/2addr v0, v2

    iget v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapTransY:F

    cmpl-float v2, v2, v1

    if-nez v2, :cond_1

    iget v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapTransX:F

    cmpl-float v2, v2, v0

    if-eqz v2, :cond_3

    :cond_1
    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapTransX:F

    iput v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapTransY:F

    if-eqz p2, :cond_2

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {p2}, Lcom/miui/gallery/widget/MiniNavMap;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object p2

    invoke-virtual {p2, v0}, Landroid/view/ViewPropertyAnimator;->translationX(F)Landroid/view/ViewPropertyAnimator;

    move-result-object p2

    invoke-virtual {p2, v1}, Landroid/view/ViewPropertyAnimator;->translationY(F)Landroid/view/ViewPropertyAnimator;

    move-result-object p2

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->getAdjustAnimDuration(Z)I

    move-result v0

    int-to-long v0, v0

    invoke-virtual {p2, v0, v1}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object p2

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->getAdjustAnimInterpolator(Z)Landroid/view/animation/Interpolator;

    move-result-object p1

    invoke-virtual {p2, p1}, Landroid/view/ViewPropertyAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/ViewPropertyAnimator;->start()V

    goto :goto_1

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/MiniNavMap;->setTranslationX(F)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/widget/MiniNavMap;->setTranslationY(F)V

    :cond_3
    :goto_1
    return-void
.end method

.method private endNavMapShowingAnim()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->isRunning()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    invoke-virtual {v0}, Landroid/animation/ObjectAnimator;->end()V

    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    :cond_1
    return-void
.end method

.method private exitHigherDefinition()V
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHasHdViewDisplayed:Z

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->hideDefinitionText()V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->hideNavMap()V

    return-void
.end method

.method private hideDefinitionText()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    :cond_0
    return-void
.end method

.method private hideNavMap()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/widget/MiniNavMap;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/MiniNavMap;->setVisibility(I)V

    :cond_0
    return-void
.end method

.method private initHdTextView()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const v1, 0x7f0b0137

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    const/4 v3, 0x1

    invoke-virtual {v0, v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    const v1, 0x7f090186

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/PhotoPageImageItem;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mParams:Landroid/view/ViewGroup$MarginLayoutParams;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06035d

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefaultMarginTop:I

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06035c

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTipsHorizontalMargin:I

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mParams:Landroid/view/ViewGroup$MarginLayoutParams;

    iget v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTipsHorizontalMargin:I

    iget v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefaultMarginTop:I

    iget v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mCurActionBarHeight:I

    add-int/2addr v2, v3

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v1, v3}, Landroid/view/ViewGroup$MarginLayoutParams;->setMargins(IIII)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mParams:Landroid/view/ViewGroup$MarginLayoutParams;

    check-cast v0, Landroid/widget/RelativeLayout$LayoutParams;

    const/16 v1, 0xe

    const/4 v2, -0x1

    invoke-virtual {v0, v1, v2}, Landroid/widget/RelativeLayout$LayoutParams;->addRule(II)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->requestLayout()V

    :cond_0
    return-void
.end method

.method private initNavMap()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const v1, 0x7f0b013e

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/widget/MiniNavMap;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    new-instance v0, Landroid/widget/RelativeLayout$LayoutParams;

    const/4 v1, -0x2

    invoke-direct {v0, v1, v1}, Landroid/widget/RelativeLayout$LayoutParams;-><init>(II)V

    const/16 v1, 0xb

    invoke-virtual {v0, v1}, Landroid/widget/RelativeLayout$LayoutParams;->addRule(I)V

    const/16 v1, 0xc

    invoke-virtual {v0, v1}, Landroid/widget/RelativeLayout$LayoutParams;->addRule(I)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {v1, v2, v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mPhotoViewNavMapBridge:Lcom/miui/gallery/ui/PhotoViewNavMapBridge;

    if-nez v0, :cond_1

    new-instance v0, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;

    invoke-direct {v0}, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mPhotoViewNavMapBridge:Lcom/miui/gallery/ui/PhotoViewNavMapBridge;

    goto :goto_0

    :cond_1
    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->disconnect()V

    :goto_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mPhotoViewNavMapBridge:Lcom/miui/gallery/ui/PhotoViewNavMapBridge;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v3, v3, Lcom/miui/gallery/ui/PhotoPageImageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v3}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result v3

    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v4, v4, Lcom/miui/gallery/ui/PhotoPageImageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v4}, Lcom/miui/gallery/model/BaseDataItem;->getHeight()I

    move-result v4

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v3

    invoke-virtual {v0, v1, v2, v3}, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->connect(Luk/co/senab/photoview/PhotoView;Lcom/miui/gallery/widget/MiniNavMap;I)V

    return-void
.end method

.method private needShowMiniNavMap()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->isAnimEntering()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->isAnimExiting()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->isRotating()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->isDrawableDisplayInside()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getScaleLevel()Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    move-result-object v0

    sget-object v1, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->HIGH:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private recordEnterExitHighScaleLevel(ZZ)V
    .locals 3

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v1}, Lcom/miui/gallery/model/ImageType;->name()Ljava/lang/String;

    move-result-object v1

    const-string v2, "imageType"

    invoke-interface {v0, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz p2, :cond_0

    const-string p2, "doubleTap"

    goto :goto_0

    :cond_0
    const-string p2, "gestureScale"

    :goto_0
    const-string v1, "triggerBy"

    invoke-interface {v0, v1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz p1, :cond_1

    const-string p1, "photo_enter_hd_preview"

    goto :goto_1

    :cond_1
    const-string p1, "photo_exit_hd_preview"

    :goto_1
    const-string p2, "photo"

    invoke-static {p2, p1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private registerScreenshotReceiver()V
    .locals 3

    iget-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mIsScreenshotReceiverRegistered:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mIsScreenshotReceiverRegistered:Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTakeScreenshotReceiver:Landroid/content/BroadcastReceiver;

    const-string v2, "miui.intent.TAKE_SCREENSHOT"

    filled-new-array {v2}, [Ljava/lang/String;

    move-result-object v2

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/ReceiverUtils;->registerReceiver(Landroid/content/Context;Landroid/content/BroadcastReceiver;[Ljava/lang/String;)V

    return-void
.end method

.method private showDefinitionText()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/widget/TextView;->getText()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getText()Ljava/lang/CharSequence;

    move-result-object v1

    invoke-interface {v1}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/TalkBackUtil;->requestAnnouncementEvent(Landroid/view/View;Ljava/lang/String;)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->cancel()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setAlpha(F)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setSelected(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->alpha(F)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const-wide/16 v1, 0x12c

    invoke-virtual {v0, v1, v2}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    new-instance v1, Lmiui/view/animation/SineEaseInOutInterpolator;

    invoke-direct {v1}, Lmiui/view/animation/SineEaseInOutInterpolator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const-wide/16 v1, 0x15e

    invoke-virtual {v0, v1, v2}, Landroid/view/ViewPropertyAnimator;->setStartDelay(J)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$2;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$2;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setListener(Landroid/animation/Animator$AnimatorListener;)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->start()V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHideDefinitionRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHideDefinitionRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0xbb8

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    :cond_2
    return-void
.end method

.method private showNavMap(Z)V
    .locals 1

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->showNavMap(ZZ)V

    return-void
.end method

.method private showNavMap(ZZ)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/widget/MiniNavMap;->getVisibility()I

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/MiniNavMap;->setVisibility(I)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->endNavMapShowingAnim()V

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    const/4 v0, 0x2

    new-array v0, v0, [F

    fill-array-data v0, :array_0

    const-string v2, "Alpha"

    invoke-static {p2, v2, v0}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    const/4 v0, 0x1

    invoke-virtual {p2, v0}, Landroid/animation/ObjectAnimator;->setAutoCancel(Z)V

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$3;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;)V

    invoke-virtual {p2, v0}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    const-wide/16 v2, 0x258

    invoke-virtual {p1, v2, v3}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    sget-object p2, Lcom/miui/gallery/ui/PhotoPageItem;->ZOOM_INTERPOLATOR:Landroid/view/animation/Interpolator;

    invoke-virtual {p1, p2}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    const-wide/16 v2, 0x96

    invoke-virtual {p1, v2, v3}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapAnimator:Landroid/animation/ObjectAnimator;

    invoke-virtual {p1}, Landroid/animation/ObjectAnimator;->start()V

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageImageItem;->isActionBarVisible()Z

    move-result p1

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object p2, p2, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {p2}, Luk/co/senab/photoview/PhotoView;->getDisplayRect()Landroid/graphics/RectF;

    move-result-object p2

    invoke-virtual {p0, p1, p2, v1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->adjustLocation(ZLandroid/graphics/RectF;Z)V

    :cond_2
    return-void

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method

.method private translateByActionBarVisibility(I)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefaultMarginTop:I

    iget v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mCurActionBarHeight:I

    add-int/2addr v1, v2

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    new-instance v0, Landroid/view/animation/TranslateAnimation;

    int-to-float p1, p1

    const/4 v2, 0x0

    invoke-direct {v0, v2, v2, v2, p1}, Landroid/view/animation/TranslateAnimation;-><init>(FFFF)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v2, 0x10e0001

    invoke-virtual {p1, v2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p1

    int-to-long v2, p1

    invoke-virtual {v0, v2, v3}, Landroid/view/animation/TranslateAnimation;->setDuration(J)V

    new-instance p1, Landroid/view/animation/DecelerateInterpolator;

    invoke-direct {p1}, Landroid/view/animation/DecelerateInterpolator;-><init>()V

    invoke-virtual {v0, p1}, Landroid/view/animation/TranslateAnimation;->setInterpolator(Landroid/view/animation/Interpolator;)V

    const/4 p1, 0x1

    invoke-virtual {v0, p1}, Landroid/view/animation/TranslateAnimation;->setFillAfter(Z)V

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$4;

    invoke-direct {p1, p0, v1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager$4;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;I)V

    invoke-virtual {v0, p1}, Landroid/view/animation/TranslateAnimation;->setAnimationListener(Landroid/view/animation/Animation$AnimationListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->startAnimation(Landroid/view/animation/Animation;)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mParams:Landroid/view/ViewGroup$MarginLayoutParams;

    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTipsHorizontalMargin:I

    const/4 v2, 0x0

    invoke-virtual {p1, v0, v1, v0, v2}, Landroid/view/ViewGroup$MarginLayoutParams;->setMargins(IIII)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {p1}, Landroid/widget/TextView;->requestLayout()V

    :goto_0
    return-void
.end method

.method private unregisterScreenshotReceiver()V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mIsScreenshotReceiverRegistered:Z

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mIsScreenshotReceiverRegistered:Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTakeScreenshotReceiver:Landroid/content/BroadcastReceiver;

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ReceiverUtils;->safeUnregisterReceiver(Landroid/content/Context;Landroid/content/BroadcastReceiver;)Z

    return-void
.end method


# virtual methods
.method public adjustLocation(ZLandroid/graphics/RectF;Z)V
    .locals 0

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Lcom/miui/gallery/widget/MiniNavMap;->getVisibility()I

    move-result p2

    if-nez p2, :cond_0

    invoke-direct {p0, p1, p3}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->doAdjustLocation(ZZ)V

    :cond_0
    return-void
.end method

.method protected changeVisibilityForSpecialScene()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->needShowMiniNavMap()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->isPagerSelected()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->showNavMap(Z)V

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->hideNavMap()V

    :goto_0
    return-void
.end method

.method protected getHorizontalMargin()I
    .locals 2

    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0604b7

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    iget v0, v0, Landroid/content/res/Configuration;->orientation:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->isActionBarVisible()Z

    move-result v0

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-static {v1}, Lcom/miui/gallery/compat/view/ViewCompat;->getSystemWindowInsetRight(Landroid/view/View;)I

    move-result v1

    :goto_0
    add-int/2addr v0, v1

    return v0

    :cond_1
    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-static {v1}, Lcom/android/internal/WindowInsetsCompat;->getDisplayCutoutInsetsRight(Landroid/view/View;)I

    move-result v1

    goto :goto_0

    :cond_2
    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    return v0
.end method

.method protected getVerticalMargin()I
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->isActionBarVisible()Z

    move-result v0

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0604b7

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    :cond_0
    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginRight:I

    return v0

    :cond_1
    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginBottom:I

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageImageItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0604b6

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginBottom:I

    :cond_2
    iget v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMapMarginBottom:I

    return v0
.end method

.method public onActionBarVisibleChanged(Ljava/lang/Boolean;Landroid/graphics/RectF;I)V
    .locals 4

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    move v0, p3

    goto :goto_0

    :cond_0
    move v0, v1

    :goto_0
    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mCurActionBarHeight:I

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    invoke-direct {p0, p3}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->translateByActionBarVisibility(I)V

    iget-object p3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object p3, p3, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {p3, v1}, Luk/co/senab/photoview/PhotoView;->setDragInsideBoundsEnabled(Z)V

    goto :goto_1

    :cond_1
    neg-int p3, p3

    invoke-direct {p0, p3}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->translateByActionBarVisibility(I)V

    iget-object p3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object p3, p3, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getScaleLevel()Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    move-result-object v0

    sget-object v3, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->HIGH:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    if-ne v0, v3, :cond_2

    move v1, v2

    :cond_2
    invoke-virtual {p3, v1}, Luk/co/senab/photoview/PhotoView;->setDragInsideBoundsEnabled(Z)V

    :goto_1
    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    invoke-virtual {p0, p1, p2, v2}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->adjustLocation(ZLandroid/graphics/RectF;Z)V

    return-void
.end method

.method public onSelected()V
    .locals 5

    invoke-super {p0}, Lcom/miui/gallery/ui/PhotoPageItem$AbsPhotoRectAwareManager;->onSelected()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    move v0, v1

    move v2, v0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result v0

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v2, v2, Lcom/miui/gallery/ui/PhotoPageImageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v2}, Lcom/miui/gallery/model/BaseDataItem;->getHeight()I

    move-result v2

    :goto_0
    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v3, v3, Lcom/miui/gallery/ui/PhotoPageImageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v3, :cond_2

    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v3, v3, Lcom/miui/gallery/ui/PhotoPageImageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v3}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_1

    goto :goto_1

    :cond_1
    invoke-static {v0, v2}, Lcom/miui/gallery/model/ImageType;->of(II)Lcom/miui/gallery/model/ImageType;

    move-result-object v3

    iput-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    goto :goto_2

    :cond_2
    :goto_1
    sget-object v3, Lcom/miui/gallery/model/ImageType;->NORMAL:Lcom/miui/gallery/model/ImageType;

    iput-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    :goto_2
    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    sget-object v4, Lcom/miui/gallery/model/ImageType;->HIGH_RESOLUTION:Lcom/miui/gallery/model/ImageType;

    if-ne v3, v4, :cond_3

    const v3, 0x7f100522

    iput v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHRPreviewTipsRes:I

    const v3, 0x7f1003a0

    iput v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTapForHRPreviewTipsRes:I

    goto :goto_3

    :cond_3
    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    sget-object v4, Lcom/miui/gallery/model/ImageType;->HIGH_RESOLUTION_108M:Lcom/miui/gallery/model/ImageType;

    if-ne v3, v4, :cond_4

    const v3, 0x7f1004f9

    iput v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHRPreviewTipsRes:I

    const v3, 0x7f10039f

    iput v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mTapForHRPreviewTipsRes:I

    :cond_4
    :goto_3
    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v3, v3, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v4}, Lcom/miui/gallery/model/ImageType;->isHighResolution()Z

    move-result v4

    invoke-virtual {v3, v0, v2, v4}, Luk/co/senab/photoview/PhotoView;->setHDState(IIZ)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v2}, Lcom/miui/gallery/model/ImageType;->isNavMapSupported()Z

    move-result v2

    if-eqz v2, :cond_5

    const/high16 v2, 0x3f800000    # 1.0f

    goto :goto_4

    :cond_5
    const/high16 v2, 0x3fc00000    # 1.5f

    :goto_4
    invoke-virtual {v0, v2}, Luk/co/senab/photoview/PhotoView;->setPointsScaleEnlargeFactor(F)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v0}, Lcom/miui/gallery/model/ImageType;->isHighResolution()Z

    move-result v0

    if-nez v0, :cond_7

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v0}, Lcom/miui/gallery/model/ImageType;->isNavMapSupported()Z

    move-result v0

    if-eqz v0, :cond_6

    goto :goto_5

    :cond_6
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Luk/co/senab/photoview/PhotoView;->setOnScaleStageChangedListener(Luk/co/senab/photoview/PhotoViewAttacher$OnScaleLevelChangedListener;)V

    goto :goto_6

    :cond_7
    :goto_5
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mOnScaleLevelChangedListener:Luk/co/senab/photoview/PhotoViewAttacher$OnScaleLevelChangedListener;

    invoke-virtual {v0, v2}, Luk/co/senab/photoview/PhotoView;->setOnScaleStageChangedListener(Luk/co/senab/photoview/PhotoViewAttacher$OnScaleLevelChangedListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v0}, Lcom/miui/gallery/model/ImageType;->isHighResolution()Z

    move-result v0

    if-eqz v0, :cond_8

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->initHdTextView()V

    :cond_8
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v0}, Lcom/miui/gallery/model/ImageType;->isNavMapSupported()Z

    move-result v0

    if-eqz v0, :cond_9

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->initNavMap()V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->needShowMiniNavMap()Z

    move-result v0

    if-eqz v0, :cond_9

    invoke-direct {p0, v1}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->showNavMap(Z)V

    :cond_9
    :goto_6
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->registerScreenshotReceiver()V

    return-void
.end method

.method public onUnSelected()V
    .locals 0

    invoke-super {p0}, Lcom/miui/gallery/ui/PhotoPageItem$AbsPhotoRectAwareManager;->onUnSelected()V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->release()V

    return-void
.end method

.method public release()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/ui/PhotoPageItem$AbsPhotoRectAwareManager;->release()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    const/16 v1, 0x8

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/widget/TextView;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->cancel()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->clearAnimation()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mDefinitionStateView:Landroid/widget/TextView;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/widget/MiniNavMap;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->cancel()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mNavMap:Lcom/miui/gallery/widget/MiniNavMap;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/MiniNavMap;->setVisibility(I)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mPhotoViewNavMapBridge:Lcom/miui/gallery/ui/PhotoViewNavMapBridge;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoViewNavMapBridge;->disconnect()V

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHideDefinitionRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    invoke-virtual {v0}, Lcom/miui/gallery/model/ImageType;->isHighResolution()Z

    move-result v0

    if-eqz v0, :cond_3

    const-string v0, "photo"

    const-string v1, "photo_hd_satisfy_count"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    iget-boolean v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mIsEverEnterHd:Z

    if-eqz v1, :cond_3

    const-string v1, "photo_hd_enter_count"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    :cond_3
    sget-object v0, Lcom/miui/gallery/model/ImageType;->NORMAL:Lcom/miui/gallery/model/ImageType;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mImageType:Lcom/miui/gallery/model/ImageType;

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mHasHdViewDisplayed:Z

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->mIsEverEnterHd:Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageImageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageImageItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Luk/co/senab/photoview/PhotoView;->setOnScaleStageChangedListener(Luk/co/senab/photoview/PhotoViewAttacher$OnScaleLevelChangedListener;)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$HigherDefinitionManager;->unregisterScreenshotReceiver()V

    return-void
.end method
