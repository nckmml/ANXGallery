.class public Lcom/miui/gallery/data/DBCloud;
.super Lcom/miui/gallery/data/DBImage;
.source "DBCloud.java"


# instance fields
.field private mAddress:Ljava/lang/String;

.field private mCanModified:Z

.field protected mExtraGps:Ljava/lang/String;

.field private mIsPublic:Z

.field private mOriginFileName:Ljava/lang/String;

.field private mOriginSha1:Ljava/lang/String;

.field private mPublicUrl:Ljava/lang/String;

.field private mShareUrl:Ljava/lang/String;

.field private mThumbnailInfo:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/database/Cursor;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/data/DBImage;-><init>(Landroid/database/Cursor;)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/data/DBImage;-><init>(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public getAlbumId()Ljava/lang/String;
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getGroupId()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getBaseUri()Landroid/net/Uri;
    .locals 1

    sget-object v0, Lcom/miui/gallery/cloud/GalleryCloudUtils;->CLOUD_URI:Landroid/net/Uri;

    return-object v0
.end method

.method protected getBasicValues(Lorg/json/JSONObject;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    invoke-super {p0, p1}, Lcom/miui/gallery/data/DBImage;->getBasicValues(Lorg/json/JSONObject;)V

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getServerType()I

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/data/DBCloud;->mThumbnailInfo:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getThumbnailInfo()Lcom/miui/gallery/cloud/ThumbnailInfo;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/ThumbnailInfo;->getFaceId()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getThumbnailInfo()Lcom/miui/gallery/cloud/ThumbnailInfo;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/ThumbnailInfo;->getFaceId()Ljava/lang/String;

    move-result-object v0

    const-string v1, "faceId"

    invoke-virtual {p1, v1, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getServerType()I

    move-result v0

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getServerType()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_2

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->isFavorite()Z

    move-result v0

    const-string v1, "isFavorite"

    invoke-virtual {p1, v1, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    :cond_2
    return-void
.end method

.method public getOriginSha1()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/data/DBCloud;->mOriginSha1:Ljava/lang/String;

    return-object v0
.end method

.method public getPublicUrl()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/data/DBCloud;->mPublicUrl:Ljava/lang/String;

    return-object v0
.end method

.method public getRequestId()Ljava/lang/String;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getServerId()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getShareUrl()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/data/DBCloud;->mShareUrl:Ljava/lang/String;

    return-object v0
.end method

.method public getTagId()Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "DBCloud"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getThumbnailInfo()Lcom/miui/gallery/cloud/ThumbnailInfo;
    .locals 4

    new-instance v0, Lcom/miui/gallery/cloud/ThumbnailInfo;

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/data/DBCloud;->mThumbnailInfo:Ljava/lang/String;

    const/4 v3, 0x0

    invoke-direct {v0, v1, v3, v2}, Lcom/miui/gallery/cloud/ThumbnailInfo;-><init>(IZLjava/lang/String;)V

    return-object v0
.end method

.method public isPublic()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/data/DBCloud;->mIsPublic:Z

    return v0
.end method

.method public isUbiFocus()Z
    .locals 1

    iget v0, p0, Lcom/miui/gallery/data/DBCloud;->mUbiSubImageCount:I

    if-lez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public reloadData(Landroid/database/Cursor;)Z
    .locals 12

    const-string v0, "DBCloud"

    invoke-super {p0, p1}, Lcom/miui/gallery/data/DBImage;->reloadData(Landroid/database/Cursor;)Z

    move-result v1

    new-instance v2, Lcom/miui/gallery/util/UpdateHelper;

    invoke-direct {v2}, Lcom/miui/gallery/util/UpdateHelper;-><init>()V

    iget-boolean v3, p0, Lcom/miui/gallery/data/DBCloud;->mCanModified:Z

    const/16 v4, 0x2d

    invoke-interface {p1, v4}, Landroid/database/Cursor;->getInt(I)I

    move-result v4

    invoke-virtual {v2, v3, v4}, Lcom/miui/gallery/util/UpdateHelper;->update(II)I

    move-result v3

    const/4 v4, 0x0

    const/4 v5, 0x1

    if-ne v3, v5, :cond_0

    move v3, v5

    goto :goto_0

    :cond_0
    move v3, v4

    :goto_0
    iput-boolean v3, p0, Lcom/miui/gallery/data/DBCloud;->mCanModified:Z

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mShareUrl:Ljava/lang/String;

    const/16 v6, 0x2e

    invoke-static {p1, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->getCursorString(Landroid/database/Cursor;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mShareUrl:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mCreatorId:Ljava/lang/String;

    const/16 v6, 0x32

    invoke-static {p1, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->getCursorString(Landroid/database/Cursor;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mCreatorId:Ljava/lang/String;

    iget-boolean v3, p0, Lcom/miui/gallery/data/DBCloud;->mIsPublic:Z

    const/16 v6, 0x33

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getInt(I)I

    move-result v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(II)I

    move-result v3

    if-ne v3, v5, :cond_1

    move v3, v5

    goto :goto_1

    :cond_1
    move v3, v4

    :goto_1
    iput-boolean v3, p0, Lcom/miui/gallery/data/DBCloud;->mIsPublic:Z

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mPublicUrl:Ljava/lang/String;

    const/16 v6, 0x34

    invoke-static {p1, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->getCursorString(Landroid/database/Cursor;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mPublicUrl:Ljava/lang/String;

    iget-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mDownloadFileAddTime:J

    const/16 v3, 0x36

    invoke-interface {p1, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v8

    invoke-virtual {v2, v6, v7, v8, v9}, Lcom/miui/gallery/util/UpdateHelper;->update(JJ)J

    move-result-wide v6

    iput-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mDownloadFileAddTime:J

    iget-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mMixedDateTime:J

    const/16 v3, 0x37

    invoke-interface {p1, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v8

    invoke-virtual {v2, v6, v7, v8, v9}, Lcom/miui/gallery/util/UpdateHelper;->update(JJ)J

    move-result-wide v6

    iput-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mMixedDateTime:J

    iget v3, p0, Lcom/miui/gallery/data/DBCloud;->mUbiSubImageCount:I

    const/16 v6, 0x38

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getInt(I)I

    move-result v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(II)I

    move-result v3

    iput v3, p0, Lcom/miui/gallery/data/DBCloud;->mUbiSubImageCount:I

    iget v3, p0, Lcom/miui/gallery/data/DBCloud;->mUbiFocusIndex:I

    const/16 v6, 0x39

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getInt(I)I

    move-result v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(II)I

    move-result v3

    iput v3, p0, Lcom/miui/gallery/data/DBCloud;->mUbiFocusIndex:I

    iget v3, p0, Lcom/miui/gallery/data/DBCloud;->mUbiSubIndex:I

    const/16 v6, 0x3a

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getInt(I)I

    move-result v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(II)I

    move-result v3

    iput v3, p0, Lcom/miui/gallery/data/DBCloud;->mUbiSubIndex:I

    iget v3, p0, Lcom/miui/gallery/data/DBCloud;->mLables:I

    const/16 v6, 0x41

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getInt(I)I

    move-result v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(II)I

    move-result v3

    iput v3, p0, Lcom/miui/gallery/data/DBCloud;->mLables:I

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mEditedColumns:Ljava/lang/String;

    const/16 v6, 0x3b

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mEditedColumns:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mFromLocalGroupId:Ljava/lang/String;

    const/16 v6, 0x3c

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mFromLocalGroupId:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mSecretKey:[B

    const/16 v6, 0x3d

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getBlob(I)[B

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update([B[B)[B

    move-result-object v3

    if-eqz v3, :cond_2

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mSecretKey:[B

    :cond_2
    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mAppKey:Ljava/lang/String;

    const/16 v6, 0x3e

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mAppKey:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mBabyInfoJson:Ljava/lang/String;

    const/16 v6, 0x3f

    invoke-static {p1, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->getCursorString(Landroid/database/Cursor;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mBabyInfoJson:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mPeopleFaceId:Ljava/lang/String;

    const/16 v6, 0x40

    invoke-static {p1, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->getCursorString(Landroid/database/Cursor;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mPeopleFaceId:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mThumbnailInfo:Ljava/lang/String;

    const/16 v6, 0x42

    invoke-static {p1, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->getCursorString(Landroid/database/Cursor;I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mThumbnailInfo:Ljava/lang/String;

    iget-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mAttributes:J

    const/16 v3, 0x44

    invoke-interface {p1, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v8

    invoke-virtual {v2, v6, v7, v8, v9}, Lcom/miui/gallery/util/UpdateHelper;->update(JJ)J

    move-result-wide v6

    iput-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mAttributes:J

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mAddress:Ljava/lang/String;

    const/16 v6, 0x46

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mAddress:Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mExtraGps:Ljava/lang/String;

    const/16 v6, 0x45

    invoke-interface {p1, v6}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v2, v3, v6}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    iput-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mExtraGps:Ljava/lang/String;

    iget-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mSpecialTypeFlags:J

    const/16 v3, 0x47

    invoke-interface {p1, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v8

    invoke-virtual {v2, v6, v7, v8, v9}, Lcom/miui/gallery/util/UpdateHelper;->update(JJ)J

    move-result-wide v6

    iput-wide v6, p0, Lcom/miui/gallery/data/DBCloud;->mSpecialTypeFlags:J

    :try_start_0
    iget-object p1, p0, Lcom/miui/gallery/data/DBCloud;->mAddress:Ljava/lang/String;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/data/DBCloud;->mExtraGps:Ljava/lang/String;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mExtraGps:Ljava/lang/String;

    if-eqz p1, :cond_3

    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mLatitudeStr:Ljava/lang/String;

    iget-object v6, p0, Lcom/miui/gallery/data/DBCloud;->mLongitudeStr:Ljava/lang/String;

    iget-object v7, p0, Lcom/miui/gallery/data/DBCloud;->mLatitudeStrRef:Ljava/lang/String;

    iget-object v8, p0, Lcom/miui/gallery/data/DBCloud;->mLongitudeStrRef:Ljava/lang/String;

    invoke-static {v3, v6, v7, v8}, Lcom/miui/gallery/data/LocationManager;->formatExifGpsString(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    :cond_3
    iget-object v6, p0, Lcom/miui/gallery/data/DBCloud;->mGeoInfo:Lorg/json/JSONObject;

    const-string v7, "isAccurate"

    invoke-virtual {v6, v7, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    iget-object p1, p0, Lcom/miui/gallery/data/DBCloud;->mGeoInfo:Lorg/json/JSONObject;

    const-string v6, "gps"

    invoke-virtual {p1, v6, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_2

    const/4 p1, 0x0

    :try_start_1
    new-instance v3, Lorg/json/JSONArray;

    iget-object v6, p0, Lcom/miui/gallery/data/DBCloud;->mAddress:Ljava/lang/String;

    invoke-direct {v3, v6}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    iget-object v6, p0, Lcom/miui/gallery/data/DBCloud;->mGeoInfo:Lorg/json/JSONObject;

    const-string v7, "addressList"

    invoke-virtual {v6, v7, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-lez v6, :cond_4

    invoke-virtual {v3, v4}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object p1
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_2

    goto :goto_2

    :catch_0
    :try_start_2
    iget-object p1, p0, Lcom/miui/gallery/data/DBCloud;->mAddress:Ljava/lang/String;

    :cond_4
    :goto_2
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3
    :try_end_2
    .catch Lorg/json/JSONException; {:try_start_2 .. :try_end_2} :catch_2

    if-nez v3, :cond_5

    :try_start_3
    iget-object v3, p0, Lcom/miui/gallery/data/DBCloud;->mGeoInfo:Lorg/json/JSONObject;

    const-string v6, "address"

    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v6, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_1
    .catch Lorg/json/JSONException; {:try_start_3 .. :try_end_3} :catch_2

    goto :goto_3

    :catch_1
    move-exception p1

    :try_start_4
    const-string v3, "Failed to convert Address info, %s"

    invoke-static {v0, v3, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_4
    .catch Lorg/json/JSONException; {:try_start_4 .. :try_end_4} :catch_2

    goto :goto_3

    :catch_2
    const-string p1, "DBCloud(Cursor c): put JSON error"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    :cond_5
    :goto_3
    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getSha1()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_7

    const-string p1, "isFavorite"

    filled-new-array {p1}, [Ljava/lang/String;

    move-result-object v7

    new-array v9, v5, [Ljava/lang/String;

    invoke-virtual {p0}, Lcom/miui/gallery/data/DBCloud;->getSha1()Ljava/lang/String;

    move-result-object p1

    aput-object p1, v9, v4

    const/4 v10, 0x0

    new-instance v11, Lcom/miui/gallery/data/DBCloud$1;

    invoke-direct {v11, p0}, Lcom/miui/gallery/data/DBCloud$1;-><init>(Lcom/miui/gallery/data/DBCloud;)V

    const-string v6, "favorites"

    const-string v8, "sha1 = ?"

    invoke-static/range {v6 .. v11}, Lcom/miui/gallery/util/GalleryUtils;->safeQuery(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/GalleryUtils$QueryHandler;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    iget-boolean v0, p0, Lcom/miui/gallery/data/DBCloud;->mIsFavorite:Z

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    if-eqz p1, :cond_6

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_6

    move p1, v5

    goto :goto_4

    :cond_6
    move p1, v4

    :goto_4
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-virtual {v2, v0, p1}, Lcom/miui/gallery/util/UpdateHelper;->update(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    iput-boolean p1, p0, Lcom/miui/gallery/data/DBCloud;->mIsFavorite:Z

    :cond_7
    if-nez v1, :cond_8

    invoke-virtual {v2}, Lcom/miui/gallery/util/UpdateHelper;->isUpdated()Z

    move-result p1

    if-eqz p1, :cond_9

    :cond_8
    move v4, v5

    :cond_9
    return v4
.end method

.method public setOriginInfo(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/data/DBCloud;->mOriginSha1:Ljava/lang/String;

    iput-object p2, p0, Lcom/miui/gallery/data/DBCloud;->mOriginFileName:Ljava/lang/String;

    return-void
.end method

.method public setRequestAlbumId(Ljava/lang/String;)V
    .locals 2

    invoke-static {p1}, Ljava/lang/Long;->valueOf(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/data/DBCloud;->setGroupId(J)V

    return-void
.end method

.method public toJSONObject()Lorg/json/JSONObject;
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/data/DBImage;->toJSONObject()Lorg/json/JSONObject;

    move-result-object v0

    if-nez v0, :cond_0

    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    :cond_0
    return-object v0
.end method
