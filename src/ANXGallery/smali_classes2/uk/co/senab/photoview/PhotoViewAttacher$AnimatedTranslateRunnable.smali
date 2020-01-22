.class Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;
.super Ljava/lang/Object;
.source "PhotoViewAttacher.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Luk/co/senab/photoview/PhotoViewAttacher;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "AnimatedTranslateRunnable"
.end annotation


# instance fields
.field private mCurrentX:I

.field private mCurrentY:I

.field private final mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

.field final synthetic this$0:Luk/co/senab/photoview/PhotoViewAttacher;


# direct methods
.method public constructor <init>(Luk/co/senab/photoview/PhotoViewAttacher;Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Lmiui/view/animation/CubicEaseOutInterpolator;

    invoke-direct {p1}, Lmiui/view/animation/CubicEaseOutInterpolator;-><init>()V

    invoke-static {p2, p1}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->getScroller(Landroid/content/Context;Landroid/view/animation/Interpolator;)Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    move-result-object p1

    iput-object p1, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    invoke-virtual {v0}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->isFinished()Z

    move-result v0

    const/16 v1, 0x16

    if-eqz v0, :cond_0

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0, v1}, Luk/co/senab/photoview/PhotoViewAttacher;->access$100(Luk/co/senab/photoview/PhotoViewAttacher;I)V

    return-void

    :cond_0
    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoViewAttacher;->getImageView()Landroid/widget/ImageView;

    move-result-object v0

    if-nez v0, :cond_1

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0, v1}, Luk/co/senab/photoview/PhotoViewAttacher;->access$100(Luk/co/senab/photoview/PhotoViewAttacher;I)V

    return-void

    :cond_1
    iget-object v2, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v2, v1}, Luk/co/senab/photoview/PhotoViewAttacher;->access$200(Luk/co/senab/photoview/PhotoViewAttacher;I)V

    iget-object v2, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    invoke-virtual {v2}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->computeScrollOffset()Z

    move-result v2

    if-eqz v2, :cond_2

    iget-object v1, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    invoke-virtual {v1}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->getCurrX()I

    move-result v1

    iget v2, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mCurrentX:I

    sub-int/2addr v1, v2

    iget-object v2, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    invoke-virtual {v2}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->getCurrY()I

    move-result v2

    iget v3, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mCurrentY:I

    sub-int/2addr v2, v3

    iget-object v3, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    invoke-virtual {v3}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->getCurrX()I

    move-result v3

    iput v3, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mCurrentX:I

    iget-object v3, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    invoke-virtual {v3}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->getCurrY()I

    move-result v3

    iput v3, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mCurrentY:I

    iget-object v3, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    int-to-float v1, v1

    int-to-float v2, v2

    invoke-static {v3, v1, v2}, Luk/co/senab/photoview/PhotoViewAttacher;->access$300(Luk/co/senab/photoview/PhotoViewAttacher;FF)V

    iget-object v1, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-virtual {v1}, Luk/co/senab/photoview/PhotoViewAttacher;->getDrawMatrix()Landroid/graphics/Matrix;

    move-result-object v2

    invoke-static {v1, v2}, Luk/co/senab/photoview/PhotoViewAttacher;->access$400(Luk/co/senab/photoview/PhotoViewAttacher;Landroid/graphics/Matrix;)V

    invoke-static {v0, p0}, Luk/co/senab/photoview/Compat;->postOnAnimation(Landroid/view/View;Ljava/lang/Runnable;)V

    goto :goto_0

    :cond_2
    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0, v1}, Luk/co/senab/photoview/PhotoViewAttacher;->access$100(Luk/co/senab/photoview/PhotoViewAttacher;I)V

    :goto_0
    return-void
.end method

.method public translate(IIII)Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;
    .locals 6

    iput p1, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mCurrentX:I

    iput p2, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mCurrentY:I

    if-nez p3, :cond_0

    if-eqz p4, :cond_1

    :cond_0
    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$AnimatedTranslateRunnable;->mScroller:Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;

    const/16 v5, 0x12c

    move v1, p1

    move v2, p2

    move v3, p3

    move v4, p4

    invoke-virtual/range {v0 .. v5}, Luk/co/senab/photoview/scrollerproxy/ScrollerProxy;->startScroll(IIIII)V

    :cond_1
    return-object p0
.end method
