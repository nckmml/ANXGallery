.class public Lcom/miui/gallery/cloud/ThumbnailMetaWriter;
.super Ljava/lang/Object;
.source "ThumbnailMetaWriter.java"


# instance fields
.field private final mDateTime:Ljava/lang/String;

.field private final mGPSDateStamp:Ljava/lang/String;

.field private final mGPSTimeStamp:Ljava/lang/String;

.field private final mMixDateTime:J

.field private final mOriginalFileName:Ljava/lang/String;

.field private final mSha1:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/data/DBImage;)V
    .locals 2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getSha1()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, p1, v0, v1}, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;-><init>(Lcom/miui/gallery/data/DBImage;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>(Lcom/miui/gallery/data/DBImage;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mSha1:Ljava/lang/String;

    iput-object p3, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mOriginalFileName:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getMixedDateTime()J

    move-result-wide p2

    iput-wide p2, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mMixDateTime:J

    const-string p2, "dateTime"

    invoke-virtual {p1, p2}, Lcom/miui/gallery/data/DBImage;->getJsonExifString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mDateTime:Ljava/lang/String;

    const-string p2, "GPSDateStamp"

    invoke-virtual {p1, p2}, Lcom/miui/gallery/data/DBImage;->getJsonExifString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSDateStamp:Ljava/lang/String;

    const-string p2, "GPSTimeStamp"

    invoke-virtual {p1, p2}, Lcom/miui/gallery/data/DBImage;->getJsonExifString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSTimeStamp:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mSha1:Ljava/lang/String;

    iput-object p2, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mOriginalFileName:Ljava/lang/String;

    iput-wide p3, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mMixDateTime:J

    iput-object p5, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mDateTime:Ljava/lang/String;

    iput-object p6, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSDateStamp:Ljava/lang/String;

    iput-object p7, p0, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSTimeStamp:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public write(Ljava/lang/String;)Z
    .locals 16

    move-object/from16 v1, p0

    move-object/from16 v2, p1

    const-string v3, "ThumbnailMetaWriter"

    const/4 v4, 0x0

    const/4 v5, 0x0

    :try_start_0
    invoke-static/range {p1 .. p1}, Lcom/miui/gallery/util/DocumentProviderUtils;->needUseDocumentProvider(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0, v2}, Lcom/miui/gallery/util/DocumentProviderUtils;->openFileDescriptor(Landroid/content/Context;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;

    move-result-object v6
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v6, :cond_0

    :try_start_1
    const-string v0, "Failed to open file descriptor  of %s"

    invoke-static {v3, v0, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    invoke-static {v6}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return v4

    :cond_0
    :try_start_2
    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-virtual {v6}, Landroid/os/ParcelFileDescriptor;->getFileDescriptor()Ljava/io/FileDescriptor;

    move-result-object v7

    invoke-interface {v0, v7}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/io/FileDescriptor;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/media/ExifInterface;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    goto :goto_0

    :catch_0
    move-exception v0

    goto/16 :goto_2

    :cond_1
    :try_start_3
    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v0, v2}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/media/ExifInterface;
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-object v6, v5

    :goto_0
    :try_start_4
    new-instance v7, Lcom/miui/gallery/util/ExifUtil$UserCommentData;

    iget-object v8, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mSha1:Ljava/lang/String;

    iget-object v9, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mOriginalFileName:Ljava/lang/String;

    invoke-static {v9}, Lcom/miui/gallery/util/FileUtils;->getExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    invoke-direct {v7, v8, v9}, Lcom/miui/gallery/util/ExifUtil$UserCommentData;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {v0, v7}, Lcom/miui/gallery/util/ExifUtil;->setUserCommentData(Landroid/media/ExifInterface;Lcom/miui/gallery/util/ExifUtil$UserCommentData;)V

    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSDateStamp:Ljava/lang/String;

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    const-wide/16 v8, -0x1

    if-nez v7, :cond_2

    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSTimeStamp:Ljava/lang/String;

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_2

    const-string v7, "GPSDateStamp"

    iget-object v10, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSDateStamp:Ljava/lang/String;

    invoke-virtual {v0, v7, v10}, Landroid/media/ExifInterface;->setAttribute(Ljava/lang/String;Ljava/lang/String;)V

    const-string v7, "GPSTimeStamp"

    iget-object v10, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSTimeStamp:Ljava/lang/String;

    invoke-virtual {v0, v7, v10}, Landroid/media/ExifInterface;->setAttribute(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSDateStamp:Ljava/lang/String;

    iget-object v10, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mGPSTimeStamp:Ljava/lang/String;

    invoke-static {v7, v10}, Lcom/miui/gallery/util/GalleryTimeUtils;->getGpsDateTime(Ljava/lang/String;Ljava/lang/String;)J

    move-result-wide v10

    goto :goto_1

    :cond_2
    move-wide v10, v8

    :goto_1
    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mDateTime:Ljava/lang/String;

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    const-wide/16 v12, 0x0

    if-nez v7, :cond_3

    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mDateTime:Ljava/lang/String;

    invoke-static {v0, v7}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Landroid/media/ExifInterface;Ljava/lang/String;)V

    cmp-long v7, v10, v12

    if-gez v7, :cond_3

    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mDateTime:Ljava/lang/String;

    invoke-static {v7}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDateTime(Ljava/lang/String;)J

    move-result-wide v10

    :cond_3
    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mDateTime:Ljava/lang/String;

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_4

    invoke-static {v0}, Lcom/miui/gallery/util/ExifUtil;->getDateTime(Landroid/media/ExifInterface;)J

    move-result-wide v14

    cmp-long v7, v14, v8

    if-nez v7, :cond_5

    iget-wide v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mMixDateTime:J

    cmp-long v7, v7, v12

    if-ltz v7, :cond_5

    :cond_4
    invoke-static {}, Lcom/miui/gallery/util/GalleryTimeUtils;->getDefaultDateFormat()Ljava/text/SimpleDateFormat;

    move-result-object v7

    new-instance v8, Ljava/util/Date;

    iget-wide v14, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mMixDateTime:J

    invoke-direct {v8, v14, v15}, Ljava/util/Date;-><init>(J)V

    invoke-virtual {v7, v8}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v7

    invoke-static {v0, v7}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Landroid/media/ExifInterface;Ljava/lang/String;)V

    cmp-long v7, v10, v12

    if-gez v7, :cond_5

    iget-wide v10, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mMixDateTime:J

    :cond_5
    invoke-virtual {v0}, Landroid/media/ExifInterface;->saveAttributes()V

    invoke-static/range {p1 .. p1}, Lcom/miui/gallery/util/ExifUtil;->getUserCommentSha1(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-eqz v7, :cond_6

    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mSha1:Ljava/lang/String;

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_7

    :cond_6
    iget-object v7, v1, Lcom/miui/gallery/cloud/ThumbnailMetaWriter;->mSha1:Ljava/lang/String;

    invoke-static {v0, v7}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_9

    :cond_7
    cmp-long v0, v10, v12

    if-ltz v0, :cond_8

    invoke-static {v2, v10, v11}, Lcom/miui/gallery/util/FileUtils;->setLastModified(Ljava/lang/String;J)Z

    move-result v0

    if-nez v0, :cond_8

    const-string v0, "failed to set last modified for thumbnail"

    invoke-static {v3, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    :cond_8
    const/4 v0, 0x1

    invoke-static {v6}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return v0

    :catchall_0
    move-exception v0

    move-object v6, v5

    goto :goto_4

    :catch_1
    move-exception v0

    move-object v6, v5

    :goto_2
    move-object v5, v0

    :try_start_5
    invoke-static {v3, v5}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    :cond_9
    invoke-static {v6}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sget-object v3, Landroid/os/Build;->MODEL:Ljava/lang/String;

    const-string v6, "model"

    invoke-interface {v0, v6, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v3, Landroid/os/Build$VERSION;->INCREMENTAL:Ljava/lang/String;

    const-string v6, "version"

    invoke-interface {v0, v6, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v3, "path"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz v5, :cond_a

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, " "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    goto :goto_3

    :cond_a
    const-string v2, ""

    :goto_3
    const-string v3, "exception"

    invoke-interface {v0, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "exif_parser"

    const-string v3, "exif_write_error"

    invoke-static {v2, v3, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return v4

    :catchall_1
    move-exception v0

    :goto_4
    invoke-static {v6}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw v0
.end method
