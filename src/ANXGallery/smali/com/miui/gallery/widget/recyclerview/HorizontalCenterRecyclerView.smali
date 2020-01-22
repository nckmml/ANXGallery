.class public Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;
.super Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;
.source "HorizontalCenterRecyclerView.java"


# instance fields
.field private mLastWidth:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;-><init>(Landroid/content/Context;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method


# virtual methods
.method protected onLayout(ZIIII)V
    .locals 4

    invoke-super/range {p0 .. p5}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;->onLayout(ZIIII)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->getChildCount()I

    move-result v0

    if-lez v0, :cond_2

    const/4 v0, 0x0

    move v1, v0

    move v2, v1

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->getChildCount()I

    move-result v3

    if-ge v1, v3, :cond_0

    invoke-virtual {p0, v1}, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    add-int/2addr v2, v3

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    iget v1, p0, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->mLastWidth:I

    if-eq v1, v2, :cond_2

    iput v2, p0, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->mLastWidth:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->getMeasuredWidth()I

    move-result v1

    sub-int/2addr v1, v2

    if-lez v1, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->getPaddingLeft()I

    move-result v2

    div-int/lit8 v1, v1, 0x2

    if-ne v2, v1, :cond_1

    return-void

    :cond_1
    invoke-virtual {p0, v1, v0, v1, v0}, Lcom/miui/gallery/widget/recyclerview/HorizontalCenterRecyclerView;->setPadding(IIII)V

    invoke-super/range {p0 .. p5}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;->onLayout(ZIIII)V

    :cond_2
    return-void
.end method
