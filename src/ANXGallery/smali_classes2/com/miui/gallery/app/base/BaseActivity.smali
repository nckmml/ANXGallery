.class public abstract Lcom/miui/gallery/app/base/BaseActivity;
.super Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;
.source "BaseActivity.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/app/base/BaseActivity$ScreenBroadcastReceiver;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/app/base/BasePermissionCheckActivitity<",
        "TP;>;"
    }
.end annotation


# instance fields
.field private mScreenReceiver:Landroid/content/BroadcastReceiver;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;-><init>()V

    return-void
.end method


# virtual methods
.method protected isShowWhenLocked()Z
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v1, "StartActivityWhenLocked"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseActivity;->supportShowOnScreenLocked()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseActivity;->isShowWhenLocked()Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    invoke-static {p0, p1}, Lcom/android/internal/WindowCompat;->setShowWhenLocked(Landroid/app/Activity;Z)V

    new-instance p1, Lcom/miui/gallery/app/base/BaseActivity$ScreenBroadcastReceiver;

    const/4 v0, 0x0

    invoke-direct {p1, p0, v0}, Lcom/miui/gallery/app/base/BaseActivity$ScreenBroadcastReceiver;-><init>(Lcom/miui/gallery/app/base/BaseActivity;Lcom/miui/gallery/app/base/BaseActivity$1;)V

    iput-object p1, p0, Lcom/miui/gallery/app/base/BaseActivity;->mScreenReceiver:Landroid/content/BroadcastReceiver;

    iget-object p1, p0, Lcom/miui/gallery/app/base/BaseActivity;->mScreenReceiver:Landroid/content/BroadcastReceiver;

    const-string v0, "android.intent.action.SCREEN_OFF"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/util/ReceiverUtils;->registerReceiver(Landroid/content/Context;Landroid/content/BroadcastReceiver;[Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method protected onDestroy()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/base/BasePermissionCheckActivitity;->onDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/app/base/BaseActivity;->mScreenReceiver:Landroid/content/BroadcastReceiver;

    if-eqz v0, :cond_0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/app/base/BaseActivity;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    :cond_0
    return-void
.end method

.method protected supportShowOnScreenLocked()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method
