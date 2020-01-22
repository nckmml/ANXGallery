.class public Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;
.super Ljava/lang/Object;
.source "HomePageImmersionMenuHelper.java"

# interfaces
.implements Lcom/miui/gallery/widget/menu/ImmersionMenuListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;,
        Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;
    }
.end annotation


# instance fields
.field private mContext:Landroid/content/Context;

.field private mCurrentPage:Ljava/lang/String;

.field private mFeatureItemsMap:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mHasRedDotItem:Z

.field private mInstallStateListener:Lcom/miui/gallery/util/PrintInstallHelper$InstallStateListener;

.field private mMenuItemsCheckListener:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;

.field private mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

.field private mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

.field private mPrintStatusObserver:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mFeatureItemsMap:Ljava/util/Map;

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    new-instance v0, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    invoke-direct {v0, p1, p0}, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;-><init>(Landroid/content/Context;Lcom/miui/gallery/widget/menu/ImmersionMenuListener;)V

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerPrintStatusObserver()V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;)Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;Lcom/miui/gallery/cloudcontrol/FeatureProfile$Status;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->onPrintStatueChanged(Lcom/miui/gallery/cloudcontrol/FeatureProfile$Status;)V

    return-void
.end method

.method private isTrashBinEnable()Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/util/deviceprovider/ApplicationHelper;->isCloudTrashBinFeatureOpen()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    invoke-static {v0}, Lcom/miui/gallery/util/SyncUtil;->existXiaomiAccount(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private onPrintStatueChanged(Lcom/miui/gallery/cloudcontrol/FeatureProfile$Status;)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/util/PrintInstallHelper;->isPhotoPrintEnable()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-static {}, Lcom/miui/gallery/reddot/DisplayStatusManager;->regenerateRedDotMap()V

    const p1, 0x7f09020d

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->setMenuVisibility(IZ)Z

    const-string p1, "photo_print"

    const-string v0, "photo_print_enable"

    const-string v1, "true"

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordStringPropertyEvent(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private registerFeature(ILjava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mFeatureItemsMap:Ljava/util/Map;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {v0, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method private registerPrintStatusObserver()V
    .locals 3

    new-instance v0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;-><init>(Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$1;)V

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPrintStatusObserver:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->getInstance()Lcom/miui/gallery/cloudcontrol/CloudControlManager;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPrintStatusObserver:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;

    const-string v2, "photo-print"

    invoke-virtual {v0, v2, v1}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->registerStatusObserver(Ljava/lang/String;Lcom/miui/gallery/cloudcontrol/observers/FeatureStatusObserver;)Lcom/miui/gallery/cloudcontrol/FeatureProfile$Status;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->onPrintStatueChanged(Lcom/miui/gallery/cloudcontrol/FeatureProfile$Status;)V

    return-void
.end method

.method private setMenuVisibility(IZ)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;->getImmersionMenu()Lcom/miui/gallery/widget/menu/ImmersionMenu;

    move-result-object v0

    if-nez v0, :cond_1

    return v1

    :cond_1
    invoke-virtual {v0, p1}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->findItem(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object p1

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->setMenuVisibility(Lcom/miui/gallery/widget/menu/ImmersionMenuItem;Z)Z

    move-result p1

    return p1
.end method

.method private setMenuVisibility(Lcom/miui/gallery/widget/menu/ImmersionMenuItem;Z)Z
    .locals 2

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return v0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->isVisible()Z

    move-result v1

    if-eq v1, p2, :cond_1

    invoke-virtual {p1, p2}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setVisible(Z)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->checkRedDotFeature()V

    const/4 p1, 0x1

    return p1

    :cond_1
    return v0
.end method

.method private unregisterPrintStatusObserver()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPrintStatusObserver:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->getInstance()Lcom/miui/gallery/cloudcontrol/CloudControlManager;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPrintStatusObserver:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$PrintStatusObserver;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->unregisterStatusObserver(Lcom/miui/gallery/cloudcontrol/observers/FeatureStatusObserver;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public checkRedDotFeature()V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;->getImmersionMenu()Lcom/miui/gallery/widget/menu/ImmersionMenu;

    move-result-object v0

    if-nez v0, :cond_1

    return-void

    :cond_1
    const/4 v1, 0x0

    iput-boolean v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mHasRedDotItem:Z

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mFeatureItemsMap:Ljava/util/Map;

    invoke-interface {v2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_3

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Integer;

    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v3

    invoke-virtual {v0, v3}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->findItem(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v3

    invoke-static {v4}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getRedDotStatus(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_2

    invoke-virtual {v3}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->isVisible()Z

    move-result v4

    if-eqz v4, :cond_2

    const/4 v4, 0x1

    goto :goto_1

    :cond_2
    move v4, v1

    :goto_1
    iget-boolean v5, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mHasRedDotItem:Z

    or-int/2addr v5, v4

    iput-boolean v5, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mHasRedDotItem:Z

    invoke-virtual {v3, v4}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setIsRedDotDisplayed(Z)V

    goto :goto_0

    :cond_3
    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mMenuItemsCheckListener:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;

    if-eqz v1, :cond_4

    iget-boolean v2, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mHasRedDotItem:Z

    invoke-interface {v1, v2}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;->onMenuItemsChecked(Z)V

    :cond_4
    invoke-virtual {p0, v0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->updateImmersionMenu(Lcom/miui/gallery/widget/menu/ImmersionMenu;)V

    return-void
.end method

.method public onActivityDestroy()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    invoke-virtual {v0}, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;->dismiss()V

    :cond_0
    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mInstallStateListener:Lcom/miui/gallery/util/PrintInstallHelper$InstallStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/util/PrintInstallHelper;->removeInstallStateListener(Lcom/miui/gallery/util/PrintInstallHelper$InstallStateListener;)V

    invoke-static {}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/util/MovieLibraryLoaderHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->removeDownloadStateListener(Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;)V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->unregisterPrintStatusObserver()V

    return-void
.end method

.method public onCreateImmersionMenu(Lcom/miui/gallery/widget/menu/ImmersionMenu;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isDeviceSupportStoryFunction()Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    const v1, 0x7f1004f3

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    const v1, 0x7f090200

    invoke-virtual {p1, v1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->add(ILjava/lang/CharSequence;)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const v2, 0x7f07019f

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setIconResource(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    const-string v0, "collage"

    invoke-direct {p0, v1, v0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerFeature(ILjava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/util/PhotoMovieEntranceUtils;->isPhotoMovieAvailable()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    const v1, 0x7f1004f4

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    const v1, 0x7f09020c

    invoke-virtual {p1, v1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->add(ILjava/lang/CharSequence;)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const v2, 0x7f0701a0

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setIconResource(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const-string v2, "photo_movie"

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerFeature(ILjava/lang/String;)V

    new-instance v1, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$1;

    invoke-direct {v1, p0, v0, p1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$1;-><init>(Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;Lcom/miui/gallery/widget/menu/ImmersionMenuItem;Lcom/miui/gallery/widget/menu/ImmersionMenu;)V

    iput-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    invoke-static {}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/util/MovieLibraryLoaderHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->addDownloadStateListener(Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    const v1, 0x7f1004f2

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    const v1, 0x7f0901ff

    invoke-virtual {p1, v1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->add(ILjava/lang/CharSequence;)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const v2, 0x7f07019e

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setIconResource(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    const-string v0, "photo_cleaner"

    invoke-direct {p0, v1, v0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerFeature(ILjava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    const v1, 0x7f10086a

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    const v1, 0x7f09037b

    invoke-virtual {p1, v1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->add(ILjava/lang/CharSequence;)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const v2, 0x7f0701a3

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setIconResource(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const-string v2, "trash_bin"

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerFeature(ILjava/lang/String;)V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->isTrashBinEnable()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->setMenuVisibility(Lcom/miui/gallery/widget/menu/ImmersionMenuItem;Z)Z

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    const v1, 0x7f1004f5

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    const v1, 0x7f09020d

    invoke-virtual {p1, v1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->add(ILjava/lang/CharSequence;)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const v2, 0x7f0701a1

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setIconResource(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    const-string v2, "photo_print"

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerFeature(ILjava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/util/PrintInstallHelper;->isPhotoPrintEnable()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setVisible(Z)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    new-instance v1, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$2;

    invoke-direct {v1, p0, v0, p1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$2;-><init>(Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;Lcom/miui/gallery/widget/menu/ImmersionMenuItem;Lcom/miui/gallery/widget/menu/ImmersionMenu;)V

    iput-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mInstallStateListener:Lcom/miui/gallery/util/PrintInstallHelper$InstallStateListener;

    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mInstallStateListener:Lcom/miui/gallery/util/PrintInstallHelper$InstallStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/util/PrintInstallHelper;->addInstallStateListener(Lcom/miui/gallery/util/PrintInstallHelper$InstallStateListener;)V

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    const v1, 0x7f1004d9

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    const v1, 0x7f090212

    invoke-virtual {p1, v1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->add(ILjava/lang/CharSequence;)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object p1

    const v0, 0x7f0701a2

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->setIconResource(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    const-string p1, "settings"

    invoke-direct {p0, v1, p1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerFeature(ILjava/lang/String;)V

    return-void
.end method

.method public onImmersionMenuSelected(Lcom/miui/gallery/widget/menu/ImmersionMenu;Lcom/miui/gallery/widget/menu/ImmersionMenuItem;)V
    .locals 6

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-virtual {p2}, Lcom/miui/gallery/widget/menu/ImmersionMenuItem;->getItemId()I

    move-result p1

    iget-object p2, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mFeatureItemsMap:Ljava/util/Map;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p2, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    if-eqz p2, :cond_1

    invoke-virtual {p2}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p2}, Lcom/miui/gallery/reddot/DisplayStatusManager;->setRedDotClicked(Ljava/lang/String;)V

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v2, 0x1

    new-array v3, v2, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mCurrentPage:Ljava/lang/String;

    const/4 v5, 0x0

    aput-object v4, v3, v5

    const-string v4, "%s_redDotDisplayed"

    invoke-static {v1, v4, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iget-boolean v3, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mHasRedDotItem:Z

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    invoke-interface {v0, v1, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v3, 0x3

    new-array v3, v3, [Ljava/lang/Object;

    const-string v4, "click_menu"

    aput-object v4, v3, v5

    aput-object p2, v3, v2

    const/4 p2, 0x2

    aput-object v0, v3, p2

    const-string p2, "%s_%s"

    invoke-static {v1, p2, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    const-string v0, "feature_red_dot"

    invoke-static {v0, p2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->checkRedDotFeature()V

    const-string p2, "home"

    sparse-switch p1, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    const-string p2, "HomePageActivity"

    invoke-static {p1, p2}, Lcom/miui/gallery/util/IntentUtil;->gotoTrashBin(Landroid/content/Context;Ljava/lang/String;)V

    goto :goto_0

    :sswitch_1
    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    invoke-static {p1}, Lcom/miui/gallery/util/IntentUtil;->enterGallerySetting(Landroid/content/Context;)V

    goto :goto_0

    :sswitch_2
    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/util/PrintInstallHelper;->start(Landroid/content/Context;)V

    const-string p1, "home_page_menu_photo_print"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    :sswitch_3
    invoke-static {}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/util/MovieLibraryLoaderHelper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    check-cast v0, Landroid/app/Activity;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->checkAbleOrDownload(Landroid/app/Activity;)Z

    move-result p1

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    check-cast p1, Landroid/app/Activity;

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Common;->URI_PHOTO_MOVIE:Landroid/net/Uri;

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ActionURIHandler;->handleUri(Landroid/app/Activity;Landroid/net/Uri;)V

    :cond_2
    const-string p1, "home_page_menu_photo_movie"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    :sswitch_4
    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    check-cast p1, Landroid/app/Activity;

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Common;->URI_COLLAGE_PAGE:Landroid/net/Uri;

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ActionURIHandler;->handleUri(Landroid/app/Activity;Landroid/net/Uri;)V

    const-string p1, "home_page_menu_collage"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    :sswitch_5
    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    check-cast p1, Landroid/app/Activity;

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Common;->URI_CLEANER_PAGE:Landroid/net/Uri;

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ActionURIHandler;->handleUri(Landroid/app/Activity;Landroid/net/Uri;)V

    const-string p1, "home_page_menu_cleaner"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void

    :sswitch_data_0
    .sparse-switch
        0x7f0901ff -> :sswitch_5
        0x7f090200 -> :sswitch_4
        0x7f09020c -> :sswitch_3
        0x7f09020d -> :sswitch_2
        0x7f090212 -> :sswitch_1
        0x7f09037b -> :sswitch_0
    .end sparse-switch
.end method

.method public onPrepareImmersionMenu(Lcom/miui/gallery/widget/menu/ImmersionMenu;)Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mContext:Landroid/content/Context;

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    const v0, 0x7f09037b

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->findItem(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object v0

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->isTrashBinEnable()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->setMenuVisibility(Lcom/miui/gallery/widget/menu/ImmersionMenuItem;Z)Z

    move-result v0

    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/util/PrintInstallHelper;->isPhotoPrintEnable()Z

    move-result v1

    const v2, 0x7f09020d

    invoke-virtual {p1, v2}, Lcom/miui/gallery/widget/menu/ImmersionMenu;->findItem(I)Lcom/miui/gallery/widget/menu/ImmersionMenuItem;

    move-result-object p1

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->setMenuVisibility(Lcom/miui/gallery/widget/menu/ImmersionMenuItem;Z)Z

    move-result p1

    or-int/2addr p1, v0

    if-eqz v1, :cond_1

    const-string v0, "photo_print"

    const-string v1, "photo_print_menu_displayed"

    const-string v2, "true"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordStringPropertyEvent(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    const-string v0, "action_bar_more"

    invoke-static {v0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->setRedDotClicked(Ljava/lang/String;)V

    return p1
.end method

.method public registerMenuItemsCheckListener(Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mMenuItemsCheckListener:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;

    return-void
.end method

.method public showImmersionMenu(Landroid/view/View;Ljava/lang/String;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;->show(Landroid/view/View;Landroid/view/ViewGroup;)V

    :cond_0
    iput-object p2, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mCurrentPage:Ljava/lang/String;

    new-instance p1, Ljava/util/HashMap;

    invoke-direct {p1}, Ljava/util/HashMap;-><init>()V

    sget-object p2, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mCurrentPage:Ljava/lang/String;

    aput-object v2, v0, v1

    const-string v1, "%s_redDotDisplayed"

    invoke-static {p2, v1, v0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    iget-boolean v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mHasRedDotItem:Z

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-interface {p1, p2, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p2, "feature_red_dot"

    const-string v0, "show_immersion_menu"

    invoke-static {p2, v0, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public updateImmersionMenu(Lcom/miui/gallery/widget/menu/ImmersionMenu;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->mPhoneImmersionMenu:Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/widget/menu/PhoneImmersionMenu;->update(Lcom/miui/gallery/widget/menu/ImmersionMenu;)V

    :cond_0
    return-void
.end method
