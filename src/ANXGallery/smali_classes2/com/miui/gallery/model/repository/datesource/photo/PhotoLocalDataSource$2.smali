.class Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;
.super Ljava/lang/Object;
.source "PhotoLocalDataSource.java"

# interfaces
.implements Ljava/util/concurrent/Callable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;->editPhotoDateTimeDetailInfo(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/concurrent/Callable<",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;

.field final synthetic val$bean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->this$0:Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;

    iput-object p2, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->val$bean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public call()Ljava/lang/Boolean;
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->val$bean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->getId()J

    move-result-wide v1

    iget-object v3, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->val$bean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-virtual {v3}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->getNewTime()J

    move-result-wide v3

    iget-object v5, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->val$bean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-virtual {v5}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->getPath()Ljava/lang/String;

    move-result-object v5

    iget-object v6, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->val$bean:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-virtual {v6}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;->isFavorite()Z

    move-result v6

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/provider/CloudUtils;->editPhotoTimeInfoBy(Landroid/content/Context;JJLjava/lang/String;Z)[J

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->this$0:Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;

    invoke-static {v1, v0}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;->access$000(Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;[J)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic call()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$2;->call()Ljava/lang/Boolean;

    move-result-object v0

    return-object v0
.end method
