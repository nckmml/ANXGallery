.class Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;
.super Landroid/animation/AnimatorListenerAdapter;
.source "CleanerItemAnimator.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;


# direct methods
.method constructor <init>(Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object p1, p1, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->this$0:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object v0, v0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->val$holder:Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;->dispatchMoveFinished(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V

    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object p1, p1, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->this$0:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;

    invoke-static {p1}, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;->access$100(Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;)Ljava/util/ArrayList;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object v0, v0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->val$holder:Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object p1, p1, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->this$0:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;->isRunning()Z

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object p1, p1, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->this$0:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;->dispatchAnimationsFinished()V

    :cond_0
    return-void
.end method

.method public onAnimationStart(Landroid/animation/Animator;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object p1, p1, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->this$0:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1$1;->this$1:Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;

    iget-object v0, v0, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator$1;->val$holder:Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/recyclerview/CleanerItemAnimator;->dispatchMoveStarting(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V

    return-void
.end method
