.class public Lcn/kuaipan/android/kss/download/KssDownloader;
.super Ljava/lang/Object;
.source "KssDownloader.java"

# interfaces
.implements Lcn/kuaipan/android/kss/KssDef;


# instance fields
.field private final mTransmitter:Lcn/kuaipan/android/http/KscHttpTransmitter;


# direct methods
.method public constructor <init>(Lcn/kuaipan/android/http/KscHttpTransmitter;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcn/kuaipan/android/kss/download/KssDownloader;->mTransmitter:Lcn/kuaipan/android/http/KscHttpTransmitter;

    return-void
.end method

.method private download(Lcn/kuaipan/android/kss/IKssDownloadRequestResult;Lcn/kuaipan/android/kss/download/KssAccessor;Lcn/kuaipan/android/kss/download/LoadMap;Lcn/kuaipan/android/http/KssTransferStopper;Ljava/util/concurrent/atomic/AtomicInteger;)V
    .locals 30
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/InterruptedException;,
            Ljava/security/InvalidKeyException;,
            Lcn/kuaipan/android/exception/KscException;
        }
    .end annotation

    move-object/from16 v1, p0

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    invoke-virtual/range {p3 .. p3}, Lcn/kuaipan/android/kss/download/LoadMap;->obtainRecorder()Lcn/kuaipan/android/kss/download/LoadRecorder;

    move-result-object v0

    move-object v4, v0

    :goto_0
    if-eqz v4, :cond_10

    invoke-static {}, Ljava/lang/Thread;->interrupted()Z

    move-result v0

    if-nez v0, :cond_f

    invoke-virtual {v4}, Lcn/kuaipan/android/kss/download/LoadRecorder;->getSpace()Lcn/kuaipan/android/kss/download/LoadMap$Space;

    move-result-object v0

    invoke-virtual {v0}, Lcn/kuaipan/android/kss/download/LoadMap$Space;->getStart()J

    move-result-wide v5

    move-object/from16 v7, p1

    invoke-interface {v7, v5, v6}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getBlockUrls(J)[Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v3, v5, v6}, Lcn/kuaipan/android/kss/download/LoadMap;->getBlockStart(J)J

    move-result-wide v9

    sub-long/2addr v5, v9

    if-eqz v8, :cond_e

    array-length v0, v8

    if-lez v0, :cond_e

    new-instance v9, Lcn/kuaipan/android/kss/RC4Encoder;

    invoke-interface/range {p1 .. p1}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getSecureKey()[B

    move-result-object v0

    invoke-direct {v9, v0}, Lcn/kuaipan/android/kss/RC4Encoder;-><init>([B)V

    const/4 v0, 0x0

    move v10, v0

    :goto_1
    array-length v0, v8

    if-ge v10, v0, :cond_c

    invoke-static {}, Ljava/lang/Thread;->interrupted()Z

    move-result v0

    if-nez v0, :cond_b

    const/4 v12, 0x0

    :try_start_0
    invoke-virtual {v9}, Lcn/kuaipan/android/kss/RC4Encoder;->init()V

    aget-object v0, v8, v10

    new-instance v15, Lcn/kuaipan/android/http/KscHttpRequest;

    sget-object v13, Lcn/kuaipan/android/http/KscHttpRequest$HttpMethod;->GET:Lcn/kuaipan/android/http/KscHttpRequest$HttpMethod;

    invoke-direct {v15, v13, v0, v9, v12}, Lcn/kuaipan/android/http/KscHttpRequest;-><init>(Lcn/kuaipan/android/http/KscHttpRequest$HttpMethod;Ljava/lang/String;Lcn/kuaipan/android/http/IKscDecoder;Lcn/kuaipan/android/http/IKscTransferListener;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_7
    .catchall {:try_start_0 .. :try_end_0} :catchall_3

    const-wide/16 v13, 0x0

    cmp-long v16, v5, v13

    if-lez v16, :cond_0

    :try_start_1
    invoke-virtual {v15}, Lcn/kuaipan/android/http/KscHttpRequest;->getRequest()Lorg/apache/http/client/methods/HttpUriRequest;

    move-result-object v12

    const-string v13, "Range"

    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "bytes="

    invoke-virtual {v14, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v14, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v11, "-"

    invoke-virtual {v14, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v11

    invoke-interface {v12, v13, v11}, Lorg/apache/http/client/methods/HttpUriRequest;->addHeader(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    goto :goto_2

    :catch_0
    move-exception v0

    move-wide/from16 v23, v5

    move-object/from16 v29, v15

    const/4 v12, 0x0

    move-object/from16 v5, p5

    goto/16 :goto_7

    :cond_0
    :goto_2
    :try_start_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v11

    iget-object v13, v1, Lcn/kuaipan/android/kss/download/KssDownloader;->mTransmitter:Lcn/kuaipan/android/http/KscHttpTransmitter;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_6
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    const/4 v14, 0x4

    move-wide/from16 v23, v5

    move-object/from16 v5, p4

    :try_start_3
    invoke-virtual {v13, v15, v14, v5}, Lcn/kuaipan/android/http/KscHttpTransmitter;->execute(Lcn/kuaipan/android/http/KscHttpRequest;ILcn/kuaipan/android/http/KssTransferStopper;)Lcn/kuaipan/android/http/KscHttpResponse;

    move-result-object v6
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_5
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    :try_start_4
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v13

    sub-long v21, v13, v11

    invoke-virtual {v6}, Lcn/kuaipan/android/http/KscHttpResponse;->getResponse()Lorg/apache/http/HttpResponse;

    move-result-object v13
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    if-eqz v13, :cond_1

    :try_start_5
    invoke-virtual {v6}, Lcn/kuaipan/android/http/KscHttpResponse;->getResponse()Lorg/apache/http/HttpResponse;

    move-result-object v13

    invoke-interface {v13}, Lorg/apache/http/HttpResponse;->getEntity()Lorg/apache/http/HttpEntity;

    move-result-object v13

    if-eqz v13, :cond_1

    invoke-interface {v13}, Lorg/apache/http/HttpEntity;->getContentLength()J

    move-result-wide v13
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_1
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    move-wide/from16 v25, v13

    goto :goto_3

    :catch_1
    move-exception v0

    move-object/from16 v5, p5

    move-object v12, v6

    move-object/from16 v29, v15

    goto/16 :goto_7

    :cond_1
    const-wide/16 v25, 0x0

    :goto_3
    :try_start_6
    invoke-virtual {v6}, Lcn/kuaipan/android/http/KscHttpResponse;->getStatusCode()I

    move-result v27

    const-string v13, ""

    invoke-virtual {v6}, Lcn/kuaipan/android/http/KscHttpResponse;->getError()Ljava/lang/Throwable;

    move-result-object v28
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_4
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    if-eqz v28, :cond_2

    :try_start_7
    invoke-virtual/range {v28 .. v28}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v13

    invoke-virtual {v13}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v13
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_1
    .catchall {:try_start_7 .. :try_end_7} :catchall_1

    :cond_2
    move-object/from16 v20, v13

    :try_start_8
    invoke-static {}, Lcom/xiaomi/micloudsdk/stat/MiCloudStatManager;->getInstance()Lcom/xiaomi/micloudsdk/stat/MiCloudStatManager;

    move-result-object v13
    :try_end_8
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_8} :catch_4
    .catchall {:try_start_8 .. :try_end_8} :catchall_1

    move-object v14, v0

    move-object/from16 v29, v15

    move-wide/from16 v15, v21

    move-wide/from16 v17, v25

    move/from16 v19, v27

    :try_start_9
    invoke-virtual/range {v13 .. v20}, Lcom/xiaomi/micloudsdk/stat/MiCloudStatManager;->addHttpEvent(Ljava/lang/String;JJILjava/lang/String;)V

    if-nez v28, :cond_3

    invoke-static {}, Lcom/xiaomi/micloudsdk/stat/MiCloudNetEventStatInjector;->getInstance()Lcom/xiaomi/micloudsdk/stat/MiCloudNetEventStatInjector;

    move-result-object v15

    new-instance v14, Lcom/xiaomi/micloudsdk/stat/NetSuccessStatParam;

    const/16 v28, 0x0

    move-object v13, v14

    move-object v5, v14

    move-object v14, v0

    move-object v0, v15

    move-wide v15, v11

    move-wide/from16 v17, v21

    move-wide/from16 v19, v25

    move/from16 v21, v27

    move/from16 v22, v28

    invoke-direct/range {v13 .. v22}, Lcom/xiaomi/micloudsdk/stat/NetSuccessStatParam;-><init>(Ljava/lang/String;JJJII)V

    invoke-virtual {v0, v5}, Lcom/xiaomi/micloudsdk/stat/MiCloudNetEventStatInjector;->addNetSuccessEvent(Lcom/xiaomi/micloudsdk/stat/NetSuccessStatParam;)V

    goto :goto_4

    :cond_3
    invoke-static {}, Lcom/xiaomi/micloudsdk/stat/MiCloudNetEventStatInjector;->getInstance()Lcom/xiaomi/micloudsdk/stat/MiCloudNetEventStatInjector;

    move-result-object v5

    new-instance v15, Lcom/xiaomi/micloudsdk/stat/NetFailedStatParam;

    const/16 v20, 0x0

    move-object v13, v15

    move-object v14, v0

    move-object v0, v15

    move-wide v15, v11

    move-wide/from16 v17, v21

    move-object/from16 v19, v28

    invoke-direct/range {v13 .. v20}, Lcom/xiaomi/micloudsdk/stat/NetFailedStatParam;-><init>(Ljava/lang/String;JJLjava/lang/Throwable;I)V

    invoke-virtual {v5, v0}, Lcom/xiaomi/micloudsdk/stat/MiCloudNetEventStatInjector;->addNetFailedEvent(Lcom/xiaomi/micloudsdk/stat/NetFailedStatParam;)V

    :goto_4
    invoke-static {v6}, Lcn/kuaipan/android/exception/ErrorHelper;->throwError(Lcn/kuaipan/android/http/KscHttpResponse;)V
    :try_end_9
    .catch Ljava/lang/Exception; {:try_start_9 .. :try_end_9} :catch_3
    .catchall {:try_start_9 .. :try_end_9} :catchall_0

    move-object/from16 v5, p5

    :try_start_a
    invoke-direct {v1, v6, v2, v4, v5}, Lcn/kuaipan/android/kss/download/KssDownloader;->save(Lcn/kuaipan/android/http/KscHttpResponse;Lcn/kuaipan/android/kss/download/KssAccessor;Lcn/kuaipan/android/kss/download/LoadRecorder;Ljava/util/concurrent/atomic/AtomicInteger;)V

    const/4 v11, 0x1

    invoke-virtual {v3, v2, v11}, Lcn/kuaipan/android/kss/download/LoadMap;->verify(Lcn/kuaipan/android/kss/download/KssAccessor;Z)V
    :try_end_a
    .catch Ljava/lang/Exception; {:try_start_a .. :try_end_a} :catch_2
    .catchall {:try_start_a .. :try_end_a} :catchall_0

    invoke-direct {v1, v6}, Lcn/kuaipan/android/kss/download/KssDownloader;->releaseResponse(Lcn/kuaipan/android/http/KscHttpResponse;)V

    if-eqz v4, :cond_d

    invoke-virtual {v4}, Lcn/kuaipan/android/kss/download/LoadRecorder;->recycle()V

    goto/16 :goto_d

    :catch_2
    move-exception v0

    goto :goto_5

    :catchall_0
    move-exception v0

    goto/16 :goto_b

    :catch_3
    move-exception v0

    move-object/from16 v5, p5

    goto :goto_5

    :catchall_1
    move-exception v0

    move-object/from16 v29, v15

    goto/16 :goto_b

    :catch_4
    move-exception v0

    move-object/from16 v5, p5

    move-object/from16 v29, v15

    :goto_5
    move-object v12, v6

    goto :goto_7

    :catch_5
    move-exception v0

    move-object/from16 v5, p5

    move-object/from16 v29, v15

    goto :goto_6

    :catchall_2
    move-exception v0

    move-object/from16 v29, v15

    const/4 v6, 0x0

    goto :goto_b

    :catch_6
    move-exception v0

    move-wide/from16 v23, v5

    move-object/from16 v29, v15

    move-object/from16 v5, p5

    :goto_6
    const/4 v12, 0x0

    goto :goto_7

    :catchall_3
    move-exception v0

    const/4 v6, 0x0

    const/16 v29, 0x0

    goto :goto_b

    :catch_7
    move-exception v0

    move-wide/from16 v23, v5

    move-object/from16 v5, p5

    const/4 v12, 0x0

    const/16 v29, 0x0

    :goto_7
    :try_start_b
    invoke-static {v0}, Lcn/kuaipan/android/exception/ErrorHelper;->handleInterruptException(Ljava/lang/Throwable;)V

    invoke-static {v0}, Lcn/kuaipan/android/exception/ErrorHelper;->isStopByCallerException(Ljava/lang/Throwable;)Z

    move-result v6

    if-nez v6, :cond_7

    array-length v6, v8
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_4

    const/4 v11, 0x1

    sub-int/2addr v6, v11

    if-lt v10, v6, :cond_4

    goto :goto_9

    :cond_4
    if-eqz v29, :cond_5

    invoke-virtual/range {v29 .. v29}, Lcn/kuaipan/android/http/KscHttpRequest;->getRequest()Lorg/apache/http/client/methods/HttpUriRequest;

    move-result-object v0

    invoke-interface {v0}, Lorg/apache/http/client/methods/HttpUriRequest;->abort()V

    goto :goto_8

    :cond_5
    invoke-direct {v1, v12}, Lcn/kuaipan/android/kss/download/KssDownloader;->releaseResponse(Lcn/kuaipan/android/http/KscHttpResponse;)V

    :goto_8
    if-eqz v4, :cond_6

    invoke-virtual {v4}, Lcn/kuaipan/android/kss/download/LoadRecorder;->recycle()V

    :cond_6
    add-int/lit8 v10, v10, 0x1

    move-wide/from16 v5, v23

    goto/16 :goto_1

    :cond_7
    :goto_9
    if-nez v12, :cond_8

    :try_start_c
    const-string v2, "No response."

    goto :goto_a

    :cond_8
    invoke-virtual {v12}, Lcn/kuaipan/android/http/KscHttpResponse;->dump()Ljava/lang/String;

    move-result-object v2

    :goto_a
    invoke-static {v0, v2}, Lcn/kuaipan/android/exception/KscException;->newException(Ljava/lang/Throwable;Ljava/lang/String;)Lcn/kuaipan/android/exception/KscException;

    move-result-object v0

    throw v0
    :try_end_c
    .catchall {:try_start_c .. :try_end_c} :catchall_4

    :catchall_4
    move-exception v0

    move-object v6, v12

    :goto_b
    if-eqz v29, :cond_9

    invoke-virtual/range {v29 .. v29}, Lcn/kuaipan/android/http/KscHttpRequest;->getRequest()Lorg/apache/http/client/methods/HttpUriRequest;

    move-result-object v2

    invoke-interface {v2}, Lorg/apache/http/client/methods/HttpUriRequest;->abort()V

    goto :goto_c

    :cond_9
    invoke-direct {v1, v6}, Lcn/kuaipan/android/kss/download/KssDownloader;->releaseResponse(Lcn/kuaipan/android/http/KscHttpResponse;)V

    :goto_c
    if-eqz v4, :cond_a

    invoke-virtual {v4}, Lcn/kuaipan/android/kss/download/LoadRecorder;->recycle()V

    :cond_a
    throw v0

    :cond_b
    new-instance v0, Ljava/lang/InterruptedException;

    invoke-direct {v0}, Ljava/lang/InterruptedException;-><init>()V

    throw v0

    :cond_c
    move-object/from16 v5, p5

    :cond_d
    :goto_d
    invoke-virtual/range {p3 .. p3}, Lcn/kuaipan/android/kss/download/LoadMap;->obtainRecorder()Lcn/kuaipan/android/kss/download/LoadRecorder;

    move-result-object v4

    goto/16 :goto_0

    :cond_e
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v2, "No available urls to download."

    invoke-direct {v0, v2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_f
    new-instance v0, Ljava/lang/InterruptedException;

    invoke-direct {v0}, Ljava/lang/InterruptedException;-><init>()V

    throw v0

    :cond_10
    return-void
.end method

.method private releaseResponse(Lcn/kuaipan/android/http/KscHttpResponse;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/InterruptedException;
        }
    .end annotation

    if-eqz p1, :cond_1

    :try_start_0
    invoke-virtual {p1}, Lcn/kuaipan/android/http/KscHttpResponse;->release()V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    invoke-static {p1}, Lcn/kuaipan/android/exception/ErrorHelper;->isInterrupted(Ljava/lang/Throwable;)Ljava/lang/InterruptedException;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    throw p1

    :cond_1
    :goto_0
    return-void
.end method

.method private save(Lcn/kuaipan/android/http/KscHttpResponse;Lcn/kuaipan/android/kss/download/KssAccessor;Lcn/kuaipan/android/kss/download/LoadRecorder;Ljava/util/concurrent/atomic/AtomicInteger;)V
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/IllegalStateException;,
            Ljava/io/IOException;
        }
    .end annotation

    const/4 v0, 0x3

    const/4 v1, 0x0

    :try_start_0
    invoke-virtual {p1}, Lcn/kuaipan/android/http/KscHttpResponse;->getContent()Ljava/io/InputStream;

    move-result-object v2

    if-eqz v2, :cond_3

    const/16 p1, 0x2000

    new-array p1, p1, [B
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    move v3, v1

    :cond_0
    :try_start_1
    invoke-virtual {v2, p1}, Ljava/io/InputStream;->read([B)I

    move-result v4

    if-ltz v4, :cond_1

    const/4 v3, 0x1

    if-lez v4, :cond_0

    invoke-virtual {p2, p1, v1, v4, p3}, Lcn/kuaipan/android/kss/download/KssAccessor;->write([BIILcn/kuaipan/android/kss/download/LoadRecorder;)I

    move-result v5
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-ge v5, v4, :cond_0

    :cond_1
    if-eqz v3, :cond_2

    invoke-virtual {p4, v0}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    :cond_2
    return-void

    :catchall_0
    move-exception p1

    move v1, v3

    goto :goto_0

    :cond_3
    :try_start_2
    new-instance p2, Lcn/kuaipan/android/exception/KscRuntimeException;

    const p3, 0x7a128

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Not meet exception, but no response.\n"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcn/kuaipan/android/http/KscHttpResponse;->dump()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p2, p3, p1}, Lcn/kuaipan/android/exception/KscRuntimeException;-><init>(ILjava/lang/String;)V

    throw p2
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    :catchall_1
    move-exception p1

    :goto_0
    if-eqz v1, :cond_4

    invoke-virtual {p4, v0}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    :cond_4
    throw p1
.end method


# virtual methods
.method public download(Ljava/io/File;ZLcn/kuaipan/android/http/IKscTransferListener;Lcn/kuaipan/android/http/KssTransferStopper;Lcn/kuaipan/android/kss/IKssDownloadRequestResult;)V
    .locals 10
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcn/kuaipan/android/exception/KscException;,
            Ljava/lang/InterruptedException;
        }
    .end annotation

    const/4 v0, 0x0

    const/4 v1, 0x0

    :try_start_0
    invoke-interface {p5}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getTotalSize()J

    move-result-wide v2

    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v4

    if-eqz v4, :cond_2

    if-eqz p2, :cond_0

    invoke-virtual {p1}, Ljava/io/File;->isDirectory()Z

    move-result p2

    if-nez p2, :cond_0

    invoke-virtual {p1}, Ljava/io/File;->length()J

    move-result-wide v4

    cmp-long p2, v4, v2

    if-lez p2, :cond_3

    :cond_0
    invoke-static {p1}, Lcn/kuaipan/android/utils/FileUtils;->deletes(Ljava/io/File;)Z

    move-result p2

    if-eqz p2, :cond_1

    goto :goto_0

    :cond_1
    new-instance p2, Ljava/lang/SecurityException;

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    const-string p4, "Failed delete target file. Can\'t download to dest path: "

    invoke-virtual {p3, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p2, p1}, Ljava/lang/SecurityException;-><init>(Ljava/lang/String;)V

    throw p2

    :cond_2
    invoke-virtual {p1}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object p2

    invoke-virtual {p2}, Ljava/io/File;->mkdirs()Z

    :cond_3
    :goto_0
    new-instance p2, Lcn/kuaipan/android/kss/download/LoadMap;

    invoke-direct {p2, p5, p3}, Lcn/kuaipan/android/kss/download/LoadMap;-><init>(Lcn/kuaipan/android/kss/IKssDownloadRequestResult;Lcn/kuaipan/android/http/IKscTransferListener;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_3

    :try_start_1
    invoke-static {p1}, Lcn/kuaipan/android/kss/download/KInfo;->getInfoFile(Ljava/io/File;)Ljava/io/File;

    move-result-object p3

    new-instance v8, Lcn/kuaipan/android/kss/download/KInfo;

    invoke-direct {v8, p3}, Lcn/kuaipan/android/kss/download/KInfo;-><init>(Ljava/io/File;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    :try_start_2
    invoke-virtual {p3}, Ljava/io/File;->exists()Z

    move-result p3

    if-eqz p3, :cond_4

    invoke-virtual {v8}, Lcn/kuaipan/android/kss/download/KInfo;->load()V

    invoke-virtual {v8}, Lcn/kuaipan/android/kss/download/KInfo;->getHash()Ljava/lang/String;

    move-result-object p3

    invoke-interface {p5}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getHash()Ljava/lang/String;

    move-result-object v2

    invoke-static {p3, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p3

    if-eqz p3, :cond_4

    invoke-virtual {v8, p2}, Lcn/kuaipan/android/kss/download/KInfo;->loadToMap(Lcn/kuaipan/android/kss/download/LoadMap;)Z

    move-result p3

    goto :goto_1

    :cond_4
    move p3, v0

    :goto_1
    if-nez p3, :cond_5

    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result p3

    if-eqz p3, :cond_5

    invoke-virtual {p1}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-virtual {p2, v2, v3}, Lcn/kuaipan/android/kss/download/LoadMap;->initSize(J)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    :cond_5
    :try_start_3
    new-instance p3, Lcn/kuaipan/android/kss/download/KssAccessor;

    invoke-direct {p3, p1}, Lcn/kuaipan/android/kss/download/KssAccessor;-><init>(Ljava/io/File;)V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_4
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :try_start_4
    invoke-virtual {p2, p3, v0}, Lcn/kuaipan/android/kss/download/LoadMap;->verify(Lcn/kuaipan/android/kss/download/KssAccessor;Z)V

    invoke-interface {p5}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getTotalSize()J

    move-result-wide v1

    invoke-virtual {p1}, Ljava/io/File;->length()J

    move-result-wide v3

    cmp-long v3, v3, v1

    if-eqz v3, :cond_6

    invoke-virtual {p3, v1, v2}, Lcn/kuaipan/android/kss/download/KssAccessor;->inflate(J)V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_3
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    :cond_6
    :try_start_5
    new-instance v1, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v9, 0x3

    invoke-direct {v1, v9}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    :goto_2
    invoke-virtual {p2}, Lcn/kuaipan/android/kss/download/LoadMap;->isCompleted()Z

    move-result v0

    if-nez v0, :cond_9

    invoke-static {}, Ljava/lang/Thread;->interrupted()Z

    move-result v2
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    if-nez v2, :cond_8

    move-object v2, p0

    move-object v3, p5

    move-object v4, p3

    move-object v5, p2

    move-object v6, p4

    move-object v7, v1

    :try_start_6
    invoke-direct/range {v2 .. v7}, Lcn/kuaipan/android/kss/download/KssDownloader;->download(Lcn/kuaipan/android/kss/IKssDownloadRequestResult;Lcn/kuaipan/android/kss/download/KssAccessor;Lcn/kuaipan/android/kss/download/LoadMap;Lcn/kuaipan/android/http/KssTransferStopper;Ljava/util/concurrent/atomic/AtomicInteger;)V

    invoke-virtual {v1, v9}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V
    :try_end_6
    .catch Ljava/security/InvalidKeyException; {:try_start_6 .. :try_end_6} :catch_1
    .catch Lcn/kuaipan/android/exception/KscException; {:try_start_6 .. :try_end_6} :catch_0
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    goto :goto_2

    :catch_0
    move-exception v2

    :try_start_7
    invoke-static {v2}, Lcn/kuaipan/android/exception/ErrorHelper;->isNetworkException(Ljava/lang/Throwable;)Z

    move-result v3

    if-eqz v3, :cond_7

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicInteger;->decrementAndGet()I

    move-result v3

    if-ltz v3, :cond_7

    const-wide/16 v2, 0x1388

    invoke-static {v2, v3}, Ljava/lang/Thread;->sleep(J)V

    goto :goto_2

    :cond_7
    throw v2

    :catch_1
    move-exception p1

    const-string p4, "Failed when download kss block."

    invoke-static {p1, p4}, Lcn/kuaipan/android/exception/KscException;->newException(Ljava/lang/Throwable;Ljava/lang/String;)Lcn/kuaipan/android/exception/KscException;

    move-result-object p1

    throw p1

    :cond_8
    new-instance p1, Ljava/lang/InterruptedException;

    invoke-direct {p1}, Ljava/lang/InterruptedException;-><init>()V

    throw p1

    :cond_9
    invoke-interface {p5}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getModifyTime()J

    move-result-wide v1

    const-wide/16 v3, 0x0

    cmp-long p4, v1, v3

    if-lez p4, :cond_a

    invoke-virtual {p1, v1, v2}, Ljava/io/File;->setLastModified(J)Z
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    :cond_a
    :try_start_8
    invoke-virtual {p3}, Lcn/kuaipan/android/kss/download/KssAccessor;->close()V
    :try_end_8
    .catch Ljava/lang/Throwable; {:try_start_8 .. :try_end_8} :catch_2

    :catch_2
    if-eqz v0, :cond_b

    invoke-virtual {v8}, Lcn/kuaipan/android/kss/download/KInfo;->delete()V

    goto :goto_3

    :cond_b
    invoke-interface {p5}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getHash()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v8, p1}, Lcn/kuaipan/android/kss/download/KInfo;->setHash(Ljava/lang/String;)V

    invoke-virtual {v8, p2}, Lcn/kuaipan/android/kss/download/KInfo;->setLoadMap(Lcn/kuaipan/android/kss/download/LoadMap;)V

    invoke-virtual {v8}, Lcn/kuaipan/android/kss/download/KInfo;->save()V

    :goto_3
    return-void

    :catchall_0
    move-exception p1

    move-object v1, p3

    goto :goto_5

    :catch_3
    move-exception p1

    move-object v1, p3

    goto :goto_4

    :catch_4
    move-exception p1

    :goto_4
    :try_start_9
    const-string p3, "Local IO error, when prepare kss download."

    invoke-static {p1, p3}, Lcn/kuaipan/android/exception/KscException;->newException(Ljava/lang/Throwable;Ljava/lang/String;)Lcn/kuaipan/android/exception/KscException;

    move-result-object p1

    throw p1
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_1

    :catchall_1
    move-exception p1

    goto :goto_5

    :catchall_2
    move-exception p1

    move-object v8, v1

    goto :goto_5

    :catchall_3
    move-exception p1

    move-object p2, v1

    move-object v8, p2

    :goto_5
    if-eqz v1, :cond_c

    :try_start_a
    invoke-virtual {v1}, Lcn/kuaipan/android/kss/download/KssAccessor;->close()V
    :try_end_a
    .catch Ljava/lang/Throwable; {:try_start_a .. :try_end_a} :catch_5

    :catch_5
    :cond_c
    if-eqz v8, :cond_e

    if-nez v0, :cond_d

    if-eqz p2, :cond_e

    invoke-interface {p5}, Lcn/kuaipan/android/kss/IKssDownloadRequestResult;->getHash()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {v8, p3}, Lcn/kuaipan/android/kss/download/KInfo;->setHash(Ljava/lang/String;)V

    invoke-virtual {v8, p2}, Lcn/kuaipan/android/kss/download/KInfo;->setLoadMap(Lcn/kuaipan/android/kss/download/LoadMap;)V

    invoke-virtual {v8}, Lcn/kuaipan/android/kss/download/KInfo;->save()V

    goto :goto_6

    :cond_d
    invoke-virtual {v8}, Lcn/kuaipan/android/kss/download/KInfo;->delete()V

    :cond_e
    :goto_6
    throw p1
.end method
