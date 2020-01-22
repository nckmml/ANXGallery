.class Lcom/miui/gallery/activity/HomePageStartupHelper$1;
.super Ljava/lang/Object;
.source "HomePageStartupHelper.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/HomePageStartupHelper;-><init>(Landroid/content/Context;Lcom/miui/gallery/activity/HomePageStartupHelper$Attacher;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/HomePageStartupHelper;Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$1;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    iput-object p2, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$1;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$1;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    new-instance v1, Lcom/miui/gallery/activity/HomePageStartupHelper$1$1;

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$1;->val$context:Landroid/content/Context;

    invoke-static {}, Lcom/miui/gallery/Config$ThumbConfig;->get()Lcom/miui/gallery/Config$ThumbConfig;

    move-result-object v3

    iget v3, v3, Lcom/miui/gallery/Config$ThumbConfig;->sPredictItemsOneScreen:I

    const/4 v4, 0x0

    invoke-direct {v1, p0, v2, v4, v3}, Lcom/miui/gallery/activity/HomePageStartupHelper$1$1;-><init>(Lcom/miui/gallery/activity/HomePageStartupHelper$1;Landroid/content/Context;Landroid/view/ViewGroup;I)V

    invoke-static {v0, v1}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$002(Lcom/miui/gallery/activity/HomePageStartupHelper;Lcom/miui/gallery/ui/AsyncViewPrefetch;)Lcom/miui/gallery/ui/AsyncViewPrefetch;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AsyncViewPrefetch;->prefetch()V

    return-void
.end method
