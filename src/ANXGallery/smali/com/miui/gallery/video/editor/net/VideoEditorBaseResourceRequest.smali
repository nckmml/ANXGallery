.class public abstract Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;
.super Lcom/miui/gallery/net/BaseGalleryRequest;
.source "VideoEditorBaseResourceRequest.java"


# instance fields
.field protected type:I


# direct methods
.method public constructor <init>(I)V
    .locals 4

    const/4 v0, 0x0

    const-string v1, "https://i.mi.com/gallery/public/resource/info"

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/net/BaseGalleryRequest;-><init>(ILjava/lang/String;)V

    iput p1, p0, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->type:I

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->getParentId()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->toString(J)Ljava/lang/String;

    move-result-object p1

    const-string v0, "id"

    invoke-virtual {p0, v0, p1}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->addParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/net/base/BaseRequest;

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->setUseCache(Z)Lcom/miui/gallery/net/base/BaseRequest;

    const-wide v0, 0x9a7ec800L

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->setCacheExpires(J)Lcom/miui/gallery/net/base/BaseRequest;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const-wide/32 v2, 0x5265c00

    add-long/2addr v0, v2

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->setCacheSoftTtl(J)Lcom/miui/gallery/net/base/BaseRequest;

    return-void
.end method


# virtual methods
.method protected abstract getParentId()J
.end method

.method protected abstract newLocalResource()Lcom/miui/gallery/video/editor/model/LocalResource;
.end method

.method protected onRequestSuccess(Lorg/json/JSONObject;)V
    .locals 9
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    const-string v0, "id"

    :try_start_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    const-string v2, "galleryResourceInfoList"

    invoke-virtual {p1, v2}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v2

    const-string v3, "expireAt"

    invoke-virtual {p1, v3}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;)J

    move-result-wide v3

    const-wide/16 v5, 0x0

    cmp-long v5, v3, v5

    if-eqz v5, :cond_0

    invoke-virtual {p0, v3, v4}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->setCacheSoftTtl(J)Lcom/miui/gallery/net/base/BaseRequest;

    :cond_0
    const/4 v3, 0x0

    if-eqz v2, :cond_1

    move v4, v3

    :goto_0
    invoke-virtual {v2}, Lorg/json/JSONArray;->length()I

    move-result v5

    if-ge v4, v5, :cond_1

    invoke-virtual {v2, v4}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v5

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->newLocalResource()Lcom/miui/gallery/video/editor/model/LocalResource;

    move-result-object v6

    invoke-virtual {v5, v0}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;)J

    move-result-wide v7

    iput-wide v7, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->id:J

    invoke-virtual {v5, v0}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;)J

    move-result-wide v7

    iput-wide v7, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->parent:J

    const-string v7, "icon"

    invoke-virtual {v5, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    iput-object v7, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->icon:Ljava/lang/String;

    const-string v7, "text"

    invoke-virtual {v5, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    iput-object v7, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->label:Ljava/lang/String;

    const-string v7, "extraInfo"

    invoke-virtual {v5, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    iput-object v7, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->extra:Ljava/lang/String;

    const-string v7, "type"

    invoke-virtual {v5, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->type:Ljava/lang/String;

    new-instance v5, Lorg/json/JSONObject;

    iget-object v7, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->extra:Ljava/lang/String;

    invoke-direct {v5, v7}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    const-string v7, "nameKey"

    invoke-virtual {v5, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    iput-object v5, v6, Lcom/miui/gallery/video/editor/model/LocalResource;->nameKey:Ljava/lang/String;

    invoke-virtual {p0, v6}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->setResult(Lcom/miui/gallery/video/editor/model/LocalResource;)V

    invoke-interface {v1, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/Object;

    aput-object v1, v0, v3

    invoke-virtual {p0, v0}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->deliverResponse([Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/gson/JsonParseException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception v0

    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    sget-object v1, Lcom/miui/gallery/net/base/ErrorCode;->HANDLE_ERROR:Lcom/miui/gallery/net/base/ErrorCode;

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v1, v0, p1}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->deliverError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_1

    :catch_1
    move-exception v0

    invoke-virtual {v0}, Lcom/google/gson/JsonParseException;->printStackTrace()V

    sget-object v1, Lcom/miui/gallery/net/base/ErrorCode;->PARSE_ERROR:Lcom/miui/gallery/net/base/ErrorCode;

    invoke-virtual {v0}, Lcom/google/gson/JsonParseException;->getMessage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v1, v0, p1}, Lcom/miui/gallery/video/editor/net/VideoEditorBaseResourceRequest;->deliverError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_1
    return-void
.end method

.method protected abstract setResult(Lcom/miui/gallery/video/editor/model/LocalResource;)V
.end method
