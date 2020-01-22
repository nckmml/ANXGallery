.class Lcom/miui/gallery/video/editor/widget/AdjustView$2;
.super Ljava/lang/Object;
.source "AdjustView.java"

# interfaces
.implements Landroid/widget/SeekBar$OnSeekBarChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/video/editor/widget/AdjustView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

.field value:I


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/widget/AdjustView;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->value:I

    return-void
.end method


# virtual methods
.method public onProgressChanged(Landroid/widget/SeekBar;IZ)V
    .locals 2

    iput p2, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->value:I

    iget-object p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    const/4 p3, 0x1

    invoke-static {p1, p3}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$002(Lcom/miui/gallery/video/editor/widget/AdjustView;Z)Z

    iget-object p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    iget p3, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->value:I

    invoke-static {p1}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$200(Lcom/miui/gallery/video/editor/widget/AdjustView;)Ljava/util/List;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    invoke-static {v1}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$100(Lcom/miui/gallery/video/editor/widget/AdjustView;)Lcom/miui/gallery/video/editor/adapter/AdjustAdapter;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/video/editor/adapter/AdjustAdapter;->getSelection()I

    move-result v1

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/video/editor/model/AdjustData;

    invoke-static {p1, p3, v0}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$300(Lcom/miui/gallery/video/editor/widget/AdjustView;ILcom/miui/gallery/video/editor/model/AdjustData;)V

    if-nez p2, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/widget/AdjustView;->getContext()Landroid/content/Context;

    move-result-object p1

    sget-object p2, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->EFFECT_KEY_MESH_NORMAL:Ljava/lang/String;

    invoke-static {p1, p2}, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->performHapticFeedback(Landroid/content/Context;Ljava/lang/String;)Z

    :cond_0
    return-void
.end method

.method public onStartTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$002(Lcom/miui/gallery/video/editor/widget/AdjustView;Z)Z

    return-void
.end method

.method public onStopTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 3

    iget-object p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$002(Lcom/miui/gallery/video/editor/widget/AdjustView;Z)Z

    iget-object p1, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    iget v0, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->value:I

    invoke-static {p1}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$200(Lcom/miui/gallery/video/editor/widget/AdjustView;)Ljava/util/List;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/video/editor/widget/AdjustView$2;->this$0:Lcom/miui/gallery/video/editor/widget/AdjustView;

    invoke-static {v2}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$100(Lcom/miui/gallery/video/editor/widget/AdjustView;)Lcom/miui/gallery/video/editor/adapter/AdjustAdapter;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/gallery/video/editor/adapter/AdjustAdapter;->getSelection()I

    move-result v2

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/video/editor/model/AdjustData;

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/video/editor/widget/AdjustView;->access$300(Lcom/miui/gallery/video/editor/widget/AdjustView;ILcom/miui/gallery/video/editor/model/AdjustData;)V

    return-void
.end method
