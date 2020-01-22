.class Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$4;
.super Ljava/lang/Object;
.source "PhotoDetailFragment.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->downloadOrigin()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$4;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 0

    const-string p1, "PhotoDetailFragment"

    const-string p2, "pre DownLoad Origin"

    invoke-static {p1, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$4;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->downLoadOrigin()V

    return-void
.end method
