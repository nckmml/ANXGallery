.class public abstract Lcom/miui/gallery/base_optimization/mvp/view/Activity;
.super Lcom/miui/gallery/app/Activity;
.source "Activity.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;
.implements Lcom/miui/gallery/base_optimization/mvp/view/IView;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/app/Activity;",
        "Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;",
        "Lcom/miui/gallery/base_optimization/mvp/view/IView<",
        "TP;>;"
    }
.end annotation


# instance fields
.field private mActivityDelegate:Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate<",
            "TP;>;"
        }
    .end annotation
.end field

.field private mFragmentSupport:Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/Activity;-><init>()V

    return-void
.end method

.method private initFragmentSupport(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mFragmentSupport:Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;

    invoke-direct {v0, p1, p0}, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;-><init>(ILandroid/app/Activity;)V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mFragmentSupport:Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;

    :cond_0
    return-void
.end method


# virtual methods
.method public abstract getLayoutId()I
.end method

.method public loadRootFragment(ILandroid/app/Fragment;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mFragmentSupport:Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;

    if-nez v0, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->initFragmentSupport(I)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mFragmentSupport:Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;

    invoke-interface {v0, p1, p2}, Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;->loadRootFragment(ILandroid/app/Fragment;)V

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Activity;->onCreate(Landroid/os/Bundle;)V

    new-instance v0, Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate;

    invoke-direct {v0, p0}, Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate;-><init>(Lcom/miui/gallery/base_optimization/mvp/view/Activity;)V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mActivityDelegate:Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate;

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mActivityDelegate:Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate;->onCreate(Landroid/os/Bundle;)V

    return-void
.end method

.method protected onDestroy()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onDestroy()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mActivityDelegate:Lcom/miui/gallery/base_optimization/mvp/view/ActivityDelegate;

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/Activity;->mFragmentSupport:Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;

    return-void
.end method
