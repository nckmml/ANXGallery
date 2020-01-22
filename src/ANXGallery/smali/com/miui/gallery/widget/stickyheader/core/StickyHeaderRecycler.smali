.class public final Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;
.super Ljava/lang/Object;
.source "StickyHeaderRecycler.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<VH:",
        "Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field private final mHeaderAdapter:Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter<",
            "TVH;>;"
        }
    .end annotation
.end field

.field private final mLayoutHeaders:Landroid/util/LongSparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/LongSparseArray<",
            "TVH;>;"
        }
    .end annotation
.end field

.field private final mRecycleHeaders:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/util/LinkedList<",
            "TVH;>;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter<",
            "TVH;>;)V"
        }
    .end annotation

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mHeaderAdapter:Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;

    new-instance p1, Landroid/util/LongSparseArray;

    invoke-direct {p1}, Landroid/util/LongSparseArray;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mLayoutHeaders:Landroid/util/LongSparseArray;

    new-instance p1, Landroid/util/SparseArray;

    invoke-direct {p1}, Landroid/util/SparseArray;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mRecycleHeaders:Landroid/util/SparseArray;

    return-void
.end method

.method private addHeaderToRecycler(Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TVH;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mRecycleHeaders:Landroid/util/SparseArray;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->getHeaderViewType()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/LinkedList;

    if-nez v0, :cond_0

    new-instance v0, Ljava/util/LinkedList;

    invoke-direct {v0}, Ljava/util/LinkedList;-><init>()V

    iget-object v1, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mRecycleHeaders:Landroid/util/SparseArray;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->getHeaderViewType()I

    move-result v2

    invoke-virtual {v1, v2, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    :cond_0
    invoke-virtual {v0, p1}, Ljava/util/LinkedList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method private pollHeaderFromRecycler(I)Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TVH;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mRecycleHeaders:Landroid/util/SparseArray;

    invoke-virtual {v0, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/LinkedList;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Ljava/util/LinkedList;->size()I

    move-result v0

    if-lez v0, :cond_0

    invoke-virtual {p1}, Ljava/util/LinkedList;->remove()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return-object p1
.end method


# virtual methods
.method public getHeaderView(Landroidx/recyclerview/widget/RecyclerView;I)Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroidx/recyclerview/widget/RecyclerView;",
            "I)TVH;"
        }
    .end annotation

    invoke-static {p2}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderUtil;->isValidHeaderIndex(I)Z

    move-result v0

    const/4 v1, 0x0

    const-string v2, "StickyHeaderRecycler"

    if-nez v0, :cond_0

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string p2, "getHeaderView: header index %d is invalid"

    invoke-static {v2, p2, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-object v1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mHeaderAdapter:Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;

    invoke-interface {v0, p2}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;->getHeaderId(I)J

    move-result-wide v3

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mHeaderAdapter:Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;

    invoke-interface {v0, p2}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;->getHeaderViewType(I)I

    move-result v0

    invoke-static {v3, v4}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderUtil;->isValidHeaderId(J)Z

    move-result v5

    if-nez v5, :cond_1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string p2, "getHeaderView: header id is invalid, index %d"

    invoke-static {v2, p2, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-object v1

    :cond_1
    iget-object v1, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mLayoutHeaders:Landroid/util/LongSparseArray;

    invoke-virtual {v1, v3, v4}, Landroid/util/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;

    const/4 v5, 0x0

    if-eqz v1, :cond_2

    const/4 v6, 0x1

    goto :goto_0

    :cond_2
    move v6, v5

    :goto_0
    if-nez v1, :cond_3

    invoke-direct {p0, v0}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->pollHeaderFromRecycler(I)Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;

    move-result-object v1

    :cond_3
    if-nez v1, :cond_4

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v7

    iget-object v1, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mHeaderAdapter:Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;

    invoke-interface {v1, p1, v0}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;->onCreateHeaderViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;

    move-result-object v1

    iput v0, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->mHeaderViewType:I

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v9

    sub-long/2addr v9, v7

    invoke-static {v9, v10}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v7, "create header cost %s"

    invoke-static {v2, v7, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_4
    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mHeaderAdapter:Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;

    invoke-interface {v0, v1, p2}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderAdapter;->onBindHeaderViewHolder(Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;I)V

    iput-wide v3, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->mHeaderId:J

    iput p2, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->mHeaderIndex:I

    iget-object p2, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {p2}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p2

    if-nez p2, :cond_5

    iget-object p2, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    new-instance v0, Landroid/view/ViewGroup$LayoutParams;

    const/4 v3, -0x2

    invoke-direct {v0, v3, v3}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {p2, v0}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :cond_5
    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getWidth()I

    move-result p2

    const/high16 v0, 0x40000000    # 2.0f

    invoke-static {p2, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getHeight()I

    move-result v0

    invoke-static {v0, v5}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getPaddingStart()I

    move-result v3

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getPaddingEnd()I

    move-result v4

    add-int/2addr v3, v4

    iget-object v4, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v4}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v4

    iget v4, v4, Landroid/view/ViewGroup$LayoutParams;->width:I

    invoke-static {p2, v3, v4}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result p2

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getPaddingTop()I

    move-result v3

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getPaddingBottom()I

    move-result p1

    add-int/2addr v3, p1

    iget-object p1, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p1

    iget p1, p1, Landroid/view/ViewGroup$LayoutParams;->height:I

    invoke-static {v0, v3, p1}, Landroid/view/ViewGroup;->getChildMeasureSpec(III)I

    move-result p1

    iget-object v0, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0, p2, p1}, Landroid/view/View;->measure(II)V

    iget-object p1, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    iget-object p2, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {p2}, Landroid/view/View;->getMeasuredWidth()I

    move-result p2

    iget-object v0, v1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    invoke-virtual {p1, v5, v5, p2, v0}, Landroid/view/View;->layout(IIII)V

    if-nez v6, :cond_6

    iget-object p1, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mLayoutHeaders:Landroid/util/LongSparseArray;

    invoke-virtual {v1}, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->getHeaderId()J

    move-result-wide v3

    invoke-virtual {p1, v3, v4, v1}, Landroid/util/LongSparseArray;->put(JLjava/lang/Object;)V

    invoke-virtual {v1}, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->getHeaderId()J

    move-result-wide p1

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const-string p2, "layout header %s"

    invoke-static {v2, p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_6
    return-object v1
.end method

.method getLayoutHeader(J)Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)TVH;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mLayoutHeaders:Landroid/util/LongSparseArray;

    invoke-virtual {v0, p1, p2}, Landroid/util/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;

    return-object p1
.end method

.method getLayoutHeaders()Landroid/util/LongSparseArray;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Landroid/util/LongSparseArray<",
            "TVH;>;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mLayoutHeaders:Landroid/util/LongSparseArray;

    invoke-virtual {v0}, Landroid/util/LongSparseArray;->clone()Landroid/util/LongSparseArray;

    move-result-object v0

    return-object v0
.end method

.method public recycleHeader(Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TVH;)V"
        }
    .end annotation

    invoke-virtual {p1}, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->getHeaderId()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "StickyHeaderRecycler"

    const-string v2, "recycle header %s"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->mLayoutHeaders:Landroid/util/LongSparseArray;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;->getHeaderId()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Landroid/util/LongSparseArray;->remove(J)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/stickyheader/core/StickyHeaderRecycler;->addHeaderToRecycler(Lcom/miui/gallery/widget/stickyheader/core/HeaderViewHolder;)V

    return-void
.end method
