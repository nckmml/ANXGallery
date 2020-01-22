.class public Lcom/miui/gallery/video/editor/ui/AudioFragment;
.super Lcom/miui/gallery/video/editor/ui/MenuFragment;
.source "AudioFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/video/editor/ui/AudioFragment$MyAudioItemSelectChangeListener;
    }
.end annotation


# instance fields
.field private mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

.field private mAudios:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/video/editor/LocalAudio;",
            ">;"
        }
    .end annotation
.end field

.field private mBgSoundEnable:Z

.field private mCancelView:Landroid/view/View;

.field private mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

.field private mOkView:Landroid/view/View;

.field private mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

.field private mSavedSelectedAudioIndex:I

.field private mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

.field private mTitleView:Landroid/widget/TextView;

.field private mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

.field private mVoiceClicked:Z


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/ui/MenuFragment;-><init>()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAudios:Ljava/util/ArrayList;

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mBgSoundEnable:Z

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVoiceClicked:Z

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/video/editor/ui/AudioFragment;Ljava/util/List;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->refreshData(Ljava/util/List;)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAudios:Ljava/util/ArrayList;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/video/editor/ui/AudioFragment;I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->updateSelectedItemPosition(I)V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/video/editor/ui/AudioFragment;Lcom/miui/gallery/video/editor/LocalAudio;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->successProcess(Lcom/miui/gallery/video/editor/LocalAudio;)V

    return-void
.end method

.method static synthetic access$700(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    return-object p0
.end method

.method private initListener()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mOkView:Landroid/view/View;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$cvz7Wk3eaGjPBLlqLee8nf8IsdY;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$cvz7Wk3eaGjPBLlqLee8nf8IsdY;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mCancelView:Landroid/view/View;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$LNy6vH72B0OTqgSlwsb0SJDKOBw;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$LNy6vH72B0OTqgSlwsb0SJDKOBw;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/AudioFragment$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment$1;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->setDownloadTaskListener(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;)V

    return-void
.end method

.method private initRecyclerView(Landroid/view/View;)V
    .locals 8

    const v0, 0x7f0902a0

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    new-instance p1, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mContext:Landroid/content/Context;

    const/4 v1, 0x0

    invoke-direct {p1, v0, v1, v1}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    new-instance v0, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;-><init>(Landroid/content/Context;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->setSmoothScroller(Lcom/miui/gallery/ui/SmoothScrollerController;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mLinearLayoutManager:Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f0602d5

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f0602d0

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    new-instance v0, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$BlankDivider;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v1, v0

    move v2, v3

    invoke-direct/range {v1 .. v7}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$BlankDivider;-><init>(IIIIII)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;->addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    new-instance p1, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAudios:Ljava/util/ArrayList;

    invoke-direct {p1, v0, v1}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;-><init>(Landroid/content/Context;Ljava/util/List;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    new-instance v0, Lcom/miui/gallery/video/editor/ui/AudioFragment$MyAudioItemSelectChangeListener;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/video/editor/ui/AudioFragment$MyAudioItemSelectChangeListener;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;Lcom/miui/gallery/video/editor/ui/AudioFragment$1;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->setItemSelectChangeListener(Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$ItemSelectChangeListener;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSingleChoiceRecyclerView:Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView;

    invoke-static {p1}, Lcom/miui/gallery/widget/overscroll/HorizontalOverScrollBounceEffectDecorator;->setOverScrollEffect(Landroidx/recyclerview/widget/RecyclerView;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    iget v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSavedSelectedAudioIndex:I

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->setSelectedItemPosition(I)V

    return-void
.end method

.method private refreshData(Ljava/util/List;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/editor/model/VideoEditorBaseLocalResource;",
            ">;)V"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_0
    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_3

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/video/editor/model/VideoEditorBaseLocalResource;

    if-eqz v4, :cond_0

    iget-object v5, v4, Lcom/miui/gallery/video/editor/model/VideoEditorBaseLocalResource;->type:Ljava/lang/String;

    const-string v6, "ve_type_none"

    invoke-virtual {v6, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-interface {v0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    iget-object v5, v4, Lcom/miui/gallery/video/editor/model/VideoEditorBaseLocalResource;->type:Ljava/lang/String;

    const-string v6, "ve_type_extra"

    invoke-virtual {v6, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_2

    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_2
    iget-object v5, v4, Lcom/miui/gallery/video/editor/model/VideoEditorBaseLocalResource;->type:Ljava/lang/String;

    const-string v6, "ve_type_custom"

    invoke-virtual {v6, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_0

    invoke-interface {v2, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_3
    iget-object v3, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    iget-object v4, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mContext:Landroid/content/Context;

    invoke-virtual {v3, v4}, Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;->getLocalTemplateEntities(Landroid/content/Context;)Ljava/util/List;

    move-result-object v3

    if-eqz p1, :cond_4

    invoke-interface {v3, v0}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    invoke-interface {v3, v1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    invoke-interface {v3, v2}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    :cond_4
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    invoke-static {v0, v3}, Lcom/miui/gallery/video/editor/manager/AudioManager;->loadAudioData(Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;Ljava/util/List;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    new-instance v0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$t9U3EqYu6E6Qlpe0QAUWPqkOTXE;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$t9U3EqYu6E6Qlpe0QAUWPqkOTXE;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;Ljava/util/ArrayList;)V

    invoke-static {v0}, Lcom/miui/gallery/threadpool/ThreadManager;->runOnMainThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private successProcess(Lcom/miui/gallery/video/editor/LocalAudio;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/LocalAudio;->getSrcPath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/VideoEditor;->setBackgroundMusic(Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    new-instance v0, Lcom/miui/gallery/video/editor/ui/AudioFragment$5;

    invoke-direct {v0, p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment$5;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/VideoEditor;->apply(Lcom/miui/gallery/video/editor/VideoEditor$OnCompletedListener;)Z

    return-void
.end method

.method private updateAudioVoiceView(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mCallback:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IVideoEditorFragmentCallback;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mCallback:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IVideoEditorFragmentCallback;

    invoke-interface {v0, p1}, Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IVideoEditorFragmentCallback;->updateAudioVoiceView(Z)V

    :cond_0
    return-void
.end method

.method private updateSelectedItemPosition(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->setSelectedItemPosition(I)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->clearLastSelectedPostion()V

    :cond_0
    return-void
.end method


# virtual methods
.method public doApply()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    if-nez v0, :cond_0

    const-string v0, "AudioFragment"

    const-string v1, "doApply: videoEditor is null."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v0, 0x0

    return v0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->getSelectedItemPosition()I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mSavedSelectedAudioIndex:I

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/VideoEditor;->saveEditState()V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->recordEventWithApply()V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->onExitMode()V

    const/4 v0, 0x1

    return v0
.end method

.method public doCancel()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    if-nez v0, :cond_0

    const-string v0, "AudioFragment"

    const-string v1, "doCancel: videoEditor is null."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v0, 0x0

    return v0

    :cond_0
    iget-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVoiceClicked:Z

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mBgSoundEnable:Z

    xor-int/lit8 v0, v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mBgSoundEnable:Z

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/VideoEditor;->restoreEditState()V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/AudioFragment$3;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment$3;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/VideoEditor;->apply(Lcom/miui/gallery/video/editor/VideoEditor$OnCompletedListener;)Z

    move-result v0

    return v0
.end method

.method public getCurrentEffect()Ljava/util/List;
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return-object v1

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->getSelectedItemPosition()I

    move-result v2

    invoke-virtual {v0, v2}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->getAudio(I)Lcom/miui/gallery/video/editor/LocalAudio;

    move-result-object v0

    if-nez v0, :cond_1

    return-object v1

    :cond_1
    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/LocalAudio;->isCustom()Z

    move-result v2

    const-string v3, "source"

    const-string v4, "mute"

    const/4 v5, 0x1

    const/4 v6, 0x0

    const/4 v7, 0x2

    if-eqz v2, :cond_3

    new-array v0, v7, [Ljava/lang/String;

    const-string v1, "custom"

    aput-object v1, v0, v6

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v1}, Lcom/miui/gallery/video/editor/VideoEditor;->isSourceAudioEnable()Z

    move-result v1

    if-eqz v1, :cond_2

    goto :goto_0

    :cond_2
    move-object v3, v4

    :goto_0
    aput-object v3, v0, v5

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    return-object v0

    :cond_3
    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/LocalAudio;->isExtra()Z

    move-result v2

    if-eqz v2, :cond_5

    new-array v1, v7, [Ljava/lang/String;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/LocalAudio;->getLabel()Ljava/lang/String;

    move-result-object v0

    aput-object v0, v1, v6

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/VideoEditor;->isSourceAudioEnable()Z

    move-result v0

    if-eqz v0, :cond_4

    goto :goto_1

    :cond_4
    move-object v3, v4

    :goto_1
    aput-object v3, v1, v5

    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    return-object v0

    :cond_5
    return-object v1
.end method

.method public getEffectId()I
    .locals 1

    const v0, 0x7f0903ab

    return v0
.end method

.method public synthetic lambda$initListener$129$AudioFragment(Landroid/view/View;)V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->doApply()Z

    return-void
.end method

.method public synthetic lambda$initListener$130$AudioFragment(Landroid/view/View;)V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->doCancel()Z

    return-void
.end method

.method public synthetic lambda$refreshData$134$AudioFragment(Ljava/util/ArrayList;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAudios:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAudios:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->notifyDataSetChanged()V

    :cond_0
    return-void
.end method

.method public synthetic lambda$updateVoiceState$131$AudioFragment()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/VideoEditor;->play()V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->updatePlayBtnView()V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->recordEventWithEffectChanged()V

    return-void
.end method

.method public loadResourceData()V
    .locals 3

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mHasLoadData:Z

    new-instance v0, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->getEffectId()I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;-><init>(ILcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;)V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    new-instance v1, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;

    invoke-direct {v1, p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;->execute(Lcom/miui/gallery/net/base/ResponseListener;)V

    return-void
.end method

.method public notifyDateSetChanged(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->notifyItemChanged(ILjava/lang/Object;)V

    :cond_0
    return-void
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 1

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/video/editor/ui/MenuFragment;->onActivityResult(IILandroid/content/Intent;)V

    const/16 v0, 0x3e8

    if-ne p1, v0, :cond_2

    const/4 p1, -0x1

    if-ne p2, p1, :cond_2

    invoke-virtual {p3}, Landroid/content/Intent;->getClipData()Landroid/content/ClipData;

    move-result-object p1

    if-nez p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-virtual {p3}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/miui/gallery/util/ConvertFilepathUtil;->getPath(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroid/content/ClipData;->getItemAt(I)Landroid/content/ClipData$Item;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/ClipData$Item;->getUri()Landroid/net/Uri;

    move-result-object p1

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-static {p2, p1}, Lcom/miui/gallery/util/ConvertFilepathUtil;->getPath(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object p1

    :goto_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p2

    if-nez p2, :cond_2

    invoke-static {p1}, Lcom/nexstreaming/nexeditorsdk/nexClip;->getSupportedClip(Ljava/lang/String;)Lcom/nexstreaming/nexeditorsdk/nexClip;

    move-result-object p2

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    sget-object p3, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->FILE:Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    invoke-virtual {p3, p1}, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->wrap(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Lcom/miui/gallery/video/editor/VideoEditor;->setBackgroundMusic(Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    new-instance p2, Lcom/miui/gallery/video/editor/ui/AudioFragment$4;

    invoke-direct {p2, p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment$4;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {p1, p2}, Lcom/miui/gallery/video/editor/VideoEditor;->apply(Lcom/miui/gallery/video/editor/VideoEditor$OnCompletedListener;)Z

    goto :goto_1

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    const p2, 0x7f1008e7

    invoke-static {p1, p2}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    :cond_2
    :goto_1
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/video/editor/ui/MenuFragment;->onCreate(Landroid/os/Bundle;)V

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/AudioManager;->initDataWithBgColor()Ljava/util/ArrayList;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAudios:Ljava/util/ArrayList;

    return-void
.end method

.method public onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 2

    new-instance p1, Lcom/miui/gallery/video/editor/ui/menu/AudioView;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p3

    invoke-direct {p1, p3}, Lcom/miui/gallery/video/editor/ui/menu/AudioView;-><init>(Landroid/content/Context;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->initRecyclerView(Landroid/view/View;)V

    const p3, 0x7f090092

    invoke-virtual {p1, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p3

    iput-object p3, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mCancelView:Landroid/view/View;

    const p3, 0x7f09023d

    invoke-virtual {p1, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p3

    iput-object p3, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mOkView:Landroid/view/View;

    const p3, 0x7f090366

    invoke-virtual {p1, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p3

    check-cast p3, Landroid/widget/TextView;

    iput-object p3, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mTitleView:Landroid/widget/TextView;

    iget-object p3, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mTitleView:Landroid/widget/TextView;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f10088a

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p3, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    new-instance p3, Lcom/miui/gallery/video/editor/factory/AudioFactory;

    invoke-direct {p3}, Lcom/miui/gallery/video/editor/factory/AudioFactory;-><init>()V

    iput-object p3, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    new-instance p3, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    invoke-direct {p3, p2, v0, v1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;-><init>(Landroid/content/Context;Landroid/app/FragmentManager;Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;)V

    iput-object p3, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    iget-boolean p2, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mBgSoundEnable:Z

    invoke-direct {p0, p2}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->updateAudioVoiceView(Z)V

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->initListener()V

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->loadResourceData()V

    return-object p1
.end method

.method public onDestroyView()V
    .locals 3

    invoke-super {p0}, Lcom/miui/gallery/video/editor/ui/MenuFragment;->onDestroyView()V

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->updateAudioVoiceView(Z)V

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    invoke-virtual {v1, v2}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->setItemSelectChangeListener(Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$ItemSelectChangeListener;)V

    iput-object v2, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mAdapter:Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditorResourceRequest:Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lcom/miui/gallery/video/editor/net/VideoEditorResourceRequest;->cancel()V

    :cond_1
    iput-boolean v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVoiceClicked:Z

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->cancel()V

    iput-object v2, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mResourceDownloadManager:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;

    :cond_2
    return-void
.end method

.method public updateVoiceState(Z)V
    .locals 1

    iput-boolean p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mBgSoundEnable:Z

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/VideoEditor;->setSourceAudioEnable(Ljava/lang/Boolean;)V

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVoiceClicked:Z

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mVideoEditor:Lcom/miui/gallery/video/editor/VideoEditor;

    new-instance v0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$j8e7XfdW9i5FCZ8_pEOARo4s4NA;

    invoke-direct {v0, p0}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$j8e7XfdW9i5FCZ8_pEOARo4s4NA;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/VideoEditor;->apply(Lcom/miui/gallery/video/editor/VideoEditor$OnCompletedListener;)Z

    return-void
.end method
