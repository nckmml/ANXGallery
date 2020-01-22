.class Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;
.super Lio/reactivex/subscribers/DisposableSubscriber;
.source "CloudAlbumListPresenter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->doChangeAlbumUploadStatus(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lio/reactivex/subscribers/DisposableSubscriber<",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;

.field final synthetic val$viewBean:Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;

    iput-object p2, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->val$viewBean:Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    invoke-direct {p0}, Lio/reactivex/subscribers/DisposableSubscriber;-><init>()V

    return-void
.end method


# virtual methods
.method public onComplete()V
    .locals 0

    return-void
.end method

.method public onError(Ljava/lang/Throwable;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->val$viewBean:Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;->showChangeAlbumUploadStatusFailed(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    return-void
.end method

.method public onNext(Ljava/lang/Boolean;)V
    .locals 1

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->val$viewBean:Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->isBackup()Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;->setBackup(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->val$viewBean:Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;->showChangeAlbumUploadStatusSuccess(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;

    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->val$viewBean:Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListContract$V;->showChangeAlbumUploadStatusFailed(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    :goto_0
    return-void
.end method

.method public bridge synthetic onNext(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListPresenter$3;->onNext(Ljava/lang/Boolean;)V

    return-void
.end method
