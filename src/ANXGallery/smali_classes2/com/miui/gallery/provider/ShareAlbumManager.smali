.class public Lcom/miui/gallery/provider/ShareAlbumManager;
.super Ljava/lang/Object;
.source "ShareAlbumManager.java"


# direct methods
.method public static getOriginalAlbumId(J)J
    .locals 2

    const-wide/32 v0, 0x7ffe795f

    sub-long/2addr p0, v0

    return-wide p0
.end method

.method public static getShareAlbumInfoTipTextIfNeed(Lcom/miui/gallery/model/Album;)Ljava/lang/String;
    .locals 6

    invoke-static {}, Lcom/miui/widget/util/Pools;->getStringBuilderPool()Lcom/miui/widget/util/Pools$Pool;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/widget/util/Pools$Pool;->acquire()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    :try_start_0
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->isOtherShareAlbum()Z

    move-result v1

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-eqz v1, :cond_1

    invoke-static {}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getInstance()Lcom/miui/gallery/share/ShareAlbumCacheManager;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v4

    invoke-virtual {v1, v4, v5}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getShareAlbum(J)Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;

    move-result-object v1

    if-eqz v1, :cond_3

    invoke-virtual {v1}, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->getOwnerName()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_3

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->isBabyAlbum()Z

    move-result p0

    if-eqz p0, :cond_0

    const p0, 0x7f100069

    new-array v3, v3, [Ljava/lang/Object;

    invoke-virtual {v1}, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->getOwnerName()Ljava/lang/String;

    move-result-object v1

    aput-object v1, v3, v2

    invoke-static {p0, v3}, Lcom/miui/gallery/util/ResourceUtils;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_0
    const p0, 0x7f10006a

    new-array v3, v3, [Ljava/lang/Object;

    invoke-virtual {v1}, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->getOwnerName()Ljava/lang/String;

    move-result-object v1

    aput-object v1, v3, v2

    invoke-static {p0, v3}, Lcom/miui/gallery/util/ResourceUtils;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->isBabyAlbum()Z

    move-result v1

    if-eqz v1, :cond_2

    const p0, 0x7f100080

    invoke-static {p0}, Lcom/miui/gallery/util/ResourceUtils;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_2
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->isOwnerShareAlbum()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-static {}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getInstance()Lcom/miui/gallery/share/ShareAlbumCacheManager;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v4

    invoke-virtual {v1, v4, v5}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getShareAlbum(J)Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;

    move-result-object p0

    if-eqz p0, :cond_3

    const v1, 0x7f0e000f

    iget v4, p0, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->mUserCount:I

    new-array v3, v3, [Ljava/lang/Object;

    iget p0, p0, Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;->mUserCount:I

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    aput-object p0, v3, v2

    invoke-static {v1, v4, v3}, Lcom/miui/gallery/util/ResourceUtils;->getQuantityString(II[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    :goto_0
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    invoke-static {}, Lcom/miui/widget/util/Pools;->getStringBuilderPool()Lcom/miui/widget/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lcom/miui/widget/util/Pools$Pool;->release(Ljava/lang/Object;)V

    return-object p0

    :catchall_0
    move-exception p0

    invoke-static {}, Lcom/miui/widget/util/Pools;->getStringBuilderPool()Lcom/miui/widget/util/Pools$Pool;

    move-result-object v1

    invoke-interface {v1, v0}, Lcom/miui/widget/util/Pools$Pool;->release(Ljava/lang/Object;)V

    throw p0
.end method

.method public static getUniformAlbumId(J)J
    .locals 2

    const-wide/32 v0, 0x7ffe795f

    add-long/2addr p0, v0

    return-wide p0
.end method

.method public static isOtherShareAlbumId(J)Z
    .locals 2

    const-wide/32 v0, 0x7ffe795f

    cmp-long v0, p0, v0

    if-ltz v0, :cond_0

    const-wide/32 v0, 0x7ffffff5

    cmp-long p0, p0, v0

    if-gez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static query(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    .locals 10

    invoke-static {p0}, Lcom/miui/account/AccountHelper;->getXiaomiAccount(Landroid/content/Context;)Landroid/accounts/Account;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    iget-object v0, v0, Landroid/accounts/Account;->name:Ljava/lang/String;

    aput-object v0, v1, v2

    const/4 v0, 0x1

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    const v2, 0x7f10006b

    invoke-virtual {p0, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/database/DatabaseUtils;->sqlEscapeString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    aput-object p0, v1, v0

    const-string p0, "(SELECT * FROM (SELECT 2147383647+_id AS _id, creatorId, albumId, serverId, nickname FROM shareAlbum LEFT JOIN (SELECT user_id AS creatorId, miliao_nick AS nickname FROM userInfo) USING (creatorId)) LEFT JOIN (SELECT COUNT(*) AS count, albumId FROM shareUser GROUP BY albumId) USING (albumId) UNION SELECT * FROM (SELECT * FROM (SELECT _id, \'%s\' AS creatorId, serverId AS albumId, serverId FROM cloud WHERE (serverType=0)) LEFT JOIN (SELECT user_id AS creatorId, %s AS nickname FROM userInfo) USING (creatorId)) LEFT JOIN (SELECT COUNT(*) AS count, albumId FROM cloudUser GROUP BY albumId) USING (albumId))"

    invoke-static {p0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object v2, p1

    move-object v4, p2

    move-object v5, p3

    move-object v6, p4

    move-object v9, p5

    invoke-virtual/range {v2 .. v9}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p0

    return-object p0
.end method
