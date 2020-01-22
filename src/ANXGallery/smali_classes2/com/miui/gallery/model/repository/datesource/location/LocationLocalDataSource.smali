.class public Lcom/miui/gallery/model/repository/datesource/location/LocationLocalDataSource;
.super Ljava/lang/Object;
.source "LocationLocalDataSource.java"

# interfaces
.implements Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;
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

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/location/LocationLocalDataSource$1;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/model/repository/datesource/location/LocationLocalDataSource$1;-><init>(Lcom/miui/gallery/model/repository/datesource/location/LocationLocalDataSource;Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)V

    invoke-static {v0}, Lio/reactivex/Flowable;->fromCallable(Ljava/util/concurrent/Callable;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
