.class Lcom/miui/gallery/widget/TwoStageDrawer$RecyclerViewWrapper;
.super Ljava/lang/Object;
.source "TwoStageDrawer.java"

# interfaces
.implements Lcom/miui/gallery/widget/TwoStageDrawer$ScrollableView;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/widget/TwoStageDrawer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "RecyclerViewWrapper"
.end annotation


# instance fields
.field private mRecycler:Landroidx/recyclerview/widget/RecyclerView;


# direct methods
.method public constructor <init>(Landroidx/recyclerview/widget/RecyclerView;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/widget/TwoStageDrawer$RecyclerViewWrapper;->mRecycler:Landroidx/recyclerview/widget/RecyclerView;

    return-void
.end method


# virtual methods
.method public canScrollDown()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/widget/TwoStageDrawer$RecyclerViewWrapper;->mRecycler:Landroidx/recyclerview/widget/RecyclerView;

    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->canScrollVertically(I)Z

    move-result v0

    return v0
.end method
