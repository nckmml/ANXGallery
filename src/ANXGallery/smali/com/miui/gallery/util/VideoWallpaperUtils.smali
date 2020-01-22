.class public Lcom/miui/gallery/util/VideoWallpaperUtils;
.super Ljava/lang/Object;
.source "VideoWallpaperUtils.java"


# static fields
.field private static sSupported:Z


# direct methods
.method static constructor <clinit>()V
    .locals 3

    sget-boolean v0, Lcom/miui/os/Rom;->IS_MIUI:Z

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/util/BuildUtil;->isTablet()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/util/BuildUtil;->isPad()Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "com.android.thememanager"

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->getAppVersionCode(Ljava/lang/String;)I

    move-result v1

    const/16 v2, 0x1f4

    if-gt v1, v2, :cond_0

    sget-boolean v1, Lcom/miui/os/Rom;->IS_INTERNATIONAL:Z

    if-nez v1, :cond_1

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->getAppVersionCode(Ljava/lang/String;)I

    move-result v0

    const/16 v1, 0x1ea

    if-lt v0, v1, :cond_1

    :cond_0
    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    :goto_0
    sput-boolean v0, Lcom/miui/gallery/util/VideoWallpaperUtils;->sSupported:Z

    return-void
.end method

.method public static isSupported()Z
    .locals 1

    sget-boolean v0, Lcom/miui/gallery/util/VideoWallpaperUtils;->sSupported:Z

    return v0
.end method

.method public static setVideoWallpaper(Landroid/content/Context;Ljava/lang/String;)V
    .locals 3

    const-string v0, "video"

    :try_start_0
    new-instance v1, Landroid/content/Intent;

    const-string v2, "miui.intent.action.START_VIDEO_DETAIL"

    invoke-direct {v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v2, "android.intent.category.DEFAULT"

    invoke-virtual {v1, v2}, Landroid/content/Intent;->addCategory(Ljava/lang/String;)Landroid/content/Intent;

    const-string v2, "path"

    invoke-virtual {v1, v2, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    const-string p1, "miref"

    const-string v2, "com.miui.gallery"

    invoke-virtual {v1, p1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    invoke-virtual {p0, v1}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V

    const-string p0, "set_as_video_wallpaper"

    invoke-static {v0, p0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    const-string p1, "VideoWallpaperUtils"

    invoke-static {p1, p0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    const-string p0, "set_slide_wallpaper_fail"

    invoke-static {v0, p0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void
.end method
