.class public final synthetic Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenAnimatorHelper$bCqwFSBZ7F7QqqT7EnpmcuGI0ek;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

.field private final synthetic f$1:I


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenAnimatorHelper$bCqwFSBZ7F7QqqT7EnpmcuGI0ek;->f$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    iput p2, p0, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenAnimatorHelper$bCqwFSBZ7F7QqqT7EnpmcuGI0ek;->f$1:I

    return-void
.end method


# virtual methods
.method public final onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenAnimatorHelper$bCqwFSBZ7F7QqqT7EnpmcuGI0ek;->f$0:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    iget v1, p0, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenAnimatorHelper$bCqwFSBZ7F7QqqT7EnpmcuGI0ek;->f$1:I

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->lambda$startMenuAnimator$83$ScreenAnimatorHelper(ILandroid/animation/ValueAnimator;)V

    return-void
.end method
