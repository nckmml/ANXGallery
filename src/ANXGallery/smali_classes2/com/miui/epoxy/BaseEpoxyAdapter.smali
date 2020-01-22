.class public abstract Lcom/miui/epoxy/BaseEpoxyAdapter;
.super Lcom/miui/epoxy/EpoxyAdapter;
.source "BaseEpoxyAdapter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;,
        Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lcom/miui/epoxy/EpoxyAdapter;"
    }
.end annotation


# instance fields
.field protected final dataList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "TT;>;"
        }
    .end annotation
.end field

.field private emptyViewModel:Lcom/miui/epoxy/EpoxyModel;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;"
        }
    .end annotation
.end field

.field private final footers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/epoxy/BaseEpoxyAdapter<",
            "TT;>.OrderedMap<",
            "Ljava/lang/Long;",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;"
        }
    .end annotation
.end field

.field private hasMore:Z

.field private final headers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/epoxy/BaseEpoxyAdapter<",
            "TT;>.OrderedMap<",
            "Ljava/lang/Long;",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;"
        }
    .end annotation
.end field

.field private loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/epoxy/EpoxyLoadMoreModel<",
            "*>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/epoxy/EpoxyAdapter;-><init>()V

    new-instance v0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;-><init>(Lcom/miui/epoxy/BaseEpoxyAdapter;Lcom/miui/epoxy/BaseEpoxyAdapter$1;)V

    iput-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->headers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    new-instance v0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-direct {v0, p0, v1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;-><init>(Lcom/miui/epoxy/BaseEpoxyAdapter;Lcom/miui/epoxy/BaseEpoxyAdapter$1;)V

    iput-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->footers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    new-instance v0, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;

    invoke-direct {v0, v1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;-><init>(Lcom/miui/epoxy/BaseEpoxyAdapter$1;)V

    iput-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    iput-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->emptyViewModel:Lcom/miui/epoxy/EpoxyModel;

    return-void
.end method

.method private addDataModels(Ljava/util/Collection;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;)V"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->getLoadMoreOrFirstFooter()Lcom/miui/epoxy/EpoxyModel;

    move-result-object v0

    if-nez v0, :cond_0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addModels(Ljava/util/Collection;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->insertModelsBefore(Ljava/util/Collection;Lcom/miui/epoxy/EpoxyModel;)V

    :goto_0
    return-void
.end method

.method private batchDoPropertiesStage(Ljava/util/Collection;Ljava/util/Collection;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "TT;>;",
            "Ljava/util/Collection<",
            "TT;>;)V"
        }
    .end annotation

    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_0

    invoke-interface {p2}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_0

    nop

    :cond_0
    return-void
.end method

.method private getLoadMoreOrFirstFooter()Lcom/miui/epoxy/EpoxyModel;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;"
        }
    .end annotation

    iget-boolean v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->footers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-virtual {v0}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->getFirstOrNull()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/epoxy/EpoxyModel;

    :goto_0
    return-object v0
.end method


# virtual methods
.method public final addDataList(Ljava/util/Collection;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "TT;>;)V"
        }
    .end annotation

    iget-boolean v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    invoke-virtual {p0, p1, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addDataList(Ljava/util/Collection;Z)V

    return-void
.end method

.method public final addDataList(Ljava/util/Collection;Z)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "TT;>;Z)V"
        }
    .end annotation

    invoke-virtual {p0, p2}, Lcom/miui/epoxy/BaseEpoxyAdapter;->setHasMore(Z)V

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->transDataList(Ljava/util/Collection;)Ljava/util/Collection;

    move-result-object p2

    invoke-direct {p0, p2}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addDataModels(Ljava/util/Collection;)V

    iget-object p2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {p2, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    invoke-virtual {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->checkEmptyView()V

    return-void
.end method

.method public checkEmptyView()V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->isDataListEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->emptyViewModel:Lcom/miui/epoxy/EpoxyModel;

    if-eqz v0, :cond_1

    invoke-virtual {p0, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->containsModel(Lcom/miui/epoxy/EpoxyModel;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->headers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-virtual {v0}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->size()I

    move-result v0

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->emptyViewModel:Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {p0, v0, v1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addModel(ILcom/miui/epoxy/EpoxyModel;)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->emptyViewModel:Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {p0, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->removeModel(Lcom/miui/epoxy/EpoxyModel;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public clearData()V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    invoke-virtual {p0, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->clearData(Z)V

    return-void
.end method

.method public clearData(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;

    move-result-object v0

    invoke-virtual {p0, v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->updateDataList(Ljava/util/Collection;Z)V

    return-void
.end method

.method protected isDataListEmpty()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    return v0
.end method

.method public abstract notifyDataChanged(Ljava/lang/Object;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation
.end method

.method public removeData(Ljava/lang/Object;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->replaceAllDataModels()V

    :cond_0
    return-void
.end method

.method protected replaceAllDataModels()V
    .locals 1

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->replaceAllDataModels(Z)V

    return-void
.end method

.method protected replaceAllDataModels(Z)V
    .locals 2

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->headers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-virtual {v1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    invoke-virtual {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->isDataListEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->emptyViewModel:Lcom/miui/epoxy/EpoxyModel;

    if-eqz v1, :cond_0

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-virtual {p0, v1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->transDataList(Ljava/util/Collection;)Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    iget-boolean v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_1
    :goto_0
    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->footers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-virtual {v1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    invoke-virtual {p0, v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->replaceAllModels(Ljava/util/List;Z)V

    return-void
.end method

.method public final setEmptyViewModel(Lcom/miui/epoxy/EpoxyModel;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->emptyViewModel:Lcom/miui/epoxy/EpoxyModel;

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    if-eqz v0, :cond_1

    invoke-virtual {p0, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->removeModel(Lcom/miui/epoxy/EpoxyModel;)V

    :cond_1
    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->emptyViewModel:Lcom/miui/epoxy/EpoxyModel;

    return-void
.end method

.method public final setHasMore(Z)V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    iput-boolean p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    const/4 v0, 0x1

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->footers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-virtual {p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->size()I

    move-result p1

    if-nez p1, :cond_1

    new-array p1, v0, [Lcom/miui/epoxy/EpoxyModel;

    const/4 v0, 0x0

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    aput-object v1, p1, v0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->addModels([Lcom/miui/epoxy/EpoxyModel;)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->footers:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-virtual {v0}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->getFirstOrNull()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {p0, p1, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->insertModelBefore(Lcom/miui/epoxy/EpoxyModel;Lcom/miui/epoxy/EpoxyModel;)V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    invoke-virtual {p1, v0}, Lcom/miui/epoxy/EpoxyLoadMoreModel;->setState(I)V

    iget-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->removeModel(Lcom/miui/epoxy/EpoxyModel;)V

    :goto_0
    return-void
.end method

.method protected abstract transData(Ljava/lang/Object;)Ljava/util/Collection;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;"
        }
    .end annotation
.end method

.method protected transDataList(Ljava/util/Collection;)Ljava/util/Collection;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "TT;>;)",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {p0, v1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->transData(Ljava/lang/Object;)Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    goto :goto_0

    :cond_0
    return-object v0
.end method

.method public final updateDataList(Ljava/util/Collection;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "TT;>;)V"
        }
    .end annotation

    iget-boolean v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    invoke-virtual {p0, p1, v0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->updateDataList(Ljava/util/Collection;Z)V

    return-void
.end method

.method public final updateDataList(Ljava/util/Collection;Z)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "TT;>;Z)V"
        }
    .end annotation

    iput-boolean p2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->hasMore:Z

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->loadMoreModel:Lcom/miui/epoxy/EpoxyLoadMoreModel;

    const/4 v0, 0x1

    invoke-virtual {p2, v0}, Lcom/miui/epoxy/EpoxyLoadMoreModel;->setState(I)V

    :cond_0
    iget-boolean p2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->supportPropertyStage:Z

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-direct {p0, p2, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->batchDoPropertiesStage(Ljava/util/Collection;Ljava/util/Collection;)V

    :cond_1
    iget-object p2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {p2}, Ljava/util/List;->clear()V

    iget-object p2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {p2, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    invoke-virtual {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter;->replaceAllDataModels()V

    return-void
.end method
