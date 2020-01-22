.class public Lcom/miui/gallery/model/repository/LocationRepository;
.super Ljava/lang/Object;
.source "LocationRepository.java"

# interfaces
.implements Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;


# instance fields
.field private mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

.field private mRemoteDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;


# direct methods
.method public constructor <init>()V
    .locals 2

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/location/LocationLocalDataSource;

    invoke-direct {v0}, Lcom/miui/gallery/model/repository/datesource/location/LocationLocalDataSource;-><init>()V

    new-instance v1, Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource;

    invoke-direct {v1}, Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource;-><init>()V

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/model/repository/LocationRepository;-><init>(Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;)V

    return-void
.end method

.method public constructor <init>(Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/model/repository/LocationRepository;->mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    iput-object p2, p0, Lcom/miui/gallery/model/repository/LocationRepository;->mRemoteDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    return-void
.end method


# virtual methods
.method public getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;
    .locals 3
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

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;->isHaveId()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;->isHaveCoordinate()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x2

    new-array v0, v0, [Lorg/reactivestreams/Publisher;

    const/4 v1, 0x0

    iget-object v2, p0, Lcom/miui/gallery/model/repository/LocationRepository;->mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-interface {v2, p1}, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;->getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;

    move-result-object v2

    aput-object v2, v0, v1

    const/4 v1, 0x1

    iget-object v2, p0, Lcom/miui/gallery/model/repository/LocationRepository;->mRemoteDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-interface {v2, p1}, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;->getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    aput-object p1, v0, v1

    invoke-static {v0}, Lio/reactivex/Flowable;->concatArrayDelayError([Lorg/reactivestreams/Publisher;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;->isHaveId()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/model/repository/LocationRepository;->mLocalDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;->getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/model/repository/LocationRepository;->mRemoteDataSource:Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-interface {v0, p1}, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;->getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
