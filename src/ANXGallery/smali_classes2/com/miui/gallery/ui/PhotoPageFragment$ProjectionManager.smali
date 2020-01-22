.class Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;
.super Ljava/lang/Object;
.source "PhotoPageFragment.java"

# interfaces
.implements Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;
.implements Lcom/miui/gallery/projection/RemoteControlReceiver$RemoteControlListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "ProjectionManager"
.end annotation


# instance fields
.field private mConnectController:Lcom/miui/gallery/projection/IConnectController;

.field private mCurItem:Lcom/miui/gallery/model/BaseDataItem;

.field private final mHandler:Landroid/os/Handler;

.field private mRemoteController:Lcom/miui/gallery/projection/RemoteController;

.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPageFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPageFragment;)V
    .locals 2

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mHandler:Landroid/os/Handler;

    new-instance v0, Lcom/miui/gallery/projection/RemoteController;

    invoke-direct {v0, p0}, Lcom/miui/gallery/projection/RemoteController;-><init>(Lcom/miui/gallery/projection/RemoteControlReceiver$RemoteControlListener;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    invoke-static {}, Lcom/miui/gallery/projection/ConnectControllerSingleton;->getInstance()Lcom/miui/gallery/projection/ConnectController;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    check-cast v0, Lcom/miui/gallery/projection/ConnectController;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/projection/ConnectController;->attachActivity(Landroid/app/Activity;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {p1, p0}, Lcom/miui/gallery/projection/IConnectController;->registerStatusListener(Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;)V

    return-void
.end method

.method static synthetic access$4700(Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->projectClicked()V

    return-void
.end method

.method private isBigScreenSupported(Lcom/miui/gallery/model/BaseDataItem;)Z
    .locals 1

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->isSecret()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getPathDisplayBetter()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    return p1

    :cond_1
    :goto_0
    const/4 p1, 0x0

    return p1
.end method

.method private projectClicked()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0}, Lcom/miui/gallery/activity/BaseActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/gallery/util/PhotoOperationsUtil;->isScreenProjectOn(Landroid/content/ContentResolver;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    const v2, 0x7f1006db

    invoke-virtual {v1, v2}, Lcom/miui/gallery/activity/BaseActivity;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController;->project()V

    :goto_0
    return-void
.end method


# virtual methods
.method public close()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0, p0}, Lcom/miui/gallery/projection/IConnectController;->unregisterStatusListener(Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    check-cast v0, Lcom/miui/gallery/projection/ConnectController;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/ConnectController;->detachActivity(Landroid/app/Activity;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController;->closeService()V

    return-void
.end method

.method public disableRemoteControl()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/RemoteController;->disableDelay(Landroid/content/Context;)V

    return-void
.end method

.method public enableRemoteControl()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController;->isConnected()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/RemoteController;->enable(Landroid/content/Context;)V

    :cond_0
    return-void
.end method

.method public enterSlideShow(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0, p1}, Lcom/miui/gallery/projection/IConnectController;->showSlide(I)Z

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    const v1, 0x7f1006de

    invoke-virtual {v0, v1}, Lcom/miui/gallery/activity/BaseActivity;->getText(I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    return-void

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/projection/RemoteController;->disable(Landroid/content/Context;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v1}, Lcom/miui/gallery/projection/IConnectController;->getConnectedDevice()Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/ui/ProjectSlideFragment;->showProjectSlideFragment(Lcom/miui/gallery/activity/BaseActivity;Lmiui/app/Fragment;Ljava/lang/String;)V

    return-void
.end method

.method public exitSlideShow()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mCurItem:Lcom/miui/gallery/model/BaseDataItem;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v1}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result v1

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->settleItem(Lcom/miui/gallery/model/BaseDataItem;I)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/RemoteController;->enable(Landroid/content/Context;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->isVisible()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/RemoteController;->disableDelay(Landroid/content/Context;)V

    :cond_0
    return-void
.end method

.method public isConnected()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController;->isConnected()Z

    move-result v0

    return v0
.end method

.method public onConnectStatusChanged(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mHandler:Landroid/os/Handler;

    new-instance v1, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager$1;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager$1;-><init>(Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;I)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/projection/RemoteController;->enable(Landroid/content/Context;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mRemoteController:Lcom/miui/gallery/projection/RemoteController;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/projection/RemoteController;->disable(Landroid/content/Context;)V

    :goto_0
    return-void
.end method

.method public onOpenStatusChanged(Z)V
    .locals 0

    return-void
.end method

.method public onRemoteControl(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mAdapter:Lcom/miui/gallery/adapter/PhotoPageAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/adapter/PhotoPageAdapter;->getCount()I

    move-result v0

    if-gtz v0, :cond_0

    return-void

    :cond_0
    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result p1

    add-int/lit8 p1, p1, -0x1

    if-ltz p1, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-static {v1}, Lcom/miui/gallery/util/MiscUtil;->isKeyGuardLocked(Landroid/content/Context;)Z

    move-result v1

    xor-int/lit8 v1, v1, 0x1

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItem(IZ)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result p1

    add-int/lit8 p1, p1, 0x1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mAdapter:Lcom/miui/gallery/adapter/PhotoPageAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/adapter/PhotoPageAdapter;->getCount()I

    move-result v0

    if-ge p1, v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-static {v1}, Lcom/miui/gallery/util/MiscUtil;->isKeyGuardLocked(Landroid/content/Context;)Z

    move-result v1

    xor-int/lit8 v1, v1, 0x1

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItem(IZ)V

    :cond_2
    :goto_0
    return-void
.end method

.method refreshProjectState()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0}, Lcom/miui/gallery/projection/IConnectController;->getConnectStatus()I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->refreshProjectState(I)V

    return-void
.end method

.method refreshProjectState(I)V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->isAdded()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    sget-object v0, Lcom/miui/gallery/ui/ShareStateRouter;->IS_MISHARE_AVAILABLE:Lcom/miui/gallery/util/LazyValue;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_3

    const/4 v0, -0x1

    if-eq p1, v0, :cond_2

    if-eqz p1, :cond_1

    if-eq p1, v1, :cond_1

    goto/16 :goto_1

    :cond_1
    invoke-static {}, Lcom/miui/gallery/ui/ShareStateRouter;->getInstance()Lcom/miui/gallery/ui/ShareStateRouter;

    move-result-object p1

    const/4 v0, 0x3

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/ShareStateRouter;->broadcastProjectState(I)V

    goto/16 :goto_1

    :cond_2
    invoke-static {}, Lcom/miui/gallery/ui/ShareStateRouter;->getInstance()Lcom/miui/gallery/ui/ShareStateRouter;

    move-result-object p1

    const/4 v0, 0x2

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/ShareStateRouter;->broadcastProjectState(I)V

    goto :goto_1

    :cond_3
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2100(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$MenuManager;

    move-result-object v0

    if-eqz v0, :cond_7

    const/4 v0, 0x0

    if-eqz p1, :cond_5

    if-eq p1, v1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v2, 0x7f100607

    invoke-virtual {p1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v2, 0x7f1005f6

    new-array v3, v1, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v4}, Lcom/miui/gallery/projection/IConnectController;->getConnectedDevice()Ljava/lang/String;

    move-result-object v4

    aput-object v4, v3, v0

    invoke-virtual {p1, v2, v3}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_5
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v2, 0x7f1005f7

    new-array v3, v1, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v4}, Lcom/miui/gallery/projection/IConnectController;->getConnectingDevice()Ljava/lang/String;

    move-result-object v4

    aput-object v4, v3, v0

    invoke-virtual {p1, v2, v3}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    :goto_0
    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mCurItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-direct {p0, v2}, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->isBigScreenSupported(Lcom/miui/gallery/model/BaseDataItem;)Z

    move-result v2

    const-string v3, "PhotoPageFragment"

    if-eqz v2, :cond_6

    const-string v0, "refreshProjectState: [%s] [visible]"

    invoke-static {v3, v0, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2100(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$MenuManager;

    move-result-object v0

    invoke-virtual {v0, p1, v1, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$MenuManager;->refreshCastItem(Ljava/lang/String;ZZ)V

    goto :goto_1

    :cond_6
    const-string v2, "refreshProjectState: [%s] [gone]"

    invoke-static {v3, v2, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v2}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2100(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$MenuManager;

    move-result-object v2

    invoke-virtual {v2, p1, v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$MenuManager;->refreshCastItem(Ljava/lang/String;ZZ)V

    :cond_7
    :goto_1
    return-void
.end method

.method public settleItem(Lcom/miui/gallery/model/BaseDataItem;I)V
    .locals 3

    if-nez p1, :cond_0

    return-void

    :cond_0
    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mCurItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "PhotoPageFragment"

    const-string v2, "updateItem %s  %d"

    invoke-static {v1, v2, p1, v0}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getPathDisplayBetter()Ljava/lang/String;

    move-result-object p1

    invoke-interface {v0, p1, p2}, Lcom/miui/gallery/projection/IConnectController;->updateCurrentPhoto(Ljava/lang/String;I)V

    return-void
.end method

.method public updateRemoteView(Lcom/miui/gallery/model/BaseDataItem;Landroid/graphics/RectF;)V
    .locals 12

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result v0

    if-lez v0, :cond_2

    invoke-virtual {p2}, Landroid/graphics/RectF;->width()F

    move-result v0

    const/4 v1, 0x0

    cmpl-float v0, v0, v1

    if-lez v0, :cond_2

    invoke-virtual {p2}, Landroid/graphics/RectF;->width()F

    move-result v0

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v0, v1

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v1}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v1, v2

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v2, v2, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v2}, Lcom/miui/gallery/widget/ViewPager;->getHeight()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getHeight()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v2, v3

    invoke-static {v1, v2}, Ljava/lang/Math;->min(FF)F

    move-result v1

    const v2, 0x358637bd    # 1.0E-6f

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/Utils;->floatNear(FFF)Z

    move-result v2

    if-nez v2, :cond_1

    cmpg-float v2, v0, v1

    if-gez v2, :cond_0

    goto :goto_0

    :cond_0
    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v2, v2, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v2}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    int-to-float v2, v2

    iget v3, p2, Landroid/graphics/RectF;->left:F

    sub-float/2addr v2, v3

    div-float/2addr v2, v0

    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v3, v3, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v3}, Lcom/miui/gallery/widget/ViewPager;->getHeight()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    int-to-float v3, v3

    iget p2, p2, Landroid/graphics/RectF;->top:F

    sub-float/2addr v3, p2

    div-float/2addr v3, v0

    move v11, v0

    goto :goto_1

    :cond_1
    :goto_0
    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result p2

    int-to-float p2, p2

    const/high16 v0, 0x40000000    # 2.0f

    div-float v2, p2, v0

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getHeight()I

    move-result p2

    int-to-float p2, p2

    div-float v3, p2, v0

    move v11, v1

    :goto_1
    move v5, v2

    move v6, v3

    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result p2

    int-to-float p2, p2

    mul-float/2addr p2, v1

    invoke-static {p2}, Ljava/lang/Math;->round(F)I

    move-result p2

    int-to-float v7, p2

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getHeight()I

    move-result p2

    int-to-float p2, p2

    mul-float/2addr p2, v1

    invoke-static {p2}, Ljava/lang/Math;->round(F)I

    move-result p2

    int-to-float v8, p2

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result p2

    int-to-float v9, p2

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getHeight()I

    move-result p1

    int-to-float v10, p1

    invoke-interface/range {v4 .. v11}, Lcom/miui/gallery/projection/IConnectController;->syncRemoteView(FFFFFFF)V

    :cond_2
    return-void
.end method

.method public updateSet(Lcom/miui/gallery/model/BaseDataSet;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->mConnectController:Lcom/miui/gallery/projection/IConnectController;

    invoke-interface {v0, p1}, Lcom/miui/gallery/projection/IConnectController;->updateCurrentFolder(Lcom/miui/gallery/projection/IConnectController$DataSet;)V

    return-void
.end method
