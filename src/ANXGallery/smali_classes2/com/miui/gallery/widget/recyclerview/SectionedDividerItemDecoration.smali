.class public Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;
.super Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;
.source "SectionedDividerItemDecoration.java"


# instance fields
.field private mDividerTypeProvider:Lcom/miui/gallery/widget/DividerTypeProvider;

.field private mItemDistance:I

.field private mItemDivider:Landroid/graphics/drawable/Drawable;

.field private mItemDividerHeight:I

.field private mItemDividerPadding:Landroid/graphics/Rect;

.field private mSectionDistance:I

.field private mSectionDivider:Landroid/graphics/drawable/Drawable;

.field private mSectionDividerHeight:I

.field private mSectionDividerMargin:I


# direct methods
.method public constructor <init>(Landroid/content/Context;IILcom/miui/gallery/widget/DividerTypeProvider;)V
    .locals 1

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;-><init>()V

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDivider:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    const p3, 0x7f06059d

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDividerHeight:I

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    const p3, 0x7f06037f

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerHeight:I

    new-instance p2, Landroid/graphics/Rect;

    const/4 p3, 0x0

    invoke-direct {p2, p3, p3, p3, p3}, Landroid/graphics/Rect;-><init>(IIII)V

    iput-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iput-object p4, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mDividerTypeProvider:Lcom/miui/gallery/widget/DividerTypeProvider;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    const p3, 0x7f06007e

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDistance:I

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    const p3, 0x7f06007f

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDistance:I

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const p2, 0x7f060080

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDividerMargin:I

    return-void
.end method

.method private drawBottomDivider(Landroid/graphics/Canvas;Landroid/view/View;III)V
    .locals 3

    invoke-virtual {p2}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/RecyclerView$LayoutParams;

    invoke-virtual {p2}, Landroid/view/View;->getBottom()I

    move-result v1

    iget v0, v0, Landroidx/recyclerview/widget/RecyclerView$LayoutParams;->bottomMargin:I

    add-int/2addr v1, v0

    invoke-static {p2}, Landroidx/core/view/ViewCompat;->getTranslationY(Landroid/view/View;)F

    move-result v0

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    add-int/2addr v1, v0

    const/4 v0, 0x2

    const/4 v2, 0x1

    if-eq p3, v2, :cond_7

    if-eq p3, v0, :cond_3

    const/4 p2, 0x3

    if-eq p3, p2, :cond_0

    goto/16 :goto_3

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    if-eqz p2, :cond_2

    iget p3, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerHeight:I

    if-gtz p3, :cond_1

    goto :goto_0

    :cond_1
    add-int/2addr p3, v1

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    add-int/2addr v1, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr p3, v0

    invoke-virtual {p2, p4, v1, p5, p3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_3

    :cond_2
    :goto_0
    return-void

    :cond_3
    iget-object p3, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    if-eqz p3, :cond_6

    iget p3, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerHeight:I

    if-gtz p3, :cond_4

    goto :goto_2

    :cond_4
    add-int/2addr p3, v1

    invoke-virtual {p2}, Landroid/view/View;->getLayoutDirection()I

    move-result p2

    if-ne v2, p2, :cond_5

    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->right:I

    add-int/2addr p4, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    add-int/2addr v1, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->left:I

    sub-int/2addr p5, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr p3, v0

    invoke-virtual {p2, p4, v1, p5, p3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    goto :goto_1

    :cond_5
    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->left:I

    add-int/2addr p4, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    add-int/2addr v1, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->right:I

    sub-int/2addr p5, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr p3, v0

    invoke-virtual {p2, p4, v1, p5, p3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    :goto_1
    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_3

    :cond_6
    :goto_2
    return-void

    :cond_7
    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDivider:Landroid/graphics/drawable/Drawable;

    if-eqz p2, :cond_9

    iget p3, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDividerHeight:I

    if-gtz p3, :cond_8

    goto :goto_3

    :cond_8
    iget v2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDistance:I

    div-int/2addr v2, v0

    add-int/2addr v1, v2

    add-int/2addr p3, v1

    iget v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDividerMargin:I

    add-int/2addr p4, v0

    sub-int/2addr p5, v0

    invoke-virtual {p2, p4, v1, p5, p3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDivider:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    :cond_9
    :goto_3
    return-void
.end method

.method private drawTopDivider(Landroid/graphics/Canvas;Landroid/view/View;III)V
    .locals 2

    invoke-virtual {p2}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/RecyclerView$LayoutParams;

    invoke-virtual {p2}, Landroid/view/View;->getTop()I

    move-result v1

    iget v0, v0, Landroidx/recyclerview/widget/RecyclerView$LayoutParams;->topMargin:I

    add-int/2addr v1, v0

    invoke-static {p2}, Landroidx/core/view/ViewCompat;->getTranslationY(Landroid/view/View;)F

    move-result p2

    invoke-static {p2}, Ljava/lang/Math;->round(F)I

    move-result p2

    add-int/2addr v1, p2

    const/4 p2, 0x3

    if-eq p3, p2, :cond_0

    goto :goto_0

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    if-eqz p2, :cond_2

    iget p3, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerHeight:I

    if-gtz p3, :cond_1

    goto :goto_0

    :cond_1
    add-int/2addr p3, v1

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    add-int/2addr v1, v0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr p3, v0

    invoke-virtual {p2, p4, v1, p5, p3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    iget-object p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDivider:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private getBottomDividerType(I)I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mDividerTypeProvider:Lcom/miui/gallery/widget/DividerTypeProvider;

    if-eqz v0, :cond_0

    invoke-interface {v0, p1}, Lcom/miui/gallery/widget/DividerTypeProvider;->getBottomDividerType(I)I

    move-result p1

    return p1

    :cond_0
    const/4 p1, 0x0

    return p1
.end method

.method private getTopDividerType(I)I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mDividerTypeProvider:Lcom/miui/gallery/widget/DividerTypeProvider;

    if-eqz v0, :cond_0

    invoke-interface {v0, p1}, Lcom/miui/gallery/widget/DividerTypeProvider;->getTopDividerType(I)I

    move-result p1

    return p1

    :cond_0
    const/4 p1, 0x0

    return p1
.end method


# virtual methods
.method public getItemOffsets(Landroid/graphics/Rect;Landroid/view/View;Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$State;)V
    .locals 2

    invoke-virtual {p3, p2}, Landroidx/recyclerview/widget/RecyclerView;->getChildAdapterPosition(Landroid/view/View;)I

    move-result p2

    invoke-direct {p0, p2}, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->getTopDividerType(I)I

    move-result p3

    const/4 p4, 0x3

    const/4 v0, 0x0

    if-eq p3, p4, :cond_0

    move p3, v0

    goto :goto_0

    :cond_0
    iget p3, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerHeight:I

    :goto_0
    invoke-direct {p0, p2}, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->getBottomDividerType(I)I

    move-result p2

    const/4 v1, 0x1

    if-eq p2, v1, :cond_3

    const/4 v1, 0x2

    if-eq p2, v1, :cond_2

    if-eq p2, p4, :cond_1

    move p2, v0

    goto :goto_1

    :cond_1
    iget p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerHeight:I

    goto :goto_1

    :cond_2
    iget p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDistance:I

    goto :goto_1

    :cond_3
    iget p2, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mSectionDistance:I

    :goto_1
    invoke-virtual {p1, v0, p3, v0, p2}, Landroid/graphics/Rect;->set(IIII)V

    return-void
.end method

.method public onDrawOver(Landroid/graphics/Canvas;Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$State;)V
    .locals 12

    invoke-virtual {p2}, Landroidx/recyclerview/widget/RecyclerView;->getChildCount()I

    move-result v0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_0

    invoke-virtual {p2, v1}, Landroidx/recyclerview/widget/RecyclerView;->getChildAt(I)Landroid/view/View;

    move-result-object v8

    invoke-static {v8}, Landroidx/core/view/ViewCompat;->getTranslationX(Landroid/view/View;)F

    move-result v2

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v2

    invoke-virtual {v8}, Landroid/view/View;->getLeft()I

    move-result v3

    add-int v9, v3, v2

    invoke-virtual {v8}, Landroid/view/View;->getRight()I

    move-result v3

    add-int v10, v3, v2

    invoke-virtual {p2, v8}, Landroidx/recyclerview/widget/RecyclerView;->getChildAdapterPosition(Landroid/view/View;)I

    move-result v11

    invoke-direct {p0, v11}, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->getTopDividerType(I)I

    move-result v5

    move-object v2, p0

    move-object v3, p1

    move-object v4, v8

    move v6, v9

    move v7, v10

    invoke-direct/range {v2 .. v7}, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->drawTopDivider(Landroid/graphics/Canvas;Landroid/view/View;III)V

    invoke-direct {p0, v11}, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->getBottomDividerType(I)I

    move-result v5

    invoke-direct/range {v2 .. v7}, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->drawBottomDivider(Landroid/graphics/Canvas;Landroid/view/View;III)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    invoke-super {p0, p1, p2, p3}, Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;->onDrawOver(Landroid/graphics/Canvas;Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$State;)V

    return-void
.end method

.method public setItemDividerPadding(Landroid/graphics/Rect;)V
    .locals 1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    invoke-virtual {v0, p1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/widget/recyclerview/SectionedDividerItemDecoration;->mItemDividerPadding:Landroid/graphics/Rect;

    invoke-virtual {p1}, Landroid/graphics/Rect;->setEmpty()V

    :goto_0
    return-void
.end method
