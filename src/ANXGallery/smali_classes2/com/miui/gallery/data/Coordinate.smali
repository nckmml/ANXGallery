.class Lcom/miui/gallery/data/Coordinate;
.super Ljava/lang/Object;
.source "Coordinate.java"


# instance fields
.field public latitude:D

.field public longitude:D


# direct methods
.method public constructor <init>(DD)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-wide p1, p0, Lcom/miui/gallery/data/Coordinate;->latitude:D

    iput-wide p3, p0, Lcom/miui/gallery/data/Coordinate;->longitude:D

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {p1, p2}, Lcom/miui/gallery/data/LocationUtil;->convertRationalLatLonToDouble(Ljava/lang/String;Ljava/lang/String;)D

    move-result-wide p1

    iput-wide p1, p0, Lcom/miui/gallery/data/Coordinate;->latitude:D

    invoke-static {p3, p4}, Lcom/miui/gallery/data/LocationUtil;->convertRationalLatLonToDouble(Ljava/lang/String;Ljava/lang/String;)D

    move-result-wide p1

    iput-wide p1, p0, Lcom/miui/gallery/data/Coordinate;->longitude:D

    return-void
.end method


# virtual methods
.method public isValid()Z
    .locals 4

    iget-wide v0, p0, Lcom/miui/gallery/data/Coordinate;->latitude:D

    iget-wide v2, p0, Lcom/miui/gallery/data/Coordinate;->longitude:D

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/data/LocationUtil;->isValidateCoordinate(DD)Z

    move-result v0

    return v0
.end method
