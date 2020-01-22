.class public abstract Lcom/miui/gallery/cloud/SyncCloudBase;
.super Lcom/miui/gallery/cloud/SyncFromServer;
.source "SyncCloudBase.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/gallery/cloud/SyncFromServer;-><init>(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;)V

    return-void
.end method

.method private final getLargerTags(J)Ljava/util/Set;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    invoke-static {}, Lcom/google/common/collect/Sets;->newHashSet()Ljava/util/HashSet;

    move-result-object v0

    const/4 v1, 0x0

    :try_start_0
    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v2

    const/16 v3, 0x64

    invoke-static {v2, v3}, Lcom/miui/gallery/util/UriUtil;->appendLimit(Landroid/net/Uri;I)Landroid/net/Uri;

    move-result-object v5

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncCloudBase;->mContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v4

    const-string v2, "serverTag"

    filled-new-array {v2}, [Ljava/lang/String;

    move-result-object v6

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "serverTag > "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x0

    const-string v9, "serverTag ASC "

    invoke-virtual/range {v4 .. v9}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v1

    if-eqz v1, :cond_0

    :goto_0
    invoke-interface {v1}, Landroid/database/Cursor;->moveToNext()Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    invoke-interface {v1, p1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :cond_0
    if-eqz v1, :cond_1

    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_1
    return-object v0

    :catchall_0
    move-exception p1

    if-eqz v1, :cond_2

    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_2
    throw p1
.end method

.method private final handleCustom(Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V
    .locals 11
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    invoke-static {p2}, Lcom/miui/gallery/cloud/CloudUtils;->getContentValuesForAll(Lorg/json/JSONObject;)Landroid/content/ContentValues;

    move-result-object v0

    const-string v1, "editedColumns"

    invoke-virtual {v0, v1}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    invoke-static {v0, p1}, Lcom/miui/gallery/cloud/CloudUtils;->reviseAttributes(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    const-string v1, "serverType"

    invoke-virtual {v0, v1}, Landroid/content/ContentValues;->getAsInteger(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    const/4 v2, 0x2

    const/4 v3, 0x1

    const-string v4, "sha1"

    if-eq v1, v3, :cond_3

    if-ne v1, v2, :cond_0

    goto :goto_0

    :cond_0
    if-nez v1, :cond_8

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v5

    const-string v6, "localFile"

    invoke-virtual {v0, v6}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v8

    if-nez v8, :cond_2

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v8

    if-nez v8, :cond_2

    invoke-virtual {v5, v7}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v8

    if-nez v8, :cond_2

    const-string v8, ".cloud/owner"

    invoke-virtual {v7, v8}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_1

    invoke-virtual {v0, v6}, Landroid/content/ContentValues;->remove(Ljava/lang/String;)V

    :cond_1
    invoke-static {v5, v7}, Lcom/miui/gallery/cloud/SyncCloudBase;->statSuspiciousAlbumPathModify(Ljava/lang/String;Ljava/lang/String;)V

    :cond_2
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p0, v5, v0}, Lcom/miui/gallery/cloud/SyncCloudBase;->updateLocalGroupIdInGroup(Ljava/lang/String;Landroid/content/ContentValues;)V

    goto/16 :goto_3

    :cond_3
    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getLocalGroupId(Landroid/content/ContentValues;)Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_4

    invoke-direct {p0, v0, p2}, Lcom/miui/gallery/cloud/SyncCloudBase;->logForNullLocalGroupId(Landroid/content/ContentValues;Lorg/json/JSONObject;)V

    goto :goto_1

    :cond_4
    const-string v6, "localGroupId"

    invoke-virtual {v0, v6, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    :goto_1
    invoke-static {v0, p1}, Lcom/miui/gallery/cloud/CloudUtils;->reviseSpecialTypeFlags(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getSha1()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0, v4}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_8

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getGroupId()J

    move-result-wide v5

    const-string v7, "groupId"

    invoke-virtual {v0, v7}, Landroid/content/ContentValues;->getAsLong(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/Long;->longValue()J

    move-result-wide v8

    cmp-long v5, v5, v8

    if-eqz v5, :cond_8

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v5

    iget-object v6, p0, Lcom/miui/gallery/cloud/SyncCloudBase;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v7}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    const-string v9, "serverId"

    invoke-static {v5, v6, v9, v8}, Lcom/miui/gallery/cloud/CloudUtils;->getItem(Landroid/net/Uri;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/data/DBImage;

    move-result-object v5

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v6

    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_5

    const/4 v5, 0x0

    goto :goto_2

    :cond_5
    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Lcom/miui/gallery/util/StorageUtils;->getPathInPrimaryStorage(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    :goto_2
    const-string v6, "SyncCloudBase"

    if-eqz v5, :cond_7

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lcom/miui/gallery/util/FileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_6

    new-instance v8, Ljava/io/File;

    invoke-direct {v8, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    new-instance v9, Ljava/io/File;

    invoke-static {v7}, Lcom/miui/gallery/util/FileUtils;->getFileName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    invoke-direct {v9, v5, v10}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {v8, v9}, Lcom/miui/gallery/util/FileUtils;->move(Ljava/io/File;Ljava/io/File;)Z

    move-result v8

    invoke-static {v8}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v8

    const-string v9, "move local file %s while updating group id, result %s"

    invoke-static {v6, v9, v7, v8}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_6
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getThumbnailFile()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lcom/miui/gallery/util/FileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_8

    new-instance v8, Ljava/io/File;

    invoke-direct {v8, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    new-instance v9, Ljava/io/File;

    invoke-static {v7}, Lcom/miui/gallery/util/FileUtils;->getFileName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    invoke-direct {v9, v5, v10}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {v8, v9}, Lcom/miui/gallery/util/FileUtils;->move(Ljava/io/File;Ljava/io/File;)Z

    move-result v5

    invoke-static {v5}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v5

    const-string v8, "move thumb file %s while updating group id, result %s"

    invoke-static {v6, v8, v7, v5}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_3

    :cond_7
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getGroupId()J

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v0, v7}, Landroid/content/ContentValues;->getAsLong(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v7

    const-string v8, "get dst folder error while updating group id, old %s, new %s"

    invoke-static {v6, v8, v5, v7}, Lcom/miui/gallery/util/SyncLog;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_8
    :goto_3
    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v5

    invoke-static {v5, v0, p1}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDBForSync(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v5

    invoke-static {v5, v0, p1}, Lcom/miui/gallery/cloud/CloudUtils;->updateFilePathForSync(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    if-eq v1, v3, :cond_9

    if-ne v1, v2, :cond_a

    :cond_9
    invoke-virtual {v0, v4}, Landroid/content/ContentValues;->containsKey(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_a

    const-string p1, "isFavorite"

    invoke-virtual {p2, p1}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-virtual {v0, v4}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p2, p1}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleFavoriteInfo(Lorg/json/JSONObject;Ljava/lang/String;)V

    :cond_a
    return-void
.end method

.method private final handleDelete(Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncCloudBase;->mContext:Landroid/content/Context;

    invoke-static {v0, v1, p1, p2}, Lcom/miui/gallery/cloud/DeleteCloudItem;->updateForDeleteOrPurgedOnLocal(Landroid/net/Uri;Landroid/content/Context;Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V

    return-void
.end method

.method static handleNewGroup(Landroid/content/Context;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;)Ljava/lang/String;
    .locals 12
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    invoke-static {p3}, Lcom/miui/gallery/cloud/CloudUtils;->isUmodifyAlbum(Ljava/lang/String;)Z

    move-result v0

    const-string v1, "_"

    const/4 v2, 0x0

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const-string v3, "localFlag"

    const-string v4, "fileName"

    const/4 v5, 0x0

    if-eqz v0, :cond_4

    invoke-static {p3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/miui/gallery/cloud/CloudUtils;->getItemByServerID(Landroid/content/Context;Ljava/lang/String;)Lcom/miui/gallery/data/DBImage;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-static {p2, v0}, Lcom/miui/gallery/cloud/CloudUtils;->reviseAttributes(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-static {p1, p2, v0}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDBForSync(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-virtual {v0}, Lcom/miui/gallery/data/DBImage;->getId()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getServerUnModifyAlbumsStrategy()Lcom/miui/gallery/cloudcontrol/strategies/ServerUnModifyAlbumsStrategy;

    move-result-object v0

    invoke-static {p3}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v6

    invoke-virtual {v0, v6, v7}, Lcom/miui/gallery/cloudcontrol/strategies/ServerUnModifyAlbumsStrategy;->getServerAlbumName(J)Ljava/lang/String;

    move-result-object p3

    if-eqz p3, :cond_1

    invoke-static {p0, p3}, Lcom/miui/gallery/cloud/CloudUtils;->getGroupByFileName(Landroid/content/Context;Ljava/lang/String;)Lcom/miui/gallery/data/DBCloud;

    move-result-object p0

    goto :goto_0

    :cond_1
    move-object p0, v5

    :goto_0
    if-eqz p0, :cond_2

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getFileName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-virtual {p3, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    new-instance v0, Landroid/content/ContentValues;

    invoke-direct {v0}, Landroid/content/ContentValues;-><init>()V

    invoke-virtual {v0, v4, p3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1, v0, p0}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDB(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)I

    :cond_2
    invoke-virtual {p2, v3, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    invoke-static {p2, v5}, Lcom/miui/gallery/cloud/CloudUtils;->reviseAttributes(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-static {p1, p2}, Lcom/miui/gallery/util/GalleryUtils;->safeInsertImage(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;

    move-result-object p0

    if-eqz p0, :cond_3

    invoke-virtual {p0}, Landroid/net/Uri;->getLastPathSegment()Ljava/lang/String;

    move-result-object v5

    :cond_3
    return-object v5

    :cond_4
    invoke-static {}, Lcom/miui/gallery/util/deviceprovider/ApplicationHelper;->isAutoUploadFeatureOpen()Z

    move-result p3

    if-eqz p3, :cond_5

    const-string p3, "appKey"

    invoke-virtual {p2, p3}, Landroid/content/ContentValues;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-virtual {p2, p3}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0, p3}, Lcom/miui/gallery/cloud/CloudUtils;->getGroupByAppKey(Landroid/content/Context;Ljava/lang/String;)Lcom/miui/gallery/data/DBCloud;

    move-result-object v0

    if-eqz v0, :cond_6

    invoke-static {p2, v0}, Lcom/miui/gallery/cloud/CloudUtils;->reviseAttributes(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-static {p1, p2, v0}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDBForSync(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-virtual {v0}, Lcom/miui/gallery/data/DBCloud;->getId()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_5
    move-object p3, v5

    :cond_6
    const-string v0, "localFile"

    invoke-virtual {p2, v0}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {p2, v4}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-static {p0, v7}, Lcom/miui/gallery/cloud/CloudUtils;->getGroupByFileName(Landroid/content/Context;Ljava/lang/String;)Lcom/miui/gallery/data/DBCloud;

    move-result-object v8

    if-eqz v8, :cond_c

    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getServerId()Ljava/lang/String;

    move-result-object v9

    invoke-static {v9}, Lcom/miui/gallery/cloud/CloudUtils;->isUmodifyAlbum(Ljava/lang/String;)Z

    move-result v9

    if-eqz v9, :cond_7

    invoke-static {p2}, Lcom/miui/gallery/cloud/SyncCloudBase;->statSuspiciousAlbum(Landroid/content/ContentValues;)V

    goto :goto_3

    :cond_7
    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getLocalFile()Ljava/lang/String;

    move-result-object v9

    invoke-static {v9}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v10

    if-nez v10, :cond_b

    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v10

    if-nez v10, :cond_b

    invoke-virtual {v9, v6}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v9

    if-eqz v9, :cond_8

    goto :goto_2

    :cond_8
    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getLocalFlag()I

    move-result v9

    const/16 v10, 0x8

    if-eq v9, v10, :cond_a

    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getLocalFlag()I

    move-result v9

    const/4 v10, 0x7

    if-ne v9, v10, :cond_9

    goto :goto_1

    :cond_9
    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getLocalFlag()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getFileName()Ljava/lang/String;

    move-result-object v4

    const-string v8, "SyncCloudBase"

    const-string v9, "old album\'s localflag: %s, old album\'s name: %s"

    invoke-static {v8, v9, v1, v4}, Lcom/miui/gallery/util/SyncLog;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_3

    :cond_a
    :goto_1
    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getFileName()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v10

    invoke-virtual {v9, v10, v11}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    new-instance v9, Landroid/content/ContentValues;

    invoke-direct {v9}, Landroid/content/ContentValues;-><init>()V

    invoke-virtual {v9, v4, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1, v9, v8}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDB(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)I

    goto :goto_3

    :cond_b
    :goto_2
    invoke-static {p2, v8}, Lcom/miui/gallery/cloud/CloudUtils;->reviseAttributes(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-static {p1, p2, v8}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDBForSync(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-virtual {v8}, Lcom/miui/gallery/data/DBCloud;->getId()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_c
    :goto_3
    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_d

    invoke-static {v7, p3}, Lcom/miui/gallery/cloud/CloudUtils;->getOwnerAlbumLocalFile(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    const/16 p3, 0x26

    invoke-static {p3}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object p3

    const-string v1, "editedColumns"

    invoke-virtual {p2, v1}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-static {p3, v4}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->mergeEditedColumns(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, v0, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p2, v1, p3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    :cond_d
    const/4 p3, 0x1

    invoke-static {p0, v0, v6, p3}, Lcom/miui/gallery/cloud/CloudUtils;->getGroupItemByColumnnameAndValue(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Z)Lcom/miui/gallery/data/DBCloud;

    move-result-object p0

    if-eqz p0, :cond_e

    invoke-static {p2, p0}, Lcom/miui/gallery/cloud/CloudUtils;->reviseAttributes(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-static {p1, p2, p0}, Lcom/miui/gallery/cloud/CloudUtils;->updateToLocalDBForSync(Landroid/net/Uri;Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getId()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_e
    invoke-virtual {p2, v3, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    invoke-static {p2, v5}, Lcom/miui/gallery/cloud/CloudUtils;->reviseAttributes(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-static {p1, p2}, Lcom/miui/gallery/util/GalleryUtils;->safeInsertImage(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;

    move-result-object p0

    if-eqz p0, :cond_f

    invoke-virtual {p0}, Landroid/net/Uri;->getLastPathSegment()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_f
    return-object v5
.end method

.method private final handlePurged(Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncCloudBase;->mContext:Landroid/content/Context;

    invoke-static {v0, v1, p1, p2}, Lcom/miui/gallery/cloud/DeleteCloudItem;->updateForDeleteOrPurgedOnLocal(Landroid/net/Uri;Landroid/content/Context;Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V

    return-void
.end method

.method private logForNullLocalGroupId(Landroid/content/ContentValues;Lorg/json/JSONObject;)V
    .locals 2

    if-nez p2, :cond_0

    const-string p2, ""

    goto :goto_0

    :cond_0
    invoke-virtual {p2}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p2

    :goto_0
    const-string v0, "SyncCloudBase"

    const-string v1, "localGroupId is null, schemaJson= "

    invoke-static {v0, v1, p2}, Lcom/miui/gallery/util/SyncLog;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance p2, Ljava/util/HashMap;

    invoke-direct {p2}, Ljava/util/HashMap;-><init>()V

    const-string v0, "groupId"

    invoke-virtual {p1, v0}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-interface {p2, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v0, "serverStatus"

    invoke-virtual {p1, v0}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "serverstatus"

    invoke-interface {p2, v0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p1, Landroid/os/Build;->MODEL:Ljava/lang/String;

    const-string v0, "model"

    invoke-interface {p2, v0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p1, Landroid/os/Build$VERSION;->INCREMENTAL:Ljava/lang/String;

    const-string v0, "version"

    invoke-interface {p2, v0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "Sync"

    const-string v0, "group_record_not_found"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private static statSuspiciousAlbum(Landroid/content/ContentValues;)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    invoke-virtual {p0}, Landroid/content/ContentValues;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v1, "album_info"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "Sync"

    const-string v1, "sync_suspicious_album"

    invoke-static {p0, v1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private static statSuspiciousAlbumPathModify(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const-string v1, "oldLocalFile"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "newLocalFile"

    invoke-virtual {v0, p0, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "Sync"

    const-string p1, "sync_suspicious_album_path_modify"

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method


# virtual methods
.method protected appendParams(Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/apache/http/NameValuePair;",
            ">;",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/GalleryCloudSyncTagUtils$SyncTagItem;",
            ">;)V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/UnsupportedEncodingException;,
            Ljavax/crypto/IllegalBlockSizeException;,
            Ljavax/crypto/BadPaddingException;
        }
    .end annotation

    if-nez p2, :cond_0

    return-void

    :cond_0
    invoke-super {p0, p1, p2}, Lcom/miui/gallery/cloud/SyncFromServer;->appendParams(Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    const/4 v0, 0x0

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/cloud/GalleryCloudSyncTagUtils$SyncTagItem;

    iget-wide v1, v1, Lcom/miui/gallery/cloud/GalleryCloudSyncTagUtils$SyncTagItem;->currentValue:J

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/cloud/SyncCloudBase;->getLargerTags(J)Ljava/util/Set;

    move-result-object v6

    invoke-static {}, Lcom/google/common/collect/Maps;->newHashMap()Ljava/util/HashMap;

    move-result-object v1

    invoke-virtual {p1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/apache/http/NameValuePair;

    invoke-interface {v3}, Lorg/apache/http/NameValuePair;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v3}, Lorg/apache/http/NameValuePair;->getValue()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v4, v3}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getSyncUrl()Ljava/lang/String;

    move-result-object v3

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncCloudBase;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    invoke-virtual {v2}, Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;->getSecurity()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/cloud/GalleryCloudSyncTagUtils$SyncTagItem;

    iget-wide v7, p2, Lcom/miui/gallery/cloud/GalleryCloudSyncTagUtils$SyncTagItem;->currentValue:J

    move-object v4, v1

    invoke-static/range {v3 .. v8}, Lcom/xiaomi/micloudsdk/request/utils/Request;->addFilterTagsToParams(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/util/Set;J)V

    const-string p2, "filterTag"

    invoke-virtual {v1, p2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_2

    new-instance v1, Lorg/apache/http/message/BasicNameValuePair;

    invoke-direct {v1, p2, v0}, Lorg/apache/http/message/BasicNameValuePair;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    return-void
.end method

.method protected belong2BabyAlbum(Landroid/content/ContentValues;Z)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncCloudBase;->mContext:Landroid/content/Context;

    const-string v1, "groupId"

    invoke-virtual {p1, v1}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1, p2}, Lcom/miui/gallery/cloud/baby/BabyAlbumUtils;->isBabyAlbumForThisServerId(Landroid/content/Context;Ljava/lang/String;Z)Z

    move-result p1

    return p1
.end method

.method protected abstract getCandidateItemsInAGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Lcom/miui/gallery/data/DBImage;",
            ">;"
        }
    .end annotation
.end method

.method protected abstract getItem(Ljava/lang/String;)Lcom/miui/gallery/data/DBImage;
.end method

.method protected abstract getLocalGroupId(Landroid/content/ContentValues;)Ljava/lang/String;
.end method

.method protected final handleDataJson(Lorg/json/JSONObject;)Z
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    const-string v0, "content"

    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object p1

    const/4 v0, 0x0

    if-eqz p1, :cond_4

    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-gtz v1, :cond_0

    goto :goto_1

    :cond_0
    move v1, v0

    move v2, v1

    :goto_0
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result v3

    if-ge v1, v3, :cond_3

    invoke-virtual {p1, v1}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    invoke-virtual {p0, v3}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleItem(Lorg/json/JSONObject;)Z

    move-result v4

    if-eqz v4, :cond_1

    const/4 v2, 0x1

    :cond_1
    const-string v4, "type"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const-string v5, "status"

    invoke-virtual {v3, v5}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    const-string v6, "group"

    invoke-virtual {v4, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_2

    const-string v4, "custom"

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_2

    const-string v4, "isPublic"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_2

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getBoolean(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_2

    const-string v4, "id"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v3, v0}, Lcom/miui/gallery/cloud/AlbumShareOperations;->requestPublicUrl(Ljava/lang/String;Z)Lcom/miui/gallery/util/AsyncResult;

    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_3
    return v2

    :cond_4
    :goto_1
    const-string p1, "SyncCloudBase"

    const-string v1, "contentArray is empty, return."

    invoke-static {p1, v1}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;)V

    return v0
.end method

.method protected handleFavoriteInfo(Lorg/json/JSONObject;Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    return-void
.end method

.method protected final handleItem(Lorg/json/JSONObject;)Z
    .locals 10
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    const-string v0, "type"

    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "status"

    invoke-virtual {p1, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "id"

    invoke-virtual {p1, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v3

    monitor-enter v3

    :try_start_0
    invoke-virtual {p0, v2}, Lcom/miui/gallery/cloud/SyncCloudBase;->getItem(Ljava/lang/String;)Lcom/miui/gallery/data/DBImage;

    move-result-object v4

    const/4 v5, 0x0

    if-nez v4, :cond_2

    invoke-static {p1}, Lcom/miui/gallery/cloud/CloudUtils;->getContentValuesForAll(Lorg/json/JSONObject;)Landroid/content/ContentValues;

    move-result-object v4

    const-string v6, "group"

    invoke-virtual {v0, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncCloudBase;->mContext:Landroid/content/Context;

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v0

    invoke-static {p1, v0, v4, v2}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleNewGroup(Landroid/content/Context;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1, v4}, Lcom/miui/gallery/cloud/SyncCloudBase;->updateLocalGroupIdInGroup(Ljava/lang/String;Landroid/content/ContentValues;)V

    monitor-exit v3

    return v5

    :cond_0
    const-string v0, "localGroupId"

    invoke-virtual {p0, v4}, Lcom/miui/gallery/cloud/SyncCloudBase;->getLocalGroupId(Landroid/content/ContentValues;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v0, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0, v4, v2, v1, p1}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleNewImage(Landroid/content/ContentValues;Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleUbiSubImage(Lorg/json/JSONObject;Ljava/lang/String;)V

    const-string v0, "sha1"

    invoke-virtual {v4, v0}, Landroid/content/ContentValues;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    const-string v0, "isFavorite"

    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    const-string v0, "sha1"

    invoke-virtual {v4, v0}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleFavoriteInfo(Lorg/json/JSONObject;Ljava/lang/String;)V

    :cond_1
    const/4 p1, 0x1

    monitor-exit v3

    return p1

    :cond_2
    invoke-virtual {v4}, Lcom/miui/gallery/data/DBImage;->getServerTag()J

    move-result-wide v6

    const-string v0, "tag"

    invoke-static {p1, v0}, Lcom/miui/gallery/cloud/CloudUtils;->getLongAttributeFromJson(Lorg/json/JSONObject;Ljava/lang/String;)J

    move-result-wide v8

    cmp-long v0, v6, v8

    if-ltz v0, :cond_3

    const-string v0, "SyncCloudBase"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "cloud:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " local tag:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Lcom/miui/gallery/data/DBImage;->getServerTag()J

    move-result-wide v6

    invoke-virtual {v1, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, " >= server tag:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "tag"

    invoke-static {p1, v2}, Lcom/miui/gallery/cloud/CloudUtils;->getLongAttributeFromJson(Lorg/json/JSONObject;Ljava/lang/String;)J

    move-result-wide v6

    invoke-virtual {v1, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", don\'t update local."

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;)V

    monitor-exit v3

    return v5

    :cond_3
    const-string v0, "custom"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-direct {p0, v4, p1}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleCustom(Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V

    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleUbiSubImage(Lorg/json/JSONObject;Ljava/lang/String;)V

    goto :goto_0

    :cond_4
    const-string v0, "deleted"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-direct {p0, v4, p1}, Lcom/miui/gallery/cloud/SyncCloudBase;->handleDelete(Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V

    goto :goto_0

    :cond_5
    const-string v0, "purged"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6

    invoke-direct {p0, v4, p1}, Lcom/miui/gallery/cloud/SyncCloudBase;->handlePurged(Lcom/miui/gallery/data/DBImage;Lorg/json/JSONObject;)V

    goto :goto_0

    :cond_6
    const-string v0, "SyncCloudBase"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "status unrecognized, schema:"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/miui/gallery/util/SyncLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    monitor-exit v3

    return v5

    :catchall_0
    move-exception p1

    monitor-exit v3
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method protected handleNewImage(Landroid/content/ContentValues;Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;)Ljava/lang/String;
    .locals 20

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    const/4 v2, 0x0

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const-string v3, "localFlag"

    invoke-virtual {v1, v3, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string v2, "fileName"

    invoke-virtual {v1, v2}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    const-string v4, "sha1"

    invoke-virtual {v1, v4}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const-string v5, "localGroupId"

    invoke-virtual {v1, v5}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_0

    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    move-object/from16 v5, p4

    invoke-direct {v0, v1, v5}, Lcom/miui/gallery/cloud/SyncCloudBase;->logForNullLocalGroupId(Landroid/content/ContentValues;Lorg/json/JSONObject;)V

    goto :goto_0

    :cond_0
    invoke-virtual {v0, v3, v4, v5}, Lcom/miui/gallery/cloud/SyncCloudBase;->getCandidateItemsInAGroup(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    :goto_0
    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    const/4 v6, 0x0

    if-eqz v5, :cond_5

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/miui/gallery/data/DBImage;

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getSha1()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7, v4}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v7

    const-string v8, "SyncCloudBase"

    const-string v9, "localFile"

    const-string v10, "thumbnailFile"

    if-eqz v7, :cond_3

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getSize()J

    move-result-wide v2

    const-string v4, "size"

    invoke-virtual {v1, v4}, Landroid/content/ContentValues;->getAsInteger(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    int-to-long v11, v4

    cmp-long v2, v2, v11

    if-gez v2, :cond_2

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v10, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v9}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v2

    const-string v3, "match db sha1, thumbnail %s"

    invoke-static {v8, v3, v2}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto/16 :goto_1

    :cond_2
    invoke-virtual {v1, v10}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v9, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v2

    const-string v3, "match db sha1, original file %s"

    invoke-static {v8, v3, v2}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto/16 :goto_1

    :cond_3
    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v7

    const-string v11, ".jpg"

    invoke-virtual {v7, v11}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v7

    if-eqz v7, :cond_1

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getSize()J

    move-result-wide v11

    const-wide/32 v13, 0x100000

    cmp-long v7, v11, v13

    if-gez v7, :cond_1

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lcom/miui/gallery/util/ExifUtil;->getUserCommentSha1(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-static {v4, v7}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v7

    const-string v11, "Sync"

    const-string v12, "name"

    const-string v13, "groupId"

    if-eqz v7, :cond_4

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v10, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v9}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v2

    const-string v3, "match exif sha1, thumbnail %s"

    invoke-static {v8, v3, v2}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v2

    invoke-virtual {v1, v13}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-interface {v2, v13, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v2, v12, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v3, "sync_item_match_exif_sha1"

    invoke-static {v11, v3, v2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    goto :goto_1

    :cond_4
    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getDatetaken()J

    move-result-wide v14

    const-string v7, "dateTaken"

    invoke-virtual {v1, v7}, Landroid/content/ContentValues;->getAsLong(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/Long;->longValue()J

    move-result-wide v16

    const-wide/16 v18, 0x0

    cmp-long v7, v14, v18

    if-lez v7, :cond_1

    cmp-long v7, v16, v18

    if-lez v7, :cond_1

    const-wide/16 v18, 0x3e8

    div-long v14, v14, v18

    div-long v16, v16, v18

    cmp-long v7, v14, v16

    if-nez v7, :cond_1

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getLocalFile()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v10, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v9}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v2}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const-string v7, "match taken time, thumbnail %s, origin %s"

    invoke-static {v8, v7, v3, v4}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v3

    invoke-virtual {v1, v13}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-interface {v3, v13, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getFileName()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "_"

    invoke-virtual {v4, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2}, Landroid/content/ContentValues;->getAsString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v3, v12, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v2, "sync_item_match_taken_time"

    invoke-static {v11, v2, v3}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    goto :goto_1

    :cond_5
    move-object v5, v6

    :goto_1
    if-eqz v5, :cond_6

    invoke-static {v1, v5}, Lcom/miui/gallery/cloud/CloudUtils;->reviseSpecialTypeFlags(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-static {v1, v5}, Lcom/miui/gallery/util/GalleryUtils;->safeUpdateImage(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)I

    invoke-virtual {v5}, Lcom/miui/gallery/data/DBImage;->getId()Ljava/lang/String;

    move-result-object v1

    return-object v1

    :cond_6
    invoke-static {v1, v6}, Lcom/miui/gallery/cloud/CloudUtils;->reviseSpecialTypeFlags(Landroid/content/ContentValues;Lcom/miui/gallery/data/DBImage;)V

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/cloud/SyncCloudBase;->getBaseUri()Landroid/net/Uri;

    move-result-object v2

    invoke-static {v2, v1}, Lcom/miui/gallery/util/GalleryUtils;->safeInsertImage(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;

    move-result-object v2

    if-eqz v2, :cond_7

    invoke-virtual {v2}, Landroid/net/Uri;->getLastPathSegment()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v1, v6}, Lcom/miui/gallery/cloud/SyncCloudBase;->onNewImageSynced(Landroid/content/ContentValues;Ljava/lang/String;)V

    :cond_7
    return-object v6
.end method

.method protected abstract handleUbiSubImage(Lorg/json/JSONObject;Ljava/lang/String;)V
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation
.end method

.method protected abstract onNewImageSynced(Landroid/content/ContentValues;Ljava/lang/String;)V
.end method

.method protected abstract updateLocalGroupIdInGroup(Ljava/lang/String;Landroid/content/ContentValues;)V
.end method
