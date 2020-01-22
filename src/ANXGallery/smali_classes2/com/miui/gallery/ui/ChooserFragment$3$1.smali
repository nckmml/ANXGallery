.class Lcom/miui/gallery/ui/ChooserFragment$3$1;
.super Ljava/lang/Object;
.source "ChooserFragment.java"

# interfaces
.implements Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/ChooserFragment$3;->fileNotYetSet()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/ChooserFragment$3;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/ChooserFragment$3;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$3$1;->this$1:Lcom/miui/gallery/ui/ChooserFragment$3;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onFilesProcessed(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroid/net/Uri;",
            ">;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment$3$1;->this$1:Lcom/miui/gallery/ui/ChooserFragment$3;

    iget-object v0, v0, Lcom/miui/gallery/ui/ChooserFragment$3;->this$0:Lcom/miui/gallery/ui/ChooserFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/ChooserFragment;->access$400(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->sendFiles(Ljava/util/List;)V

    return-void
.end method
