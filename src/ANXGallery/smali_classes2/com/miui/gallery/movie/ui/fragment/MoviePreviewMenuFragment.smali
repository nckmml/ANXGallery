.class public Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;
.super Landroid/app/Fragment;
.source "MoviePreviewMenuFragment.java"


# instance fields
.field private mCurrentTimeView:Lcom/miui/gallery/movie/ui/view/StrokeTextView;

.field private mEndPadding:I

.field private mIsSeekTimeline:Z

.field private mMenuBottomView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;

.field private mMenuBottomViewClickListener:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView$IMenuBottomViewClickListener;

.field private mMenuFragmentListener:Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

.field private mMenuTitleClickListener:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle$IMenuTitleClickListener;

.field private mMenuTitleView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;

.field private mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

.field private mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

.field private mOnGlobalLayoutListener:Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;

.field private mPixelPerMicroSeconds:D

.field private mSequenceWidth:I

.field private mStartPadding:I

.field private mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

.field private mWhiteView:Landroid/view/View;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Landroid/app/Fragment;-><init>()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mIsSeekTimeline:Z

    new-instance v0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$2;-><init>(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuTitleClickListener:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle$IMenuTitleClickListener;

    new-instance v0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$3;-><init>(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuBottomViewClickListener:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView$IMenuBottomViewClickListener;

    new-instance v0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$4;-><init>(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mOnGlobalLayoutListener:Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)Lcom/miui/gallery/movie/core/MovieManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)Lcom/miui/gallery/movie/entity/MovieInfo;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    return-object p0
.end method

.method static synthetic access$1000(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->initListener()V

    return-void
.end method

.method static synthetic access$1100(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->removeOnGlobalLayoutListener()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMultiThumbnailView()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuFragmentListener:Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMenuBottomView()V

    return-void
.end method

.method static synthetic access$502(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mStartPadding:I

    return p1
.end method

.method static synthetic access$600(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mWhiteView:Landroid/view/View;

    return-object p0
.end method

.method static synthetic access$802(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mEndPadding:I

    return p1
.end method

.method static synthetic access$900(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMenuTitleView()V

    return-void
.end method

.method private initListener()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    new-instance v1, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MoviePreviewMenuFragment$vF4cGQcX7QsgAV03uNJ_r9vpnxc;

    invoke-direct {v1, p0}, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MoviePreviewMenuFragment$vF4cGQcX7QsgAV03uNJ_r9vpnxc;-><init>(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V

    invoke-interface {v0, v1}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->setOnScrollChangeListener(Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView$OnScrollChangeListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuBottomView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuBottomViewClickListener:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView$IMenuBottomViewClickListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;->setIMenuBottomViewClickListener(Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView$IMenuBottomViewClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuTitleView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuTitleClickListener:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle$IMenuTitleClickListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;->setListener(Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle$IMenuTitleClickListener;)V

    return-void
.end method

.method private initView(Landroid/view/View;)V
    .locals 4

    sget v0, Lcom/miui/gallery/movie/R$id;->preview_title_area:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuTitleView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;

    sget v0, Lcom/miui/gallery/movie/R$id;->preivew_menu_bottom:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuBottomView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;

    sget v0, Lcom/miui/gallery/movie/R$id;->thumbnail_view_root:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/movie/core/MovieManager;->createThumbnailSequenceView(Landroid/content/Context;)Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    invoke-interface {v1}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->getRealView()Landroid/view/View;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;I)V

    sget v0, Lcom/miui/gallery/movie/R$id;->tv_movie_duration:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/ui/view/StrokeTextView;

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mCurrentTimeView:Lcom/miui/gallery/movie/ui/view/StrokeTextView;

    sget v0, Lcom/miui/gallery/movie/R$id;->white_view:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mWhiteView:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    invoke-interface {v0}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->getPixelPerMicrosecond()D

    move-result-wide v0

    const-wide/high16 v2, 0x4000000000000000L    # 2.0

    mul-double/2addr v0, v2

    iput-wide v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mPixelPerMicroSeconds:D

    invoke-virtual {p1}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mOnGlobalLayoutListener:Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;

    invoke-virtual {p1, v0}, Landroid/view/ViewTreeObserver;->addOnGlobalLayoutListener(Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;)V

    return-void
.end method

.method private removeOnGlobalLayoutListener()V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->getView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mOnGlobalLayoutListener:Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;

    invoke-virtual {v0, v1}, Landroid/view/ViewTreeObserver;->removeOnGlobalLayoutListener(Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;)V

    return-void
.end method

.method private setDuration(I)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mCurrentTimeView:Lcom/miui/gallery/movie/ui/view/StrokeTextView;

    if-eqz v0, :cond_0

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    div-int/lit16 p1, p1, 0x3e8

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    aput-object p1, v1, v2

    const-string p1, "00:%02d"

    invoke-static {v0, p1, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mCurrentTimeView:Lcom/miui/gallery/movie/ui/view/StrokeTextView;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/ui/view/StrokeTextView;->setText(Ljava/lang/CharSequence;)V

    :cond_0
    return-void
.end method

.method private updateMenuBottomView()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuBottomView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v1}, Lcom/miui/gallery/movie/core/MovieManager;->getState()I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_0

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    invoke-virtual {v0, v2}, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;->updatePlayBtnState(Z)V

    return-void
.end method

.method private updateMenuTitleView()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuTitleView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;

    iget-boolean v0, v0, Lcom/miui/gallery/movie/entity/MovieInfo;->isShortVideo:Z

    invoke-virtual {v1, v0}, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;->updateTitleViewState(Z)V

    :cond_0
    return-void
.end method

.method private updateMultiThumbnailView()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-interface {v0, v1}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->setThumbnailAspectRatio(F)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    const/4 v1, 0x1

    invoke-interface {v0, v1}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->setThumbnailImageFillMode(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v1}, Lcom/miui/gallery/movie/core/MovieManager;->getThumbnailImages()Ljava/lang/Object;

    move-result-object v1

    invoke-interface {v0, v1}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->setThumbnailSequenceDescArray(Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    iget-wide v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mPixelPerMicroSeconds:D

    invoke-interface {v0, v1, v2}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->setPixelPerMicrosecond(D)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    iget v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mStartPadding:I

    invoke-interface {v0, v1}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->setStartPadding(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    iget v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mEndPadding:I

    invoke-interface {v0, v1}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->setEndPadding(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v1}, Lcom/miui/gallery/movie/core/MovieManager;->getTotalTime()I

    move-result v1

    iget-wide v2, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mPixelPerMicroSeconds:D

    invoke-interface {v0, v1, v2, v3}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->getSequenceWidth(ID)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mSequenceWidth:I

    return-void
.end method


# virtual methods
.method public synthetic lambda$initListener$16$MoviePreviewMenuFragment(II)V
    .locals 2

    iget-boolean p2, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mIsSeekTimeline:Z

    if-nez p2, :cond_0

    return-void

    :cond_0
    int-to-double p1, p1

    iget-wide v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mPixelPerMicroSeconds:D

    div-double/2addr p1, v0

    const-wide/high16 v0, 0x3fe0000000000000L    # 0.5

    add-double/2addr p1, v0

    invoke-static {p1, p2}, Ljava/lang/Math;->floor(D)D

    move-result-wide p1

    double-to-int p1, p1

    div-int/lit16 p1, p1, 0x3e8

    iget-object p2, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuFragmentListener:Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

    if-eqz p2, :cond_1

    invoke-interface {p2, p1}, Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;->seek(I)V

    :cond_1
    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->setDuration(I)V

    return-void
.end method

.method public onAttach(Landroid/content/Context;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/app/Fragment;->onAttach(Landroid/content/Context;)V

    instance-of v0, p1, Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuFragmentListener:Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuFragmentListener:Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

    invoke-interface {p1}, Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;->getMovieManager()Lcom/miui/gallery/movie/core/MovieManager;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuFragmentListener:Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;

    invoke-interface {p1}, Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;->getMovieInfo()Lcom/miui/gallery/movie/entity/MovieInfo;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    :cond_0
    return-void
.end method

.method public onCreateAnimator(IZI)Landroid/animation/Animator;
    .locals 4

    new-instance p1, Landroid/animation/ObjectAnimator;

    invoke-direct {p1}, Landroid/animation/ObjectAnimator;-><init>()V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p3

    sget v0, Lcom/miui/gallery/movie/R$dimen;->movie_preview_height_offset:I

    invoke-virtual {p3, v0}, Landroid/content/res/Resources;->getDimension(I)F

    move-result p3

    float-to-int p3, p3

    const/4 v0, 0x2

    const/4 v1, 0x0

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz p2, :cond_0

    sget-object p2, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v0, v0, [F

    int-to-float p3, p3

    aput p3, v0, v3

    aput v1, v0, v2

    invoke-static {p2, v0}, Landroid/animation/PropertyValuesHolder;->ofFloat(Landroid/util/Property;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object p2

    new-array p3, v2, [Landroid/animation/PropertyValuesHolder;

    aput-object p2, p3, v3

    invoke-virtual {p1, p3}, Landroid/animation/ObjectAnimator;->setValues([Landroid/animation/PropertyValuesHolder;)V

    new-instance p2, Lmiui/view/animation/CubicEaseOutInterpolator;

    invoke-direct {p2}, Lmiui/view/animation/CubicEaseOutInterpolator;-><init>()V

    invoke-virtual {p1, p2}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    sget p3, Lcom/miui/gallery/movie/R$integer;->movie_preview_appear_delay:I

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p2

    int-to-long p2, p2

    invoke-virtual {p1, p2, p3}, Landroid/animation/ObjectAnimator;->setStartDelay(J)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    sget p3, Lcom/miui/gallery/movie/R$integer;->movie_preview_appear_duration:I

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p2

    int-to-long p2, p2

    invoke-virtual {p1, p2, p3}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->getView()Landroid/view/View;

    move-result-object p2

    invoke-virtual {p2, v1}, Landroid/view/View;->setAlpha(F)V

    new-instance p2, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$1;

    invoke-direct {p2, p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment$1;-><init>(Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;)V

    invoke-virtual {p1, p2}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    goto :goto_0

    :cond_0
    sget-object p2, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v0, v0, [F

    aput v1, v0, v3

    int-to-float p3, p3

    aput p3, v0, v2

    invoke-static {p2, v0}, Landroid/animation/PropertyValuesHolder;->ofFloat(Landroid/util/Property;[F)Landroid/animation/PropertyValuesHolder;

    move-result-object p2

    new-array p3, v2, [Landroid/animation/PropertyValuesHolder;

    aput-object p2, p3, v3

    invoke-virtual {p1, p3}, Landroid/animation/ObjectAnimator;->setValues([Landroid/animation/PropertyValuesHolder;)V

    new-instance p2, Lmiui/view/animation/QuarticEaseOutInterpolator;

    invoke-direct {p2}, Lmiui/view/animation/QuarticEaseOutInterpolator;-><init>()V

    invoke-virtual {p1, p2}, Landroid/animation/ObjectAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    sget p3, Lcom/miui/gallery/movie/R$integer;->movie_preview_disappear_duration:I

    invoke-virtual {p2, p3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p2

    int-to-long p2, p2

    invoke-virtual {p1, p2, p3}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    :goto_0
    return-object p1
.end method

.method public onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    sget p3, Lcom/miui/gallery/movie/R$layout;->movie_fragment_preview:I

    const/4 v0, 0x0

    invoke-virtual {p1, p3, p2, v0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->initView(Landroid/view/View;)V

    return-object p1
.end method

.method public onDestroyView()V
    .locals 1

    invoke-super {p0}, Landroid/app/Fragment;->onDestroyView()V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuTitleView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuTitle;->removeListener()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mMenuBottomView:Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/view/MoviePreviewMenuBottomView;->removeListener()V

    :cond_1
    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->removeOnGlobalLayoutListener()V

    return-void
.end method

.method public onHiddenChanged(Z)V
    .locals 0

    invoke-super {p0, p1}, Landroid/app/Fragment;->onHiddenChanged(Z)V

    if-nez p1, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMultiThumbnailView()V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMenuTitleView()V

    :cond_0
    return-void
.end method

.method public onPlaybackEOF()V
    .locals 3

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mIsSeekTimeline:Z

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    const/16 v2, 0x11

    invoke-interface {v1, v2}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->fullScroll(I)V

    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mIsSeekTimeline:Z

    invoke-direct {p0, v0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->setDuration(I)V

    return-void
.end method

.method public onProgressChange(FI)V
    .locals 2

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mIsSeekTimeline:Z

    iget v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mSequenceWidth:I

    int-to-float v1, v1

    mul-float/2addr p1, v1

    invoke-static {p1}, Ljava/lang/Math;->round(F)I

    move-result p1

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mThumbnailSequenceView:Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;

    invoke-interface {v1, p1, v0}, Lcom/miui/gallery/movie/ui/view/IMultiThumbnailSequenceView;->scrollTo(II)V

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->mIsSeekTimeline:Z

    invoke-direct {p0, p2}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->setDuration(I)V

    return-void
.end method

.method public onStateChanged(I)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMenuBottomView()V

    return-void
.end method

.method public updateAfterSetInfo()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMultiThumbnailView()V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateMenuBottomView()V

    return-void
.end method
