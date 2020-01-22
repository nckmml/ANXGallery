.class public Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;
.super Ljava/lang/Object;
.source "BasePresenter.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<V::",
        "Lcom/miui/gallery/base_optimization/mvp/view/IView;",
        ">",
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter<",
        "TV;>;"
    }
.end annotation


# instance fields
.field private mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

.field private mView:Lcom/miui/gallery/base_optimization/mvp/view/IView;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TV;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public addDispose(Lio/reactivex/disposables/Disposable;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

    if-nez v0, :cond_0

    new-instance v0, Lio/reactivex/disposables/CompositeDisposable;

    invoke-direct {v0}, Lio/reactivex/disposables/CompositeDisposable;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

    invoke-virtual {v0, p1}, Lio/reactivex/disposables/CompositeDisposable;->add(Lio/reactivex/disposables/Disposable;)Z

    return-void
.end method

.method public getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TV;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->mView:Lcom/miui/gallery/base_optimization/mvp/view/IView;

    return-object v0
.end method

.method public onAttachView(Lcom/miui/gallery/base_optimization/mvp/view/IView;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TV;)V"
        }
    .end annotation

    if-eqz p1, :cond_0

    iput-object p1, p0, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->mView:Lcom/miui/gallery/base_optimization/mvp/view/IView;

    return-void

    :cond_0
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string v0, "view can\'t null"

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method protected onDestory()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lio/reactivex/disposables/CompositeDisposable;->dispose()V

    :cond_0
    return-void
.end method

.method public onDetachView()V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->onDestory()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/mvp/presenter/BasePresenter;->mView:Lcom/miui/gallery/base_optimization/mvp/view/IView;

    return-void
.end method
