.class Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;
.super Ljava/lang/Object;
.source "ScreenCropView.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/screen/base/ScreenBaseGestureView$FeatureGesListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "GesListener"
.end annotation


# instance fields
.field private mResizeStart:Landroid/graphics/PointF;

.field final synthetic this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Landroid/graphics/PointF;

    invoke-direct {p1}, Landroid/graphics/PointF;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->mResizeStart:Landroid/graphics/PointF;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;-><init>(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)V

    return-void
.end method


# virtual methods
.method public onActionUp(FF)V
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3000(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;

    move-result-object p1

    const/4 p2, 0x0

    invoke-static {p1, p2}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;->access$3102(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;I)I

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    sget-object v0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;->SKIP:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;

    invoke-static {p1, v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$2802(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3000(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$400(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Landroid/graphics/RectF;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {v1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3700(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$BoundsFixCallback;

    move-result-object v1

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;->fixImageBounds(Landroid/graphics/RectF;Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$BoundsFixCallback;)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {p1, p2}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3300(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;Z)V

    return-void
.end method

.method public onDown(Landroid/view/MotionEvent;)Z
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;->IDLE:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;

    invoke-static {v0, v1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$2802(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$2900(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3000(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {v1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3000(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;

    move-result-object v1

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v3

    invoke-static {v1, v2, v3}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;->access$3200(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;FF)I

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;->access$3102(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;I)I

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3000(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;->access$3100(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;)I

    move-result v0

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;->RESIZE:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;

    invoke-static {v0, v1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$2802(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$EventState;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->mResizeStart:Landroid/graphics/PointF;

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v1

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result p1

    invoke-virtual {v0, v1, p1}, Landroid/graphics/PointF;->set(FF)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3300(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;Z)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {p1, v0}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3402(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;Z)Z

    return v0
.end method

.method public onScale(Landroid/view/ScaleGestureDetector;)Z
    .locals 0

    const/4 p1, 0x0

    return p1
.end method

.method public onScaleBegin(Landroid/view/ScaleGestureDetector;)Z
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$4000(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)V

    const/4 p1, 0x1

    return p1
.end method

.method public onScaleEnd(Landroid/view/ScaleGestureDetector;)V
    .locals 0

    return-void
.end method

.method public onScroll(Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)V
    .locals 0

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result p1

    iget-object p3, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->mResizeStart:Landroid/graphics/PointF;

    iget p3, p3, Landroid/graphics/PointF;->x:F

    sub-float/2addr p1, p3

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getY()F

    move-result p2

    iget-object p3, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->mResizeStart:Landroid/graphics/PointF;

    iget p3, p3, Landroid/graphics/PointF;->y:F

    sub-float/2addr p2, p3

    iget-object p3, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {p3}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3000(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;

    move-result-object p3

    invoke-static {p3, p1, p2}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;->access$3500(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$ResizeDetector;FF)Landroid/graphics/PointF;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->mResizeStart:Landroid/graphics/PointF;

    iget p3, p2, Landroid/graphics/PointF;->x:F

    iget p4, p1, Landroid/graphics/PointF;->x:F

    add-float/2addr p3, p4

    iput p3, p2, Landroid/graphics/PointF;->x:F

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->mResizeStart:Landroid/graphics/PointF;

    iget p3, p2, Landroid/graphics/PointF;->y:F

    iget p1, p1, Landroid/graphics/PointF;->y:F

    add-float/2addr p3, p1

    iput p3, p2, Landroid/graphics/PointF;->y:F

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView$GesListener;->this$0:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;->access$3600(Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropView;)V

    return-void
.end method

.method public onSingleTapUp(Landroid/view/MotionEvent;)V
    .locals 0

    return-void
.end method
