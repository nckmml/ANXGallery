.class Lcom/xiaomi/push/log/LogUploader$UploadTask;
.super Lcom/xiaomi/push/log/LogUploader$Task;
.source "LogUploader.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/xiaomi/push/log/LogUploader;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "UploadTask"
.end annotation


# instance fields
.field file:Ljava/io/File;

.field force:Z

.field retryNum:I

.field final synthetic this$0:Lcom/xiaomi/push/log/LogUploader;

.field token:Ljava/lang/String;

.field uploaded:Z

.field url:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/xiaomi/push/log/LogUploader;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Z)V
    .locals 0

    iput-object p1, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->this$0:Lcom/xiaomi/push/log/LogUploader;

    invoke-direct {p0, p1}, Lcom/xiaomi/push/log/LogUploader$Task;-><init>(Lcom/xiaomi/push/log/LogUploader;)V

    iput-object p2, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->url:Ljava/lang/String;

    iput-object p3, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->token:Ljava/lang/String;

    iput-object p4, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->file:Ljava/io/File;

    iput-boolean p5, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->force:Z

    return-void
.end method

.method private checkLimit()Z
    .locals 12

    const-string v0, "times"

    const-string v1, "time"

    iget-object v2, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->this$0:Lcom/xiaomi/push/log/LogUploader;

    invoke-static {v2}, Lcom/xiaomi/push/log/LogUploader;->access$000(Lcom/xiaomi/push/log/LogUploader;)Landroid/content/Context;

    move-result-object v2

    const/4 v3, 0x0

    const-string v4, "log.timestamp"

    invoke-virtual {v2, v4, v3}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v2

    const-string v4, "log.requst"

    const-string v5, ""

    invoke-interface {v2, v4, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    :try_start_0
    new-instance v8, Lorg/json/JSONObject;

    invoke-direct {v8, v5}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v1}, Lorg/json/JSONObject;->getLong(Ljava/lang/String;)J

    move-result-wide v6

    invoke-virtual {v8, v0}, Lorg/json/JSONObject;->getInt(Ljava/lang/String;)I

    move-result v5
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move v5, v3

    :goto_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    sub-long/2addr v8, v6

    const-wide/32 v10, 0x5265c00

    cmp-long v8, v8, v10

    if-gez v8, :cond_1

    const/16 v8, 0xa

    if-le v5, v8, :cond_0

    return v3

    :cond_0
    move v3, v5

    goto :goto_1

    :cond_1
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    :goto_1
    new-instance v5, Lorg/json/JSONObject;

    invoke-direct {v5}, Lorg/json/JSONObject;-><init>()V

    const/4 v8, 0x1

    :try_start_1
    invoke-virtual {v5, v1, v6, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    add-int/2addr v3, v8

    invoke-virtual {v5, v0, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    invoke-interface {v2}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-virtual {v5}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v4, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->commit()Z
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_2

    :catch_1
    move-exception v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "JSONException on put "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Lorg/json/JSONException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    :goto_2
    return v8
.end method


# virtual methods
.method public canExcuteNow()Z
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->this$0:Lcom/xiaomi/push/log/LogUploader;

    invoke-static {v0}, Lcom/xiaomi/push/log/LogUploader;->access$000(Lcom/xiaomi/push/log/LogUploader;)Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/network/Network;->isWIFIConnected(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->force:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->this$0:Lcom/xiaomi/push/log/LogUploader;

    invoke-static {v0}, Lcom/xiaomi/push/log/LogUploader;->access$000(Lcom/xiaomi/push/log/LogUploader;)Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/network/Network;->hasNetwork(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    return v0
.end method

.method public postProcess()V
    .locals 3

    iget-boolean v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->uploaded:Z

    const/4 v1, 0x3

    const/4 v2, 0x1

    if-nez v0, :cond_0

    iget v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->retryNum:I

    add-int/2addr v0, v2

    iput v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->retryNum:I

    iget v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->retryNum:I

    if-ge v0, v1, :cond_0

    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->this$0:Lcom/xiaomi/push/log/LogUploader;

    invoke-static {v0}, Lcom/xiaomi/push/log/LogUploader;->access$100(Lcom/xiaomi/push/log/LogUploader;)Ljava/util/concurrent/ConcurrentLinkedQueue;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/util/concurrent/ConcurrentLinkedQueue;->add(Ljava/lang/Object;)Z

    :cond_0
    iget-boolean v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->uploaded:Z

    if-nez v0, :cond_1

    iget v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->retryNum:I

    if-lt v0, v1, :cond_2

    :cond_1
    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    :cond_2
    iget-object v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->this$0:Lcom/xiaomi/push/log/LogUploader;

    iget v1, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->retryNum:I

    shl-int v1, v2, v1

    mul-int/lit16 v1, v1, 0x3e8

    int-to-long v1, v1

    invoke-static {v0, v1, v2}, Lcom/xiaomi/push/log/LogUploader;->access$200(Lcom/xiaomi/push/log/LogUploader;J)V

    return-void
.end method

.method public process()V
    .locals 4

    :try_start_0
    invoke-direct {p0}, Lcom/xiaomi/push/log/LogUploader$UploadTask;->checkLimit()Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const-string v1, "uid"

    invoke-static {}, Lcom/xiaomi/push/service/ServiceConfig;->getDeviceUUID()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "token"

    iget-object v2, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->token:Ljava/lang/String;

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "net"

    iget-object v2, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->this$0:Lcom/xiaomi/push/log/LogUploader;

    invoke-static {v2}, Lcom/xiaomi/push/log/LogUploader;->access$000(Lcom/xiaomi/push/log/LogUploader;)Landroid/content/Context;

    move-result-object v2

    invoke-static {v2}, Lcom/xiaomi/channel/commonutils/network/Network;->getActiveConnPoint(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v1, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->url:Ljava/lang/String;

    iget-object v2, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->file:Ljava/io/File;

    const-string v3, "file"

    invoke-static {v1, v0, v2, v3}, Lcom/xiaomi/channel/commonutils/network/Network;->uploadFile(Ljava/lang/String;Ljava/util/Map;Ljava/io/File;Ljava/lang/String;)Ljava/lang/String;

    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/xiaomi/push/log/LogUploader$UploadTask;->uploaded:Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-void
.end method
