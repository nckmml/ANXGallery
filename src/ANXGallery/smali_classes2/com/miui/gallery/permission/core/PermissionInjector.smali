.class public Lcom/miui/gallery/permission/core/PermissionInjector;
.super Landroid/app/Fragment;
.source "PermissionInjector.java"

# interfaces
.implements Lcom/miui/gallery/permission/core/PermissionCheckCallback;


# instance fields
.field private mCallback:Lcom/miui/gallery/permission/core/PermissionCheckCallback;

.field private mHelper:Lcom/miui/gallery/permission/core/PermissionCheckHelper;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/app/Fragment;-><init>()V

    return-void
.end method

.method public static injectIfNeededIn(Landroid/app/Activity;Lcom/miui/gallery/permission/core/PermissionCheckCallback;Landroid/os/Bundle;)V
    .locals 2

    const-string v0, "PermissionInjector"

    if-nez p1, :cond_0

    const-string p0, "The callback is null"

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-interface {p1}, Lcom/miui/gallery/permission/core/PermissionCheckCallback;->getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;

    move-result-object v1

    if-eqz v1, :cond_3

    array-length v1, v1

    if-nez v1, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Landroid/app/Activity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p0

    const-string v0, "com.miui.gallery.permission.injector"

    invoke-virtual {p0, v0}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object v1

    if-nez v1, :cond_2

    new-instance v1, Lcom/miui/gallery/permission/core/PermissionInjector;

    invoke-direct {v1}, Lcom/miui/gallery/permission/core/PermissionInjector;-><init>()V

    invoke-virtual {v1, p1}, Lcom/miui/gallery/permission/core/PermissionInjector;->setCallback(Lcom/miui/gallery/permission/core/PermissionCheckCallback;)V

    invoke-virtual {v1, p2}, Lcom/miui/gallery/permission/core/PermissionInjector;->setArguments(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object p1

    invoke-virtual {p1, v1, v0}, Landroid/app/FragmentTransaction;->add(Landroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    move-result-object p1

    invoke-virtual {p1}, Landroid/app/FragmentTransaction;->commit()I

    invoke-virtual {p0}, Landroid/app/FragmentManager;->executePendingTransactions()Z

    :cond_2
    return-void

    :cond_3
    :goto_0
    const-string p0, "Has no permissions to grant"

    invoke-static {v0, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private isShowWhenLocked()Z
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/permission/core/PermissionInjector;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    const-string v2, "SHOW_WHEN_LOCKED"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    :cond_0
    return v1
.end method


# virtual methods
.method public getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/permission/core/PermissionInjector;->mCallback:Lcom/miui/gallery/permission/core/PermissionCheckCallback;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/permission/core/PermissionCheckCallback;->getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;

    move-result-object v0

    return-object v0

    :cond_0
    const/4 v0, 0x0

    new-array v0, v0, [Lcom/miui/gallery/permission/core/RuntimePermission;

    return-object v0
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/app/Fragment;->onCreate(Landroid/os/Bundle;)V

    new-instance p1, Lcom/miui/gallery/permission/core/PermissionCheckHelper;

    invoke-direct {p0}, Lcom/miui/gallery/permission/core/PermissionInjector;->isShowWhenLocked()Z

    move-result v0

    invoke-direct {p1, p0, v0, p0}, Lcom/miui/gallery/permission/core/PermissionCheckHelper;-><init>(Landroid/app/Fragment;ZLcom/miui/gallery/permission/core/PermissionCheckCallback;)V

    iput-object p1, p0, Lcom/miui/gallery/permission/core/PermissionInjector;->mHelper:Lcom/miui/gallery/permission/core/PermissionCheckHelper;

    iget-object p1, p0, Lcom/miui/gallery/permission/core/PermissionInjector;->mHelper:Lcom/miui/gallery/permission/core/PermissionCheckHelper;

    invoke-virtual {p1}, Lcom/miui/gallery/permission/core/PermissionCheckHelper;->checkPermission()V

    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/permission/core/PermissionInjector;->mCallback:Lcom/miui/gallery/permission/core/PermissionCheckCallback;

    if-eqz v0, :cond_0

    invoke-interface {v0, p1, p2}, Lcom/miui/gallery/permission/core/PermissionCheckCallback;->onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/permission/core/PermissionInjector;->getActivity()Landroid/app/Activity;

    move-result-object p1

    if-eqz p1, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/permission/core/PermissionInjector;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-virtual {p1}, Landroid/app/Activity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p1

    invoke-virtual {p1}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroid/app/FragmentTransaction;->remove(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object p1

    invoke-virtual {p1}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    :cond_1
    return-void
.end method

.method public onRequestPermissionsResult(I[Ljava/lang/String;[I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/permission/core/PermissionInjector;->mHelper:Lcom/miui/gallery/permission/core/PermissionCheckHelper;

    invoke-virtual {v0, p1, p2, p3}, Lcom/miui/gallery/permission/core/PermissionCheckHelper;->onRequestPermissionsResult(I[Ljava/lang/String;[I)V

    return-void
.end method

.method setCallback(Lcom/miui/gallery/permission/core/PermissionCheckCallback;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/permission/core/PermissionInjector;->mCallback:Lcom/miui/gallery/permission/core/PermissionCheckCallback;

    return-void
.end method
