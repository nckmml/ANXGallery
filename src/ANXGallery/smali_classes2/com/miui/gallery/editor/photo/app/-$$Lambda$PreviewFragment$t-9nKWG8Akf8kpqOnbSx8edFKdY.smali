.class public final synthetic Lcom/miui/gallery/editor/photo/app/-$$Lambda$PreviewFragment$t-9nKWG8Akf8kpqOnbSx8edFKdY;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/app/PreviewFragment;

.field private final synthetic f$1:Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/app/PreviewFragment;Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PreviewFragment$t-9nKWG8Akf8kpqOnbSx8edFKdY;->f$0:Lcom/miui/gallery/editor/photo/app/PreviewFragment;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PreviewFragment$t-9nKWG8Akf8kpqOnbSx8edFKdY;->f$1:Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    return-void
.end method


# virtual methods
.method public final onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PreviewFragment$t-9nKWG8Akf8kpqOnbSx8edFKdY;->f$0:Lcom/miui/gallery/editor/photo/app/PreviewFragment;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PreviewFragment$t-9nKWG8Akf8kpqOnbSx8edFKdY;->f$1:Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/editor/photo/app/PreviewFragment;->lambda$prepareShowEditFragment$108$PreviewFragment(Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;Landroid/animation/ValueAnimator;)V

    return-void
.end method
