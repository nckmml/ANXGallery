.class public final Lcom/miui/widget/util/Pools;
.super Ljava/lang/Object;
.source "Pools.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/widget/util/Pools$SoftReferencePool;,
        Lcom/miui/widget/util/Pools$BasePool;,
        Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;,
        Lcom/miui/widget/util/Pools$IInstanceHolder;,
        Lcom/miui/widget/util/Pools$Manager;,
        Lcom/miui/widget/util/Pools$Pool;
    }
.end annotation


# static fields
.field private static final mInstanceHolderMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/Object<",
            "*>;>;"
        }
    .end annotation
.end field

.field private static final mSoftReferenceInstanceHolderMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Class<",
            "*>;",
            "Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder<",
            "*>;>;"
        }
    .end annotation
.end field

.field private static final mStringBuilderPool:Lcom/miui/widget/util/Pools$Pool;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/widget/util/Pools$Pool<",
            "Ljava/lang/StringBuilder;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/widget/util/Pools;->mInstanceHolderMap:Ljava/util/HashMap;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/widget/util/Pools;->mSoftReferenceInstanceHolderMap:Ljava/util/HashMap;

    new-instance v0, Lcom/miui/widget/util/Pools$1;

    invoke-direct {v0}, Lcom/miui/widget/util/Pools$1;-><init>()V

    const/4 v1, 0x4

    invoke-static {v0, v1}, Lcom/miui/widget/util/Pools;->createSoftReferencePool(Lcom/miui/widget/util/Pools$Manager;I)Lcom/miui/widget/util/Pools$SoftReferencePool;

    move-result-object v0

    sput-object v0, Lcom/miui/widget/util/Pools;->mStringBuilderPool:Lcom/miui/widget/util/Pools$Pool;

    return-void
.end method

.method static synthetic access$100()Ljava/util/HashMap;
    .locals 1

    sget-object v0, Lcom/miui/widget/util/Pools;->mSoftReferenceInstanceHolderMap:Ljava/util/HashMap;

    return-object v0
.end method

.method public static createSoftReferencePool(Lcom/miui/widget/util/Pools$Manager;I)Lcom/miui/widget/util/Pools$SoftReferencePool;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lcom/miui/widget/util/Pools$Manager<",
            "TT;>;I)",
            "Lcom/miui/widget/util/Pools$SoftReferencePool<",
            "TT;>;"
        }
    .end annotation

    new-instance v0, Lcom/miui/widget/util/Pools$SoftReferencePool;

    invoke-direct {v0, p0, p1}, Lcom/miui/widget/util/Pools$SoftReferencePool;-><init>(Lcom/miui/widget/util/Pools$Manager;I)V

    return-object v0
.end method

.method public static getStringBuilderPool()Lcom/miui/widget/util/Pools$Pool;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/widget/util/Pools$Pool<",
            "Ljava/lang/StringBuilder;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/miui/widget/util/Pools;->mStringBuilderPool:Lcom/miui/widget/util/Pools$Pool;

    return-object v0
.end method

.method static onSoftReferencePoolClose(Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;I)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder<",
            "TT;>;I)V"
        }
    .end annotation

    sget-object v0, Lcom/miui/widget/util/Pools;->mSoftReferenceInstanceHolderMap:Ljava/util/HashMap;

    monitor-enter v0

    neg-int p1, p1

    :try_start_0
    invoke-virtual {p0, p1}, Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;->resize(I)V

    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method static onSoftReferencePoolCreate(Ljava/lang/Class;I)Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/Class<",
            "TT;>;I)",
            "Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder<",
            "TT;>;"
        }
    .end annotation

    sget-object v0, Lcom/miui/widget/util/Pools;->mSoftReferenceInstanceHolderMap:Ljava/util/HashMap;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/miui/widget/util/Pools;->mSoftReferenceInstanceHolderMap:Ljava/util/HashMap;

    invoke-virtual {v1, p0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;

    if-nez v1, :cond_0

    new-instance v1, Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;

    invoke-direct {v1, p0, p1}, Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;-><init>(Ljava/lang/Class;I)V

    sget-object p1, Lcom/miui/widget/util/Pools;->mSoftReferenceInstanceHolderMap:Ljava/util/HashMap;

    invoke-virtual {p1, p0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    :cond_0
    invoke-virtual {v1, p1}, Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;->resize(I)V

    :goto_0
    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
