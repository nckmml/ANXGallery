.class public Lcom/miui/gallery/cloud/base/RetryRequestHelper;
.super Ljava/lang/Object;
.source "RetryRequestHelper.java"


# direct methods
.method public static isRetriableErrorCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Z
    .locals 2

    sget-object v0, Lcom/miui/gallery/cloud/base/RetryRequestHelper$1;->$SwitchMap$com$miui$gallery$cloud$base$GallerySyncCode:[I

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/base/GallerySyncCode;->ordinal()I

    move-result p0

    aget p0, v0, p0

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v1, 0x2

    if-eq p0, v1, :cond_0

    const/4 v1, 0x3

    if-eq p0, v1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method

.method public static isRetriableException(Ljava/lang/Exception;)Z
    .locals 1

    instance-of v0, p0, Ljava/net/ConnectException;

    if-nez v0, :cond_1

    instance-of v0, p0, Lorg/apache/http/conn/ConnectTimeoutException;

    if-nez v0, :cond_1

    instance-of v0, p0, Ljava/net/SocketException;

    if-nez v0, :cond_1

    instance-of v0, p0, Ljava/net/SocketTimeoutException;

    if-nez v0, :cond_1

    instance-of v0, p0, Ljava/net/UnknownHostException;

    if-nez v0, :cond_1

    instance-of v0, p0, Ljavax/net/ssl/SSLException;

    if-nez v0, :cond_1

    instance-of p0, p0, Lcom/xiaomi/opensdk/exception/RetriableException;

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public static retryTask(Lcom/miui/gallery/cloud/base/SyncTask;)Lcom/miui/gallery/cloud/base/GallerySyncResult;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lcom/miui/gallery/cloud/base/SyncTask<",
            "TT;>;)",
            "Lcom/miui/gallery/cloud/base/GallerySyncResult<",
            "TT;>;"
        }
    .end annotation

    const-string v0, "RetryRequestHelper"

    const/4 v1, 0x0

    const/4 v2, 0x0

    :goto_0
    const/4 v3, 0x3

    if-ge v1, v3, :cond_6

    :try_start_0
    invoke-interface {p0}, Lcom/miui/gallery/cloud/base/SyncTask;->run()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2

    iget-object v3, v2, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    sget-object v4, Lcom/miui/gallery/cloud/base/GallerySyncCode;->OK:Lcom/miui/gallery/cloud/base/GallerySyncCode;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-ne v3, v4, :cond_1

    if-nez v2, :cond_0

    new-instance v0, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;-><init>()V

    :goto_1
    sget-object v3, Lcom/miui/gallery/cloud/base/GallerySyncCode;->UNKNOWN:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v0, v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v0

    goto :goto_2

    :cond_0
    move-object v0, v2

    :goto_2
    invoke-interface {p0}, Lcom/miui/gallery/cloud/base/SyncTask;->getIdentifier()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0, v1}, Lcom/miui/gallery/cloud/base/RetryRequestHelper;->statResult(Ljava/lang/String;Lcom/miui/gallery/cloud/base/GallerySyncResult;I)V

    return-object v2

    :cond_1
    :try_start_1
    iget-object v3, v2, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-static {v3}, Lcom/miui/gallery/cloud/base/RetryRequestHelper;->isRetriableErrorCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Z

    move-result v3

    if-eqz v3, :cond_3

    const/4 v3, 0x2

    if-ge v1, v3, :cond_3

    iget-wide v3, v2, Lcom/miui/gallery/cloud/base/GallerySyncResult;->retryAfter:J

    const-wide/16 v5, 0x0

    cmp-long v3, v3, v5

    if-lez v3, :cond_2

    const-string v3, "%s retry after %s"

    invoke-interface {p0}, Lcom/miui/gallery/cloud/base/SyncTask;->getIdentifier()Ljava/lang/String;

    move-result-object v4

    iget-wide v5, v2, Lcom/miui/gallery/cloud/base/GallerySyncResult;->retryAfter:J

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-static {v0, v3, v4, v5}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-wide v3, v2, Lcom/miui/gallery/cloud/base/GallerySyncResult;->retryAfter:J

    const-wide/16 v5, 0x1e

    invoke-static {v3, v4, v5, v6}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v3

    const-wide/16 v5, 0x3e8

    mul-long/2addr v3, v5

    invoke-static {v3, v4}, Lcom/miui/gallery/threadpool/ThreadManager;->sleepThread(J)V

    goto :goto_3

    :cond_2
    const-string v3, "%s return %s, retry"

    invoke-interface {p0}, Lcom/miui/gallery/cloud/base/SyncTask;->getIdentifier()Ljava/lang/String;

    move-result-object v4

    iget-object v5, v2, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-static {v0, v3, v4, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_3

    :cond_3
    if-nez v2, :cond_0

    new-instance v0, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;-><init>()V

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_4

    :catch_0
    move-exception v3

    :try_start_2
    invoke-static {v0, v3}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    new-instance v4, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    invoke-direct {v4}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;-><init>()V

    sget-object v5, Lcom/miui/gallery/cloud/base/GallerySyncCode;->UNKNOWN:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v4, v5}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v4

    invoke-virtual {v4, v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setException(Ljava/lang/Exception;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2

    invoke-static {v3}, Lcom/miui/gallery/cloud/base/RetryRequestHelper;->isRetriableException(Ljava/lang/Exception;)Z

    move-result v3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-nez v3, :cond_4

    goto :goto_5

    :cond_4
    :goto_3
    add-int/lit8 v1, v1, 0x1

    goto/16 :goto_0

    :goto_4
    if-nez v2, :cond_5

    new-instance v2, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    invoke-direct {v2}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;-><init>()V

    sget-object v3, Lcom/miui/gallery/cloud/base/GallerySyncCode;->UNKNOWN:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v2, v3}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2

    :cond_5
    invoke-interface {p0}, Lcom/miui/gallery/cloud/base/SyncTask;->getIdentifier()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v2, v1}, Lcom/miui/gallery/cloud/base/RetryRequestHelper;->statResult(Ljava/lang/String;Lcom/miui/gallery/cloud/base/GallerySyncResult;I)V

    throw v0

    :cond_6
    :goto_5
    if-nez v2, :cond_7

    new-instance v0, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;-><init>()V

    sget-object v2, Lcom/miui/gallery/cloud/base/GallerySyncCode;->UNKNOWN:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->setCode(Lcom/miui/gallery/cloud/base/GallerySyncCode;)Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloud/base/GallerySyncResult$Builder;->build()Lcom/miui/gallery/cloud/base/GallerySyncResult;

    move-result-object v2

    :cond_7
    invoke-interface {p0}, Lcom/miui/gallery/cloud/base/SyncTask;->getIdentifier()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v2, v1}, Lcom/miui/gallery/cloud/base/RetryRequestHelper;->statResult(Ljava/lang/String;Lcom/miui/gallery/cloud/base/GallerySyncResult;I)V

    return-object v2
.end method

.method private static statResult(Ljava/lang/String;Lcom/miui/gallery/cloud/base/GallerySyncResult;I)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const-string v1, "request"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object p0, p1, Lcom/miui/gallery/cloud/base/GallerySyncResult;->code:Lcom/miui/gallery/cloud/base/GallerySyncCode;

    invoke-virtual {p0}, Lcom/miui/gallery/cloud/base/GallerySyncCode;->name()Ljava/lang/String;

    move-result-object p0

    const-string v1, "code"

    invoke-virtual {v0, v1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object p0, p1, Lcom/miui/gallery/cloud/base/GallerySyncResult;->exception:Ljava/lang/Exception;

    if-eqz p0, :cond_0

    iget-object p0, p1, Lcom/miui/gallery/cloud/base/GallerySyncResult;->exception:Ljava/lang/Exception;

    invoke-virtual {p0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p0

    const-string p1, "exception"

    invoke-virtual {v0, p1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_0
    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    const-string p1, "retryTimes"

    invoke-virtual {v0, p1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p0, "Sync"

    const-string p1, "sync_request_result"

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method
