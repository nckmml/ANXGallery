.class public abstract Lcom/miui/gallery/app/base/BaseFragment;
.super Lcom/miui/gallery/base_optimization/mvp/view/Fragment;
.source "BaseFragment.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/base_optimization/mvp/view/Fragment<",
        "TP;>;"
    }
.end annotation


# instance fields
.field private mScreenOrientation:I


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;-><init>()V

    return-void
.end method


# virtual methods
.method protected getThemeRes()I
    .locals 1

    const v0, 0x7f110143

    return v0
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    iget v0, p1, Landroid/content/res/Configuration;->orientation:I

    iget v1, p0, Lcom/miui/gallery/app/base/BaseFragment;->mScreenOrientation:I

    if-eq v0, v1, :cond_1

    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    iput p1, p0, Lcom/miui/gallery/app/base/BaseFragment;->mScreenOrientation:I

    iget p1, p0, Lcom/miui/gallery/app/base/BaseFragment;->mScreenOrientation:I

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->onScreenOrientationChangeToVertical()V

    goto :goto_0

    :cond_0
    const/4 v0, 0x2

    if-ne p1, v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->onScreenOrientationChangeToHorizontal()V

    :cond_1
    :goto_0
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object p1

    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    iput p1, p0, Lcom/miui/gallery/app/base/BaseFragment;->mScreenOrientation:I

    return-void
.end method

.method public onPause()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->onPause()V

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->useImageLoader()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->onPagePause()V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->recordPageByDefault()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->getPageName()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordPageEnd(Landroid/app/Activity;Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method public onResume()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->onResume()V

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->useImageLoader()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->onPageResume()V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->recordPageByDefault()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/gallery/app/base/BaseFragment;->getPageName()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordPageStart(Landroid/app/Activity;Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method protected onScreenOrientationChangeToHorizontal()V
    .locals 0

    return-void
.end method

.method protected onScreenOrientationChangeToVertical()V
    .locals 0

    return-void
.end method

.method protected recordPageByDefault()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method protected useImageLoader()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method
