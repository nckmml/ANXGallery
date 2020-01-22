.class Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;
.super Lio/reactivex/subscribers/DisposableSubscriber;
.source "HiddenAlbumPresenter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->unAlbumHide(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V
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
.field final synthetic this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;

.field final synthetic val$itemData:Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;

    iput-object p2, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;->val$itemData:Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;

    invoke-direct {p0}, Lio/reactivex/subscribers/DisposableSubscriber;-><init>()V

    return-void
.end method


# virtual methods
.method public onComplete()V
    .locals 0

    return-void
.end method

.method public onError(Ljava/lang/Throwable;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;->showUnHiddenAlbumIsFaild()V

    return-void
.end method

.method public onNext(Ljava/lang/Boolean;)V
    .locals 1

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;

    iget-object v0, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;->val$itemData:Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;->showUnHiddenAlbumIsSuccess(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V

    :cond_0
    return-void
.end method

.method public bridge synthetic onNext(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumPresenter$2;->onNext(Ljava/lang/Boolean;)V

    return-void
.end method
