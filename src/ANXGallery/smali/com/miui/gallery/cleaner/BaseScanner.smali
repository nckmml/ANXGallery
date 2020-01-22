.class public abstract Lcom/miui/gallery/cleaner/BaseScanner;
.super Ljava/lang/Object;
.source "BaseScanner.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;,
        Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;
    }
.end annotation


# instance fields
.field mListeners:Ljava/util/concurrent/CopyOnWriteArraySet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/CopyOnWriteArraySet<",
            "Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;",
            ">;"
        }
    .end annotation
.end field

.field protected mMediaItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;",
            ">;"
        }
    .end annotation
.end field

.field protected mOnScanResultClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

.field protected mType:I


# direct methods
.method protected constructor <init>(I)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    new-instance v0, Lcom/miui/gallery/cleaner/BaseScanner$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/cleaner/BaseScanner$1;-><init>(Lcom/miui/gallery/cleaner/BaseScanner;)V

    iput-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mOnScanResultClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

    new-instance v0, Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-direct {v0}, Ljava/util/concurrent/CopyOnWriteArraySet;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mListeners:Ljava/util/concurrent/CopyOnWriteArraySet;

    iput p1, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mType:I

    return-void
.end method

.method private updateScanResult(JLcom/miui/gallery/cleaner/ScanResult;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mListeners:Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArraySet;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;

    if-eqz v1, :cond_0

    iget v2, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mType:I

    invoke-interface {v1, v2, p1, p2, p3}, Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;->onUpdate(IJLcom/miui/gallery/cleaner/ScanResult;)V

    goto :goto_0

    :cond_1
    return-void
.end method


# virtual methods
.method public final addListener(Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;)V
    .locals 1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mListeners:Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/CopyOnWriteArraySet;->add(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method

.method protected buildScanResult()Lcom/miui/gallery/cleaner/ScanResult;
    .locals 10

    const/4 v0, 0x4

    new-array v0, v0, [Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

    iget-object v1, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    monitor-enter v1

    :try_start_0
    iget-object v2, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    const/4 v3, 0x0

    const-wide/16 v4, 0x0

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;

    iget-wide v7, v6, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mSize:J

    add-long/2addr v4, v7

    array-length v7, v0

    if-ge v3, v7, :cond_0

    new-instance v7, Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

    iget-wide v8, v6, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mId:J

    iget-object v6, v6, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mPath:Ljava/lang/String;

    invoke-direct {v7, v8, v9, v6}, Lcom/miui/gallery/cleaner/ScanResult$ResultImage;-><init>(JLjava/lang/String;)V

    aput-object v7, v0, v3

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    new-instance v1, Lcom/miui/gallery/cleaner/ScanResult$Builder;

    invoke-direct {v1}, Lcom/miui/gallery/cleaner/ScanResult$Builder;-><init>()V

    iget v2, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mType:I

    invoke-virtual {v1, v2}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setType(I)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v1

    invoke-virtual {v1, v4, v5}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setSize(J)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setImages([Lcom/miui/gallery/cleaner/ScanResult$ResultImage;)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    invoke-virtual {v0, v3}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setCount(I)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mOnScanResultClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setOnScanResultClickListener(Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->build()Lcom/miui/gallery/cleaner/ScanResult;

    move-result-object v0

    return-object v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public getScanResultIds()Ljava/util/List;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2, v1}, Ljava/util/ArrayList;-><init>(I)V

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v1, :cond_0

    iget-object v4, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;

    iget-wide v4, v4, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mId:J

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-interface {v2, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_0
    monitor-exit v0

    return-object v2

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method protected abstract getSelection()Ljava/lang/String;
.end method

.method public isLoadingValid()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public onMediaItemDeleted(J)V
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/cleaner/BaseScanner;->removeItem(J)Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/BaseScanner;->buildScanResult()Lcom/miui/gallery/cleaner/ScanResult;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/cleaner/BaseScanner;->updateScanResult(Lcom/miui/gallery/cleaner/ScanResult;)V

    :cond_0
    return-void
.end method

.method protected onReset()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    return-void
.end method

.method protected abstract recordClickScanResultEvent()V
.end method

.method protected removeItem(J)Z
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;

    iget-wide v2, v2, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mId:J

    cmp-long v2, v2, p1

    if-nez v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->remove()V

    const/4 p1, 0x1

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    :goto_0
    monitor-exit v0

    return p1

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public removeItems([J)V
    .locals 6

    if-nez p1, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    monitor-enter v0

    :try_start_0
    array-length v1, p1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_2

    aget-wide v3, p1, v2

    invoke-virtual {p0, v3, v4}, Lcom/miui/gallery/cleaner/BaseScanner;->removeItem(J)Z

    move-result v5

    if-eqz v5, :cond_1

    long-to-int v3, v3

    int-to-long v3, v3

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/BaseScanner;->buildScanResult()Lcom/miui/gallery/cleaner/ScanResult;

    move-result-object v5

    invoke-direct {p0, v3, v4, v5}, Lcom/miui/gallery/cleaner/BaseScanner;->updateScanResult(JLcom/miui/gallery/cleaner/ScanResult;)V

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public final removeListener(Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;)V
    .locals 1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mListeners:Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/CopyOnWriteArraySet;->remove(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method

.method public final reset()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mListeners:Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArraySet;->clear()V

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/BaseScanner;->onReset()V

    return-void
.end method

.method protected scan()Lcom/miui/gallery/cleaner/ScanResult;
    .locals 8

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/BaseScanner;->getSelection()Ljava/lang/String;

    move-result-object v3

    const/4 v7, 0x0

    if-nez v3, :cond_0

    return-object v7

    :cond_0
    invoke-static {}, Lcom/miui/gallery/util/StaticContext;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lcom/miui/gallery/provider/GalleryContract$Media;->URI_OWNER_ALBUM_MEDIA:Landroid/net/Uri;

    sget-object v2, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->NORMAL_SCAN_PROJECTION:[Ljava/lang/String;

    const/4 v4, 0x0

    new-instance v6, Lcom/miui/gallery/cleaner/BaseScanner$2;

    invoke-direct {v6, p0}, Lcom/miui/gallery/cleaner/BaseScanner$2;-><init>(Lcom/miui/gallery/cleaner/BaseScanner;)V

    const-string v5, "alias_create_time DESC"

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v1

    if-nez v1, :cond_1

    return-object v7

    :cond_1
    iput-object v0, p0, Lcom/miui/gallery/cleaner/BaseScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/BaseScanner;->buildScanResult()Lcom/miui/gallery/cleaner/ScanResult;

    move-result-object v0

    return-object v0
.end method

.method protected final updateScanResult(Lcom/miui/gallery/cleaner/ScanResult;)V
    .locals 2

    const-wide/16 v0, 0x0

    invoke-direct {p0, v0, v1, p1}, Lcom/miui/gallery/cleaner/BaseScanner;->updateScanResult(JLcom/miui/gallery/cleaner/ScanResult;)V

    return-void
.end method
