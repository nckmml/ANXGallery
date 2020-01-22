.class public Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;
.super Landroid/app/Activity;
.source "ScreenEditorActivity.java"

# interfaces
.implements Lcom/miui/gallery/permission/core/PermissionCheckCallback;


# instance fields
.field private mActivity:Landroid/app/Activity;

.field private mAnimatorViewCallback:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper$AnimatorViewCallback;

.field private mBackView:Landroid/widget/ImageView;

.field private mBottomLayoutView:Landroid/widget/FrameLayout;

.field private mChooserFragment:Landroid/app/Fragment;

.field private mCommonLine:Landroidx/constraintlayout/widget/Guideline;

.field private mCompleteListener:Lcom/miui/gallery/ui/ProcessTask$OnCompleteListener;

.field private mCurrentBottomFragment:Landroid/app/Fragment;

.field private mDecoderCallbacks:Lcom/miui/gallery/editor/photo/app/InitializeController$Callbacks;

.field private mDeleteDialogClickListener:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment$DialogClickListener;

.field private mDeleteView:Landroid/view/View;

.field private mDoodleFragment:Landroid/app/Fragment;

.field private mDraftManager:Lcom/miui/gallery/editor/photo/app/DraftManager;

.field private mEditBottomLine:Landroidx/constraintlayout/widget/Guideline;

.field private mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

.field private mEditTopLine:Landroidx/constraintlayout/widget/Guideline;

.field private mEditViewLayoutView:Landroid/widget/FrameLayout;

.field private mExportCallbacks:Lcom/miui/gallery/editor/photo/app/ExportFragment$Callbacks;

.field private mExportTask:Lcom/miui/gallery/editor/photo/app/ExportTask;

.field private mFinishEditorView:Landroid/view/View;

.field private mInitializeController:Lcom/miui/gallery/editor/photo/app/InitializeController;

.field private mIsFromLongScreen:Z

.field private mIsFromSendMode:Z

.field private mIsLongCropShow:Z

.field private mIsLongScreenMode:Z

.field private mIsMenuExpand:Z

.field private mIsShared:Z

.field private mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

.field private mLongCropLayout:Landroid/view/View;

.field private mLongScreenCropCallback:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment$ScreenLongCropCallback;

.field private mMenuBottomLine:Landroidx/constraintlayout/widget/Guideline;

.field private mMenuLayoutView:Landroid/widget/FrameLayout;

.field private mMenuTopLine:Landroidx/constraintlayout/widget/Guideline;

.field private mMosaicFragment:Landroid/app/Fragment;

.field private mNavFragment:Landroid/app/Fragment;

.field private mNeedExported:Z

.field private mOnClickListener:Landroid/view/View$OnClickListener;

.field private mOnFilesProcessedListener:Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;

.field private mOnIntentSelectedListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

.field private mOnMishareClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

.field private mPageMode:I

.field private mRevertView:Landroid/view/View;

.field private mRevokeView:Landroid/view/View;

.field private mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

.field private mScreenDeleteDialogFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;

.field private mScreenEditBottomCallback:Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment$Callback;

.field private mScreenEditorFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorFragment;

.field private mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

.field private mScreenLongCropFragment:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

.field private mScreenRenderCallback:Lcom/miui/gallery/editor/photo/screen/base/ScreenRenderCallback;

.field private mScreenRenderManager:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

.field private mScreenRootBg:Landroid/widget/FrameLayout;

.field private mScreenShareView:Lcom/miui/gallery/editor/photo/screen/home/ScreenShareView;

.field private mShareBottomLine:Landroidx/constraintlayout/widget/Guideline;

.field private mShareLayoutView:Landroid/widget/FrameLayout;

.field private mSharePendingIntent:Landroid/content/Intent;

.field private mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

.field private mShareTopLine:Landroidx/constraintlayout/widget/Guideline;

.field private mSingleClickListener:Lcom/miui/gallery/listener/SingleClickListener;

.field private mTextFragment:Landroid/app/Fragment;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mNeedExported:Z

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$3;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnClickListener:Landroid/view/View$OnClickListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$4;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSingleClickListener:Lcom/miui/gallery/listener/SingleClickListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$5;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$5;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDeleteDialogClickListener:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment$DialogClickListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$3yDqiaitReGPos_ELLnTmM8IJiY;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$3yDqiaitReGPos_ELLnTmM8IJiY;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCompleteListener:Lcom/miui/gallery/ui/ProcessTask$OnCompleteListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$7;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$7;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDecoderCallbacks:Lcom/miui/gallery/editor/photo/app/InitializeController$Callbacks;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$8;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$8;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mExportCallbacks:Lcom/miui/gallery/editor/photo/app/ExportFragment$Callbacks;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$9;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$9;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongScreenCropCallback:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment$ScreenLongCropCallback;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$10;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mAnimatorViewCallback:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper$AnimatorViewCallback;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$11;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$11;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditBottomCallback:Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment$Callback;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$12;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$12;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnIntentSelectedListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$13;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$13;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnMishareClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$15;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$15;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderCallback:Lcom/miui/gallery/editor/photo/screen/base/ScreenRenderCallback;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    return-object p0
.end method

.method static synthetic access$1000(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->deleteSourceFileAndFinish()V

    return-void
.end method

.method static synthetic access$1100(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroid/app/Activity;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    return-object p0
.end method

.method static synthetic access$1200(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/ui/ProcessTask$OnCompleteListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCompleteListener:Lcom/miui/gallery/ui/ProcessTask$OnCompleteListener;

    return-object p0
.end method

.method static synthetic access$1300(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/app/DraftManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDraftManager:Lcom/miui/gallery/editor/photo/app/DraftManager;

    return-object p0
.end method

.method static synthetic access$1400(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/home/ScreenShareView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenShareView:Lcom/miui/gallery/editor/photo/screen/home/ScreenShareView;

    return-object p0
.end method

.method static synthetic access$1500(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromLongScreen:Z

    return p0
.end method

.method static synthetic access$1602(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsLongCropShow:Z

    return p1
.end method

.method static synthetic access$1700(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->showLongCropFragment()V

    return-void
.end method

.method static synthetic access$1800(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isFromNormalEdit()Z

    move-result p0

    return p0
.end method

.method static synthetic access$1900(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/app/ExportTask;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mExportTask:Lcom/miui/gallery/editor/photo/app/ExportTask;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->checkAndDoRender(Z)V

    return-void
.end method

.method static synthetic access$2000(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderManager:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    return-object p0
.end method

.method static synthetic access$2100(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Landroid/net/Uri;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->parseUriAndShare(Landroid/net/Uri;)V

    return-void
.end method

.method static synthetic access$2200(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroid/widget/FrameLayout;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBottomLayoutView:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method static synthetic access$2300(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/ConstraintLayout;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    return-object p0
.end method

.method static synthetic access$2400(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/ConstraintLayout;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    return-object p0
.end method

.method static synthetic access$2500(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroid/widget/FrameLayout;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRootBg:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method static synthetic access$2600(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroid/widget/FrameLayout;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMenuLayoutView:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method static synthetic access$2700(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareBottomLine:Landroidx/constraintlayout/widget/Guideline;

    return-object p0
.end method

.method static synthetic access$2800(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLine:Landroidx/constraintlayout/widget/Guideline;

    return-object p0
.end method

.method static synthetic access$2900(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCommonLine:Landroidx/constraintlayout/widget/Guideline;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->updateLongScreenPreviewShow()V

    return-void
.end method

.method static synthetic access$3000(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMenuBottomLine:Landroidx/constraintlayout/widget/Guideline;

    return-object p0
.end method

.method static synthetic access$3100(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMenuTopLine:Landroidx/constraintlayout/widget/Guideline;

    return-object p0
.end method

.method static synthetic access$3200(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditBottomLine:Landroidx/constraintlayout/widget/Guideline;

    return-object p0
.end method

.method static synthetic access$3300(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Landroidx/constraintlayout/widget/Guideline;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLine:Landroidx/constraintlayout/widget/Guideline;

    return-object p0
.end method

.method static synthetic access$3400(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    return p0
.end method

.method static synthetic access$3500(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->hideMenuFragment()V

    return-void
.end method

.method static synthetic access$3600(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenLongCropFragment:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

    return-object p0
.end method

.method static synthetic access$3700(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    return-object p0
.end method

.method static synthetic access$3800(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Landroidx/recyclerview/widget/RecyclerView;Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;II)V
    .locals 0

    invoke-direct {p0, p1, p2, p3, p4}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->showEditorNav(Landroidx/recyclerview/widget/RecyclerView;Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;II)V

    return-void
.end method

.method static synthetic access$3902(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Landroid/content/Intent;)Landroid/content/Intent;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    return-object p1
.end method

.method static synthetic access$400(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsLongScreenMode:Z

    return p0
.end method

.method static synthetic access$4002(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;)Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnFilesProcessedListener:Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;

    return-object p1
.end method

.method static synthetic access$4100(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnIntentSelectedListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    return-object p0
.end method

.method static synthetic access$4200(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Landroid/net/Uri;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->doShare(Landroid/net/Uri;)V

    return-void
.end method

.method static synthetic access$4300(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mNeedExported:Z

    return p0
.end method

.method static synthetic access$500(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isFromNormalShare()Z

    move-result p0

    return p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    return p0
.end method

.method static synthetic access$602(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    return p1
.end method

.method static synthetic access$700(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenDeleteDialogFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;

    return-object p0
.end method

.method static synthetic access$702(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;)Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenDeleteDialogFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;

    return-object p1
.end method

.method static synthetic access$800(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment$DialogClickListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDeleteDialogClickListener:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment$DialogClickListener;

    return-object p0
.end method

.method static synthetic access$900(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->doExportTask()V

    return-void
.end method

.method private checkAndDoRender(Z)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;->isModifiedBaseLast()Z

    move-result v0

    if-eqz v0, :cond_0

    move v0, v1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    invoke-interface {v2}, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;->isModifiedBaseLast()Z

    move-result v2

    if-nez v2, :cond_2

    if-eqz v0, :cond_1

    goto :goto_1

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderCallback:Lcom/miui/gallery/editor/photo/screen/base/ScreenRenderCallback;

    invoke-interface {v0, p1}, Lcom/miui/gallery/editor/photo/screen/base/ScreenRenderCallback;->onComplete(Z)V

    goto :goto_2

    :cond_2
    :goto_1
    iput-boolean v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mNeedExported:Z

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderManager:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getRenderData()Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderCallback:Lcom/miui/gallery/editor/photo/screen/base/ScreenRenderCallback;

    invoke-virtual {v0, p1, v1, v2}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;->renderBitmap(ZLcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;Lcom/miui/gallery/editor/photo/screen/base/ScreenRenderCallback;)V

    :goto_2
    return-void
.end method

.method private deleteSourceFileAndFinish()V
    .locals 3

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$IQJc1_McaLXM99MIAvKNRkrt8y4;

    invoke-direct {v1, p0}, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$IQJc1_McaLXM99MIAvKNRkrt8y4;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    new-instance v2, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$6;

    invoke-direct {v2, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$6;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;Lcom/miui/gallery/threadpool/FutureListener;)Lcom/miui/gallery/threadpool/Future;

    return-void
.end method

.method private doExportTask()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->hasEdited()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->checkAndDoRender(Z)V

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isSharePage()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mExportTask:Lcom/miui/gallery/editor/photo/app/ExportTask;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/ExportTask;->getSource()Landroid/net/Uri;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->parseUriAndShare(Landroid/net/Uri;)V

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->finish()V

    :goto_0
    return-void
.end method

.method private doInitialize()V
    .locals 2

    new-instance v0, Lcom/miui/gallery/editor/photo/app/InitializeController;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDecoderCallbacks:Lcom/miui/gallery/editor/photo/app/InitializeController$Callbacks;

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/editor/photo/app/InitializeController;-><init>(Landroid/app/Activity;Lcom/miui/gallery/editor/photo/app/InitializeController$Callbacks;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mInitializeController:Lcom/miui/gallery/editor/photo/app/InitializeController;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mInitializeController:Lcom/miui/gallery/editor/photo/app/InitializeController;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/InitializeController;->doInitialize()V

    return-void
.end method

.method private doShare(Landroid/net/Uri;)V
    .locals 6

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsShared:Z

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    if-eqz v1, :cond_4

    if-nez p1, :cond_0

    goto/16 :goto_1

    :cond_0
    invoke-virtual {v1}, Landroid/content/Intent;->getComponent()Landroid/content/ComponentName;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/ComponentName;->getPackageName()Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    iget-object v3, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    invoke-static {v2, v3}, Lcom/miui/gallery/provider/GalleryOpenProvider;->needReturnContentUri(Landroid/content/Context;Landroid/content/Intent;)Z

    move-result v2

    const-string v3, "com.miui.xman"

    const/4 v4, 0x1

    if-eqz v2, :cond_1

    invoke-virtual {p1}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/provider/GalleryOpenProvider;->translateToContent(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_1

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    invoke-virtual {v2, v1, p1, v4}, Landroid/app/Activity;->grantUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    invoke-virtual {v2, v3, p1, v4}, Landroid/app/Activity;->grantUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V

    :cond_1
    const-string v2, "com.miui.mishare.connectivity"

    invoke-virtual {v2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    const/4 v5, 0x0

    if-eqz v2, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnFilesProcessedListener:Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;

    new-array v2, v4, [Landroid/net/Uri;

    aput-object p1, v2, v0

    invoke-static {v2}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    invoke-interface {v1, p1}, Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;->onFilesProcessed(Ljava/util/List;)V

    iput-object v5, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    return-void

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    const-string v2, "android.intent.action.SEND"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    const-string v2, "android.intent.extra.STREAM"

    invoke-virtual {v0, v2, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    const/high16 v0, 0x10000000

    invoke-virtual {p1, v0}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    const/high16 v0, 0x8080000

    invoke-virtual {p1, v0}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    new-instance p1, Landroid/content/Intent;

    const-string v0, "miui.intent.action.XMAN_SHARE_MANAGER"

    invoke-direct {p1, v0}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, v3}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    const-string v0, "com.tencent.mm"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    sget-object v0, Lcom/miui/gallery/util/IntentUtil;->IS_XMAN_AVAILABLE:Lcom/miui/gallery/util/LazyValue;

    invoke-virtual {v0, v5}, Lcom/miui/gallery/util/LazyValue;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    const-string v1, "android.intent.extra.INTENT"

    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    invoke-virtual {p0, p1, v4}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->startActivityForResult(Landroid/content/Intent;I)V

    goto :goto_0

    :cond_3
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSharePendingIntent:Landroid/content/Intent;

    invoke-virtual {p0, p1, v4}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->startActivityForResult(Landroid/content/Intent;I)V

    :goto_0
    return-void

    :cond_4
    :goto_1
    const-string p1, "ScreenEditorActivity"

    const-string v0, "share error."

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private getFilePathWithUri(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;
    .locals 3

    const-string v0, ""

    if-nez p2, :cond_0

    return-object v0

    :cond_0
    invoke-virtual {p2}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->ofUri(Ljava/lang/String;)Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    move-result-object v1

    sget-object v2, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->FILE:Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    if-ne v1, v2, :cond_1

    sget-object p1, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->FILE:Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    invoke-virtual {p2}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->crop(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_1
    sget-object v2, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->CONTENT:Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    if-ne v1, v2, :cond_2

    invoke-static {p1, p2}, Lcom/miui/gallery/util/ContentUtils;->getValidFilePathForContentUri(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_2
    return-object v0
.end method

.method private hasEdited()Z
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;->onExport()Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    invoke-interface {v0}, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;->onExport()Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;->isModified()Z

    move-result v0

    if-eqz v0, :cond_0

    move v0, v1

    goto :goto_0

    :cond_0
    move v0, v2

    :goto_0
    iget-object v3, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    if-eqz v3, :cond_1

    invoke-interface {v3}, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;->isModified()Z

    move-result v3

    if-eqz v3, :cond_1

    move v3, v1

    goto :goto_1

    :cond_1
    move v3, v2

    :goto_1
    if-nez v0, :cond_3

    if-eqz v3, :cond_2

    goto :goto_2

    :cond_2
    move v1, v2

    :cond_3
    :goto_2
    return v1
.end method

.method private hideMenuFragment()V
    .locals 2

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    iget-boolean v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->startMenuAnimator(Z)V

    return-void
.end method

.method private initScreenAnimatorHelper()V
    .locals 7

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getIntent()Landroid/content/Intent;

    move-result-object v1

    const-string v2, "ThumbnailRect"

    invoke-virtual {v1, v2}, Landroid/content/Intent;->getIntArrayExtra(Ljava/lang/String;)[I

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mAnimatorViewCallback:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper$AnimatorViewCallback;

    invoke-direct {v0, p0, v1, v2}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;[ILcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper$AnimatorViewCallback;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isFromNormalEdit()Z

    move-result v0

    const/4 v1, 0x1

    const/4 v2, 0x0

    const/4 v3, 0x2

    const/4 v4, 0x0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    new-array v3, v3, [Landroid/view/View;

    iget-object v5, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRootBg:Landroid/widget/FrameLayout;

    aput-object v5, v3, v2

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBottomLayoutView:Landroid/widget/FrameLayout;

    aput-object v2, v3, v1

    invoke-virtual {v0, v4, v3}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->setViewsAlpha(F[Landroid/view/View;)V

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isFromNormalShare()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    const/4 v5, 0x3

    new-array v5, v5, [Landroid/view/View;

    iget-object v6, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRootBg:Landroid/widget/FrameLayout;

    aput-object v6, v5, v2

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBottomLayoutView:Landroid/widget/FrameLayout;

    aput-object v2, v5, v1

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    aput-object v1, v5, v3

    invoke-virtual {v0, v4, v5}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->setViewsAlpha(F[Landroid/view/View;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private initView()V
    .locals 4

    const v0, 0x7f0902c2

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDeleteView:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDeleteView:Landroid/view/View;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSingleClickListener:Lcom/miui/gallery/listener/SingleClickListener;

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const v0, 0x7f0902c7

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mFinishEditorView:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mFinishEditorView:Landroid/view/View;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mSingleClickListener:Lcom/miui/gallery/listener/SingleClickListener;

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const v0, 0x7f090243

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevokeView:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevokeView:Landroid/view/View;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const v0, 0x7f090242

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevertView:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevertView:Landroid/view/View;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const v0, 0x7f0902cc

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRootBg:Landroid/widget/FrameLayout;

    const v0, 0x7f0902c3

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/ConstraintLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    const v0, 0x7f090080

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBottomLayoutView:Landroid/widget/FrameLayout;

    const v0, 0x7f090379

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroidx/constraintlayout/widget/ConstraintLayout;

    iput-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    const v1, 0x7f0902c5

    invoke-virtual {p0, v1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/FrameLayout;

    iput-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditViewLayoutView:Landroid/widget/FrameLayout;

    const v2, 0x7f0902c8

    invoke-virtual {p0, v2}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    iput-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropLayout:Landroid/view/View;

    const v2, 0x7f0902c9

    invoke-virtual {p0, v2}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/FrameLayout;

    iput-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMenuLayoutView:Landroid/widget/FrameLayout;

    const v2, 0x7f090302

    invoke-virtual {p0, v2}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/editor/photo/screen/home/ScreenShareView;

    iput-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenShareView:Lcom/miui/gallery/editor/photo/screen/home/ScreenShareView;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/ConstraintLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    const v0, 0x7f090303

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareLayoutView:Landroid/widget/FrameLayout;

    const v0, 0x7f090009

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBackView:Landroid/widget/ImageView;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBackView:Landroid/widget/ImageView;

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const v0, 0x7f090301

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLine:Landroidx/constraintlayout/widget/Guideline;

    const v0, 0x7f0902fe

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareBottomLine:Landroidx/constraintlayout/widget/Guideline;

    const v0, 0x7f0900cb

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCommonLine:Landroidx/constraintlayout/widget/Guideline;

    const v0, 0x7f090216

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMenuTopLine:Landroidx/constraintlayout/widget/Guideline;

    const v0, 0x7f0901fc

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMenuBottomLine:Landroidx/constraintlayout/widget/Guideline;

    const v0, 0x7f090083

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditBottomLine:Landroidx/constraintlayout/widget/Guideline;

    const v0, 0x7f090373

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLine:Landroidx/constraintlayout/widget/Guideline;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevokeView:Landroid/view/View;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Landroid/view/View;->setEnabled(Z)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevertView:Landroid/view/View;

    invoke-virtual {v0, v2}, Landroid/view/View;->setEnabled(Z)V

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorFragment;

    invoke-direct {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorFragment;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorFragment;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorFragment;

    const-string v3, "fragment_tag_editor"

    invoke-virtual {v0, v1, v2, v3}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commit()I

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isFromNormalShare()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->configShareModeView()V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->configEditModeView()V

    :goto_0
    iget v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->showBottomViewWithMode(I)V

    return-void
.end method

.method private isFromNormalEdit()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromLongScreen:Z

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromSendMode:Z

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private isFromNormalShare()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromLongScreen:Z

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromSendMode:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private onCtaChecked()V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/preference/DocumentProviderPreference;->isFirstEntrance()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/util/DocumentProviderUtils;->needRequestExternalSDCardPermission(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p0}, Lcom/miui/gallery/util/DocumentProviderUtils;->startFirstEntrancyPermissionActivityForResult(Landroid/app/Activity;)V

    :cond_0
    return-void
.end method

.method private parseUriAndShare(Landroid/net/Uri;)V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsShared:Z

    if-nez v0, :cond_0

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsShared:Z

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$XWJMO5pz5-4z4Hxr3T0h0Ld-9c4;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$XWJMO5pz5-4z4Hxr3T0h0Ld-9c4;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;Landroid/net/Uri;)V

    new-instance p1, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$14;

    invoke-direct {p1, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$14;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;Lcom/miui/gallery/threadpool/FutureListener;)Lcom/miui/gallery/threadpool/Future;

    :cond_0
    return-void
.end method

.method private showEditorNav(Landroidx/recyclerview/widget/RecyclerView;Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;II)V
    .locals 0

    if-ne p4, p3, :cond_1

    iget-boolean p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->hideMenuFragment()V

    goto :goto_0

    :cond_0
    invoke-direct {p0, p2}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->showMenuFragment(Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;)V

    :goto_0
    return-void

    :cond_1
    iget-object p4, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    iget p2, p2, Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;->id:I

    invoke-interface {p4, p2}, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;->setCurrentScreenEditor(I)Z

    move-result p2

    if-nez p2, :cond_2

    return-void

    :cond_2
    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getAdapter()Landroidx/recyclerview/widget/RecyclerView$Adapter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/editor/photo/screen/home/ScreenNavAdapter;

    invoke-virtual {p1, p3}, Lcom/miui/gallery/editor/photo/screen/home/ScreenNavAdapter;->setSelection(I)V

    iget-boolean p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    if-eqz p1, :cond_3

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->hideMenuFragment()V

    :cond_3
    return-void
.end method

.method private showLongCropFragment()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenLongCropFragment:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

    invoke-direct {v0}, Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenLongCropFragment:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenLongCropFragment:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

    invoke-virtual {v1, v0}, Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;->setArguments(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    const v1, 0x7f0902c8

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenLongCropFragment:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

    const-string v3, "fragment_tag_long_crop"

    invoke-virtual {v0, v1, v2, v3}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderManager:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    new-instance v1, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$-5TON95PmjcozGXJs-iq6HCPSnk;

    invoke-direct {v1, p0}, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$-5TON95PmjcozGXJs-iq6HCPSnk;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;->setOriginLoadedListener(Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager$OnOriginLoadedListener;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderManager:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;->decodeOrigin()V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->configEditModeView()V

    return-void
.end method

.method private showMenuFragment(Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;)V
    .locals 4

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    const v1, 0x7f0902c9

    invoke-virtual {v0, v1}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object v0

    iget v2, p1, Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;->id:I

    const/4 v3, 0x2

    if-ne v2, v3, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDoodleFragment:Landroid/app/Fragment;

    if-nez p1, :cond_0

    new-instance p1, Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleMenuFragment;

    invoke-direct {p1}, Lcom/miui/gallery/editor/photo/screen/doodle/ScreenDoodleMenuFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDoodleFragment:Landroid/app/Fragment;

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDoodleFragment:Landroid/app/Fragment;

    goto :goto_0

    :cond_1
    iget v2, p1, Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;->id:I

    const/4 v3, 0x3

    if-ne v2, v3, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mTextFragment:Landroid/app/Fragment;

    if-nez p1, :cond_2

    new-instance p1, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextMenuFragment;

    invoke-direct {p1}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextMenuFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mTextFragment:Landroid/app/Fragment;

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mTextFragment:Landroid/app/Fragment;

    goto :goto_0

    :cond_3
    iget p1, p1, Lcom/miui/gallery/editor/photo/screen/entity/ScreenNavigatorData;->id:I

    const/4 v2, 0x4

    if-ne p1, v2, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMosaicFragment:Landroid/app/Fragment;

    if-nez p1, :cond_4

    new-instance p1, Lcom/miui/gallery/editor/photo/screen/mosaic/ScreenMosaicMenuFragment;

    invoke-direct {p1}, Lcom/miui/gallery/editor/photo/screen/mosaic/ScreenMosaicMenuFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMosaicFragment:Landroid/app/Fragment;

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mMosaicFragment:Landroid/app/Fragment;

    goto :goto_0

    :cond_5
    const/4 p1, 0x0

    :goto_0
    if-nez p1, :cond_6

    return-void

    :cond_6
    if-nez v0, :cond_7

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0, v1, p1}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object p1

    invoke-virtual {p1}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    goto :goto_1

    :cond_7
    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0, v1, p1}, Landroid/app/FragmentTransaction;->replace(ILandroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object p1

    invoke-virtual {p1}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    :goto_1
    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->startMenuAnimator(Z)V

    return-void
.end method

.method private updateLongScreenPreviewShow()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    invoke-interface {v1}, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;->onExport()Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;

    move-result-object v1

    iget-object v1, v1, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mDrawEntry:Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;

    invoke-interface {v0, v1}, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;->setScreenDrawEntry(Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public changeWithMode(I)V
    .locals 2

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$1;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->startSharePageExitAnimator(Landroid/animation/Animator$AnimatorListener;)V

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    if-ne p1, v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    new-instance v1, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$2;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity$2;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;I)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->startEditPageExitAnimator(Landroid/animation/Animator$AnimatorListener;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public configEditModeView()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroidx/constraintlayout/widget/ConstraintLayout;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Landroidx/constraintlayout/widget/ConstraintLayout;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBottomLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v0, v2, v2, v2, v2}, Landroid/widget/FrameLayout;->setPadding(IIII)V

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsLongScreenMode:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsLongCropShow:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropLayout:Landroid/view/View;

    invoke-virtual {v0, v2}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditViewLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->setVisibility(I)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropLayout:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditViewLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v0, v2}, Landroid/widget/FrameLayout;->setVisibility(I)V

    :goto_0
    invoke-static {p0}, Lcom/android/internal/WindowCompat;->isNotch(Landroid/app/Activity;)Z

    move-result v0

    const v1, 0x7f060557

    if-eqz v0, :cond_1

    invoke-static {p0}, Lcom/android/internal/WindowCompat;->getTopNotchHeight(Landroid/app/Activity;)I

    move-result v0

    iget-object v3, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLine:Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    invoke-virtual {v4, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    add-int/2addr v4, v0

    invoke-virtual {v3, v4}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineBegin(I)V

    iget-object v3, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v3}, Landroidx/constraintlayout/widget/ConstraintLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v3

    check-cast v3, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    invoke-virtual {v4, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    add-int/2addr v1, v0

    iput v1, v3, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->height:I

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v1, v3}, Landroidx/constraintlayout/widget/ConstraintLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v1, v2, v0, v2, v2}, Landroidx/constraintlayout/widget/ConstraintLayout;->setPadding(IIII)V

    goto :goto_1

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v0}, Landroidx/constraintlayout/widget/ConstraintLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-virtual {v2, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    iput v1, v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->height:I

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v1, v0}, Landroidx/constraintlayout/widget/ConstraintLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :goto_1
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRootBg:Landroid/widget/FrameLayout;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    invoke-virtual {v1}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f05019f

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->setBackgroundColor(I)V

    return-void
.end method

.method public configShareModeView()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroidx/constraintlayout/widget/ConstraintLayout;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareLayoutView:Landroid/widget/FrameLayout;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Landroid/widget/FrameLayout;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v0, v2}, Landroidx/constraintlayout/widget/ConstraintLayout;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropLayout:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mEditViewLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBottomLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v3, 0x7f060525

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    iput v1, v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->height:I

    const v1, 0x7f0900cb

    iput v1, v0, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->topToBottom:I

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mBottomLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v1, v0}, Landroid/widget/FrameLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isFromNormalShare()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06054f

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v3, 0x7f06054e

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    iget-object v3, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareLayoutView:Landroid/widget/FrameLayout;

    invoke-virtual {v3, v0, v2, v1, v2}, Landroid/widget/FrameLayout;->setPadding(IIII)V

    :cond_0
    invoke-static {p0}, Lcom/android/internal/WindowCompat;->isNotch(Landroid/app/Activity;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p0}, Lcom/android/internal/WindowCompat;->getTopNotchHeight(Landroid/app/Activity;)I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v1}, Landroidx/constraintlayout/widget/ConstraintLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f06054a

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    add-int/2addr v2, v0

    iput v2, v1, Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;->height:I

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mShareTopLayoutView:Landroidx/constraintlayout/widget/ConstraintLayout;

    invoke-virtual {v0, v1}, Landroidx/constraintlayout/widget/ConstraintLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRootBg:Landroid/widget/FrameLayout;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    invoke-virtual {v1}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f050173

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->setBackgroundColor(I)V

    return-void
.end method

.method public getRenderData()Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    invoke-interface {v0}, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;->onExport()Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    if-eqz v1, :cond_0

    invoke-interface {v1}, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;->onExport()Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

    move-result-object v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    invoke-interface {v1}, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;->onExport()Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->setLongCropEntry(Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;)V

    :cond_0
    return-object v0
.end method

.method public getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;
    .locals 4

    new-instance v0, Lcom/miui/gallery/permission/core/RuntimePermission;

    const v1, 0x7f100635

    invoke-virtual {p0, v1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    const-string v3, "android.permission.WRITE_EXTERNAL_STORAGE"

    invoke-direct {v0, v3, v1, v2}, Lcom/miui/gallery/permission/core/RuntimePermission;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    new-array v1, v2, [Lcom/miui/gallery/permission/core/RuntimePermission;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    return-object v1
.end method

.method public isMenuExpand()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsMenuExpand:Z

    return v0
.end method

.method public isSharePage()Z
    .locals 2

    iget v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    return v1
.end method

.method public synthetic lambda$deleteSourceFileAndFinish$85$ScreenEditorActivity(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/String;
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mExportTask:Lcom/miui/gallery/editor/photo/app/ExportTask;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/ExportTask;->getSource()Landroid/net/Uri;

    move-result-object v0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFilePathWithUri(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public synthetic lambda$new$86$ScreenEditorActivity(Ljava/lang/Object;)V
    .locals 1

    const-string p1, "ScreenEditorActivity"

    const-string v0, "delete Screenshots file success!"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->finish()V

    return-void
.end method

.method public synthetic lambda$onAttachFragment$88$ScreenEditorActivity(ZZZ)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevokeView:Landroid/view/View;

    invoke-virtual {p1, p2}, Landroid/view/View;->setEnabled(Z)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mRevertView:Landroid/view/View;

    invoke-virtual {p1, p3}, Landroid/view/View;->setEnabled(Z)V

    return-void
.end method

.method public synthetic lambda$onPermissionsChecked$87$ScreenEditorActivity(Z)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->onCtaChecked()V

    return-void
.end method

.method public synthetic lambda$parseUriAndShare$90$ScreenEditorActivity(Landroid/net/Uri;Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/String;
    .locals 0

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    invoke-direct {p0, p2, p1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFilePathWithUri(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public synthetic lambda$showLongCropFragment$89$ScreenEditorActivity(Landroid/graphics/Bitmap;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenLongCropFragment:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment;->setOriginBitmap(Landroid/graphics/Bitmap;)V

    return-void
.end method

.method protected onActivityResult(IILandroid/content/Intent;)V
    .locals 1

    invoke-super {p0, p1, p2, p3}, Landroid/app/Activity;->onActivityResult(IILandroid/content/Intent;)V

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mNeedExported:Z

    :cond_0
    invoke-static {p0, p1, p2, p3}, Lcom/miui/gallery/util/DocumentProviderUtils;->handleRequestPermissionResult(Landroid/app/Activity;IILandroid/content/Intent;)V

    return-void
.end method

.method public onAttachFragment(Landroid/app/Fragment;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/app/Activity;->onAttachFragment(Landroid/app/Fragment;)V

    instance-of v0, p1, Lcom/miui/gallery/editor/photo/app/ExportFragment;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/miui/gallery/editor/photo/app/ExportFragment;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mExportCallbacks:Lcom/miui/gallery/editor/photo/app/ExportFragment$Callbacks;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/app/ExportFragment;->setCallbacks(Lcom/miui/gallery/editor/photo/app/ExportFragment$Callbacks;)V

    goto :goto_0

    :cond_0
    instance-of v0, p1, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    if-eqz v0, :cond_1

    check-cast p1, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$pA1LPMYBUwZTIIDBElaYV43Q61o;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$pA1LPMYBUwZTIIDBElaYV43Q61o;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    invoke-interface {p1, v0}, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;->setOperationUpdateListener(Lcom/miui/gallery/editor/photo/screen/home/OperationUpdateListener;)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditorListener:Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsLongScreenMode:Z

    invoke-interface {p1, v0}, Lcom/miui/gallery/editor/photo/screen/home/IScreenEditorController;->setLongCrop(Z)V

    goto :goto_0

    :cond_1
    instance-of v0, p1, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    if-eqz v0, :cond_2

    check-cast p1, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongCropEditorController:Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mLongScreenCropCallback:Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment$ScreenLongCropCallback;

    invoke-interface {p1, v0}, Lcom/miui/gallery/editor/photo/screen/longcrop/ILongCropEditorController;->setScreenLongCropCallback(Lcom/miui/gallery/editor/photo/screen/longcrop/ScreenLongCropFragment$ScreenLongCropCallback;)V

    goto :goto_0

    :cond_2
    instance-of v0, p1, Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment;

    if-eqz v0, :cond_3

    check-cast p1, Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenEditBottomCallback:Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment$Callback;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment;->setCallback(Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment$Callback;)V

    :cond_3
    :goto_0
    return-void
.end method

.method public onAttachedToWindow()V
    .locals 1

    invoke-static {p0}, Lcom/android/internal/WindowCompat;->isNotch(Landroid/app/Activity;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/SystemUiUtil;->extendToStatusBar(Landroid/view/View;)V

    :cond_0
    return-void
.end method

.method public onBackPressed()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->isFromNormalShare()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->finish()V

    return-void

    :cond_0
    iget v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->finish()V

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    iget v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->changeWithMode(I)V

    :goto_0
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const/4 v0, 0x0

    const-string v1, "allow_use_on_offline_global"

    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result p1

    invoke-static {p1}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->setToAllowUseOnOfflineGlobal(Z)V

    const/4 p1, 0x0

    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getWindow()Landroid/view/Window;

    move-result-object v1

    invoke-static {v1}, Lcom/android/internal/WindowCompat;->setCutoutModeShortEdges(Landroid/view/Window;)V

    const v1, 0x7f0b016d

    invoke-virtual {p0, v1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->setContentView(I)V

    iput-object p0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mActivity:Landroid/app/Activity;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getIntent()Landroid/content/Intent;

    move-result-object v1

    sget-object v2, Lcom/miui/gallery/sdk/editor/Constants;->EXTRA_IS_LONG_SCREENSHOT:Ljava/lang/String;

    invoke-virtual {v1, v2, v0}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v2

    iput-boolean v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsLongScreenMode:Z

    const-string v2, "FromLongScreenshot"

    invoke-virtual {v1, v2, v0}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v2

    iput-boolean v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromLongScreen:Z

    const-string v2, "is_from_send"

    invoke-virtual {v1, v2, v0}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromSendMode:Z

    new-instance v0, Lcom/miui/gallery/editor/photo/app/DraftManager;

    invoke-virtual {v1}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v2

    invoke-virtual {v1}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v1

    invoke-direct {v0, p0, v2, v1}, Lcom/miui/gallery/editor/photo/app/DraftManager;-><init>(Landroid/content/Context;Landroid/net/Uri;Landroid/os/Bundle;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDraftManager:Lcom/miui/gallery/editor/photo/app/DraftManager;

    invoke-static {p0}, Lcom/miui/gallery/editor/photo/app/ExportTask;->from(Landroid/app/Activity;)Lcom/miui/gallery/editor/photo/app/ExportTask;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mExportTask:Lcom/miui/gallery/editor/photo/app/ExportTask;

    iget-boolean v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsFromSendMode:Z

    iput v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mPageMode:I

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->initView()V

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->initScreenAnimatorHelper()V

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDraftManager:Lcom/miui/gallery/editor/photo/app/DraftManager;

    invoke-direct {v0, v1}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;-><init>(Lcom/miui/gallery/editor/photo/app/DraftManager;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderManager:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    invoke-static {p0, p0, p1}, Lcom/miui/gallery/permission/core/PermissionInjector;->injectIfNeededIn(Landroid/app/Activity;Lcom/miui/gallery/permission/core/PermissionCheckCallback;Landroid/os/Bundle;)V

    sget-object p1, Lcom/miui/gallery/editor/photo/screen/core/ScreenProviderManager;->INSTANCE:Lcom/miui/gallery/editor/photo/screen/core/ScreenProviderManager;

    invoke-virtual {p1, p0}, Lcom/miui/gallery/editor/photo/screen/core/ScreenProviderManager;->onActivityCreate(Landroid/content/Context;)V

    return-void
.end method

.method protected onDestroy()V
    .locals 2

    invoke-super {p0}, Landroid/app/Activity;->onDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenAnimatorHelper:Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenAnimatorHelper;->release()V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mDraftManager:Lcom/miui/gallery/editor/photo/app/DraftManager;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/DraftManager;->release()V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mExportTask:Lcom/miui/gallery/editor/photo/app/ExportTask;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/ExportTask;->closeExportDialog()V

    :cond_0
    sget-object v0, Lcom/miui/gallery/editor/photo/screen/core/ScreenProviderManager;->INSTANCE:Lcom/miui/gallery/editor/photo/screen/core/ScreenProviderManager;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/screen/core/ScreenProviderManager;->onActivityDestroy()V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenRenderManager:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;->release()V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenDeleteDialogFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;->dismissSafely()V

    iput-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mScreenDeleteDialogFragment:Lcom/miui/gallery/editor/photo/screen/home/ScreenDeleteDialogFragment;

    :cond_2
    iput-object v1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnFilesProcessedListener:Lcom/miui/gallery/ui/PhotoPageFragmentBase$OnFilesProcessedListener;

    return-void
.end method

.method protected onPause()V
    .locals 1

    invoke-super {p0}, Landroid/app/Activity;->onPause()V

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->onPagePause()V

    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->allowUseOnOfflineGlobal()Z

    move-result p1

    if-nez p1, :cond_1

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$CTA;->canConnectNetwork()Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    new-instance p1, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$5UNI58s3o5SOeTwrxnBs3YAJo5o;

    invoke-direct {p1, p0}, Lcom/miui/gallery/editor/photo/screen/home/-$$Lambda$ScreenEditorActivity$5UNI58s3o5SOeTwrxnBs3YAJo5o;-><init>(Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;)V

    invoke-static {p0, p1}, Lcom/miui/gallery/agreement/AgreementsUtils;->showUserAgreements(Landroid/app/Activity;Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;)V

    goto :goto_1

    :cond_1
    :goto_0
    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->onCtaChecked()V

    :goto_1
    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->doInitialize()V

    return-void
.end method

.method protected onResume()V
    .locals 1

    invoke-super {p0}, Landroid/app/Activity;->onResume()V

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->onPageResume()V

    return-void
.end method

.method public showBottomViewWithMode(I)V
    .locals 4

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mNavFragment:Landroid/app/Fragment;

    if-nez p1, :cond_0

    iget-boolean p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mIsLongScreenMode:Z

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment;->newInstance(Z)Lcom/miui/gallery/editor/photo/screen/home/ScreenNavFragment;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mNavFragment:Landroid/app/Fragment;

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mNavFragment:Landroid/app/Fragment;

    const-string v0, "ScreenNavFragment"

    goto :goto_0

    :cond_1
    const/4 v0, 0x1

    if-ne p1, v0, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mChooserFragment:Landroid/app/Fragment;

    if-nez p1, :cond_2

    new-instance p1, Landroid/content/Intent;

    const-string v1, "android.intent.action.SEND"

    invoke-direct {p1, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v1, "image/*"

    invoke-virtual {p1, v1}, Landroid/content/Intent;->setType(Ljava/lang/String;)Landroid/content/Intent;

    const/4 v1, 0x0

    const/4 v2, 0x3

    invoke-static {p1, v1, v0, v2}, Lcom/miui/gallery/ui/ChooserFragment;->newInstance(Landroid/content/Intent;IZI)Lcom/miui/gallery/ui/ChooserFragment;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mChooserFragment:Landroid/app/Fragment;

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mChooserFragment:Landroid/app/Fragment;

    check-cast p1, Lcom/miui/gallery/ui/ChooserFragment;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnIntentSelectedListener:Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/ChooserFragment;->setOnIntentSelectedListener(Lcom/miui/gallery/ui/ChooserFragment$OnIntentSelectedListener;)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mChooserFragment:Landroid/app/Fragment;

    check-cast p1, Lcom/miui/gallery/ui/ChooserFragment;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mOnMishareClickedListener:Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/ChooserFragment;->setOnMishareClickedListener(Lcom/miui/gallery/ui/ChooserFragment$OnMishareClickedListener;)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mChooserFragment:Landroid/app/Fragment;

    const-string v0, "ChooserFragment"

    goto :goto_0

    :cond_3
    const/4 p1, 0x0

    const-string v0, ""

    :goto_0
    if-nez p1, :cond_4

    return-void

    :cond_4
    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v1

    invoke-virtual {v1}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCurrentBottomFragment:Landroid/app/Fragment;

    const v3, 0x7f090080

    if-nez v2, :cond_5

    invoke-virtual {v1, v3, p1, v0}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    goto :goto_1

    :cond_5
    if-eq v2, p1, :cond_7

    invoke-virtual {p1}, Landroid/app/Fragment;->isAdded()Z

    move-result v2

    if-nez v2, :cond_6

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v2

    invoke-virtual {v2, v0}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object v2

    if-nez v2, :cond_6

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCurrentBottomFragment:Landroid/app/Fragment;

    invoke-virtual {v1, v2}, Landroid/app/FragmentTransaction;->hide(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object v1

    invoke-virtual {v1, v3, p1, v0}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    goto :goto_1

    :cond_6
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCurrentBottomFragment:Landroid/app/Fragment;

    invoke-virtual {v1, v0}, Landroid/app/FragmentTransaction;->hide(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/app/FragmentTransaction;->show(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    :cond_7
    :goto_1
    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorActivity;->mCurrentBottomFragment:Landroid/app/Fragment;

    return-void
.end method
