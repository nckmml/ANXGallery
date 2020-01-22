.class public abstract Lcom/miui/epoxy/EpoxyLoadMoreModel;
.super Lcom/miui/epoxy/EpoxyModel;
.source "EpoxyLoadMoreModel.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<VH:",
        "Lcom/miui/epoxy/EpoxyViewHolder;",
        ">",
        "Lcom/miui/epoxy/EpoxyModel<",
        "TVH;>;"
    }
.end annotation


# instance fields
.field private state:I


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/epoxy/EpoxyModel;-><init>()V

    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/epoxy/EpoxyLoadMoreModel;->state:I

    return-void
.end method


# virtual methods
.method public bindData(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TVH;)V"
        }
    .end annotation

    iget v0, p0, Lcom/miui/epoxy/EpoxyLoadMoreModel;->state:I

    if-eqz v0, :cond_2

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    const/4 v1, 0x2

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyLoadMoreModel;->onLoadMoreFailed(Lcom/miui/epoxy/EpoxyViewHolder;)V

    goto :goto_0

    :cond_1
    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyLoadMoreModel;->onLoadMoreComplete(Lcom/miui/epoxy/EpoxyViewHolder;)V

    goto :goto_0

    :cond_2
    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyLoadMoreModel;->onLoadMoreStart(Lcom/miui/epoxy/EpoxyViewHolder;)V

    :goto_0
    return-void
.end method

.method public abstract onLoadMoreComplete(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TVH;)V"
        }
    .end annotation
.end method

.method public abstract onLoadMoreFailed(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TVH;)V"
        }
    .end annotation
.end method

.method public abstract onLoadMoreStart(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TVH;)V"
        }
    .end annotation
.end method

.method public final setState(I)V
    .locals 0

    iput p1, p0, Lcom/miui/epoxy/EpoxyLoadMoreModel;->state:I

    return-void
.end method
