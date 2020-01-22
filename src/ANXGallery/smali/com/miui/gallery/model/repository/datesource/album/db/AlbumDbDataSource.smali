.class public Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;
.super Ljava/lang/Object;
.source "AlbumDbDataSource.java"

# interfaces
.implements Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;


# instance fields
.field private mAlbumCursorSafeConvert:Lcom/miui/gallery/loader/CursorConvertCallback;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/loader/CursorConvertCallback<",
            "Lcom/miui/gallery/model/AlbumList;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/miui/gallery/loader/AlbumConvertCallback;

    invoke-direct {v0}, Lcom/miui/gallery/loader/AlbumConvertCallback;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->mAlbumCursorSafeConvert:Lcom/miui/gallery/loader/CursorConvertCallback;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)Lcom/miui/gallery/loader/CursorConvertCallback;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->mAlbumCursorSafeConvert:Lcom/miui/gallery/loader/CursorConvertCallback;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;Landroid/net/Uri;ZLandroid/database/ContentObserver;)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->registerContentObserver(Landroid/net/Uri;ZLandroid/database/ContentObserver;)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;Landroid/database/ContentObserver;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->unRegisterContentObserver(Landroid/database/ContentObserver;)V

    return-void
.end method

.method private registerContentObserver(Landroid/net/Uri;ZLandroid/database/ContentObserver;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    invoke-virtual {v0, p1, p2, p3}, Landroid/content/ContentResolver;->registerContentObserver(Landroid/net/Uri;ZLandroid/database/ContentObserver;)V

    return-void
.end method

.method private unRegisterContentObserver(Landroid/database/ContentObserver;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/ContentResolver;->unregisterContentObserver(Landroid/database/ContentObserver;)V

    return-void
.end method


# virtual methods
.method public cancelAlbumHiddenStatus(J)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    const/4 v0, 0x0

    invoke-virtual {p0, v0, p1, p2}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->doChangeAlbumHiddenStatus(ZJ)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public doChangeAlbumBackupStatus(ZJ)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ZJ)",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$6;

    invoke-direct {v0, p0, p2, p3, p1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$6;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;JZ)V

    invoke-static {v0}, Lio/reactivex/Flowable;->fromCallable(Ljava/util/concurrent/Callable;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public doChangeAlbumHiddenStatus(ZJ)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ZJ)",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;

    invoke-direct {v0, p0, p2, p3, p1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;JZ)V

    invoke-static {v0}, Lio/reactivex/Flowable;->fromCallable(Ljava/util/concurrent/Callable;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public queryAlbumListShareInfo()Lio/reactivex/Flowable;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
            ">;>;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$4;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)V

    sget-object v1, Lio/reactivex/BackpressureStrategy;->BUFFER:Lio/reactivex/BackpressureStrategy;

    invoke-static {v0, v1}, Lio/reactivex/Flowable;->create(Lio/reactivex/FlowableOnSubscribe;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;

    move-result-object v0

    return-object v0
.end method

.method public queryAlbumsExcludeSystemAndRubbish()Lio/reactivex/Flowable;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/Album;",
            ">;>;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)V

    sget-object v1, Lio/reactivex/BackpressureStrategy;->BUFFER:Lio/reactivex/BackpressureStrategy;

    invoke-static {v0, v1}, Lio/reactivex/Flowable;->create(Lio/reactivex/FlowableOnSubscribe;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$1;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)V

    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object v0

    return-object v0
.end method

.method public queryHiddenAlbum()Lio/reactivex/Flowable;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/Album;",
            ">;>;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)V

    sget-object v1, Lio/reactivex/BackpressureStrategy;->BUFFER:Lio/reactivex/BackpressureStrategy;

    invoke-static {v0, v1}, Lio/reactivex/Flowable;->create(Lio/reactivex/FlowableOnSubscribe;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;

    move-result-object v0

    return-object v0
.end method
