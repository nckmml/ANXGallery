.class Lcom/miui/gallery/collage/app/poster/PosterMenuFragment$1;
.super Ljava/lang/Object;
.source "PosterMenuFragment.java"

# interfaces
.implements Lcom/miui/gallery/collage/core/CollagePresenter$DataLoadListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment$1;->this$0:Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDataLoad()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment$1;->this$0:Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;->access$002(Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;Z)Z

    iget-object v0, p0, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment$1;->this$0:Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;

    invoke-static {v0}, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;->access$100(Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;)V

    iget-object v0, p0, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment$1;->this$0:Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;

    invoke-static {v0}, Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;->access$200(Lcom/miui/gallery/collage/app/poster/PosterMenuFragment;)V

    return-void
.end method
