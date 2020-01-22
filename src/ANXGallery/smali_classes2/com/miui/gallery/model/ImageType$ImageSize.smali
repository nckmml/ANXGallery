.class Lcom/miui/gallery/model/ImageType$ImageSize;
.super Ljava/lang/Object;
.source "ImageType.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/model/ImageType;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "ImageSize"
.end annotation


# instance fields
.field private final height:I

.field private final width:I


# direct methods
.method public constructor <init>(II)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/miui/gallery/model/ImageType$ImageSize;->width:I

    iput p2, p0, Lcom/miui/gallery/model/ImageType$ImageSize;->height:I

    return-void
.end method


# virtual methods
.method public getHeight()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/model/ImageType$ImageSize;->height:I

    return v0
.end method

.method public getWidth()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/model/ImageType$ImageSize;->width:I

    return v0
.end method
