.class public Lcom/miui/gallery/activity/BaseActivity;
.super Lcom/miui/gallery/app/Activity;
.source "BaseActivity.java"

# interfaces
.implements Lcom/miui/gallery/permission/core/PermissionCheckCallback;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/activity/BaseActivity$ScreenBroadcastReceiver;
    }
.end annotation


# instance fields
.field protected mActionBar:Lmiui/app/ActionBar;

.field private mIsResumed:Z

.field private mIsStartingEnterTransition:Z

.field private mScreenReceiver:Landroid/content/BroadcastReceiver;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/app/Activity;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsStartingEnterTransition:Z

    return-void
.end method

.method static synthetic access$102(Lcom/miui/gallery/activity/BaseActivity;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsStartingEnterTransition:Z

    return p1
.end method


# virtual methods
.method protected allowUseOnOffline()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method protected checkPermission()V
    .locals 3

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->isShowWhenLocked()Z

    move-result v1

    const-string v2, "SHOW_WHEN_LOCKED"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    invoke-static {p0, p0, v0}, Lcom/miui/gallery/permission/core/PermissionInjector;->injectIfNeededIn(Landroid/app/Activity;Lcom/miui/gallery/permission/core/PermissionCheckCallback;Landroid/os/Bundle;)V

    return-void
.end method

.method protected getFragmentContainerId()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;
    .locals 4

    new-instance v0, Lcom/miui/gallery/permission/core/RuntimePermission;

    const v1, 0x7f100635

    invoke-virtual {p0, v1}, Lcom/miui/gallery/activity/BaseActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    const-string v3, "android.permission.WRITE_EXTERNAL_STORAGE"

    invoke-direct {v0, v3, v1, v2}, Lcom/miui/gallery/permission/core/RuntimePermission;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    new-array v1, v2, [Lcom/miui/gallery/permission/core/RuntimePermission;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    return-object v1
.end method

.method protected hasCustomContentView()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected initActionBar()V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mActionBar:Lmiui/app/ActionBar;

    return-void
.end method

.method protected isCheckPermissionCustomized()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected isShowWhenLocked()Z
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v1, "StartActivityWhenLocked"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method protected needShowUserAgreements()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method protected onActivityResult(IILandroid/content/Intent;)V
    .locals 0

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/app/Activity;->onActivityResult(IILandroid/content/Intent;)V

    invoke-static {p0, p1, p2, p3}, Lcom/miui/gallery/util/DocumentProviderUtils;->handleRequestPermissionResult(Landroid/app/Activity;IILandroid/content/Intent;)V

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Activity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->hasCustomContentView()Z

    move-result p1

    if-nez p1, :cond_0

    const p1, 0x7f0b0059

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->setContentView(I)V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->initActionBar()V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->isCheckPermissionCustomized()Z

    move-result p1

    if-nez p1, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->checkPermission()V

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->supportShowOnScreenLocked()Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->isShowWhenLocked()Z

    move-result p1

    if-eqz p1, :cond_2

    const/4 p1, 0x1

    invoke-static {p0, p1}, Lcom/android/internal/WindowCompat;->setShowWhenLocked(Landroid/app/Activity;Z)V

    new-instance p1, Lcom/miui/gallery/activity/BaseActivity$ScreenBroadcastReceiver;

    const/4 v0, 0x0

    invoke-direct {p1, p0, v0}, Lcom/miui/gallery/activity/BaseActivity$ScreenBroadcastReceiver;-><init>(Lcom/miui/gallery/activity/BaseActivity;Lcom/miui/gallery/activity/BaseActivity$1;)V

    iput-object p1, p0, Lcom/miui/gallery/activity/BaseActivity;->mScreenReceiver:Landroid/content/BroadcastReceiver;

    iget-object p1, p0, Lcom/miui/gallery/activity/BaseActivity;->mScreenReceiver:Landroid/content/BroadcastReceiver;

    const-string v0, "android.intent.action.SCREEN_OFF"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/ReceiverUtils;->registerReceiver(Landroid/content/Context;Landroid/content/BroadcastReceiver;[Ljava/lang/String;)V

    :cond_2
    return-void
.end method

.method protected onCtaChecked(Z)V
    .locals 0

    return-void
.end method

.method protected onDestroy()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mScreenReceiver:Landroid/content/BroadcastReceiver;

    if-eqz v0, :cond_0

    invoke-static {p0, v0}, Lcom/miui/gallery/util/ReceiverUtils;->safeUnregisterReceiver(Landroid/content/Context;Landroid/content/BroadcastReceiver;)Z

    :cond_0
    return-void
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 1

    const/16 v0, 0x52

    if-eq p1, v0, :cond_0

    const/16 v0, 0xbb

    if-ne p1, v0, :cond_1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->supportEnterSetting()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/util/IntentUtil;->enterGallerySetting(Landroid/content/Context;)V

    const/4 p1, 0x1

    return p1

    :cond_1
    invoke-super {p0, p1, p2}, Lcom/miui/gallery/app/Activity;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result p1

    return p1
.end method

.method protected onPause()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onPause()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsResumed:Z

    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->allowUseOnOfflineGlobal()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->allowUseOnOffline()Z

    move-result p1

    if-nez p1, :cond_2

    :cond_0
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->canConnectNetwork()Z

    move-result p1

    if-nez p1, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->needShowUserAgreements()Z

    move-result p1

    if-nez p1, :cond_1

    goto :goto_0

    :cond_1
    new-instance p1, Lcom/miui/gallery/activity/BaseActivity$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/activity/BaseActivity$1;-><init>(Lcom/miui/gallery/activity/BaseActivity;)V

    invoke-static {p0, p1}, Lcom/miui/gallery/agreement/AgreementsUtils;->showUserAgreements(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    goto :goto_1

    :cond_2
    :goto_0
    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onCtaChecked(Z)V

    :goto_1
    return-void
.end method

.method protected onResume()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onResume()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsResumed:Z

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsStartingEnterTransition:Z

    return-void
.end method

.method protected onStop()V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsStartingEnterTransition:Z

    if-eqz v0, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/util/TransitionPatching;->onActivityStopWhenEnterStarting(Landroid/app/Activity;)V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsStartingEnterTransition:Z

    :cond_0
    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onStop()V

    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mActionBar:Lmiui/app/ActionBar;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lmiui/app/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    :cond_0
    return-void
.end method

.method public startFragment(Landroid/app/Fragment;Ljava/lang/String;ZZ)V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->getFragmentContainerId()I

    move-result v0

    if-lez v0, :cond_3

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0, p2}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object v0

    if-eqz v0, :cond_0

    const-string p1, "BaseActivity"

    const-string p3, "already has tag of fragment %s"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    if-eqz p3, :cond_1

    invoke-virtual {v0, p2}, Landroid/app/FragmentTransaction;->addToBackStack(Ljava/lang/String;)Landroid/app/FragmentTransaction;

    :cond_1
    if-eqz p4, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->getFragmentContainerId()I

    move-result p3

    invoke-virtual {v0, p3, p1, p2}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    goto :goto_0

    :cond_2
    invoke-virtual {p0}, Lcom/miui/gallery/activity/BaseActivity;->getFragmentContainerId()I

    move-result p3

    invoke-virtual {v0, p3, p1, p2}, Landroid/app/FragmentTransaction;->replace(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    :goto_0
    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    return-void

    :cond_3
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "invalidate container id"

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public startPostponedEnterTransition()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->startPostponedEnterTransition()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/activity/BaseActivity;->mIsStartingEnterTransition:Z

    new-instance v0, Lcom/miui/gallery/activity/BaseActivity$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/activity/BaseActivity$2;-><init>(Lcom/miui/gallery/activity/BaseActivity;)V

    invoke-static {p0, v0}, Lcom/miui/gallery/util/TransitionPatching;->setOnEnterStartedListener(Landroid/app/Activity;Ljava/lang/Runnable;)V

    return-void
.end method

.method protected supportEnterSetting()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected supportShowOnScreenLocked()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method
