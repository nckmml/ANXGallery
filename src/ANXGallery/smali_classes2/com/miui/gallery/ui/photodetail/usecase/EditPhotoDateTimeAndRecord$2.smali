.class Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$2;
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
        "Ljava/lang/Boolean;",
        "Lorg/reactivestreams/Publisher<",
        "Ljava/lang/String;",
        ">;>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$2;->this$0:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$2;->apply(Ljava/lang/Boolean;)Lorg/reactivestreams/Publisher;

    move-result-object p1

    return-object p1
.end method

.method public apply(Ljava/lang/Boolean;)Lorg/reactivestreams/Publisher;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Boolean;",
            ")",
            "Lorg/reactivestreams/Publisher<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$2;->this$0:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;

    iget-object p1, p1, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;->mRepository:Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord$2;->this$0:Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;

    iget-wide v0, v0, Lcom/miui/gallery/ui/photodetail/usecase/EditPhotoDateTimeAndRecord;->mAlbumId:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    invoke-interface {p1, v0}, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;->queryAlbumNameByAlbumId(Ljava/lang/Long;)Lio/reactivex/Flowable;

    move-result-object p1

    return-object p1

    :cond_0
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "edit photo datetime failed"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1
.end method
