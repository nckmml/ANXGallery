.class public abstract Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;
.super Lcom/miui/gallery/ui/BaseMediaFragment;
.source "SearchResultFragmentBase.java"

# interfaces
.implements Lcom/miui/gallery/search/core/display/RequestLoadMoreListener;


# instance fields
.field protected mFilterLoaderCallback:Landroid/app/LoaderManager$LoaderCallbacks;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/app/LoaderManager$LoaderCallbacks<",
            "Lcom/miui/gallery/search/core/result/SuggestionResult;",
            ">;"
        }
    .end annotation
.end field

.field protected mInFeedbackTaskMode:Z

.field private mLastInvalidTime:J

.field protected mQueryText:Ljava/lang/String;

.field protected mResultSectionQueryInfo:Lcom/miui/gallery/search/core/QueryInfo;

.field protected mSectionDataLoaderCallback:Landroid/app/LoaderManager$LoaderCallbacks;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/app/LoaderManager$LoaderCallbacks<",
            "Lcom/miui/gallery/search/core/result/SuggestionResult;",
            ">;"
        }
    .end annotation
.end field

.field protected mSectionDataResultHelper:Lcom/miui/gallery/search/resultpage/SearchResultHelper;

.field private mSectionProcessor:Lcom/miui/gallery/search/core/resultprocessor/SectionedResultProcessor;

.field protected mSectionsLoaderCallback:Landroid/app/LoaderManager$LoaderCallbacks;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/app/LoaderManager$LoaderCallbacks<",
            "Lcom/miui/gallery/search/core/result/SuggestionResult<",
            "Lcom/miui/gallery/search/core/suggestion/GroupedSuggestionCursor<",
            "Lcom/miui/gallery/search/core/suggestion/SuggestionSection;",
            ">;>;>;"
        }
    .end annotation
.end field

.field protected mStatusHandleHelper:Lcom/miui/gallery/search/StatusHandleHelper;

.field protected mSupportFeedback:Z


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/BaseMediaFragment;-><init>()V

    new-instance v0, Lcom/miui/gallery/search/StatusHandleHelper;

    invoke-direct {v0}, Lcom/miui/gallery/search/StatusHandleHelper;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mStatusHandleHelper:Lcom/miui/gallery/search/StatusHandleHelper;

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSupportFeedback:Z

    iput-boolean v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mInFeedbackTaskMode:Z

    new-instance v0, Lcom/miui/gallery/search/core/resultprocessor/SectionedResultProcessor;

    invoke-direct {v0}, Lcom/miui/gallery/search/core/resultprocessor/SectionedResultProcessor;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionProcessor:Lcom/miui/gallery/search/core/resultprocessor/SectionedResultProcessor;

    new-instance v0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase$1;-><init>(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)V

    iput-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionsLoaderCallback:Landroid/app/LoaderManager$LoaderCallbacks;

    new-instance v0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase$2;-><init>(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)V

    iput-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionDataLoaderCallback:Landroid/app/LoaderManager$LoaderCallbacks;

    new-instance v0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase$3;-><init>(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)V

    iput-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mFilterLoaderCallback:Landroid/app/LoaderManager$LoaderCallbacks;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;Landroid/os/Bundle;)Z
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->shouldUserPersistentResponse(Landroid/os/Bundle;)Z

    move-result p0

    return p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)Lcom/miui/gallery/activity/BaseActivity;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)Lcom/miui/gallery/search/core/resultprocessor/SectionedResultProcessor;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionProcessor:Lcom/miui/gallery/search/core/resultprocessor/SectionedResultProcessor;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)Lcom/miui/gallery/activity/BaseActivity;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mLastInvalidTime:J

    return-wide v0
.end method

.method static synthetic access$402(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;J)J
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mLastInvalidTime:J

    return-wide p1
.end method

.method static synthetic access$500(Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;)Lcom/miui/gallery/activity/BaseActivity;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    return-object p0
.end method

.method private shouldUserPersistentResponse(Landroid/os/Bundle;)Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->usePersistentResponse()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    const-string v0, "force_requery"

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result p1

    if-nez p1, :cond_1

    :cond_0
    const/4 v1, 0x1

    :cond_1
    return v1
.end method


# virtual methods
.method protected abstract changeFilterData(Lcom/miui/gallery/search/core/QueryInfo;Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;)V
.end method

.method protected abstract changeSuggestions(Lcom/miui/gallery/search/core/QueryInfo;Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;)V
.end method

.method protected abstract closeLoadMore()V
.end method

.method protected createSearchResultHelper(Lcom/miui/gallery/search/core/QueryInfo;Lcom/miui/gallery/search/core/suggestion/GroupedSuggestionCursor;)Lcom/miui/gallery/search/resultpage/SearchResultHelper;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/search/core/QueryInfo;",
            "Lcom/miui/gallery/search/core/suggestion/GroupedSuggestionCursor<",
            "Lcom/miui/gallery/search/core/suggestion/SuggestionSection;",
            ">;)",
            "Lcom/miui/gallery/search/resultpage/SearchResultHelper;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/search/resultpage/SearchResultHelper;

    invoke-direct {v0, p1, p2}, Lcom/miui/gallery/search/resultpage/SearchResultHelper;-><init>(Lcom/miui/gallery/search/core/QueryInfo;Lcom/miui/gallery/search/core/suggestion/GroupedSuggestionCursor;)V

    return-object v0
.end method

.method public doRetry()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mStatusHandleHelper:Lcom/miui/gallery/search/StatusHandleHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/search/StatusHandleHelper;->getResultStatus()I

    move-result v0

    invoke-static {v0}, Lcom/miui/gallery/search/SearchConstants;->isErrorStatus(I)Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->openLoadMore()V

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mStatusHandleHelper:Lcom/miui/gallery/search/StatusHandleHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/search/StatusHandleHelper;->refreshInfoViews()V

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionDataResultHelper:Lcom/miui/gallery/search/resultpage/SearchResultHelper;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    invoke-virtual {p0, v1}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->restartSectionLoader(Landroid/os/Bundle;)V

    goto :goto_1

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/search/resultpage/SearchResultHelper;->canLoadNextPage()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0, v1}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->restartSectionDataLoader(Landroid/os/Bundle;)V

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->closeLoadMore()V

    :goto_0
    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionDataResultHelper:Lcom/miui/gallery/search/resultpage/SearchResultHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/search/resultpage/SearchResultHelper;->needLoadFilterList()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-virtual {p0, v1}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->restartFilterLoader(Landroid/os/Bundle;)V

    :cond_2
    :goto_1
    return-void
.end method

.method protected getDefaultTitle()Ljava/lang/String;
    .locals 1

    const v0, 0x7f100764

    invoke-virtual {p0, v0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->getString(I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected abstract getErrorViewAdapter()Lcom/miui/gallery/search/StatusHandleHelper$AbstractErrorViewAdapter;
.end method

.method protected abstract getFrom()Ljava/lang/String;
.end method

.method protected abstract getLayoutResource()I
.end method

.method protected abstract getResultView()Landroid/view/View;
.end method

.method protected getSectionDataQueryInfoBuilder()Lcom/miui/gallery/search/core/QueryInfo$Builder;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionDataResultHelper:Lcom/miui/gallery/search/resultpage/SearchResultHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/search/resultpage/SearchResultHelper;->buildDataListQueryInfo()Lcom/miui/gallery/search/core/QueryInfo$Builder;

    move-result-object v0

    return-object v0
.end method

.method protected initResultQueryInfoBuilder(Landroid/net/Uri;)Lcom/miui/gallery/search/core/QueryInfo$Builder;
    .locals 5

    new-instance v0, Lcom/miui/gallery/search/core/QueryInfo$Builder;

    sget-object v1, Lcom/miui/gallery/search/SearchConstants$SearchType;->SEARCH_TYPE_RESULT:Lcom/miui/gallery/search/SearchConstants$SearchType;

    invoke-direct {v0, v1}, Lcom/miui/gallery/search/core/QueryInfo$Builder;-><init>(Lcom/miui/gallery/search/SearchConstants$SearchType;)V

    invoke-virtual {p1}, Landroid/net/Uri;->getQueryParameterNames()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    const-string v3, "title"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-virtual {p1, v2}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iput-object v2, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mQueryText:Ljava/lang/String;

    goto :goto_0

    :cond_0
    const-string v3, "inFeedbackTaskMode"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_1

    invoke-virtual {p1, v2, v4}, Landroid/net/Uri;->getBooleanQueryParameter(Ljava/lang/String;Z)Z

    move-result v2

    iput-boolean v2, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mInFeedbackTaskMode:Z

    goto :goto_0

    :cond_1
    const-string v3, "supportFeedback"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-virtual {p1, v2, v4}, Landroid/net/Uri;->getBooleanQueryParameter(Ljava/lang/String;Z)Z

    move-result v2

    iput-boolean v2, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSupportFeedback:Z

    goto :goto_0

    :cond_2
    invoke-virtual {p1, v2}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->addParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/search/core/QueryInfo$Builder;

    goto :goto_0

    :cond_3
    return-object v0
.end method

.method protected initStatusHandlerHelper(Landroid/view/View;)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mStatusHandleHelper:Lcom/miui/gallery/search/StatusHandleHelper;

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->getResultView()Landroid/view/View;

    move-result-object v1

    const v2, 0x7f0901ae

    invoke-virtual {p1, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v2

    const v3, 0x7f0901f0

    invoke-virtual {p1, v3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v3

    const v4, 0x7f090128

    invoke-virtual {p1, v4}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v4

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->getErrorViewAdapter()Lcom/miui/gallery/search/StatusHandleHelper$AbstractErrorViewAdapter;

    move-result-object v5

    invoke-virtual/range {v0 .. v5}, Lcom/miui/gallery/search/StatusHandleHelper;->init(Landroid/view/View;Landroid/view/View;Landroid/view/View;Landroid/view/View;Lcom/miui/gallery/search/StatusHandleHelper$ErrorViewAdapter;)V

    return-void
.end method

.method protected abstract moreThanOnePage()Z
.end method

.method public onActivityCreated(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/BaseMediaFragment;->onActivityCreated(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->getFrom()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/search/statistics/SearchStatUtils;->createNewSerial(Ljava/lang/String;)Ljava/lang/String;

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->restartSectionLoader(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->openLoadMore()V

    iget-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mStatusHandleHelper:Lcom/miui/gallery/search/StatusHandleHelper;

    invoke-virtual {p1}, Lcom/miui/gallery/search/StatusHandleHelper;->refreshInfoViews()V

    return-void
.end method

.method public onDestroy()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/ui/BaseMediaFragment;->onDestroy()V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->getFrom()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/search/statistics/SearchStatUtils;->onCompleteSerial(Ljava/lang/String;)Ljava/lang/String;

    return-void
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->getLayoutResource()I

    move-result v0

    const/4 v1, 0x0

    invoke-virtual {p1, v0, p2, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p2}, Lcom/miui/gallery/activity/BaseActivity;->getIntent()Landroid/content/Intent;

    move-result-object p2

    invoke-virtual {p2}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object p2

    invoke-virtual {p0, p2}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->initResultQueryInfoBuilder(Landroid/net/Uri;)Lcom/miui/gallery/search/core/QueryInfo$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->build()Lcom/miui/gallery/search/core/QueryInfo;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mResultSectionQueryInfo:Lcom/miui/gallery/search/core/QueryInfo;

    invoke-virtual {p0, p1, p3, p2}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->onInflateView(Landroid/view/View;Landroid/os/Bundle;Landroid/net/Uri;)V

    const-string p3, "title"

    invoke-virtual {p2, p3}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p2}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->updateTitle(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->initStatusHandlerHelper(Landroid/view/View;)V

    return-object p1
.end method

.method protected abstract onInflateView(Landroid/view/View;Landroid/os/Bundle;Landroid/net/Uri;)V
.end method

.method protected abstract onLoadComplete()V
.end method

.method public onLoadMoreRequested()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSectionDataResultHelper:Lcom/miui/gallery/search/resultpage/SearchResultHelper;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/search/resultpage/SearchResultHelper;->canLoadNextPage()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->restartSectionDataLoader(Landroid/os/Bundle;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->closeLoadMore()V

    :goto_0
    return-void
.end method

.method public onResume()V
    .locals 0

    invoke-super {p0}, Lcom/miui/gallery/ui/BaseMediaFragment;->onResume()V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->doRetry()V

    return-void
.end method

.method protected abstract openLoadMore()V
.end method

.method protected receiveResultUpdates()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected abstract restartFilterLoader(Landroid/os/Bundle;)V
.end method

.method protected abstract restartSectionDataLoader(Landroid/os/Bundle;)V
.end method

.method protected abstract restartSectionLoader(Landroid/os/Bundle;)V
.end method

.method protected supportFeedback()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mSupportFeedback:Z

    return v0
.end method

.method protected updateTitle(Ljava/lang/String;)V
    .locals 1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    invoke-virtual {v0, p1}, Lmiui/app/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object p1

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultFragmentBase;->getDefaultTitle()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Lmiui/app/ActionBar;->setTitle(Ljava/lang/CharSequence;)V

    :goto_0
    return-void
.end method

.method protected abstract usePersistentResponse()Z
.end method
