.class public Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;
.super Lcom/miui/gallery/widget/stickyheader/StickyHeaderLinearLayoutManager;
.source "StickyHeaderGridLayoutManager.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<VH:",
        "Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;",
        ">",
        "Lcom/miui/gallery/widget/stickyheader/StickyHeaderLinearLayoutManager<",
        "TVH;>;"
    }
.end annotation


# direct methods
.method public constructor <init>(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderLinearLayoutManager;-><init>(Landroidx/recyclerview/widget/RecyclerView;)V

    return-void
.end method

.method private assertLayoutManager()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutManager()Landroidx/recyclerview/widget/RecyclerView$LayoutManager;

    move-result-object v0

    instance-of v0, v0, Landroidx/recyclerview/widget/GridLayoutManager;

    if-eqz v0, :cond_0

    return-void

    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "the layoutManager of RecyclerView should be GridLayoutManager"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private getFirstItemPositionOfSpan(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)I
    .locals 3

    iget-object v0, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/GridLayoutManager$LayoutParams;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/GridLayoutManager$LayoutParams;->getSpanIndex()I

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->getAdapterPosition()I

    move-result p1

    return p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutManager()Landroidx/recyclerview/widget/RecyclerView$LayoutManager;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/GridLayoutManager;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/GridLayoutManager;->getSpanSizeLookup()Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;

    move-result-object v1

    invoke-virtual {v0}, Landroidx/recyclerview/widget/GridLayoutManager;->getSpanCount()I

    move-result v0

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->getAdapterPosition()I

    move-result p1

    add-int/lit8 p1, p1, -0x1

    :goto_0
    const/4 v2, -0x1

    if-le p1, v2, :cond_2

    invoke-virtual {v1, p1, v0}, Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;->getSpanIndex(II)I

    move-result v2

    if-nez v2, :cond_1

    return p1

    :cond_1
    add-int/lit8 p1, p1, -0x1

    goto :goto_0

    :cond_2
    return v2
.end method


# virtual methods
.method public hasStickyHeader(Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;->assertLayoutManager()V

    iget-object v0, p2, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/GridLayoutManager$LayoutParams;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/GridLayoutManager$LayoutParams;->getSpanIndex()I

    move-result v0

    if-nez v0, :cond_0

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderLinearLayoutManager;->hasStickyHeader(Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method public needOffsetForHeader(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)Z
    .locals 4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;->isReady()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    const-string v0, "StickyHeaderGridLayoutManager"

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->getAdapterPosition()I

    move-result v2

    const/4 v3, -0x1

    if-ne v2, v3, :cond_1

    goto :goto_0

    :cond_1
    invoke-direct {p0}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;->assertLayoutManager()V

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;->getFirstItemPositionOfSpan(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)I

    move-result p1

    if-ne p1, v3, :cond_2

    const-string p1, "invalid position"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return v1

    :cond_2
    add-int/lit8 v0, p1, -0x1

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridLayoutManager;->hasDifferentHeader(II)Z

    move-result p1

    return p1

    :cond_3
    :goto_0
    const-string p1, "the holder is invalid"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return v1
.end method
