.class public Lcom/xiaomi/micloudsdk/stat/MiCloudErrorStatReason;
.super Ljava/lang/Object;
.source "MiCloudErrorStatReason.java"


# direct methods
.method public static getLocalExceptionReasonForStats(Lcom/xiaomi/micloudsdk/exception/SyncLocalException;)Ljava/lang/String;
    .locals 0

    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/exception/SyncLocalException;->getErrorCode()I

    move-result p0

    invoke-static {p0}, Lcom/xiaomi/micloudsdk/stat/MiCloudErrorStatReason;->getLocalExceptionReasonForStatsByCode(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static getLocalExceptionReasonForStatsByCode(I)Ljava/lang/String;
    .locals 0

    packed-switch p0, :pswitch_data_0

    packed-switch p0, :pswitch_data_1

    const-string p0, "sync_unknown_error"

    goto :goto_0

    :pswitch_0
    const-string p0, "wlan_only_error"

    goto :goto_0

    :pswitch_1
    const-string p0, "cloud_space_full"

    goto :goto_0

    :pswitch_2
    const-string p0, "gdpr_deny"

    goto :goto_0

    :pswitch_3
    const-string p0, "secure_space_limit"

    goto :goto_0

    :pswitch_4
    const-string p0, "permission_limit"

    goto :goto_0

    :pswitch_5
    const-string p0, "activated_fail"

    goto :goto_0

    :pswitch_6
    const-string p0, "cta_error"

    :goto_0
    return-object p0

    :pswitch_data_0
    .packed-switch 0x3e8
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x7d0
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static getServerExceptionReasonForStats(Lcom/xiaomi/micloudsdk/exception/CloudServerException;)Ljava/lang/String;
    .locals 2

    iget v0, p0, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->statusCode:I

    const/16 v1, -0x2711

    if-eq v0, v1, :cond_6

    const/16 v1, 0x193

    if-eq v0, v1, :cond_5

    const/16 v1, 0x196

    if-eq v0, v1, :cond_4

    const/16 v1, 0x190

    if-eq v0, v1, :cond_3

    const/16 v1, 0x191

    if-eq v0, v1, :cond_2

    iget v0, p0, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->code:I

    const v1, 0xcb23

    if-ne v0, v1, :cond_0

    const-string p0, "gdpr_deny"

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->is5xxServerException()Z

    move-result p0

    if-eqz p0, :cond_1

    const-string p0, "server_5xx_error"

    goto :goto_0

    :cond_1
    const-string p0, "sync_unknown_error"

    goto :goto_0

    :cond_2
    const-string p0, "sever_error_unauthorized"

    goto :goto_0

    :cond_3
    const-string p0, "sever_error_bad_request"

    goto :goto_0

    :cond_4
    const-string p0, "sever_error_not-acceptable"

    goto :goto_0

    :cond_5
    const-string p0, "sever_error_forbidden"

    goto :goto_0

    :cond_6
    iget p0, p0, Lcom/xiaomi/micloudsdk/exception/CloudServerException;->code:I

    invoke-static {p0}, Lcom/xiaomi/micloudsdk/stat/MiCloudErrorStatReason;->getLocalExceptionReasonForStatsByCode(I)Ljava/lang/String;

    move-result-object p0

    :goto_0
    return-object p0
.end method

.method public static getSyncResultErrorReasonForStats(Landroid/content/SyncResult;)Ljava/lang/String;
    .locals 0

    invoke-virtual {p0}, Landroid/content/SyncResult;->hasHardError()Z

    move-result p0

    if-eqz p0, :cond_0

    const-string p0, "sync_hard_error"

    goto :goto_0

    :cond_0
    const-string p0, "sync_soft_error"

    :goto_0
    return-object p0
.end method
