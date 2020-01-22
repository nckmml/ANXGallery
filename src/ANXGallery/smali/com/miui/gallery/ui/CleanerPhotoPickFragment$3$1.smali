.class Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;
.super Ljava/lang/Object;
.source "CleanerPhotoPickFragment.java"

# interfaces
.implements Lcom/miui/gallery/ui/DeletionTask$OnDeletionCompleteListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->onClick(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

.field final synthetic val$deleteMessage:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iput-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->val$deleteMessage:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDeleted(I[J)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object v1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object v1, v1, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f0e0028

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    const/4 v5, 0x0

    aput-object v4, v3, v5

    invoke-virtual {v1, v2, p1, v3}, Landroid/content/res/Resources;->getQuantityString(II[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    if-lez p1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-static {v0, v5}, Lcom/miui/gallery/util/SoundUtils;->playSoundForOperation(Landroid/content/Context;I)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    invoke-virtual {v0, p2}, Lcom/miui/gallery/cleaner/BaseScanner;->removeItems([J)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->resetScanResult()V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->resetCheckState()V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mScanResultIds:Ljava/util/List;

    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result p2

    if-gtz p2, :cond_2

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    invoke-virtual {p2}, Lcom/miui/gallery/cleaner/BaseScanner;->isLoadingValid()Z

    move-result p2

    if-nez p2, :cond_2

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->this$1:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object p2, p2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p2}, Lcom/miui/gallery/activity/BaseActivity;->finish()V

    :cond_2
    new-instance p2, Ljava/util/HashMap;

    invoke-direct {p2}, Ljava/util/HashMap;-><init>()V

    int-to-float p1, p1

    sget-object v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->DELETE_COUNT_STAGE:[I

    invoke-static {p1, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->formatValueStage(F[I)Ljava/lang/String;

    move-result-object p1

    const-string v0, "count"

    invoke-virtual {p2, v0, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3$1;->val$deleteMessage:Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->getCleanerSubEvent()Ljava/lang/String;

    move-result-object p1

    const-string v0, "cleaner"

    invoke-static {v0, p1, p2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method
