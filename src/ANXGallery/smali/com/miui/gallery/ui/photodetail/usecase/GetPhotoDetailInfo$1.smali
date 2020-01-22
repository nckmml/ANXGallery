.class Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo$1;
.super Ljava/lang/Object;
.source "GetPhotoDetailInfo.java"

# interfaces
.implements Lio/reactivex/functions/Function;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;->buildUseCaseFlowable(Lcom/miui/gallery/model/BaseDataItem;)Lio/reactivex/Flowable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lio/reactivex/functions/Function<",
        "Lcom/miui/gallery/model/PhotoDetailInfo;",
        "Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo$1;->this$0:Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public apply(Lcom/miui/gallery/model/PhotoDetailInfo;)Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    invoke-direct {v0}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;-><init>()V

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->mapping(Lcom/miui/gallery/model/PhotoDetailInfo;)V

    return-object v0
.end method

.method public bridge synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p1, Lcom/miui/gallery/model/PhotoDetailInfo;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/usecase/GetPhotoDetailInfo$1;->apply(Lcom/miui/gallery/model/PhotoDetailInfo;)Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;

    move-result-object p1

    return-object p1
.end method
