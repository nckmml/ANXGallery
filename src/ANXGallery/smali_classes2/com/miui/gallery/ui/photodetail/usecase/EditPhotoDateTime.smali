.class public Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;
.super Lcom/miui/gallery/app/base/BaseUseCase;
.source "EditPhotoDateTime.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/app/base/BaseUseCase<",
        "Ljava/lang/Boolean;",
        "Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;",
        ">;"
    }
.end annotation


# instance fields
.field protected mRepository:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseUseCase;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;->mRepository:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    return-void
.end method


# virtual methods
.method protected buildUseCaseFlowable(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;
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

    if-nez p1, :cond_0

    invoke-static {}, Lio/reactivex/Flowable;->empty()Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;->mRepository:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;->editPhotoDateTimeDetailInfo(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method protected bridge synthetic buildUseCaseFlowable(Ljava/lang/Object;)Lio/reactivex/Flowable;
    .locals 0

    check-cast p1, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;->buildUseCaseFlowable(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
