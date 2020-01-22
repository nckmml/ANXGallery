.class public Lcom/miui/gallery/ui/ChooserFragment;
.super Landroid/app/Fragment;
.source "ChooserFragment.java"

# interfaces
.implements Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;
.implements Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;
.implements Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/ChooserFragment$Component;,
        Lcom/miui/gallery/ui/ChooserFragment$PriorityComparator;,
        Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;,
        Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;,
        Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;,
        Lcom/miui/gallery/ui/ChooserFragment$SortInitializeListener;,
        Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;
    }
.end annotation


# instance fields
.field private mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

.field private mListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

.field private mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

.field private mOnMishareClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

.field private mOnProjectClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;

.field private mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

.field private mSorterInitializedListener:Lcom/miui/gallery/util/ShareComponentSorter$OnInitializedListener;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/app/Fragment;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    return-object p0
.end method

.method static synthetic access$1300(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mOnProjectClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mOnMishareClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/mishare/app/view/MiShareGalleryTransferView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    return-object p0
.end method

.method public static newInstance(Landroid/content/Intent;IZI)Lcom/miui/gallery/ui/ChooserFragment;
    .locals 3

    new-instance v0, Lcom/miui/gallery/ui/ChooserFragment;

    invoke-direct {v0}, Lcom/miui/gallery/ui/ChooserFragment;-><init>()V

    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    const-string v2, "key_target_intent"

    invoke-virtual {v1, v2, p0}, Landroid/os/Bundle;->putParcelable(Ljava/lang/String;Landroid/os/Parcelable;)V

    const-string p0, "key_theme"

    invoke-virtual {v1, p0, p1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    const-string p0, "init_visible"

    invoke-virtual {v1, p0, p2}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    const-string p0, "share_mode"

    invoke-virtual {v1, p0, p3}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/ChooserFragment;->setArguments(Landroid/os/Bundle;)V

    return-object v0
.end method


# virtual methods
.method public configView(Landroid/view/View;I)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    sget-object v1, Lcom/miui/gallery/ui/ShareStateRouter;->IS_MISHARE_AVAILABLE:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Boolean;

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    invoke-static {}, Lcom/miui/gallery/ui/ShareStateRouter;->getInstance()Lcom/miui/gallery/ui/ShareStateRouter;

    move-result-object v1

    invoke-virtual {v1, p0}, Lcom/miui/gallery/ui/ShareStateRouter;->registerProjectStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;)V

    invoke-static {}, Lcom/miui/gallery/ui/ShareStateRouter;->getInstance()Lcom/miui/gallery/ui/ShareStateRouter;

    move-result-object v1

    invoke-virtual {v1, p0}, Lcom/miui/gallery/ui/ShareStateRouter;->registerPrintStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;)V

    const v1, 0x7f09021e

    invoke-virtual {p1, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->bind()V

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {p1, v2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v3, 0x7f10017c

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setDeliveryTitle(Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    new-instance v1, Lcom/miui/gallery/ui/ChooserFragment$2;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/ChooserFragment$2;-><init>(Lcom/miui/gallery/ui/ChooserFragment;)V

    invoke-virtual {p1, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setScreenThrowClickListener(Landroid/view/View$OnClickListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    new-instance v1, Lcom/miui/gallery/ui/ChooserFragment$3;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/ChooserFragment$3;-><init>(Lcom/miui/gallery/ui/ChooserFragment;)V

    invoke-virtual {p1, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setFileIfNotYet(Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v1, 0x7f06031b

    invoke-virtual {p1, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iput p1, v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->bottomMargin:I

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v1, 0x7f06031c

    invoke-virtual {p1, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iput p1, v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->bottomMargin:I

    :goto_0
    const/4 p1, 0x2

    if-eq p2, p1, :cond_2

    const/4 p1, 0x3

    if-eq p2, p1, :cond_1

    goto :goto_1

    :cond_1
    sget-object p1, Lcom/miui/gallery/ui/ShareStateRouter;->IS_MISHARE_AVAILABLE:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setMiPrintEnable(Z)V

    :cond_2
    sget-object p1, Lcom/miui/gallery/ui/ShareStateRouter;->IS_MISHARE_AVAILABLE:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {p1, v2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setScreenThrowEnable(Z)V

    :cond_3
    :goto_1
    return-void
.end method

.method public onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 6

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getArguments()Landroid/os/Bundle;

    move-result-object p3

    if-eqz p3, :cond_1

    const-string v0, "key_target_intent"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;)Landroid/os/Parcelable;

    move-result-object v0

    check-cast v0, Landroid/content/Intent;

    if-eqz v0, :cond_1

    const/4 v1, 0x0

    const-string v2, "key_theme"

    invoke-virtual {p3, v2, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v2

    const/4 v3, 0x1

    const-string v4, "init_visible"

    invoke-virtual {p3, v4, v3}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v4

    const-string v5, "share_mode"

    invoke-virtual {p3, v5, v3}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result p3

    const v3, 0x7f0b0066

    invoke-virtual {p1, v3, p2, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    const p2, 0x7f0900ac

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroidx/recyclerview/widget/RecyclerView;

    iput-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    new-instance v3, Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getActivity()Landroid/app/Activity;

    move-result-object v5

    invoke-direct {v3, v5, v1, v1}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    invoke-virtual {p2, v3}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    new-instance p2, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {p2, v1, v0, v2, v4}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;-><init>(Landroid/content/Context;Landroid/content/Intent;IZ)V

    iput-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment;->mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    invoke-virtual {p2, v0}, Landroidx/recyclerview/widget/RecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment;->mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    new-instance v0, Lcom/miui/gallery/ui/ChooserFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/ChooserFragment$1;-><init>(Lcom/miui/gallery/ui/ChooserFragment;)V

    invoke-virtual {p2, v0}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->setOnIntentSelectedListener(Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;)V

    invoke-virtual {p0, p1, p3}, Lcom/miui/gallery/ui/ChooserFragment;->configView(Landroid/view/View;I)V

    invoke-static {}, Lcom/miui/gallery/util/ShareComponentSorter;->getInstance()Lcom/miui/gallery/util/ShareComponentSorter;

    move-result-object p2

    invoke-virtual {p2}, Lcom/miui/gallery/util/ShareComponentSorter;->initialized()Z

    move-result p2

    if-nez p2, :cond_0

    const-string p2, "ChooserFragment"

    const-string p3, "sorter not initialized"

    invoke-static {p2, p3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    new-instance p2, Lcom/miui/gallery/ui/ChooserFragment$SortInitializeListener;

    const/4 p3, 0x0

    invoke-direct {p2, p0, p3}, Lcom/miui/gallery/ui/ChooserFragment$SortInitializeListener;-><init>(Lcom/miui/gallery/ui/ChooserFragment;Lcom/miui/gallery/ui/ChooserFragment$1;)V

    iput-object p2, p0, Lcom/miui/gallery/ui/ChooserFragment;->mSorterInitializedListener:Lcom/miui/gallery/util/ShareComponentSorter$OnInitializedListener;

    invoke-static {}, Lcom/miui/gallery/util/ShareComponentSorter;->getInstance()Lcom/miui/gallery/util/ShareComponentSorter;

    move-result-object p2

    iget-object p3, p0, Lcom/miui/gallery/ui/ChooserFragment;->mSorterInitializedListener:Lcom/miui/gallery/util/ShareComponentSorter$OnInitializedListener;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/util/ShareComponentSorter;->registerOnInitializedListener(Lcom/miui/gallery/util/ShareComponentSorter$OnInitializedListener;)V

    invoke-static {}, Lcom/miui/gallery/util/ShareComponentSorter;->getInstance()Lcom/miui/gallery/util/ShareComponentSorter;

    move-result-object p2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/ChooserFragment;->getActivity()Landroid/app/Activity;

    move-result-object p3

    invoke-virtual {p3}, Landroid/app/Activity;->getApplicationContext()Landroid/content/Context;

    move-result-object p3

    invoke-virtual {p2, p3}, Lcom/miui/gallery/util/ShareComponentSorter;->initialize(Landroid/content/Context;)V

    :cond_0
    return-object p1

    :cond_1
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "target intent couldn\'t be null"

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public onDestroy()V
    .locals 1

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    iput-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mOnProjectClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;

    iput-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mOnMishareClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->release()V

    :cond_0
    invoke-super {p0}, Landroid/app/Fragment;->onDestroy()V

    return-void
.end method

.method public onDestroyView()V
    .locals 2

    invoke-super {p0}, Landroid/app/Fragment;->onDestroyView()V

    invoke-static {}, Lcom/miui/gallery/ui/ShareStateRouter;->getInstance()Lcom/miui/gallery/ui/ShareStateRouter;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/miui/gallery/ui/ShareStateRouter;->removeProjectStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$ProjectStateObserver;)V

    invoke-static {}, Lcom/miui/gallery/ui/ShareStateRouter;->getInstance()Lcom/miui/gallery/ui/ShareStateRouter;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/miui/gallery/ui/ShareStateRouter;->removePrintStateObserver(Lcom/miui/gallery/ui/ShareStateRouter$PrintStateObserver;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mSorterInitializedListener:Lcom/miui/gallery/util/ShareComponentSorter$OnInitializedListener;

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/util/ShareComponentSorter;->getInstance()Lcom/miui/gallery/util/ShareComponentSorter;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mSorterInitializedListener:Lcom/miui/gallery/util/ShareComponentSorter$OnInitializedListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/util/ShareComponentSorter;->removeOnInitializedListener(Lcom/miui/gallery/util/ShareComponentSorter$OnInitializedListener;)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->unbind()V

    :cond_1
    return-void
.end method

.method public onObservePrintState(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setMiPrintEnable(Z)V

    return-void
.end method

.method public onObserveProjectState(I)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eq p1, v2, :cond_3

    const/4 v3, 0x2

    if-eq p1, v3, :cond_2

    const/4 v1, 0x3

    if-eq p1, v1, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {v0, v2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setScreenThrowHighLight(Z)V

    goto :goto_0

    :cond_2
    invoke-virtual {v0, v2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setScreenThrowEnable(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {p1, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setScreenThrowHighLight(Z)V

    goto :goto_0

    :cond_3
    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setScreenThrowEnable(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mMiShareTransferView:Lcom/miui/mishare/app/view/MiShareGalleryTransferView;

    invoke-virtual {p1, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setScreenThrowHighLight(Z)V

    :goto_0
    return-void
.end method

.method public onPageScrollStateChanged(I)V
    .locals 0

    return-void
.end method

.method public onPageScrolled(IFI)V
    .locals 0

    return-void
.end method

.method public onPageSelected(I)V
    .locals 0

    return-void
.end method

.method public onVisibilityChanged(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->resume()V

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->pause()V

    :cond_1
    :goto_0
    return-void
.end method

.method public requery(Landroid/content/Intent;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/ChooserFragment;->mAdapter:Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/ChooserFragment$ResolverAdapter;->requery(Landroid/content/Intent;)Z

    return-void
.end method

.method public setOnIntentSelectedListener(Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    return-void
.end method

.method public setOnMishareClickedListener(Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mOnMishareClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

    return-void
.end method

.method public setOnProjectClickedListener(Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment;->mOnProjectClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;

    return-void
.end method
