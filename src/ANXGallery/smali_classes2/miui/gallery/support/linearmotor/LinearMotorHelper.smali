.class public Lmiui/gallery/support/linearmotor/LinearMotorHelper;
.super Ljava/lang/Object;
.source "LinearMotorHelper.java"


# static fields
.field public static EFFECT_KEY_MESH_LIGHT:Ljava/lang/String; = "mesh_light"

.field public static EFFECT_KEY_MESH_NORMAL:Ljava/lang/String; = "mesh_normal"

.field public static EFFECT_KEY_SWITCH:Ljava/lang/String; = "switch"

.field public static HAPTIC_MESH_NORMAL:I = 0x10000005

.field public static HAPTIC_STRENGTH_ID_0:I = 0x0

.field public static HAPTIC_STRENGTH_ID_1:I = 0x1

.field public static HAPTIC_STRENGTH_ID_2:I = 0x2

.field public static HAPTIC_SWITCH:I = 0x10000003

.field private static final LINEAR_MOTOR_SUPPORTED:Lcom/miui/gallery/util/LazyValue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/util/LazyValue<",
            "Ljava/lang/Void;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lmiui/gallery/support/linearmotor/LinearMotorHelper$1;

    invoke-direct {v0}, Lmiui/gallery/support/linearmotor/LinearMotorHelper$1;-><init>()V

    sput-object v0, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->LINEAR_MOTOR_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    return-void
.end method

.method public static performHapticFeedback(Landroid/content/Context;Ljava/lang/String;)Z
    .locals 4

    const-string v0, "LinearMotorHelper"

    const/4 v1, 0x0

    :try_start_0
    sget-boolean v2, Lmiui/gallery/support/core/SdkHelper;->IS_MIUI:Z

    if-eqz v2, :cond_2

    sget-object v2, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->LINEAR_MOTOR_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Boolean;

    invoke-virtual {v2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    if-nez v2, :cond_0

    goto :goto_1

    :cond_0
    if-nez p0, :cond_1

    return v1

    :cond_1
    :try_start_1
    new-instance v2, Lmiui/util/HapticFeedbackUtil;

    invoke-direct {v2, p0, v1}, Lmiui/util/HapticFeedbackUtil;-><init>(Landroid/content/Context;Z)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :try_start_2
    invoke-virtual {v2, p1, v1}, Lmiui/util/HapticFeedbackUtil;->performHapticFeedback(Ljava/lang/String;Z)Z

    move-result v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    invoke-virtual {v2}, Lmiui/util/HapticFeedbackUtil;->release()V

    goto :goto_2

    :catchall_0
    move-exception p0

    move-object v3, v2

    goto :goto_0

    :catchall_1
    move-exception p0

    :goto_0
    invoke-virtual {v3}, Lmiui/util/HapticFeedbackUtil;->release()V

    throw p0

    :cond_2
    :goto_1
    const-string p0, "the device is not support LinearMotorVibrate."

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0

    return v1

    :catch_0
    move-exception p0

    const-string p1, "performHapticFeedback  exception: "

    invoke-static {v0, p1, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_2
    return v1
.end method

.method public static performHapticFeedback(Landroid/content/Context;Ljava/lang/String;I)Z
    .locals 4

    const-string v0, "LinearMotorHelper"

    const/4 v1, 0x0

    :try_start_0
    sget-boolean v2, Lmiui/gallery/support/core/SdkHelper;->IS_MIUI:Z

    if-eqz v2, :cond_2

    sget-object v2, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->LINEAR_MOTOR_SUPPORTED:Lcom/miui/gallery/util/LazyValue;

    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Boolean;

    invoke-virtual {v2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    if-nez v2, :cond_0

    goto :goto_1

    :cond_0
    if-nez p0, :cond_1

    return v1

    :cond_1
    :try_start_1
    new-instance v2, Lmiui/util/HapticFeedbackUtil;

    invoke-direct {v2, p0, v1}, Lmiui/util/HapticFeedbackUtil;-><init>(Landroid/content/Context;Z)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :try_start_2
    invoke-virtual {v2, p1, v1, p2}, Lmiui/util/HapticFeedbackUtil;->performHapticFeedback(Ljava/lang/String;ZI)Z

    move-result v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    invoke-virtual {v2}, Lmiui/util/HapticFeedbackUtil;->release()V

    goto :goto_2

    :catchall_0
    move-exception p0

    move-object v3, v2

    goto :goto_0

    :catchall_1
    move-exception p0

    :goto_0
    invoke-virtual {v3}, Lmiui/util/HapticFeedbackUtil;->release()V

    throw p0

    :cond_2
    :goto_1
    const-string p0, "the device is not support LinearMotorVibrate."

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0

    return v1

    :catch_0
    move-exception p0

    const-string p1, "performHapticFeedback Exception: "

    invoke-static {v0, p1, p0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_2
    return v1
.end method
