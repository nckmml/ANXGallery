.class public Lcom/miui/gallery/activity/HomePageActivity;
.super Lcom/miui/gallery/activity/BaseActivity;
.source "HomePageActivity.java"

# interfaces
.implements Landroidx/lifecycle/DefaultLifecycleObserver;
.implements Lcom/miui/gallery/activity/HomePageStartupHelper$Attacher;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/activity/HomePageActivity$MediaScanJob;,
        Lcom/miui/gallery/activity/HomePageActivity$CallbackWrapper;
    }
.end annotation


# instance fields
.field private MT_CAMERA_PACKAGE_NAME:Ljava/lang/String;

.field private MT_CAMERA_SERVICE_CLASS_NAME:Ljava/lang/String;

.field private mCurrentPagePosition:I

.field private mFirstTime:Z

.field private mFragmentPagerScrollState:I

.field private mHomePageStartupHelper:Lcom/miui/gallery/activity/HomePageStartupHelper;

.field private mImmersionMenuHelper:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

.field private mIsInStartup:Z

.field private mIsProcessFirstResumed:Z

.field private mMTCameraServiceConnection:Landroid/content/ServiceConnection;

.field private mMediaScannerReceiver:Lcom/miui/gallery/scanner/MediaScannerReceiver;

.field private mMenuItemsCheckListener:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;

.field private mPager:Lmiui/view/ViewPager;

.field private mStartUpPage:I


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/activity/BaseActivity;-><init>()V

    const-string v0, "com.mlab.cam"

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->MT_CAMERA_PACKAGE_NAME:Ljava/lang/String;

    const-string v0, "com.mtlab.service.CameraRemoteService"

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->MT_CAMERA_SERVICE_CLASS_NAME:Ljava/lang/String;

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mFirstTime:Z

    const/4 v1, 0x0

    iput v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    iput-boolean v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mIsProcessFirstResumed:Z

    iput-boolean v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mIsInStartup:Z

    iput v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mFragmentPagerScrollState:I

    new-instance v0, Lcom/miui/gallery/activity/HomePageActivity$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/activity/HomePageActivity$2;-><init>(Lcom/miui/gallery/activity/HomePageActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMenuItemsCheckListener:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/activity/HomePageActivity;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    return p0
.end method

.method static synthetic access$1000(Lcom/miui/gallery/activity/HomePageActivity;)Landroid/content/ServiceConnection;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMTCameraServiceConnection:Landroid/content/ServiceConnection;

    return-object p0
.end method

.method static synthetic access$1002(Lcom/miui/gallery/activity/HomePageActivity;Landroid/content/ServiceConnection;)Landroid/content/ServiceConnection;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMTCameraServiceConnection:Landroid/content/ServiceConnection;

    return-object p1
.end method

.method static synthetic access$102(Lcom/miui/gallery/activity/HomePageActivity;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    return p1
.end method

.method static synthetic access$1100(Lcom/miui/gallery/activity/HomePageActivity;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/HomePageActivity;->MT_CAMERA_PACKAGE_NAME:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$1200(Lcom/miui/gallery/activity/HomePageActivity;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/HomePageActivity;->MT_CAMERA_SERVICE_CLASS_NAME:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/activity/HomePageActivity;)Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mImmersionMenuHelper:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/activity/HomePageActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->markAlbumPageVisible()V

    return-void
.end method

.method static synthetic access$400(Lcom/miui/gallery/activity/HomePageActivity;I)Ljava/lang/String;
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/activity/HomePageActivity;->getPageName(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$500(Lcom/miui/gallery/activity/HomePageActivity;I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/activity/HomePageActivity;->updateActionBarMore(I)V

    return-void
.end method

.method static synthetic access$602(Lcom/miui/gallery/activity/HomePageActivity;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mFragmentPagerScrollState:I

    return p1
.end method

.method static synthetic access$700(Lcom/miui/gallery/activity/HomePageActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->setupOtherFragments()V

    return-void
.end method

.method static synthetic access$800(Lcom/miui/gallery/activity/HomePageActivity;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mFirstTime:Z

    return p0
.end method

.method static synthetic access$802(Lcom/miui/gallery/activity/HomePageActivity;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mFirstTime:Z

    return p1
.end method

.method static synthetic access$900(Lcom/miui/gallery/activity/HomePageActivity;)Lmiui/view/ViewPager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mPager:Lmiui/view/ViewPager;

    return-object p0
.end method

.method static synthetic access$902(Lcom/miui/gallery/activity/HomePageActivity;Lmiui/view/ViewPager;)Lmiui/view/ViewPager;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mPager:Lmiui/view/ViewPager;

    return-object p1
.end method

.method private bindMTCameraRemoteService()V
    .locals 2

    sget-object v0, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    const-string v1, "vela"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getWorkHandler()Lcom/android/internal/CompatHandler;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/activity/HomePageActivity$4;

    invoke-direct {v1, p0}, Lcom/miui/gallery/activity/HomePageActivity$4;-><init>(Lcom/miui/gallery/activity/HomePageActivity;)V

    invoke-virtual {v0, v1}, Lcom/android/internal/CompatHandler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private getPageName(I)Ljava/lang/String;
    .locals 1

    if-nez p1, :cond_0

    const-string p1, "home"

    return-object p1

    :cond_0
    const/4 v0, 0x1

    if-ne p1, v0, :cond_1

    const-string p1, "album"

    return-object p1

    :cond_1
    const/4 v0, 0x2

    if-ne p1, v0, :cond_2

    const-string p1, "assistant"

    return-object p1

    :cond_2
    const/4 p1, 0x0

    return-object p1
.end method

.method private markAlbumPageVisible()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0}, Lmiui/app/ActionBar;->getFragmentTabCount()I

    move-result v0

    const/4 v1, 0x1

    if-ge v1, v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->getFragmentAt(I)Landroid/app/Fragment;

    move-result-object v0

    if-eqz v0, :cond_0

    instance-of v2, v0, Lcom/miui/gallery/ui/AlbumPageFragment;

    if-eqz v2, :cond_0

    invoke-virtual {v0, v1}, Landroid/app/Fragment;->setUserVisibleHint(Z)V

    :cond_0
    return-void
.end method

.method private registerMediaScannerReceiver()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMediaScannerReceiver:Lcom/miui/gallery/scanner/MediaScannerReceiver;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/scanner/MediaScannerReceiver;

    invoke-direct {v0}, Lcom/miui/gallery/scanner/MediaScannerReceiver;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMediaScannerReceiver:Lcom/miui/gallery/scanner/MediaScannerReceiver;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMediaScannerReceiver:Lcom/miui/gallery/scanner/MediaScannerReceiver;

    invoke-static {p0, v0}, Lcom/miui/gallery/scanner/MediaScannerReceiver;->register(Landroid/content/Context;Lcom/miui/gallery/scanner/MediaScannerReceiver;)V

    return-void
.end method

.method private setupActionBar()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, p0, v1, v2}, Lmiui/app/ActionBar;->setFragmentViewPagerMode(Landroid/content/Context;Landroid/app/FragmentManager;Z)V

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    const v1, 0x7f0b001e

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->setCustomView(I)V

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0}, Lmiui/app/ActionBar;->getCustomView()Landroid/view/View;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

    invoke-direct {v1, p0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mImmersionMenuHelper:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mImmersionMenuHelper:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMenuItemsCheckListener:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->registerMenuItemsCheckListener(Lcom/miui/gallery/activity/HomePageImmersionMenuHelper$MenuItemsCheckListener;)V

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mImmersionMenuHelper:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

    invoke-virtual {v1}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->checkRedDotFeature()V

    const v1, 0x7f090220

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/activity/HomePageActivity$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/activity/HomePageActivity$1;-><init>(Lcom/miui/gallery/activity/HomePageActivity;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    invoke-direct {p0, v0}, Lcom/miui/gallery/activity/HomePageActivity;->updateActionBarMore(I)V

    return-void
.end method

.method private setupHomePageFragment()V
    .locals 12

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v1}, Lmiui/app/ActionBar;->newTab()Landroid/app/ActionBar$Tab;

    move-result-object v1

    const v2, 0x7f1004f7

    invoke-virtual {v1, v2}, Landroid/app/ActionBar$Tab;->setText(I)Landroid/app/ActionBar$Tab;

    move-result-object v2

    const-class v3, Lcom/miui/gallery/ui/HomePageFragment;

    const-string v1, "HomePageFragment"

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-virtual/range {v0 .. v5}, Lmiui/app/ActionBar;->addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;Ljava/lang/Class;Landroid/os/Bundle;Z)I

    iget-object v6, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0}, Lmiui/app/ActionBar;->newTab()Landroid/app/ActionBar$Tab;

    move-result-object v0

    const v1, 0x7f10006d

    invoke-virtual {v0, v1}, Landroid/app/ActionBar$Tab;->setText(I)Landroid/app/ActionBar$Tab;

    move-result-object v8

    const-class v9, Landroid/app/Fragment;

    const-string v7, "AlbumStub"

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-virtual/range {v6 .. v11}, Lmiui/app/ActionBar;->addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;Ljava/lang/Class;Landroid/os/Bundle;Z)I

    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isDeviceSupportStoryFunction()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0}, Lmiui/app/ActionBar;->newTab()Landroid/app/ActionBar$Tab;

    move-result-object v0

    const v2, 0x7f100099

    invoke-virtual {v0, v2}, Landroid/app/ActionBar$Tab;->setText(I)Landroid/app/ActionBar$Tab;

    move-result-object v3

    const-class v4, Landroid/app/Fragment;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v2, "StoryStub"

    invoke-virtual/range {v1 .. v6}, Lmiui/app/ActionBar;->addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;Ljava/lang/Class;Landroid/os/Bundle;Z)I

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    new-instance v1, Lcom/miui/gallery/activity/HomePageActivity$3;

    invoke-direct {v1, p0}, Lcom/miui/gallery/activity/HomePageActivity$3;-><init>(Lcom/miui/gallery/activity/HomePageActivity;)V

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->addOnFragmentViewPagerChangeListener(Lmiui/app/ActionBar$FragmentViewPagerChangeListener;)V

    return-void
.end method

.method private setupOtherFragments()V
    .locals 9

    iget-boolean v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mIsInStartup:Z

    if-eqz v0, :cond_3

    iget v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mFragmentPagerScrollState:I

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mIsInStartup:Z

    iget v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v2}, Lmiui/app/ActionBar;->newTab()Landroid/app/ActionBar$Tab;

    move-result-object v2

    const v3, 0x7f10006d

    invoke-virtual {v2, v3}, Landroid/app/ActionBar$Tab;->setText(I)Landroid/app/ActionBar$Tab;

    move-result-object v3

    const/4 v4, 0x1

    const-class v5, Lcom/miui/gallery/ui/AlbumPageFragment;

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v2, "AlbumPageFragment"

    invoke-virtual/range {v1 .. v7}, Lmiui/app/ActionBar;->addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;ILjava/lang/Class;Landroid/os/Bundle;Z)I

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    const/4 v2, 0x2

    invoke-virtual {v1, v2}, Lmiui/app/ActionBar;->removeFragmentTabAt(I)V

    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isDeviceSupportStoryFunction()Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v1}, Lmiui/app/ActionBar;->newTab()Landroid/app/ActionBar$Tab;

    move-result-object v1

    const v3, 0x7f0b0043

    invoke-virtual {v1, v3}, Landroid/app/ActionBar$Tab;->setCustomView(I)Landroid/app/ActionBar$Tab;

    move-result-object v4

    const/4 v5, 0x2

    const-class v6, Lcom/miui/gallery/card/ui/cardlist/AssistantPageFragment;

    const/4 v7, 0x0

    const/4 v8, 0x0

    const-string v3, "AssistantPageFragment"

    invoke-virtual/range {v2 .. v8}, Lmiui/app/ActionBar;->addFragmentTab(Ljava/lang/String;Landroid/app/ActionBar$Tab;ILjava/lang/Class;Landroid/os/Bundle;Z)I

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    const/4 v2, 0x3

    invoke-virtual {v1, v2}, Lmiui/app/ActionBar;->removeFragmentTabAt(I)V

    :cond_1
    iget v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mStartUpPage:I

    if-lez v1, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v2}, Lmiui/app/ActionBar;->getTabCount()I

    move-result v2

    if-ge v1, v2, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget v2, p0, Lcom/miui/gallery/activity/HomePageActivity;->mStartUpPage:I

    invoke-virtual {v1, v2}, Lmiui/app/ActionBar;->getTabAt(I)Landroid/app/ActionBar$Tab;

    move-result-object v1

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->selectTab(Landroid/app/ActionBar$Tab;)V

    goto :goto_0

    :cond_2
    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v2, v0}, Lmiui/app/ActionBar;->getTabAt(I)Landroid/app/ActionBar$Tab;

    move-result-object v0

    invoke-virtual {v1, v0}, Lmiui/app/ActionBar;->selectTab(Landroid/app/ActionBar$Tab;)V

    :cond_3
    :goto_0
    return-void
.end method

.method private unbindMTCameraRemoteService()V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getWorkHandler()Lcom/android/internal/CompatHandler;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/activity/HomePageActivity$5;

    invoke-direct {v1, p0}, Lcom/miui/gallery/activity/HomePageActivity$5;-><init>(Lcom/miui/gallery/activity/HomePageActivity;)V

    invoke-virtual {v0, v1}, Lcom/android/internal/CompatHandler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private unregisterMediaScannerReceiver()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMediaScannerReceiver:Lcom/miui/gallery/scanner/MediaScannerReceiver;

    invoke-static {p0, v0}, Lcom/miui/gallery/scanner/MediaScannerReceiver;->unregister(Landroid/content/Context;Lcom/miui/gallery/scanner/MediaScannerReceiver;)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mMediaScannerReceiver:Lcom/miui/gallery/scanner/MediaScannerReceiver;

    return-void
.end method

.method private updateActionBarMore(I)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isDeviceSupportStoryFunction()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->getTabAt(I)Landroid/app/ActionBar$Tab;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ActionBar$Tab;->getCustomView()Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->getTabAt(I)Landroid/app/ActionBar$Tab;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ActionBar$Tab;->getCustomView()Landroid/view/View;

    move-result-object v0

    const v1, 0x7f090065

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    const-string v1, "assistant_tab"

    invoke-static {v1}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getRedDotStatus(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x0

    goto :goto_0

    :cond_0
    const/4 v1, 0x4

    :goto_0
    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mImmersionMenuHelper:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->checkRedDotFeature()V

    :cond_2
    const/4 v0, 0x1

    if-ne p1, v0, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {p1, v0}, Lmiui/app/ActionBar;->setDisplayShowCustomEnabled(Z)V

    goto :goto_1

    :cond_3
    if-nez p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {p1, v0}, Lmiui/app/ActionBar;->setDisplayShowCustomEnabled(Z)V

    goto :goto_1

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {p1, v0}, Lmiui/app/ActionBar;->setDisplayShowCustomEnabled(Z)V

    :goto_1
    return-void
.end method


# virtual methods
.method protected dispatchAppFocused()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    iget v1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->getFragmentAt(I)Landroid/app/Fragment;

    move-result-object v0

    instance-of v1, v0, Lcom/miui/gallery/util/OnAppFocusedListener;

    if-eqz v1, :cond_0

    check-cast v0, Lcom/miui/gallery/util/OnAppFocusedListener;

    invoke-interface {v0}, Lcom/miui/gallery/util/OnAppFocusedListener;->onAppFocused()V

    :cond_0
    return-void
.end method

.method public getStartupHelper()Lcom/miui/gallery/activity/HomePageStartupHelper;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mHomePageStartupHelper:Lcom/miui/gallery/activity/HomePageStartupHelper;

    return-object v0
.end method

.method protected hasCustomContentView()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method protected isCheckPermissionCustomized()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2

    new-instance v0, Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-direct {v0, p0, p0}, Lcom/miui/gallery/activity/HomePageStartupHelper;-><init>(Landroid/content/Context;Lcom/miui/gallery/activity/HomePageStartupHelper$Attacher;)V

    iput-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mHomePageStartupHelper:Lcom/miui/gallery/activity/HomePageStartupHelper;

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mHomePageStartupHelper:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/activity/HomePageStartupHelper;->onActivityCreate()V

    invoke-super {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageActivity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    const-string v1, "extra_start_page"

    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mStartUpPage:I

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Lmiui/app/ActionBar;->setFragmentViewPagerMode(Landroid/content/Context;Landroid/app/FragmentManager;)V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->setupHomePageFragment()V

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->onCreateOrDestroyHomePage()V

    return-void
.end method

.method protected onCtaChecked(Z)V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/preference/DocumentProviderPreference;->isFirstEntrance()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/util/DocumentProviderUtils;->needRequestExternalSDCardPermission(Landroid/content/Context;)Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/util/DocumentProviderUtils;->startFirstEntrancyPermissionActivityForResult(Landroid/app/Activity;)V

    :cond_0
    return-void
.end method

.method public onDestroy()V
    .locals 1

    invoke-static {}, Landroidx/lifecycle/ProcessLifecycleOwner;->get()Landroidx/lifecycle/LifecycleOwner;

    move-result-object v0

    invoke-interface {v0}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroidx/lifecycle/Lifecycle;->removeObserver(Landroidx/lifecycle/LifecycleObserver;)V

    invoke-super {p0}, Lcom/miui/gallery/activity/BaseActivity;->onDestroy()V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->unregisterMediaScannerReceiver()V

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mHomePageStartupHelper:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/activity/HomePageStartupHelper;->onActivityDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mImmersionMenuHelper:Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/activity/HomePageImmersionMenuHelper;->onActivityDestroy()V

    :cond_0
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->onCreateOrDestroyHomePage()V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->unbindMTCameraRemoteService()V

    return-void
.end method

.method protected onPause()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/activity/BaseActivity;->onPause()V

    iget v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    invoke-direct {p0, v0}, Lcom/miui/gallery/activity/HomePageActivity;->getPageName(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordPageEnd(Landroid/app/Activity;Ljava/lang/String;)V

    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 2

    const-string v0, "HomePageActivity"

    const-string v1, "onPermissionsChecked"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/activity/BaseActivity;->onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object p1

    new-instance p2, Lcom/miui/gallery/activity/HomePageActivity$MediaScanJob;

    const/4 v0, 0x0

    invoke-direct {p2, v0}, Lcom/miui/gallery/activity/HomePageActivity$MediaScanJob;-><init>(Lcom/miui/gallery/activity/HomePageActivity$1;)V

    invoke-virtual {p1, p2}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;)Lcom/miui/gallery/threadpool/Future;

    return-void
.end method

.method protected onResume()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/activity/BaseActivity;->onResume()V

    iget v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    invoke-direct {p0, v0}, Lcom/miui/gallery/activity/HomePageActivity;->getPageName(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordPageStart(Landroid/app/Activity;Ljava/lang/String;)V

    iget v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mCurrentPagePosition:I

    invoke-direct {p0, v0}, Lcom/miui/gallery/activity/HomePageActivity;->updateActionBarMore(I)V

    return-void
.end method

.method public onResume(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 2

    invoke-static {}, Landroidx/lifecycle/ProcessLifecycleOwner;->get()Landroidx/lifecycle/LifecycleOwner;

    move-result-object v0

    if-ne p1, v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageActivity;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    invoke-virtual {p1}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p1

    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->STARTED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p1, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p1

    if-eqz p1, :cond_1

    const-string p1, "HomePageActivity"

    const-string v0, "onProcessResumed"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageActivity;->dispatchAppFocused()V

    iget-boolean p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mIsProcessFirstResumed:Z

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/gallery/activity/HomePageActivity;->mIsProcessFirstResumed:Z

    goto :goto_0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/activity/HomePageActivity$MediaScanJob;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/gallery/activity/HomePageActivity$MediaScanJob;-><init>(Lcom/miui/gallery/activity/HomePageActivity$1;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;)Lcom/miui/gallery/threadpool/Future;

    :cond_1
    :goto_0
    return-void
.end method

.method public onStartup()V
    .locals 1

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/activity/HomePageActivity;->mIsInStartup:Z

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->setupOtherFragments()V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->setupActionBar()V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->registerMediaScannerReceiver()V

    invoke-direct {p0}, Lcom/miui/gallery/activity/HomePageActivity;->bindMTCameraRemoteService()V

    invoke-static {}, Landroidx/lifecycle/ProcessLifecycleOwner;->get()Landroidx/lifecycle/LifecycleOwner;

    move-result-object v0

    invoke-interface {v0}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/HomePageActivity;->checkPermission()V

    return-void
.end method

.method public onWindowStartingActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;
    .locals 1

    new-instance v0, Lcom/miui/gallery/activity/HomePageActivity$CallbackWrapper;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/activity/HomePageActivity$CallbackWrapper;-><init>(Lcom/miui/gallery/activity/HomePageActivity;Landroid/view/ActionMode$Callback;)V

    invoke-super {p0, v0}, Lcom/miui/gallery/activity/BaseActivity;->onWindowStartingActionMode(Landroid/view/ActionMode$Callback;)Landroid/view/ActionMode;

    move-result-object p1

    return-object p1
.end method

.method protected supportEnterSetting()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method
