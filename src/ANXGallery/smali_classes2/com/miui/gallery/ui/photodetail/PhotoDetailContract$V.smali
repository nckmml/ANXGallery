.class public abstract Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;
.super Lcom/miui/gallery/app/base/BaseFragment;
.source "PhotoDetailContract.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/photodetail/PhotoDetailContract;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "V"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/app/base/BaseFragment<",
        "Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;",
        ">;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseFragment;-><init>()V

    return-void
.end method


# virtual methods
.method abstract bindLocation(Ljava/lang/String;)V
.end method

.method public abstract showDetailInfo(ZLcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
.end method

.method public abstract showDownLoadFailed()V
.end method

.method public abstract showDownLoadProgressDialog(Ljava/util/ArrayList;Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/util/BulkDownloadHelper$BulkDownloadItem;",
            ">;",
            "Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;",
            ")V"
        }
    .end annotation
.end method

.method public abstract showDownLoadSuccess(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
.end method

.method public abstract showLoadingDialog()V
.end method

.method abstract showUpdateDateTimeResult(Ljava/lang/String;)V
.end method
