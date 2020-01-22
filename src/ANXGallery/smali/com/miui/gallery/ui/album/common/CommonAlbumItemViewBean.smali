.class public Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;
.super Lcom/miui/gallery/ui/album/common/base/BaseViewBean;
.source "CommonAlbumItemViewBean.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<R:",
        "Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;",
        ">",
        "Lcom/miui/gallery/ui/album/common/base/BaseViewBean;"
    }
.end annotation


# instance fields
.field protected albumCover:Landroid/net/Uri;

.field protected albumCoverPath:Ljava/lang/String;

.field protected albumName:Ljava/lang/String;

.field protected albumPhotoCountText:Ljava/lang/String;

.field protected coverSize:J

.field private source:Lcom/miui/gallery/model/Album;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/common/base/BaseViewBean;-><init>()V

    return-void
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 6

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    const/4 v1, 0x0

    if-eqz p1, :cond_4

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    if-eq v2, v3, :cond_1

    goto :goto_1

    :cond_1
    invoke-super {p0, p1}, Lcom/miui/gallery/ui/album/common/base/BaseViewBean;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_2

    return v1

    :cond_2
    check-cast p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;

    iget-wide v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->coverSize:J

    iget-wide v4, p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->coverSize:J

    cmp-long v2, v2, v4

    if-nez v2, :cond_3

    iget-object v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumName:Ljava/lang/String;

    iget-object v3, p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumName:Ljava/lang/String;

    invoke-static {v2, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCover:Landroid/net/Uri;

    iget-object v3, p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCover:Landroid/net/Uri;

    invoke-static {v2, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCoverPath:Ljava/lang/String;

    iget-object v3, p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCoverPath:Ljava/lang/String;

    invoke-static {v2, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumPhotoCountText:Ljava/lang/String;

    iget-object v3, p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumPhotoCountText:Ljava/lang/String;

    invoke-static {v2, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->source:Lcom/miui/gallery/model/Album;

    invoke-virtual {v2}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    iget-object p1, p1, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->source:Lcom/miui/gallery/model/Album;

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-static {v2, p1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    goto :goto_0

    :cond_3
    move v0, v1

    :goto_0
    return v0

    :cond_4
    :goto_1
    return v1
.end method

.method public getAlbumCover()Landroid/net/Uri;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCover:Landroid/net/Uri;

    return-object v0
.end method

.method public getAlbumCoverPath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCoverPath:Ljava/lang/String;

    return-object v0
.end method

.method public getAlbumName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumName:Ljava/lang/String;

    return-object v0
.end method

.method public getCoverSize()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->coverSize:J

    return-wide v0
.end method

.method public getSource()Lcom/miui/gallery/model/Album;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->source:Lcom/miui/gallery/model/Album;

    return-object v0
.end method

.method public hashCode()I
    .locals 3

    const/4 v0, 0x6

    new-array v0, v0, [Ljava/lang/Object;

    iget-object v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumName:Ljava/lang/String;

    const/4 v2, 0x0

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCover:Landroid/net/Uri;

    const/4 v2, 0x1

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCoverPath:Ljava/lang/String;

    const/4 v2, 0x2

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->coverSize:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x3

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumPhotoCountText:Ljava/lang/String;

    const/4 v2, 0x4

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->source:Lcom/miui/gallery/model/Album;

    invoke-virtual {v1}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x5

    aput-object v1, v0, v2

    invoke-static {v0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result v0

    return v0
.end method

.method public mapping(Lcom/miui/gallery/model/Album;)V
    .locals 3

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->setId(J)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCoverPath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->setAlbumCoverPath(Ljava/lang/String;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getLocalizedAlbumName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->setAlbumName(Ljava/lang/String;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCoverSyncState()I

    move-result v0

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCoverId()J

    move-result-wide v1

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/model/Album;->getDownloadUri(IJ)Landroid/net/Uri;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->setAlbumCover(Landroid/net/Uri;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCoverSize()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->setCoverSize(J)V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->setSource(Lcom/miui/gallery/model/Album;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCount()I

    move-result p1

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->setAlbumPhotoCountText(Ljava/lang/String;)V

    return-void
.end method

.method public setAlbumCover(Landroid/net/Uri;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCover:Landroid/net/Uri;

    return-void
.end method

.method public setAlbumCoverPath(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCoverPath:Ljava/lang/String;

    return-void
.end method

.method public setAlbumName(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumName:Ljava/lang/String;

    return-void
.end method

.method public setAlbumPhotoCountText(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumPhotoCountText:Ljava/lang/String;

    return-void
.end method

.method public setCoverSize(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->coverSize:J

    return-void
.end method

.method public setSource(Lcom/miui/gallery/model/Album;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->source:Lcom/miui/gallery/model/Album;

    return-void
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "BaseViewBean{albumName=\'"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumName:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0x27

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    const-string v2, ", albumCover="

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCover:Landroid/net/Uri;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", albumCoverPath=\'"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->albumCoverPath:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    const-string v1, ", id="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->id:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, ", coverSize="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->coverSize:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, ", source="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->source:Lcom/miui/gallery/model/Album;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/16 v1, 0x7d

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
