.class public abstract Lcom/xiaomi/smack/SocketConnection;
.super Lcom/xiaomi/smack/Connection;
.source "SocketConnection.java"


# instance fields
.field private connectedHost:Ljava/lang/String;

.field connectionID:Ljava/lang/String;

.field private curShortConnCount:I

.field protected failedException:Ljava/lang/Exception;

.field protected volatile lastConnectedTime:J

.field protected volatile lastPingReceived:J

.field protected volatile lastPingSent:J

.field protected pushService:Lcom/xiaomi/push/service/XMPushService;

.field protected socket:Ljava/net/Socket;


# direct methods
.method public constructor <init>(Lcom/xiaomi/push/service/XMPushService;Lcom/xiaomi/smack/ConnectionConfiguration;)V
    .locals 2

    invoke-direct {p0, p1, p2}, Lcom/xiaomi/smack/Connection;-><init>(Lcom/xiaomi/push/service/XMPushService;Lcom/xiaomi/smack/ConnectionConfiguration;)V

    const/4 p2, 0x0

    iput-object p2, p0, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    iput-object p2, p0, Lcom/xiaomi/smack/SocketConnection;->connectionID:Ljava/lang/String;

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/xiaomi/smack/SocketConnection;->lastPingSent:J

    iput-wide v0, p0, Lcom/xiaomi/smack/SocketConnection;->lastPingReceived:J

    iput-wide v0, p0, Lcom/xiaomi/smack/SocketConnection;->lastConnectedTime:J

    iput-object p1, p0, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    return-void
.end method

.method private connectDirectly(Ljava/lang/String;I)V
    .locals 24
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/smack/XMPPException;
        }
    .end annotation

    move-object/from16 v1, p0

    move-object/from16 v0, p1

    move/from16 v2, p2

    const-string v3, "\n"

    const-string v4, " err:"

    const-string v5, " port:"

    const-string v6, "SMACK: Could not connect to "

    const-string v7, "SMACK: Could not connect to:"

    const/4 v8, 0x0

    iput-object v8, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    new-instance v8, Ljava/util/ArrayList;

    invoke-direct {v8}, Ljava/util/ArrayList;-><init>()V

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    const-string v10, "get bucket for host : "

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-static {v9}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->ps(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/Integer;->intValue()I

    move-result v9

    invoke-virtual/range {p0 .. p1}, Lcom/xiaomi/smack/SocketConnection;->getFallback(Ljava/lang/String;)Lcom/xiaomi/network/Fallback;

    move-result-object v14

    invoke-static {v9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v9

    invoke-static {v9}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->pe(Ljava/lang/Integer;)V

    const/4 v9, 0x1

    if-eqz v14, :cond_0

    invoke-virtual {v14, v9}, Lcom/xiaomi/network/Fallback;->getHosts(Z)Ljava/util/ArrayList;

    move-result-object v8

    :cond_0
    invoke-virtual {v8}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_1

    invoke-virtual {v8, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    const-wide/16 v10, 0x0

    iput-wide v10, v1, Lcom/xiaomi/smack/SocketConnection;->lastConnectedTime:J

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v15

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v8

    const/4 v0, 0x0

    move v10, v0

    :goto_0
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_a

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    move-object v13, v0

    check-cast v13, Ljava/lang/String;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v16

    iget v0, v1, Lcom/xiaomi/smack/SocketConnection;->connTimes:I

    add-int/2addr v0, v9

    iput v0, v1, Lcom/xiaomi/smack/SocketConnection;->connTimes:I

    :try_start_0
    new-instance v0, Ljava/lang/StringBuilder;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_6
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_5
    .catchall {:try_start_0 .. :try_end_0} :catchall_2

    :try_start_1
    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "begin to connect to "

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->w(Ljava/lang/String;)V

    invoke-virtual/range {p0 .. p0}, Lcom/xiaomi/smack/SocketConnection;->createSocket()Ljava/net/Socket;

    move-result-object v0

    iput-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->socket:Ljava/net/Socket;

    invoke-static {v13, v2}, Lcom/xiaomi/network/Host;->from(Ljava/lang/String;I)Ljava/net/InetSocketAddress;

    move-result-object v0

    iget-object v11, v1, Lcom/xiaomi/smack/SocketConnection;->socket:Ljava/net/Socket;

    const/16 v9, 0x1f40

    invoke-virtual {v11, v0, v9}, Ljava/net/Socket;->connect(Ljava/net/SocketAddress;I)V

    const-string v0, "tcp connected"

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->w(Ljava/lang/String;)V

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->socket:Ljava/net/Socket;

    const/4 v9, 0x1

    invoke-virtual {v0, v9}, Ljava/net/Socket;->setTcpNoDelay(Z)V

    iput-object v13, v1, Lcom/xiaomi/smack/SocketConnection;->connectedHost:Ljava/lang/String;

    invoke-virtual/range {p0 .. p0}, Lcom/xiaomi/smack/SocketConnection;->initConnection()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_4
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_5
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    :try_start_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v10

    sub-long v10, v10, v16

    iput-wide v10, v1, Lcom/xiaomi/smack/SocketConnection;->connectTime:J

    iput-object v15, v1, Lcom/xiaomi/smack/SocketConnection;->connectionPoint:Ljava/lang/String;

    if-eqz v14, :cond_2

    iget-wide v10, v1, Lcom/xiaomi/smack/SocketConnection;->connectTime:J
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_3
    .catch Ljava/lang/Throwable; {:try_start_2 .. :try_end_2} :catch_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    const-wide/16 v18, 0x0

    move-wide/from16 v20, v10

    move-object v10, v14

    move-object v11, v13

    move-object/from16 v22, v8

    move-object v9, v12

    move-object v8, v13

    move-wide/from16 v12, v20

    move-object/from16 v20, v14

    move-object/from16 v23, v15

    move-wide/from16 v14, v18

    :try_start_3
    invoke-virtual/range {v10 .. v15}, Lcom/xiaomi/network/Fallback;->succeedHost(Ljava/lang/String;JJ)V

    goto :goto_1

    :cond_2
    move-object/from16 v22, v8

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move-object/from16 v23, v15

    :goto_1
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v10

    iput-wide v10, v1, Lcom/xiaomi/smack/SocketConnection;->lastConnectedTime:J

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v10, "connected to "

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, " in "

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v10, v1, Lcom/xiaomi/smack/SocketConnection;->connectTime:J

    invoke-virtual {v0, v10, v11}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->w(Ljava/lang/String;)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_1
    .catch Ljava/lang/Throwable; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    const/16 v18, 0x1

    goto/16 :goto_b

    :catchall_0
    move-exception v0

    move-object/from16 v14, v23

    goto :goto_2

    :catch_0
    move-exception v0

    goto :goto_3

    :catch_1
    move-exception v0

    move-object/from16 v14, v23

    goto :goto_4

    :catchall_1
    move-exception v0

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move-object v14, v15

    :goto_2
    const/16 v18, 0x1

    goto/16 :goto_9

    :catch_2
    move-exception v0

    move-object/from16 v22, v8

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move-object/from16 v23, v15

    :goto_3
    const/16 v18, 0x1

    goto :goto_5

    :catch_3
    move-exception v0

    move-object/from16 v22, v8

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move-object v14, v15

    :goto_4
    const/16 v18, 0x1

    goto/16 :goto_6

    :catch_4
    move-exception v0

    move-object/from16 v22, v8

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move/from16 v18, v10

    move-object v14, v15

    goto/16 :goto_6

    :catchall_2
    move-exception v0

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move/from16 v18, v10

    move-object v14, v15

    goto/16 :goto_9

    :catch_5
    move-exception v0

    move-object/from16 v22, v8

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move-object/from16 v23, v15

    move/from16 v18, v10

    :goto_5
    :try_start_4
    new-instance v10, Ljava/lang/Exception;

    const-string v11, "abnormal exception"

    invoke-direct {v10, v11, v0}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    iput-object v10, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->e(Ljava/lang/Throwable;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_3

    if-nez v18, :cond_4

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->e(Ljava/lang/String;)V

    invoke-virtual {v9, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v9, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    invoke-static {v8, v0}, Lcom/xiaomi/stats/StatsHelper;->connectFail(Ljava/lang/String;Ljava/lang/Exception;)V

    if-eqz v20, :cond_3

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v10

    sub-long v12, v10, v16

    const-wide/16 v14, 0x0

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    move-object/from16 v10, v20

    move-object v11, v8

    move-object/from16 v16, v0

    invoke-virtual/range {v10 .. v16}, Lcom/xiaomi/network/Fallback;->failedHost(Ljava/lang/String;JJLjava/lang/Exception;)V

    :cond_3
    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    move-object/from16 v14, v23

    invoke-static {v14, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_5

    goto/16 :goto_b

    :cond_4
    move-object/from16 v14, v23

    :cond_5
    move-object v8, v14

    goto/16 :goto_8

    :catchall_3
    move-exception v0

    move-object/from16 v14, v23

    goto/16 :goto_9

    :catch_6
    move-exception v0

    move-object/from16 v22, v8

    move-object v9, v12

    move-object v8, v13

    move-object/from16 v20, v14

    move-object v14, v15

    move/from16 v18, v10

    :goto_6
    :try_start_5
    iput-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_4

    if-nez v18, :cond_5

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->e(Ljava/lang/String;)V

    invoke-virtual {v9, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v9, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    invoke-static {v8, v0}, Lcom/xiaomi/stats/StatsHelper;->connectFail(Ljava/lang/String;Ljava/lang/Exception;)V

    if-eqz v20, :cond_6

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v10

    sub-long v12, v10, v16

    const-wide/16 v15, 0x0

    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    move-object/from16 v10, v20

    move-object v11, v8

    move-object v8, v14

    move-wide v14, v15

    move-object/from16 v16, v0

    invoke-virtual/range {v10 .. v16}, Lcom/xiaomi/network/Fallback;->failedHost(Ljava/lang/String;JJLjava/lang/Exception;)V

    goto :goto_7

    :cond_6
    move-object v8, v14

    :goto_7
    iget-object v0, v1, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v8, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_7

    goto/16 :goto_b

    :cond_7
    :goto_8
    move/from16 v10, v18

    move-object v15, v8

    move-object v12, v9

    move-object/from16 v14, v20

    move-object/from16 v8, v22

    const/4 v9, 0x1

    goto/16 :goto_0

    :catchall_4
    move-exception v0

    :goto_9
    if-nez v18, :cond_9

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->e(Ljava/lang/String;)V

    invoke-virtual {v9, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    invoke-virtual {v2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    invoke-static {v8, v2}, Lcom/xiaomi/stats/StatsHelper;->connectFail(Ljava/lang/String;Ljava/lang/Exception;)V

    if-eqz v20, :cond_8

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sub-long v12, v2, v16

    const-wide/16 v2, 0x0

    iget-object v4, v1, Lcom/xiaomi/smack/SocketConnection;->failedException:Ljava/lang/Exception;

    move-object/from16 v10, v20

    move-object v11, v8

    move-object v5, v14

    move-wide v14, v2

    move-object/from16 v16, v4

    invoke-virtual/range {v10 .. v16}, Lcom/xiaomi/network/Fallback;->failedHost(Ljava/lang/String;JJLjava/lang/Exception;)V

    goto :goto_a

    :cond_8
    move-object v5, v14

    :goto_a
    iget-object v2, v1, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v2}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v5, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_9

    goto :goto_b

    :cond_9
    throw v0

    :cond_a
    move-object v9, v12

    move/from16 v18, v10

    :goto_b
    invoke-static {}, Lcom/xiaomi/network/HostManager;->getInstance()Lcom/xiaomi/network/HostManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/xiaomi/network/HostManager;->persist()V

    if-eqz v18, :cond_b

    return-void

    :cond_b
    new-instance v0, Lcom/xiaomi/smack/XMPPException;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Lcom/xiaomi/smack/XMPPException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private connectUsingConfiguration(Lcom/xiaomi/smack/ConnectionConfiguration;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/smack/XMPPException;,
            Ljava/io/IOException;
        }
    .end annotation

    invoke-virtual {p1}, Lcom/xiaomi/smack/ConnectionConfiguration;->getHost()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Lcom/xiaomi/smack/ConnectionConfiguration;->getPort()I

    move-result p1

    invoke-direct {p0, v0, p1}, Lcom/xiaomi/smack/SocketConnection;->connectDirectly(Ljava/lang/String;I)V

    return-void
.end method


# virtual methods
.method public batchSend([Lcom/xiaomi/slim/Blob;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/smack/XMPPException;
        }
    .end annotation

    new-instance p1, Lcom/xiaomi/smack/XMPPException;

    const-string v0, "Don\'t support send Blob"

    invoke-direct {p1, v0}, Lcom/xiaomi/smack/XMPPException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public declared-synchronized connect()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/smack/XMPPException;
        }
    .end annotation

    monitor-enter p0

    :try_start_0
    invoke-virtual {p0}, Lcom/xiaomi/smack/SocketConnection;->isConnected()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/xiaomi/smack/SocketConnection;->isConnecting()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    const/4 v1, 0x0

    invoke-virtual {p0, v1, v1, v0}, Lcom/xiaomi/smack/SocketConnection;->setConnectionStatus(IILjava/lang/Exception;)V

    iget-object v0, p0, Lcom/xiaomi/smack/SocketConnection;->config:Lcom/xiaomi/smack/ConnectionConfiguration;

    invoke-direct {p0, v0}, Lcom/xiaomi/smack/SocketConnection;->connectUsingConfiguration(Lcom/xiaomi/smack/ConnectionConfiguration;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-void

    :cond_1
    :goto_0
    :try_start_1
    const-string v0, "WARNING: current xmpp has connected"

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->w(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    goto :goto_1

    :catch_0
    move-exception v0

    :try_start_2
    new-instance v1, Lcom/xiaomi/smack/XMPPException;

    invoke-direct {v1, v0}, Lcom/xiaomi/smack/XMPPException;-><init>(Ljava/lang/Throwable;)V

    throw v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :goto_1
    monitor-exit p0

    throw v0
.end method

.method public createSocket()Ljava/net/Socket;
    .locals 1

    new-instance v0, Ljava/net/Socket;

    invoke-direct {v0}, Ljava/net/Socket;-><init>()V

    return-object v0
.end method

.method public disconnect(ILjava/lang/Exception;)V
    .locals 4

    invoke-virtual {p0, p1, p2}, Lcom/xiaomi/smack/SocketConnection;->shutdown(ILjava/lang/Exception;)V

    if-nez p2, :cond_0

    const/16 v0, 0x12

    if-ne p1, v0, :cond_1

    :cond_0
    iget-wide v0, p0, Lcom/xiaomi/smack/SocketConnection;->lastConnectedTime:J

    const-wide/16 v2, 0x0

    cmp-long p1, v0, v2

    if-eqz p1, :cond_1

    invoke-virtual {p0, p2}, Lcom/xiaomi/smack/SocketConnection;->sinkdownHost(Ljava/lang/Exception;)V

    :cond_1
    return-void
.end method

.method public getChallenge()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/smack/SocketConnection;->challenge:Ljava/lang/String;

    return-object v0
.end method

.method getFallback(Ljava/lang/String;)Lcom/xiaomi/network/Fallback;
    .locals 2

    invoke-static {}, Lcom/xiaomi/network/HostManager;->getInstance()Lcom/xiaomi/network/HostManager;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, p1, v1}, Lcom/xiaomi/network/HostManager;->getFallbacksByHost(Ljava/lang/String;Z)Lcom/xiaomi/network/Fallback;

    move-result-object v0

    invoke-virtual {v0}, Lcom/xiaomi/network/Fallback;->isEffective()Z

    move-result v1

    if-nez v1, :cond_0

    new-instance v1, Lcom/xiaomi/smack/SocketConnection$3;

    invoke-direct {v1, p0, p1}, Lcom/xiaomi/smack/SocketConnection$3;-><init>(Lcom/xiaomi/smack/SocketConnection;Ljava/lang/String;)V

    invoke-static {v1}, Lcom/xiaomi/smack/util/TaskExecutor;->execute(Ljava/lang/Runnable;)V

    :cond_0
    return-object v0
.end method

.method public getHost()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/smack/SocketConnection;->connectedHost:Ljava/lang/String;

    return-object v0
.end method

.method protected declared-synchronized initConnection()V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/smack/XMPPException;,
            Ljava/io/IOException;
        }
    .end annotation

    monitor-enter p0

    monitor-exit p0

    return-void
.end method

.method public notifyConnectionError(ILjava/lang/Exception;)V
    .locals 3

    iget-object v0, p0, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    new-instance v1, Lcom/xiaomi/smack/SocketConnection$2;

    const/4 v2, 0x2

    invoke-direct {v1, p0, v2, p1, p2}, Lcom/xiaomi/smack/SocketConnection$2;-><init>(Lcom/xiaomi/smack/SocketConnection;IILjava/lang/Exception;)V

    invoke-virtual {v0, v1}, Lcom/xiaomi/push/service/XMPushService;->executeJob(Lcom/xiaomi/push/service/XMPushService$Job;)V

    return-void
.end method

.method public ping(Z)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/smack/XMPPException;
        }
    .end annotation

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-virtual {p0, p1}, Lcom/xiaomi/smack/SocketConnection;->sendPing(Z)V

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    new-instance v2, Lcom/xiaomi/smack/SocketConnection$1;

    const/16 v3, 0xd

    invoke-direct {v2, p0, v3, v0, v1}, Lcom/xiaomi/smack/SocketConnection$1;-><init>(Lcom/xiaomi/smack/SocketConnection;IJ)V

    const-wide/16 v0, 0x2710

    invoke-virtual {p1, v2, v0, v1}, Lcom/xiaomi/push/service/XMPushService;->executeJobDelayed(Lcom/xiaomi/push/service/XMPushService$Job;J)V

    :cond_0
    return-void
.end method

.method protected abstract sendPing(Z)V
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/xiaomi/smack/XMPPException;
        }
    .end annotation
.end method

.method protected declared-synchronized shutdown(ILjava/lang/Exception;)V
    .locals 2

    monitor-enter p0

    :try_start_0
    invoke-virtual {p0}, Lcom/xiaomi/smack/SocketConnection;->getConnectionStatus()I

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    monitor-exit p0

    return-void

    :cond_0
    :try_start_1
    invoke-virtual {p0, v1, p1, p2}, Lcom/xiaomi/smack/SocketConnection;->setConnectionStatus(IILjava/lang/Exception;)V

    const-string p1, ""

    iput-object p1, p0, Lcom/xiaomi/smack/SocketConnection;->challenge:Ljava/lang/String;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :try_start_2
    iget-object p1, p0, Lcom/xiaomi/smack/SocketConnection;->socket:Ljava/net/Socket;

    invoke-virtual {p1}, Ljava/net/Socket;->close()V
    :try_end_2
    .catch Ljava/lang/Throwable; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :catch_0
    const-wide/16 p1, 0x0

    :try_start_3
    iput-wide p1, p0, Lcom/xiaomi/smack/SocketConnection;->lastPingSent:J

    iput-wide p1, p0, Lcom/xiaomi/smack/SocketConnection;->lastPingReceived:J
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit p0

    throw p1
.end method

.method protected sinkdownHost(Ljava/lang/Exception;)V
    .locals 4

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/xiaomi/smack/SocketConnection;->lastConnectedTime:J

    sub-long/2addr v0, v2

    const-wide/32 v2, 0x493e0

    cmp-long v0, v0, v2

    const/4 v1, 0x0

    if-gez v0, :cond_0

    iget-object v0, p0, Lcom/xiaomi/smack/SocketConnection;->pushService:Lcom/xiaomi/push/service/XMPushService;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/network/Network;->hasNetwork(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/xiaomi/smack/SocketConnection;->curShortConnCount:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/xiaomi/smack/SocketConnection;->curShortConnCount:I

    iget v0, p0, Lcom/xiaomi/smack/SocketConnection;->curShortConnCount:I

    const/4 v2, 0x2

    if-lt v0, v2, :cond_1

    invoke-virtual {p0}, Lcom/xiaomi/smack/SocketConnection;->getHost()Ljava/lang/String;

    move-result-object v0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "max short conn time reached, sink down current host:"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->w(Ljava/lang/String;)V

    const-wide/16 v2, 0x0

    invoke-virtual {p0, v0, v2, v3, p1}, Lcom/xiaomi/smack/SocketConnection;->sinkdownHost(Ljava/lang/String;JLjava/lang/Exception;)V

    iput v1, p0, Lcom/xiaomi/smack/SocketConnection;->curShortConnCount:I

    goto :goto_0

    :cond_0
    iput v1, p0, Lcom/xiaomi/smack/SocketConnection;->curShortConnCount:I

    :cond_1
    :goto_0
    return-void
.end method

.method protected sinkdownHost(Ljava/lang/String;JLjava/lang/Exception;)V
    .locals 10

    invoke-static {}, Lcom/xiaomi/smack/ConnectionConfiguration;->getXmppServerHost()Ljava/lang/String;

    move-result-object v0

    invoke-static {}, Lcom/xiaomi/network/HostManager;->getInstance()Lcom/xiaomi/network/HostManager;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2}, Lcom/xiaomi/network/HostManager;->getFallbacksByHost(Ljava/lang/String;Z)Lcom/xiaomi/network/Fallback;

    move-result-object v3

    if-eqz v3, :cond_0

    const-wide/16 v7, 0x0

    move-object v4, p1

    move-wide v5, p2

    move-object v9, p4

    invoke-virtual/range {v3 .. v9}, Lcom/xiaomi/network/Fallback;->failedHost(Ljava/lang/String;JJLjava/lang/Exception;)V

    invoke-static {}, Lcom/xiaomi/network/HostManager;->getInstance()Lcom/xiaomi/network/HostManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/xiaomi/network/HostManager;->persist()V

    :cond_0
    return-void
.end method

.method public updateLastReceived()V
    .locals 2

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/xiaomi/smack/SocketConnection;->lastPingReceived:J

    return-void
.end method

.method public updateLastSent()V
    .locals 2

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/xiaomi/smack/SocketConnection;->lastPingSent:J

    return-void
.end method
