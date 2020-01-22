.class public Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;
.super Ljava/lang/Object;
.source "PhotoLocalDataSource.java"

# interfaces
.implements Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;[J)Z
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;->checkIsFailed([J)Z

    move-result p0

    return p0
.end method

.method private checkIsFailed([J)Z
    .locals 0

    if-eqz p1, :cond_1

    array-length p1, p1

    if-gtz p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    return p1
.end method


# virtual methods
.method public editPhotoDateTimeDetailInfo(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;
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

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;-><init>(Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)V

    invoke-static {v0}, Lio/reactivex/Flowable;->fromCallable(Ljava/util/concurrent/Callable;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public getPhotoDetailInfo(Lcom/miui/gallery/model/BaseDataItem;)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/model/BaseDataItem;",
            ")",
            "Lio/reactivex/Flowable<",
            "Lcom/miui/gallery/model/PhotoDetailInfo;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$1;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$1;-><init>(Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;Lcom/miui/gallery/model/BaseDataItem;)V

    invoke-static {v0}, Lio/reactivex/Flowable;->fromCallable(Ljava/util/concurrent/Callable;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method

.method public queryAlbumNameByAlbumId(Ljava/lang/Long;)Lio/reactivex/Flowable;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Long;",
            ")",
            "Lio/reactivex/Flowable<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$3;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$3;-><init>(Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;Ljava/lang/Long;)V

    invoke-static {v0}, Lio/reactivex/Flowable;->fromCallable(Ljava/util/concurrent/Callable;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1
.end method
