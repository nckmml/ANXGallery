.class public Lcom/miui/gallery/util/thread/RxGalleryExecutors;
.super Ljava/lang/Object;
.source "RxGalleryExecutors.java"


# static fields
.field private static volatile INSTANCE:Lcom/miui/gallery/util/thread/RxGalleryExecutors;


# instance fields
.field private mUiThreadExecutor:Lcom/miui/gallery/util/thread/UIThreadExecutor;

.field private mUserThreadExecutor:Lcom/miui/gallery/util/thread/UserThreadExecutor;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/miui/gallery/util/thread/UIThreadExecutor;

    invoke-direct {v0}, Lcom/miui/gallery/util/thread/UIThreadExecutor;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->mUiThreadExecutor:Lcom/miui/gallery/util/thread/UIThreadExecutor;

    new-instance v0, Lcom/miui/gallery/util/thread/UserThreadExecutor;

    invoke-direct {v0}, Lcom/miui/gallery/util/thread/UserThreadExecutor;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->mUserThreadExecutor:Lcom/miui/gallery/util/thread/UserThreadExecutor;

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/util/thread/RxGalleryExecutors;
    .locals 2

    sget-object v0, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->INSTANCE:Lcom/miui/gallery/util/thread/RxGalleryExecutors;

    if-nez v0, :cond_1

    const-class v0, Lcom/miui/gallery/util/thread/RxGalleryExecutors;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->INSTANCE:Lcom/miui/gallery/util/thread/RxGalleryExecutors;

    if-nez v1, :cond_0

    new-instance v1, Lcom/miui/gallery/util/thread/RxGalleryExecutors;

    invoke-direct {v1}, Lcom/miui/gallery/util/thread/RxGalleryExecutors;-><init>()V

    sput-object v1, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->INSTANCE:Lcom/miui/gallery/util/thread/RxGalleryExecutors;

    :cond_0
    monitor-exit v0

    goto :goto_0

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1

    :cond_1
    :goto_0
    sget-object v0, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->INSTANCE:Lcom/miui/gallery/util/thread/RxGalleryExecutors;

    return-object v0
.end method


# virtual methods
.method public getUiThreadExecutor()Lcom/miui/gallery/util/thread/UIThreadExecutor;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->mUiThreadExecutor:Lcom/miui/gallery/util/thread/UIThreadExecutor;

    return-object v0
.end method

.method public getUserThreadExecutor()Lcom/miui/gallery/util/thread/UserThreadExecutor;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/util/thread/RxGalleryExecutors;->mUserThreadExecutor:Lcom/miui/gallery/util/thread/UserThreadExecutor;

    return-object v0
.end method
