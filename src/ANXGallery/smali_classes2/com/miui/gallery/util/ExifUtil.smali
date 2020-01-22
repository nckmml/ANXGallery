.class public Lcom/miui/gallery/util/ExifUtil;
.super Ljava/lang/Object;
.source "ExifUtil.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/util/ExifUtil$UserCommentData;,
        Lcom/miui/gallery/util/ExifUtil$ExifInfo;,
        Lcom/miui/gallery/util/ExifUtil$ExifCreator;
    }
.end annotation


# static fields
.field public static final sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/ExifUtil$ExifCreator<",
            "Lcom/miui/gallery3d/exif/ExifInterface;",
            ">;"
        }
    .end annotation
.end field

.field public static final sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/ExifUtil$ExifCreator<",
            "Landroid/media/ExifInterface;",
            ">;"
        }
    .end annotation
.end field

.field public static final sSupportExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/ExifUtil$ExifCreator<",
            "Landroidx/exifinterface/media/ExifInterface;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/util/ExifUtil$1;

    invoke-direct {v0}, Lcom/miui/gallery/util/ExifUtil$1;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/ExifUtil;->sSupportExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    new-instance v0, Lcom/miui/gallery/util/ExifUtil$2;

    invoke-direct {v0}, Lcom/miui/gallery/util/ExifUtil$2;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    new-instance v0, Lcom/miui/gallery/util/ExifUtil$3;

    invoke-direct {v0}, Lcom/miui/gallery/util/ExifUtil$3;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/ExifUtil;->sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    return-void
.end method

.method public static adjustRectOrientation(IILandroid/graphics/Rect;IZ)Landroid/graphics/Rect;
    .locals 1

    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0, p2}, Landroid/graphics/RectF;-><init>(Landroid/graphics/Rect;)V

    invoke-static {p0, p1, v0, p3, p4}, Lcom/miui/gallery/util/ExifUtil;->adjustRectOrientation(IILandroid/graphics/RectF;IZ)Landroid/graphics/RectF;

    move-result-object p0

    new-instance p1, Landroid/graphics/Rect;

    iget p2, p0, Landroid/graphics/RectF;->left:F

    float-to-int p2, p2

    iget p3, p0, Landroid/graphics/RectF;->top:F

    float-to-int p3, p3

    iget p4, p0, Landroid/graphics/RectF;->right:F

    float-to-int p4, p4

    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    float-to-int p0, p0

    invoke-direct {p1, p2, p3, p4, p0}, Landroid/graphics/Rect;-><init>(IIII)V

    return-object p1
.end method

.method public static adjustRectOrientation(IILandroid/graphics/RectF;IZ)Landroid/graphics/RectF;
    .locals 8

    iget v0, p2, Landroid/graphics/RectF;->left:F

    iget v1, p2, Landroid/graphics/RectF;->top:F

    invoke-virtual {p2}, Landroid/graphics/RectF;->width()F

    move-result v2

    invoke-virtual {p2}, Landroid/graphics/RectF;->height()F

    move-result v3

    const/16 v4, 0x8

    const/4 v5, 0x7

    const/4 v6, 0x6

    const/4 v7, 0x5

    if-nez p4, :cond_4

    if-eq p3, v7, :cond_3

    if-eq p3, v6, :cond_2

    if-eq p3, v5, :cond_1

    if-eq p3, v4, :cond_0

    goto :goto_0

    :cond_0
    move p3, v6

    goto :goto_0

    :cond_1
    move p3, v7

    goto :goto_0

    :cond_2
    move p3, v4

    goto :goto_0

    :cond_3
    move p3, v5

    :cond_4
    :goto_0
    const/4 p4, 0x0

    const/4 v4, 0x1

    packed-switch p3, :pswitch_data_0

    goto :goto_2

    :pswitch_0
    move p4, v4

    :pswitch_1
    int-to-float p0, p1

    iget p3, p2, Landroid/graphics/RectF;->top:F

    sub-float/2addr p0, p3

    invoke-virtual {p2}, Landroid/graphics/RectF;->height()F

    move-result p3

    sub-float v0, p0, p3

    iget v1, p2, Landroid/graphics/RectF;->left:F

    invoke-virtual {p2}, Landroid/graphics/RectF;->height()F

    move-result v2

    invoke-virtual {p2}, Landroid/graphics/RectF;->width()F

    move-result v3

    :goto_1
    move p0, p1

    goto :goto_2

    :pswitch_2
    move p4, v4

    :pswitch_3
    iget v0, p2, Landroid/graphics/RectF;->top:F

    int-to-float p0, p0

    iget p3, p2, Landroid/graphics/RectF;->left:F

    sub-float/2addr p0, p3

    invoke-virtual {p2}, Landroid/graphics/RectF;->width()F

    move-result p3

    sub-float v1, p0, p3

    invoke-virtual {p2}, Landroid/graphics/RectF;->height()F

    move-result v2

    invoke-virtual {p2}, Landroid/graphics/RectF;->width()F

    move-result v3

    goto :goto_1

    :pswitch_4
    move p4, v4

    :pswitch_5
    int-to-float p3, p0

    iget v0, p2, Landroid/graphics/RectF;->left:F

    sub-float/2addr p3, v0

    invoke-virtual {p2}, Landroid/graphics/RectF;->width()F

    move-result v0

    sub-float v0, p3, v0

    int-to-float p1, p1

    iget p3, p2, Landroid/graphics/RectF;->top:F

    sub-float/2addr p1, p3

    invoke-virtual {p2}, Landroid/graphics/RectF;->height()F

    move-result p2

    sub-float v1, p1, p2

    goto :goto_2

    :pswitch_6
    move p4, v4

    :goto_2
    :pswitch_7
    if-eqz p4, :cond_5

    int-to-float p0, p0

    sub-float/2addr p0, v0

    sub-float v0, p0, v2

    :cond_5
    new-instance p0, Landroid/graphics/RectF;

    add-float/2addr v2, v0

    add-float/2addr v3, v1

    invoke-direct {p0, v0, v1, v2, v3}, Landroid/graphics/RectF;-><init>(FFFF)V

    return-object p0

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method

.method public static createExifInterface(Ljava/io/InputStream;Lcom/miui/gallery/util/ExifUtil$ExifCreator;)Ljava/lang/Object;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/io/InputStream;",
            "Lcom/miui/gallery/util/ExifUtil$ExifCreator<",
            "TT;>;)TT;"
        }
    .end annotation

    const/4 v0, 0x0

    if-eqz p1, :cond_2

    if-nez p0, :cond_0

    goto :goto_3

    :cond_0
    :try_start_0
    invoke-interface {p1, p0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/io/InputStream;)Ljava/lang/Object;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/util/BaseMiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-object p1

    :cond_1
    :goto_0
    invoke-static {p0}, Lcom/miui/gallery/util/BaseMiscUtil;->closeSilently(Ljava/io/Closeable;)V

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception p1

    :try_start_1
    const-string v1, "ExifUtil"

    const-string v2, "Can\'t read EXIF tags from file "

    invoke-static {v1, v2, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :goto_1
    return-object v0

    :goto_2
    invoke-static {p0}, Lcom/miui/gallery/util/BaseMiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw p1

    :cond_2
    :goto_3
    return-object v0
.end method

.method public static createExifInterface(Ljava/lang/String;[BLcom/miui/gallery/util/ExifUtil$ExifCreator;)Ljava/lang/Object;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/String;",
            "[B",
            "Lcom/miui/gallery/util/ExifUtil$ExifCreator<",
            "TT;>;)TT;"
        }
    .end annotation

    const/4 v0, 0x0

    if-eqz p2, :cond_3

    invoke-static {p0}, Lcom/miui/gallery/util/BaseFileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_4

    :cond_0
    :try_start_0
    invoke-static {p0, p1}, Lcom/miui/gallery/util/BaseBitmapUtils;->createInputStream(Ljava/lang/String;[B)Ljava/io/InputStream;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    :try_start_1
    invoke-interface {p2, v1}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/io/InputStream;)Ljava/lang/Object;

    move-result-object v2
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v2, :cond_1

    invoke-static {v1}, Lcom/miui/gallery/util/BaseMiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-object v2

    :cond_1
    :goto_0
    invoke-static {v1}, Lcom/miui/gallery/util/BaseMiscUtil;->closeSilently(Ljava/io/Closeable;)V

    goto :goto_2

    :catchall_0
    move-exception p0

    goto :goto_3

    :catch_0
    move-exception v2

    goto :goto_1

    :catchall_1
    move-exception p0

    move-object v1, v0

    goto :goto_3

    :catch_1
    move-exception v2

    move-object v1, v0

    :goto_1
    :try_start_2
    const-string v3, "ExifUtil"

    const-string v4, "Can\'t read EXIF tags from file [%s] %s"

    invoke-static {v3, v4, p0, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_0

    :goto_2
    if-eqz p1, :cond_2

    invoke-static {p0, p1, p2}, Lcom/miui/gallery/util/ExifUtil;->createExifInterfaceByDecryptFile(Ljava/lang/String;[BLcom/miui/gallery/util/ExifUtil$ExifCreator;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_2
    return-object v0

    :goto_3
    invoke-static {v1}, Lcom/miui/gallery/util/BaseMiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw p0

    :cond_3
    :goto_4
    return-object v0
.end method

.method public static createExifInterfaceByDecryptFile(Ljava/lang/String;[BLcom/miui/gallery/util/ExifUtil$ExifCreator;)Ljava/lang/Object;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/String;",
            "[B",
            "Lcom/miui/gallery/util/ExifUtil$ExifCreator<",
            "TT;>;)TT;"
        }
    .end annotation

    const-string v0, "ExifUtil"

    const/4 v1, 0x0

    if-eqz p2, :cond_4

    invoke-static {p0}, Lcom/miui/gallery/util/BaseFileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_0

    goto :goto_2

    :cond_0
    :try_start_0
    new-instance v2, Ljava/io/File;

    invoke-static {}, Lcom/miui/gallery/util/StaticContext;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v3

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v4

    invoke-static {v4, v5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    invoke-direct {v2, v3, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :try_start_1
    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v3

    invoke-static {p0, v3, p1}, Lcom/miui/gallery/util/CryptoUtil;->decryptFile(Ljava/lang/String;Ljava/lang/String;[B)Z

    move-result p1

    if-eqz p1, :cond_1

    const-string p1, "create exif by decrypt file"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p1

    invoke-interface {p2, p1}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    invoke-static {v2}, Lcom/miui/gallery/util/BaseFileUtils;->deleteFile(Ljava/io/File;)Z

    return-object p0

    :catch_0
    move-exception p1

    goto :goto_0

    :catchall_0
    move-exception p0

    move-object v2, v1

    goto :goto_1

    :catch_1
    move-exception p1

    move-object v2, v1

    :goto_0
    :try_start_2
    const-string p2, "Can\'t read EXIF tags from file [%s] %s"

    invoke-static {v0, p2, p0, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    if-eqz v2, :cond_2

    :cond_1
    invoke-static {v2}, Lcom/miui/gallery/util/BaseFileUtils;->deleteFile(Ljava/io/File;)Z

    :cond_2
    return-object v1

    :catchall_1
    move-exception p0

    :goto_1
    if-eqz v2, :cond_3

    invoke-static {v2}, Lcom/miui/gallery/util/BaseFileUtils;->deleteFile(Ljava/io/File;)Z

    :cond_3
    throw p0

    :cond_4
    :goto_2
    return-object v1
.end method

.method public static exifOrientationToDegrees(I)I
    .locals 1

    const/4 v0, 0x3

    if-eq p0, v0, :cond_2

    const/4 v0, 0x6

    if-eq p0, v0, :cond_1

    const/16 v0, 0x8

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const/16 p0, 0x10e

    return p0

    :cond_1
    const/16 p0, 0x5a

    return p0

    :cond_2
    const/16 p0, 0xb4

    return p0
.end method

.method public static getDateTime(Landroid/media/ExifInterface;)J
    .locals 2
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    if-nez p0, :cond_0

    const-wide/16 v0, -0x1

    goto :goto_0

    :cond_0
    const-string v0, "DateTime"

    invoke-virtual {p0, v0}, Landroid/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "SubSecTime"

    invoke-virtual {p0, v1}, Landroid/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDateTime(Ljava/lang/String;Ljava/lang/String;)J

    move-result-wide v0

    :goto_0
    return-wide v0
.end method

.method public static getDateTime(Landroid/media/ExifInterface;Z)J
    .locals 0

    if-nez p0, :cond_0

    const-wide/16 p0, -0x1

    return-wide p0

    :cond_0
    if-eqz p1, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getDateTime(Landroid/media/ExifInterface;)J

    move-result-wide p0

    goto :goto_0

    :cond_1
    const-string p1, "DateTime"

    invoke-virtual {p0, p1}, Landroid/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDateTime(Ljava/lang/String;)J

    move-result-wide p0

    :goto_0
    return-wide p0
.end method

.method public static getDateTime(Landroidx/exifinterface/media/ExifInterface;)J
    .locals 2
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    if-nez p0, :cond_0

    const-wide/16 v0, -0x1

    goto :goto_0

    :cond_0
    const-string v0, "DateTime"

    invoke-virtual {p0, v0}, Landroidx/exifinterface/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "SubSecTime"

    invoke-virtual {p0, v1}, Landroidx/exifinterface/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDateTime(Ljava/lang/String;Ljava/lang/String;)J

    move-result-wide v0

    :goto_0
    return-wide v0
.end method

.method public static getDateTime(Landroidx/exifinterface/media/ExifInterface;Z)J
    .locals 0

    if-nez p0, :cond_0

    const-wide/16 p0, -0x1

    return-wide p0

    :cond_0
    if-eqz p1, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getDateTime(Landroidx/exifinterface/media/ExifInterface;)J

    move-result-wide p0

    goto :goto_0

    :cond_1
    const-string p1, "DateTime"

    invoke-virtual {p0, p1}, Landroidx/exifinterface/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDateTime(Ljava/lang/String;)J

    move-result-wide p0

    :goto_0
    return-wide p0
.end method

.method public static getGpsDateTime(Landroid/media/ExifInterface;)J
    .locals 2

    if-nez p0, :cond_0

    const-wide/16 v0, -0x1

    goto :goto_0

    :cond_0
    const-string v0, "GPSDateStamp"

    invoke-virtual {p0, v0}, Landroid/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "GPSTimeStamp"

    invoke-virtual {p0, v1}, Landroid/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lcom/miui/gallery/util/GalleryTimeUtils;->getGpsDateTime(Ljava/lang/String;Ljava/lang/String;)J

    move-result-wide v0

    :goto_0
    return-wide v0
.end method

.method public static getGpsDateTime(Landroidx/exifinterface/media/ExifInterface;)J
    .locals 2

    if-nez p0, :cond_0

    const-wide/16 v0, -0x1

    goto :goto_0

    :cond_0
    const-string v0, "GPSDateStamp"

    invoke-virtual {p0, v0}, Landroidx/exifinterface/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "GPSTimeStamp"

    invoke-virtual {p0, v1}, Landroidx/exifinterface/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lcom/miui/gallery/util/GalleryTimeUtils;->getGpsDateTime(Ljava/lang/String;Ljava/lang/String;)J

    move-result-wide v0

    :goto_0
    return-wide v0
.end method

.method public static getMTSpecialAITypeValue(Lcom/miui/gallery3d/exif/ExifInterface;)I
    .locals 2

    if-eqz p0, :cond_0

    const v0, 0xa503

    const/4 v1, 0x2

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery3d/exif/ExifInterface;->getTagIntValue(II)Ljava/lang/Integer;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    if-nez p0, :cond_1

    const/4 p0, -0x1

    goto :goto_1

    :cond_1
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    :goto_1
    return p0
.end method

.method public static getMTSpecialAITypeValue(Ljava/lang/String;)I
    .locals 1

    :try_start_0
    new-instance v0, Lcom/miui/gallery3d/exif/ExifInterface;

    invoke-direct {v0}, Lcom/miui/gallery3d/exif/ExifInterface;-><init>()V

    invoke-virtual {v0, p0}, Lcom/miui/gallery3d/exif/ExifInterface;->readExif(Ljava/lang/String;)V

    invoke-static {v0}, Lcom/miui/gallery/util/ExifUtil;->getMTSpecialAITypeValue(Lcom/miui/gallery3d/exif/ExifInterface;)I

    move-result p0
    :try_end_0
    .catch Lcom/miui/gallery3d/exif/ExifInvalidFormatException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    :catch_0
    move-exception p0

    invoke-virtual {p0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_0

    :catch_1
    move-exception p0

    invoke-virtual {p0}, Lcom/miui/gallery3d/exif/ExifInvalidFormatException;->printStackTrace()V

    :goto_0
    const/4 p0, -0x1

    return p0
.end method

.method public static getOrientation(Lcom/miui/gallery3d/exif/ExifInterface;)I
    .locals 1

    if-nez p0, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    :cond_0
    sget v0, Lcom/miui/gallery3d/exif/ExifInterface;->TAG_ORIENTATION:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery3d/exif/ExifInterface;->getTagIntValue(I)Ljava/lang/Integer;

    move-result-object p0

    :goto_0
    if-nez p0, :cond_1

    const/4 p0, 0x1

    goto :goto_1

    :cond_1
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    :goto_1
    return p0
.end method

.method public static getRotationDegrees(Landroidx/exifinterface/media/ExifInterface;)I
    .locals 2

    const/4 v0, 0x1

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    const-string v1, "Orientation"

    invoke-virtual {p0, v1, v0}, Landroidx/exifinterface/media/ExifInterface;->getAttributeInt(Ljava/lang/String;I)I

    move-result v0

    :goto_0
    invoke-static {v0}, Lcom/miui/gallery/util/ExifUtil;->exifOrientationToDegrees(I)I

    move-result p0

    return p0
.end method

.method public static getRotationDegrees(Lcom/miui/gallery3d/exif/ExifInterface;)I
    .locals 0

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getOrientation(Lcom/miui/gallery3d/exif/ExifInterface;)I

    move-result p0

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->exifOrientationToDegrees(I)I

    move-result p0

    return p0
.end method

.method public static getUserCommentData(Ljava/lang/String;)Lcom/miui/gallery/util/ExifUtil$UserCommentData;
    .locals 10
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    const-string v0, "ExifUtil"

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    const/4 v2, 0x0

    if-nez v1, :cond_5

    :try_start_0
    sget-object v1, Lcom/miui/gallery/util/ExifUtil;->sSupportExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v1, p0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/exifinterface/media/ExifInterface;

    new-instance v3, Lcom/miui/gallery/util/UserComment;

    new-instance v4, Lcom/miui/gallery/util/ExifInterfaceWrapper;

    invoke-direct {v4, v1}, Lcom/miui/gallery/util/ExifInterfaceWrapper;-><init>(Landroidx/exifinterface/media/ExifInterface;)V

    invoke-direct {v3, v4}, Lcom/miui/gallery/util/UserComment;-><init>(Lcom/miui/gallery/util/ExifInterfaceWrapper;)V

    invoke-virtual {v3}, Lcom/miui/gallery/util/UserComment;->isOriginalUserCommentUsable()Z

    move-result v1

    if-eqz v1, :cond_0

    new-instance v1, Lcom/miui/gallery/util/ExifUtil$UserCommentData;

    invoke-virtual {v3}, Lcom/miui/gallery/util/UserComment;->getSha1()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3}, Lcom/miui/gallery/util/UserComment;->getFileExt()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/util/ExifUtil$UserCommentData;-><init>(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v1

    :cond_0
    move-object v1, v2

    goto :goto_0

    :catch_0
    move-exception v1

    const-string v3, "Failed to read user comment using support exif interface, %s"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_0
    new-instance v3, Ljava/io/File;

    invoke-direct {v3, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v4

    const-string v5, "exif_parser"

    const-string v6, "exception"

    const-string v7, "path"

    if-eqz v4, :cond_3

    invoke-virtual {v3}, Ljava/io/File;->isFile()Z

    move-result v4

    if-eqz v4, :cond_3

    invoke-virtual {v3}, Ljava/io/File;->length()J

    move-result-wide v3

    const-wide/32 v8, 0x100000

    cmp-long v3, v3, v8

    if-gtz v3, :cond_3

    :try_start_1
    sget-object v3, Lcom/miui/gallery/util/ExifUtil;->sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v3, p0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery3d/exif/ExifInterface;

    if-eqz v3, :cond_2

    new-instance v4, Lcom/miui/gallery/util/UserComment;

    new-instance v8, Lcom/miui/gallery/util/ExifInterfaceWrapper;

    invoke-direct {v8, v3}, Lcom/miui/gallery/util/ExifInterfaceWrapper;-><init>(Lcom/miui/gallery3d/exif/ExifInterface;)V

    invoke-direct {v4, v8}, Lcom/miui/gallery/util/UserComment;-><init>(Lcom/miui/gallery/util/ExifInterfaceWrapper;)V

    invoke-virtual {v4}, Lcom/miui/gallery/util/UserComment;->isOriginalUserCommentUsable()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-virtual {v4}, Lcom/miui/gallery/util/UserComment;->getSha1()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_2

    new-instance v3, Lcom/miui/gallery/util/ExifUtil$UserCommentData;

    invoke-virtual {v4}, Lcom/miui/gallery/util/UserComment;->getSha1()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v4}, Lcom/miui/gallery/util/UserComment;->getFileExt()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v8, v4}, Lcom/miui/gallery/util/ExifUtil$UserCommentData;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/stat/BaseSamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v4

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object v1

    goto :goto_1

    :cond_1
    const-string v1, "unrecognizable UserComment"

    :goto_1
    invoke-interface {v4, v6, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-interface {v4, v7, p0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "exif_read_by_gallery_3d_exif_interface"

    invoke-static {v5, v1, v4}, Lcom/miui/gallery/stat/BaseSamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    invoke-static {p0, v3}, Lcom/miui/gallery/util/ExifUtil;->rewriteUserComment(Ljava/lang/String;Lcom/miui/gallery/util/ExifUtil$UserCommentData;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    return-object v3

    :catch_1
    move-exception v1

    const-string v3, "Failed to read user comment using gallery 3d exif interface, %s"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_2
    :try_start_2
    sget-object v3, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v3, p0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/media/ExifInterface;

    new-instance v4, Lcom/miui/gallery/util/UserComment;

    new-instance v8, Lcom/miui/gallery/util/ExifInterfaceWrapper;

    invoke-direct {v8, v3}, Lcom/miui/gallery/util/ExifInterfaceWrapper;-><init>(Landroid/media/ExifInterface;)V

    invoke-direct {v4, v8}, Lcom/miui/gallery/util/UserComment;-><init>(Lcom/miui/gallery/util/ExifInterfaceWrapper;)V

    invoke-virtual {v4}, Lcom/miui/gallery/util/UserComment;->isOriginalUserCommentUsable()Z

    move-result v3

    if-eqz v3, :cond_3

    new-instance v1, Lcom/miui/gallery/util/ExifUtil$UserCommentData;

    invoke-virtual {v4}, Lcom/miui/gallery/util/UserComment;->getSha1()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v4}, Lcom/miui/gallery/util/UserComment;->getFileExt()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v1, v3, v4}, Lcom/miui/gallery/util/ExifUtil$UserCommentData;-><init>(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    return-object v1

    :catch_2
    move-exception v1

    const-string v3, "Failed to read user comment using media exif interface, %s"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_3
    if-nez v1, :cond_4

    goto :goto_2

    :cond_4
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sget-object v2, Landroid/os/Build;->MODEL:Ljava/lang/String;

    const-string v3, "model"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v2, Landroid/os/Build$VERSION;->INCREMENTAL:Ljava/lang/String;

    const-string v3, "version"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-interface {v0, v7, p0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {v1}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-interface {v0, v6, p0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "exif_read_error"

    invoke-static {v5, p0, v0}, Lcom/miui/gallery/stat/BaseSamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    throw v1

    :cond_5
    :goto_2
    return-object v2
.end method

.method public static getUserCommentSha1(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    :try_start_0
    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getUserCommentData(Ljava/lang/String;)Lcom/miui/gallery/util/ExifUtil$UserCommentData;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/util/ExifUtil$UserCommentData;->getSha1()Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    const-string v0, "ExifUtil"

    const-string v1, "Failed to read exif!!"

    invoke-static {v0, v1, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static getXiaomiComment(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    :try_start_0
    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v0, p0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery3d/exif/ExifInterface;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery3d/exif/ExifInterface;->getXiaomiComment()Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    const-string v0, "ExifUtil"

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static getXiaomiCommentSensorType(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    :try_start_0
    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getXiaomiComment(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    const-string p0, "sensor_type"

    invoke-virtual {v0, p0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    const-string v0, "ExifUtil"

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static isFromFrontCamera(Ljava/lang/String;)Z
    .locals 1

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getXiaomiCommentSensorType(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "front"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method public static isMotionPhoto(Lcom/miui/gallery3d/exif/ExifInterface;)Z
    .locals 3

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    :cond_0
    const v1, 0x8897    # 4.8999E-41f

    const/4 v2, 0x2

    invoke-virtual {p0, v1, v2}, Lcom/miui/gallery3d/exif/ExifInterface;->getTagByteValue(II)Ljava/lang/Byte;

    move-result-object p0

    const/4 v1, 0x1

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Ljava/lang/Byte;->byteValue()B

    move-result p0

    if-ne p0, v1, :cond_1

    move v0, v1

    :cond_1
    return v0
.end method

.method public static isMotionPhoto(Ljava/lang/String;)Z
    .locals 1

    :try_start_0
    new-instance v0, Lcom/miui/gallery3d/exif/ExifInterface;

    invoke-direct {v0}, Lcom/miui/gallery3d/exif/ExifInterface;-><init>()V

    invoke-virtual {v0, p0}, Lcom/miui/gallery3d/exif/ExifInterface;->readExif(Ljava/lang/String;)V

    invoke-static {v0}, Lcom/miui/gallery/util/ExifUtil;->isMotionPhoto(Lcom/miui/gallery3d/exif/ExifInterface;)Z

    move-result p0
    :try_end_0
    .catch Lcom/miui/gallery3d/exif/ExifInvalidFormatException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    :catch_0
    move-exception p0

    invoke-virtual {p0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_0

    :catch_1
    move-exception p0

    invoke-virtual {p0}, Lcom/miui/gallery3d/exif/ExifInvalidFormatException;->printStackTrace()V

    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public static isWidthHeightRotated(I)Z
    .locals 1

    const/4 v0, 0x5

    if-eq p0, v0, :cond_0

    const/4 v0, 0x6

    if-eq p0, v0, :cond_0

    const/4 v0, 0x7

    if-eq p0, v0, :cond_0

    const/16 v0, 0x8

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const/4 p0, 0x1

    return p0
.end method

.method public static parseRotationInfo(I)Lcom/miui/gallery/util/ExifUtil$ExifInfo;
    .locals 4

    const/4 v0, 0x0

    const/4 v1, 0x1

    packed-switch p0, :pswitch_data_0

    move v1, v0

    goto :goto_0

    :pswitch_0
    move v0, v1

    :pswitch_1
    const/16 v1, 0x5a

    goto :goto_0

    :pswitch_2
    move v0, v1

    :pswitch_3
    const/16 v1, 0x10e

    goto :goto_0

    :pswitch_4
    move v0, v1

    :pswitch_5
    const/16 v1, 0xb4

    goto :goto_0

    :pswitch_6
    move v1, v0

    :pswitch_7
    move v3, v1

    move v1, v0

    move v0, v3

    :goto_0
    new-instance v2, Lcom/miui/gallery/util/ExifUtil$ExifInfo;

    invoke-direct {v2, p0, v1, v0}, Lcom/miui/gallery/util/ExifUtil$ExifInfo;-><init>(IIZ)V

    return-object v2

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_6
        :pswitch_7
        :pswitch_5
        :pswitch_4
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method

.method public static parseRotationInfo(Ljava/lang/Object;)Lcom/miui/gallery/util/ExifUtil$ExifInfo;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;)",
            "Lcom/miui/gallery/util/ExifUtil$ExifInfo;"
        }
    .end annotation

    const/4 v0, 0x0

    if-eqz p0, :cond_3

    instance-of v1, p0, Landroidx/exifinterface/media/ExifInterface;

    const/4 v2, 0x1

    const-string v3, "Orientation"

    if-eqz v1, :cond_0

    check-cast p0, Landroidx/exifinterface/media/ExifInterface;

    invoke-virtual {p0, v3, v2}, Landroidx/exifinterface/media/ExifInterface;->getAttributeInt(Ljava/lang/String;I)I

    move-result p0

    goto :goto_0

    :cond_0
    instance-of v1, p0, Lcom/miui/gallery3d/exif/ExifInterface;

    if-eqz v1, :cond_1

    check-cast p0, Lcom/miui/gallery3d/exif/ExifInterface;

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getOrientation(Lcom/miui/gallery3d/exif/ExifInterface;)I

    move-result p0

    goto :goto_0

    :cond_1
    instance-of v1, p0, Landroid/media/ExifInterface;

    if-eqz v1, :cond_2

    check-cast p0, Landroid/media/ExifInterface;

    invoke-virtual {p0, v3, v2}, Landroid/media/ExifInterface;->getAttributeInt(Ljava/lang/String;I)I

    move-result p0

    :goto_0
    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->parseRotationInfo(I)Lcom/miui/gallery/util/ExifUtil$ExifInfo;

    move-result-object p0

    return-object p0

    :cond_2
    const-string v1, "ExifUtil"

    const-string v2, "Not supported exif interface %s"

    invoke-static {v1, v2, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_3
    return-object v0
.end method

.method public static parseRotationInfo(Ljava/lang/String;[B)Lcom/miui/gallery/util/ExifUtil$ExifInfo;
    .locals 1

    if-nez p1, :cond_0

    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sSupportExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/ExifUtil;->createExifInterface(Ljava/lang/String;[BLcom/miui/gallery/util/ExifUtil$ExifCreator;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->parseRotationInfo(Ljava/lang/Object;)Lcom/miui/gallery/util/ExifUtil$ExifInfo;

    move-result-object p0

    return-object p0

    :cond_0
    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/ExifUtil;->createExifInterface(Ljava/lang/String;[BLcom/miui/gallery/util/ExifUtil$ExifCreator;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery3d/exif/ExifInterface;

    if-nez v0, :cond_1

    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sSupportExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/ExifUtil;->createExifInterfaceByDecryptFile(Ljava/lang/String;[BLcom/miui/gallery/util/ExifUtil$ExifCreator;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/exifinterface/media/ExifInterface;

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->parseRotationInfo(Ljava/lang/Object;)Lcom/miui/gallery/util/ExifUtil$ExifInfo;

    move-result-object p0

    return-object p0

    :cond_1
    invoke-static {v0}, Lcom/miui/gallery/util/ExifUtil;->parseRotationInfo(Ljava/lang/Object;)Lcom/miui/gallery/util/ExifUtil$ExifInfo;

    move-result-object p0

    return-object p0
.end method

.method private static rewriteUserComment(Ljava/lang/String;Lcom/miui/gallery/util/ExifUtil$UserCommentData;)V
    .locals 2

    const-string v0, "ExifUtil"

    :try_start_0
    const-string v1, "Try to rewrite UserComment using android.media.ExifInterface"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    sget-object v1, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v1, p0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/media/ExifInterface;

    invoke-static {p0, p1}, Lcom/miui/gallery/util/ExifUtil;->setUserCommentData(Landroid/media/ExifInterface;Lcom/miui/gallery/util/ExifUtil$UserCommentData;)V

    invoke-virtual {p0}, Landroid/media/ExifInterface;->saveAttributes()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    const-string p1, "Failed to rewrite UserComment using android.media.ExifInterface, %s"

    invoke-static {v0, p1, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_0
    return-void
.end method

.method public static setDateTime(Landroid/media/ExifInterface;Ljava/lang/String;)V
    .locals 1

    if-eqz p0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const-string v0, "DateTimeOriginal"

    invoke-virtual {p0, v0, p1}, Landroid/media/ExifInterface;->setAttribute(Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "DateTime"

    invoke-virtual {p0, v0, p1}, Landroid/media/ExifInterface;->setAttribute(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public static setDateTime(Lcom/miui/gallery3d/exif/ExifInterface;Ljava/lang/String;)V
    .locals 1

    if-eqz p0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    sget v0, Lcom/miui/gallery3d/exif/ExifInterface;->TAG_DATE_TIME_ORIGINAL:I

    invoke-virtual {p0, v0, p1}, Lcom/miui/gallery3d/exif/ExifInterface;->addDateTimeStampTag(ILjava/lang/String;)Z

    sget v0, Lcom/miui/gallery3d/exif/ExifInterface;->TAG_DATE_TIME:I

    invoke-virtual {p0, v0, p1}, Lcom/miui/gallery3d/exif/ExifInterface;->addDateTimeStampTag(ILjava/lang/String;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public static setDateTime(Landroid/media/ExifInterface;JZ)Z
    .locals 6

    const-string v0, "ExifUtil"

    const/4 v1, 0x0

    if-eqz p0, :cond_3

    const-wide/16 v2, 0x0

    cmp-long v2, p1, v2

    if-nez v2, :cond_0

    goto :goto_1

    :cond_0
    :try_start_0
    invoke-static {}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDefaultDateFormat()Ljava/text/SimpleDateFormat;

    move-result-object v2

    new-instance v3, Ljava/util/Date;

    invoke-direct {v3, p1, p2}, Ljava/util/Date;-><init>(J)V

    const/4 p1, 0x1

    if-eqz p3, :cond_1

    invoke-virtual {v2, v3}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p2}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Landroid/media/ExifInterface;Ljava/lang/String;)V

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getGpsDateTime(Landroid/media/ExifInterface;)J

    move-result-wide p2

    const-wide/16 v4, -0x1

    cmp-long p2, p2, v4

    if-eqz p2, :cond_2

    invoke-static {}, Lcom/miui/gallery/util/GalleryTimeUtils;->getUTCDateFormat()Ljava/text/SimpleDateFormat;

    move-result-object p2

    invoke-virtual {p2, v3}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p2

    const-string p3, " "

    invoke-virtual {p2, p3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p3

    aget-object v2, p3, v1

    aget-object p3, p3, p1

    invoke-static {p0, v2, p3}, Lcom/miui/gallery/util/ExifUtil;->setGpsDateTime(Landroid/media/ExifInterface;Ljava/lang/String;Ljava/lang/String;)V

    const-string p0, "\u4fee\u6539\u7167\u7247\u65f6\u95f4(\u5305\u542bGPS),\u4fee\u6539\u540e\u7684\u65f6\u95f4\u4e3a:%s"

    invoke-static {v0, p0, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-virtual {v2, v3}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p2}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Landroid/media/ExifInterface;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_2
    :goto_0
    return p1

    :catch_0
    move-exception p0

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_3
    :goto_1
    return v1
.end method

.method public static setDateTime(Lcom/miui/gallery3d/exif/ExifInterface;JZ)Z
    .locals 4

    const-string v0, "ExifUtil"

    const/4 v1, 0x0

    if-eqz p0, :cond_2

    const-wide/16 v2, 0x0

    cmp-long v2, p1, v2

    if-nez v2, :cond_0

    goto :goto_1

    :cond_0
    :try_start_0
    invoke-static {}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDefaultDateFormat()Ljava/text/SimpleDateFormat;

    move-result-object v2

    new-instance v3, Ljava/util/Date;

    invoke-direct {v3, p1, p2}, Ljava/util/Date;-><init>(J)V

    const/4 p1, 0x1

    if-eqz p3, :cond_1

    invoke-virtual {v2, v3}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p2}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Lcom/miui/gallery3d/exif/ExifInterface;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/util/GalleryTimeUtils;->getUTCDateFormat()Ljava/text/SimpleDateFormat;

    move-result-object p2

    invoke-virtual {p2, v3}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p2

    const-string p3, " "

    invoke-virtual {p2, p3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p3

    aget-object v2, p3, v1

    aget-object p3, p3, p1

    invoke-static {p0, v2, p3}, Lcom/miui/gallery/util/ExifUtil;->setGpsDateTime(Lcom/miui/gallery3d/exif/ExifInterface;Ljava/lang/String;Ljava/lang/String;)V

    const-string p0, "\u4fee\u6539\u7167\u7247\u65f6\u95f4(\u5305\u542bGPS),\u4fee\u6539\u540e\u7684\u65f6\u95f4\u4e3a:%s"

    invoke-static {v0, p0, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-virtual {v2, v3}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p2}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Lcom/miui/gallery3d/exif/ExifInterface;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    return p1

    :catch_0
    move-exception p0

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_2
    :goto_1
    return v1
.end method

.method public static setGpsDateTime(Landroid/media/ExifInterface;Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    if-eqz p0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const-string v0, "GPSDateStamp"

    invoke-virtual {p0, v0, p1}, Landroid/media/ExifInterface;->setAttribute(Ljava/lang/String;Ljava/lang/String;)V

    const-string p1, "GPSTimeStamp"

    invoke-virtual {p0, p1, p2}, Landroid/media/ExifInterface;->setAttribute(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public static setGpsDateTime(Lcom/miui/gallery3d/exif/ExifInterface;Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    if-eqz p0, :cond_1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery3d/exif/ExifInterface;->addGpsDateTimeStampTag(Ljava/lang/String;Ljava/lang/String;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public static setSubSecTime(Landroid/media/ExifInterface;J)V
    .locals 2

    if-eqz p0, :cond_1

    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-gtz v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Ljava/util/Calendar;->setTimeInMillis(J)V

    const/16 p1, 0xe

    invoke-virtual {v0, p1}, Ljava/util/Calendar;->get(I)I

    move-result p1

    if-lez p1, :cond_1

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    const-string p2, "SubSecTime"

    invoke-virtual {p0, p2, p1}, Landroid/media/ExifInterface;->setAttribute(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public static setSubSecTime(Lcom/miui/gallery3d/exif/ExifInterface;J)V
    .locals 2

    if-eqz p0, :cond_1

    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-gtz v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery3d/exif/ExifInterface;->addSubSecTimeStampTag(J)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public static setUserCommentData(Landroid/media/ExifInterface;Lcom/miui/gallery/util/ExifUtil$UserCommentData;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    if-eqz p0, :cond_0

    if-eqz p1, :cond_0

    new-instance v0, Lcom/miui/gallery/util/UserComment;

    new-instance v1, Lcom/miui/gallery/util/ExifInterfaceWrapper;

    invoke-direct {v1, p0}, Lcom/miui/gallery/util/ExifInterfaceWrapper;-><init>(Landroid/media/ExifInterface;)V

    invoke-direct {v0, v1}, Lcom/miui/gallery/util/UserComment;-><init>(Lcom/miui/gallery/util/ExifInterfaceWrapper;)V

    invoke-virtual {v0, p1}, Lcom/miui/gallery/util/UserComment;->setData(Lcom/miui/gallery/util/ExifUtil$UserCommentData;)V

    :cond_0
    return-void
.end method

.method public static supportRefocus(Lcom/miui/gallery3d/exif/ExifInterface;)Z
    .locals 2

    if-eqz p0, :cond_1

    const v0, 0x8890

    const/4 v1, 0x2

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery3d/exif/ExifInterface;->getTagStringValue(II)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const v0, 0x8898    # 4.9E-41f

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery3d/exif/ExifInterface;->getTag(II)Lcom/miui/gallery3d/exif/ExifTag;

    move-result-object p0

    if-eqz p0, :cond_1

    :cond_0
    const/4 p0, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static supportRefocus(Ljava/lang/String;)Z
    .locals 1

    :try_start_0
    new-instance v0, Lcom/miui/gallery3d/exif/ExifInterface;

    invoke-direct {v0}, Lcom/miui/gallery3d/exif/ExifInterface;-><init>()V

    invoke-virtual {v0, p0}, Lcom/miui/gallery3d/exif/ExifInterface;->readExif(Ljava/lang/String;)V

    invoke-static {v0}, Lcom/miui/gallery/util/ExifUtil;->supportRefocus(Lcom/miui/gallery3d/exif/ExifInterface;)Z

    move-result p0
    :try_end_0
    .catch Lcom/miui/gallery3d/exif/ExifInvalidFormatException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    :catch_0
    move-exception p0

    invoke-virtual {p0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_0

    :catch_1
    move-exception p0

    invoke-virtual {p0}, Lcom/miui/gallery3d/exif/ExifInvalidFormatException;->printStackTrace()V

    :goto_0
    const/4 p0, 0x0

    return p0
.end method
