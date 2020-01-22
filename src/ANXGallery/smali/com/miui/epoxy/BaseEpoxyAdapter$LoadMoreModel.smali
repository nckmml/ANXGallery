.class Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;
.super Lcom/miui/epoxy/EpoxyLoadMoreModel;
.source "BaseEpoxyAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/epoxy/BaseEpoxyAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "LoadMoreModel"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/epoxy/EpoxyLoadMoreModel<",
        "Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;",
        ">;"
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/epoxy/EpoxyLoadMoreModel;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/epoxy/BaseEpoxyAdapter$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;-><init>()V

    return-void
.end method


# virtual methods
.method public getLayoutRes()I
    .locals 1

    sget v0, Lcom/miui/epoxy/R$layout;->layout_empty_view_model:I

    return v0
.end method

.method public getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
            "Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$1;

    invoke-direct {v0, p0}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$1;-><init>(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;)V

    return-object v0
.end method

.method public onLoadMoreComplete(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)V
    .locals 1

    invoke-static {p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;->access$200(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)Landroid/widget/TextView;

    move-result-object p1

    const-string v0, "click to load"

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public bridge synthetic onLoadMoreComplete(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;->onLoadMoreComplete(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)V

    return-void
.end method

.method public onLoadMoreFailed(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)V
    .locals 1

    invoke-static {p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;->access$200(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)Landroid/widget/TextView;

    move-result-object p1

    const-string v0, "click to retry"

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public bridge synthetic onLoadMoreFailed(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;->onLoadMoreFailed(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)V

    return-void
.end method

.method public onLoadMoreStart(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)V
    .locals 1

    invoke-static {p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;->access$200(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)Landroid/widget/TextView;

    move-result-object p1

    const-string v0, "loading..."

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public bridge synthetic onLoadMoreStart(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 0

    check-cast p1, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;->onLoadMoreStart(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)V

    return-void
.end method
