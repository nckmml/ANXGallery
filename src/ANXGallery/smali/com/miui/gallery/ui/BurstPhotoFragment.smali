.class public Lcom/miui/gallery/ui/BurstPhotoFragment;
.super Lcom/miui/gallery/ui/PhotoPageFragmentBase;
.source "BurstPhotoFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;
    }
.end annotation


# instance fields
.field private mBurstChoiceManager:Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;

.field private mTitle:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;-><init>()V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/ui/BurstPhotoFragment;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mTitle:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/BurstPhotoFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->onSaveBurstItemsCompleted()V

    return-void
.end method

.method private configViewLayout(Landroid/view/View;)V
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const/4 v1, 0x0

    const-string v2, "notch_height"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v0

    const v2, 0x7f090378

    invoke-virtual {p1, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    invoke-virtual {p1, v1, v0, v1, v1}, Landroid/view/View;->setPadding(IIII)V

    iget-object p1, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0600d1

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/ViewPager;->setPageMargin(I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mAdapter:Lcom/miui/gallery/adapter/PhotoPageAdapter;

    check-cast p1, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->setPagerView(Lcom/miui/gallery/widget/ViewPager;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mAdapter:Lcom/miui/gallery/adapter/PhotoPageAdapter;

    check-cast p1, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0600d0

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->setMaxPagerItemWidth(I)V

    return-void
.end method

.method public static newInstance(Landroid/net/Uri;Landroid/os/Bundle;)Lcom/miui/gallery/ui/BurstPhotoFragment;
    .locals 1

    if-nez p1, :cond_0

    new-instance p1, Landroid/os/Bundle;

    invoke-direct {p1}, Landroid/os/Bundle;-><init>()V

    :cond_0
    if-eqz p0, :cond_1

    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p0

    const-string v0, "photo_uri"

    invoke-virtual {p1, v0, p0}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    new-instance p0, Lcom/miui/gallery/ui/BurstPhotoFragment;

    invoke-direct {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;-><init>()V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/BurstPhotoFragment;->setArguments(Landroid/os/Bundle;)V

    return-object p0
.end method

.method private onSaveBurstItemsCompleted()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    const/4 v1, -0x1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/activity/BaseActivity;->setResult(ILandroid/content/Intent;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0}, Lcom/miui/gallery/activity/BaseActivity;->finish()V

    :cond_0
    return-void
.end method


# virtual methods
.method protected getLayout(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    const v0, 0x7f0b005e

    const/4 v1, 0x0

    invoke-virtual {p1, v0, p2, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    return-object p1
.end method

.method public getPageName()Ljava/lang/String;
    .locals 1

    const-string v0, "burst"

    return-object v0
.end method

.method protected getPhotoAdapter()Lcom/miui/gallery/adapter/PhotoPageAdapter;
    .locals 7

    new-instance v6, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getInitCount()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getImageLoadParams()Lcom/miui/gallery/model/ImageLoadParams;

    move-result-object v2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v3, "photo_init_position"

    const/4 v4, 0x0

    invoke-virtual {v0, v3, v4}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getEnterViewInfo(I)Lcom/miui/gallery/util/photoview/ItemViewInfo;

    move-result-object v3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->getPhotoPageInteractionListener()Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;

    move-result-object v5

    move-object v0, v6

    move-object v4, p0

    invoke-direct/range {v0 .. v5}, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;-><init>(ILcom/miui/gallery/model/ImageLoadParams;Lcom/miui/gallery/util/photoview/ItemViewInfo;Lcom/miui/gallery/adapter/PhotoPageAdapter$OnPreviewedListener;Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;)V

    return-object v6
.end method

.method protected getTAG()Ljava/lang/String;
    .locals 1

    const-string v0, "BurstPhotoFragment"

    return-object v0
.end method

.method protected getThemeRes()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-static {v0}, Lcom/miui/gallery/util/BuildUtil;->isMiui10(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    const v0, 0x7f11014d

    return v0

    :cond_0
    const v0, 0x7f110154

    return v0
.end method

.method public onBackPressed()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mBurstChoiceManager:Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;->discard()V

    :cond_0
    const/4 v0, 0x1

    return v0
.end method

.method protected onDataSetLoaded(Lcom/miui/gallery/model/BaseDataSet;Z)V
    .locals 1

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->onDataSetLoaded(Lcom/miui/gallery/model/BaseDataSet;Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mBurstChoiceManager:Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;

    if-eqz v0, :cond_1

    if-eqz p2, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;->onStart()V

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mBurstChoiceManager:Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;->onDataSetLoaded(Lcom/miui/gallery/model/BaseDataSet;)V

    :cond_1
    return-void
.end method

.method protected onItemChanged(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mBurstChoiceManager:Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;->onItemChanged(I)V

    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 0

    invoke-super {p0}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->onResume()V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/BurstPhotoFragment;->onContentChanged()V

    return-void
.end method

.method protected onViewInflated(Landroid/view/View;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->onViewInflated(Landroid/view/View;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/BurstPhotoFragment;->configViewLayout(Landroid/view/View;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0}, Lcom/miui/gallery/activity/BaseActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-static {v0}, Lcom/android/internal/WindowCompat;->setCutoutModeShortEdges(Landroid/view/Window;)V

    const v0, 0x7f0900aa

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mTitle:Landroid/widget/TextView;

    new-instance p1, Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;-><init>(Lcom/miui/gallery/ui/BurstPhotoFragment;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/BurstPhotoFragment;->mBurstChoiceManager:Lcom/miui/gallery/ui/BurstPhotoFragment$BurstChoiceModeManager;

    return-void
.end method
