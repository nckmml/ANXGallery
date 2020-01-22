.class public Lmicloud/compat/v18/sync/PermissionUtilsCompat;
.super Ljava/lang/Object;
.source "PermissionUtilsCompat.java"


# static fields
.field private static final IMPL:Lmicloud/compat/v18/sync/IPermissionUtilsCompat;

.field private static final version:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;->CURRENT_VERSION:I

    sput v0, Lmicloud/compat/v18/sync/PermissionUtilsCompat;->version:I

    sget v0, Lmicloud/compat/v18/sync/PermissionUtilsCompat;->version:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_0

    new-instance v0, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;

    invoke-direct {v0}, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;-><init>()V

    sput-object v0, Lmicloud/compat/v18/sync/PermissionUtilsCompat;->IMPL:Lmicloud/compat/v18/sync/IPermissionUtilsCompat;

    goto :goto_0

    :cond_0
    new-instance v0, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_Base;

    invoke-direct {v0}, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_Base;-><init>()V

    sput-object v0, Lmicloud/compat/v18/sync/PermissionUtilsCompat;->IMPL:Lmicloud/compat/v18/sync/IPermissionUtilsCompat;

    :goto_0
    return-void
.end method

.method public static isGdprPermissionGranted(Landroid/content/Context;)Z
    .locals 1

    sget-object v0, Lmicloud/compat/v18/sync/PermissionUtilsCompat;->IMPL:Lmicloud/compat/v18/sync/IPermissionUtilsCompat;

    invoke-interface {v0, p0}, Lmicloud/compat/v18/sync/IPermissionUtilsCompat;->isGdprPermissionGranted(Landroid/content/Context;)Z

    move-result p0

    return p0
.end method
