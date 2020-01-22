.class public Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;
.super Landroidx/recyclerview/widget/LinearLayoutManager;
.source "ScrollLinearLayoutManager.java"


# instance fields
.field protected mSmoothScrollerController:Lcom/miui/gallery/ui/SmoothScrollerController;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;IZ)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    invoke-direct {p0, p1, p2, p3, p4}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method

.method public static onItemClick(Landroidx/recyclerview/widget/RecyclerView;I)V
    .locals 6

    invoke-virtual {p0}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutManager()Landroidx/recyclerview/widget/RecyclerView$LayoutManager;

    move-result-object v0

    invoke-virtual {p0}, Landroidx/recyclerview/widget/RecyclerView;->getAdapter()Landroidx/recyclerview/widget/RecyclerView$Adapter;

    move-result-object v1

    if-eqz v1, :cond_6

    if-eqz v0, :cond_6

    instance-of v2, v0, Landroidx/recyclerview/widget/LinearLayoutManager;

    if-eqz v2, :cond_6

    check-cast v0, Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->findLastVisibleItemPosition()I

    move-result v2

    invoke-virtual {v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->findLastCompletelyVisibleItemPosition()I

    move-result v3

    invoke-virtual {v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->findFirstVisibleItemPosition()I

    move-result v4

    invoke-virtual {v0}, Landroidx/recyclerview/widget/LinearLayoutManager;->findFirstCompletelyVisibleItemPosition()I

    move-result v0

    const/4 v5, -0x1

    if-eq v2, p1, :cond_4

    if-ne v3, p1, :cond_0

    goto :goto_1

    :cond_0
    if-eq v4, p1, :cond_2

    if-ne v0, p1, :cond_1

    goto :goto_0

    :cond_1
    move p1, v5

    goto :goto_2

    :cond_2
    :goto_0
    add-int/lit8 p1, p1, -0x1

    if-ltz p1, :cond_3

    invoke-virtual {v1}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->getItemCount()I

    move-result v0

    if-ge p1, v0, :cond_3

    goto :goto_2

    :cond_3
    const/4 p1, 0x0

    goto :goto_2

    :cond_4
    :goto_1
    add-int/lit8 p1, p1, 0x1

    if-lez p1, :cond_5

    invoke-virtual {v1}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->getItemCount()I

    move-result v0

    if-ge p1, v0, :cond_5

    goto :goto_2

    :cond_5
    invoke-virtual {v1}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->getItemCount()I

    move-result p1

    :goto_2
    if-eq p1, v5, :cond_6

    invoke-virtual {p0, p1}, Landroidx/recyclerview/widget/RecyclerView;->smoothScrollToPosition(I)V

    :cond_6
    return-void
.end method


# virtual methods
.method public setSmoothScroller(Lcom/miui/gallery/ui/SmoothScrollerController;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->mSmoothScrollerController:Lcom/miui/gallery/ui/SmoothScrollerController;

    return-void
.end method

.method public smoothScrollToPosition(Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$State;I)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->mSmoothScrollerController:Lcom/miui/gallery/ui/SmoothScrollerController;

    if-eqz p1, :cond_0

    invoke-virtual {p1, p3}, Lcom/miui/gallery/ui/SmoothScrollerController;->setTargetPosition(I)V

    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->mSmoothScrollerController:Lcom/miui/gallery/ui/SmoothScrollerController;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->startSmoothScroll(Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;)V

    :cond_0
    return-void
.end method
