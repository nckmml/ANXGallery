.class public abstract Lcom/miui/gallery/ui/AsyncViewPrefetch;
.super Ljava/lang/Object;
.source "AsyncViewPrefetch.java"

# interfaces
.implements Landroidx/asynclayoutinflater/view/AsyncLayoutInflater$OnInflateFinishedListener;
.implements Lcom/miui/gallery/ui/ViewProvider;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;
    }
.end annotation


# instance fields
.field private mCount:I

.field private mInflater:Landroidx/asynclayoutinflater/view/AsyncLayoutInflater;

.field private mParent:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Landroid/view/ViewGroup;",
            ">;"
        }
    .end annotation
.end field

.field private mPool:Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;

.field private mPosition:I

.field private volatile mStarted:Ljava/lang/Boolean;

.field private mTimer:J


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/view/ViewGroup;I)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Landroidx/asynclayoutinflater/view/AsyncLayoutInflater;

    invoke-direct {v0, p1}, Landroidx/asynclayoutinflater/view/AsyncLayoutInflater;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mInflater:Landroidx/asynclayoutinflater/view/AsyncLayoutInflater;

    iput p3, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mCount:I

    new-instance p1, Ljava/lang/ref/WeakReference;

    invoke-direct {p1, p2}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mParent:Ljava/lang/ref/WeakReference;

    new-instance p1, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;

    const/4 p2, 0x0

    invoke-direct {p1, p2}, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;-><init>(Lcom/miui/gallery/ui/AsyncViewPrefetch$1;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPool:Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;

    return-void
.end method

.method private doFetch()V
    .locals 3

    iget v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPosition:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/AsyncViewPrefetch;->getItemViewType(I)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/AsyncViewPrefetch;->getViewResId(I)I

    move-result v0

    if-lez v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mInflater:Landroidx/asynclayoutinflater/view/AsyncLayoutInflater;

    iget-object v2, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mParent:Ljava/lang/ref/WeakReference;

    invoke-virtual {v2}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/view/ViewGroup;

    invoke-virtual {v1, v0, v2, p0}, Landroidx/asynclayoutinflater/view/AsyncLayoutInflater;->inflate(ILandroid/view/ViewGroup;Landroidx/asynclayoutinflater/view/AsyncLayoutInflater$OnInflateFinishedListener;)V

    return-void

    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "invalid res id"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method public getItemViewType(I)I
    .locals 0

    const/4 p1, 0x0

    return p1
.end method

.method public getViewByType(I)Landroid/view/View;
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mStarted:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPool:Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;

    invoke-virtual {v1, p1}, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;->pop(I)Landroid/view/View;

    move-result-object p1

    if-eqz v0, :cond_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mTimer:J

    sub-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPool:Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;->size()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "AsyncViewPrefetch"

    const-string v3, "prefetch view %s, time %d, size %d"

    invoke-static {v2, v3, p1, v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_0
    return-object p1
.end method

.method public final onInflateFinished(Landroid/view/View;ILandroid/view/ViewGroup;)V
    .locals 0

    const-string p2, "AsyncViewPrefetch"

    const-string p3, "onInflateFinished"

    invoke-static {p2, p3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPool:Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;

    iget p3, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPosition:I

    invoke-virtual {p0, p3}, Lcom/miui/gallery/ui/AsyncViewPrefetch;->getItemViewType(I)I

    move-result p3

    invoke-virtual {p2, p3, p1}, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;->push(ILandroid/view/View;)V

    iget p1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPosition:I

    const/4 p2, 0x1

    add-int/2addr p1, p2

    iput p1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPosition:I

    iget p1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mPosition:I

    iget p3, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mCount:I

    if-ge p1, p3, :cond_0

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    :goto_0
    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mStarted:Ljava/lang/Boolean;

    iget-object p1, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mStarted:Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-direct {p0}, Lcom/miui/gallery/ui/AsyncViewPrefetch;->doFetch()V

    :cond_1
    return-void
.end method

.method public prefetch()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mStarted:Ljava/lang/Boolean;

    if-nez v0, :cond_0

    iget v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mCount:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "AsyncViewPrefetch"

    const-string v2, "prefetch starting, size %d"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const/4 v0, 0x1

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mStarted:Ljava/lang/Boolean;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch;->mTimer:J

    invoke-direct {p0}, Lcom/miui/gallery/ui/AsyncViewPrefetch;->doFetch()V

    return-void

    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "has started"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
