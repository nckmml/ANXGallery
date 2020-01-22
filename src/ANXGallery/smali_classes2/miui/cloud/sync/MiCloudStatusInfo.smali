.class public Lmiui/cloud/sync/MiCloudStatusInfo;
.super Ljava/lang/Object;
.source "MiCloudStatusInfo.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/cloud/sync/MiCloudStatusInfo$ItemInfo;,
        Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;
    }
.end annotation


# instance fields
.field private mQuotaInfo:Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;

.field private mUserId:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lmiui/cloud/sync/MiCloudStatusInfo;->mUserId:Ljava/lang/String;

    return-void
.end method

.method public static fromUserData(Landroid/content/Context;)Lmiui/cloud/sync/MiCloudStatusInfo;
    .locals 5

    invoke-static {p0}, Landroid/accounts/AccountManager;->get(Landroid/content/Context;)Landroid/accounts/AccountManager;

    move-result-object v0

    invoke-static {p0}, Lmiui/accounts/ExtraAccountManager;->getXiaomiAccount(Landroid/content/Context;)Landroid/accounts/Account;

    move-result-object p0

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    const-string v1, "extra_micloud_status_info_quota"

    invoke-virtual {v0, p0, v1}, Landroid/accounts/AccountManager;->getUserData(Landroid/accounts/Account;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lmiui/cloud/sync/MiCloudStatusInfo;

    iget-object v4, p0, Landroid/accounts/Account;->name:Ljava/lang/String;

    invoke-direct {v3, v4}, Lmiui/cloud/sync/MiCloudStatusInfo;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Lmiui/cloud/sync/MiCloudStatusInfo;->parseQuotaString(Ljava/lang/String;)V

    invoke-virtual {v3}, Lmiui/cloud/sync/MiCloudStatusInfo;->getQuotaInfo()Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;

    move-result-object v2

    if-eqz v2, :cond_1

    invoke-virtual {v2}, Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;->getWarn()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_2

    :cond_1
    const-string v2, "MiCloudStatusInfo"

    const-string v4, "deserialize failed"

    invoke-static {v2, v4}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    const-string v2, ""

    invoke-virtual {v0, p0, v1, v2}, Landroid/accounts/AccountManager;->setUserData(Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;)V

    :cond_2
    return-object v3
.end method


# virtual methods
.method public getQuotaInfo()Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;
    .locals 1

    iget-object v0, p0, Lmiui/cloud/sync/MiCloudStatusInfo;->mQuotaInfo:Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;

    return-object v0
.end method

.method public parseQuotaString(Ljava/lang/String;)V
    .locals 3

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    const-string v2, "MiCloudStatusInfo"

    if-eqz v0, :cond_0

    const-string p1, "parseQuotaString() quota is empty."

    invoke-static {v2, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    iput-object v1, p0, Lmiui/cloud/sync/MiCloudStatusInfo;->mQuotaInfo:Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;

    return-void

    :cond_0
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-static {p0, v0}, Lcom/xiaomi/micloudsdk/cloudinfo/utils/CloudInfoUtils;->getQuotaInfo(Lmiui/cloud/sync/MiCloudStatusInfo;Lorg/json/JSONObject;)Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;

    move-result-object p1

    iput-object p1, p0, Lmiui/cloud/sync/MiCloudStatusInfo;->mQuotaInfo:Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const-string p1, "catch JSONException in parseQuotaString()"

    invoke-static {v2, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    iput-object v1, p0, Lmiui/cloud/sync/MiCloudStatusInfo;->mQuotaInfo:Lmiui/cloud/sync/MiCloudStatusInfo$QuotaInfo;

    :goto_0
    return-void
.end method
