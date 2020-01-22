.class public Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;
.super Ljava/lang/Object;
.source "MiCloudSdkBuild.java"


# static fields
.field public static final CURRENT_VERSION:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    invoke-static {}, Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;->getCurrentVersion()I

    move-result v0

    sput v0, Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;->CURRENT_VERSION:I

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "MiCloudSdk version:"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v1, Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;->CURRENT_VERSION:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "MiCloudSdkBuild"

    invoke-static {v1, v0}, Lmiui/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private static getCurrentVersion()I
    .locals 2

    const-string v0, "com.xiaomi.micloudsdk.os.MiCloudSdkVersion"

    invoke-static {v0}, Lcom/xiaomi/micloudsdk/utils/ReflectUtils;->loadClass(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    if-eqz v0, :cond_0

    const-string v1, "version"

    invoke-static {v0, v1}, Lcom/xiaomi/micloudsdk/utils/ReflectUtils;->getStaticFieldIntValue(Ljava/lang/Class;Ljava/lang/String;)I

    move-result v0

    return v0

    :cond_0
    const-string v0, "miui.cloud.helper.BroadcastIntentHelper"

    invoke-static {v0}, Lcom/xiaomi/micloudsdk/utils/ReflectUtils;->loadClass(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    if-eqz v0, :cond_1

    const/16 v0, 0x19

    return v0

    :cond_1
    const-string v0, "com.xiaomi.micloudsdk.utils.MiCloudRuntimeConstants"

    invoke-static {v0}, Lcom/xiaomi/micloudsdk/utils/ReflectUtils;->loadClass(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    if-eqz v0, :cond_2

    const/16 v0, 0x12

    return v0

    :cond_2
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "No MiCloudSDK runtime!"

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
