.class public Lcom/miui/gallery/model/repository/PhotoRepository;
.super Ljava/lang/Object;
.source "PhotoRepository.java"

# interfaces
.implements Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;


# instance fields
.field public mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;


# direct methods
.method public constructor <init>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;

    invoke-direct {v0}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;-><init>()V

    invoke-direct {p0, v0}, Lcom/miui/gallery/model/repository/PhotoRepository;-><init>(Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;)V

    return-void
.end method

.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/model/repository/PhotoRepository;->mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    return-void
.end method


# virtual methods
.method public editPhotoDateTimeDetailInfo(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;",
            ")",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/model/repository/PhotoRepository;->mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;->editPhotoDateTimeDetailInfo(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public getPhotoDetailInfo(Lcom/miui/gallery/model/BaseDataItem;)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/model/BaseDataItem;",
            ")",
            "Lio/reactivex/Flowable<",
            "Lcom/miui/gallery/model/PhotoDetailInfo;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/model/repository/PhotoRepository;->mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;->getPhotoDetailInfo(Lcom/miui/gallery/model/BaseDataItem;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public queryAlbumNameByAlbumId(Ljava/lang/Long;)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Long;",
            ")",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/model/repository/PhotoRepository;->mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;->queryAlbumNameByAlbumId(Ljava/lang/Long;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
