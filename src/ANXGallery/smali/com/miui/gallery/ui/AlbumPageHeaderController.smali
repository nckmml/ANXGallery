.class public Lcom/miui/gallery/ui/AlbumPageHeaderController;
.super Ljava/lang/Object;
.source "AlbumPageHeaderController.java"

# interfaces
.implements Landroid/app/LoaderManager$LoaderCallbacks;
.implements Landroidx/lifecycle/DefaultLifecycleObserver;


# instance fields
.field private mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

.field private mAlbumCoverNum:I

.field private mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

.field private mFragment:Lcom/miui/gallery/app/Fragment;

.field private mIsManualLoad:Landroid/util/SparseBooleanArray;

.field private mIsPeopleAlbumSnapshotValid:Z

.field private mLoaderCreateTime:Landroid/util/SparseLongArray;

.field private mLocationsAlbumCoverServerIds:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private mLocationsSubject:Lio/reactivex/subjects/PublishSubject;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/reactivex/subjects/PublishSubject<",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;>;"
        }
    .end annotation
.end field

.field private mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

.field private mSearchStatus:I

.field private mTagsAlbumCoverServerIds:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private mTagsSubject:Lio/reactivex/subjects/PublishSubject;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/reactivex/subjects/PublishSubject<",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/miui/gallery/app/Fragment;Landroid/view/ViewGroup;)V
    .locals 4

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mSearchStatus:I

    new-instance v1, Lio/reactivex/disposables/CompositeDisposable;

    invoke-direct {v1}, Lio/reactivex/disposables/CompositeDisposable;-><init>()V

    iput-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

    new-instance v1, Landroid/util/SparseLongArray;

    invoke-direct {v1}, Landroid/util/SparseLongArray;-><init>()V

    iput-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLoaderCreateTime:Landroid/util/SparseLongArray;

    new-instance v1, Landroid/util/SparseBooleanArray;

    invoke-direct {v1}, Landroid/util/SparseBooleanArray;-><init>()V

    iput-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsManualLoad:Landroid/util/SparseBooleanArray;

    const/4 v1, 0x0

    iput-boolean v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsPeopleAlbumSnapshotValid:Z

    iput-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/app/Fragment;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/app/Fragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    iget-object v2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {v2}, Lcom/miui/gallery/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-static {v2}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v2

    const v3, 0x7f0b0034

    invoke-virtual {v2, v3, p2, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroidx/recyclerview/widget/RecyclerView;

    iput-object p2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    new-instance p2, Landroidx/recyclerview/widget/GridLayoutManager;

    iget-object v2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {v2}, Lcom/miui/gallery/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    const v3, 0x7f0a0009

    invoke-virtual {p1, v3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v3

    invoke-direct {p2, v2, v3}, Landroidx/recyclerview/widget/GridLayoutManager;-><init>(Landroid/content/Context;I)V

    iget-object v2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    invoke-virtual {v2, p2}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    const p2, 0x7f0603a1

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iget-object p2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    new-instance v2, Lcom/miui/gallery/widget/recyclerview/GridItemSpacingDecoration;

    invoke-direct {v2, p2, v1, p1, v1}, Lcom/miui/gallery/widget/recyclerview/GridItemSpacingDecoration;-><init>(Landroidx/recyclerview/widget/RecyclerView;ZII)V

    invoke-virtual {p2, v2}, Landroidx/recyclerview/widget/RecyclerView;->addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroidx/recyclerview/widget/RecyclerView;->setItemAnimator(Landroidx/recyclerview/widget/RecyclerView$ItemAnimator;)V

    new-instance p1, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    iget-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {v1}, Lcom/miui/gallery/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {p1, v1}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;-><init>(Landroid/content/Context;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    const/4 v1, 0x1

    invoke-virtual {p1, v1}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;->setHasStableIds(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/app/Fragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v1, 0x7f0a000a

    invoke-virtual {p1, v1}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAlbumCoverNum:I

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsAlbumCoverServerIds:Ljava/util/ArrayList;

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-static {}, Lio/reactivex/subjects/PublishSubject;->create()Lio/reactivex/subjects/PublishSubject;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsSubject:Lio/reactivex/subjects/PublishSubject;

    invoke-static {}, Lio/reactivex/subjects/PublishSubject;->create()Lio/reactivex/subjects/PublishSubject;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsSubject:Lio/reactivex/subjects/PublishSubject;

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsSubject:Lio/reactivex/subjects/PublishSubject;

    const/4 v1, -0x3

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->subscribeSubject(Lio/reactivex/subjects/PublishSubject;I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsSubject:Lio/reactivex/subjects/PublishSubject;

    const/4 v1, -0x4

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->subscribeSubject(Lio/reactivex/subjects/PublishSubject;I)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->setSearchBackedAlbumCoversByPreferences()V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    iget-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    invoke-virtual {p1, v1}, Landroidx/recyclerview/widget/RecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object p1

    invoke-virtual {p1, v0, p2, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    return-void
.end method

.method private buildQueryLoaderByType(Lcom/miui/gallery/search/SearchConstants$SectionType;)Lcom/miui/gallery/search/core/query/QueryLoader;
    .locals 3

    new-instance v0, Lcom/miui/gallery/search/core/QueryInfo$Builder;

    sget-object v1, Lcom/miui/gallery/search/SearchConstants$SearchType;->SEARCH_TYPE_RESULT_LIST:Lcom/miui/gallery/search/SearchConstants$SearchType;

    invoke-direct {v0, v1}, Lcom/miui/gallery/search/core/QueryInfo$Builder;-><init>(Lcom/miui/gallery/search/SearchConstants$SearchType;)V

    invoke-virtual {p1}, Lcom/miui/gallery/search/SearchConstants$SectionType;->getName()Ljava/lang/String;

    move-result-object p1

    const-string v1, "type"

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->addParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/search/core/QueryInfo$Builder;

    const-string p1, "pos"

    const-string v1, "0"

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->addParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/search/core/QueryInfo$Builder;

    iget p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAlbumCoverNum:I

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    const-string v1, "num"

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->addParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/search/core/QueryInfo$Builder;

    const/4 p1, 0x1

    invoke-static {p1}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v1

    const-string v2, "secureMode"

    invoke-virtual {v0, v2, v1}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->addParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/search/core/QueryInfo$Builder;

    invoke-static {p1}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object p1

    const-string v1, "use_persistent_response"

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->addParam(Ljava/lang/String;Ljava/lang/String;)Lcom/miui/gallery/search/core/QueryInfo$Builder;

    new-instance p1, Lcom/miui/gallery/search/core/query/QueryLoader;

    iget-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {v1}, Lcom/miui/gallery/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-virtual {v0}, Lcom/miui/gallery/search/core/QueryInfo$Builder;->build()Lcom/miui/gallery/search/core/QueryInfo;

    move-result-object v0

    new-instance v2, Lcom/miui/gallery/search/resultpage/DataListResultProcessor;

    invoke-direct {v2}, Lcom/miui/gallery/search/resultpage/DataListResultProcessor;-><init>()V

    invoke-direct {p1, v1, v0, v2}, Lcom/miui/gallery/search/core/query/QueryLoader;-><init>(Landroid/content/Context;Lcom/miui/gallery/search/core/QueryInfo;Lcom/miui/gallery/search/core/resultprocessor/ResultProcessor;)V

    return-object p1
.end method

.method private getLoaderManager()Landroid/app/LoaderManager;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {v0}, Lcom/miui/gallery/app/Fragment;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    return-object v0
.end method

.method private isAlbumCoverValid(Landroid/content/Context;J)Z
    .locals 8

    sget-object v1, Lcom/miui/gallery/provider/GalleryContract$Cloud;->CLOUD_URI:Landroid/net/Uri;

    const-string v0, "count(*)"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v2

    const/4 v7, 0x1

    new-array v4, v7, [Ljava/lang/String;

    invoke-static {p2, p3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const/4 p3, 0x0

    aput-object p2, v4, p3

    sget-object v6, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$0Nm4CXZjLxeRBnOv1blum---X5M;->INSTANCE:Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$0Nm4CXZjLxeRBnOv1blum---X5M;

    const-string v3, "serverId = ? AND ((localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\'))) AND ((localGroupId!=-1000))"

    const/4 v5, 0x0

    move-object v0, p1

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Boolean;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    move p3, v7

    :cond_0
    return p3
.end method

.method static synthetic lambda$isAlbumCoverValid$36(Landroid/database/Cursor;)Ljava/lang/Boolean;
    .locals 2

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    invoke-interface {p0}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p0, v0}, Landroid/database/Cursor;->getInt(I)I

    move-result p0

    if-lez p0, :cond_0

    const/4 v0, 0x1

    :cond_0
    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method static synthetic lambda$null$34(Lio/reactivex/CompletableObserver;)V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/util/StaticContext;->sGetAndroidContext()Landroid/content/Context;

    move-result-object p0

    invoke-static {p0}, Lcom/miui/gallery/provider/PeopleFaceSnapshotHelper;->queryAndPersist(Landroid/content/Context;)V

    return-void
.end method

.method static synthetic lambda$null$38(Ljava/lang/Boolean;)Z
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    return p0
.end method

.method static synthetic lambda$null$39(I)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const-string v0, "AlbumPageHeaderController"

    const-string v1, "doOnCancel for [%d]"

    invoke-static {v0, v1, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method static synthetic lambda$null$40(Ljava/lang/Boolean;Ljava/lang/Boolean;)Ljava/lang/Boolean;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method static synthetic lambda$takeSnapshot4PeopleAlbum$35()Lio/reactivex/CompletableSource;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$Hri9neUSl2sF2xPFeC1-n3zwsMM;->INSTANCE:Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$Hri9neUSl2sF2xPFeC1-n3zwsMM;

    return-object v0
.end method

.method private loaderId2Name(I)Ljava/lang/String;
    .locals 1

    const/4 v0, -0x5

    if-eq p1, v0, :cond_4

    const/4 v0, -0x4

    if-eq p1, v0, :cond_3

    const/4 v0, -0x3

    if-eq p1, v0, :cond_2

    const/4 v0, -0x2

    if-eq p1, v0, :cond_1

    const/4 v0, -0x1

    if-eq p1, v0, :cond_0

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_0
    const-string p1, "people_album_cover_snapshot"

    return-object p1

    :cond_1
    const-string p1, "people_album_cover"

    return-object p1

    :cond_2
    const-string p1, "locations_album_cover"

    return-object p1

    :cond_3
    const-string p1, "tags_album_cover"

    return-object p1

    :cond_4
    const-string p1, "search_status"

    return-object p1
.end method

.method private parseAlbumCoverServerId(Ljava/lang/String;)J
    .locals 2

    :try_start_0
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    const-string v1, "serverId"

    invoke-virtual {v0, v1}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-wide v0

    :catch_0
    const-string v0, "AlbumPageHeaderController"

    const-string v1, "Invalid album cover Uri: %s"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const-wide/16 v0, -0x1

    return-wide v0
.end method

.method private parsePeopleCoversFromSnapshot(Landroid/database/Cursor;)Ljava/util/ArrayList;
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/database/Cursor;",
            ")",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/model/FaceAlbumCover;",
            ">;"
        }
    .end annotation

    if-eqz p1, :cond_5

    invoke-interface {p1}, Landroid/database/Cursor;->isClosed()Z

    move-result v0

    if-nez v0, :cond_5

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result v0

    if-gtz v0, :cond_0

    goto/16 :goto_2

    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-interface {p1}, Landroid/database/Cursor;->moveToFirst()Z

    :goto_0
    invoke-interface {p1}, Landroid/database/Cursor;->isAfterLast()Z

    move-result v1

    if-nez v1, :cond_4

    invoke-static {p1}, Lcom/miui/gallery/util/face/PeopleItem;->fromCursor(Landroid/database/Cursor;)Lcom/miui/gallery/util/face/PeopleItem;

    move-result-object v1

    if-eqz v1, :cond_3

    new-instance v2, Lcom/miui/gallery/model/FaceAlbumCover;

    invoke-direct {v2}, Lcom/miui/gallery/model/FaceAlbumCover;-><init>()V

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getCloudId()J

    move-result-wide v3

    iput-wide v3, v2, Lcom/miui/gallery/model/FaceAlbumCover;->coverId:J

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getThumbFile()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_1

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getThumbFile()Ljava/lang/String;

    move-result-object v3

    goto :goto_1

    :cond_1
    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getLocalFile()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_2

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getLocalFile()Ljava/lang/String;

    move-result-object v3

    goto :goto_1

    :cond_2
    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getMicroThumbFile()Ljava/lang/String;

    move-result-object v3

    :goto_1
    iput-object v3, v2, Lcom/miui/gallery/model/FaceAlbumCover;->coverPath:Ljava/lang/String;

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getSha1()Ljava/lang/String;

    move-result-object v3

    iput-object v3, v2, Lcom/miui/gallery/model/FaceAlbumCover;->coverSha1:Ljava/lang/String;

    const/4 v3, 0x0

    iput v3, v2, Lcom/miui/gallery/model/FaceAlbumCover;->coverSyncState:I

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getSize()J

    move-result-wide v3

    iput-wide v3, v2, Lcom/miui/gallery/model/FaceAlbumCover;->coverSize:J

    new-instance v3, Lcom/miui/gallery/util/face/FaceRegionRectF;

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getFaceXScale()F

    move-result v6

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getFaceYScale()F

    move-result v7

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getFaceXScale()F

    move-result v4

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getFaceWScale()F

    move-result v5

    add-float v8, v4, v5

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getFaceYScale()F

    move-result v4

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getFaceHScale()F

    move-result v5

    add-float v9, v4, v5

    invoke-virtual {v1}, Lcom/miui/gallery/util/face/PeopleItem;->getExifOrientation()I

    move-result v10

    move-object v5, v3

    invoke-direct/range {v5 .. v10}, Lcom/miui/gallery/util/face/FaceRegionRectF;-><init>(FFFFI)V

    iput-object v3, v2, Lcom/miui/gallery/model/FaceAlbumCover;->faceRectF:Lcom/miui/gallery/util/face/FaceRegionRectF;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_3
    invoke-interface {p1}, Landroid/database/Cursor;->moveToNext()Z

    goto :goto_0

    :cond_4
    return-object v0

    :cond_5
    :goto_2
    const/4 p1, 0x0

    return-object p1
.end method

.method private setSearchBackedAlbumCoversByPreferences()V
    .locals 3

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$SearchBackedAlbum;->getLocationsAlbumServerIds()Ljava/util/List;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    const/4 v2, 0x1

    invoke-static {v0}, Lcom/google/common/collect/Lists;->newArrayList(Ljava/lang/Iterable;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v1, v2, v0}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;->setAlbumCover(ILjava/util/ArrayList;)V

    :cond_0
    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$SearchBackedAlbum;->getTagsAlbumServerIds()Ljava/util/List;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    const/4 v2, 0x2

    invoke-static {v0}, Lcom/google/common/collect/Lists;->newArrayList(Ljava/lang/Iterable;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v1, v2, v0}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;->setAlbumCover(ILjava/util/ArrayList;)V

    :cond_1
    return-void
.end method

.method private startDeferredLoad()V
    .locals 3

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v1, 0x0

    const/4 v2, -0x2

    invoke-virtual {v0, v2, v1, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v2, -0x3

    invoke-virtual {v0, v2, v1, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v2, -0x4

    invoke-virtual {v0, v2, v1, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v2, -0x5

    invoke-virtual {v0, v2, v1, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Landroid/app/LoaderManager;->destroyLoader(I)V

    return-void
.end method

.method private statAlbumLoadTime(Ljava/lang/String;JI)V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const-string v1, "loader"

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {p2, p3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    const-string p2, "costs"

    invoke-interface {v0, p2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {p4}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    const-string p2, "count"

    invoke-interface {v0, p2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "album_page_header"

    const-string p2, "classify_album_cover_load_time"

    invoke-static {p1, p2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private subscribeSubject(Lio/reactivex/subjects/PublishSubject;I)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lio/reactivex/subjects/PublishSubject<",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;>;I)V"
        }
    .end annotation

    invoke-static {}, Lcom/miui/gallery/threadpool/GallerySchedulers;->misc()Lio/reactivex/Scheduler;

    move-result-object v0

    invoke-virtual {p1, v0}, Lio/reactivex/subjects/PublishSubject;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$WIai7YG_Tqb4mapk9s31_HWsMWY;

    invoke-direct {v0, p0, p2}, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$WIai7YG_Tqb4mapk9s31_HWsMWY;-><init>(Lcom/miui/gallery/ui/AlbumPageHeaderController;I)V

    invoke-virtual {p1, v0}, Lio/reactivex/Observable;->switchMap(Lio/reactivex/functions/Function;)Lio/reactivex/Observable;

    move-result-object p1

    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object v0

    invoke-virtual {p1, v0}, Lio/reactivex/Observable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$i4x-erwI1DqQYTbi4bY0u00Wcnc;

    invoke-direct {v0, p0, p2}, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$i4x-erwI1DqQYTbi4bY0u00Wcnc;-><init>(Lcom/miui/gallery/ui/AlbumPageHeaderController;I)V

    invoke-virtual {p1, v0}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

    invoke-virtual {p2, p1}, Lio/reactivex/disposables/CompositeDisposable;->add(Lio/reactivex/disposables/Disposable;)Z

    return-void
.end method

.method private takeSnapshot4PeopleAlbum()V
    .locals 5

    sget-object v0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$sObEeRl8NQ-a8h9Kl2nOLHkZOZA;->INSTANCE:Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$sObEeRl8NQ-a8h9Kl2nOLHkZOZA;

    invoke-static {v0}, Lio/reactivex/Completable;->defer(Ljava/util/concurrent/Callable;)Lio/reactivex/Completable;

    move-result-object v0

    sget-object v1, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-static {}, Lcom/miui/gallery/threadpool/GallerySchedulers;->misc()Lio/reactivex/Scheduler;

    move-result-object v2

    const-wide/16 v3, 0x5dc

    invoke-virtual {v0, v3, v4, v1, v2}, Lio/reactivex/Completable;->delaySubscription(JLjava/util/concurrent/TimeUnit;Lio/reactivex/Scheduler;)Lio/reactivex/Completable;

    move-result-object v0

    invoke-virtual {v0}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    return-void
.end method

.method private updateSearchStatus(I)V
    .locals 3

    iget v0, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mSearchStatus:I

    if-ne p1, v0, :cond_0

    return-void

    :cond_0
    invoke-static {v0}, Lcom/miui/gallery/search/SearchConstants;->isErrorStatus(I)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p1}, Lcom/miui/gallery/search/SearchConstants;->isErrorStatus(I)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v1, -0x3

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2, p0}, Landroid/app/LoaderManager;->restartLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v0

    const/4 v1, -0x4

    invoke-virtual {v0, v1, v2, p0}, Landroid/app/LoaderManager;->restartLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    :cond_1
    iput p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mSearchStatus:I

    return-void
.end method


# virtual methods
.method public getView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mRecyclerView:Landroidx/recyclerview/widget/RecyclerView;

    return-object v0
.end method

.method public synthetic lambda$null$37$AlbumPageHeaderController(Ljava/lang/Long;)Ljava/lang/Boolean;
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v1

    invoke-direct {p0, v0, v1, v2}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->isAlbumCoverValid(Landroid/content/Context;J)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method

.method public synthetic lambda$subscribeSubject$41$AlbumPageHeaderController(ILjava/util/ArrayList;)Lio/reactivex/ObservableSource;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {p2}, Lio/reactivex/Flowable;->fromIterable(Ljava/lang/Iterable;)Lio/reactivex/Flowable;

    move-result-object p2

    new-instance v0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$xlePm470TCExuRmZuYeewJYU2t4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$xlePm470TCExuRmZuYeewJYU2t4;-><init>(Lcom/miui/gallery/ui/AlbumPageHeaderController;)V

    invoke-virtual {p2, v0}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object p2

    sget-object v0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$_RSrnJ_oaChU5DdzrX3HGt9IVLI;->INSTANCE:Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$_RSrnJ_oaChU5DdzrX3HGt9IVLI;

    invoke-virtual {p2, v0}, Lio/reactivex/Flowable;->takeUntil(Lio/reactivex/functions/Predicate;)Lio/reactivex/Flowable;

    move-result-object p2

    new-instance v0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$g9QlI45a2T1oiqODcaNQD258lQ8;

    invoke-direct {v0, p1}, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$g9QlI45a2T1oiqODcaNQD258lQ8;-><init>(I)V

    invoke-virtual {p2, v0}, Lio/reactivex/Flowable;->doOnCancel(Lio/reactivex/functions/Action;)Lio/reactivex/Flowable;

    move-result-object p1

    sget-object p2, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$oKjDg6ZdkrYrHksdgAHcwhVpI6g;->INSTANCE:Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$oKjDg6ZdkrYrHksdgAHcwhVpI6g;

    invoke-virtual {p1, p2}, Lio/reactivex/Flowable;->reduce(Lio/reactivex/functions/BiFunction;)Lio/reactivex/Maybe;

    move-result-object p1

    invoke-virtual {p1}, Lio/reactivex/Maybe;->toObservable()Lio/reactivex/Observable;

    move-result-object p1

    return-object p1
.end method

.method public synthetic lambda$subscribeSubject$42$AlbumPageHeaderController(ILjava/lang/Boolean;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    xor-int/lit8 v1, v1, 0x1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    const-string v2, "AlbumPageHeaderController"

    const-string v3, "loader [%d] need restart [%b]"

    invoke-static {v2, v3, v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-nez p2, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object p2

    const/4 v0, 0x0

    invoke-virtual {p2, p1, v0, p0}, Landroid/app/LoaderManager;->restartLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    :cond_0
    return-void
.end method

.method public onCreateLoader(ILandroid/os/Bundle;)Landroid/content/Loader;
    .locals 2

    iget-object p2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsManualLoad:Landroid/util/SparseBooleanArray;

    const/4 v0, 0x1

    invoke-virtual {p2, p1, v0}, Landroid/util/SparseBooleanArray;->put(IZ)V

    iget-object p2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLoaderCreateTime:Landroid/util/SparseLongArray;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-virtual {p2, p1, v0, v1}, Landroid/util/SparseLongArray;->put(IJ)V

    const/4 p2, -0x5

    const/4 v0, 0x0

    if-eq p1, p2, :cond_4

    const/4 p2, -0x4

    if-eq p1, p2, :cond_3

    const/4 p2, -0x3

    if-eq p1, p2, :cond_2

    const/4 p2, -0x2

    if-eq p1, p2, :cond_1

    const/4 p2, -0x1

    if-eq p1, p2, :cond_0

    goto :goto_0

    :cond_0
    new-instance v0, Landroid/content/CursorLoader;

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-direct {v0, p1}, Landroid/content/CursorLoader;-><init>(Landroid/content/Context;)V

    move-object p1, v0

    check-cast p1, Landroid/content/CursorLoader;

    sget-object p2, Lcom/miui/gallery/provider/GalleryContract$PeopleFace;->PEOPLE_SNAPSHOT_URI:Landroid/net/Uri;

    iget v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAlbumCoverNum:I

    invoke-static {p2, v1}, Lcom/miui/gallery/util/UriUtil;->appendLimit(Landroid/net/Uri;I)Landroid/net/Uri;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setUri(Landroid/net/Uri;)V

    sget-object p2, Lcom/miui/gallery/util/face/PeopleItem;->COMPAT_PROJECTION:[Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setProjection([Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    new-instance v0, Landroid/content/CursorLoader;

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-direct {v0, p1}, Landroid/content/CursorLoader;-><init>(Landroid/content/Context;)V

    move-object p1, v0

    check-cast p1, Landroid/content/CursorLoader;

    sget-object p2, Lcom/miui/gallery/provider/GalleryContract$PeopleFace;->PEOPLE_FACE_COVER_URI:Landroid/net/Uri;

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setUri(Landroid/net/Uri;)V

    goto :goto_0

    :cond_2
    sget-object p1, Lcom/miui/gallery/search/SearchConstants$SectionType;->SECTION_TYPE_LOCATION_LIST:Lcom/miui/gallery/search/SearchConstants$SectionType;

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->buildQueryLoaderByType(Lcom/miui/gallery/search/SearchConstants$SectionType;)Lcom/miui/gallery/search/core/query/QueryLoader;

    move-result-object v0

    goto :goto_0

    :cond_3
    sget-object p1, Lcom/miui/gallery/search/SearchConstants$SectionType;->SECTION_TYPE_TAG_LIST:Lcom/miui/gallery/search/SearchConstants$SectionType;

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->buildQueryLoaderByType(Lcom/miui/gallery/search/SearchConstants$SectionType;)Lcom/miui/gallery/search/core/query/QueryLoader;

    move-result-object v0

    goto :goto_0

    :cond_4
    new-instance p1, Lcom/miui/gallery/search/SearchStatusLoader;

    iget-object p2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {p2}, Lcom/miui/gallery/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-direct {p1, p2, v0}, Lcom/miui/gallery/search/SearchStatusLoader;-><init>(Landroid/content/Context;Lcom/miui/gallery/search/SearchStatusLoader$StatusReportDelegate;)V

    move-object v0, p1

    :goto_0
    return-object v0
.end method

.method public onDestroy(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mFragment:Lcom/miui/gallery/app/Fragment;

    invoke-virtual {p1}, Lcom/miui/gallery/app/Fragment;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroidx/lifecycle/Lifecycle;->removeObserver(Landroidx/lifecycle/LifecycleObserver;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mCompositeDisposable:Lio/reactivex/disposables/CompositeDisposable;

    invoke-virtual {p1}, Lio/reactivex/disposables/CompositeDisposable;->dispose()V

    return-void
.end method

.method public onLoadFinished(Landroid/content/Loader;Ljava/lang/Object;)V
    .locals 9

    invoke-virtual {p1}, Landroid/content/Loader;->getId()I

    move-result v0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-virtual {p1}, Landroid/content/Loader;->getId()I

    move-result p1

    const/4 v3, -0x5

    const/4 v4, 0x0

    if-eq p1, v3, :cond_a

    const/4 v3, -0x4

    const/4 v5, 0x0

    if-eq p1, v3, :cond_8

    const/4 v3, -0x3

    const/4 v6, 0x1

    if-eq p1, v3, :cond_6

    const/4 v3, -0x2

    if-eq p1, v3, :cond_3

    const/4 v3, -0x1

    if-eq p1, v3, :cond_0

    goto/16 :goto_4

    :cond_0
    instance-of p1, p2, Landroid/database/Cursor;

    if-eqz p1, :cond_2

    :try_start_0
    move-object p1, p2

    check-cast p1, Landroid/database/Cursor;

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->parsePeopleCoversFromSnapshot(Landroid/database/Cursor;)Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v3

    if-eqz v3, :cond_1

    iget-object v3, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    invoke-virtual {v3, v4, p1}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;->setAlbumCover(ILjava/util/ArrayList;)V

    iput-boolean v6, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsPeopleAlbumSnapshotValid:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :cond_1
    check-cast p2, Landroid/database/Cursor;

    invoke-static {p2}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    goto :goto_0

    :catchall_0
    move-exception p1

    check-cast p2, Landroid/database/Cursor;

    invoke-static {p2}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw p1

    :cond_2
    :goto_0
    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->startDeferredLoad()V

    goto/16 :goto_4

    :cond_3
    instance-of p1, p2, Landroid/database/Cursor;

    if-eqz p1, :cond_4

    move-object p1, p2

    check-cast p1, Landroid/database/Cursor;

    goto :goto_1

    :cond_4
    move-object p1, v5

    :goto_1
    if-eqz p1, :cond_5

    invoke-interface {p1}, Landroid/database/Cursor;->getExtras()Landroid/os/Bundle;

    move-result-object p2

    if-eqz p2, :cond_5

    invoke-interface {p1}, Landroid/database/Cursor;->getExtras()Landroid/os/Bundle;

    move-result-object p1

    const-string p2, "face_album_cover"

    invoke-virtual {p1, p2}, Landroid/os/Bundle;->getParcelableArrayList(Ljava/lang/String;)Ljava/util/ArrayList;

    move-result-object v5

    :cond_5
    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    invoke-virtual {p1, v4, v5}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;->setAlbumCover(ILjava/util/ArrayList;)V

    invoke-static {v5}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result p1

    if-eqz p1, :cond_b

    iget-boolean p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsPeopleAlbumSnapshotValid:Z

    if-nez p1, :cond_b

    iput-boolean v6, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsPeopleAlbumSnapshotValid:Z

    invoke-direct {p0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->takeSnapshot4PeopleAlbum()V

    goto/16 :goto_4

    :cond_6
    instance-of p1, p2, Lcom/miui/gallery/search/resultpage/DataListSourceResult;

    if-eqz p1, :cond_7

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->clear()V

    check-cast p2, Lcom/miui/gallery/search/resultpage/DataListSourceResult;

    invoke-virtual {p2}, Lcom/miui/gallery/search/resultpage/DataListSourceResult;->getData()Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;

    move-result-object p1

    if-eqz p1, :cond_7

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result p2

    if-lez p2, :cond_7

    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    invoke-interface {p1}, Landroid/database/Cursor;->moveToFirst()Z

    :goto_2
    invoke-interface {p1}, Landroid/database/Cursor;->isAfterLast()Z

    move-result p2

    if-nez p2, :cond_7

    move-object p2, p1

    check-cast p2, Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;

    invoke-interface {p2}, Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;->getSuggestionIcon()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v5, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v3, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->parseAlbumCoverServerId(Ljava/lang/String;)J

    move-result-wide v7

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-virtual {v3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-interface {p1}, Landroid/database/Cursor;->moveToNext()Z

    goto :goto_2

    :cond_7
    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    invoke-virtual {p1, v6, v5}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;->setAlbumCover(ILjava/util/ArrayList;)V

    invoke-static {v5}, Lcom/miui/gallery/preference/GalleryPreferences$SearchBackedAlbum;->setLocationsAlbumServerIds(Ljava/util/List;)V

    goto :goto_4

    :cond_8
    instance-of p1, p2, Lcom/miui/gallery/search/resultpage/DataListSourceResult;

    if-eqz p1, :cond_9

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->clear()V

    check-cast p2, Lcom/miui/gallery/search/resultpage/DataListSourceResult;

    invoke-virtual {p2}, Lcom/miui/gallery/search/resultpage/DataListSourceResult;->getData()Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;

    move-result-object p1

    if-eqz p1, :cond_9

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result p2

    if-lez p2, :cond_9

    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    invoke-interface {p1}, Landroid/database/Cursor;->moveToFirst()Z

    :goto_3
    invoke-interface {p1}, Landroid/database/Cursor;->isAfterLast()Z

    move-result p2

    if-nez p2, :cond_9

    move-object p2, p1

    check-cast p2, Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;

    invoke-interface {p2}, Lcom/miui/gallery/search/core/suggestion/SuggestionCursor;->getSuggestionIcon()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v5, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v3, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->parseAlbumCoverServerId(Ljava/lang/String;)J

    move-result-wide v6

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-virtual {v3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-interface {p1}, Landroid/database/Cursor;->moveToNext()Z

    goto :goto_3

    :cond_9
    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAdapter:Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;

    const/4 p2, 0x2

    invoke-virtual {p1, p2, v5}, Lcom/miui/gallery/adapter/AlbumPageHeaderAdapter;->setAlbumCover(ILjava/util/ArrayList;)V

    invoke-static {v5}, Lcom/miui/gallery/preference/GalleryPreferences$SearchBackedAlbum;->setTagsAlbumServerIds(Ljava/util/List;)V

    goto :goto_4

    :cond_a
    instance-of p1, p2, Ljava/lang/Integer;

    if-eqz p1, :cond_b

    check-cast p2, Ljava/lang/Integer;

    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->updateSearchStatus(I)V

    :cond_b
    :goto_4
    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsManualLoad:Landroid/util/SparseBooleanArray;

    invoke-virtual {p1, v0}, Landroid/util/SparseBooleanArray;->get(I)Z

    move-result p1

    if-eqz p1, :cond_c

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mIsManualLoad:Landroid/util/SparseBooleanArray;

    invoke-virtual {p1, v0, v4}, Landroid/util/SparseBooleanArray;->put(IZ)V

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLoaderCreateTime:Landroid/util/SparseLongArray;

    invoke-virtual {p1, v0}, Landroid/util/SparseLongArray;->get(I)J

    move-result-wide p1

    sub-long/2addr v1, p1

    iget p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAlbumCoverNum:I

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->loaderId2Name(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    const-string v4, "AlbumPageHeaderController"

    const-string v5, "load [%d] items for [%s] costs [%d ms]"

    invoke-static {v4, v5, p1, p2, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->loaderId2Name(I)Ljava/lang/String;

    move-result-object p1

    iget p2, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mAlbumCoverNum:I

    invoke-direct {p0, p1, v1, v2, p2}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->statAlbumLoadTime(Ljava/lang/String;JI)V

    :cond_c
    return-void
.end method

.method public onLoaderReset(Landroid/content/Loader;)V
    .locals 0

    return-void
.end method

.method public onResume(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsSubject:Lio/reactivex/subjects/PublishSubject;

    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mLocationsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {p1, v0}, Lio/reactivex/subjects/PublishSubject;->onNext(Ljava/lang/Object;)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsSubject:Lio/reactivex/subjects/PublishSubject;

    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/ui/AlbumPageHeaderController;->mTagsAlbumCoverServerIds:Ljava/util/ArrayList;

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {p1, v0}, Lio/reactivex/subjects/PublishSubject;->onNext(Ljava/lang/Object;)V

    :cond_1
    return-void
.end method
