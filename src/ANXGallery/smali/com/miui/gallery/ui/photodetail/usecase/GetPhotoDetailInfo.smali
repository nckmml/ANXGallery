.class public Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;
.super Lcom/miui/gallery/app/base/BaseUseCase;
.source "GetPhotoDetailInfo.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/app/base/BaseUseCase<",
        "Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;",
        "Lcom/miui/gallery/model/BaseDataItem;",
        ">;"
    }
.end annotation


# instance fields
.field private mRepository:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseUseCase;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;->mRepository:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    return-void
.end method


# virtual methods
.method protected buildUseCaseFlowable(Lcom/miui/gallery/model/BaseDataItem;)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/model/BaseDataItem;",
            ")",
            "Lio/reactivex/Flowable<",
            "Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;",
            ">;"
        }
    .end annotation

    if-nez p1, :cond_0

    invoke-static {}, Lio/reactivex/Flowable;->empty()Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;->mRepository:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;->getPhotoDetailInfo(Lcom/miui/gallery/model/BaseDataItem;)Lio/reactivex/Flowable;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo$1;-><init>(Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;)V

    invoke-virtual {p1, v0}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method protected bridge synthetic buildUseCaseFlowable(Ljava/lang/Object;)Lio/reactivex/Flowable;
    .locals 0

    check-cast p1, Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;->buildUseCaseFlowable(Lcom/miui/gallery/model/BaseDataItem;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
