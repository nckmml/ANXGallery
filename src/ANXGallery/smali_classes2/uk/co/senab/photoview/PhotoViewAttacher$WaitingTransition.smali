.class Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;
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
    name = "WaitingTransition"
.end annotation


# instance fields
.field final mEnterInfo:Lcom/miui/gallery/util/photoview/ItemViewInfo;

.field final mStartTime:J

.field final mTransitionListener:Luk/co/senab/photoview/PhotoViewAttacher$TransitionListener;

.field final synthetic this$0:Luk/co/senab/photoview/PhotoViewAttacher;


# direct methods
.method private constructor <init>(Luk/co/senab/photoview/PhotoViewAttacher;Lcom/miui/gallery/util/photoview/ItemViewInfo;Luk/co/senab/photoview/PhotoViewAttacher$TransitionListener;)V
    .locals 0

    iput-object p1, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->mEnterInfo:Lcom/miui/gallery/util/photoview/ItemViewInfo;

    iput-object p3, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->mTransitionListener:Luk/co/senab/photoview/PhotoViewAttacher$TransitionListener;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    iput-wide p1, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->mStartTime:J

    return-void
.end method

.method synthetic constructor <init>(Luk/co/senab/photoview/PhotoViewAttacher;Lcom/miui/gallery/util/photoview/ItemViewInfo;Luk/co/senab/photoview/PhotoViewAttacher$TransitionListener;Luk/co/senab/photoview/PhotoViewAttacher$1;)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;-><init>(Luk/co/senab/photoview/PhotoViewAttacher;Lcom/miui/gallery/util/photoview/ItemViewInfo;Luk/co/senab/photoview/PhotoViewAttacher$TransitionListener;)V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0}, Luk/co/senab/photoview/PhotoViewAttacher;->access$1600(Luk/co/senab/photoview/PhotoViewAttacher;)Luk/co/senab/photoview/PhotoViewAttacher$Transition;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoViewAttacher;->getImageView()Landroid/widget/ImageView;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0}, Luk/co/senab/photoview/PhotoViewAttacher;->access$1600(Luk/co/senab/photoview/PhotoViewAttacher;)Luk/co/senab/photoview/PhotoViewAttacher$Transition;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Luk/co/senab/photoview/PhotoViewAttacher$Transition;->ensureAlpha(Z)V

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0}, Luk/co/senab/photoview/PhotoViewAttacher;->access$1600(Luk/co/senab/photoview/PhotoViewAttacher;)Luk/co/senab/photoview/PhotoViewAttacher$Transition;

    move-result-object v0

    iget-object v1, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v1}, Luk/co/senab/photoview/PhotoViewAttacher;->access$1200(Luk/co/senab/photoview/PhotoViewAttacher;)F

    move-result v1

    const/high16 v2, 0x3f800000    # 1.0f

    invoke-virtual {v0, v1, v2}, Luk/co/senab/photoview/PhotoViewAttacher$Transition;->alpha(FF)V

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0}, Luk/co/senab/photoview/PhotoViewAttacher;->access$1600(Luk/co/senab/photoview/PhotoViewAttacher;)Luk/co/senab/photoview/PhotoViewAttacher$Transition;

    move-result-object v0

    iget-object v1, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->mTransitionListener:Luk/co/senab/photoview/PhotoViewAttacher$TransitionListener;

    invoke-virtual {v0, v1}, Luk/co/senab/photoview/PhotoViewAttacher$Transition;->setTransitionListener(Luk/co/senab/photoview/PhotoViewAttacher$TransitionListener;)V

    iget-object v0, p0, Luk/co/senab/photoview/PhotoViewAttacher$WaitingTransition;->this$0:Luk/co/senab/photoview/PhotoViewAttacher;

    invoke-static {v0}, Luk/co/senab/photoview/PhotoViewAttacher;->access$1600(Luk/co/senab/photoview/PhotoViewAttacher;)Luk/co/senab/photoview/PhotoViewAttacher$Transition;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Luk/co/senab/photoview/PhotoViewAttacher$Transition;->start(Z)V

    :cond_0
    invoke-static {}, Luk/co/senab/photoview/log/LogManager;->getLogger()Luk/co/senab/photoview/log/Logger;

    move-result-object v0

    const-string v1, "PhotoViewAttacher"

    const-string v2, "Loading drawable is slow, transiting only with alpha"

    invoke-interface {v0, v1, v2}, Luk/co/senab/photoview/log/Logger;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method
