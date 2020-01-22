.class public Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;
.super Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;
.source "CloudAlbumListFragment.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V<",
        "Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;",
        "Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;",
        ">;"
    }
.end annotation


# instance fields
.field private isFirstAddEventHook:Z


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;-><init>()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->isFirstAddEventHook:Z

    return-void
.end method

.method private addEventHook(Ljava/util/List;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation

    if-eqz p1, :cond_0

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_0

    iget-boolean p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->isFirstAddEventHook:Z

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    new-instance v0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;

    const-class v1, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;Ljava/lang/Class;)V

    invoke-virtual {p1, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addEventHook(Lcom/miui/epoxy/eventhook/EventHook;)V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->isFirstAddEventHook:Z

    :cond_0
    return-void
.end method

.method public static newInstance()Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;
    .locals 2

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    new-instance v1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;

    invoke-direct {v1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;-><init>()V

    invoke-virtual {v1, v0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->setArguments(Landroid/os/Bundle;)V

    return-object v1
.end method


# virtual methods
.method protected doChangeAutoUpload(Ljava/lang/Object;Z)V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/cloud/AccountCache;->getAccount()Landroid/accounts/Account;

    move-result-object v0

    if-eqz v0, :cond_1

    if-eqz p2, :cond_0

    const-string p2, "com.miui.gallery.cloud.provider"

    invoke-static {v0, p2}, Landroid/content/ContentResolver;->getSyncAutomatically(Landroid/accounts/Account;Ljava/lang/String;)Z

    move-result p2

    if-nez p2, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    const/4 v0, 0x1

    invoke-static {p2, v0}, Lcom/miui/gallery/util/SyncUtil;->setSyncAutomatically(Landroid/content/Context;Z)Z

    move-result p2

    if-nez p2, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;->converterTagBeanToCloudAlbumItemItemViewBean(Ljava/lang/Object;)Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->showChangeAlbumUploadStatusFailed(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;->doChangeAlbumUploadStatus(Ljava/lang/Object;)V

    :cond_1
    return-void
.end method

.method protected getRecycleViewDecoration()Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f060083

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f06008a

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    sub-int/2addr v0, v1

    new-instance v1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$1;

    invoke-direct {v1, p0, v0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$1;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;I)V

    return-object v1
.end method

.method protected initRecycleView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 4

    new-instance v0, Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v1

    const/4 v2, 0x0

    const/4 v3, 0x1

    invoke-direct {v0, v1, v3, v2}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getDefaultEmptyConfig()Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    move-result-object v0

    const v1, 0x7f1003ca

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->setTitle(I)Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->setEmptyPage(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06006f

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p0, v2, v2, v2, v0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->setContentPadding(IIII)V

    invoke-virtual {p1, v2}, Landroidx/recyclerview/widget/RecyclerView;->setClipToPadding(Z)V

    invoke-virtual {p1, v2}, Landroidx/recyclerview/widget/RecyclerView;->setMotionEventSplittingEnabled(Z)V

    return-void
.end method

.method public onActivityCreated(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;->onActivityCreated(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$P;->initData()V

    return-void
.end method

.method public showChangeAlbumCantBeShareAlbum(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    const v1, 0x7f1007b4

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->notifyDataChanged(Ljava/lang/Object;)V

    return-void
.end method

.method public showChangeAlbumUploadStatusFailed(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->notifyDataChanged(Ljava/lang/Object;)V

    return-void
.end method

.method public showChangeAlbumUploadStatusSuccess(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->isBackup()Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x7f1000a6

    goto :goto_0

    :cond_0
    const p1, 0x7f1000a5

    :goto_0
    invoke-static {v0, p1}, Lcom/miui/gallery/util/ToastUtils;->makeTextLong(Landroid/content/Context;I)V

    return-void
.end method

.method public showCloudList(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->setDatas(Ljava/util/List;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->addEventHook(Ljava/util/List;)V

    return-void
.end method

.method public updateCloudList(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->updateDatas(Ljava/util/List;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->addEventHook(Ljava/util/List;)V

    return-void
.end method
