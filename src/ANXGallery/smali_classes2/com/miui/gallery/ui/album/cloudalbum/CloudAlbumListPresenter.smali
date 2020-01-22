.class public Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;
.super Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;
.source "CloudAlbumListPresenter.java"


# instance fields
.field private isFirstLoad:Z

.field private mDoChangeAlbumBackUp:Lcom/miui/gallery/app/base/BaseUseCase;

.field private mQueryCloudAlbumList:Lcom/miui/gallery/app/base/BaseUseCase;

.field private mQueryShareAlbumDetailInfo:Lcom/miui/gallery/app/base/BaseUseCase;


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;-><init>()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->isFirstLoad:Z

    invoke-static {}, Lcom/miui/gallery/model/repository/config/ModelManager;->getInstance()Lcom/miui/gallery/model/repository/config/ModelManager;

    move-result-object v0

    const-class v1, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/model/repository/config/ModelManager;->getModel(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    new-instance v1, Lcom/miui/gallery/ui/album/cloudalbum/usecase/QueryCloudAlbumList;

    invoke-direct {v1, v0}, Lcom/miui/gallery/ui/album/cloudalbum/usecase/QueryCloudAlbumList;-><init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mQueryCloudAlbumList:Lcom/miui/gallery/app/base/BaseUseCase;

    new-instance v1, Lcom/miui/gallery/ui/album/common/base/usecase/QueryShareAlbumDetailInfoList;

    invoke-direct {v1, v0}, Lcom/miui/gallery/ui/album/common/base/usecase/QueryShareAlbumDetailInfoList;-><init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mQueryShareAlbumDetailInfo:Lcom/miui/gallery/app/base/BaseUseCase;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->isFirstLoad:Z

    return p0
.end method

.method static synthetic access$002(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->isFirstLoad:Z

    return p1
.end method

.method private initShareAlbumDetailInfoIfNeed()V
    .locals 3

    invoke-static {}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getInstance()Lcom/miui/gallery/share/ShareAlbumCacheManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getShareAlbumList()Ljava/util/Collection;

    move-result-object v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mQueryShareAlbumDetailInfo:Lcom/miui/gallery/app/base/BaseUseCase;

    new-instance v1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$2;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$2;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;)V

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/app/base/BaseUseCase;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public converterTagBeanToCloudAlbumItemItemViewBean(Ljava/lang/Object;)Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;
    .locals 2

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return-object v0

    :cond_0
    instance-of v1, p1, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    if-nez v1, :cond_1

    return-object v0

    :cond_1
    check-cast p1, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    return-object p1
.end method

.method public doChangeAlbumUploadStatus(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V
    .locals 4

    if-nez p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;->showChangeAlbumUploadStatusFailed(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    return-void

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->getId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isShareAlbum(J)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;->showChangeAlbumCantBeShareAlbum(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    return-void

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mDoChangeAlbumBackUp:Lcom/miui/gallery/app/base/BaseUseCase;

    if-nez v0, :cond_2

    new-instance v0, Lcom/miui/gallery/ui/album/common/base/usecase/DoChangeAlbumBackupCase;

    invoke-static {}, Lcom/miui/gallery/model/repository/config/ModelManager;->getInstance()Lcom/miui/gallery/model/repository/config/ModelManager;

    move-result-object v1

    const-class v2, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/model/repository/config/ModelManager;->getModel(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-direct {v0, v1}, Lcom/miui/gallery/ui/album/common/base/usecase/DoChangeAlbumBackupCase;-><init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mDoChangeAlbumBackUp:Lcom/miui/gallery/app/base/BaseUseCase;

    :cond_2
    new-instance v0, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->getId()J

    move-result-wide v1

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->isBackup()Z

    move-result v3

    xor-int/lit8 v3, v3, 0x1

    invoke-direct {v0, v1, v2, v3}, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;-><init>(JZ)V

    iget-object v1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mDoChangeAlbumBackUp:Lcom/miui/gallery/app/base/BaseUseCase;

    new-instance v2, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;

    invoke-direct {v2, p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    invoke-virtual {v1, v2, v0}, Lcom/miui/gallery/app/base/BaseUseCase;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    return-void
.end method

.method public doChangeAlbumUploadStatus(Ljava/lang/Object;)V
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->converterTagBeanToCloudAlbumItemItemViewBean(Ljava/lang/Object;)Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->doChangeAlbumUploadStatus(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    return-void
.end method

.method public initData()V
    .locals 3

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->initShareAlbumDetailInfoIfNeed()V

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mQueryCloudAlbumList:Lcom/miui/gallery/app/base/BaseUseCase;

    new-instance v1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$1;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;)V

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/app/base/BaseUseCase;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    return-void
.end method

.method public isShareAlbum(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)Z
    .locals 2

    if-nez p1, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->getId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isShareAlbum(J)Z

    move-result p1

    return p1
.end method

.method public isShareAlbum(Ljava/lang/Object;)Z
    .locals 1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->converterTagBeanToCloudAlbumItemItemViewBean(Ljava/lang/Object;)Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    check-cast p1, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->isShareAlbum(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)Z

    move-result p1

    return p1
.end method

.method protected onDestory()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;->onDestory()V

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mQueryCloudAlbumList:Lcom/miui/gallery/app/base/BaseUseCase;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/app/base/BaseUseCase;->dispose()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->mDoChangeAlbumBackUp:Lcom/miui/gallery/app/base/BaseUseCase;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/app/base/BaseUseCase;->dispose()V

    :cond_1
    return-void
.end method
