.class public Lcom/xiaomi/smack/util/TrafficUtils;
.super Ljava/lang/Object;
.source "TrafficUtils.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;
    }
.end annotation


# static fields
.field private static dbHelper:Lcom/xiaomi/push/providers/TrafficDatabaseHelper;

.field private static imsi:Ljava/lang/String;

.field private static lastRxTs:J

.field private static final lock:Ljava/lang/Object;

.field private static mAsyncProcessor:Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor;

.field private static volatile networkType:I

.field private static trafficList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor;-><init>(Z)V

    sput-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->mAsyncProcessor:Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor;

    const/4 v0, -0x1

    sput v0, Lcom/xiaomi/smack/util/TrafficUtils;->networkType:I

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    sput-wide v0, Lcom/xiaomi/smack/util/TrafficUtils;->lastRxTs:J

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->lock:Ljava/lang/Object;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-static {v0}, Ljava/util/Collections;->synchronizedList(Ljava/util/List;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->trafficList:Ljava/util/List;

    const-string v0, ""

    sput-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->imsi:Ljava/lang/String;

    const/4 v0, 0x0

    sput-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->dbHelper:Lcom/xiaomi/push/providers/TrafficDatabaseHelper;

    return-void
.end method

.method static synthetic access$000()Ljava/lang/Object;
    .locals 1

    sget-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->lock:Ljava/lang/Object;

    return-object v0
.end method

.method static synthetic access$100()Ljava/util/List;
    .locals 1

    sget-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->trafficList:Ljava/util/List;

    return-object v0
.end method

.method static synthetic access$200(Landroid/content/Context;Ljava/util/List;)V
    .locals 0

    invoke-static {p0, p1}, Lcom/xiaomi/smack/util/TrafficUtils;->insertTraffic(Landroid/content/Context;Ljava/util/List;)V

    return-void
.end method

.method public static distributionTraffic(Landroid/content/Context;Ljava/lang/String;JZZJ)V
    .locals 8

    invoke-static {p0}, Lcom/xiaomi/smack/util/TrafficUtils;->getNetworkType(Landroid/content/Context;)I

    move-result v0

    move-wide v1, p2

    move v3, p4

    move-wide v4, p6

    move v6, p5

    invoke-static/range {v0 .. v6}, Lcom/xiaomi/smack/util/TrafficUtils;->getTraffic(IJZJZ)J

    move-result-wide v3

    move-object v1, p0

    move-object v2, p1

    move v5, p4

    move-wide v6, p6

    invoke-static/range {v1 .. v7}, Lcom/xiaomi/smack/util/TrafficUtils;->saveTraffic(Landroid/content/Context;Ljava/lang/String;JZJ)V

    return-void
.end method

.method private static getActiveNetworkType(Landroid/content/Context;)I
    .locals 2

    const/4 v0, -0x1

    :try_start_0
    const-string v1, "connectivity"

    invoke-virtual {p0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/net/ConnectivityManager;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    if-nez p0, :cond_0

    return v0

    :cond_0
    :try_start_1
    invoke-virtual {p0}, Landroid/net/ConnectivityManager;->getActiveNetworkInfo()Landroid/net/NetworkInfo;

    move-result-object p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    if-nez p0, :cond_1

    return v0

    :cond_1
    invoke-virtual {p0}, Landroid/net/NetworkInfo;->getType()I

    move-result p0

    return p0

    :catch_0
    return v0
.end method

.method private static declared-synchronized getIMSI(Landroid/content/Context;)Ljava/lang/String;
    .locals 1

    const-class p0, Lcom/xiaomi/smack/util/TrafficUtils;

    monitor-enter p0

    :try_start_0
    sget-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->imsi:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    sget-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->imsi:Ljava/lang/String;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :cond_0
    :try_start_1
    const-string v0, ""
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public static getNetworkType(Landroid/content/Context;)I
    .locals 2

    sget v0, Lcom/xiaomi/smack/util/TrafficUtils;->networkType:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    invoke-static {p0}, Lcom/xiaomi/smack/util/TrafficUtils;->getActiveNetworkType(Landroid/content/Context;)I

    move-result p0

    sput p0, Lcom/xiaomi/smack/util/TrafficUtils;->networkType:I

    :cond_0
    sget p0, Lcom/xiaomi/smack/util/TrafficUtils;->networkType:I

    return p0
.end method

.method private static getTraffic(IJZJZ)J
    .locals 2

    if-eqz p3, :cond_0

    if-eqz p6, :cond_0

    sget-wide v0, Lcom/xiaomi/smack/util/TrafficUtils;->lastRxTs:J

    sput-wide p4, Lcom/xiaomi/smack/util/TrafficUtils;->lastRxTs:J

    sub-long/2addr p4, v0

    const-wide/16 v0, 0x7530

    cmp-long p3, p4, v0

    if-lez p3, :cond_0

    const-wide/16 p3, 0x400

    cmp-long p3, p1, p3

    if-lez p3, :cond_0

    const-wide/16 p3, 0x2

    mul-long/2addr p1, p3

    return-wide p1

    :cond_0
    if-nez p0, :cond_1

    const/16 p0, 0xd

    goto :goto_0

    :cond_1
    const/16 p0, 0xb

    :goto_0
    int-to-long p3, p0

    mul-long/2addr p1, p3

    const-wide/16 p3, 0xa

    div-long/2addr p1, p3

    return-wide p1
.end method

.method private static getTrafficDatabaseHelper(Landroid/content/Context;)Lcom/xiaomi/push/providers/TrafficDatabaseHelper;
    .locals 1

    sget-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->dbHelper:Lcom/xiaomi/push/providers/TrafficDatabaseHelper;

    if-eqz v0, :cond_0

    return-object v0

    :cond_0
    new-instance v0, Lcom/xiaomi/push/providers/TrafficDatabaseHelper;

    invoke-direct {v0, p0}, Lcom/xiaomi/push/providers/TrafficDatabaseHelper;-><init>(Landroid/content/Context;)V

    sput-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->dbHelper:Lcom/xiaomi/push/providers/TrafficDatabaseHelper;

    sget-object p0, Lcom/xiaomi/smack/util/TrafficUtils;->dbHelper:Lcom/xiaomi/push/providers/TrafficDatabaseHelper;

    return-object p0
.end method

.method public static getTrafficFlow(Ljava/lang/String;)I
    .locals 1

    :try_start_0
    const-string v0, "UTF-8"

    invoke-virtual {p0, v0}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v0

    array-length p0, v0
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    :catch_0
    invoke-virtual {p0}, Ljava/lang/String;->getBytes()[B

    move-result-object p0

    array-length p0, p0

    return p0
.end method

.method private static insertTraffic(Landroid/content/Context;Ljava/util/List;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/List<",
            "Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;",
            ">;)V"
        }
    .end annotation

    :try_start_0
    sget-object v0, Lcom/xiaomi/push/providers/TrafficDatabaseHelper;->DataBaseLock:Ljava/lang/Object;

    monitor-enter v0
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0

    :try_start_1
    invoke-static {p0}, Lcom/xiaomi/smack/util/TrafficUtils;->getTrafficDatabaseHelper(Landroid/content/Context;)Lcom/xiaomi/push/providers/TrafficDatabaseHelper;

    move-result-object p0

    invoke-virtual {p0}, Lcom/xiaomi/push/providers/TrafficDatabaseHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object p0

    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :try_start_2
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;

    new-instance v2, Landroid/content/ContentValues;

    invoke-direct {v2}, Landroid/content/ContentValues;-><init>()V

    const-string v3, "package_name"

    iget-object v4, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->packageName:Ljava/lang/String;

    invoke-virtual {v2, v3, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "message_ts"

    iget-wide v4, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->messageTs:J

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v2, v3, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    const-string v3, "network_type"

    iget v4, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->networkType:I

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v2, v3, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string v3, "bytes"

    iget-wide v4, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->bytes:J

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v2, v3, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    const-string v3, "rcv"

    iget v4, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->rcv:I

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v2, v3, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string v3, "imsi"

    iget-object v1, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->imsi:Ljava/lang/String;

    invoke-virtual {v2, v3, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    const-string v1, "traffic"

    const/4 v3, 0x0

    invoke-virtual {p0, v1, v3, v2}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    monitor-exit v0

    goto :goto_1

    :catchall_0
    move-exception p1

    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    throw p1

    :catchall_1
    move-exception p0

    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :try_start_4
    throw p0
    :try_end_4
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_4 .. :try_end_4} :catch_0

    :catch_0
    move-exception p0

    invoke-static {p0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->e(Ljava/lang/Throwable;)V

    :goto_1
    return-void
.end method

.method private static insertTrafficInfo2List(Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;)V
    .locals 6

    sget-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->trafficList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;

    invoke-virtual {v1, p0}, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->canAccumulate(Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;)Z

    move-result v2

    if-eqz v2, :cond_0

    iget-wide v2, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->bytes:J

    iget-wide v4, p0, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->bytes:J

    add-long/2addr v2, v4

    iput-wide v2, v1, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;->bytes:J

    return-void

    :cond_1
    sget-object v0, Lcom/xiaomi/smack/util/TrafficUtils;->trafficList:Ljava/util/List;

    invoke-interface {v0, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public static notifyNetworkChanage(Landroid/content/Context;)V
    .locals 0

    invoke-static {p0}, Lcom/xiaomi/smack/util/TrafficUtils;->getActiveNetworkType(Landroid/content/Context;)I

    move-result p0

    sput p0, Lcom/xiaomi/smack/util/TrafficUtils;->networkType:I

    return-void
.end method

.method private static saveTraffic(Landroid/content/Context;Ljava/lang/String;JZJ)V
    .locals 13

    move-object v0, p0

    if-eqz v0, :cond_4

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_4

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    const-string v2, "com.xiaomi.xmsf"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    const-string v1, "com.xiaomi.xmsf"

    move-object v3, p1

    invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_1

    :cond_0
    invoke-static {p0}, Lcom/xiaomi/smack/util/TrafficUtils;->getNetworkType(Landroid/content/Context;)I

    move-result v6

    const/4 v1, -0x1

    if-ne v1, v6, :cond_1

    return-void

    :cond_1
    sget-object v1, Lcom/xiaomi/smack/util/TrafficUtils;->lock:Ljava/lang/Object;

    monitor-enter v1

    :try_start_0
    sget-object v2, Lcom/xiaomi/smack/util/TrafficUtils;->trafficList:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v11

    new-instance v12, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;

    if-nez v6, :cond_2

    invoke-static {p0}, Lcom/xiaomi/smack/util/TrafficUtils;->getIMSI(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v2

    goto :goto_0

    :cond_2
    const-string v2, ""

    :goto_0
    move-object v8, v2

    move-object v2, v12

    move-object v3, p1

    move-wide/from16 v4, p5

    move/from16 v7, p4

    move-wide v9, p2

    invoke-direct/range {v2 .. v10}, Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;-><init>(Ljava/lang/String;JIILjava/lang/String;J)V

    invoke-static {v12}, Lcom/xiaomi/smack/util/TrafficUtils;->insertTrafficInfo2List(Lcom/xiaomi/smack/util/TrafficUtils$TrafficInfo;)V

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v11, :cond_3

    sget-object v1, Lcom/xiaomi/smack/util/TrafficUtils;->mAsyncProcessor:Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor;

    new-instance v2, Lcom/xiaomi/smack/util/TrafficUtils$1;

    invoke-direct {v2, p0}, Lcom/xiaomi/smack/util/TrafficUtils$1;-><init>(Landroid/content/Context;)V

    const-wide/16 v3, 0x1388

    invoke-virtual {v1, v2, v3, v4}, Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor;->addNewTaskWithDelayed(Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor$SerializedAsyncTask;J)V

    :cond_3
    return-void

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0

    :cond_4
    :goto_1
    return-void
.end method
