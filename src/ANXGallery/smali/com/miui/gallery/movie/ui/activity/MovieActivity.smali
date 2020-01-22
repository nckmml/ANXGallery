.class public Lcom/miui/gallery/movie/ui/activity/MovieActivity;
.super Lcom/miui/gallery/app/Activity;
.source "MovieActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;
.implements Lcom/miui/gallery/movie/ui/listener/MenuFragmentListener;
.implements Lcom/miui/gallery/permission/core/PermissionCheckCallback;


# instance fields
.field private mActionBar:Lmiui/app/ActionBar;

.field private mActivity:Lcom/miui/gallery/app/Activity;

.field private mAudioFocusHelper:Lcom/miui/gallery/movie/utils/AudioFocusHelper;

.field private mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

.field private mEditorMenuView:Landroid/view/View;

.field private mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

.field private mIsDestroyed:Z

.field private mIsEditorPreview:Z

.field private mMenuActivityListener:Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

.field private mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

.field private mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

.field private mMovieSavingFragment:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

.field private mMovieShareData:Lcom/miui/gallery/movie/entity/MovieShareData;

.field private mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

.field private mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

.field private mPreviewMenuView:Landroid/view/View;

.field private mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

.field private mRootView:Landroid/view/ViewGroup;

.field private mShowMode:I

.field private mSingleClickListener:Lcom/miui/gallery/listener/SingleClickListener;

.field private mStoryMovieCardId:J

.field private mStorySha1List:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/app/Activity;-><init>()V

    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsDestroyed:Z

    new-instance v0, Lcom/miui/gallery/movie/ui/activity/MovieActivity$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity$1;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mSingleClickListener:Lcom/miui/gallery/listener/SingleClickListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)Lcom/miui/gallery/movie/core/MovieManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)Lcom/miui/gallery/movie/entity/MovieShareData;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieShareData:Lcom/miui/gallery/movie/entity/MovieShareData;

    return-object p0
.end method

.method static synthetic access$102(Lcom/miui/gallery/movie/ui/activity/MovieActivity;Lcom/miui/gallery/movie/entity/MovieShareData;)Lcom/miui/gallery/movie/entity/MovieShareData;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieShareData:Lcom/miui/gallery/movie/entity/MovieShareData;

    return-object p1
.end method

.method static synthetic access$200(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)Lcom/miui/gallery/movie/entity/MovieInfo;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieSavingFragment:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    return-object p0
.end method

.method static synthetic access$302(Lcom/miui/gallery/movie/ui/activity/MovieActivity;Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;)Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieSavingFragment:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    return-object p1
.end method

.method static synthetic access$400(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)Lcom/miui/gallery/app/Activity;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    return-object p0
.end method

.method static synthetic access$500(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    return p0
.end method

.method static synthetic access$602(Lcom/miui/gallery/movie/ui/activity/MovieActivity;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    return p1
.end method

.method private configureActionBar()V
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->setDisplayShowCustomEnabled(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Lmiui/app/ActionBar;->setDisplayShowHomeEnabled(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0, v2}, Lmiui/app/ActionBar;->setDisplayShowTitleEnabled(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->setHomeButtonEnabled(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    sget v1, Lcom/miui/gallery/movie/R$layout;->movie_fragment_top:I

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->setCustomView(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0}, Lmiui/app/ActionBar;->getCustomView()Landroid/view/View;

    move-result-object v0

    sget v1, Lcom/miui/gallery/movie/R$id;->movie_title:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    sget v2, Lcom/miui/gallery/movie/R$id;->movie_share:I

    invoke-virtual {v0, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    invoke-virtual {v1, p0}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mSingleClickListener:Lcom/miui/gallery/listener/SingleClickListener;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {v0}, Lmiui/app/ActionBar;->hide()V

    return-void
.end method

.method private doDestroy()V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsDestroyed:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieShareData:Lcom/miui/gallery/movie/entity/MovieShareData;

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/movie/core/MovieManager;->destroy()V

    :cond_1
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsDestroyed:Z

    return-void
.end method

.method private doEditorPreviewChangeAnimal(Landroid/view/View;Z)V
    .locals 6

    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v1

    const/4 v2, 0x0

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz p2, :cond_0

    sget-object p2, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v3, v3, [F

    int-to-float v1, v1

    aput v1, v3, v5

    aput v2, v3, v4

    invoke-static {p1, p2, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object p2

    new-array v1, v4, [Landroid/animation/Animator;

    aput-object p2, v1, v5

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    new-instance p2, Lcom/miui/gallery/movie/ui/activity/MovieActivity$6;

    invoke-direct {p2, p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity$6;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;Landroid/view/View;)V

    invoke-virtual {v0, p2}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    goto :goto_0

    :cond_0
    sget-object p2, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v3, v3, [F

    aput v2, v3, v5

    int-to-float v1, v1

    aput v1, v3, v4

    invoke-static {p1, p2, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object p2

    new-array v1, v4, [Landroid/animation/Animator;

    aput-object p2, v1, v5

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    new-instance p2, Lcom/miui/gallery/movie/ui/activity/MovieActivity$7;

    invoke-direct {p2, p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity$7;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;Landroid/view/View;)V

    invoke-virtual {v0, p2}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->setupStartValues()V

    :goto_0
    new-instance p1, Lmiui/view/animation/CubicEaseInOutInterpolator;

    invoke-direct {p1}, Lmiui/view/animation/CubicEaseInOutInterpolator;-><init>()V

    invoke-virtual {v0, p1}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    sget p2, Lcom/miui/gallery/movie/R$integer;->movie_editor_preview_duration:I

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p1

    int-to-long p1, p1

    invoke-virtual {v0, p1, p2}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    return-void
.end method

.method private doFullScreenChangeAnimal(Landroid/view/View;Z)V
    .locals 6

    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v1

    const/4 v2, 0x0

    const/4 v3, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz p2, :cond_0

    sget-object p2, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v3, v3, [F

    int-to-float v1, v1

    aput v1, v3, v5

    aput v2, v3, v4

    invoke-static {p1, p2, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object p2

    new-array v1, v4, [Landroid/animation/Animator;

    aput-object p2, v1, v5

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    new-instance p2, Lmiui/view/animation/CubicEaseOutInterpolator;

    invoke-direct {p2}, Lmiui/view/animation/CubicEaseOutInterpolator;-><init>()V

    invoke-virtual {v0, p2}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    sget v1, Lcom/miui/gallery/movie/R$integer;->movie_background_appear_duration:I

    invoke-virtual {p2, v1}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p2

    int-to-long v1, p2

    invoke-virtual {v0, v1, v2}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    new-instance p2, Lcom/miui/gallery/movie/ui/activity/MovieActivity$4;

    invoke-direct {p2, p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity$4;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;Landroid/view/View;)V

    invoke-virtual {v0, p2}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    goto :goto_0

    :cond_0
    sget-object p2, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v3, v3, [F

    aput v2, v3, v5

    int-to-float v1, v1

    aput v1, v3, v4

    invoke-static {p1, p2, v3}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object p2

    new-array v1, v4, [Landroid/animation/Animator;

    aput-object p2, v1, v5

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    new-instance p2, Lmiui/view/animation/CubicEaseInInterpolator;

    invoke-direct {p2}, Lmiui/view/animation/CubicEaseInInterpolator;-><init>()V

    invoke-virtual {v0, p2}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    sget v1, Lcom/miui/gallery/movie/R$integer;->movie_background_disappear_duration:I

    invoke-virtual {p2, v1}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p2

    int-to-long v1, p2

    invoke-virtual {v0, v1, v2}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    new-instance p2, Lcom/miui/gallery/movie/ui/activity/MovieActivity$5;

    invoke-direct {p2, p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity$5;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;Landroid/view/View;)V

    invoke-virtual {v0, p2}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->setupStartValues()V

    :goto_0
    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    return-void
.end method

.method private doShare(Ljava/lang/String;)V
    .locals 5

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const-string v1, "MovieActivity"

    if-eqz v0, :cond_0

    const-string p1, "share outFilePath is null"

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-static {p1}, Lcom/miui/gallery/util/GalleryOpenProviderUtils;->translateToContent(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    const-string v2, "android.intent.action.SEND"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    const-string v2, "video/*"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->setType(Ljava/lang/String;)Landroid/content/Intent;

    const-string v2, "android.intent.extra.STREAM"

    invoke-virtual {v0, v2, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    const/high16 v2, 0x10000000

    invoke-virtual {v0, v2}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    const/high16 v3, 0x8080000

    invoke-virtual {v0, v3}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    invoke-virtual {v3}, Lcom/miui/gallery/app/Activity;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v3

    const/high16 v4, 0x10000

    invoke-virtual {v3, v0, v4}, Landroid/content/pm/PackageManager;->queryIntentActivities(Landroid/content/Intent;I)Ljava/util/List;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/gallery/util/BaseMiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v4

    if-nez v4, :cond_1

    const-string p1, "doShare: resInfoList is invalid."

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_1
    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/content/pm/ResolveInfo;

    iget-object v3, v3, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    iget-object v3, v3, Landroid/content/pm/ActivityInfo;->packageName:Ljava/lang/String;

    iget-object v4, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    invoke-virtual {v4, v3, p1, v2}, Lcom/miui/gallery/app/Activity;->grantUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    sget v1, Lcom/miui/gallery/movie/R$string;->movie_preview_share_title:I

    invoke-virtual {p1, v1}, Lcom/miui/gallery/app/Activity;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Landroid/content/Intent;->createChooser(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    invoke-virtual {v0, p1, v2}, Lcom/miui/gallery/app/Activity;->startActivityForResult(Landroid/content/Intent;I)V

    return-void
.end method

.method private initMode()V
    .locals 4

    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v1, 0x1

    const/4 v2, 0x3

    const/4 v3, -0x1

    if-ne v0, v3, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-boolean v0, v0, Lcom/miui/gallery/movie/entity/MovieInfo;->isFromStory:Z

    if-eqz v0, :cond_0

    move v0, v1

    goto :goto_0

    :cond_0
    move v0, v2

    :goto_0
    iput v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    :cond_1
    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    if-ne v0, v2, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    sget v1, Lcom/miui/gallery/movie/R$id;->editor_panel:I

    invoke-virtual {v0, v1}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    if-nez v0, :cond_4

    new-instance v0, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    invoke-direct {v0}, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    sget v1, Lcom/miui/gallery/movie/R$id;->editor_panel:I

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    invoke-virtual {v0, v1, v2}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commit()I

    goto :goto_1

    :cond_2
    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    sget v2, Lcom/miui/gallery/movie/R$id;->preview_panel:I

    invoke-virtual {v0, v2}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    if-nez v0, :cond_3

    new-instance v0, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    invoke-direct {v0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;-><init>()V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v2

    sget v3, Lcom/miui/gallery/movie/R$id;->preview_panel:I

    invoke-virtual {v2, v3, v0}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/FragmentManager;->executePendingTransactions()Z

    iget v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    if-ne v2, v1, :cond_3

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuView:Landroid/view/View;

    const/4 v2, 0x4

    invoke-virtual {v1, v2}, Landroid/view/View;->setVisibility(I)V

    :cond_3
    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    :cond_4
    :goto_1
    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setForMode()V

    return-void
.end method

.method private initMovieViewLayout()V
    .locals 6

    invoke-static {p0}, Lcom/miui/gallery/util/ScreenUtils;->getFullScreenHeight(Landroid/app/Activity;)I

    move-result v0

    invoke-static {p0}, Lcom/android/internal/WindowCompat;->getTopNotchHeight(Landroid/app/Activity;)I

    move-result v1

    invoke-static {}, Lcom/miui/gallery/util/ScreenUtils;->getScreenWidth()I

    move-result v2

    int-to-float v2, v2

    const/high16 v3, 0x3f800000    # 1.0f

    mul-float/2addr v2, v3

    int-to-float v3, v0

    div-float/2addr v2, v3

    invoke-static {v2}, Lcom/miui/gallery/movie/MovieConfig;->setMovieRatio(F)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/miui/gallery/movie/R$dimen;->movie_controller_height:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v4

    sub-float/2addr v3, v4

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/miui/gallery/movie/R$dimen;->movie_editor_view_margin_top:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v4

    sub-float/2addr v3, v4

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/miui/gallery/movie/R$dimen;->movie_editor_view_margin_bottom:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v4

    sub-float/2addr v3, v4

    int-to-float v4, v1

    sub-float/2addr v3, v4

    float-to-int v3, v3

    new-instance v4, Landroid/widget/RelativeLayout$LayoutParams;

    iget-object v5, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v5}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v5

    invoke-direct {v4, v5}, Landroid/widget/RelativeLayout$LayoutParams;-><init>(Landroid/view/ViewGroup$LayoutParams;)V

    iput-object v4, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget-object v4, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    int-to-float v5, v3

    mul-float/2addr v5, v2

    float-to-int v2, v5

    iput v2, v4, Landroid/widget/RelativeLayout$LayoutParams;->width:I

    iput v3, v4, Landroid/widget/RelativeLayout$LayoutParams;->height:I

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/miui/gallery/movie/R$dimen;->movie_editor_view_margin_top:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v2

    float-to-int v2, v2

    add-int/2addr v2, v1

    iput v2, v4, Landroid/widget/RelativeLayout$LayoutParams;->topMargin:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    const/16 v2, 0xe

    invoke-virtual {v1, v2}, Landroid/widget/RelativeLayout$LayoutParams;->addRule(I)V

    new-instance v1, Landroid/widget/RelativeLayout$LayoutParams;

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v3}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v3

    invoke-direct {v1, v3}, Landroid/widget/RelativeLayout$LayoutParams;-><init>(Landroid/view/ViewGroup$LayoutParams;)V

    iput-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    invoke-static {}, Lcom/miui/gallery/util/ScreenUtils;->getScreenWidth()I

    move-result v3

    iput v3, v1, Landroid/widget/RelativeLayout$LayoutParams;->width:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iput v0, v1, Landroid/widget/RelativeLayout$LayoutParams;->height:I

    const/4 v0, 0x0

    iput v0, v1, Landroid/widget/RelativeLayout$LayoutParams;->topMargin:I

    invoke-virtual {v1, v2}, Landroid/widget/RelativeLayout$LayoutParams;->addRule(I)V

    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v1, 0x3

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/RelativeLayout$LayoutParams;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget v1, v1, Landroid/widget/RelativeLayout$LayoutParams;->width:I

    iput v1, v0, Landroid/widget/RelativeLayout$LayoutParams;->width:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget v1, v1, Landroid/widget/RelativeLayout$LayoutParams;->height:I

    iput v1, v0, Landroid/widget/RelativeLayout$LayoutParams;->height:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget v1, v1, Landroid/widget/RelativeLayout$LayoutParams;->topMargin:I

    iput v1, v0, Landroid/widget/RelativeLayout$LayoutParams;->topMargin:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v1, v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/RelativeLayout$LayoutParams;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget v1, v1, Landroid/widget/RelativeLayout$LayoutParams;->width:I

    iput v1, v0, Landroid/widget/RelativeLayout$LayoutParams;->width:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget v1, v1, Landroid/widget/RelativeLayout$LayoutParams;->height:I

    iput v1, v0, Landroid/widget/RelativeLayout$LayoutParams;->height:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    iget v1, v1, Landroid/widget/RelativeLayout$LayoutParams;->topMargin:I

    iput v1, v0, Landroid/widget/RelativeLayout$LayoutParams;->topMargin:I

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v1, v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :goto_0
    return-void
.end method

.method private initStoryAlbumData()V
    .locals 4

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-boolean v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->isFromStory:Z

    if-eqz v1, :cond_3

    if-nez v0, :cond_0

    goto :goto_1

    :cond_0
    const-wide/16 v1, 0x0

    const-string v3, "card_id"

    invoke-virtual {v0, v3, v1, v2}, Landroid/content/Intent;->getLongExtra(Ljava/lang/String;J)J

    move-result-wide v1

    iput-wide v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStoryMovieCardId:J

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStorySha1List:Ljava/util/ArrayList;

    if-nez v1, :cond_1

    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStorySha1List:Ljava/util/ArrayList;

    :cond_1
    invoke-virtual {v0}, Landroid/content/Intent;->getClipData()Landroid/content/ClipData;

    move-result-object v0

    const/4 v1, 0x0

    :goto_0
    invoke-virtual {v0}, Landroid/content/ClipData;->getItemCount()I

    move-result v2

    if-ge v1, v2, :cond_3

    invoke-virtual {v0, v1}, Landroid/content/ClipData;->getItemAt(I)Landroid/content/ClipData$Item;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/ClipData$Item;->getText()Ljava/lang/CharSequence;

    move-result-object v2

    invoke-interface {v2}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v2

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStorySha1List:Ljava/util/ArrayList;

    invoke-virtual {v3, v2}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_2

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStorySha1List:Ljava/util/ArrayList;

    invoke-virtual {v3, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_3
    :goto_1
    return-void
.end method

.method private initView()V
    .locals 2

    sget v0, Lcom/miui/gallery/movie/R$id;->movie_controller_view:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->init(Lcom/miui/gallery/movie/core/MovieManager;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v1, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$vTt-NZ7-XCogp_6BuppZhEqYmBk;

    invoke-direct {v1, p0}, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$vTt-NZ7-XCogp_6BuppZhEqYmBk;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->post(Ljava/lang/Runnable;)Z

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v1, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$uYfEMBzoCBR7Nz8Ui-moJsBHA24;

    invoke-direct {v1, p0}, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$uYfEMBzoCBR7Nz8Ui-moJsBHA24;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->getMovieManager()Lcom/miui/gallery/movie/core/MovieManager;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    sget v0, Lcom/miui/gallery/movie/R$id;->preview_panel:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuView:Landroid/view/View;

    sget v0, Lcom/miui/gallery/movie/R$id;->editor_panel:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuView:Landroid/view/View;

    sget v0, Lcom/miui/gallery/movie/R$id;->movie_root:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mRootView:Landroid/view/ViewGroup;

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v1, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$-rJ-pkOIX6ei2aJ2XKzUxUnHVcs;

    invoke-direct {v1, p0}, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$-rJ-pkOIX6ei2aJ2XKzUxUnHVcs;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setIvPlayListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v1, Lcom/miui/gallery/movie/ui/activity/MovieActivity$2;

    invoke-direct {v1, p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity$2;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setProgressChangeListener(Lcom/miui/gallery/movie/ui/view/MovieControllerView$ProgressChangeListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v1, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$qzI6YVpJLUcx5YosV8JLxelQdFQ;

    invoke-direct {v1, p0}, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$qzI6YVpJLUcx5YosV8JLxelQdFQ;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setDeleteVisibleListener(Lcom/miui/gallery/movie/ui/view/MovieControllerView$DeleteIconVisibleListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v1, Lcom/miui/gallery/movie/ui/activity/MovieActivity$3;

    invoke-direct {v1, p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity$3;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setPreviewBtnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method private parseIntent()Z
    .locals 5

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/miui/gallery/movie/utils/MovieUtils;->getImageFromClipData(Landroid/content/Context;Landroid/content/Intent;)Ljava/util/List;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v2

    const/4 v3, 0x3

    if-ge v2, v3, :cond_0

    goto :goto_0

    :cond_0
    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    if-nez v2, :cond_1

    new-instance v2, Lcom/miui/gallery/movie/entity/MovieInfo;

    invoke-direct {v2, v0}, Lcom/miui/gallery/movie/entity/MovieInfo;-><init>(Ljava/util/List;)V

    iput-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getIntent()Landroid/content/Intent;

    move-result-object v3

    const-string v4, "movie_extra_preview_mode"

    invoke-virtual {v3, v4, v1}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v3

    iput-boolean v3, v2, Lcom/miui/gallery/movie/entity/MovieInfo;->isFromStory:Z

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getIntent()Landroid/content/Intent;

    move-result-object v3

    const-string v4, "card_title"

    invoke-virtual {v3, v4}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v2, Lcom/miui/gallery/movie/entity/MovieInfo;->title:Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getIntent()Landroid/content/Intent;

    move-result-object v3

    const-string v4, "card_sub_title"

    invoke-virtual {v3, v4}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v2, Lcom/miui/gallery/movie/entity/MovieInfo;->subTitle:Ljava/lang/String;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getIntent()Landroid/content/Intent;

    move-result-object v2

    const-string v3, "movie_extra_template"

    invoke-virtual {v2, v3, v1}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    invoke-static {v1}, Lcom/miui/gallery/movie/ui/factory/MovieFactory;->getTemplateNameById(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v2, Lcom/miui/gallery/movie/entity/MovieInfo;->template:Ljava/lang/String;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-boolean v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->isFromStory:Z

    invoke-static {v0, v1}, Lcom/miui/gallery/movie/utils/MovieStatUtils;->statEnter(IZ)V

    :cond_1
    const/4 v0, 0x1

    return v0

    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    sget v2, Lcom/miui/gallery/movie/R$string;->movie_delete_disable:I

    invoke-static {v0, v2}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->finish()V

    return v1
.end method

.method private setForMode()V
    .locals 3

    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v1, 0x3

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setPlayProgressVisible(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setShowPlayBtnMode(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setTouchAvailable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v2, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$5WX4ty6hT4a7H6JSSqzS8d4nR9w;

    invoke-direct {v2, p0}, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$5WX4ty6hT4a7H6JSSqzS8d4nR9w;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {v0, v2}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setDeleteClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->showPreviewBtn(Z)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setTouchAvailable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setPlayProgressVisible(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setShowPlayBtnMode(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->showPreviewBtn(Z)V

    :goto_0
    return-void
.end method

.method private setSystemBarVisible(Z)V
    .locals 0

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/util/SystemUiUtil;->showSystemBars(Landroid/view/View;)V

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {p1}, Lmiui/app/ActionBar;->show()V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/util/SystemUiUtil;->hideSystemBars(Landroid/view/View;)V

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActionBar:Lmiui/app/ActionBar;

    invoke-virtual {p1}, Lmiui/app/ActionBar;->hide()V

    :goto_0
    return-void
.end method


# virtual methods
.method public cancelExport()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v0}, Lcom/miui/gallery/movie/core/MovieManager;->cancelExport()V

    return-void
.end method

.method public changeEditor()V
    .locals 6

    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v1, 0x3

    if-ne v0, v1, :cond_1

    const/4 v0, 0x2

    iput v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    invoke-virtual {v0, v2}, Landroid/app/FragmentTransaction;->hide(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v2

    sget v3, Lcom/miui/gallery/movie/R$id;->preview_panel:I

    invoke-virtual {v2, v3}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    iput-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    if-nez v2, :cond_0

    new-instance v2, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    invoke-direct {v2}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;-><init>()V

    iput-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    sget v2, Lcom/miui/gallery/movie/R$id;->preview_panel:I

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    invoke-virtual {v0, v2, v3}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;)Landroid/app/FragmentTransaction;

    goto :goto_0

    :cond_0
    invoke-virtual {v0, v2}, Landroid/app/FragmentTransaction;->show(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    :goto_0
    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commit()I

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    new-instance v2, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$BKi9PyQXyfgYEZtf9pJ0pNFkilc;

    invoke-direct {v2, p0}, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$BKi9PyQXyfgYEZtf9pJ0pNFkilc;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    sget v4, Lcom/miui/gallery/movie/R$integer;->movie_preview_appear_delay:I

    invoke-virtual {v3, v4}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v3

    int-to-long v3, v3

    invoke-virtual {v0, v2, v3, v4}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_2

    :cond_1
    iput v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    invoke-virtual {v0, v2}, Landroid/app/FragmentTransaction;->hide(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v2

    sget v3, Lcom/miui/gallery/movie/R$id;->editor_panel:I

    invoke-virtual {v2, v3}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    iput-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    if-nez v2, :cond_2

    new-instance v2, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    invoke-direct {v2}, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;-><init>()V

    iput-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    sget v2, Lcom/miui/gallery/movie/R$id;->editor_panel:I

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    invoke-virtual {v0, v2, v3}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;)Landroid/app/FragmentTransaction;

    new-instance v2, Landroid/os/Bundle;

    invoke-direct {v2}, Landroid/os/Bundle;-><init>()V

    iget-wide v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStoryMovieCardId:J

    const-string v5, "card_id"

    invoke-virtual {v2, v5, v3, v4}, Landroid/os/Bundle;->putLong(Ljava/lang/String;J)V

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;

    invoke-virtual {v3, v2}, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;->setArguments(Landroid/os/Bundle;)V

    goto :goto_1

    :cond_2
    invoke-virtual {v0, v2}, Landroid/app/FragmentTransaction;->show(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    :goto_1
    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commit()I

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setSystemBarVisible(Z)V

    :goto_2
    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setForMode()V

    new-instance v0, Landroid/transition/ChangeBounds;

    invoke-direct {v0}, Landroid/transition/ChangeBounds;-><init>()V

    new-instance v2, Lmiui/view/animation/QuarticEaseOutInterpolator;

    invoke-direct {v2}, Lmiui/view/animation/QuarticEaseOutInterpolator;-><init>()V

    invoke-virtual {v0, v2}, Landroid/transition/ChangeBounds;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/transition/Transition;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/miui/gallery/movie/R$integer;->movie_editor_appear_delay:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v2

    int-to-long v2, v2

    invoke-virtual {v0, v2, v3}, Landroid/transition/ChangeBounds;->setStartDelay(J)Landroid/transition/Transition;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/miui/gallery/movie/R$integer;->movie_editor_appear_duration:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v2

    int-to-long v2, v2

    invoke-virtual {v0, v2, v3}, Landroid/transition/ChangeBounds;->setDuration(J)Landroid/transition/Transition;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mRootView:Landroid/view/ViewGroup;

    invoke-static {v2, v0}, Landroid/transition/TransitionManager;->beginDelayedTransition(Landroid/view/ViewGroup;Landroid/transition/Transition;)V

    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    if-ne v0, v1, :cond_3

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_3

    :cond_3
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :goto_3
    return-void
.end method

.method public changeEditorPreview()V
    .locals 4

    iget-boolean v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsEditorPreview:Z

    const/4 v1, 0x1

    xor-int/2addr v0, v1

    iput-boolean v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsEditorPreview:Z

    new-instance v0, Landroid/transition/ChangeBounds;

    invoke-direct {v0}, Landroid/transition/ChangeBounds;-><init>()V

    new-instance v2, Lmiui/view/animation/QuarticEaseInOutInterpolator;

    invoke-direct {v2}, Lmiui/view/animation/QuarticEaseInOutInterpolator;-><init>()V

    invoke-virtual {v0, v2}, Landroid/transition/ChangeBounds;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/transition/Transition;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/miui/gallery/movie/R$integer;->movie_editor_preview_duration:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v2

    int-to-long v2, v2

    invoke-virtual {v0, v2, v3}, Landroid/transition/ChangeBounds;->setDuration(J)Landroid/transition/Transition;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mRootView:Landroid/view/ViewGroup;

    invoke-static {v2, v0}, Landroid/transition/TransitionManager;->beginDelayedTransition(Landroid/view/ViewGroup;Landroid/transition/Transition;)V

    iget-boolean v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsEditorPreview:Z

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuView:Landroid/view/View;

    invoke-direct {p0, v0, v2}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->doEditorPreviewChangeAnimal(Landroid/view/View;Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->showExtraContent(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLoopPlay(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setSeekDisable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setTouchAvailable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v0}, Lcom/miui/gallery/movie/core/MovieManager;->replay()V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMenuView:Landroid/view/View;

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->doEditorPreviewChangeAnimal(Landroid/view/View;Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->showExtraContent(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLoopPlay(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setSeekDisable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mEditorMovieLps:Landroid/widget/RelativeLayout$LayoutParams;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setTouchAvailable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v0}, Lcom/miui/gallery/movie/core/MovieManager;->pause()V

    :goto_0
    return-void
.end method

.method public changeFullScreen(Z)V
    .locals 2

    const/4 v0, 0x1

    if-eqz p1, :cond_0

    move v1, v0

    goto :goto_0

    :cond_0
    const/4 v1, 0x2

    :goto_0
    iput v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    xor-int/lit8 v1, p1, 0x1

    invoke-direct {p0, v1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setSystemBarVisible(Z)V

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuView:Landroid/view/View;

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->doFullScreenChangeAnimal(Landroid/view/View;Z)V

    goto :goto_1

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuView:Landroid/view/View;

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->doFullScreenChangeAnimal(Landroid/view/View;Z)V

    :goto_1
    return-void
.end method

.method public export(Z)V
    .locals 7

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieSavingFragment:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    invoke-direct {v0}, Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieSavingFragment:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieSavingFragment:Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    iget-object v3, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    iget-object v4, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    new-instance v6, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$CQtZj2zxWhbajxzQsLGFPbM-FFo;

    invoke-direct {v6, p0}, Lcom/miui/gallery/movie/ui/activity/-$$Lambda$MovieActivity$CQtZj2zxWhbajxzQsLGFPbM-FFo;-><init>(Lcom/miui/gallery/movie/ui/activity/MovieActivity;)V

    move v5, p1

    invoke-virtual/range {v1 .. v6}, Lcom/miui/gallery/movie/ui/fragment/MovieSavingFragment;->show(Landroid/app/Activity;Lcom/miui/gallery/movie/core/MovieManager;Lcom/miui/gallery/movie/entity/MovieInfo;ZLcom/miui/gallery/movie/ui/fragment/MovieSavingFragment$OnSavingFinishListener;)V

    return-void
.end method

.method public getMovieInfo()Lcom/miui/gallery/movie/entity/MovieInfo;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    return-object v0
.end method

.method public getMovieManager()Lcom/miui/gallery/movie/core/MovieManager;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    return-object v0
.end method

.method public getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;
    .locals 5

    new-instance v0, Lcom/miui/gallery/permission/core/RuntimePermission;

    sget v1, Lcom/miui/gallery/movie/R$string;->permission_storage_desc:I

    invoke-virtual {p0, v1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    const-string v3, "android.permission.WRITE_EXTERNAL_STORAGE"

    invoke-direct {v0, v3, v1, v2}, Lcom/miui/gallery/permission/core/RuntimePermission;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    new-instance v1, Lcom/miui/gallery/permission/core/RuntimePermission;

    const-string v3, "android.permission.INTERNET"

    const-string v4, ""

    invoke-direct {v1, v3, v4, v2}, Lcom/miui/gallery/permission/core/RuntimePermission;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    const/4 v3, 0x2

    new-array v3, v3, [Lcom/miui/gallery/permission/core/RuntimePermission;

    const/4 v4, 0x0

    aput-object v0, v3, v4

    aput-object v1, v3, v2

    return-object v3
.end method

.method public getStoryMovieSha1()Ljava/util/ArrayList;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStorySha1List:Ljava/util/ArrayList;

    return-object v0
.end method

.method public handleShareEvent(Ljava/lang/String;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieShareData:Lcom/miui/gallery/movie/entity/MovieShareData;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-boolean v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->isShortVideo:Z

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/movie/entity/MovieShareData;->setVideoPath(Ljava/lang/String;Z)V

    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->doShare(Ljava/lang/String;)V

    return-void
.end method

.method public synthetic lambda$changeEditor$26$MovieActivity()V
    .locals 1

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setSystemBarVisible(Z)V

    return-void
.end method

.method public synthetic lambda$export$27$MovieActivity(ZZLjava/lang/String;)V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->isDestroyed()Z

    move-result p1

    if-eqz p1, :cond_0

    const-string p1, "MovieActivity"

    const-string p2, "movie activity is finish on saving finish"

    invoke-static {p1, p2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-boolean p1, p1, Lcom/miui/gallery/movie/entity/MovieInfo;->isFromStory:Z

    if-nez p1, :cond_1

    const/4 p1, -0x1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setResult(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->finish()V

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    new-instance p2, Ljava/io/File;

    invoke-direct {p2, p3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {p2}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/miui/gallery/movie/utils/MovieUtils;->goDetail(Landroid/content/Context;Landroid/net/Uri;)V

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->finish()V

    :goto_0
    return-void
.end method

.method public synthetic lambda$initView$21$MovieActivity()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setMovieInfo(Lcom/miui/gallery/movie/entity/MovieInfo;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->updateAfterSetInfo()V

    :cond_0
    return-void
.end method

.method public synthetic lambda$initView$22$MovieActivity(Landroid/view/View;)V
    .locals 2

    iget p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v0, 0x2

    const/4 v1, 0x1

    if-ne p1, v0, :cond_0

    iput v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    invoke-virtual {p0, v1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->changeFullScreen(Z)V

    goto :goto_0

    :cond_0
    if-ne p1, v1, :cond_1

    iput v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->changeFullScreen(Z)V

    goto :goto_0

    :cond_1
    iget-boolean p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsEditorPreview:Z

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->changeEditorPreview()V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {p1}, Lcom/miui/gallery/movie/core/MovieManager;->pauseOrResume()V

    :goto_0
    return-void
.end method

.method public synthetic lambda$initView$23$MovieActivity(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {p1}, Lcom/miui/gallery/movie/core/MovieManager;->resume()V

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->showDeleteIcon(Z)V

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMenuActivityListener:Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

    if-eqz p1, :cond_0

    invoke-interface {p1}, Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;->onResumeClick()V

    :cond_0
    return-void
.end method

.method public synthetic lambda$initView$24$MovieActivity(Z)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMenuActivityListener:Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

    if-eqz p1, :cond_0

    invoke-interface {p1}, Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;->clearEditorAdapterSelected()V

    :cond_0
    return-void
.end method

.method public synthetic lambda$setForMode$25$MovieActivity(Landroid/view/View;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMenuActivityListener:Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

    if-eqz p1, :cond_0

    invoke-interface {p1}, Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;->onDeleteClick()V

    :cond_0
    return-void
.end method

.method public onAttachFragment(Landroid/app/Fragment;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Activity;->onAttachFragment(Landroid/app/Fragment;)V

    instance-of v0, p1, Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMenuActivityListener:Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

    :cond_0
    return-void
.end method

.method public onAttachedToWindow()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onAttachedToWindow()V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->initMovieViewLayout()V

    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-direct {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setSystemBarVisible(Z)V

    return-void
.end method

.method public onBackPressed()V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->returnClick()V

    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 1

    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    sget v0, Lcom/miui/gallery/movie/R$id;->movie_title:I

    if-ne p1, v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->finish()V

    :cond_0
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    sget v0, Lcom/miui/gallery/movie/R$style;->MovieTheme:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setTheme(I)V

    if-eqz p1, :cond_0

    const-string v0, "bundle_movie_info"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;)Landroid/os/Parcelable;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/movie/entity/MovieInfo;

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    const-string v0, "bundle_show_mode"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    :cond_0
    iput-object p0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mActivity:Lcom/miui/gallery/app/Activity;

    invoke-static {p0}, Lcom/miui/gallery/movie/ui/factory/MovieFactory;->createMovieManager(Landroid/content/Context;)Lcom/miui/gallery/movie/core/MovieManager;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Activity;->onCreate(Landroid/os/Bundle;)V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->parseIntent()Z

    move-result p1

    if-nez p1, :cond_1

    return-void

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object p1

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/miui/gallery/util/SystemUiUtil;->setLayoutFullScreen(Landroid/view/View;Z)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-static {p1}, Lcom/android/internal/WindowCompat;->setCutoutModeShortEdges(Landroid/view/Window;)V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->initStoryAlbumData()V

    sget p1, Lcom/miui/gallery/movie/R$layout;->movie_activity:I

    invoke-virtual {p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setContentView(I)V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->configureActionBar()V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->initView()V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->initMode()V

    new-instance p1, Lcom/miui/gallery/movie/utils/AudioFocusHelper;

    invoke-direct {p1, p0}, Lcom/miui/gallery/movie/utils/AudioFocusHelper;-><init>(Landroid/content/Context;)V

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mAudioFocusHelper:Lcom/miui/gallery/movie/utils/AudioFocusHelper;

    return-void
.end method

.method protected onDestroy()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onDestroy()V

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->doDestroy()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    return-void
.end method

.method public onMultiWindowModeChanged(ZLandroid/content/res/Configuration;)V
    .locals 0

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/app/Activity;->onMultiWindowModeChanged(ZLandroid/content/res/Configuration;)V

    if-nez p1, :cond_1

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->initMovieViewLayout()V

    iget p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 p2, 0x2

    if-ne p1, p2, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setSystemBarVisible(Z)V

    :cond_1
    return-void
.end method

.method protected onPause()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onPause()V

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->pause()V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mAudioFocusHelper:Lcom/miui/gallery/movie/utils/AudioFocusHelper;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/movie/utils/AudioFocusHelper;->abandonAudioFocus()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->onPause()V

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->isFinishing()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-direct {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->doDestroy()V

    :cond_2
    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 0

    return-void
.end method

.method protected onResume()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/app/Activity;->onResume()V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->onResume()V

    :cond_0
    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->resume()V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mAudioFocusHelper:Lcom/miui/gallery/movie/utils/AudioFocusHelper;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/movie/utils/AudioFocusHelper;->requestAudioFocus()V

    :cond_1
    return-void
.end method

.method protected onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Activity;->onSaveInstanceState(Landroid/os/Bundle;)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    const-string v1, "bundle_movie_info"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putParcelable(Ljava/lang/String;Landroid/os/Parcelable;)V

    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const-string v1, "bundle_show_mode"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    return-void
.end method

.method public onWindowFocusChanged(Z)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/app/Activity;->onWindowFocusChanged(Z)V

    if-eqz p1, :cond_1

    iget p1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v0, 0x2

    if-ne p1, v0, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setSystemBarVisible(Z)V

    :cond_1
    return-void
.end method

.method public resetShareData()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieShareData:Lcom/miui/gallery/movie/entity/MovieShareData;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-boolean v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->isShortVideo:Z

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/entity/MovieShareData;->reset(Z)V

    :cond_0
    return-void
.end method

.method public returnClick()V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mIsEditorPreview:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->changeEditorPreview()V

    goto :goto_0

    :cond_0
    iget v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mShowMode:I

    const/4 v1, 0x3

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-boolean v0, v0, Lcom/miui/gallery/movie/entity/MovieInfo;->isFromStory:Z

    if-eqz v0, :cond_1

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->setShowDeleteMode(Z)V

    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->changeEditor()V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMenuActivityListener:Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;

    if-eqz v0, :cond_2

    invoke-interface {v0}, Lcom/miui/gallery/movie/ui/listener/MenuActivityListener;->clearEditorAdapterSelected()V

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->finish()V

    :cond_2
    :goto_0
    return-void
.end method

.method public seek(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/core/MovieManager;->seek(I)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v0}, Lcom/miui/gallery/movie/core/MovieManager;->pause()V

    int-to-float v0, p1

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v1}, Lcom/miui/gallery/movie/core/MovieManager;->getTotalTime()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v0, v1

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v1, p1, v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setCurrentPlayTime(IF)V

    return-void
.end method

.method public seekToIndex(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/core/MovieManager;->seekToIndex(I)I

    move-result p1

    int-to-float v0, p1

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieManager:Lcom/miui/gallery/movie/core/MovieManager;

    invoke-virtual {v1}, Lcom/miui/gallery/movie/core/MovieManager;->getTotalTime()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v0, v1

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v1, p1, v0}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setCurrentPlayTime(IF)V

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mPreviewMenuFragment:Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;

    if-eqz v1, :cond_0

    invoke-virtual {v1, v0, p1}, Lcom/miui/gallery/movie/ui/fragment/MoviePreviewMenuFragment;->onProgressChange(FI)V

    :cond_0
    return-void
.end method

.method public setDeleteVisible(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setShowDeleteMode(Z)V

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setDeleteVisible(Z)V

    return-void
.end method

.method public setShowDeleteMode(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->setShowDeleteMode(Z)V

    return-void
.end method

.method public showLoadingView()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieView:Lcom/miui/gallery/movie/ui/view/MovieControllerView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/movie/ui/view/MovieControllerView;->showLoadingView(Z)V

    return-void
.end method

.method public updateShareData(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieShareData:Lcom/miui/gallery/movie/entity/MovieShareData;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/entity/MovieShareData;->setShortVideo(Z)V

    :cond_0
    return-void
.end method

.method public updateStorySha1Data()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStorySha1List:Ljava/util/ArrayList;

    if-nez v0, :cond_0

    const-string v0, "MovieActivity"

    const-string v1, "mStorySha1List is null. "

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->imageList:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_1

    iget-object v1, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mMovieInfo:Lcom/miui/gallery/movie/entity/MovieInfo;

    iget-object v1, v1, Lcom/miui/gallery/movie/entity/MovieInfo;->imageList:Ljava/util/List;

    invoke-interface {v1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/movie/entity/ImageEntity;

    iget-object v1, v1, Lcom/miui/gallery/movie/entity/ImageEntity;->sha1:Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/gallery/movie/ui/activity/MovieActivity;->mStorySha1List:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method
