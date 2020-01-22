.class Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$1;
.super Ljava/lang/Object;
.source "EditPhotoDateTimeAndRecord.java"

# interfaces
.implements Lio/reactivex/functions/Function;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;->buildUseCaseFlowable(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Lio/reactivex/Flowable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lio/reactivex/functions/Function<",
        "Ljava/lang/String;",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;

.field final synthetic val$item:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$1;->this$0:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$1;->val$item:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public apply(Ljava/lang/String;)Ljava/lang/Boolean;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$1;->this$0:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$1;->val$item:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;

    invoke-static {v0, p1, v1}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;->access$000(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;Ljava/lang/String;Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTime$RequestBean;)Ljava/util/Map;

    move-result-object p1

    const-string v0, "photo_detail"

    const-string v1, "set_photo_datetime"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    const/4 p1, 0x1

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method

.method public bridge synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$1;->apply(Ljava/lang/String;)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method
