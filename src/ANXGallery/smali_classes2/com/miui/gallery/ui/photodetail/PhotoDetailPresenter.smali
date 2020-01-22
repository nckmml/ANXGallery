.class public Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;
.super Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;
.source "PhotoDetailPresenter.java"


# static fields
.field private static final TAG:Ljava/lang/String; = "PhotoDetailPresenter"


# instance fields
.field private mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

.field private mDateCalendar:Ljava/util/Calendar;

.field private mDownLoadListener:Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;

.field private mEditPhotoDateTime:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;

.field private mGetLocationInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;

.field private mGetPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;

.field private mPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

.field private mUserSelectDateTime:J


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;)Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    return-object p0
.end method

.method static synthetic access$002(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    return-object p1
.end method

.method static synthetic access$100(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;)Lcom/miui/gallery/model/BaseDataItem;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;Landroid/location/Address;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->parseAddress(Landroid/location/Address;)V

    return-void
.end method

.method static synthetic access$302(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;J)J
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mUserSelectDateTime:J

    return-wide p1
.end method

.method private parseAddress(Landroid/location/Address;)V
    .locals 1

    if-eqz p1, :cond_0

    invoke-static {p1}, Lio/reactivex/Flowable;->just(Ljava/lang/Object;)Lio/reactivex/Flowable;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$4;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;)V

    invoke-virtual {p1, v0}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object p1

    invoke-static {}, Lcom/miui/gallery/util/RxUtils;->ioAndMainThread()Lio/reactivex/FlowableTransformer;

    move-result-object v0

    invoke-virtual {p1, v0}, Lio/reactivex/Flowable;->compose(Lio/reactivex/FlowableTransformer;)Lio/reactivex/Flowable;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$3;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;)V

    invoke-virtual {p1, v0}, Lio/reactivex/Flowable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->addDispose(Lio/reactivex/disposables/Disposable;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public canDownLoad()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public downLoadOrigin()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mDownLoadListener:Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$6;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$6;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mDownLoadListener:Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v0

    if-eqz v0, :cond_1

    new-instance v0, Lcom/miui/gallery/util/BulkDownloadHelper$BulkDownloadItem;

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getDownloadUri()Landroid/net/Uri;

    move-result-object v1

    sget-object v2, Lcom/miui/gallery/sdk/download/DownloadType;->ORIGIN_FORCE:Lcom/miui/gallery/sdk/download/DownloadType;

    iget-object v3, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v3}, Lcom/miui/gallery/model/BaseDataItem;->getSize()J

    move-result-wide v3

    invoke-direct {v0, v1, v2, v3, v4}, Lcom/miui/gallery/util/BulkDownloadHelper$BulkDownloadItem;-><init>(Landroid/net/Uri;Lcom/miui/gallery/sdk/download/DownloadType;J)V

    goto :goto_0

    :cond_1
    new-instance v0, Lcom/miui/gallery/util/BulkDownloadHelper$BulkDownloadItem;

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getDownloadUri()Landroid/net/Uri;

    move-result-object v1

    sget-object v2, Lcom/miui/gallery/sdk/download/DownloadType;->ORIGIN:Lcom/miui/gallery/sdk/download/DownloadType;

    iget-object v3, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v3}, Lcom/miui/gallery/model/BaseDataItem;->getSize()J

    move-result-wide v3

    invoke-direct {v0, v1, v2, v3, v4}, Lcom/miui/gallery/util/BulkDownloadHelper$BulkDownloadItem;-><init>(Landroid/net/Uri;Lcom/miui/gallery/sdk/download/DownloadType;J)V

    :goto_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    iget-object v2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mDownLoadListener:Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->showDownLoadProgressDialog(Ljava/util/ArrayList;Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;)V

    return-void
.end method

.method public getDatePickerStartTime()J
    .locals 5

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveDateTime()Z

    move-result v0

    const-wide/16 v1, 0x0

    if-eqz v0, :cond_1

    iget-wide v3, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mUserSelectDateTime:J

    cmp-long v0, v3, v1

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getDateTime()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mUserSelectDateTime:J

    :cond_0
    iget-wide v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mUserSelectDateTime:J

    return-wide v0

    :cond_1
    return-wide v1
.end method

.method public getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    return-object v0
.end method

.method public getFileLocalPath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return-object v0

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getThumnailPath()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    :goto_0
    return-object v0
.end method

.method public getLocation()[D
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return-object v0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getLocation()[D

    move-result-object v0

    return-object v0
.end method

.method public init(Lcom/miui/gallery/model/BaseDataItem;)V
    .locals 3

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    new-instance v0, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;

    invoke-static {}, Lcom/miui/gallery/model/repository/config/ModelManager;->getInstance()Lcom/miui/gallery/model/repository/config/ModelManager;

    move-result-object v1

    const-class v2, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/model/repository/config/ModelManager;->getModel(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-direct {v0, v1}, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;-><init>(Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mGetPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mGetPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;

    new-instance v1, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$1;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$1;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;Lcom/miui/gallery/model/BaseDataItem;)V

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    return-void
.end method

.method public isCanClick()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isHaveLocalPath()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getThumnailPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_2

    :cond_1
    const/4 v1, 0x1

    :cond_2
    return v1
.end method

.method public isHaveLocation()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getDetailInfo()Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveLocation()Z

    move-result v0

    return v0
.end method

.method public isThumbFile()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getThumnailPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v1, 0x1

    :cond_1
    return v1
.end method

.method protected onDestory()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->onDestory()V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mGetLocationInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;->dispose()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mEditPhotoDateTime:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;->dispose()V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mGetPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;->dispose()V

    :cond_2
    return-void
.end method

.method public requestLocation()V
    .locals 9

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mGetLocationInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;

    if-nez v0, :cond_1

    new-instance v0, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;

    invoke-static {}, Lcom/miui/gallery/model/repository/config/ModelManager;->getInstance()Lcom/miui/gallery/model/repository/config/ModelManager;

    move-result-object v1

    const-class v2, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/model/repository/config/ModelManager;->getModel(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-direct {v0, v1}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;-><init>(Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mGetLocationInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getLocation()[D

    move-result-object v0

    new-instance v8, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getKey()J

    move-result-wide v2

    const/4 v1, 0x0

    aget-wide v4, v0, v1

    const/4 v1, 0x1

    aget-wide v6, v0, v1

    move-object v1, v8

    invoke-direct/range {v1 .. v7}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo$RequestBean;-><init>(JDD)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mPhotoDetailInfo:Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getLocationText()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mGetLocationInfo:Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;

    new-instance v2, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;

    invoke-direct {v2, p0, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;Ljava/lang/String;)V

    invoke-virtual {v1, v2, v8}, Lcom/miui/gallery/ui/photodetail/usecase/GetLocationInfo;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    goto :goto_0

    :cond_2
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getFileLocalPath()Ljava/lang/String;

    move-result-object v0

    const-string v1, "PhotoDetailPresenter"

    const-string v2, "file path: %s no location"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->bindLocation(Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method public updatePhotoDateTime(J)V
    .locals 7

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    return-void

    :cond_1
    iget-wide v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mUserSelectDateTime:J

    invoke-static {p1, p2, v0, v1}, Lcom/miui/gallery/card/scenario/DateUtils;->isSameHour(JJ)Z

    move-result v0

    if-eqz v0, :cond_2

    return-void

    :cond_2
    const-string v0, "photo_detail"

    const-string v1, "click_photo_datetimepicker_confirm_button"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mEditPhotoDateTime:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;

    if-nez v0, :cond_3

    new-instance v0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;

    invoke-static {}, Lcom/miui/gallery/model/repository/config/ModelManager;->getInstance()Lcom/miui/gallery/model/repository/config/ModelManager;

    move-result-object v1

    const-class v2, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/model/repository/config/ModelManager;->getModel(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getLocalGroupId()J

    move-result-wide v3

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getCreateTime()J

    move-result-wide v5

    move-object v1, v0

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;-><init>(Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;JJ)V

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mEditPhotoDateTime:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;

    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mDateCalendar:Ljava/util/Calendar;

    :cond_3
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mDateCalendar:Ljava/util/Calendar;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Ljava/util/Calendar;->setTimeInMillis(J)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mDateCalendar:Ljava/util/Calendar;

    const/16 v1, 0xe

    invoke-virtual {v0, v1}, Ljava/util/Calendar;->get(I)I

    move-result v0

    int-to-long v0, v0

    add-long/2addr p1, v0

    new-instance v0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getKey()J

    move-result-wide v1

    invoke-direct {v0, p1, p2, v1, v2}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;-><init>(JJ)V

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mBaseDataItem:Lcom/miui/gallery/model/BaseDataItem;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Lcom/miui/gallery/model/BaseDataItem;->getFavoriteInfo(Z)Lcom/miui/gallery/model/FavoriteInfo;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/model/FavoriteInfo;->isFavorite()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->setFavorite(Z)V

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->mEditPhotoDateTime:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;

    new-instance v2, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;

    invoke-direct {v2, p0, p1, p2, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;JLcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)V

    invoke-virtual {v1, v2, v0}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    return-void
.end method
