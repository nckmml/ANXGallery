.class public Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;
.super Ljava/lang/Object;
.source "PhotoDetailViewBean.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field private cacheLocation:Ljava/lang/String;

.field private dateText:Ljava/lang/String;

.field private dateTime:J

.field private displayFilePath:Ljava/lang/String;

.field private fileName:Ljava/lang/String;

.field private fileSize:J

.field private fileSizeText:Ljava/lang/String;

.field private id:J

.field private isFile:Z

.field private location:[D

.field private locationText:Ljava/lang/String;

.field private notDownLoad:Ljava/lang/String;

.field private phoneModel:Ljava/lang/String;

.field private takenParam:Ljava/lang/String;

.field private timeText:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private genAperture(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    const-string v0, "0+?$"

    const-string v1, ""

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "."

    invoke-virtual {p1, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "0"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "f/"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private genExposureTime(Ljava/lang/String;)Ljava/lang/String;
    .locals 9

    :try_start_0
    invoke-static {p1}, Ljava/lang/Double;->valueOf(Ljava/lang/String;)Ljava/lang/Double;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Double;->doubleValue()D

    move-result-wide v0

    const-wide/high16 v2, 0x3ff0000000000000L    # 1.0

    cmpg-double v4, v0, v2

    const/4 v5, 0x0

    const/4 v6, 0x1

    if-gez v4, :cond_0

    sget-object v4, Ljava/util/Locale;->US:Ljava/util/Locale;

    const-string v7, "1/%d"

    new-array v6, v6, [Ljava/lang/Object;

    div-double/2addr v2, v0

    invoke-static {v2, v3}, Ljava/lang/Math;->round(D)J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    aput-object v0, v6, v5

    invoke-static {v4, v7, v6}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    double-to-int v4, v0

    int-to-double v7, v4

    sub-double/2addr v0, v7

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {v4}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "\'\'"

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-wide v7, 0x3f1a36e2eb1c432dL    # 1.0E-4

    cmpl-double v4, v0, v7

    if-lez v4, :cond_1

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Ljava/util/Locale;->US:Ljava/util/Locale;

    const-string v8, " 1/%d"

    new-array v6, v6, [Ljava/lang/Object;

    div-double/2addr v2, v0

    invoke-static {v2, v3}, Ljava/lang/Math;->round(D)J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    aput-object v0, v6, v5

    invoke-static {v7, v8, v6}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    invoke-virtual {v0}, Ljava/lang/NumberFormatException;->printStackTrace()V

    :cond_1
    :goto_0
    return-object p1
.end method

.method private genFileSizeInfo(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;
    .locals 6

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getFileSize()Ljava/lang/Long;

    move-result-object v1

    const-string v2, "    "

    if-eqz v1, :cond_0

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v4

    invoke-static {v3, v4, v5}, Lcom/miui/gallery/util/FormatUtil;->formatFileSize(Landroid/content/Context;J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getFileWidthAndHeight()[Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getOrientation()Ljava/lang/Integer;

    move-result-object v3

    if-eqz v1, :cond_1

    const/4 v4, 0x0

    aget-object v4, v1, v4

    const/4 v5, 0x1

    aget-object v1, v1, v5

    invoke-direct {p0, v4, v1, v3}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genPixels(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getFileDuataion()Ljava/lang/Integer;

    move-result-object p1

    if-eqz p1, :cond_2

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-static {v1, p1}, Lcom/miui/gallery/util/FormatUtil;->formatDuration(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    return-object p1
.end method

.method private genFlashFired(I)Ljava/lang/String;
    .locals 2

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/model/PhotoDetailInfo$FlashState;

    invoke-direct {v1, p1}, Lcom/miui/gallery/model/PhotoDetailInfo$FlashState;-><init>(I)V

    invoke-virtual {v1}, Lcom/miui/gallery/model/PhotoDetailInfo$FlashState;->isFlashFired()Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x7f10049e

    goto :goto_0

    :cond_0
    const p1, 0x7f10049d

    :goto_0
    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private genFocalLength(Ljava/lang/Double;)Ljava/lang/String;
    .locals 1

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, "mm"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private genLocationText([D)Ljava/lang/String;
    .locals 6

    if-eqz p1, :cond_1

    const/4 v0, 0x0

    aget-wide v1, p1, v0

    const/4 v3, 0x1

    aget-wide v4, p1, v3

    invoke-static {v1, v2, v4, v5}, Lcom/miui/gallery/data/LocationUtil;->isValidateCoordinate(DD)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    aget-wide v4, p1, v0

    invoke-virtual {v1, v4, v5}, Ljava/lang/StringBuilder;->append(D)Ljava/lang/StringBuilder;

    const-string v0, ", "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    aget-wide v2, p1, v3

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(D)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_1
    :goto_0
    const/4 p1, 0x0

    return-object p1
.end method

.method private genPixels(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;
    .locals 3

    const-string v0, "px"

    const-string v1, "x"

    if-eqz p3, :cond_1

    :try_start_0
    invoke-virtual {p3}, Ljava/lang/Integer;->intValue()I

    move-result p3

    invoke-static {p3}, Lcom/miui/gallery/util/ExifUtil;->exifOrientationToDegrees(I)I

    move-result p3

    const/16 v2, 0x5a

    if-eq p3, v2, :cond_0

    const/16 v2, 0x10e

    if-ne p3, v2, :cond_1

    :cond_0
    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p1

    :catch_0
    move-exception p3

    const-string v2, "PhotoDetailViewBean"

    invoke-static {v2, p3}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_1
    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private genTimeMainTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;
    .locals 1

    const/16 v0, 0x380

    invoke-virtual {p1, v0}, Lcom/miui/gallery/model/PhotoDetailInfo;->getTakenTime(I)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private genTimeSubTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;
    .locals 3

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getTakenTime()Ljava/lang/Long;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    const/16 v1, 0x400

    invoke-virtual {p1, v1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getTakenTime(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0x2c

    const-string v2, "    "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getTakenTime(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    return-object p1
.end method


# virtual methods
.method public genTakenMainTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;
    .locals 2

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getModel()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getMake()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_1

    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    return-object p1
.end method

.method public genTakenSubTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;
    .locals 3

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getAperture()Ljava/lang/String;

    move-result-object v1

    const-string v2, "    "

    if-eqz v1, :cond_0

    invoke-direct {p0, v1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genAperture(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getExposureTime()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_1

    invoke-direct {p0, v1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genExposureTime(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getISO()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_2

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    return-object p1
.end method

.method public genTakenThirdTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;
    .locals 2

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getFocalLength()Ljava/lang/Double;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-direct {p0, v1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genFocalLength(Ljava/lang/Double;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "    "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getFlashStatus()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_1

    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genFlashFired(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    return-object p1
.end method

.method public getCacheLocation()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->cacheLocation:Ljava/lang/String;

    return-object v0
.end method

.method public getDateText()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->dateText:Ljava/lang/String;

    return-object v0
.end method

.method public getDateTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->dateTime:J

    return-wide v0
.end method

.method public getDisplayFilePath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->displayFilePath:Ljava/lang/String;

    return-object v0
.end method

.method public getFileName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->fileName:Ljava/lang/String;

    return-object v0
.end method

.method public getFileSizeText()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->fileSizeText:Ljava/lang/String;

    return-object v0
.end method

.method public getLocation()[D
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->location:[D

    return-object v0
.end method

.method public getLocationText()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->locationText:Ljava/lang/String;

    return-object v0
.end method

.method public getNotDownLoad()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->notDownLoad:Ljava/lang/String;

    return-object v0
.end method

.method public getPhoneModel()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->phoneModel:Ljava/lang/String;

    return-object v0
.end method

.method public getTakenParam()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->takenParam:Ljava/lang/String;

    return-object v0
.end method

.method public getTimeText()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->timeText:Ljava/lang/String;

    return-object v0
.end method

.method public isFile()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isFile:Z

    return v0
.end method

.method public isHaveCacheLocation()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getCacheLocation()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public isHaveDateTime()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getDateText()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isHaveDownLoadTip()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getNotDownLoad()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public isHaveFileInfo()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getFileSizeText()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public isHaveFilePath()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getDisplayFilePath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public isHaveLocation()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getLocation()[D

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isHaveTakenParams()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getTakenParam()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public mapping(Lcom/miui/gallery/model/PhotoDetailInfo;)V
    .locals 4

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genTimeMainTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setDateText(Ljava/lang/String;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genTimeSubTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setTimeText(Ljava/lang/String;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getTakenTime()Ljava/lang/Long;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setDateTime(J)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getFileName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setFileName(Ljava/lang/String;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genFileSizeInfo(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setFileSizeText(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genTakenMainTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setPhoneModel(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genTakenSubTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genTakenThirdTitle(Lcom/miui/gallery/model/PhotoDetailInfo;)Ljava/lang/String;

    move-result-object v1

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v2

    invoke-interface {v2}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/StringBuilder;

    invoke-static {v0}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v3

    if-nez v3, :cond_1

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    invoke-static {v1}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_3

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->length()I

    move-result v0

    if-lez v0, :cond_2

    const-string v0, "\n"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setTakenParam(Ljava/lang/String;)V

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0, v2}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getFileLocalPath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setDisplayFilePath(Ljava/lang/String;)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getLocation()[D

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setLocation([D)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->isFilePath()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setFile(Z)V

    invoke-virtual {p1}, Lcom/miui/gallery/model/PhotoDetailInfo;->getCacheLocation()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->setCacheLocation(Ljava/lang/String;)V

    return-void
.end method

.method public setCacheLocation(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->cacheLocation:Ljava/lang/String;

    return-void
.end method

.method public setDateText(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->dateText:Ljava/lang/String;

    return-void
.end method

.method public setDateTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->dateTime:J

    return-void
.end method

.method public setDisplayFilePath(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->displayFilePath:Ljava/lang/String;

    return-void
.end method

.method public setFile(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isFile:Z

    return-void
.end method

.method public setFileName(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->fileName:Ljava/lang/String;

    return-void
.end method

.method public setFileSize(J)V
    .locals 2

    iput-wide p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->fileSize:J

    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-lez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->fileSizeText:Ljava/lang/String;

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1, p1, p2}, Lcom/miui/gallery/util/FormatUtil;->formatFileSize(Landroid/content/Context;J)Ljava/lang/String;

    move-result-object p1

    const-string p2, "[\\s\\S]+[K|k|M|m][B|b]"

    invoke-virtual {v0, p2, p1}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->fileSizeText:Ljava/lang/String;

    :cond_0
    return-void
.end method

.method public setFileSizeText(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->fileSizeText:Ljava/lang/String;

    return-void
.end method

.method public setId(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->id:J

    return-void
.end method

.method public setLocation([D)V
    .locals 0

    if-eqz p1, :cond_0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->location:[D

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->location:[D

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->genLocationText([D)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->locationText:Ljava/lang/String;

    :cond_0
    return-void
.end method

.method public setPhoneModel(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->phoneModel:Ljava/lang/String;

    return-void
.end method

.method public setTakenParam(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->takenParam:Ljava/lang/String;

    return-void
.end method

.method public setTimeText(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->timeText:Ljava/lang/String;

    return-void
.end method
