.class Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;
.super Ljava/lang/Object;
.source "WaterMarkFragment.java"

# interfaces
.implements Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onGlobalLayout()V
    .locals 5

    const-string v0, "WaterMarkFragment"

    const-string v1, "---onGlobalLayout start---"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v1}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2400(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getRootView()Landroid/view/View;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-virtual {v2}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-static {v2}, Lcom/miui/gallery/util/ScreenUtils;->getExactScreenHeight(Landroid/app/Activity;)I

    move-result v2

    invoke-virtual {v1}, Landroid/view/View;->getHeight()I

    move-result v3

    const/4 v4, 0x2

    new-array v4, v4, [I

    invoke-virtual {v1, v4}, Landroid/view/View;->getLocationOnScreen([I)V

    sub-int/2addr v2, v3

    const/4 v1, 0x1

    aget v3, v4, v1

    sub-int/2addr v2, v3

    iget-object v3, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v3}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2500(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)Z

    move-result v3

    if-nez v3, :cond_0

    if-lez v2, :cond_0

    const-string v3, "soft keyboard start to show!"

    invoke-static {v0, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v3, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v3, v1}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2502(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;Z)Z

    iget-object v3, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v3}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2600(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)Landroid/animation/ObjectAnimator;

    move-result-object v3

    invoke-virtual {v3}, Landroid/animation/ObjectAnimator;->start()V

    :cond_0
    if-nez v2, :cond_1

    iget-object v2, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v2}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2500(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)Z

    move-result v2

    if-eqz v2, :cond_1

    const-string v2, "soft keyboard start to dismiss!"

    invoke-static {v0, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2700(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$9;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2000(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)Z

    move-result v2

    xor-int/2addr v1, v2

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$2502(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;Z)Z

    :cond_1
    return-void
.end method
