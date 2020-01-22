.class public Lcom/miui/epoxy/EpoxyAdapter;
.super Landroidx/recyclerview/widget/RecyclerView$Adapter;
.source "EpoxyAdapter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;,
        Lcom/miui/epoxy/EpoxyAdapter$ModelList;,
        Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroidx/recyclerview/widget/RecyclerView$Adapter<",
        "Lcom/miui/epoxy/EpoxyViewHolder;",
        ">;"
    }
.end annotation


# static fields
.field private static final LOG_TAG:Ljava/lang/String; = "EpoxyAdapter"


# instance fields
.field private final boundViewHolders:Landroidx/collection/LongSparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/collection/LongSparseArray<",
            "Lcom/miui/epoxy/EpoxyViewHolder;",
            ">;"
        }
    .end annotation
.end field

.field private final eventHookHelper:Lcom/miui/epoxy/eventhook/EventHookHelper;

.field private isAttached:Z

.field protected final models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

.field private spanCount:I

.field private final spanSizeLookup:Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;

.field protected supportPropertyStage:Z

.field private viewHolderState:Lcom/miui/epoxy/ViewHolderState;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$Adapter;-><init>()V

    new-instance v0, Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-direct {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;-><init>()V

    iput-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    new-instance v0, Lcom/miui/epoxy/eventhook/EventHookHelper;

    invoke-direct {v0, p0}, Lcom/miui/epoxy/eventhook/EventHookHelper;-><init>(Lcom/miui/epoxy/EpoxyAdapter;)V

    iput-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->eventHookHelper:Lcom/miui/epoxy/eventhook/EventHookHelper;

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->isAttached:Z

    iput-boolean v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->supportPropertyStage:Z

    new-instance v0, Landroidx/collection/LongSparseArray;

    invoke-direct {v0}, Landroidx/collection/LongSparseArray;-><init>()V

    iput-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->boundViewHolders:Landroidx/collection/LongSparseArray;

    new-instance v0, Lcom/miui/epoxy/ViewHolderState;

    invoke-direct {v0}, Lcom/miui/epoxy/ViewHolderState;-><init>()V

    iput-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->viewHolderState:Lcom/miui/epoxy/ViewHolderState;

    new-instance v0, Lcom/miui/epoxy/EpoxyAdapter$1;

    invoke-direct {v0, p0}, Lcom/miui/epoxy/EpoxyAdapter$1;-><init>(Lcom/miui/epoxy/EpoxyAdapter;)V

    iput-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->spanSizeLookup:Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;

    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->spanCount:I

    invoke-virtual {p0, v0}, Lcom/miui/epoxy/EpoxyAdapter;->setHasStableIds(Z)V

    iget-object v1, p0, Lcom/miui/epoxy/EpoxyAdapter;->spanSizeLookup:Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;

    invoke-virtual {v1, v0}, Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;->setSpanIndexCacheEnabled(Z)V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/epoxy/EpoxyAdapter;)I
    .locals 0

    iget p0, p0, Lcom/miui/epoxy/EpoxyAdapter;->spanCount:I

    return p0
.end method


# virtual methods
.method public addEventHook(Lcom/miui/epoxy/eventhook/EventHook;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<VH:",
            "Lcom/miui/epoxy/EpoxyViewHolder;",
            ">(",
            "Lcom/miui/epoxy/eventhook/EventHook<",
            "TVH;>;)V"
        }
    .end annotation

    iget-boolean v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->isAttached:Z

    if-eqz v0, :cond_0

    sget-object v0, Lcom/miui/epoxy/EpoxyAdapter;->LOG_TAG:Ljava/lang/String;

    const-string v1, "addEventHook is called after adapter attached"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->eventHookHelper:Lcom/miui/epoxy/eventhook/EventHookHelper;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/eventhook/EventHookHelper;->add(Lcom/miui/epoxy/eventhook/EventHook;)V

    return-void
.end method

.method public addModel(ILcom/miui/epoxy/EpoxyModel;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->size()I

    move-result v0

    if-gt p1, v0, :cond_1

    if-gez p1, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p1, p2}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->add(ILcom/miui/epoxy/EpoxyModel;)V

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->notifyItemInserted(I)V

    :cond_1
    :goto_0
    return-void
.end method

.method public addModels(Ljava/util/Collection;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->size()I

    move-result v0

    iget-object v1, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v1, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->addAll(Ljava/util/Collection;)Z

    invoke-interface {p1}, Ljava/util/Collection;->size()I

    move-result p1

    invoke-virtual {p0, v0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->notifyItemRangeInserted(II)V

    return-void
.end method

.method public varargs addModels([Lcom/miui/epoxy/EpoxyModel;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    invoke-static {p1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->addModels(Ljava/util/Collection;)V

    return-void
.end method

.method protected calculateDiff(Ljava/util/List;)Landroidx/recyclerview/widget/DiffUtil$DiffResult;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;)",
            "Landroidx/recyclerview/widget/DiffUtil$DiffResult;"
        }
    .end annotation

    new-instance v0, Lcom/miui/epoxy/EpoxyAdapter$2;

    invoke-direct {v0, p0, p1}, Lcom/miui/epoxy/EpoxyAdapter$2;-><init>(Lcom/miui/epoxy/EpoxyAdapter;Ljava/util/List;)V

    invoke-static {v0}, Landroidx/recyclerview/widget/DiffUtil;->calculateDiff(Landroidx/recyclerview/widget/DiffUtil$Callback;)Landroidx/recyclerview/widget/DiffUtil$DiffResult;

    move-result-object p1

    return-object p1
.end method

.method public containsModel(Lcom/miui/epoxy/EpoxyModel;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)Z"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->indexOf(Ljava/lang/Object;)I

    move-result p1

    if-ltz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method public getItemCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->size()I

    move-result v0

    return v0
.end method

.method public getItemId(I)J
    .locals 2

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->getModel(I)Lcom/miui/epoxy/EpoxyModel;

    move-result-object p1

    if-nez p1, :cond_0

    const-wide/16 v0, -0x1

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyModel;->id()J

    move-result-wide v0

    :goto_0
    return-wide v0
.end method

.method public getItemViewType(I)I
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->getModel(I)Lcom/miui/epoxy/EpoxyModel;

    move-result-object p1

    if-nez p1, :cond_0

    const/4 p1, -0x1

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyModel;->getViewType()I

    move-result p1

    :goto_0
    return p1
.end method

.method public getModel(I)Lcom/miui/epoxy/EpoxyModel;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;"
        }
    .end annotation

    if-ltz p1, :cond_0

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/epoxy/EpoxyModel;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return-object p1
.end method

.method public getSpanSizeLookup(I)Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->setSpanCount(I)V

    iget-object p1, p0, Lcom/miui/epoxy/EpoxyAdapter;->spanSizeLookup:Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;

    return-object p1
.end method

.method public insertModelBefore(Lcom/miui/epoxy/EpoxyModel;Lcom/miui/epoxy/EpoxyModel;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p2}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->indexOf(Ljava/lang/Object;)I

    move-result p2

    const/4 v0, -0x1

    if-ne p2, v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p2, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->add(ILcom/miui/epoxy/EpoxyModel;)V

    invoke-virtual {p0, p2}, Lcom/miui/epoxy/EpoxyAdapter;->notifyItemInserted(I)V

    return-void
.end method

.method public insertModelsBefore(Ljava/util/Collection;Lcom/miui/epoxy/EpoxyModel;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p2}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->indexOf(Ljava/lang/Object;)I

    move-result p2

    const/4 v0, -0x1

    if-ne p2, v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p2, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->addAll(ILjava/util/Collection;)Z

    invoke-interface {p1}, Ljava/util/Collection;->size()I

    move-result p1

    invoke-virtual {p0, p2, p1}, Lcom/miui/epoxy/EpoxyAdapter;->notifyItemRangeInserted(II)V

    return-void
.end method

.method public notifyModelChanged(Lcom/miui/epoxy/EpoxyModel;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lcom/miui/epoxy/EpoxyAdapter;->notifyModelChanged(Lcom/miui/epoxy/EpoxyModel;Ljava/lang/Object;)V

    return-void
.end method

.method public notifyModelChanged(Lcom/miui/epoxy/EpoxyModel;Ljava/lang/Object;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;",
            "Ljava/lang/Object;",
            ")V"
        }
    .end annotation

    if-nez p1, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->indexOf(Ljava/lang/Object;)I

    move-result p1

    const/4 v0, -0x1

    if-eq p1, v0, :cond_1

    invoke-virtual {p0, p1, p2}, Lcom/miui/epoxy/EpoxyAdapter;->notifyItemChanged(ILjava/lang/Object;)V

    :cond_1
    return-void
.end method

.method public onAttachedToRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 0

    invoke-super {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->onAttachedToRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/epoxy/EpoxyAdapter;->isAttached:Z

    return-void
.end method

.method public bridge synthetic onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/EpoxyViewHolder;

    invoke-virtual {p0, p1, p2}, Lcom/miui/epoxy/EpoxyAdapter;->onBindViewHolder(Lcom/miui/epoxy/EpoxyViewHolder;I)V

    return-void
.end method

.method public bridge synthetic onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/util/List;)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/EpoxyViewHolder;

    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/epoxy/EpoxyAdapter;->onBindViewHolder(Lcom/miui/epoxy/EpoxyViewHolder;ILjava/util/List;)V

    return-void
.end method

.method public onBindViewHolder(Lcom/miui/epoxy/EpoxyViewHolder;I)V
    .locals 1

    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;

    move-result-object v0

    invoke-virtual {p0, p1, p2, v0}, Lcom/miui/epoxy/EpoxyAdapter;->onBindViewHolder(Lcom/miui/epoxy/EpoxyViewHolder;ILjava/util/List;)V

    return-void
.end method

.method public onBindViewHolder(Lcom/miui/epoxy/EpoxyViewHolder;ILjava/util/List;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyViewHolder;",
            "I",
            "Ljava/util/List<",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0, p2}, Lcom/miui/epoxy/EpoxyAdapter;->getModel(I)Lcom/miui/epoxy/EpoxyModel;

    move-result-object p2

    if-eqz p1, :cond_2

    if-nez p2, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->boundViewHolders:Landroidx/collection/LongSparseArray;

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->getItemId()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Landroidx/collection/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->viewHolderState:Lcom/miui/epoxy/ViewHolderState;

    iget-object v1, p0, Lcom/miui/epoxy/EpoxyAdapter;->boundViewHolders:Landroidx/collection/LongSparseArray;

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->getItemId()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Landroidx/collection/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/epoxy/EpoxyViewHolder;

    invoke-virtual {v0, v1}, Lcom/miui/epoxy/ViewHolderState;->save(Lcom/miui/epoxy/EpoxyViewHolder;)V

    :cond_1
    invoke-virtual {p1, p2, p3}, Lcom/miui/epoxy/EpoxyViewHolder;->bind(Lcom/miui/epoxy/EpoxyModel;Ljava/util/List;)V

    iget-object p2, p0, Lcom/miui/epoxy/EpoxyAdapter;->viewHolderState:Lcom/miui/epoxy/ViewHolderState;

    invoke-virtual {p2, p1}, Lcom/miui/epoxy/ViewHolderState;->restore(Lcom/miui/epoxy/EpoxyViewHolder;)V

    iget-object p2, p0, Lcom/miui/epoxy/EpoxyAdapter;->boundViewHolders:Landroidx/collection/LongSparseArray;

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->getItemId()J

    move-result-wide v0

    invoke-virtual {p2, v0, v1, p1}, Landroidx/collection/LongSparseArray;->put(JLjava/lang/Object;)V

    :cond_2
    :goto_0
    return-void
.end method

.method public bridge synthetic onCreateViewHolder(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/epoxy/EpoxyAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/epoxy/EpoxyViewHolder;

    move-result-object p1

    return-object p1
.end method

.method public onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/epoxy/EpoxyViewHolder;
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-static {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->access$100(Lcom/miui/epoxy/EpoxyAdapter$ModelList;)Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    move-result-object v0

    invoke-virtual {v0, p2, p1}, Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;->create(ILandroid/view/ViewGroup;)Lcom/miui/epoxy/EpoxyViewHolder;

    move-result-object p1

    iget-boolean p2, p0, Lcom/miui/epoxy/EpoxyAdapter;->supportPropertyStage:Z

    iput-boolean p2, p1, Lcom/miui/epoxy/EpoxyViewHolder;->supportPropertyStage:Z

    iget-object p2, p0, Lcom/miui/epoxy/EpoxyAdapter;->eventHookHelper:Lcom/miui/epoxy/eventhook/EventHookHelper;

    invoke-virtual {p2, p1}, Lcom/miui/epoxy/eventhook/EventHookHelper;->bind(Lcom/miui/epoxy/EpoxyViewHolder;)V

    return-object p1
.end method

.method public onDetachedFromRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 0

    invoke-super {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->onDetachedFromRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/epoxy/EpoxyAdapter;->isAttached:Z

    return-void
.end method

.method public bridge synthetic onViewAttachedToWindow(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/EpoxyViewHolder;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->onViewAttachedToWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V

    return-void
.end method

.method public onViewAttachedToWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 1

    iget-object v0, p1, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyModel;->attachedToWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V

    return-void
.end method

.method public bridge synthetic onViewDetachedFromWindow(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/EpoxyViewHolder;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->onViewDetachedFromWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V

    return-void
.end method

.method public onViewDetachedFromWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 1

    iget-object v0, p1, Lcom/miui/epoxy/EpoxyViewHolder;->model:Lcom/miui/epoxy/EpoxyModel;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyModel;->detachedFromWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V

    return-void
.end method

.method public bridge synthetic onViewRecycled(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/EpoxyViewHolder;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->onViewRecycled(Lcom/miui/epoxy/EpoxyViewHolder;)V

    return-void
.end method

.method public onViewRecycled(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 3

    if-nez p1, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->viewHolderState:Lcom/miui/epoxy/ViewHolderState;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/ViewHolderState;->save(Lcom/miui/epoxy/EpoxyViewHolder;)V

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->boundViewHolders:Landroidx/collection/LongSparseArray;

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->getItemId()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Landroidx/collection/LongSparseArray;->remove(J)V

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->unbind()V

    return-void
.end method

.method public removeModel(Lcom/miui/epoxy/EpoxyModel;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->indexOf(Ljava/lang/Object;)I

    move-result p1

    if-ltz p1, :cond_0

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->size()I

    move-result v0

    if-ge p1, v0, :cond_0

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->remove(I)Ljava/lang/Object;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->notifyItemRemoved(I)V

    :cond_0
    return-void
.end method

.method public replaceAllModels(Ljava/util/List;Z)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;Z)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->size()I

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->addModels(Ljava/util/Collection;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter;->calculateDiff(Ljava/util/List;)Landroidx/recyclerview/widget/DiffUtil$DiffResult;

    move-result-object v0

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {p2}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->clear()V

    iget-object p2, p0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {p2, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->addAll(Ljava/util/Collection;)Z

    :cond_1
    invoke-virtual {v0, p0}, Landroidx/recyclerview/widget/DiffUtil$DiffResult;->dispatchUpdatesTo(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    :goto_0
    return-void
.end method

.method public setSpanCount(I)V
    .locals 0

    iput p1, p0, Lcom/miui/epoxy/EpoxyAdapter;->spanCount:I

    return-void
.end method
