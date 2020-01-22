.class Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;
.super Landroid/database/DataSetObserver;
.source "SimilarPhotoPickFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/SimilarPhotoPickFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/SimilarPhotoPickFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/SimilarPhotoPickFragment;

    invoke-direct {p0}, Landroid/database/DataSetObserver;-><init>()V

    return-void
.end method


# virtual methods
.method public onChanged()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/SimilarPhotoPickFragment;

    iget-boolean v0, v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mIsFirstLoadFinish:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/SimilarPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->getCount()I

    move-result v0

    if-lez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/SimilarPhotoPickFragment;

    const/4 v1, 0x0

    iput-boolean v1, v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mIsFirstLoadFinish:Z

    invoke-static {v0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->access$000(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Lmiui/widget/SlidingButton;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/widget/SlidingButton;->isChecked()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/SimilarPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setAllItemsCheckState(Z)V

    :cond_0
    return-void
.end method
