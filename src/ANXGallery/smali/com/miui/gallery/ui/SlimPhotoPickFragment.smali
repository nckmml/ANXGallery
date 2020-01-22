.class public Lcom/miui/gallery/ui/SlimPhotoPickFragment;
.super Lcom/miui/gallery/ui/CleanerPhotoPickFragment;
.source "SlimPhotoPickFragment.java"


# instance fields
.field private mSlimDescriptionDialog:Landroid/app/Dialog;

.field private mStartSlimListener:Landroid/view/View$OnClickListener;


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;-><init>(I)V

    new-instance v0, Lcom/miui/gallery/ui/SlimPhotoPickFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/SlimPhotoPickFragment$1;-><init>(Lcom/miui/gallery/ui/SlimPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mStartSlimListener:Landroid/view/View$OnClickListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/SlimPhotoPickFragment;)Landroid/app/Dialog;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mSlimDescriptionDialog:Landroid/app/Dialog;

    return-object p0
.end method


# virtual methods
.method protected getLayoutSource()I
    .locals 1

    const v0, 0x7f0b01a5

    return v0
.end method

.method public getPageName()Ljava/lang/String;
    .locals 1

    const-string v0, "cleaner_slim_photo_pick"

    return-object v0
.end method

.method protected getSelection()Ljava/lang/String;
    .locals 3

    const/4 v0, 0x3

    new-array v0, v0, [Ljava/lang/Object;

    sget-object v1, Lcom/miui/gallery/cleaner/slim/SlimScanner;->SYNCED_SLIM_SCAN_SELECTION:Ljava/lang/String;

    const/4 v2, 0x0

    aput-object v1, v0, v2

    const/4 v1, 0x1

    const-string v2, "_id"

    aput-object v2, v0, v1

    iget-object v1, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mScanResultIds:Ljava/util/List;

    const-string v2, ","

    invoke-static {v2, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x2

    aput-object v1, v0, v2

    const-string v1, "%s AND %s IN (%s)"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 3

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onCreate(Landroid/os/Bundle;)V

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$PhotoSlim;->isFirstUsePhotoSlim()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    const v0, 0x7f0b0146

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-virtual {p1, v0, v1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    new-instance v0, Landroid/app/Dialog;

    iget-object v1, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-direct {v0, v1}, Landroid/app/Dialog;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mSlimDescriptionDialog:Landroid/app/Dialog;

    iget-object v0, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mSlimDescriptionDialog:Landroid/app/Dialog;

    invoke-virtual {v0, p1}, Landroid/app/Dialog;->setContentView(Landroid/view/View;)V

    const v0, 0x7f09008d

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/Button;

    new-instance v0, Lcom/miui/gallery/ui/SlimPhotoPickFragment$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/SlimPhotoPickFragment$2;-><init>(Lcom/miui/gallery/ui/SlimPhotoPickFragment;)V

    invoke-virtual {p1, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mSlimDescriptionDialog:Landroid/app/Dialog;

    invoke-virtual {p1}, Landroid/app/Dialog;->getWindow()Landroid/view/Window;

    move-result-object p1

    const/16 v0, 0x50

    invoke-virtual {p1, v0}, Landroid/view/Window;->setGravity(I)V

    const/4 v0, -0x1

    const/4 v1, -0x2

    invoke-virtual {p1, v0, v1}, Landroid/view/Window;->setLayout(II)V

    iget-object p1, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mSlimDescriptionDialog:Landroid/app/Dialog;

    invoke-virtual {p1}, Landroid/app/Dialog;->show()V

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/preference/GalleryPreferences$PhotoSlim;->setIsFirstUsePhotoSlim(Ljava/lang/Boolean;)V

    :cond_0
    return-void
.end method

.method public onDestroy()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mSlimDescriptionDialog:Landroid/app/Dialog;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/app/Dialog;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mSlimDescriptionDialog:Landroid/app/Dialog;

    invoke-virtual {v0}, Landroid/app/Dialog;->dismiss()V

    :cond_0
    return-void
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 0

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p2}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object p2

    const p3, 0x7f10030a

    invoke-virtual {p2, p3}, Lmiui/app/ActionBar;->setTitle(I)V

    iget-object p2, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mDeleteButton:Landroid/widget/Button;

    iget-object p3, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mStartSlimListener:Landroid/view/View$OnClickListener;

    invoke-virtual {p2, p3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/SlimPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    const/4 p3, 0x0

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->setClickToPhotoPageEnable(Z)V

    return-object p1
.end method

.method protected recordCancelSelectAllEvent()V
    .locals 2

    const-string v0, "cleaner"

    const-string v1, "slim_select_all_cancel"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method
