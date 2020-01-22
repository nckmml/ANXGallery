.class public Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;
.super Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;
.source "HiddenAlbumPresenter.java"


# instance fields
.field private isFirstLoad:Z

.field private mSelectHiddenList:Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;

.field private mUnHideAlbum:Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;-><init>()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->isFirstLoad:Z

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->isFirstLoad:Z

    return p0
.end method

.method static synthetic access$002(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->isFirstLoad:Z

    return p1
.end method


# virtual methods
.method public initData()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mSelectHiddenList:Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;

    invoke-static {}, Lcom/miui/gallery/model/repository/config/ModelManager;->getInstance()Lcom/miui/gallery/model/repository/config/ModelManager;

    move-result-object v1

    const-class v2, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/model/repository/config/ModelManager;->getModel(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-direct {v0, v1}, Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;-><init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mSelectHiddenList:Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mSelectHiddenList:Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;

    new-instance v1, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$1;-><init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;)V

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    return-void
.end method

.method protected onDestory()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;->onDestory()V

    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mUnHideAlbum:Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;->dispose()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mSelectHiddenList:Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/ui/album/hiddenalbum/usecase/QueryHiddenList;->dispose()V

    :cond_1
    return-void
.end method

.method public unAlbumHide(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V
    .locals 4

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getId()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-gtz v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mUnHideAlbum:Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;

    if-nez v0, :cond_1

    new-instance v0, Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;

    invoke-static {}, Lcom/miui/gallery/model/repository/config/ModelManager;->getInstance()Lcom/miui/gallery/model/repository/config/ModelManager;

    move-result-object v1

    const-class v2, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/model/repository/config/ModelManager;->getModel(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-direct {v0, v1}, Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;-><init>(Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mUnHideAlbum:Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->mUnHideAlbum:Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;

    new-instance v1, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;-><init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getId()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/ui/album/common/base/usecase/DoUnHideAlbumCase;->execute(Lio/reactivex/subscribers/DisposableSubscriber;Ljava/lang/Object;)V

    :cond_2
    :goto_0
    return-void
.end method
