.class public Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;
.super Lcom/miui/gallery/app/base/BaseUseCase;
.source "GetLocationInfo.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/app/base/BaseUseCase<",
        "Landroid/location/Address;",
        "Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;",
        ">;"
    }
.end annotation


# instance fields
.field private mRepository:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseUseCase;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;->mRepository:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    return-void
.end method


# virtual methods
.method protected buildUseCaseFlowable(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;",
            ")",
            "Lio/reactivex/Flowable<",
            "Landroid/location/Address;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;->mRepository:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;->getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method protected bridge synthetic buildUseCaseFlowable(Ljava/lang/Object;)Lio/reactivex/Flowable;
    .locals 0

    check-cast p1, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;->buildUseCaseFlowable(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
