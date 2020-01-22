.class public abstract Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;
.super Lcom/miui/gallery/base_optimization/mvp/view/Activity;
.source "BasePermissionCheckActivitity.java"

# interfaces
.implements Lcom/miui/gallery/permission/core/PermissionCheckCallback;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/base_optimization/mvp/view/Activity<",
        "TP;>;",
        "Lcom/miui/gallery/permission/core/PermissionCheckCallback;"
    }
.end annotation


# instance fields
.field private mDelegate:Lcom/miui/gallery/app/base/PermissionDelegate;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/base_optimization/mvp/view/Activity;-><init>()V

    return-void
.end method


# virtual methods
.method public allowUseOnOffline()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;
    .locals 4

    new-instance v0, Lcom/miui/gallery/permission/core/RuntimePermission;

    const v1, 0x7f100635

    invoke-virtual {p0, v1}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    const-string v3, "android.permission.WRITE_EXTERNAL_STORAGE"

    invoke-direct {v0, v3, v1, v2}, Lcom/miui/gallery/permission/core/RuntimePermission;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    new-array v1, v2, [Lcom/miui/gallery/permission/core/RuntimePermission;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    return-object v1
.end method

.method protected isShowWhenLocked()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->onCreate(Landroid/os/Bundle;)V

    new-instance p1, Lcom/miui/gallery/app/base/PermissionDelegate;

    invoke-direct {p1, p0}, Lcom/miui/gallery/app/base/PermissionDelegate;-><init>(Landroid/app/Activity;)V

    iput-object p1, p0, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->mDelegate:Lcom/miui/gallery/app/base/PermissionDelegate;

    iget-object p1, p0, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->mDelegate:Lcom/miui/gallery/app/base/PermissionDelegate;

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->isShowWhenLocked()Z

    move-result v1

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/app/base/PermissionDelegate;->onCreate([Lcom/miui/gallery/permission/core/RuntimePermission;Z)V

    return-void
.end method

.method public onCtaChecked(Z)V
    .locals 0

    return-void
.end method

.method protected onDestroy()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->onDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->mDelegate:Lcom/miui/gallery/app/base/PermissionDelegate;

    invoke-virtual {v0}, Lcom/miui/gallery/app/base/PermissionDelegate;->onDestory()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->mDelegate:Lcom/miui/gallery/app/base/PermissionDelegate;

    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->allowUseOnOfflineGlobal()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->allowUseOnOffline()Z

    move-result p1

    if-nez p1, :cond_1

    :cond_0
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->canConnectNetwork()Z

    move-result p1

    if-eqz p1, :cond_2

    :cond_1
    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->onCtaChecked(Z)V

    goto :goto_0

    :cond_2
    new-instance p1, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity$1;-><init>(Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;)V

    invoke-static {p0, p1}, Lcom/miui/gallery/agreement/AgreementsUtils;->showUserAgreements(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    :goto_0
    return-void
.end method
