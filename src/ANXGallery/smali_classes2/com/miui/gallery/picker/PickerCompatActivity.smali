.class public Lcom/miui/gallery/picker/PickerCompatActivity;
.super Lcom/miui/security/CrossUserCompatActivity;
.source "PickerCompatActivity.java"

# interfaces
.implements Lcom/miui/gallery/permission/core/PermissionCheckCallback;


# instance fields
.field protected mActionBar:Lmiui/app/ActionBar;

.field private mIsResumed:Z


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/security/CrossUserCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected allowUseOnOffline()Z
    .locals 1

    const/4 v0, 0x1

    return v0
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

    invoke-virtual {p0, v1}, Lcom/miui/gallery/picker/PickerCompatActivity;->getString(I)Ljava/lang/String;

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

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/picker/PickerCompatActivity;->mActionBar:Lmiui/app/ActionBar;

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/security/CrossUserCompatActivity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->hasCustomContentView()Z

    move-result p1

    if-nez p1, :cond_0

    const p1, 0x7f0b0059

    invoke-virtual {p0, p1}, Lcom/miui/gallery/picker/PickerCompatActivity;->setContentView(I)V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->initActionBar()V

    const/4 p1, 0x0

    invoke-static {p0, p0, p1}, Lcom/miui/gallery/permission/core/PermissionInjector;->injectIfNeededIn(Landroid/app/Activity;Lcom/miui/gallery/permission/core/PermissionCheckCallback;Landroid/os/Bundle;)V

    return-void
.end method

.method protected onPause()V
    .locals 1

    invoke-super {p0}, Lcom/miui/security/CrossUserCompatActivity;->onPause()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/picker/PickerCompatActivity;->mIsResumed:Z

    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->allowUseOnOfflineGlobal()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->allowUseOnOffline()Z

    move-result p1

    if-nez p1, :cond_2

    :cond_0
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->canConnectNetwork()Z

    move-result p1

    if-eqz p1, :cond_1

    goto :goto_0

    :cond_1
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->canConnectNetwork()Z

    move-result p1

    if-nez p1, :cond_2

    const/4 p1, 0x0

    invoke-static {p0, p1}, Lcom/miui/gallery/agreement/AgreementsUtils;->showUserAgreements(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    :cond_2
    :goto_0
    return-void
.end method

.method protected onResume()V
    .locals 1

    invoke-super {p0}, Lcom/miui/security/CrossUserCompatActivity;->onResume()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/picker/PickerCompatActivity;->mIsResumed:Z

    return-void
.end method

.method protected onStop()V
    .locals 0

    invoke-super {p0}, Lcom/miui/security/CrossUserCompatActivity;->onStop()V

    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerCompatActivity;->mActionBar:Lmiui/app/ActionBar;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lmiui/app/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    :cond_0
    return-void
.end method

.method public startFragment(Landroid/app/Fragment;Ljava/lang/String;ZZ)V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->getFragmentContainerId()I

    move-result v0

    if-lez v0, :cond_3

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0, p2}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object v0

    if-eqz v0, :cond_0

    const-string p1, "PickerCompatActivity"

    const-string p3, "already has tag of fragment %s"

    invoke-static {p1, p3, p2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    if-eqz p3, :cond_1

    invoke-virtual {v0, p2}, Landroid/app/FragmentTransaction;->addToBackStack(Ljava/lang/String;)Landroid/app/FragmentTransaction;

    :cond_1
    if-eqz p4, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->getFragmentContainerId()I

    move-result p3

    invoke-virtual {v0, p3, p1, p2}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    goto :goto_0

    :cond_2
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->getFragmentContainerId()I

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
