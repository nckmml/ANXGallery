.class Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;
.super Ljava/lang/Object;
.source "BaseMovieView.java"

# interfaces
.implements Landroid/view/View$OnTouchListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/movie/ui/view/BaseMovieView;->init()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private changed:Z

.field private downX:F

.field private dx:F

.field private layout:I

.field private moveX:F

.field final synthetic this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;


# direct methods
.method constructor <init>(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->downX:F

    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->moveX:F

    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->dx:F

    const/4 p1, -0x1

    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->layout:I

    return-void
.end method


# virtual methods
.method public onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z
    .locals 4

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$100(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)Z

    move-result p1

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return v0

    :cond_0
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getAction()I

    move-result p1

    if-eqz p1, :cond_6

    const/4 v1, 0x1

    if-eq p1, v1, :cond_3

    const/4 v2, 0x2

    if-eq p1, v2, :cond_1

    const/4 p2, 0x3

    if-eq p1, p2, :cond_3

    goto/16 :goto_1

    :cond_1
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->moveX:F

    iget p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->moveX:F

    iget p2, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->downX:F

    sub-float/2addr p1, p2

    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->dx:F

    iget p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->dx:F

    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result p1

    iget-object p2, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {p2}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$200(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)I

    move-result p2

    int-to-float p2, p2

    cmpl-float p1, p1, p2

    if-lez p1, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$300(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)F

    move-result p1

    const/4 p2, 0x0

    cmpl-float p1, p1, p2

    if-lez p1, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    iget v2, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->dx:F

    invoke-static {v2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {v3}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$300(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)F

    move-result v3

    div-float/2addr v2, v3

    invoke-static {p1, v2}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$402(Lcom/miui/gallery/movie/ui/view/BaseMovieView;F)F

    iget p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->dx:F

    cmpg-float p1, p1, p2

    if-gez p1, :cond_2

    move p1, v0

    goto :goto_0

    :cond_2
    move p1, v1

    :goto_0
    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->layout:I

    iput-boolean v1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->changed:Z

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$500(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)Lcom/miui/gallery/movie/ui/view/BaseMovieView$IProgressChangeListener;

    move-result-object p1

    if-eqz p1, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$500(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)Lcom/miui/gallery/movie/ui/view/BaseMovieView$IProgressChangeListener;

    move-result-object p1

    iget p2, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->layout:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {v1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$400(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)F

    move-result v1

    invoke-interface {p1, p2, v1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView$IProgressChangeListener;->onVideoProgressChanging(IF)V

    goto :goto_1

    :cond_3
    iget-boolean p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->changed:Z

    if-eqz p1, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$500(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)Lcom/miui/gallery/movie/ui/view/BaseMovieView$IProgressChangeListener;

    move-result-object p1

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->this$0:Lcom/miui/gallery/movie/ui/view/BaseMovieView;

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView;->access$500(Lcom/miui/gallery/movie/ui/view/BaseMovieView;)Lcom/miui/gallery/movie/ui/view/BaseMovieView$IProgressChangeListener;

    move-result-object p1

    invoke-interface {p1}, Lcom/miui/gallery/movie/ui/view/BaseMovieView$IProgressChangeListener;->onVideoProgressChanged()V

    :cond_4
    iget-boolean p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->changed:Z

    return p1

    :cond_5
    :goto_1
    return v0

    :cond_6
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getX()F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->downX:F

    iput-boolean v0, p0, Lcom/miui/gallery/movie/ui/view/BaseMovieView$2;->changed:Z

    return v0
.end method
