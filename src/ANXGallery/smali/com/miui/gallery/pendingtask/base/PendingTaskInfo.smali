.class public Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;
.super Lcom/miui/gallery/dao/base/Entity;
.source "PendingTaskInfo.java"


# instance fields
.field private mCreateTime:J

.field private mData:[B

.field private mExpireTime:J

.field private mMinLatency:J

.field private mNetType:I

.field private mRequireCharging:Z

.field private mRequireDeviceIdle:Z

.field private mRetryTime:I

.field private mTaskTag:Ljava/lang/String;

.field private mTaskType:I


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mNetType:I

    return-void
.end method


# virtual methods
.method public getCreateTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mCreateTime:J

    return-wide v0
.end method

.method public getData()[B
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mData:[B

    return-object v0
.end method

.method public getExpireTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mExpireTime:J

    return-wide v0
.end method

.method public getMinLatencyMillis()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mMinLatency:J

    return-wide v0
.end method

.method public getNetType()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mNetType:I

    return v0
.end method

.method public getRetryTime()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRetryTime:I

    return v0
.end method

.method protected final getTableColumns()Ljava/util/List;
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

    const-string v2, "taskType"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "taskTag"

    const-string v3, "TEXT"

    invoke-static {v0, v2, v3}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "netType"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "charging"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "deviceIdle"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "data"

    const-string v3, "BLOB"

    invoke-static {v0, v2, v3}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "createTime"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const/4 v2, 0x0

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    const-string v3, "expireTime"

    invoke-static {v0, v3, v1, v2}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "retryTime"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    return-object v0
.end method

.method public getTaskType()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mTaskType:I

    return v0
.end method

.method public increaseRetryTime()V
    .locals 1

    iget v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRetryTime:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRetryTime:I

    return-void
.end method

.method public isRequireCharging()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireCharging:Z

    return v0
.end method

.method public isRequireDeviceIdle()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireDeviceIdle:Z

    return v0
.end method

.method protected final onConvertToContents(Landroid/content/ContentValues;)V
    .locals 2

    iget v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mTaskType:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "taskType"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-object v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mTaskTag:Ljava/lang/String;

    const-string v1, "taskTag"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mNetType:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "netType"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-boolean v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireCharging:Z

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "charging"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-boolean v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireDeviceIdle:Z

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "deviceIdle"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-object v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mData:[B

    const-string v1, "data"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;[B)V

    iget-wide v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mCreateTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "createTime"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mExpireTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "expireTime"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRetryTime:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "retryTime"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    return-void
.end method

.method protected final onInitFromCursor(Landroid/database/Cursor;)V
    .locals 3

    const-string v0, "taskType"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mTaskType:I

    const-string v0, "taskTag"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mTaskTag:Ljava/lang/String;

    const-string v0, "netType"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mNetType:I

    const-string v0, "charging"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_0

    move v0, v2

    goto :goto_0

    :cond_0
    move v0, v1

    :goto_0
    iput-boolean v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireCharging:Z

    const-string v0, "deviceIdle"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    if-ne v0, v2, :cond_1

    move v1, v2

    :cond_1
    iput-boolean v1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireDeviceIdle:Z

    const-string v0, "data"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getBlob(Landroid/database/Cursor;Ljava/lang/String;)[B

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mData:[B

    const-string v0, "createTime"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mCreateTime:J

    const-string v0, "expireTime"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mExpireTime:J

    const-string v0, "retryTime"

    invoke-static {p1, v0}, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRetryTime:I

    return-void
.end method

.method public setCreateTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mCreateTime:J

    return-void
.end method

.method public setData([B)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mData:[B

    return-void
.end method

.method public setExpireTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mExpireTime:J

    return-void
.end method

.method public setMinLatencyMillis(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mMinLatency:J

    return-void
.end method

.method public setNetType(I)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mNetType:I

    return-void
.end method

.method public setRequireCharging(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireCharging:Z

    return-void
.end method

.method public setRequireDeviceIdle(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mRequireDeviceIdle:Z

    return-void
.end method

.method public setTaskTag(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mTaskTag:Ljava/lang/String;

    return-void
.end method

.method public setTaskType(I)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/pendingtask/base/PendingTaskInfo;->mTaskType:I

    return-void
.end method
