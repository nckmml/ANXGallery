.class public Lcom/miui/keyguard/LockScreenHelper;
.super Ljava/lang/Object;
.source "LockScreenHelper.java"


# direct methods
.method public static getLockWallpaperProvider(Landroid/content/ContentResolver;)Ljava/lang/String;
    .locals 1

    sget-boolean v0, Lcom/miui/core/SdkHelper;->IS_MIUI:Z

    if-eqz v0, :cond_0

    const-string v0, "lock_wallpaper_provider_authority"

    invoke-static {p0, v0}, Landroid/provider/Settings$System;->getString(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static setLockWallpaperProvider(Landroid/content/ContentResolver;Ljava/lang/String;)V
    .locals 1

    sget-boolean v0, Lcom/miui/core/SdkHelper;->IS_MIUI:Z

    if-eqz v0, :cond_0

    const-string v0, "lock_wallpaper_provider_authority"

    invoke-static {p0, v0, p1}, Landroid/provider/Settings$System;->putString(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;)Z

    :cond_0
    return-void
.end method
