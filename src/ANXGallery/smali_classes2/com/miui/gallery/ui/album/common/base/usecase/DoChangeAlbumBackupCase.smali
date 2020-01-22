.class public Lcom/miui/gallery/ui/album/common/base/usecase/DoChangeAlbumBackupCase;
.super Lcom/miui/gallery/app/base/BaseUseCase;
.source "DoChangeAlbumBackupCase.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/app/base/BaseUseCase<",
        "Ljava/lang/Boolean;",
        "Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;",
        ">;"
    }
.end annotation


# instance fields
.field private mRepository:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseUseCase;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/album/common/base/usecase/DoChangeAlbumBackupCase;->mRepository:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    return-void
.end method


# virtual methods
.method protected buildUseCaseFlowable(Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;)Lio/reactivex/Flowable;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;",
            ")",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/album/common/base/usecase/DoChangeAlbumBackupCase;->mRepository:Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;->isEnable()Z

    move-result v1

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;->getAlbumId()J

    move-result-wide v2

    invoke-interface {v0, v1, v2, v3}, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;->doChangeAlbumBackupStatus(ZJ)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method protected bridge synthetic buildUseCaseFlowable(Ljava/lang/Object;)Lio/reactivex/Flowable;
    .locals 0

    check-cast p1, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/common/base/usecase/DoChangeAlbumBackupCase;->buildUseCaseFlowable(Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
