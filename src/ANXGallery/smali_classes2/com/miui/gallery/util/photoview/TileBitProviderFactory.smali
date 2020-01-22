.class public Lcom/miui/gallery/util/photoview/TileBitProviderFactory;
.super Ljava/lang/Object;
.source "TileBitProviderFactory.java"


# direct methods
.method public static create(Ljava/lang/String;II[B)Lcom/miui/gallery/util/photoview/TileBitProvider;
    .locals 2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-static {p1, p2}, Lcom/miui/gallery/model/ImageType;->of(II)Lcom/miui/gallery/model/ImageType;

    move-result-object p1

    sget-object p2, Lcom/miui/gallery/model/ImageType;->HIGH_RESOLUTION_108M:Lcom/miui/gallery/model/ImageType;

    if-ne p1, p2, :cond_0

    new-instance p1, Lcom/miui/gallery/util/photoview/ParallelTileBitProvider;

    const/4 p2, 0x3

    invoke-direct {p1, p0, p3, p2}, Lcom/miui/gallery/util/photoview/ParallelTileBitProvider;-><init>(Ljava/lang/String;[BI)V

    goto :goto_0

    :cond_0
    new-instance p1, Lcom/miui/gallery/util/photoview/TileBitProviderRegion;

    invoke-direct {p1, p0, p3}, Lcom/miui/gallery/util/photoview/TileBitProviderRegion;-><init>(Ljava/lang/String;[B)V

    :goto_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p2

    sub-long/2addr p2, v0

    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    const-string p2, "TileBitProviderFactory"

    const-string p3, "create tile provider costs: %d"

    invoke-static {p2, p3, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-object p1
.end method
