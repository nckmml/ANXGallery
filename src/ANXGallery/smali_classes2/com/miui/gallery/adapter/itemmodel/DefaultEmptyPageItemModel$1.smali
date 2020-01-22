.class Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;
.super Ljava/lang/Object;
.source "DefaultEmptyPageItemModel.java"

# interfaces
.implements Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
        "Lcom/miui/epoxy/EpoxyViewHolder;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;


# direct methods
.method constructor <init>(Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public create(Landroid/view/View;Landroid/view/View;)Lcom/miui/epoxy/EpoxyViewHolder;
    .locals 0

    iget-object p2, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;

    iget-object p2, p2, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mEmptyView:Landroid/view/View;

    if-eqz p2, :cond_1

    instance-of p2, p1, Landroid/view/ViewGroup;

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;

    iget-object p2, p2, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mEmptyView:Landroid/view/View;

    invoke-virtual {p2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p2

    if-ne p2, p1, :cond_0

    check-cast p1, Landroid/view/ViewGroup;

    iget-object p2, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;

    iget-object p2, p2, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mEmptyView:Landroid/view/View;

    invoke-virtual {p1, p2}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    :cond_0
    new-instance p1, Lcom/miui/epoxy/EpoxyViewHolder;

    iget-object p2, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;

    iget-object p2, p2, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mEmptyView:Landroid/view/View;

    invoke-direct {p1, p2}, Lcom/miui/epoxy/EpoxyViewHolder;-><init>(Landroid/view/View;)V

    return-object p1

    :cond_1
    iget-object p2, p0, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;

    iput-object p1, p2, Lcom/miui/gallery/adapter/itemmodel/DefaultEmptyPageItemModel;->mEmptyView:Landroid/view/View;

    new-instance p2, Lcom/miui/epoxy/EpoxyViewHolder;

    invoke-direct {p2, p1}, Lcom/miui/epoxy/EpoxyViewHolder;-><init>(Landroid/view/View;)V

    return-object p2
.end method
