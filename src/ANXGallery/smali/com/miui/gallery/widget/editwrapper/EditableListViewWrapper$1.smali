.class Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper$1;
.super Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;
.source "EditableListViewWrapper.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;


# direct methods
.method constructor <init>(Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper$1;->this$0:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onScrollStateChanged(Landroidx/recyclerview/widget/RecyclerView;I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper$1;->this$0:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;

    invoke-static {v0, p2}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;->access$002(Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;I)I

    iget-object v0, p0, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper$1;->this$0:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;

    invoke-static {v0}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;->access$100(Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;)Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper$1;->this$0:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;

    invoke-static {v0}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;->access$100(Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;)Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;->onScrollStateChanged(Landroidx/recyclerview/widget/RecyclerView;I)V

    :cond_0
    return-void
.end method

.method public onScrolled(Landroidx/recyclerview/widget/RecyclerView;II)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper$1;->this$0:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;

    invoke-static {v0}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;->access$100(Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;)Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper$1;->this$0:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;

    invoke-static {v0}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;->access$100(Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapper;)Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;

    move-result-object v0

    invoke-virtual {v0, p1, p2, p3}, Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;->onScrolled(Landroidx/recyclerview/widget/RecyclerView;II)V

    :cond_0
    return-void
.end method
