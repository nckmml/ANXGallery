.class Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;
.super Lio/reactivex/subscribers/DisposableSubscriber;
.source "PhotoDetailPresenter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->requestLocation()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lio/reactivex/subscribers/DisposableSubscriber<",
        "Landroid/location/Address;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

.field final synthetic val$location:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->val$location:Ljava/lang/String;

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

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p1

    const-string v0, "PhotoDetailPresenter"

    invoke-static {v0, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->val$location:Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->bindLocation(Ljava/lang/String;)V

    return-void
.end method

.method public onNext(Landroid/location/Address;)V
    .locals 1

    invoke-static {p1}, Lcom/miui/gallery/data/LocationUtil;->isInvalidAddress(Landroid/location/Address;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-static {v0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->access$200(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;Landroid/location/Address;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->val$location:Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->bindLocation(Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method public bridge synthetic onNext(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Landroid/location/Address;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$2;->onNext(Landroid/location/Address;)V

    return-void
.end method
