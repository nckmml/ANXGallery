.class public Lcom/miui/gallery/provider/AlbumManager;
.super Ljava/lang/Object;
.source "AlbumManager.java"


# static fields
.field private static ALBUM_ATTRIBUTES:Ljava/util/Map; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Long;",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private static ALBUM_SYNC_ATTRIBUTES:Ljava/util/List; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private static final SQL_ALBUM_BASIC:Ljava/lang/String;

.field private static final SQL_ALBUM_COUNT:Ljava/lang/String; = ", count(_id) AS media_count"

.field private static final SQL_ALBUM_COVER_DEFAULT:Ljava/lang/String;

.field private static final SQL_ALBUM_COVER_FORMAT:Ljava/lang/String;

.field private static final SQL_ALBUM_COVER_PLACEHOLDER:Ljava/lang/String; = ", -1 AS cover_id, NULL AS cover_path, NULL AS cover_sha1, 0 AS cover_sync_state, 0 AS cover_size, NULL AS latest_photo "

.field private static final SQL_ALBUM_DEDUPLICATE_COUNT:Ljava/lang/String; = ", count(DISTINCT sha1) AS media_count"

.field private static SQL_ALBUM_IMMUTABLE:Ljava/lang/String; = "CASE WHEN localFile LIKE \'/%%\' THEN 1 ELSE 0 END AS immutable"

.field private static SQL_ALBUM_LOCAL_PATH:Ljava/lang/String; = "localFile AS local_path"

.field private static SQL_ALBUM_NAME:Ljava/lang/String; = "fileName AS name"

.field private static SQL_ALBUM_NOT_HIDDEN:Ljava/lang/String; = "AND (attributes & 16 = 0)"

.field private static SQL_ALBUM_SORT_BY:Ljava/lang/String; = "CASE WHEN sortBy IS NULL THEN CASE WHEN dateTaken >0 THEN 9223372036854775807 - dateTaken ELSE dateTaken END ELSE sortBy END AS sortBy"

.field public static final SQL_BABY_ALBUM_COVER:Ljava/lang/String;

.field public static final SQL_BABY_OTHER_SHARED_ALBUM_COVER:Ljava/lang/String;

.field private static SQL_EXCLUDE_BABY_ALBUM:Ljava/lang/String; = "AND (babyInfoJson IS NULL)"

.field private static SQL_EXCLUDE_MEDIA_IN_BABY_ALBUM:Ljava/lang/String; = "AND (localGroupId in (SELECT _id FROM cloud WHERE serverType=0 AND babyInfoJson IS NULL )) "

.field private static final SQL_RECENT_ALBUM_COVER:Ljava/lang/String;

.field private static final SQL_SHARE_ALBUM:Ljava/lang/String;

.field private static final SQL_SHARE_ALBUM_BASIC:Ljava/lang/String;

.field private static final SQL_SHARE_ALBUM_COUNT:Ljava/lang/String; = ", count(_id) AS media_count"

.field private static final SQL_SHARE_ALBUM_COVER:Ljava/lang/String;

.field private static SQL_SHARE_ALBUM_NOT_HIDDEN:Ljava/lang/String; = "AND (attributes & 16 = 0)"

.field private static SQL_SHARE_ALBUM_SORT_BY:Ljava/lang/String; = "CASE WHEN sortBy IS NULL THEN CASE WHEN dateTaken >0 THEN 9223372036854775807 - dateTaken ELSE dateTaken END ELSE sortBy END AS sortBy"

.field private static final SQL_SHARE_DEDUPLICATE_ALBUM_COUNT:Ljava/lang/String; = ", count(DISTINCT sha1) AS media_count"

.field private static final SQL_UNION_SHARE_ALBUM:Ljava/lang/String;

.field private static sScreenshotsAlbumId:J = -0x1L


# direct methods
.method static constructor <clinit>()V
    .locals 16

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "SELECT _id AS _id, attributes AS attributes, "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_NAME:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v2, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_LOCAL_PATH:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "localFlag"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " AS "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "flag"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "dateTaken"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "top_time"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "peopleId"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "face_people_id"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "babyInfoJson"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "baby_info"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "NULL AS "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "baby_sharer_info"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "serverId"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "thumbnailInfo"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "thumbnail_info"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v4, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_SORT_BY:Ljava/lang/String;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v4, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_IMMUTABLE:Ljava/lang/String;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, " "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "FROM "

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "cloud"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "WHERE "

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "(serverType=0)"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "AND "

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\'))"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "%s "

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "%s"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_BASIC:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "SELECT 2147383647+_id AS _id, attributes AS attributes, sharealbum.fileName AS name, NULL AS local_path, localFlag AS flag, dateTaken AS top_time, peopleId AS face_people_id, babyInfoJson AS baby_info, sharerInfo AS baby_sharer_info, serverId AS serverId, NULL AS thumbnail_info, "

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM_SORT_BY:Ljava/lang/String;

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v7, 0x0

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "immutable"

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "shareAlbum"

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "WHERE "

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " IS NOT NULL "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM_BASIC:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, ", _id AS cover_id, ("

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v3, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_CLEAR_FIRST:Ljava/lang/String;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ") AS "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, "cover_path"

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, "sha1"

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v9, "cover_sha1"

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, " CASE WHEN localFlag = 0  THEN 0 WHEN localFlag IN (5, 6, 9) THEN 1 ELSE 3 END "

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v11, "cover_sync_state"

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v12, "size"

    invoke-virtual {v0, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v13, "cover_size"

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v14, "max(CASE"

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v14, " WHEN "

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v14, "localGroupId"

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " IN "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "(SELECT "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "_id"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " FROM "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "cloud"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " WHERE "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "localFile"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " COLLATE NOCASE IN (%s)"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " AND "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "(serverType=0)"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, ")"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " THEN "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "dateModified"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " ELSE ("

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, ") END) "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "AS latest_photo "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COVER_FORMAT:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v15, ", _id AS cover_id, ("

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v15, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_CLEAR_FIRST:Ljava/lang/String;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "max("

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, " CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, ") AS latest_photo "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COVER_DEFAULT:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v15, ", _id AS cover_id, ("

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v15, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_CLEAR_FIRST:Ljava/lang/String;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "max("

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, "dateModified"

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v15, ") AS latest_photo "

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_RECENT_ALBUM_COVER:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v15, ", 1073741823+_id AS cover_id, ("

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v15, Lcom/miui/gallery/provider/InternalContract$ShareImage;->ALIAS_CLEAR_FIRST:Ljava/lang/String;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "max("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " CASE WHEN mixedDateTime NOT NULL THEN mixedDateTime ELSE dateTaken END "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ") AS latest_photo "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM_COVER:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "(SELECT * FROM (SELECT cloud.serverId as imageId"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COVER_DEFAULT:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "cloud"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " WHERE "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " = %s ))"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_BABY_ALBUM_COVER:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "(SELECT * FROM (SELECT serverId as imageId"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COVER_DEFAULT:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "shareImage"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " WHERE "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " = %s ))"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_BABY_OTHER_SHARED_ALBUM_COVER:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "SELECT * FROM ("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM_BASIC:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " LEFT JOIN "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "(SELECT "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const v1, 0x7ffe795f

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "+"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "_id"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM_COVER:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", 0 AS size "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "shareImage"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "WHERE "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\'))"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "GROUP BY "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ") USING ("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "_id"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, " UNION "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_UNION_SHARE_ALBUM:Ljava/lang/String;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const-wide/16 v1, 0x1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-wide/16 v2, 0x2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-wide/16 v1, 0x4

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-wide/16 v2, 0x8

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-wide/16 v1, 0x10

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-wide/16 v2, 0x20

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-wide/16 v1, 0x40

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-wide/16 v2, 0x80

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v0}, Ljava/util/Collections;->unmodifiableMap(Ljava/util/Map;)Ljava/util/Map;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->ALBUM_ATTRIBUTES:Ljava/util/Map;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const-wide/16 v1, 0x1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    const-wide/16 v1, 0x4

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    const-wide/16 v1, 0x10

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    const-wide/16 v1, 0x40

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {v0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/AlbumManager;->ALBUM_SYNC_ATTRIBUTES:Ljava/util/List;

    return-void
.end method

.method private static doQueryScreenshotsAlbumId(Landroid/content/Context;)J
    .locals 7

    sget-object v1, Lcom/miui/gallery/provider/GalleryContract$Cloud;->CLOUD_URI:Landroid/net/Uri;

    const-string v0, "_id"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v2

    const/4 v0, 0x1

    new-array v4, v0, [Ljava/lang/String;

    const-wide/16 v5, 0x2

    invoke-static {v5, v6}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0

    const/4 v3, 0x0

    aput-object v0, v4, v3

    sget-object v6, Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;->INSTANCE:Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;

    const-string v3, "serverId = ? AND (serverType=0)"

    const/4 v5, 0x0

    move-object v0, p0

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Long;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    goto :goto_0

    :cond_0
    const-wide/16 v0, -0x1

    :goto_0
    return-wide v0
.end method

.method private static genPlaceholderAlbumSelection(JLjava/lang/String;JJJ)Ljava/lang/String;
    .locals 2

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x5

    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {p0, p1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    const/4 p1, 0x0

    aput-object p0, v1, p1

    const/4 p0, 0x1

    aput-object p2, v1, p0

    invoke-static {p3, p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    const/4 p1, 0x2

    aput-object p0, v1, p1

    invoke-static {p5, p6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    const/4 p1, 0x3

    aput-object p0, v1, p1

    invoke-static {p7, p8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    const/4 p1, 0x4

    aput-object p0, v1, p1

    const-string p0, " UNION SELECT %d AS _id, 0 AS attributes, \'%s\' AS name, NULL AS local_path, 0 AS flag, %d AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, \'%d\' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable , 2147483647 AS media_count, -1 AS cover_id, NULL AS cover_path, NULL AS cover_sha1, 0 AS cover_sync_state, 0 AS cover_size, NULL AS latest_photo , 0 AS size "

    invoke-static {v0, p0, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static generateFaceAlbumCover(Landroid/database/sqlite/SQLiteDatabase;)Landroid/os/Bundle;
    .locals 10

    invoke-static {}, Lcom/miui/gallery/provider/FaceManager;->buildTopFaceCoverQuery()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/database/sqlite/SQLiteDatabase;->rawQuery(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p0

    if-eqz p0, :cond_3

    :try_start_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const-string v1, "cover_id"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    const-string v2, "cover_path"

    invoke-interface {p0, v2}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v2

    const-string v3, "cover_sha1"

    invoke-interface {p0, v3}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v3

    const-string v4, "cover_sync_state"

    invoke-interface {p0, v4}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v4

    const-string v5, "cover_size"

    invoke-interface {p0, v5}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v5

    const-string v6, "faceRect"

    invoke-interface {p0, v6}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v6

    :cond_0
    invoke-interface {p0}, Landroid/database/Cursor;->moveToNext()Z

    move-result v7

    if-eqz v7, :cond_2

    new-instance v7, Lcom/miui/gallery/model/FaceAlbumCover;

    invoke-direct {v7}, Lcom/miui/gallery/model/FaceAlbumCover;-><init>()V

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v8

    iput-wide v8, v7, Lcom/miui/gallery/model/FaceAlbumCover;->coverId:J

    invoke-interface {p0, v2}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v8

    iput-object v8, v7, Lcom/miui/gallery/model/FaceAlbumCover;->coverPath:Ljava/lang/String;

    invoke-interface {p0, v3}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v8

    iput-object v8, v7, Lcom/miui/gallery/model/FaceAlbumCover;->coverSha1:Ljava/lang/String;

    invoke-interface {p0, v4}, Landroid/database/Cursor;->getInt(I)I

    move-result v8

    iput v8, v7, Lcom/miui/gallery/model/FaceAlbumCover;->coverSyncState:I

    invoke-interface {p0, v5}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v8

    iput-wide v8, v7, Lcom/miui/gallery/model/FaceAlbumCover;->coverSize:J

    invoke-interface {p0, v6}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-static {v8}, Lcom/miui/gallery/util/face/FaceRegionRectF;->resolveFrom(Ljava/lang/String;)Lcom/miui/gallery/util/face/FaceRegionRectF;

    move-result-object v8

    iput-object v8, v7, Lcom/miui/gallery/model/FaceAlbumCover;->faceRectF:Lcom/miui/gallery/util/face/FaceRegionRectF;

    iget-object v8, v7, Lcom/miui/gallery/model/FaceAlbumCover;->faceRectF:Lcom/miui/gallery/util/face/FaceRegionRectF;

    if-eqz v8, :cond_1

    invoke-virtual {v0, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v7

    const/4 v8, 0x4

    if-ne v7, v8, :cond_0

    :cond_2
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    const-string v2, "face_album_cover"

    invoke-virtual {v1, v2, v0}, Landroid/os/Bundle;->putParcelableArrayList(Ljava/lang/String;Ljava/util/ArrayList;)V

    const-string v0, "face_album_count"

    invoke-interface {p0}, Landroid/database/Cursor;->getCount()I

    move-result v2

    invoke-virtual {v1, v0, v2}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    invoke-interface {p0}, Landroid/database/Cursor;->close()V

    return-object v1

    :catchall_0
    move-exception v0

    invoke-interface {p0}, Landroid/database/Cursor;->close()V

    throw v0

    :cond_3
    return-object v1
.end method

.method public static getAlbumAttributes()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/Long;",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/provider/AlbumManager;->ALBUM_ATTRIBUTES:Ljava/util/Map;

    return-object v0
.end method

.method private static getAlbumBasicColumns(ZZ)Ljava/lang/String;
    .locals 4

    sget-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_BASIC:Ljava/lang/String;

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    const-string v2, ""

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_NOT_HIDDEN:Ljava/lang/String;

    goto :goto_0

    :cond_0
    move-object p0, v2

    :goto_0
    const/4 v3, 0x0

    aput-object p0, v1, v3

    const/4 p0, 0x1

    if-eqz p1, :cond_1

    sget-object v2, Lcom/miui/gallery/provider/AlbumManager;->SQL_EXCLUDE_BABY_ALBUM:Ljava/lang/String;

    :cond_1
    aput-object v2, v1, p0

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static getAlbumSyncAttributes()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/provider/AlbumManager;->ALBUM_SYNC_ATTRIBUTES:Ljava/util/List;

    return-object v0
.end method

.method private static getExcludeEmptyAlbumSelection(Z)Ljava/lang/String;
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "(media_count >0 "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    if-eqz p0, :cond_0

    const-string p0, ""

    goto :goto_0

    :cond_0
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, " OR ("

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v2, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_USER_CREATE_ALBUM:Ljava/lang/String;

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    :goto_0
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " OR "

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "_id"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "="

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const p0, 0x7ffffffe

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static getExcludeEmptyRecentAlbumSelection()Ljava/lang/String;
    .locals 1

    const-string v0, "(media_count > 0 OR _id!=2147483644)"

    return-object v0
.end method

.method private static getExcludeHiddenAlbumSelection(Z)Ljava/lang/String;
    .locals 0

    if-eqz p0, :cond_0

    const-string p0, " AND (localGroupId in (SELECT _id FROM cloud WHERE serverType=0 AND attributes&16=0)) "

    goto :goto_0

    :cond_0
    const-string p0, ""

    :goto_0
    return-object p0
.end method

.method private static getExcludeHiddenShareAlbumSelection(Z)Ljava/lang/String;
    .locals 0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM_NOT_HIDDEN:Ljava/lang/String;

    goto :goto_0

    :cond_0
    const-string p0, ""

    :goto_0
    return-object p0
.end method

.method private static getExcludeMediaInBabyAlbumSelection(Z)Ljava/lang/String;
    .locals 0

    if-eqz p0, :cond_0

    sget-object p0, Lcom/miui/gallery/provider/AlbumManager;->SQL_EXCLUDE_MEDIA_IN_BABY_ALBUM:Ljava/lang/String;

    goto :goto_0

    :cond_0
    const-string p0, ""

    :goto_0
    return-object p0
.end method

.method private static getExcludeNonLocalSelection(IZ)Ljava/lang/String;
    .locals 2

    const/4 v0, 0x1

    if-eq p0, v0, :cond_1

    const/4 v1, 0x2

    if-eq p0, v1, :cond_0

    invoke-static {p1}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeNonLocalSelection(Z)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    sget-object p0, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_LOCAL_VIDEO:Ljava/lang/String;

    goto :goto_0

    :cond_1
    sget-object p0, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_LOCAL_IMAGE:Ljava/lang/String;

    :goto_0
    if-eqz p1, :cond_2

    new-array p1, v0, [Ljava/lang/Object;

    const/4 v0, 0x0

    aput-object p0, p1, v0

    const-string p0, " AND %s "

    invoke-static {p0, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_1

    :cond_2
    const-string p0, ""

    :goto_1
    return-object p0
.end method

.method private static getExcludeNonLocalSelection(Z)Ljava/lang/String;
    .locals 2

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    new-array p0, p0, [Ljava/lang/Object;

    const/4 v0, 0x0

    sget-object v1, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_LOCAL_MEDIA:Ljava/lang/String;

    aput-object v1, p0, v0

    const-string v0, " AND %s "

    invoke-static {v0, p0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const-string p0, ""

    :goto_0
    return-object p0
.end method

.method private static getFaceAlbumColumns()Ljava/lang/String;
    .locals 4

    const-wide/32 v0, 0x7ffffffe

    const-wide/16 v2, -0x3e5

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/preference/GalleryPreferences$Album;->getVirtualAlbumSortBy(JJ)J

    move-result-wide v0

    sget-object v2, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const/4 v1, 0x0

    aput-object v0, v3, v1

    const-string v0, " UNION SELECT 2147483646 AS _id, 0 AS attributes, \'FACE\' AS name, NULL AS local_path, 0 AS flag, -997 AS top_time,NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, \'-2147483646\' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable, COUNT(*) AS media_count, 0 AS size , -1 AS cover_id, NULL AS cover_path, NULL AS cover_sha1, 0 AS cover_sync_state, 0 AS cover_size, NULL AS latest_photo FROM peopleFace WHERE ((localFlag=5 OR (localFlag=0 AND serverStatus=\'normal\')) AND type = \'FACE\')"

    invoke-static {v2, v0, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private static getFavoritesAlbumColumns(Ljava/lang/String;ZZLjava/lang/Integer;Z)Ljava/lang/String;
    .locals 13

    const-wide/32 v0, 0x7ffffffa

    const-wide/16 v2, -0x3e8

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/preference/GalleryPreferences$Album;->getVirtualAlbumSortBy(JJ)J

    move-result-wide v11

    if-eqz p4, :cond_0

    const-wide/32 v4, 0x7ffffffa

    const-wide/16 v7, -0x3e8

    const-wide/32 v9, -0x7ffffffa

    const-string v6, "FAVORITES"

    invoke-static/range {v4 .. v12}, Lcom/miui/gallery/provider/AlbumManager;->genPlaceholderAlbumSelection(JLjava/lang/String;JJJ)Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v1, 0x8

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x1

    const-string v3, ", count(DISTINCT sha1) AS media_count"

    aput-object v3, v1, v2

    const/4 v2, 0x2

    aput-object p0, v1, v2

    const/4 v2, 0x3

    const-string v3, ", 0 AS size "

    aput-object v3, v1, v2

    const/4 v2, 0x4

    invoke-static/range {p3 .. p3}, Lcom/miui/gallery/provider/AlbumManager;->getMediaTypeSelection(Ljava/lang/Integer;)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x5

    invoke-static {p2}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeNonLocalSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x6

    invoke-static {p1}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeHiddenAlbumSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x7

    invoke-static {p2}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeMediaInBabyAlbumSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const-string v2, " UNION SELECT 2147483642 AS _id, 0 AS attributes, \'FAVORITES\' AS name, NULL AS local_path, 0 AS flag, -1000 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, \'-2147483642\' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM extended_cloud WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\')) AND (localGroupId!=-1000) AND (serverType IN (1,2) AND isFavorite NOT NULL AND isFavorite > 0) %s%s%s%s"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private static getMediaTypeSelection(Ljava/lang/Integer;)Ljava/lang/String;
    .locals 5

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v1, 0x1

    const/4 v2, 0x2

    if-eq v0, v2, :cond_0

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-ne v0, v1, :cond_1

    :cond_0
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v3, 0x0

    const-string v4, "serverType"

    aput-object v4, v2, v3

    invoke-virtual {p0}, Ljava/lang/Integer;->toString()Ljava/lang/String;

    move-result-object p0

    aput-object p0, v2, v1

    const-string p0, " AND %s = %s "

    invoke-static {v0, p0, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    const-string p0, ""

    return-object p0
.end method

.method private static getPanoAlbumColumns(Ljava/lang/String;ZZZ)Ljava/lang/String;
    .locals 13

    const-wide/32 v0, 0x7ffffffd

    const-wide/16 v2, -0x3e2

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/preference/GalleryPreferences$Album;->getVirtualAlbumSortBy(JJ)J

    move-result-wide v11

    if-eqz p3, :cond_0

    const-wide/32 v4, 0x7ffffffd

    const-wide/16 v7, -0x3e2

    const-wide/32 v9, -0x7ffffffd

    const-string v6, "PANO"

    invoke-static/range {v4 .. v12}, Lcom/miui/gallery/provider/AlbumManager;->genPlaceholderAlbumSelection(JLjava/lang/String;JJJ)Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x6

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x1

    const-string v3, ", count(DISTINCT sha1) AS media_count"

    aput-object v3, v1, v2

    const/4 v3, 0x2

    aput-object p0, v1, v3

    const/4 v3, 0x3

    const-string v4, ", 0 AS size "

    aput-object v4, v1, v3

    const/4 v3, 0x4

    move v4, p2

    invoke-static {v2, p2}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeNonLocalSelection(IZ)Ljava/lang/String;

    move-result-object v2

    aput-object v2, v1, v3

    const/4 v2, 0x5

    invoke-static {p1}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeHiddenAlbumSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const-string v2, " UNION SELECT 2147483645 AS _id, 0 AS attributes, \'PANO\' AS name, NULL AS local_path, 0 AS flag, -994 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, \'-2147483645\' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\')) AND (exifImageWidth > 1080 AND exifImageLength > 0 AND exifImageWidth- 3 * exifImageLength > 0 AND title LIKE \'PANO%%\') AND (localGroupId!=-1000)%s%s"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private static getRecentAlbumColumns(ZZZLjava/lang/Integer;ZZ)Ljava/lang/String;
    .locals 13

    const-wide/32 v0, 0x7ffffffc

    const-wide/16 v2, -0x3e9

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/preference/GalleryPreferences$Album;->getVirtualAlbumSortBy(JJ)J

    move-result-wide v11

    if-eqz p5, :cond_0

    const-wide/32 v4, 0x7ffffffc

    const-wide/16 v7, -0x3e9

    const-wide/32 v9, -0x7ffffffc

    const-string v6, "RECENT"

    invoke-static/range {v4 .. v12}, Lcom/miui/gallery/provider/AlbumManager;->genPlaceholderAlbumSelection(JLjava/lang/String;JJJ)Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v1, 0x8

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x1

    if-eqz p2, :cond_1

    sget-object v3, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_DEDUPLICATE_COUNT:Ljava/lang/String;

    goto :goto_0

    :cond_1
    sget-object v3, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COUNT:Ljava/lang/String;

    :goto_0
    aput-object v3, v1, v2

    const/4 v2, 0x2

    if-eqz p4, :cond_2

    sget-object v3, Lcom/miui/gallery/provider/AlbumManager;->SQL_RECENT_ALBUM_COVER:Ljava/lang/String;

    goto :goto_1

    :cond_2
    sget-object v3, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COVER_PLACEHOLDER:Ljava/lang/String;

    :goto_1
    aput-object v3, v1, v2

    const/4 v2, 0x3

    const-string v3, ", 0 AS size "

    aput-object v3, v1, v2

    const/4 v2, 0x4

    invoke-static/range {p3 .. p3}, Lcom/miui/gallery/provider/AlbumManager;->getMediaTypeSelection(Ljava/lang/Integer;)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x5

    invoke-static {p1}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeNonLocalSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x6

    invoke-static {p0}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeHiddenAlbumSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x7

    invoke-static {p1}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeMediaInBabyAlbumSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const-string v2, " UNION SELECT 2147483644 AS _id, 0 AS attributes, \'RECENT\' AS name, NULL AS local_path, 0 AS flag, -1001 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, \'-2147483644\' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM (SELECT mediaId AS _id, sha1, localFlag, dateModified, serverStatus, localGroupId, thumbnailFile, microthumbfile, localFile, serverType, size, babyInfoJson FROM (recentDiscoveredMedia JOIN (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud ON cloud._id = mediaId) WHERE ( CASE WHEN DATE(dateModified/1000, \'unixepoch\', \'localtime\') == DATE(\'now\', \'localtime\') THEN 0 ELSE CAST(JULIANDAY(\'now\', \'localtime\', \'start of day\') - JULIANDAY(dateModified/1000, \'unixepoch\', \'localtime\') AS int) + 1 END < 30)) WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\')) AND (localGroupId!=-1000)%s%s%s%s"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private static getShareAlbumBasicColumns(ZZLjava/lang/Integer;)Ljava/lang/String;
    .locals 3

    sget-object v0, Lcom/miui/gallery/provider/AlbumManager;->SQL_UNION_SHARE_ALBUM:Ljava/lang/String;

    const/4 v1, 0x3

    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {p0}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeHiddenShareAlbumSelection(Z)Ljava/lang/String;

    move-result-object p0

    const/4 v2, 0x0

    aput-object p0, v1, v2

    if-eqz p1, :cond_0

    sget-object p0, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_DEDUPLICATE_ALBUM_COUNT:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/provider/AlbumManager;->SQL_SHARE_ALBUM_COUNT:Ljava/lang/String;

    :goto_0
    const/4 p1, 0x1

    aput-object p0, v1, p1

    const/4 p0, 0x2

    invoke-static {p2}, Lcom/miui/gallery/provider/AlbumManager;->getMediaTypeSelection(Ljava/lang/Integer;)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v1, p0

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static getVideoAlbumColumns(Ljava/lang/String;ZZZ)Ljava/lang/String;
    .locals 13

    const-wide/32 v0, 0x7fffffff

    const-wide/16 v2, -0x3e6

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/preference/GalleryPreferences$Album;->getVirtualAlbumSortBy(JJ)J

    move-result-wide v11

    if-eqz p3, :cond_0

    const-wide/32 v4, 0x7fffffff

    const-wide/16 v7, -0x3e6

    const-wide/32 v9, -0x7fffffff

    const-string v6, "VIDEO"

    invoke-static/range {v4 .. v12}, Lcom/miui/gallery/provider/AlbumManager;->genPlaceholderAlbumSelection(JLjava/lang/String;JJJ)Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x6

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v1, v2

    const/4 v2, 0x1

    const-string v3, ", count(DISTINCT sha1) AS media_count"

    aput-object v3, v1, v2

    const/4 v2, 0x2

    aput-object p0, v1, v2

    const/4 v3, 0x3

    const-string v4, ", 0 AS size "

    aput-object v4, v1, v3

    const/4 v3, 0x4

    move v4, p2

    invoke-static {v2, p2}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeNonLocalSelection(IZ)Ljava/lang/String;

    move-result-object v2

    aput-object v2, v1, v3

    const/4 v2, 0x5

    invoke-static {p1}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeHiddenAlbumSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v1, v2

    const-string v2, " UNION SELECT 2147483647 AS _id, 0 AS attributes, \'VIDEO\' AS name, NULL AS local_path, 0 AS flag, -998 AS top_time, NULL AS face_people_id, NULL AS baby_info, NULL AS baby_sharer_info, \'-2147483647\' AS serverId, NULL AS thumbnail_info, %d AS sortBy, 0 AS immutable %s %s %sFROM (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud WHERE serverType=2 AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\')) AND (localGroupId!=-1000)%s%s"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private static isInLocalMode()Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$LocalMode;->isOnlyShowLocalPhoto()Z

    move-result v0

    return v0
.end method

.method private static isInShowHiddenMode()Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$HiddenAlbum;->isShowHiddenAlbum()Z

    move-result v0

    return v0
.end method

.method private static isIncludeFaceAlbum(Z)Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/ui/AIAlbumStatusHelper;->isFaceAlbumEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method private static isQueryColumn([Ljava/lang/String;Ljava/lang/String;)Z
    .locals 5

    const/4 v0, 0x0

    if-eqz p0, :cond_2

    array-length v1, p0

    if-lez v1, :cond_2

    aget-object v1, p0, v0

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    const-string v2, "*"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    const/4 v2, 0x1

    if-eqz v1, :cond_0

    return v2

    :cond_0
    array-length v1, p0

    move v3, v0

    :goto_0
    if-ge v3, v1, :cond_2

    aget-object v4, p0, v3

    invoke-virtual {v4}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {p1, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    return v2

    :cond_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    return v0
.end method

.method public static isVirtualAlbum(J)Z
    .locals 6

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->ALL_VIRTUAL_ALBUM_IDS:[I

    array-length v1, v0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_1

    aget v4, v0, v3

    int-to-long v4, v4

    cmp-long v4, v4, p0

    if-nez v4, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    return v2
.end method

.method static synthetic lambda$doQueryScreenshotsAlbumId$33(Landroid/database/Cursor;)Ljava/lang/Long;
    .locals 6

    const-wide/16 v0, -0x1

    if-eqz p0, :cond_0

    invoke-interface {p0}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v2

    if-eqz v2, :cond_0

    const/4 v2, 0x0

    invoke-interface {p0, v2}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v2

    goto :goto_0

    :cond_0
    move-wide v2, v0

    :goto_0
    const-wide/16 v4, 0x0

    cmp-long p0, v2, v4

    if-lez p0, :cond_1

    move-wide v0, v2

    :cond_1
    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    return-object p0
.end method

.method public static packageAttributeBit(JZZ)J
    .locals 4

    sget-object v0, Lcom/miui/gallery/provider/AlbumManager;->ALBUM_ATTRIBUTES:Ljava/util/Map;

    invoke-static {p0, p1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    if-eqz p2, :cond_0

    goto :goto_0

    :cond_0
    move-wide p0, v2

    :goto_0
    if-eqz p3, :cond_1

    goto :goto_1

    :cond_1
    move-wide v0, v2

    :goto_1
    or-long/2addr p0, v0

    return-wide p0
.end method

.method public static query(Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;ZZZZZZZZZLjava/lang/Integer;ZZZLjava/lang/String;)Landroid/database/Cursor;
    .locals 21

    move-object/from16 v0, p2

    move/from16 v7, p13

    move-object/from16 v8, p14

    move/from16 v9, p16

    const/4 v10, 0x0

    const/4 v11, 0x1

    if-nez p12, :cond_0

    invoke-static {}, Lcom/miui/gallery/provider/AlbumManager;->isInShowHiddenMode()Z

    move-result v1

    if-nez v1, :cond_0

    if-eqz p17, :cond_0

    move v12, v11

    goto :goto_0

    :cond_0
    move v12, v10

    :goto_0
    if-nez p12, :cond_1

    invoke-static {}, Lcom/miui/gallery/provider/AlbumManager;->isInLocalMode()Z

    move-result v1

    if-eqz v1, :cond_1

    move v13, v11

    goto :goto_1

    :cond_1
    move v13, v10

    :goto_1
    if-eqz p15, :cond_3

    invoke-static {}, Lcom/miui/gallery/provider/MediaSortDateHelper;->getSortDateProvider()Lcom/miui/gallery/provider/MediaSortDateHelper$SortDateProvider;

    move-result-object v1

    sget-object v2, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->MODIFY_TIME:Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    invoke-interface {v1, v2}, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDateProvider;->getAlbumPathsBySortDate(Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;)Ljava/util/List;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v2

    if-eqz v2, :cond_2

    sget-object v2, Ljava/util/Locale;->US:Ljava/util/Locale;

    sget-object v3, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COVER_FORMAT:Ljava/lang/String;

    new-array v4, v11, [Ljava/lang/Object;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "\'"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v14, "\', \'"

    invoke-static {v14, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    aput-object v1, v4, v10

    invoke-static {v2, v3, v4}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    goto :goto_2

    :cond_2
    sget-object v1, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COVER_DEFAULT:Ljava/lang/String;

    goto :goto_2

    :cond_3
    const-string v1, ", -1 AS cover_id, NULL AS cover_path, NULL AS cover_sha1, 0 AS cover_sync_state, 0 AS cover_size, NULL AS latest_photo "

    :goto_2
    move-object v14, v1

    const-string v15, ""

    if-eqz p6, :cond_5

    if-eqz v8, :cond_4

    invoke-virtual/range {p14 .. p14}, Ljava/lang/Integer;->intValue()I

    move-result v1

    if-ne v1, v11, :cond_5

    :cond_4
    invoke-static {v13}, Lcom/miui/gallery/provider/AlbumManager;->isIncludeFaceAlbum(Z)Z

    move-result v1

    if-eqz v1, :cond_5

    invoke-static {}, Lcom/miui/gallery/provider/AlbumManager;->getFaceAlbumColumns()Ljava/lang/String;

    move-result-object v1

    move-object/from16 v16, v1

    goto :goto_3

    :cond_5
    move-object/from16 v16, v15

    :goto_3
    const/4 v6, 0x2

    if-eqz p5, :cond_7

    if-eqz v8, :cond_6

    invoke-virtual/range {p14 .. p14}, Ljava/lang/Integer;->intValue()I

    move-result v1

    if-ne v1, v6, :cond_7

    :cond_6
    invoke-static {v14, v12, v13, v9}, Lcom/miui/gallery/provider/AlbumManager;->getVideoAlbumColumns(Ljava/lang/String;ZZZ)Ljava/lang/String;

    move-result-object v1

    move-object/from16 v17, v1

    goto :goto_4

    :cond_7
    move-object/from16 v17, v15

    :goto_4
    if-eqz p7, :cond_8

    if-nez v13, :cond_8

    invoke-static {v12, v7, v8}, Lcom/miui/gallery/provider/AlbumManager;->getShareAlbumBasicColumns(ZZLjava/lang/Integer;)Ljava/lang/String;

    move-result-object v1

    move-object/from16 v18, v1

    goto :goto_5

    :cond_8
    move-object/from16 v18, v15

    :goto_5
    if-eqz p8, :cond_a

    if-eqz v8, :cond_9

    invoke-virtual/range {p14 .. p14}, Ljava/lang/Integer;->intValue()I

    move-result v1

    if-ne v1, v11, :cond_a

    :cond_9
    invoke-static {v14, v12, v13, v9}, Lcom/miui/gallery/provider/AlbumManager;->getPanoAlbumColumns(Ljava/lang/String;ZZZ)Ljava/lang/String;

    move-result-object v1

    move-object/from16 v19, v1

    goto :goto_6

    :cond_a
    move-object/from16 v19, v15

    :goto_6
    if-eqz p9, :cond_b

    move v1, v12

    move v2, v13

    move/from16 v3, p13

    move-object/from16 v4, p14

    move/from16 v5, p15

    move/from16 v20, v6

    move/from16 v6, p16

    invoke-static/range {v1 .. v6}, Lcom/miui/gallery/provider/AlbumManager;->getRecentAlbumColumns(ZZZLjava/lang/Integer;ZZ)Ljava/lang/String;

    move-result-object v1

    goto :goto_7

    :cond_b
    move/from16 v20, v6

    move-object v1, v15

    :goto_7
    if-eqz p10, :cond_c

    invoke-static {v14, v12, v13, v8, v9}, Lcom/miui/gallery/provider/AlbumManager;->getFavoritesAlbumColumns(Ljava/lang/String;ZZLjava/lang/Integer;Z)Ljava/lang/String;

    move-result-object v15

    :cond_c
    invoke-static {v12, v13}, Lcom/miui/gallery/provider/AlbumManager;->getAlbumBasicColumns(ZZ)Ljava/lang/String;

    move-result-object v2

    const-string v3, "size"

    move-object/from16 v4, p1

    invoke-static {v4, v3}, Lcom/miui/gallery/provider/AlbumManager;->isQueryColumn([Ljava/lang/String;Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_d

    const-string v3, ", sum(size) AS size "

    goto :goto_8

    :cond_d
    const-string v3, ", 0 AS size "

    :goto_8
    const/16 v5, 0xc

    new-array v5, v5, [Ljava/lang/Object;

    aput-object v2, v5, v10

    if-eqz v7, :cond_e

    sget-object v2, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_DEDUPLICATE_COUNT:Ljava/lang/String;

    goto :goto_9

    :cond_e
    sget-object v2, Lcom/miui/gallery/provider/AlbumManager;->SQL_ALBUM_COUNT:Ljava/lang/String;

    :goto_9
    aput-object v2, v5, v11

    aput-object v14, v5, v20

    const/4 v2, 0x3

    aput-object v3, v5, v2

    const/4 v2, 0x4

    invoke-static/range {p14 .. p14}, Lcom/miui/gallery/provider/AlbumManager;->getMediaTypeSelection(Ljava/lang/Integer;)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v5, v2

    const/4 v2, 0x5

    invoke-static {v13}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeNonLocalSelection(Z)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v5, v2

    const/4 v2, 0x6

    aput-object v18, v5, v2

    const/4 v2, 0x7

    aput-object v17, v5, v2

    const/16 v2, 0x8

    aput-object v19, v5, v2

    const/16 v2, 0x9

    aput-object v1, v5, v2

    const/16 v1, 0xa

    aput-object v15, v5, v1

    const/16 v1, 0xb

    aput-object v16, v5, v1

    const-string v1, "(SELECT * FROM (%s) LEFT JOIN (SELECT localGroupId AS _id%s%s%sFROM (SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud WHERE (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\')) %s%sGROUP BY localGroupId) USING (_id)%s %s %s %s %s %s)"

    invoke-static {v1, v5}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, ")"

    const-string v3, " AND ("

    if-eqz p11, :cond_10

    invoke-static/range {p2 .. p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_f

    invoke-static {v13}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeEmptyAlbumSelection(Z)Ljava/lang/String;

    move-result-object v0

    goto :goto_a

    :cond_f
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {v13}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeEmptyAlbumSelection(Z)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_a

    :cond_10
    if-nez p12, :cond_12

    if-eqz p9, :cond_12

    invoke-static/range {p2 .. p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_11

    invoke-static {}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeEmptyRecentAlbumSelection()Ljava/lang/String;

    move-result-object v0

    goto :goto_a

    :cond_11
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {}, Lcom/miui/gallery/provider/AlbumManager;->getExcludeEmptyRecentAlbumSelection()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    :cond_12
    :goto_a
    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object/from16 p5, p0

    move-object/from16 p6, v1

    move-object/from16 p7, p1

    move-object/from16 p8, v0

    move-object/from16 p9, p3

    move-object/from16 p10, v2

    move-object/from16 p11, v3

    move-object/from16 p12, p4

    move-object/from16 p13, p18

    invoke-virtual/range {p5 .. p13}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method public static queryFaceAlbumCover(Landroid/database/sqlite/SQLiteDatabase;)Landroid/database/Cursor;
    .locals 2

    new-instance v0, Lcom/miui/gallery/provider/AlbumManager$1;

    invoke-direct {v0}, Lcom/miui/gallery/provider/AlbumManager$1;-><init>()V

    const/4 v1, 0x0

    invoke-static {v1}, Lcom/miui/gallery/provider/AlbumManager;->isIncludeFaceAlbum(Z)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/provider/AlbumManager;->generateFaceAlbumCover(Landroid/database/sqlite/SQLiteDatabase;)Landroid/os/Bundle;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/database/AbstractCursor;->setExtras(Landroid/os/Bundle;)V

    :cond_0
    return-object v0
.end method

.method public static declared-synchronized queryScreenshotsAlbumId(Landroid/content/Context;)J
    .locals 5

    const-class v0, Lcom/miui/gallery/provider/AlbumManager;

    monitor-enter v0

    :try_start_0
    sget-wide v1, Lcom/miui/gallery/provider/AlbumManager;->sScreenshotsAlbumId:J

    const-wide/16 v3, -0x1

    cmp-long v1, v1, v3

    if-nez v1, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/provider/AlbumManager;->doQueryScreenshotsAlbumId(Landroid/content/Context;)J

    move-result-wide v1

    sput-wide v1, Lcom/miui/gallery/provider/AlbumManager;->sScreenshotsAlbumId:J

    :cond_0
    sget-wide v1, Lcom/miui/gallery/provider/AlbumManager;->sScreenshotsAlbumId:J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-wide v1

    :catchall_0
    move-exception p0

    monitor-exit v0

    throw p0
.end method
