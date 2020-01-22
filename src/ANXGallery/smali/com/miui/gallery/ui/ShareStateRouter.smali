.class public Lcom/miui/gallery/ui/ShareStateRouter;
.super Ljava/lang/Object;
.source "ShareStateRouter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;,
        Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;
    }
.end annotation


# static fields
.field public static final IS_MISHARE_AVAILABLE:Lcom/miui/gallery/util/LazyValue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/LazyValue<",
            "Landroid/content/Context;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field

.field private static sShareStateRouter:Lcom/miui/gallery/ui/ShareStateRouter;


# instance fields
.field private mPrintStateObservers:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;",
            ">;"
        }
    .end annotation
.end field

.field private mProjectStateObservers:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/ui/ShareStateRouter$1;

    invoke-direct {v0}, Lcom/miui/gallery/ui/ShareStateRouter$1;-><init>()V

    sput-object v0, Lcom/miui/gallery/ui/ShareStateRouter;->IS_MISHARE_AVAILABLE:Lcom/miui/gallery/util/LazyValue;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mProjectStateObservers:Ljava/util/List;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mPrintStateObservers:Ljava/util/List;

    return-void
.end method

.method public static declared-synchronized getInstance()Lcom/miui/gallery/ui/ShareStateRouter;
    .locals 2

    const-class v0, Lcom/miui/gallery/ui/ShareStateRouter;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/miui/gallery/ui/ShareStateRouter;->sShareStateRouter:Lcom/miui/gallery/ui/ShareStateRouter;

    if-nez v1, :cond_0

    new-instance v1, Lcom/miui/gallery/ui/ShareStateRouter;

    invoke-direct {v1}, Lcom/miui/gallery/ui/ShareStateRouter;-><init>()V

    sput-object v1, Lcom/miui/gallery/ui/ShareStateRouter;->sShareStateRouter:Lcom/miui/gallery/ui/ShareStateRouter;

    :cond_0
    sget-object v1, Lcom/miui/gallery/ui/ShareStateRouter;->sShareStateRouter:Lcom/miui/gallery/ui/ShareStateRouter;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method


# virtual methods
.method public broadcastPrintState(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mPrintStateObservers:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;

    invoke-interface {v1, p1}, Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;->onObservePrintState(Z)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public broadcastProjectState(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mProjectStateObservers:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;

    invoke-interface {v1, p1}, Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;->onObserveProjectState(I)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public registerPrintStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mPrintStateObservers:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public registerProjectStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mProjectStateObservers:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public removePrintStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mPrintStateObservers:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(Ljava/lang/Object;)Z

    return-void
.end method

.method public removeProjectStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ShareStateRouter;->mProjectStateObservers:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(Ljava/lang/Object;)Z

    return-void
.end method
