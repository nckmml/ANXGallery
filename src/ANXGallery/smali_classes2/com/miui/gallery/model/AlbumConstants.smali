.class public interface abstract Lcom/miui/gallery/model/AlbumConstants;
.super Ljava/lang/Object;
.source "AlbumConstants.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/model/AlbumConstants$ShareAlbum;
    }
.end annotation


# static fields
.field public static final PROJECTION:[Ljava/lang/String;

.field public static final PROJECTION_NO_CLASSIFICATION:[Ljava/lang/String;

.field public static final SHARED_ALBUM_PROJECTION:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 22

    const/16 v0, 0x13

    new-array v0, v0, [Ljava/lang/String;

    const-string v1, "_id"

    const/4 v2, 0x0

    aput-object v1, v0, v2

    const/4 v2, 0x1

    const-string v3, "name"

    aput-object v3, v0, v2

    const/4 v2, 0x2

    const-string v3, "cover_id"

    aput-object v3, v0, v2

    const/4 v2, 0x3

    const-string v3, "cover_path"

    aput-object v3, v0, v2

    const/4 v2, 0x4

    const-string v3, "cover_sha1"

    aput-object v3, v0, v2

    const/4 v2, 0x5

    const-string v3, "cover_sync_state"

    aput-object v3, v0, v2

    const/4 v2, 0x6

    const-string v3, "media_count"

    aput-object v3, v0, v2

    const/4 v2, 0x7

    const-string v3, "face_people_id"

    aput-object v3, v0, v2

    const/16 v2, 0x8

    const-string v3, "baby_info"

    aput-object v3, v0, v2

    const/16 v2, 0x9

    const-string v3, "thumbnail_info"

    aput-object v3, v0, v2

    const/16 v2, 0xa

    const-string v3, "serverId"

    aput-object v3, v0, v2

    const/16 v2, 0xb

    const-string v3, "attributes"

    aput-object v3, v0, v2

    const/16 v2, 0xc

    const-string v3, "immutable"

    aput-object v3, v0, v2

    const/16 v2, 0xd

    const-string v3, "top_time"

    aput-object v3, v0, v2

    const/16 v2, 0xe

    const-string v3, "sortBy"

    aput-object v3, v0, v2

    const/16 v2, 0xf

    const-string v3, "baby_sharer_info"

    aput-object v3, v0, v2

    const/16 v2, 0x10

    const-string v3, "local_path"

    aput-object v3, v0, v2

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v3, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_ALBUM_CLASSIFICATION:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " AS "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "classification"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const/16 v3, 0x11

    aput-object v2, v0, v3

    const/16 v2, 0x12

    const-string v3, "cover_size"

    aput-object v3, v0, v2

    sput-object v0, Lcom/miui/gallery/model/AlbumConstants;->PROJECTION:[Ljava/lang/String;

    const-string v4, "_id"

    const-string v5, "name"

    const-string v6, "cover_id"

    const-string v7, "cover_path"

    const-string v8, "cover_sha1"

    const-string v9, "cover_sync_state"

    const-string v10, "media_count"

    const-string v11, "face_people_id"

    const-string v12, "baby_info"

    const-string v13, "thumbnail_info"

    const-string v14, "serverId"

    const-string v15, "attributes"

    const-string v16, "immutable"

    const-string v17, "top_time"

    const-string v18, "sortBy"

    const-string v19, "baby_sharer_info"

    const-string v20, "local_path"

    const-string v21, "cover_size"

    filled-new-array/range {v4 .. v21}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/model/AlbumConstants;->PROJECTION_NO_CLASSIFICATION:[Ljava/lang/String;

    const-string v0, "creatorId"

    const-string v2, "count"

    const-string v3, "nickname"

    filled-new-array {v1, v0, v2, v3}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/model/AlbumConstants;->SHARED_ALBUM_PROJECTION:[Ljava/lang/String;

    return-void
.end method
