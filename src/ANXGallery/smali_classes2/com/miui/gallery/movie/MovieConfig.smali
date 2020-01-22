.class public Lcom/miui/gallery/movie/MovieConfig;
.super Ljava/lang/Object;
.source "MovieConfig.java"


# static fields
.field public static sAudioDir:Ljava/lang/String; = null

.field private static sInited:Z = false

.field private static sMovieRatio:F = 0.0f

.field public static sTemplateDir:Ljava/lang/String; = null

.field public static sTestLocationType:I = -0x1

.field private static sUseXmSdk:Z

.field private static final sXmSDKList:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    const-string v0, "tucana"

    const-string v1, "phoenixin"

    const-string v2, "phoenix"

    filled-new-array {v0, v1, v2}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/movie/MovieConfig;->sXmSDKList:[Ljava/lang/String;

    sget-object v0, Lcom/miui/gallery/movie/MovieConfig;->sXmSDKList:[Ljava/lang/String;

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_1

    aget-object v3, v0, v2

    sget-object v4, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    const/4 v3, 0x1

    sput-boolean v3, Lcom/miui/gallery/movie/MovieConfig;->sUseXmSdk:Z

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public static getMovieRatio()F
    .locals 1

    sget v0, Lcom/miui/gallery/movie/MovieConfig;->sMovieRatio:F

    return v0
.end method

.method public static init(Landroid/content/Context;)V
    .locals 2

    sget-boolean v0, Lcom/miui/gallery/movie/MovieConfig;->sInited:Z

    if-nez v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/movie/MovieConfig;->isUserXmSdk()Z

    move-result v0

    if-eqz v0, :cond_0

    const-string v0, "xm_"

    goto :goto_0

    :cond_0
    const-string v0, ""

    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "movie"

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/content/Context;->getExternalFilesDir(Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Ljava/io/File;->separator:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "template"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/movie/MovieConfig;->sTemplateDir:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Ljava/io/File;->separator:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "audio"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/movie/MovieConfig;->sAudioDir:Ljava/lang/String;

    invoke-static {p0}, Lcom/miui/gallery/util/BaseFileUtils;->addNoMedia(Ljava/lang/String;)Z

    const/4 p0, 0x1

    sput-boolean p0, Lcom/miui/gallery/movie/MovieConfig;->sInited:Z

    :cond_1
    return-void
.end method

.method public static isUserXmSdk()Z
    .locals 1

    sget-boolean v0, Lcom/miui/gallery/movie/MovieConfig;->sUseXmSdk:Z

    return v0
.end method

.method public static setMovieRatio(F)V
    .locals 0

    sput p0, Lcom/miui/gallery/movie/MovieConfig;->sMovieRatio:F

    return-void
.end method
