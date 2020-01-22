.class public Lcom/miui/gallery/app/base/PermissionDelegate;
.super Ljava/lang/Object;
.source "PermissionDelegate.java"


# instance fields
.field private mActivityWeakReference:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Landroid/app/Activity;",
            ">;"
        }
    .end annotation
.end field

.field private mUserRequestPermission:[Lcom/miui/gallery/permission/core/RuntimePermission;


# direct methods
.method public constructor <init>(Landroid/app/Activity;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/miui/gallery/app/base/PermissionDelegate;->mActivityWeakReference:Ljava/lang/ref/WeakReference;

    return-void
.end method

.method private finish()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/app/base/PermissionDelegate;->getActivity()Landroid/app/Activity;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/PermissionDelegate;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->finish()V

    :cond_0
    return-void
.end method

.method private getActivity()Landroid/app/Activity;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/app/base/PermissionDelegate;->mActivityWeakReference:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/Activity;

    return-object v0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method


# virtual methods
.method public onCreate([Lcom/miui/gallery/permission/core/RuntimePermission;Z)V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/app/base/PermissionDelegate;->getActivity()Landroid/app/Activity;

    move-result-object v0

    instance-of v0, v0, Lcom/miui/gallery/permission/core/PermissionCheckCallback;

    if-nez v0, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/PermissionDelegate;->finish()V

    return-void

    :cond_0
    iput-object p1, p0, Lcom/miui/gallery/app/base/PermissionDelegate;->mUserRequestPermission:[Lcom/miui/gallery/permission/core/RuntimePermission;

    new-instance p1, Landroid/os/Bundle;

    invoke-direct {p1}, Landroid/os/Bundle;-><init>()V

    const-string v0, "SHOW_WHEN_LOCKED"

    invoke-virtual {p1, v0, p2}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    invoke-direct {p0}, Lcom/miui/gallery/app/base/PermissionDelegate;->getActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-direct {p0}, Lcom/miui/gallery/app/base/PermissionDelegate;->getActivity()Landroid/app/Activity;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/permission/core/PermissionCheckCallback;

    invoke-static {p2, v0, p1}, Lcom/miui/gallery/permission/core/PermissionInjector;->injectIfNeededIn(Landroid/app/Activity;Lcom/miui/gallery/permission/core/PermissionCheckCallback;Landroid/os/Bundle;)V

    return-void
.end method

.method public onDestory()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/app/base/PermissionDelegate;->mActivityWeakReference:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->clear()V

    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/app/base/PermissionDelegate;->mActivityWeakReference:Ljava/lang/ref/WeakReference;

    return-void
.end method
