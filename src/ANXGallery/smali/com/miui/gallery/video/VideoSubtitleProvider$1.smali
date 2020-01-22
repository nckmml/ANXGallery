.class Lcom/miui/gallery/video/VideoSubtitleProvider$1;
.super Lcom/miui/gallery/threadpool/FutureHandler;
.source "VideoSubtitleProvider.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/video/VideoSubtitleProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/threadpool/FutureHandler<",
        "Ljava/util/List<",
        "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
        ">;>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/VideoSubtitleProvider;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/VideoSubtitleProvider;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$1;->this$0:Lcom/miui/gallery/video/VideoSubtitleProvider;

    invoke-direct {p0}, Lcom/miui/gallery/threadpool/FutureHandler;-><init>()V

    return-void
.end method


# virtual methods
.method public onPostExecute(Lcom/miui/gallery/threadpool/Future;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/threadpool/Future<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
            ">;>;)V"
        }
    .end annotation

    invoke-interface {p1}, Lcom/miui/gallery/threadpool/Future;->isCancelled()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    invoke-interface {p1}, Lcom/miui/gallery/threadpool/Future;->get()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/List;

    if-nez p1, :cond_1

    return-void

    :cond_1
    const-string v0, "VideoSubtitleProvider"

    const-string v1, "read subtitle success."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$1;->this$0:Lcom/miui/gallery/video/VideoSubtitleProvider;

    invoke-static {v0}, Lcom/miui/gallery/video/VideoSubtitleProvider;->access$000(Lcom/miui/gallery/video/VideoSubtitleProvider;)Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;

    move-result-object v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$1;->this$0:Lcom/miui/gallery/video/VideoSubtitleProvider;

    invoke-static {v0}, Lcom/miui/gallery/video/VideoSubtitleProvider;->access$000(Lcom/miui/gallery/video/VideoSubtitleProvider;)Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$1;->this$0:Lcom/miui/gallery/video/VideoSubtitleProvider;

    invoke-static {v1}, Lcom/miui/gallery/video/VideoSubtitleProvider;->access$100(Lcom/miui/gallery/video/VideoSubtitleProvider;)Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$1;->this$0:Lcom/miui/gallery/video/VideoSubtitleProvider;

    invoke-static {v2}, Lcom/miui/gallery/video/VideoSubtitleProvider;->access$200(Lcom/miui/gallery/video/VideoSubtitleProvider;)Landroid/graphics/Typeface;

    move-result-object v2

    invoke-interface {v0, v1, p1, v2}, Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;->onSubtitleParsed(Ljava/lang/String;Ljava/util/List;Landroid/graphics/Typeface;)V

    :cond_2
    return-void
.end method
