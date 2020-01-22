.class public Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;
.super Lcom/miui/epoxy/BaseEpoxyAdapter;
.source "SimpleEpoxyAdapter.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lcom/miui/epoxy/BaseEpoxyAdapter<",
        "TT;>;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public notifyDataChanged(Ljava/lang/Object;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    if-nez p1, :cond_0

    return-void

    :cond_0
    const/4 v0, -0x1

    iget-object v1, p0, Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {v1, p1}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result v1

    if-eq v0, v1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;->dataList:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;->getModel(I)Lcom/miui/epoxy/EpoxyModel;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;->notifyModelChanged(Lcom/miui/epoxy/EpoxyModel;)V

    :cond_1
    return-void
.end method

.method public removeData(Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    invoke-super {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter;->removeData(Ljava/lang/Object;)V

    invoke-virtual {p0}, Lcom/miui/gallery/adapter/SimpleEpoxyAdapter;->checkEmptyView()V

    return-void
.end method

.method protected transData(Ljava/lang/Object;)Ljava/util/Collection;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;"
        }
    .end annotation

    invoke-static {p1}, Lcom/miui/gallery/adapter/itemmodel/trans/ItemModelTransManager;->transDataToModel(Ljava/lang/Object;)Lcom/miui/epoxy/EpoxyModel;

    move-result-object p1

    invoke-static {p1}, Ljava/util/Collections;->singletonList(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    return-object p1
.end method
