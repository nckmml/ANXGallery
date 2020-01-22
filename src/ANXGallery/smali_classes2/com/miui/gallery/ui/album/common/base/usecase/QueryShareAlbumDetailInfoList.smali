.class public Lcom/miui/gallery/ui/album/common/base/usecase/QueryShareAlbumDetailInfoList;
.super Lcom/miui/gallery/app/base/BaseUseCase;
.source "QueryShareAlbumDetailInfoList.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/app/base/BaseUseCase<",
        "Ljava/util/List<",
        "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
        ">;",
        "Ljava/lang/Void;",
        ">;"
    }
.end annotation


# instance fields
.field private mRepository:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseUseCase;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/album/common/base/usecase/QueryShareAlbumDetailInfoList;->mRepository:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    return-void
.end method


# virtual methods
.method protected bridge synthetic buildUseCaseFlowable(Ljava/lang/Object;)Lio/reactivex/Flowable;
    .locals 0

    check-cast p1, Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/common/base/usecase/QueryShareAlbumDetailInfoList;->buildUseCaseFlowable(Ljava/lang/Void;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method protected buildUseCaseFlowable(Ljava/lang/Void;)Lio/reactivex/Flowable;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Void;",
            ")",
            "Lio/reactivex/Flowable<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;",
            ">;>;"
        }
    .end annotation

    iget-object p1, p0, Lcom/miui/gallery/ui/album/common/base/usecase/QueryShareAlbumDetailInfoList;->mRepository:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-interface {p1}, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;->queryAlbumListShareInfo()Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
