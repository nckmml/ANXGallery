.class public Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;
.super Ljava/lang/Object;
.source "EditPhotoDateTime.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "RequestBean"
.end annotation


# instance fields
.field private id:J

.field private isFavorite:Z

.field private newTime:J

.field private path:Ljava/lang/String;


# direct methods
.method public constructor <init>(JJ)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-wide p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->newTime:J

    iput-wide p3, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->id:J

    return-void
.end method


# virtual methods
.method public getId()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->id:J

    return-wide v0
.end method

.method public getNewTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->newTime:J

    return-wide v0
.end method

.method public getPath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->path:Ljava/lang/String;

    return-object v0
.end method

.method public isFavorite()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->isFavorite:Z

    return v0
.end method

.method public setFavorite(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->isFavorite:Z

    return-void
.end method
