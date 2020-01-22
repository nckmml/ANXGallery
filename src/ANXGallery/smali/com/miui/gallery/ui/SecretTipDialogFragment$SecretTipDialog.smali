.class Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;
.super Landroid/app/Dialog;
.source "SecretTipDialogFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/SecretTipDialogFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "SecretTipDialog"
.end annotation


# instance fields
.field private mAnimationCanceled:Z

.field private mAnimatorSet:Landroid/animation/AnimatorSet;

.field private mContentContainer:Landroid/view/View;

.field private mContentPaddingUpdateListener:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

.field private mPositiveBtn:Landroid/widget/Button;

.field private mPositiveListener:Landroid/view/View$OnClickListener;

.field private mPressIndicator:Landroid/view/View;

.field final synthetic this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/ui/SecretTipDialogFragment;Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-direct {p0, p2}, Landroid/app/Dialog;-><init>(Landroid/content/Context;)V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimationCanceled:Z

    new-instance p1, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog$2;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog$2;-><init>(Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mContentPaddingUpdateListener:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimationCanceled:Z

    return p0
.end method

.method static synthetic access$202(Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimationCanceled:Z

    return p1
.end method

.method static synthetic access$300(Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mContentContainer:Landroid/view/View;

    return-object p0
.end method

.method private loadAnimator()Landroid/animation/AnimatorSet;
    .locals 15

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const/4 v1, 0x1

    const v2, 0x7f080003

    invoke-virtual {v0, v2, v1, v1}, Landroid/content/res/Resources;->getFraction(III)F

    move-result v0

    iget-object v2, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mPressIndicator:Landroid/view/View;

    const/4 v3, 0x3

    new-array v4, v3, [Landroid/animation/PropertyValuesHolder;

    const/4 v5, 0x2

    new-array v6, v5, [F

    const/4 v7, 0x0

    aput v0, v6, v7

    const/high16 v8, 0x3f800000    # 1.0f

    aput v8, v6, v1

    const-string v9, "scaleX"

    invoke-static {v9, v6}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v6

    aput-object v6, v4, v7

    new-array v6, v5, [F

    aput v0, v6, v7

    aput v8, v6, v1

    const-string v10, "scaleY"

    invoke-static {v10, v6}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v6

    aput-object v6, v4, v1

    new-array v6, v5, [F

    fill-array-data v6, :array_0

    const-string v11, "alpha"

    invoke-static {v11, v6}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v6

    aput-object v6, v4, v5

    invoke-static {v2, v4}, Landroid/animation/ObjectAnimator;->ofPropertyValuesHolder(Ljava/lang/Object;[Landroid/animation/PropertyValuesHolder;)Landroid/animation/ObjectAnimator;

    move-result-object v2

    new-instance v4, Lmiui/view/animation/CubicEaseInOutInterpolator;

    invoke-direct {v4}, Lmiui/view/animation/CubicEaseInOutInterpolator;-><init>()V

    invoke-virtual {v2, v4}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    iget-object v4, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v4}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    const v6, 0x7f0a0057

    invoke-virtual {v4, v6}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v4

    int-to-long v12, v4

    invoke-virtual {v2, v12, v13}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    iget-object v4, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v4}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    const v6, 0x7f0a0053

    invoke-virtual {v4, v6}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v4

    int-to-long v12, v4

    invoke-virtual {v2, v12, v13}, Landroid/animation/ObjectAnimator;->setStartDelay(J)V

    iget-object v4, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v4}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    const v6, 0x7f060592

    invoke-virtual {v4, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    iget-object v6, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v6}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    const v12, 0x7f060594

    invoke-virtual {v6, v12}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v6

    new-array v12, v5, [I

    aput v6, v12, v7

    aput v4, v12, v1

    invoke-static {v12}, Landroid/animation/ValueAnimator;->ofInt([I)Landroid/animation/ValueAnimator;

    move-result-object v12

    iget-object v13, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mContentPaddingUpdateListener:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    invoke-virtual {v12, v13}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    new-instance v13, Lmiui/view/animation/QuadraticEaseInOutInterpolator;

    invoke-direct {v13}, Lmiui/view/animation/QuadraticEaseInOutInterpolator;-><init>()V

    invoke-virtual {v12, v13}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    iget-object v13, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v13}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v13

    const v14, 0x7f0a0054

    invoke-virtual {v13, v14}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v13

    int-to-long v13, v13

    invoke-virtual {v12, v13, v14}, Landroid/animation/ValueAnimator;->setStartDelay(J)V

    iget-object v13, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v13}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v13

    const v14, 0x7f0a0059

    invoke-virtual {v13, v14}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v13

    int-to-long v13, v13

    invoke-virtual {v12, v13, v14}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    new-array v13, v5, [I

    aput v4, v13, v7

    aput v6, v13, v1

    invoke-static {v13}, Landroid/animation/ValueAnimator;->ofInt([I)Landroid/animation/ValueAnimator;

    move-result-object v4

    iget-object v6, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mContentPaddingUpdateListener:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    invoke-virtual {v4, v6}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    new-instance v6, Lmiui/view/animation/QuadraticEaseInOutInterpolator;

    invoke-direct {v6}, Lmiui/view/animation/QuadraticEaseInOutInterpolator;-><init>()V

    invoke-virtual {v4, v6}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    iget-object v6, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v6}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    const v13, 0x7f0a0055

    invoke-virtual {v6, v13}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v6

    int-to-long v13, v6

    invoke-virtual {v4, v13, v14}, Landroid/animation/ValueAnimator;->setStartDelay(J)V

    iget-object v6, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v6}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    const v13, 0x7f0a005a

    invoke-virtual {v6, v13}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v6

    int-to-long v13, v6

    invoke-virtual {v4, v13, v14}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    iget-object v6, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mPressIndicator:Landroid/view/View;

    new-array v13, v3, [Landroid/animation/PropertyValuesHolder;

    new-array v14, v5, [F

    aput v8, v14, v7

    aput v0, v14, v1

    invoke-static {v9, v14}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v9

    aput-object v9, v13, v7

    new-array v9, v5, [F

    aput v8, v9, v7

    aput v0, v9, v1

    invoke-static {v10, v9}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v0

    aput-object v0, v13, v1

    new-array v0, v5, [F

    fill-array-data v0, :array_1

    invoke-static {v11, v0}, Landroid/animation/PropertyValuesHolder;->ofFloat(Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object v0

    aput-object v0, v13, v5

    invoke-static {v6, v13}, Landroid/animation/ObjectAnimator;->ofPropertyValuesHolder(Ljava/lang/Object;[Landroid/animation/PropertyValuesHolder;)Landroid/animation/ObjectAnimator;

    move-result-object v0

    new-instance v6, Lmiui/view/animation/CubicEaseInOutInterpolator;

    invoke-direct {v6}, Lmiui/view/animation/CubicEaseInOutInterpolator;-><init>()V

    invoke-virtual {v0, v6}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    iget-object v6, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v6}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    const v8, 0x7f0a0058

    invoke-virtual {v6, v8}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v6

    int-to-long v8, v6

    invoke-virtual {v0, v8, v9}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    new-instance v6, Landroid/animation/AnimatorSet;

    invoke-direct {v6}, Landroid/animation/AnimatorSet;-><init>()V

    const/4 v8, 0x4

    new-array v8, v8, [Landroid/animation/Animator;

    aput-object v2, v8, v7

    aput-object v12, v8, v1

    aput-object v4, v8, v5

    aput-object v0, v8, v3

    invoke-virtual {v6, v8}, Landroid/animation/AnimatorSet;->playSequentially([Landroid/animation/Animator;)V

    return-object v6

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data

    :array_1
    .array-data 4
        0x3f800000    # 1.0f
        0x0
    .end array-data
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 2

    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v0

    const v1, 0x7f090269

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mPositiveListener:Landroid/view/View$OnClickListener;

    if-eqz v0, :cond_1

    invoke-interface {v0, p1}, Landroid/view/View$OnClickListener;->onClick(Landroid/view/View;)V

    :cond_1
    :goto_0
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2

    invoke-super {p0, p1}, Landroid/app/Dialog;->onCreate(Landroid/os/Bundle;)V

    const p1, 0x7f0b0190

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->setContentView(I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->access$000(Lcom/miui/gallery/ui/SecretTipDialogFragment;)Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;

    move-result-object p1

    const v0, 0x7f0900d2

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mContentContainer:Landroid/view/View;

    iget-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->access$000(Lcom/miui/gallery/ui/SecretTipDialogFragment;)Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;

    move-result-object p1

    const v0, 0x7f09026c

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mPressIndicator:Landroid/view/View;

    const p1, 0x7f090269

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/Button;

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mPositiveBtn:Landroid/widget/Button;

    iget-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mPositiveBtn:Landroid/widget/Button;

    invoke-virtual {p1, p0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/Window;->getAttributes()Landroid/view/WindowManager$LayoutParams;

    move-result-object v0

    const/16 v1, 0x50

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->gravity:I

    const/4 v1, -0x1

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->width:I

    const/4 v1, -0x2

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->height:I

    invoke-virtual {p1, v0}, Landroid/view/Window;->setAttributes(Landroid/view/WindowManager$LayoutParams;)V

    return-void
.end method

.method public onPause()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->isStarted()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->pause()V

    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->isPaused()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->resume()V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->startAnimation()V

    :goto_0
    return-void
.end method

.method public setPositiveButtonOnClickListener(Landroid/view/View$OnClickListener;)Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mPositiveListener:Landroid/view/View$OnClickListener;

    return-object p0
.end method

.method public startAnimation()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->stopAnimation()V

    iget-boolean v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimationCanceled:Z

    if-eqz v0, :cond_1

    return-void

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    if-nez v0, :cond_2

    invoke-direct {p0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->loadAnimator()Landroid/animation/AnimatorSet;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    new-instance v1, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog$1;-><init>(Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;)V

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    return-void
.end method

.method public stopAnimation()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;->mAnimatorSet:Landroid/animation/AnimatorSet;

    :cond_0
    return-void
.end method
