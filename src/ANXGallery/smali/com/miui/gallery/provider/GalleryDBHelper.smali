.class public Lcom/miui/gallery/provider/GalleryDBHelper;
.super Landroid/database/sqlite/SQLiteOpenHelper;
.source "GalleryDBHelper.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/provider/GalleryDBHelper$UpdateLocalGroupIdQueryHandler;,
        Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;
    }
.end annotation


# static fields
.field private static final sCloudControlTables:[Ljava/lang/String;

.field private static final sCloudTables:[Ljava/lang/String;

.field private static sDBHelper:Lcom/miui/gallery/provider/GalleryDBHelper;

.field private static final sPeopleFaceTables:[Ljava/lang/String;

.field private static sViewNameVersionMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private mAlbumColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mAlbumCoverKeyColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mCloudCacheColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mCloudColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mCloudControlColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mCloudSettingColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mCloudUserColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field protected mContext:Landroid/content/Context;

.field private mDiscoveryMessageColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mEventColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mEventPhotoColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mFace2ImagesColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mFavoritesColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mLocalUbifocusColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mNewFlagCacheColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mOldVersion:I

.field private mOwnerSubUbiImageColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mPeopleFaceColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mPeopleRecommendColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mPhotoGpsCacheColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mShareAlbumColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mShareImageColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mShareSubUbiImageColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mShareUserColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mUserInfoColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field

.field private mWhiteListLastModifyColumns:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 22

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sViewNameVersionMap:Ljava/util/HashMap;

    sget-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sViewNameVersionMap:Ljava/util/HashMap;

    const/4 v1, 0x3

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "extended_cloud"

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sViewNameVersionMap:Ljava/util/HashMap;

    const/4 v1, 0x1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "extended_faceImage"

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v3, "cloud"

    const-string v4, "shareAlbum"

    const-string v5, "shareUser"

    const-string v6, "shareImage"

    const-string v7, "cloudUser"

    const-string v8, "cloudCache"

    const-string v9, "newFlagCache"

    const-string v10, "userInfo"

    const-string v11, "ownerSubUbifocus"

    const-string v12, "peopleFace"

    const-string v13, "faceToImages"

    const-string v14, "peopleRecommend"

    const-string v15, "shareSubUbifocus"

    const-string v16, "event"

    const-string v17, "eventPhoto"

    const-string v18, "albumCoverKey"

    const-string v19, "recent_discovered_media"

    const-string v20, "discovery_message"

    const-string v21, "cloudSetting"

    filled-new-array/range {v3 .. v21}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sCloudTables:[Ljava/lang/String;

    const-string v0, "cloudControl"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sCloudControlTables:[Ljava/lang/String;

    const-string v0, "peopleFace"

    const-string v1, "faceToImages"

    const-string v2, "peopleRecommend"

    filled-new-array {v0, v1, v2}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sPeopleFaceTables:[Ljava/lang/String;

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 3

    const-string v0, "gallery.db"

    const/4 v1, 0x0

    const/16 v2, 0x5c

    invoke-direct {p0, p1, v0, v1, v2}, Landroid/database/sqlite/SQLiteOpenHelper;-><init>(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mWhiteListLastModifyColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mLocalUbifocusColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    iput-object p1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/provider/GalleryDBHelper;Landroid/database/sqlite/SQLiteDatabase;ILjava/lang/String;ZZ)V
    .locals 0

    invoke-direct/range {p0 .. p5}, Lcom/miui/gallery/provider/GalleryDBHelper;->refillLocalFileAndAttributes(Landroid/database/sqlite/SQLiteDatabase;ILjava/lang/String;ZZ)V

    return-void
.end method

.method static synthetic access$100(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z
    .locals 0

    invoke-static {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method private static addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V
    .locals 5

    iget-object v0, p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mDefault:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x3

    const/4 v2, 0x2

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-eqz v0, :cond_0

    new-array v0, v1, [Ljava/lang/Object;

    aput-object p1, v0, v4

    iget-object p1, p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mName:Ljava/lang/String;

    aput-object p1, v0, v3

    iget-object p1, p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mColumnType:Ljava/lang/String;

    aput-object p1, v0, v2

    const-string p1, "ALTER TABLE %s ADD COLUMN %s %s"

    invoke-static {p1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 v0, 0x4

    new-array v0, v0, [Ljava/lang/Object;

    aput-object p1, v0, v4

    iget-object p1, p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mName:Ljava/lang/String;

    aput-object p1, v0, v3

    iget-object p1, p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mColumnType:Ljava/lang/String;

    aput-object p1, v0, v2

    iget-object p1, p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mDefault:Ljava/lang/String;

    aput-object p1, v0, v1

    const-string p1, "ALTER TABLE %s ADD COLUMN %s %s DEFAULT %s"

    invoke-static {p1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    :goto_0
    :try_start_0
    invoke-virtual {p0, p1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p2

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v0

    const-string v1, "sql"

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->getVersion()I

    move-result p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const-string p1, "dbversion"

    invoke-interface {v0, p1, p0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " : "

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/Exception;->getCause()Ljava/lang/Throwable;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const-string p1, "exception"

    invoke-interface {v0, p1, p0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "db_helper"

    const-string p1, "db_add_columns"

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    new-instance p0, Landroid/database/SQLException;

    invoke-virtual {p2}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1, p2}, Landroid/database/SQLException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw p0
.end method

.method private addRecordsForCameraAndScreenshots(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    new-instance v0, Lcom/miui/gallery/provider/GalleryDBHelper$2;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper$2;-><init>(Lcom/miui/gallery/provider/GalleryDBHelper;Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-static {v0}, Lcom/miui/gallery/cloud/CloudUtils;->addRecordsForCameraAndScreenshots(Lcom/miui/gallery/cloud/CloudUtils$Insertable;)V

    return-void
.end method

.method private addUniqueIndexs(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo35(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private cleanCloudData(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 5

    sget-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sCloudTables:[Ljava/lang/String;

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    aget-object v3, v0, v2

    const/4 v4, 0x0

    :try_start_0
    invoke-virtual {p1, v3, v4, v4}, Landroid/database/sqlite/SQLiteDatabase;->delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception v3

    const-string v4, "GalleryDBHelper"

    invoke-static {v4, v3}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method private clearColumnList()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mWhiteListLastModifyColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mWhiteListLastModifyColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mLocalUbifocusColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mLocalUbifocusColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V

    iput-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    return-void
.end method

.method private createIndexOnCloudCacheTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 3

    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    const-string v2, "cloudCache"

    aput-object v2, v0, v1

    const/4 v1, 0x1

    const-string v2, "serverId"

    aput-object v2, v0, v1

    const-string v1, "create index index_cloudCache_serverId on %s (%s)"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_19(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_20(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_21(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_43(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_51(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_53(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_70(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_75(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private createIndexOnCloudTable_19(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index index_fileName on cloud (fileName)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_sort on cloud (dateModified DESC ,_id DESC)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudTable_20(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    return-void
.end method

.method private createIndexOnCloudTable_21(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index index_localFlag on cloud (localFlag)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_fileName_NOCASE on cloud (fileName COLLATE NOCASE)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_cloud_albumId on cloud (albumId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudTable_43(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_mixed_datetime on cloud (mixedDateTime)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudTable_51(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "drop index index_mixed_datetime"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index if not exists index_cloud_mixed_exif_datetime on cloud (mixedDateTime, exifDateTime)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudTable_53(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_cloud_size on cloud (size)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudTable_70(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_microthumbnail on cloud (groupId DESC, dateModified DESC)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudTable_75(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_cloud_sha1 on cloud (sha1)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnCloudUserTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudUserTable_22(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private createIndexOnCloudUserTable_22(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index index_cloudUser_albumId on cloudUser (albumId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_cloudUser_userId on cloudUser (userId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_cloudUser_localAlbumId on cloudUser (localAlbumId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnFaceTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_serverId_peopleFace on peopleFace (serverId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index if not exists index_groupId_peopleFace on peopleFace (groupId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index if not exists index_faceId_faceToImages on faceToImages (faceId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index if not exists index_imageServerId_faceToImages on faceToImages (imageServerId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnNewFlagCacheTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 3

    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    const-string v2, "newFlagCache"

    aput-object v2, v0, v1

    const/4 v1, 0x1

    const-string v2, "albumId"

    aput-object v2, v0, v1

    const-string v1, "create index index_newFlagCache_albumId on %s (%s)"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo32(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private createIndexOnPeopleRecommendTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_peopleServerID_peopleRecommend on peopleRecommend (peopleServerId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareAlbumTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareAlbumTable_21(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private createIndexOnShareAlbumTable_21(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index index_shareAlbum_albumId on shareAlbum (albumId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_shareAlbum_creatorId on shareAlbum (creatorId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareImageTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_21(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_43(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_51(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_53(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_70(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private createIndexOnShareImageTable_21(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index index_shareImage_albumId on shareImage (albumId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_shareImage_creatorId on shareImage (creatorId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareImageTable_43(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_mixed_datetime on shareImage (mixedDateTime)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareImageTable_51(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "drop index index_mixed_datetime"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index if not exists index_shareimage_mixed_exif_datetime on shareimage (mixedDateTime, exifDateTime)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareImageTable_53(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_shareimage_size on shareimage (size)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareImageTable_70(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index if not exists index_shareimage_microthumbnail on shareImage (groupId DESC, dateModified DESC)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareUserTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareUserTable_21(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareUserTable_22(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private createIndexOnShareUserTable_21(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index index_shareUser_albumId on shareUser (albumId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "create index index_shareUser_userId on shareUser (userId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnShareUserTable_22(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "create index index_shareUser_localAlbumId on shareUser (localAlbumId)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createIndexOnWhiteListLastModifyTable(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "CREATE UNIQUE INDEX IF NOT EXISTS index_path ON whiteListLastModify (path)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/database/sqlite/SQLiteDatabase;",
            "Ljava/lang/String;",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;)V"
        }
    .end annotation

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x2

    const/4 v5, 0x1

    if-ge v2, v3, :cond_5

    invoke-virtual {p3, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    iget-object v6, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mDefault:Ljava/lang/String;

    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_0

    new-array v4, v4, [Ljava/lang/Object;

    iget-object v6, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mName:Ljava/lang/String;

    aput-object v6, v4, v1

    iget-object v6, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mColumnType:Ljava/lang/String;

    aput-object v6, v4, v5

    const-string v6, " %s %s"

    invoke-static {v6, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    :cond_0
    const/4 v6, 0x3

    new-array v6, v6, [Ljava/lang/Object;

    iget-object v7, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mName:Ljava/lang/String;

    aput-object v7, v6, v1

    iget-object v7, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mColumnType:Ljava/lang/String;

    aput-object v7, v6, v5

    iget-object v7, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mDefault:Ljava/lang/String;

    aput-object v7, v6, v4

    const-string v4, " %s %s DEFAULT %s"

    invoke-static {v4, v6}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_1
    iget-boolean v4, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->isUnique:Z

    if-eqz v4, :cond_1

    const-string v4, " UNIQUE"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    if-nez v2, :cond_2

    const-string v4, " PRIMARY KEY "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    iget-object v3, v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mName:Ljava/lang/String;

    const-string v4, "_id"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    const-string v3, " AUTOINCREMENT"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result v3

    sub-int/2addr v3, v5

    if-ge v2, v3, :cond_4

    const-string v3, " , "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_4
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_5
    new-array p3, v4, [Ljava/lang/Object;

    aput-object p2, p3, v1

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    aput-object v0, p3, v5

    const-string v0, "CREATE TABLE IF NOT EXISTS %s (%s)"

    invoke-static {v0, p3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p1, p3}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    const-string p3, "cloud"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_6

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto/16 :goto_2

    :cond_6
    const-string p3, "shareAlbum"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_7

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareAlbumTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_7
    const-string p3, "shareUser"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_8

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareUserTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_8
    const-string p3, "shareImage"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_9

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_9
    const-string p3, "cloudUser"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_a

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudUserTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_a
    const-string p3, "cloudCache"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_b

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudCacheTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_b
    const-string p3, "newFlagCache"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_c

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnNewFlagCacheTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_c
    const-string p3, "whiteListLastModify"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p3

    if-eqz p3, :cond_d

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnWhiteListLastModifyTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_d
    const-string p3, "faceToImages"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p3

    if-eqz p3, :cond_e

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnFaceTable(Landroid/database/sqlite/SQLiteDatabase;)V

    goto :goto_2

    :cond_e
    const-string p3, "peopleRecommend"

    invoke-virtual {p2, p3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_f

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnPeopleRecommendTable(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_f
    :goto_2
    return-void
.end method

.method private static createViewByName(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V
    .locals 1

    const-string v0, "extended_cloud"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createViewExtendedCloud(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void

    :cond_0
    const-string v0, "extended_faceImage"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createViewExtendedFaceImage(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1
    return-void
.end method

.method private static createViewExtendedCloud(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 5

    const-string v0, "DROP VIEW IF EXISTS extended_cloud;"

    invoke-virtual {p0, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "SELECT "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "cloud._id AS _id,cloud.sha1 AS sha1,microthumbfile AS microthumbfile,thumbnailFile AS thumbnailFile,localFile AS localFile,serverType AS serverType,title AS title,duration AS duration,description AS description,location AS location,size AS size,localGroupId AS localGroupId,mimeType AS mimeType,exifGPSLatitude AS exifGPSLatitude,exifGPSLatitudeRef AS exifGPSLatitudeRef,exifGPSLongitude AS exifGPSLongitude,exifGPSLongitudeRef AS exifGPSLongitudeRef,exifOrientation AS exifOrientation,secretKey AS secretKey,localFlag AS localFlag,mixedDateTime AS mixedDateTime,dateTaken AS dateTaken,exifImageWidth AS exifImageWidth,exifImageLength AS exifImageLength,serverStatus AS serverStatus,dateModified AS dateModified,creatorId AS creatorId,serverTag AS serverTag,serverId AS serverId,fileName AS fileName,groupId AS groupId,ubiSubImageCount AS ubiSubImageCount,ubiSubIndex AS ubiSubIndex,ubiFocusIndex AS ubiFocusIndex,babyInfoJson AS babyInfoJson,isFavorite AS isFavorite,specialTypeFlags AS specialTypeFlags"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " FROM "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "(SELECT * FROM cloud ORDER BY mixedDateTime DESC,dateModified DESC,_id DESC ) cloud"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " LEFT OUTER JOIN "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "favorites"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " ON "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "cloud"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "."

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "sha1"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, " = "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "CREATE VIEW extended_cloud AS "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ";"

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    return-void
.end method

.method public static createViewExtendedFaceImage(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 10

    const-string v0, "DROP VIEW IF EXISTS extended_faceImage;"

    invoke-virtual {p0, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "SELECT "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "peopleFace._id AS _id,peopleFace.serverId AS serverId,peopleFace.groupId AS groupId,peopleFace.localGroupId AS localGroupId,microthumbfile AS microthumbfile,thumbnailFile AS thumbnailFile,dateTaken AS dateTaken,mixedDateTime AS mixedDateTime,mimeType AS mimeType,duration AS duration,location AS location,size AS size,exifImageWidth AS exifImageWidth,exifImageLength AS exifImageLength,exifOrientation AS exifOrientation,exifGPSLatitude AS exifGPSLatitude,exifGPSLatitudeRef AS exifGPSLatitudeRef,exifGPSLongitude AS exifGPSLongitude,exifGPSLongitudeRef AS exifGPSLongitudeRef,serverType AS serverType,localFile AS localFile,specialTypeFlags AS specialTypeFlags,fileName AS fileName,sha1 AS sha1,title AS title,dateModified AS dateModified,ubiSubImageCount AS ubiSubImageCount,ubiSubIndex AS ubiSubIndex,ubiFocusIndex AS ubiFocusIndex,secretKey AS secretKey,faceXScale AS faceXScale,faceYScale AS faceYScale,faceWScale AS faceWScale,faceHScale AS faceHScale,leftEyeXScale AS leftEyeXScale,leftEyeYScale AS leftEyeYScale,RightEyeXScale AS RightEyeXScale,RightEyeYScale AS RightEyeYScale,peopleFace.localFlag AS localFlag,peopleFace.serverStatus AS serverStatus,cloud._id AS photo_id,cloud.serverId AS photo_server_id,faceCoverScore AS faceCoverScore"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " FROM "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "peopleFace"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " JOIN "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "faceToImages"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "cloud"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " ON "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "."

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "serverId"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, " = "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "faceId"

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, " AND "

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "imageServerId"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " WHERE "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "type"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " = \'"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "FACE"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "\'"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, " AND ("

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "localFlag"

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, " not in ("

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v7, 0xb

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v7, ", "

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v8, 0x0

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v9, -0x1

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v7, 0x2

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v7, ")"

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, " OR ( "

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "serverStatus"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "custom"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "\'))"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "groupId"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " != "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-wide/16 v6, 0x3e8

    invoke-virtual {v0, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "localGroupId"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-wide/16 v3, -0x3e8

    invoke-virtual {v0, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, " ORDER BY "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "_id"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " desc "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "CREATE VIEW extended_faceImage AS "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ";"

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    return-void
.end method

.method private createViewIfNeeded(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 7

    sget-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sViewNameVersionMap:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-static {v2}, Lcom/miui/gallery/preference/GalleryPreferences$DataBase;->getViewVersion(Ljava/lang/String;)I

    move-result v2

    if-nez p2, :cond_1

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Integer;

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v3

    if-eq v2, v3, :cond_0

    :cond_1
    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    const-string v5, "GalleryDBHelper"

    const-string v6, "recreate view: [%s], oldVersion: [%d], newVersion: [%d]"

    invoke-static {v5, v6, v3, v2, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-static {p1, v2}, Lcom/miui/gallery/provider/GalleryDBHelper;->createViewByName(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-static {v2, v1}, Lcom/miui/gallery/preference/GalleryPreferences$DataBase;->setViewVersion(Ljava/lang/String;I)V

    goto :goto_0

    :cond_2
    sget-object p1, Lcom/miui/gallery/provider/GalleryDBHelper;->sViewNameVersionMap:Ljava/util/HashMap;

    invoke-virtual {p1}, Ljava/util/HashMap;->clear()V

    return-void
.end method

.method private deleteVideoThumbnailFile(Ljava/util/ArrayList;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/provider/GalleryDBHelper$6;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper$6;-><init>(Lcom/miui/gallery/provider/GalleryDBHelper;Ljava/util/ArrayList;)V

    invoke-virtual {v0}, Lcom/miui/gallery/provider/GalleryDBHelper$6;->run()V

    return-void
.end method

.method private fixCameraAlbumAttributes(Landroid/database/sqlite/SQLiteDatabase;)I
    .locals 6

    new-instance v0, Landroid/content/ContentValues;

    invoke-direct {v0}, Landroid/content/ContentValues;-><init>()V

    const-wide/16 v1, 0x5

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "attributes"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v3, 0x5

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    const-string v5, "serverId"

    aput-object v5, v3, v4

    const-wide/16 v4, 0x1

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    const/4 v5, 0x1

    aput-object v4, v3, v5

    const/4 v4, 0x2

    aput-object v2, v3, v4

    const-wide/16 v4, 0x10

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const/4 v4, 0x3

    aput-object v2, v3, v4

    const/4 v4, 0x4

    aput-object v2, v3, v4

    const-string v2, "%s=%d and (%s & %d)=%d"

    invoke-static {v1, v2, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "cloud"

    const/4 v3, 0x0

    invoke-virtual {p1, v2, v0, v1, v3}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result p1

    return p1
.end method

.method public static getCloudControlTables()[Ljava/lang/String;
    .locals 2

    sget-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sCloudControlTables:[Ljava/lang/String;

    array-length v1, v0

    invoke-static {v0, v1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    return-object v0
.end method

.method public static getCloudTables()[Ljava/lang/String;
    .locals 2

    sget-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sCloudTables:[Ljava/lang/String;

    array-length v1, v0

    invoke-static {v0, v1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    return-object v0
.end method

.method public static getCursorString(Landroid/database/Cursor;I)Ljava/lang/String;
    .locals 0

    invoke-interface {p0, p1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    const-string p0, ""

    :cond_0
    return-object p0
.end method

.method public static declared-synchronized getInstance()Lcom/miui/gallery/provider/GalleryDBHelper;
    .locals 2

    const-class v0, Lcom/miui/gallery/provider/GalleryDBHelper;

    monitor-enter v0

    :try_start_0
    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->getInstance(Landroid/content/Context;)Lcom/miui/gallery/provider/GalleryDBHelper;

    move-result-object v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public static declared-synchronized getInstance(Landroid/content/Context;)Lcom/miui/gallery/provider/GalleryDBHelper;
    .locals 2

    const-class v0, Lcom/miui/gallery/provider/GalleryDBHelper;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/miui/gallery/provider/GalleryDBHelper;->sDBHelper:Lcom/miui/gallery/provider/GalleryDBHelper;

    if-nez v1, :cond_0

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper;

    invoke-direct {v1, p0}, Lcom/miui/gallery/provider/GalleryDBHelper;-><init>(Landroid/content/Context;)V

    sput-object v1, Lcom/miui/gallery/provider/GalleryDBHelper;->sDBHelper:Lcom/miui/gallery/provider/GalleryDBHelper;

    :cond_0
    sget-object p0, Lcom/miui/gallery/provider/GalleryDBHelper;->sDBHelper:Lcom/miui/gallery/provider/GalleryDBHelper;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object p0

    :catchall_0
    move-exception p0

    monitor-exit v0

    throw p0
.end method

.method public static getPeopleFaceTables()[Ljava/lang/String;
    .locals 2

    sget-object v0, Lcom/miui/gallery/provider/GalleryDBHelper;->sPeopleFaceTables:[Ljava/lang/String;

    array-length v1, v0

    invoke-static {v0, v1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    return-object v0
.end method

.method private getVideosExceptCreated(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)Ljava/util/ArrayList;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/database/sqlite/SQLiteDatabase;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    const-string v0, "sha1"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v3

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object v1, p1

    move-object v2, p2

    move-object v4, p3

    invoke-virtual/range {v1 .. v8}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p1

    if-eqz p1, :cond_1

    :try_start_0
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    :goto_0
    invoke-interface {p1}, Landroid/database/Cursor;->moveToNext()Z

    move-result p3

    if-eqz p3, :cond_0

    const/4 p3, 0x0

    invoke-interface {p1, p3}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :cond_0
    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    return-object p2

    :catchall_0
    move-exception p2

    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    throw p2

    :cond_1
    const/4 p1, 0x0

    return-object p1
.end method

.method private initCloudColumns()V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v2, "integer"

    const-string v3, "_id"

    invoke-direct {v1, v3, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "groupId"

    invoke-direct {v1, v3, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "size"

    invoke-direct {v1, v3, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "dateModified"

    invoke-direct {v1, v3, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "text"

    const-string v4, "mimeType"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "title"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "description"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "fileName"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "dateTaken"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "duration"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "serverId"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "serverType"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "serverStatus"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "serverTag"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifImageWidth"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifImageLength"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifOrientation"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSLatitude"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSLongitude"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifMake"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifModel"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifFlash"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSLatitudeRef"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSLongitudeRef"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifExposureTime"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifFNumber"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifISOSpeedRatings"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSAltitude"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSAltitudeRef"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSTimeStamp"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSDateStamp"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifWhiteBalance"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifFocalLength"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifGPSProcessingMethod"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "exifDateTime"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "localFlag"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "thumbnailFile"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "downloadFile"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "localFile"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "sha1"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "sortBy"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "microthumbfile"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "localGroupId"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "localImageId"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "albumId"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "canModified"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "shareUrl"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "albumUserTag"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "newImageFlag"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "newUserFlag"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "creatorId"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "isPublic"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "publicUrl"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "downloadFileStatus"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "downloadFileTime"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "mixedDateTime"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "ubiSubImageCount"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "ubiFocusIndex"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "ubiSubIndex"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "editedColumns"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "fromLocalGroupId"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "secretKey"

    const-string v5, "blob"

    invoke-direct {v1, v4, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "appKey"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "babyInfoJson"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "peopleId"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "lables"

    invoke-direct {v1, v4, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "thumbnailInfo"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "location"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-wide/16 v4, 0x0

    invoke-static {v4, v5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    const-string v5, "attributes"

    invoke-direct {v1, v5, v2, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "extraGPS"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v4, "address"

    invoke-direct {v1, v4, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-wide/16 v3, 0x0

    invoke-static {v3, v4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v3

    const-string v4, "specialTypeFlags"

    invoke-direct {v1, v4, v2, v3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method private refillBabyAlbumDataTaken(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 8

    const-string v0, "_id"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v3

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    const-string v4, "peopleId"

    aput-object v4, v1, v2

    const-string v2, " NOT ( %s is null )"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    new-instance v7, Lcom/miui/gallery/provider/GalleryDBHelper$3;

    invoke-direct {v7, p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper$3;-><init>(Lcom/miui/gallery/provider/GalleryDBHelper;Landroid/database/sqlite/SQLiteDatabase;)V

    const-string v2, "cloud"

    const/4 v5, 0x0

    const/4 v6, 0x0

    move-object v1, p1

    invoke-static/range {v1 .. v7}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    return-void
.end method

.method private refillIsFavorite(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 7

    new-instance v6, Lcom/miui/gallery/provider/GalleryDBHelper$5;

    invoke-direct {v6, p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper$5;-><init>(Lcom/miui/gallery/provider/GalleryDBHelper;Landroid/database/sqlite/SQLiteDatabase;)V

    const-string v0, "description"

    const-string v1, "sha1"

    filled-new-array {v0, v1}, [Ljava/lang/String;

    move-result-object v2

    const-string v1, "cloud"

    const-string v3, "serverType IN (1,2) AND description NOT NULL AND description != \'\' AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\'))"

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v0, p1

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    return-void
.end method

.method private refillLocalFileAndAttributes(Landroid/database/sqlite/SQLiteDatabase;ILjava/lang/String;ZZ)V
    .locals 6

    const/4 v0, 0x6

    invoke-static {v0}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v1

    sget-object v2, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v3, 0xf

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    const-string v5, "cloud"

    aput-object v5, v3, v4

    const/4 v4, 0x1

    const-string v5, "localFile"

    aput-object v5, v3, v4

    const/4 v4, 0x2

    aput-object p3, v3, v4

    const-string p3, "attributes"

    const/4 v4, 0x3

    aput-object p3, v3, v4

    const/4 v4, 0x4

    aput-object p3, v3, v4

    const-wide/16 v4, 0x5

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p3

    const/4 v4, 0x5

    aput-object p3, v3, v4

    const-wide/16 v4, 0x0

    if-eqz p4, :cond_0

    const-wide/16 p3, 0x1

    goto :goto_0

    :cond_0
    move-wide p3, v4

    :goto_0
    invoke-static {p3, p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p3

    aput-object p3, v3, v0

    const/4 p3, 0x7

    if-eqz p5, :cond_1

    const-wide/16 v4, 0x4

    :cond_1
    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p4

    aput-object p4, v3, p3

    const/16 p3, 0x8

    const-string p4, "editedColumns"

    aput-object p4, v3, p3

    const/16 p3, 0x9

    aput-object p4, v3, p3

    const/16 p3, 0xa

    aput-object v1, v3, p3

    const/16 p3, 0xb

    aput-object v1, v3, p3

    const/16 p3, 0xc

    aput-object v1, v3, p3

    const/16 p3, 0xd

    const-string p4, "_id"

    aput-object p4, v3, p3

    const/16 p3, 0xe

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    aput-object p2, v3, p3

    const-string p2, "update %s set %s=\'%s\', %s=((%s & ~%d) | %d | %d), %s=coalesce(replace(%s, \'%s\', \'\') || \'%s\', \'%s\') where %s=%d"

    invoke-static {v2, p2, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method public static refillLocalGroupId(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 13

    const-string v0, "custom"

    const-string v1, "serverStatus"

    const/4 v2, 0x2

    const/4 v3, 0x1

    const-string v4, "_id"

    const/4 v5, 0x0

    if-eqz p1, :cond_0

    const-string p1, "serverId"

    filled-new-array {v4, p1}, [Ljava/lang/String;

    move-result-object v8

    sget-object p1, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v6, 0x4

    new-array v6, v6, [Ljava/lang/Object;

    const-string v7, "serverType"

    aput-object v7, v6, v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    aput-object v7, v6, v3

    aput-object v1, v6, v2

    const/4 v7, 0x3

    aput-object v0, v6, v7

    const-string v7, "%s=%s AND %s=\'%s\'"

    invoke-static {p1, v7, v6}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v9

    const/4 v10, 0x0

    const/4 v11, 0x0

    new-instance v12, Lcom/miui/gallery/provider/GalleryDBHelper$UpdateLocalGroupIdQueryHandler;

    const-string p1, "cloud"

    const-string v6, "groupId"

    invoke-direct {v12, p0, p1, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$UpdateLocalGroupIdQueryHandler;-><init>(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)V

    const-string v7, "cloud"

    move-object v6, p0

    invoke-static/range {v6 .. v12}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    :cond_0
    if-eqz p2, :cond_1

    const-string p1, "albumId"

    filled-new-array {v4, p1}, [Ljava/lang/String;

    move-result-object v8

    sget-object p2, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v2, v2, [Ljava/lang/Object;

    aput-object v1, v2, v5

    aput-object v0, v2, v3

    const-string v0, "%s=\'%s\'"

    invoke-static {p2, v0, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v9

    const/4 v10, 0x0

    const/4 v11, 0x0

    new-instance v12, Lcom/miui/gallery/provider/GalleryDBHelper$UpdateLocalGroupIdQueryHandler;

    const-string p2, "shareImage"

    invoke-direct {v12, p0, p2, p1}, Lcom/miui/gallery/provider/GalleryDBHelper$UpdateLocalGroupIdQueryHandler;-><init>(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)V

    const-string v7, "shareAlbum"

    move-object v6, p0

    invoke-static/range {v6 .. v12}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    :cond_1
    return-void
.end method

.method private refillLocalGroupId(Landroid/database/sqlite/SQLiteDatabase;ZZZ)V
    .locals 1

    const/4 v0, 0x1

    xor-int/2addr p2, v0

    if-nez p3, :cond_0

    if-nez p4, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-static {p1, p2, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->refillLocalGroupId(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    return-void
.end method

.method private refillRelationTypeOfPeople(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 9

    const-string v0, "peopleContactJsonInfo"

    const-string v1, "_id"

    filled-new-array {v1, v0}, [Ljava/lang/String;

    move-result-object v4

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v3, 0x0

    aput-object v0, v2, v3

    const-string v0, " NOT ( %s is null )"

    invoke-static {v1, v0, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    new-instance v8, Lcom/miui/gallery/provider/GalleryDBHelper$4;

    invoke-direct {v8, p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper$4;-><init>(Lcom/miui/gallery/provider/GalleryDBHelper;Landroid/database/sqlite/SQLiteDatabase;)V

    const-string v3, "peopleFace"

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v2, p1

    invoke-static/range {v2 .. v8}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    return-void
.end method

.method private replaceHongMiFilePath(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 14

    sget-boolean v0, Lcom/miui/os/Device;->IS_HONGMI:Z

    if-eqz v0, :cond_0

    const-string v0, "localFile"

    const-string v1, "thumbnailFile"

    const-string v2, "microthumbfile"

    filled-new-array {v0, v1, v2}, [Ljava/lang/String;

    move-result-object v0

    const-string v1, "update %s set %s = substr(%s, 0, 16) || (substr(%s, 10, 7) = \'sdcard0\') ||  substr(%s, 17) where substr(%s, 10, 7) = \'sdcard1\' OR substr(%s, 10, 7) = \'sdcard0\' "

    array-length v2, v0

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    if-ge v4, v2, :cond_0

    aget-object v5, v0, v4

    const/4 v6, 0x7

    new-array v7, v6, [Ljava/lang/Object;

    const-string v8, "cloud"

    aput-object v8, v7, v3

    const/4 v8, 0x1

    aput-object v5, v7, v8

    const/4 v9, 0x2

    aput-object v5, v7, v9

    const/4 v10, 0x3

    aput-object v5, v7, v10

    const/4 v11, 0x4

    aput-object v5, v7, v11

    const/4 v12, 0x5

    aput-object v5, v7, v12

    const/4 v13, 0x6

    aput-object v5, v7, v13

    invoke-static {v1, v7}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {p1, v7}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    new-array v6, v6, [Ljava/lang/Object;

    const-string v7, "shareImage"

    aput-object v7, v6, v3

    aput-object v5, v6, v8

    aput-object v5, v6, v9

    aput-object v5, v6, v10

    aput-object v5, v6, v11

    aput-object v5, v6, v12

    aput-object v5, v6, v13

    invoke-static {v1, v6}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p1, v5}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method private static safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z
    .locals 2

    :try_start_0
    invoke-virtual {p0, p1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p0, 0x1

    return p0

    :catch_0
    move-exception p0

    invoke-virtual {p0}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v0, "GalleryDBHelper"

    const-string v1, "fail to execSQL: %s , detail: %s"

    invoke-static {v0, v1, p1, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    const/4 p0, 0x0

    return p0
.end method

.method private static setAllSyncTagAsDefault(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 4

    const-string v0, "GalleryDBHelperConvertOldData"

    const-string v1, "setAllSyncTagAsDefault, url != 2.1"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Landroid/content/ContentValues;

    invoke-direct {v0}, Landroid/content/ContentValues;-><init>()V

    const/4 v1, 0x0

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "syncTag"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string v2, "shareSyncTagAlbumList"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string v2, "shareSyncTagAlbumInfo"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string v2, "shareSyncTagImageList"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const/4 v2, 0x0

    const-string v3, "cloudSetting"

    invoke-virtual {p1, v3, v0, v2, v2}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    new-instance v0, Landroid/content/ContentValues;

    invoke-direct {v0}, Landroid/content/ContentValues;-><init>()V

    const-string v3, "albumImageTag"

    invoke-virtual {v0, v3, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string v1, "shareAlbum"

    invoke-virtual {p1, v1, v0, v2, v2}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    new-instance p1, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    invoke-direct {p1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;-><init>()V

    sget-object v0, Lcom/miui/gallery/cloud/base/SyncType;->NORMAL:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object p1

    const-wide v0, 0x7fffffffffffffffL

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncReason(J)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->build()Lcom/miui/gallery/cloud/base/SyncRequest;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/miui/gallery/util/SyncUtil;->requestSync(Landroid/content/Context;Lcom/miui/gallery/cloud/base/SyncRequest;)V

    return-void
.end method

.method private updateCameraAlbumValues(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 6

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v1, 0xd

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    const-string v3, "cloud"

    aput-object v3, v1, v2

    const/4 v2, 0x1

    const-string v3, "dateTaken"

    aput-object v3, v1, v2

    const-wide/16 v2, -0x3e7

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const/4 v3, 0x2

    aput-object v2, v1, v3

    const/4 v3, 0x3

    const-string v4, "mixedDateTime"

    aput-object v4, v1, v3

    const/4 v3, 0x4

    aput-object v2, v1, v3

    const-string v3, "sortBy"

    const/4 v4, 0x5

    aput-object v3, v1, v4

    const/4 v4, 0x6

    aput-object v3, v1, v4

    const/4 v4, 0x7

    aput-object v3, v1, v4

    const-wide/16 v4, -0x3ea

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    const/16 v5, 0x8

    aput-object v4, v1, v5

    const/16 v4, 0x9

    aput-object v2, v1, v4

    const/16 v2, 0xa

    aput-object v3, v1, v2

    const/16 v2, 0xb

    const-string v3, "serverId"

    aput-object v3, v1, v2

    const-wide/16 v2, 0x1

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const/16 v3, 0xc

    aput-object v2, v1, v3

    const-string v2, "UPDATE %s SET %s=%d, %s=%d, %s=(CASE WHEN %s=null THEN null WHEN %s>%d THEN %d ELSE %s END) WHERE %s=%d"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private upgradeAlbumEditedColumns(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 14

    const/4 v0, 0x6

    invoke-static {v0}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x7

    new-array v2, v2, [Ljava/lang/Object;

    const/4 v3, 0x0

    const-string v4, "cloud"

    aput-object v4, v2, v3

    const-string v4, "editedColumns"

    const/4 v5, 0x1

    aput-object v4, v2, v5

    const/4 v6, 0x2

    aput-object v4, v2, v6

    const/4 v4, 0x3

    aput-object v1, v2, v4

    const/4 v7, 0x4

    aput-object v1, v2, v7

    const/4 v8, 0x5

    aput-object v1, v2, v8

    const-string v1, "(serverType=0)"

    const-string v9, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\'))"

    invoke-static {v1, v9}, Landroid/database/DatabaseUtils;->concatenateWhere(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    sget-object v9, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v10, v0, [Ljava/lang/Object;

    const-string v11, "attributes"

    aput-object v11, v10, v3

    const-wide/16 v12, 0xc

    invoke-static {v12, v13}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v10, v5

    aput-object v3, v10, v6

    aput-object v11, v10, v4

    const-wide/16 v3, 0x30

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v10, v7

    aput-object v3, v10, v8

    const-string v3, "(%s & %d) = %d or (%s & %d) = %d"

    invoke-static {v9, v3, v10}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v1, v3}, Landroid/database/DatabaseUtils;->concatenateWhere(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    aput-object v1, v2, v0

    const-string v0, "update %s set %s=coalesce(replace(%s, \'%s\', \'\') || \'%s\', \'%s\') where %s"

    invoke-static {v0, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    return-void
.end method

.method private upgradeTo21(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x2c

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x2d

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x2e

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_21(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/4 p3, 0x6

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "cloudSetting"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/4 v0, 0x7

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 v0, 0x8

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 v0, 0x9

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const-string p3, "shareAlbum"

    invoke-direct {p0, p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const-string p3, "shareImage"

    invoke-direct {p0, p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    const-string p3, "shareUser"

    invoke-direct {p0, p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo22(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    const/16 v0, 0xc

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "shareUser"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    const/16 v1, 0xb

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareUserTable_22(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 p3, 0xa

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "cloudSetting"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    const-string p3, "cloudUser"

    invoke-direct {p0, p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo23(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v0, 0x10

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "shareAlbum"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v1, 0x11

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v1, 0x12

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v1, 0x13

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x2e

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo24(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v0, 0x14

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "shareAlbum"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo25(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x2f

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo26(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x30

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x31

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 p3, 0x15

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareAlbum"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo27(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x32

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo28(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    const-string v1, "cloudCache"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo29(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    const-string v1, "newFlagCache"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo31(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x33

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x34

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 p3, 0x16

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareAlbum"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v0, 0x17

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    iget p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 p3, 0x15

    if-lt p2, p3, :cond_2

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->cleanCloudData(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_2
    return-void
.end method

.method private upgradeTo32(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "CREATE INDEX index_cloud_server_type on cloud (serverType)"

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private upgradeTo33(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    const-string v1, "userInfo"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo34(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mWhiteListLastModifyColumns:Ljava/util/ArrayList;

    const-string v1, "whiteListLastModify"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo35(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 5

    const/4 v0, 0x2

    new-array v1, v0, [Ljava/lang/Object;

    const/4 v2, 0x0

    const-string v3, "cloud"

    aput-object v3, v1, v2

    const/4 v3, 0x1

    const-string v4, "serverId"

    aput-object v4, v1, v3

    const-string v4, "CREATE UNIQUE INDEX IF NOT EXISTS index_serverId ON %s (%s)"

    invoke-static {v4, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    new-array v1, v0, [Ljava/lang/Object;

    const-string v4, "shareAlbum"

    aput-object v4, v1, v2

    const-string v4, "albumId"

    aput-object v4, v1, v3

    const-string v4, "CREATE UNIQUE INDEX IF NOT EXISTS index_albumId ON %s (%s)"

    invoke-static {v4, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    new-array v1, v0, [Ljava/lang/Object;

    const-string v4, "shareImage"

    aput-object v4, v1, v2

    const-string v4, "shareId"

    aput-object v4, v1, v3

    const-string v4, "CREATE UNIQUE INDEX IF NOT EXISTS index_shareId ON %s (%s)"

    invoke-static {v4, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    new-array v0, v0, [Ljava/lang/Object;

    const-string v1, "whiteListLastModify"

    aput-object v1, v0, v2

    const-string v1, "path"

    aput-object v1, v0, v3

    const-string v1, "CREATE UNIQUE INDEX IF NOT EXISTS index_path ON %s (%s)"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private upgradeTo36(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x35

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x36

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x2f

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 v0, 0x30

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo37(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->replaceHongMiFilePath(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private upgradeTo38(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 v0, 0xb

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloudSetting"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    invoke-static {p2, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->setAllSyncTagAsDefault(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method private upgradeTo39(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    const-string v1, "photoGpsCache"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo40(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    const-string v0, "DROP TABLE IF EXISTS photoGpsCache"

    invoke-virtual {p1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    const-string v1, "photoGpsCache"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo41(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 9

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v1, 0x8

    new-array v2, v1, [Ljava/lang/Object;

    const/4 v3, 0x0

    const-string v4, "serverType"

    aput-object v4, v2, v3

    const/4 v4, 0x2

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    const/4 v6, 0x1

    aput-object v5, v2, v6

    const-string v5, "localFlag"

    aput-object v5, v2, v4

    const/4 v4, 0x7

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    const/4 v8, 0x3

    aput-object v7, v2, v8

    const/4 v7, 0x4

    aput-object v5, v2, v7

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    const/4 v8, 0x5

    aput-object v7, v2, v8

    const/4 v7, 0x6

    aput-object v5, v2, v7

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    aput-object v1, v2, v4

    const-string v1, " %s = %d AND ( %s != %d OR %s != %d OR %s != %d )"

    invoke-static {v0, v1, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "cloud"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getVideosExceptCreated(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)Ljava/util/ArrayList;

    move-result-object v2

    const-string v4, "shareImage"

    invoke-direct {p0, p1, v4, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getVideosExceptCreated(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)Ljava/util/ArrayList;

    move-result-object v5

    if-eqz v2, :cond_0

    invoke-virtual {v2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v7

    if-eqz v7, :cond_1

    :cond_0
    if-eqz v5, :cond_4

    invoke-virtual {v5}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v7

    if-eqz v7, :cond_1

    goto :goto_0

    :cond_1
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "DELETE FROM %s WHERE "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    if-eqz v2, :cond_2

    invoke-virtual {v2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_2

    new-array v7, v6, [Ljava/lang/Object;

    aput-object v1, v7, v3

    invoke-static {v0, v7}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    invoke-direct {p0, v2}, Lcom/miui/gallery/provider/GalleryDBHelper;->deleteVideoThumbnailFile(Ljava/util/ArrayList;)V

    :cond_2
    if-eqz v5, :cond_3

    invoke-virtual {v5}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_3

    new-array v1, v6, [Ljava/lang/Object;

    aput-object v4, v1, v3

    invoke-static {v0, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    invoke-direct {p0, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->deleteVideoThumbnailFile(Ljava/util/ArrayList;)V

    :cond_3
    const-wide/16 v0, 0x0

    invoke-static {v0, v1}, Lcom/miui/gallery/util/deprecated/Preference;->sSetLastSlowScanTime(J)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    invoke-static {v0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->setAllSyncTagAsDefault(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V

    return-void

    :cond_4
    :goto_0
    const-string p1, "GalleryDBHelper"

    const-string v0, "none video in database, skip delete and resync."

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private upgradeTo42(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x1d

    if-lt v0, v1, :cond_0

    const/16 v1, 0x25

    if-le v0, v1, :cond_1

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "upgrade to 42, should clean data, oldVesion:"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "GalleryDBHelper"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->cleanCloudData(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-static {}, Lcom/miui/gallery/util/deprecated/Preference;->setDBUpgradeTo42()V

    new-instance p1, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    invoke-direct {p1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;-><init>()V

    sget-object v0, Lcom/miui/gallery/cloud/base/SyncType;->NORMAL:Lcom/miui/gallery/cloud/base/SyncType;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncType(Lcom/miui/gallery/cloud/base/SyncType;)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object p1

    const-wide v0, 0x7fffffffffffffffL

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->setSyncReason(J)Lcom/miui/gallery/cloud/base/SyncRequest$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/cloud/base/SyncRequest$Builder;->build()Lcom/miui/gallery/cloud/base/SyncRequest;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    invoke-static {v0, p1}, Lcom/miui/gallery/util/SyncUtil;->requestSync(Landroid/content/Context;Lcom/miui/gallery/cloud/base/SyncRequest;)V

    :cond_1
    return-void
.end method

.method private upgradeTo43(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 8

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x3

    new-array v2, v1, [Ljava/lang/Object;

    const/4 v3, 0x0

    const-string v4, "exifDateTime"

    aput-object v4, v2, v3

    const/4 v4, 0x1

    const-string v5, "dateModified"

    aput-object v5, v2, v4

    invoke-static {}, Ljava/util/TimeZone;->getDefault()Ljava/util/TimeZone;

    move-result-object v5

    invoke-virtual {v5}, Ljava/util/TimeZone;->getRawOffset()I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    const/4 v6, 0x2

    aput-object v5, v2, v6

    const-string v5, "CASE WHEN %1$s IS NULL THEN %2$s ELSE strftime(\'%%s000\', substr(%1$s, 0, 5)||\'-\'||substr(%1$s, 6, 2)||\'-\'||substr(%1$s,9,2)||\' \'||substr(%1$s, 11, 9)||\'.000\')%3$+d END"

    invoke-static {v0, v5, v2}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    const-string v2, "mixedDateTime"

    const-string v5, "UPDATE %s SET %s = %s"

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v7, 0x37

    invoke-virtual {p2, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v7, "cloud"

    invoke-static {p1, v7, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    new-array p2, v1, [Ljava/lang/Object;

    aput-object v7, p2, v3

    aput-object v2, p2, v4

    aput-object v0, p2, v6

    invoke-static {v5, p2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_43(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x31

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    new-array p2, v1, [Ljava/lang/Object;

    aput-object p3, p2, v3

    aput-object v2, p2, v4

    aput-object v0, p2, v6

    invoke-static {v5, p2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_43(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1
    return-void
.end method

.method private upgradeTo44(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mLocalUbifocusColumns:Ljava/util/ArrayList;

    const-string v1, "localUbifocus"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo45(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 1

    const-string v0, "cloud"

    invoke-static {v0}, Lcom/miui/gallery/util/deprecated/Time;->getUpgradeMixedDateTimeSqlString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    const-string v0, "shareImage"

    invoke-static {v0}, Lcom/miui/gallery/util/deprecated/Time;->getUpgradeMixedDateTimeSqlString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    return-void
.end method

.method private upgradeTo46(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    const-string v0, "cloudSetting"

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 v1, 0xc

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 p3, 0x18

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareAlbum"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    new-instance p2, Landroid/content/ContentValues;

    invoke-direct {p2}, Landroid/content/ContentValues;-><init>()V

    const-string p3, "syncInfo"

    const-string v1, ""

    invoke-virtual {p2, p3, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    const/4 p3, 0x0

    invoke-virtual {p1, v0, p2, p3, p3}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    const/4 p1, 0x1

    invoke-static {p1}, Lcom/miui/gallery/util/deprecated/Preference;->setSyncFetchSyncExtraInfoFromV2ToV3(Z)V

    return-void
.end method

.method private upgradeTo47(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    const-string v1, "ownerSubUbifocus"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    const-string v1, "shareSubUbifocus"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x38

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x39

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x3a

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x32

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 v0, 0x33

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 v0, 0x34

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo48(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x3b

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x35

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo49(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x3c

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x36

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo50(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 v0, 0xd

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloudSetting"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo51(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 0

    if-nez p2, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_51(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    if-nez p3, :cond_1

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_51(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1
    return-void
.end method

.method private upgradeTo52(Landroid/database/sqlite/SQLiteDatabase;ZZZZ)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x3d

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x37

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    if-nez p4, :cond_2

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x33

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "ownerSubUbifocus"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_2
    if-nez p5, :cond_3

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    const/16 p3, 0x35

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareSubUbifocus"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_3
    return-void
.end method

.method private upgradeTo53(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 0

    if-nez p2, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_53(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    if-nez p3, :cond_1

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_53(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1
    return-void
.end method

.method private upgradeTo54(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x3e

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo55(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    const-string v1, "peopleFace"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    const-string v1, "faceToImages"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo56(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 v0, 0xe

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloudSetting"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const/16 v1, 0xf

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo58(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    const-string v1, "peopleRecommend"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    const/16 v0, 0x10

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "peopleFace"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo59(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventColumns:Ljava/util/ArrayList;

    const-string v1, "event"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    const-string v1, "eventPhoto"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo61(Z)V
    .locals 0

    return-void
.end method

.method private upgradeTo62(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    const-string v1, "albumCoverKey"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo63(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x3f

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v1, 0x40

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    const/16 p3, 0x11

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "peopleFace"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo64(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 3

    const/16 v0, 0xe

    const/16 v1, 0xd

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v2, "cloudUser"

    invoke-static {p1, v2, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v2, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareUser"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo65(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v0, 0x19

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "shareAlbum"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v1, 0x1a

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v1, 0x1b

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo66(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 v0, 0x1c

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "shareAlbum"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo67(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 1

    if-nez p3, :cond_0

    iget-object p3, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const/16 v0, 0x38

    invoke-virtual {p3, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "shareImage"

    invoke-static {p1, v0, p3}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 p3, 0x41

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "cloud"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo68(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    const/16 v0, 0xf

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloudUser"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo69(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const/16 v0, 0x42

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "cloud"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const/16 p3, 0x1d

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string p3, "shareAlbum"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo70(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 0

    if-nez p2, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_70(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    if-nez p3, :cond_1

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnShareImageTable_70(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1
    return-void
.end method

.method private upgradeTo71(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    const-string v1, "eventPhoto"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo72(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 1

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    const/16 v0, 0x12

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "peopleFace"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo73(Landroid/database/sqlite/SQLiteDatabase;ZZZ)V
    .locals 9

    const-wide/16 v0, 0x0

    const-string v2, "integer"

    const-string v3, "attributes"

    const-string v4, "text"

    const-string v5, "location"

    if-nez p2, :cond_0

    new-instance v6, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v6, v5, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    const-string v7, "cloud"

    invoke-static {p1, v7, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    new-instance v6, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v8

    invoke-direct {v6, v3, v2, v8}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1, v7, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    new-instance v6, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v6, v5, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    const-string v4, "shareImage"

    invoke-static {p1, v4, v6}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    if-nez p4, :cond_2

    new-instance v4, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v4, v3, v2, v0}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "shareAlbum"

    invoke-static {p1, v0, v4}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_2
    invoke-static {}, Lcom/miui/gallery/data/LocationGenerator;->getInstance()Lcom/miui/gallery/data/LocationGenerator;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/data/LocationGenerator;->generate(Landroid/content/Context;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->addRecordsForCameraAndScreenshots(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1, p2, p3, p4}, Lcom/miui/gallery/provider/GalleryDBHelper;->refillLocalGroupId(Landroid/database/sqlite/SQLiteDatabase;ZZZ)V

    return-void
.end method

.method private upgradeTo74(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 11

    if-nez p2, :cond_0

    const-string p2, "serverId"

    const-string v0, "_id"

    const-string v1, "description"

    const-string v2, "fileName"

    const-string v3, "appKey"

    filled-new-array {v0, v1, v2, v3, p2}, [Ljava/lang/String;

    move-result-object v6

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v1, 0xd

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    const-string v3, "serverType"

    aput-object v3, v1, v2

    const/4 v3, 0x1

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v1, v3

    const/4 v3, 0x2

    const-string v4, "localFlag"

    aput-object v4, v1, v3

    const/4 v3, 0x3

    const/16 v5, 0x8

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    aput-object v7, v1, v3

    const/4 v3, 0x4

    aput-object v4, v1, v3

    const/4 v3, 0x5

    const/16 v7, 0xa

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    aput-object v8, v1, v3

    const/4 v3, 0x6

    aput-object v4, v1, v3

    const/4 v3, 0x7

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v1, v3

    const-string v2, "serverStatus"

    aput-object v2, v1, v5

    const/16 v2, 0x9

    const-string v3, "custom"

    aput-object v3, v1, v2

    const-string v2, "localFile"

    aput-object v2, v1, v7

    const/16 v2, 0xb

    aput-object p2, v1, v2

    const/16 p2, 0xc

    const-wide/16 v2, 0x1

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    aput-object v2, v1, p2

    const-string p2, "%s=%s AND (%s=%d OR %s=%d OR (%s=%d AND %s=\'%s\')) AND %s IS NULL AND %s != %d"

    invoke-static {v0, p2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x0

    const/4 v9, 0x0

    new-instance v10, Lcom/miui/gallery/provider/GalleryDBHelper$1;

    invoke-direct {v10, p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper$1;-><init>(Lcom/miui/gallery/provider/GalleryDBHelper;Landroid/database/sqlite/SQLiteDatabase;)V

    const-string v5, "cloud"

    move-object v4, p1

    invoke-static/range {v4 .. v10}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    :cond_0
    return-void
.end method

.method private upgradeTo75(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 0

    if-nez p2, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_75(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    return-void
.end method

.method private upgradeTo76(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 3

    if-nez p2, :cond_0

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "relationType"

    const-string v1, "integer"

    const-string v2, "0"

    invoke-direct {p2, v0, v1, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "peopleFace"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo77(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 0

    if-nez p2, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->refillBabyAlbumDataTaken(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    if-nez p3, :cond_1

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->refillRelationTypeOfPeople(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1
    return-void
.end method

.method private upgradeTo78(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 18

    if-nez p2, :cond_0

    if-nez p3, :cond_0

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v1, 0xf

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    const-string v3, "serverType"

    aput-object v3, v1, v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const/4 v4, 0x1

    aput-object v3, v1, v4

    const/4 v3, 0x2

    const-string v5, "localFlag"

    aput-object v5, v1, v3

    const/4 v6, 0x7

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    const/4 v8, 0x3

    aput-object v7, v1, v8

    const/4 v7, 0x4

    aput-object v5, v1, v7

    const/16 v9, 0x8

    invoke-static {v9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    const/4 v11, 0x5

    aput-object v10, v1, v11

    const/4 v10, 0x6

    aput-object v5, v1, v10

    const/16 v12, 0xa

    invoke-static {v12}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v13

    aput-object v13, v1, v6

    aput-object v5, v1, v9

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    const/16 v13, 0x9

    aput-object v5, v1, v13

    const-string v5, "serverStatus"

    aput-object v5, v1, v12

    const/16 v5, 0xb

    const-string v14, "custom"

    aput-object v14, v1, v5

    const/16 v5, 0xc

    const-string v14, "babyInfoJson"

    aput-object v14, v1, v5

    const/16 v5, 0xd

    const-string v14, "attributes"

    aput-object v14, v1, v5

    const/16 v5, 0xe

    const-wide/16 v15, 0x1

    invoke-static/range {v15 .. v16}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v17

    aput-object v17, v1, v5

    const-string v5, "%s=%s AND (%s = %d OR %s=%d OR %s=%d OR (%s=%d AND %s=\'%s\')) AND %s NOT NULL AND %s & %d = 0"

    invoke-static {v0, v5, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v10}, Lcom/miui/gallery/cloud/GalleryCloudUtils;->transformToEditedColumnsElement(I)Ljava/lang/String;

    move-result-object v1

    sget-object v5, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v12, v12, [Ljava/lang/Object;

    const-string v17, "cloud"

    aput-object v17, v12, v2

    aput-object v14, v12, v4

    aput-object v14, v12, v3

    invoke-static/range {v15 .. v16}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    aput-object v2, v12, v8

    const-string v2, "editedColumns"

    aput-object v2, v12, v7

    aput-object v2, v12, v11

    aput-object v1, v12, v10

    aput-object v1, v12, v6

    aput-object v1, v12, v9

    aput-object v0, v12, v13

    const-string v0, "update %s set %s=(%s | %d), %s=coalesce(replace(%s, \'%s\', \'\') || \'%s\', \'%s\') where %s"

    invoke-static {v5, v0, v12}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    move-object/from16 v1, p1

    invoke-static {v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    :cond_0
    return-void
.end method

.method private upgradeTo79(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 4

    if-nez p2, :cond_0

    new-instance p2, Landroid/content/ContentValues;

    invoke-direct {p2}, Landroid/content/ContentValues;-><init>()V

    const-wide/16 v0, -0x3e8

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "localGroupId"

    invoke-virtual {p2, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    const-string v0, "thumbnailFile"

    invoke-virtual {p2, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-wide/16 v2, 0x3e8

    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v2

    aput-object v2, v0, v1

    const-string v1, "cloud"

    const-string v2, "groupId=?"

    invoke-static {p1, v1, p2, v2, v0}, Lcom/miui/gallery/util/SafeDBUtil;->safeUpdate(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    :cond_0
    if-eqz p3, :cond_1

    const-string p1, "GalleryDBHelper"

    const-string p2, "delete secret thumbnail, because it has no sha1"

    invoke-static {p1, p2}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/data/BackgroundJobService;->startJobDeleteSecretThumbnail(Landroid/content/Context;)V

    :cond_1
    return-void
.end method

.method private upgradeTo80(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 5

    if-nez p2, :cond_0

    sget-object p2, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/16 v0, 0x9

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    const-string v2, "cloud"

    aput-object v2, v0, v1

    const/4 v2, 0x1

    const-string v3, "attributes"

    aput-object v3, v0, v2

    const/4 v2, 0x2

    aput-object v3, v0, v2

    const/4 v2, 0x3

    const-wide/16 v3, 0x4

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    aput-object v3, v0, v2

    const/4 v2, 0x4

    const-string v3, "serverType"

    aput-object v3, v0, v2

    const/4 v2, 0x5

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    aput-object v1, v0, v2

    const/4 v1, 0x6

    const-string v2, "serverId"

    aput-object v2, v0, v1

    const/4 v1, 0x7

    aput-object v2, v0, v1

    const/16 v1, 0x8

    const-wide/16 v2, 0x1

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    aput-object v2, v0, v1

    const-string v1, "update %s set %s=(%s & ~%d)  where %s=%s and (%s is null or %s!=\'%s\')"

    invoke-static {p2, v1, v0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->safeExecSQL(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)Z

    :cond_0
    return-void
.end method

.method private upgradeTo81(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 3

    if-nez p2, :cond_0

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "eTag"

    const-string v1, "integer"

    const-string v2, "0"

    invoke-direct {p2, v0, v1, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "peopleFace"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo82(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 4

    if-nez p2, :cond_0

    new-instance p2, Landroid/content/ContentValues;

    invoke-direct {p2}, Landroid/content/ContentValues;-><init>()V

    const-wide/16 v0, -0x3e8

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "localGroupId"

    invoke-virtual {p2, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-wide/16 v2, 0x3e8

    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v2

    aput-object v2, v0, v1

    const-string v1, "cloud"

    const-string v2, "groupId=?"

    invoke-static {p1, v1, p2, v2, v0}, Lcom/miui/gallery/util/SafeDBUtil;->safeUpdate(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    :cond_0
    return-void
.end method

.method private upgradeTo83(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 2

    if-eqz p2, :cond_0

    new-instance p2, Landroid/content/ContentValues;

    invoke-direct {p2}, Landroid/content/ContentValues;-><init>()V

    const-string v0, "location"

    invoke-virtual {p2, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    const/4 v0, 0x0

    const-string v1, "cloud"

    invoke-static {p1, v1, p2, v0, v0}, Lcom/miui/gallery/util/SafeDBUtil;->safeUpdate(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    invoke-static {}, Lcom/miui/gallery/data/LocationGenerator;->getInstance()Lcom/miui/gallery/data/LocationGenerator;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/data/LocationGenerator;->generate(Landroid/content/Context;)V

    const-string p1, "GalleryDBHelper"

    const-string p2, "upgradeTo83 success"

    invoke-static {p1, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private upgradeTo84(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 5

    const/4 v0, 0x0

    const/4 v1, 0x1

    const-string v2, "update %s set attributes=( ((attributes & 1) * 1) | ((attributes & 2) * 2) | ((attributes & 4) * 4) ) where attributes > 0"

    if-nez p2, :cond_0

    sget-object p2, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array v3, v1, [Ljava/lang/Object;

    const-string v4, "cloud"

    aput-object v4, v3, v0

    invoke-static {p2, v2, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    :cond_0
    if-nez p3, :cond_1

    sget-object p2, Ljava/util/Locale;->US:Ljava/util/Locale;

    new-array p3, v1, [Ljava/lang/Object;

    const-string v1, "shareAlbum"

    aput-object v1, p3, v0

    invoke-static {p2, v2, p3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method private upgradeTo85(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    const-string v1, "discoveryMessage"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    const-string v1, "recentDiscoveredMedia"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo86(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 4

    const-string v0, "address"

    const-string v1, "extraGPS"

    const-string v2, "text"

    if-nez p2, :cond_0

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {p2, v1, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "cloud"

    invoke-static {p1, v3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {p2, v0, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1, v3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {p2, v1, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {p2, v0, v2}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo87(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    const-string v1, "cloudControl"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    return-void
.end method

.method private upgradeTo88(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 2

    if-nez p2, :cond_0

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v0, "relationText"

    const-string v1, "text"

    invoke-direct {p2, v0, v1}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "peopleFace"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method

.method private upgradeTo89(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 4

    if-nez p2, :cond_0

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "UPDATE %s SET attributes=(attributes | (((attributes & 2) | (NOT (attributes & 1))) << 6))  WHERE (serverType=0) AND "

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v0, Lcom/miui/gallery/provider/InternalContract$Cloud;->ALIAS_NON_SYSTEM_ALBUM:Ljava/lang/String;

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    const-string v3, "cloud"

    aput-object v3, v1, v2

    invoke-static {v0, p2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private upgradeTo90(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    const-string v1, "favorites"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    if-nez p2, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->updateCameraAlbumValues(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->refillIsFavorite(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_0
    return-void
.end method

.method private upgradeTo91(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    .locals 5

    const-wide/16 v0, 0x0

    const-string v2, "integer"

    const-string v3, "specialTypeFlags"

    if-nez p2, :cond_0

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    invoke-direct {p2, v3, v2, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v4, "cloud"

    invoke-static {p1, v4, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    if-nez p3, :cond_1

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p3

    invoke-direct {p2, v3, v2, p3}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string p3, "shareImage"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_1
    return-void
.end method

.method private upgradeTo92(Landroid/database/sqlite/SQLiteDatabase;Z)V
    .locals 3

    if-nez p2, :cond_0

    new-instance p2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-wide/high16 v0, -0x4010000000000000L    # -1.0

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(D)Ljava/lang/String;

    move-result-object v0

    const-string v1, "faceCoverScore"

    const-string v2, "real"

    invoke-direct {p2, v1, v2, v0}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "peopleFace"

    invoke-static {p1, v0, p2}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public analyze()V
    .locals 1

    const-string v0, "analyze;"

    invoke-virtual {p0, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->execSQL(Ljava/lang/String;)Z

    return-void
.end method

.method public delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0

    invoke-virtual {v0, p1, p2, p3}, Landroid/database/sqlite/SQLiteDatabase;->delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I

    move-result p1

    return p1
.end method

.method public deleteDatabase()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mContext:Landroid/content/Context;

    const-string v1, "gallery.db"

    invoke-virtual {v0, v1}, Landroid/content/Context;->deleteDatabase(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method public execSQL(Ljava/lang/String;)Z
    .locals 2

    :try_start_0
    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p1, 0x1

    return p1

    :catch_0
    move-exception p1

    const-string v0, "GalleryDBHelper"

    const-string v1, "exec sql"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const/4 p1, 0x0

    return p1
.end method

.method public getCloudColumns()Ljava/util/ArrayList;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;",
            ">;"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->initCloudColumns()V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    return-object v0
.end method

.method public insert(Ljava/lang/String;Landroid/content/ContentValues;)J
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, p1, v1, p2}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    move-result-wide p1

    return-wide p1
.end method

.method public onConfigure(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 19

    move-object/from16 v0, p0

    invoke-super/range {p0 .. p1}, Landroid/database/sqlite/SQLiteOpenHelper;->onConfigure(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->initCloudColumns()V

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "_id"

    const-string v4, "integer"

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v5, "text"

    const-string v6, "bucket_id"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "bucket_path"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "recent_visit_time"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "visit_count"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "is_hidden_recent"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "is_manually_recent"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "is_hidden"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "sort_by"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "cover_path"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "sdcard_sort_by"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "accountName"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "accountType"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "syncTag"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "isSync"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "isSyncOnlyOnWifi"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "shareSyncTagAlbumList"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "shareSyncTagAlbumInfo"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "shareSyncTagImageList"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "shareSyncTagUserList"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "ownerSyncTagUserList"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "syncInfo"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "shareSyncInfo"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "cloudTabNewFlag"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "faceWatermark"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "faceSyncToken"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "groupId"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v7, "dateModified"

    invoke-direct {v2, v7, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "description"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "fileName"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "dateTaken"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "serverId"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v12, "serverType"

    invoke-direct {v2, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v13, "serverStatus"

    invoke-direct {v2, v13, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "serverTag"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v15, "localFlag"

    invoke-direct {v2, v15, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 p1, v12

    const-string v12, "sortBy"

    invoke-direct {v2, v12, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v12, "canModified"

    invoke-direct {v2, v12, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v12, "albumId"

    invoke-direct {v2, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v16, v11

    const-string v11, "creatorId"

    invoke-direct {v2, v11, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "shareUrl"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "albumStatus"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "albumTag"

    invoke-direct {v2, v11, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "albumImageTag"

    invoke-direct {v2, v11, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "albumUserTag"

    invoke-direct {v2, v11, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "shareUrlLong"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "newImageFlag"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "isPublic"

    invoke-direct {v2, v11, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "publicUrl"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "syncInfo"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "babyInfoJson"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "peopleId"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "sharerInfo"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "editedColumns"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "thumbnailInfo"

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-wide/16 v17, 0x0

    invoke-static/range {v17 .. v18}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v11

    move-object/from16 v17, v10

    const-string v10, "attributes"

    invoke-direct {v2, v10, v4, v11}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "userId"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "userName"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "createTime"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "requestType"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "requestValue"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v13, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v15, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "shareUrl"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "shareText"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "localAlbumId"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "relation"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "relationText"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "size"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v7, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "mimeType"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "title"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v10, v17

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v11, "duration"

    invoke-direct {v2, v11, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v11, v16

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v10, p1

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v13, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifImageWidth"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifImageLength"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifOrientation"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSLatitude"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSLongitude"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifMake"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifModel"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifFlash"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSLatitudeRef"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSLongitudeRef"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifExposureTime"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifFNumber"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifISOSpeedRatings"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSAltitude"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSAltitudeRef"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSTimeStamp"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSDateStamp"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifWhiteBalance"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifFocalLength"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifGPSProcessingMethod"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "exifDateTime"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v15, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "thumbnailFile"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "downloadFile"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "localFile"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "sha1"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "sortBy"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "microthumbfile"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "localGroupId"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "localImageId"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "creatorId"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "shareId"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "downloadFileStatus"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "downloadFileTime"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "mixedDateTime"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "ubiSubImageCount"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "ubiFocusIndex"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "ubiSubIndex"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "editedColumns"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "fromLocalGroupId"

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "secretKey"

    move-object/from16 v16, v9

    const-string v9, "blob"

    invoke-direct {v2, v10, v9}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "lables"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "location"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "extraGPS"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "address"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-wide/16 v9, 0x0

    invoke-static {v9, v10}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v9

    const-string v10, "specialTypeFlags"

    invoke-direct {v2, v10, v4, v9}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "userId"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "userName"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "createTime"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "requestType"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "requestValue"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v13, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v15, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "shareUrl"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "shareText"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "localAlbumId"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "relation"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "relationText"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "phone"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "barcodeData"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "barcodeDataDeadline"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "smsShareData"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "smsShareDataDeadline"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "newImageFlag"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "newUserFlag"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "date_modified"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "user_id"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "alias_nick"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "miliao_nick"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "miliao_icon_url"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mWhiteListLastModifyColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "path"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mWhiteListLastModifyColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "stamp"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "gpsData"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "cityId"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mLocalUbifocusColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "data"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mLocalUbifocusColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "focusIndex"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "size"

    invoke-direct {v2, v9, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v7, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "mimeType"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v9, "title"

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v9, v16

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v10, v17

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v10, "duration"

    invoke-direct {v2, v10, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v10, p1

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v13, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 p1, v14

    const-string v14, "exifImageWidth"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifImageLength"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifOrientation"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSLatitude"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSLongitude"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifMake"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifModel"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifFlash"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSLatitudeRef"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSLongitudeRef"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifExposureTime"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifFNumber"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifISOSpeedRatings"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSAltitude"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSAltitudeRef"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSTimeStamp"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSDateStamp"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifWhiteBalance"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifFocalLength"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifGPSProcessingMethod"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "exifDateTime"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v15, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "thumbnailFile"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "downloadFile"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "localFile"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "sha1"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "sortBy"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "microthumbfile"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "localGroupId"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "localImageId"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "downloadFileStatus"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "downloadFileTime"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "mixedDateTime"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "ubiServerId"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "ubiLocalId"

    invoke-direct {v2, v14, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "ubiSubIndex"

    invoke-direct {v2, v14, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v14, "secretKey"

    move-object/from16 v16, v12

    const-string v12, "blob"

    invoke-direct {v2, v14, v12}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v12, "size"

    invoke-direct {v2, v12, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v7, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v7, "mimeType"

    invoke-direct {v2, v7, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v7, "title"

    invoke-direct {v2, v7, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v9, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v7, v17

    invoke-direct {v2, v7, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "duration"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v10, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v13, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v8, p1

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifImageWidth"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifImageLength"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifOrientation"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSLatitude"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSLongitude"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifMake"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifModel"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifFlash"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSLatitudeRef"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSLongitudeRef"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifExposureTime"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifFNumber"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifISOSpeedRatings"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSAltitude"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSAltitudeRef"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSTimeStamp"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSDateStamp"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifWhiteBalance"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifFocalLength"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifGPSProcessingMethod"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "exifDateTime"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v15, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "thumbnailFile"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "downloadFile"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "localFile"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "sha1"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "sortBy"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "microthumbfile"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "localGroupId"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "localImageId"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    move-object/from16 v8, v16

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "creatorId"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "shareId"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "downloadFileStatus"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "downloadFileTime"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "mixedDateTime"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "ubiServerId"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "ubiLocalId"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "ubiSubIndex"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "secretKey"

    const-string v9, "blob"

    invoke-direct {v2, v8, v9}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v11, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "type"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "localGroupId"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v15, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "real"

    const-string v8, "faceXScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "faceYScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "faceWScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "faceHScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "leftEyeXScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "leftEyeYScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "RightEyeXScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "RightEyeYScale"

    invoke-direct {v2, v8, v6}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v13, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "peopleName"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "visibilityType"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "peopleType"

    invoke-direct {v2, v8, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "peopleContactJsonInfo"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "relationType"

    const-string v9, "0"

    invoke-direct {v2, v8, v4, v9}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "eTag"

    const-string v9, "0"

    invoke-direct {v2, v8, v4, v9}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v8, "relationText"

    invoke-direct {v2, v8, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-wide/high16 v8, -0x4010000000000000L    # -1.0

    invoke-static {v8, v9}, Ljava/lang/String;->valueOf(D)Ljava/lang/String;

    move-result-object v8

    const-string v9, "faceCoverScore"

    invoke-direct {v2, v9, v6, v8}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "faceId"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "imageServerId"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "peopleServerId"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "recommendPeoplesJson"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "recommendHistoryJson"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "lastUpdateFromServerTime"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "name"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "localEventId"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "key"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v7, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "path"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "userKey"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "lastModified"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "key"

    const-string v7, "blob"

    invoke-direct {v2, v6, v7}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "message"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "title"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "subTitle"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "type"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "priority"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "receiveTime"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "updateTime"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "triggerTime"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "expireTime"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "actionUri"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "messageSource"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "isConsumed"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "extraData"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "mediaId"

    const/4 v7, 0x1

    invoke-direct {v2, v6, v4, v7}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "dateAdded"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "source"

    invoke-direct {v2, v6, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "featureName"

    invoke-direct {v2, v6, v5, v7}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "status"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v6, "strategy"

    invoke-direct {v2, v6, v5}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "isFavorite"

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "dateFavorite"

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "source"

    invoke-direct {v2, v3, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v1, v0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    new-instance v2, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v3, "sha1"

    const/4 v4, 0x1

    invoke-direct {v2, v3, v5, v4}, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public onCreate(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    const-string v1, "album"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    const-string v1, "cloud"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    const-string v1, "cloudSetting"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareAlbumColumns:Ljava/util/ArrayList;

    const-string v1, "shareAlbum"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareImageColumns:Ljava/util/ArrayList;

    const-string v1, "shareImage"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareUserColumns:Ljava/util/ArrayList;

    const-string v1, "shareUser"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudUserColumns:Ljava/util/ArrayList;

    const-string v1, "cloudUser"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudCacheColumns:Ljava/util/ArrayList;

    const-string v1, "cloudCache"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mNewFlagCacheColumns:Ljava/util/ArrayList;

    const-string v1, "newFlagCache"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mUserInfoColumns:Ljava/util/ArrayList;

    const-string v1, "userInfo"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mWhiteListLastModifyColumns:Ljava/util/ArrayList;

    const-string v1, "whiteListLastModify"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPhotoGpsCacheColumns:Ljava/util/ArrayList;

    const-string v1, "photoGpsCache"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mLocalUbifocusColumns:Ljava/util/ArrayList;

    const-string v1, "localUbifocus"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOwnerSubUbiImageColumns:Ljava/util/ArrayList;

    const-string v1, "ownerSubUbifocus"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mShareSubUbiImageColumns:Ljava/util/ArrayList;

    const-string v1, "shareSubUbifocus"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleFaceColumns:Ljava/util/ArrayList;

    const-string v1, "peopleFace"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFace2ImagesColumns:Ljava/util/ArrayList;

    const-string v1, "faceToImages"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mPeopleRecommendColumns:Ljava/util/ArrayList;

    const-string v1, "peopleRecommend"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventColumns:Ljava/util/ArrayList;

    const-string v1, "event"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mEventPhotoColumns:Ljava/util/ArrayList;

    const-string v1, "eventPhoto"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumCoverKeyColumns:Ljava/util/ArrayList;

    const-string v1, "albumCoverKey"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mDiscoveryMessageColumns:Ljava/util/ArrayList;

    const-string v1, "discoveryMessage"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mRecentDiscoveredMediaColumns:Ljava/util/ArrayList;

    const-string v1, "recentDiscoveredMedia"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudControlColumns:Ljava/util/ArrayList;

    const-string v1, "cloudControl"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    iget-object v0, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mFavoritesColumns:Ljava/util/ArrayList;

    const-string v1, "favorites"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createViewIfNeeded(Landroid/database/sqlite/SQLiteDatabase;Z)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->addUniqueIndexs(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-direct {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->clearColumnList()V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->addRecordsForCameraAndScreenshots(Landroid/database/sqlite/SQLiteDatabase;)V

    return-void
.end method

.method public onOpen(Landroid/database/sqlite/SQLiteDatabase;)V
    .locals 6

    invoke-super {p0, p1}, Landroid/database/sqlite/SQLiteOpenHelper;->onOpen(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$DataBase;->getEverFixedCameraAlbumAttributes()Z

    move-result v0

    const-string v1, "version old[%d], new[%d], exception[%s]"

    const-string v2, "GalleryDBHelper"

    if-nez v0, :cond_0

    :try_start_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->fixCameraAlbumAttributes(Landroid/database/sqlite/SQLiteDatabase;)I

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$DataBase;->setEverFixedCameraAlbumAttributes()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    new-instance v3, Ljava/util/HashMap;

    invoke-direct {v3}, Ljava/util/HashMap;-><init>()V

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    iget v5, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, "_"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Landroid/database/sqlite/SQLiteDatabase;->getVersion()I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    const-string v5, "version"

    invoke-interface {v3, v5, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v0}, Landroid/util/Log;->getStackTraceString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v4

    const-string v5, "exception"

    invoke-interface {v3, v5, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v4, "db_helper"

    const-string v5, "db_fix_camera_attributes"

    invoke-static {v4, v5, v3}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    iget v3, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {p1}, Landroid/database/sqlite/SQLiteDatabase;->getVersion()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v0}, Landroid/util/Log;->getStackTraceString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v1, v3, v4, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_0
    :goto_0
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$DataBase;->getEverUpgradeAlbumEditedColumns()Z

    move-result v0

    if-nez v0, :cond_1

    :try_start_1
    const-string v0, "upgradeAlbumEditedColumns"

    invoke-static {v2, v0}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeAlbumEditedColumns(Landroid/database/sqlite/SQLiteDatabase;)V

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$DataBase;->setEverUpgradeAlbumEditedColumns()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    :catch_1
    move-exception v0

    iget v3, p0, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {p1}, Landroid/database/sqlite/SQLiteDatabase;->getVersion()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v0}, Landroid/util/Log;->getStackTraceString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v1, v3, v4, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_1
    :goto_1
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createViewIfNeeded(Landroid/database/sqlite/SQLiteDatabase;Z)V

    return-void
.end method

.method public onUpgrade(Landroid/database/sqlite/SQLiteDatabase;II)V
    .locals 18

    move-object/from16 v6, p0

    move-object/from16 v7, p1

    move/from16 v0, p2

    iput v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    move/from16 v2, p3

    if-ge v1, v2, :cond_54

    invoke-static/range {p2 .. p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-static/range {p3 .. p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v8, "GalleryDBHelper"

    const-string v2, "onUpgrade oldVersion[%d], newVersion[%d]"

    invoke-static {v8, v2, v0, v1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v9, 0x5c

    if-ge v0, v9, :cond_53

    const/16 v1, 0x9

    const-string v2, "DROP TABLE IF EXISTS cloud"

    const/4 v10, 0x0

    const/4 v11, 0x1

    if-ge v0, v1, :cond_0

    const-string v0, "DROP TABLE IF EXISTS album"

    invoke-virtual {v7, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    invoke-virtual {v7, v2}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    invoke-virtual/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->onCreate(Landroid/database/sqlite/SQLiteDatabase;)V

    move v0, v11

    goto :goto_0

    :cond_0
    move v0, v10

    :goto_0
    move v1, v0

    move v3, v1

    iget v4, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v5, 0xe

    const-string v12, "cloudSetting"

    const-string v13, "DROP TABLE IF EXISTS cloudSetting"

    const-string v14, "cloud"

    if-ge v4, v5, :cond_2

    if-nez v0, :cond_1

    invoke-virtual {v7, v2}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    iget-object v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    invoke-direct {v6, v7, v14, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    move v0, v11

    :cond_1
    if-nez v1, :cond_2

    invoke-virtual {v7, v13}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    iget-object v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    invoke-direct {v6, v7, v12, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    move v1, v11

    :cond_2
    iget v4, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v5, 0xf

    if-ge v4, v5, :cond_3

    if-nez v3, :cond_3

    iget-object v3, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    const/16 v4, 0xa

    invoke-virtual {v3, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    const-string v5, "album"

    invoke-static {v7, v5, v3}, Lcom/miui/gallery/provider/GalleryDBHelper;->addColumn(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;)V

    sget-object v3, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v15, 0x3

    new-array v15, v15, [Ljava/lang/Object;

    aput-object v5, v15, v10

    iget-object v5, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mAlbumColumns:Ljava/util/ArrayList;

    invoke-virtual {v5, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;

    iget-object v4, v4, Lcom/miui/gallery/provider/GalleryDBHelper$TableColumn;->mName:Ljava/lang/String;

    aput-object v4, v15, v11

    const/4 v4, 0x2

    invoke-static {v11}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v15, v4

    const-string v4, "UPDATE %s SET %s=%d"

    invoke-static {v3, v4, v15}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v7, v3}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    :cond_3
    iget v3, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x12

    if-ge v3, v4, :cond_5

    const-string v3, "upgrade to 18"

    invoke-static {v8, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget v3, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    if-nez v0, :cond_4

    invoke-virtual {v7, v2}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    iget-object v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudColumns:Ljava/util/ArrayList;

    invoke-direct {v6, v7, v14, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    move v0, v11

    :cond_4
    if-nez v1, :cond_5

    invoke-virtual {v7, v13}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    iget-object v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mCloudSettingColumns:Ljava/util/ArrayList;

    invoke-direct {v6, v7, v12, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createTable(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/ArrayList;)V

    move v12, v0

    move v13, v11

    goto :goto_1

    :cond_5
    move v12, v0

    move v13, v1

    :goto_1
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x13

    if-ge v0, v1, :cond_6

    const-string v0, "upgrade to 19"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    if-nez v12, :cond_6

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_19(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_6
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x14

    if-ge v0, v1, :cond_7

    const-string v0, "upgrade to 20"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    if-nez v12, :cond_7

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnCloudTable_20(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_7
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x15

    if-ge v0, v1, :cond_8

    const-string v0, "upgrade to 21"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v13}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo21(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    move v5, v11

    goto :goto_2

    :cond_8
    move v5, v10

    :goto_2
    move v14, v5

    move v15, v14

    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x16

    if-ge v0, v1, :cond_9

    const-string v0, "upgrade to 22"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v14, v13}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo22(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    move v4, v11

    goto :goto_3

    :cond_9
    move v4, v10

    :goto_3
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x17

    if-ge v0, v1, :cond_a

    const-string v0, "upgrade to 23"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v15, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo23(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_a
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x18

    if-ge v0, v1, :cond_b

    const-string v0, "upgrade to 24"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo24(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_b
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x19

    if-ge v0, v1, :cond_c

    const-string v0, "upgrade to 25"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo25(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_c
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x1a

    if-ge v0, v1, :cond_d

    const-string v0, "upgrade to 26"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo26(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_d
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x1b

    if-ge v0, v1, :cond_e

    const-string v0, "upgrade to 27"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo27(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_e
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x1c

    if-ge v0, v1, :cond_f

    const-string v0, "upgrade to 28"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo28(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_f
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x1d

    if-ge v0, v1, :cond_10

    const-string v0, "upgrade to 29"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo29(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_10
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x1f

    if-ge v0, v1, :cond_11

    const-string v0, "upgrade to 31"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo31(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_11
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x20

    if-ge v0, v1, :cond_12

    const-string v0, "upgrade to 32"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo32(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_12
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x21

    if-ge v0, v1, :cond_13

    const-string v0, "upgrade to 33"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo33(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_13
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x22

    if-ge v0, v1, :cond_14

    const-string v0, "upgrade to 34"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo34(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_14
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x23

    if-ge v0, v1, :cond_15

    const-string v0, "upgrade to 35"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo35(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_15
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x24

    if-ge v0, v1, :cond_16

    const-string v0, "upgrade to 36"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo36(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_16
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x25

    if-ge v0, v1, :cond_17

    const-string v0, "upgrade to 37"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo37(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_17
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x26

    if-ge v0, v1, :cond_18

    const-string v0, "upgrade to 38"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v13}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo38(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_18
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x27

    if-ge v0, v1, :cond_19

    const-string v0, "upgrade to 39"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo39(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_19
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x28

    if-ge v0, v1, :cond_1a

    const-string v0, "upgrade to 40"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo40(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1a
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x29

    if-ge v0, v1, :cond_1b

    const-string v0, "upgrade to 41"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo41(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1b
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x2a

    if-ge v0, v1, :cond_1c

    const-string v0, "upgrade to 42"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo42(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1c
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x2b

    if-ge v0, v1, :cond_1d

    const-string v0, "upgrade to 43"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo43(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_1d
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x2c

    if-ge v0, v1, :cond_1e

    const-string v0, "upgrade to 44"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo44(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1e
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x2d

    if-ge v0, v1, :cond_1f

    const-string v0, "upgrade to 45"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo45(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_1f
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x2e

    if-ge v0, v1, :cond_20

    const-string v0, "upgrade to 46"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v13, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo46(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_20
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x2f

    if-ge v0, v1, :cond_21

    const-string v0, "upgrade to 47"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo47(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    move/from16 v16, v11

    goto :goto_4

    :cond_21
    move/from16 v16, v10

    :goto_4
    move/from16 v17, v16

    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x30

    if-ge v0, v1, :cond_22

    const-string v0, "upgrade to 48"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo48(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_22
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x31

    if-ge v0, v1, :cond_23

    const-string v0, "upgrade to 49"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo49(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_23
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x32

    if-ge v0, v1, :cond_24

    const-string v0, "upgrade to 50"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v13}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo50(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_24
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x33

    if-ge v0, v1, :cond_25

    const-string v0, "upgrade to 51"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo51(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_25
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x34

    if-ge v0, v1, :cond_26

    const-string v0, "upgrade to 52"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move v2, v12

    move v3, v5

    move v10, v4

    move/from16 v4, v16

    move v11, v5

    move/from16 v5, v17

    invoke-direct/range {v0 .. v5}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo52(Landroid/database/sqlite/SQLiteDatabase;ZZZZ)V

    goto :goto_5

    :cond_26
    move v10, v4

    move v11, v5

    :goto_5
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x35

    const-string v2, "upgrade to 53"

    if-ge v0, v1, :cond_27

    invoke-static {v8, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v11}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo53(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_27
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x36

    if-ge v0, v1, :cond_28

    invoke-static {v8, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo54(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_28
    iget v0, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v1, 0x37

    if-ge v0, v1, :cond_29

    const-string v0, "upgrade to 55"

    invoke-static {v8, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo55(Landroid/database/sqlite/SQLiteDatabase;)V

    const/4 v0, 0x1

    goto :goto_6

    :cond_29
    const/4 v0, 0x0

    :goto_6
    iget v2, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x38

    if-ge v2, v3, :cond_2a

    const-string v2, "upgrade to 56"

    invoke-static {v8, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v13}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo56(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_2a
    iget v2, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x39

    if-ge v2, v3, :cond_2b

    const-string v2, "upgrade to 57"

    invoke-static {v8, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->createIndexOnFaceTable(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_2b
    iget v2, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x3a

    if-ge v2, v3, :cond_2c

    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo58(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_2c
    iget v2, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x3b

    if-ge v2, v3, :cond_2d

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo59(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_2d
    iget v2, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    if-lt v2, v1, :cond_2e

    const/16 v1, 0x3c

    if-gt v2, v1, :cond_2e

    const/4 v1, 0x1

    goto :goto_7

    :cond_2e
    const/4 v1, 0x0

    :goto_7
    iget v2, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x3d

    if-ge v2, v3, :cond_2f

    invoke-direct {v6, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo61(Z)V

    :cond_2f
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x3e

    if-ge v1, v2, :cond_30

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo62(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_30
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x3f

    if-ge v1, v2, :cond_31

    const-string v1, "upgrade to 63"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo63(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_31
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x40

    if-ge v1, v2, :cond_32

    invoke-direct {v6, v7, v10, v14}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo64(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_32
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x41

    if-ge v1, v2, :cond_33

    invoke-direct {v6, v7, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo65(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_33
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x42

    if-ge v1, v2, :cond_34

    invoke-direct {v6, v7, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo66(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_34
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x43

    if-ge v1, v2, :cond_35

    invoke-direct {v6, v7, v12, v11}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo67(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_35
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x44

    if-ge v1, v2, :cond_36

    invoke-direct {v6, v7, v10}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo68(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_36
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x45

    if-ge v1, v2, :cond_37

    invoke-direct {v6, v7, v12, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo69(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_37
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x46

    if-ge v1, v2, :cond_38

    invoke-direct {v6, v7, v12, v11}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo70(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_38
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x47

    if-ge v1, v2, :cond_39

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo71(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_39
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x48

    if-ge v1, v2, :cond_3a

    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo72(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_3a
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x49

    if-ge v1, v3, :cond_3b

    const-string v1, "upgrade to 73"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v11, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo73(Landroid/database/sqlite/SQLiteDatabase;ZZZ)V

    :cond_3b
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x4a

    if-ge v1, v4, :cond_3c

    const-string v1, "upgrade to 74"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo74(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_3c
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x4b

    if-ge v1, v4, :cond_3d

    const-string v1, "upgrade to 75"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo75(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_3d
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x4c

    if-ge v1, v4, :cond_3e

    const-string v1, "upgrade to 76"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo76(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_3e
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x4d

    if-ge v1, v4, :cond_3f

    const-string v1, "upgrade to 77"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo77(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_3f
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x4e

    if-ge v1, v4, :cond_41

    const-string v1, "upgrade to 78"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    if-ge v1, v3, :cond_40

    const/4 v1, 0x1

    goto :goto_8

    :cond_40
    const/4 v1, 0x0

    :goto_8
    invoke-direct {v6, v7, v12, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo78(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_41
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x4f

    if-ge v1, v4, :cond_43

    const-string v1, "upgrade to 79"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    if-le v1, v2, :cond_42

    const/4 v1, 0x1

    goto :goto_9

    :cond_42
    const/4 v1, 0x0

    :goto_9
    invoke-direct {v6, v7, v12, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo79(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_43
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x50

    if-ge v1, v4, :cond_45

    const-string v1, "upgrade to 80"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    if-ge v1, v3, :cond_44

    const/4 v1, 0x1

    goto :goto_a

    :cond_44
    const/4 v1, 0x0

    :goto_a
    invoke-direct {v6, v7, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo80(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_45
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x51

    if-ge v1, v4, :cond_46

    const-string v1, "upgrade to 81"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo81(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_46
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x52

    if-ge v1, v4, :cond_47

    const-string v1, "upgrade to 82"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo82(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_47
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v4, 0x53

    if-ge v1, v4, :cond_49

    const-string v1, "upgrade to 83"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    if-le v1, v3, :cond_48

    const/4 v1, 0x1

    goto :goto_b

    :cond_48
    const/4 v1, 0x0

    :goto_b
    invoke-direct {v6, v7, v1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo83(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_49
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x54

    if-ge v1, v3, :cond_4a

    if-le v1, v2, :cond_4a

    const-string v1, "upgrade to 84"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v15}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo84(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_4a
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x55

    if-ge v1, v3, :cond_4b

    const-string v1, "upgrade to 85"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo85(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_4b
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x56

    if-ge v1, v3, :cond_4c

    const-string v1, "upgrade to 86"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v11}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo86(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    :cond_4c
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x57

    if-ge v1, v3, :cond_4d

    const-string v1, "upgrade to 87"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo87(Landroid/database/sqlite/SQLiteDatabase;)V

    :cond_4d
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x58

    if-ge v1, v3, :cond_4e

    const-string v1, "upgrade to 88"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo88(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_4e
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x59

    if-ge v1, v3, :cond_4f

    if-le v1, v2, :cond_4f

    const-string v1, "upgrade to 89"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo89(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_4f
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v2, 0x5a

    if-ge v1, v2, :cond_50

    const-string v1, "upgrade to 90"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo90(Landroid/database/sqlite/SQLiteDatabase;Z)V

    const/4 v1, 0x1

    goto :goto_c

    :cond_50
    const/4 v1, 0x0

    :goto_c
    iget v2, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    const/16 v3, 0x5b

    if-ge v2, v3, :cond_51

    const-string v1, "upgrade to 91"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v12, v11}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo91(Landroid/database/sqlite/SQLiteDatabase;ZZ)V

    const/4 v11, 0x1

    goto :goto_d

    :cond_51
    move v11, v1

    :goto_d
    iget v1, v6, Lcom/miui/gallery/provider/GalleryDBHelper;->mOldVersion:I

    if-ge v1, v9, :cond_52

    const-string v1, "upgrade to 92"

    invoke-static {v8, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->upgradeTo92(Landroid/database/sqlite/SQLiteDatabase;Z)V

    const/4 v11, 0x1

    :cond_52
    if-eqz v11, :cond_53

    const/4 v0, 0x1

    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/provider/GalleryDBHelper;->createViewIfNeeded(Landroid/database/sqlite/SQLiteDatabase;Z)V

    :cond_53
    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->clearColumnList()V

    return-void

    :cond_54
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "database cannot be downgraded"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    .locals 9

    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move-object v6, p6

    move-object/from16 v7, p7

    move-object/from16 v8, p8

    invoke-virtual/range {v0 .. v8}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method public query(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    .locals 10

    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0

    move v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move-object/from16 v6, p6

    move-object/from16 v7, p7

    move-object/from16 v8, p8

    move-object/from16 v9, p9

    invoke-virtual/range {v0 .. v9}, Landroid/database/sqlite/SQLiteDatabase;->query(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method public update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryDBHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0

    invoke-virtual {v0, p1, p2, p3, p4}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result p1

    return p1
.end method
