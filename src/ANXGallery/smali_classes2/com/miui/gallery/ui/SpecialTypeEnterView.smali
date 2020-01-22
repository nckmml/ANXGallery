.class public Lcom/miui/gallery/ui/SpecialTypeEnterView;
.super Ljava/lang/Object;
.source "SpecialTypeEnterView.java"


# instance fields
.field private mCommonEnterView:Landroid/view/View;

.field private mEnterHideAnim:Landroid/view/animation/Animation;

.field private mEnterShowAnim:Landroid/view/animation/Animation;

.field private mMarginBottom:I

.field private mSystemWindowInsetBottom:I


# direct methods
.method public constructor <init>(Landroid/view/View;Landroid/view/View$OnClickListener;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    invoke-virtual {p1, p2}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method public constructor <init>(Landroid/view/ViewGroup;Landroid/view/View$OnClickListener;)V
    .locals 3

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const v1, 0x7f0b01a7

    const/4 v2, 0x0

    invoke-virtual {v0, v1, p1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    invoke-direct {p0, p1, v0, p2}, Lcom/miui/gallery/ui/SpecialTypeEnterView;-><init>(Landroid/view/ViewGroup;Landroid/view/View;Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method public constructor <init>(Landroid/view/ViewGroup;Landroid/view/View;Landroid/view/View$OnClickListener;)V
    .locals 0

    invoke-direct {p0, p2, p3}, Lcom/miui/gallery/ui/SpecialTypeEnterView;-><init>(Landroid/view/View;Landroid/view/View$OnClickListener;)V

    invoke-virtual {p1, p2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    return-void
.end method

.method public static buildCache(Landroid/content/Context;)Lcom/miui/gallery/util/RecyclerLayoutCache;
    .locals 6

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    new-instance v0, Lcom/miui/gallery/util/RecyclerLayoutCache;

    const/4 v1, 0x3

    invoke-direct {v0, v1}, Lcom/miui/gallery/util/RecyclerLayoutCache;-><init>(I)V

    new-instance v2, Landroid/widget/RelativeLayout;

    invoke-direct {v2, p0}, Landroid/widget/RelativeLayout;-><init>(Landroid/content/Context;)V

    invoke-static {p0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p0

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    if-ge v4, v1, :cond_1

    const v5, 0x7f0b01a7

    invoke-virtual {p0, v5, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v5

    invoke-virtual {v0, v5}, Lcom/miui/gallery/util/RecyclerLayoutCache;->put(Landroid/view/View;)V

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_1
    return-object v0
.end method

.method private getInsetBottom(Landroid/view/View;)I
    .locals 0

    invoke-static {p1}, Lcom/miui/gallery/compat/view/ViewCompat;->getSystemWindowInsetBottom(Landroid/view/View;)I

    move-result p1

    return p1
.end method

.method private setOnApplyWindowInsetsListener()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    new-instance v1, Lcom/miui/gallery/ui/-$$Lambda$SpecialTypeEnterView$l1adDHodAzcLmAgxbtThAGXmUds;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/-$$Lambda$SpecialTypeEnterView$l1adDHodAzcLmAgxbtThAGXmUds;-><init>(Lcom/miui/gallery/ui/SpecialTypeEnterView;)V

    invoke-static {v0, v1}, Landroidx/core/view/ViewCompat;->setOnApplyWindowInsetsListener(Landroid/view/View;Landroidx/core/view/OnApplyWindowInsetsListener;)V

    return-void
.end method

.method private startViewAlphaAnim(Landroid/view/View;Z)V
    .locals 2

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p1}, Landroid/view/View;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    if-eqz p2, :cond_0

    const/high16 p2, 0x3f800000    # 1.0f

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    :goto_0
    invoke-virtual {p1, p2}, Landroid/view/ViewPropertyAnimator;->alpha(F)Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    const-wide/16 v0, 0x0

    invoke-virtual {p1, v0, v1}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    new-instance p2, Lmiui/view/animation/SineEaseInOutInterpolator;

    invoke-direct {p2}, Lmiui/view/animation/SineEaseInOutInterpolator;-><init>()V

    invoke-virtual {p1, p2}, Landroid/view/ViewPropertyAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/ViewPropertyAnimator;->start()V

    :cond_1
    return-void
.end method

.method private updatePosition()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Landroid/view/ViewGroup$MarginLayoutParams;

    if-eqz v1, :cond_0

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->getInsetBottom(Landroid/view/View;)I

    move-result v2

    iget v3, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mMarginBottom:I

    add-int/2addr v2, v3

    iput v2, v1, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    invoke-virtual {v0, v1}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public getView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    return-object v0
.end method

.method public hide(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v1

    if-nez v1, :cond_2

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mEnterHideAnim:Landroid/view/animation/Animation;

    if-nez p1, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p1

    const v1, 0x7f010022

    invoke-static {p1, v1}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mEnterHideAnim:Landroid/view/animation/Animation;

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mEnterHideAnim:Landroid/view/animation/Animation;

    invoke-virtual {v0, p1}, Landroid/view/View;->startAnimation(Landroid/view/animation/Animation;)V

    :cond_1
    const/16 p1, 0x8

    invoke-virtual {v0, p1}, Landroid/view/View;->setVisibility(I)V

    :cond_2
    return-void
.end method

.method public isVisible()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public synthetic lambda$setOnApplyWindowInsetsListener$8$SpecialTypeEnterView(Landroid/view/View;Landroidx/core/view/WindowInsetsCompat;)Landroidx/core/view/WindowInsetsCompat;
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    invoke-static {p1}, Lcom/miui/gallery/compat/view/ViewCompat;->getSystemWindowInsetBottom(Landroid/view/View;)I

    move-result p1

    iget v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mSystemWindowInsetBottom:I

    if-eq p1, v0, :cond_0

    iput p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mSystemWindowInsetBottom:I

    invoke-direct {p0}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->updatePosition()V

    :cond_0
    return-object p2
.end method

.method public setVisible(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    const/16 p1, 0x8

    :goto_0
    invoke-virtual {v0, p1}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method

.method public show(Z)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v1

    const/16 v2, 0x8

    if-ne v1, v2, :cond_2

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mEnterShowAnim:Landroid/view/animation/Animation;

    if-nez p1, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p1

    const v1, 0x7f010021

    invoke-static {p1, v1}, Landroid/view/animation/AnimationUtils;->loadAnimation(Landroid/content/Context;I)Landroid/view/animation/Animation;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mEnterShowAnim:Landroid/view/animation/Animation;

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mEnterShowAnim:Landroid/view/animation/Animation;

    invoke-virtual {v0, p1}, Landroid/view/View;->startAnimation(Landroid/view/animation/Animation;)V

    :cond_1
    const/4 p1, 0x0

    invoke-virtual {v0, p1}, Landroid/view/View;->setVisibility(I)V

    :cond_2
    return-void
.end method

.method public startEnterAlphaAnim(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    invoke-direct {p0, v0, p1}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->startViewAlphaAnim(Landroid/view/View;Z)V

    return-void
.end method

.method public update(Landroid/view/ViewGroup;ZII)V
    .locals 3

    if-lez p3, :cond_5

    if-gtz p4, :cond_0

    goto :goto_1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mCommonEnterView:Landroid/view/View;

    instance-of v1, v0, Landroid/widget/ImageView;

    if-eqz v1, :cond_1

    move-object p4, v0

    check-cast p4, Landroid/widget/ImageView;

    invoke-virtual {p4, p3}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    :cond_1
    instance-of v1, v0, Landroid/view/ViewGroup;

    if-eqz v1, :cond_2

    move-object v1, v0

    check-cast v1, Landroid/view/ViewGroup;

    const v2, 0x7f090340

    invoke-virtual {v1, v2}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/TextView;

    invoke-virtual {v2, p4}, Landroid/widget/TextView;->setText(I)V

    invoke-virtual {v0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p4

    invoke-virtual {p4, p3}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p3

    const p4, 0x7f09019d

    invoke-virtual {v1, p4}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object p4

    check-cast p4, Landroid/widget/ImageView;

    invoke-virtual {p4, p3}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    :cond_2
    :goto_0
    const p3, 0x7f0605d6

    if-eqz p2, :cond_3

    invoke-static {}, Lcom/miui/gallery/util/IntentUtil;->isSupportNewVideoPlayer()Z

    move-result p2

    if-eqz p2, :cond_3

    const p3, 0x7f0605de

    :cond_3
    invoke-virtual {v0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/ui/SpecialTypeEnterView;->mMarginBottom:I

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p2

    if-nez p2, :cond_4

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    :cond_4
    invoke-direct {p0}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->updatePosition()V

    invoke-direct {p0}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->setOnApplyWindowInsetsListener()V

    :cond_5
    :goto_1
    return-void
.end method
