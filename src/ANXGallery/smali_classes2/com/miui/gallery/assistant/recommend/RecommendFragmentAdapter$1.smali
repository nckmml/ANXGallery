.class Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter$1;
.super Lcom/miui/gallery/listener/SingleClickListener;
.source "RecommendFragmentAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;->onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;

.field final synthetic val$recommendItem:Lcom/miui/gallery/cloudcontrol/RecommendItem;


# direct methods
.method constructor <init>(Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;Lcom/miui/gallery/cloudcontrol/RecommendItem;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter$1;->this$0:Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;

    iput-object p2, p0, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter$1;->val$recommendItem:Lcom/miui/gallery/cloudcontrol/RecommendItem;

    invoke-direct {p0}, Lcom/miui/gallery/listener/SingleClickListener;-><init>()V

    return-void
.end method


# virtual methods
.method protected onSingleClick(Landroid/view/View;)V
    .locals 2

    new-instance p1, Ljava/util/HashMap;

    invoke-direct {p1}, Ljava/util/HashMap;-><init>()V

    iget-object v0, p0, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter$1;->val$recommendItem:Lcom/miui/gallery/cloudcontrol/RecommendItem;

    invoke-virtual {v0}, Lcom/miui/gallery/cloudcontrol/RecommendItem;->getName()Ljava/lang/String;

    move-result-object v0

    const-string v1, "item"

    invoke-virtual {p1, v1, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v0, "assistant_discover"

    const-string v1, "discover_item_clicked"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    new-instance p1, Landroid/content/Intent;

    const-string v0, "com.miui.gallery.action.VIEW_WEB_RECOMMEND"

    invoke-direct {p1, v0}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter$1;->this$0:Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;

    iget-object v1, p0, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter$1;->val$recommendItem:Lcom/miui/gallery/cloudcontrol/RecommendItem;

    invoke-virtual {v1}, Lcom/miui/gallery/cloudcontrol/RecommendItem;->getLandingUrl()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;->access$000(Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "url"

    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    const-string v0, "from_recommend"

    const/4 v1, 0x1

    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    iget-object v0, p0, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter$1;->this$0:Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;

    invoke-static {v0}, Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;->access$100(Lcom/miui/gallery/assistant/recommend/RecommendFragmentAdapter;)Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V

    return-void
.end method
