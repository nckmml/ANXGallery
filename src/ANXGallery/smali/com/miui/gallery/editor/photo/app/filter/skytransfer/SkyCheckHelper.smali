.class public Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;
.super Ljava/lang/Object;
.source "SkyCheckHelper.java"


# static fields
.field private static sInstance:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;

.field private static sIsSkyLibAvailable:Z

.field private static sIsSkyResAvailable:Z

.field private static final sSkyWhiteList:[Ljava/lang/String;


# instance fields
.field private mChildDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

.field private mDownloadStateListeners:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;",
            ">;"
        }
    .end annotation
.end field

.field private mIsDownloading:Z

.field private mStubStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;


# direct methods
.method static constructor <clinit>()V
    .locals 39

    new-instance v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;

    invoke-direct {v0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;-><init>()V

    sput-object v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sInstance:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;

    const-string v1, "umi"

    const-string v2, "cmi"

    const-string v3, "dipper"

    const-string v4, "ursa"

    const-string v5, "jason"

    const-string v6, "wayne"

    const-string v7, "platina"

    const-string v8, "chiron"

    const-string v9, "sagit"

    const-string v10, "polaris"

    const-string v11, "perseus"

    const-string v12, "equuleus"

    const-string v13, "sirius"

    const-string v14, "comet"

    const-string v15, "lavender"

    const-string v16, "capricorn"

    const-string v17, "lithium"

    const-string v18, "natrium"

    const-string v19, "scorpio"

    const-string v20, "gemini"

    const-string v21, "cepheus"

    const-string v22, "davinci"

    const-string v23, "davinciin"

    const-string v24, "grus"

    const-string v25, "raphael"

    const-string v26, "raphaelin"

    const-string v27, "pyxis"

    const-string v28, "vela"

    const-string v29, "crux"

    const-string v30, "beryllium"

    const-string v31, "tucana"

    const-string v32, "violet"

    const-string v33, "phoenix"

    const-string v34, "phoenixin"

    const-string v35, "andromeda"

    const-string v36, "begonia"

    const-string v37, "begoniain"

    const-string v38, "draco"

    filled-new-array/range {v1 .. v38}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sSkyWhiteList:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-direct {v0}, Ljava/util/concurrent/CopyOnWriteArraySet;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mDownloadStateListeners:Ljava/util/Set;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper$2;-><init>(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mChildDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper$3;-><init>(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mStubStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->startDownload(Z)V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;ZI)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->refreshDownloadResult(ZI)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->refreshDownloadStart()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->refreshDownloading(I)V

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;
    .locals 1

    sget-object v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sInstance:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;

    return-object v0
.end method

.method public static isSkyAvailable()Z
    .locals 3

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->isSkyEnable()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyLibAvailable:Z

    const/4 v2, 0x1

    if-nez v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->getLoaderState()I

    move-result v0

    if-nez v0, :cond_0

    move v0, v2

    goto :goto_0

    :cond_0
    move v0, v1

    :goto_0
    sput-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyLibAvailable:Z

    :cond_1
    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyResAvailable:Z

    if-nez v0, :cond_2

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->isResourceDownloaded()Z

    move-result v0

    sput-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyResAvailable:Z

    :cond_2
    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyLibAvailable:Z

    if-eqz v0, :cond_3

    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyResAvailable:Z

    if-eqz v0, :cond_3

    move v1, v2

    :cond_3
    return v1
.end method

.method public static isSkyEnable()Z
    .locals 6

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->isEditorProcess()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    :cond_0
    sget-object v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sSkyWhiteList:[Ljava/lang/String;

    array-length v2, v0

    move v3, v1

    :goto_0
    if-ge v3, v2, :cond_2

    aget-object v4, v0, v3

    sget-object v5, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v4, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    const/4 v0, 0x1

    return v0

    :cond_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    return v1
.end method

.method private refreshDownloadResult(ZI)V
    .locals 3

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mIsDownloading:Z

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mDownloadStateListeners:Ljava/util/Set;

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    invoke-interface {v2, v0, p1, p2}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;->onFinish(IZI)V

    goto :goto_0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/util/StaticContext;->sGetAndroidContext()Landroid/content/Context;

    move-result-object p2

    if-eqz p1, :cond_1

    const v0, 0x7f100491

    goto :goto_1

    :cond_1
    const v0, 0x7f100490

    :goto_1
    invoke-static {p2, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "refreshDownloadResult"

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "SkyCheckHelper"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private refreshDownloadStart()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mDownloadStateListeners:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    const/4 v2, 0x0

    invoke-interface {v1, v2}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;->onDownloadStart(I)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method private refreshDownloading(I)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mDownloadStateListeners:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    const/4 v2, 0x0

    invoke-interface {v1, v2, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;->onDownloading(II)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method private startDownload(Z)V
    .locals 3

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->isSkyAvailable()Z

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_0

    invoke-direct {p0, v2, v1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->refreshDownloadResult(ZI)V

    goto :goto_0

    :cond_0
    iput-boolean v2, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mIsDownloading:Z

    invoke-direct {p0, v1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->refreshDownloading(I)V

    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyResAvailable:Z

    if-nez v0, :cond_1

    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyLibAvailable:Z

    if-nez v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mChildDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->setDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->startDownload(Z)V

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mChildDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->setDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->startDownload(Z)V

    goto :goto_0

    :cond_1
    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyLibAvailable:Z

    if-nez v0, :cond_2

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mStubStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->setDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->startDownload(Z)V

    goto :goto_0

    :cond_2
    sget-boolean v0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->sIsSkyResAvailable:Z

    if-nez v0, :cond_3

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mStubStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->setDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->startDownload(Z)V

    :cond_3
    :goto_0
    return-void
.end method


# virtual methods
.method public addDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V
    .locals 1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mDownloadStateListeners:Ljava/util/Set;

    invoke-interface {v0, p1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method

.method public checkAbleOrDownload(Landroid/app/Activity;)Z
    .locals 2

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->isSkyEnable()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->isSkyAvailable()Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 p1, 0x1

    return p1

    :cond_1
    invoke-virtual {p0, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->startDownloadWithCheck(Landroid/app/Activity;)V

    return v1
.end method

.method public removeDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V
    .locals 1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mDownloadStateListeners:Ljava/util/Set;

    invoke-interface {v0, p1}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    :cond_0
    return-void
.end method

.method public startDownloadWithCheck(Landroid/app/Activity;)V
    .locals 8

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->mIsDownloading:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isNetworkConnected()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object p1

    const v0, 0x7f100490

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    const-string p1, "SkyCheckHelper"

    const-string v0, "download sdk no network"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 p1, -0x1

    invoke-direct {p0, v1, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->refreshDownloadResult(ZI)V

    goto :goto_0

    :cond_1
    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v0

    if-eqz v0, :cond_2

    new-instance v7, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper$1;

    invoke-direct {v7, p0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper$1;-><init>(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;)V

    invoke-virtual {p1}, Landroid/app/Activity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v2

    invoke-virtual {p1}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v3, 0x7f100493

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v4, 0x7f100492

    invoke-virtual {v0, v4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    const/16 v5, 0x16

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v4, v1

    invoke-static {v0, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {p1}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f1000f2

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p1}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f1000f3

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-static/range {v2 .. v7}, Lcom/miui/gallery/ui/ConfirmDialog;->showConfirmDialog(Landroid/app/FragmentManager;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/ui/ConfirmDialog$ConfirmDialogInterface;)V

    goto :goto_0

    :cond_2
    invoke-direct {p0, v1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->startDownload(Z)V

    :goto_0
    return-void
.end method

.method public startDownloadWithWifi()V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isNetworkConnected()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->startDownload(Z)V

    :cond_0
    return-void
.end method
