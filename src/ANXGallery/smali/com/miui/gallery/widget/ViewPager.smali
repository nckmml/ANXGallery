.class public Lcom/miui/gallery/widget/ViewPager;
.super Landroid/view/ViewGroup;
.source "ViewPager.java"

# interfaces
.implements Lcom/miui/gallery/widget/slip/ISlipAnimView;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/widget/ViewPager$LayoutParams;,
        Lcom/miui/gallery/widget/ViewPager$PagerObserver;,
        Lcom/miui/gallery/widget/ViewPager$SavedState;,
        Lcom/miui/gallery/widget/ViewPager$Decor;,
        Lcom/miui/gallery/widget/ViewPager$OnAdapterChangeListener;,
        Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;,
        Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;,
        Lcom/miui/gallery/widget/ViewPager$ItemInfo;
    }
.end annotation


# static fields
.field private static final COMPARATOR:Ljava/util/Comparator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Comparator<",
            "Lcom/miui/gallery/widget/ViewPager$ItemInfo;",
            ">;"
        }
    .end annotation
.end field

.field private static final DEBUG:Z

.field private static final LAYOUT_ATTRS:[I

.field private static final sInterpolator:Landroid/view/animation/Interpolator;


# instance fields
.field private mActivePointerId:I

.field private mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

.field private mAdapterChangeListener:Lcom/miui/gallery/widget/ViewPager$OnAdapterChangeListener;

.field private mBottomMarginProgress:F

.field private mBottomPageBounds:I

.field private mCalledSuper:Z

.field private mChildHeightMeasureSpec:I

.field private mChildWidthMeasureSpec:I

.field private mCloseEnough:I

.field private mCurItem:I

.field private mDecorChildCount:I

.field private mDefaultGutterSize:I

.field mDragEnabled:Z

.field private mFakeDragging:Z

.field private mFirstLayout:Z

.field private mFirstOffset:F

.field private mFirstOffsetLeftScreen:F

.field private mFlingDistance:I

.field private mForceReplayout:Z

.field private mGutterSize:I

.field private mHeightSlipRatio:F

.field private mInLayout:Z

.field private mInitialHeight:I

.field private mInitialMotionX:F

.field private mInitialPageMargin:I

.field private mInitialWidth:I

.field private mInternalPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

.field private mIsBeingDragged:Z

.field private mIsUnableToDrag:Z

.field private final mItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/widget/ViewPager$ItemInfo;",
            ">;"
        }
    .end annotation
.end field

.field private mLastMotionX:F

.field private mLastMotionY:F

.field private mLastOffset:F

.field private mLastOffsetLeftScreen:F

.field private mLastOrientation:I

.field mLastPageOffset:F

.field private mLastSettledItem:I

.field private mLeftEdge:Landroid/widget/EdgeEffect;

.field private mLeftOffscreenPageLimit:I

.field private mMarginDrawable:Landroid/graphics/drawable/Drawable;

.field private mMarginSlipRatio:F

.field private mMaximumVelocity:I

.field private mMinimumVelocity:I

.field private mNeedCalculatePageOffsets:Z

.field private mObserver:Lcom/miui/gallery/widget/ViewPager$PagerObserver;

.field private mOnPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

.field private mPageMargin:I

.field private mPageSettledListener:Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;

.field private mPopulatePending:Z

.field private mRestoredAdapterState:Landroid/os/Parcelable;

.field private mRestoredCurItem:I

.field private mReverseVelocity:I

.field private mRightEdge:Landroid/widget/EdgeEffect;

.field private mRightOffscreenPageLimit:I

.field private mScrollState:I

.field private mScroller:Landroid/widget/Scroller;

.field private mScrollingCacheEnabled:Z

.field private mSlipProgress:F

.field private final mTempItem:Lcom/miui/gallery/widget/ViewPager$ItemInfo;

.field private final mTempRect:Landroid/graphics/Rect;

.field private mTopPageBounds:I

.field private mTouchSlop:I

.field private mVelocityTracker:Landroid/view/VelocityTracker;

.field private mWidthSlipRatio:F


# direct methods
.method static constructor <clinit>()V
    .locals 3

    const-string v0, "ViewPager"

    const/4 v1, 0x3

    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    sput-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    const/4 v0, 0x1

    new-array v0, v0, [I

    const/4 v1, 0x0

    const v2, 0x10100b3

    aput v2, v0, v1

    sput-object v0, Lcom/miui/gallery/widget/ViewPager;->LAYOUT_ATTRS:[I

    new-instance v0, Lcom/miui/gallery/widget/ViewPager$1;

    invoke-direct {v0}, Lcom/miui/gallery/widget/ViewPager$1;-><init>()V

    sput-object v0, Lcom/miui/gallery/widget/ViewPager;->COMPARATOR:Ljava/util/Comparator;

    new-instance v0, Lcom/miui/gallery/widget/ViewPager$2;

    invoke-direct {v0}, Lcom/miui/gallery/widget/ViewPager$2;-><init>()V

    sput-object v0, Lcom/miui/gallery/widget/ViewPager;->sInterpolator:Landroid/view/animation/Interpolator;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/widget/ViewPager;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/gallery/widget/ViewPager;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 3

    invoke-direct {p0, p1, p2, p3}, Landroid/view/ViewGroup;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    new-instance p3, Ljava/util/ArrayList;

    invoke-direct {p3}, Ljava/util/ArrayList;-><init>()V

    iput-object p3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    new-instance p3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    invoke-direct {p3}, Lcom/miui/gallery/widget/ViewPager$ItemInfo;-><init>()V

    iput-object p3, p0, Lcom/miui/gallery/widget/ViewPager;->mTempItem:Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    new-instance p3, Landroid/graphics/Rect;

    invoke-direct {p3}, Landroid/graphics/Rect;-><init>()V

    iput-object p3, p0, Lcom/miui/gallery/widget/ViewPager;->mTempRect:Landroid/graphics/Rect;

    const/4 p3, -0x1

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredCurItem:I

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredAdapterState:Landroid/os/Parcelable;

    const v0, -0x800001

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffset:F

    const v0, 0x7f7fffff    # Float.MAX_VALUE

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffset:F

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffsetLeftScreen:F

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffsetLeftScreen:F

    const/4 v1, 0x1

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    const/4 v2, 0x3

    iput v2, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstLayout:Z

    const/4 p3, 0x0

    iput-boolean p3, p0, Lcom/miui/gallery/widget/ViewPager;->mNeedCalculatePageOffsets:Z

    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mDragEnabled:Z

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastPageOffset:F

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mScrollState:I

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialWidth:I

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialHeight:I

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialPageMargin:I

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mSlipProgress:F

    const/high16 v0, 0x3f800000    # 1.0f

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mWidthSlipRatio:F

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mHeightSlipRatio:F

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mMarginSlipRatio:F

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOrientation:I

    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/gallery/widget/ViewPager;->initViewPager(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method static synthetic access$100()[I
    .locals 1

    sget-object v0, Lcom/miui/gallery/widget/ViewPager;->LAYOUT_ATTRS:[I

    return-object v0
.end method

.method private calculatePageLimits(I)V
    .locals 3

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    const/4 v1, 0x1

    const/4 v2, 0x3

    if-le v0, p1, :cond_0

    iput v2, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    goto :goto_0

    :cond_0
    if-ge v0, p1, :cond_1

    iput v2, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    :cond_1
    :goto_0
    return-void
.end method

.method private calculatePageOffsets(Lcom/miui/gallery/widget/ViewPager$ItemInfo;ILcom/miui/gallery/widget/ViewPager$ItemInfo;)V
    .locals 9

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->calculatePageWidthFactor(Lcom/miui/gallery/widget/ViewPager$ItemInfo;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v1

    const/4 v2, 0x0

    if-lez v1, :cond_0

    iget v3, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    int-to-float v3, v3

    int-to-float v1, v1

    div-float v1, v3, v1

    goto :goto_0

    :cond_0
    move v1, v2

    :goto_0
    const/4 v3, 0x0

    if-eqz p3, :cond_6

    iget v4, p3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v5, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ge v4, v5, :cond_3

    iget v5, p3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget p3, p3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v5, p3

    add-float/2addr v5, v1

    add-int/lit8 v4, v4, 0x1

    move p3, v3

    :goto_1
    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-gt v4, v6, :cond_6

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v6

    if-ge p3, v6, :cond_6

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    :goto_2
    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-le v4, v7, :cond_1

    iget-object v7, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->size()I

    move-result v7

    add-int/lit8 v7, v7, -0x1

    if-ge p3, v7, :cond_1

    add-int/lit8 p3, p3, 0x1

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_2

    :cond_1
    :goto_3
    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ge v4, v7, :cond_2

    iget-object v7, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v7, v4}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result v7

    add-float/2addr v7, v1

    add-float/2addr v5, v7

    add-int/lit8 v4, v4, 0x1

    goto :goto_3

    :cond_2
    iput v5, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v6, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v6, v1

    add-float/2addr v5, v6

    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    :cond_3
    iget v5, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-le v4, v5, :cond_6

    iget-object v5, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v5

    add-int/lit8 v5, v5, -0x1

    iget p3, p3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    add-int/lit8 v4, v4, -0x1

    :goto_4
    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-lt v4, v6, :cond_6

    if-ltz v5, :cond_6

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    :goto_5
    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ge v4, v7, :cond_4

    if-lez v5, :cond_4

    add-int/lit8 v5, v5, -0x1

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_5

    :cond_4
    :goto_6
    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-le v4, v7, :cond_5

    iget-object v7, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v7, v4}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result v7

    add-float/2addr v7, v1

    sub-float/2addr p3, v7

    add-int/lit8 v4, v4, -0x1

    goto :goto_6

    :cond_5
    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v7, v1

    sub-float/2addr p3, v7

    iput p3, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    add-int/lit8 v4, v4, -0x1

    goto :goto_4

    :cond_6
    iget-object p3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result p3

    iget v4, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v5, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    add-int/lit8 v5, v5, -0x1

    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-nez v6, :cond_7

    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    goto :goto_7

    :cond_7
    const v6, -0x800001

    :goto_7
    iput v6, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffset:F

    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-nez v6, :cond_8

    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    goto :goto_8

    :cond_8
    move v6, v2

    :goto_8
    iput v6, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffsetLeftScreen:F

    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    add-int/lit8 v0, v0, -0x1

    if-ne v6, v0, :cond_9

    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    goto :goto_9

    :cond_9
    const v6, 0x7f7fffff    # Float.MAX_VALUE

    :goto_9
    iput v6, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffset:F

    iget v6, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v6, v0, :cond_a

    iget v2, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    :cond_a
    iput v2, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffsetLeftScreen:F

    add-int/lit8 v2, p2, -0x1

    :goto_a
    if-ltz v2, :cond_d

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    :goto_b
    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-le v5, v7, :cond_b

    iget-object v7, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    add-int/lit8 v8, v5, -0x1

    invoke-virtual {v7, v5}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result v5

    add-float/2addr v5, v1

    sub-float/2addr v4, v5

    move v5, v8

    goto :goto_b

    :cond_b
    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v7, v1

    sub-float/2addr v4, v7

    iput v4, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-nez v7, :cond_c

    iput v4, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffset:F

    iget v6, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    iput v6, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffsetLeftScreen:F

    :cond_c
    add-int/lit8 v2, v2, -0x1

    add-int/lit8 v5, v5, -0x1

    goto :goto_a

    :cond_d
    iget v2, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v4, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v2, v4

    add-float/2addr v2, v1

    iget p1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    add-int/lit8 p1, p1, 0x1

    add-int/lit8 p2, p2, 0x1

    :goto_c
    if-ge p2, p3, :cond_10

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v4, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    :goto_d
    iget v5, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ge p1, v5, :cond_e

    iget-object v5, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    add-int/lit8 v6, p1, 0x1

    invoke-virtual {v5, p1}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result p1

    add-float/2addr p1, v1

    add-float/2addr v2, p1

    move p1, v6

    goto :goto_d

    :cond_e
    iget v5, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v5, v0, :cond_f

    iput v2, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffset:F

    iget v5, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    iput v5, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffsetLeftScreen:F

    :cond_f
    iput v2, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v4, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v4, v1

    add-float/2addr v2, v4

    add-int/lit8 p2, p2, 0x1

    add-int/lit8 p1, p1, 0x1

    goto :goto_c

    :cond_10
    iput-boolean v3, p0, Lcom/miui/gallery/widget/ViewPager;->mNeedCalculatePageOffsets:Z

    return-void
.end method

.method private calculatePageWidthFactor(Lcom/miui/gallery/widget/ViewPager$ItemInfo;)V
    .locals 8

    iget v0, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mMarginSlipRatio:F

    const/high16 v2, 0x3f800000    # 1.0f

    sub-float v1, v2, v1

    iget v3, p0, Lcom/miui/gallery/widget/ViewPager;->mSlipProgress:F

    mul-float/2addr v1, v3

    sub-float v1, v2, v1

    iget v3, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialPageMargin:I

    int-to-float v3, v3

    mul-float/2addr v1, v3

    float-to-int v1, v1

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    const/high16 v4, 0x40000000    # 2.0f

    if-eqz v3, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mSlipProgress:F

    const/4 v6, 0x0

    invoke-static {v5, v6}, Lcom/miui/gallery/util/MiscUtil;->floatEquals(FF)Z

    move-result v5

    if-nez v5, :cond_0

    iget v5, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    invoke-direct {p0, v5}, Lcom/miui/gallery/widget/ViewPager;->getSlipScale(I)F

    move-result v5

    sub-float v5, v2, v5

    iget v6, p0, Lcom/miui/gallery/widget/ViewPager;->mSlipProgress:F

    mul-float/2addr v5, v6

    sub-float v5, v2, v5

    goto :goto_1

    :cond_0
    move v5, v2

    :goto_1
    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget v7, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    invoke-virtual {v6, v7}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result v6

    mul-float/2addr v5, v6

    iput v5, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    iget v5, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    sub-float v5, v2, v5

    div-float/2addr v5, v4

    iput v5, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    goto :goto_0

    :cond_1
    iget v1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v2, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    sub-float/2addr v0, v2

    div-float/2addr v0, v4

    add-float/2addr v1, v0

    iput v1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    return-void
.end method

.method private callBackPageSettled()V
    .locals 2

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastSettledItem:I

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-eq v0, v1, :cond_0

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mLastSettledItem:I

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mPageSettledListener:Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;

    if-eqz v0, :cond_0

    invoke-interface {v0, v1}, Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;->onPageSettled(I)V

    :cond_0
    return-void
.end method

.method private completeScroll()V
    .locals 8

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScrollState:I

    const/4 v1, 0x2

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-ne v0, v1, :cond_0

    move v0, v2

    goto :goto_0

    :cond_0
    move v0, v3

    :goto_0
    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mScrollState:I

    if-ne v4, v1, :cond_1

    move v1, v2

    goto :goto_1

    :cond_1
    move v1, v3

    :goto_1
    if-eqz v0, :cond_4

    invoke-direct {p0, v3}, Lcom/miui/gallery/widget/ViewPager;->setScrollingCacheEnabled(Z)V

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v4}, Landroid/widget/Scroller;->abortAnimation()V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollY()I

    move-result v5

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v6}, Landroid/widget/Scroller;->getCurrX()I

    move-result v6

    iget-object v7, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v7}, Landroid/widget/Scroller;->getCurrY()I

    move-result v7

    if-ne v4, v6, :cond_2

    if-eq v5, v7, :cond_3

    :cond_2
    invoke-virtual {p0, v6, v7}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    :cond_3
    invoke-direct {p0, v3}, Lcom/miui/gallery/widget/ViewPager;->setScrollState(I)V

    :cond_4
    if-eqz v1, :cond_5

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->callBackPageSettled()V

    :cond_5
    iput-boolean v3, p0, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_6
    :goto_2
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_7

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget-boolean v5, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->scrolling:Z

    if-eqz v5, :cond_6

    iput-boolean v3, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->scrolling:Z

    move v0, v2

    goto :goto_2

    :cond_7
    if-eqz v0, :cond_8

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    :cond_8
    return-void
.end method

.method private determineTargetPage(IFII)I
    .locals 7

    invoke-static {p4}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mFlingDistance:I

    const-string v2, "ViewPager"

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-le v0, v1, :cond_6

    invoke-static {p3}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mMinimumVelocity:I

    if-le v0, v1, :cond_6

    const-string v0, "go on scrolling to new page, offset: "

    const v1, 0x3e99999a    # 0.3f

    if-gez p4, :cond_2

    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mReverseVelocity:I

    if-le p3, v5, :cond_0

    move v5, v4

    goto :goto_0

    :cond_0
    move v5, v3

    :goto_0
    add-int/2addr v5, p1

    if-gez p3, :cond_8

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result v6

    if-ne v5, v6, :cond_8

    sget-boolean v6, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v6, :cond_1

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, p2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_1
    add-float/2addr v1, p2

    float-to-int v0, v1

    add-int/2addr v5, v0

    goto :goto_4

    :cond_2
    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mReverseVelocity:I

    neg-int v5, v5

    if-ge p3, v5, :cond_3

    move v5, v3

    goto :goto_1

    :cond_3
    move v5, v4

    :goto_1
    add-int/2addr v5, p1

    if-lez p3, :cond_8

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result v6

    if-ne v5, v6, :cond_8

    sget-boolean v6, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v6, :cond_4

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, p2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_4
    cmpg-float v0, p2, v1

    if-gez v0, :cond_5

    move v0, v3

    goto :goto_2

    :cond_5
    move v0, v4

    :goto_2
    sub-int/2addr v5, v0

    goto :goto_4

    :cond_6
    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-lt p1, v0, :cond_7

    const v0, 0x3f19999a    # 0.6f

    goto :goto_3

    :cond_7
    const v0, 0x3ecccccd    # 0.4f

    :goto_3
    int-to-float v1, p1

    add-float/2addr v0, p2

    add-float/2addr v1, v0

    float-to-int v5, v1

    :cond_8
    :goto_4
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lez v0, :cond_9

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v6

    sub-int/2addr v6, v3

    invoke-virtual {v1, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v0, v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v1, v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    invoke-static {v5, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v5

    :cond_9
    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_a

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x6

    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {p4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p4

    aput-object p4, v1, v4

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    aput-object p3, v1, v3

    const/4 p3, 0x2

    invoke-static {p2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p2

    aput-object p2, v1, p3

    const/4 p2, 0x3

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result p3

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    aput-object p3, v1, p2

    const/4 p2, 0x4

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    aput-object p1, v1, p2

    const/4 p1, 0x5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    aput-object p2, v1, p1

    const-string p1, "deltaX: %s, velocity: %s, offset: %s, curItem: %s, displayItem: %s, tarItem: %s"

    invoke-static {v0, p1, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_a
    return v5
.end method

.method private endDrag()V
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    iput-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mIsUnableToDrag:Z

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_0
    return-void
.end method

.method private findPointerIndex(Landroid/view/MotionEvent;I)I
    .locals 5

    invoke-virtual {p1, p2}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result p1

    if-ltz v0, :cond_1

    if-lt v0, p1, :cond_0

    goto :goto_0

    :cond_0
    return v0

    :cond_1
    :goto_0
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    sget-object v2, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v3, 0x3

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    aput-object p2, v3, v4

    const/4 p2, 0x1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v3, p2

    const/4 p2, 0x2

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    aput-object p1, v3, p2

    const-string p1, "id[%d], index[%d], count[%d]"

    invoke-static {v2, p1, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const-string p2, "invalid_pointer"

    invoke-virtual {v1, p2, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string p1, "gesture"

    const-string p2, "gesture_view_pager"

    invoke-static {p1, p2, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    const/4 p1, -0x1

    return p1
.end method

.method private getChildRectInPagerCoordinates(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    .locals 2

    if-nez p1, :cond_0

    new-instance p1, Landroid/graphics/Rect;

    invoke-direct {p1}, Landroid/graphics/Rect;-><init>()V

    :cond_0
    if-nez p2, :cond_1

    const/4 p2, 0x0

    invoke-virtual {p1, p2, p2, p2, p2}, Landroid/graphics/Rect;->set(IIII)V

    return-object p1

    :cond_1
    invoke-virtual {p2}, Landroid/view/View;->getLeft()I

    move-result v0

    iput v0, p1, Landroid/graphics/Rect;->left:I

    invoke-virtual {p2}, Landroid/view/View;->getRight()I

    move-result v0

    iput v0, p1, Landroid/graphics/Rect;->right:I

    invoke-virtual {p2}, Landroid/view/View;->getTop()I

    move-result v0

    iput v0, p1, Landroid/graphics/Rect;->top:I

    invoke-virtual {p2}, Landroid/view/View;->getBottom()I

    move-result v0

    iput v0, p1, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {p2}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p2

    :goto_0
    instance-of v0, p2, Landroid/view/ViewGroup;

    if-eqz v0, :cond_2

    if-eq p2, p0, :cond_2

    check-cast p2, Landroid/view/ViewGroup;

    iget v0, p1, Landroid/graphics/Rect;->left:I

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getLeft()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->left:I

    iget v0, p1, Landroid/graphics/Rect;->right:I

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getRight()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->right:I

    iget v0, p1, Landroid/graphics/Rect;->top:I

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getTop()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->top:I

    iget v0, p1, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getBottom()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Rect;->bottom:I

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getParent()Landroid/view/ViewParent;

    move-result-object p2

    goto :goto_0

    :cond_2
    return-object p1
.end method

.method private getMaxScrollOffset()F
    .locals 2

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffset:F

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffsetLeftScreen:F

    sub-float/2addr v0, v1

    return v0
.end method

.method private getMinScrollOffset()F
    .locals 2

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffset:F

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffsetLeftScreen:F

    sub-float/2addr v0, v1

    return v0
.end method

.method private getSlipScale(I)F
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialHeight:I

    int-to-float v1, v1

    iget v2, p0, Lcom/miui/gallery/widget/ViewPager;->mHeightSlipRatio:F

    mul-float/2addr v1, v2

    float-to-int v1, v1

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/widget/PagerAdapter;->getSlipWidth(II)I

    move-result p1

    if-lez p1, :cond_0

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mWidthSlipRatio:F

    const/high16 v1, 0x3f800000    # 1.0f

    int-to-float p1, p1

    mul-float/2addr p1, v1

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialWidth:I

    int-to-float v1, v1

    div-float/2addr p1, v1

    invoke-static {v0, p1}, Ljava/lang/Math;->min(FF)F

    move-result p1

    goto :goto_0

    :cond_0
    iget p1, p0, Lcom/miui/gallery/widget/ViewPager;->mWidthSlipRatio:F

    :goto_0
    return p1
.end method

.method private infoForCurrentScrollPosition()Lcom/miui/gallery/widget/ViewPager$ItemInfo;
    .locals 12

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v0

    const/4 v1, 0x0

    if-lez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v2

    int-to-float v2, v2

    int-to-float v3, v0

    div-float/2addr v2, v3

    goto :goto_0

    :cond_0
    move v2, v1

    :goto_0
    if-lez v0, :cond_1

    iget v3, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    int-to-float v3, v3

    int-to-float v0, v0

    div-float v0, v3, v0

    goto :goto_1

    :cond_1
    move v0, v1

    :goto_1
    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, -0x1

    const/4 v6, 0x1

    move v8, v1

    move v9, v8

    move v1, v4

    move v7, v5

    move v5, v6

    :goto_2
    iget-object v10, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v10}, Ljava/util/ArrayList;->size()I

    move-result v10

    if-ge v1, v10, :cond_7

    iget-object v10, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v10, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    if-nez v5, :cond_2

    iget v11, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    add-int/2addr v7, v6

    if-eq v11, v7, :cond_2

    iget-object v10, p0, Lcom/miui/gallery/widget/ViewPager;->mTempItem:Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    add-float/2addr v8, v9

    add-float/2addr v8, v0

    iput v8, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iput v7, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget-object v7, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget v8, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    invoke-virtual {v7, v8}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result v7

    iput v7, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-int/lit8 v1, v1, -0x1

    :cond_2
    iget v8, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v7, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float v7, v8, v7

    iget v9, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float v9, v8, v9

    iget v11, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v9, v11

    add-float/2addr v9, v0

    if-nez v5, :cond_4

    cmpl-float v5, v2, v7

    if-ltz v5, :cond_3

    goto :goto_3

    :cond_3
    return-object v3

    :cond_4
    :goto_3
    cmpg-float v3, v2, v9

    if-ltz v3, :cond_6

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    sub-int/2addr v3, v6

    if-ne v1, v3, :cond_5

    goto :goto_4

    :cond_5
    iget v7, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v9, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-int/lit8 v1, v1, 0x1

    move v5, v4

    move-object v3, v10

    goto :goto_2

    :cond_6
    :goto_4
    return-object v10

    :cond_7
    return-object v3
.end method

.method private isGutterDrag(FF)Z
    .locals 3

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mGutterSize:I

    int-to-float v0, v0

    cmpg-float v0, p1, v0

    const/4 v1, 0x0

    if-gez v0, :cond_0

    cmpl-float v0, p2, v1

    if-gtz v0, :cond_1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v0

    iget v2, p0, Lcom/miui/gallery/widget/ViewPager;->mGutterSize:I

    sub-int/2addr v0, v2

    int-to-float v0, v0

    cmpl-float p1, p1, v0

    if-lez p1, :cond_2

    cmpg-float p1, p2, v1

    if-gez p1, :cond_2

    :cond_1
    const/4 p1, 0x1

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method private needIntercept(FF)Z
    .locals 4

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mTouchSlop:I

    int-to-float v0, v0

    cmpl-float v0, p1, v0

    if-lez v0, :cond_0

    float-to-double v0, p1

    float-to-double p1, p2

    const-wide v2, 0x3fdbecde5da115a9L    # 0.4363323129985824

    invoke-static {v2, v3}, Ljava/lang/Math;->tan(D)D

    move-result-wide v2

    mul-double/2addr p1, v2

    cmpl-double p1, v0, p1

    if-lez p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method private onSecondaryPointerUp(Landroid/view/MotionEvent;)V
    .locals 3

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v1

    iget v2, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    if-ne v1, v2, :cond_1

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v1

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/view/VelocityTracker;->clear()V

    :cond_1
    return-void
.end method

.method private pageScrolled(IZ)Z
    .locals 6

    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result p2

    const-string v0, "onPageScrolled did not call superclass implementation"

    const/4 v1, 0x0

    if-nez p2, :cond_1

    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCalledSuper:Z

    const/4 p1, 0x0

    invoke-virtual {p0, v1, p1, v1}, Lcom/miui/gallery/widget/ViewPager;->onPageScrolled(IFI)V

    iget-boolean p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCalledSuper:Z

    if-eqz p1, :cond_0

    return v1

    :cond_0
    new-instance p1, Ljava/lang/IllegalStateException;

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_1
    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->infoForCurrentScrollPosition()Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object p2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v2

    iget v3, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    add-int v4, v2, v3

    int-to-float v3, v3

    int-to-float v2, v2

    div-float/2addr v3, v2

    iget v5, p2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    int-to-float p1, p1

    div-float/2addr p1, v2

    iget v2, p2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    sub-float/2addr p1, v2

    iget v2, p2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float/2addr p1, v2

    iget p2, p2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr p2, v3

    div-float/2addr p1, p2

    int-to-float p2, v4

    mul-float/2addr p2, p1

    float-to-int p2, p2

    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCalledSuper:Z

    invoke-virtual {p0, v5, p1, p2}, Lcom/miui/gallery/widget/ViewPager;->onPageScrolled(IFI)V

    iget-boolean p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCalledSuper:Z

    if-eqz p1, :cond_2

    const/4 p1, 0x1

    return p1

    :cond_2
    new-instance p1, Ljava/lang/IllegalStateException;

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method private performDrag(F)Z
    .locals 9

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    sub-float/2addr v0, p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result p1

    int-to-float p1, p1

    add-float/2addr p1, v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v0

    int-to-float v0, v0

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->getMinScrollOffset()F

    move-result v1

    mul-float/2addr v1, v0

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->getMaxScrollOffset()F

    move-result v2

    mul-float/2addr v2, v0

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    const/4 v4, 0x0

    invoke-virtual {v3, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget-object v5, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v6

    const/4 v7, 0x1

    sub-int/2addr v6, v7

    invoke-virtual {v5, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v6, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-eqz v6, :cond_0

    iget v1, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v3, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float/2addr v1, v3

    mul-float/2addr v1, v0

    move v3, v4

    goto :goto_0

    :cond_0
    move v3, v7

    :goto_0
    iget v6, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget-object v8, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v8}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v8

    sub-int/2addr v8, v7

    if-eq v6, v8, :cond_1

    iget v2, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v5, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float/2addr v2, v5

    mul-float/2addr v2, v0

    move v5, v4

    goto :goto_1

    :cond_1
    move v5, v7

    :goto_1
    cmpg-float v6, p1, v1

    if-gez v6, :cond_3

    if-eqz v3, :cond_2

    sub-float p1, v1, p1

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result p1

    div-float/2addr p1, v0

    invoke-virtual {v2, p1}, Landroid/widget/EdgeEffect;->onPull(F)V

    move v4, v7

    :cond_2
    move p1, v1

    goto :goto_2

    :cond_3
    cmpl-float v1, p1, v2

    if-lez v1, :cond_5

    if-eqz v5, :cond_4

    sub-float/2addr p1, v2

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result p1

    div-float/2addr p1, v0

    invoke-virtual {v1, p1}, Landroid/widget/EdgeEffect;->onPull(F)V

    move v4, v7

    :cond_4
    move p1, v2

    :cond_5
    :goto_2
    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    float-to-int v1, p1

    int-to-float v2, v1

    sub-float/2addr p1, v2

    add-float/2addr v0, p1

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollY()I

    move-result p1

    invoke-virtual {p0, v1, p1}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    invoke-direct {p0, v1, v7}, Lcom/miui/gallery/widget/ViewPager;->pageScrolled(IZ)Z

    return v4
.end method

.method private populateLeft(Lcom/miui/gallery/widget/ViewPager$ItemInfo;II)I
    .locals 6

    add-int/lit8 v0, p2, -0x1

    const/4 v1, 0x0

    if-ltz v0, :cond_0

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_0

    :cond_0
    move-object v2, v1

    :goto_0
    const/high16 v3, 0x40000000    # 2.0f

    iget p1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    sub-float/2addr v3, p1

    iget p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    add-int/lit8 p1, p1, -0x1

    const/4 v4, 0x0

    :goto_1
    if-ltz p1, :cond_6

    cmpl-float v5, v4, v3

    if-ltz v5, :cond_3

    if-ge p1, p3, :cond_3

    if-nez v2, :cond_1

    goto :goto_3

    :cond_1
    iget v5, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne p1, v5, :cond_5

    iget-boolean v5, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->scrolling:Z

    if-nez v5, :cond_5

    iget-object v5, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v5, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    iget-object v5, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v2, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    invoke-virtual {v5, p0, p1, v2}, Lcom/miui/gallery/widget/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    add-int/lit8 v0, v0, -0x1

    add-int/lit8 p2, p2, -0x1

    if-ltz v0, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_2

    :cond_2
    move-object v2, v1

    goto :goto_2

    :cond_3
    if-eqz v2, :cond_4

    iget v5, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne p1, v5, :cond_4

    iget v2, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v4, v2

    add-int/lit8 v0, v0, -0x1

    if-ltz v0, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_2

    :cond_4
    add-int/lit8 v2, v0, 0x1

    invoke-virtual {p0, p1, v2}, Lcom/miui/gallery/widget/ViewPager;->addNewItem(II)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v2

    iget v2, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v4, v2

    add-int/lit8 p2, p2, 0x1

    if-ltz v0, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    :cond_5
    :goto_2
    add-int/lit8 p1, p1, -0x1

    goto :goto_1

    :cond_6
    :goto_3
    return p2
.end method

.method private populateRight(Lcom/miui/gallery/widget/ViewPager$ItemInfo;II)I
    .locals 7

    iget p1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-int/lit8 v0, p2, 0x1

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v1}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v1

    const/high16 v2, 0x40000000    # 2.0f

    cmpg-float v3, p1, v2

    if-gez v3, :cond_6

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x0

    if-ge v0, v3, :cond_0

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_0

    :cond_0
    move-object v3, v4

    :goto_0
    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    :cond_1
    :goto_1
    add-int/lit8 v5, v5, 0x1

    if-ge v5, v1, :cond_6

    cmpl-float v6, p1, v2

    if-ltz v6, :cond_4

    if-le v5, p3, :cond_4

    if-nez v3, :cond_2

    goto :goto_2

    :cond_2
    iget v6, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v5, v6, :cond_1

    iget-boolean v6, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->scrolling:Z

    if-nez v6, :cond_1

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v3, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    invoke-virtual {v6, p0, v5, v3}, Lcom/miui/gallery/widget/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v0, v3, :cond_3

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_1

    :cond_3
    move-object v3, v4

    goto :goto_1

    :cond_4
    if-eqz v3, :cond_5

    iget v6, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v5, v6, :cond_5

    iget v3, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr p1, v3

    add-int/lit8 v0, v0, 0x1

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v0, v3, :cond_3

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_1

    :cond_5
    invoke-virtual {p0, v5, v0}, Lcom/miui/gallery/widget/ViewPager;->addNewItem(II)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v3

    add-int/lit8 v0, v0, 0x1

    iget v3, v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr p1, v3

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v0, v3, :cond_3

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v3, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_1

    :cond_6
    :goto_2
    return p2
.end method

.method private recomputeScrollPosition(IIII)V
    .locals 6

    if-lez p2, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    add-int/2addr p3, p1

    add-int/2addr p2, p4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result p4

    int-to-float p4, p4

    int-to-float p2, p2

    div-float/2addr p4, p2

    int-to-float p2, p3

    mul-float/2addr p4, p2

    float-to-int v1, p4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollY()I

    move-result p2

    invoke-virtual {p0, v1, p2}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {p2}, Landroid/widget/Scroller;->isFinished()Z

    move-result p2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {p2}, Landroid/widget/Scroller;->getDuration()I

    move-result p2

    iget-object p3, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {p3}, Landroid/widget/Scroller;->timePassed()I

    move-result p3

    sub-int v5, p2, p3

    iget p2, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0, p2}, Lcom/miui/gallery/widget/ViewPager;->infoForPosition(I)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object p2

    if-eqz p2, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    const/4 v2, 0x0

    iget p3, p2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget p2, p2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float/2addr p3, p2

    int-to-float p1, p1

    mul-float/2addr p3, p1

    float-to-int v3, p3

    const/4 v4, 0x0

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-virtual/range {v0 .. v5}, Landroid/widget/Scroller;->startScroll(IIIII)V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {p1}, Landroid/widget/Scroller;->abortAnimation()V

    goto :goto_1

    :cond_1
    iget p2, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0, p2}, Lcom/miui/gallery/widget/ViewPager;->infoForPosition(I)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object p2

    if-eqz p2, :cond_2

    iget p2, p2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget p3, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffset:F

    invoke-static {p2, p3}, Ljava/lang/Math;->min(FF)F

    move-result p2

    goto :goto_0

    :cond_2
    const/4 p2, 0x0

    :goto_0
    int-to-float p1, p1

    mul-float/2addr p2, p1

    float-to-int p1, p2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result p2

    if-eq p1, p2, :cond_3

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->completeScroll()V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollY()I

    move-result p2

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    :cond_3
    :goto_1
    return-void
.end method

.method private refreshItem(I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-nez v0, :cond_0

    return-void

    :cond_0
    if-ltz p1, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lt p1, v0, :cond_1

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    if-eqz p1, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    iget p1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/widget/PagerAdapter;->refreshItem(Ljava/lang/Object;I)V

    :cond_2
    :goto_0
    return-void
.end method

.method private removeNonDecorViews()V
    .locals 2

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v1

    if-ge v0, v1, :cond_1

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v1, v1, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-nez v1, :cond_0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->removeViewAt(I)V

    add-int/lit8 v0, v0, -0x1

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method private resetSlipInitials()V
    .locals 2

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mSlipProgress:F

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/gallery/util/MiscUtil;->floatEquals(FF)Z

    move-result v0

    if-eqz v0, :cond_0

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialHeight:I

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialHeight:I

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialWidth:I

    :cond_0
    return-void
.end method

.method private setScrollState(I)V
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScrollState:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mScrollState:I

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mOnPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_1

    invoke-interface {v0, p1}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageScrollStateChanged(I)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mInternalPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_2

    invoke-interface {v0, p1}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageScrollStateChanged(I)V

    :cond_2
    return-void
.end method

.method private setScrollingCacheEnabled(Z)V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScrollingCacheEnabled:Z

    if-eq v0, p1, :cond_0

    iput-boolean p1, p0, Lcom/miui/gallery/widget/ViewPager;->mScrollingCacheEnabled:Z

    :cond_0
    return-void
.end method


# virtual methods
.method public addFocusables(Ljava/util/ArrayList;II)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Landroid/view/View;",
            ">;II)V"
        }
    .end annotation

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getDescendantFocusability()I

    move-result v1

    const/high16 v2, 0x60000

    if-eq v1, v2, :cond_2

    const/4 v2, 0x0

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v3

    if-ge v2, v3, :cond_2

    invoke-virtual {p0, v2}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    if-nez v4, :cond_1

    invoke-virtual {p0, v3}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v4

    if-eqz v4, :cond_1

    iget v4, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne v4, v5, :cond_1

    invoke-virtual {v3, p1, p2, p3}, Landroid/view/View;->addFocusables(Ljava/util/ArrayList;II)V

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    const/high16 p2, 0x40000

    if-ne v1, p2, :cond_3

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p2

    if-ne v0, p2, :cond_6

    :cond_3
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->isFocusable()Z

    move-result p2

    if-nez p2, :cond_4

    return-void

    :cond_4
    const/4 p2, 0x1

    and-int/2addr p3, p2

    if-ne p3, p2, :cond_5

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->isInTouchMode()Z

    move-result p2

    if-eqz p2, :cond_5

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->isFocusableInTouchMode()Z

    move-result p2

    if-nez p2, :cond_5

    return-void

    :cond_5
    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_6
    return-void
.end method

.method addNewItem(II)Lcom/miui/gallery/widget/ViewPager$ItemInfo;
    .locals 2

    new-instance v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    invoke-direct {v0}, Lcom/miui/gallery/widget/ViewPager$ItemInfo;-><init>()V

    iput p1, v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v1, p0, p1}, Lcom/miui/gallery/widget/PagerAdapter;->instantiateItem(Landroid/view/ViewGroup;I)Ljava/lang/Object;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v1, p1}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result p1

    iput p1, v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    if-ltz p2, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-lt p2, p1, :cond_0

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {p1, p2, v0}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    goto :goto_1

    :cond_1
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_1
    return-object v0
.end method

.method public addTouchables(Ljava/util/ArrayList;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Landroid/view/View;",
            ">;)V"
        }
    .end annotation

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v1

    if-ge v0, v1, :cond_1

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getVisibility()I

    move-result v2

    if-nez v2, :cond_0

    invoke-virtual {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v2

    if-eqz v2, :cond_0

    iget v2, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v3, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne v2, v3, :cond_0

    invoke-virtual {v1, p1}, Landroid/view/View;->addTouchables(Ljava/util/ArrayList;)V

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V
    .locals 3

    invoke-virtual {p0, p3}, Lcom/miui/gallery/widget/ViewPager;->checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0, p3}, Lcom/miui/gallery/widget/ViewPager;->generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;

    move-result-object p3

    :cond_0
    move-object v0, p3

    check-cast v0, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v1, v0, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    instance-of v2, p1, Lcom/miui/gallery/widget/ViewPager$Decor;

    or-int/2addr v1, v2

    iput-boolean v1, v0, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    iget-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mInLayout:Z

    if-eqz v1, :cond_2

    iget-boolean v1, v0, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-nez v1, :cond_1

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->needsMeasure:Z

    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/gallery/widget/ViewPager;->addViewInLayout(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)Z

    goto :goto_0

    :cond_1
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string p2, "Cannot add pager decor view during layout"

    invoke-direct {p1, p2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_2
    invoke-super {p0, p1, p2, p3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    :goto_0
    return-void
.end method

.method public arrowScroll(I)Z
    .locals 5

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->findFocus()Landroid/view/View;

    move-result-object v0

    if-ne v0, p0, :cond_0

    const/4 v0, 0x0

    :cond_0
    const/4 v1, 0x0

    invoke-static {}, Landroid/view/FocusFinder;->getInstance()Landroid/view/FocusFinder;

    move-result-object v2

    invoke-virtual {v2, p0, v0, p1}, Landroid/view/FocusFinder;->findNextFocus(Landroid/view/ViewGroup;Landroid/view/View;I)Landroid/view/View;

    move-result-object v2

    const/16 v3, 0x42

    const/16 v4, 0x11

    if-eqz v2, :cond_4

    if-eq v2, v0, :cond_4

    if-ne p1, v4, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mTempRect:Landroid/graphics/Rect;

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/widget/ViewPager;->getChildRectInPagerCoordinates(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v1

    iget v1, v1, Landroid/graphics/Rect;->left:I

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mTempRect:Landroid/graphics/Rect;

    invoke-direct {p0, v3, v0}, Lcom/miui/gallery/widget/ViewPager;->getChildRectInPagerCoordinates(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v3

    iget v3, v3, Landroid/graphics/Rect;->left:I

    if-eqz v0, :cond_1

    if-lt v1, v3, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->pageLeft()Z

    move-result v0

    goto :goto_0

    :cond_1
    invoke-virtual {v2}, Landroid/view/View;->requestFocus()Z

    move-result v0

    :goto_0
    move v1, v0

    goto :goto_2

    :cond_2
    if-ne p1, v3, :cond_8

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mTempRect:Landroid/graphics/Rect;

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/widget/ViewPager;->getChildRectInPagerCoordinates(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v1

    iget v1, v1, Landroid/graphics/Rect;->left:I

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mTempRect:Landroid/graphics/Rect;

    invoke-direct {p0, v3, v0}, Lcom/miui/gallery/widget/ViewPager;->getChildRectInPagerCoordinates(Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;

    move-result-object v3

    iget v3, v3, Landroid/graphics/Rect;->left:I

    if-eqz v0, :cond_3

    if-gt v1, v3, :cond_3

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->pageRight()Z

    move-result v0

    goto :goto_0

    :cond_3
    invoke-virtual {v2}, Landroid/view/View;->requestFocus()Z

    move-result v0

    goto :goto_0

    :cond_4
    if-eq p1, v4, :cond_7

    const/4 v0, 0x1

    if-ne p1, v0, :cond_5

    goto :goto_1

    :cond_5
    if-eq p1, v3, :cond_6

    const/4 v0, 0x2

    if-ne p1, v0, :cond_8

    :cond_6
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->pageRight()Z

    move-result v1

    goto :goto_2

    :cond_7
    :goto_1
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->pageLeft()Z

    move-result v1

    :cond_8
    :goto_2
    if-eqz v1, :cond_9

    invoke-static {p1}, Landroid/view/SoundEffectConstants;->getContantForFocusDirection(I)I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->playSoundEffect(I)V

    :cond_9
    return v1
.end method

.method protected canScroll(Landroid/view/View;ZIII)Z
    .locals 12

    move-object v0, p1

    instance-of v1, v0, Landroid/view/ViewGroup;

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    move-object v1, v0

    check-cast v1, Landroid/view/ViewGroup;

    invoke-virtual {p1}, Landroid/view/View;->getScrollX()I

    move-result v3

    invoke-virtual {p1}, Landroid/view/View;->getScrollY()I

    move-result v4

    invoke-virtual {v1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v5

    sub-int/2addr v5, v2

    :goto_0
    if-ltz v5, :cond_1

    invoke-virtual {v1, v5}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v7

    add-int v6, p4, v3

    invoke-virtual {v7}, Landroid/view/View;->getLeft()I

    move-result v8

    if-lt v6, v8, :cond_0

    invoke-virtual {v7}, Landroid/view/View;->getRight()I

    move-result v8

    if-ge v6, v8, :cond_0

    add-int v8, p5, v4

    invoke-virtual {v7}, Landroid/view/View;->getTop()I

    move-result v9

    if-lt v8, v9, :cond_0

    invoke-virtual {v7}, Landroid/view/View;->getBottom()I

    move-result v9

    if-ge v8, v9, :cond_0

    const/4 v9, 0x1

    invoke-virtual {v7}, Landroid/view/View;->getLeft()I

    move-result v10

    sub-int v10, v6, v10

    invoke-virtual {v7}, Landroid/view/View;->getTop()I

    move-result v6

    sub-int v11, v8, v6

    move-object v6, p0

    move v8, v9

    move v9, p3

    invoke-virtual/range {v6 .. v11}, Lcom/miui/gallery/widget/ViewPager;->canScroll(Landroid/view/View;ZIII)Z

    move-result v6

    if-eqz v6, :cond_0

    return v2

    :cond_0
    add-int/lit8 v5, v5, -0x1

    goto :goto_0

    :cond_1
    if-eqz p2, :cond_2

    move v1, p3

    neg-int v1, v1

    invoke-virtual {p1, v1}, Landroid/view/View;->canScrollHorizontally(I)Z

    move-result v0

    if-eqz v0, :cond_2

    goto :goto_1

    :cond_2
    const/4 v2, 0x0

    :goto_1
    return v2
.end method

.method protected checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z
    .locals 1

    instance-of v0, p1, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    if-eqz v0, :cond_0

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->checkLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method public computeScroll()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollY()I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->getCurrX()I

    move-result v2

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v3}, Landroid/widget/Scroller;->getCurrY()I

    move-result v3

    if-ne v0, v2, :cond_0

    if-eq v1, v3, :cond_1

    :cond_0
    invoke-virtual {p0, v2, v3}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    const/4 v0, 0x0

    invoke-direct {p0, v2, v0}, Lcom/miui/gallery/widget/ViewPager;->pageScrolled(IZ)Z

    move-result v1

    if-nez v1, :cond_1

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v1}, Landroid/widget/Scroller;->abortAnimation()V

    invoke-virtual {p0, v0, v3}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->postInvalidateOnAnimation()V

    return-void

    :cond_2
    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->completeScroll()V

    return-void
.end method

.method dataSetChanged()V
    .locals 10

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getLeftOffscreenPageLimit()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getRightOffscreenPageLimit()I

    move-result v2

    add-int/2addr v1, v2

    const/4 v2, 0x1

    add-int/2addr v1, v2

    const/4 v3, 0x0

    if-ge v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v1}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v1

    if-ge v0, v1, :cond_0

    move v0, v2

    goto :goto_0

    :cond_0
    move v0, v3

    :goto_0
    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    move v4, v0

    move v5, v1

    move v0, v3

    move v1, v0

    :goto_1
    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v6

    if-ge v0, v6, :cond_8

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget-object v7, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v8, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    iget v9, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    invoke-virtual {v7, v8, v9}, Lcom/miui/gallery/widget/PagerAdapter;->getItemPosition(Ljava/lang/Object;I)I

    move-result v7

    const/4 v8, -0x1

    if-ne v7, v8, :cond_1

    goto :goto_3

    :cond_1
    const/4 v8, -0x3

    if-ne v7, v8, :cond_2

    invoke-direct {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->refreshItem(I)V

    goto :goto_3

    :cond_2
    const/4 v8, -0x2

    if-ne v7, v8, :cond_5

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v4, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    add-int/lit8 v0, v0, -0x1

    if-nez v1, :cond_3

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v1, p0}, Lcom/miui/gallery/widget/PagerAdapter;->startUpdate(Landroid/view/ViewGroup;)V

    move v1, v2

    :cond_3
    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget-object v8, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    invoke-virtual {v4, p0, v7, v8}, Lcom/miui/gallery/widget/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    iget v6, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v4, v6, :cond_4

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    iget-object v5, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v5}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v5

    sub-int/2addr v5, v2

    invoke-static {v4, v5}, Ljava/lang/Math;->min(II)I

    move-result v4

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v4

    move v5, v4

    :cond_4
    :goto_2
    move v4, v2

    goto :goto_3

    :cond_5
    iget v8, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-eq v8, v7, :cond_7

    iget v4, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v8, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne v4, v8, :cond_6

    move v5, v7

    :cond_6
    iput v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    goto :goto_2

    :cond_7
    :goto_3
    add-int/2addr v0, v2

    goto :goto_1

    :cond_8
    if-eqz v1, :cond_9

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/widget/PagerAdapter;->finishUpdate(Landroid/view/ViewGroup;)V

    :cond_9
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    sget-object v1, Lcom/miui/gallery/widget/ViewPager;->COMPARATOR:Ljava/util/Comparator;

    invoke-static {v0, v1}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    if-eqz v4, :cond_c

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v0

    move v1, v3

    :goto_4
    if-ge v1, v0, :cond_b

    invoke-virtual {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    invoke-virtual {v4}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v6, v4, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-nez v6, :cond_a

    const/4 v6, 0x0

    iput v6, v4, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->widthFactor:F

    :cond_a
    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    :cond_b
    invoke-virtual {p0, v5, v3, v2}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZ)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->requestLayout()V

    :cond_c
    return-void
.end method

.method public dispatchKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 1

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->dispatchKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->executeKeyEvent(Landroid/view/KeyEvent;)Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    return p1
.end method

.method public dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z
    .locals 6

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v0

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_1

    invoke-virtual {p0, v2}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    invoke-virtual {v3}, Landroid/view/View;->getVisibility()I

    move-result v4

    if-nez v4, :cond_0

    invoke-virtual {p0, v3}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v4

    if-eqz v4, :cond_0

    iget v4, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne v4, v5, :cond_0

    invoke-virtual {v3, p1}, Landroid/view/View;->dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z

    move-result v3

    if-eqz v3, :cond_0

    const/4 p1, 0x1

    return p1

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    return v1
.end method

.method distanceInfluenceForSnapDuration(F)F
    .locals 4

    const/high16 v0, 0x3f000000    # 0.5f

    sub-float/2addr p1, v0

    float-to-double v0, p1

    const-wide v2, 0x3fde28c7460698c7L    # 0.4712389167638204

    mul-double/2addr v0, v2

    double-to-float p1, v0

    float-to-double v0, p1

    invoke-static {v0, v1}, Ljava/lang/Math;->sin(D)D

    move-result-wide v0

    double-to-float p1, v0

    return p1
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 7

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->draw(Landroid/graphics/Canvas;)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getOverScrollMode()I

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    const/4 v2, 0x1

    if-ne v0, v2, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    if-le v0, v2, :cond_0

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->finish()V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->finish()V

    goto/16 :goto_1

    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v0

    if-nez v0, :cond_2

    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getHeight()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingTop()I

    move-result v2

    sub-int/2addr v1, v2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingBottom()I

    move-result v2

    sub-int/2addr v1, v2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v2

    const/high16 v3, 0x43870000    # 270.0f

    invoke-virtual {p1, v3}, Landroid/graphics/Canvas;->rotate(F)V

    neg-int v3, v1

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingTop()I

    move-result v4

    add-int/2addr v3, v4

    int-to-float v3, v3

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstOffset:F

    int-to-float v5, v2

    mul-float/2addr v4, v5

    invoke-virtual {p1, v3, v4}, Landroid/graphics/Canvas;->translate(FF)V

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v3, v1, v2}, Landroid/widget/EdgeEffect;->setSize(II)V

    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v1, p1}, Landroid/widget/EdgeEffect;->draw(Landroid/graphics/Canvas;)Z

    move-result v1

    invoke-virtual {p1, v0}, Landroid/graphics/Canvas;->restoreToCount(I)V

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v0

    if-nez v0, :cond_3

    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getHeight()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingTop()I

    move-result v4

    sub-int/2addr v3, v4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingBottom()I

    move-result v4

    sub-int/2addr v3, v4

    const/high16 v4, 0x42b40000    # 90.0f

    invoke-virtual {p1, v4}, Landroid/graphics/Canvas;->rotate(F)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingTop()I

    move-result v4

    neg-int v4, v4

    int-to-float v4, v4

    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOffset:F

    const/high16 v6, 0x3f800000    # 1.0f

    add-float/2addr v5, v6

    neg-float v5, v5

    int-to-float v6, v2

    mul-float/2addr v5, v6

    invoke-virtual {p1, v4, v5}, Landroid/graphics/Canvas;->translate(FF)V

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v4, v3, v2}, Landroid/widget/EdgeEffect;->setSize(II)V

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v2, p1}, Landroid/widget/EdgeEffect;->draw(Landroid/graphics/Canvas;)Z

    move-result v2

    or-int/2addr v1, v2

    invoke-virtual {p1, v0}, Landroid/graphics/Canvas;->restoreToCount(I)V

    :cond_3
    :goto_1
    if-eqz v1, :cond_4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->postInvalidateOnAnimation()V

    :cond_4
    return-void
.end method

.method protected drawableStateChanged()V
    .locals 2

    invoke-super {p0}, Landroid/view/ViewGroup;->drawableStateChanged()V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mMarginDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->isStateful()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getDrawableState()[I

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    :cond_0
    return-void
.end method

.method public executeKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 2

    invoke-virtual {p1}, Landroid/view/KeyEvent;->getAction()I

    move-result v0

    if-nez v0, :cond_4

    invoke-virtual {p1}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v0

    const/16 v1, 0x15

    if-eq v0, v1, :cond_3

    const/16 v1, 0x16

    if-eq v0, v1, :cond_2

    const/16 v1, 0x3d

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xb

    if-lt v0, v1, :cond_4

    invoke-virtual {p1}, Landroid/view/KeyEvent;->hasNoModifiers()Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 p1, 0x2

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->arrowScroll(I)Z

    move-result p1

    goto :goto_1

    :cond_1
    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Landroid/view/KeyEvent;->hasModifiers(I)Z

    move-result p1

    if-eqz p1, :cond_4

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->arrowScroll(I)Z

    move-result p1

    goto :goto_1

    :cond_2
    const/16 p1, 0x42

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->arrowScroll(I)Z

    move-result p1

    goto :goto_1

    :cond_3
    const/16 p1, 0x11

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->arrowScroll(I)Z

    move-result p1

    goto :goto_1

    :cond_4
    :goto_0
    const/4 p1, 0x0

    :goto_1
    return p1
.end method

.method protected generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;
    .locals 1

    new-instance v0, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    invoke-direct {v0}, Lcom/miui/gallery/widget/ViewPager$LayoutParams;-><init>()V

    return-object v0
.end method

.method public generateLayoutParams(Landroid/util/AttributeSet;)Landroid/view/ViewGroup$LayoutParams;
    .locals 2

    new-instance v0, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1, p1}, Lcom/miui/gallery/widget/ViewPager$LayoutParams;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-object v0
.end method

.method protected generateLayoutParams(Landroid/view/ViewGroup$LayoutParams;)Landroid/view/ViewGroup$LayoutParams;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->generateDefaultLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p1

    return-object p1
.end method

.method public final getActiveCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    return v0
.end method

.method public getAdapter()Lcom/miui/gallery/widget/PagerAdapter;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    return-object v0
.end method

.method public getCurrentItem()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    return v0
.end method

.method public getItem(I)Ljava/lang/Object;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v2, v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v2, p1, :cond_0

    iget-object p1, v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    return-object p1

    :cond_1
    const/4 p1, 0x0

    return-object p1
.end method

.method public final getItemByNativeIndex(I)Ljava/lang/Object;
    .locals 1

    if-ltz p1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lt p1, v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget-object p1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    return-object p1

    :cond_1
    :goto_0
    const/4 p1, 0x0

    return-object p1
.end method

.method public getLeftOffscreenPageLimit()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    return v0
.end method

.method public getPageMargin()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    return v0
.end method

.method public final getPositionByNativeIndex(I)I
    .locals 1

    if-ltz p1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lt p1, v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget p1, p1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    return p1

    :cond_1
    :goto_0
    const/4 p1, -0x1

    return p1
.end method

.method public getRightOffscreenPageLimit()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    return v0
.end method

.method infoForAnyChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;
    .locals 1

    :goto_0
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eq v0, p0, :cond_1

    instance-of p1, v0, Landroid/view/View;

    if-nez p1, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    move-object p1, v0

    check-cast p1, Landroid/view/View;

    goto :goto_0

    :cond_1
    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object p1

    return-object p1
.end method

.method infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v3, v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    invoke-virtual {v2, p1, v3}, Lcom/miui/gallery/widget/PagerAdapter;->isViewFromObject(Landroid/view/View;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    return-object v1

    :cond_1
    const/4 p1, 0x0

    return-object p1
.end method

.method infoForPosition(I)Lcom/miui/gallery/widget/ViewPager$ItemInfo;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v2, v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v2, p1, :cond_0

    return-object v1

    :cond_1
    const/4 p1, 0x0

    return-object p1
.end method

.method initViewPager(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 3

    const/4 v0, 0x0

    const/4 v1, 0x1

    if-eqz p2, :cond_0

    sget-object v2, Lcom/miui/gallery/R$styleable;->ViewPager:[I

    invoke-virtual {p1, p2, v2, p3, v0}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object p2

    invoke-virtual {p2, v1, v1}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    const/4 p3, 0x3

    invoke-virtual {p2, p3, p3}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    const/4 p3, 0x4

    const/high16 v2, 0x3f800000    # 1.0f

    invoke-virtual {p2, p3, v2}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mWidthSlipRatio:F

    invoke-virtual {p2, v0, v2}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mHeightSlipRatio:F

    const/4 p3, 0x2

    invoke-virtual {p2, p3, v2}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mMarginSlipRatio:F

    invoke-virtual {p2}, Landroid/content/res/TypedArray;->recycle()V

    :cond_0
    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->setWillNotDraw(Z)V

    const/high16 p2, 0x40000

    invoke-virtual {p0, p2}, Lcom/miui/gallery/widget/ViewPager;->setDescendantFocusability(I)V

    invoke-virtual {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setFocusable(Z)V

    new-instance p2, Landroid/widget/Scroller;

    sget-object p3, Lcom/miui/gallery/widget/ViewPager;->sInterpolator:Landroid/view/animation/Interpolator;

    invoke-direct {p2, p1, p3}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;)V

    iput-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object p2

    invoke-virtual {p2}, Landroid/view/ViewConfiguration;->getScaledPagingTouchSlop()I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mTouchSlop:I

    invoke-virtual {p2}, Landroid/view/ViewConfiguration;->getScaledMinimumFlingVelocity()I

    move-result p3

    iput p3, p0, Lcom/miui/gallery/widget/ViewPager;->mMinimumVelocity:I

    invoke-virtual {p2}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/widget/ViewPager;->mMaximumVelocity:I

    iget p2, p0, Lcom/miui/gallery/widget/ViewPager;->mMinimumVelocity:I

    mul-int/lit8 p2, p2, 0xa

    iget p3, p0, Lcom/miui/gallery/widget/ViewPager;->mMaximumVelocity:I

    div-int/lit8 p3, p3, 0xa

    invoke-static {p2, p3}, Ljava/lang/Math;->max(II)I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/widget/ViewPager;->mReverseVelocity:I

    new-instance p2, Landroid/widget/EdgeEffect;

    invoke-direct {p2, p1}, Landroid/widget/EdgeEffect;-><init>(Landroid/content/Context;)V

    iput-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    new-instance p2, Landroid/widget/EdgeEffect;

    invoke-direct {p2, p1}, Landroid/widget/EdgeEffect;-><init>(Landroid/content/Context;)V

    iput-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object p1

    iget p1, p1, Landroid/util/DisplayMetrics;->density:F

    const/high16 p2, 0x41200000    # 10.0f

    mul-float/2addr p2, p1

    float-to-int p2, p2

    iput p2, p0, Lcom/miui/gallery/widget/ViewPager;->mFlingDistance:I

    const/high16 p2, 0x40000000    # 2.0f

    mul-float/2addr p2, p1

    float-to-int p2, p2

    iput p2, p0, Lcom/miui/gallery/widget/ViewPager;->mCloseEnough:I

    const/high16 p2, 0x41800000    # 16.0f

    mul-float/2addr p1, p2

    float-to-int p1, p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mDefaultGutterSize:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getImportantForAccessibility()I

    move-result p1

    if-nez p1, :cond_1

    invoke-virtual {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setImportantForAccessibility(I)V

    :cond_1
    return-void
.end method

.method protected onAttachedToWindow()V
    .locals 1

    invoke-super {p0}, Landroid/view/ViewGroup;->onAttachedToWindow()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstLayout:Z

    return-void
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 2

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOrientation:I

    iget v1, p1, Landroid/content/res/Configuration;->orientation:I

    if-eq v0, v1, :cond_0

    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLastOrientation:I

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->resetSlipInitials()V

    :cond_0
    return-void
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 17

    move-object/from16 v0, p0

    iget v1, v0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    if-lez v1, :cond_4

    iget-object v1, v0, Lcom/miui/gallery/widget/ViewPager;->mMarginDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_4

    iget-object v1, v0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-lez v1, :cond_4

    iget-object v1, v0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v1, :cond_4

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v1

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v2

    iget v3, v0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    int-to-float v3, v3

    int-to-float v4, v2

    div-float/2addr v3, v4

    iget-object v5, v0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    const/4 v6, 0x0

    invoke-virtual {v5, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v7, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget-object v8, v0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v8}, Ljava/util/ArrayList;->size()I

    move-result v8

    iget v9, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget-object v10, v0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    add-int/lit8 v11, v8, -0x1

    invoke-virtual {v10, v11}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v10, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    :goto_0
    if-ge v9, v10, :cond_4

    :goto_1
    iget v11, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-le v9, v11, :cond_0

    if-ge v6, v8, :cond_0

    iget-object v5, v0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    add-int/lit8 v6, v6, 0x1

    invoke-virtual {v5, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    goto :goto_1

    :cond_0
    iget v11, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    if-ne v9, v11, :cond_1

    iget v7, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v11, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v7, v11

    mul-float/2addr v7, v4

    iget v11, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v12, v5, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    add-float/2addr v11, v12

    add-float/2addr v11, v3

    goto :goto_2

    :cond_1
    iget-object v11, v0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v11, v9}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result v11

    add-float v12, v7, v11

    mul-float/2addr v12, v4

    add-float/2addr v11, v3

    add-float/2addr v7, v11

    move v11, v7

    move v7, v12

    :goto_2
    iget v12, v0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    int-to-float v13, v12

    add-float/2addr v13, v7

    int-to-float v14, v1

    cmpl-float v13, v13, v14

    if-lez v13, :cond_2

    iget-object v13, v0, Lcom/miui/gallery/widget/ViewPager;->mMarginDrawable:Landroid/graphics/drawable/Drawable;

    const/high16 v14, 0x3f800000    # 1.0f

    sub-float v15, v7, v14

    float-to-int v15, v15

    iget v14, v0, Lcom/miui/gallery/widget/ViewPager;->mTopPageBounds:I

    int-to-float v12, v12

    add-float/2addr v12, v7

    const/high16 v16, 0x3f800000    # 1.0f

    add-float v12, v12, v16

    float-to-int v12, v12

    move/from16 v16, v3

    iget v3, v0, Lcom/miui/gallery/widget/ViewPager;->mBottomPageBounds:I

    invoke-virtual {v13, v15, v14, v12, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    iget-object v3, v0, Lcom/miui/gallery/widget/ViewPager;->mMarginDrawable:Landroid/graphics/drawable/Drawable;

    move-object/from16 v12, p1

    invoke-virtual {v3, v12}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_3

    :cond_2
    move-object/from16 v12, p1

    move/from16 v16, v3

    :goto_3
    add-int v3, v1, v2

    int-to-float v3, v3

    cmpl-float v3, v7, v3

    if-lez v3, :cond_3

    goto :goto_4

    :cond_3
    add-int/lit8 v9, v9, 0x1

    move v7, v11

    move/from16 v3, v16

    goto :goto_0

    :cond_4
    move-object/from16 v12, p1

    :goto_4
    invoke-super/range {p0 .. p1}, Landroid/view/ViewGroup;->onDraw(Landroid/graphics/Canvas;)V

    return-void
.end method

.method public onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    const-class v0, Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V
    .locals 3

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V

    const-class v0, Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    if-le v0, v1, :cond_0

    move v0, v1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setScrollable(Z)V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v0, :cond_1

    iget v2, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ltz v2, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    sub-int/2addr v0, v1

    if-ge v2, v0, :cond_1

    const/16 v0, 0x1000

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v0, :cond_2

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-lez v1, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    if-ge v1, v0, :cond_2

    const/16 v0, 0x2000

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    :cond_2
    return-void
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 18

    move-object/from16 v6, p0

    move-object/from16 v7, p1

    iget-boolean v0, v6, Lcom/miui/gallery/widget/ViewPager;->mDragEnabled:Z

    const/4 v8, 0x0

    if-nez v0, :cond_0

    return v8

    :cond_0
    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    and-int/lit16 v0, v0, 0xff

    const/4 v1, 0x3

    const/4 v2, -0x1

    const-string v9, "ViewPager"

    if-eq v0, v1, :cond_17

    const/4 v10, 0x1

    if-ne v0, v10, :cond_1

    goto/16 :goto_5

    :cond_1
    if-eqz v0, :cond_5

    iget-boolean v1, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    if-eqz v1, :cond_3

    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_2

    const-string v0, "Intercept returning true!"

    invoke-static {v9, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_2
    return v10

    :cond_3
    iget-boolean v1, v6, Lcom/miui/gallery/widget/ViewPager;->mIsUnableToDrag:Z

    if-eqz v1, :cond_5

    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_4

    const-string v0, "Intercept returning false!"

    invoke-static {v9, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_4
    return v8

    :cond_5
    const-string v1, ","

    const/4 v3, 0x2

    if-eqz v0, :cond_13

    if-eq v0, v3, :cond_7

    const/4 v1, 0x6

    if-eq v0, v1, :cond_6

    goto/16 :goto_4

    :cond_6
    invoke-direct/range {p0 .. p1}, Lcom/miui/gallery/widget/ViewPager;->onSecondaryPointerUp(Landroid/view/MotionEvent;)V

    goto/16 :goto_4

    :cond_7
    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    if-ne v0, v2, :cond_8

    goto/16 :goto_4

    :cond_8
    invoke-direct {v6, v7, v0}, Lcom/miui/gallery/widget/ViewPager;->findPointerIndex(Landroid/view/MotionEvent;I)I

    move-result v0

    if-ne v0, v2, :cond_9

    goto/16 :goto_4

    :cond_9
    invoke-virtual {v7, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v11

    iget v2, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    sub-float v12, v11, v2

    invoke-static {v12}, Ljava/lang/Math;->abs(F)F

    move-result v13

    invoke-virtual {v7, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v14

    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionY:F

    sub-float v0, v14, v0

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v15

    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_a

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Moved x to "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v14}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v2, " diff="

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v9, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_a
    const/4 v5, 0x0

    invoke-static {v12, v5}, Lcom/miui/gallery/util/MiscUtil;->floatEquals(FF)Z

    move-result v0

    if-nez v0, :cond_b

    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-direct {v6, v0, v12}, Lcom/miui/gallery/widget/ViewPager;->isGutterDrag(FF)Z

    move-result v0

    if-nez v0, :cond_b

    const/4 v2, 0x0

    float-to-int v3, v12

    float-to-int v4, v11

    float-to-int v1, v14

    move-object/from16 v0, p0

    move/from16 v16, v1

    move-object/from16 v1, p0

    move/from16 v17, v5

    move/from16 v5, v16

    invoke-virtual/range {v0 .. v5}, Lcom/miui/gallery/widget/ViewPager;->canScroll(Landroid/view/View;ZIII)Z

    move-result v0

    if-eqz v0, :cond_c

    iput v11, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    iput v11, v6, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    iput v14, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionY:F

    iput-boolean v10, v6, Lcom/miui/gallery/widget/ViewPager;->mIsUnableToDrag:Z

    return v8

    :cond_b
    move/from16 v17, v5

    :cond_c
    invoke-direct {v6, v13, v15}, Lcom/miui/gallery/widget/ViewPager;->needIntercept(FF)Z

    move-result v0

    if-eqz v0, :cond_10

    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_d

    const-string v0, "Starting drag!"

    invoke-static {v9, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_d
    iput-boolean v10, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    invoke-direct {v6, v10}, Lcom/miui/gallery/widget/ViewPager;->setScrollState(I)V

    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    sub-float v0, v11, v0

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    iget v1, v6, Lcom/miui/gallery/widget/ViewPager;->mTouchSlop:I

    mul-int/lit8 v2, v1, 0x2

    int-to-float v2, v2

    cmpl-float v0, v0, v2

    if-lez v0, :cond_e

    iput v11, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    iput v11, v6, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    iput v14, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionY:F

    goto :goto_1

    :cond_e
    cmpl-float v0, v12, v17

    if-lez v0, :cond_f

    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    int-to-float v1, v1

    add-float/2addr v0, v1

    goto :goto_0

    :cond_f
    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    int-to-float v1, v1

    sub-float/2addr v0, v1

    :goto_0
    iput v0, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    :goto_1
    invoke-direct {v6, v10}, Lcom/miui/gallery/widget/ViewPager;->setScrollingCacheEnabled(Z)V

    goto :goto_2

    :cond_10
    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mTouchSlop:I

    int-to-float v0, v0

    cmpl-float v0, v15, v0

    if-lez v0, :cond_12

    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_11

    const-string v0, "Starting unable to drag!"

    invoke-static {v9, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_11
    iput-boolean v10, v6, Lcom/miui/gallery/widget/ViewPager;->mIsUnableToDrag:Z

    :cond_12
    :goto_2
    iget-boolean v0, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    if-eqz v0, :cond_15

    invoke-direct {v6, v11}, Lcom/miui/gallery/widget/ViewPager;->performDrag(F)Z

    move-result v0

    if-eqz v0, :cond_15

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->postInvalidateOnAnimation()V

    goto/16 :goto_4

    :cond_13
    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    iput v0, v6, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    iput v0, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    iput v0, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionY:F

    invoke-virtual {v7, v8}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v0

    iput v0, v6, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    iput-boolean v8, v6, Lcom/miui/gallery/widget/ViewPager;->mIsUnableToDrag:Z

    iget-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    iget v0, v6, Lcom/miui/gallery/widget/ViewPager;->mScrollState:I

    if-ne v0, v3, :cond_14

    iget-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->getFinalX()I

    move-result v0

    iget-object v2, v6, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v2}, Landroid/widget/Scroller;->getCurrX()I

    move-result v2

    sub-int/2addr v0, v2

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    iget v2, v6, Lcom/miui/gallery/widget/ViewPager;->mCloseEnough:I

    if-le v0, v2, :cond_14

    iget-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    iput-boolean v8, v6, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    iput-boolean v10, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    invoke-direct {v6, v10}, Lcom/miui/gallery/widget/ViewPager;->setScrollState(I)V

    goto :goto_3

    :cond_14
    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->completeScroll()V

    iput-boolean v8, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    :goto_3
    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_15

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Down at "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, v6, Lcom/miui/gallery/widget/ViewPager;->mLastMotionY:F

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v1, " mIsBeingDragged="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, "mIsUnableToDrag="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, v6, Lcom/miui/gallery/widget/ViewPager;->mIsUnableToDrag:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v9, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_15
    :goto_4
    iget-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-nez v0, :cond_16

    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_16
    iget-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v0, v7}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    iget-boolean v0, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    return v0

    :cond_17
    :goto_5
    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_18

    const-string v0, "Intercept done!"

    invoke-static {v9, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_18
    iput-boolean v8, v6, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    iput-boolean v8, v6, Lcom/miui/gallery/widget/ViewPager;->mIsUnableToDrag:Z

    iput v2, v6, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    iget-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_19

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    const/4 v0, 0x0

    iput-object v0, v6, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_19
    return v8
.end method

.method protected onLayout(ZIIII)V
    .locals 18

    move-object/from16 v0, p0

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/gallery/widget/ViewPager;->mInLayout:Z

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    const/4 v2, 0x0

    iput-boolean v2, v0, Lcom/miui/gallery/widget/ViewPager;->mInLayout:Z

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v3

    sub-int v4, p4, p2

    sub-int v5, p5, p3

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingLeft()I

    move-result v6

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingTop()I

    move-result v7

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingRight()I

    move-result v8

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingBottom()I

    move-result v9

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v10

    iget v11, v0, Lcom/miui/gallery/widget/ViewPager;->mInitialHeight:I

    if-nez v11, :cond_0

    iput v4, v0, Lcom/miui/gallery/widget/ViewPager;->mInitialWidth:I

    iput v5, v0, Lcom/miui/gallery/widget/ViewPager;->mInitialHeight:I

    :cond_0
    move v11, v6

    move v12, v8

    move v6, v2

    move v8, v6

    :goto_0
    const/16 v13, 0x8

    if-ge v6, v3, :cond_8

    invoke-virtual {v0, v6}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v14

    invoke-virtual {v14}, Landroid/view/View;->getVisibility()I

    move-result v15

    if-eq v15, v13, :cond_7

    invoke-virtual {v14}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v13

    check-cast v13, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v15, v13, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-eqz v15, :cond_7

    iget v15, v13, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v15, v15, 0x7

    iget v13, v13, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v13, v13, 0x70

    if-eq v15, v1, :cond_3

    const/4 v1, 0x3

    if-eq v15, v1, :cond_2

    const/4 v1, 0x5

    if-eq v15, v1, :cond_1

    move v1, v11

    goto :goto_2

    :cond_1
    sub-int v1, v4, v12

    invoke-virtual {v14}, Landroid/view/View;->getMeasuredWidth()I

    move-result v15

    sub-int/2addr v1, v15

    invoke-virtual {v14}, Landroid/view/View;->getMeasuredWidth()I

    move-result v15

    add-int/2addr v12, v15

    goto :goto_1

    :cond_2
    invoke-virtual {v14}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    add-int/2addr v1, v11

    goto :goto_2

    :cond_3
    invoke-virtual {v14}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    sub-int v1, v4, v1

    div-int/lit8 v1, v1, 0x2

    invoke-static {v1, v11}, Ljava/lang/Math;->max(II)I

    move-result v1

    :goto_1
    move/from16 v17, v11

    move v11, v1

    move/from16 v1, v17

    :goto_2
    const/16 v15, 0x10

    if-eq v13, v15, :cond_6

    const/16 v15, 0x30

    if-eq v13, v15, :cond_5

    const/16 v15, 0x50

    if-eq v13, v15, :cond_4

    move v13, v7

    goto :goto_3

    :cond_4
    sub-int v13, v5, v9

    invoke-virtual {v14}, Landroid/view/View;->getMeasuredHeight()I

    move-result v15

    sub-int/2addr v13, v15

    invoke-virtual {v14}, Landroid/view/View;->getMeasuredHeight()I

    move-result v15

    add-int/2addr v9, v15

    goto :goto_3

    :cond_5
    invoke-virtual {v14}, Landroid/view/View;->getMeasuredHeight()I

    move-result v13

    add-int/2addr v13, v7

    move/from16 v17, v13

    move v13, v7

    move/from16 v7, v17

    goto :goto_3

    :cond_6
    invoke-virtual {v14}, Landroid/view/View;->getMeasuredHeight()I

    move-result v13

    sub-int v13, v5, v13

    div-int/lit8 v13, v13, 0x2

    invoke-static {v13, v7}, Ljava/lang/Math;->max(II)I

    move-result v13

    :goto_3
    add-int/2addr v11, v10

    invoke-virtual {v14}, Landroid/view/View;->getMeasuredWidth()I

    move-result v15

    add-int/2addr v15, v11

    invoke-virtual {v14}, Landroid/view/View;->getMeasuredHeight()I

    move-result v16

    add-int v2, v13, v16

    invoke-virtual {v14, v11, v13, v15, v2}, Landroid/view/View;->layout(IIII)V

    add-int/lit8 v8, v8, 0x1

    move v11, v1

    :cond_7
    add-int/lit8 v6, v6, 0x1

    const/4 v1, 0x1

    const/4 v2, 0x0

    goto/16 :goto_0

    :cond_8
    const/4 v1, 0x0

    :goto_4
    if-ge v1, v3, :cond_d

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    invoke-virtual {v2}, Landroid/view/View;->getVisibility()I

    move-result v6

    if-eq v6, v13, :cond_c

    invoke-virtual {v2}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v10, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-nez v10, :cond_c

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v10

    if-eqz v10, :cond_c

    int-to-float v14, v4

    iget v15, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    mul-float/2addr v14, v15

    float-to-int v14, v14

    add-int/2addr v14, v11

    iget v15, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetTopScreen:F

    int-to-float v13, v5

    mul-float/2addr v15, v13

    int-to-float v13, v7

    add-float/2addr v15, v13

    float-to-int v13, v15

    iget-boolean v15, v0, Lcom/miui/gallery/widget/ViewPager;->mForceReplayout:Z

    if-nez v15, :cond_a

    iget-boolean v15, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->needsMeasure:Z

    if-eqz v15, :cond_9

    goto :goto_5

    :cond_9
    move/from16 v16, v3

    goto :goto_6

    :cond_a
    :goto_5
    const/4 v15, 0x0

    iput-boolean v15, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->needsMeasure:Z

    sub-int v16, v4, v11

    sub-int v15, v16, v12

    int-to-float v15, v15

    iget v6, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->widthFactor:F

    mul-float/2addr v15, v6

    float-to-int v6, v15

    const/high16 v15, 0x40000000    # 2.0f

    invoke-static {v6, v15}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    const/4 v15, 0x0

    iput-boolean v15, v0, Lcom/miui/gallery/widget/ViewPager;->mForceReplayout:Z

    sub-int v15, v5, v7

    sub-int/2addr v15, v9

    move/from16 v16, v3

    const/high16 v3, 0x40000000    # 2.0f

    invoke-static {v15, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v3

    invoke-virtual {v2, v6, v3}, Landroid/view/View;->measure(II)V

    :goto_6
    sget-boolean v3, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v3, :cond_b

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Positioning #"

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v6, " "

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v15, " f="

    invoke-virtual {v3, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v10, v10, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    invoke-virtual {v3, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v10, ":"

    invoke-virtual {v3, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v10, ","

    invoke-virtual {v3, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v6

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v6, "x"

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    const-string v6, "ViewPager"

    invoke-static {v6, v3}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_b
    invoke-virtual {v2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v3

    add-int/2addr v3, v14

    invoke-virtual {v2}, Landroid/view/View;->getMeasuredHeight()I

    move-result v6

    mul-int/lit8 v10, v13, 0x2

    sub-int/2addr v6, v10

    invoke-virtual {v2, v14, v13, v3, v6}, Landroid/view/View;->layout(IIII)V

    goto :goto_7

    :cond_c
    move/from16 v16, v3

    :goto_7
    add-int/lit8 v1, v1, 0x1

    move/from16 v3, v16

    const/16 v13, 0x8

    goto/16 :goto_4

    :cond_d
    iput v7, v0, Lcom/miui/gallery/widget/ViewPager;->mTopPageBounds:I

    sub-int/2addr v5, v9

    iput v5, v0, Lcom/miui/gallery/widget/ViewPager;->mBottomPageBounds:I

    iput v8, v0, Lcom/miui/gallery/widget/ViewPager;->mDecorChildCount:I

    const/4 v1, 0x0

    iput-boolean v1, v0, Lcom/miui/gallery/widget/ViewPager;->mFirstLayout:Z

    return-void
.end method

.method protected onMeasure(II)V
    .locals 16

    move-object/from16 v0, p0

    const/4 v1, 0x0

    move/from16 v2, p1

    invoke-static {v1, v2}, Lcom/miui/gallery/widget/ViewPager;->getDefaultSize(II)I

    move-result v2

    move/from16 v3, p2

    invoke-static {v1, v3}, Lcom/miui/gallery/widget/ViewPager;->getDefaultSize(II)I

    move-result v3

    invoke-virtual {v0, v2, v3}, Lcom/miui/gallery/widget/ViewPager;->setMeasuredDimension(II)V

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getMeasuredWidth()I

    move-result v2

    div-int/lit8 v3, v2, 0xa

    iget v4, v0, Lcom/miui/gallery/widget/ViewPager;->mDefaultGutterSize:I

    invoke-static {v3, v4}, Ljava/lang/Math;->min(II)I

    move-result v3

    iput v3, v0, Lcom/miui/gallery/widget/ViewPager;->mGutterSize:I

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingLeft()I

    move-result v3

    sub-int/2addr v2, v3

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingRight()I

    move-result v3

    sub-int/2addr v2, v3

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getMeasuredHeight()I

    move-result v3

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingTop()I

    move-result v4

    sub-int/2addr v3, v4

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingBottom()I

    move-result v4

    sub-int/2addr v3, v4

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v4

    move v5, v3

    move v3, v2

    move v2, v1

    :goto_0
    const/16 v6, 0x8

    const/4 v7, 0x1

    const/high16 v8, 0x40000000    # 2.0f

    if-ge v2, v4, :cond_c

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v9

    invoke-virtual {v9}, Landroid/view/View;->getVisibility()I

    move-result v10

    if-eq v10, v6, :cond_b

    invoke-virtual {v9}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    if-eqz v6, :cond_b

    iget-boolean v10, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-eqz v10, :cond_b

    iget v10, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v10, v10, 0x7

    iget v11, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v11, v11, 0x70

    const/16 v12, 0x30

    if-eq v11, v12, :cond_1

    const/16 v12, 0x50

    if-ne v11, v12, :cond_0

    goto :goto_1

    :cond_0
    move v11, v1

    goto :goto_2

    :cond_1
    :goto_1
    move v11, v7

    :goto_2
    const/4 v12, 0x3

    if-eq v10, v12, :cond_3

    const/4 v12, 0x5

    if-ne v10, v12, :cond_2

    goto :goto_3

    :cond_2
    move v7, v1

    :cond_3
    :goto_3
    const/high16 v10, -0x80000000

    if-eqz v11, :cond_4

    move v12, v10

    move v10, v8

    goto :goto_4

    :cond_4
    if-eqz v7, :cond_5

    move v12, v8

    goto :goto_4

    :cond_5
    move v12, v10

    :goto_4
    iget v13, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->width:I

    const/4 v14, -0x1

    const/4 v15, -0x2

    if-eq v13, v15, :cond_7

    iget v10, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->width:I

    if-eq v10, v14, :cond_6

    iget v10, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->width:I

    move v13, v10

    goto :goto_5

    :cond_6
    move v13, v3

    :goto_5
    move v10, v8

    goto :goto_6

    :cond_7
    move v13, v3

    :goto_6
    iget v1, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->height:I

    if-eq v1, v15, :cond_9

    iget v1, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->height:I

    if-eq v1, v14, :cond_8

    iget v1, v6, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->height:I

    goto :goto_7

    :cond_8
    move v1, v5

    goto :goto_7

    :cond_9
    move v1, v5

    move v8, v12

    :goto_7
    invoke-static {v13, v10}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v6

    invoke-static {v1, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    invoke-virtual {v9, v6, v1}, Landroid/view/View;->measure(II)V

    if-eqz v11, :cond_a

    invoke-virtual {v9}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    sub-int/2addr v5, v1

    goto :goto_8

    :cond_a
    if-eqz v7, :cond_b

    invoke-virtual {v9}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    sub-int/2addr v3, v1

    :cond_b
    :goto_8
    add-int/lit8 v2, v2, 0x1

    const/4 v1, 0x0

    goto/16 :goto_0

    :cond_c
    invoke-static {v3, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    iput v1, v0, Lcom/miui/gallery/widget/ViewPager;->mChildWidthMeasureSpec:I

    invoke-static {v5, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    iput v1, v0, Lcom/miui/gallery/widget/ViewPager;->mChildHeightMeasureSpec:I

    iput-boolean v7, v0, Lcom/miui/gallery/widget/ViewPager;->mInLayout:Z

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    const/4 v1, 0x0

    iput-boolean v1, v0, Lcom/miui/gallery/widget/ViewPager;->mInLayout:Z

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v2

    :goto_9
    if-ge v1, v2, :cond_f

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    invoke-virtual {v4}, Landroid/view/View;->getVisibility()I

    move-result v7

    if-eq v7, v6, :cond_e

    sget-boolean v7, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v7, :cond_d

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "Measuring #"

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v9, " "

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v9, ": "

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v9, v0, Lcom/miui/gallery/widget/ViewPager;->mChildWidthMeasureSpec:I

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    const-string v9, "ViewPager"

    invoke-static {v9, v7}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_d
    invoke-virtual {v4}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v7

    check-cast v7, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v9, v7, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-nez v9, :cond_e

    int-to-float v9, v3

    iget v7, v7, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->widthFactor:F

    mul-float/2addr v9, v7

    float-to-int v7, v9

    invoke-static {v7, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v7

    invoke-static {v5, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v9

    invoke-virtual {v4, v7, v9}, Landroid/view/View;->measure(II)V

    :cond_e
    add-int/lit8 v1, v1, 0x1

    goto :goto_9

    :cond_f
    return-void
.end method

.method protected onPageScrolled(IFI)V
    .locals 11

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mDecorChildCount:I

    const/4 v1, 0x1

    if-lez v0, :cond_5

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingLeft()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getPaddingRight()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v5

    const/4 v6, 0x0

    :goto_0
    if-ge v6, v5, :cond_5

    invoke-virtual {p0, v6}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v7

    invoke-virtual {v7}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v8

    check-cast v8, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v9, v8, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-nez v9, :cond_0

    goto :goto_3

    :cond_0
    iget v8, v8, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->gravity:I

    and-int/lit8 v8, v8, 0x7

    if-eq v8, v1, :cond_3

    const/4 v9, 0x3

    if-eq v8, v9, :cond_2

    const/4 v9, 0x5

    if-eq v8, v9, :cond_1

    move v8, v2

    goto :goto_2

    :cond_1
    sub-int v8, v4, v3

    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    sub-int/2addr v8, v9

    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    add-int/2addr v3, v9

    goto :goto_1

    :cond_2
    invoke-virtual {v7}, Landroid/view/View;->getWidth()I

    move-result v8

    add-int/2addr v8, v2

    goto :goto_2

    :cond_3
    invoke-virtual {v7}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    sub-int v8, v4, v8

    div-int/lit8 v8, v8, 0x2

    invoke-static {v8, v2}, Ljava/lang/Math;->max(II)I

    move-result v8

    :goto_1
    move v10, v8

    move v8, v2

    move v2, v10

    :goto_2
    add-int/2addr v2, v0

    invoke-virtual {v7}, Landroid/view/View;->getLeft()I

    move-result v9

    sub-int/2addr v2, v9

    if-eqz v2, :cond_4

    invoke-virtual {v7, v2}, Landroid/view/View;->offsetLeftAndRight(I)V

    :cond_4
    move v2, v8

    :goto_3
    add-int/lit8 v6, v6, 0x1

    goto :goto_0

    :cond_5
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mOnPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_6

    invoke-interface {v0, p1, p2, p3}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageScrolled(IFI)V

    :cond_6
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mInternalPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz v0, :cond_7

    invoke-interface {v0, p1, p2, p3}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageScrolled(IFI)V

    :cond_7
    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCalledSuper:Z

    return-void
.end method

.method protected onRequestFocusInDescendants(ILandroid/graphics/Rect;)Z
    .locals 8

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v0

    and-int/lit8 v1, p1, 0x2

    const/4 v2, 0x0

    const/4 v3, -0x1

    const/4 v4, 0x1

    if-eqz v1, :cond_0

    move v3, v0

    move v0, v2

    move v1, v4

    goto :goto_0

    :cond_0
    add-int/lit8 v0, v0, -0x1

    move v1, v3

    :goto_0
    if-eq v0, v3, :cond_2

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    invoke-virtual {v5}, Landroid/view/View;->getVisibility()I

    move-result v6

    if-nez v6, :cond_1

    invoke-virtual {p0, v5}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v6

    if-eqz v6, :cond_1

    iget v6, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v7, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne v6, v7, :cond_1

    invoke-virtual {v5, p1, p2}, Landroid/view/View;->requestFocus(ILandroid/graphics/Rect;)Z

    move-result v5

    if-eqz v5, :cond_1

    return v4

    :cond_1
    add-int/2addr v0, v1

    goto :goto_0

    :cond_2
    return v2
.end method

.method public onRestoreInstanceState(Landroid/os/Parcelable;)V
    .locals 3

    instance-of v0, p1, Lcom/miui/gallery/widget/ViewPager$SavedState;

    if-nez v0, :cond_0

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    return-void

    :cond_0
    check-cast p1, Lcom/miui/gallery/widget/ViewPager$SavedState;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/ViewPager$SavedState;->getSuperState()Landroid/os/Parcelable;

    move-result-object v0

    invoke-super {p0, v0}, Landroid/view/ViewGroup;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v0, :cond_1

    iget-object v1, p1, Lcom/miui/gallery/widget/ViewPager$SavedState;->adapterState:Landroid/os/Parcelable;

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/widget/PagerAdapter;->restoreState(Landroid/os/Parcelable;Ljava/lang/ClassLoader;)V

    iget p1, p1, Lcom/miui/gallery/widget/ViewPager$SavedState;->position:I

    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-virtual {p0, p1, v0, v1}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZ)V

    goto :goto_0

    :cond_1
    iget v0, p1, Lcom/miui/gallery/widget/ViewPager$SavedState;->position:I

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredCurItem:I

    iget-object p1, p1, Lcom/miui/gallery/widget/ViewPager$SavedState;->adapterState:Landroid/os/Parcelable;

    iput-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredAdapterState:Landroid/os/Parcelable;

    :goto_0
    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Parcelable;
    .locals 2

    invoke-super {p0}, Landroid/view/ViewGroup;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/widget/ViewPager$SavedState;

    invoke-direct {v1, v0}, Lcom/miui/gallery/widget/ViewPager$SavedState;-><init>(Landroid/os/Parcelable;)V

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    iput v0, v1, Lcom/miui/gallery/widget/ViewPager$SavedState;->position:I

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->saveState()Landroid/os/Parcelable;

    move-result-object v0

    iput-object v0, v1, Lcom/miui/gallery/widget/ViewPager$SavedState;->adapterState:Landroid/os/Parcelable;

    :cond_0
    return-object v1
.end method

.method protected onSizeChanged(IIII)V
    .locals 0

    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/ViewGroup;->onSizeChanged(IIII)V

    if-eq p1, p3, :cond_0

    const/4 p2, 0x0

    invoke-direct {p0, p1, p3, p2, p2}, Lcom/miui/gallery/widget/ViewPager;->recomputeScrollPosition(IIII)V

    :cond_0
    return-void
.end method

.method public onSlipping(F)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mSlipProgress:F

    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 10

    iget-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mFakeDragging:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    return v1

    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    const/4 v2, 0x0

    if-nez v0, :cond_1

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getEdgeFlags()I

    move-result v0

    if-eqz v0, :cond_1

    return v2

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v0, :cond_14

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    if-nez v0, :cond_2

    goto/16 :goto_3

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-nez v0, :cond_3

    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_3
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    and-int/lit16 v0, v0, 0xff

    if-eqz v0, :cond_11

    const/4 v3, -0x1

    if-eq v0, v1, :cond_f

    const/4 v4, 0x2

    if-eq v0, v4, :cond_8

    const/4 v4, 0x3

    if-eq v0, v4, :cond_7

    const/4 v4, 0x5

    if-eq v0, v4, :cond_6

    const/4 v4, 0x6

    if-eq v0, v4, :cond_4

    goto/16 :goto_2

    :cond_4
    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->onSecondaryPointerUp(Landroid/view/MotionEvent;)V

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/widget/ViewPager;->findPointerIndex(Landroid/view/MotionEvent;I)I

    move-result v0

    if-ne v0, v3, :cond_5

    goto/16 :goto_2

    :cond_5
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    goto/16 :goto_2

    :cond_6
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v3

    iput v3, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    goto/16 :goto_2

    :cond_7
    iget-boolean p1, p0, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    if-eqz p1, :cond_12

    iget p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0, p1, v1, v1}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZ)V

    iput v3, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->endDrag()V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->onRelease()V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->onRelease()V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result p1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v0

    :goto_0
    or-int v2, p1, v0

    goto/16 :goto_2

    :cond_8
    iget-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    if-nez v0, :cond_d

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/widget/ViewPager;->findPointerIndex(Landroid/view/MotionEvent;I)I

    move-result v0

    if-ne v0, v3, :cond_9

    goto/16 :goto_2

    :cond_9
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result v4

    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    sub-float v5, v4, v5

    invoke-static {v5}, Ljava/lang/Math;->abs(F)F

    move-result v5

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result v0

    iget v6, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionY:F

    sub-float v6, v0, v6

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v6

    sget-boolean v7, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    const-string v8, "ViewPager"

    if-eqz v7, :cond_a

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "Moved x to "

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v9, ","

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v0, " diff="

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v8, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_a
    invoke-direct {p0, v5, v6}, Lcom/miui/gallery/widget/ViewPager;->needIntercept(FF)Z

    move-result v0

    if-eqz v0, :cond_d

    sget-boolean v0, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz v0, :cond_b

    const-string v0, "Starting drag!"

    invoke-static {v8, v0}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    :cond_b
    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    sub-float/2addr v4, v0

    const/4 v5, 0x0

    cmpl-float v4, v4, v5

    if-lez v4, :cond_c

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mTouchSlop:I

    int-to-float v4, v4

    add-float/2addr v0, v4

    goto :goto_1

    :cond_c
    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mTouchSlop:I

    int-to-float v4, v4

    sub-float/2addr v0, v4

    :goto_1
    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-direct {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setScrollState(I)V

    invoke-direct {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setScrollingCacheEnabled(Z)V

    :cond_d
    iget-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    if-eqz v0, :cond_12

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    invoke-direct {p0, p1, v0}, Lcom/miui/gallery/widget/ViewPager;->findPointerIndex(Landroid/view/MotionEvent;I)I

    move-result v0

    if-ne v0, v3, :cond_e

    goto/16 :goto_2

    :cond_e
    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result p1

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->performDrag(F)Z

    move-result v2

    goto/16 :goto_2

    :cond_f
    iget-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    if-eqz v0, :cond_12

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mVelocityTracker:Landroid/view/VelocityTracker;

    const/16 v4, 0x3e8

    iget v5, p0, Lcom/miui/gallery/widget/ViewPager;->mMaximumVelocity:I

    int-to-float v5, v5

    invoke-virtual {v0, v4, v5}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    invoke-virtual {v0, v4}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v0

    float-to-int v0, v0

    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v4

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v5

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->infoForCurrentScrollPosition()Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v6

    iget v7, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    int-to-float v5, v5

    int-to-float v4, v4

    div-float/2addr v5, v4

    iget v4, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    sub-float/2addr v5, v4

    iget v4, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float/2addr v5, v4

    iget v4, v6, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    div-float/2addr v5, v4

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    invoke-direct {p0, p1, v4}, Lcom/miui/gallery/widget/ViewPager;->findPointerIndex(Landroid/view/MotionEvent;I)I

    move-result v4

    if-ne v4, v3, :cond_10

    goto :goto_2

    :cond_10
    invoke-virtual {p1, v4}, Landroid/view/MotionEvent;->getX(I)F

    move-result p1

    iget v2, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    sub-float/2addr p1, v2

    float-to-int p1, p1

    invoke-direct {p0, v7, v5, v0, p1}, Lcom/miui/gallery/widget/ViewPager;->determineTargetPage(IFII)I

    move-result p1

    invoke-virtual {p0, p1, v1, v1, v0}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZI)V

    iput v3, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->endDrag()V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->onRelease()V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->onRelease()V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {p1}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result p1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mRightEdge:Landroid/widget/EdgeEffect;

    invoke-virtual {v0}, Landroid/widget/EdgeEffect;->isFinished()Z

    move-result v0

    goto/16 :goto_0

    :cond_11
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->abortAnimation()V

    iput-boolean v2, p0, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mIsBeingDragged:Z

    invoke-direct {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setScrollState(I)V

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialMotionX:F

    iput v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLastMotionX:F

    invoke-virtual {p1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mActivePointerId:I

    :cond_12
    :goto_2
    if-eqz v2, :cond_13

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->postInvalidateOnAnimation()V

    :cond_13
    return v1

    :cond_14
    :goto_3
    return v2
.end method

.method pageLeft()Z
    .locals 2

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-lez v0, :cond_0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItem(IZ)V

    return v1

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method pageRight()Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v0, :cond_0

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    const/4 v2, 0x1

    sub-int/2addr v0, v2

    if-ge v1, v0, :cond_0

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    add-int/2addr v0, v2

    invoke-virtual {p0, v0, v2}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItem(IZ)V

    return v2

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public performAccessibilityAction(ILandroid/os/Bundle;)Z
    .locals 2

    invoke-super {p0, p1, p2}, Landroid/view/ViewGroup;->performAccessibilityAction(ILandroid/os/Bundle;)Z

    move-result p2

    const/4 v0, 0x1

    if-eqz p2, :cond_0

    return v0

    :cond_0
    const/16 p2, 0x1000

    const/4 v1, 0x0

    if-eq p1, p2, :cond_3

    const/16 p2, 0x2000

    if-eq p1, p2, :cond_1

    return v1

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz p1, :cond_2

    iget p2, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-lez p2, :cond_2

    invoke-virtual {p1}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result p1

    if-ge p2, p1, :cond_2

    iget p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    sub-int/2addr p1, v0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItem(I)V

    return v0

    :cond_2
    return v1

    :cond_3
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz p1, :cond_4

    iget p2, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ltz p2, :cond_4

    invoke-virtual {p1}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result p1

    sub-int/2addr p1, v0

    if-ge p2, p1, :cond_4

    iget p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    add-int/2addr p1, v0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItem(I)V

    return v0

    :cond_4
    return v1
.end method

.method populate()V
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->populate(I)V

    return-void
.end method

.method populate(I)V
    .locals 11

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    const/4 v1, 0x0

    if-eq v0, p1, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->calculatePageLimits(I)V

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->infoForPosition(I)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v0

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    goto :goto_0

    :cond_0
    move-object v0, v1

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-nez p1, :cond_1

    return-void

    :cond_1
    iget-boolean p1, p0, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    const-string v2, "ViewPager"

    if-eqz p1, :cond_3

    sget-boolean p1, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz p1, :cond_2

    const-string p1, "populate is pending, skipping for now..."

    invoke-static {v2, p1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    :cond_2
    return-void

    :cond_3
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWindowToken()Landroid/os/IBinder;

    move-result-object p1

    if-nez p1, :cond_4

    return-void

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {p1, p0}, Lcom/miui/gallery/widget/PagerAdapter;->startUpdate(Landroid/view/ViewGroup;)V

    iget p1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getLeftOffscreenPageLimit()I

    move-result v3

    sub-int/2addr p1, v3

    const/4 v3, 0x0

    invoke-static {v3, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v4}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v4

    add-int/lit8 v5, v4, -0x1

    iget v6, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getRightOffscreenPageLimit()I

    move-result v7

    add-int/2addr v6, v7

    invoke-static {v5, v6}, Ljava/lang/Math;->min(II)I

    move-result v5

    if-lez v4, :cond_5

    iget-object v6, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_5

    const/4 v6, 0x1

    goto :goto_1

    :cond_5
    move v6, v3

    :goto_1
    move v7, v3

    :goto_2
    iget-object v8, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v8}, Ljava/util/ArrayList;->size()I

    move-result v8

    if-ge v7, v8, :cond_7

    iget-object v8, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v8, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v9, v8, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v10, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-lt v9, v10, :cond_6

    iget v9, v8, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v10, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne v9, v10, :cond_7

    goto :goto_3

    :cond_6
    add-int/lit8 v7, v7, 0x1

    goto :goto_2

    :cond_7
    move-object v8, v1

    :goto_3
    if-nez v8, :cond_8

    if-lez v4, :cond_8

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0, v4, v7}, Lcom/miui/gallery/widget/ViewPager;->addNewItem(II)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v8

    :cond_8
    if-eqz v8, :cond_a

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    iget v9, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    if-gt v4, v9, :cond_9

    invoke-direct {p0, v8, v7, p1}, Lcom/miui/gallery/widget/ViewPager;->populateLeft(Lcom/miui/gallery/widget/ViewPager$ItemInfo;II)I

    move-result p1

    invoke-direct {p0, v8, p1, v5}, Lcom/miui/gallery/widget/ViewPager;->populateRight(Lcom/miui/gallery/widget/ViewPager$ItemInfo;II)I

    move-result p1

    goto :goto_4

    :cond_9
    invoke-direct {p0, v8, v7, v5}, Lcom/miui/gallery/widget/ViewPager;->populateRight(Lcom/miui/gallery/widget/ViewPager$ItemInfo;II)I

    move-result v4

    invoke-direct {p0, v8, v4, p1}, Lcom/miui/gallery/widget/ViewPager;->populateLeft(Lcom/miui/gallery/widget/ViewPager$ItemInfo;II)I

    move-result p1

    :goto_4
    invoke-direct {p0, v8, p1, v0}, Lcom/miui/gallery/widget/ViewPager;->calculatePageOffsets(Lcom/miui/gallery/widget/ViewPager$ItemInfo;ILcom/miui/gallery/widget/ViewPager$ItemInfo;)V

    :cond_a
    sget-boolean p1, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz p1, :cond_b

    const-string p1, "Current page list:"

    invoke-static {v2, p1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    move p1, v3

    :goto_5
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p1, v0, :cond_b

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "#"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v4, ": page "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v4, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget v4, v4, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    add-int/lit8 p1, p1, 0x1

    goto :goto_5

    :cond_b
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-eqz v8, :cond_c

    iget-object v2, v8, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    goto :goto_6

    :cond_c
    move-object v2, v1

    :goto_6
    invoke-virtual {p1, p0, v0, v2}, Lcom/miui/gallery/widget/PagerAdapter;->setPrimaryItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {p1, p0}, Lcom/miui/gallery/widget/PagerAdapter;->finishUpdate(Landroid/view/ViewGroup;)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result p1

    move v0, v3

    :goto_7
    if-ge v0, p1, :cond_e

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    invoke-virtual {v2}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/widget/ViewPager$LayoutParams;

    iget-boolean v5, v4, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->isDecor:Z

    if-nez v5, :cond_d

    invoke-virtual {p0, v2}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v2

    if-eqz v2, :cond_d

    iget v2, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->widthFactor:F

    iput v2, v4, Lcom/miui/gallery/widget/ViewPager$LayoutParams;->widthFactor:F

    :cond_d
    add-int/lit8 v0, v0, 0x1

    goto :goto_7

    :cond_e
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->hasFocus()Z

    move-result p1

    if-eqz p1, :cond_12

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->findFocus()Landroid/view/View;

    move-result-object p1

    if-eqz p1, :cond_f

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->infoForAnyChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v1

    :cond_f
    if-eqz v1, :cond_10

    iget p1, v1, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-eq p1, v0, :cond_12

    :cond_10
    :goto_8
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result p1

    if-ge v3, p1, :cond_12

    invoke-virtual {p0, v3}, Lcom/miui/gallery/widget/ViewPager;->getChildAt(I)Landroid/view/View;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->infoForChild(Landroid/view/View;)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v0

    if-eqz v0, :cond_11

    iget v0, v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne v0, v1, :cond_11

    const/4 v0, 0x2

    invoke-virtual {p1, v0}, Landroid/view/View;->requestFocus(I)Z

    move-result p1

    if-eqz p1, :cond_11

    goto :goto_9

    :cond_11
    add-int/lit8 v3, v3, 0x1

    goto :goto_8

    :cond_12
    :goto_9
    if-eqz v6, :cond_14

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mOnPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz p1, :cond_13

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-interface {p1, v0}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    :cond_13
    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mPageSettledListener:Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;

    if-eqz p1, :cond_14

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-interface {p1, v0}, Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;->onPageSettled(I)V

    :cond_14
    return-void
.end method

.method public setAdapter(Lcom/miui/gallery/widget/PagerAdapter;)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mObserver:Lcom/miui/gallery/widget/ViewPager$PagerObserver;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/widget/PagerAdapter;->unregisterDataSetObserver(Landroid/database/DataSetObserver;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/widget/PagerAdapter;->startUpdate(Landroid/view/ViewGroup;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iget-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget v4, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->position:I

    iget-object v2, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->object:Ljava/lang/Object;

    invoke-virtual {v3, p0, v4, v2}, Lcom/miui/gallery/widget/PagerAdapter;->destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/widget/PagerAdapter;->finishUpdate(Landroid/view/ViewGroup;)V

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->removeNonDecorViews()V

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0, v1, v1}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iput-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    if-eqz v2, :cond_4

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mObserver:Lcom/miui/gallery/widget/ViewPager$PagerObserver;

    const/4 v3, 0x0

    if-nez v2, :cond_2

    new-instance v2, Lcom/miui/gallery/widget/ViewPager$PagerObserver;

    invoke-direct {v2, p0, v3}, Lcom/miui/gallery/widget/ViewPager$PagerObserver;-><init>(Lcom/miui/gallery/widget/ViewPager;Lcom/miui/gallery/widget/ViewPager$1;)V

    iput-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mObserver:Lcom/miui/gallery/widget/ViewPager$PagerObserver;

    :cond_2
    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mObserver:Lcom/miui/gallery/widget/ViewPager$PagerObserver;

    invoke-virtual {v2, v4}, Lcom/miui/gallery/widget/PagerAdapter;->registerDataSetObserver(Landroid/database/DataSetObserver;)V

    iput-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    const/4 v2, 0x1

    iput-boolean v2, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstLayout:Z

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredCurItem:I

    if-ltz v4, :cond_3

    iget-object v4, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget-object v5, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredAdapterState:Landroid/os/Parcelable;

    invoke-virtual {v4, v5, v3}, Lcom/miui/gallery/widget/PagerAdapter;->restoreState(Landroid/os/Parcelable;Ljava/lang/ClassLoader;)V

    iget v4, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredCurItem:I

    invoke-virtual {p0, v4, v1, v2}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZ)V

    const/4 v1, -0x1

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredCurItem:I

    iput-object v3, p0, Lcom/miui/gallery/widget/ViewPager;->mRestoredAdapterState:Landroid/os/Parcelable;

    goto :goto_1

    :cond_3
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    :cond_4
    :goto_1
    iget-object v1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapterChangeListener:Lcom/miui/gallery/widget/ViewPager$OnAdapterChangeListener;

    if-eqz v1, :cond_5

    if-eq v0, p1, :cond_5

    invoke-interface {v1, v0, p1}, Lcom/miui/gallery/widget/ViewPager$OnAdapterChangeListener;->onAdapterChanged(Lcom/miui/gallery/widget/PagerAdapter;Lcom/miui/gallery/widget/PagerAdapter;)V

    :cond_5
    return-void
.end method

.method public setBottomMarginProgress(F)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mBottomMarginProgress:F

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/widget/ViewPager;->mForceReplayout:Z

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->requestLayout()V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->invalidate()V

    return-void
.end method

.method public setCurrentItem(I)V
    .locals 2

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    iget-boolean v1, p0, Lcom/miui/gallery/widget/ViewPager;->mFirstLayout:Z

    xor-int/lit8 v1, v1, 0x1

    invoke-virtual {p0, p1, v1, v0}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZ)V

    return-void
.end method

.method public setCurrentItem(IZ)V
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/widget/ViewPager;->mPopulatePending:Z

    invoke-virtual {p0, p1, p2, v0}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZ)V

    return-void
.end method

.method setCurrentItemInternal(IZZ)V
    .locals 1

    const/4 v0, 0x0

    invoke-virtual {p0, p1, p2, p3, v0}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItemInternal(IZZI)V

    return-void
.end method

.method setCurrentItemInternal(IZZI)V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    const/4 v1, 0x0

    if-eqz v0, :cond_d

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    if-gtz v0, :cond_0

    goto/16 :goto_5

    :cond_0
    if-nez p3, :cond_1

    iget p3, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-ne p3, p1, :cond_1

    iget-object p3, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result p3

    if-eqz p3, :cond_1

    invoke-direct {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setScrollingCacheEnabled(Z)V

    return-void

    :cond_1
    const/4 p3, 0x1

    if-gez p1, :cond_2

    move p1, v1

    goto :goto_0

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result v0

    if-lt p1, v0, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/PagerAdapter;->getCount()I

    move-result p1

    sub-int/2addr p1, p3

    :cond_3
    :goto_0
    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getRightOffscreenPageLimit()I

    move-result v2

    add-int/2addr v0, v2

    if-gt p1, v0, :cond_4

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getLeftOffscreenPageLimit()I

    move-result v2

    sub-int/2addr v0, v2

    if-ge p1, v0, :cond_5

    :cond_4
    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_5

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    iput-boolean p3, v2, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->scrolling:Z

    goto :goto_1

    :cond_5
    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    if-eq v0, p1, :cond_6

    goto :goto_2

    :cond_6
    move p3, v1

    :goto_2
    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->populate(I)V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->infoForPosition(I)Lcom/miui/gallery/widget/ViewPager$ItemInfo;

    move-result-object v0

    if-eqz v0, :cond_7

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v2

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->getMinScrollOffset()F

    move-result v3

    iget v4, v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offset:F

    iget v0, v0, Lcom/miui/gallery/widget/ViewPager$ItemInfo;->offsetLeftScreen:F

    sub-float/2addr v4, v0

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->getMaxScrollOffset()F

    move-result v0

    invoke-static {v4, v0}, Ljava/lang/Math;->min(FF)F

    move-result v0

    invoke-static {v3, v0}, Ljava/lang/Math;->max(FF)F

    move-result v0

    int-to-float v2, v2

    mul-float/2addr v2, v0

    float-to-int v0, v2

    goto :goto_3

    :cond_7
    move v0, v1

    :goto_3
    if-eqz p2, :cond_9

    invoke-virtual {p0, v0, v1, p4}, Lcom/miui/gallery/widget/ViewPager;->smoothScrollTo(III)V

    if-eqz p3, :cond_8

    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mOnPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz p2, :cond_8

    invoke-interface {p2, p1}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    :cond_8
    if-eqz p3, :cond_c

    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mInternalPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz p2, :cond_c

    invoke-interface {p2, p1}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    goto :goto_4

    :cond_9
    if-eqz p3, :cond_a

    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mOnPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz p2, :cond_a

    invoke-interface {p2, p1}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    :cond_a
    if-eqz p3, :cond_b

    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mInternalPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    if-eqz p2, :cond_b

    invoke-interface {p2, p1}, Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;->onPageSelected(I)V

    :cond_b
    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->completeScroll()V

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/widget/ViewPager;->scrollTo(II)V

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->callBackPageSettled()V

    :cond_c
    :goto_4
    return-void

    :cond_d
    :goto_5
    invoke-direct {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setScrollingCacheEnabled(Z)V

    return-void
.end method

.method public setDraggable(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/widget/ViewPager;->mDragEnabled:Z

    return-void
.end method

.method public setHeightSlipRatio(F)V
    .locals 2

    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/MiscUtil;->clamp(FFF)F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mHeightSlipRatio:F

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->isLaidOut()Z

    move-result p1

    if-eqz p1, :cond_1

    sget-boolean p1, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz p1, :cond_0

    const-string p1, "ViewPager"

    const-string v0, "update height slip ratio, request layout"

    invoke-static {p1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->requestLayout()V

    :cond_1
    return-void
.end method

.method public setLeftOffscreenPageLimit(I)V
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    if-eq p1, v0, :cond_0

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mLeftOffscreenPageLimit:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    :cond_0
    return-void
.end method

.method public setMarginSlipRatio(F)V
    .locals 2

    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/MiscUtil;->clamp(FFF)F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mMarginSlipRatio:F

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->isLaidOut()Z

    move-result p1

    if-eqz p1, :cond_1

    sget-boolean p1, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz p1, :cond_0

    const-string p1, "ViewPager"

    const-string v0, "update margin slip ratio, request layout"

    invoke-static {p1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->requestLayout()V

    :cond_1
    return-void
.end method

.method setOnAdapterChangeListener(Lcom/miui/gallery/widget/ViewPager$OnAdapterChangeListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapterChangeListener:Lcom/miui/gallery/widget/ViewPager$OnAdapterChangeListener;

    return-void
.end method

.method public setOnPageChangeListener(Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mOnPageChangeListener:Lcom/miui/gallery/widget/ViewPager$OnPageChangeListener;

    return-void
.end method

.method public setOnPageSettledListener(Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mPageSettledListener:Lcom/miui/gallery/widget/ViewPager$OnPageSettledListener;

    return-void
.end method

.method public setPageMargin(I)V
    .locals 2

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    iget v1, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    iput v1, p0, Lcom/miui/gallery/widget/ViewPager;->mInitialPageMargin:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v1

    invoke-direct {p0, v1, v1, p1, v0}, Lcom/miui/gallery/widget/ViewPager;->recomputeScrollPosition(IIII)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->requestLayout()V

    return-void
.end method

.method public setPageMarginDrawable(I)V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->setPageMarginDrawable(Landroid/graphics/drawable/Drawable;)V

    return-void
.end method

.method public setPageMarginDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    const/4 v0, 0x1

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/widget/ViewPager;->setPageMarginDrawable(Landroid/graphics/drawable/Drawable;Z)V

    return-void
.end method

.method public setPageMarginDrawable(Landroid/graphics/drawable/Drawable;Z)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/widget/ViewPager;->mMarginDrawable:Landroid/graphics/drawable/Drawable;

    if-eqz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->refreshDrawableState()V

    :cond_0
    if-nez p1, :cond_1

    const/4 p1, 0x1

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    :goto_0
    invoke-virtual {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->setWillNotDraw(Z)V

    if-eqz p2, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->invalidate()V

    :cond_2
    return-void
.end method

.method public setRightOffscreenPageLimit(I)V
    .locals 1

    iget v0, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    if-eq p1, v0, :cond_0

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mRightOffscreenPageLimit:I

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    :cond_0
    return-void
.end method

.method public setWidthSlipRatio(F)V
    .locals 2

    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/MiscUtil;->clamp(FFF)F

    move-result p1

    iput p1, p0, Lcom/miui/gallery/widget/ViewPager;->mWidthSlipRatio:F

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->isLaidOut()Z

    move-result p1

    if-eqz p1, :cond_1

    sget-boolean p1, Lcom/miui/gallery/widget/ViewPager;->DEBUG:Z

    if-eqz p1, :cond_0

    const-string p1, "ViewPager"

    const-string v0, "update width slip ratio, request layout"

    invoke-static {p1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->requestLayout()V

    :cond_1
    return-void
.end method

.method smoothScrollTo(III)V
    .locals 8

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getChildCount()I

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    invoke-direct {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setScrollingCacheEnabled(Z)V

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollX()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getScrollY()I

    move-result v4

    sub-int v5, p1, v3

    sub-int v6, p2, v4

    if-nez v5, :cond_1

    if-nez v6, :cond_1

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->completeScroll()V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->populate()V

    invoke-direct {p0, v1}, Lcom/miui/gallery/widget/ViewPager;->setScrollState(I)V

    invoke-direct {p0}, Lcom/miui/gallery/widget/ViewPager;->callBackPageSettled()V

    return-void

    :cond_1
    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->setScrollingCacheEnabled(Z)V

    const/4 p1, 0x2

    invoke-direct {p0, p1}, Lcom/miui/gallery/widget/ViewPager;->setScrollState(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result p1

    div-int/lit8 p2, p1, 0x2

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v0

    int-to-float v0, v0

    const/high16 v1, 0x3f800000    # 1.0f

    mul-float/2addr v0, v1

    int-to-float p1, p1

    div-float/2addr v0, p1

    invoke-static {v1, v0}, Ljava/lang/Math;->min(FF)F

    move-result v0

    int-to-float p2, p2

    invoke-virtual {p0, v0}, Lcom/miui/gallery/widget/ViewPager;->distanceInfluenceForSnapDuration(F)F

    move-result v0

    mul-float/2addr v0, p2

    add-float/2addr p2, v0

    invoke-static {p3}, Ljava/lang/Math;->abs(I)I

    move-result p3

    if-lez p3, :cond_2

    const/high16 p1, 0x447a0000    # 1000.0f

    int-to-float p3, p3

    div-float/2addr p2, p3

    invoke-static {p2}, Ljava/lang/Math;->abs(F)F

    move-result p2

    mul-float/2addr p2, p1

    invoke-static {p2}, Ljava/lang/Math;->round(F)I

    move-result p1

    mul-int/lit8 p1, p1, 0x4

    goto :goto_0

    :cond_2
    iget-object p2, p0, Lcom/miui/gallery/widget/ViewPager;->mAdapter:Lcom/miui/gallery/widget/PagerAdapter;

    iget p3, p0, Lcom/miui/gallery/widget/ViewPager;->mCurItem:I

    invoke-virtual {p2, p3}, Lcom/miui/gallery/widget/PagerAdapter;->getPageWidth(I)F

    move-result p2

    mul-float/2addr p1, p2

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result p2

    int-to-float p2, p2

    iget p3, p0, Lcom/miui/gallery/widget/ViewPager;->mPageMargin:I

    int-to-float p3, p3

    add-float/2addr p1, p3

    div-float/2addr p2, p1

    add-float/2addr p2, v1

    const/high16 p1, 0x42c80000    # 100.0f

    mul-float/2addr p2, p1

    float-to-int p1, p2

    :goto_0
    const/16 p2, 0x258

    invoke-static {p1, p2}, Ljava/lang/Math;->min(II)I

    move-result v7

    iget-object v2, p0, Lcom/miui/gallery/widget/ViewPager;->mScroller:Landroid/widget/Scroller;

    invoke-virtual/range {v2 .. v7}, Landroid/widget/Scroller;->startScroll(IIIII)V

    invoke-virtual {p0}, Lcom/miui/gallery/widget/ViewPager;->postInvalidateOnAnimation()V

    return-void
.end method

.method protected verifyDrawable(Landroid/graphics/drawable/Drawable;)Z
    .locals 1

    invoke-super {p0, p1}, Landroid/view/ViewGroup;->verifyDrawable(Landroid/graphics/drawable/Drawable;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/widget/ViewPager;->mMarginDrawable:Landroid/graphics/drawable/Drawable;

    if-ne p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    return p1
.end method
