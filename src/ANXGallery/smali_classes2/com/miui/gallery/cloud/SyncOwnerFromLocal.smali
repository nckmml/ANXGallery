.class public Lcom/miui/gallery/cloud/SyncOwnerFromLocal;
.super Lcom/miui/gallery/cloud/SyncFromLocalBase;
.source "SyncOwnerFromLocal.java"


# instance fields
.field private mAutoCreateImageItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mAutoCreateVideoItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mCopyImageToOwnerItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mCreateGroupItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mDeleteGroupItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mDeleteImageItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mEditCloudThumbnailInfoItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mEditFavoriteInfoItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mEditGeoInfoItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mEditGroupDescriptionItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mEditNameItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mEditedItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mLargestElapsedTime:J

.field private mManualCreateOwnerImageItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mManualCreateOwnerVideoItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mMoveImageToOwnerItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mNoDelay:Z

.field private mPhotosToBeSynced:I

.field private mRenameGroupItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/cloud/RequestCloudItem;",
            ">;"
        }
    .end annotation
.end field

.field private mVideosToBeSynced:I


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Z)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/gallery/cloud/SyncFromLocalBase;-><init>(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;)V

    iput-boolean p4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    return-void
.end method

.method private shouldSyncEditItemName(Lcom/miui/gallery/data/DBImage;)Z
    .locals 2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    if-eqz v0, :cond_1

    const/4 v1, 0x5

    if-eq v0, v1, :cond_1

    const/4 v1, 0x6

    if-eq v0, v1, :cond_1

    const/16 v1, 0x9

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    return p1

    :cond_1
    :goto_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x7

    invoke-static {v0}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    return p1
.end method

.method private shouldSyncEditedDescription(Lcom/miui/gallery/data/DBImage;)Z
    .locals 2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    if-eqz v0, :cond_1

    const/16 v1, 0xa

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    return p1

    :cond_1
    :goto_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x6

    invoke-static {v0}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    return p1
.end method

.method private shouldSyncEditedGeoInfo(Lcom/miui/gallery/data/DBImage;)Z
    .locals 2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    if-eqz v0, :cond_1

    const/4 v1, 0x5

    if-eq v0, v1, :cond_1

    const/4 v1, 0x6

    if-eq v0, v1, :cond_1

    const/16 v1, 0x9

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    return p1

    :cond_1
    :goto_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object p1

    const/16 v0, 0x46

    invoke-static {v0}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    return p1
.end method

.method private shouldSyncEditedItem(Lcom/miui/gallery/data/DBImage;)Z
    .locals 3

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    const/4 v2, 0x5

    if-eq v0, v2, :cond_1

    const/4 v2, 0x6

    if-eq v0, v2, :cond_1

    const/16 v2, 0x9

    if-ne v0, v2, :cond_0

    goto :goto_0

    :cond_0
    return v1

    :cond_1
    :goto_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->isUbiFocus()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->isShareItem()Z

    move-result p1

    invoke-static {p1}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->getFocusIndexColumnElement(Z)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_2

    const/4 v1, 0x1

    :cond_2
    return v1
.end method

.method private shouldSyncEditedThumbnailInfo(Lcom/miui/gallery/data/DBImage;)Z
    .locals 7

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    instance-of v1, p1, Lcom/miui/gallery/data/DBCloud;

    const/4 v2, 0x0

    if-eqz v1, :cond_3

    const/16 v1, 0x8

    if-eqz v0, :cond_0

    const/16 v3, 0xa

    if-eq v0, v3, :cond_0

    if-ne v0, v1, :cond_3

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object v0

    const/16 v3, 0x42

    invoke-static {v3}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_3

    check-cast p1, Lcom/miui/gallery/data/DBCloud;

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBCloud;->getThumbnailInfo()Lcom/miui/gallery/cloud/ThumbnailInfo;

    move-result-object p1

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Lcom/miui/gallery/cloud/ThumbnailInfo;->getBgImageLocalId()J

    move-result-wide v3

    const-wide/16 v5, -0x1

    cmp-long p1, v3, v5

    const/4 v0, 0x1

    if-nez p1, :cond_1

    return v0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->getBaseUri()Landroid/net/Uri;

    move-result-object p1

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-static {v3, v4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v3

    const-string v4, "_id"

    invoke-static {p1, v5, v4, v3}, Lcom/miui/gallery/cloud/CloudUtils;->getItem(Landroid/net/Uri;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/data/DBImage;

    move-result-object p1

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result p1

    if-eq v1, p1, :cond_2

    goto :goto_0

    :cond_2
    move v0, v2

    :goto_0
    return v0

    :cond_3
    return v2
.end method

.method private shouldSyncFavoriteInfo(Lcom/miui/gallery/data/DBImage;)Z
    .locals 2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    if-eqz v0, :cond_1

    const/4 v1, 0x5

    if-eq v0, v1, :cond_1

    const/4 v1, 0x6

    if-eq v0, v1, :cond_1

    const/16 v1, 0x9

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    return p1

    :cond_1
    :goto_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object p1

    const/4 v0, -0x1

    invoke-static {v0}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    return p1
.end method


# virtual methods
.method protected generateDBImage(Landroid/database/Cursor;)Lcom/miui/gallery/data/DBImage;
    .locals 1

    new-instance v0, Lcom/miui/gallery/data/DBCloud;

    invoke-direct {v0, p1}, Lcom/miui/gallery/data/DBCloud;-><init>(Landroid/database/Cursor;)V

    return-object v0
.end method

.method protected bridge synthetic generateDBImage(Landroid/database/Cursor;)Lcom/miui/gallery/data/DBItem;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->generateDBImage(Landroid/database/Cursor;)Lcom/miui/gallery/data/DBImage;

    move-result-object p1

    return-object p1
.end method

.method protected getBaseUri()Landroid/net/Uri;
    .locals 1

    sget-object v0, Lcom/miui/gallery/cloud/GalleryCloudUtils;->CLOUD_URI:Landroid/net/Uri;

    return-object v0
.end method

.method protected getSelectionClause()Ljava/lang/String;
    .locals 7

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x2

    new-array v2, v1, [Ljava/lang/Object;

    invoke-static {}, Lcom/miui/gallery/cloud/CloudUtils;->getMaxImageSizeLimit()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    const/4 v4, 0x0

    aput-object v3, v2, v4

    invoke-static {}, Lcom/miui/gallery/cloud/CloudUtils;->getMaxVideoSizeLimit()J

    move-result-wide v5

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    const/4 v5, 0x1

    aput-object v3, v2, v5

    const-string v3, "((serverType = 1 AND size < %s) OR (serverType = 2 AND size < %s))"

    invoke-static {v0, v3, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    sget-object v2, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v1, v1, [Ljava/lang/Object;

    const-string v3, "(serverType=0)"

    aput-object v3, v1, v4

    aput-object v0, v1, v5

    const-string v0, "(%s OR %s)"

    invoke-static {v2, v0, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    sget-object v1, Lcom/miui/gallery/cloud/CloudUtils;->SELECTION_OWNER_NEED_SYNC:Ljava/lang/String;

    invoke-static {v1, v0}, Landroid/database/DatabaseUtils;->concatenateWhere(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {}, Lcom/miui/gallery/provider/ProcessingMediaManager;->queryProcessingMediaPaths()Ljava/util/List;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v2

    if-eqz v2, :cond_2

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "localFile NOT IN ("

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    :goto_0
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v3

    if-ge v4, v3, :cond_1

    invoke-interface {v1, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/CharSequence;

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_0

    invoke-interface {v1, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-static {v2, v3}, Landroid/database/DatabaseUtils;->appendEscapedSQLString(Ljava/lang/StringBuilder;Ljava/lang/String;)V

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v3

    sub-int/2addr v3, v5

    if-eq v4, v3, :cond_0

    const-string v3, ", "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_0
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_1
    const-string v1, ")"

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/database/DatabaseUtils;->concatenateWhere(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_2
    new-array v1, v5, [Ljava/lang/Object;

    aput-object v0, v1, v4

    const-string v0, " (%s) "

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected handleRequestCloudItemList()V
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCreateGroupItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const-string v1, "SyncOwnerFromLocal"

    if-lez v0, :cond_0

    const-string v0, "start create group items"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/CreateGroupItem;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/CreateGroupItem;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCreateGroupItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerVideoItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_1

    const-string v0, "start upload manual create videos"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerVideoItems:Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/miui/gallery/cloud/UpDownloadManager;->dispatchList(Ljava/util/List;)I

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateVideoItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_2

    const-string v0, "start upload auto create videos"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateVideoItems:Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/miui/gallery/cloud/UpDownloadManager;->dispatchList(Ljava/util/List;)I

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerImageItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_3

    const-string v0, "start upload manual create images"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerImageItems:Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/miui/gallery/cloud/UpDownloadManager;->dispatchList(Ljava/util/List;)I

    :cond_3
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateImageItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_4

    const-string v0, "start upload auto create images"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateImageItems:Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/miui/gallery/cloud/UpDownloadManager;->dispatchList(Ljava/util/List;)I

    :cond_4
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCopyImageToOwnerItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_5

    const-string v0, "start copy image items"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/CopyImageToOwnerAlbum;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/CopyImageToOwnerAlbum;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCopyImageToOwnerItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_5

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_5

    return-void

    :cond_5
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mMoveImageToOwnerItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_6

    const-string v0, "start move image items"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/MoveItemToOwnerAlbum;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/MoveItemToOwnerAlbum;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mMoveImageToOwnerItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_6

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_6

    return-void

    :cond_6
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteImageItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_7

    const-string v0, "start delete image items"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/DeleteCloudItem;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/DeleteCloudItem;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteImageItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_7

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_7

    return-void

    :cond_7
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteGroupItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_8

    const-string v0, "start delete group items"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/DeleteCloudItem;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/DeleteCloudItem;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteGroupItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_8

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_8

    return-void

    :cond_8
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mRenameGroupItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_9

    const-string v0, "start rename group items"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/RenameCloudItem;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/RenameCloudItem;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mRenameGroupItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_9

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_9

    return-void

    :cond_9
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditedItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_a

    const-string v0, "start edit items"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/EditCloudItem;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/EditCloudItem;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditedItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_a

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_a

    return-void

    :cond_a
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditCloudThumbnailInfoItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_b

    const-string v0, "start album thumbnail info"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/EditCloudThumbnailInfo;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/EditCloudThumbnailInfo;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditCloudThumbnailInfoItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_b

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_b

    return-void

    :cond_b
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGroupDescriptionItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_c

    const-string v0, "start edit album description"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/EditGroupDescription;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/EditGroupDescription;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGroupDescriptionItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_c

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_c

    return-void

    :cond_c
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGeoInfoItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_d

    const-string v0, "start edit geo info"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/EditCloudGeoInfo;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/EditCloudGeoInfo;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGeoInfoItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_d

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_d

    return-void

    :cond_d
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditFavoriteInfoItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_e

    const-string v0, "start edit favorite info"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/EditCloudFavoriteInfo;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v2}, Lcom/miui/gallery/cloud/EditCloudFavoriteInfo;-><init>(Landroid/content/Context;)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v5, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditFavoriteInfoItems:Ljava/util/ArrayList;

    invoke-static {v2, v3, v4, v5, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_e

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v2, :cond_e

    return-void

    :cond_e
    iget-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditNameItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_f

    const-string v0, "start edit item name info"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/SyncLog;->v(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/cloud/EditCloudItemName;

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/miui/gallery/cloud/EditCloudItemName;-><init>(Landroid/content/Context;)V

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mContext:Landroid/content/Context;

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAccount:Landroid/accounts/Account;

    iget-object v3, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mExtendedAuthToken:Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;

    iget-object v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditNameItems:Ljava/util/ArrayList;

    invoke-static {v1, v2, v3, v4, v0}, Lcom/miui/gallery/cloud/RetryOperation;->doOperation(Landroid/content/Context;Landroid/accounts/Account;Lcom/miui/gallery/cloud/base/GalleryExtendedAuthToken;Ljava/util/List;Lcom/miui/gallery/cloud/Operation;)Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    if-eqz v0, :cond_f

    iget-object v0, v0, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v1, Lcom/miui/gallery/cloud/base/GallerySyncCode;->CONDITION_INTERRUPTED:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    if-ne v0, v1, :cond_f

    :cond_f
    return-void
.end method

.method protected initRequestCloudItemList()V
    .locals 1

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCreateGroupItems:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerImageItems:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateImageItems:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCopyImageToOwnerItems:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mMoveImageToOwnerItems:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteImageItems:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteGroupItems:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mRenameGroupItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateVideoItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerVideoItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditedItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditCloudThumbnailInfoItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGroupDescriptionItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGeoInfoItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditFavoriteInfoItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/google/common/collect/Lists;->newArrayList()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditNameItems:Ljava/util/ArrayList;

    return-void
.end method

.method protected putToRequestCloudItemList(Lcom/miui/gallery/data/DBItem;)V
    .locals 7

    check-cast p1, Lcom/miui/gallery/data/DBImage;

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_5

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->isItemType()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->shouldSyncEditedItem(Lcom/miui/gallery/data/DBImage;)Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditedItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->shouldSyncEditedGeoInfo(Lcom/miui/gallery/data/DBImage;)Z

    move-result v0

    if-eqz v0, :cond_1

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGeoInfoItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->shouldSyncFavoriteInfo(Lcom/miui/gallery/data/DBImage;)Z

    move-result v0

    if-eqz v0, :cond_2

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditFavoriteInfoItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->shouldSyncEditItemName(Lcom/miui/gallery/data/DBImage;)Z

    move-result v0

    if-eqz v0, :cond_5

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditNameItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_3
    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->shouldSyncEditedThumbnailInfo(Lcom/miui/gallery/data/DBImage;)Z

    move-result v0

    if-eqz v0, :cond_4

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditCloudThumbnailInfoItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_4
    invoke-direct {p0, p1}, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->shouldSyncEditedDescription(Lcom/miui/gallery/data/DBImage;)Z

    move-result v0

    if-eqz v0, :cond_5

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mEditGroupDescriptionItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_5
    :goto_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    const/4 v2, 0x4

    const-string v3, "unsupport local flag= %d serverType= %d"

    const-string v4, "SyncOwnerFromLocal"

    const/4 v5, 0x2

    const/4 v6, 0x1

    packed-switch v0, :pswitch_data_0

    :pswitch_0
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getEditedColumns()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_10

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string v0, "unsupport local flag: %d"

    invoke-static {v4, v0, p1}, Lcom/miui/gallery/util/SyncLog;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto/16 :goto_1

    :pswitch_1
    const-string p1, "don\'t handle move from flag."

    invoke-static {v4, p1}, Lcom/miui/gallery/util/SyncLog;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto/16 :goto_1

    :pswitch_2
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->isItemType()Z

    move-result v0

    if-nez v0, :cond_10

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getServerId()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_6

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCreateGroupItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object p1

    const-string v0, "Sync"

    const-string v1, "sync_album_renamed_before_sync"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    goto/16 :goto_1

    :cond_6
    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mRenameGroupItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :pswitch_3
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getServerType()I

    move-result v0

    if-eqz v0, :cond_9

    if-eq v0, v6, :cond_8

    if-eq v0, v5, :cond_7

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "unsupport local flag="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", serverType="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getServerType()I

    move-result p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v4, p1}, Lcom/miui/gallery/util/SyncLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    goto/16 :goto_1

    :cond_7
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    const/4 v1, 0x3

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerVideoItems:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mVideosToBeSynced:I

    add-int/2addr v0, v6

    iput v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mVideosToBeSynced:I

    iget-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getDateModified()J

    move-result-wide v4

    sub-long/2addr v2, v4

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    goto/16 :goto_1

    :cond_8
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    const/4 v1, 0x5

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mManualCreateOwnerImageItems:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mPhotosToBeSynced:I

    add-int/2addr v0, v6

    iput v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mPhotosToBeSynced:I

    iget-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getDateModified()J

    move-result-wide v4

    sub-long/2addr v2, v4

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    goto/16 :goto_1

    :cond_9
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCreateGroupItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :pswitch_4
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getServerType()I

    move-result v0

    if-eqz v0, :cond_c

    if-eq v0, v6, :cond_b

    if-eq v0, v5, :cond_a

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getServerType()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {v4, v3, v0, p1}, Lcom/miui/gallery/util/SyncLog;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto/16 :goto_1

    :cond_a
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v5, p1, v1}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateVideoItems:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mVideosToBeSynced:I

    add-int/2addr v0, v6

    iput v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mVideosToBeSynced:I

    iget-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getDateModified()J

    move-result-wide v4

    sub-long/2addr v2, v4

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    goto/16 :goto_1

    :cond_b
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v2, p1, v1}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateImageItems:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mPhotosToBeSynced:I

    add-int/2addr v0, v6

    iput v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mPhotosToBeSynced:I

    iget-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getDateModified()J

    move-result-wide v4

    sub-long/2addr v2, v4

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    goto/16 :goto_1

    :cond_c
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCreateGroupItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    :pswitch_5
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->isItemType()Z

    move-result v0

    if-eqz v0, :cond_10

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mCopyImageToOwnerItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :pswitch_6
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->isItemType()Z

    move-result v0

    if-eqz v0, :cond_10

    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mMoveImageToOwnerItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :pswitch_7
    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getServerType()I

    move-result v0

    if-eq v0, v6, :cond_e

    if-eq v0, v5, :cond_d

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getLocalFlag()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->getServerType()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {v4, v3, v0, p1}, Lcom/miui/gallery/util/SyncLog;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_1

    :cond_d
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v5, p1, v1}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateVideoItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_e
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v2, p1, v1}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mAutoCreateImageItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :pswitch_8
    new-instance v0, Lcom/miui/gallery/cloud/RequestCloudItem;

    iget-boolean v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mNoDelay:Z

    invoke-direct {v0, v1, p1, v2}, Lcom/miui/gallery/cloud/RequestCloudItem;-><init>(ILcom/miui/gallery/data/DBImage;Z)V

    iget-object p1, v0, Lcom/miui/gallery/cloud/RequestCloudItem;->dbCloud:Lcom/miui/gallery/data/DBImage;

    invoke-virtual {p1}, Lcom/miui/gallery/data/DBImage;->isItemType()Z

    move-result p1

    if-eqz p1, :cond_f

    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteImageItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_f
    iget-object p1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mDeleteGroupItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_10
    :goto_1
    return-void

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_8
        :pswitch_0
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_5
        :pswitch_2
        :pswitch_1
    .end packed-switch
.end method

.method public sync()V
    .locals 9
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    const-string v0, "owner_largest_elapsed_time"

    const-string v1, "owner_videos_to_be_synced"

    const-string v2, "owner_photos_to_be_synced"

    const-string v3, "items_to_be_synced"

    const/4 v4, 0x0

    iput v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mPhotosToBeSynced:I

    iput v4, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mVideosToBeSynced:I

    const-wide/16 v4, 0x0

    :try_start_0
    invoke-super {p0}, Lcom/miui/gallery/cloud/SyncFromLocalBase;->sync()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget v6, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mPhotosToBeSynced:I

    if-lez v6, :cond_0

    int-to-long v6, v6

    invoke-static {v3, v2, v6, v7}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordNumericPropertyEvent(Ljava/lang/String;Ljava/lang/String;J)V

    :cond_0
    iget v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mVideosToBeSynced:I

    if-lez v2, :cond_1

    int-to-long v6, v2

    invoke-static {v3, v1, v6, v7}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordNumericPropertyEvent(Ljava/lang/String;Ljava/lang/String;J)V

    :cond_1
    iget-wide v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    cmp-long v4, v1, v4

    if-lez v4, :cond_2

    invoke-static {v3, v0, v1, v2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordNumericPropertyEvent(Ljava/lang/String;Ljava/lang/String;J)V

    :cond_2
    return-void

    :catchall_0
    move-exception v6

    iget v7, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mPhotosToBeSynced:I

    if-lez v7, :cond_3

    int-to-long v7, v7

    invoke-static {v3, v2, v7, v8}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordNumericPropertyEvent(Ljava/lang/String;Ljava/lang/String;J)V

    :cond_3
    iget v2, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mVideosToBeSynced:I

    if-lez v2, :cond_4

    int-to-long v7, v2

    invoke-static {v3, v1, v7, v8}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordNumericPropertyEvent(Ljava/lang/String;Ljava/lang/String;J)V

    :cond_4
    iget-wide v1, p0, Lcom/miui/gallery/cloud/SyncOwnerFromLocal;->mLargestElapsedTime:J

    cmp-long v4, v1, v4

    if-lez v4, :cond_5

    invoke-static {v3, v0, v1, v2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordNumericPropertyEvent(Ljava/lang/String;Ljava/lang/String;J)V

    :cond_5
    throw v6
.end method
