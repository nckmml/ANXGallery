.class Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "ChooserFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "CellHolder"
.end annotation


# instance fields
.field public mIcon:Landroid/widget/ImageView;

.field public mInfo:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;

.field public mText:Landroid/widget/TextView;

.field final synthetic this$0:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;Landroid/view/ViewGroup;Z)V
    .locals 4

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->this$0:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->access$800(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;)Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const v1, 0x7f0b0065

    const/4 v2, 0x0

    invoke-virtual {v0, v1, p2, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p2

    invoke-direct {p0, p2}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    if-eqz p3, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->itemView:Landroid/view/View;

    invoke-virtual {p2}, Landroid/view/View;->getPaddingStart()I

    move-result p2

    iget-object p3, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->itemView:Landroid/view/View;

    invoke-virtual {p3}, Landroid/view/View;->getPaddingEnd()I

    move-result p3

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getPaddingTop()I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->itemView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getPaddingBottom()I

    move-result v1

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->access$800(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;)Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f0600ff

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    add-int/2addr p2, v2

    iget-object v2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->itemView:Landroid/view/View;

    invoke-virtual {v2, p2, v0, p3, v1}, Landroid/view/View;->setPaddingRelative(IIII)V

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->itemView:Landroid/view/View;

    const p3, 0x7f0900ab

    invoke-virtual {p2, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/ImageView;

    iput-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mIcon:Landroid/widget/ImageView;

    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->itemView:Landroid/view/View;

    const p3, 0x7f0900ad

    invoke-virtual {p2, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mText:Landroid/widget/TextView;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->access$900(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;)I

    move-result p2

    if-nez p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mText:Landroid/widget/TextView;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->access$800(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;)Landroid/content/Context;

    move-result-object p1

    const p3, 0x7f1100be

    invoke-virtual {p2, p1, p3}, Landroid/widget/TextView;->setTextAppearance(Landroid/content/Context;I)V

    goto :goto_0

    :cond_1
    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mText:Landroid/widget/TextView;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->access$800(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;)Landroid/content/Context;

    move-result-object p1

    const p3, 0x7f1100bd

    invoke-virtual {p2, p1, p3}, Landroid/widget/TextView;->setTextAppearance(Landroid/content/Context;I)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mIcon:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method


# virtual methods
.method public needRefresh(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mInfo:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mIcon:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    return p1
.end method

.method public onClick(Landroid/view/View;)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/util/ShareComponentSorter;->getInstance()Lcom/miui/gallery/util/ShareComponentSorter;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mInfo:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;

    invoke-static {v0}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;->access$1000(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;)Landroid/content/pm/ResolveInfo;

    move-result-object v0

    iget-object v0, v0, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    iget-object v0, v0, Landroid/content/pm/ActivityInfo;->packageName:Ljava/lang/String;

    iget-object v1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mInfo:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;

    invoke-static {v1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;->access$1000(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;)Landroid/content/pm/ResolveInfo;

    move-result-object v1

    iget-object v1, v1, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    iget-object v1, v1, Landroid/content/pm/ActivityInfo;->name:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/util/ShareComponentSorter;->touch(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->this$0:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->access$1100(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;)Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->this$0:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->access$1100(Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;)Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    move-result-object p1

    new-instance v0, Landroid/content/Intent;

    iget-object v1, p0, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$CellHolder;->mInfo:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter$DisplayResolvedInfo;->getResolvedIntent()Landroid/content/Intent;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Intent;)V

    invoke-interface {p1, v0}, Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;->onIntentSelected(Landroid/content/Intent;)V

    :cond_0
    invoke-static {}, Lcom/miui/gallery/util/ShareComponentSorter;->getInstance()Lcom/miui/gallery/util/ShareComponentSorter;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/util/ShareComponentSorter;->save()V

    return-void
.end method
