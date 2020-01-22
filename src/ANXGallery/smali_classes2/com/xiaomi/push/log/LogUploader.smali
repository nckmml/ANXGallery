.class public Lcom/xiaomi/push/log/LogUploader;
.super Ljava/lang/Object;
.source "LogUploader.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/xiaomi/push/log/LogUploader$CleanUpTask;,
        Lcom/xiaomi/push/log/LogUploader$UploadTask;,
        Lcom/xiaomi/push/log/LogUploader$Task;
    }
.end annotation


# static fields
.field private static volatile sInstance:Lcom/xiaomi/push/log/LogUploader;


# instance fields
.field private mContext:Landroid/content/Context;

.field private final mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentLinkedQueue<",
            "Lcom/xiaomi/push/log/LogUploader$Task;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method private constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/ConcurrentLinkedQueue;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentLinkedQueue;-><init>()V

    iput-object v0, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    iput-object p1, p0, Lcom/xiaomi/push/log/LogUploader;->mContext:Landroid/content/Context;

    iget-object p1, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    new-instance v0, Lcom/xiaomi/push/log/LogUploader$CleanUpTask;

    invoke-direct {v0, p0}, Lcom/xiaomi/push/log/LogUploader$CleanUpTask;-><init>(Lcom/xiaomi/push/log/LogUploader;)V

    invoke-virtual {p1, v0}, Ljava/util/concurrent/ConcurrentLinkedQueue;->add(Ljava/lang/Object;)Z

    const-wide/16 v0, 0x0

    invoke-direct {p0, v0, v1}, Lcom/xiaomi/push/log/LogUploader;->executeTask(J)V

    return-void
.end method

.method static synthetic access$000(Lcom/xiaomi/push/log/LogUploader;)Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/xiaomi/push/log/LogUploader;->mContext:Landroid/content/Context;

    return-object p0
.end method

.method static synthetic access$100(Lcom/xiaomi/push/log/LogUploader;)Ljava/util/concurrent/ConcurrentLinkedQueue;
    .locals 0

    iget-object p0, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    return-object p0
.end method

.method static synthetic access$200(Lcom/xiaomi/push/log/LogUploader;J)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/xiaomi/push/log/LogUploader;->uploadIfNeed(J)V

    return-void
.end method

.method static synthetic access$300(Lcom/xiaomi/push/log/LogUploader;)V
    .locals 0

    invoke-direct {p0}, Lcom/xiaomi/push/log/LogUploader;->cleanUp()V

    return-void
.end method

.method private cleanExpiredTask()V
    .locals 3

    :cond_0
    :goto_0
    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentLinkedQueue;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentLinkedQueue;->peek()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/xiaomi/push/log/LogUploader$Task;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/xiaomi/push/log/LogUploader$Task;->isExpired()Z

    move-result v1

    if-nez v1, :cond_1

    iget-object v1, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentLinkedQueue;->size()I

    move-result v1

    const/4 v2, 0x6

    if-le v1, v2, :cond_2

    :cond_1
    const-string v1, "remove Expired task"

    invoke-static {v1}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    iget-object v1, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/ConcurrentLinkedQueue;->remove(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_2
    return-void
.end method

.method private cleanUp()V
    .locals 4

    invoke-static {}, Lcom/xiaomi/channel/commonutils/file/SDCardUtils;->isSDCardBusy()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/xiaomi/channel/commonutils/file/SDCardUtils;->isSDCardUnavailable()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_1

    :cond_0
    :try_start_0
    new-instance v0, Ljava/io/File;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v2, p0, Lcom/xiaomi/push/log/LogUploader;->mContext:Landroid/content/Context;

    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Landroid/content/Context;->getExternalFilesDir(Ljava/lang/String;)Ljava/io/File;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, "/.logcache"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-virtual {v0}, Ljava/io/File;->isDirectory()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-virtual {v0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_1

    aget-object v3, v0, v2

    invoke-virtual {v3}, Ljava/io/File;->delete()Z
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :catch_0
    :cond_1
    :goto_1
    return-void
.end method

.method private executeTask(J)V
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentLinkedQueue;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    new-instance v0, Lcom/xiaomi/push/log/LogUploader$2;

    invoke-direct {v0, p0}, Lcom/xiaomi/push/log/LogUploader$2;-><init>(Lcom/xiaomi/push/log/LogUploader;)V

    invoke-static {v0, p1, p2}, Lcom/xiaomi/smack/util/TaskExecutor;->execute(Lcom/xiaomi/channel/commonutils/misc/SerializedAsyncTaskProcessor$SerializedAsyncTask;J)V

    :cond_0
    return-void
.end method

.method public static getInstance(Landroid/content/Context;)Lcom/xiaomi/push/log/LogUploader;
    .locals 2

    sget-object v0, Lcom/xiaomi/push/log/LogUploader;->sInstance:Lcom/xiaomi/push/log/LogUploader;

    if-nez v0, :cond_1

    const-class v0, Lcom/xiaomi/push/log/LogUploader;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/xiaomi/push/log/LogUploader;->sInstance:Lcom/xiaomi/push/log/LogUploader;

    if-nez v1, :cond_0

    new-instance v1, Lcom/xiaomi/push/log/LogUploader;

    invoke-direct {v1, p0}, Lcom/xiaomi/push/log/LogUploader;-><init>(Landroid/content/Context;)V

    sput-object v1, Lcom/xiaomi/push/log/LogUploader;->sInstance:Lcom/xiaomi/push/log/LogUploader;

    :cond_0
    monitor-exit v0

    goto :goto_0

    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    :cond_1
    :goto_0
    sget-object v0, Lcom/xiaomi/push/log/LogUploader;->sInstance:Lcom/xiaomi/push/log/LogUploader;

    iput-object p0, v0, Lcom/xiaomi/push/log/LogUploader;->mContext:Landroid/content/Context;

    sget-object p0, Lcom/xiaomi/push/log/LogUploader;->sInstance:Lcom/xiaomi/push/log/LogUploader;

    return-object p0
.end method

.method private uploadIfNeed(J)V
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentLinkedQueue;->peek()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/xiaomi/push/log/LogUploader$Task;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/xiaomi/push/log/LogUploader$Task;->canExcuteNow()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-direct {p0, p1, p2}, Lcom/xiaomi/push/log/LogUploader;->executeTask(J)V

    :cond_0
    return-void
.end method


# virtual methods
.method public checkUpload()V
    .locals 2

    invoke-direct {p0}, Lcom/xiaomi/push/log/LogUploader;->cleanExpiredTask()V

    const-wide/16 v0, 0x0

    invoke-direct {p0, v0, v1}, Lcom/xiaomi/push/log/LogUploader;->uploadIfNeed(J)V

    return-void
.end method

.method public upload(Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/util/Date;IZ)V
    .locals 11

    move-object v8, p0

    iget-object v9, v8, Lcom/xiaomi/push/log/LogUploader;->mTasks:Ljava/util/concurrent/ConcurrentLinkedQueue;

    new-instance v10, Lcom/xiaomi/push/log/LogUploader$1;

    move-object v0, v10

    move-object v1, p0

    move/from16 v2, p5

    move-object v3, p3

    move-object v4, p4

    move-object v5, p1

    move-object v6, p2

    move/from16 v7, p6

    invoke-direct/range {v0 .. v7}, Lcom/xiaomi/push/log/LogUploader$1;-><init>(Lcom/xiaomi/push/log/LogUploader;ILjava/util/Date;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;Z)V

    invoke-virtual {v9, v10}, Ljava/util/concurrent/ConcurrentLinkedQueue;->add(Ljava/lang/Object;)Z

    const-wide/16 v0, 0x0

    invoke-direct {p0, v0, v1}, Lcom/xiaomi/push/log/LogUploader;->executeTask(J)V

    return-void
.end method
