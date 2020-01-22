.class Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$ViewCacheExtensionWrapper;
.super Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;
.source "StickyHeaderGridRecyclerView.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "ViewCacheExtensionWrapper"
.end annotation


# instance fields
.field private final mWrapped:Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;

.field final synthetic this$0:Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$ViewCacheExtensionWrapper;->this$0:Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$ViewCacheExtensionWrapper;->mWrapped:Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$1;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$ViewCacheExtensionWrapper;-><init>(Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;)V

    return-void
.end method


# virtual methods
.method public getViewForPositionAndType(Landroidx/recyclerview/widget/RecyclerView$Recycler;II)Landroid/view/View;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$ViewCacheExtensionWrapper;->mWrapped:Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;

    iget-object v1, p0, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$ViewCacheExtensionWrapper;->this$0:Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;

    iget-object v1, v1, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;->mAdapter:Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridAdapterWrapper;

    invoke-virtual {v1, p2}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridAdapterWrapper;->decryptPosition(I)I

    move-result p2

    iget-object v1, p0, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView$ViewCacheExtensionWrapper;->this$0:Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;

    iget-object v1, v1, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridRecyclerView;->mAdapter:Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridAdapterWrapper;

    invoke-virtual {v1, p3}, Lcom/miui/gallery/widget/stickyheader/StickyHeaderGridAdapterWrapper;->decryptViewType(I)I

    move-result p3

    invoke-virtual {v0, p1, p2, p3}, Landroidx/recyclerview/widget/RecyclerView$ViewCacheExtension;->getViewForPositionAndType(Landroidx/recyclerview/widget/RecyclerView$Recycler;II)Landroid/view/View;

    move-result-object p1

    return-object p1
.end method
