.class public Lcom/miui/gallery/search/statistics/SearchStatReportService;
.super Lcom/miui/gallery/service/IntentServiceBase;
.source "SearchStatReportService.java"


# static fields
.field public static EXTRA_LOG_ITEM:Ljava/lang/String; = "log_item"

.field public static EXTRA_UPLOAD:Ljava/lang/String; = "upload"


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/service/IntentServiceBase;-><init>()V

    return-void
.end method

.method private uploadStatEvents(Lcom/miui/gallery/search/statistics/SearchStatLogItem;)V
    .locals 9

    const-string v0, "Done uploading [%d] items, delete uploaded events"

    const-string v1, "SearchStatReportService"

    invoke-static {}, Lcom/miui/gallery/search/utils/SearchUtils;->getXiaomiId()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_6

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/gallery/util/PrivacyAgreementUtils;->isCloudServiceAgreementEnable(Landroid/content/Context;)Z

    move-result v3

    if-eqz v3, :cond_6

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isNetworkConnected()Z

    move-result v3

    if-eqz v3, :cond_6

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v3

    if-eqz v3, :cond_0

    goto/16 :goto_6

    :cond_0
    invoke-static {}, Lcom/miui/gallery/search/statistics/SearchStatStorageHelper;->getSavedLogs()Ljava/util/ArrayList;

    move-result-object v3

    if-eqz v3, :cond_1

    invoke-virtual {v3}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_2

    :cond_1
    if-nez p1, :cond_2

    return-void

    :cond_2
    if-nez v3, :cond_3

    new-instance v3, Ljava/util/ArrayList;

    const/4 v4, 0x1

    invoke-direct {v3, v4}, Ljava/util/ArrayList;-><init>(I)V

    :cond_3
    if-eqz p1, :cond_4

    invoke-virtual {v3, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_4
    const/4 p1, 0x0

    move v4, p1

    :goto_0
    :try_start_0
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v5

    if-ge v4, v5, :cond_5

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v5

    add-int/lit8 v6, v4, 0xa

    invoke-static {v5, v6}, Ljava/lang/Math;->min(II)I

    move-result v5

    invoke-virtual {v3, v4, v5}, Ljava/util/ArrayList;->subList(II)Ljava/util/List;

    move-result-object v6
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    :try_start_1
    const-string v7, "uploading batch statistic events [%d-%d][%s]..."

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-static {v1, v7, v4, v8, v6}, Lcom/miui/gallery/search/utils/SearchLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    new-instance v4, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;

    invoke-direct {v4}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;-><init>()V

    const/16 v7, 0x3ea

    invoke-virtual {v4, v7}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->setMethod(I)Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;

    move-result-object v4

    invoke-static {v2}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->getDefaultUserPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v4, v7}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->setUserPath(Ljava/lang/String;)Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;

    move-result-object v4

    invoke-virtual {v4, v2}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->setUserId(Ljava/lang/String;)Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;

    move-result-object v4

    const-string v7, "actionlog"

    invoke-virtual {v4, v7}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->setQueryAppendPath(Ljava/lang/String;)Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;

    move-result-object v4

    const-string v7, "data"

    invoke-static {v6}, Lcom/miui/gallery/search/statistics/SearchStatUtils;->getDataJson(Ljava/util/List;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v4, v7, v6}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->addQueryParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;

    move-result-object v4

    invoke-virtual {v4, p1}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->setReportError(Z)Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest$Builder;->build()Lcom/miui/gallery/search/core/source/server/ServerSearchRequest;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/search/core/source/server/ServerSearchRequest;->executeSync()[Ljava/lang/Object;

    const-string v4, "Upload batch succeed"

    invoke-static {v1, v4}, Lcom/miui/gallery/search/utils/SearchLog;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/search/statistics/SearchStatStorageHelper;->clearSavedLogs()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception v4

    :try_start_2
    const-string v6, "Upload batch failed, %s"

    invoke-static {v1, v6, v4}, Lcom/miui/gallery/search/utils/SearchLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :goto_1
    move v4, v5

    goto :goto_0

    :goto_2
    :try_start_3
    throw p1
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :cond_5
    :goto_3
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {v1, v0, p1}, Lcom/miui/gallery/search/utils/SearchLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/search/statistics/SearchStatStorageHelper;->clearSavedLogs()V

    goto :goto_4

    :catchall_1
    move-exception p1

    goto :goto_5

    :catch_1
    move-exception p1

    :try_start_4
    invoke-static {v1, p1}, Lcom/miui/gallery/search/utils/SearchLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    goto :goto_3

    :goto_4
    return-void

    :goto_5
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-static {v1, v0, v2}, Lcom/miui/gallery/search/utils/SearchLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/search/statistics/SearchStatStorageHelper;->clearSavedLogs()V

    throw p1

    :cond_6
    :goto_6
    if-eqz p1, :cond_7

    invoke-static {p1}, Lcom/miui/gallery/search/statistics/SearchStatStorageHelper;->saveLogItem(Lcom/miui/gallery/search/statistics/SearchStatLogItem;)I

    :cond_7
    return-void
.end method


# virtual methods
.method protected getNotification()Landroid/app/Notification;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/search/statistics/SearchStatReportService;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/NotificationHelper;->getEmptyNotification(Landroid/content/Context;)Landroid/app/Notification;

    move-result-object v0

    return-object v0
.end method

.method protected getNotificationId()I
    .locals 1

    const/4 v0, 0x7

    return v0
.end method

.method protected onHandleIntent(Landroid/content/Intent;)V
    .locals 3

    invoke-super {p0, p1}, Lcom/miui/gallery/service/IntentServiceBase;->onHandleIntent(Landroid/content/Intent;)V

    const-string v0, "SearchStatReportService"

    if-nez p1, :cond_0

    const-string p1, "Received a NULL intent!"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    const/4 v1, 0x0

    sget-object v2, Lcom/miui/gallery/search/statistics/SearchStatReportService;->EXTRA_LOG_ITEM:Ljava/lang/String;

    invoke-virtual {p1, v2}, Landroid/content/Intent;->getSerializableExtra(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v2

    if-eqz v2, :cond_1

    sget-object v1, Lcom/miui/gallery/search/statistics/SearchStatReportService;->EXTRA_LOG_ITEM:Ljava/lang/String;

    invoke-virtual {p1, v1}, Landroid/content/Intent;->getSerializableExtra(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/search/statistics/SearchStatLogItem;

    const-string v2, "On log [%s]"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/search/utils/SearchLog;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_1
    sget-object v0, Lcom/miui/gallery/search/statistics/SearchStatReportService;->EXTRA_UPLOAD:Ljava/lang/String;

    const/4 v2, 0x0

    invoke-virtual {p1, v0, v2}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-direct {p0, v1}, Lcom/miui/gallery/search/statistics/SearchStatReportService;->uploadStatEvents(Lcom/miui/gallery/search/statistics/SearchStatLogItem;)V

    goto :goto_0

    :cond_2
    if-eqz v1, :cond_3

    invoke-static {v1}, Lcom/miui/gallery/search/statistics/SearchStatStorageHelper;->saveLogItem(Lcom/miui/gallery/search/statistics/SearchStatLogItem;)I

    :cond_3
    :goto_0
    return-void
.end method
