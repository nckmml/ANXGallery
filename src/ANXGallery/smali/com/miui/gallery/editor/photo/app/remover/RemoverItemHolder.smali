.class Lcom/miui/gallery/editor/photo/app/remover/RemoverItemHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "RemoverItemHolder.java"


# instance fields
.field private mIconView:Landroid/widget/ImageView;

.field private mLabelView:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 1

    invoke-direct {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    const v0, 0x7f0901d1

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/remover/RemoverItemHolder;->mLabelView:Landroid/widget/TextView;

    const v0, 0x7f09019e

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/remover/RemoverItemHolder;->mIconView:Landroid/widget/ImageView;

    return-void
.end method


# virtual methods
.method bind(Lcom/miui/gallery/editor/photo/core/common/model/RemoverData;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/remover/RemoverItemHolder;->mIconView:Landroid/widget/ImageView;

    iget v1, p1, Lcom/miui/gallery/editor/photo/core/common/model/RemoverData;->mIcon:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/remover/RemoverItemHolder;->mLabelView:Landroid/widget/TextView;

    iget-object p1, p1, Lcom/miui/gallery/editor/photo/core/common/model/RemoverData;->name:Ljava/lang/String;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method
