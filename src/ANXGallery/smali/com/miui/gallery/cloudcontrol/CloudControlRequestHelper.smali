.class public Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;
.super Ljava/lang/Object;
.source "CloudControlRequestHelper.java"


# instance fields
.field private mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->mContext:Landroid/content/Context;

    return-void
.end method

.method private doPostJobs()V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/ui/AIAlbumStatusHelper;->doPostCloudControlJob()V

    return-void
.end method

.method private execRequestSyncInternal(Z)Z
    .locals 9

    iget-object v0, p0, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/gallery/util/PrivacyAgreementUtils;->isCloudServiceAgreementEnable(Landroid/content/Context;)Z

    move-result v0

    const-string v1, "CloudControlRequestHelper"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    const-string p1, "Request failed: privacy agreement disabled"

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return v2

    :cond_0
    invoke-static {}, Lcom/miui/gallery/agreement/AgreementsUtils;->isNetworkingAgreementAccepted()Z

    move-result v0

    if-nez v0, :cond_1

    const-string p1, "Request failed: CTA not confirmed."

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return v2

    :cond_1
    invoke-virtual {p0, p1}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->execRecommendRequest(Z)Z

    new-instance v0, Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;-><init>()V

    const/4 v1, 0x1

    if-eqz p1, :cond_2

    const/16 v3, 0x3ea

    goto :goto_0

    :cond_2
    move v3, v1

    :goto_0
    invoke-virtual {v0, v3}, Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;->setMethod(I)Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;

    move-result-object v0

    if-eqz p1, :cond_3

    invoke-static {}, Lcom/miui/gallery/cloud/HostManager$CloudControl;->getUrl()Ljava/lang/String;

    move-result-object v3

    goto :goto_1

    :cond_3
    invoke-static {}, Lcom/miui/gallery/cloud/HostManager$CloudControl;->getAnonymousUrl()Ljava/lang/String;

    move-result-object v3

    :goto_1
    invoke-virtual {v0, v3}, Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;->setUrl(Ljava/lang/String;)Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;

    move-result-object v0

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->getSyncToken()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;->setSyncToken(Ljava/lang/String;)Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequest$Builder;->build()Lcom/miui/gallery/cloudcontrol/CloudControlRequest;

    move-result-object v0

    :try_start_0
    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequest;->simpleExecuteSync()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/cloudcontrol/CloudControlResponse;

    if-nez v0, :cond_4

    sget-object v4, Lcom/miui/gallery/net/base/ErrorCode;->BODY_EMPTY:Lcom/miui/gallery/net/base/ErrorCode;

    const-string v5, "Response is empty"

    const-string v6, "Response is empty"

    const/4 v8, 0x0

    move-object v3, p0

    move v7, p1

    invoke-direct/range {v3 .. v8}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;ZZ)V
    :try_end_0
    .catch Lcom/miui/gallery/net/base/RequestError; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->setLastRequestTime(J)V

    return v2

    :cond_4
    :try_start_1
    invoke-direct {p0, v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleResponse(Lcom/miui/gallery/cloudcontrol/CloudControlResponse;)V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-static {v3, v4}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->setLastRequestSucceedTime(J)V
    :try_end_1
    .catch Lcom/miui/gallery/net/base/RequestError; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-static {v2, v3}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->setLastRequestTime(J)V

    return v1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception v0

    :try_start_2
    sget-object v4, Lcom/miui/gallery/net/base/ErrorCode;->HANDLE_ERROR:Lcom/miui/gallery/net/base/ErrorCode;

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v5

    const/4 v6, 0x0

    const/4 v8, 0x0

    move-object v3, p0

    move v7, p1

    invoke-direct/range {v3 .. v8}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;ZZ)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->setLastRequestTime(J)V

    return v2

    :catch_1
    move-exception v0

    :try_start_3
    invoke-virtual {v0}, Lcom/miui/gallery/net/base/RequestError;->getErrorCode()Lcom/miui/gallery/net/base/ErrorCode;

    move-result-object v4

    invoke-virtual {v0}, Lcom/miui/gallery/net/base/RequestError;->getMessage()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0}, Lcom/miui/gallery/net/base/RequestError;->getResponseData()Ljava/lang/Object;

    move-result-object v6

    const/4 v8, 0x0

    move-object v3, p0

    move v7, p1

    invoke-direct/range {v3 .. v8}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;ZZ)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->setLastRequestTime(J)V

    return v2

    :goto_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->setLastRequestTime(J)V

    throw p1
.end method

.method private getRequestIntervalMinutes()I
    .locals 4

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->getLastRequestTime()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v2, v0, v2

    if-nez v2, :cond_0

    const v0, 0x7fffffff

    return v0

    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sub-long/2addr v2, v0

    const-wide/16 v0, 0x3e8

    div-long/2addr v2, v0

    const-wide/16 v0, 0x3c

    div-long/2addr v2, v0

    long-to-int v0, v2

    return v0
.end method

.method private handleError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;ZZ)V
    .locals 3

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/net/base/ErrorCode;->name()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const-string p1, "UNKNOWN"

    :goto_0
    const/4 v0, 0x4

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    aput-object p1, v0, v1

    const/4 v1, 0x1

    aput-object p2, v0, v1

    const/4 v1, 0x2

    invoke-static {p3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    aput-object v2, v0, v1

    const/4 v1, 0x3

    invoke-static {p4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    aput-object v2, v0, v1

    const-string v1, "CloudControlRequestHelper"

    const-string v2, "Request failed, errorCode: %s, errorMessage: %s, responseData: %s, isLoggedIn: %b."

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v0

    if-nez p5, :cond_1

    invoke-direct {p0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->getRequestIntervalMinutes()I

    move-result v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    const-string v2, "request_interval(minutes)"

    invoke-interface {v0, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    const-string v1, "error_code"

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "error_message"

    invoke-interface {v0, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {p3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const-string p2, "response"

    invoke-interface {v0, p2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz p5, :cond_2

    const-string p1, "recommend_cloud_control"

    goto :goto_1

    :cond_2
    const-string p1, "cloud_control"

    :goto_1
    if-eqz p4, :cond_3

    const-string p2, "cloud_control_real_name_request_error"

    goto :goto_2

    :cond_3
    const-string p2, "cloud_control_anonymous_request_error"

    :goto_2
    invoke-static {p1, p2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private handleResponse(Lcom/miui/gallery/cloudcontrol/CloudControlResponse;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleResponse(Lcom/miui/gallery/cloudcontrol/CloudControlResponse;Z)V

    return-void
.end method

.method private handleResponse(Lcom/miui/gallery/cloudcontrol/CloudControlResponse;Z)V
    .locals 5

    invoke-virtual {p1}, Lcom/miui/gallery/cloudcontrol/CloudControlResponse;->getFeatureProfiles()Ljava/util/ArrayList;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 v0, 0x0

    invoke-virtual {p1}, Lcom/miui/gallery/cloudcontrol/CloudControlResponse;->getFeatureProfiles()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/cloudcontrol/FeatureProfile;

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->getInstance()Lcom/miui/gallery/cloudcontrol/CloudControlManager;

    move-result-object v3

    invoke-virtual {v3, v2}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->insertToCache(Lcom/miui/gallery/cloudcontrol/FeatureProfile;)V

    iget-object v3, p0, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->mContext:Landroid/content/Context;

    invoke-static {v3, v2}, Lcom/miui/gallery/cloudcontrol/CloudControlDBHelper;->tryInsertToDB(Landroid/content/Context;Lcom/miui/gallery/cloudcontrol/FeatureProfile;)I

    move-result v3

    if-nez v3, :cond_0

    const/4 v0, 0x1

    invoke-static {v2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "CloudControlRequestHelper"

    const-string v4, "Persist error: %s"

    invoke-static {v3, v4, v2}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    if-eqz v0, :cond_2

    if-nez p2, :cond_2

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v0

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "response"

    invoke-interface {v0, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "cloud_control"

    const-string v2, "cloud_control_persist_error"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    :cond_2
    invoke-virtual {p1}, Lcom/miui/gallery/cloudcontrol/CloudControlResponse;->getSyncToken()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_3

    if-nez p2, :cond_3

    invoke-virtual {p1}, Lcom/miui/gallery/cloudcontrol/CloudControlResponse;->getSyncToken()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/preference/GalleryPreferences$CloudControl;->setSyncToken(Ljava/lang/String;)V

    :cond_3
    return-void
.end method


# virtual methods
.method public execRecommendRequest()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/gallery/util/SyncUtil;->existXiaomiAccount(Landroid/content/Context;)Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->execRecommendRequest(Z)Z

    move-result v0

    return v0
.end method

.method public execRecommendRequest(Z)Z
    .locals 9

    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isDeviceSupportStoryFunction()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    const/4 v0, 0x1

    if-eqz p1, :cond_1

    new-instance v2, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;

    invoke-direct {v2}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;-><init>()V

    invoke-virtual {v2, v0}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;->setMethod(Z)Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;

    move-result-object v2

    invoke-static {}, Lcom/miui/gallery/cloud/HostManager$RecommendList;->getUrl()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;->setUrl(Ljava/lang/String;)Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;->build()Lcom/miui/gallery/cloudcontrol/RecommendListRequest;

    move-result-object v2

    goto :goto_0

    :cond_1
    new-instance v2, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;

    invoke-direct {v2}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;-><init>()V

    invoke-virtual {v2, v1}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;->setMethod(Z)Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;

    move-result-object v2

    invoke-static {}, Lcom/miui/gallery/cloud/HostManager$RecommendList;->getAnonymousUrl()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;->setUrl(Ljava/lang/String;)Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;

    move-result-object v2

    invoke-virtual {v2}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest$Builder;->build()Lcom/miui/gallery/cloudcontrol/RecommendListRequest;

    move-result-object v2

    :goto_0
    :try_start_0
    invoke-virtual {v2}, Lcom/miui/gallery/cloudcontrol/RecommendListRequest;->simpleExecuteSync()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/cloudcontrol/RecommendListResponse;

    if-nez v2, :cond_2

    sget-object v4, Lcom/miui/gallery/net/base/ErrorCode;->BODY_EMPTY:Lcom/miui/gallery/net/base/ErrorCode;

    const-string v5, "Response is empty"

    const-string v6, "Response is empty"

    const/4 v8, 0x1

    move-object v3, p0

    move v7, p1

    invoke-direct/range {v3 .. v8}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;ZZ)V

    return v1

    :cond_2
    invoke-direct {p0, v2, v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleResponse(Lcom/miui/gallery/cloudcontrol/CloudControlResponse;Z)V
    :try_end_0
    .catch Lcom/miui/gallery/net/base/RequestError; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return v0

    :catch_0
    move-exception v0

    sget-object v3, Lcom/miui/gallery/net/base/ErrorCode;->HANDLE_ERROR:Lcom/miui/gallery/net/base/ErrorCode;

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v4

    const/4 v5, 0x0

    const/4 v7, 0x1

    move-object v2, p0

    move v6, p1

    invoke-direct/range {v2 .. v7}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;ZZ)V

    return v1

    :catch_1
    move-exception v0

    invoke-virtual {v0}, Lcom/miui/gallery/net/base/RequestError;->getErrorCode()Lcom/miui/gallery/net/base/ErrorCode;

    move-result-object v3

    invoke-virtual {v0}, Lcom/miui/gallery/net/base/RequestError;->getMessage()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0}, Lcom/miui/gallery/net/base/RequestError;->getResponseData()Ljava/lang/Object;

    move-result-object v5

    const/4 v7, 0x1

    move-object v2, p0

    move v6, p1

    invoke-direct/range {v2 .. v7}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->handleError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;ZZ)V

    return v1
.end method

.method public execRequestSync()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/gallery/util/SyncUtil;->existXiaomiAccount(Landroid/content/Context;)Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->execRequestSync(Z)Z

    move-result v0

    return v0
.end method

.method public execRequestSync(Z)Z
    .locals 3

    invoke-direct {p0, p1}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->execRequestSyncInternal(Z)Z

    move-result p1

    if-eqz p1, :cond_0

    :try_start_0
    invoke-direct {p0}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->doPostJobs()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    const-string v1, "CloudControlRequestHelper"

    const-string v2, "Error occurred while executing post cloud control request job, %s"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_0
    :goto_0
    return p1
.end method
