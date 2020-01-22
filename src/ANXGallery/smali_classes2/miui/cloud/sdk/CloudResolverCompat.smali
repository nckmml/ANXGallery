.class public Lmiui/cloud/sdk/CloudResolverCompat;
.super Ljava/lang/Object;
.source "CloudResolverCompat.java"


# static fields
.field private static final SDK_VERSION:Lmiui/cloud/sdk/LazyValue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lmiui/cloud/sdk/LazyValue<",
            "Landroid/content/Context;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lmiui/cloud/sdk/CloudResolverCompat$1;

    invoke-direct {v0}, Lmiui/cloud/sdk/CloudResolverCompat$1;-><init>()V

    sput-object v0, Lmiui/cloud/sdk/CloudResolverCompat;->SDK_VERSION:Lmiui/cloud/sdk/LazyValue;

    return-void
.end method

.method public static getResumeTime(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)J
    .locals 2

    invoke-static {p0}, Lmiui/cloud/sdk/CloudResolverCompat;->getSDKVersion(Landroid/content/Context;)I

    move-result v0

    const/16 v1, 0x1d

    if-ge v0, v1, :cond_0

    :try_start_0
    invoke-static {p0, p2}, Lcom/xiaomi/micloudsdk/sync/MiCloudResolver;->getResumeTime(Landroid/content/Context;Ljava/lang/String;)J

    move-result-wide p0

    return-wide p0

    :cond_0
    invoke-static {p1}, Lmiui/cloud/content/MiSyncPolicyResolver;->getMiSyncPauseToTime(Landroid/accounts/Account;)J

    move-result-wide p0
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    return-wide p0

    :catch_0
    move-exception p0

    const-string p1, "CloudResolverCompat"

    const-string p2, "getResumeTime"

    invoke-static {p1, p2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const-wide/16 p0, -0x1

    return-wide p0
.end method

.method public static getSDKVersion(Landroid/content/Context;)I
    .locals 1

    sget-object v0, Lmiui/cloud/sdk/CloudResolverCompat;->SDK_VERSION:Lmiui/cloud/sdk/LazyValue;

    invoke-virtual {v0, p0}, Lmiui/cloud/sdk/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0
.end method

.method public static isSyncPaused(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)Z
    .locals 2

    invoke-static {p0, p1, p2}, Lmiui/cloud/sdk/CloudResolverCompat;->getResumeTime(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)J

    move-result-wide p0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    cmp-long p0, p0, v0

    if-lez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static pauseSync(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;J)Z
    .locals 2

    invoke-static {p0}, Lmiui/cloud/sdk/CloudResolverCompat;->getSDKVersion(Landroid/content/Context;)I

    move-result v0

    const/16 v1, 0x1d

    if-ge v0, v1, :cond_0

    :try_start_0
    invoke-static {p0, p1, p2, p3, p4}, Lcom/xiaomi/micloudsdk/sync/MiCloudResolver;->pauseSync(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;J)V

    goto :goto_0

    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    add-long/2addr v0, p3

    invoke-static {p1, v0, v1}, Lmiui/cloud/content/MiSyncPolicyResolver;->setMiSyncPauseToTime(Landroid/accounts/Account;J)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    const/4 p0, 0x1

    return p0

    :catch_0
    move-exception p0

    const-string p1, "CloudResolverCompat"

    const-string p2, "pauseSync"

    invoke-static {p1, p2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p0, 0x0

    return p0
.end method

.method public static resumeSync(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)Z
    .locals 2

    invoke-static {p0}, Lmiui/cloud/sdk/CloudResolverCompat;->getSDKVersion(Landroid/content/Context;)I

    move-result v0

    const/16 v1, 0x1d

    if-ge v0, v1, :cond_0

    :try_start_0
    invoke-static {p0, p1, p2}, Lcom/xiaomi/micloudsdk/sync/MiCloudResolver;->resumeSync(Landroid/content/Context;Landroid/accounts/Account;Ljava/lang/String;)V

    goto :goto_0

    :cond_0
    invoke-static {p1}, Lmiui/cloud/content/MiSyncPolicyResolver;->setMiSyncResume(Landroid/accounts/Account;)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    const/4 p0, 0x1

    return p0

    :catch_0
    move-exception p0

    const-string p1, "CloudResolverCompat"

    const-string p2, "resumeSync"

    invoke-static {p1, p2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p0, 0x0

    return p0
.end method
