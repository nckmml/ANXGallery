.class public Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter$ImplHorizLayout;
.super Ljava/lang/Object;
.source "RecyclerViewOverScrollDecorAdapter.java"

# interfaces
.implements Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter$Impl;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4
    name = "ImplHorizLayout"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;


# direct methods
.method protected constructor <init>(Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter$ImplHorizLayout;->this$0:Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public isInAbsoluteEnd()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter$ImplHorizLayout;->this$0:Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;

    iget-object v0, v0, Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->canScrollHorizontally(I)Z

    move-result v0

    xor-int/2addr v0, v1

    return v0
.end method

.method public isInAbsoluteStart()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter$ImplHorizLayout;->this$0:Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;

    iget-object v0, v0, Lcom/miui/gallery/widget/overscroll/RecyclerViewOverScrollDecorAdapter;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->canScrollHorizontally(I)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method
