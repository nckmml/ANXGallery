.class Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;
.super Lcom/miui/gallery/provider/cloudmanager/CloudManager$CursorTask;
.source "CloudManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/cloudmanager/CloudManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "EditPhotoDateInfoById"
.end annotation


# instance fields
.field protected isFavorites:Z

.field protected mBasicColumns:[Ljava/lang/String;

.field protected mBasicConditionStartPosition:I

.field protected mBasicQuerySelection:Ljava/lang/String;

.field protected mDirtyBulk:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field protected mIdQuerySelection:Ljava/lang/String;

.field protected mPhotoNewTime:J

.field protected mSourcePhotoId:J


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/util/ArrayList;JJZ)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;JJZ)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$CursorTask;-><init>(Landroid/content/Context;Ljava/util/ArrayList;)V

    iput-wide p5, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mPhotoNewTime:J

    iput-object p2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mDirtyBulk:Ljava/util/ArrayList;

    iput-boolean p7, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->isFavorites:Z

    iput-wide p3, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object p1

    invoke-interface {p1}, Lmiui/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/StringBuilder;

    const-string p2, "localFile"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p3, " like \'%s%c\'"

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->length()I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mBasicConditionStartPosition:I

    const-string p3, " AND "

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p3, "localFlag"

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p3, " <> "

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 p3, 0xf

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    iput-object p3, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mBasicQuerySelection:Ljava/lang/String;

    iget p3, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mBasicConditionStartPosition:I

    const/4 p4, 0x0

    const-string p5, "_id = \'%s\'"

    invoke-virtual {p1, p4, p3, p5}, Ljava/lang/StringBuilder;->replace(IILjava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    iput-object p3, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mIdQuerySelection:Ljava/lang/String;

    invoke-static {}, Lmiui/util/Pools;->getStringBuilderPool()Lmiui/util/Pools$Pool;

    move-result-object p3

    invoke-interface {p3, p1}, Lmiui/util/Pools$Pool;->release(Ljava/lang/Object;)V

    const-string p1, "dateTaken"

    const-string p3, "serverId"

    const-string p4, "localGroupId"

    const-string p5, "attributes"

    filled-new-array {p1, p2, p3, p4, p5}, [Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mBasicColumns:[Ljava/lang/String;

    return-void
.end method

.method private logStepEndTime()V
    .locals 3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "CloudManager"

    const-string v2, "[Edit FileInfo] step endTime %d"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method private scannerNewFile(JLjava/io/File;I)J
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mContext:Landroid/content/Context;

    new-instance v1, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    invoke-direct {v1}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;-><init>()V

    invoke-virtual {v1, p1, p2}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->setAlbumId(J)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    invoke-virtual {p1, p3}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->setSaveFile(Ljava/io/File;)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->disableBulkNotify()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->enableInternalScanRequest()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    iget-wide p2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mPhotoNewTime:J

    invoke-virtual {p1, p2, p3}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->setFileLastModifyTime(J)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    invoke-virtual {p1, p4}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->setAlbumAttributes(I)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    const/4 p2, 0x7

    invoke-virtual {p1, p2}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->setLocalFlag(I)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->enableForceParserPhotoDateTime()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->build()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/miui/gallery/scanner/SaveToCloudUtil;->saveToCloudDB(Landroid/content/Context;Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;)J

    move-result-wide p1

    return-wide p1
.end method


# virtual methods
.method protected execute(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)J
    .locals 33

    move-object/from16 v1, p0

    move-object/from16 v0, p1

    move-object/from16 v10, p2

    const-string v11, "_id=?"

    const-string v12, "cloud"

    const-string v13, "CloudManager"

    :try_start_0
    iget-object v2, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    iget-object v3, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    const-string v4, "localFile"

    invoke-interface {v3, v4}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v3

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v2

    const-string v3, "[Edit FileInfo] 1. query source record is success ,sourceFilePath: [%s]"

    invoke-static {v13, v3, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->logStepEndTime()V

    invoke-static {v2}, Lcom/miui/gallery/util/DocumentProviderUtils;->needUseDocumentProvider(Ljava/lang/String;)Z

    move-result v3

    invoke-static {v2}, Lcom/miui/gallery/util/FileMimeUtil;->hasExif(Ljava/lang/String;)Z

    move-result v4

    const-string v5, "[Edit FileInfo] 2. prepare edit PhotoDateTime , now sourceFilePath: [%s] ,isNeedUseDocumentProvider: [%b] ,isExifSupportModification: [%b]"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v6

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v7

    invoke-static {v13, v5, v2, v6, v7}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->logStepEndTime()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_6

    const-wide/16 v5, -0x4

    const/4 v8, 0x1

    if-eqz v4, :cond_1

    :try_start_1
    iget-object v9, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mContext:Landroid/content/Context;

    iget-wide v14, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mPhotoNewTime:J

    invoke-static {v9, v2, v14, v15, v8}, Lcom/miui/gallery/util/GalleryExifUtils;->setImageFileDataTime(Landroid/content/Context;Ljava/lang/String;JZ)Landroid/util/Pair;

    move-result-object v9

    if-eqz v9, :cond_0

    iget-object v14, v9, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v14, Ljava/lang/Boolean;

    invoke-virtual {v14}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v14

    iget-object v9, v9, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v9, Ljava/io/File;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :cond_0
    const/4 v9, 0x0

    const/4 v14, 0x0

    goto :goto_0

    :catch_0
    move-exception v0

    move-object v11, v13

    const/4 v9, 0x0

    goto/16 :goto_8

    :cond_1
    :try_start_2
    new-instance v9, Ljava/io/File;

    new-instance v14, Ljava/io/File;

    invoke-direct {v14, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    iget-wide v7, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mPhotoNewTime:J

    invoke-static {v14, v7, v8}, Lcom/miui/gallery/util/FileUtils;->generateFileDateTimeName(Ljava/io/File;J)Ljava/lang/String;

    move-result-object v7

    invoke-direct {v9, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_6

    :try_start_3
    new-instance v7, Ljava/io/File;

    invoke-direct {v7, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v7, v9}, Lcom/miui/gallery/util/FileUtils;->copyFile(Ljava/io/File;Ljava/io/File;)Z

    move-result v14

    if-nez v14, :cond_2

    invoke-static {v9}, Lcom/miui/gallery/util/FileUtils;->deleteFile(Ljava/io/File;)Z
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_5

    return-wide v5

    :cond_2
    :goto_0
    :try_start_4
    const-string v7, "[Edit FileInfo] 3. edit Photo exif is [%b], now sourceFilePath: [%s] , newFilePath is: [%s]"

    invoke-static {v14}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v8
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_4

    if-eqz v9, :cond_3

    :try_start_5
    invoke-virtual {v9}, Ljava/io/File;->getAbsoluteFile()Ljava/io/File;

    move-result-object v18
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_5

    goto :goto_1

    :cond_3
    :try_start_6
    const-string v18, "edit failed"

    :goto_1
    move-object/from16 v15, v18

    invoke-static {v13, v7, v8, v2, v15}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-virtual {v9}, Ljava/io/File;->exists()Z

    move-result v7
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_4

    if-nez v7, :cond_4

    :try_start_7
    const-string v0, "[Edit FileInfo] 3. edit Photo exif is [%b],but newFile not exists"

    invoke-static {v14}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-static {v13, v0, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_5

    return-wide v5

    :cond_4
    if-eqz v4, :cond_5

    if-eqz v4, :cond_6

    if-nez v14, :cond_6

    :cond_5
    :try_start_8
    invoke-virtual {v9}, Ljava/io/File;->exists()Z

    move-result v4

    if-eqz v4, :cond_13

    iget-wide v7, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mPhotoNewTime:J

    invoke-virtual {v9, v7, v8}, Ljava/io/File;->setLastModified(J)Z

    move-result v4

    if-nez v4, :cond_6

    goto/16 :goto_6

    :cond_6
    invoke-virtual {v9}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v14

    iget-object v4, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    iget-object v5, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    const-string v6, "serverId"

    invoke-interface {v5, v6}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v5

    invoke-interface {v4, v5}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v20

    const-string v4, "[Edit FileInfo] 4. prepare edit source local_flag to LOCAL_FLAG_NOT_USE, now sourceFilePath: [%s] , source ServerId [%s]"

    invoke-static/range {v20 .. v21}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-static {v13, v4, v2, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    new-instance v4, Landroid/content/ContentValues;

    invoke-direct {v4}, Landroid/content/ContentValues;-><init>()V

    const-string v5, "localFlag"

    const/16 v6, 0xf

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-virtual {v4, v5, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const/4 v8, 0x1

    new-array v5, v8, [Ljava/lang/String;

    iget-wide v6, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    invoke-static {v6, v7}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v6

    const/4 v7, 0x0

    aput-object v6, v5, v7

    invoke-virtual {v0, v12, v4, v11, v5}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v4
    :try_end_8
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_8} :catch_4

    int-to-long v4, v4

    const-wide/16 v17, 0x0

    cmp-long v4, v4, v17

    if-gtz v4, :cond_7

    :try_start_9
    const-string v0, "[Edit FileInfo]4. Failed edit Source Local_Flag , SourceCloudId Is [%s]"

    iget-wide v2, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-static {v13, v0, v2}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {v9}, Lcom/miui/gallery/util/FileUtils;->deleteFile(Ljava/io/File;)Z
    :try_end_9
    .catch Ljava/lang/Exception; {:try_start_9 .. :try_end_9} :catch_5

    const-wide/16 v2, -0x10

    return-wide v2

    :cond_7
    :try_start_a
    const-string v4, "[Edit FileInfo] 4. Edit Source LocalFlag Is Success,SourceCloudId Is [%s] , ServerId is [%s]"

    iget-wide v5, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-static/range {v20 .. v21}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    invoke-static {v13, v4, v5, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->logStepEndTime()V

    const-string v4, "[Edit FileInfo] 5. prepare delete source File ,file is [%s]"

    invoke-static {v13, v4, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance v24, Ljava/util/ArrayList;

    invoke-direct/range {v24 .. v24}, Ljava/util/ArrayList;-><init>()V

    new-instance v2, Lcom/miui/gallery/provider/cloudmanager/CloudManager$DeleteFile;

    iget-object v4, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mContext:Landroid/content/Context;

    iget-wide v5, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    const/16 v27, 0x18

    move-object/from16 v22, v2

    move-object/from16 v23, v4

    move-wide/from16 v25, v5

    invoke-direct/range {v22 .. v27}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$DeleteFile;-><init>(Landroid/content/Context;Ljava/util/ArrayList;JI)V

    invoke-virtual {v2, v0, v10}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$DeleteFile;->run(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)J

    move-result-wide v22

    cmp-long v2, v22, v17

    if-lez v2, :cond_12

    invoke-virtual {v9}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_12

    const-string v2, "[Edit FileInfo] 5. delete source File is Success"

    invoke-static {v13, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->logStepEndTime()V

    const-string v2, "[Edit FileInfo] 6. prepare query albumId And attributes"

    invoke-static {v13, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v2, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    iget-object v4, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    const-string v5, "localGroupId"

    invoke-interface {v4, v5}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v4

    invoke-interface {v2, v4}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v5

    iget-object v2, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    iget-object v4, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mCursor:Landroid/database/Cursor;

    const-string v15, "attributes"

    invoke-interface {v4, v15}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v4

    invoke-interface {v2, v4}, Landroid/database/Cursor;->getInt(I)I

    move-result v15

    cmp-long v2, v5, v17

    if-eqz v2, :cond_11

    const-wide/16 v24, -0x1

    cmp-long v2, v5, v24

    if-nez v2, :cond_8

    goto/16 :goto_5

    :cond_8
    const-string v2, "[Edit FileInfo] 6. query albumId And attributes is Success"

    invoke-static {v13, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "[Edit FileInfo] 7. prepare scanner newFile [%s]"

    invoke-static {v13, v2, v14}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-direct {v1, v5, v6, v9, v15}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->scannerNewFile(JLjava/io/File;I)J

    move-result-wide v24

    const-wide/16 v26, -0x2

    cmp-long v2, v24, v26

    if-nez v2, :cond_c

    if-eqz v3, :cond_c

    const-string v2, "[Edit FileInfo] 7. scanner sdcard File [%s] is exist,now delete record"

    invoke-static {v13, v2, v14}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "localFile = \'"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, "\'"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    const-string v3, "cloud"

    const-string v2, "_id"

    filled-new-array {v2}, [Ljava/lang/String;

    move-result-object v19
    :try_end_a
    .catch Ljava/lang/Exception; {:try_start_a .. :try_end_a} :catch_4

    const/16 v24, 0x0

    const/16 v25, 0x0

    const/16 v28, 0x0

    const/16 v29, 0x0

    move-object/from16 v2, p1

    move-object/from16 v30, v4

    move-object/from16 v4, v19

    move-wide/from16 v31, v5

    move-object/from16 v5, v30

    move-object/from16 v6, v24

    move-object/from16 v7, v25

    move-object/from16 v8, v28

    move-object/from16 v19, v13

    move-object/from16 v16, v14

    const/4 v14, 0x0

    move-object v13, v9

    move-object/from16 v9, v29

    :try_start_b
    invoke-virtual/range {v2 .. v9}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v2
    :try_end_b
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_b} :catch_2

    if-eqz v2, :cond_9

    :try_start_c
    invoke-interface {v2}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v3

    if-eqz v3, :cond_9

    const/4 v3, 0x0

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v4

    move-object/from16 v6, v30

    invoke-virtual {v0, v12, v6, v14}, Landroid/database/sqlite/SQLiteDatabase;->delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I

    const/4 v6, 0x1

    new-array v7, v6, [Ljava/lang/String;

    invoke-static {v4, v5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    aput-object v4, v7, v3

    invoke-virtual {v10, v11, v7}, Lcom/miui/gallery/provider/cache/MediaManager;->delete(Ljava/lang/String;[Ljava/lang/String;)I

    goto :goto_2

    :catch_1
    move-exception v0

    move-object v9, v13

    move-object/from16 v11, v19

    goto/16 :goto_8

    :cond_9
    const/4 v3, 0x0

    const/4 v6, 0x1

    :goto_2
    if-eqz v2, :cond_a

    invoke-interface {v2}, Landroid/database/Cursor;->close()V
    :try_end_c
    .catch Ljava/lang/Exception; {:try_start_c .. :try_end_c} :catch_1

    :cond_a
    move-wide/from16 v4, v31

    :try_start_d
    invoke-direct {v1, v4, v5, v13, v15}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->scannerNewFile(JLjava/io/File;I)J

    move-result-wide v4

    const-string v2, "[Edit FileInfo] 7.again scanner sdcard File [%s],result is [%b]"

    cmp-long v7, v4, v26

    if-eqz v7, :cond_b

    move v3, v6

    :cond_b
    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3
    :try_end_d
    .catch Ljava/lang/Exception; {:try_start_d .. :try_end_d} :catch_2

    move-object/from16 v6, v16

    move-object/from16 v11, v19

    :try_start_e
    invoke-static {v11, v2, v6, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    move-wide v7, v4

    goto :goto_3

    :catch_2
    move-exception v0

    move-object/from16 v11, v19

    goto/16 :goto_7

    :cond_c
    move-object v11, v13

    move-object v6, v14

    const/4 v14, 0x0

    move-object v13, v9

    move-wide/from16 v7, v24

    :goto_3
    const-string v2, "[Edit FileInfo] 7. Scanner newFile IsSuccess %s "

    invoke-static {v11, v2, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->logStepEndTime()V

    cmp-long v2, v7, v17

    if-lez v2, :cond_14

    cmp-long v2, v20, v17

    const-wide/16 v15, -0x20

    if-eqz v2, :cond_d

    new-instance v2, Landroid/content/ContentValues;

    invoke-direct {v2}, Landroid/content/ContentValues;-><init>()V

    const-string v3, "[Edit FileInfo] 9. Edit newFile LocalImageId ,sourceFile ServerId  Is %s"

    invoke-static/range {v20 .. v21}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-static {v11, v3, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-string v3, "localImageId"

    iget-wide v4, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v2, v3, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-virtual {v1, v7, v8}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->getIdSelection(J)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v12, v2, v3, v14}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v2

    int-to-long v2, v2

    cmp-long v4, v2, v17

    if-gez v4, :cond_e

    move-wide v2, v15

    goto :goto_4

    :cond_d
    move-wide/from16 v2, v22

    :cond_e
    :goto_4
    iget-boolean v4, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->isFavorites:Z

    if-eqz v4, :cond_10

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v2

    iget-object v5, v1, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mDirtyBulk:Ljava/util/ArrayList;

    const/4 v9, 0x1

    move-object/from16 v3, p1

    move-object/from16 v4, p2

    move-wide v6, v7

    move v8, v9

    invoke-static/range {v2 .. v8}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$800(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Ljava/util/ArrayList;JI)J

    move-result-wide v2

    cmp-long v0, v2, v17

    if-gez v0, :cond_f

    move-wide v2, v15

    :cond_f
    const-string v0, "[Edit FileInfo] 10. addRemoveFavoritesByPath is Success,update or delete count %d"

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-static {v11, v0, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_10
    return-wide v2

    :cond_11
    :goto_5
    const-wide/16 v2, -0x80

    return-wide v2

    :cond_12
    move-object v11, v13

    goto :goto_9

    :cond_13
    :goto_6
    move-object v11, v13

    move-object v13, v9

    const-string v0, "[Edit FileInfo] 3. edit Photo no exif info and edit lastModifytime is Failed,Android Rom version is [%d]"

    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-static {v11, v0, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {v13}, Lcom/miui/gallery/util/FileUtils;->deleteFile(Ljava/io/File;)Z
    :try_end_e
    .catch Ljava/lang/Exception; {:try_start_e .. :try_end_e} :catch_3

    return-wide v5

    :catch_3
    move-exception v0

    :goto_7
    move-object v9, v13

    goto :goto_8

    :catch_4
    move-exception v0

    move-object v11, v13

    move-object v13, v9

    goto :goto_8

    :catch_5
    move-exception v0

    move-object v11, v13

    goto :goto_8

    :catch_6
    move-exception v0

    move-object v11, v13

    const/4 v14, 0x0

    move-object v9, v14

    :goto_8
    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v0

    const-string v2, "[Edit FileInfo] Failed edit ,Error Message Is %s"

    invoke-static {v11, v2, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_9
    move-object v13, v9

    :cond_14
    const-string v0, "[Edit FileInfo] No NewFile And now Delete newFile"

    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {v13}, Lcom/miui/gallery/util/FileUtils;->deleteFile(Ljava/io/File;)Z

    const-wide/16 v2, -0x10

    return-wide v2
.end method

.method protected getFileName(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "."

    invoke-virtual {p1, v0}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v0

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    invoke-virtual {p1, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_1
    :goto_0
    const-string p1, ""

    return-object p1
.end method

.method protected getFilePathSelection(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mBasicQuerySelection:Ljava/lang/String;

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->getFileName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const/4 v2, 0x0

    aput-object p1, v1, v2

    const/16 p1, 0x25

    invoke-static {p1}, Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;

    move-result-object p1

    const/4 v2, 0x1

    aput-object p1, v1, v2

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method protected getIdSelection(J)Ljava/lang/String;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mIdQuerySelection:Ljava/lang/String;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const/4 p2, 0x0

    aput-object p1, v1, p2

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method protected prepare(Landroid/database/sqlite/SQLiteDatabase;)Landroid/database/Cursor;
    .locals 8

    iget-object v2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mBasicColumns:[Ljava/lang/String;

    iget-wide v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->getIdSelection(J)Ljava/lang/String;

    move-result-object v3

    const-string v1, "cloud"

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v0, p1

    invoke-virtual/range {v0 .. v7}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p1

    return-object p1
.end method

.method public toString()Ljava/lang/String;
    .locals 4

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    iget-wide v2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->mSourcePhotoId:J

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const/4 v3, 0x0

    aput-object v2, v1, v3

    const-string v2, "updatePhotoDateTime by id: [%s]"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
