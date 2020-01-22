.class Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl$1;
.super Ljava/lang/Object;
.source "XmsMultiThumbnailSequenceViewImpl.java"

# interfaces
.implements Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->nativeGetIcon(Ljava/lang/String;J)J
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;

.field final synthetic val$path:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl$1;->this$0:Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;

    iput-object p2, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl$1;->val$path:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onLoadingCancelled(Ljava/lang/String;Landroid/view/View;)V
    .locals 0

    return-void
.end method

.method public onLoadingComplete(Ljava/lang/String;Landroid/view/View;Landroid/graphics/Bitmap;)V
    .locals 0

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "onLoadingComplete"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p2, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl$1;->val$path:Ljava/lang/String;

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "XmsMultiThumbnailSequenceViewImpl"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl$1;->this$0:Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;

    invoke-static {p1}, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->access$000(Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;)V

    return-void
.end method

.method public onLoadingFailed(Ljava/lang/String;Landroid/view/View;Lcom/nostra13/universalimageloader/core/assist/FailReason;)V
    .locals 0

    return-void
.end method

.method public onLoadingStarted(Ljava/lang/String;Landroid/view/View;)V
    .locals 0

    return-void
.end method
