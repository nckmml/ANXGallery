.class public Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;
.super Ljava/lang/Object;
.source "BigBitmapLoadUtils.java"


# static fields
.field private static final sSpecialWhiteList:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-string v0, "davinci"

    const-string v1, "davinciin"

    filled-new-array {v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->sSpecialWhiteList:[Ljava/lang/String;

    return-void
.end method

.method public static calculateInSampleSize(Landroid/content/Context;ZII)I
    .locals 10

    int-to-long v0, p2

    int-to-long v2, p3

    mul-long/2addr v0, v2

    const-wide/16 v2, 0x4

    mul-long/2addr v0, v2

    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v3, 0x1

    const/16 v4, 0x1a

    if-lt v2, v4, :cond_4

    const-wide/32 v4, 0x5b8d800

    cmp-long v2, v0, v4

    if-lez v2, :cond_3

    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->isMemoryReach6G(Landroid/content/Context;)Z

    move-result v2

    const-wide/32 v6, 0xf7f4900

    if-eqz v2, :cond_0

    if-eqz p1, :cond_2

    const-wide/32 v6, 0x19fcd500

    goto :goto_0

    :cond_0
    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->isMemoryBeyond3G(Landroid/content/Context;)Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->isBigMemoryApp(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_1

    goto :goto_0

    :cond_1
    move-wide v6, v4

    :cond_2
    :goto_0
    move p0, v3

    :goto_1
    int-to-long v4, p0

    div-long v8, v0, v4

    div-long/2addr v8, v4

    cmp-long p1, v6, v8

    if-gez p1, :cond_6

    mul-int/lit8 p0, p0, 0x2

    goto :goto_1

    :cond_3
    move p0, v3

    goto :goto_3

    :cond_4
    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->getMaxMemory(Landroid/content/Context;)I

    move-result p0

    int-to-long p0, p0

    const-wide/16 v4, 0x400

    mul-long/2addr p0, v4

    mul-long/2addr p0, v4

    const-wide/16 v4, 0x3

    div-long/2addr p0, v4

    move v2, v3

    :goto_2
    int-to-long v4, v2

    div-long v6, v0, v4

    div-long/2addr v6, v4

    cmp-long v4, p0, v6

    if-gez v4, :cond_5

    mul-int/lit8 v2, v2, 0x2

    goto :goto_2

    :cond_5
    move p0, v2

    :cond_6
    :goto_3
    if-eq p0, v3, :cond_7

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string v0, "BigBitmapLoadUtils"

    const-string v1, "decoding original bitmap,inSampleSize:%d"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance p1, Ljava/util/HashMap;

    invoke-direct {p1}, Ljava/util/HashMap;-><init>()V

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "inSampleSize"

    invoke-virtual {p1, v1, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    div-int/lit16 p2, p2, 0x3e8

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    aput-object p2, v0, v1

    div-int/lit16 p3, p3, 0x3e8

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    aput-object p2, v0, v3

    const-string p2, "%dk*%dk"

    invoke-static {p2, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    const-string p3, "size"

    invoke-virtual {p1, p3, p2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p2, Landroid/os/Build;->MODEL:Ljava/lang/String;

    const-string p3, "model"

    invoke-virtual {p1, p3, p2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p2, "photo_editor"

    const-string p3, "decode_resize"

    invoke-static {p2, p3, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    :cond_7
    return p0
.end method

.method public static getMaxMemory(Landroid/content/Context;)I
    .locals 3

    const-string v0, "activity"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/app/ActivityManager;

    invoke-virtual {p0}, Landroid/app/ActivityManager;->getLargeMemoryClass()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "BigBitmapLoadUtils"

    const-string v2, "max memory:%dM"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return p0
.end method

.method private static getPhoneTotalMem(Landroid/content/Context;)J
    .locals 3

    const-string v0, "activity"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/app/ActivityManager;

    new-instance v0, Landroid/app/ActivityManager$MemoryInfo;

    invoke-direct {v0}, Landroid/app/ActivityManager$MemoryInfo;-><init>()V

    invoke-virtual {p0, v0}, Landroid/app/ActivityManager;->getMemoryInfo(Landroid/app/ActivityManager$MemoryInfo;)V

    iget-wide v1, v0, Landroid/app/ActivityManager$MemoryInfo;->totalMem:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    const-string v1, "BigBitmapLoadUtils"

    const-string v2, "phone total memory:%d"

    invoke-static {v1, v2, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-wide v0, v0, Landroid/app/ActivityManager$MemoryInfo;->totalMem:J

    return-wide v0
.end method

.method public static isBigMemoryApp(Landroid/content/Context;)Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->isSpecialBigMemoryDevices()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->getMaxMemory(Landroid/content/Context;)I

    move-result p0

    const/16 v0, 0x200

    if-lt p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p0, 0x1

    :goto_1
    return p0
.end method

.method public static isBitmapNeedCompress(Landroid/content/Context;II)Z
    .locals 4

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v1, 0x0

    const/16 v2, 0x1a

    if-lt v0, v2, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->isMemoryReach6G(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_0

    int-to-long p0, p1

    int-to-long v2, p2

    mul-long/2addr p0, v2

    const-wide/16 v2, 0x4

    mul-long/2addr p0, v2

    const-wide/32 v2, 0xf7f4900

    cmp-long p2, p0, v2

    if-lez p2, :cond_0

    const-wide/32 v2, 0x19fcd500

    cmp-long p0, p0, v2

    if-gtz p0, :cond_0

    const/4 v1, 0x1

    :cond_0
    return v1
.end method

.method private static isMemoryBeyond3G(Landroid/content/Context;)Z
    .locals 4

    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->getPhoneTotalMem(Landroid/content/Context;)J

    move-result-wide v0

    const-wide v2, 0xccccccccL

    cmp-long p0, v0, v2

    if-lez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method private static isMemoryReach6G(Landroid/content/Context;)Z
    .locals 4

    invoke-static {p0}, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->getPhoneTotalMem(Landroid/content/Context;)J

    move-result-wide v0

    const-wide v2, 0x153333333L

    cmp-long p0, v0, v2

    if-lez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isSpecialBigMemoryDevices()Z
    .locals 6

    sget-object v0, Lcom/miui/gallery/editor/photo/utils/BigBitmapLoadUtils;->sSpecialWhiteList:[Ljava/lang/String;

    array-length v1, v0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_1

    aget-object v4, v0, v3

    sget-object v5, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v4, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    return v2
.end method
