.class public Lcom/miui/gallery/video/VideoFrameProvider;
.super Ljava/lang/Object;
.source "VideoFrameProvider.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/video/VideoFrameProvider$Listener;,
        Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;,
        Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;,
        Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;,
        Lcom/miui/gallery/video/VideoFrameProvider$ThumbListRequestTask;,
        Lcom/miui/gallery/video/VideoFrameProvider$SingleFrameRequestTask;,
        Lcom/miui/gallery/video/VideoFrameProvider$ReleaseRequestTask;,
        Lcom/miui/gallery/video/VideoFrameProvider$AssignSurfaceTask;,
        Lcom/miui/gallery/video/VideoFrameProvider$PrepareRequestTask;
    }
.end annotation


# static fields
.field private static final SUPPORTED_DEVICES:[Ljava/lang/String;

.field private static sIsDeviceSupported:Z


# instance fields
.field private mConnection:Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;

.field private mHandlerThread:Landroid/os/HandlerThread;

.field private mLastSingleFrameTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

.field private mLastThumbListTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

.field private mListeners:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoFrameProvider$Listener;",
            ">;"
        }
    .end annotation
.end field

.field private mMainHandler:Landroid/os/Handler;

.field private final mThumbListCache:Landroid/util/LruCache;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/LruCache<",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;",
            ">;"
        }
    .end annotation
.end field

.field private mWorkHandler:Landroid/os/Handler;


# direct methods
.method static constructor <clinit>()V
    .locals 38

    const-string v0, "dipper"

    const-string v1, "cepheus"

    const-string v2, "lavender"

    const-string v3, "perseus"

    const-string v4, "ursa"

    const-string v5, "jason"

    const-string v6, "wayne"

    const-string v7, "platina"

    const-string v8, "chiron"

    const-string v9, "sagit"

    const-string v10, "polaris"

    const-string v11, "equuleus"

    const-string v12, "sirius"

    const-string v13, "comet"

    const-string v14, "capricorn"

    const-string v15, "lithium"

    const-string v16, "natrium"

    const-string v17, "scorpio"

    const-string v18, "gemini"

    const-string v19, "davinci"

    const-string v20, "davinciin"

    const-string v21, "grus"

    const-string v22, "raphael"

    const-string v23, "raphaelin"

    const-string v24, "pyxis"

    const-string v25, "vela"

    const-string v26, "crux"

    const-string v27, "beryllium"

    const-string v28, "tucana"

    const-string v29, "ginkgo"

    const-string v30, "violet"

    const-string v31, "laurus"

    const-string v32, "phoenix"

    const-string v33, "phoenixin"

    const-string v34, "andromeda"

    const-string v35, "draco"

    const-string v36, "umi"

    const-string v37, "cmi"

    filled-new-array/range {v0 .. v37}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/video/VideoFrameProvider;->SUPPORTED_DEVICES:[Ljava/lang/String;

    const/4 v0, 0x0

    sput-boolean v0, Lcom/miui/gallery/video/VideoFrameProvider;->sIsDeviceSupported:Z

    sget-object v1, Lcom/miui/gallery/video/VideoFrameProvider;->SUPPORTED_DEVICES:[Ljava/lang/String;

    array-length v2, v1

    :goto_0
    if-ge v0, v2, :cond_1

    aget-object v3, v1, v0

    sget-object v4, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_0

    const/4 v3, 0x1

    sput-boolean v3, Lcom/miui/gallery/video/VideoFrameProvider;->sIsDeviceSupported:Z

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-direct {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mListeners:Ljava/util/List;

    new-instance v0, Landroid/util/LruCache;

    const/4 v1, 0x2

    invoke-direct {v0, v1}, Landroid/util/LruCache;-><init>(I)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    new-instance v0, Landroid/os/HandlerThread;

    const-string v1, "VideoFrameProvider-Thread"

    invoke-direct {v0, v1}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mMainHandler:Landroid/os/Handler;

    new-instance v0, Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mConnection:Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;J)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/gallery/video/VideoFrameProvider;->notifySingleFrame(Ljava/lang/String;J)V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/video/VideoFrameProvider;->notifyThumbListResponse(Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/video/VideoFrameProvider;)Landroid/util/LruCache;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/video/VideoFrameProvider;)Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mConnection:Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;

    return-object p0
.end method

.method private cancelTask(Ljava/lang/Runnable;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mWorkHandler:Landroid/os/Handler;

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    invoke-virtual {v0, p1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method public static createBitmap([BIIILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    .locals 7

    const/4 v0, 0x0

    if-eqz p0, :cond_3

    array-length v1, p0

    if-nez v1, :cond_0

    goto :goto_1

    :cond_0
    mul-int v1, p2, p3

    mul-int/lit8 v2, v1, 0x4

    add-int/2addr v2, p1

    array-length v3, p0

    if-le v2, v3, :cond_1

    return-object v0

    :cond_1
    new-array v1, v1, [I

    const/4 v3, 0x0

    :goto_0
    add-int/lit8 v4, p1, 0x3

    if-ge v4, v2, :cond_2

    aget-byte v5, p0, p1

    and-int/lit16 v5, v5, 0xff

    shl-int/lit8 v5, v5, 0x18

    add-int/lit8 v6, p1, 0x1

    aget-byte v6, p0, v6

    and-int/lit16 v6, v6, 0xff

    shl-int/lit8 v6, v6, 0x10

    or-int/2addr v5, v6

    add-int/lit8 v6, p1, 0x2

    aget-byte v6, p0, v6

    and-int/lit16 v6, v6, 0xff

    shl-int/lit8 v6, v6, 0x8

    or-int/2addr v5, v6

    aget-byte v4, p0, v4

    and-int/lit16 v4, v4, 0xff

    or-int/2addr v4, v5

    aput v4, v1, v3

    add-int/lit8 p1, p1, 0x4

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    :try_start_0
    invoke-static {v1, p2, p3, p4}, Landroid/graphics/Bitmap;->createBitmap([IIILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    const-string p1, "VideoFrameProvider"

    const-string p2, "createBitmap error\n"

    invoke-static {p1, p2, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_3
    :goto_1
    return-object v0
.end method

.method public static getFrameCount(J)I
    .locals 2

    const-wide/16 v0, 0x0

    cmp-long v0, p0, v0

    if-gtz v0, :cond_0

    const-string p0, "VideoFrameProvider"

    const-string p1, "the duration must more than 0"

    invoke-static {p0, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0

    :cond_0
    const-wide/16 v0, 0x3e8

    div-long/2addr p0, v0

    long-to-int p0, p0

    const/4 p1, 0x7

    if-gt p0, p1, :cond_1

    goto :goto_0

    :cond_1
    add-int/lit8 p0, p0, -0x7

    int-to-double p0, p0

    const-wide v0, 0x3fd99999a0000000L    # 0.4000000059604645

    invoke-static {p0, p1, v0, v1}, Ljava/lang/Math;->pow(DD)D

    move-result-wide p0

    const-wide/high16 v0, 0x401c000000000000L    # 7.0

    add-double/2addr p0, v0

    const-wide/high16 v0, 0x3ff0000000000000L    # 1.0

    sub-double/2addr p0, v0

    double-to-int p0, p0

    :goto_0
    const/4 p1, 0x5

    const/16 v0, 0x1e

    invoke-static {p0, v0}, Ljava/lang/Math;->min(II)I

    move-result p0

    invoke-static {p1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method public static isDeviceSupport()Z
    .locals 1

    sget-boolean v0, Lcom/miui/gallery/video/VideoFrameProvider;->sIsDeviceSupported:Z

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/util/BuildUtil;->isPad()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private notifySingleFrame(Ljava/lang/String;J)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mMainHandler:Landroid/os/Handler;

    new-instance v1, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$eghBPuc5O_TNPDyKs6eWwuzy4OM;

    invoke-direct {v1, p0, p1, p2, p3}, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$eghBPuc5O_TNPDyKs6eWwuzy4OM;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;J)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private notifyThumbListResponse(Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mMainHandler:Landroid/os/Handler;

    new-instance v1, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;

    invoke-direct {v1, p0, p1, p2}, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public static readRemoteData(Ljava/io/FileDescriptor;)[B
    .locals 10

    const-string v0, "VideoFrameProvider"

    const/4 v1, 0x0

    if-eqz p0, :cond_2

    invoke-virtual {p0}, Ljava/io/FileDescriptor;->valid()Z

    move-result v2

    if-nez v2, :cond_0

    goto :goto_3

    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    :try_start_0
    new-instance v4, Ljava/io/FileInputStream;

    invoke-direct {v4, p0}, Ljava/io/FileInputStream;-><init>(Ljava/io/FileDescriptor;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    :try_start_1
    new-instance p0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {p0}, Ljava/io/ByteArrayOutputStream;-><init>()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const/16 v5, 0x400

    :try_start_2
    new-array v5, v5, [B

    :goto_0
    invoke-virtual {v4, v5}, Ljava/io/FileInputStream;->read([B)I

    move-result v6

    if-lez v6, :cond_1

    const/4 v7, 0x0

    invoke-virtual {p0, v5, v7, v6}, Ljava/io/ByteArrayOutputStream;->write([BII)V

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v5

    const-string v6, "read remote data length: %d, cost: %dms"

    array-length v7, v5

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    sub-long/2addr v8, v2

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-static {v0, v6, v7, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    invoke-static {v4}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-object v5

    :catch_0
    move-exception v2

    goto :goto_1

    :catchall_0
    move-exception v0

    move-object p0, v1

    goto :goto_2

    :catch_1
    move-exception v2

    move-object p0, v1

    goto :goto_1

    :catchall_1
    move-exception v0

    move-object p0, v1

    move-object v4, p0

    goto :goto_2

    :catch_2
    move-exception v2

    move-object p0, v1

    move-object v4, p0

    :goto_1
    :try_start_3
    const-string v3, "read remote data error\n"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    invoke-static {v4}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-object v1

    :catchall_2
    move-exception v0

    :goto_2
    invoke-static {v4}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw v0

    :cond_2
    :goto_3
    return-object v1
.end method

.method private submitTask(Ljava/lang/Runnable;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mWorkHandler:Landroid/os/Handler;

    if-nez v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Landroid/os/HandlerThread;->start()V

    new-instance v0, Landroid/os/Handler;

    iget-object v1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    invoke-virtual {v1}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mWorkHandler:Landroid/os/Handler;

    :cond_0
    if-eqz v0, :cond_1

    invoke-virtual {v0, p1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_1
    return-void
.end method


# virtual methods
.method public addListener(Lcom/miui/gallery/video/VideoFrameProvider$Listener;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mListeners:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getThumbListInfo(Ljava/lang/String;)Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;
    .locals 2

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    invoke-virtual {v1, p1}, Landroid/util/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    monitor-exit v0

    return-object p1

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public synthetic lambda$notifySingleFrame$14$VideoFrameProvider(Ljava/lang/String;J)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mListeners:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/video/VideoFrameProvider$Listener;

    invoke-interface {v1, p1, p2, p3}, Lcom/miui/gallery/video/VideoFrameProvider$Listener;->onSingleFrameResponse(Ljava/lang/String;J)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public synthetic lambda$notifyThumbListResponse$13$VideoFrameProvider(Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mListeners:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/video/VideoFrameProvider$Listener;

    invoke-interface {v1, p1, p2}, Lcom/miui/gallery/video/VideoFrameProvider$Listener;->onThumbListResponse(Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public onStart()V
    .locals 2

    const-string v0, "VideoFrameProvider"

    const-string v1, "onStart"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mConnection:Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;->reset()V

    return-void
.end method

.method public onStop()V
    .locals 2

    const-string v0, "VideoFrameProvider"

    const-string v1, "onStop"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mConnection:Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;->cancel()V

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    invoke-virtual {v1}, Landroid/util/LruCache;->evictAll()V

    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public prepareForVideo(Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mLastThumbListTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->cancelTask(Ljava/lang/Runnable;)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mLastThumbListTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

    new-instance v0, Lcom/miui/gallery/video/VideoFrameProvider$PrepareRequestTask;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/video/VideoFrameProvider$PrepareRequestTask;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->submitTask(Ljava/lang/Runnable;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public release()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mWorkHandler:Landroid/os/Handler;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    iput-object v1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mWorkHandler:Landroid/os/Handler;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    invoke-virtual {v0}, Landroid/os/HandlerThread;->quit()Z

    iput-object v1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mListeners:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    monitor-enter v0

    :try_start_0
    iget-object v2, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mThumbListCache:Landroid/util/LruCache;

    invoke-virtual {v2}, Landroid/util/LruCache;->evictAll()V

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mConnection:Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider$MiuiVideoConnection;->release()V

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mMainHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    return-void

    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method public releaseForVideo(Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/miui/gallery/video/VideoFrameProvider$ReleaseRequestTask;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/video/VideoFrameProvider$ReleaseRequestTask;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->submitTask(Ljava/lang/Runnable;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public requestSingleFrame(Ljava/lang/String;IIJLandroid/view/Surface;)V
    .locals 9

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/miui/gallery/video/VideoFrameProvider$SingleFrameRequestTask;

    move-object v1, v0

    move-object v2, p0

    move-object v3, p1

    move-wide v4, p4

    move v6, p2

    move v7, p3

    move-object v8, p6

    invoke-direct/range {v1 .. v8}, Lcom/miui/gallery/video/VideoFrameProvider$SingleFrameRequestTask;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;JIILandroid/view/Surface;)V

    iget-object p1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mLastSingleFrameTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/VideoFrameProvider;->cancelTask(Ljava/lang/Runnable;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->submitTask(Ljava/lang/Runnable;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mLastSingleFrameTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

    :cond_1
    :goto_0
    return-void
.end method

.method public requestThumbList(Ljava/lang/String;II)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_2

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/gallery/video/VideoFrameProvider;->getThumbListInfo(Ljava/lang/String;)Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->notifyThumbListResponse(Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    goto :goto_0

    :cond_1
    new-instance v0, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListRequestTask;

    invoke-direct {v0, p0, p1, p2, p3}, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListRequestTask;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;II)V

    iget-object p1, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mLastThumbListTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/VideoFrameProvider;->cancelTask(Ljava/lang/Runnable;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->submitTask(Ljava/lang/Runnable;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mLastThumbListTask:Lcom/miui/gallery/video/VideoFrameProvider$RequestTask;

    :cond_2
    :goto_0
    return-void
.end method

.method public setSurfaceForVideo(Ljava/lang/String;Landroid/view/Surface;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoFrameProvider;->mHandlerThread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    if-nez p2, :cond_0

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/miui/gallery/video/VideoFrameProvider$AssignSurfaceTask;

    invoke-direct {v0, p0, p1, p2}, Lcom/miui/gallery/video/VideoFrameProvider$AssignSurfaceTask;-><init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;Landroid/view/Surface;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->submitTask(Ljava/lang/Runnable;)V

    :cond_1
    :goto_0
    return-void
.end method
