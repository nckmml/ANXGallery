.class public Lcom/miui/gallery/ui/SimilarPhotoPickFragment;
.super Lcom/miui/gallery/ui/CleanerPhotoPickFragment;
.source "SimilarPhotoPickFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SortCursor;,
        Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SimilarPhotoPickLoaderCallback;
    }
.end annotation


# instance fields
.field private mCheckedHeaderGroupId:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private mClusterGroupId:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private mDataSetObserver:Landroid/database/DataSetObserver;

.field private mGroupItemCount:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private mGroupStartPos:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private mIsScrolling:Z

.field private mKeepClearCheckBox:Lmiui/widget/SlidingButton;

.field private mKeepClearCheckListener:Landroid/widget/CompoundButton$OnCheckedChangeListener;

.field private mLoadMoreLayout:Lcom/miui/gallery/widget/LoadMoreLayout;

.field private mMultiChoiceModeListener:Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;

.field private mNeedResetLoader:Z

.field private mOnScanResultUpdateListener:Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;

.field private mOnScrollListener:Landroid/widget/AbsListView$OnScrollListener;

.field private mSimilarPhotoPickLoaderCallbacks:Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SimilarPhotoPickLoaderCallback;


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x3

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;-><init>(I)V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mCheckedHeaderGroupId:Ljava/util/ArrayList;

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mIsScrolling:Z

    new-instance v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;-><init>(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mDataSetObserver:Landroid/database/DataSetObserver;

    new-instance v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$2;-><init>(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mMultiChoiceModeListener:Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;

    new-instance v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$3;-><init>(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckListener:Landroid/widget/CompoundButton$OnCheckedChangeListener;

    new-instance v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$4;-><init>(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mOnScrollListener:Landroid/widget/AbsListView$OnScrollListener;

    new-instance v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$5;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$5;-><init>(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mOnScanResultUpdateListener:Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Lmiui/widget/SlidingButton;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckBox:Lmiui/widget/SlidingButton;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mCheckedHeaderGroupId:Ljava/util/ArrayList;

    return-object p0
.end method

.method static synthetic access$1000(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Lcom/miui/gallery/widget/LoadMoreLayout;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mLoadMoreLayout:Lcom/miui/gallery/widget/LoadMoreLayout;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->keepClearPhotos()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mIsScrolling:Z

    return p0
.end method

.method static synthetic access$302(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mIsScrolling:Z

    return p1
.end method

.method static synthetic access$400(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mNeedResetLoader:Z

    return p0
.end method

.method static synthetic access$402(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mNeedResetLoader:Z

    return p1
.end method

.method static synthetic access$500(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->restartLoader()V

    return-void
.end method

.method static synthetic access$700(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGroupItemCount:Ljava/util/ArrayList;

    return-object p0
.end method

.method static synthetic access$800(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGroupStartPos:Ljava/util/ArrayList;

    return-object p0
.end method

.method static synthetic access$900(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mClusterGroupId:Ljava/util/ArrayList;

    return-object p0
.end method

.method private keepClearPhotos()V
    .locals 7

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    check-cast v0, Lcom/miui/gallery/adapter/SimilarPhotoPickAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/adapter/SimilarPhotoPickAdapter;->getHeaderPositions()Ljava/util/ArrayList;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    check-cast v1, Lcom/miui/gallery/adapter/SimilarPhotoPickAdapter;

    invoke-virtual {v1}, Lcom/miui/gallery/adapter/SimilarPhotoPickAdapter;->getGroupIds()[J

    move-result-object v1

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v2

    if-eqz v2, :cond_1

    if-eqz v1, :cond_1

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v4

    if-ge v3, v4, :cond_1

    iget-object v4, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mCheckedHeaderGroupId:Ljava/util/ArrayList;

    aget-wide v5, v1, v3

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_0

    iget-object v4, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-interface {v0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    invoke-virtual {v4, v5, v2}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setItemChecked(IZ)V

    iget-object v4, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mCheckedHeaderGroupId:Ljava/util/ArrayList;

    aget-wide v5, v1, v3

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method private restartLoader()V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->resetScanResult()V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/app/LoaderManager;->getLoader(I)Landroid/content/Loader;

    move-result-object v0

    if-eqz v0, :cond_0

    move-object v1, v0

    check-cast v1, Landroid/content/CursorLoader;

    invoke-virtual {p0, v1}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->configLoader(Landroid/content/CursorLoader;)V

    invoke-virtual {v0}, Landroid/content/Loader;->forceLoad()V

    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mNeedResetLoader:Z

    return-void
.end method


# virtual methods
.method protected createAdapter()Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;
    .locals 2

    new-instance v0, Lcom/miui/gallery/adapter/SimilarPhotoPickAdapter;

    iget-object v1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-direct {v0, v1}, Lcom/miui/gallery/adapter/SimilarPhotoPickAdapter;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method protected getDeleteMessage()Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;
    .locals 2

    new-instance v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;-><init>()V

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->setDupType(I)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    move-result-object v0

    const/16 v1, 0x30

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->setReason(I)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    move-result-object v0

    const-string v1, "cleaner_similar_used"

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->setCleanerSubEvent(Ljava/lang/String;)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->build()Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;

    move-result-object v0

    return-object v0
.end method

.method protected getLayoutSource()I
    .locals 1

    const v0, 0x7f0b01a1

    return v0
.end method

.method protected getLoaderCallback()Landroid/app/LoaderManager$LoaderCallbacks;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mSimilarPhotoPickLoaderCallbacks:Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SimilarPhotoPickLoaderCallback;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SimilarPhotoPickLoaderCallback;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SimilarPhotoPickLoaderCallback;-><init>(Lcom/miui/gallery/ui/SimilarPhotoPickFragment;Lcom/miui/gallery/ui/SimilarPhotoPickFragment$1;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mSimilarPhotoPickLoaderCallbacks:Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SimilarPhotoPickLoaderCallback;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mSimilarPhotoPickLoaderCallbacks:Lcom/miui/gallery/ui/SimilarPhotoPickFragment$SimilarPhotoPickLoaderCallback;

    return-object v0
.end method

.method public getPageName()Ljava/lang/String;
    .locals 1

    const-string v0, "cleaner_similar_photo_pick"

    return-object v0
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    const/4 v0, 0x2

    if-ne p1, v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0a0066

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/widget/GridView;->setNumColumns(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0a0065

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/widget/GridView;->setNumColumns(I)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    invoke-virtual {p1}, Landroid/widget/GridView;->getFirstVisiblePosition()I

    move-result p1

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    invoke-virtual {v0, p1}, Landroid/widget/GridView;->setSelection(I)V

    return-void
.end method

.method public onDestroy()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    check-cast v0, Lcom/miui/gallery/cleaner/SimilarScanner;

    invoke-virtual {v0}, Lcom/miui/gallery/cleaner/SimilarScanner;->removeSingleItemGroups()V

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    iget-object v1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mOnScanResultUpdateListener:Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/cleaner/BaseScanner;->removeListener(Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;)V

    return-void
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 3

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object p2

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p3}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object p3

    const v0, 0x7f100306

    invoke-virtual {p3, v0}, Lmiui/app/ActionBar;->setTitle(I)V

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p3}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object p3

    invoke-virtual {p3}, Lmiui/app/ActionBar;->getCustomView()Landroid/view/View;

    move-result-object p3

    const v0, 0x7f09010e

    invoke-virtual {p3, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p3

    const/16 v0, 0x8

    invoke-virtual {p3, v0}, Landroid/view/View;->setVisibility(I)V

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mDataSetObserver:Landroid/database/DataSetObserver;

    invoke-virtual {p3, v0}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->registerDataSetObserver(Landroid/database/DataSetObserver;)V

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    invoke-virtual {p3, v0}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setAdapter(Landroid/widget/ListAdapter;)V

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mMultiChoiceModeListener:Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;

    invoke-virtual {p3, v0}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setMultiChoiceModeListener(Lcom/miui/gallery/widget/editwrapper/MultiChoiceModeListener;)V

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    check-cast p3, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    const/4 v0, 0x1

    invoke-virtual {p3, v0}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->setHeadersIgnorePadding(Z)V

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    iget-object v1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mOnScrollListener:Landroid/widget/AbsListView$OnScrollListener;

    invoke-virtual {p3, v1}, Landroid/widget/GridView;->setOnScrollListener(Landroid/widget/AbsListView$OnScrollListener;)V

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    const v1, 0x7f0b00d9

    const/4 v2, 0x0

    invoke-virtual {p1, v1, p3, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/widget/LoadMoreLayout;

    iput-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mLoadMoreLayout:Lcom/miui/gallery/widget/LoadMoreLayout;

    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    check-cast p1, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mLoadMoreLayout:Lcom/miui/gallery/widget/LoadMoreLayout;

    invoke-virtual {p1, p3}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->addFooterView(Landroid/view/View;)V

    const p1, 0x7f0901cf

    invoke-virtual {p2, p1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lmiui/widget/SlidingButton;

    iput-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckBox:Lmiui/widget/SlidingButton;

    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckBox:Lmiui/widget/SlidingButton;

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckListener:Landroid/widget/CompoundButton$OnCheckedChangeListener;

    invoke-virtual {p1, p3}, Lmiui/widget/SlidingButton;->setOnPerformCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckBox:Lmiui/widget/SlidingButton;

    invoke-virtual {p1, v0}, Lmiui/widget/SlidingButton;->setEnabled(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckBox:Lmiui/widget/SlidingButton;

    invoke-virtual {p1, v0}, Lmiui/widget/SlidingButton;->setChecked(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    iget-object p3, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mOnScanResultUpdateListener:Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;

    invoke-virtual {p1, p3}, Lcom/miui/gallery/cleaner/BaseScanner;->addListener(Lcom/miui/gallery/cleaner/BaseScanner$OnScanResultUpdateListener;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->resetScanResult()V

    return-object p2
.end method

.method public onItemSelectedChanged()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->getCount()I

    move-result v0

    const/4 v1, 0x0

    if-lez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mDeleteButton:Landroid/widget/Button;

    iget-object v2, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {v2}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->getCheckedItemCount()I

    move-result v2

    if-lez v2, :cond_0

    const/4 v1, 0x1

    :cond_0
    invoke-virtual {v0, v1}, Landroid/widget/Button;->setEnabled(Z)V

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mDeleteButton:Landroid/widget/Button;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setEnabled(Z)V

    :goto_0
    return-void
.end method

.method public onResume()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onResume()V

    iget-boolean v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mIsScrolling:Z

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mNeedResetLoader:Z

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->restartLoader()V

    :cond_0
    return-void
.end method

.method protected recordCancelSelectAllEvent()V
    .locals 0

    return-void
.end method

.method protected resetCheckState()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->resetCheckState()V

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mKeepClearCheckBox:Lmiui/widget/SlidingButton;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/widget/SlidingButton;->setChecked(Z)V

    return-void
.end method

.method protected resetScanResult()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->resetScanResult()V

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    check-cast v0, Lcom/miui/gallery/cleaner/SimilarScanner;

    invoke-virtual {v0}, Lcom/miui/gallery/cleaner/SimilarScanner;->getGroupStartPos()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGroupStartPos:Ljava/util/ArrayList;

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    check-cast v0, Lcom/miui/gallery/cleaner/SimilarScanner;

    invoke-virtual {v0}, Lcom/miui/gallery/cleaner/SimilarScanner;->getGroupItemCount()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mGroupItemCount:Ljava/util/ArrayList;

    iget-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    check-cast v0, Lcom/miui/gallery/cleaner/SimilarScanner;

    invoke-virtual {v0}, Lcom/miui/gallery/cleaner/SimilarScanner;->getClusterGroupId()Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/SimilarPhotoPickFragment;->mClusterGroupId:Ljava/util/ArrayList;

    return-void
.end method
