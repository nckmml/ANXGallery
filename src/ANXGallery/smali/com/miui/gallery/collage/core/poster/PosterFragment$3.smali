.class Lcom/miui/gallery/collage/core/poster/PosterFragment$3;
.super Ljava/lang/Object;
.source "PosterFragment.java"

# interfaces
.implements Lcom/miui/gallery/collage/widget/CollageLayout$BitmapExchangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/collage/core/poster/PosterFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/collage/core/poster/PosterFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/collage/core/poster/PosterFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/collage/core/poster/PosterFragment$3;->this$0:Lcom/miui/gallery/collage/core/poster/PosterFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onBitmapExchange(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/collage/core/poster/PosterFragment$3;->this$0:Lcom/miui/gallery/collage/core/poster/PosterFragment;

    invoke-static {v0}, Lcom/miui/gallery/collage/core/poster/PosterFragment;->access$200(Lcom/miui/gallery/collage/core/poster/PosterFragment;)Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/collage/widget/CollageImageView;

    iget-object v1, p0, Lcom/miui/gallery/collage/core/poster/PosterFragment$3;->this$0:Lcom/miui/gallery/collage/core/poster/PosterFragment;

    invoke-static {v1}, Lcom/miui/gallery/collage/core/poster/PosterFragment;->access$200(Lcom/miui/gallery/collage/core/poster/PosterFragment;)Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1, p2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/collage/widget/CollageImageView;

    iget-object v2, p0, Lcom/miui/gallery/collage/core/poster/PosterFragment$3;->this$0:Lcom/miui/gallery/collage/core/poster/PosterFragment;

    invoke-static {v2}, Lcom/miui/gallery/collage/core/poster/PosterFragment;->access$200(Lcom/miui/gallery/collage/core/poster/PosterFragment;)Ljava/util/Map;

    move-result-object v2

    invoke-interface {v2, p1, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object p1, p0, Lcom/miui/gallery/collage/core/poster/PosterFragment$3;->this$0:Lcom/miui/gallery/collage/core/poster/PosterFragment;

    invoke-static {p1}, Lcom/miui/gallery/collage/core/poster/PosterFragment;->access$200(Lcom/miui/gallery/collage/core/poster/PosterFragment;)Ljava/util/Map;

    move-result-object p1

    invoke-interface {p1, p2, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method
