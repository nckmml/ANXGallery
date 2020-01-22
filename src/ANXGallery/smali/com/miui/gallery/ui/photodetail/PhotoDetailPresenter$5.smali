.class Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;
.super Lio/reactivex/subscribers/DisposableSubscriber;
.source "PhotoDetailPresenter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->updatePhotoDateTime(J)V
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
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

.field final synthetic val$releaseDateTime:J

.field final synthetic val$requestBean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;JLcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    iput-wide p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->val$releaseDateTime:J

    iput-object p4, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->val$requestBean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

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

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->showUpdateDateTimeResult(Ljava/lang/String;)V

    return-void
.end method

.method public onNext(Ljava/lang/Boolean;)V
    .locals 2

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    iget-wide v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->val$releaseDateTime:J

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->access$302(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;J)J

    new-instance p1, Ljava/io/File;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-static {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->access$100(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;)Lcom/miui/gallery/model/BaseDataItem;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->val$requestBean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->getNewTime()J

    move-result-wide v0

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/FileUtils;->generateFileDateTimeName(Ljava/io/File;J)Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->showUpdateDateTimeResult(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method public bridge synthetic onNext(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->onNext(Ljava/lang/Boolean;)V

    return-void
.end method

.method protected onStart()V
    .locals 1

    invoke-super {p0}, Lio/reactivex/subscribers/DisposableSubscriber;->onStart()V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$5;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->showLoadingDialog()V

    return-void
.end method
