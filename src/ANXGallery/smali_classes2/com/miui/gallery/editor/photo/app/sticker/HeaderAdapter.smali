.class public Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;
.super Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$Adapter;
.source "HeaderAdapter.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$Adapter<",
        "Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;",
        ">;",
        "Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable;"
    }
.end annotation


# instance fields
.field private mCategories:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/photo/core/common/model/StickerCategory;",
            ">;"
        }
    .end annotation
.end field

.field private mDelegator:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;


# direct methods
.method public constructor <init>(Ljava/util/List;Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/photo/core/common/model/StickerCategory;",
            ">;",
            "Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;",
            ")V"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$Adapter;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mCategories:Ljava/util/List;

    new-instance p1, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;

    const/4 v0, 0x0

    invoke-direct {p1, v0, p2}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;-><init>(ILcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;)V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mDelegator:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;

    return-void
.end method


# virtual methods
.method public getItemCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mCategories:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    return v0
.end method

.method public getSelection()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mDelegator:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->getSelection()I

    move-result v0

    return v0
.end method

.method public onAttachedToRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$Adapter;->onAttachedToRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mDelegator:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->onAttachedToRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V

    return-void
.end method

.method public bridge synthetic onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V
    .locals 0

    check-cast p1, Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->onBindViewHolder(Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;I)V

    return-void
.end method

.method public onBindViewHolder(Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;I)V
    .locals 1

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$Adapter;->onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mCategories:Ljava/util/List;

    invoke-interface {v0, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/editor/photo/core/common/model/StickerCategory;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;->bind(Lcom/miui/gallery/editor/photo/core/common/model/StickerCategory;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mDelegator:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;

    invoke-virtual {v0, p1, p2}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V

    return-void
.end method

.method public bridge synthetic onCreateViewHolder(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;

    move-result-object p1

    return-object p1
.end method

.method public onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;
    .locals 3

    new-instance p2, Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->getInflater()Landroid/view/LayoutInflater;

    move-result-object v0

    const v1, 0x7f0b01aa

    const/4 v2, 0x0

    invoke-virtual {v0, v1, p1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    invoke-direct {p2, p1}, Lcom/miui/gallery/editor/photo/app/sticker/HeaderHolder;-><init>(Landroid/view/View;)V

    return-object p2
.end method

.method public onDetachedFromRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$Adapter;->onDetachedFromRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mDelegator:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->onDetachedFromRecyclerView(Landroidx/recyclerview/widget/RecyclerView;)V

    return-void
.end method

.method public setSelection(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->mDelegator:Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Delegator;->setSelection(I)V

    return-void
.end method
