.class public Lcom/miui/gallery/dao/GalleryLiteEntityManager;
.super Lcom/miui/gallery/dao/base/EntityManager;
.source "GalleryLiteEntityManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/dao/GalleryLiteEntityManager$SingletonHolder;
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 3

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "gallery_lite.db"

    const/4 v2, 0x2

    invoke-direct {p0, v0, v1, v2}, Lcom/miui/gallery/dao/base/EntityManager;-><init>(Landroid/content/Context;Ljava/lang/String;I)V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/dao/GalleryLiteEntityManager$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;
    .locals 1

    sget-object v0, Lcom/miui/gallery/dao/GalleryLiteEntityManager$SingletonHolder;->INSTANCE:Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    return-object v0
.end method


# virtual methods
.method protected onDatabaseDowngrade(Landroid/database/sqlite/SQLiteDatabase;II)V
    .locals 1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    const-string p3, "GalleryLiteEntityManager"

    const-string v0, "onDatabaseDowngrade from %s to %s"

    invoke-static {p3, v0, p1, p2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method

.method protected onDatabaseUpgrade(Landroid/database/sqlite/SQLiteDatabase;II)V
    .locals 1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    const-string p3, "GalleryLiteEntityManager"

    const-string v0, "onDatabaseUpgrade: from %d to %d"

    invoke-static {p3, v0, p1, p2}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method

.method protected onInitTableList()V
    .locals 1

    const-class v0, Lcom/miui/gallery/model/Album;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->addTable(Ljava/lang/Class;)V

    const-class v0, Lcom/miui/gallery/util/face/PeopleItem;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->addTable(Ljava/lang/Class;)V

    const-class v0, Lcom/miui/gallery/model/HomeMedia;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->addTable(Ljava/lang/Class;)V

    const-class v0, Lcom/miui/gallery/model/HomeMediaHeader;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->addTable(Ljava/lang/Class;)V

    return-void
.end method

.method public warmUp()V
    .locals 13

    const-string v0, "GalleryLiteEntityManager"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    const/4 v3, 0x0

    :try_start_0
    const-class v5, Lcom/miui/gallery/model/Album;

    const-string v4, "count(*)"

    filled-new-array {v4}, [Ljava/lang/String;

    move-result-object v6

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    move-object v4, p0

    invoke-virtual/range {v4 .. v12}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->rawQuery(Ljava/lang/Class;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v3

    if-eqz v3, :cond_0

    invoke-interface {v3}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v4

    if-eqz v4, :cond_0

    const/4 v4, 0x0

    invoke-interface {v3, v4}, Landroid/database/Cursor;->getInt(I)I

    move-result v4

    const-string v5, "table Album has %d items"

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v0, v5, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_0
    const-string v4, "warm up costs: %dms"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v5

    sub-long/2addr v5, v1

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-static {v0, v4, v1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    :catch_0
    move-exception v1

    :try_start_1
    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :goto_0
    invoke-static {v3}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-void

    :goto_1
    invoke-static {v3}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw v0
.end method
