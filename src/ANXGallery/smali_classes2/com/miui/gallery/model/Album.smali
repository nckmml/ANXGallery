.class public Lcom/miui/gallery/model/Album;
.super Lcom/miui/gallery/dao/base/Entity;
.source "Album.java"

# interfaces
.implements Landroid/os/Parcelable;
.implements Lcom/miui/gallery/model/AlbumConstants;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/model/Album$AlbumType;
    }
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/miui/gallery/model/Album;",
            ">;"
        }
    .end annotation
.end field

.field private static sLocalizedNamesCache:Lcom/google/common/cache/LoadingCache;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/google/common/cache/LoadingCache<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private mAlbumClassification:I

.field private mAlbumId:J

.field private mAlbumName:Ljava/lang/String;

.field private mAlbumType:Lcom/miui/gallery/model/Album$AlbumType;

.field private mAttributes:J

.field private mBabyInfo:Ljava/lang/String;

.field private mBabyShareInfo:Ljava/lang/String;

.field private mCount:I

.field private mCoverId:J

.field private mCoverPath:Ljava/lang/String;

.field private mCoverSha1:Ljava/lang/String;

.field private mCoverSize:J

.field private mCoverSyncState:I

.field private mIsImmutable:Z

.field private mIsPlaceholder:Z

.field private mLocalPath:Ljava/lang/String;

.field private mPeopleId:Ljava/lang/String;

.field private mServerId:Ljava/lang/String;

.field private mSortBy:J

.field private mThumbnailInfoOfBaby:Ljava/lang/String;

.field private mTopTime:J


# direct methods
.method static constructor <clinit>()V
    .locals 3

    invoke-static {}, Lcom/google/common/cache/CacheBuilder;->newBuilder()Lcom/google/common/cache/CacheBuilder;

    move-result-object v0

    const-wide/16 v1, 0xa

    invoke-virtual {v0, v1, v2}, Lcom/google/common/cache/CacheBuilder;->maximumSize(J)Lcom/google/common/cache/CacheBuilder;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/model/Album$1;

    invoke-direct {v1}, Lcom/miui/gallery/model/Album$1;-><init>()V

    invoke-virtual {v0, v1}, Lcom/google/common/cache/CacheBuilder;->build(Lcom/google/common/cache/CacheLoader;)Lcom/google/common/cache/LoadingCache;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/model/Album;->sLocalizedNamesCache:Lcom/google/common/cache/LoadingCache;

    new-instance v0, Lcom/miui/gallery/model/Album$2;

    invoke-direct {v0}, Lcom/miui/gallery/model/Album$2;-><init>()V

    sput-object v0, Lcom/miui/gallery/model/Album;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    return-void
.end method

.method public constructor <init>(J)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    iput-wide p1, p0, Lcom/miui/gallery/model/Album;->mAlbumId:J

    return-void
.end method

.method protected constructor <init>(Landroid/os/Parcel;)V
    .locals 5

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mId:J

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mAlbumId:J

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverId:J

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mCoverPath:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mCoverSha1:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/model/Album;->mCoverSyncState:I

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverSize:J

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/model/Album;->mCount:I

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mPeopleId:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyInfo:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mThumbnailInfoOfBaby:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mServerId:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mAttributes:J

    invoke-virtual {p1}, Landroid/os/Parcel;->readByte()B

    move-result v0

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    move v0, v1

    goto :goto_0

    :cond_0
    move v0, v2

    :goto_0
    iput-boolean v0, p0, Lcom/miui/gallery/model/Album;->mIsImmutable:Z

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v3

    iput-wide v3, p0, Lcom/miui/gallery/model/Album;->mTopTime:J

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v3

    iput-wide v3, p0, Lcom/miui/gallery/model/Album;->mSortBy:J

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyShareInfo:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mLocalPath:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/model/Album;->mAlbumClassification:I

    invoke-virtual {p1}, Landroid/os/Parcel;->readByte()B

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_1

    :cond_1
    move v1, v2

    :goto_1
    iput-boolean v1, p0, Lcom/miui/gallery/model/Album;->mIsPlaceholder:Z

    invoke-static {}, Lcom/miui/gallery/model/Album$AlbumType;->values()[Lcom/miui/gallery/model/Album$AlbumType;

    move-result-object v0

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result p1

    aget-object p1, v0, p1

    iput-object p1, p0, Lcom/miui/gallery/model/Album;->mAlbumType:Lcom/miui/gallery/model/Album$AlbumType;

    return-void
.end method

.method public static fromCursorSafe(Landroid/database/Cursor;)Lcom/miui/gallery/model/Album;
    .locals 5

    const/4 v0, 0x0

    if-eqz p0, :cond_16

    invoke-interface {p0}, Landroid/database/Cursor;->isClosed()Z

    move-result v1

    if-eqz v1, :cond_0

    goto/16 :goto_0

    :cond_0
    const-string v1, "_id"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    const/4 v2, -0x1

    if-ne v2, v1, :cond_1

    return-object v0

    :cond_1
    new-instance v0, Lcom/miui/gallery/model/Album;

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/model/Album;-><init>(J)V

    const-string v1, "name"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_2

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    :cond_2
    const-string v1, "cover_id"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_3

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/Album;->mCoverId:J

    :cond_3
    const-string v1, "cover_path"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_4

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mCoverPath:Ljava/lang/String;

    :cond_4
    const-string v1, "cover_sha1"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_5

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mCoverSha1:Ljava/lang/String;

    :cond_5
    const-string v1, "cover_sync_state"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_6

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    iput v1, v0, Lcom/miui/gallery/model/Album;->mCoverSyncState:I

    :cond_6
    const-string v1, "cover_size"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_7

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/Album;->mCoverSize:J

    :cond_7
    const-string v1, "media_count"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_8

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    iput v1, v0, Lcom/miui/gallery/model/Album;->mCount:I

    :cond_8
    iget v1, v0, Lcom/miui/gallery/model/Album;->mCount:I

    const v2, 0x7fffffff

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-ne v1, v2, :cond_9

    iput v3, v0, Lcom/miui/gallery/model/Album;->mCount:I

    iput-boolean v4, v0, Lcom/miui/gallery/model/Album;->mIsPlaceholder:Z

    :cond_9
    const-string v1, "face_people_id"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_a

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mPeopleId:Ljava/lang/String;

    :cond_a
    const-string v1, "baby_info"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_b

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mBabyInfo:Ljava/lang/String;

    :cond_b
    const-string v1, "thumbnail_info"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_c

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mThumbnailInfoOfBaby:Ljava/lang/String;

    :cond_c
    const-string v1, "serverId"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_d

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mServerId:Ljava/lang/String;

    :cond_d
    const-string v1, "attributes"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_e

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/Album;->mAttributes:J

    :cond_e
    const-string v1, "immutable"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_10

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    if-ne v1, v4, :cond_f

    move v3, v4

    :cond_f
    iput-boolean v3, v0, Lcom/miui/gallery/model/Album;->mIsImmutable:Z

    :cond_10
    const-string v1, "top_time"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_11

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/Album;->mTopTime:J

    :cond_11
    const-string v1, "sortBy"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_12

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/Album;->mSortBy:J

    :cond_12
    const-string v1, "baby_sharer_info"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_13

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mBabyShareInfo:Ljava/lang/String;

    :cond_13
    const-string v1, "local_path"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_14

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/Album;->mLocalPath:Ljava/lang/String;

    :cond_14
    const-string v1, "classification"

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->isNull(I)Z

    move-result v2

    if-nez v2, :cond_15

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result p0

    iput p0, v0, Lcom/miui/gallery/model/Album;->mAlbumClassification:I

    :cond_15
    invoke-static {v0}, Lcom/miui/gallery/model/Album;->parseAlbumType(Lcom/miui/gallery/model/Album;)Lcom/miui/gallery/model/Album$AlbumType;

    move-result-object p0

    iput-object p0, v0, Lcom/miui/gallery/model/Album;->mAlbumType:Lcom/miui/gallery/model/Album$AlbumType;

    :cond_16
    :goto_0
    return-object v0
.end method

.method private static getAlbumName(Ljava/lang/String;JLjava/lang/String;)Ljava/lang/String;
    .locals 4

    const-wide/16 v0, 0x1

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    const p0, 0x7f100056

    goto :goto_0

    :cond_0
    const-wide/16 v2, 0x2

    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    const p0, 0x7f100072

    goto :goto_0

    :cond_1
    const-wide/32 v2, 0x7fffffff

    cmp-long p0, p1, v2

    if-nez p0, :cond_2

    const p0, 0x7f100083

    goto :goto_0

    :cond_2
    const-wide/32 v2, 0x7ffffffe

    cmp-long p0, p1, v2

    if-nez p0, :cond_3

    const p0, 0x7f10005b

    goto :goto_0

    :cond_3
    const-wide/32 v2, 0x7ffffffd

    cmp-long p0, p1, v2

    if-nez p0, :cond_4

    const p0, 0x7f10006e

    goto :goto_0

    :cond_4
    const-wide/32 v2, 0x7ffffffc

    cmp-long p0, p1, v2

    if-nez p0, :cond_5

    const p0, 0x7f100066

    goto :goto_0

    :cond_5
    const-wide/32 v2, 0x7ffffffa

    cmp-long p0, p1, v2

    if-nez p0, :cond_6

    const p0, 0x7f10005c

    goto :goto_0

    :cond_6
    move p0, v1

    :goto_0
    if-eqz p0, :cond_7

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/Locale;->toString()Ljava/lang/String;

    move-result-object p1

    sget-object p2, Lcom/miui/gallery/model/Album;->sLocalizedNamesCache:Lcom/google/common/cache/LoadingCache;

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/Object;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    aput-object p0, v2, v1

    const/4 p0, 0x1

    aput-object p1, v2, p0

    const-string p0, "%d_%s"

    invoke-static {v0, p0, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-interface {p2, p0}, Lcom/google/common/cache/LoadingCache;->getUnchecked(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    goto :goto_1

    :cond_7
    const/4 p0, 0x0

    :goto_1
    if-eqz p0, :cond_8

    goto :goto_2

    :cond_8
    move-object p0, p3

    :goto_2
    return-object p0
.end method

.method public static getDownloadUri(IJ)Landroid/net/Uri;
    .locals 0

    if-nez p0, :cond_0

    invoke-static {p1, p2}, Lcom/miui/gallery/model/Album;->getDownloadUri(J)Landroid/net/Uri;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static getDownloadUri(J)Landroid/net/Uri;
    .locals 0

    invoke-static {p0, p1}, Lcom/miui/gallery/util/uil/CloudUriAdapter;->getDownloadUri(J)Landroid/net/Uri;

    move-result-object p0

    return-object p0
.end method

.method public static isAutoUploadedAlbum(Lcom/miui/gallery/model/Album;)Z
    .locals 5

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v1

    const-wide/16 v3, 0x1

    and-long/2addr v1, v3

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-nez v1, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getServerId()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/model/Album;->isSystemAlbum(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/model/Album;->isScreenshotsAlbum(Lcom/miui/gallery/model/Album;)Z

    move-result v1

    if-eqz v1, :cond_2

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v1

    invoke-static {v1, v2}, Lcom/miui/gallery/model/Album;->isOtherShareAlbum(J)Z

    move-result p0

    if-eqz p0, :cond_3

    :cond_2
    const/4 v0, 0x1

    :cond_3
    return v0
.end method

.method public static isFaceAlbum(J)Z
    .locals 2

    const-wide/32 v0, 0x7ffffffe

    cmp-long p0, p0, v0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isHiddenAlbum(J)Z
    .locals 2

    const-wide/16 v0, 0x10

    and-long/2addr p0, v0

    const-wide/16 v0, 0x0

    cmp-long p0, p0, v0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isOtherAlbum(J)Z
    .locals 2

    const-wide/16 v0, 0x40

    and-long/2addr p0, v0

    const-wide/16 v0, 0x0

    cmp-long p0, p0, v0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isOtherShareAlbum(J)Z
    .locals 0

    invoke-static {p0, p1}, Lcom/miui/gallery/provider/ShareAlbumManager;->isOtherShareAlbumId(J)Z

    move-result p0

    return p0
.end method

.method public static isOwnerShareAlbum(J)Z
    .locals 2

    const-wide/32 v0, 0x7ffe795f

    cmp-long v0, p0, v0

    if-gez v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->getInstance()Lcom/miui/gallery/share/ShareAlbumCacheManager;

    move-result-object v0

    invoke-virtual {v0, p0, p1}, Lcom/miui/gallery/share/ShareAlbumCacheManager;->containsKey(J)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isPanoAlbum(J)Z
    .locals 2

    const-wide/32 v0, 0x7ffffffd

    cmp-long p0, p0, v0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isRecentAlbum(J)Z
    .locals 2

    const-wide/32 v0, 0x7ffffffc

    cmp-long p0, p0, v0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isRubbishAlbum(J)Z
    .locals 4

    const-wide/16 v0, 0x40

    and-long/2addr v0, p0

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    const-wide/16 v0, 0x10

    and-long/2addr v0, p0

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    const-wide/16 v0, 0x20

    and-long/2addr p0, v0

    cmp-long p0, p0, v2

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isScreenshotsAlbum(Lcom/miui/gallery/model/Album;)Z
    .locals 2

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const-wide/16 v0, 0x2

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getServerId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static isShareAlbum(J)Z
    .locals 1

    invoke-static {p0, p1}, Lcom/miui/gallery/model/Album;->isOtherShareAlbum(J)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p0, p1}, Lcom/miui/gallery/model/Album;->isOwnerShareAlbum(J)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p0, 0x1

    :goto_1
    return p0
.end method

.method private static isSystemAlbum(Ljava/lang/String;)Z
    .locals 5

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->ALL_SYSTEM_ALBUM_SERVER_IDS:[Ljava/lang/Long;

    array-length v1, v0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_1

    aget-object v4, v0, v3

    invoke-static {v4}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    return v2
.end method

.method private static parseAlbumType(Lcom/miui/gallery/model/Album;)Lcom/miui/gallery/model/Album$AlbumType;
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getSortBy()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/preference/GalleryPreferences$Album;->isForceTopAlbumByTopTime(J)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lcom/miui/gallery/model/Album$AlbumType;->PINNED:Lcom/miui/gallery/model/Album$AlbumType;

    return-object p0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getServerId()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/model/Album;->isSystemAlbum(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    sget-object p0, Lcom/miui/gallery/model/Album$AlbumType;->SYSTEM:Lcom/miui/gallery/model/Album$AlbumType;

    return-object p0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getBabyInfo()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    sget-object p0, Lcom/miui/gallery/model/Album$AlbumType;->BABY:Lcom/miui/gallery/model/Album$AlbumType;

    return-object p0

    :cond_2
    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/provider/ShareAlbumManager;->isOtherShareAlbumId(J)Z

    move-result p0

    if-eqz p0, :cond_3

    sget-object p0, Lcom/miui/gallery/model/Album$AlbumType;->OTHERS_SHARE:Lcom/miui/gallery/model/Album$AlbumType;

    return-object p0

    :cond_3
    sget-object p0, Lcom/miui/gallery/model/Album$AlbumType;->NORMAL:Lcom/miui/gallery/model/Album$AlbumType;

    return-object p0
.end method


# virtual methods
.method public describeContents()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public getAlbumClassification()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/model/Album;->mAlbumClassification:I

    return v0
.end method

.method public getAlbumId()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mAlbumId:J

    return-wide v0
.end method

.method public getAlbumName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    return-object v0
.end method

.method public getAlbumType()Lcom/miui/gallery/model/Album$AlbumType;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mAlbumType:Lcom/miui/gallery/model/Album$AlbumType;

    return-object v0
.end method

.method public getAttributes()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mAttributes:J

    return-wide v0
.end method

.method public getBabyInfo()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyInfo:Ljava/lang/String;

    return-object v0
.end method

.method public getBabyShareInfo()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyShareInfo:Ljava/lang/String;

    return-object v0
.end method

.method public getCount()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/model/Album;->mCount:I

    return v0
.end method

.method public getCoverId()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverId:J

    return-wide v0
.end method

.method public getCoverPath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mCoverPath:Ljava/lang/String;

    return-object v0
.end method

.method public getCoverSize()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverSize:J

    return-wide v0
.end method

.method public getCoverSyncState()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/model/Album;->mCoverSyncState:I

    return v0
.end method

.method public getLocalPath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mLocalPath:Ljava/lang/String;

    return-object v0
.end method

.method public getLocalizedAlbumName()Ljava/lang/String;
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mServerId:Ljava/lang/String;

    iget-wide v1, p0, Lcom/miui/gallery/model/Album;->mAlbumId:J

    iget-object v3, p0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/model/Album;->getAlbumName(Ljava/lang/String;JLjava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getPeopleId()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mPeopleId:Ljava/lang/String;

    return-object v0
.end method

.method public getServerId()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mServerId:Ljava/lang/String;

    return-object v0
.end method

.method public getSortBy()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mSortBy:J

    return-wide v0
.end method

.method protected getTableColumns()Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/miui/gallery/dao/base/TableColumn;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const-string v1, "INTEGER"

    const-string v2, "album_id"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "TEXT"

    const-string v3, "name"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "cover_id"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "cover_path"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "cover_sha1"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "cover_sync_state"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "cover_size"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "media_count"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "face_people_id"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "baby_info"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "thumbnail_info"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "serverId"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "attributes"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "immutable"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "top_time"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "sortBy"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "baby_sharer_info"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "local_path"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "classification"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/model/Album;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    return-object v0
.end method

.method public getThumbnailInfoOfBaby()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mThumbnailInfoOfBaby:Ljava/lang/String;

    return-object v0
.end method

.method public isAutoUploadedAlbum()Z
    .locals 1

    invoke-static {p0}, Lcom/miui/gallery/model/Album;->isAutoUploadedAlbum(Lcom/miui/gallery/model/Album;)Z

    move-result v0

    return v0
.end method

.method public isBabyAlbum()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyInfo:Ljava/lang/String;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isFaceAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isFaceAlbum(J)Z

    move-result v0

    return v0
.end method

.method public isHiddenAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isHiddenAlbum(J)Z

    move-result v0

    return v0
.end method

.method public isImmutable()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/model/Album;->mIsImmutable:Z

    return v0
.end method

.method public isOtherAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isOtherAlbum(J)Z

    move-result v0

    return v0
.end method

.method public isOtherShareAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isOtherShareAlbum(J)Z

    move-result v0

    return v0
.end method

.method public isOwnerShareAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isOwnerShareAlbum(J)Z

    move-result v0

    return v0
.end method

.method public isPanoAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isPanoAlbum(J)Z

    move-result v0

    return v0
.end method

.method public isPlaceholder()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/model/Album;->mIsPlaceholder:Z

    return v0
.end method

.method public isRecentAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isRecentAlbum(J)Z

    move-result v0

    return v0
.end method

.method public isRubbishAlbum()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/model/Album;->isRubbishAlbum(J)Z

    move-result v0

    return v0
.end method

.method protected onConvertToContents(Landroid/content/ContentValues;)V
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mAlbumId:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "album_id"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    const-string v1, "name"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverId:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "cover_id"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mCoverPath:Ljava/lang/String;

    const-string v1, "cover_path"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mCoverSha1:Ljava/lang/String;

    const-string v1, "cover_sha1"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget v0, p0, Lcom/miui/gallery/model/Album;->mCoverSyncState:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "cover_sync_state"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverSize:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "cover_size"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget v0, p0, Lcom/miui/gallery/model/Album;->mCount:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "media_count"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mPeopleId:Ljava/lang/String;

    const-string v1, "face_people_id"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyInfo:Ljava/lang/String;

    const-string v1, "baby_info"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mThumbnailInfoOfBaby:Ljava/lang/String;

    const-string v1, "thumbnail_info"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mServerId:Ljava/lang/String;

    const-string v1, "serverId"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mAttributes:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "attributes"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-boolean v0, p0, Lcom/miui/gallery/model/Album;->mIsImmutable:Z

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "immutable"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mTopTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "top_time"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mSortBy:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "sortBy"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyShareInfo:Ljava/lang/String;

    const-string v1, "baby_sharer_info"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/Album;->mLocalPath:Ljava/lang/String;

    const-string v1, "local_path"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget v0, p0, Lcom/miui/gallery/model/Album;->mAlbumClassification:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "classification"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    return-void
.end method

.method protected onInitFromCursor(Landroid/database/Cursor;)V
    .locals 2

    const-string v0, "album_id"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mAlbumId:J

    const-string v0, "name"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    const-string v0, "cover_id"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverId:J

    const-string v0, "cover_path"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mCoverPath:Ljava/lang/String;

    const-string v0, "cover_sha1"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mCoverSha1:Ljava/lang/String;

    const-string v0, "cover_sync_state"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/model/Album;->mCoverSyncState:I

    const-string v0, "cover_size"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverSize:J

    const-string v0, "media_count"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/model/Album;->mCount:I

    const-string v0, "face_people_id"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mPeopleId:Ljava/lang/String;

    const-string v0, "baby_info"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyInfo:Ljava/lang/String;

    const-string v0, "thumbnail_info"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mThumbnailInfoOfBaby:Ljava/lang/String;

    const-string v0, "serverId"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mServerId:Ljava/lang/String;

    const-string v0, "attributes"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mAttributes:J

    const-string v0, "immutable"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    iput-boolean v1, p0, Lcom/miui/gallery/model/Album;->mIsImmutable:Z

    const-string v0, "top_time"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mTopTime:J

    const-string v0, "sortBy"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/Album;->mSortBy:J

    const-string v0, "baby_sharer_info"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mBabyShareInfo:Ljava/lang/String;

    const-string v0, "local_path"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/Album;->mLocalPath:Ljava/lang/String;

    const-string v0, "classification"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/Album;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/model/Album;->mAlbumClassification:I

    invoke-static {p0}, Lcom/miui/gallery/model/Album;->parseAlbumType(Lcom/miui/gallery/model/Album;)Lcom/miui/gallery/model/Album$AlbumType;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/model/Album;->mAlbumType:Lcom/miui/gallery/model/Album$AlbumType;

    return-void
.end method

.method public setAlbumName(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    return-void
.end method

.method public setAlbumType(Lcom/miui/gallery/model/Album$AlbumType;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/Album;->mAlbumType:Lcom/miui/gallery/model/Album$AlbumType;

    return-void
.end method

.method public setCount(I)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/model/Album;->mCount:I

    return-void
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mId:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mAlbumId:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mAlbumName:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverId:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mCoverPath:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mCoverSha1:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget p2, p0, Lcom/miui/gallery/model/Album;->mCoverSyncState:I

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeInt(I)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mCoverSize:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    iget p2, p0, Lcom/miui/gallery/model/Album;->mCount:I

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeInt(I)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mPeopleId:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mBabyInfo:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mThumbnailInfoOfBaby:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mServerId:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mAttributes:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    iget-boolean p2, p0, Lcom/miui/gallery/model/Album;->mIsImmutable:Z

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeByte(B)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mTopTime:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    iget-wide v0, p0, Lcom/miui/gallery/model/Album;->mSortBy:J

    invoke-virtual {p1, v0, v1}, Landroid/os/Parcel;->writeLong(J)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mBabyShareInfo:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mLocalPath:Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeString(Ljava/lang/String;)V

    iget p2, p0, Lcom/miui/gallery/model/Album;->mAlbumClassification:I

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeInt(I)V

    iget-boolean p2, p0, Lcom/miui/gallery/model/Album;->mIsPlaceholder:Z

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeInt(I)V

    iget-object p2, p0, Lcom/miui/gallery/model/Album;->mAlbumType:Lcom/miui/gallery/model/Album$AlbumType;

    invoke-virtual {p2}, Lcom/miui/gallery/model/Album$AlbumType;->ordinal()I

    move-result p2

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeInt(I)V

    return-void
.end method
