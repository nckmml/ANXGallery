.class public Lcom/miui/gallery/util/RxUtils;
.super Ljava/lang/Object;
.source "RxUtils.java"


# direct methods
.method public static ioAndMainThread()Lio/reactivex/FlowableTransformer;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">()",
            "Lio/reactivex/FlowableTransformer<",
            "TT;TT;>;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/util/RxUtils$1;

    invoke-direct {v0}, Lcom/miui/gallery/util/RxUtils$1;-><init>()V

    return-object v0
.end method
