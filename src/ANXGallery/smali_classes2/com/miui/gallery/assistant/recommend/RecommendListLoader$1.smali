.class Lcom/miui/gallery/assistant/recommend/RecommendListLoader$1;
.super Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;
.source "RecommendListLoader.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/assistant/recommend/RecommendListLoader;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver<",
        "Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/assistant/recommend/RecommendListLoader;


# direct methods
.method constructor <init>(Lcom/miui/gallery/assistant/recommend/RecommendListLoader;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader$1;->this$0:Lcom/miui/gallery/assistant/recommend/RecommendListLoader;

    invoke-direct {p0}, Lcom/miui/gallery/cloudcontrol/observers/FeatureStrategyObserver;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic onStrategyChanged(Ljava/lang/String;Lcom/miui/gallery/cloudcontrol/strategies/BaseStrategy;)V
    .locals 0

    check-cast p2, Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/assistant/recommend/RecommendListLoader$1;->onStrategyChanged(Ljava/lang/String;Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;)V

    return-void
.end method

.method public onStrategyChanged(Ljava/lang/String;Lcom/miui/gallery/cloudcontrol/strategies/RecommendStrategy;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/assistant/recommend/RecommendListLoader$1;->this$0:Lcom/miui/gallery/assistant/recommend/RecommendListLoader;

    invoke-virtual {p1}, Lcom/miui/gallery/assistant/recommend/RecommendListLoader;->onContentChanged()V

    return-void
.end method
