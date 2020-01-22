.class public interface abstract Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;
.super Ljava/lang/Object;
.source "IAlbumRepository.java"


# virtual methods
.method public abstract cancelAlbumHiddenStatus(J)Lio/reactivex/Flowable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end method

.method public abstract doChangeAlbumBackupStatus(ZJ)Lio/reactivex/Flowable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ZJ)",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end method

.method public abstract queryAlbumListShareInfo()Lio/reactivex/Flowable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
            ">;>;"
        }
    .end annotation
.end method

.method public abstract queryAlbumsExcludeSystemAndRubbish()Lio/reactivex/Flowable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/Album;",
            ">;>;"
        }
    .end annotation
.end method

.method public abstract queryHiddenAlbum()Lio/reactivex/Flowable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/Album;",
            ">;>;"
        }
    .end annotation
.end method
