.class public Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;
.super Lcom/miui/gallery/net/BaseGalleryRequest;
.source "CommonGalleryRequest.java"


# instance fields
.field private mDataType:Ljava/lang/reflect/Type;


# direct methods
.method public constructor <init>(ILjava/lang/String;Ljava/lang/reflect/Type;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/net/BaseGalleryRequest;-><init>(ILjava/lang/String;)V

    iput-object p3, p0, Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;->mDataType:Ljava/lang/reflect/Type;

    return-void
.end method


# virtual methods
.method protected onRequestSuccess(Lorg/json/JSONObject;)V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    if-eqz p1, :cond_0

    :try_start_0
    invoke-virtual {p1}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;->mDataType:Ljava/lang/reflect/Type;

    invoke-static {v0, v1}, Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;->fromJson(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;

    move-result-object v0

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    invoke-virtual {p0, v1}, Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;->deliverResponse([Ljava/lang/Object;)V

    goto :goto_0

    :cond_0
    sget-object v0, Lcom/miui/gallery/net/base/ErrorCode;->BODY_EMPTY:Lcom/miui/gallery/net/base/ErrorCode;

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1, v1}, Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;->deliverError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/gson/JsonParseException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    sget-object v1, Lcom/miui/gallery/net/base/ErrorCode;->HANDLE_ERROR:Lcom/miui/gallery/net/base/ErrorCode;

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v1, v0, p1}, Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;->deliverError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_0

    :catch_1
    move-exception v0

    invoke-virtual {v0}, Lcom/google/gson/JsonParseException;->printStackTrace()V

    sget-object v1, Lcom/miui/gallery/net/base/ErrorCode;->PARSE_ERROR:Lcom/miui/gallery/net/base/ErrorCode;

    invoke-virtual {v0}, Lcom/google/gson/JsonParseException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v1, v0, p1}, Lcom/miui/gallery/cloud/card/network/CommonGalleryRequest;->deliverError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_0
    return-void
.end method
