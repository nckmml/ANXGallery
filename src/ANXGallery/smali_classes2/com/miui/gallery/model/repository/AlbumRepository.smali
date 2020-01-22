.class public Lcom/miui/gallery/model/repository/AlbumRepository;
.super Ljava/lang/Object;
.source "AlbumRepository.java"

# interfaces
.implements Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;


# instance fields
.field private mDbDataSource:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;


# direct methods
.method public constructor <init>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

    invoke-direct {v0}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;-><init>()V

    invoke-direct {p0, v0}, Lcom/miui/gallery/model/repository/AlbumRepository;-><init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V

    return-void
.end method

.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/model/repository/AlbumRepository;->mDbDataSource:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

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

    iget-object v0, p0, Lcom/miui/gallery/model/repository/AlbumRepository;->mDbDataSource:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-interface {v0, p1, p2}, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;->cancelAlbumHiddenStatus(J)Lio/reactivex/Flowable;

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

    iget-object v0, p0, Lcom/miui/gallery/model/repository/AlbumRepository;->mDbDataSource:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-interface {v0, p1, p2, p3}, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;->doChangeAlbumBackupStatus(ZJ)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public queryAlbumListShareInfo()Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
            ">;>;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/model/repository/AlbumRepository;->mDbDataSource:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-interface {v0}, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;->queryAlbumListShareInfo()Lio/reactivex/Flowable;

    move-result-object v0

    return-object v0
.end method

.method public queryAlbumsExcludeSystemAndRubbish()Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/Album;",
            ">;>;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/model/repository/AlbumRepository;->mDbDataSource:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-interface {v0}, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;->queryAlbumsExcludeSystemAndRubbish()Lio/reactivex/Flowable;

    move-result-object v0

    return-object v0
.end method

.method public queryHiddenAlbum()Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/Album;",
            ">;>;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/model/repository/AlbumRepository;->mDbDataSource:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-interface {v0}, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;->queryHiddenAlbum()Lio/reactivex/Flowable;

    move-result-object v0

    return-object v0
.end method
