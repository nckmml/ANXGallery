.class public Lcom/miui/gallery/ui/PhotoPageVideoItem;
.super Lcom/miui/gallery/ui/PhotoPageItem;
.source "PhotoPageVideoItem.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoDownloadManager;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoItemRunnable;,
        Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoCheckManager;
    }
.end annotation


# instance fields
.field private mAntiDoubleClickListener:Lcom/miui/gallery/widget/AntiDoubleClickListener;

.field private mOnSurfacePreparedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;

.field private mOnThumbnailLoadedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;

.field private mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

.field private mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

.field private mThumbnailManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

.field private mVideoIcon:Landroid/view/View;

.field private mVideoItemRunnable:Ljava/lang/Runnable;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageItem;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageVideoItem$2;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$2;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mAntiDoubleClickListener:Lcom/miui/gallery/widget/AntiDoubleClickListener;

    return-void
.end method

.method static synthetic access$1000(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->handNoFileExist()V

    return-void
.end method

.method static synthetic access$1200(Lcom/miui/gallery/ui/PhotoPageVideoItem;)Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mOnSurfacePreparedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;

    return-object p0
.end method

.method static synthetic access$1400(Lcom/miui/gallery/ui/PhotoPageVideoItem;)Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mOnThumbnailLoadedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/PhotoPageVideoItem;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/ui/PhotoPageVideoItem;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->doSetVideoItemVisible(Z)V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/gallery/ui/PhotoPageVideoItem;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setVideoItemVisible(Z)V

    return-void
.end method

.method static synthetic access$700(Lcom/miui/gallery/ui/PhotoPageVideoItem;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->canPlay()Z

    move-result p0

    return p0
.end method

.method static synthetic access$800(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->playVideo()V

    return-void
.end method

.method static synthetic access$900(Lcom/miui/gallery/ui/PhotoPageVideoItem;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setVideoItemVisibleDelay(Z)V

    return-void
.end method

.method private canPlay()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->isPagerSelected()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->hasWindowFocus()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private cancelVideoItemRunnable()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoItemRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMainHandler()Lcom/android/internal/CompatHandler;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoItemRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lcom/android/internal/CompatHandler;->removeCallbacks(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method private doSetVideoItemVisible(Z)V
    .locals 1

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->needShowPlayIcon()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    :goto_0
    return-void
.end method

.method private handNoFileExist()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getDownloadUri()Landroid/net/Uri;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->downloadOrigin()V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getContext()Landroid/content/Context;

    move-result-object v0

    const v1, 0x7f1008ed

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    :goto_0
    return-void
.end method

.method private needShowPlayIcon()Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->isAnimExiting()Z

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDownloadManager:Lcom/miui/gallery/ui/PhotoPageItem$DownloadManager;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDownloadManager:Lcom/miui/gallery/ui/PhotoPageItem$DownloadManager;

    check-cast v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoDownloadManager;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoDownloadManager;->isProgressVisible()Z

    move-result v0

    if-nez v0, :cond_2

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOriginalPath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    :cond_1
    const/4 v0, 0x1

    goto :goto_0

    :cond_2
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private playVideo()V
    .locals 2

    const-string v0, "PhotoPageVideoItem"

    const-string v1, "playVideo"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoPageInteractionListener:Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoPageInteractionListener:Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-interface {v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;->playVideo(Lcom/miui/gallery/model/BaseDataItem;)V

    goto :goto_0

    :cond_0
    const-string v1, "playVideo callback is null"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method private setVideoIconVisibleWithAnim(Z)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    if-eqz p1, :cond_0

    const/high16 p1, 0x3f800000    # 1.0f

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-virtual {v0, p1}, Landroid/view/ViewPropertyAnimator;->alpha(F)Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    const-wide/16 v0, 0x0

    invoke-virtual {p1, v0, v1}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    new-instance v0, Lmiui/view/animation/SineEaseInOutInterpolator;

    invoke-direct {v0}, Lmiui/view/animation/SineEaseInOutInterpolator;-><init>()V

    invoke-virtual {p1, v0}, Landroid/view/ViewPropertyAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/view/ViewPropertyAnimator;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/ViewPropertyAnimator;->start()V

    return-void
.end method

.method private setVideoItemVisible(Z)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->cancelVideoItemRunnable()V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->doSetVideoItemVisible(Z)V

    return-void
.end method

.method private setVideoItemVisibleDelay(Z)V
    .locals 3

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->cancelVideoItemRunnable()V

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoItemRunnable;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoItemRunnable;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;Z)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoItemRunnable:Ljava/lang/Runnable;

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMainHandler()Lcom/android/internal/CompatHandler;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoItemRunnable:Ljava/lang/Runnable;

    const-wide/16 v1, 0xc8

    invoke-virtual {p1, v0, v1, v2}, Lcom/android/internal/CompatHandler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method


# virtual methods
.method public animEnter(Lcom/miui/gallery/util/photoview/ItemViewInfo;Lcom/miui/gallery/ui/PhotoPageItem$TransitionListener;)V
    .locals 1

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$1;

    invoke-direct {v0, p0, p2}, Lcom/miui/gallery/ui/PhotoPageVideoItem$1;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;Lcom/miui/gallery/ui/PhotoPageItem$TransitionListener;)V

    const/4 p2, 0x0

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setVideoItemVisible(Z)V

    invoke-super {p0, p1, v0}, Lcom/miui/gallery/ui/PhotoPageItem;->animEnter(Lcom/miui/gallery/util/photoview/ItemViewInfo;Lcom/miui/gallery/ui/PhotoPageItem$TransitionListener;)V

    return-void
.end method

.method public animExit(Lcom/miui/gallery/util/photoview/ItemViewInfo;Lcom/miui/gallery/ui/PhotoPageItem$TransitionListener;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onExit()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->onExit()V

    :cond_1
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setVideoItemVisible(Z)V

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageItem;->animExit(Lcom/miui/gallery/util/photoview/ItemViewInfo;Lcom/miui/gallery/ui/PhotoPageItem$TransitionListener;)V

    return-void
.end method

.method protected createCheckManager()Lcom/miui/gallery/ui/PhotoPageItem$CheckManager;
    .locals 1

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoCheckManager;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoCheckManager;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V

    return-object v0
.end method

.method protected createDownloadManager()Lcom/miui/gallery/ui/PhotoPageItem$DownloadManager;
    .locals 2

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoDownloadManager;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoDownloadManager;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;Lcom/miui/gallery/ui/PhotoPageVideoItem$1;)V

    return-object v0
.end method

.method protected displayImage(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/assist/ImageSize;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingProgressListener;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->isShowPreview()Z

    move-result v0

    if-nez v0, :cond_1

    :cond_0
    invoke-super/range {p0 .. p5}, Lcom/miui/gallery/ui/PhotoPageItem;->displayImage(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/assist/ImageSize;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingProgressListener;)V

    :cond_1
    return-void
.end method

.method protected doOnMatrixChanged(Landroid/graphics/RectF;)V
    .locals 6

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem;->doOnMatrixChanged(Landroid/graphics/RectF;)V

    const/4 v0, 0x2

    new-array v1, v0, [F

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getWidth()I

    move-result v2

    div-int/2addr v2, v0

    int-to-float v2, v2

    const/4 v3, 0x0

    aput v2, v1, v3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getHeight()I

    move-result v2

    div-int/2addr v2, v0

    int-to-float v2, v2

    const/4 v4, 0x1

    aput v2, v1, v4

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v2}, Luk/co/senab/photoview/PhotoView;->getSuppMatrix()Landroid/graphics/Matrix;

    move-result-object v2

    invoke-virtual {v2, v1}, Landroid/graphics/Matrix;->mapPoints([F)V

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    aget v3, v1, v3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getWidth()I

    move-result v5

    div-int/2addr v5, v0

    int-to-float v5, v5

    sub-float/2addr v3, v5

    invoke-virtual {v2, v3}, Landroid/view/View;->setTranslationX(F)V

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    aget v1, v1, v4

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getHeight()I

    move-result v3

    div-int/2addr v3, v0

    int-to-float v0, v3

    sub-float/2addr v1, v0

    invoke-virtual {v2, v1}, Landroid/view/View;->setTranslationY(F)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onMatrixChanged()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    if-eqz v0, :cond_1

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->onMatrixChanged(Landroid/graphics/RectF;)V

    :cond_1
    return-void
.end method

.method protected doOnSelected(ZZZ)V
    .locals 0

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/ui/PhotoPageItem;->doOnSelected(ZZZ)V

    return-void
.end method

.method protected doOnUnSelected(ZI)V
    .locals 0

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageItem;->doOnUnSelected(ZI)V

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz p2, :cond_0

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onUnSelected(Z)V

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    if-eqz p2, :cond_1

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->onUnSelected(Z)V

    :cond_1
    return-void
.end method

.method public getPreviewSurface()Landroid/view/Surface;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->getSurface()Landroid/view/Surface;

    move-result-object v0

    return-object v0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public getVideoThumbnail()Landroid/graphics/Bitmap;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mThumbnailManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->getVideoThumbnail()Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method protected onActionModeChanged(Z)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem;->onActionModeChanged(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->onActionModeChanged(Z)V

    :cond_0
    return-void
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onConfigurationChanged()V

    :cond_0
    return-void
.end method

.method protected onDetachedFromWindow()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->cancelVideoItemRunnable()V

    invoke-super {p0}, Lcom/miui/gallery/ui/PhotoPageItem;->onDetachedFromWindow()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->access$400(Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;)V

    :cond_0
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mOnSurfacePreparedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mThumbnailManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->release()V

    :cond_1
    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mOnThumbnailLoadedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->release()V

    :cond_2
    return-void
.end method

.method protected onFinishInflate()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/ui/PhotoPageItem;->onFinishInflate()V

    const v0, 0x7f0903c2

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mAntiDoubleClickListener:Lcom/miui/gallery/widget/AntiDoubleClickListener;

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-static {}, Lcom/miui/gallery/util/IntentUtil;->isSupportNewVideoPlayer()Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;Lcom/miui/gallery/ui/PhotoPageVideoItem$1;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mThumbnailManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    invoke-static {}, Lcom/miui/gallery/util/IntentUtil;->isVideoPlayerSupportEditSubtitle()Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;Lcom/miui/gallery/ui/PhotoPageVideoItem$1;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    :cond_0
    return-void
.end method

.method protected onImageLoadFinish(Lcom/miui/gallery/error/core/ErrorCode;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem;->onImageLoadFinish(Lcom/miui/gallery/error/core/ErrorCode;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {p1}, Luk/co/senab/photoview/PhotoView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setVideoItemVisible(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mThumbnailManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->onImageLoadFinish()V

    :cond_1
    return-void
.end method

.method public onPreviewStart()V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setVideoIconVisibleWithAnim(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onPreviewStart()V

    :cond_0
    return-void
.end method

.method public onPreviewStop()V
    .locals 1

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->setVideoIconVisibleWithAnim(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onPreviewStop()V

    :cond_0
    return-void
.end method

.method public onPreviewUpdate(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onPreviewUpdate(Z)V

    :cond_0
    return-void
.end method

.method public onSlipping(F)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem;->onSlipping(F)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->cancel()V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mVideoIcon:Landroid/view/View;

    const/high16 v1, 0x3f800000    # 1.0f

    sub-float/2addr v1, p1

    invoke-virtual {v0, v1}, Landroid/view/View;->setAlpha(F)V

    return-void
.end method

.method public setOnSurfacePreparedListener(Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mOnSurfacePreparedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnSurfacePreparedListener;

    return-void
.end method

.method public setOnThumbnailLoadedListener(Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mOnThumbnailLoadedListener:Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;

    return-void
.end method

.method public swapItem(Lcom/miui/gallery/model/BaseDataItem;)V
    .locals 2

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/model/BaseDataItem;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPreviewManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoPreviewManager;->onUnSelected(Z)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mThumbnailManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->onItemUpdated()V

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    if-eqz v0, :cond_3

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->onUnSelected(Z)V

    :cond_3
    invoke-super {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem;->swapItem(Lcom/miui/gallery/model/BaseDataItem;)V

    return-void
.end method

.method public updateSubtitleView(Ljava/lang/String;Landroid/graphics/Typeface;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mSubtitleManager:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1, p2}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoSubtitleManager;->update(Ljava/lang/String;Landroid/graphics/Typeface;)V

    :cond_0
    return-void
.end method
