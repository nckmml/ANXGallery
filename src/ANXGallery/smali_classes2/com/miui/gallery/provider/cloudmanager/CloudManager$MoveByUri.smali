.class Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;
.super Lcom/miui/gallery/provider/cloudmanager/CloudManager$BaseCopyOrMoveByUri;
.source "CloudManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/cloudmanager/CloudManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "MoveByUri"
.end annotation


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/util/ArrayList;Landroid/net/Uri;J)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;",
            "Landroid/net/Uri;",
            "J)V"
        }
    .end annotation

    invoke-direct/range {p0 .. p5}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$BaseCopyOrMoveByUri;-><init>(Landroid/content/Context;Ljava/util/ArrayList;Landroid/net/Uri;J)V

    return-void
.end method


# virtual methods
.method protected execute(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)J
    .locals 12

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mCursor:Landroid/database/Cursor;

    const-wide/16 v1, 0x0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mCursor:Landroid/database/Cursor;

    invoke-interface {v0}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mCursor:Landroid/database/Cursor;

    const/4 v3, 0x0

    invoke-interface {v0, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v3

    move-wide v8, v3

    goto :goto_0

    :cond_0
    move-wide v8, v1

    :goto_0
    cmp-long v0, v8, v1

    if-lez v0, :cond_2

    new-instance v0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;

    iget-object v6, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mContext:Landroid/content/Context;

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->getDirtyBulk()Ljava/util/ArrayList;

    move-result-object v7

    iget-wide v10, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mAlbumId:J

    move-object v5, v0

    invoke-direct/range {v5 .. v11}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;-><init>(Landroid/content/Context;Ljava/util/ArrayList;JJ)V

    invoke-virtual {v0, p1, p2}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$Move;->run(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)J

    move-result-wide p1

    cmp-long v0, p1, v1

    if-lez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mContext:Landroid/content/Context;

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->getDirtyBulk()Ljava/util/ArrayList;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/provider/cloudmanager/FileHandleService;->dispatchTask(Landroid/content/Context;Ljava/util/List;)Z

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->getDirtyBulk()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    :cond_1
    return-wide p1

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    new-instance p2, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;

    new-instance v4, Ljava/io/File;

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mTarPath:Ljava/lang/String;

    invoke-direct {v4, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    iget-wide v5, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mAlbumId:J

    const/16 v7, 0x8

    const/4 v8, 0x1

    move-object v3, p2

    invoke-direct/range {v3 .. v8}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;-><init>(Ljava/io/File;JIZ)V

    invoke-static {p1, p2}, Lcom/miui/gallery/scanner/SaveToCloudUtil;->saveToCloudDB(Landroid/content/Context;Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;)J

    move-result-wide p1

    cmp-long v0, p1, v1

    if-lez v0, :cond_3

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->markAsDirty(J)V

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mContext:Landroid/content/Context;

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->getDirtyBulk()Ljava/util/ArrayList;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/provider/cloudmanager/FileHandleService;->dispatchTask(Landroid/content/Context;Ljava/util/List;)Z

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->getDirtyBulk()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    return-wide p1

    :cond_3
    const-wide/16 v0, -0x2

    cmp-long v0, p1, v0

    if-nez v0, :cond_4

    const-wide/16 p1, -0x67

    return-wide p1

    :cond_4
    const-wide/16 v0, -0x1

    cmp-long p1, p1, v0

    const-wide/16 v0, -0x65

    if-nez p1, :cond_5

    :cond_5
    return-wide v0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    iget-object v1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mUri:Landroid/net/Uri;

    invoke-virtual {v1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$MoveByUri;->mAlbumId:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x1

    aput-object v1, v0, v2

    const-string v1, "MoveByUri{%s, %d}"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
