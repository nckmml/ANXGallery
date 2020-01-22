.class Lcom/miui/epoxy/EpoxyAdapter$2;
.super Landroidx/recyclerview/widget/DiffUtil$Callback;
.source "EpoxyAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/epoxy/EpoxyAdapter;->calculateDiff(Ljava/util/List;)Landroidx/recyclerview/widget/DiffUtil$DiffResult;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/epoxy/EpoxyAdapter;

.field final synthetic val$newModels:Ljava/util/List;


# direct methods
.method constructor <init>(Lcom/miui/epoxy/EpoxyAdapter;Ljava/util/List;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->this$0:Lcom/miui/epoxy/EpoxyAdapter;

    iput-object p2, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->val$newModels:Ljava/util/List;

    invoke-direct {p0}, Landroidx/recyclerview/widget/DiffUtil$Callback;-><init>()V

    return-void
.end method

.method private getOrNull(Ljava/util/List;I)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/util/List<",
            "TT;>;I)TT;"
        }
    .end annotation

    if-eqz p1, :cond_0

    if-ltz p2, :cond_0

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    if-ge p2, v0, :cond_0

    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return-object p1
.end method


# virtual methods
.method public areContentsTheSame(II)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->this$0:Lcom/miui/epoxy/EpoxyAdapter;

    iget-object v0, v0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-direct {p0, v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$2;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/epoxy/EpoxyModel;

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->val$newModels:Ljava/util/List;

    invoke-direct {p0, v0, p2}, Lcom/miui/epoxy/EpoxyAdapter$2;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/epoxy/EpoxyModel;

    if-eqz p1, :cond_1

    if-eqz p2, :cond_1

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p1, p2}, Lcom/miui/epoxy/EpoxyModel;->isContentTheSame(Lcom/miui/epoxy/EpoxyModel;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x1

    return p1

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->this$0:Lcom/miui/epoxy/EpoxyAdapter;

    iget-boolean v0, v0, Lcom/miui/epoxy/EpoxyAdapter;->supportPropertyStage:Z

    if-eqz v0, :cond_1

    invoke-virtual {p2, p1}, Lcom/miui/epoxy/EpoxyModel;->doPropertiesStage(Lcom/miui/epoxy/EpoxyModel;)V

    :cond_1
    const/4 p1, 0x0

    return p1
.end method

.method public areItemsTheSame(II)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->this$0:Lcom/miui/epoxy/EpoxyAdapter;

    iget-object v0, v0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-direct {p0, v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$2;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/epoxy/EpoxyModel;

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->val$newModels:Ljava/util/List;

    invoke-direct {p0, v0, p2}, Lcom/miui/epoxy/EpoxyAdapter$2;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/epoxy/EpoxyModel;

    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyModel;->id()J

    move-result-wide v0

    invoke-virtual {p2}, Lcom/miui/epoxy/EpoxyModel;->id()J

    move-result-wide p1

    cmp-long p1, v0, p1

    if-nez p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method public getNewListSize()I
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->val$newModels:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    return v0
.end method

.method public getOldListSize()I
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$2;->this$0:Lcom/miui/epoxy/EpoxyAdapter;

    iget-object v0, v0, Lcom/miui/epoxy/EpoxyAdapter;->models:Lcom/miui/epoxy/EpoxyAdapter$ModelList;

    invoke-virtual {v0}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->size()I

    move-result v0

    return v0
.end method
