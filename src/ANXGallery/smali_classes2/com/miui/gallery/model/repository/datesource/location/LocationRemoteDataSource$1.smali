.class Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource$1;
.super Ljava/lang/Object;
.source "LocationRemoteDataSource.java"

# interfaces
.implements Ljava/util/concurrent/Callable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource;->getLocationInfo(Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)Lio/reactivex/Flowable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/concurrent/Callable<",
        "Landroid/location/Address;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource;

.field final synthetic val$requestBean:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource;Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource$1;->this$0:Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource;

    iput-object p2, p0, Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource$1;->val$requestBean:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public call()Landroid/location/Address;
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/data/ReverseGeocoder;

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/miui/gallery/data/ReverseGeocoder;-><init>(Landroid/content/Context;)V

    iget-object v1, p0, Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource$1;->val$requestBean:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;->getLatitude()D

    move-result-wide v1

    iget-object v3, p0, Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource$1;->val$requestBean:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;

    invoke-virtual {v3}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;->getLongitude()D

    move-result-wide v3

    const/4 v5, 0x1

    invoke-virtual/range {v0 .. v5}, Lcom/miui/gallery/data/ReverseGeocoder;->lookupAddress(DDZ)Landroid/location/Address;

    move-result-object v0

    if-nez v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/data/LocationUtil;->getInvalidAddress()Landroid/location/Address;

    move-result-object v0

    :cond_0
    return-object v0
.end method

.method public bridge synthetic call()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/model/repository/datesource/location/LocationRemoteDataSource$1;->call()Landroid/location/Address;

    move-result-object v0

    return-object v0
.end method
