.class public Lcom/miui/gallery/movie/net/AudioResourceRequest;
.super Lcom/miui/gallery/movie/net/LocalResourceRequest;
.source "AudioResourceRequest.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/net/LocalResourceRequest;-><init>()V

    return-void
.end method


# virtual methods
.method protected getParentId()J
    .locals 2

    invoke-static {}, Lcom/miui/gallery/movie/MovieConfig;->isUserXmSdk()Z

    move-result v0

    if-eqz v0, :cond_0

    const-wide v0, 0x2b56061e390040L

    goto :goto_0

    :cond_0
    const-wide v0, 0x24083aabd00040L

    :goto_0
    return-wide v0
.end method

.method protected newLocalResource()Lcom/miui/gallery/movie/entity/MovieResource;
    .locals 1

    new-instance v0, Lcom/miui/gallery/movie/entity/AudioResource;

    invoke-direct {v0}, Lcom/miui/gallery/movie/entity/AudioResource;-><init>()V

    return-object v0
.end method
