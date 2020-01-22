.class public Lcom/xiaomi/micloudsdk/request/utils/RequestInjector;
.super Ljava/lang/Object;
.source "RequestInjector.java"


# direct methods
.method public static checkResponse(Landroid/content/Context;Ljava/lang/String;)V
    .locals 2

    const-string v0, "RequestInjector"

    const-string v1, "checkResponse"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-static {p1}, Lcom/xiaomi/micloudsdk/request/utils/RequestInjector;->isPrivacyError(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-static {p0}, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;->notifyPrivacyDenied(Landroid/content/Context;)V

    :cond_1
    return-void
.end method

.method public static handleCloudServerException(Landroid/content/Context;Lcom/xiaomi/micloudsdk/exception/CloudServerException;)V
    .locals 2

    iget v0, p1, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->code:I

    const/16 v1, 0x1f7

    if-ne v0, v1, :cond_0

    iget v0, p1, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->retryTime:I

    if-lez v0, :cond_0

    new-instance v0, Landroid/content/Intent;

    const-string v1, "com.xiaomi.action.DATA_IN_TRANSFER"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v1, "com.miui.cloudservice"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    iget p1, p1, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->retryTime:I

    const-string v1, "retryTime"

    invoke-virtual {v0, v1, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    invoke-virtual {p0, v0}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;)V

    goto :goto_0

    :cond_0
    iget p1, p1, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->code:I

    const v0, 0xcb23

    if-ne p1, v0, :cond_1

    invoke-static {p0}, Lmicloud/compat/v18/utils/PrivacyIntentHelperCompat;->notifyPrivacyDenied(Landroid/content/Context;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private static isPrivacyError(Ljava/lang/String;)Z
    .locals 2

    const/4 v0, 0x0

    :try_start_0
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    const-string p0, "code"

    invoke-virtual {v1, p0, v0}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result p0
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    const v1, 0xcb23

    if-ne p0, v1, :cond_0

    const/4 p0, 0x1

    return p0

    :catch_0
    move-exception p0

    invoke-virtual {p0}, Lorg/json/JSONException;->printStackTrace()V

    :cond_0
    return v0
.end method
