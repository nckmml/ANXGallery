.class public Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;
.super Ljava/lang/Object;
.source "BaseOperationAlbumRequestBean.java"


# instance fields
.field private albumId:J

.field private enable:Z


# direct methods
.method public constructor <init>(JZ)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-wide p1, p0, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;->albumId:J

    iput-boolean p3, p0, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;->enable:Z

    return-void
.end method


# virtual methods
.method public getAlbumId()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;->albumId:J

    return-wide v0
.end method

.method public isEnable()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/ui/album/common/base/requestbean/BaseOperationAlbumRequestBean;->enable:Z

    return v0
.end method
