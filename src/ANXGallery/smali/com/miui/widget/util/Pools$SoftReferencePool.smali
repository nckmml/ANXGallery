.class public Lcom/miui/widget/util/Pools$SoftReferencePool;
.super Lcom/miui/widget/util/Pools$BasePool;
.source "Pools.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/util/Pools;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "SoftReferencePool"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lcom/miui/widget/util/Pools$BasePool<",
        "TT;>;"
    }
.end annotation


# direct methods
.method constructor <init>(Lcom/miui/widget/util/Pools$Manager;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/widget/util/Pools$Manager<",
            "TT;>;I)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Lcom/miui/widget/util/Pools$BasePool;-><init>(Lcom/miui/widget/util/Pools$Manager;I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic acquire()Ljava/lang/Object;
    .locals 1

    invoke-super {p0}, Lcom/miui/widget/util/Pools$BasePool;->acquire()Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic close()V
    .locals 0

    invoke-super {p0}, Lcom/miui/widget/util/Pools$BasePool;->close()V

    return-void
.end method

.method final createInstanceHolder(Ljava/lang/Class;I)Lcom/miui/widget/util/Pools$IInstanceHolder;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "TT;>;I)",
            "Lcom/miui/widget/util/Pools$IInstanceHolder<",
            "TT;>;"
        }
    .end annotation

    invoke-static {p1, p2}, Lcom/miui/widget/util/Pools;->onSoftReferencePoolCreate(Ljava/lang/Class;I)Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;

    move-result-object p1

    return-object p1
.end method

.method final destroyInstanceHolder(Lcom/miui/widget/util/Pools$IInstanceHolder;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/widget/util/Pools$IInstanceHolder<",
            "TT;>;I)V"
        }
    .end annotation

    check-cast p1, Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;

    invoke-static {p1, p2}, Lcom/miui/widget/util/Pools;->onSoftReferencePoolClose(Lcom/miui/widget/util/Pools$SoftReferenceInstanceHolder;I)V

    return-void
.end method

.method public bridge synthetic release(Ljava/lang/Object;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/widget/util/Pools$BasePool;->release(Ljava/lang/Object;)V

    return-void
.end method
