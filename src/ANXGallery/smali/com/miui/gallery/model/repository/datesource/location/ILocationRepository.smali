.class public interface abstract Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;
.super Ljava/lang/Object;
.source "ILocationRepository.java"


# virtual methods
.method public abstract getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;
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
.end method
