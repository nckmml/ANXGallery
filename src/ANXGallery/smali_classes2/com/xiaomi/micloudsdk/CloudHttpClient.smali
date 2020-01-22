.class public Lcom/xiaomi/micloudsdk/CloudHttpClient;
.super Lcom/xiaomi/micloudsdk/request/CloudHttpClient;
.source "CloudHttpClient.java"


# annotations
.annotation runtime Ljava/lang/Deprecated;
.end annotation


# direct methods
.method private constructor <init>(Lorg/apache/http/client/HttpClient;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/xiaomi/micloudsdk/request/CloudHttpClient;-><init>(Lorg/apache/http/client/HttpClient;)V

    return-void
.end method

.method public static newInstance()Lcom/xiaomi/micloudsdk/CloudHttpClient;
    .locals 2

    new-instance v0, Lcom/xiaomi/micloudsdk/CloudHttpClient;

    invoke-static {}, Lcom/xiaomi/micloudsdk/CloudHttpClient;->initClient()Lorg/apache/http/impl/client/DefaultHttpClient;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/xiaomi/micloudsdk/CloudHttpClient;-><init>(Lorg/apache/http/client/HttpClient;)V

    return-object v0
.end method
