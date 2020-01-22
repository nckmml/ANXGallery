.class Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;
.super Lcom/miui/gallery/provider/cloudmanager/CloudManager$Media;
.source "CloudManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/cloudmanager/CloudManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "Move"
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

    invoke-static {p3, p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-static {p5, p6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    const-string p3, "CloudManager"

    const-string p4, "Move mediaId %d albumId %d"

    invoke-static {p3, p4, p1, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method


# virtual methods
.method protected execute(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)J
    .locals 16

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    iget-object v3, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mCursor:Landroid/database/Cursor;

    const/4 v4, 0x2

    invoke-interface {v3, v4}, Landroid/database/Cursor;->getInt(I)I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    const-string v5, "CloudManager"

    const-string v6, "current LOCAL_FLAG is %d"

    invoke-static {v5, v6, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-string v4, "localGroupId"

    const-string v6, "cloud"

    const-string v7, "_id=?"

    const/4 v8, 0x0

    const/4 v9, 0x1

    if-eqz v3, :cond_1

    new-instance v3, Landroid/content/ContentValues;

    invoke-direct {v3}, Landroid/content/ContentValues;-><init>()V

    iget-wide v10, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mAlbumId:J

    invoke-static {v10, v11}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v10

    invoke-virtual {v3, v4, v10}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    new-array v4, v9, [Ljava/lang/String;

    iget-wide v10, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v10, v11}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v10

    aput-object v10, v4, v8

    invoke-virtual {v1, v6, v3, v7, v4}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    const-string v6, "MOVE FINISH: local item, result(%d)"

    invoke-static {v5, v6, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    if-lez v1, :cond_0

    new-array v1, v9, [Ljava/lang/String;

    iget-wide v4, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v4, v5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    aput-object v4, v1, v8

    invoke-virtual {v2, v7, v1, v3}, Lcom/miui/gallery/provider/cache/MediaManager;->update(Ljava/lang/String;[Ljava/lang/String;Landroid/content/ContentValues;)I

    iget-wide v1, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->markAsDirty(J)V

    :cond_0
    iget-wide v1, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    return-wide v1

    :cond_1
    new-instance v3, Landroid/content/ContentValues;

    invoke-direct {v3}, Landroid/content/ContentValues;-><init>()V

    iget-object v10, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mCursor:Landroid/database/Cursor;

    invoke-interface {v10, v9}, Landroid/database/Cursor;->getInt(I)I

    move-result v10

    int-to-long v10, v10

    invoke-static {v10, v11}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$000(J)Z

    move-result v10

    const-string v11, "updates: %s"

    const-string v12, "localImageId"

    const/4 v13, 0x3

    const-string v14, "fromLocalGroupId"

    const/4 v15, 0x5

    const-string v8, "localFlag"

    if-eqz v10, :cond_3

    const-string v10, "system album:"

    invoke-static {v5, v10}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {v15}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    invoke-virtual {v3, v8, v10}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-object v8, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mCursor:Landroid/database/Cursor;

    invoke-interface {v8, v13}, Landroid/database/Cursor;->getInt(I)I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v3, v14, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-wide v13, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mAlbumId:J

    invoke-static {v13, v14}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v3, v4, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    const-string v4, "groupId"

    invoke-virtual {v3, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    invoke-virtual {v3, v12}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    invoke-static {v3}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$100(Landroid/content/ContentValues;)Landroid/content/ContentValues;

    move-result-object v4

    invoke-static {v5, v11, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-array v4, v9, [Ljava/lang/String;

    iget-wide v10, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v10, v11}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v8

    const/4 v10, 0x0

    aput-object v8, v4, v10

    invoke-virtual {v1, v6, v3, v7, v4}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    const-string v6, "MOVE FINISH: system album item, result(%d)"

    invoke-static {v5, v6, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    if-lez v1, :cond_2

    new-array v1, v9, [Ljava/lang/String;

    iget-wide v4, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v4, v5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    aput-object v4, v1, v10

    invoke-virtual {v2, v7, v1, v3}, Lcom/miui/gallery/provider/cache/MediaManager;->update(Ljava/lang/String;[Ljava/lang/String;Landroid/content/ContentValues;)I

    iget-wide v1, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->markAsDirty(J)V

    :cond_2
    iget-wide v1, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    goto/16 :goto_1

    :cond_3
    const-string v10, "cloud album:"

    invoke-static {v5, v10}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/16 v10, 0xb

    invoke-static {v10}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    invoke-virtual {v3, v8, v10}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    new-instance v10, Landroid/content/ContentValues;

    invoke-direct {v10}, Landroid/content/ContentValues;-><init>()V

    invoke-static {v15}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v15

    invoke-virtual {v10, v8, v15}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    invoke-static {}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$200()[Ljava/lang/String;

    move-result-object v8

    iget-object v15, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mCursor:Landroid/database/Cursor;

    invoke-static {v8, v15}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$300([Ljava/lang/String;Landroid/database/Cursor;)Landroid/content/ContentValues;

    move-result-object v8

    invoke-virtual {v10, v8}, Landroid/content/ContentValues;->putAll(Landroid/content/ContentValues;)V

    invoke-static {}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$400()[Ljava/lang/String;

    move-result-object v8

    iget-object v15, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mCursor:Landroid/database/Cursor;

    invoke-static {v8, v15}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$300([Ljava/lang/String;Landroid/database/Cursor;)Landroid/content/ContentValues;

    move-result-object v8

    invoke-virtual {v10, v8}, Landroid/content/ContentValues;->putAll(Landroid/content/ContentValues;)V

    iget-object v8, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mCursor:Landroid/database/Cursor;

    invoke-interface {v8, v13}, Landroid/database/Cursor;->getInt(I)I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v10, v14, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-wide v13, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mAlbumId:J

    invoke-static {v13, v14}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v10, v4, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v13, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v13, v14}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v10, v12, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-static {v10}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$100(Landroid/content/ContentValues;)Landroid/content/ContentValues;

    move-result-object v4

    const-string v8, "inserts: %s"

    invoke-static {v5, v8, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const/4 v4, 0x0

    invoke-virtual {v1, v6, v4, v10}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    move-result-wide v12

    const-wide/16 v14, 0x0

    cmp-long v4, v12, v14

    if-lez v4, :cond_5

    invoke-virtual {v2, v12, v13, v10}, Lcom/miui/gallery/provider/cache/MediaManager;->insert(JLandroid/content/ContentValues;)J

    invoke-virtual {v0, v12, v13}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->markAsDirty(J)V

    invoke-static {v3}, Lcom/miui/gallery/provider/cloudmanager/CloudManager;->access$100(Landroid/content/ContentValues;)Landroid/content/ContentValues;

    move-result-object v4

    invoke-static {v5, v11, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-array v4, v9, [Ljava/lang/String;

    iget-wide v10, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v10, v11}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v8

    const/4 v10, 0x0

    aput-object v8, v4, v10

    invoke-virtual {v1, v6, v3, v7, v4}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v8

    if-lez v8, :cond_4

    new-array v1, v9, [Ljava/lang/String;

    iget-wide v3, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v3, v4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v10

    invoke-virtual {v2, v7, v1}, Lcom/miui/gallery/provider/cache/MediaManager;->delete(Ljava/lang/String;[Ljava/lang/String;)I

    iget-wide v1, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->markAsDirty(J)V

    :cond_4
    move-wide v1, v12

    goto :goto_0

    :cond_5
    const/4 v10, 0x0

    const-wide/16 v1, -0x65

    move v8, v10

    :goto_0
    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    const-string v6, "MOVE FINISH: cloud album item, results(update:%d; insert:%d)"

    invoke-static {v5, v6, v3, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    :goto_1
    return-wide v1
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    iget-wide v1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x0

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mAlbumId:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x1

    aput-object v1, v0, v2

    const-string v1, "Move{%d, %d}"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected verify(Landroid/database/sqlite/SQLiteDatabase;)J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->mMediaId:J

    invoke-static {v0, v1}, Lcom/miui/gallery/provider/ShareMediaManager;->isOtherShareMediaId(J)Z

    move-result v0

    if-eqz v0, :cond_0

    const-string p1, "CloudManager"

    const-string v0, "Illegal operate: move share media"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    const-wide/16 v0, -0x72

    return-wide v0

    :cond_0
    invoke-super {p0, p1}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Media;->verify(Landroid/database/sqlite/SQLiteDatabase;)J

    move-result-wide v0

    return-wide v0
.end method
