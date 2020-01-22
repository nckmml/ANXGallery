.class public Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;
.super Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;
.source "DefaultEmptyPageItemModel.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel<",
        "Ljava/lang/Void;",
        "Lcom/miui/epoxy/EpoxyViewHolder;",
        ">;"
    }
.end annotation


# instance fields
.field protected mConfig:Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

.field protected mEmptyView:Landroid/view/View;

.field protected mLayoutId:I


# direct methods
.method public constructor <init>(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mConfig:Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mLayoutId:I

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mEmptyView:Landroid/view/View;

    return-void
.end method


# virtual methods
.method public bindData(Lcom/miui/epoxy/EpoxyViewHolder;Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyViewHolder;",
            "Ljava/util/List<",
            "Ljava/lang/Object;",
            ">;)V"
        }
    .end annotation

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->bindData(Lcom/miui/epoxy/EpoxyViewHolder;Ljava/util/List;)V

    iget-object p2, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mConfig:Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    if-eqz p2, :cond_0

    iget-object p2, p1, Lcom/miui/epoxy/EpoxyViewHolder;->itemView:Landroid/view/View;

    instance-of p2, p2, Lcom/miui/gallery/widget/EmptyPage;

    if-eqz p2, :cond_0

    iget-object p1, p1, Lcom/miui/epoxy/EpoxyViewHolder;->itemView:Landroid/view/View;

    check-cast p1, Lcom/miui/gallery/widget/EmptyPage;

    iget-object p2, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mConfig:Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/widget/EmptyPage;->apply(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)V

    :cond_0
    return-void
.end method

.method public getLayoutRes()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mLayoutId:I

    if-nez v0, :cond_0

    const v0, 0x7f0b00a0

    :cond_0
    return v0
.end method

.method public getSpanSize(III)I
    .locals 0

    return p1
.end method

.method public getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
            "Lcom/miui/epoxy/EpoxyViewHolder;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;-><init>(Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;)V

    return-object v0
.end method
