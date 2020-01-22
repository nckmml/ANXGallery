.class public Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;
.super Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;
.source "CloudAlbumItemViewBean.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean<",
        "Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;",
        ">;"
    }
.end annotation


# instance fields
.field private isBabyAlbum:Z

.field private isBackup:Z

.field private photoNumberText:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;-><init>()V

    return-void
.end method


# virtual methods
.method public getPhotoNumberText()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->photoNumberText:Ljava/lang/String;

    return-object v0
.end method

.method public isBackup()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->isBackup:Z

    return v0
.end method

.method public mapping(Lcom/miui/gallery/model/Album;)V
    .locals 7

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/album/common/CommonAlbumItemViewBean;->mapping(Lcom/miui/gallery/model/Album;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->isOtherShareAlbum()Z

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    const v3, 0x7f0e0013

    if-nez v0, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->isOwnerShareAlbum()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCount()I

    move-result v0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCount()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v2, v1

    invoke-static {v3, v0, v2}, Lcom/miui/gallery/util/ResourceUtils;->getQuantityString(II[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->setPhotoNumberText(Ljava/lang/String;)V

    goto :goto_2

    :cond_1
    :goto_0
    invoke-static {p1}, Lcom/miui/gallery/provider/ShareAlbumManager;->getShareAlbumInfoTipTextIfNeed(Lcom/miui/gallery/model/Album;)Ljava/lang/String;

    move-result-object v0

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCount()I

    move-result v5

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->getCount()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v2, v1

    invoke-static {v3, v5, v2}, Lcom/miui/gallery/util/ResourceUtils;->getQuantityString(II[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_2

    const-string v0, ""

    goto :goto_1

    :cond_2
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, " | "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    :goto_1
    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->setPhotoNumberText(Ljava/lang/String;)V

    :goto_2
    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->isAutoUploadedAlbum()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->setBackup(Z)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/Album;->isBabyAlbum()Z

    move-result p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->setBabyAlbum(Z)V

    return-void
.end method

.method public setBabyAlbum(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->isBabyAlbum:Z

    return-void
.end method

.method public setBackup(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->isBackup:Z

    return-void
.end method

.method public setPhotoNumberText(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->photoNumberText:Ljava/lang/String;

    return-void
.end method
