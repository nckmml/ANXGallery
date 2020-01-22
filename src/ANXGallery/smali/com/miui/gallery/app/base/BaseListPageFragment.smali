.class public abstract Lcom/miui/gallery/app/base/BaseListPageFragment;
.super Lcom/miui/gallery/app/base/BaseFragment;
.source "BaseListPageFragment.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        "P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/app/base/BaseFragment<",
        "TP;>;"
    }
.end annotation


# instance fields
.field protected mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/epoxy/BaseEpoxyAdapter<",
            "TT;>;"
        }
    .end annotation
.end field

.field protected mCurrentEmptyPageModel:Lcom/miui/epoxy/EpoxyModel;

.field protected mEmptyConfig:Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

.field protected mLoadingView:Landroid/view/View;

.field protected mRecyleView:Landroidx/recyclerview/widget/RecyclerView;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseFragment;-><init>()V

    return-void
.end method

.method private initSelf(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mRecyleView:Landroidx/recyclerview/widget/RecyclerView;

    iget-object v1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseListPageFragment;->getRecycleViewDecoration()Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/app/base/BaseListPageFragment;->addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public addDatas(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "TT;>;)V"
        }
    .end annotation

    if-nez p1, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/app/base/BaseListPageFragment;->initSelf(Z)V

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->checkEmptyView()V

    return-void

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseListPageFragment;->goneProgress()V

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addDataList(Ljava/util/Collection;)V

    return-void
.end method

.method protected addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mRecyleView:Landroidx/recyclerview/widget/RecyclerView;

    if-eqz v0, :cond_1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {v0, p1}, Landroidx/recyclerview/widget/RecyclerView;->addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    :cond_1
    :goto_0
    return-void
.end method

.method protected findRecycleView()Landroidx/recyclerview/widget/RecyclerView;
    .locals 1

    const v0, 0x7f09029f

    invoke-virtual {p0, v0}, Lcom/miui/gallery/app/base/BaseListPageFragment;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/RecyclerView;

    return-object v0
.end method

.method protected getDefaultEmptyConfig()Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mEmptyConfig:Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    return-object v0
.end method

.method public getLayoutId()I
    .locals 1

    const v0, 0x7f0b0169

    return v0
.end method

.method protected getRecycleViewDecoration()Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;
    .locals 1

    const/4 v0, 0x0

    return-object v0
.end method

.method protected goneProgress()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mLoadingView:Landroid/view/View;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mLoadingView:Landroid/view/View;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    :cond_0
    return-void
.end method

.method protected abstract initRecycleView(Landroidx/recyclerview/widget/RecyclerView;)V
.end method

.method public initView(Landroid/view/View;Landroid/os/Bundle;Landroid/view/View;)V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseListPageFragment;->findRecycleView()Landroidx/recyclerview/widget/RecyclerView;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mRecyleView:Landroidx/recyclerview/widget/RecyclerView;

    iget-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mRecyleView:Landroidx/recyclerview/widget/RecyclerView;

    if-eqz p1, :cond_1

    new-instance p1, Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;

    invoke-direct {p1}, Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    new-instance p1, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    invoke-direct {p1}, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;-><init>()V

    const p2, 0x7f0703f2

    invoke-virtual {p1, p2}, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->setBackground(I)Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    move-result-object p1

    const p2, 0x7f070147

    invoke-virtual {p1, p2}, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->setIcon(I)Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->disableActionButton()Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mEmptyConfig:Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    iget-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mRecyleView:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/app/base/BaseListPageFragment;->initRecycleView(Landroidx/recyclerview/widget/RecyclerView;)V

    iget-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mRecyleView:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutManager()Landroidx/recyclerview/widget/RecyclerView$LayoutManager;

    move-result-object p1

    if-eqz p1, :cond_0

    const-class p2, Landroidx/recyclerview/widget/GridLayoutManager;

    invoke-virtual {p2, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_0

    check-cast p1, Landroidx/recyclerview/widget/GridLayoutManager;

    iget-object p2, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {p1}, Landroidx/recyclerview/widget/GridLayoutManager;->getSpanCount()I

    move-result p3

    invoke-virtual {p2, p3}, Lcom/miui/epoxy/BaseEpoxyAdapter;->getSpanSizeLookup(I)Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroidx/recyclerview/widget/GridLayoutManager;->setSpanSizeLookup(Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;)V

    :cond_0
    return-void

    :cond_1
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string p2, "can\'t find recycleView from layout"

    invoke-direct {p1, p2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method protected setContentPadding(IIII)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mRecyleView:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {v0, p1, p2, p3, p4}, Landroidx/recyclerview/widget/RecyclerView;->setPadding(IIII)V

    return-void
.end method

.method public setDatas(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "TT;>;)V"
        }
    .end annotation

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/miui/gallery/app/base/BaseListPageFragment;->initSelf(Z)V

    if-eqz p1, :cond_1

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->clearData()V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/app/base/BaseListPageFragment;->addDatas(Ljava/util/List;)V

    return-void

    :cond_1
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->checkEmptyView()V

    return-void
.end method

.method public setEmptyPage(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    :cond_0
    new-instance v0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;

    invoke-direct {v0, p1}, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;-><init>(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)V

    iput-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mCurrentEmptyPageModel:Lcom/miui/epoxy/EpoxyModel;

    iget-object p1, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mCurrentEmptyPageModel:Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {p1, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->setEmptyViewModel(Lcom/miui/epoxy/EpoxyModel;)V

    return-void
.end method

.method public updateDatas(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "TT;>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseListPageFragment;->mAdapter:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->updateDataList(Ljava/util/Collection;)V

    :cond_0
    return-void
.end method
