.class public Lcom/miui/gallery/assistant/recommend/RecommendListLoader;
.super Landroid/content/AsyncTaskLoader;
.source "RecommendListLoader.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/content/AsyncTaskLoader<",
        "Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;",
        ">;"
    }
.end annotation


# instance fields
.field private mContext:Landroid/content/Context;

.field private mFeatureStrategyObserver:Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    invoke-direct {p0, p1}, Landroid/content/AsyncTaskLoader;-><init>(Landroid/content/Context;)V

    new-instance v0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/assistant/recommend/RecommendListLoader$1;-><init>(Lcom/miui/gallery/assistant/recommend/RecommendListLoader;)V

    iput-object v0, p0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->mFeatureStrategyObserver:Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;

    iput-object p1, p0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->mContext:Landroid/content/Context;

    return-void
.end method


# virtual methods
.method public loadInBackground()Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;
    .locals 3

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getRecommendStrategy()Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;

    move-result-object v0

    if-nez v0, :cond_0

    new-instance v1, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;

    iget-object v2, p0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->mContext:Landroid/content/Context;

    invoke-direct {v1, v2}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;-><init>(Landroid/content/Context;)V

    invoke-virtual {v1}, Lcom/miui/gallery/cloudcontrol/CloudControlRequestHelper;->execRecommendRequest()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlStrategyHelper;->getRecommendStrategy()Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;

    move-result-object v0

    :cond_0
    return-object v0
.end method

.method public bridge synthetic loadInBackground()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->loadInBackground()Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;

    move-result-object v0

    return-object v0
.end method

.method protected onStartLoading()V
    .locals 5

    invoke-super {p0}, Landroid/content/AsyncTaskLoader;->onStartLoading()V

    invoke-virtual {p0}, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->forceLoad()V

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->getInstance()Lcom/miui/gallery/cloudcontrol/CloudControlManager;

    move-result-object v0

    const-class v1, Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;

    iget-object v2, p0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->mFeatureStrategyObserver:Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;

    const-string v3, "recommendation"

    const/4 v4, 0x0

    invoke-virtual {v0, v3, v1, v4, v2}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->registerStrategyObserver(Ljava/lang/String;Ljava/lang/Class;Lcom/miui/gallery/cloudcontrol/Merger;Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;)Lcom/miui/gallery/cloudcontrol/strategies/BaseStrategy;

    return-void
.end method

.method protected onStopLoading()V
    .locals 2

    invoke-super {p0}, Landroid/content/AsyncTaskLoader;->onStopLoading()V

    invoke-static {}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->getInstance()Lcom/miui/gallery/cloudcontrol/CloudControlManager;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->mFeatureStrategyObserver:Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/cloudcontrol/CloudControlManager;->unregisterStrategyObserver(Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;)V

    return-void
.end method
