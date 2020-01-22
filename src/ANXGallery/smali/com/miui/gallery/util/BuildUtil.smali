.class public Lcom/miui/gallery/util/BuildUtil;
.super Lcom/miui/gallery/util/BaseBuildUtil;
.source "BuildUtil.java"


# static fields
.field public static final IS_M_OR_LATER:Z

.field private static final MIUI_SDK_LEVEL:Lcom/miui/gallery/util/ModuleLevel;

.field private static sIsOLED:Ljava/lang/Boolean;

.field private static sIsTablet:Ljava/lang/Boolean;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    sput-boolean v0, Lcom/miui/gallery/util/BuildUtil;->IS_M_OR_LATER:Z

    new-instance v0, Lcom/miui/gallery/util/ModuleLevel;

    const-string v1, "com.miui.core"

    invoke-direct {v0, v1}, Lcom/miui/gallery/util/ModuleLevel;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/miui/gallery/util/BuildUtil;->MIUI_SDK_LEVEL:Lcom/miui/gallery/util/ModuleLevel;

    return-void
.end method

.method public static getDeviceName(Landroid/content/Context;)Ljava/lang/String;
    .locals 0

    invoke-static {p0}, Lcom/miui/settings/Settings;->getDeviceName(Landroid/content/Context;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static isDefaultLockStyle()Z
    .locals 2

    new-instance v0, Ljava/io/File;

    const-string v1, "/data/system/theme//lockscreen"

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    const/4 v0, 0x1

    return v0
.end method

.method public static isMiui10(Landroid/content/Context;)Z
    .locals 1

    sget-object v0, Lcom/miui/gallery/util/BuildUtil;->MIUI_SDK_LEVEL:Lcom/miui/gallery/util/ModuleLevel;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/util/ModuleLevel;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    const/16 v0, 0x12

    if-ge p0, v0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isOledDisplay()Z
    .locals 2

    sget-object v0, Lcom/miui/gallery/util/BuildUtil;->sIsOLED:Ljava/lang/Boolean;

    if-nez v0, :cond_0

    const-string v0, "ro.display.type"

    const-string v1, "default"

    invoke-static {v0, v1}, Lcom/android/internal/SystemPropertiesCompat;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "oled"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/util/BuildUtil;->sIsOLED:Ljava/lang/Boolean;

    :cond_0
    sget-object v0, Lcom/miui/gallery/util/BuildUtil;->sIsOLED:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static isPad()Z
    .locals 1

    invoke-static {}, Lcom/miui/os/FeatureHelper;->isPad()Z

    move-result v0

    return v0
.end method

.method public static isTablet()Z
    .locals 2

    sget-object v0, Lcom/miui/gallery/util/BuildUtil;->sIsTablet:Ljava/lang/Boolean;

    if-nez v0, :cond_0

    const-string v0, "ro.build.characteristics"

    const-string v1, "default"

    invoke-static {v0, v1}, Lcom/android/internal/SystemPropertiesCompat;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "tablet"

    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/util/BuildUtil;->sIsTablet:Ljava/lang/Boolean;

    :cond_0
    sget-object v0, Lcom/miui/gallery/util/BuildUtil;->sIsTablet:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static isXiaoMi()Z
    .locals 1

    invoke-static {}, Lcom/miui/os/FeatureHelper;->isXiaoMi()Z

    move-result v0

    return v0
.end method
