.class Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;
.super Ljava/lang/Object;
.source "SearchImageResultFragmentBase.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->onLoadComplete()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;


# direct methods
.method constructor <init>(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    invoke-virtual {v0}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->moreThanOnePage()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    invoke-static {v0}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->access$000(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;)Landroid/view/View;

    move-result-object v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    invoke-static {v0}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->access$100(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;)Lcom/miui/gallery/activity/BaseActivity;

    move-result-object v1

    invoke-static {v1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v1

    const v2, 0x7f0b0185

    iget-object v3, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    iget-object v3, v3, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->mGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    const/4 v4, 0x0

    invoke-virtual {v1, v2, v3, v4}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->access$002(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;Landroid/view/View;)Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    iget-object v0, v0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->mGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    iget-object v1, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    invoke-static {v1}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->access$000(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->addFooterView(Landroid/view/View;)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    iget-object v0, v0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->mStatusHandleHelper:Lcom/miui/gallery/search/StatusHandleHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/search/StatusHandleHelper;->refreshInfoViews()V

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    invoke-static {v0}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->access$000(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;)Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    iget-object v0, v0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->mGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    iget-object v1, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    invoke-static {v1}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->access$000(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->removeFooterView(Landroid/view/View;)Z

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase$1;->this$0:Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;->access$002(Lcom/miui/gallery/search/resultpage/SearchImageResultFragmentBase;Landroid/view/View;)Landroid/view/View;

    :cond_2
    :goto_0
    return-void
.end method
