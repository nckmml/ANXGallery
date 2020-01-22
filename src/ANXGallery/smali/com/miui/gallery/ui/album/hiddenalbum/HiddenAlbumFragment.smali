.class public Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;
.super Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;
.source "HiddenAlbumFragment.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V<",
        "Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;",
        "Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;",
        ">;"
    }
.end annotation


# instance fields
.field private isFirstAddEventHook:Z


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;-><init>()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->isFirstAddEventHook:Z

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->showConfirmDialog(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V

    return-void
.end method

.method private addEventHook(Ljava/util/List;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation

    if-eqz p1, :cond_0

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_0

    iget-boolean p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->isFirstAddEventHook:Z

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    new-instance v0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;

    const-class v1, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$2;-><init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;Ljava/lang/Class;)V

    invoke-virtual {p1, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addEventHook(Lcom/miui/epoxy/eventhook/EventHook;)V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->isFirstAddEventHook:Z

    :cond_0
    return-void
.end method

.method public static newInstance()Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;
    .locals 2

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    new-instance v1, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    invoke-direct {v1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;-><init>()V

    invoke-virtual {v1, v0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->setArguments(Landroid/os/Bundle;)V

    return-object v1
.end method

.method private showConfirmDialog(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V
    .locals 7

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v0

    new-instance v5, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$3;

    invoke-direct {v5, p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$3;-><init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V

    new-instance v6, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$4;

    invoke-direct {v6, p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$4;-><init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;)V

    const v1, 0x7f10071b

    const v2, 0x7f10071c

    const v3, 0x7f1005e8

    const v4, 0x7f10010a

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/DialogUtil;->showInfoDialog(Landroid/content/Context;IIIILandroid/content/DialogInterface$OnClickListener;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog;

    return-void
.end method


# virtual methods
.method protected getRecycleViewDecoration()Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06008a

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f060083

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    new-instance v2, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;

    invoke-direct {v2, p0, v1, v0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;-><init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;II)V

    return-object v2
.end method

.method protected initRecycleView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 4

    new-instance v0, Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v1

    const/4 v2, 0x0

    const/4 v3, 0x1

    invoke-direct {v0, v1, v3, v2}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getDefaultEmptyConfig()Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    move-result-object v0

    const v1, 0x7f1003cb

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->setTitle(I)Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->setEmptyPage(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06006f

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p0, v2, v2, v2, v0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->setContentPadding(IIII)V

    invoke-virtual {p1, v2}, Landroidx/recyclerview/widget/RecyclerView;->setClipToPadding(Z)V

    return-void
.end method

.method public onActivityCreated(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;->onActivityCreated(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;->initData()V

    return-void
.end method

.method public showHiddenListData(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->setDatas(Ljava/util/List;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->addEventHook(Ljava/util/List;)V

    return-void
.end method

.method public showUnHiddenAlbumIsFaild()V
    .locals 0

    return-void
.end method

.method public showUnHiddenAlbumIsSuccess(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->removeData(Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;->getAlbumName()Ljava/lang/String;

    move-result-object p1

    const/4 v2, 0x0

    aput-object p1, v1, v2

    const p1, 0x7f10071a

    invoke-virtual {p0, p1, v1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    return-void
.end method

.method public updateHiddenListData(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->updateDatas(Ljava/util/List;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->addEventHook(Ljava/util/List;)V

    return-void
.end method
