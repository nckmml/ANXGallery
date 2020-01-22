.class public Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;
.super Ljava/lang/Object;
.source "FragmentDelegate.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/mvp/view/IView;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/base_optimization/mvp/view/IView<",
        "TP;>;"
    }
.end annotation


# instance fields
.field private mActivity:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Landroid/app/Activity;",
            ">;"
        }
    .end annotation
.end field

.field private mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/base_optimization/mvp/view/Fragment<",
            "TP;>;"
        }
    .end annotation
.end field

.field private mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TP;"
        }
    .end annotation
.end field

.field private mRootView:Landroid/view/View;

.field protected mUserFirstVisible:Z

.field private mViews:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/miui/gallery/base_optimization/mvp/view/Fragment;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/base_optimization/mvp/view/Fragment<",
            "TP;>;)V"
        }
    .end annotation

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mUserFirstVisible:Z

    iput-object p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    return-void
.end method

.method private final findPresenterFromClass(I)Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)TP;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-static {v0, p1}, Lcom/miui/gallery/base_optimization/util/GenericUtils;->getClassT(Ljava/lang/Object;I)Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_0

    instance-of v0, p1, Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    return-object p1

    :cond_0
    const/4 p1, 0x0

    return-object p1
.end method


# virtual methods
.method public findViewById(I)Landroid/view/View;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<V:",
            "Landroid/view/View;",
            ">(I)TV;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mRootView:Landroid/view/View;

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mViews:Landroid/util/SparseArray;

    if-nez v0, :cond_1

    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mViews:Landroid/util/SparseArray;

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mViews:Landroid/util/SparseArray;

    invoke-virtual {v0, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mViews:Landroid/util/SparseArray;

    invoke-virtual {v0, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/view/View;

    return-object p1

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mRootView:Landroid/view/View;

    invoke-virtual {v0, p1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mViews:Landroid/util/SparseArray;

    invoke-virtual {v1, p1, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    return-object v0
.end method

.method public getPageName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    const-string v0, ""

    return-object v0
.end method

.method public getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TP;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    return-object v0
.end method

.method public getSafeActivity()Landroid/app/Activity;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mActivity:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/Activity;

    return-object v0

    :cond_1
    const/4 v0, 0x0

    return-object v0
.end method

.method protected initPresenter()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    if-nez v0, :cond_1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->findPresenterFromClass(I)Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    if-nez v0, :cond_0

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->findPresenterFromClass(I)Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    if-eqz v0, :cond_1

    iget-object v1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-interface {v0, v1}, Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;->onAttachView(Lcom/miui/gallery/base_optimization/mvp/view/IView;)V

    :cond_1
    return-void
.end method

.method public onAttach(Landroid/app/Activity;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mActivity:Ljava/lang/ref/WeakReference;

    if-nez v0, :cond_0

    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mActivity:Ljava/lang/ref/WeakReference;

    :cond_0
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->getThemeRes()I

    move-result p1

    if-eqz p1, :cond_0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->setThemeRes(I)V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->initPresenter()V

    return-void
.end method

.method public onDetach()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mActivity:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->clear()V

    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mActivity:Ljava/lang/ref/WeakReference;

    iget-object v1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mPresenter:Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    if-eqz v1, :cond_1

    invoke-interface {v1}, Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;->onDetachView()V

    :cond_1
    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mRootView:Landroid/view/View;

    return-void
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->getLayoutId()I

    move-result v0

    const/4 v1, -0x1

    if-eq v1, v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mRootView:Landroid/view/View;

    if-eqz v0, :cond_0

    check-cast v0, Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->removeAllViews()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-virtual {v0}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->getLayoutId()I

    move-result v0

    const/4 v1, 0x0

    invoke-virtual {p1, v0, p2, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mRootView:Landroid/view/View;

    iget-object p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mRootView:Landroid/view/View;

    invoke-virtual {p1, v0, p3, p2}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->initView(Landroid/view/View;Landroid/os/Bundle;Landroid/view/View;)V

    iget-object p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mRootView:Landroid/view/View;

    return-object p1

    :cond_1
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "layoutId can\'t null"

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public onPause()V
    .locals 0

    return-void
.end method

.method public onResume()V
    .locals 0

    return-void
.end method

.method public setThemeRes(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->setThemeRes(I)V

    return-void
.end method

.method public setUserVisibleHint(Z)V
    .locals 0

    if-eqz p1, :cond_0

    iget-boolean p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mUserFirstVisible:Z

    if-nez p1, :cond_0

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mUserFirstVisible:Z

    iget-object p1, p0, Lcom/miui/gallery/base_optimization/mvp/view/FragmentDelegate;->mFragment:Lcom/miui/gallery/base_optimization/mvp/view/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/base_optimization/mvp/view/Fragment;->onUserFirstVisible()V

    :cond_0
    return-void
.end method
