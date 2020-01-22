.class public Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;
.super Lcom/miui/gallery/widget/recyclerview/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter;
.source "TypeFaceAdapter.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/widget/recyclerview/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter<",
        "Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;",
        ">;"
    }
.end annotation


# instance fields
.field private mLayoutInflater:Landroid/view/LayoutInflater;

.field private mTextStyleList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/widget/recyclerview/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->mTextStyleList:Ljava/util/List;

    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->mLayoutInflater:Landroid/view/LayoutInflater;

    return-void
.end method


# virtual methods
.method public getItemCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->mTextStyleList:Ljava/util/List;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    return v0
.end method

.method public onBindView(Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->mTextStyleList:Ljava/util/List;

    invoke-interface {v0, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->getSelectedItemPosition()I

    move-result v1

    if-ne p2, v1, :cond_0

    const/4 p2, 0x1

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    :goto_0
    invoke-virtual {p1, v0, p2}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;->bind(Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;Z)V

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;->isDownloadSuccess()Z

    move-result p1

    if-eqz p1, :cond_1

    const/16 p1, 0x11

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;->setState(I)V

    :cond_1
    return-void
.end method

.method public bridge synthetic onBindView(Lcom/miui/gallery/widget/recyclerview/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$SingleChoiceViewHolder;I)V
    .locals 0

    check-cast p1, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->onBindView(Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;I)V

    return-void
.end method

.method public bridge synthetic onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;ILjava/util/List;)V
    .locals 0

    check-cast p1, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;

    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->onBindViewHolder(Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;ILjava/util/List;)V

    return-void
.end method

.method public onBindViewHolder(Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;ILjava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;",
            "I",
            "Ljava/util/List<",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    invoke-interface {p3}, Ljava/util/List;->isEmpty()Z

    move-result p3

    if-eqz p3, :cond_0

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->onBindViewHolder(Lcom/miui/gallery/widget/recyclerview/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$SingleChoiceViewHolder;I)V

    goto :goto_1

    :cond_0
    iget-object p3, p0, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->mTextStyleList:Ljava/util/List;

    invoke-interface {p3, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->getSelectedItemPosition()I

    move-result v0

    if-ne p2, v0, :cond_1

    const/4 p2, 0x1

    goto :goto_0

    :cond_1
    const/4 p2, 0x0

    :goto_0
    invoke-virtual {p1, p3, p2}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;->bind(Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;Z)V

    invoke-virtual {p3}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;->isDownloadSuccess()Z

    move-result p1

    if-eqz p1, :cond_2

    const/16 p1, 0x11

    invoke-virtual {p3, p1}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TextStyle;->setState(I)V

    :cond_2
    :goto_1
    return-void
.end method

.method public onCreateSingleChoiceViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;
    .locals 2

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->mLayoutInflater:Landroid/view/LayoutInflater;

    const v0, 0x7f0b01b6

    const/4 v1, 0x0

    invoke-virtual {p2, v0, p1, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    new-instance p2, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;

    invoke-direct {p2, p1}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;-><init>(Landroid/view/View;)V

    return-object p2
.end method

.method public bridge synthetic onCreateSingleChoiceViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/gallery/widget/recyclerview/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$SingleChoiceViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceAdapter;->onCreateSingleChoiceViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/gallery/editor/photo/core/imports/text/typeface/TypeFaceHolder;

    move-result-object p1

    return-object p1
.end method
