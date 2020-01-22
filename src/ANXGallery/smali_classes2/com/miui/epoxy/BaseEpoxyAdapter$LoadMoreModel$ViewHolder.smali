.class public Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;
.super Lcom/miui/epoxy/EpoxyViewHolder;
.source "BaseEpoxyAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "ViewHolder"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;

.field private title:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;Landroid/view/View;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;->this$0:Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel;

    invoke-direct {p0, p2}, Lcom/miui/epoxy/EpoxyViewHolder;-><init>(Landroid/view/View;)V

    sget p1, Lcom/miui/epoxy/R$id;->section_title:I

    invoke-virtual {p2, p1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;->title:Landroid/widget/TextView;

    return-void
.end method

.method static synthetic access$200(Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$LoadMoreModel$ViewHolder;->title:Landroid/widget/TextView;

    return-object p0
.end method
