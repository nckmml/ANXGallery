.class public abstract Lcom/miui/gallery/base_optimization/mvp/view/Fragment;
.super Lcom/miui/gallery/app/Fragment;
.source "Fragment.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/mvp/view/IView;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/app/Fragment;",
        "Lcom/miui/gallery/base_optimization/mvp/view/IView<",
        "TP;>;"
    }
.end annotation


# instance fields
.field private mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate<",
            "TP;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/Fragment;-><init>()V

    return-void
.end method


# virtual methods
.method public findViewById(I)Landroid/view/View;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<V:",
            "Landroid/view/View;",
            ">(I)TV;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->findViewById(I)Landroid/view/View;

    move-result-object p1

    return-object p1
.end method

.method public finish()V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->onBackPressed()V

    return-void
.end method

.method public abstract getLayoutId()I
.end method

.method public getPageName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->getPageName()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TP;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    return-object v0
.end method

.method public getSafeActivity()Landroid/app/Activity;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->getSafeActivity()Landroid/app/Activity;

    move-result-object v0

    return-object v0
.end method

.method protected getThemeRes()I
    .locals 1

    const v0, 0x103022f

    return v0
.end method

.method public abstract initView(Landroid/view/View;Landroid/os/Bundle;Landroid/view/View;)V
.end method

.method public onAttach(Landroid/app/Activity;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Fragment;->onAttach(Landroid/app/Activity;)V

    new-instance v0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-direct {v0, p0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;-><init>(Lcom/miui/gallery/base_optimization/mvp/view/Fragment;)V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->onAttach(Landroid/app/Activity;)V

    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Fragment;->onCreate(Landroid/os/Bundle;)V

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->onCreate(Landroid/os/Bundle;)V

    return-void
.end method

.method public onDestroy()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->onDetach()V

    invoke-super {p0}, Lcom/miui/gallery/app/Fragment;->onDestroy()V

    return-void
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0, p1, p2, p3}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object p1

    return-object p1
.end method

.method public onPause()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Fragment;->onPause()V

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->onPause()V

    return-void
.end method

.method public onResume()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Fragment;->onResume()V

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->onResume()V

    return-void
.end method

.method protected onUserFirstVisible()V
    .locals 0

    return-void
.end method

.method public setUserVisibleHint(Z)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Fragment;->setUserVisibleHint(Z)V

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-direct {v0, p0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;-><init>(Lcom/miui/gallery/base_optimization/mvp/view/Fragment;)V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->mFragmentDelegate:Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->setUserVisibleHint(Z)V

    return-void
.end method
