.class Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$1;
.super Ljava/lang/Object;
.source "BaseEpoxyAdapter.java"

# interfaces
.implements Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;->getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
        "Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;


# direct methods
.method constructor <init>(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$1;->this$0:Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public create(Landroid/view/View;Landroid/view/View;)Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;
    .locals 1

    new-instance p2, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$1;->this$0:Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;

    invoke-direct {p2, v0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;-><init>(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;Landroid/view/View;)V

    return-object p2
.end method

.method public bridge synthetic create(Landroid/view/View;Landroid/view/View;)Lcom/miui/epoxy/EpoxyViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$1;->create(Landroid/view/View;Landroid/view/View;)Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;

    move-result-object p1

    return-object p1
.end method
