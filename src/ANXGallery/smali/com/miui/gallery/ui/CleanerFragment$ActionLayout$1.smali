.class Lcom/miui/gallery/ui/CleanerFragment$ActionLayout$1;
.super Ljava/lang/Object;
.source "CleanerFragment.java"

# interfaces
.implements Landroid/view/animation/Animation$AnimationListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/CleanerFragment$ActionLayout;->hide(Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/CleanerFragment$ActionLayout;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/CleanerFragment$ActionLayout;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerFragment$ActionLayout$1;->this$1:Lcom/miui/gallery/ui/CleanerFragment$ActionLayout;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/view/animation/Animation;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerFragment$ActionLayout$1;->this$1:Lcom/miui/gallery/ui/CleanerFragment$ActionLayout;

    invoke-static {p1}, Lcom/miui/gallery/ui/CleanerFragment$ActionLayout;->access$1000(Lcom/miui/gallery/ui/CleanerFragment$ActionLayout;)Landroid/view/View;

    move-result-object p1

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method

.method public onAnimationRepeat(Landroid/view/animation/Animation;)V
    .locals 0

    return-void
.end method

.method public onAnimationStart(Landroid/view/animation/Animation;)V
    .locals 0

    return-void
.end method
