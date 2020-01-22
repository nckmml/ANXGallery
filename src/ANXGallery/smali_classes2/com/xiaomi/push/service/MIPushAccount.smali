.class public Lcom/xiaomi/push/service/MIPushAccount;
.super Ljava/lang/Object;
.source "MIPushAccount.java"


# instance fields
.field public final account:Ljava/lang/String;

.field public final appId:Ljava/lang/String;

.field public final appToken:Ljava/lang/String;

.field public final envType:I

.field public final packageName:Ljava/lang/String;

.field public final security:Ljava/lang/String;

.field public final token:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/xiaomi/push/service/MIPushAccount;->account:Ljava/lang/String;

    iput-object p2, p0, Lcom/xiaomi/push/service/MIPushAccount;->token:Ljava/lang/String;

    iput-object p3, p0, Lcom/xiaomi/push/service/MIPushAccount;->security:Ljava/lang/String;

    iput-object p4, p0, Lcom/xiaomi/push/service/MIPushAccount;->appId:Ljava/lang/String;

    iput-object p5, p0, Lcom/xiaomi/push/service/MIPushAccount;->appToken:Ljava/lang/String;

    iput-object p6, p0, Lcom/xiaomi/push/service/MIPushAccount;->packageName:Ljava/lang/String;

    iput p7, p0, Lcom/xiaomi/push/service/MIPushAccount;->envType:I

    return-void
.end method

.method public static isAbTestSupported(Landroid/content/Context;)Z
    .locals 1

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p0

    const-string v0, "com.xiaomi.xmsf"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-static {}, Lcom/xiaomi/push/service/MIPushAccount;->isMIUIAlphaVersion()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isMIUIAlphaVersion()Z
    .locals 3

    :try_start_0
    const-string v0, "android.os.Build"

    const/4 v1, 0x0

    invoke-static {v1, v0}, Lcom/xiaomi/channel/commonutils/android/SystemUtils;->loadClass(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    const-string v2, "IS_ALPHA_BUILD"

    invoke-virtual {v0, v2}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->getBoolean(Ljava/lang/Object;)Z

    move-result v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return v0

    :catch_0
    const/4 v0, 0x0

    return v0
.end method

.method private static isMIUIPush(Landroid/content/Context;)Z
    .locals 1

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p0

    const-string v0, "com.xiaomi.xmsf"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method


# virtual methods
.method public toClientLoginInfo(Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;Landroid/content/Context;Lcom/xiaomi/push/service/ClientEventDispatcher;Ljava/lang/String;)Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;
    .locals 10

    invoke-virtual {p2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->pkgName:Ljava/lang/String;

    iget-object v0, p0, Lcom/xiaomi/push/service/MIPushAccount;->account:Ljava/lang/String;

    iput-object v0, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->userId:Ljava/lang/String;

    iget-object v0, p0, Lcom/xiaomi/push/service/MIPushAccount;->security:Ljava/lang/String;

    iput-object v0, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->security:Ljava/lang/String;

    iget-object v0, p0, Lcom/xiaomi/push/service/MIPushAccount;->token:Ljava/lang/String;

    iput-object v0, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->token:Ljava/lang/String;

    const-string v0, "5"

    iput-object v0, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->chid:Ljava/lang/String;

    const-string v0, "XMPUSH-PASS"

    iput-object v0, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->authMethod:Ljava/lang/String;

    const/4 v0, 0x0

    iput-boolean v0, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->kick:Z

    invoke-static {p2}, Lcom/xiaomi/push/service/MIPushAccount;->isMIUIPush(Landroid/content/Context;)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-static {p2}, Lcom/xiaomi/channel/commonutils/android/AppInfoUtils;->getRunningAppPkgNames(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    :cond_0
    const-string v1, ""

    :goto_0
    const/16 v2, 0xc

    new-array v2, v2, [Ljava/lang/Object;

    const-string v3, "sdk_ver"

    aput-object v3, v2, v0

    const/16 v3, 0x26

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const/4 v4, 0x1

    aput-object v3, v2, v4

    const/4 v3, 0x2

    const-string v5, "cpvn"

    aput-object v5, v2, v3

    const/4 v5, 0x3

    const-string v6, "3_7_0"

    aput-object v6, v2, v5

    const/4 v6, 0x4

    const-string v7, "cpvc"

    aput-object v7, v2, v6

    const/16 v7, 0x77ec

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    const/4 v8, 0x5

    aput-object v7, v2, v8

    const/4 v7, 0x6

    const-string v9, "aapn"

    aput-object v9, v2, v7

    const/4 v9, 0x7

    aput-object v1, v2, v9

    const/16 v1, 0x8

    const-string v9, "country_code"

    aput-object v9, v2, v1

    const/16 v1, 0x9

    invoke-static {p2}, Lcom/xiaomi/push/service/AppRegionStorage;->getInstance(Landroid/content/Context;)Lcom/xiaomi/push/service/AppRegionStorage;

    move-result-object v9

    invoke-virtual {v9}, Lcom/xiaomi/push/service/AppRegionStorage;->getCountryCode()Ljava/lang/String;

    move-result-object v9

    aput-object v9, v2, v1

    const/16 v1, 0xa

    const-string v9, "region"

    aput-object v9, v2, v1

    const/16 v1, 0xb

    invoke-static {p2}, Lcom/xiaomi/push/service/AppRegionStorage;->getInstance(Landroid/content/Context;)Lcom/xiaomi/push/service/AppRegionStorage;

    move-result-object v9

    invoke-virtual {v9}, Lcom/xiaomi/push/service/AppRegionStorage;->getRegion()Ljava/lang/String;

    move-result-object v9

    aput-object v9, v2, v1

    const-string v1, "%1$s:%2$s,%3$s:%4$s,%5$s:%6$s:%7$s:%8$s,%9$s:%10$s,%11$s:%12$s"

    invoke-static {v1, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->clientExtra:Ljava/lang/String;

    invoke-static {p2}, Lcom/xiaomi/push/service/MIPushAccount;->isMIUIPush(Landroid/content/Context;)Z

    move-result v1

    if-eqz v1, :cond_1

    const-string v1, "1000271"

    goto :goto_1

    :cond_1
    iget-object v1, p0, Lcom/xiaomi/push/service/MIPushAccount;->appId:Ljava/lang/String;

    :goto_1
    new-array v2, v7, [Ljava/lang/Object;

    const-string v7, "appid"

    aput-object v7, v2, v0

    aput-object v1, v2, v4

    const-string v1, "locale"

    aput-object v1, v2, v3

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/Locale;->toString()Ljava/lang/String;

    move-result-object v1

    aput-object v1, v2, v5

    const-string v1, "miid"

    aput-object v1, v2, v6

    invoke-static {p2}, Lcom/xiaomi/channel/commonutils/android/SystemUtils;->getMIID(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    aput-object v1, v2, v8

    const-string v1, "%1$s:%2$s,%3$s:%4$s,%5$s:%6$s,sync:1"

    invoke-static {v1, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->cloudExtra:Ljava/lang/String;

    invoke-static {p2}, Lcom/xiaomi/push/service/MIPushAccount;->isAbTestSupported(Landroid/content/Context;)Z

    move-result p2

    if-eqz p2, :cond_2

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->cloudExtra:Ljava/lang/String;

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    new-array v1, v3, [Ljava/lang/Object;

    const-string v2, "ab"

    aput-object v2, v1, v0

    aput-object p4, v1, v4

    const-string p4, ",%1$s:%2$s"

    invoke-static {p4, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p4

    invoke-virtual {p2, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    iput-object p2, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->cloudExtra:Ljava/lang/String;

    :cond_2
    iput-object p3, p1, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;->mClientEventDispatcher:Lcom/xiaomi/push/service/ClientEventDispatcher;

    return-object p1
.end method

.method public toClientLoginInfo(Lcom/xiaomi/push/service/XMPushService;)Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;
    .locals 3

    new-instance v0, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    invoke-direct {v0, p1}, Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;-><init>(Lcom/xiaomi/push/service/XMPushService;)V

    invoke-virtual {p1}, Lcom/xiaomi/push/service/XMPushService;->getClientEventDispatcher()Lcom/xiaomi/push/service/ClientEventDispatcher;

    move-result-object v1

    const-string v2, "c"

    invoke-virtual {p0, v0, p1, v1, v2}, Lcom/xiaomi/push/service/MIPushAccount;->toClientLoginInfo(Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;Landroid/content/Context;Lcom/xiaomi/push/service/ClientEventDispatcher;Ljava/lang/String;)Lcom/xiaomi/push/service/PushClientsManager$ClientLoginInfo;

    return-object v0
.end method
