.class Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$3;
.super Ljava/lang/Object;
.source "PhotoDetailPresenter.java"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->parseAddress(Landroid/location/Address;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lio/reactivex/functions/Consumer<",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$3;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic accept(Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$3;->accept(Ljava/lang/String;)V

    return-void
.end method

.method public accept(Ljava/lang/String;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter$3;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;->getView()Lcom/miui/gallery/base_optimization/mvp/view/IView;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->bindLocation(Ljava/lang/String;)V

    return-void
.end method
