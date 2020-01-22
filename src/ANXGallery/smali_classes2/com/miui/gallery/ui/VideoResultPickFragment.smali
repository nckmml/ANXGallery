.class public Lcom/miui/gallery/ui/VideoResultPickFragment;
.super Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;
.source "VideoResultPickFragment.java"


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x2

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;-><init>(I)V

    return-void
.end method


# virtual methods
.method protected getDeleteMessage()Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;
    .locals 2

    new-instance v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;-><init>()V

    const-string v1, "cleaner_video_used"

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->setCleanerSubEvent(Ljava/lang/String;)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    move-result-object v0

    const/16 v1, 0x2f

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->setReason(I)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->setDupType(I)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->build()Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;

    move-result-object v0

    return-object v0
.end method

.method protected getLayoutSource()I
    .locals 1

    const v0, 0x7f0b01d6

    return v0
.end method

.method public getPageName()Ljava/lang/String;
    .locals 1

    const-string v0, "cleaner_video_result_pick"

    return-object v0
.end method

.method protected getSelection()Ljava/lang/String;
    .locals 3

    const/4 v0, 0x3

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    const-string v2, "_id"

    aput-object v2, v0, v1

    iget-object v1, p0, Lcom/miui/gallery/ui/VideoResultPickFragment;->mScanResultIds:Ljava/util/List;

    const-string v2, ","

    invoke-static {v2, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    aput-object v1, v0, v2

    const/4 v1, 0x2

    const-string v2, "(size > 0)"

    aput-object v2, v0, v1

    const-string v1, "%s IN (%s) AND %s"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 0

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/ui/VideoResultPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p2}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object p2

    const p3, 0x7f100311

    invoke-virtual {p2, p3}, Lmiui/app/ActionBar;->setTitle(I)V

    return-object p1
.end method

.method protected recordCancelSelectAllEvent()V
    .locals 2

    const-string v0, "cleaner"

    const-string v1, "video_select_all_cancel"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
