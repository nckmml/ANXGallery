.class Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;
.super Ljava/lang/Object;
.source "PhotoPageVideoItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageVideoItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "LoadThumbnailTask"
.end annotation


# instance fields
.field private mCallback:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;

.field private mFuture:Lcom/miui/gallery/threadpool/Future;


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/PhotoPageVideoItem$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;-><init>()V

    return-void
.end method

.method static synthetic access$1500(Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;)Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->mCallback:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;

    return-object p0
.end method

.method static synthetic lambda$load$21(Landroid/graphics/Bitmap;IILcom/miui/gallery/threadpool/ThreadPool$JobContext;)Landroid/graphics/Bitmap;
    .locals 0

    invoke-interface {p3}, Lcom/miui/gallery/threadpool/ThreadPool$JobContext;->isCancelled()Z

    move-result p3

    if-eqz p3, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    invoke-static {p0, p1, p2}, Lcom/miui/gallery/util/BitmapUtils;->createScaledBitmap(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public load(Landroid/graphics/Bitmap;IILcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->mFuture:Lcom/miui/gallery/threadpool/Future;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/threadpool/Future;->cancel()V

    :cond_0
    iput-object p4, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->mCallback:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object p4

    new-instance v0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;

    invoke-direct {v0, p1, p2, p3}, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;-><init>(Landroid/graphics/Bitmap;II)V

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask$1;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;)V

    invoke-virtual {p4, v0, p1}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;Lcom/miui/gallery/threadpool/FutureListener;)Lcom/miui/gallery/threadpool/Future;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->mFuture:Lcom/miui/gallery/threadpool/Future;

    return-void
.end method

.method public release()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->mFuture:Lcom/miui/gallery/threadpool/Future;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/threadpool/Future;->cancel()V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->mFuture:Lcom/miui/gallery/threadpool/Future;

    :cond_0
    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->mCallback:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;

    return-void
.end method
