.class Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;
.super Lcom/miui/gallery/provider/cloudmanager/CloudManager$Media;
.source "CloudManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/cloudmanager/CloudManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "Copy"
.end annotation


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/util/ArrayList;JJ)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;JJ)V"
        }
    .end annotation

    invoke-direct/range {p0 .. p6}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Media;-><init>(Landroid/content/Context;Ljava/util/ArrayList;JJ)V

    invoke-static {p5, p6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-static {p3, p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    const-string p3, "CloudManager"

    const-string p4, "Copy albumId %d mediaId %d"

    invoke-static {p3, p4, p1, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method


# virtual methods
.method protected execute(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)J
    .locals 18

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->toString()Ljava/lang/String;

    move-result-object v3

    const-string v4, "CloudManager"

    const-string v5, "%s execute"

    invoke-static {v4, v5, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$200()[Ljava/lang/String;

    move-result-object v3

    iget-object v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mCursor:Landroid/database/Cursor;

    invoke-static {v3, v5}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$300([Ljava/lang/String;Landroid/database/Cursor;)Landroid/content/ContentValues;

    move-result-object v3

    iget-object v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mCursor:Landroid/database/Cursor;

    const/4 v6, 0x2

    invoke-interface {v5, v6}, Landroid/database/Cursor;->getInt(I)I

    move-result v5

    const-wide/16 v7, -0x65

    const/4 v9, 0x6

    invoke-static {v9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    const/16 v11, 0x9

    invoke-static {v11}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v12

    const-string v13, "localFlag"

    if-eqz v5, :cond_0

    const/4 v14, 0x5

    if-eq v5, v14, :cond_0

    if-eq v5, v9, :cond_0

    if-eq v5, v11, :cond_0

    const-string v5, "no server id, just insert as manual create"

    invoke-static {v4, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v5, -0x2

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v3, v13, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    goto :goto_0

    :cond_0
    const-string v9, "has server id, just insert as copy"

    invoke-static {v4, v9}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    if-nez v5, :cond_2

    iget-wide v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v5, v6}, Lcom/miui/gallery/provider/ShareMediaManager;->isOtherShareMediaId(J)Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-virtual {v3, v13, v12}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    goto :goto_0

    :cond_1
    invoke-virtual {v3, v13, v10}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    goto :goto_0

    :cond_2
    iget-object v9, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mCursor:Landroid/database/Cursor;

    const/16 v14, 0x1a

    invoke-interface {v9, v14}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v9

    invoke-static {v9}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v14

    if-eqz v14, :cond_3

    const-string v1, "not synced but localImageId is null"

    invoke-static {v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-wide v7

    :cond_3
    invoke-static {v9}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v14

    iput-wide v14, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    if-ne v5, v11, :cond_4

    iget-wide v14, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v14, v15}, Lcom/miui/gallery/provider/ShareMediaManager;->convertToMediaId(J)J

    move-result-wide v14

    iput-wide v14, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    :cond_4
    iget-object v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mCursor:Landroid/database/Cursor;

    invoke-static {v5}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    invoke-virtual/range {p0 .. p1}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->prepare(Landroid/database/sqlite/SQLiteDatabase;)Landroid/database/Cursor;

    move-result-object v5

    iput-object v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mCursor:Landroid/database/Cursor;

    invoke-virtual/range {p0 .. p1}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->verify(Landroid/database/sqlite/SQLiteDatabase;)J

    move-result-wide v14

    const-wide/16 v16, -0x1

    cmp-long v5, v14, v16

    if-eqz v5, :cond_5

    return-wide v14

    :cond_5
    iget-object v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mCursor:Landroid/database/Cursor;

    invoke-interface {v5, v6}, Landroid/database/Cursor;->getInt(I)I

    move-result v5

    if-eqz v5, :cond_6

    const-string v1, "backtrack not synced"

    invoke-static {v4, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-wide v7

    :cond_6
    iget-wide v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v5, v6}, Lcom/miui/gallery/provider/ShareMediaManager;->isOtherShareMediaId(J)Z

    move-result v5

    if-eqz v5, :cond_7

    invoke-virtual {v3, v13, v12}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    goto :goto_0

    :cond_7
    invoke-virtual {v3, v13, v10}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    :goto_0
    invoke-static {}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$400()[Ljava/lang/String;

    move-result-object v5

    iget-object v6, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mCursor:Landroid/database/Cursor;

    invoke-static {v5, v6}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$300([Ljava/lang/String;Landroid/database/Cursor;)Landroid/content/ContentValues;

    move-result-object v5

    invoke-virtual {v3, v5}, Landroid/content/ContentValues;->putAll(Landroid/content/ContentValues;)V

    iget-wide v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v5, v6}, Lcom/miui/gallery/provider/ShareMediaManager;->isOtherShareMediaId(J)Z

    move-result v5

    const-string v6, "shareImage"

    const-string v9, "cloud"

    const/4 v10, 0x0

    const-string v11, "localImageId"

    const-string v12, "localGroupId"

    if-eqz v5, :cond_9

    iget-wide v13, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v13, v14}, Lcom/miui/gallery/provider/ShareMediaManager;->getOriginalMediaId(J)J

    move-result-wide v13

    iget-wide v7, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v7, v8}, Lcom/miui/gallery/provider/ShareAlbumManager;->isOtherShareAlbumId(J)Z

    move-result v5

    if-eqz v5, :cond_8

    iget-wide v7, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v7, v8}, Lcom/miui/gallery/provider/ShareAlbumManager;->getOriginalAlbumId(J)J

    move-result-wide v7

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v3, v12, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-static {v13, v14}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v3, v11, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-virtual {v1, v6, v10, v3}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    const-string v6, "Copy other share to other share id %d"

    invoke-static {v4, v6, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_2

    :cond_8
    iget-wide v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v3, v12, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-static {v13, v14}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v3, v11, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-virtual {v1, v9, v10, v3}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    move-result-wide v5

    invoke-virtual {v2, v5, v6, v3}, Lcom/miui/gallery/provider/cache/MediaManager;->insert(JLandroid/content/ContentValues;)J

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "Copy other share to owner id %d"

    invoke-static {v4, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_1

    :cond_9
    iget-wide v7, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v7, v8}, Lcom/miui/gallery/provider/ShareAlbumManager;->isOtherShareAlbumId(J)Z

    move-result v5

    if-eqz v5, :cond_a

    iget-wide v7, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v7, v8}, Lcom/miui/gallery/provider/ShareAlbumManager;->getOriginalAlbumId(J)J

    move-result-wide v7

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v3, v12, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v7, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v3, v11, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-virtual {v1, v6, v10, v3}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    const-string v6, "Copy owner to other share id %d"

    invoke-static {v4, v6, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_2

    :cond_a
    iget-wide v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v3, v12, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v5, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v3, v11, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-virtual {v1, v9, v10, v3}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    move-result-wide v5

    invoke-virtual {v2, v5, v6, v3}, Lcom/miui/gallery/provider/cache/MediaManager;->insert(JLandroid/content/ContentValues;)J

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "Copy owner to owner id %d"

    invoke-static {v4, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_1
    move-wide v1, v5

    :goto_2
    invoke-static {v3}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$100(Landroid/content/ContentValues;)Landroid/content/ContentValues;

    move-result-object v3

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    const-string v6, "inserts: %s COPY RESULT: %d"

    invoke-static {v4, v6, v3, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    const-wide/16 v3, 0x0

    cmp-long v3, v1, v3

    if-lez v3, :cond_c

    iget-wide v3, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v3, v4}, Lcom/miui/gallery/provider/ShareAlbumManager;->isOtherShareAlbumId(J)Z

    move-result v3

    if-eqz v3, :cond_b

    invoke-static {v1, v2}, Lcom/miui/gallery/provider/ShareMediaManager;->convertToMediaId(J)J

    move-result-wide v1

    :cond_b
    move-wide v7, v1

    invoke-virtual {v0, v7, v8}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->markAsDirty(J)V

    move-wide v15, v7

    goto :goto_3

    :cond_c
    const-wide/16 v15, -0x65

    :goto_3
    return-wide v15
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    iget-wide v1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mMediaId:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x0

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Copy;->mAlbumId:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x1

    aput-object v1, v0, v2

    const-string v1, "Copy{%d, %d}"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
