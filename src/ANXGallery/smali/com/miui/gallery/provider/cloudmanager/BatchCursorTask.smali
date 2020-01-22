.class abstract Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;
.super Lcom/miui/gallery/provider/cloudmanager/BatchTask;
.source "BatchCursorTask.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchItemData;,
        Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        ">",
        "Lcom/miui/gallery/provider/cloudmanager/BatchTask<",
        "TK;",
        "Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData<",
        "TK;>;>;"
    }
.end annotation


# instance fields
.field private mContext:Landroid/content/Context;

.field private mDirtyBulk:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/util/ArrayList;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchTask;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->mContext:Landroid/content/Context;

    iput-object p2, p0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->mDirtyBulk:Ljava/util/ArrayList;

    return-void
.end method


# virtual methods
.method protected genBatchItemData()Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchItemData;
    .locals 1

    new-instance v0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchItemData;

    invoke-direct {v0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchItemData;-><init>()V

    return-object v0
.end method

.method protected genBatchOperationData([Ljava/lang/Object;)Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([TK;)",
            "Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData<",
            "TK;>;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;

    invoke-direct {v0, p1}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;-><init>([Ljava/lang/Object;)V

    return-object v0
.end method

.method protected getBatchCount()I
    .locals 1

    const/16 v0, 0x64

    return v0
.end method

.method public getContext()Landroid/content/Context;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method public getDirtyBulk()Ljava/util/ArrayList;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->mDirtyBulk:Ljava/util/ArrayList;

    return-object v0
.end method

.method protected final markAsDirty(J)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->mDirtyBulk:Ljava/util/ArrayList;

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method protected final markAsDirty(Ljava/util/Collection;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "Ljava/lang/Long;",
            ">;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->mDirtyBulk:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    return-void
.end method

.method protected prepareBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;)Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/database/sqlite/SQLiteDatabase;",
            "Lcom/miui/gallery/provider/cache/MediaManager;",
            "Landroid/os/Bundle;",
            ")",
            "Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData<",
            "TK;>;"
        }
    .end annotation

    invoke-virtual {p0, p3}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->getBatchExecuteKeys(Landroid/os/Bundle;)[Ljava/lang/Object;

    move-result-object p2

    invoke-virtual {p0, p2}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->genBatchOperationData([Ljava/lang/Object;)Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;

    move-result-object p3

    array-length v0, p2

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_0

    aget-object v2, p2, v1

    iget-object v3, p3, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->keyItemDataMap:Landroid/util/ArrayMap;

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->genBatchItemData()Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchItemData;

    move-result-object v4

    invoke-virtual {v3, v2, v4}, Landroid/util/ArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :try_start_0
    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->queryCursor(Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/Object;)Landroid/database/Cursor;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception p1

    const-string p2, "CloudManager.BatchCursorTask"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_1
    iput-object v0, p3, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->cursor:Landroid/database/Cursor;

    return-object p3
.end method

.method protected abstract queryCursor(Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/Object;)Landroid/database/Cursor;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/database/sqlite/SQLiteDatabase;",
            "[TK;)",
            "Landroid/database/Cursor;"
        }
    .end annotation
.end method

.method protected release()V
    .locals 1

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->mContext:Landroid/content/Context;

    return-void
.end method

.method protected releaseBatchBundle(Landroid/os/Bundle;)V
    .locals 0

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/os/Bundle;->clear()V

    :cond_0
    return-void
.end method

.method public run(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;)[J
    .locals 13

    const-string v0, "%s finish"

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->getTotalCount()I

    move-result v1

    new-array v1, v1, [J

    const-wide/16 v2, -0x65

    invoke-static {v1, v2, v3}, Ljava/util/Arrays;->fill([JJ)V

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    const-string v3, "CloudManager.BatchCursorTask"

    const-string v4, "%s is running"

    invoke-static {v3, v4, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :try_start_0
    new-instance v2, Landroid/os/Bundle;

    invoke-direct {v2}, Landroid/os/Bundle;-><init>()V

    const/4 v4, 0x0

    move-object v5, v2

    move v2, v4

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->getTotalCount()I

    move-result v6

    if-ge v2, v6, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->getTotalCount()I

    move-result v6

    sub-int/2addr v6, v2

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->getBatchCount()I

    move-result v7

    invoke-static {v6, v7}, Ljava/lang/Math;->min(II)I

    move-result v6
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    add-int v7, v2, v6

    :try_start_1
    invoke-virtual {p0, v2, v6, v5}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->getBatchBundle(IILandroid/os/Bundle;)Landroid/os/Bundle;

    move-result-object v5

    invoke-virtual {p0, p1, p2, v5}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->runBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;)[J

    move-result-object v8

    array-length v9, v8

    if-eq v9, v6, :cond_0

    const-string v9, "%s, Invalid batch result, expecting %d results, but actually is %d"

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v11

    array-length v12, v8

    invoke-static {v12}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v12

    invoke-static {v3, v9, v10, v11, v12}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_0
    move v9, v4

    :goto_1
    array-length v10, v8

    if-ge v9, v10, :cond_1

    if-ge v9, v6, :cond_1

    add-int v10, v2, v9

    aget-wide v11, v8, v9

    aput-wide v11, v1, v10
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    add-int/lit8 v9, v9, 0x1

    goto :goto_1

    :cond_1
    :try_start_2
    invoke-virtual {p0, v5}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->releaseBatchBundle(Landroid/os/Bundle;)V

    move v2, v7

    goto :goto_0

    :catchall_0
    move-exception p1

    invoke-virtual {p0, v5}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->releaseBatchBundle(Landroid/os/Bundle;)V

    throw p1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    :cond_2
    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v3, v0, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->release()V

    return-object v1

    :catchall_1
    move-exception p1

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {v3, v0, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->release()V

    throw p1
.end method

.method protected runBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;)[J
    .locals 4

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p3}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->describeBundle(Landroid/os/Bundle;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "CloudManager.BatchCursorTask"

    const-string v3, "%s run batch for bundle %s"

    invoke-static {v2, v3, v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-virtual {p0, p3}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->getBatchExecuteKeys(Landroid/os/Bundle;)[Ljava/lang/Object;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [J

    const/4 v1, 0x0

    :try_start_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->prepareBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;)Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;

    move-result-object v1

    invoke-virtual {p0, p1, p2, p3, v1}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->verifyBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;)V

    invoke-virtual {v1}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->isAllInvalid()Z

    move-result v3

    if-nez v3, :cond_0

    invoke-virtual {p0, p1, p2, p3, v1}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->executeBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;Ljava/lang/Object;)V

    :cond_0
    invoke-virtual {v1, v0}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->copyResultsTo([J)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_1

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    :catch_0
    move-exception p1

    :try_start_1
    invoke-static {v2, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    const-wide/16 p1, -0x65

    invoke-static {v0, p1, p2}, Ljava/util/Arrays;->fill([JJ)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v1, :cond_1

    :goto_0
    invoke-virtual {v1}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->release()V

    :cond_1
    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p3}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask;->describeBundle(Landroid/os/Bundle;)Ljava/lang/String;

    move-result-object p2

    const-string p3, "%s done run batch for bundle %s"

    invoke-static {v2, p3, p1, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v0

    :goto_1
    if-eqz v1, :cond_2

    invoke-virtual {v1}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->release()V

    :cond_2
    throw p1
.end method

.method protected verifyBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/database/sqlite/SQLiteDatabase;",
            "Lcom/miui/gallery/provider/cache/MediaManager;",
            "Landroid/os/Bundle;",
            "Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData<",
            "TK;>;)V"
        }
    .end annotation

    iget-object p1, p4, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->cursor:Landroid/database/Cursor;

    const-string p2, "CloudManager.BatchCursorTask"

    if-nez p1, :cond_0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p3, "cursor for %s is null, abort"

    invoke-static {p2, p3, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-wide/16 p1, -0x65

    invoke-virtual {p4, p1, p2}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->fillResult(J)V

    goto :goto_0

    :cond_0
    iget-object p1, p4, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->cursor:Landroid/database/Cursor;

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result p1

    if-gtz p1, :cond_1

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p3, "cursor for %s has nothing, abort"

    invoke-static {p2, p3, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-wide/16 p1, -0x66

    invoke-virtual {p4, p1, p2}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->fillResult(J)V

    goto :goto_0

    :cond_1
    const-wide/16 p1, -0x1

    invoke-virtual {p4, p1, p2}, Lcom/miui/gallery/provider/cloudmanager/BatchCursorTask$BatchOperationData;->fillResult(J)V

    :goto_0
    return-void
.end method
