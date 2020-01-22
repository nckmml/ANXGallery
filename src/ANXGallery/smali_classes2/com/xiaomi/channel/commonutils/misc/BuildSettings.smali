.class public Lcom/xiaomi/channel/commonutils/misc/BuildSettings;
.super Ljava/lang/Object;
.source "BuildSettings.java"


# static fields
.field public static final IsBetaBuild:Z

.field public static final IsDebugBuild:Z

.field public static final IsDefaultChannel:Z

.field public static final IsForYYBuild:Z

.field public static final IsLogableBuild:Z

.field public static final IsRCBuild:Z

.field public static IsTestBuild:Z

.field public static final ReleaseChannel:Ljava/lang/String;

.field private static envType:I


# direct methods
.method static constructor <clinit>()V
    .locals 5

    sget-boolean v0, Lcom/xiaomi/channel/commonutils/misc/DebugSwitch;->sDebugServerHost:Z

    const-string v1, "ONEBOX"

    if-eqz v0, :cond_0

    move-object v0, v1

    goto :goto_0

    :cond_0
    const-string v0, "@SHIP.TO.2A2FE0D7@"

    :goto_0
    sput-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    const-string v2, "2A2FE0D7"

    invoke-virtual {v0, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    sput-boolean v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsDefaultChannel:Z

    sget-boolean v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsDefaultChannel:Z

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-nez v0, :cond_2

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    const-string v4, "DEBUG"

    invoke-virtual {v4, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_1

    :cond_1
    move v0, v2

    goto :goto_2

    :cond_2
    :goto_1
    move v0, v3

    :goto_2
    sput-boolean v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsDebugBuild:Z

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    const-string v4, "LOGABLE"

    invoke-virtual {v4, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    sput-boolean v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsLogableBuild:Z

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    const-string v4, "YY"

    invoke-virtual {v0, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    sput-boolean v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsForYYBuild:Z

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    const-string v4, "TEST"

    invoke-virtual {v0, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    sput-boolean v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsTestBuild:Z

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    const-string v4, "BETA"

    invoke-virtual {v4, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    sput-boolean v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsBetaBuild:Z

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    if-eqz v0, :cond_3

    const-string v4, "RC"

    invoke-virtual {v0, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    move v2, v3

    :cond_3
    sput-boolean v2, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->IsRCBuild:Z

    sput v3, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    const-string v2, "SANDBOX"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    const/4 v0, 0x2

    sput v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    goto :goto_3

    :cond_4
    sget-object v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->ReleaseChannel:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_5

    const/4 v0, 0x3

    sput v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    goto :goto_3

    :cond_5
    sput v3, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    :goto_3
    return-void
.end method

.method public static IsOneBoxBuild()Z
    .locals 2

    sget v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    const/4 v1, 0x3

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public static IsSandBoxBuild()Z
    .locals 2

    sget v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public static getEnvType()I
    .locals 1

    sget v0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    return v0
.end method

.method public static setEnvType(I)V
    .locals 0

    sput p0, Lcom/xiaomi/channel/commonutils/misc/BuildSettings;->envType:I

    return-void
.end method
