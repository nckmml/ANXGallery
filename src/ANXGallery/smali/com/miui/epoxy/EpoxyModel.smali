.class public abstract Lcom/miui/epoxy/EpoxyModel;
.super Ljava/lang/Object;
.source "EpoxyModel.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Lcom/miui/epoxy/EpoxyViewHolder;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# static fields
.field private static idCounter:J = -0x2L


# instance fields
.field private afterModelBuild:Z

.field private id:J

.field private shouldStageProperty:Z

.field protected viewHolder:Lcom/miui/epoxy/EpoxyViewHolder;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>()V
    .locals 4

    sget-wide v0, Lcom/miui/epoxy/EpoxyModel;->idCounter:J

    const-wide/16 v2, 0x1

    sub-long v2, v0, v2

    sput-wide v2, Lcom/miui/epoxy/EpoxyModel;->idCounter:J

    invoke-direct {p0, v0, v1}, Lcom/miui/epoxy/EpoxyModel;-><init>(J)V

    return-void
.end method

.method protected constructor <init>(J)V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/epoxy/EpoxyModel;->afterModelBuild:Z

    iput-boolean v0, p0, Lcom/miui/epoxy/EpoxyModel;->shouldStageProperty:Z

    const-wide/16 v0, -0x1

    cmp-long v0, p1, v0

    if-nez v0, :cond_0

    sget-wide p1, Lcom/miui/epoxy/EpoxyModel;->idCounter:J

    const-wide/16 v0, 0x1

    sub-long v0, p1, v0

    sput-wide v0, Lcom/miui/epoxy/EpoxyModel;->idCounter:J

    iput-wide p1, p0, Lcom/miui/epoxy/EpoxyModel;->id:J

    goto :goto_0

    :cond_0
    iput-wide p1, p0, Lcom/miui/epoxy/EpoxyModel;->id:J

    :goto_0
    return-void
.end method

.method protected static hashInt(I)I
    .locals 1

    shl-int/lit8 v0, p0, 0xd

    xor-int/2addr p0, v0

    ushr-int/lit8 v0, p0, 0x11

    xor-int/2addr p0, v0

    shl-int/lit8 v0, p0, 0x5

    xor-int/2addr p0, v0

    return p0
.end method


# virtual methods
.method public attachedToWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/miui/epoxy/EpoxyModel;->viewHolder:Lcom/miui/epoxy/EpoxyViewHolder;

    return-void
.end method

.method public bindData(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lcom/miui/epoxy/EpoxyModel;->bindData(Lcom/miui/epoxy/EpoxyViewHolder;Ljava/util/List;)V

    return-void
.end method

.method public bindData(Lcom/miui/epoxy/EpoxyViewHolder;Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;",
            "Ljava/util/List<",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    return-void
.end method

.method public bindPartialData(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyModel;->bindData(Lcom/miui/epoxy/EpoxyViewHolder;)V

    return-void
.end method

.method public detachedFromWindow(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    iget-object p1, p0, Lcom/miui/epoxy/EpoxyModel;->viewHolder:Lcom/miui/epoxy/EpoxyViewHolder;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->unbind()V

    :cond_0
    return-void
.end method

.method public doPropertiesCleanUp()V
    .locals 0

    return-void
.end method

.method public doPropertiesStage(Lcom/miui/epoxy/EpoxyModel;)V
    .locals 0

    return-void
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 7

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lcom/miui/epoxy/EpoxyModel;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/miui/epoxy/EpoxyModel;

    iget-wide v3, p0, Lcom/miui/epoxy/EpoxyModel;->id:J

    iget-wide v5, p1, Lcom/miui/epoxy/EpoxyModel;->id:J

    cmp-long v1, v3, v5

    if-eqz v1, :cond_2

    return v2

    :cond_2
    invoke-virtual {p0}, Lcom/miui/epoxy/EpoxyModel;->getViewType()I

    move-result v1

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyModel;->getViewType()I

    move-result p1

    if-ne v1, p1, :cond_3

    goto :goto_0

    :cond_3
    move v0, v2

    :goto_0
    return v0
.end method

.method public abstract getLayoutRes()I
.end method

.method public getSpanSize(III)I
    .locals 0

    const/4 p1, 0x1

    return p1
.end method

.method public abstract getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
            "TT;>;"
        }
    .end annotation
.end method

.method protected getViewType()I
    .locals 1

    invoke-virtual {p0}, Lcom/miui/epoxy/EpoxyModel;->getLayoutRes()I

    move-result v0

    invoke-static {v0}, Lcom/miui/epoxy/EpoxyModel;->hashInt(I)I

    move-result v0

    return v0
.end method

.method public hashCode()I
    .locals 4

    iget-wide v0, p0, Lcom/miui/epoxy/EpoxyModel;->id:J

    const/16 v2, 0x20

    ushr-long v2, v0, v2

    xor-long/2addr v0, v2

    long-to-int v0, v0

    mul-int/lit8 v0, v0, 0x1f

    invoke-virtual {p0}, Lcom/miui/epoxy/EpoxyModel;->getViewType()I

    move-result v1

    add-int/2addr v0, v1

    return v0
.end method

.method public final id()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/epoxy/EpoxyModel;->id:J

    return-wide v0
.end method

.method public isContentTheSame(Lcom/miui/epoxy/EpoxyModel;)Z
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)Z"
        }
    .end annotation

    const/4 p1, 0x1

    return p1
.end method

.method public isPropertiesChanged()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public shouldSaveViewState()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method shouldStageProperty(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/epoxy/EpoxyModel;->shouldStageProperty:Z

    return-void
.end method

.method public unbind(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    return-void
.end method
