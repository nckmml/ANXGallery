.class Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;
.super Ljava/lang/Object;
.source "PhotoDetailFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 5
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "ResourceType"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->isCanClick()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    const v0, 0x7f0901f4

    if-eq p1, v0, :cond_6

    const v0, 0x7f090257

    if-eq p1, v0, :cond_5

    const v0, 0x7f09035b

    if-eq p1, v0, :cond_1

    goto/16 :goto_1

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$000(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Z

    move-result p1

    if-nez p1, :cond_2

    goto/16 :goto_1

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->isThumbFile()Z

    move-result p1

    if-nez p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$100(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Z

    move-result p1

    if-eqz p1, :cond_3

    goto :goto_0

    :cond_3
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$300(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    goto :goto_1

    :cond_4
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$200(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    goto :goto_1

    :cond_5
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->isHaveLocalPath()Z

    move-result p1

    if-eqz p1, :cond_8

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->getFileLocalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/FileUtils;->getParentFolderPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/util/IntentUtil;->jumpToExplore(Landroid/content/Context;Ljava/lang/String;)V

    goto :goto_1

    :cond_6
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->isHaveLocation()Z

    move-result p1

    if-eqz p1, :cond_8

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->getLocation()[D

    move-result-object p1

    if-eqz p1, :cond_7

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v0

    const/4 v1, 0x0

    aget-wide v1, p1, v1

    const/4 v3, 0x1

    aget-wide v3, p1, v3

    invoke-static {v0, v1, v2, v3, v4}, Lcom/miui/gallery/util/IntentUtil;->showOnMap(Landroid/content/Context;DD)Z

    move-result p1

    if-nez p1, :cond_7

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object p1

    const v0, 0x7f1005e1

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    :cond_7
    const-string p1, "photo_detail"

    const-string v0, "show_on_map"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    :cond_8
    :goto_1
    return-void
.end method
