.class public Lcom/miui/gallery/provider/deprecated/GalleryCloudProvider$AlbumInfo;
.super Ljava/lang/Object;
.source "GalleryCloudProvider.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/deprecated/GalleryCloudProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xc
    name = "AlbumInfo"
.end annotation


# static fields
.field protected static final PROJECTION:[Ljava/lang/String;


# instance fields
.field public final mAlbumId:J

.field public final mAttributes:J


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-string v0, "_id"

    const-string v1, "attributes"

    filled-new-array {v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/deprecated/GalleryCloudProvider$AlbumInfo;->PROJECTION:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(JJ)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-wide p1, p0, Lcom/miui/gallery/provider/deprecated/GalleryCloudProvider$AlbumInfo;->mAlbumId:J

    iput-wide p3, p0, Lcom/miui/gallery/provider/deprecated/GalleryCloudProvider$AlbumInfo;->mAttributes:J

    return-void
.end method
