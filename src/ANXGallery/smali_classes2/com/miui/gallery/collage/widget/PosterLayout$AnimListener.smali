.class Lcom/miui/gallery/collage/widget/PosterLayout$AnimListener;
.super Ljava/lang/Object;
.source "PosterLayout.java"

# interfaces
.implements Landroid/animation/Animator$AnimatorListener;
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/collage/widget/PosterLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "AnimListener"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/collage/widget/PosterLayout;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/collage/widget/PosterLayout;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/collage/widget/PosterLayout$AnimListener;->this$0:Lcom/miui/gallery/collage/widget/PosterLayout;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/collage/widget/PosterLayout;Lcom/miui/gallery/collage/widget/PosterLayout$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/collage/widget/PosterLayout$AnimListener;-><init>(Lcom/miui/gallery/collage/widget/PosterLayout;)V

    return-void
.end method


# virtual methods
.method public onAnimationCancel(Landroid/animation/Animator;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/collage/widget/PosterLayout$AnimListener;->this$0:Lcom/miui/gallery/collage/widget/PosterLayout;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/gallery/collage/widget/PosterLayout;->access$700(Lcom/miui/gallery/collage/widget/PosterLayout;F)V

    iget-object p1, p0, Lcom/miui/gallery/collage/widget/PosterLayout$AnimListener;->this$0:Lcom/miui/gallery/collage/widget/PosterLayout;

    invoke-virtual {p1}, Lcom/miui/gallery/collage/widget/PosterLayout;->invalidate()V

    return-void
.end method

.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 0

    return-void
.end method

.method public onAnimationRepeat(Landroid/animation/Animator;)V
    .locals 0

    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 0

    return-void
.end method

.method public onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 1

    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    iget-object v0, p0, Lcom/miui/gallery/collage/widget/PosterLayout$AnimListener;->this$0:Lcom/miui/gallery/collage/widget/PosterLayout;

    invoke-static {v0, p1}, Lcom/miui/gallery/collage/widget/PosterLayout;->access$700(Lcom/miui/gallery/collage/widget/PosterLayout;F)V

    iget-object p1, p0, Lcom/miui/gallery/collage/widget/PosterLayout$AnimListener;->this$0:Lcom/miui/gallery/collage/widget/PosterLayout;

    invoke-virtual {p1}, Lcom/miui/gallery/collage/widget/PosterLayout;->invalidate()V

    return-void
.end method