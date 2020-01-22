.class public Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;
.super Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;
.source "PhotoDetailFragment.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V<",
        "Lcom/miui/gallery/ui/photodetail/PhotoDetailPresenter;",
        ">;"
    }
.end annotation


# instance fields
.field private isCanEditPhotoDate:Z

.field private isNeedDownLoadOriginPhoto:Z

.field private mClickListener:Landroid/view/View$OnClickListener;

.field private mContentContainer:Landroid/view/View;

.field private mDialogPositiveButtonTextColor:I

.field private mFileInfoItem:Landroid/view/View;

.field private mFileInfoSub:Landroid/widget/TextView;

.field private mFileInfoTitle:Landroid/widget/TextView;

.field private mItem:Lcom/miui/gallery/model/BaseDataItem;

.field private mLoadingProgressDialog:Lmiui/app/ProgressDialog;

.field private mLocation:Landroid/widget/TextView;

.field private mLocationItem:Landroid/view/View;

.field private mParamsItem:Landroid/view/View;

.field private mPath:Landroid/widget/TextView;

.field private mPathItem:Landroid/view/View;

.field private mProgress:Landroid/widget/ProgressBar;

.field private mResultIntent:Landroid/content/Intent;

.field private mScreenshotPackageInfo:Landroid/widget/TextView;

.field private mScreenshotPackageItem:Landroid/view/View;

.field private mSubTitleView:Landroid/widget/TextView;

.field private mTakenParamsSub:Landroid/widget/TextView;

.field private mTakenParamsTitle:Landroid/widget/TextView;

.field private mTextViewHighColor:I

.field private mTimeItem:Landroid/view/View;

.field private mTimeSub:Landroid/widget/TextView;

.field private mTimeTitle:Landroid/widget/TextView;

.field private mTipNoDownload:Landroid/widget/TextView;

.field private mTitleView:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;-><init>()V

    new-instance v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$1;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mClickListener:Landroid/view/View$OnClickListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isCanEditPhotoDate:Z

    return p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isNeedDownLoadOriginPhoto:Z

    return p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->showDownLoadTipDialog()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->showDateTimePicker()V

    return-void
.end method

.method static synthetic access$400(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->downloadOrigin()V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTitleView:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$502(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;Landroid/widget/TextView;)Landroid/widget/TextView;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTitleView:Landroid/widget/TextView;

    return-object p1
.end method

.method static synthetic access$600(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mSubTitleView:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$602(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;Landroid/widget/TextView;)Landroid/widget/TextView;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mSubTitleView:Landroid/widget/TextView;

    return-object p1
.end method

.method static synthetic access$700(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mDialogPositiveButtonTextColor:I

    return p0
.end method

.method static synthetic access$702(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mDialogPositiveButtonTextColor:I

    return p1
.end method

.method private bindDetail(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 1

    :try_start_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindTime(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindFileInfo(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindNotDownloadTip(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindTakenParams(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindPath(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveLocation()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->requestLocation()V

    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindScreenshotPackageInfo(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    invoke-virtual {p1}, Ljava/lang/Exception;->printStackTrace()V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mContentContainer:Landroid/view/View;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mProgress:Landroid/widget/ProgressBar;

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/widget/ProgressBar;->setVisibility(I)V

    return-void
.end method

.method private bindFileInfo(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 3

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveFileInfo()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoTitle:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getFileName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getFileSizeText()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    const/4 v2, 0x1

    if-lez v1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoSub:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoSub:Landroid/widget/TextView;

    invoke-direct {p0, p1, v2}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    move v0, v2

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoSub:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoItem:Landroid/view/View;

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    return-void
.end method

.method private bindNotDownloadTip(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 1

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveDownLoadTip()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTipNoDownload:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getNotDownLoad()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTipNoDownload:Landroid/widget/TextView;

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTipNoDownload:Landroid/widget/TextView;

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    :goto_0
    return-void
.end method

.method private bindPath(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f05010f

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getColor(I)I

    move-result v0

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveFilePath()Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mPath:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getDisplayFilePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mPathItem:Landroid/view/View;

    const/4 v2, 0x1

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isFile()Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mClickListener:Landroid/view/View$OnClickListener;

    iget v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTextViewHighColor:I

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mPathItem:Landroid/view/View;

    const/4 v1, 0x0

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    :cond_1
    const/4 p1, 0x0

    :goto_0
    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mPath:Landroid/widget/TextView;

    invoke-virtual {v1, p1}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mPath:Landroid/widget/TextView;

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setTextColor(Landroid/widget/TextView;I)V

    return-void
.end method

.method private bindScreenshotPackageInfo(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 6

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveCacheLocation()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getFileName()Ljava/lang/String;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v2}, Lcom/miui/gallery/model/BaseDataItem;->getLocalGroupId()J

    move-result-wide v2

    const-wide/16 v4, 0x2

    cmp-long v2, v2, v4

    if-nez v2, :cond_0

    if-eqz v0, :cond_0

    const-string v2, "Screenshot"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mScreenshotPackageInfo:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getCacheLocation()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mScreenshotPackageItem:Landroid/view/View;

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mScreenshotPackageItem:Landroid/view/View;

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mScreenshotPackageItem:Landroid/view/View;

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    :goto_0
    return-void
.end method

.method private bindTakenParams(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 4

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveTakenParams()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTakenParamsTitle:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getPhoneModel()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getTakenParam()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v2

    const/4 v3, 0x1

    if-lez v2, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTakenParamsSub:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTakenParamsSub:Landroid/widget/TextView;

    invoke-direct {p0, p1, v3}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    move v0, v3

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTakenParamsSub:Landroid/widget/TextView;

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mParamsItem:Landroid/view/View;

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    return-void

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mParamsItem:Landroid/view/View;

    invoke-direct {p0, p1, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    return-void
.end method

.method private bindTime(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 1

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->isHaveDateTime()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getDateText()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getTimeText()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, v0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setTimeToView(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeItem:Landroid/view/View;

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    iget-boolean p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isCanEditPhotoDate:Z

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeTitle:Landroid/widget/TextView;

    iget v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTextViewHighColor:I

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setTextColor(Landroid/widget/TextView;I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeSub:Landroid/widget/TextView;

    iget v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTextViewHighColor:I

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setTextColor(Landroid/widget/TextView;I)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeItem:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {p1, v0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeItem:Landroid/view/View;

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method private downloadOrigin()V
    .locals 8

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v2, 0x7f1003ae

    invoke-virtual {v0, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v3, 0x7f1003af

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v3

    const v4, 0x104000a

    const/high16 v5, 0x1040000

    new-instance v6, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$4;

    invoke-direct {v6, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$4;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    new-instance v7, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$5;

    invoke-direct {v7, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$5;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    invoke-static/range {v1 .. v7}, Lcom/miui/gallery/util/DialogUtil;->showInfoDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;IILandroid/content/DialogInterface$OnClickListener;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog;

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->downLoadOrigin()V

    :goto_0
    return-void
.end method

.method private finishActivity(I)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mResultIntent:Landroid/content/Intent;

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mResultIntent:Landroid/content/Intent;

    invoke-virtual {v0, p1, v1}, Landroid/app/Activity;->setResult(ILandroid/content/Intent;)V

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/app/Activity;->setResult(I)V

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-virtual {p1}, Landroid/app/Activity;->finish()V

    return-void
.end method

.method public static newInstance()Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;
    .locals 2

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    new-instance v1, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-direct {v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;-><init>()V

    invoke-virtual {v1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setArguments(Landroid/os/Bundle;)V

    return-object v1
.end method

.method private putResult(Ljava/lang/String;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mResultIntent:Landroid/content/Intent;

    if-nez v0, :cond_0

    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mResultIntent:Landroid/content/Intent;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mResultIntent:Landroid/content/Intent;

    const-string v1, "photo_focused_path"

    invoke-virtual {v0, v1, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    return-void
.end method

.method private setItemVisible(Landroid/view/View;Z)V
    .locals 0

    if-eqz p2, :cond_0

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroid/view/View;->setVisibility(I)V

    goto :goto_0

    :cond_0
    const/16 p2, 0x8

    invoke-virtual {p1, p2}, Landroid/view/View;->setVisibility(I)V

    :goto_0
    return-void
.end method

.method private setTextColor(Landroid/widget/TextView;I)V
    .locals 0

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setTextColor(I)V

    return-void
.end method

.method private setTimeToView(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeTitle:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeSub:Landroid/widget/TextView;

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method private showDateTimePicker()V
    .locals 3

    new-instance v0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->showHour()Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->enable24Hour()Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    const/16 v1, 0x14

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->setPickerItemLabelSize(I)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->getDatePickerStartTime()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->setStartTime(J)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->setMaxTime(J)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    const v1, 0x7f0b0093

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->setTitleView(I)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    sget-boolean v1, Lcom/miui/os/Rom;->IS_INTERNATIONAL:Z

    xor-int/lit8 v1, v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->setIsShowLabel(Z)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->setOnDateSetListener(Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->create()Lcom/miui/widget/picker/DateTimePickerDialog;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$9;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$9;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    new-instance v1, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$10;

    invoke-direct {v1, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$10;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setOnShowListener(Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;)V

    invoke-virtual {v0}, Lcom/miui/widget/picker/DateTimePickerDialog;->show()V

    return-void
.end method

.method private showDownLoadTipDialog()V
    .locals 9

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->canDownLoad()Z

    move-result v0

    if-nez v0, :cond_1

    return-void

    :cond_1
    const-string v0, "PhotoDetailFragment"

    const-string v1, "show DownLoad Tip Dialog"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v2

    const v3, 0x7f1003c6

    const v4, 0x7f1003c7

    const v5, 0x7f100913

    const v6, 0x7f1005df

    new-instance v7, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$2;

    invoke-direct {v7, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$2;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    new-instance v8, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$3;

    invoke-direct {v8, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$3;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    invoke-static/range {v2 .. v8}, Lcom/miui/gallery/util/DialogUtil;->showInfoDialog(Landroid/content/Context;IIIILandroid/content/DialogInterface$OnClickListener;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog;

    return-void
.end method


# virtual methods
.method public bindLocation(Ljava/lang/String;)V
    .locals 1

    invoke-static {p1}, Lcom/miui/gallery/util/StringUtils;->isEmpty(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLocation:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLocationItem:Landroid/view/View;

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLocationItem:Landroid/view/View;

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->setItemVisible(Landroid/view/View;Z)V

    :goto_0
    return-void
.end method

.method public getLayoutId()I
    .locals 1

    const v0, 0x7f0b011f

    return v0
.end method

.method public initView(Landroid/view/View;Landroid/os/Bundle;Landroid/view/View;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-virtual {p2}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object p2

    invoke-virtual {p2}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object p2

    if-eqz p2, :cond_0

    const-string v0, "photo_detail_target"

    invoke-virtual {p2, v0}, Landroid/os/Bundle;->getSerializable(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/model/BaseDataItem;

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mItem:Lcom/miui/gallery/model/BaseDataItem;

    if-nez v0, :cond_1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->finish()V

    :cond_1
    const/4 v0, 0x0

    const-string v1, "photodetail_is_photo_datetime_editable"

    invoke-virtual {p2, v1, v0}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isCanEditPhotoDate:Z

    const-string v0, "photo_detail_is_need_download_originphoto"

    invoke-virtual {p2, v0}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;)Z

    move-result p2

    iput-boolean p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isNeedDownLoadOriginPhoto:Z

    invoke-static {p3}, Lcom/miui/gallery/util/ViewUtils;->setRootViewClickable(Landroid/view/View;)V

    const p2, 0x7f09035b

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeItem:Landroid/view/View;

    const p2, 0x7f09035d

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeTitle:Landroid/widget/TextView;

    const p2, 0x7f09035c

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTimeSub:Landroid/widget/TextView;

    const p2, 0x7f090151

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoItem:Landroid/view/View;

    const p2, 0x7f090154

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoTitle:Landroid/widget/TextView;

    const p2, 0x7f090362

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTipNoDownload:Landroid/widget/TextView;

    const p2, 0x7f090153

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mFileInfoSub:Landroid/widget/TextView;

    const p2, 0x7f090250

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mParamsItem:Landroid/view/View;

    const p2, 0x7f090253

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTakenParamsTitle:Landroid/widget/TextView;

    const p2, 0x7f090251

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTakenParamsSub:Landroid/widget/TextView;

    const p2, 0x7f090256

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mPathItem:Landroid/view/View;

    const p2, 0x7f090257

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mPath:Landroid/widget/TextView;

    const p2, 0x7f0901f3

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLocationItem:Landroid/view/View;

    const p2, 0x7f0901f4

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLocation:Landroid/widget/TextView;

    const p2, 0x7f0900d2

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mContentContainer:Landroid/view/View;

    const p2, 0x7f090277

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/ProgressBar;

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mProgress:Landroid/widget/ProgressBar;

    const p2, 0x7f0902ce

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mScreenshotPackageItem:Landroid/view/View;

    const p2, 0x7f0902cf

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mScreenshotPackageInfo:Landroid/widget/TextView;

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLocation:Landroid/widget/TextView;

    iget-object p2, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const p2, 0x7f05010c

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getColor(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mTextViewHighColor:I

    return-void
.end method

.method public onActivityCreated(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$V;->onActivityCreated(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->init(Lcom/miui/gallery/model/BaseDataItem;)V

    return-void
.end method

.method public onBackPressed()Z
    .locals 1

    const/4 v0, -0x1

    invoke-direct {p0, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->finishActivity(I)V

    const/4 v0, 0x0

    return v0
.end method

.method public showDetailInfo(ZLcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isCanEditPhotoDate:Z

    if-eqz v0, :cond_0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isCanEditPhotoDate:Z

    :cond_0
    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindDetail(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V

    return-void
.end method

.method public showDownLoadFailed()V
    .locals 7

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f1003a4

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f1003a6

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v2

    new-instance v5, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$6;

    invoke-direct {v5, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$6;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    new-instance v6, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$7;

    invoke-direct {v6, p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$7;-><init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V

    const v3, 0x7f1003a5

    const/high16 v4, 0x1040000

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/DialogUtil;->showInfoDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;IILandroid/content/DialogInterface$OnClickListener;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog;

    return-void
.end method

.method public showDownLoadProgressDialog(Ljava/util/ArrayList;Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/util/BulkDownloadHelper$BulkDownloadItem;",
            ">;",
            "Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;",
            ")V"
        }
    .end annotation

    const-string v0, "PhotoDetailFragment"

    const-string v1, "show DownLoad Dialog"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {p1}, Lcom/miui/gallery/ui/DownloadProgressFragment;->newInstance(Ljava/util/ArrayList;)Lcom/miui/gallery/ui/DownloadProgressFragment;

    move-result-object p1

    invoke-virtual {p1, p2}, Lcom/miui/gallery/ui/DownloadFragment;->setOnDownloadListener(Lcom/miui/gallery/ui/DownloadFragment$OnDownloadListener;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p2

    const-string v0, "DownloadFragment"

    invoke-virtual {p1, p2, v0}, Lcom/miui/gallery/ui/DownloadFragment;->showAllowingStateLoss(Landroid/app/FragmentManager;Ljava/lang/String;)V

    return-void
.end method

.method public showDownLoadSuccess(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V
    .locals 3

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getDisplayFilePath()Ljava/lang/String;

    move-result-object v0

    const-string v1, "PhotoDetailFragment"

    const-string v2, "DownLoad %s is Success"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->isNeedDownLoadOriginPhoto:Z

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->bindFileInfo(Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->showDateTimePicker()V

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/viewbean/PhotoDetailViewBean;->getDisplayFilePath()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->putResult(Ljava/lang/String;)V

    return-void
.end method

.method public showLoadingDialog()V
    .locals 2

    new-instance v0, Lmiui/app/ProgressDialog;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {v0, v1}, Lmiui/app/ProgressDialog;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLoadingProgressDialog:Lmiui/app/ProgressDialog;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLoadingProgressDialog:Lmiui/app/ProgressDialog;

    const v1, 0x7f10066a

    invoke-virtual {p0, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lmiui/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLoadingProgressDialog:Lmiui/app/ProgressDialog;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/app/ProgressDialog;->setCancelable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLoadingProgressDialog:Lmiui/app/ProgressDialog;

    invoke-virtual {v0, v1}, Lmiui/app/ProgressDialog;->setCanceledOnTouchOutside(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLoadingProgressDialog:Lmiui/app/ProgressDialog;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/app/ProgressDialog;->setIndeterminate(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLoadingProgressDialog:Lmiui/app/ProgressDialog;

    invoke-virtual {v0}, Lmiui/app/ProgressDialog;->show()V

    return-void
.end method

.method showUpdateDateTimeResult(Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->mLoadingProgressDialog:Lmiui/app/ProgressDialog;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lmiui/app/ProgressDialog;->dismiss()V

    :cond_0
    if-eqz p1, :cond_1

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->putResult(Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object p1

    const v0, 0x7f100374

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->onBackPressed()Z

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getSafeActivity()Landroid/app/Activity;

    move-result-object p1

    const v0, 0x7f100373

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    :goto_0
    return-void
.end method
