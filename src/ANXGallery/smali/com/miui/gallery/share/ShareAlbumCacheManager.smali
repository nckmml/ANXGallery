.class public Lcom/miui/gallery/share/ShareAlbumCacheManager;
.super Ljava/lang/Object;
.source "ShareAlbumCacheManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/share/ShareAlbumCacheManager$SingletonHolder;
    }
.end annotation


# instance fields
.field private mSharedAlbums:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
            ">;"
        }
    .end annotation
.end field

.field private final readLock:Ljava/util/concurrent/locks/Lock;

.field private final readWriteLock:Ljava/util/concurrent/locks/ReadWriteLock;

.field private final writeLock:Ljava/util/concurrent/locks/Lock;


# direct methods
.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-direct {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->readWriteLock:Ljava/util/concurrent/locks/ReadWriteLock;

    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->readWriteLock:Ljava/util/concurrent/locks/ReadWriteLock;

    invoke-interface {v0}, Ljava/util/concurrent/locks/ReadWriteLock;->readLock()Ljava/util/concurrent/locks/Lock;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->readLock:Ljava/util/concurrent/locks/Lock;

    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->readWriteLock:Ljava/util/concurrent/locks/ReadWriteLock;

    invoke-interface {v0}, Ljava/util/concurrent/locks/ReadWriteLock;->writeLock()Ljava/util/concurrent/locks/Lock;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->writeLock:Ljava/util/concurrent/locks/Lock;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/share/ShareAlbumCacheManager$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/share/ShareAlbumCacheManager;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/share/ShareAlbumCacheManager;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/share/ShareAlbumCacheManager$SingletonHolder;->access$000()Lcom/miui/gallery/share/ShareAlbumCacheManager;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method public containsKey(J)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getShareAlbumMaps()Ljava/util/Map;

    move-result-object v0

    invoke-static {p1, p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result p1

    return p1
.end method

.method public getShareAlbum(J)Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getShareAlbumMaps()Ljava/util/Map;

    move-result-object v0

    invoke-static {p1, p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;

    return-object p1
.end method

.method public getShareAlbumList()Ljava/util/Collection;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection<",
            "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return-object v0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getShareAlbumMaps()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v0

    invoke-static {v0}, Ljava/util/Collections;->unmodifiableCollection(Ljava/util/Collection;)Ljava/util/Collection;

    move-result-object v0

    return-object v0
.end method

.method public getShareAlbumMaps()Ljava/util/Map;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
            ">;"
        }
    .end annotation

    :try_start_0
    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->readLock:Ljava/util/concurrent/locks/Lock;

    invoke-interface {v0}, Ljava/util/concurrent/locks/Lock;->lock()V

    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    invoke-static {v0}, Ljava/util/Collections;->unmodifiableMap(Ljava/util/Map;)Ljava/util/Map;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object v1, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->readLock:Ljava/util/concurrent/locks/Lock;

    invoke-interface {v1}, Ljava/util/concurrent/locks/Lock;->unlock()V

    return-object v0

    :catchall_0
    move-exception v0

    iget-object v1, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->readLock:Ljava/util/concurrent/locks/Lock;

    invoke-interface {v1}, Ljava/util/concurrent/locks/Lock;->unlock()V

    throw v0
.end method

.method public putSharedAlbums(Landroid/database/Cursor;)Z
    .locals 5

    const/4 v0, 0x0

    if-eqz p1, :cond_6

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result v1

    if-nez v1, :cond_0

    goto/16 :goto_2

    :cond_0
    const/4 v1, 0x1

    :try_start_0
    iget-object v2, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->writeLock:Ljava/util/concurrent/locks/Lock;

    invoke-interface {v2}, Ljava/util/concurrent/locks/Lock;->lockInterruptibly()V

    iget-object v2, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    if-eqz v2, :cond_1

    iget-object v2, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    invoke-interface {v2}, Ljava/util/Map;->clear()V

    :cond_1
    invoke-interface {p1}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v2

    if-eqz v2, :cond_4

    :cond_2
    new-instance v2, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;

    invoke-direct {v2}, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;-><init>()V

    invoke-interface {p1, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v2, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->mAlbumId:Ljava/lang/String;

    invoke-interface {p1, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v2, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->mCreatorId:Ljava/lang/String;

    const/4 v3, 0x2

    invoke-interface {p1, v3}, Landroid/database/Cursor;->getInt(I)I

    move-result v3

    iput v3, v2, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->mUserCount:I

    const/4 v3, 0x3

    invoke-interface {p1, v3}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v2, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->mOwnerNickName:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    if-nez v3, :cond_3

    new-instance v3, Ljava/util/HashMap;

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result v4

    invoke-direct {v3, v4}, Ljava/util/HashMap;-><init>(I)V

    iput-object v3, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    :cond_3
    iget-object v3, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    iget-object v4, v2, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->mAlbumId:Ljava/lang/String;

    invoke-interface {v3, v4, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-interface {p1}, Landroid/database/Cursor;->moveToNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Thread;->isInterrupted()Z

    move-result v2
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v2, :cond_2

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    :catch_0
    move-exception p1

    :try_start_1
    invoke-virtual {p1}, Ljava/lang/InterruptedException;->printStackTrace()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :cond_4
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->writeLock:Ljava/util/concurrent/locks/Lock;

    invoke-interface {p1}, Ljava/util/concurrent/locks/Lock;->unlock()V

    iget-object p1, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->mSharedAlbums:Ljava/util/Map;

    invoke-interface {p1}, Ljava/util/Map;->size()I

    move-result p1

    if-lez p1, :cond_5

    move v0, v1

    :cond_5
    return v0

    :goto_1
    iget-object v0, p0, Lcom/miui/gallery/share/ShareAlbumCacheManager;->writeLock:Ljava/util/concurrent/locks/Lock;

    invoke-interface {v0}, Ljava/util/concurrent/locks/Lock;->unlock()V

    throw p1

    :cond_6
    :goto_2
    return v0
.end method
