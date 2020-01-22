.class Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;
.super Ljava/lang/Object;
.source "PhotoPageFragment.java"

# interfaces
.implements Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;
.implements Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;
.implements Lcom/miui/gallery/video/VideoFrameProvider$Listener;
.implements Lcom/miui/gallery/video/VideoFrameSeekBar$OnSeekBarChangeListener;
.implements Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "VideoPreviewManager"
.end annotation


# instance fields
.field private mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

.field private mDragStartTransitionY:F

.field private mDraggingIndex:I

.field private mDurationBar:Landroid/view/ViewGroup;

.field private mDurationBarHideRunnable:Ljava/lang/Runnable;

.field private mDurationBarMargin:I

.field private mDurationTextView:Landroid/widget/TextView;

.field private mEditSubtitleCallback:Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager$Callback;

.field private mIsLeftItemVideo:Z

.field private mIsRightItemVideo:Z

.field private mIsRtl:Z

.field private mLastRequestTime:J

.field private mPagerScrollState:I

.field private mProgressTextView:Landroid/widget/TextView;

.field private mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

.field private mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

.field private mSeekBarAnimListener:Landroid/animation/Animator$AnimatorListener;

.field private mSeekBarRunningAnim:Z

.field private mSeekNeedCallback:Z

.field private mShowDefaultThumb:Z

.field private mSubtitleItems:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
            ">;"
        }
    .end annotation
.end field

.field private mSubtitleProvider:Lcom/miui/gallery/video/VideoSubtitleProvider;

.field private mSubtitleTypeface:Landroid/graphics/Typeface;

.field private mThumbItemHeight:I

.field private mThumbItemWidth:I

.field private mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

.field private mTotalDuration:J

.field private mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPageFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPageFragment;)V
    .locals 4

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekNeedCallback:Z

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDraggingIndex:I

    const/4 v1, 0x0

    iput v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDragStartTransitionY:F

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mPagerScrollState:I

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarRunningAnim:Z

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsLeftItemVideo:Z

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRightItemVideo:Z

    new-instance v1, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager$1;-><init>(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mEditSubtitleCallback:Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager$Callback;

    new-instance v1, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager$2;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager$2;-><init>(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarAnimListener:Landroid/animation/Animator$AnimatorListener;

    new-instance v1, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager$3;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager$3;-><init>(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarHideRunnable:Ljava/lang/Runnable;

    new-instance v1, Lcom/miui/gallery/video/VideoFrameProvider;

    iget-object v2, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-direct {v1, v2}, Lcom/miui/gallery/video/VideoFrameProvider;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {v1, p0}, Lcom/miui/gallery/video/VideoFrameProvider;->addListener(Lcom/miui/gallery/video/VideoFrameProvider$Listener;)V

    new-instance v1, Lcom/miui/gallery/video/VideoSubtitleProvider;

    iget-object v2, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-direct {v1, v2, p0}, Lcom/miui/gallery/video/VideoSubtitleProvider;-><init>(Landroid/content/Context;Lcom/miui/gallery/video/VideoSubtitleProvider$Listener;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleProvider:Lcom/miui/gallery/video/VideoSubtitleProvider;

    iget-object v1, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-static {v1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v1

    const v2, 0x7f0b01d5

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/video/VideoFrameSeekBar;

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f0606db

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    iput v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbItemWidth:I

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f0606da

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    iput v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbItemHeight:I

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f06066b

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    iput v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarMargin:I

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Configuration;->getLayoutDirection()I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_0

    move v0, v2

    :cond_0
    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRtl:Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->setMenuCustomView(Landroid/view/View;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/video/VideoFrameSeekBar;->setOnSeekBarChangeListener(Lcom/miui/gallery/video/VideoFrameSeekBar$OnSeekBarChangeListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->setVisibility(I)V

    invoke-static {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2300(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/util/IntentUtil;->isVideoPlayerSupportEditSubtitle()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2300(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager;

    move-result-object p1

    const-wide/16 v0, 0x4000

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mEditSubtitleCallback:Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager$Callback;

    const/4 v3, 0x2

    invoke-virtual {p1, v0, v1, v2, v3}, Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager;->addTypeCallback(JLcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager$Callback;I)V

    :cond_1
    return-void
.end method

.method static synthetic access$8100(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;)J
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->getSeekTime()J

    move-result-wide v0

    return-wide v0
.end method

.method static synthetic access$8800(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;)Lcom/miui/gallery/video/VideoSubtitleProvider;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleProvider:Lcom/miui/gallery/video/VideoSubtitleProvider;

    return-object p0
.end method

.method static synthetic access$8902(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarRunningAnim:Z

    return p1
.end method

.method static synthetic access$9000(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;)Landroid/view/ViewGroup;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    return-object p0
.end method

.method private dismissDurationBar()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    if-eqz v0, :cond_0

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarHideRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeCallbacks(Ljava/lang/Runnable;)Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->cancel()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->setAlpha(F)V

    :cond_0
    return-void
.end method

.method private dismissSeekBar()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, v1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->updateFrameList(Ljava/util/List;ZZ)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->setVisibility(I)V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    return-void
.end method

.method private generateThumbList(Landroid/graphics/Bitmap;J)Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/graphics/Bitmap;",
            "J)",
            "Ljava/util/List<",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation

    if-nez p1, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    invoke-static {p2, p3}, Lcom/miui/gallery/video/VideoFrameProvider;->getFrameCount(J)I

    move-result p2

    const/4 p3, 0x5

    invoke-static {p2, p3}, Ljava/lang/Math;->max(II)I

    move-result p2

    new-instance p3, Ljava/util/ArrayList;

    invoke-direct {p3, p2}, Ljava/util/ArrayList;-><init>(I)V

    const/4 v0, 0x0

    :goto_0
    if-ge v0, p2, :cond_1

    invoke-virtual {p3, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-object p3
.end method

.method private getSeekTime()J
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getProgress()F

    move-result v0

    iget-wide v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    long-to-float v3, v1

    mul-float/2addr v0, v3

    float-to-int v0, v0

    int-to-long v3, v0

    invoke-static {v3, v4, v1, v2}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v0

    const-wide/16 v2, 0x0

    invoke-static {v2, v3, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    return-wide v0
.end method

.method private hideDurationBar(Z)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getAlpha()F

    move-result v0

    const/4 v1, 0x0

    cmpl-float v0, v0, v1

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarHideRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeCallbacks(Ljava/lang/Runnable;)Z

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarHideRunnable:Ljava/lang/Runnable;

    const-wide/16 v1, 0x0

    invoke-virtual {p1, v0, v1, v2}, Landroid/view/ViewGroup;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarHideRunnable:Ljava/lang/Runnable;

    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    :cond_2
    :goto_0
    return-void
.end method

.method private isValidVideoItem(Lcom/miui/gallery/model/BaseDataItem;)Z
    .locals 1

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->isVideo()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->isSecret()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method private onRequestFrameFailed()V
    .locals 2

    const-string v0, "VideoPreviewManager"

    const-string v1, "onRequestFrameFailed"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->onSeekFinish()V

    return-void
.end method

.method private onSeekFinish()V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekNeedCallback:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$8700(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$8700(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->prepareFinish(Landroid/content/Context;)V

    const-string v0, "VideoPreviewManager"

    const-string v1, "onSeekFinish"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekNeedCallback:Z

    return-void
.end method

.method private requestFrame(J)V
    .locals 8

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    if-eqz v0, :cond_2

    iget-wide v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-lez v4, :cond_2

    cmp-long v2, p1, v2

    if-ltz v2, :cond_2

    cmp-long v0, p1, v0

    if-lez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getPreviewSurface()Landroid/view/Surface;

    move-result-object v7

    if-nez v7, :cond_1

    return-void

    :cond_1
    iput-wide p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mLastRequestTime:J

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;->getWidth()I

    move-result v3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;->getHeight()I

    move-result v4

    move-wide v5, p1

    invoke-virtual/range {v1 .. v7}, Lcom/miui/gallery/video/VideoFrameProvider;->requestSingleFrame(Ljava/lang/String;IIJLandroid/view/Surface;)V

    return-void

    :cond_2
    :goto_0
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->onRequestFrameFailed()V

    return-void
.end method

.method private showDurationBar()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->getView()Landroid/view/View;

    move-result-object v0

    const v1, 0x7f0903a8

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewStub;

    invoke-virtual {v0}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    const v1, 0x7f0903c3

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProgressTextView:Landroid/widget/TextView;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    const v1, 0x7f0903a7

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationTextView:Landroid/widget/TextView;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->setAlpha(F)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->setVisibility(I)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateDuration()V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateDurationBarLayout()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarHideRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeCallbacks(Ljava/lang/Runnable;)Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getAlpha()F

    move-result v0

    const/high16 v1, 0x3f800000    # 1.0f

    cmpg-float v0, v0, v1

    if-gez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->alpha(F)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const-wide/16 v1, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    new-instance v1, Lmiui/view/animation/SineEaseInOutInterpolator;

    invoke-direct {v1}, Lmiui/view/animation/SineEaseInOutInterpolator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->start()V

    :cond_1
    return-void
.end method

.method private startSeekBarTranslateAnim()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getTranslationY()F

    move-result v0

    const/4 v1, 0x0

    cmpl-float v2, v0, v1

    if-lez v2, :cond_0

    iget-boolean v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarRunningAnim:Z

    if-nez v2, :cond_0

    const/high16 v2, 0x3f800000    # 1.0f

    mul-float/2addr v0, v2

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v2}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getHeight()I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v0, v2

    const/high16 v2, 0x437a0000    # 250.0f

    mul-float/2addr v0, v2

    float-to-int v0, v0

    const/16 v2, 0x96

    invoke-static {v0, v2}, Ljava/lang/Math;->max(II)I

    move-result v0

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v2}, Lcom/miui/gallery/video/VideoFrameSeekBar;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v2

    invoke-virtual {v2, v1}, Landroid/view/ViewPropertyAnimator;->translationY(F)Landroid/view/ViewPropertyAnimator;

    move-result-object v1

    int-to-long v2, v0

    invoke-virtual {v1, v2, v3}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const-wide/16 v1, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/view/ViewPropertyAnimator;->setStartDelay(J)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    new-instance v1, Lmiui/view/animation/CubicEaseOutInterpolator;

    invoke-direct {v1}, Lmiui/view/animation/CubicEaseOutInterpolator;-><init>()V

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarAnimListener:Landroid/animation/Animator$AnimatorListener;

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setListener(Landroid/animation/Animator$AnimatorListener;)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->start()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarRunningAnim:Z

    :cond_0
    return-void
.end method

.method private translateSeekBar(ZF)V
    .locals 2

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    iget-boolean v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsLeftItemVideo:Z

    if-nez v1, :cond_2

    :cond_0
    if-nez p1, :cond_1

    iget-boolean p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRightItemVideo:Z

    if-nez p1, :cond_2

    :cond_1
    cmpl-float p1, p2, v0

    if-nez p1, :cond_3

    :cond_2
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->startSeekBarTranslateAnim()V

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDragStartTransitionY:F

    return-void

    :cond_3
    iget-boolean p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarRunningAnim:Z

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/ViewPropertyAnimator;->cancel()V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBarRunningAnim:Z

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getTranslationY()F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDragStartTransitionY:F

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getHeight()I

    move-result p1

    int-to-float p1, p1

    mul-float/2addr p2, p1

    const/high16 v1, 0x40000000    # 2.0f

    mul-float/2addr p2, v1

    iget v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDragStartTransitionY:F

    cmpl-float v0, v1, v0

    if-lez v0, :cond_5

    add-float/2addr v1, p2

    invoke-static {v1, p1}, Ljava/lang/Math;->min(FF)F

    move-result p2

    :cond_5
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/video/VideoFrameSeekBar;->setTranslationY(F)V

    return-void
.end method

.method private updateAndShowSeekBar(Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V
    .locals 6

    const-string v0, "VideoPreviewManager"

    const-string v1, "updateAndShowSeekBar"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    const/4 v1, 0x1

    if-eqz p1, :cond_5

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    if-eqz v2, :cond_1

    invoke-virtual {v2, p1}, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    iget-boolean v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    if-eqz v2, :cond_6

    :cond_1
    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;->getDuration()J

    move-result-wide v2

    iput-wide v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;->getThumbList()Ljava/util/List;

    move-result-object v2

    iget-boolean v3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    invoke-static {v2}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v4

    if-nez v4, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v2}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getVideoThumbnail()Landroid/graphics/Bitmap;

    move-result-object v2

    iget-wide v4, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    invoke-direct {p0, v2, v4, v5}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->generateThumbList(Landroid/graphics/Bitmap;J)Ljava/util/List;

    move-result-object v2

    iput-boolean v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    goto :goto_0

    :cond_2
    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    :goto_0
    iget-object v4, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    iget-boolean v5, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    if-eqz v5, :cond_4

    if-nez v3, :cond_3

    goto :goto_1

    :cond_3
    move v5, v0

    goto :goto_2

    :cond_4
    :goto_1
    move v5, v1

    :goto_2
    xor-int/2addr v1, v3

    invoke-virtual {v4, v2, v5, v1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->updateFrameList(Ljava/util/List;ZZ)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    goto :goto_3

    :cond_5
    iget-boolean p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    if-nez p1, :cond_6

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getDuration()I

    move-result p1

    mul-int/lit16 p1, p1, 0x3e8

    int-to-long v2, p1

    iput-wide v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getVideoThumbnail()Landroid/graphics/Bitmap;

    move-result-object p1

    iget-wide v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    invoke-direct {p0, p1, v2, v3}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->generateThumbList(Landroid/graphics/Bitmap;J)Ljava/util/List;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v2

    iput-boolean v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v2, p1, v1, v1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->updateFrameList(Ljava/util/List;ZZ)V

    :cond_6
    :goto_3
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getVisibility()I

    move-result p1

    if-eqz p1, :cond_7

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/VideoFrameSeekBar;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getHeight()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/VideoFrameSeekBar;->setTranslationY(F)V

    :cond_7
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->startSeekBarTranslateAnim()V

    return-void
.end method

.method private updateDuration()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getProgress()F

    move-result v0

    iget-wide v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    long-to-float v1, v1

    mul-float/2addr v0, v1

    float-to-double v0, v0

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProgressTextView:Landroid/widget/TextView;

    const-wide v3, 0x408f400000000000L    # 1000.0

    div-double/2addr v0, v3

    invoke-static {v0, v1}, Ljava/lang/Math;->floor(D)D

    move-result-wide v0

    double-to-long v0, v0

    invoke-static {v0, v1}, Lcom/miui/gallery/util/FormatUtil;->formatVideoDuration(J)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationTextView:Landroid/widget/TextView;

    iget-wide v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    long-to-double v1, v1

    div-double/2addr v1, v3

    invoke-static {v1, v2}, Ljava/lang/Math;->floor(D)D

    move-result-wide v1

    double-to-long v1, v1

    invoke-static {v1, v2}, Lcom/miui/gallery/util/FormatUtil;->formatVideoDuration(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method private updateDurationBarLayout()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2000(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$PhotoMaskManager;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2000(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$PhotoMaskManager;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/ui/PhotoPageFragment$PhotoMaskManager;->getSplitBarHeight()I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {v2}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getHeight()I

    move-result v2

    add-int/2addr v1, v2

    iget v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBarMargin:I

    add-int/2addr v1, v2

    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDurationBar:Landroid/view/ViewGroup;

    invoke-virtual {v1, v0}, Landroid/view/ViewGroup;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private updateLeftAndRightItemState()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mAdapter:Lcom/miui/gallery/adapter/PhotoPageAdapter;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v0}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mAdapter:Lcom/miui/gallery/adapter/PhotoPageAdapter;

    add-int/lit8 v2, v0, -0x1

    invoke-virtual {v1, v2}, Lcom/miui/gallery/adapter/PhotoPageAdapter;->getDataItem(I)Lcom/miui/gallery/model/BaseDataItem;

    move-result-object v1

    invoke-direct {p0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->isValidVideoItem(Lcom/miui/gallery/model/BaseDataItem;)Z

    move-result v1

    iput-boolean v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsLeftItemVideo:Z

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mAdapter:Lcom/miui/gallery/adapter/PhotoPageAdapter;

    add-int/lit8 v0, v0, 0x1

    invoke-virtual {v1, v0}, Lcom/miui/gallery/adapter/PhotoPageAdapter;->getDataItem(I)Lcom/miui/gallery/model/BaseDataItem;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->isValidVideoItem(Lcom/miui/gallery/model/BaseDataItem;)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRightItemVideo:Z

    iget-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRtl:Z

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsLeftItemVideo:Z

    iget-boolean v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRightItemVideo:Z

    iput-boolean v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsLeftItemVideo:Z

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRightItemVideo:Z

    :cond_1
    :goto_0
    return-void
.end method

.method private updateSubtitleView(J)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    if-eqz v1, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->isSupportSubtitle()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleItems:Ljava/util/List;

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleItems:Ljava/util/List;

    invoke-static {v0, p1, p2}, Lcom/miui/gallery/video/VideoSubtitleProvider;->findActiveSubtitle(Ljava/util/List;J)Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;

    move-result-object p1

    if-nez p1, :cond_1

    const/4 p1, 0x0

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->getText()Ljava/lang/String;

    move-result-object p1

    :goto_0
    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleTypeface:Landroid/graphics/Typeface;

    invoke-virtual {p2, p1, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->updateSubtitleView(Ljava/lang/String;Landroid/graphics/Typeface;)V

    :cond_2
    :goto_1
    return-void
.end method


# virtual methods
.method onActionBarVisibilityChanged(Z)V
    .locals 0

    if-nez p1, :cond_0

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->hideDurationBar(Z)V

    :cond_0
    return-void
.end method

.method public onDestroy()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider;->release()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleProvider:Lcom/miui/gallery/video/VideoSubtitleProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoSubtitleProvider;->release()V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->dismissDurationBar()V

    return-void
.end method

.method onOrientationChanged()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->dismissDurationBar()V

    return-void
.end method

.method public onPageScrollStateChanged(I)V
    .locals 1

    iput p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mPagerScrollState:I

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_0

    return-void

    :cond_0
    if-nez p1, :cond_1

    const-string p1, "VideoPreviewManager"

    const-string v0, "onPageScrollIdle"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->settleItem()V

    goto :goto_0

    :cond_1
    const/4 v0, 0x1

    if-ne p1, v0, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDraggingIndex:I

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p1}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getTranslationY()F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDragStartTransitionY:F

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateLeftAndRightItemState()V

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->hideDurationBar(Z)V

    :cond_2
    :goto_0
    return-void
.end method

.method public onPageScrolled(IFI)V
    .locals 2

    iget-object p3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p3}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getVisibility()I

    move-result p3

    if-eqz p3, :cond_0

    return-void

    :cond_0
    iget-object p3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object p3, p3, Lcom/miui/gallery/ui/PhotoPageFragment;->mPagerHelper:Lcom/miui/gallery/ui/PhotoPagerHelper;

    iget-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRtl:Z

    invoke-virtual {p3, p1, v0}, Lcom/miui/gallery/ui/PhotoPagerHelper;->getCorrectPosition(IZ)I

    move-result p1

    iget p3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDraggingIndex:I

    const/4 v0, 0x1

    if-eq p3, p1, :cond_2

    iget-boolean v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mIsRtl:Z

    if-eqz v1, :cond_1

    add-int/2addr p3, v0

    if-ne p3, p1, :cond_5

    goto :goto_0

    :cond_1
    sub-int/2addr p3, v0

    if-ne p3, p1, :cond_5

    :cond_2
    :goto_0
    iget p3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDraggingIndex:I

    if-eq p3, p1, :cond_3

    goto :goto_1

    :cond_3
    const/4 v0, 0x0

    :goto_1
    iget p3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mDraggingIndex:I

    if-eq p3, p1, :cond_4

    const/high16 p1, 0x3f800000    # 1.0f

    sub-float p2, p1, p2

    :cond_4
    invoke-direct {p0, v0, p2}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->translateSeekBar(ZF)V

    :cond_5
    return-void
.end method

.method public onProgressChanged(F)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateDuration()V

    iget-wide v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    long-to-float v0, v0

    mul-float/2addr p1, v0

    float-to-int p1, p1

    int-to-long v0, p1

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->requestFrame(J)V

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateSubtitleView(J)V

    return-void
.end method

.method public onScrollStateChanged(Z)V
    .locals 2

    const/4 v0, 0x1

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->showDurationBar()V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->onPreviewStart()V

    goto :goto_0

    :cond_0
    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->hideDurationBar(Z)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->onPreviewStop()V

    :cond_1
    :goto_0
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2300(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager;

    move-result-object v1

    if-eqz v1, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2300(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager;

    move-result-object v1

    xor-int/2addr p1, v0

    invoke-virtual {v1, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$SpecialTypeManager;->setEnterVisible(Z)V

    :cond_2
    return-void
.end method

.method public onSingleFrameResponse(Ljava/lang/String;J)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const-string v0, "VideoPreviewManager"

    const-string v1, "onSingleFrameResponse %d"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-wide v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mLastRequestTime:J

    cmp-long p2, p2, v0

    if-nez p2, :cond_0

    const/4 p2, 0x1

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    :goto_0
    invoke-virtual {p1, p2}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->onPreviewUpdate(Z)V

    :cond_1
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->onSeekFinish()V

    return-void
.end method

.method public onStart()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider;->onStart()V

    return-void
.end method

.method public onStop()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/video/VideoFrameProvider;->onStop()V

    return-void
.end method

.method public onSubtitleParsed(Ljava/lang/String;Ljava/util/List;Landroid/graphics/Typeface;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;",
            ">;",
            "Landroid/graphics/Typeface;",
            ")V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_1

    invoke-static {p2}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    iput-object p2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleItems:Ljava/util/List;

    iput-object p3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleTypeface:Landroid/graphics/Typeface;

    iget-wide p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    long-to-float p1, p1

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    invoke-virtual {p2}, Lcom/miui/gallery/video/VideoFrameSeekBar;->getProgress()F

    move-result p2

    mul-float/2addr p1, p2

    float-to-long p1, p1

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateSubtitleView(J)V

    :cond_1
    :goto_0
    return-void
.end method

.method public onSurfacePrepared(Landroid/view/Surface;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0, p1}, Lcom/miui/gallery/video/VideoFrameProvider;->setSurfaceForVideo(Ljava/lang/String;Landroid/view/Surface;)V

    return-void
.end method

.method public onThumbListResponse(Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V
    .locals 2

    const-string v0, "VideoPreviewManager"

    const-string v1, "onThumbListResponse"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    if-eqz p2, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    iget p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mPagerScrollState:I

    if-nez p1, :cond_1

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateAndShowSeekBar(Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public onThumbnailLoaded(Landroid/graphics/Bitmap;)V
    .locals 0

    iget p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mPagerScrollState:I

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateAndShowSeekBar(Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    :cond_0
    return-void
.end method

.method seekTo(JZ)V
    .locals 4

    iput-boolean p3, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekNeedCallback:Z

    iget-wide v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    const-wide/16 v2, 0x0

    cmp-long p3, v0, v2

    if-lez p3, :cond_2

    cmp-long p3, p1, v2

    if-gez p3, :cond_0

    goto :goto_0

    :cond_0
    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->min(JJ)J

    move-result-wide p1

    invoke-static {v2, v3, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    long-to-float p3, p1

    const/high16 v0, 0x3f800000    # 1.0f

    mul-float/2addr p3, v0

    iget-wide v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    long-to-float v1, v1

    div-float/2addr p3, v1

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSeekBar:Lcom/miui/gallery/video/VideoFrameSeekBar;

    const/4 v2, 0x0

    invoke-static {v0, p3}, Ljava/lang/Math;->min(FF)F

    move-result v0

    invoke-static {v2, v0}, Ljava/lang/Math;->max(FF)F

    move-result v0

    invoke-virtual {v1, v0}, Lcom/miui/gallery/video/VideoFrameSeekBar;->setProgress(F)V

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->requestFrame(J)V

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateSubtitleView(J)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->onPreviewStop()V

    :cond_1
    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    iget-wide v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mTotalDuration:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    invoke-static {p3}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p3

    const-string v0, "VideoPreviewManager"

    const-string v1, "seekTo %d, total %d, percent %f"

    invoke-static {v0, v1, p1, p2, p3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    :cond_2
    :goto_0
    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->onSeekFinish()V

    return-void
.end method

.method settleItem()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->dismissSeekBar()V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->dismissDurationBar()V

    return-void

    :cond_0
    const-string v0, "VideoPreviewManager"

    const-string v1, "onSettleItem"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getPreviewSurface()Landroid/view/Surface;

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/VideoFrameProvider;->getThumbListInfo(Ljava/lang/String;)Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    move-result-object v0

    :cond_2
    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateAndShowSeekBar(Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateLeftAndRightItemState()V

    const-wide/16 v0, 0x0

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->updateSubtitleView(J)V

    return-void
.end method

.method updateItem(Lcom/miui/gallery/model/BaseDataItem;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lcom/miui/gallery/video/VideoFrameProvider;->releaseForVideo(Ljava/lang/String;)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setOnSurfacePreparedListener(Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setOnThumbnailLoadedListener(Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;)V

    :cond_1
    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbListInfo:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mShowDefaultThumb:Z

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleItems:Ljava/util/List;

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mSubtitleTypeface:Landroid/graphics/Typeface;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageFragment;->mPagerHelper:Lcom/miui/gallery/ui/PhotoPagerHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPagerHelper;->getCurrentItem()Lcom/miui/gallery/ui/PhotoPageItem;

    move-result-object v0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->isValidVideoItem(Lcom/miui/gallery/model/BaseDataItem;)Z

    move-result v2

    if-eqz v2, :cond_3

    instance-of v2, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;

    if-nez v2, :cond_2

    goto :goto_0

    :cond_2
    const-string v1, "VideoPreviewManager"

    const-string v2, "updateItem"

    invoke-static {v1, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    check-cast v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setOnSurfacePreparedListener(Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setOnThumbnailLoadedListener(Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/VideoFrameProvider;->prepareForVideo(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mProvider:Lcom/miui/gallery/video/VideoFrameProvider;

    invoke-virtual {p1}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object p1

    iget v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbItemWidth:I

    iget v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mThumbItemHeight:I

    invoke-virtual {v0, p1, v1, v2}, Lcom/miui/gallery/video/VideoFrameProvider;->requestThumbList(Ljava/lang/String;II)V

    goto :goto_1

    :cond_3
    :goto_0
    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mCurrentItem:Lcom/miui/gallery/model/BaseDataItem;

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->mVideoPageItem:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    :goto_1
    return-void
.end method
