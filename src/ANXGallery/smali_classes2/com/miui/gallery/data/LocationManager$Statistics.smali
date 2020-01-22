.class Lcom/miui/gallery/data/LocationManager$Statistics;
.super Ljava/lang/Object;
.source "LocationManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/data/LocationManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "Statistics"
.end annotation


# instance fields
.field public addressNull:I

.field public cacheDistance:I

.field public cacheHit:I

.field public cost:I

.field public count:I

.field public error:I

.field public latLngInvalid:I

.field public saveFail:I

.field private startTime:J

.field final synthetic this$0:Lcom/miui/gallery/data/LocationManager;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/data/LocationManager;)V
    .locals 2

    iput-object p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->this$0:Lcom/miui/gallery/data/LocationManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->count:I

    iput p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->cacheHit:I

    iput p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->cost:I

    iput p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->error:I

    iput p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->latLngInvalid:I

    iput p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->addressNull:I

    iput p1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->saveFail:I

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->startTime:J

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/data/LocationManager;Lcom/miui/gallery/data/LocationManager$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/data/LocationManager$Statistics;-><init>(Lcom/miui/gallery/data/LocationManager;)V

    return-void
.end method


# virtual methods
.method public getAverageCost()I
    .locals 2

    iget v0, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->count:I

    iget v1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->cacheHit:I

    sub-int/2addr v0, v1

    iget v1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->error:I

    sub-int/2addr v0, v1

    if-lez v0, :cond_0

    iget v1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->cost:I

    if-lez v1, :cond_0

    div-int/2addr v1, v0

    return v1

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getAvgCacheDistance()I
    .locals 2

    iget v0, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->cacheDistance:I

    if-lez v0, :cond_0

    iget v1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->cacheHit:I

    if-lez v1, :cond_0

    div-int/2addr v0, v1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getCacheHitPercent()I
    .locals 3

    iget v0, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->count:I

    if-lez v0, :cond_0

    iget v1, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->cacheHit:I

    if-gt v1, v0, :cond_0

    const/high16 v2, 0x42c80000    # 100.0f

    int-to-float v1, v1

    mul-float/2addr v1, v2

    int-to-float v0, v0

    div-float/2addr v1, v0

    float-to-int v0, v1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getTotalTime()I
    .locals 4

    iget-wide v0, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->startTime:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->startTime:J

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x3e8

    div-long/2addr v0, v2

    long-to-int v0, v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public start()V
    .locals 2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/data/LocationManager$Statistics;->startTime:J

    return-void
.end method
