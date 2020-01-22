.class public Lcom/miui/gallery/model/HomeMedia;
.super Lcom/miui/gallery/dao/base/Entity;
.source "HomeMedia.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/model/HomeMedia$Constants;
    }
.end annotation


# instance fields
.field private isFavorite:I

.field private mAliasClearPath:Ljava/lang/String;

.field private mAliasCreateDate:J

.field private mAliasCreateTime:J

.field private mAliasMicroPath:Ljava/lang/String;

.field private mAliasSortTime:J

.field private mBurstGroup:J

.field private mDuration:J

.field private mLocation:Ljava/lang/String;

.field private mMediaId:J

.field private mMimeType:Ljava/lang/String;

.field private mOriginPath:Ljava/lang/String;

.field private mSha1:Ljava/lang/String;

.field private mSize:J

.field private mSpecialTypeFlags:J

.field private mSyncState:J

.field private mThumbPath:Ljava/lang/String;

.field private mTitle:Ljava/lang/String;

.field private mType:I


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    return-void
.end method

.method public static convertFromMediaCursor(Landroid/database/Cursor;)Lcom/miui/gallery/model/HomeMedia;
    .locals 3

    new-instance v0, Lcom/miui/gallery/model/HomeMedia;

    invoke-direct {v0}, Lcom/miui/gallery/model/HomeMedia;-><init>()V

    const/4 v1, 0x0

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mMediaId:J

    const/4 v1, 0x1

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mAliasMicroPath:Ljava/lang/String;

    const/4 v1, 0x2

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateDate:J

    const/4 v1, 0x3

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateTime:J

    const/4 v1, 0x4

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mLocation:Ljava/lang/String;

    const/4 v1, 0x5

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mSha1:Ljava/lang/String;

    const/4 v1, 0x6

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    iput v1, v0, Lcom/miui/gallery/model/HomeMedia;->mType:I

    const/4 v1, 0x7

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mDuration:J

    const/16 v1, 0x8

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mMimeType:Ljava/lang/String;

    const/16 v1, 0x9

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    int-to-long v1, v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mSyncState:J

    const/16 v1, 0xa

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mThumbPath:Ljava/lang/String;

    const/16 v1, 0xb

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mOriginPath:Ljava/lang/String;

    const/16 v1, 0xc

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mAliasClearPath:Ljava/lang/String;

    const/16 v1, 0xd

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    iput v1, v0, Lcom/miui/gallery/model/HomeMedia;->isFavorite:I

    const/16 v1, 0xe

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    int-to-long v1, v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mSpecialTypeFlags:J

    const/16 v1, 0xf

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mAliasSortTime:J

    const/16 v1, 0x10

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mSize:J

    const/16 v1, 0x11

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/model/HomeMedia;->mTitle:Ljava/lang/String;

    const/16 v1, 0x12

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    iput-wide v1, v0, Lcom/miui/gallery/model/HomeMedia;->mBurstGroup:J

    return-object v0
.end method


# virtual methods
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

    const-string v2, "media_id"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "TEXT"

    const-string v3, "alias_micro_thumbnail"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "alias_create_date"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "alias_create_time"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "location"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "sha1"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "serverType"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "duration"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "mimeType"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "alias_sync_state"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "thumbnailFile"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "localFile"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "alias_clear_thumbnail"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "alias_is_favorite"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "specialTypeFlags"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "alias_sort_time"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "size"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "title"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "burst_group_id"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/model/HomeMedia;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    return-object v0
.end method

.method protected onConvertToContents(Landroid/content/ContentValues;)V
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mMediaId:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "media_id"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasMicroPath:Ljava/lang/String;

    const-string v1, "alias_micro_thumbnail"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateDate:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "alias_create_date"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "alias_create_time"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mLocation:Ljava/lang/String;

    const-string v1, "location"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSha1:Ljava/lang/String;

    const-string v1, "sha1"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget v0, p0, Lcom/miui/gallery/model/HomeMedia;->mType:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "serverType"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mDuration:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "duration"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mMimeType:Ljava/lang/String;

    const-string v1, "mimeType"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSyncState:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "alias_sync_state"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mThumbPath:Ljava/lang/String;

    const-string v1, "thumbnailFile"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mOriginPath:Ljava/lang/String;

    const-string v1, "localFile"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasClearPath:Ljava/lang/String;

    const-string v1, "alias_clear_thumbnail"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget v0, p0, Lcom/miui/gallery/model/HomeMedia;->isFavorite:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "alias_is_favorite"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSpecialTypeFlags:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "specialTypeFlags"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasSortTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "alias_sort_time"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSize:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "size"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mTitle:Ljava/lang/String;

    const-string v1, "title"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mBurstGroup:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "burst_group_id"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    return-void
.end method

.method protected onInitFromCursor(Landroid/database/Cursor;)V
    .locals 2

    const-string v0, "media_id"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mMediaId:J

    const-string v0, "alias_micro_thumbnail"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasMicroPath:Ljava/lang/String;

    const-string v0, "alias_create_date"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateDate:J

    const-string v0, "alias_create_time"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateTime:J

    const-string v0, "location"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mLocation:Ljava/lang/String;

    const-string v0, "sha1"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSha1:Ljava/lang/String;

    const-string v0, "serverType"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/model/HomeMedia;->mType:I

    const-string v0, "duration"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mDuration:J

    const-string v0, "mimeType"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mMimeType:Ljava/lang/String;

    const-string v0, "alias_sync_state"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    int-to-long v0, v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSyncState:J

    const-string v0, "thumbnailFile"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mThumbPath:Ljava/lang/String;

    const-string v0, "localFile"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mOriginPath:Ljava/lang/String;

    const-string v0, "alias_clear_thumbnail"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasClearPath:Ljava/lang/String;

    const-string v0, "alias_is_favorite"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/model/HomeMedia;->isFavorite:I

    const-string v0, "specialTypeFlags"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    int-to-long v0, v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSpecialTypeFlags:J

    const-string v0, "alias_sort_time"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasSortTime:J

    const-string v0, "size"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mSize:J

    const-string v0, "title"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/model/HomeMedia;->mTitle:Ljava/lang/String;

    const-string v0, "burst_group_id"

    invoke-static {p1, v0}, Lcom/miui/gallery/model/HomeMedia;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/model/HomeMedia;->mBurstGroup:J

    return-void
.end method

.method public values()[Ljava/lang/Object;
    .locals 3

    const/16 v0, 0x13

    new-array v0, v0, [Ljava/lang/Object;

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mMediaId:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x0

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasMicroPath:Ljava/lang/String;

    const/4 v2, 0x1

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateDate:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x2

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasCreateTime:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x3

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mLocation:Ljava/lang/String;

    const/4 v2, 0x4

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mSha1:Ljava/lang/String;

    const/4 v2, 0x5

    aput-object v1, v0, v2

    iget v1, p0, Lcom/miui/gallery/model/HomeMedia;->mType:I

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const/4 v2, 0x6

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mDuration:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x7

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mMimeType:Ljava/lang/String;

    const/16 v2, 0x8

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mSyncState:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/16 v2, 0x9

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mThumbPath:Ljava/lang/String;

    const/16 v2, 0xa

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mOriginPath:Ljava/lang/String;

    const/16 v2, 0xb

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasClearPath:Ljava/lang/String;

    const/16 v2, 0xc

    aput-object v1, v0, v2

    iget v1, p0, Lcom/miui/gallery/model/HomeMedia;->isFavorite:I

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const/16 v2, 0xd

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mSpecialTypeFlags:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/16 v2, 0xe

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mAliasSortTime:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/16 v2, 0xf

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mSize:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/16 v2, 0x10

    aput-object v1, v0, v2

    iget-object v1, p0, Lcom/miui/gallery/model/HomeMedia;->mTitle:Ljava/lang/String;

    const/16 v2, 0x11

    aput-object v1, v0, v2

    iget-wide v1, p0, Lcom/miui/gallery/model/HomeMedia;->mBurstGroup:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/16 v2, 0x12

    aput-object v1, v0, v2

    return-object v0
.end method
