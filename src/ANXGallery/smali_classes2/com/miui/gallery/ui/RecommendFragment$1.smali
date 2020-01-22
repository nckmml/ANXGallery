.class Lcom/miui/gallery/ui/RecommendFragment$1;
.super Ljava/lang/Object;
.source "RecommendFragment.java"

# interfaces
.implements Landroid/app/LoaderManager$LoaderCallbacks;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/RecommendFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/app/LoaderManager$LoaderCallbacks<",
        "Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/RecommendFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/RecommendFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreateLoader(ILandroid/os/Bundle;)Landroid/content/Loader;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Landroid/os/Bundle;",
            ")",
            "Landroid/content/Loader<",
            "Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;",
            ">;"
        }
    .end annotation

    new-instance p1, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;

    iget-object p2, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    iget-object p2, p2, Lcom/miui/gallery/ui/RecommendFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-direct {p1, p2}, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;-><init>(Landroid/content/Context;)V

    return-object p1
.end method

.method public onLoadFinished(Landroid/content/Loader;Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Loader<",
            "Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;",
            ">;",
            "Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;",
            ")V"
        }
    .end annotation

    const/4 p1, 0x0

    if-eqz p2, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/RecommendFragment;->access$000(Lcom/miui/gallery/ui/RecommendFragment;)Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;

    move-result-object v0

    invoke-virtual {p2}, Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;->getRecommendItems()Ljava/util/ArrayList;

    move-result-object p2

    invoke-virtual {p2}, Ljava/util/ArrayList;->clone()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/ArrayList;

    invoke-virtual {v0, p2}, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;->changeRecommendItems(Ljava/util/ArrayList;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-static {p2}, Lcom/miui/gallery/ui/RecommendFragment;->access$100(Lcom/miui/gallery/ui/RecommendFragment;)Landroid/view/View;

    move-result-object p2

    const/16 v0, 0x8

    invoke-virtual {p2, v0}, Landroid/view/View;->setVisibility(I)V

    iget-object p2, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-static {p2}, Lcom/miui/gallery/ui/RecommendFragment;->access$200(Lcom/miui/gallery/ui/RecommendFragment;)Lcom/miui/gallery/widget/recyclerview/GalleryRecyclerView;

    move-result-object p2

    invoke-virtual {p2, p1}, Lcom/miui/gallery/widget/recyclerview/GalleryRecyclerView;->setVisibility(I)V

    const-string p2, "recommendation"

    invoke-static {p2}, Lcom/miui/gallery/reddot/DisplayStatusManager;->setRedDotClicked(Ljava/lang/String;)V

    new-instance p2, Lcom/miui/gallery/ui/RecommendFragment$RequeryTask;

    iget-object v0, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/RecommendFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-direct {p2, v0}, Lcom/miui/gallery/ui/RecommendFragment$RequeryTask;-><init>(Landroid/app/Activity;)V

    new-array p1, p1, [Ljava/lang/Void;

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/RecommendFragment$RequeryTask;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    goto :goto_0

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-static {p2}, Lcom/miui/gallery/ui/RecommendFragment;->access$100(Lcom/miui/gallery/ui/RecommendFragment;)Landroid/view/View;

    move-result-object p2

    invoke-virtual {p2, p1}, Landroid/view/View;->setVisibility(I)V

    iget-object p2, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-static {p2}, Lcom/miui/gallery/ui/RecommendFragment;->access$100(Lcom/miui/gallery/ui/RecommendFragment;)Landroid/view/View;

    move-result-object p2

    const v0, 0x7f09019d

    invoke-virtual {p2, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    invoke-virtual {p2, p1}, Landroid/view/View;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/RecommendFragment;->access$100(Lcom/miui/gallery/ui/RecommendFragment;)Landroid/view/View;

    move-result-object p1

    const p2, 0x7f090340

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    const p2, 0x7f10009a

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(I)V

    :goto_0
    return-void
.end method

.method public bridge synthetic onLoadFinished(Landroid/content/Loader;Ljava/lang/Object;)V
    .locals 0

    check-cast p2, Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/ui/RecommendFragment$1;->onLoadFinished(Landroid/content/Loader;Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;)V

    return-void
.end method

.method public onLoaderReset(Landroid/content/Loader;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Loader<",
            "Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;",
            ">;)V"
        }
    .end annotation

    iget-object p1, p0, Lcom/miui/gallery/ui/RecommendFragment$1;->this$0:Lcom/miui/gallery/ui/RecommendFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/RecommendFragment;->access$000(Lcom/miui/gallery/ui/RecommendFragment;)Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;

    move-result-object p1

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;->changeRecommendItems(Ljava/util/ArrayList;)V

    return-void
.end method
