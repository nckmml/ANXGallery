.class Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment$3;
.super Ljava/lang/Object;
.source "LayoutMenuFragment.java"

# interfaces
.implements Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment$3;->this$0:Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public OnItemClick(Landroidx/recyclerview/widget/RecyclerView;Landroid/view/View;I)Z
    .locals 0

    invoke-static {p1, p3}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->onItemClick(Landroidx/recyclerview/widget/RecyclerView;I)V

    iget-object p1, p0, Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment$3;->this$0:Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;

    invoke-static {p1}, Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;->access$400(Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;)Lcom/miui/gallery/collage/app/layout/LayoutMenuAdapter;

    move-result-object p1

    invoke-virtual {p1, p3}, Lcom/miui/gallery/collage/app/layout/LayoutMenuAdapter;->setSelection(I)V

    iget-object p1, p0, Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment$3;->this$0:Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;

    invoke-static {p1, p3}, Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;->access$500(Lcom/miui/gallery/collage/app/layout/LayoutMenuFragment;I)V

    const/4 p1, 0x1

    return p1
.end method
