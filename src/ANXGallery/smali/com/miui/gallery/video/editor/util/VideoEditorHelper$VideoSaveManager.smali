.class Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;
.super Ljava/lang/Object;
.source "VideoEditorHelper.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/video/editor/util/VideoEditorHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "VideoSaveManager"
.end annotation


# instance fields
.field private mFuture:Lcom/miui/gallery/threadpool/Future;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/threadpool/Future<",
            "Ljava/lang/Void;",
            ">;"
        }
    .end annotation
.end field

.field private mHelperWeakReference:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Lcom/miui/gallery/video/editor/util/VideoEditorHelper;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/miui/gallery/video/editor/util/VideoEditorHelper;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->mHelperWeakReference:Ljava/lang/ref/WeakReference;

    return-void
.end method

.method static synthetic access$700(Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;)Lcom/miui/gallery/video/editor/util/VideoEditorHelper;
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->getVideoEditorHelper()Lcom/miui/gallery/video/editor/util/VideoEditorHelper;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$800(Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;)Lcom/miui/gallery/threadpool/Future;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->mFuture:Lcom/miui/gallery/threadpool/Future;

    return-object p0
.end method

.method private getVideoEditorHelper()Lcom/miui/gallery/video/editor/util/VideoEditorHelper;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->mHelperWeakReference:Ljava/lang/ref/WeakReference;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper;

    :goto_0
    return-object v0
.end method


# virtual methods
.method public handleVideoSave(Ljava/lang/String;)V
    .locals 3

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const-string v1, "VideoEditorHelper"

    if-eqz v0, :cond_0

    const-string p1, "the video path is null. "

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->getVideoEditorHelper()Lcom/miui/gallery/video/editor/util/VideoEditorHelper;

    move-result-object v0

    if-nez v0, :cond_1

    const-string p1, "the VideoEditorHelper is null. "

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->mFuture:Lcom/miui/gallery/threadpool/Future;

    if-eqz v0, :cond_2

    invoke-interface {v0}, Lcom/miui/gallery/threadpool/Future;->cancel()V

    :cond_2
    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager$1;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager$1;-><init>(Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;Ljava/lang/String;)V

    new-instance v2, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager$2;

    invoke-direct {v2, p0, p1}, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager$2;-><init>(Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;Ljava/lang/String;)V

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;Lcom/miui/gallery/threadpool/FutureListener;)Lcom/miui/gallery/threadpool/Future;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->mFuture:Lcom/miui/gallery/threadpool/Future;

    return-void
.end method

.method public onDestroy()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->mFuture:Lcom/miui/gallery/threadpool/Future;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/threadpool/Future;->cancel()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/video/editor/util/VideoEditorHelper$VideoSaveManager;->mFuture:Lcom/miui/gallery/threadpool/Future;

    :cond_0
    return-void
.end method
