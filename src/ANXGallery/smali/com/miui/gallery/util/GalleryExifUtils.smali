.class public Lcom/miui/gallery/util/GalleryExifUtils;
.super Ljava/lang/Object;
.source "GalleryExifUtils.java"


# direct methods
.method public static isPhotoContainsSpecialTags(Ljava/lang/String;)Z
    .locals 1

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->supportRefocus(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->isMotionPhoto(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/util/ExifUtil;->getMTSpecialAITypeValue(Ljava/lang/String;)I

    move-result p0

    if-lez p0, :cond_1

    :cond_0
    const/4 p0, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static setImageFileDataTime(Landroid/content/Context;Ljava/lang/String;JZ)Landroid/util/Pair;
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/lang/String;",
            "JZ)",
            "Landroid/util/Pair<",
            "Ljava/lang/Boolean;",
            "Ljava/io/File;",
            ">;"
        }
    .end annotation

    move-object/from16 v0, p0

    move-wide/from16 v1, p2

    move/from16 v3, p4

    new-instance v4, Ljava/io/File;

    move-object/from16 v5, p1

    invoke-direct {v4, v5}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v4, v1, v2}, Lcom/miui/gallery/util/FileUtils;->generateFileDateTimeName(Ljava/io/File;J)Ljava/lang/String;

    move-result-object v6

    const/4 v7, 0x0

    :try_start_0
    invoke-static/range {p1 .. p1}, Lcom/miui/gallery/util/DocumentProviderUtils;->needUseDocumentProvider(Ljava/lang/String;)Z

    move-result v8

    sget v9, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v10, 0x1c

    const/4 v11, 0x0

    const/4 v12, 0x1

    if-lt v9, v10, :cond_0

    move v9, v12

    goto :goto_0

    :cond_0
    move v9, v11

    :goto_0
    invoke-static/range {p1 .. p1}, Lcom/miui/gallery/util/GalleryExifUtils;->isPhotoContainsSpecialTags(Ljava/lang/String;)Z

    move-result v5

    sget v10, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v13, 0x17

    if-ne v10, v13, :cond_1

    move v10, v12

    goto :goto_1

    :cond_1
    move v10, v11

    :goto_1
    if-eqz v9, :cond_3

    if-eqz v5, :cond_3

    if-nez v8, :cond_2

    goto :goto_2

    :cond_2
    move-object v13, v7

    goto :goto_3

    :cond_3
    :goto_2
    new-instance v13, Ljava/io/File;

    invoke-direct {v13, v6}, Ljava/io/File;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    :try_start_1
    invoke-static {v4, v13}, Lcom/miui/gallery/util/FileUtils;->copyFile(Ljava/io/File;Ljava/io/File;)Z

    move-result v14

    if-nez v14, :cond_4

    return-object v7

    :cond_4
    :goto_3
    if-eqz v8, :cond_8

    if-eqz v9, :cond_6

    if-eqz v5, :cond_6

    new-instance v14, Ljava/io/File;

    invoke-static {v0, v4, v1, v2}, Lcom/miui/gallery/util/FileUtils;->generateCacheFilePath(Landroid/content/Context;Ljava/io/File;J)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v14, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    :try_start_2
    invoke-static {v4, v14}, Lcom/miui/gallery/util/FileUtils;->copyFile(Ljava/io/File;Ljava/io/File;)Z

    move-result v4

    if-eqz v4, :cond_5

    sget-object v4, Lcom/miui/gallery/util/ExifUtil;->sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v4, v0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery3d/exif/ExifInterface;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    move-object v13, v7

    goto :goto_4

    :cond_5
    return-object v7

    :cond_6
    :try_start_3
    invoke-static {v0, v6}, Lcom/miui/gallery/util/DocumentProviderUtils;->openFileDescriptor(Landroid/content/Context;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;

    move-result-object v0

    if-nez v0, :cond_7

    return-object v7

    :cond_7
    sget-object v4, Lcom/miui/gallery/util/ExifUtil;->sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-virtual {v0}, Landroid/os/ParcelFileDescriptor;->getFileDescriptor()Ljava/io/FileDescriptor;

    move-result-object v14

    invoke-interface {v4, v14}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/io/FileDescriptor;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery3d/exif/ExifInterface;

    move-object v14, v13

    move-object v13, v0

    move-object v0, v7

    goto :goto_4

    :cond_8
    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sGallery3DExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v0, v6}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    move-object v4, v0

    check-cast v4, Lcom/miui/gallery3d/exif/ExifInterface;
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0

    move-object v0, v7

    move-object v14, v13

    move-object v13, v0

    :goto_4
    if-nez v0, :cond_9

    move-object v0, v6

    :cond_9
    if-eqz v5, :cond_a

    :try_start_4
    invoke-virtual {v4, v0}, Lcom/miui/gallery3d/exif/ExifInterface;->readExif(Ljava/lang/String;)V

    invoke-static {v4, v1, v2, v3}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Lcom/miui/gallery3d/exif/ExifInterface;JZ)Z

    invoke-static {v4, v1, v2}, Lcom/miui/gallery/util/ExifUtil;->setSubSecTime(Lcom/miui/gallery3d/exif/ExifInterface;J)V

    invoke-virtual {v4, v0}, Lcom/miui/gallery3d/exif/ExifInterface;->forceRewriteExif(Ljava/lang/String;)V

    move-object v4, v7

    goto :goto_6

    :cond_a
    if-eqz v8, :cond_b

    sget-object v4, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-virtual {v13}, Landroid/os/ParcelFileDescriptor;->getFileDescriptor()Ljava/io/FileDescriptor;

    move-result-object v15

    invoke-interface {v4, v15}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/io/FileDescriptor;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/media/ExifInterface;

    goto :goto_5

    :cond_b
    sget-object v4, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v4, v6}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/media/ExifInterface;

    :goto_5
    invoke-static {v4, v1, v2, v3}, Lcom/miui/gallery/util/ExifUtil;->setDateTime(Landroid/media/ExifInterface;JZ)Z

    invoke-static {v4, v1, v2}, Lcom/miui/gallery/util/ExifUtil;->setSubSecTime(Landroid/media/ExifInterface;J)V

    invoke-virtual {v4}, Landroid/media/ExifInterface;->saveAttributes()V

    :goto_6
    if-eqz v5, :cond_d

    if-eqz v8, :cond_c

    if-nez v9, :cond_c

    sget-object v0, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-virtual {v13}, Landroid/os/ParcelFileDescriptor;->getFileDescriptor()Ljava/io/FileDescriptor;

    move-result-object v3

    invoke-interface {v0, v3}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/io/FileDescriptor;)Ljava/lang/Object;

    move-result-object v0

    move-object v4, v0

    check-cast v4, Landroid/media/ExifInterface;

    goto :goto_7

    :cond_c
    sget-object v3, Lcom/miui/gallery/util/ExifUtil;->sMediaExifCreator:Lcom/miui/gallery/util/ExifUtil$ExifCreator;

    invoke-interface {v3, v0}, Lcom/miui/gallery/util/ExifUtil$ExifCreator;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    move-object v4, v0

    check-cast v4, Landroid/media/ExifInterface;

    :cond_d
    :goto_7
    invoke-static {v4, v12}, Lcom/miui/gallery/util/ExifUtil;->getDateTime(Landroid/media/ExifInterface;Z)J

    move-result-wide v3

    cmp-long v0, v3, v1

    if-eqz v0, :cond_e

    return-object v7

    :cond_e
    if-eqz v5, :cond_10

    if-eqz v9, :cond_10

    if-eqz v8, :cond_10

    new-instance v0, Ljava/io/File;

    invoke-direct {v0, v6}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v14, v0}, Lcom/miui/gallery/util/FileUtils;->copyFileByDocumentProvider(Ljava/io/File;Ljava/io/File;)Z

    move-result v1

    if-eqz v1, :cond_f

    new-instance v1, Landroid/util/Pair;

    invoke-static {v14}, Lcom/miui/gallery/util/FileUtils;->deleteFile(Ljava/io/File;)Z

    move-result v2

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v1

    :cond_f
    return-object v7

    :cond_10
    if-eqz v10, :cond_11

    invoke-virtual {v14, v1, v2}, Ljava/io/File;->setLastModified(J)Z

    new-instance v0, Landroid/util/Pair;

    invoke-static {v12}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-direct {v0, v1, v14}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v0

    :cond_11
    new-instance v0, Landroid/util/Pair;

    invoke-virtual {v14}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_12

    invoke-virtual {v14, v1, v2}, Ljava/io/File;->setLastModified(J)Z

    move-result v1

    if-eqz v1, :cond_12

    move v11, v12

    :cond_12
    invoke-static {v11}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-direct {v0, v1, v14}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_2

    return-object v0

    :catch_0
    move-object v14, v13

    goto :goto_8

    :catch_1
    move-object v14, v7

    :catch_2
    :goto_8
    invoke-static {v14}, Lcom/miui/gallery/util/FileUtils;->deleteFile(Ljava/io/File;)Z

    return-object v7
.end method
