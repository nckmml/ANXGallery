.class Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;
.super Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_Base;
.source "PermissionUtilsCompatMiCloud_V23.java"


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_Base;-><init>()V

    return-void
.end method


# virtual methods
.method public isGdprPermissionGranted(Landroid/content/Context;)Z
    .locals 2

    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    const/4 v0, 0x1

    const-string v1, "micloud_gdpr_permission_granted"

    invoke-static {p1, v1, v0}, Landroid/provider/Settings$System;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method
