.class Lcom/miui/gallery/editor/photo/app/miuibeautify/ParameterViewHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "ParameterViewHolder.java"


# instance fields
.field private mTextView:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 1

    invoke-direct {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    const v0, 0x7f0901d0

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/miuibeautify/ParameterViewHolder;->mTextView:Landroid/widget/TextView;

    return-void
.end method


# virtual methods
.method bind(ILcom/miui/gallery/editor/photo/core/imports/miuibeauty/MiuiBeautifyData;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/miuibeautify/ParameterViewHolder;->mTextView:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1, p1, v1, v1}, Landroid/widget/TextView;->setCompoundDrawablesWithIntrinsicBounds(IIII)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/miuibeautify/ParameterViewHolder;->mTextView:Landroid/widget/TextView;

    iget-object p2, p2, Lcom/miui/gallery/editor/photo/core/imports/miuibeauty/MiuiBeautifyData;->name:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method
