.class public final Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;
.super Ljava/lang/Object;
.source "Selectable.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Delegator"
.end annotation


# instance fields
.field private mParent:Landroidx/recyclerview/widget/RecyclerView;

.field private mSelection:I

.field private mSelector:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, -0x1

    invoke-direct {p0, v0}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;-><init>(I)V

    return-void
.end method

.method public constructor <init>(I)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;-><init>(ILcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;)V

    return-void
.end method

.method public constructor <init>(ILcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelection:I

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelector:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;

    if-eqz p2, :cond_0

    iput-object p0, p2, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;->mAdapter:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable;

    :cond_0
    return-void
.end method


# virtual methods
.method public getSelection()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelection:I

    return v0
.end method

.method public onAttachedToRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelector:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;

    if-eqz v0, :cond_0

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelector:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;

    iget-boolean v0, v0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;->mRequireLayer:Z

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getLayerType()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x2

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setLayerType(ILandroid/graphics/Paint;)V

    :cond_0
    iput-object p1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    return-void

    :cond_1
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "already attach to a recycler view"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V
    .locals 4

    iget-object v0, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    iget v1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelection:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-ne p2, v1, :cond_1

    move v1, v2

    goto :goto_0

    :cond_1
    move v1, v3

    :goto_0
    invoke-virtual {v0, v1}, Landroid/view/View;->setActivated(Z)V

    iget-object p1, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    iget v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelection:I

    if-ne p2, v0, :cond_2

    goto :goto_1

    :cond_2
    move v2, v3

    :goto_1
    invoke-virtual {p1, v2}, Landroid/view/View;->setSelected(Z)V

    return-void
.end method

.method public onDetachedFromRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    if-ne v0, p1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelector:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;

    if-eqz v0, :cond_0

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView;->removeItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    :cond_0
    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    goto :goto_0

    :cond_1
    const-string p1, "Selectable.Delegator"

    const-string v0, "not the attached parent view ."

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method public setSelection(I)V
    .locals 5

    iget v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelection:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    const/4 v1, -0x1

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eq v0, v1, :cond_2

    iget-object v4, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {v4, v0}, Landroidx/recyclerview/widget/RecyclerView;->findViewHolderForAdapterPosition(I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v4, v0, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    if-eqz v4, :cond_1

    iget-object v4, v0, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v4, v2}, Landroid/view/View;->setActivated(Z)V

    iget-object v0, v0, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0, v2}, Landroid/view/View;->setSelected(Z)V

    goto :goto_0

    :cond_1
    iget v0, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelection:I

    move v2, v3

    goto :goto_1

    :cond_2
    :goto_0
    move v0, v3

    :goto_1
    iput p1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelection:I

    if-eq p1, v1, :cond_3

    iget-object v4, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {v4, p1}, Landroidx/recyclerview/widget/RecyclerView;->findViewHolderForAdapterPosition(I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;

    move-result-object p1

    if-eqz p1, :cond_3

    iget-object v4, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    if-eqz v4, :cond_3

    iget-object v4, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v4, v3}, Landroid/view/View;->setActivated(Z)V

    iget-object p1, p1, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {p1, v3}, Landroid/view/View;->setSelected(Z)V

    :cond_3
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mSelector:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->invalidate()V

    :cond_4
    if-eqz v2, :cond_5

    if-eq v0, v1, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->mParent:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getAdapter()Landroidx/recyclerview/widget/RecyclerView$Adapter;

    move-result-object p1

    invoke-virtual {p1, v0}, Landroidx/recyclerview/widget/RecyclerView$Adapter;->notifyItemChanged(I)V

    :cond_5
    return-void
.end method
