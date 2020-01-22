.class public Lcom/miui/gallery/projection/ConnectControllerSingleton;
.super Ljava/lang/Object;
.source "ConnectControllerSingleton.java"


# static fields
.field private static sInstance:Lcom/miui/gallery/projection/ConnectController;


# direct methods
.method public static declared-synchronized getInstance()Lcom/miui/gallery/projection/ConnectController;
    .locals 2

    const-class v0, Lcom/miui/gallery/projection/ConnectControllerSingleton;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/miui/gallery/projection/ConnectControllerSingleton;->sInstance:Lcom/miui/gallery/projection/ConnectController;

    if-nez v1, :cond_1

    invoke-static {}, Lcom/miui/gallery/projection/ConnectControllerSingleton;->isMilinkV1()Z

    move-result v1

    if-eqz v1, :cond_0

    new-instance v1, Lcom/miui/gallery/projection/ConnectControllerImplV1;

    invoke-direct {v1}, Lcom/miui/gallery/projection/ConnectControllerImplV1;-><init>()V

    sput-object v1, Lcom/miui/gallery/projection/ConnectControllerSingleton;->sInstance:Lcom/miui/gallery/projection/ConnectController;

    goto :goto_0

    :cond_0
    new-instance v1, Lcom/miui/gallery/projection/ConnectControllerImplV2;

    invoke-direct {v1}, Lcom/miui/gallery/projection/ConnectControllerImplV2;-><init>()V

    sput-object v1, Lcom/miui/gallery/projection/ConnectControllerSingleton;->sInstance:Lcom/miui/gallery/projection/ConnectController;

    :cond_1
    :goto_0
    sget-object v1, Lcom/miui/gallery/projection/ConnectControllerSingleton;->sInstance:Lcom/miui/gallery/projection/ConnectController;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method private static isMilinkV1()Z
    .locals 2

    const-string v0, "com.milink.api.v1.MilinkClientManager"

    const-string v1, "showScanList"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ReflectUtils;->getMethod(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/reflect/Method;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method
