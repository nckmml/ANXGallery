.class public Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;
.super Ljava/lang/Object;
.source "NexAssetTemplateManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ICheckExpiredAssetListener;,
        Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadWaterMarkListener;,
        Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;,
        Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$NexTemplateManagerHolder;
    }
.end annotation


# static fields
.field private static TAG:Ljava/lang/String; = "NexAssetTemplateManager"

.field private static assetInstallRootPath:Ljava/lang/String;

.field private static assetStoreSubDir:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;-><init>()V

    return-void
.end method

.method static synthetic access$200()Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->TAG:Ljava/lang/String;

    return-object v0
.end method

.method public static getInstance()Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$NexTemplateManagerHolder;->access$100()Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;

    move-result-object v0

    return-object v0
.end method

.method public static getKmTemplateManager()Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;
    .locals 2

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;->getTemplateManager(Landroid/content/Context;Landroid/content/Context;)Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;

    move-result-object v0

    return-object v0
.end method

.method private initPath()V
    .locals 1

    sget-object v0, Lcom/miui/gallery/video/editor/config/VideoEditorConfig;->ASSET_STORE_PATH:Ljava/lang/String;

    sput-object v0, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->assetStoreSubDir:Ljava/lang/String;

    sget-object v0, Lcom/miui/gallery/video/editor/config/VideoEditorConfig;->ASSET_INSTALL_ROOT_PATH:Ljava/lang/String;

    sput-object v0, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->assetInstallRootPath:Ljava/lang/String;

    sget-object v0, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->assetStoreSubDir:Ljava/lang/String;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/util/FileHelper;->createDir(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object v0, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->assetStoreSubDir:Ljava/lang/String;

    invoke-static {v0}, Lcom/nexstreaming/nexeditorsdk/nexApplicationConfig;->setAssetStoreRootPath(Ljava/lang/String;)V

    sget-object v0, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->assetInstallRootPath:Ljava/lang/String;

    invoke-static {v0}, Lcom/nexstreaming/nexeditorsdk/nexApplicationConfig;->setAssetInstallRootPath(Ljava/lang/String;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public checkExpiredAsset(Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ICheckExpiredAssetListener;)V
    .locals 2

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->getKmTemplateManager()Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;

    move-result-object v0

    if-eqz v0, :cond_1

    new-instance v1, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$1;

    invoke-direct {v1, p0, v0, p1}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$1;-><init>(Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ICheckExpiredAssetListener;)V

    invoke-virtual {v0, v1}, Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;->loadTemplate(Ljava/lang/Runnable;)V

    :cond_1
    return-void
.end method

.method public installProcess(ILcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;)V
    .locals 2

    if-nez p2, :cond_0

    return-void

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->initPath()V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/nexstreaming/nexeditorsdk/nexAssetPackageManager;->getAssetPackageManager(Landroid/content/Context;)Lcom/nexstreaming/nexeditorsdk/nexAssetPackageManager;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$2;

    invoke-direct {v1, p0, p2}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$2;-><init>(Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;)V

    invoke-virtual {v0, p1, v1}, Lcom/nexstreaming/nexeditorsdk/nexAssetPackageManager;->installPackagesAsync(ILcom/nexstreaming/nexeditorsdk/nexAssetPackageManager$OnInstallPackageListener;)V

    return-void
.end method

.method public loadSmartEffectTemplateList()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/nexstreaming/nexeditorsdk/nexTemplateManager$Template;",
            ">;"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->initPath()V

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->getKmTemplateManager()Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;->loadTemplate()V

    invoke-virtual {v0}, Lcom/nexstreaming/nexeditorsdk/nexTemplateManager;->getTemplates()Ljava/util/List;

    move-result-object v0

    return-object v0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public loadWaterMarkTemplateList(Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadWaterMarkListener;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->initPath()V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/nexstreaming/nexeditorsdk/nexOverlayPreset;->getOverlayPreset(Landroid/content/Context;)Lcom/nexstreaming/nexeditorsdk/nexOverlayPreset;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nexstreaming/nexeditorsdk/nexOverlayPreset;->getIDs()[Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, v0}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadWaterMarkListener;->onFinished([Ljava/lang/String;)V

    return-void
.end method

.method public uninstallPackageById(Ljava/lang/String;)V
    .locals 1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/nexstreaming/nexeditorsdk/nexAssetPackageManager;->getAssetPackageManager(Landroid/content/Context;)Lcom/nexstreaming/nexeditorsdk/nexAssetPackageManager;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/nexstreaming/nexeditorsdk/nexAssetPackageManager;->uninstallPackageById(Ljava/lang/String;)V

    :cond_0
    return-void
.end method
