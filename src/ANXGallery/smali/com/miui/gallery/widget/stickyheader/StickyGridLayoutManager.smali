.class public Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager;
.super Landroidx/recyclerview/widget/GridLayoutManager;
.source "StickyGridLayoutManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;
    }
.end annotation


# instance fields
.field private mLayoutChangedListener:Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;


# direct methods
.method public constructor <init>(Landroid/content/Context;I)V
    .locals 0

    invoke-direct {p0, p1, p2}, Landroidx/recyclerview/widget/GridLayoutManager;-><init>(Landroid/content/Context;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 0

    invoke-direct {p0, p1, p2, p3, p4}, Landroidx/recyclerview/widget/GridLayoutManager;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    return-void
.end method


# virtual methods
.method public setLayoutChangedListener(Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager;->mLayoutChangedListener:Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;

    return-void
.end method

.method public setSpanCount(I)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager;->getSpanCount()I

    move-result v0

    invoke-super {p0, p1}, Landroidx/recyclerview/widget/GridLayoutManager;->setSpanCount(I)V

    iget-object v1, p0, Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager;->mLayoutChangedListener:Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;

    if-eqz v1, :cond_0

    if-eq v0, p1, :cond_0

    invoke-interface {v1, v0, p1}, Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;->onSpanCountChanged(II)V

    :cond_0
    return-void
.end method

.method public setSpanSizeLookup(Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager;->getSpanSizeLookup()Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;

    move-result-object v0

    invoke-super {p0, p1}, Landroidx/recyclerview/widget/GridLayoutManager;->setSpanSizeLookup(Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;)V

    iget-object v1, p0, Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager;->mLayoutChangedListener:Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;

    if-eqz v1, :cond_0

    if-eq v0, p1, :cond_0

    invoke-interface {v1, v0, p1}, Lcom/miui/gallery/widget/stickyheader/StickyGridLayoutManager$OnLayoutChangedListener;->onSpanSizeLookupChanged(Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;)V

    :cond_0
    return-void
.end method
