.class Lcom/miui/gallery/ui/FacePageFragment$6;
.super Ljava/lang/Object;
.source "FacePageFragment.java"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/FacePageFragment;->seeIfHasRecommendFace()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/threadpool/ThreadPool$Job<",
        "Ljava/lang/Void;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/FacePageFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/FacePageFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/FacePageFragment$6;->this$0:Lcom/miui/gallery/ui/FacePageFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/FacePageFragment$6;->run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;

    move-result-object p1

    return-object p1
.end method

.method public run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/ui/FacePageFragment$6;->this$0:Lcom/miui/gallery/ui/FacePageFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/FacePageFragment;->access$900(Lcom/miui/gallery/ui/FacePageFragment;)Z

    move-result p1

    const/4 v0, 0x0

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/FacePageFragment$6;->this$0:Lcom/miui/gallery/ui/FacePageFragment;

    new-instance v1, Lcom/miui/gallery/util/face/CheckoutRecommendPeople;

    invoke-direct {v1, v0, p1}, Lcom/miui/gallery/util/face/CheckoutRecommendPeople;-><init>(Landroid/os/Handler;Lcom/miui/gallery/util/face/CheckoutRecommendPeople$CheckoutStatusListener;)V

    invoke-static {p1, v1}, Lcom/miui/gallery/ui/FacePageFragment;->access$1002(Lcom/miui/gallery/ui/FacePageFragment;Lcom/miui/gallery/util/face/CheckoutRecommendPeople;)Lcom/miui/gallery/util/face/CheckoutRecommendPeople;

    iget-object p1, p0, Lcom/miui/gallery/ui/FacePageFragment$6;->this$0:Lcom/miui/gallery/ui/FacePageFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/FacePageFragment;->access$1000(Lcom/miui/gallery/ui/FacePageFragment;)Lcom/miui/gallery/util/face/CheckoutRecommendPeople;

    move-result-object p1

    iget-object v1, p0, Lcom/miui/gallery/ui/FacePageFragment$6;->this$0:Lcom/miui/gallery/ui/FacePageFragment;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/FacePageFragment;->getServerIdOfAlbum()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Lcom/miui/gallery/util/face/CheckoutRecommendPeople;->getRecommendPeopleFromNet(Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/FacePageFragment$6;->this$0:Lcom/miui/gallery/ui/FacePageFragment;

    const/4 v1, 0x1

    invoke-static {p1, v1}, Lcom/miui/gallery/ui/FacePageFragment;->access$902(Lcom/miui/gallery/ui/FacePageFragment;Z)Z

    :cond_0
    return-object v0
.end method
