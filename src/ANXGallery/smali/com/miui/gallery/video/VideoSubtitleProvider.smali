.class public Lcom/miui/gallery/video/VideoSubtitleProvider;
.super Ljava/lang/Object;
.source "VideoSubtitleProvider.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;,
        Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;
    }
.end annotation


# instance fields
.field private mContext:Landroid/content/Context;

.field private mFuture:Lcom/miui/gallery/threadpool/Future;

.field public mFutureListener:Lcom/miui/gallery/threadpool/FutureListener;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/threadpool/FutureListener<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
            ">;>;"
        }
    .end annotation
.end field

.field private mListener:Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;

.field private mTask:Lcom/miui/gallery/threadpool/ThreadPool$Job;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/threadpool/ThreadPool$Job<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
            ">;>;"
        }
    .end annotation
.end field

.field private volatile mTypeface:Landroid/graphics/Typeface;

.field private final mTypefaceLock:Ljava/lang/Object;

.field private mVideoPath:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypefaceLock:Ljava/lang/Object;

    new-instance v0, Lcom/miui/gallery/video/-$$Lambda$VideoSubtitleProvider$I5_2CMXGGn9RwlJch43oau9bp8I;

    invoke-direct {v0, p0}, Lcom/miui/gallery/video/-$$Lambda$VideoSubtitleProvider$I5_2CMXGGn9RwlJch43oau9bp8I;-><init>(Lcom/miui/gallery/video/VideoSubtitleProvider;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTask:Lcom/miui/gallery/threadpool/ThreadPool$Job;

    new-instance v0, Lcom/miui/gallery/video/VideoSubtitleProvider$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/video/VideoSubtitleProvider$1;-><init>(Lcom/miui/gallery/video/VideoSubtitleProvider;)V

    iput-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mFutureListener:Lcom/miui/gallery/threadpool/FutureListener;

    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mContext:Landroid/content/Context;

    iput-object p2, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mListener:Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/video/VideoSubtitleProvider;)Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mListener:Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/video/VideoSubtitleProvider;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mVideoPath:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/video/VideoSubtitleProvider;)Landroid/graphics/Typeface;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypeface:Landroid/graphics/Typeface;

    return-object p0
.end method

.method private createTextTypeface()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypefaceLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypeface:Landroid/graphics/Typeface;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v1, :cond_2

    :try_start_1
    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/util/BuildUtil;->isMiui10(Landroid/content/Context;)Z

    move-result v1

    if-eqz v1, :cond_0

    const-string v1, "/system/fonts/Miui-Light.ttf"

    invoke-static {v1}, Landroid/graphics/Typeface;->createFromFile(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypeface:Landroid/graphics/Typeface;

    goto :goto_0

    :cond_0
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x1a

    if-lt v1, v2, :cond_1

    new-instance v1, Landroid/graphics/Typeface$Builder;

    const-string v2, "/system/fonts/MiLanProVF.ttf"

    invoke-direct {v1, v2}, Landroid/graphics/Typeface$Builder;-><init>(Ljava/lang/String;)V

    const-string v2, "\'wght\' 305"

    invoke-virtual {v1, v2}, Landroid/graphics/Typeface$Builder;->setFontVariationSettings(Ljava/lang/String;)Landroid/graphics/Typeface$Builder;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/Typeface$Builder;->build()Landroid/graphics/Typeface;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypeface:Landroid/graphics/Typeface;

    goto :goto_0

    :cond_1
    const-string v1, "/system/fonts/MiLanProVF.ttf"

    invoke-static {v1}, Landroid/graphics/Typeface;->createFromFile(Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypeface:Landroid/graphics/Typeface;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catch_0
    move-exception v1

    :try_start_2
    const-string v2, "VideoSubtitleProvider"

    const-string v3, "createTextTypeface occurs error.\n"

    invoke-static {v2, v3, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_2
    :goto_0
    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v1
.end method

.method public static findActiveSubtitle(Ljava/util/List;J)Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
            ">;J)",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;"
        }
    .end annotation

    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_7

    const-wide/16 v2, 0x0

    cmp-long v0, p1, v2

    if-gez v0, :cond_0

    goto :goto_1

    :cond_0
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v2, 0x0

    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;

    invoke-virtual {v3}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->getStartTime()J

    move-result-wide v3

    cmp-long v3, p1, v3

    if-gez v3, :cond_1

    return-object v1

    :cond_1
    add-int/lit8 v0, v0, -0x1

    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;

    invoke-virtual {v3}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->getStartTime()J

    move-result-wide v4

    cmp-long v4, p1, v4

    if-ltz v4, :cond_3

    invoke-virtual {v3}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->getEndTime()J

    move-result-wide v4

    cmp-long p0, p1, v4

    if-lez p0, :cond_2

    return-object v1

    :cond_2
    return-object v3

    :cond_3
    :goto_0
    add-int/lit8 v3, v2, 0x1

    if-le v0, v3, :cond_5

    add-int v3, v0, v2

    div-int/lit8 v3, v3, 0x2

    invoke-interface {p0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;

    invoke-virtual {v4}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->getStartTime()J

    move-result-wide v4

    cmp-long v4, v4, p1

    if-lez v4, :cond_4

    move v0, v3

    goto :goto_0

    :cond_4
    move v2, v3

    goto :goto_0

    :cond_5
    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;

    invoke-virtual {p0}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->getEndTime()J

    move-result-wide v2

    cmp-long p1, p1, v2

    if-lez p1, :cond_6

    return-object v1

    :cond_6
    return-object p0

    :cond_7
    :goto_1
    return-object v1
.end method

.method private static getSubtitleUri(Landroid/content/Context;Ljava/lang/String;)Landroid/net/Uri;
    .locals 0

    if-eqz p0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const-string p0, "content://com.miui.video.SRT/srt"

    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    invoke-virtual {p0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroid/net/Uri$Builder;->appendPath(Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object p0

    invoke-virtual {p0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private static parseMs(Ljava/lang/String;)J
    .locals 14

    const-string v0, ":"

    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    aget-object v1, v1, v2

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v3

    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    const/4 v5, 0x1

    aget-object v1, v1, v5

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v6

    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    const/4 v8, 0x2

    aget-object v1, v1, v8

    const-string v9, ","

    invoke-virtual {v1, v9}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    aget-object v1, v1, v2

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v1

    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    aget-object p0, p0, v8

    invoke-virtual {p0, v9}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    aget-object p0, p0, v5

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v8

    const-wide/16 v10, 0x3c

    mul-long/2addr v3, v10

    mul-long/2addr v3, v10

    const-wide/16 v12, 0x3e8

    mul-long/2addr v3, v12

    mul-long/2addr v6, v10

    mul-long/2addr v6, v12

    add-long/2addr v3, v6

    mul-long/2addr v1, v12

    add-long/2addr v3, v1

    add-long/2addr v3, v8

    return-wide v3
.end method

.method private parseSrtInputStream(Ljava/io/InputStream;)Ljava/util/List;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/InputStream;",
            ")",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    if-eqz p1, :cond_4

    invoke-virtual {p1}, Ljava/io/InputStream;->available()I

    move-result v0

    if-gtz v0, :cond_0

    goto :goto_2

    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    new-instance v1, Ljava/io/InputStreamReader;

    const-string v2, "UTF-8"

    invoke-direct {v1, p1, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/lang/String;)V

    new-instance p1, Ljava/io/BufferedReader;

    invoke-direct {p1, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    :cond_1
    invoke-virtual {p1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_3

    invoke-virtual {p1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_2

    goto :goto_1

    :cond_2
    new-instance v2, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;

    invoke-direct {v2}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;-><init>()V

    const-string v3, "-->"

    invoke-virtual {v1, v3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    const/4 v3, 0x0

    aget-object v3, v1, v3

    invoke-static {v3}, Lcom/miui/gallery/video/VideoSubtitleProvider;->parseMs(Ljava/lang/String;)J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->setStartTime(J)V

    const/4 v3, 0x1

    aget-object v1, v1, v3

    invoke-static {v1}, Lcom/miui/gallery/video/VideoSubtitleProvider;->parseMs(Ljava/lang/String;)J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->setEndTime(J)V

    invoke-virtual {p1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->setText(Ljava/lang/String;)V

    invoke-interface {v0, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_0
    invoke-virtual {p1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    const-string v2, ""

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    :cond_3
    :goto_1
    return-object v0

    :cond_4
    :goto_2
    const/4 p1, 0x0

    return-object p1
.end method


# virtual methods
.method public cancel()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mFuture:Lcom/miui/gallery/threadpool/Future;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/threadpool/Future;->cancel()V

    iput-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mFuture:Lcom/miui/gallery/threadpool/Future;

    :cond_0
    iput-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mVideoPath:Ljava/lang/String;

    return-void
.end method

.method public synthetic lambda$new$15$VideoSubtitleProvider(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/util/List;
    .locals 5

    invoke-interface {p1}, Lcom/miui/gallery/threadpool/ThreadPool$JobContext;->isCancelled()Z

    move-result p1

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    return-object v0

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/video/VideoSubtitleProvider;->createTextTypeface()V

    iget-object p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mVideoPath:Ljava/lang/String;

    if-eqz p1, :cond_3

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_1

    goto :goto_2

    :cond_1
    invoke-static {p1, v1}, Lcom/miui/gallery/video/VideoSubtitleProvider;->getSubtitleUri(Landroid/content/Context;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    if-nez v1, :cond_2

    return-object v0

    :cond_2
    :try_start_0
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    invoke-virtual {p1, v1}, Landroid/content/ContentResolver;->openInputStream(Landroid/net/Uri;)Ljava/io/InputStream;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :try_start_1
    invoke-direct {p0, p1}, Lcom/miui/gallery/video/VideoSubtitleProvider;->parseSrtInputStream(Ljava/io/InputStream;)Ljava/util/List;

    move-result-object v0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-object v0

    :catch_0
    move-exception v1

    goto :goto_0

    :catchall_0
    move-exception p1

    move-object v4, v0

    move-object v0, p1

    move-object p1, v4

    goto :goto_1

    :catch_1
    move-exception v1

    move-object p1, v0

    :goto_0
    :try_start_2
    const-string v2, "VideoSubtitleProvider"

    const-string v3, "read subtitle error.\n"

    invoke-static {v2, v3, v1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-object v0

    :catchall_1
    move-exception v0

    :goto_1
    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw v0

    :cond_3
    :goto_2
    return-object v0
.end method

.method public release()V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/video/VideoSubtitleProvider;->cancel()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mContext:Landroid/content/Context;

    iput-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mFutureListener:Lcom/miui/gallery/threadpool/FutureListener;

    iput-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTypeface:Landroid/graphics/Typeface;

    return-void
.end method

.method public request(Ljava/lang/String;)V
    .locals 2

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mFuture:Lcom/miui/gallery/threadpool/Future;

    if-eqz v0, :cond_1

    invoke-interface {v0}, Lcom/miui/gallery/threadpool/Future;->cancel()V

    :cond_1
    iput-object p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mVideoPath:Ljava/lang/String;

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mTask:Lcom/miui/gallery/threadpool/ThreadPool$Job;

    iget-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mFutureListener:Lcom/miui/gallery/threadpool/FutureListener;

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;Lcom/miui/gallery/threadpool/FutureListener;)Lcom/miui/gallery/threadpool/Future;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider;->mFuture:Lcom/miui/gallery/threadpool/Future;

    return-void
.end method
