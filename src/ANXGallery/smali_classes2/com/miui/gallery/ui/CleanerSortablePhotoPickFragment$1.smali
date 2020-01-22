.class Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;
.super Ljava/lang/Object;
.source "CleanerSortablePhotoPickFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/app/LoaderManager;->getLoader(I)Landroid/content/Loader;

    move-result-object v0

    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    const v1, 0x7f0900e9

    const-string v2, "cleaner"

    if-eq p1, v1, :cond_1

    const v1, 0x7f09030b

    if-eq p1, v1, :cond_0

    goto :goto_0

    :cond_0
    const-string p1, "video_sort_by_size"

    invoke-static {v2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;

    move-object v1, v0

    check-cast v1, Landroid/content/CursorLoader;

    sget-object v2, Lcom/miui/gallery/widget/SortByHeader$SortBy;->SIZE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-static {p1, v1, v2}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->access$000(Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;Landroid/content/CursorLoader;Lcom/miui/gallery/widget/SortByHeader$SortBy;)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;

    move-object v1, v0

    check-cast v1, Landroid/content/CursorLoader;

    sget-object v3, Lcom/miui/gallery/widget/SortByHeader$SortBy;->DATE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-static {p1, v1, v3}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->access$000(Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;Landroid/content/CursorLoader;Lcom/miui/gallery/widget/SortByHeader$SortBy;)V

    const-string p1, "video_sort_by_date"

    invoke-static {v2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    invoke-virtual {v0}, Landroid/content/Loader;->forceLoad()V

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortByHeader:Lcom/miui/gallery/widget/SortByHeader;

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    iget-object v1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;->this$0:Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->access$100(Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;)I

    move-result v1

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/widget/SortByHeader;->updateCurrentSortView(Lcom/miui/gallery/widget/SortByHeader$SortBy;I)V

    return-void
.end method
