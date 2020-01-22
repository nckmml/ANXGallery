.class Lcom/miui/gallery/ui/ChooserFragment$3;
.super Ljava/lang/Object;
.source "ChooserFragment.java"

# interfaces
.implements Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/ChooserFragment;->configView(Landroid/view/View;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/ChooserFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/ChooserFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$3;->this$0:Lcom/miui/gallery/ui/ChooserFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public fileNotYetSet()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment$3;->this$0:Lcom/miui/gallery/ui/ChooserFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/ChooserFragment;->access$300(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment$3;->this$0:Lcom/miui/gallery/ui/ChooserFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/ChooserFragment;->access$300(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/ui/ChooserFragment$3$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/ChooserFragment$3$1;-><init>(Lcom/miui/gallery/ui/ChooserFragment$3;)V

    invoke-interface {v0, v1}, Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;->onMishareClicked(Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;)V

    :cond_0
    return-void
.end method
