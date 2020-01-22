.class Lcom/miui/gallery/editor/ui/view/DownloadView$1;
.super Lcom/miui/gallery/editor/ui/view/DownloadView$MyAnimalListener;
.source "DownloadView.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/editor/ui/view/DownloadView;->endDownloading()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/editor/ui/view/DownloadView;


# direct methods
.method constructor <init>(Lcom/miui/gallery/editor/ui/view/DownloadView;)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/editor/ui/view/DownloadView$1;->this$0:Lcom/miui/gallery/editor/ui/view/DownloadView;

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/editor/ui/view/DownloadView$MyAnimalListener;-><init>(Lcom/miui/gallery/editor/ui/view/DownloadView;Lcom/miui/gallery/editor/ui/view/DownloadView$1;)V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/editor/ui/view/DownloadView$1;->this$0:Lcom/miui/gallery/editor/ui/view/DownloadView;

    invoke-static {p1}, Lcom/miui/gallery/editor/ui/view/DownloadView;->access$100(Lcom/miui/gallery/editor/ui/view/DownloadView;)Landroid/widget/ImageView;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/editor/ui/view/DownloadView$1$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/editor/ui/view/DownloadView$1$1;-><init>(Lcom/miui/gallery/editor/ui/view/DownloadView$1;)V

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/editor/ui/view/DownloadView;->access$300(Lcom/miui/gallery/editor/ui/view/DownloadView;Landroid/view/View;Landroid/animation/Animator$AnimatorListener;)V

    return-void
.end method
