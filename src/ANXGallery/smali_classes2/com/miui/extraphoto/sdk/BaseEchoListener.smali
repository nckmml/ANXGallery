.class public abstract Lcom/miui/extraphoto/sdk/BaseEchoListener;
.super Lcom/miui/extraphoto/sdk/IEchoListener$Stub;
.source "BaseEchoListener.java"


# static fields
.field private static final UPDATE_PROJECTION:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const-string v0, "*"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/extraphoto/sdk/BaseEchoListener;->UPDATE_PROJECTION:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/extraphoto/sdk/IEchoListener$Stub;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/extraphoto/sdk/BaseEchoListener;I)Z
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/extraphoto/sdk/BaseEchoListener;->isDeleted(I)Z

    move-result p0

    return p0
.end method

.method static synthetic access$100(Lcom/miui/extraphoto/sdk/BaseEchoListener;Landroid/database/Cursor;ILandroid/content/ContentValues;)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/extraphoto/sdk/BaseEchoListener;->putValue(Landroid/database/Cursor;ILandroid/content/ContentValues;)V

    return-void
.end method

.method private isDeleted(I)Z
    .locals 1

    const/4 v0, -0x1

    if-eq p1, v0, :cond_1

    const/4 v0, 0x2

    if-eq p1, v0, :cond_1

    const/4 v0, 0x5

    if-ne p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    return p1
.end method

.method private putValue(Landroid/database/Cursor;ILandroid/content/ContentValues;)V
    .locals 2

    invoke-interface {p1, p2}, Landroid/database/Cursor;->getType(I)I

    move-result v0

    const/4 v1, 0x1

    if-eq v0, v1, :cond_3

    const/4 v1, 0x2

    if-eq v0, v1, :cond_2

    const/4 v1, 0x3

    if-eq v0, v1, :cond_1

    const/4 v1, 0x4

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    invoke-interface {p1, p2}, Landroid/database/Cursor;->getColumnName(I)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, p2}, Landroid/database/Cursor;->getBlob(I)[B

    move-result-object p1

    invoke-virtual {p3, v0, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;[B)V

    goto :goto_0

    :cond_1
    invoke-interface {p1, p2}, Landroid/database/Cursor;->getColumnName(I)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, p2}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p3, v0, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    :cond_2
    invoke-interface {p1, p2}, Landroid/database/Cursor;->getColumnName(I)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, p2}, Landroid/database/Cursor;->getDouble(I)D

    move-result-wide p1

    invoke-static {p1, p2}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p1

    invoke-virtual {p3, v0, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Double;)V

    goto :goto_0

    :cond_3
    invoke-interface {p1, p2}, Landroid/database/Cursor;->getColumnName(I)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, p2}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p3, v0, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method private updateDataBase(Ljava/lang/String;)Ljava/lang/String;
    .locals 7

    new-instance v6, Lcom/miui/extraphoto/sdk/BaseEchoListener$3;

    invoke-direct {v6, p0, p1}, Lcom/miui/extraphoto/sdk/BaseEchoListener$3;-><init>(Lcom/miui/extraphoto/sdk/BaseEchoListener;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lcom/miui/gallery/provider/GalleryContract$Cloud;->CLOUD_URI:Landroid/net/Uri;

    sget-object v2, Lcom/miui/extraphoto/sdk/BaseEchoListener;->UPDATE_PROJECTION:[Ljava/lang/String;

    const/4 v3, 0x1

    new-array v4, v3, [Ljava/lang/String;

    const/4 v3, 0x0

    aput-object p1, v4, v3

    const-string v3, "localFile like ?"

    const/4 v5, 0x0

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    return-object p1
.end method


# virtual methods
.method public final onEchoEnd(Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 8
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    const-string v0, "BaseEchoListener"

    const/4 v1, 0x0

    if-eqz p3, :cond_2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    new-instance v4, Ljava/io/File;

    invoke-direct {v4, p2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    new-instance v5, Ljava/io/File;

    invoke-direct {v5, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v4, v5}, Lcom/miui/gallery/util/FileUtils;->move(Ljava/io/File;Ljava/io/File;)Z

    move-result v4

    const/4 v5, 0x0

    if-eqz v4, :cond_1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    sub-long/2addr v6, v2

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "move file cost %d"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-direct {p0, p1}, Lcom/miui/extraphoto/sdk/BaseEchoListener;->updateDataBase(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    sub-long/2addr v3, v1

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "update dataBase cost: %d"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    const-string v1, "update db success %s, %s"

    invoke-static {v0, v1, p1, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_0

    :cond_0
    move p3, v5

    :goto_0
    move-object v1, p1

    goto :goto_1

    :cond_1
    const-string p3, "remove file fail %s, %s"

    invoke-static {v0, p3, p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    move p3, v5

    goto :goto_1

    :cond_2
    const-string v2, "echo file fail %s, %s"

    invoke-static {v0, v2, p1, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    :goto_1
    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMainHandler()Lcom/android/internal/CompatHandler;

    move-result-object p1

    new-instance p2, Lcom/miui/extraphoto/sdk/BaseEchoListener$2;

    invoke-direct {p2, p0, v1, p3}, Lcom/miui/extraphoto/sdk/BaseEchoListener$2;-><init>(Lcom/miui/extraphoto/sdk/BaseEchoListener;Ljava/lang/String;Z)V

    invoke-virtual {p1, p2}, Lcom/android/internal/CompatHandler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final onEchoStart()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMainHandler()Lcom/android/internal/CompatHandler;

    move-result-object v0

    new-instance v1, Lcom/miui/extraphoto/sdk/BaseEchoListener$1;

    invoke-direct {v1, p0}, Lcom/miui/extraphoto/sdk/BaseEchoListener$1;-><init>(Lcom/miui/extraphoto/sdk/BaseEchoListener;)V

    invoke-virtual {v0, v1}, Lcom/android/internal/CompatHandler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public abstract onEnd(Ljava/lang/String;Z)V
.end method

.method public abstract onStart()V
.end method
