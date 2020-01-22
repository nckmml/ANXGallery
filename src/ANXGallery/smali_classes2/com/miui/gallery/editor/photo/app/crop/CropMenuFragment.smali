.class public Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;
.super Lcom/miui/gallery/editor/photo/app/MenuFragment;
.source "CropMenuFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$AutoCropTask;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/editor/photo/app/MenuFragment<",
        "Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;",
        "Lcom/miui/gallery/editor/photo/core/SdkProvider<",
        "Lcom/miui/gallery/editor/photo/core/common/model/CropData;",
        "Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;",
        ">;>;"
    }
.end annotation


# instance fields
.field private mAdapter:Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;

.field private mAutoCropData:Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;

.field private mAutoCropListener:Landroid/view/View$OnClickListener;

.field private mBubbleIndicator:Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator;

.field private mClearListener:Landroid/view/View$OnClickListener;

.field private mCurrentProgress:I

.field private mDataList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/photo/core/common/model/CropData;",
            ">;"
        }
    .end annotation
.end field

.field private mIndicatorCallback:Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator$Callback;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator$Callback<",
            "Landroid/widget/TextView;",
            ">;"
        }
    .end annotation
.end field

.field private mOnCropStateChangedListener:Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment$OnCropStateChangedListener;

.field private mOnItemClickListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

.field private mRecyclerView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

.field private mSeekBar:Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;

.field private mSeekBarChangeListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

.field private mTitle:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 1

    sget-object v0, Lcom/miui/gallery/editor/photo/core/Effect;->CROP:Lcom/miui/gallery/editor/photo/core/Effect;

    invoke-direct {p0, v0}, Lcom/miui/gallery/editor/photo/app/MenuFragment;-><init>(Lcom/miui/gallery/editor/photo/core/Effect;)V

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mCurrentProgress:I

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;

    invoke-direct {v0}, Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAutoCropData:Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$2;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mOnCropStateChangedListener:Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment$OnCropStateChangedListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$3;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mClearListener:Landroid/view/View$OnClickListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$4;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAutoCropListener:Landroid/view/View$OnClickListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$5;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$5;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mOnItemClickListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$6;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$6;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mIndicatorCallback:Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator$Callback;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$7;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$7;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSeekBarChangeListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1000(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mDataList:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$1100(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1200(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1300(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1400(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;I)I
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getDegreeShow(I)I

    move-result p0

    return p0
.end method

.method static synthetic access$1500(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mCurrentProgress:I

    return p0
.end method

.method static synthetic access$1502(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;I)I
    .locals 0

    iput p1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mCurrentProgress:I

    return p1
.end method

.method static synthetic access$1600(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;I)F
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getDegree(I)F

    move-result p0

    return p0
.end method

.method static synthetic access$1700(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1800(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1900(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$2000(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAutoCropData:Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;

    return-object p0
.end method

.method static synthetic access$2100(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;F)I
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getProgressFromDegree(F)I

    move-result p0

    return p0
.end method

.method static synthetic access$2200(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Landroid/graphics/Bitmap;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getPreview()Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Landroid/view/View$OnClickListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mClearListener:Landroid/view/View$OnClickListener;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->setNormalTitle()V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSeekBar:Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mBubbleIndicator:Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAdapter:Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;

    return-object p0
.end method

.method static synthetic access$800(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$900(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->doAutoCrop(Z)V

    return-void
.end method

.method private doAutoCrop(Z)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAutoCropData:Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;

    invoke-virtual {v1}, Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;->getDegree()F

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;->autoCrop(F)V

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    const v1, 0x7f100644

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mClearListener:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    new-instance v1, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$8;

    invoke-direct {v1, p0, p1}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$8;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;Z)V

    const-wide/16 v2, 0x190

    invoke-virtual {v0, v1, v2, v3}, Landroid/widget/TextView;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private getDegree(I)F
    .locals 1

    int-to-float p1, p1

    const/high16 v0, 0x42b40000    # 90.0f

    div-float/2addr p1, v0

    const/high16 v0, 0x42340000    # 45.0f

    mul-float/2addr p1, v0

    return p1
.end method

.method private getDegreeShow(I)I
    .locals 1

    if-nez p1, :cond_0

    const/4 p1, 0x0

    return p1

    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getDegree(I)F

    move-result v0

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    if-nez v0, :cond_2

    if-lez p1, :cond_1

    const/4 p1, 0x1

    return p1

    :cond_1
    const/4 p1, -0x1

    return p1

    :cond_2
    return v0
.end method

.method private getProgressFromDegree(F)I
    .locals 2

    const/high16 v0, 0x42b40000    # 90.0f

    mul-float/2addr v0, p1

    const/high16 v1, 0x42340000    # 45.0f

    div-float/2addr v0, v1

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    if-nez v0, :cond_1

    const/4 v0, 0x0

    cmpl-float p1, p1, v0

    if-lez p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, -0x1

    :goto_0
    return p1

    :cond_1
    return v0
.end method

.method private setNormalTitle()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAutoCropData:Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/app/crop/AutoCropData;->canAutoRotation()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    const v1, 0x7f100642

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f050161

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setTextColor(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAutoCropListener:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    const v1, 0x7f100641

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f050164

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setTextColor(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    :goto_0
    return-void
.end method


# virtual methods
.method public onCreate(Landroid/os/Bundle;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/editor/photo/app/MenuFragment;->onCreate(Landroid/os/Bundle;)V

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mDataList:Ljava/util/List;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mDataList:Ljava/util/List;

    sget-object v0, Lcom/miui/gallery/editor/photo/core/common/model/CropData;->MIRROR:Lcom/miui/gallery/editor/photo/core/common/model/CropData;

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mDataList:Ljava/util/List;

    sget-object v0, Lcom/miui/gallery/editor/photo/core/common/model/CropData;->ROTATE:Lcom/miui/gallery/editor/photo/core/common/model/CropData;

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance p1, Ljava/util/ArrayList;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSdkProvider:Lcom/miui/gallery/editor/photo/core/SdkProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/core/SdkProvider;->list()Ljava/util/List;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mDataList:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    new-instance p1, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$AutoCropTask;

    const/4 v0, 0x0

    invoke-direct {p1, p0, v0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$AutoCropTask;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$1;)V

    sget-object v0, Landroid/os/AsyncTask;->THREAD_POOL_EXECUTOR:Ljava/util/concurrent/Executor;

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Void;

    invoke-virtual {p1, v0, v1}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$AutoCropTask;->executeOnExecutor(Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;

    return-void
.end method

.method public onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 0

    new-instance p1, Lcom/miui/gallery/editor/photo/app/menu/CropView;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-direct {p1, p2}, Lcom/miui/gallery/editor/photo/app/menu/CropView;-><init>(Landroid/content/Context;)V

    return-object p1
.end method

.method public onDestroyView()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/editor/photo/app/MenuFragment;->onDestroyView()V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;->setOnCropChangedListener(Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment$OnCropStateChangedListener;)V

    return-void
.end method

.method public onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
    .locals 5

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/editor/photo/app/MenuFragment;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mOnCropStateChangedListener:Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment$OnCropStateChangedListener;

    invoke-virtual {p2, v0}, Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment;->setOnCropChangedListener(Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractCropFragment$OnCropStateChangedListener;)V

    const p2, 0x7f090375

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/editor/photo/widgets/TouchTransLinearLayout;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment$1;-><init>(Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;)V

    invoke-virtual {p2, v0}, Lcom/miui/gallery/editor/photo/widgets/TouchTransLinearLayout;->setOnTouchEvent(Lcom/miui/gallery/editor/photo/widgets/TouchTransLinearLayout$OnTouchEvent;)V

    const p2, 0x7f0902eb

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSeekBar:Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSeekBar:Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;

    const/16 v0, 0x5a

    invoke-virtual {p2, v0}, Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;->setMaxValue(I)V

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSeekBar:Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;

    const/4 v0, 0x0

    invoke-virtual {p2, v0}, Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;->setCurrentValue(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    const v1, 0x7f0b0195

    const/4 v2, 0x0

    invoke-static {p2, v1, v2}, Landroid/view/View;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    new-instance v1, Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f060449

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    iget-object v3, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mIndicatorCallback:Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator$Callback;

    iget-object v4, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSeekBarChangeListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    invoke-direct {v1, p2, v2, v3, v4}, Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator;-><init>(Landroid/view/View;ILcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator$Callback;Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    iput-object v1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mBubbleIndicator:Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator;

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mSeekBar:Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mBubbleIndicator:Lcom/miui/gallery/editor/photo/widgets/seekbar/BubbleIndicator;

    invoke-virtual {p2, v1}, Lcom/miui/gallery/editor/photo/widgets/seekbar/BiDirectionSeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    const p2, 0x7f0902a0

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mRecyclerView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    new-instance p2, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v1

    invoke-direct {p2, v1}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;-><init>(Landroid/content/Context;)V

    new-instance v1, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v1, v2}, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;-><init>(Landroid/content/Context;)V

    invoke-virtual {p2, v1}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->setSmoothScroller(Lcom/miui/gallery/ui/SmoothScrollerController;)V

    invoke-virtual {p2, v0}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->setOrientation(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mRecyclerView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    invoke-virtual {v0, p2}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mRecyclerView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    invoke-static {p2}, Lcom/miui/gallery/widget/overscroll/HorizontalOverScrollBounceEffectDecorator;->setOverScrollEffect(Landroidx/recyclerview/widget/RecyclerView;)V

    new-instance p2, Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mDataList:Ljava/util/List;

    new-instance v2, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    const v4, 0x7f050161

    invoke-virtual {v3, v4}, Landroid/content/res/Resources;->getColor(I)I

    move-result v3

    invoke-direct {v2, v3}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;-><init>(I)V

    invoke-direct {p2, v0, v1, v2}, Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;-><init>(Landroid/content/Context;Ljava/util/List;Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;)V

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAdapter:Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mRecyclerView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAdapter:Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;

    invoke-virtual {p2, v0}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mAdapter:Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mOnItemClickListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

    invoke-virtual {p2, v0}, Lcom/miui/gallery/editor/photo/app/crop/CropAdapter;->setOnItemClickListener(Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;)V

    const p2, 0x7f090366

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/crop/CropMenuFragment;->mTitle:Landroid/widget/TextView;

    const/4 p2, 0x4

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setVisibility(I)V

    return-void
.end method
