.class public Lcom/miui/gallery/movie/xmsdk/XmMovieManager;
.super Lcom/miui/gallery/movie/core/MovieManager;
.source "XmMovieManager.java"

# interfaces
.implements Lcom/miui/gallery/xmstreaming/XmsContext$PlaybackCallback;
.implements Lcom/miui/gallery/xmstreaming/XmsContext$StreamingEngineCallback;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/movie/core/MovieManager<",
        "Ljava/util/ArrayList<",
        "Lcom/miui/gallery/xmstreaming/XmsMultiThumbnailSequenceView$ThumbnailSequenceDesc;",
        ">;>;",
        "Lcom/miui/gallery/xmstreaming/XmsContext$PlaybackCallback;",
        "Lcom/miui/gallery/xmstreaming/XmsContext$StreamingEngineCallback;"
    }
.end annotation


# instance fields
.field private mExportListener:Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;

.field private mHandler:Landroid/os/Handler;

.field private mIsPaused:Z

.field private mLock:Ljava/lang/Object;

.field private mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

.field private mMovieState:I

.field private mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/CopyOnWriteArrayList<",
            "Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;",
            ">;"
        }
    .end annotation
.end field

.field private mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

.field private mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

.field private mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/movie/core/MovieManager;-><init>()V

    new-instance v0, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-direct {v0}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    new-instance v0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager$2;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager$2;-><init>(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;Landroid/os/Looper;)V

    iput-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mHandler:Landroid/os/Handler;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;)Lcom/miui/gallery/xmstreaming/XmsTimeline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;)Lcom/miui/gallery/xmstreaming/XmsContext;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;)Landroid/os/Handler;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mHandler:Landroid/os/Handler;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->resetImage()V

    return-void
.end method

.method static synthetic access$400(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;)Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mExportListener:Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;

    return-object p0
.end method

.method private getStateFromXmEngine(I)I
    .locals 1

    const/4 v0, 0x1

    if-eq p1, v0, :cond_0

    const/4 v0, 0x2

    if-eq p1, v0, :cond_0

    const/4 v0, 0x3

    if-eq p1, v0, :cond_0

    const/16 p1, -0x1f4

    return p1

    :cond_0
    return v0
.end method

.method private initTimeLine(Landroid/content/Context;)V
    .locals 7

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    const-string v1, "XmMovieManager"

    if-nez v0, :cond_0

    const-string p1, "streamingContext is null!"

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/util/ScreenUtils;->getScreenWidth()I

    move-result v0

    const/high16 v2, 0x3f800000    # 1.0f

    move-object v3, p1

    check-cast v3, Landroid/app/Activity;

    invoke-static {v3}, Lcom/miui/gallery/util/ScreenUtils;->getFullScreenHeight(Landroid/app/Activity;)I

    move-result v4

    int-to-float v4, v4

    mul-float/2addr v4, v2

    int-to-float v2, v0

    div-float/2addr v4, v2

    invoke-static {v4}, Lcom/miui/gallery/movie/entity/MovieAspectRatio;->getFitRatio(F)Lcom/miui/gallery/movie/entity/MovieAspectRatio;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/movie/entity/MovieAspectRatio;->getRatio()F

    move-result v4

    mul-float/2addr v2, v4

    float-to-int v2, v2

    invoke-static {v3}, Lcom/miui/gallery/util/ScreenUtils;->getFullScreenHeight(Landroid/app/Activity;)I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    const-string v6, "initTimeLine in size: %d:%d"

    invoke-static {v1, v6, v4, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v1, v0, v3}, Lcom/miui/gallery/xmstreaming/XmsContext;->createTimeline(II)Lcom/miui/gallery/xmstreaming/XmsTimeline;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {v1, v0}, Lcom/miui/gallery/xmstreaming/XmsTimeline;->setPreferWidth(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/xmstreaming/XmsTimeline;->setPreferHeight(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/xmstreaming/XmsContext;->setPlaybackCallback(Lcom/miui/gallery/xmstreaming/XmsContext$PlaybackCallback;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/xmstreaming/XmsContext;->setStreamingEngineCallback(Lcom/miui/gallery/xmstreaming/XmsContext$StreamingEngineCallback;)V

    const/4 v0, 0x0

    invoke-static {}, Lcom/miui/gallery/util/BaseBuildUtil;->isInternational()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-static {}, Lcom/miui/settings/Settings;->getRegion()Ljava/lang/String;

    move-result-object v0

    const-string v1, "IN"

    invoke-virtual {v0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v0, 0x2

    goto :goto_0

    :cond_1
    const/4 v0, 0x1

    :cond_2
    :goto_0
    sget v1, Lcom/miui/gallery/movie/MovieConfig;->sTestLocationType:I

    if-ltz v1, :cond_3

    sget v0, Lcom/miui/gallery/movie/MovieConfig;->sTestLocationType:I

    :cond_3
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    iget-object v2, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {v1, p1, v2, v0}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->initHelper(Landroid/content/Context;Lcom/miui/gallery/xmstreaming/XmsTimeline;I)V

    return-void
.end method

.method private resetImage()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {v1}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->clearClip()V

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->imageList:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/movie/entity/ImageEntity;

    iget-object v3, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    iget-object v2, v2, Lcom/miui/gallery/movie/entity/ImageEntity;->image:Ljava/lang/String;

    invoke-virtual {v3, v2}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->appendClip(Ljava/lang/String;)V

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {v1}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->reBuildTimeLine()V

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v2, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/xmstreaming/XmsContext;->applyTimeline(Lcom/miui/gallery/xmstreaming/XmsTimeline;)Z

    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method


# virtual methods
.method public addImage(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/movie/entity/ImageEntity;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->resetImage()V

    return-void
.end method

.method public addStateChangeListener(Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;)V
    .locals 1

    if-eqz p1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    if-nez v0, :cond_0

    new-instance v0, Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-direct {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/CopyOnWriteArrayList;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/CopyOnWriteArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    return-void
.end method

.method public cancelExport()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v2, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {v2}, Lcom/miui/gallery/xmstreaming/XmsTimeline;->getInternalObject()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Lcom/miui/gallery/xmstreaming/XmsContext;->cancelCompile(J)Z

    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public createThumbnailSequenceView(Landroid/content/Context;)Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;
    .locals 1

    new-instance v0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewWrapper;

    invoke-direct {v0, p1}, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewWrapper;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method public destroy()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/movie/core/MovieManager;->destroy()V

    const-string v0, "XmMovieManager"

    const-string v1, "destroy"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-boolean v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mIsPaused:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0}, Lcom/miui/gallery/xmstreaming/XmsContext;->release()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mExportListener:Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;

    :cond_0
    return-void
.end method

.method public export(Ljava/lang/String;Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iput-object p2, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mExportListener:Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;

    iget-object p2, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mExportListener:Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;

    invoke-interface {p2}, Lcom/miui/gallery/movie/core/MovieManager$EncodeStateInterface;->onEncodeStart()V

    iget-object p2, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    new-instance v2, Lcom/miui/gallery/movie/xmsdk/XmMovieManager$3;

    invoke-direct {v2, p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager$3;-><init>(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;)V

    invoke-virtual {p2, v1, p1, v2}, Lcom/miui/gallery/xmstreaming/XmsContext;->compileTimeline(Lcom/miui/gallery/xmstreaming/XmsTimeline;Ljava/lang/String;Lcom/miui/gallery/xmstreaming/XmsContext$CompileCallback;)V

    monitor-exit v0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public bridge synthetic getThumbnailImages()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->getThumbnailImages()Ljava/util/ArrayList;

    move-result-object v0

    return-object v0
.end method

.method public getThumbnailImages()Ljava/util/ArrayList;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/xmstreaming/XmsMultiThumbnailSequenceView$ThumbnailSequenceDesc;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {v1}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->getThumbnailImages()Ljava/util/ArrayList;

    move-result-object v1

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public getTotalTime()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->getDuration()I

    move-result v0

    return v0
.end method

.method public init(Landroid/content/Context;)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/xmstreaming/XmsContext;->getInstance()Lcom/miui/gallery/xmstreaming/XmsContext;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/xmstreaming/XmsContext;->initPlayer(Z)V

    new-instance v0, Landroid/view/TextureView;

    invoke-direct {v0, p1}, Landroid/view/TextureView;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mDisplayView:Landroid/view/View;

    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->initTimeLine(Landroid/content/Context;)V

    new-instance p1, Lcom/miui/gallery/movie/xmsdk/XmMovieManager$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager$1;-><init>(Lcom/miui/gallery/movie/xmsdk/XmMovieManager;)V

    invoke-virtual {v0, p1}, Landroid/view/TextureView;->setSurfaceTextureListener(Landroid/view/TextureView$SurfaceTextureListener;)V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mIsPaused:Z

    return-void
.end method

.method public isReadyForExport()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0}, Lcom/miui/gallery/xmstreaming/XmsContext;->isReadyForExport()Z

    move-result v0

    return v0
.end method

.method public isReadyForSwitch()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0}, Lcom/miui/gallery/xmstreaming/XmsContext;->isReadyForSwitch()Z

    move-result v0

    return v0
.end method

.method public isSupportVideo(Ljava/lang/String;)Z
    .locals 0

    const/4 p1, 0x1

    return p1
.end method

.method public moveImage(II)V
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mHandler:Landroid/os/Handler;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroid/os/Handler;->removeMessages(I)V

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mHandler:Landroid/os/Handler;

    const-wide/16 v0, 0xfa

    invoke-virtual {p1, p2, v0, v1}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    return-void
.end method

.method public onFirstVideoFramePresented()V
    .locals 0

    return-void
.end method

.method public onPause()V
    .locals 3

    invoke-super {p0}, Lcom/miui/gallery/movie/core/MovieManager;->onPause()V

    const-string v0, "XmMovieManager"

    const-string v1, "onPause"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->pause()V

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v1}, Lcom/miui/gallery/xmstreaming/XmsContext;->pauseToBackground()V

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mHandler:Landroid/os/Handler;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/os/Handler;->removeMessages(I)V

    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mIsPaused:Z

    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public onPlaybackEOF()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;

    invoke-interface {v1}, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;->onPlaybackEOF()V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0}, Lcom/miui/gallery/xmstreaming/XmsContext;->skipPause()V

    return-void
.end method

.method public onPlaybackPreloadingCompletion()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;

    invoke-interface {v1}, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;->onPlaybackPreloadingCompletion()V

    goto :goto_0

    :cond_0
    const-string v0, "XmMovieManager"

    const-string v1, "onPlaybackPreloadingCompletion"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public onPlaybackTimelinePosition(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;

    invoke-interface {v1, p1}, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;->onPlaybackTimeChanged(I)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 3

    const-string v0, "XmMovieManager"

    const-string v1, "onResume"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-super {p0}, Lcom/miui/gallery/movie/core/MovieManager;->onResume()V

    iget-boolean v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mIsPaused:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {v1}, Lcom/miui/gallery/xmstreaming/XmsTimeline;->getInternalObject()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/xmstreaming/XmsContext;->resumeToForeground(J)V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mIsPaused:Z

    :cond_0
    return-void
.end method

.method public onStreamingEngineStateChanged(I)V
    .locals 3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "XmMovieManager"

    const-string v2, "state: %d"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->getStateFromXmEngine(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieState:I

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {p1}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;

    iget v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieState:I

    invoke-interface {v0, v1}, Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;->onStateChanged(I)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public pause()V
    .locals 2

    const-string v0, "XmMovieManager"

    const-string v1, "pause"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0}, Lcom/miui/gallery/xmstreaming/XmsContext;->pause()V

    return-void
.end method

.method public pauseOrResume()V
    .locals 3

    iget v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieState:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "XmMovieManager"

    const-string v2, "pauseOrResume:%d"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieState:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->pause()V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->resume()V

    :goto_0
    return-void
.end method

.method public removeImageAtIndex(I)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->resetImage()V

    return-void
.end method

.method public removeStateChangeListener(Lcom/miui/gallery/movie/core/MovieManager$StateChangeListener;)V
    .locals 0

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStateChangeListeners:Ljava/util/concurrent/CopyOnWriteArrayList;

    return-void
.end method

.method public replay()V
    .locals 1

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->seek(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->resume()V

    return-void
.end method

.method public resetImage(Ljava/util/List;Z)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/movie/entity/ImageEntity;",
            ">;Z)V"
        }
    .end annotation

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter p1

    :try_start_0
    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {v0, p2}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->changeTimelineDuration(Z)V

    invoke-direct {p0}, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->resetImage()V

    monitor-exit p1

    return-void

    :catchall_0
    move-exception p2

    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p2
.end method

.method public resume()V
    .locals 2

    const-string v0, "XmMovieManager"

    const-string v1, "resume"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0}, Lcom/miui/gallery/xmstreaming/XmsContext;->resume()V

    return-void
.end method

.method public seek(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/xmstreaming/XmsContext;->seekToPos(I)V

    return-void
.end method

.method public seekToIndex(I)I
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {v1, p1}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->geClipMiddleDuration(I)I

    move-result p1

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    invoke-virtual {v1, p1}, Lcom/miui/gallery/xmstreaming/XmsContext;->seekToPos(I)V

    monitor-exit v0

    return p1

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public setAudio(Lcom/miui/gallery/movie/entity/AudioResource;)V
    .locals 4

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "setAudio"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/movie/entity/AudioResource;->getResType()I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/movie/entity/AudioResource;->getDownloadSrcPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "XmMovieManager"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    if-eqz p1, :cond_2

    :try_start_0
    invoke-virtual {p1}, Lcom/miui/gallery/movie/entity/AudioResource;->getResType()I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/movie/entity/AudioResource;->getResType()I

    move-result v1

    const/4 v3, 0x2

    if-ne v1, v3, :cond_1

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {p1}, Lcom/miui/gallery/movie/entity/AudioResource;->getDownloadSrcPath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1, v2}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->changeMusic(Ljava/lang/String;I)V

    goto :goto_1

    :cond_1
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {p1}, Lcom/miui/gallery/movie/entity/AudioResource;->getDownloadSrcPath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1, v3}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->changeMusic(Ljava/lang/String;I)V

    goto :goto_1

    :cond_2
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    const/4 v1, 0x0

    const/4 v2, 0x3

    invoke-virtual {p1, v1, v2}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->changeMusic(Ljava/lang/String;I)V

    :goto_1
    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/xmstreaming/XmsContext;->applyTimeline(Lcom/miui/gallery/xmstreaming/XmsTimeline;)Z

    monitor-exit v0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public setInfo(Lcom/miui/gallery/movie/entity/MovieInfo;)V
    .locals 5

    const-string v0, "XmMovieManager"

    const-string v1, "setInfo"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iput-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    iget-boolean v2, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->isShortVideo:Z

    invoke-virtual {v1, v2}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->setShortVideo(Z)V

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->imageList:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/movie/entity/ImageEntity;

    iget-object v3, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    iget-object v2, v2, Lcom/miui/gallery/movie/entity/ImageEntity;->image:Ljava/lang/String;

    invoke-virtual {v3, v2}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->appendClip(Ljava/lang/String;)V

    goto :goto_0

    :cond_0
    iget-object v1, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->template:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    const-string v2, "assets:/xms/movieTemplateNormal"

    const-string v3, "movieAssetsNormal"

    if-nez v1, :cond_3

    iget-object v1, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->template:Ljava/lang/String;

    invoke-static {v1, v3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_1

    :cond_1
    iget-object v1, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->template:Ljava/lang/String;

    invoke-static {v1}, Lcom/miui/gallery/movie/ui/factory/TemplateFactory;->getTemplateDir(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/util/BaseFileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_2

    iput-object v3, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->template:Ljava/lang/String;

    const-string v1, "default template:%s is not download"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_2

    :cond_2
    move-object v2, v1

    goto :goto_2

    :cond_3
    :goto_1
    iput-object v3, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->template:Ljava/lang/String;

    :goto_2
    iget-object p1, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->template:Ljava/lang/String;

    invoke-static {p1, v3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->title:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v3, v3, Lcom/miui/gallery/movie/entity/MovieInfo;->subTitle:Ljava/lang/String;

    invoke-virtual {p1, v1, v3}, Lcom/miui/gallery/xmstreaming/XmsTimeline;->setTitle(Ljava/lang/String;Ljava/lang/String;)V

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {p1, v2}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->setTemplate(Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/xmstreaming/XmsContext;->applyTimeline(Lcom/miui/gallery/xmstreaming/XmsTimeline;)Z

    const-string p1, "setInfo end"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public setIsShortVideo(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {v1, p1}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->changeTimelineDuration(Z)V

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/xmstreaming/XmsContext;->applyTimeline(Lcom/miui/gallery/xmstreaming/XmsTimeline;)Z

    monitor-exit v0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public setTemplate(Lcom/miui/gallery/movie/entity/TemplateResource;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-boolean v1, p1, Lcom/miui/gallery/movie/entity/TemplateResource;->isPackageAssets:Z

    if-eqz v1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->title:Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v2, v2, Lcom/miui/gallery/movie/entity/MovieInfo;->subTitle:Ljava/lang/String;

    invoke-virtual {p1, v1, v2}, Lcom/miui/gallery/xmstreaming/XmsTimeline;->setTitle(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    const-string v1, "assets:/xms/movieTemplateNormal"

    invoke-virtual {p1, v1}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->setTemplate(Ljava/lang/String;)V

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    const/4 v2, 0x0

    invoke-virtual {v1, v2, v2}, Lcom/miui/gallery/xmstreaming/XmsTimeline;->setTitle(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mThemeHelper:Lcom/miui/gallery/xmstreaming/XmsThemeHelper;

    invoke-virtual {p1}, Lcom/miui/gallery/movie/entity/TemplateResource;->getDownloadSrcPath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Lcom/miui/gallery/xmstreaming/XmsThemeHelper;->setTemplate(Ljava/lang/String;)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mStreamingContext:Lcom/miui/gallery/xmstreaming/XmsContext;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmMovieManager;->mTimeline:Lcom/miui/gallery/xmstreaming/XmsTimeline;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/xmstreaming/XmsContext;->applyTimeline(Lcom/miui/gallery/xmstreaming/XmsTimeline;)Z

    monitor-exit v0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method
