.class public Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;
.super Lcom/miui/gallery/video/editor/ui/MenuFragment;
.source "SmartEffectFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;,
        Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$SmartEffectItemSelectChangeListener;
    }
.end annotation


# instance fields
.field private final TAG:Ljava/lang/String;

.field private mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

.field private mCancelView:Landroid/view/View;

.field private mLayout:Landroid/view/View;

.field private mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

.field private mLoadSmartEffectTemplateTask:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;

.field private mOkView:Landroid/view/View;

.field private mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

.field private mSavedSelectedSmartEffectIndex:I

.field private mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

.field private mSmartEffectManager:Lcom/miui/gallery/video/editor/manager/SmartEffectManager;

.field private mSmartEffects:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/video/editor/SmartEffect;",
            ">;"
        }
    .end annotation
.end field

.field private mTitleView:Landroid/widget/TextView;

.field private mToast:Landroid/widget/Toast;

.field private mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

.field private mVideoTotalTime:I


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/ui/MenuFragment;-><init>()V

    const-string v0, "SmartEffectFragment"

    iput-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->TAG:Ljava/lang/String;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSmartEffects:Ljava/util/ArrayList;

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLoadSmartEffectTemplateTask:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;

    return-object p0
.end method

.method static synthetic access$1000(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLayout:Landroid/view/View;

    return-object p0
.end method

.method static synthetic access$1100(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Lcom/miui/gallery/video/editor/manager/SmartEffectManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSmartEffectManager:Lcom/miui/gallery/video/editor/manager/SmartEffectManager;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;Ljava/util/List;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->refreshData(Ljava/util/List;)V

    return-void
.end method

.method static synthetic access$400(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSmartEffects:Ljava/util/ArrayList;

    return-object p0
.end method

.method static synthetic access$500(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->updateSelectedItemPosition(I)V

    return-void
.end method

.method static synthetic access$800(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)Landroid/widget/Toast;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mToast:Landroid/widget/Toast;

    return-object p0
.end method

.method static synthetic access$802(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;Landroid/widget/Toast;)Landroid/widget/Toast;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mToast:Landroid/widget/Toast;

    return-object p1
.end method

.method static synthetic access$900(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoTotalTime:I

    return p0
.end method

.method private initListener()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mOkView:Landroid/view/View;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$z_CQ5TTc87pY8L7xIbx4YTgtfbE;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$z_CQ5TTc87pY8L7xIbx4YTgtfbE;-><init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mCancelView:Landroid/view/View;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$5e6jdK8gJhHnpiGCPGlZIo7d2JY;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$5e6jdK8gJhHnpiGCPGlZIo7d2JY;-><init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;-><init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->setDownloadTaskListener(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;)V

    return-void
.end method

.method private initRecyclerView(Landroid/view/View;)V
    .locals 8

    const v0, 0x7f0902a0

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    new-instance p1, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mContext:Landroid/content/Context;

    const/4 v1, 0x0

    invoke-direct {p1, v0, v1, v1}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    new-instance v0, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;-><init>(Landroid/content/Context;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->setSmoothScroller(Lcom/miui/gallery/ui/SmoothScrollerController;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f0602d5

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f0602d0

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    new-instance v0, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$BlankDivider;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v1, v0

    move v2, v3

    invoke-direct/range {v1 .. v7}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$BlankDivider;-><init>(IIIIII)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;->addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    new-instance p1, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSmartEffects:Ljava/util/ArrayList;

    invoke-direct {p1, v0, v1}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;-><init>(Landroid/content/Context;Ljava/util/List;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    new-instance v0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$SmartEffectItemSelectChangeListener;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$SmartEffectItemSelectChangeListener;-><init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->setItemSelectChangeListener(Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$ItemSelectChangeListener;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    invoke-static {p1}, Lcom/miui/gallery/widget/overscroll/HorizontalOverScrollBounceEffectDecorator;->setOverScrollEffect(Landroidx/recyclerview/widget/RecyclerView;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    iget v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSavedSelectedSmartEffectIndex:I

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->setSelectedItemPosition(I)V

    return-void
.end method

.method private refreshData(Ljava/util/List;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/editor/model/VideoEditorBaseLocalResource;",
            ">;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;->getLocalTemplateEntities(Landroid/content/Context;)Ljava/util/List;

    move-result-object v0

    if-eqz p1, :cond_0

    invoke-interface {v0, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    :cond_0
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    invoke-static {v0}, Lcom/miui/gallery/video/editor/manager/SmartEffectManager;->loadSmartEffects(Ljava/util/List;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->getInstance()Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$4;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$4;-><init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;Ljava/util/ArrayList;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->checkExpiredAsset(Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ICheckExpiredAssetListener;)V

    return-void
.end method

.method private updateSelectedItemPosition(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->setSelectedItemPosition(I)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->clearLastSelectedPostion()V

    :cond_0
    return-void
.end method


# virtual methods
.method public doApply()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    if-nez v0, :cond_0

    const-string v0, "SmartEffectFragment"

    const-string v1, "doApply: videoEditor is null."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v0, 0x0

    return v0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/VideoEditor;->saveEditState()V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->getSelectedItemPosition()I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSavedSelectedSmartEffectIndex:I

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->recordEventWithApply()V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->onExitMode()V

    const/4 v0, 0x1

    return v0
.end method

.method public doCancel()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    if-nez v0, :cond_0

    const-string v0, "SmartEffectFragment"

    const-string v1, "doCancel: videoEditor is null."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v0, 0x0

    return v0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/VideoEditor;->restoreEditState()V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$2;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$2;-><init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/VideoEditor;->apply(Lcom/miui/gallery/video/editor/VideoEditor$OnCompletedListener;)Z

    move-result v0

    return v0
.end method

.method public getCurrentEffect()Ljava/util/List;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->getSelectedItemPosition()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->getSmartEffect(I)Lcom/miui/gallery/video/editor/SmartEffect;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/SmartEffect;->getLabel()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/String;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    return-object v0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public getEffectId()I
    .locals 1

    const v0, 0x7f0903b7

    return v0
.end method

.method public isHighFrames()Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/VideoEditor;->getVideoFrames()I

    move-result v0

    const/16 v2, 0x64

    if-lt v0, v2, :cond_0

    const/4 v1, 0x1

    :cond_0
    return v1
.end method

.method public synthetic lambda$initListener$140$SmartEffectFragment(Landroid/view/View;)V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->doApply()Z

    return-void
.end method

.method public synthetic lambda$initListener$141$SmartEffectFragment(Landroid/view/View;)V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->doCancel()Z

    return-void
.end method

.method public loadResourceData()V
    .locals 3

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mHasLoadData:Z

    new-instance v0, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->getEffectId()I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;-><init>(ILcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;)V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$3;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$3;-><init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;->execute(Lcom/miui/gallery/net/base/ResponseListener;)V

    return-void
.end method

.method public notifyDateSetChanged(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->notifyItemChanged(ILjava/lang/Object;)V

    :cond_0
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/video/editor/ui/MenuFragment;->onCreate(Landroid/os/Bundle;)V

    new-instance p1, Lcom/miui/gallery/video/editor/manager/SmartEffectManager;

    invoke-direct {p1}, Lcom/miui/gallery/video/editor/manager/SmartEffectManager;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSmartEffectManager:Lcom/miui/gallery/video/editor/manager/SmartEffectManager;

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/SmartEffectManager;->initDataWithBgColor()Ljava/util/ArrayList;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mSmartEffects:Ljava/util/ArrayList;

    return-void
.end method

.method public onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    new-instance p1, Lcom/miui/gallery/video/editor/ui/menu/SmartEffectView;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p3

    invoke-direct {p1, p3}, Lcom/miui/gallery/video/editor/ui/menu/SmartEffectView;-><init>(Landroid/content/Context;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLayout:Landroid/view/View;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLayout:Landroid/view/View;

    const p3, 0x7f090092

    invoke-virtual {p1, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mCancelView:Landroid/view/View;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLayout:Landroid/view/View;

    const p3, 0x7f09023d

    invoke-virtual {p1, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mOkView:Landroid/view/View;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLayout:Landroid/view/View;

    const p3, 0x7f090366

    invoke-virtual {p1, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mTitleView:Landroid/widget/TextView;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mTitleView:Landroid/widget/TextView;

    iget-object p3, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mContext:Landroid/content/Context;

    invoke-virtual {p3}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p3

    const v0, 0x7f1008b8

    invoke-virtual {p3, v0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p1, p3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/VideoEditor;->getProjectTotalTime()I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoTotalTime:I

    new-instance p1, Lcom/miui/gallery/video/editor/factory/SmartEffectFactory;

    invoke-direct {p1}, Lcom/miui/gallery/video/editor/factory/SmartEffectFactory;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    new-instance p1, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    invoke-direct {p1, p2, p3, v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;-><init>(Landroid/content/Context;Landroid/app/FragmentManager;Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    new-instance p1, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;

    invoke-direct {p1}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLoadSmartEffectTemplateTask:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLayout:Landroid/view/View;

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->initRecyclerView(Landroid/view/View;)V

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->initListener()V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->loadResourceData()V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLayout:Landroid/view/View;

    return-object p1
.end method

.method public onDestroyView()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/video/editor/ui/MenuFragment;->onDestroyView()V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mToast:Landroid/widget/Toast;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/widget/Toast;->cancel()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;->setItemSelectChangeListener(Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$ItemSelectChangeListener;)V

    iput-object v1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/SmartEffectRecyclerViewAdapter;

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;->cancel()V

    iput-object v1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->cancel()V

    iput-object v1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    :cond_3
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLoadSmartEffectTemplateTask:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;

    if-eqz v0, :cond_4

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;->cancel()V

    iput-object v1, p0, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment;->mLoadSmartEffectTemplateTask:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask;

    :cond_4
    return-void
.end method
