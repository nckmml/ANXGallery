.class Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;
.super Lcom/miui/gallery/provider/cloudmanager/CloudManager$CursorTask;
.source "CloudManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/cloudmanager/CloudManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "MediaConflict"
.end annotation


# instance fields
.field private mAlbumId:J

.field private mFileName:Ljava/lang/String;

.field private mMediaId:J

.field private mSha1:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/lang/String;JJLjava/lang/String;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$CursorTask;-><init>(Landroid/content/Context;Ljava/util/ArrayList;)V

    iput-object p2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mFileName:Ljava/lang/String;

    iput-wide p3, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mAlbumId:J

    iput-wide p5, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mMediaId:J

    iput-object p7, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mSha1:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method protected execute(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)J
    .locals 2

    iget-wide p1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mMediaId:J

    invoke-static {p1, p2}, Lcom/miui/gallery/provider/ShareMediaManager;->isOtherShareMediaId(J)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p1, p2}, Lcom/miui/gallery/provider/ShareMediaManager;->getOriginalMediaId(J)J

    move-result-wide p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mCursor:Landroid/database/Cursor;

    const/4 v1, 0x0

    invoke-interface {v0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v0

    cmp-long p1, v0, p1

    if-nez p1, :cond_1

    const-wide/16 p1, -0x67

    return-wide p1

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mCursor:Landroid/database/Cursor;

    const/4 p2, 0x1

    invoke-interface {p1, p2}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mSha1:Ljava/lang/String;

    invoke-static {p1, p2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p2

    if-eqz p2, :cond_2

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_2

    const-wide/16 p1, -0x68

    return-wide p1

    :cond_2
    const-wide/16 p1, -0x69

    return-wide p1
.end method

.method protected prepare(Landroid/database/sqlite/SQLiteDatabase;)Landroid/database/Cursor;
    .locals 17

    move-object/from16 v0, p0

    iget-wide v1, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mAlbumId:J

    invoke-static {v1, v2}, Lcom/miui/gallery/provider/ShareAlbumManager;->isOtherShareAlbumId(J)Z

    move-result v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    const/4 v4, 0x2

    const-string v5, "sha1"

    const-string v6, "_id"

    if-eqz v1, :cond_0

    iget-wide v7, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mAlbumId:J

    invoke-static {v7, v8}, Lcom/miui/gallery/provider/ShareAlbumManager;->getOriginalAlbumId(J)J

    move-result-wide v7

    filled-new-array {v6, v5}, [Ljava/lang/String;

    move-result-object v11

    new-array v13, v4, [Ljava/lang/String;

    iget-object v1, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mFileName:Ljava/lang/String;

    aput-object v1, v13, v3

    invoke-static {v7, v8}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v1

    aput-object v1, v13, v2

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const-string v10, "shareImage"

    const-string v12, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\')) AND fileName = ? AND localGroupId=?"

    move-object/from16 v9, p1

    invoke-virtual/range {v9 .. v16}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v1

    return-object v1

    :cond_0
    filled-new-array {v6, v5}, [Ljava/lang/String;

    move-result-object v1

    new-array v6, v4, [Ljava/lang/String;

    iget-object v4, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mFileName:Ljava/lang/String;

    aput-object v4, v6, v3

    iget-wide v3, v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mAlbumId:J

    invoke-static {v3, v4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v6, v2

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const-string v3, "cloud"

    const-string v5, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\')) AND fileName = ? AND localGroupId=?"

    move-object/from16 v2, p1

    move-object v4, v1

    invoke-virtual/range {v2 .. v9}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v1

    return-object v1
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, ".Conflict{"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mFileName:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "}"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected verify(Landroid/database/sqlite/SQLiteDatabase;)J
    .locals 4

    invoke-super {p0, p1}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$CursorTask;->verify(Landroid/database/sqlite/SQLiteDatabase;)J

    move-result-wide v0

    const-wide/16 v2, -0x1

    cmp-long p1, v0, v2

    if-eqz p1, :cond_0

    return-wide v0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->mFileName:Ljava/lang/String;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MediaConflict;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v0, "CloudManager"

    const-string v1, "%s\'s fileName is empty, so no conflict"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-wide/16 v0, -0x66

    return-wide v0

    :cond_1
    return-wide v2
.end method
