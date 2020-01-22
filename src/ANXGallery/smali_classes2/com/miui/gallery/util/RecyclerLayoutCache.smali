.class public Lcom/miui/gallery/util/RecyclerLayoutCache;
.super Ljava/lang/Object;
.source "RecyclerLayoutCache.java"


# instance fields
.field private mCache:Ljava/util/LinkedList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/LinkedList<",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation
.end field

.field private final mCacheSize:I


# direct methods
.method public constructor <init>(I)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/LinkedList;

    invoke-direct {v0}, Ljava/util/LinkedList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/util/RecyclerLayoutCache;->mCache:Ljava/util/LinkedList;

    iput p1, p0, Lcom/miui/gallery/util/RecyclerLayoutCache;->mCacheSize:I

    return-void
.end method


# virtual methods
.method public get()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/util/RecyclerLayoutCache;->mCache:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/LinkedList;->poll()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    return-object v0
.end method

.method public put(Landroid/view/View;)V
    .locals 2

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    instance-of v1, v0, Landroid/view/ViewGroup;

    if-eqz v1, :cond_1

    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {v0, p1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/util/RecyclerLayoutCache;->mCache:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/LinkedList;->size()I

    move-result v0

    iget v1, p0, Lcom/miui/gallery/util/RecyclerLayoutCache;->mCacheSize:I

    if-ge v0, v1, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/util/RecyclerLayoutCache;->mCache:Ljava/util/LinkedList;

    invoke-virtual {v0, p1}, Ljava/util/LinkedList;->push(Ljava/lang/Object;)V

    :cond_2
    return-void
.end method
