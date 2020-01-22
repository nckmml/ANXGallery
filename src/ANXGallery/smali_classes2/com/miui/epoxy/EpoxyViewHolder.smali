.class public Lcom/miui/epoxy/EpoxyViewHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "EpoxyViewHolder.java"


# instance fields
.field model:Lcom/miui/epoxy/EpoxyModel;

.field supportPropertyStage:Z


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/epoxy/EpoxyViewHolder;->supportPropertyStage:Z

    return-void
.end method


# virtual methods
.method bind(Lcom/miui/epoxy/EpoxyModel;Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel;",
            "Ljava/util/List<",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    iget-boolean v0, p0, Lcom/miui/epoxy/EpoxyViewHolder;->supportPropertyStage:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Lcom/miui/epoxy/EpoxyModel;->shouldStageProperty(Z)V

    :cond_0
    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyModel;->isPropertiesChanged()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p1, p0}, Lcom/miui/epoxy/EpoxyModel;->bindPartialData(Lcom/miui/epoxy/EpoxyViewHolder;)V

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyModel;->doPropertiesCleanUp()V

    goto :goto_0

    :cond_1
    if-eqz p2, :cond_2

    invoke-interface {p2}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_2

    invoke-virtual {p1, p0, p2}, Lcom/miui/epoxy/EpoxyModel;->bindData(Lcom/miui/epoxy/EpoxyViewHolder;Ljava/util/List;)V

    goto :goto_0

    :cond_2
    invoke-virtual {p1, p0}, Lcom/miui/epoxy/EpoxyModel;->bindData(Lcom/miui/epoxy/EpoxyViewHolder;)V

    :goto_0
    iput-object p1, p0, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    return-void
.end method

.method shouldSaveViewState()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyModel;->shouldSaveViewState()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method unbind()V
    .locals 2

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-boolean v1, p0, Lcom/miui/epoxy/EpoxyViewHolder;->supportPropertyStage:Z

    if-eqz v1, :cond_1

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/epoxy/EpoxyModel;->shouldStageProperty(Z)V

    :cond_1
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyModel;->isPropertiesChanged()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyModel;->doPropertiesCleanUp()V

    :cond_2
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {v0, p0}, Lcom/miui/epoxy/EpoxyModel;->unbind(Lcom/miui/epoxy/EpoxyViewHolder;)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    return-void
.end method
