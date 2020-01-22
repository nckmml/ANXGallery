.class public Lcom/miui/widget/picker/NumberPicker;
.super Landroid/widget/LinearLayout;
.source "NumberPicker.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;,
        Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;,
        Lcom/miui/widget/picker/NumberPicker$CustomEditText;,
        Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;,
        Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;,
        Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;,
        Lcom/miui/widget/picker/NumberPicker$InputTextFilter;,
        Lcom/miui/widget/picker/NumberPicker$Formatter;,
        Lcom/miui/widget/picker/NumberPicker$OnScrollListener;,
        Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;,
        Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;,
        Lcom/miui/widget/picker/NumberPicker$NumberFormatter;
    }
.end annotation


# static fields
.field private static final DEFAULT_LAYOUT_RESOURCE_ID:I

.field private static final DIGIT_CHARACTERS:[C

.field private static final PRESSED_STATE_SET:[I

.field static final TWO_DIGIT_FORMATTER:Lcom/miui/widget/picker/NumberPicker$Formatter;

.field private static final sIdGenerator:Ljava/util/concurrent/atomic/AtomicInteger;


# instance fields
.field private MARGIN_LABEL_LEFT:I

.field private MARGIN_LABEL_TOP:I

.field private isShowLabel:Z

.field private mAccessibilityNodeProvider:Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;

.field private final mAdjustScroller:Landroid/widget/Scroller;

.field private mBeginSoftInputOnLongPressCommand:Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;

.field private mBottomSelectionDividerBottom:I

.field private mChangeCurrentByOneFromLongPressCommand:Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

.field private final mComputeMaxWidth:Z

.field private mCurrentScrollOffset:I

.field private mDecrementVirtualButtonPressed:Z

.field private final mDefaultTypeface:Landroid/graphics/Typeface;

.field private mDisplayedMaxTextWidth:F

.field private mDisplayedValues:[Ljava/lang/String;

.field private final mFlingScroller:Landroid/widget/Scroller;

.field private mFormatter:Lcom/miui/widget/picker/NumberPicker$Formatter;

.field private final mHasSelectorWheel:Z

.field private final mId:I

.field private mIncrementVirtualButtonPressed:Z

.field private mIngonreMoveEvents:Z

.field private mInitialScrollOffset:I

.field private final mInputText:Landroid/widget/EditText;

.field private mLabel:Ljava/lang/CharSequence;

.field private mLabelPaint:Landroid/graphics/Paint;

.field private mLabelTextColor:I

.field private mLabelTextSize:I

.field private mLastDownEventTime:J

.field private mLastDownEventY:F

.field private mLastDownOrMoveEventY:F

.field private mLastHandledDownDpadKeyCode:I

.field private mLastHoveredChildVirtualViewId:I

.field private mLongPressUpdateInterval:J

.field private mMaxFlingSpeedFactor:F

.field private final mMaxHeight:I

.field private mMaxValue:I

.field private mMaxWidth:I

.field private mMaximumFlingVelocity:I

.field private final mMinHeight:I

.field private mMinValue:I

.field private final mMinWidth:I

.field private mMinimumFlingVelocity:I

.field private final mNormalWheelPaint:Landroid/graphics/Paint;

.field private mOnScrollListener:Lcom/miui/widget/picker/NumberPicker$OnScrollListener;

.field private mOnValueChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

.field private final mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

.field private mPreviousScrollerY:I

.field private mScrollState:I

.field private final mSelectionDividerHeight:I

.field private final mSelectionDividersDistance:I

.field private mSelectorElementHeight:I

.field private final mSelectorIndexToStringCache:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final mSelectorIndices:[I

.field private mSelectorTextGapHeight:I

.field private final mSelectorWheelPaint:Landroid/graphics/Paint;

.field private mSetSelectionCommand:Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

.field private mShowSoftInputOnTap:Z

.field private mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

.field private mTextColorHilight:I

.field private mTextColorHint:I

.field private mTextMaxAscent:F

.field private mTextPadding:I

.field private final mTextSize:I

.field private mTextSizeHilight:I

.field private mTextSizeHint:I

.field private mTopSelectionDividerTop:I

.field private mTouchSlop:I

.field private mUpdateText:Ljava/lang/String;

.field private mValue:I

.field private mVelocityTracker:Landroid/view/VelocityTracker;

.field private mWrapSelectorWheel:Z


# direct methods
.method static constructor <clinit>()V
    .locals 3

    sget v0, Lcom/miui/widget/R$layout;->numberpicker_layout:I

    sput v0, Lcom/miui/widget/picker/NumberPicker;->DEFAULT_LAYOUT_RESOURCE_ID:I

    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    sput-object v0, Lcom/miui/widget/picker/NumberPicker;->sIdGenerator:Ljava/util/concurrent/atomic/AtomicInteger;

    new-instance v0, Lcom/miui/widget/picker/NumberPicker$NumberFormatter;

    const/4 v2, 0x2

    invoke-direct {v0, v2}, Lcom/miui/widget/picker/NumberPicker$NumberFormatter;-><init>(I)V

    sput-object v0, Lcom/miui/widget/picker/NumberPicker;->TWO_DIGIT_FORMATTER:Lcom/miui/widget/picker/NumberPicker$Formatter;

    const/4 v0, 0x1

    new-array v0, v0, [I

    const v2, 0x10100a7

    aput v2, v0, v1

    sput-object v0, Lcom/miui/widget/picker/NumberPicker;->PRESSED_STATE_SET:[I

    const/16 v0, 0xa

    new-array v0, v0, [C

    fill-array-data v0, :array_0

    sput-object v0, Lcom/miui/widget/picker/NumberPicker;->DIGIT_CHARACTERS:[C

    return-void

    :array_0
    .array-data 2
        0x30s
        0x31s
        0x32s
        0x33s
        0x34s
        0x35s
        0x36s
        0x37s
        0x38s
        0x39s
    .end array-data
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/widget/picker/NumberPicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    sget v0, Lcom/miui/widget/R$attr;->numberPickerStyle:I

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/widget/picker/NumberPicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 6

    invoke-direct {p0, p1, p2, p3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    sget-object v0, Lcom/miui/widget/picker/NumberPicker;->sIdGenerator:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    move-result v0

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mId:I

    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_LEFT:I

    const/4 v1, 0x0

    iput v1, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_TOP:I

    const/16 v2, 0x190

    iput v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    const-wide/16 v2, 0x12c

    iput-wide v2, p0, Lcom/miui/widget/picker/NumberPicker;->mLongPressUpdateInterval:J

    new-instance v2, Landroid/util/SparseArray;

    invoke-direct {v2}, Landroid/util/SparseArray;-><init>()V

    iput-object v2, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndexToStringCache:Landroid/util/SparseArray;

    const/4 v2, 0x5

    new-array v2, v2, [I

    iput-object v2, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    const/high16 v2, -0x80000000

    iput v2, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iput v1, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    const/4 v2, -0x1

    iput v2, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHandledDownDpadKeyCode:I

    const/16 v3, 0x1e

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextPadding:I

    const/16 v3, 0x19

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    const/16 v3, 0xe

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    const/16 v3, 0xa

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    const v3, -0x49ffd

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHilight:I

    const/high16 v4, 0x7f000000

    iput v4, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHint:I

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextColor:I

    const/high16 v3, 0x3f800000    # 1.0f

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxFlingSpeedFactor:F

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v4

    iget v4, v4, Landroid/util/DisplayMetrics;->density:F

    cmpl-float v3, v4, v3

    if-eqz v3, :cond_0

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    int-to-float v3, v3

    mul-float/2addr v3, v4

    float-to-int v3, v3

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    int-to-float v3, v3

    mul-float/2addr v3, v4

    float-to-int v3, v3

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    int-to-float v3, v3

    mul-float/2addr v3, v4

    float-to-int v3, v3

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_LEFT:I

    int-to-float v3, v3

    mul-float/2addr v3, v4

    float-to-int v3, v3

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_LEFT:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_TOP:I

    int-to-float v3, v3

    mul-float/2addr v3, v4

    float-to-int v3, v3

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_TOP:I

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v3

    sget-object v5, Lcom/miui/widget/R$styleable;->NumberPicker:[I

    invoke-virtual {v3, p2, v5, p3, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object p2

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_android_text:I

    invoke-virtual {p2, p3}, Landroid/content/res/TypedArray;->getText(I)Ljava/lang/CharSequence;

    move-result-object p3

    iput-object p3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_textSizeHighlight:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    invoke-virtual {p2, p3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_textSizeHint:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    invoke-virtual {p2, p3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_android_labelTextSize:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    invoke-virtual {p2, p3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_android_textColorHighlight:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHilight:I

    invoke-virtual {p2, p3, v3}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHilight:I

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_android_textColorHint:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHint:I

    invoke-virtual {p2, p3, v3}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHint:I

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_labelTextColor:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextColor:I

    invoke-virtual {p2, p3, v3}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextColor:I

    sget p3, Lcom/miui/widget/R$styleable;->NumberPicker_labelPadding:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextPadding:I

    invoke-virtual {p2, p3, v3}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextPadding:I

    invoke-virtual {p2}, Landroid/content/res/TypedArray;->recycle()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initSoundPlayer()V

    sget p2, Lcom/miui/widget/picker/NumberPicker;->DEFAULT_LAYOUT_RESOURCE_ID:I

    iput-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    const/high16 p3, 0x40000000    # 2.0f

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v3

    invoke-static {v0, p3, v3}, Landroid/util/TypedValue;->applyDimension(IFLandroid/util/DisplayMetrics;)F

    move-result p3

    float-to-int p3, p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectionDividerHeight:I

    const/high16 p3, 0x42340000    # 45.0f

    mul-float/2addr p3, v4

    float-to-int p3, p3

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectionDividersDistance:I

    iput v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMinHeight:I

    const/high16 p3, 0x434a0000    # 202.0f

    mul-float/2addr v4, p3

    float-to-int p3, v4

    iput p3, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxHeight:I

    iget p3, p0, Lcom/miui/widget/picker/NumberPicker;->mMinHeight:I

    if-eq p3, v2, :cond_2

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxHeight:I

    if-eq v3, v2, :cond_2

    if-gt p3, v3, :cond_1

    goto :goto_0

    :cond_1
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "minHeight > maxHeight"

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_2
    :goto_0
    iput v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMinWidth:I

    iput v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    iget p3, p0, Lcom/miui/widget/picker/NumberPicker;->mMinWidth:I

    if-eq p3, v2, :cond_4

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    if-eq v3, v2, :cond_4

    if-gt p3, v3, :cond_3

    goto :goto_1

    :cond_3
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "minWidth > maxWidth"

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_4
    :goto_1
    iget p3, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    if-ne p3, v2, :cond_5

    move p3, v0

    goto :goto_2

    :cond_5
    move p3, v1

    :goto_2
    iput-boolean p3, p0, Lcom/miui/widget/picker/NumberPicker;->mComputeMaxWidth:Z

    new-instance p3, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    invoke-direct {p3, p0}, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;-><init>(Lcom/miui/widget/picker/NumberPicker;)V

    iput-object p3, p0, Lcom/miui/widget/picker/NumberPicker;->mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    iget-boolean p3, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    xor-int/2addr p3, v0

    invoke-virtual {p0, p3}, Lcom/miui/widget/picker/NumberPicker;->setWillNotDraw(Z)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object p3

    const-string v3, "layout_inflater"

    invoke-virtual {p3, v3}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Landroid/view/LayoutInflater;

    invoke-virtual {p3, p2, p0, v0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    sget p2, Lcom/miui/widget/R$id;->numberpicker_input:I

    invoke-virtual {p0, p2}, Lcom/miui/widget/picker/NumberPicker;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/EditText;

    iput-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    new-instance p3, Lcom/miui/widget/picker/NumberPicker$1;

    invoke-direct {p3, p0}, Lcom/miui/widget/picker/NumberPicker$1;-><init>(Lcom/miui/widget/picker/NumberPicker;)V

    invoke-virtual {p2, p3}, Landroid/widget/EditText;->setOnFocusChangeListener(Landroid/view/View$OnFocusChangeListener;)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    new-array p3, v0, [Landroid/text/InputFilter;

    new-instance v3, Lcom/miui/widget/picker/NumberPicker$InputTextFilter;

    invoke-direct {v3, p0}, Lcom/miui/widget/picker/NumberPicker$InputTextFilter;-><init>(Lcom/miui/widget/picker/NumberPicker;)V

    aput-object v3, p3, v1

    invoke-virtual {p2, p3}, Landroid/widget/EditText;->setFilters([Landroid/text/InputFilter;)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const/4 p3, 0x2

    invoke-virtual {p2, p3}, Landroid/widget/EditText;->setRawInputType(I)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const/4 p3, 0x6

    invoke-virtual {p2, p3}, Landroid/widget/EditText;->setImeOptions(I)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const/4 p3, 0x4

    invoke-virtual {p2, p3}, Landroid/widget/EditText;->setVisibility(I)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const p3, 0x800003

    invoke-virtual {p2, p3}, Landroid/widget/EditText;->setGravity(I)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const/4 p3, 0x0

    invoke-virtual {p2, p3}, Landroid/widget/EditText;->setScaleX(F)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {p2, v1}, Landroid/widget/EditText;->setSaveEnabled(Z)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    iget p3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextPadding:I

    invoke-virtual {p2}, Landroid/widget/EditText;->getPaddingTop()I

    move-result v1

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mTextPadding:I

    iget-object v4, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v4}, Landroid/widget/EditText;->getPaddingRight()I

    move-result v4

    invoke-virtual {p2, p3, v1, v3, v4}, Landroid/widget/EditText;->setPadding(IIII)V

    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result p2

    iput p2, p0, Lcom/miui/widget/picker/NumberPicker;->mTouchSlop:I

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledMinimumFlingVelocity()I

    move-result p2

    iput p2, p0, Lcom/miui/widget/picker/NumberPicker;->mMinimumFlingVelocity:I

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result p1

    div-int/lit8 p1, p1, 0x8

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaximumFlingVelocity:I

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->getTextSize()F

    move-result p1

    float-to-int p1, p1

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSize:I

    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    sget-object p2, Landroid/graphics/Paint$Align;->CENTER:Landroid/graphics/Paint$Align;

    invoke-virtual {p1, p2}, Landroid/graphics/Paint;->setTextAlign(Landroid/graphics/Paint$Align;)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    int-to-float p2, p2

    invoke-virtual {p1, p2}, Landroid/graphics/Paint;->setTextSize(F)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->getTypeface()Landroid/graphics/Typeface;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mDefaultTypeface:Landroid/graphics/Typeface;

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mDefaultTypeface:Landroid/graphics/Typeface;

    invoke-virtual {p1, p2}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->getTextColors()Landroid/content/res/ColorStateList;

    move-result-object p1

    sget-object p2, Lcom/miui/widget/picker/NumberPicker;->ENABLED_STATE_SET:[I

    invoke-virtual {p1, p2, v2}, Landroid/content/res/ColorStateList;->getColorForState([II)I

    move-result p1

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {p2, p1}, Landroid/graphics/Paint;->setColor(I)V

    new-instance p1, Landroid/graphics/Paint;

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    invoke-direct {p1, p2}, Landroid/graphics/Paint;-><init>(Landroid/graphics/Paint;)V

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1}, Landroid/graphics/Paint;->ascent()F

    move-result p1

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mTextMaxAscent:F

    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1}, Landroid/graphics/Paint;-><init>()V

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setFakeBoldText(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextColor:I

    invoke-virtual {p1, p2}, Landroid/graphics/Paint;->setColor(I)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    int-to-float p2, p2

    invoke-virtual {p1, p2}, Landroid/graphics/Paint;->setTextSize(F)V

    new-instance p1, Landroid/widget/Scroller;

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object p2

    const/4 p3, 0x0

    invoke-direct {p1, p2, p3, v0}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;Z)V

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    new-instance p1, Landroid/widget/Scroller;

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object p2

    new-instance p3, Landroid/view/animation/DecelerateInterpolator;

    const/high16 v1, 0x40200000    # 2.5f

    invoke-direct {p3, v1}, Landroid/view/animation/DecelerateInterpolator;-><init>(F)V

    invoke-direct {p1, p2, p3}, Landroid/widget/Scroller;-><init>(Landroid/content/Context;Landroid/view/animation/Interpolator;)V

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mAdjustScroller:Landroid/widget/Scroller;

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getImportantForAccessibility()I

    move-result p1

    if-nez p1, :cond_6

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->setImportantForAccessibility(I)V

    :cond_6
    return-void
.end method

.method static synthetic access$000(Lcom/miui/widget/picker/NumberPicker;)Landroid/widget/EditText;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/widget/picker/NumberPicker;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->validateInputTextView(Landroid/view/View;)V

    return-void
.end method

.method static synthetic access$1000(Lcom/miui/widget/picker/NumberPicker;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/widget/picker/NumberPicker;->mIncrementVirtualButtonPressed:Z

    return p0
.end method

.method static synthetic access$1002(Lcom/miui/widget/picker/NumberPicker;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/NumberPicker;->mIncrementVirtualButtonPressed:Z

    return p1
.end method

.method static synthetic access$1100(Lcom/miui/widget/picker/NumberPicker;)I
    .locals 0

    iget p0, p0, Lcom/miui/widget/picker/NumberPicker;->mBottomSelectionDividerBottom:I

    return p0
.end method

.method static synthetic access$1200(Lcom/miui/widget/picker/NumberPicker;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/widget/picker/NumberPicker;->mDecrementVirtualButtonPressed:Z

    return p0
.end method

.method static synthetic access$1202(Lcom/miui/widget/picker/NumberPicker;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/NumberPicker;->mDecrementVirtualButtonPressed:Z

    return p1
.end method

.method static synthetic access$1300(Lcom/miui/widget/picker/NumberPicker;)I
    .locals 0

    iget p0, p0, Lcom/miui/widget/picker/NumberPicker;->mTopSelectionDividerTop:I

    return p0
.end method

.method static synthetic access$1400(Lcom/miui/widget/picker/NumberPicker;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->changeValueByOne(Z)V

    return-void
.end method

.method static synthetic access$1500(Lcom/miui/widget/picker/NumberPicker;)J
    .locals 2

    iget-wide v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLongPressUpdateInterval:J

    return-wide v0
.end method

.method static synthetic access$1602(Lcom/miui/widget/picker/NumberPicker;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/NumberPicker;->mIngonreMoveEvents:Z

    return p1
.end method

.method static synthetic access$1700(Lcom/miui/widget/picker/NumberPicker;)I
    .locals 0

    iget p0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectionDividerHeight:I

    return p0
.end method

.method static synthetic access$1800(Lcom/miui/widget/picker/NumberPicker;)I
    .locals 0

    iget p0, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    return p0
.end method

.method static synthetic access$1900(Lcom/miui/widget/picker/NumberPicker;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    return p0
.end method

.method static synthetic access$2000(Lcom/miui/widget/picker/NumberPicker;I)I
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->getWrappedSelectorIndex(I)I

    move-result p0

    return p0
.end method

.method static synthetic access$2100(Lcom/miui/widget/picker/NumberPicker;)I
    .locals 0

    iget p0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    return p0
.end method

.method static synthetic access$2200(Lcom/miui/widget/picker/NumberPicker;I)Ljava/lang/String;
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->formatNumber(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$500()[C
    .locals 1

    sget-object v0, Lcom/miui/widget/picker/NumberPicker;->DIGIT_CHARACTERS:[C

    return-object v0
.end method

.method static synthetic access$600(Lcom/miui/widget/picker/NumberPicker;)[Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/widget/picker/NumberPicker;Ljava/lang/String;)I
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->getSelectedPos(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method static synthetic access$800(Lcom/miui/widget/picker/NumberPicker;)I
    .locals 0

    iget p0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    return p0
.end method

.method static synthetic access$900(Lcom/miui/widget/picker/NumberPicker;II)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/widget/picker/NumberPicker;->postSetSelectionCommand(II)V

    return-void
.end method

.method private changeValueByOne(Z)V
    .locals 13

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->moveToFinalScrollerPosition(Landroid/widget/Scroller;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mAdjustScroller:Landroid/widget/Scroller;

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->moveToFinalScrollerPosition(Landroid/widget/Scroller;)Z

    :cond_0
    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mPreviousScrollerY:I

    if-eqz p1, :cond_1

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    neg-int v5, p1

    const/16 v6, 0x12c

    invoke-virtual/range {v1 .. v6}, Landroid/widget/Scroller;->startScroll(IIIII)V

    goto :goto_0

    :cond_1
    iget-object v7, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    iget v11, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    const/16 v12, 0x12c

    invoke-virtual/range {v7 .. v12}, Landroid/widget/Scroller;->startScroll(IIIII)V

    :goto_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    goto :goto_1

    :cond_2
    const/4 v0, 0x1

    if-eqz p1, :cond_3

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    add-int/2addr p1, v0

    invoke-direct {p0, p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setValueInternal(IZ)V

    goto :goto_1

    :cond_3
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    sub-int/2addr p1, v0

    invoke-direct {p0, p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setValueInternal(IZ)V

    :goto_1
    return-void
.end method

.method private decrementSelectorIndices([I)V
    .locals 3

    array-length v0, p1

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    :goto_0
    if-lez v0, :cond_0

    add-int/lit8 v2, v0, -0x1

    aget v2, p1, v2

    aput v2, p1, v0

    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    :cond_0
    aget v0, p1, v1

    sub-int/2addr v0, v1

    iget-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-eqz v1, :cond_1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    if-ge v0, v1, :cond_1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    :cond_1
    const/4 v1, 0x0

    aput v0, p1, v1

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->ensureCachedScrollSelectorValue(I)V

    return-void
.end method

.method private ensureCachedScrollSelectorValue(I)V
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndexToStringCache:Landroid/util/SparseArray;

    invoke-virtual {v0, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    if-eqz v1, :cond_0

    return-void

    :cond_0
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    if-lt p1, v1, :cond_3

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    if-le p1, v2, :cond_1

    goto :goto_0

    :cond_1
    iget-object v2, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    if-eqz v2, :cond_2

    sub-int v1, p1, v1

    aget-object v1, v2, v1

    goto :goto_1

    :cond_2
    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->formatNumber(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_1

    :cond_3
    :goto_0
    const-string v1, ""

    :goto_1
    invoke-virtual {v0, p1, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    return-void
.end method

.method private ensureScrollWheelAdjusted()Z
    .locals 7

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    sub-int/2addr v0, v1

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    iput v1, p0, Lcom/miui/widget/picker/NumberPicker;->mPreviousScrollerY:I

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v1

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    div-int/lit8 v3, v2, 0x2

    if-le v1, v3, :cond_1

    if-lez v0, :cond_0

    neg-int v2, v2

    :cond_0
    add-int/2addr v0, v2

    :cond_1
    move v5, v0

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mAdjustScroller:Landroid/widget/Scroller;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/16 v6, 0x320

    invoke-virtual/range {v1 .. v6}, Landroid/widget/Scroller;->startScroll(IIIII)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    const/4 v0, 0x1

    return v0

    :cond_2
    return v1
.end method

.method private fling(I)V
    .locals 10

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mPreviousScrollerY:I

    if-lez p1, :cond_0

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const v9, 0x7fffffff

    move v5, p1

    invoke-virtual/range {v1 .. v9}, Landroid/widget/Scroller;->fling(IIIIIIII)V

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    const/4 v2, 0x0

    const v3, 0x7fffffff

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const v9, 0x7fffffff

    move v5, p1

    invoke-virtual/range {v1 .. v9}, Landroid/widget/Scroller;->fling(IIIIIIII)V

    :goto_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    return-void
.end method

.method private formatNumber(I)Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mFormatter:Lcom/miui/widget/picker/NumberPicker$Formatter;

    if-eqz v0, :cond_0

    invoke-interface {v0, p1}, Lcom/miui/widget/picker/NumberPicker$Formatter;->format(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    invoke-static {p1}, Lcom/miui/widget/util/SimpleNumberFormatter;->format(I)Ljava/lang/String;

    move-result-object p1

    :goto_0
    return-object p1
.end method

.method private getAlphaGradient(FIZ)I
    .locals 1

    const/high16 v0, 0x3f800000    # 1.0f

    cmpl-float v0, p1, v0

    if-ltz v0, :cond_0

    return p2

    :cond_0
    if-eqz p3, :cond_1

    neg-float p1, p1

    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result p3

    int-to-float p3, p3

    mul-float/2addr p1, p3

    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result p3

    int-to-float p3, p3

    add-float/2addr p1, p3

    goto :goto_0

    :cond_1
    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result p3

    int-to-float p3, p3

    mul-float/2addr p1, p3

    :goto_0
    float-to-int p1, p1

    const p3, 0xffffff

    and-int/2addr p2, p3

    shl-int/lit8 p1, p1, 0x18

    or-int/2addr p1, p2

    return p1
.end method

.method private getSelectedPos(Ljava/lang/String;)I
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    if-nez v0, :cond_0

    :try_start_0
    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    return p1

    :cond_0
    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    array-length v1, v1

    if-ge v0, v1, :cond_2

    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p1

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    aget-object v1, v1, v0

    invoke-virtual {v1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    add-int/2addr p1, v0

    return p1

    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    :try_start_1
    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1
    :try_end_1
    .catch Ljava/lang/NumberFormatException; {:try_start_1 .. :try_end_1} :catch_0

    return p1

    :catch_0
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    return p1
.end method

.method private getTextSize(FII)F
    .locals 1

    const/high16 v0, 0x3f800000    # 1.0f

    cmpl-float v0, p1, v0

    if-ltz v0, :cond_0

    int-to-float p1, p3

    return p1

    :cond_0
    sub-int/2addr p3, p2

    int-to-float p3, p3

    mul-float/2addr p1, p3

    int-to-float p2, p2

    add-float/2addr p1, p2

    return p1
.end method

.method private getWrappedSelectorIndex(I)I
    .locals 2

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    if-le p1, v0, :cond_0

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    sub-int/2addr p1, v0

    sub-int/2addr v0, v1

    rem-int/2addr p1, v0

    add-int/2addr v1, p1

    add-int/lit8 v1, v1, -0x1

    return v1

    :cond_0
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    if-ge p1, v1, :cond_1

    sub-int p1, v1, p1

    sub-int v1, v0, v1

    rem-int/2addr p1, v1

    sub-int/2addr v0, p1

    add-int/lit8 v0, v0, 0x1

    return v0

    :cond_1
    return p1
.end method

.method private incrementSelectorIndices([I)V
    .locals 3

    const/4 v0, 0x0

    :goto_0
    array-length v1, p1

    add-int/lit8 v1, v1, -0x1

    if-ge v0, v1, :cond_0

    add-int/lit8 v1, v0, 0x1

    aget v2, p1, v1

    aput v2, p1, v0

    move v0, v1

    goto :goto_0

    :cond_0
    array-length v0, p1

    add-int/lit8 v0, v0, -0x2

    aget v0, p1, v0

    add-int/lit8 v0, v0, 0x1

    iget-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-eqz v1, :cond_1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    if-le v0, v1, :cond_1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    :cond_1
    array-length v1, p1

    add-int/lit8 v1, v1, -0x1

    aput v0, p1, v1

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->ensureCachedScrollSelectorValue(I)V

    return-void
.end method

.method private initSoundPlayer()V
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    if-nez v0, :cond_0

    const-string v0, "NumberPicker_sound_play"

    invoke-static {v0}, Lcom/miui/widget/util/async/WorkerThreads;->aquireWorker(Ljava/lang/String;)Landroid/os/Looper;

    move-result-object v0

    new-instance v1, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v1, v0, v2}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;-><init>(Landroid/os/Looper;Landroid/content/Context;)V

    iput-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mId:I

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->init(I)V

    :cond_0
    return-void
.end method

.method private initializeFadingEdges()V
    .locals 2

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->setVerticalFadingEdgeEnabled(Z)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getBottom()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getTop()I

    move-result v1

    sub-int/2addr v0, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSize:I

    sub-int/2addr v0, v1

    div-int/lit8 v0, v0, 0x2

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->setFadingEdgeLength(I)V

    return-void
.end method

.method private initializeSelectorWheel()V
    .locals 4

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheelIndices()V

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    array-length v1, v0

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSize:I

    mul-int/2addr v1, v2

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getBottom()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getTop()I

    move-result v3

    sub-int/2addr v2, v3

    sub-int/2addr v2, v1

    int-to-float v1, v2

    const/4 v2, 0x0

    cmpg-float v3, v1, v2

    if-gez v3, :cond_0

    move v1, v2

    :cond_0
    array-length v0, v0

    int-to-float v0, v0

    div-float/2addr v1, v0

    const/high16 v0, 0x3f000000    # 0.5f

    add-float/2addr v1, v0

    float-to-int v0, v1

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorTextGapHeight:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSize:I

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorTextGapHeight:I

    add-int/2addr v0, v1

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getBaseline()I

    move-result v0

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getTop()I

    move-result v1

    add-int/2addr v0, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    mul-int/lit8 v1, v1, 0x2

    sub-int/2addr v0, v1

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    return-void
.end method

.method private initializeSelectorWheelIndices()V
    .locals 5

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndexToStringCache:Landroid/util/SparseArray;

    invoke-virtual {v0}, Landroid/util/SparseArray;->clear()V

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getValue()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    iget-object v3, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    array-length v3, v3

    if-ge v2, v3, :cond_1

    add-int/lit8 v3, v2, -0x2

    add-int/2addr v3, v1

    iget-boolean v4, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-eqz v4, :cond_0

    invoke-direct {p0, v3}, Lcom/miui/widget/picker/NumberPicker;->getWrappedSelectorIndex(I)I

    move-result v3

    :cond_0
    aput v3, v0, v2

    aget v3, v0, v2

    invoke-direct {p0, v3}, Lcom/miui/widget/picker/NumberPicker;->ensureCachedScrollSelectorValue(I)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method private makeMeasureSpec(II)I
    .locals 4

    const/4 v0, -0x1

    if-ne p2, v0, :cond_0

    return p1

    :cond_0
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v0

    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getMode(I)I

    move-result v1

    const/high16 v2, -0x80000000

    const/high16 v3, 0x40000000    # 2.0f

    if-eq v1, v2, :cond_3

    if-eqz v1, :cond_2

    if-ne v1, v3, :cond_1

    return p1

    :cond_1
    new-instance p1, Ljava/lang/IllegalArgumentException;

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "Unknown measure mode: "

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_2
    invoke-static {p2, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    return p1

    :cond_3
    invoke-static {v0, p2}, Ljava/lang/Math;->min(II)I

    move-result p1

    invoke-static {p1, v3}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    return p1
.end method

.method private moveToFinalScrollerPosition(Landroid/widget/Scroller;)Z
    .locals 6

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Landroid/widget/Scroller;->forceFinished(Z)V

    invoke-virtual {p1}, Landroid/widget/Scroller;->getFinalY()I

    move-result v1

    invoke-virtual {p1}, Landroid/widget/Scroller;->getCurrY()I

    move-result p1

    sub-int/2addr v1, p1

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    add-int/2addr p1, v1

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    rem-int/2addr p1, v2

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    sub-int/2addr v2, p1

    const/4 p1, 0x0

    if-eqz v2, :cond_2

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v3

    iget v4, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    div-int/lit8 v5, v4, 0x2

    if-le v3, v5, :cond_1

    if-lez v2, :cond_0

    sub-int/2addr v2, v4

    goto :goto_0

    :cond_0
    add-int/2addr v2, v4

    :cond_1
    :goto_0
    add-int/2addr v1, v2

    invoke-virtual {p0, p1, v1}, Lcom/miui/widget/picker/NumberPicker;->scrollBy(II)V

    return v0

    :cond_2
    return p1
.end method

.method private notifyChange(I)V
    .locals 2

    const/4 v0, 0x4

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->sendAccessibilityEvent(I)V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->playSound()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->EFFECT_KEY_MESH_LIGHT:Ljava/lang/String;

    invoke-static {v0, v1}, Lmiui/gallery/support/linearmotor/LinearMotorHelper;->performHapticFeedback(Landroid/content/Context;Ljava/lang/String;)Z

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mOnValueChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    if-eqz v0, :cond_0

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    invoke-interface {v0, p0, p1, v1}, Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;->onValueChange(Lcom/miui/widget/picker/NumberPicker;II)V

    :cond_0
    return-void
.end method

.method private onScrollStateChange(I)V
    .locals 2

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    if-nez p1, :cond_2

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mUpdateText:Ljava/lang/String;

    if-eqz v0, :cond_1

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mUpdateText:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    :cond_1
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mUpdateText:Ljava/lang/String;

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->stopSoundPlay()V

    :cond_2
    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mOnScrollListener:Lcom/miui/widget/picker/NumberPicker$OnScrollListener;

    if-eqz v0, :cond_3

    invoke-interface {v0, p0, p1}, Lcom/miui/widget/picker/NumberPicker$OnScrollListener;->onScrollStateChange(Lcom/miui/widget/picker/NumberPicker;I)V

    :cond_3
    return-void
.end method

.method private onScrollerFinished(Landroid/widget/Scroller;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    if-ne p1, v0, :cond_1

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->ensureScrollWheelAdjusted()Z

    move-result p1

    if-nez p1, :cond_0

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    :cond_0
    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->onScrollStateChange(I)V

    goto :goto_0

    :cond_1
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    const/4 v0, 0x1

    if-eq p1, v0, :cond_2

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    :cond_2
    :goto_0
    return-void
.end method

.method private playSound()V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->play()V

    :cond_0
    return-void
.end method

.method private postBeginSoftInputOnLongPressCommand()V
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mBeginSoftInputOnLongPressCommand:Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;

    invoke-direct {v0, p0}, Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;-><init>(Lcom/miui/widget/picker/NumberPicker;)V

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mBeginSoftInputOnLongPressCommand:Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;

    goto :goto_0

    :cond_0
    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :goto_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mBeginSoftInputOnLongPressCommand:Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;

    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {p0, v0, v1, v2}, Lcom/miui/widget/picker/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private postChangeCurrentByOneFromLongPress(ZJ)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mChangeCurrentByOneFromLongPressCommand:Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

    invoke-direct {v0, p0}, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;-><init>(Lcom/miui/widget/picker/NumberPicker;)V

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mChangeCurrentByOneFromLongPressCommand:Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

    goto :goto_0

    :cond_0
    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :goto_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mChangeCurrentByOneFromLongPressCommand:Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

    invoke-static {v0, p1}, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;->access$200(Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mChangeCurrentByOneFromLongPressCommand:Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/widget/picker/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private postSetSelectionCommand(II)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSetSelectionCommand:Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

    invoke-direct {v0, p0}, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;-><init>(Lcom/miui/widget/picker/NumberPicker;)V

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSetSelectionCommand:Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

    goto :goto_0

    :cond_0
    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :goto_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSetSelectionCommand:Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

    invoke-static {v0, p1}, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->access$302(Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;I)I

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mSetSelectionCommand:Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

    invoke-static {p1, p2}, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->access$402(Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;I)I

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mSetSelectionCommand:Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private refreshWheel()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheelIndices()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    return-void
.end method

.method private releaseSoundPlayer()V
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    if-eqz v0, :cond_0

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mId:I

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->release(I)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    :cond_0
    return-void
.end method

.method private removeAllCallbacks()V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mChangeCurrentByOneFromLongPressCommand:Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

    if-eqz v0, :cond_0

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSetSelectionCommand:Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;

    if-eqz v0, :cond_1

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mBeginSoftInputOnLongPressCommand:Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;

    if-eqz v0, :cond_2

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :cond_2
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;->cancel()V

    return-void
.end method

.method private removeBeginSoftInputCommand()V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mBeginSoftInputOnLongPressCommand:Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;

    if-eqz v0, :cond_0

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method private removeChangeCurrentByOneFromLongPress()V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mChangeCurrentByOneFromLongPressCommand:Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;

    if-eqz v0, :cond_0

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method private resolveSizeAndStateRespectingMinSize(III)I
    .locals 1

    const/4 v0, -0x1

    if-eq p1, v0, :cond_0

    invoke-static {p1, p2}, Ljava/lang/Math;->max(II)I

    move-result p1

    const/4 p2, 0x0

    invoke-static {p1, p3, p2}, Lcom/miui/widget/picker/NumberPicker;->resolveSizeAndState(III)I

    move-result p1

    return p1

    :cond_0
    return p2
.end method

.method private setValueInternal(IZ)V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-eqz v0, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->getWrappedSelectorIndex(I)I

    move-result p1

    goto :goto_0

    :cond_0
    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    invoke-static {p1, v0}, Ljava/lang/Math;->max(II)I

    move-result p1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    invoke-static {p1, v0}, Ljava/lang/Math;->min(II)I

    move-result p1

    :goto_0
    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    if-ne v0, p1, :cond_1

    return-void

    :cond_1
    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    if-eqz p2, :cond_2

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->notifyChange(I)V

    :cond_2
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheelIndices()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    return-void
.end method

.method private stopSoundPlay()V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSoundPlayHandler:Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;->stop()V

    :cond_0
    return-void
.end method

.method private updateInputTextView()Z
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    if-nez v0, :cond_0

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->formatNumber(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_0
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    sub-int/2addr v1, v2

    aget-object v0, v0, v1

    :goto_0
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_3

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    if-eqz v1, :cond_1

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mUpdateText:Ljava/lang/String;

    goto :goto_1

    :cond_1
    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    :cond_2
    :goto_1
    const/4 v0, 0x1

    return v0

    :cond_3
    const/4 v0, 0x0

    return v0
.end method

.method private validateInputTextView(Landroid/view/View;)V
    .locals 1

    check-cast p1, Landroid/widget/TextView;

    invoke-virtual {p1}, Landroid/widget/TextView;->getText()Ljava/lang/CharSequence;

    move-result-object p1

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->getSelectedPos(Ljava/lang/String;)I

    move-result p1

    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setValueInternal(IZ)V

    :goto_0
    return-void
.end method


# virtual methods
.method public computeScroll()V
    .locals 4

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mAdjustScroller:Landroid/widget/Scroller;

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v1

    if-eqz v1, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0}, Landroid/widget/Scroller;->computeScrollOffset()Z

    invoke-virtual {v0}, Landroid/widget/Scroller;->getCurrY()I

    move-result v1

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mPreviousScrollerY:I

    if-nez v2, :cond_1

    invoke-virtual {v0}, Landroid/widget/Scroller;->getStartY()I

    move-result v2

    iput v2, p0, Lcom/miui/widget/picker/NumberPicker;->mPreviousScrollerY:I

    :cond_1
    const/4 v2, 0x0

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mPreviousScrollerY:I

    sub-int v3, v1, v3

    invoke-virtual {p0, v2, v3}, Lcom/miui/widget/picker/NumberPicker;->scrollBy(II)V

    iput v1, p0, Lcom/miui/widget/picker/NumberPicker;->mPreviousScrollerY:I

    invoke-virtual {v0}, Landroid/widget/Scroller;->isFinished()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->onScrollerFinished(Landroid/widget/Scroller;)V

    goto :goto_0

    :cond_2
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    :goto_0
    return-void
.end method

.method protected dispatchHoverEvent(Landroid/view/MotionEvent;)Z
    .locals 8

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-nez v0, :cond_0

    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchHoverEvent(Landroid/view/MotionEvent;)Z

    move-result p1

    return p1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "accessibility"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/accessibility/AccessibilityManager;

    invoke-virtual {v0}, Landroid/view/accessibility/AccessibilityManager;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_6

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    float-to-int v0, v0

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mTopSelectionDividerTop:I

    if-ge v0, v1, :cond_1

    const/4 v0, 0x3

    goto :goto_0

    :cond_1
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mBottomSelectionDividerBottom:I

    if-le v0, v1, :cond_2

    const/4 v0, 0x1

    goto :goto_0

    :cond_2
    const/4 v0, 0x2

    :goto_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result p1

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getAccessibilityNodeProvider()Landroid/view/accessibility/AccessibilityNodeProvider;

    move-result-object v1

    check-cast v1, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;

    const/4 v2, 0x7

    const/4 v3, -0x1

    const/16 v4, 0x100

    const/4 v5, 0x0

    const/16 v6, 0x40

    const/16 v7, 0x80

    if-eq p1, v2, :cond_5

    const/16 v2, 0x9

    if-eq p1, v2, :cond_4

    const/16 v2, 0xa

    if-eq p1, v2, :cond_3

    goto :goto_1

    :cond_3
    invoke-virtual {v1, v0, v4}, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;->sendAccessibilityEventForVirtualView(II)V

    iput v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHoveredChildVirtualViewId:I

    goto :goto_1

    :cond_4
    invoke-virtual {v1, v0, v7}, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;->sendAccessibilityEventForVirtualView(II)V

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHoveredChildVirtualViewId:I

    invoke-virtual {v1, v0, v6, v5}, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;->performAction(IILandroid/os/Bundle;)Z

    goto :goto_1

    :cond_5
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHoveredChildVirtualViewId:I

    if-eq p1, v0, :cond_6

    if-eq p1, v3, :cond_6

    invoke-virtual {v1, p1, v4}, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;->sendAccessibilityEventForVirtualView(II)V

    invoke-virtual {v1, v0, v7}, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;->sendAccessibilityEventForVirtualView(II)V

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHoveredChildVirtualViewId:I

    invoke-virtual {v1, v0, v6, v5}, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;->performAction(IILandroid/os/Bundle;)Z

    :cond_6
    :goto_1
    const/4 p1, 0x0

    return p1
.end method

.method public dispatchKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 5

    invoke-virtual {p1}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v0

    const/16 v1, 0x13

    const/16 v2, 0x14

    if-eq v0, v1, :cond_1

    if-eq v0, v2, :cond_1

    const/16 v1, 0x17

    if-eq v0, v1, :cond_0

    const/16 v1, 0x42

    if-eq v0, v1, :cond_0

    goto :goto_3

    :cond_0
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeAllCallbacks()V

    goto :goto_3

    :cond_1
    iget-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-nez v1, :cond_2

    goto :goto_3

    :cond_2
    invoke-virtual {p1}, Landroid/view/KeyEvent;->getAction()I

    move-result v1

    const/4 v3, 0x1

    if-eqz v1, :cond_4

    if-eq v1, v3, :cond_3

    goto :goto_3

    :cond_3
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHandledDownDpadKeyCode:I

    if-ne v1, v0, :cond_9

    const/4 p1, -0x1

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHandledDownDpadKeyCode:I

    return v3

    :cond_4
    iget-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-nez v1, :cond_6

    if-ne v0, v2, :cond_5

    goto :goto_0

    :cond_5
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getValue()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getMinValue()I

    move-result v4

    if-le v1, v4, :cond_9

    goto :goto_1

    :cond_6
    :goto_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getValue()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getMaxValue()I

    move-result v4

    if-ge v1, v4, :cond_9

    :goto_1
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->requestFocus()Z

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLastHandledDownDpadKeyCode:I

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeAllCallbacks()V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    invoke-virtual {p1}, Landroid/widget/Scroller;->isFinished()Z

    move-result p1

    if-eqz p1, :cond_8

    if-ne v0, v2, :cond_7

    move p1, v3

    goto :goto_2

    :cond_7
    const/4 p1, 0x0

    :goto_2
    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->changeValueByOne(Z)V

    :cond_8
    return v3

    :cond_9
    :goto_3
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchKeyEvent(Landroid/view/KeyEvent;)Z

    move-result p1

    return p1
.end method

.method public dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    const/4 p1, 0x1

    return p1
.end method

.method public dispatchTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    const/4 v1, 0x1

    if-eq v0, v1, :cond_0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeAllCallbacks()V

    :goto_0
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    move-result p1

    return p1
.end method

.method public dispatchTrackballEvent(Landroid/view/MotionEvent;)Z
    .locals 2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    const/4 v1, 0x1

    if-eq v0, v1, :cond_0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeAllCallbacks()V

    :goto_0
    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->dispatchTrackballEvent(Landroid/view/MotionEvent;)Z

    move-result p1

    return p1
.end method

.method protected drawableStateChanged()V
    .locals 0

    invoke-super {p0}, Landroid/widget/LinearLayout;->drawableStateChanged()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->tryComputeMaxWidth()V

    return-void
.end method

.method public getAccessibilityNodeProvider()Landroid/view/accessibility/AccessibilityNodeProvider;
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-nez v0, :cond_0

    invoke-super {p0}, Landroid/widget/LinearLayout;->getAccessibilityNodeProvider()Landroid/view/accessibility/AccessibilityNodeProvider;

    move-result-object v0

    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mAccessibilityNodeProvider:Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;

    if-nez v0, :cond_1

    new-instance v0, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;

    invoke-direct {v0, p0}, Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;-><init>(Lcom/miui/widget/picker/NumberPicker;)V

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mAccessibilityNodeProvider:Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mAccessibilityNodeProvider:Lcom/miui/widget/picker/NumberPicker$AccessibilityNodeProviderImpl;

    return-object v0
.end method

.method protected getBottomFadingEdgeStrength()F
    .locals 1

    const v0, 0x3f666666    # 0.9f

    return v0
.end method

.method public getDisplayedValues()[Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    return-object v0
.end method

.method public getMaxValue()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    return v0
.end method

.method public getMinValue()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    return v0
.end method

.method protected getTopFadingEdgeStrength()F
    .locals 1

    const v0, 0x3f666666    # 0.9f

    return v0
.end method

.method public getValue()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    return v0
.end method

.method public getWrapSelectorWheel()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    return v0
.end method

.method protected onAttachedToWindow()V
    .locals 0

    invoke-super {p0}, Landroid/widget/LinearLayout;->onAttachedToWindow()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initSoundPlayer()V

    return-void
.end method

.method protected onDetachedFromWindow()V
    .locals 1

    invoke-super {p0}, Landroid/widget/LinearLayout;->onDetachedFromWindow()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->releaseSoundPlayer()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeAllCallbacks()V

    const-string v0, "NumberPicker_sound_play"

    invoke-static {v0}, Lcom/miui/widget/util/async/WorkerThreads;->releaseWorker(Ljava/lang/String;)V

    return-void
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 14

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-nez v0, :cond_0

    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->onDraw(Landroid/graphics/Canvas;)V

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getPaddingLeft()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getPaddingRight()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getRight()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getLeft()I

    move-result v3

    sub-int/2addr v2, v3

    add-int/2addr v2, v0

    sub-int/2addr v2, v1

    div-int/lit8 v2, v2, 0x2

    int-to-float v0, v2

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    int-to-float v1, v1

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    mul-int/lit8 v3, v3, 0x2

    add-int/2addr v2, v3

    int-to-float v2, v2

    iget-object v3, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndexToStringCache:Landroid/util/SparseArray;

    iget-object v4, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    const/4 v5, 0x0

    move v6, v1

    move v1, v5

    :goto_0
    array-length v7, v4

    const/high16 v8, 0x40000000    # 2.0f

    if-ge v1, v7, :cond_2

    aget v7, v4, v1

    invoke-virtual {v3, v7}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    sub-float v9, v2, v6

    invoke-static {v9}, Ljava/lang/Math;->abs(F)F

    move-result v9

    iget v10, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    int-to-float v10, v10

    div-float/2addr v9, v10

    iget v10, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    iget v11, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    invoke-direct {p0, v9, v10, v11}, Lcom/miui/widget/picker/NumberPicker;->getTextSize(FII)F

    move-result v10

    iget-object v11, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {v11, v10}, Landroid/graphics/Paint;->setTextSize(F)V

    iget-object v11, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    iget v12, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHint:I

    invoke-direct {p0, v9, v12, v5}, Lcom/miui/widget/picker/NumberPicker;->getAlphaGradient(FIZ)I

    move-result v12

    invoke-virtual {v11, v12}, Landroid/graphics/Paint;->setColor(I)V

    iget v11, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    int-to-float v11, v11

    sub-float v11, v10, v11

    div-float/2addr v11, v8

    add-float/2addr v11, v6

    iget-object v12, p0, Lcom/miui/widget/picker/NumberPicker;->mNormalWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v7, v0, v11, v12}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    const/high16 v11, 0x3f800000    # 1.0f

    cmpg-float v11, v9, v11

    if-gez v11, :cond_1

    iget-object v11, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {v11, v10}, Landroid/graphics/Paint;->setTextSize(F)V

    iget-object v11, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    iget v12, p0, Lcom/miui/widget/picker/NumberPicker;->mTextColorHilight:I

    const/4 v13, 0x1

    invoke-direct {p0, v9, v12, v13}, Lcom/miui/widget/picker/NumberPicker;->getAlphaGradient(FIZ)I

    move-result v9

    invoke-virtual {v11, v9}, Landroid/graphics/Paint;->setColor(I)V

    iget v9, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    int-to-float v9, v9

    sub-float/2addr v10, v9

    div-float/2addr v10, v8

    add-float/2addr v10, v6

    iget-object v8, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v7, v0, v10, v8}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    :cond_1
    iget v7, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    int-to-float v7, v7

    add-float/2addr v6, v7

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_4

    iget-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->isShowLabel:Z

    if-eqz v1, :cond_4

    invoke-static {p0}, Lcom/miui/widget/util/ViewUtils;->isLayoutRtl(Landroid/view/View;)Z

    move-result v1

    if-eqz v1, :cond_3

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedMaxTextWidth:F

    div-float/2addr v1, v8

    sub-float/2addr v0, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_LEFT:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    iget-object v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    invoke-interface {v3}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v1

    sub-float/2addr v0, v1

    goto :goto_1

    :cond_3
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedMaxTextWidth:F

    div-float/2addr v1, v8

    add-float/2addr v0, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_LEFT:I

    int-to-float v1, v1

    add-float/2addr v0, v1

    :goto_1
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    sub-float/2addr v2, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    div-int/lit8 v1, v1, 0x2

    int-to-float v1, v1

    add-float/2addr v2, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->MARGIN_LABEL_TOP:I

    int-to-float v1, v1

    add-float/2addr v2, v1

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    invoke-interface {v1}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v1

    iget-object v3, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v1, v0, v2, v3}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    :cond_4
    return-void
.end method

.method public onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 2

    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    const-class v0, Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setScrollable(Z)V

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    add-int/2addr v0, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    mul-int/2addr v0, v1

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setScrollY(I)V

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    sub-int/2addr v0, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    mul-int/2addr v0, v1

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setMaxScrollY(I)V

    return-void
.end method

.method public onInterceptTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 5

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_8

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_2

    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    if-eqz v0, :cond_1

    return v1

    :cond_1
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeAllCallbacks()V

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const/4 v2, 0x4

    invoke-virtual {v0, v2}, Landroid/widget/EditText;->setVisibility(I)V

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownEventY:F

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownOrMoveEventY:F

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v2

    iput-wide v2, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownEventTime:J

    iput-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->mIngonreMoveEvents:Z

    iput-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->mShowSoftInputOnTap:Z

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownEventY:F

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mTopSelectionDividerTop:I

    int-to-float v0, v0

    cmpg-float v0, p1, v0

    const/4 v2, 0x1

    if-gez v0, :cond_2

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    if-nez p1, :cond_3

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    const/4 v0, 0x2

    invoke-virtual {p1, v0}, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;->buttonPressDelayed(I)V

    goto :goto_0

    :cond_2
    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mBottomSelectionDividerBottom:I

    int-to-float v0, v0

    cmpl-float p1, p1, v0

    if-lez p1, :cond_3

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    if-nez p1, :cond_3

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;->buttonPressDelayed(I)V

    :cond_3
    :goto_0
    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    invoke-virtual {p1}, Landroid/widget/Scroller;->isFinished()Z

    move-result p1

    if-nez p1, :cond_4

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    invoke-virtual {p1, v2}, Landroid/widget/Scroller;->forceFinished(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mAdjustScroller:Landroid/widget/Scroller;

    invoke-virtual {p1, v2}, Landroid/widget/Scroller;->forceFinished(Z)V

    invoke-direct {p0, v1}, Lcom/miui/widget/picker/NumberPicker;->onScrollStateChange(I)V

    goto :goto_1

    :cond_4
    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mAdjustScroller:Landroid/widget/Scroller;

    invoke-virtual {p1}, Landroid/widget/Scroller;->isFinished()Z

    move-result p1

    if-nez p1, :cond_5

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mFlingScroller:Landroid/widget/Scroller;

    invoke-virtual {p1, v2}, Landroid/widget/Scroller;->forceFinished(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mAdjustScroller:Landroid/widget/Scroller;

    invoke-virtual {p1, v2}, Landroid/widget/Scroller;->forceFinished(Z)V

    goto :goto_1

    :cond_5
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownEventY:F

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mTopSelectionDividerTop:I

    int-to-float v0, v0

    cmpg-float v0, p1, v0

    if-gez v0, :cond_6

    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result p1

    int-to-long v3, p1

    invoke-direct {p0, v1, v3, v4}, Lcom/miui/widget/picker/NumberPicker;->postChangeCurrentByOneFromLongPress(ZJ)V

    goto :goto_1

    :cond_6
    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mBottomSelectionDividerBottom:I

    int-to-float v0, v0

    cmpl-float p1, p1, v0

    if-lez p1, :cond_7

    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result p1

    int-to-long v0, p1

    invoke-direct {p0, v2, v0, v1}, Lcom/miui/widget/picker/NumberPicker;->postChangeCurrentByOneFromLongPress(ZJ)V

    goto :goto_1

    :cond_7
    iput-boolean v2, p0, Lcom/miui/widget/picker/NumberPicker;->mShowSoftInputOnTap:Z

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->postBeginSoftInputOnLongPressCommand()V

    :goto_1
    return v2

    :cond_8
    :goto_2
    return v1
.end method

.method protected onLayout(ZIIII)V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-nez v0, :cond_0

    invoke-super/range {p0 .. p5}, Landroid/widget/LinearLayout;->onLayout(ZIIII)V

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredWidth()I

    move-result p2

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredHeight()I

    move-result p3

    iget-object p4, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {p4}, Landroid/widget/EditText;->getMeasuredWidth()I

    move-result p4

    iget-object p5, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {p5}, Landroid/widget/EditText;->getMeasuredHeight()I

    move-result p5

    sub-int/2addr p2, p4

    div-int/lit8 p2, p2, 0x2

    sub-int/2addr p3, p5

    div-int/lit8 p3, p3, 0x2

    add-int/2addr p4, p2

    add-int/2addr p5, p3

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v0, p2, p3, p4, p5}, Landroid/widget/EditText;->layout(IIII)V

    if-eqz p1, :cond_1

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheel()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeFadingEdges()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getHeight()I

    move-result p1

    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectionDividersDistance:I

    sub-int/2addr p1, p2

    div-int/lit8 p1, p1, 0x2

    iget p3, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectionDividerHeight:I

    sub-int/2addr p1, p3

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mTopSelectionDividerTop:I

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mTopSelectionDividerTop:I

    mul-int/lit8 p3, p3, 0x2

    add-int/2addr p1, p3

    add-int/2addr p1, p2

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mBottomSelectionDividerBottom:I

    :cond_1
    return-void
.end method

.method protected onMeasure(II)V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-nez v0, :cond_0

    invoke-super {p0, p1, p2}, Landroid/widget/LinearLayout;->onMeasure(II)V

    return-void

    :cond_0
    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    invoke-direct {p0, p1, v0}, Lcom/miui/widget/picker/NumberPicker;->makeMeasureSpec(II)I

    move-result v0

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxHeight:I

    invoke-direct {p0, p2, v1}, Lcom/miui/widget/picker/NumberPicker;->makeMeasureSpec(II)I

    move-result v1

    invoke-super {p0, v0, v1}, Landroid/widget/LinearLayout;->onMeasure(II)V

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinWidth:I

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredWidth()I

    move-result v1

    invoke-direct {p0, v0, v1, p1}, Lcom/miui/widget/picker/NumberPicker;->resolveSizeAndStateRespectingMinSize(III)I

    move-result p1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinHeight:I

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredHeight()I

    move-result v1

    invoke-direct {p0, v0, v1, p2}, Lcom/miui/widget/picker/NumberPicker;->resolveSizeAndStateRespectingMinSize(III)I

    move-result p2

    invoke-virtual {p0, p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setMeasuredDimension(II)V

    return-void
.end method

.method public onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 2

    invoke-super {p0, p1}, Landroid/widget/LinearLayout;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mUpdateText:Ljava/lang/String;

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    if-nez v0, :cond_1

    const-string v0, ""

    :cond_1
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Landroid/view/accessibility/AccessibilityEvent;->getText()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 9

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->isEnabled()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_d

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mHasSelectorWheel:Z

    if-nez v0, :cond_0

    goto/16 :goto_4

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-nez v0, :cond_1

    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v0, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    const/4 v2, 0x2

    const/4 v3, 0x1

    if-eq v0, v3, :cond_6

    if-eq v0, v2, :cond_2

    goto/16 :goto_3

    :cond_2
    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mIngonreMoveEvents:Z

    if-eqz v0, :cond_3

    goto/16 :goto_3

    :cond_3
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result p1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mScrollState:I

    if-eq v0, v3, :cond_4

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownEventY:F

    sub-float v0, p1, v0

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    float-to-int v0, v0

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mTouchSlop:I

    if-le v0, v1, :cond_5

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeAllCallbacks()V

    invoke-direct {p0, v3}, Lcom/miui/widget/picker/NumberPicker;->onScrollStateChange(I)V

    goto :goto_0

    :cond_4
    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownOrMoveEventY:F

    sub-float v0, p1, v0

    float-to-int v0, v0

    invoke-virtual {p0, v1, v0}, Lcom/miui/widget/picker/NumberPicker;->scrollBy(II)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    :cond_5
    :goto_0
    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownOrMoveEventY:F

    goto/16 :goto_3

    :cond_6
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeBeginSoftInputCommand()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->removeChangeCurrentByOneFromLongPress()V

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;->cancel()V

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mVelocityTracker:Landroid/view/VelocityTracker;

    const/16 v4, 0x3e8

    iget v5, p0, Lcom/miui/widget/picker/NumberPicker;->mMaximumFlingVelocity:I

    int-to-float v5, v5

    invoke-virtual {v0, v4, v5}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->getYVelocity()F

    move-result v0

    float-to-int v0, v0

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v4

    iget v5, p0, Lcom/miui/widget/picker/NumberPicker;->mMaximumFlingVelocity:I

    invoke-static {v5}, Ljava/lang/Math;->abs(I)I

    move-result v5

    if-lt v4, v5, :cond_7

    int-to-float v0, v0

    iget v4, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxFlingSpeedFactor:F

    mul-float/2addr v0, v4

    float-to-int v0, v0

    :cond_7
    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v4

    iget v5, p0, Lcom/miui/widget/picker/NumberPicker;->mMinimumFlingVelocity:I

    if-le v4, v5, :cond_8

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/NumberPicker;->fling(I)V

    invoke-direct {p0, v2}, Lcom/miui/widget/picker/NumberPicker;->onScrollStateChange(I)V

    goto :goto_2

    :cond_8
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    float-to-int v0, v0

    int-to-float v4, v0

    iget v5, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownEventY:F

    sub-float/2addr v4, v5

    invoke-static {v4}, Ljava/lang/Math;->abs(F)F

    move-result v4

    float-to-int v4, v4

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v5

    iget-wide v7, p0, Lcom/miui/widget/picker/NumberPicker;->mLastDownEventTime:J

    sub-long/2addr v5, v7

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mTouchSlop:I

    if-gt v4, p1, :cond_b

    invoke-static {}, Landroid/view/ViewConfiguration;->getTapTimeout()I

    move-result p1

    int-to-long v7, p1

    cmp-long p1, v5, v7

    if-gez p1, :cond_b

    iget-boolean p1, p0, Lcom/miui/widget/picker/NumberPicker;->mShowSoftInputOnTap:Z

    if-eqz p1, :cond_9

    iput-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker;->mShowSoftInputOnTap:Z

    goto :goto_1

    :cond_9
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    div-int/2addr v0, p1

    sub-int/2addr v0, v2

    if-lez v0, :cond_a

    invoke-direct {p0, v3}, Lcom/miui/widget/picker/NumberPicker;->changeValueByOne(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    invoke-virtual {p1, v3}, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;->buttonTapped(I)V

    goto :goto_1

    :cond_a
    if-gez v0, :cond_c

    invoke-direct {p0, v1}, Lcom/miui/widget/picker/NumberPicker;->changeValueByOne(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mPressedStateHelper:Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker$PressedStateHelper;->buttonTapped(I)V

    goto :goto_1

    :cond_b
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->ensureScrollWheelAdjusted()Z

    :cond_c
    :goto_1
    invoke-direct {p0, v1}, Lcom/miui/widget/picker/NumberPicker;->onScrollStateChange(I)V

    :goto_2
    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {p1}, Landroid/view/VelocityTracker;->recycle()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mVelocityTracker:Landroid/view/VelocityTracker;

    :goto_3
    return v3

    :cond_d
    :goto_4
    return v1
.end method

.method public scrollBy(II)V
    .locals 4

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    const/4 v1, 0x2

    if-nez v0, :cond_0

    if-lez p2, :cond_0

    aget v0, p1, v1

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    if-gt v0, v2, :cond_0

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    return-void

    :cond_0
    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-nez v0, :cond_1

    if-gez p2, :cond_1

    aget v0, p1, v1

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    if-lt v0, v2, :cond_1

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    return-void

    :cond_1
    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    add-int/2addr v0, p2

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    :cond_2
    :goto_0
    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    sub-int v0, p2, v0

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorTextGapHeight:I

    const/4 v3, 0x1

    if-le v0, v2, :cond_3

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    sub-int/2addr p2, v0

    iput p2, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->decrementSelectorIndices([I)V

    aget p2, p1, v1

    invoke-direct {p0, p2, v3}, Lcom/miui/widget/picker/NumberPicker;->setValueInternal(IZ)V

    iget-boolean p2, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-nez p2, :cond_2

    aget p2, p1, v1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    if-gt p2, v0, :cond_2

    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iput p2, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    goto :goto_0

    :cond_3
    :goto_1
    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    sub-int v0, p2, v0

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorTextGapHeight:I

    neg-int v2, v2

    if-ge v0, v2, :cond_4

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorElementHeight:I

    add-int/2addr p2, v0

    iput p2, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->incrementSelectorIndices([I)V

    aget p2, p1, v1

    invoke-direct {p0, p2, v3}, Lcom/miui/widget/picker/NumberPicker;->setValueInternal(IZ)V

    iget-boolean p2, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-nez p2, :cond_3

    aget p2, p1, v1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    if-lt p2, v0, :cond_3

    iget p2, p0, Lcom/miui/widget/picker/NumberPicker;->mInitialScrollOffset:I

    iput p2, p0, Lcom/miui/widget/picker/NumberPicker;->mCurrentScrollOffset:I

    goto :goto_1

    :cond_4
    return-void
.end method

.method public setDisplayedValues([Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const v0, 0x80001

    invoke-virtual {p1, v0}, Landroid/widget/EditText;->setRawInputType(I)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    const/4 v0, 0x2

    invoke-virtual {p1, v0}, Landroid/widget/EditText;->setRawInputType(I)V

    :goto_0
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheelIndices()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->tryComputeMaxWidth()V

    return-void
.end method

.method public setFormatter(Lcom/miui/widget/picker/NumberPicker$Formatter;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mFormatter:Lcom/miui/widget/picker/NumberPicker$Formatter;

    if-ne p1, v0, :cond_0

    return-void

    :cond_0
    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mFormatter:Lcom/miui/widget/picker/NumberPicker$Formatter;

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheelIndices()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    return-void
.end method

.method public setIsShowLabel(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/NumberPicker;->isShowLabel:Z

    return-void
.end method

.method public setLabel(Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    if-nez v0, :cond_0

    if-nez p1, :cond_1

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    if-eqz v0, :cond_2

    invoke-virtual {v0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    :cond_1
    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabel:Ljava/lang/CharSequence;

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    :cond_2
    return-void
.end method

.method public setLableTextSize(I)V
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelPaint:Landroid/graphics/Paint;

    if-eqz p1, :cond_1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mLabelTextSize:I

    int-to-float v0, v0

    invoke-virtual {p1, v0}, Landroid/graphics/Paint;->setTextSize(F)V

    :cond_1
    return-void
.end method

.method public setMaxFlingSpeedFactor(F)V
    .locals 1

    const/4 v0, 0x0

    cmpl-float v0, p1, v0

    if-ltz v0, :cond_0

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxFlingSpeedFactor:F

    :cond_0
    return-void
.end method

.method public setMaxValue(I)V
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    if-ltz p1, :cond_3

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    if-ge p1, v0, :cond_1

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    :cond_1
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    sub-int/2addr p1, v0

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    array-length v0, v0

    if-le p1, v0, :cond_2

    const/4 p1, 0x1

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    :goto_0
    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheelIndices()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->tryComputeMaxWidth()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    return-void

    :cond_3
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string v0, "maxValue must be >= 0"

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public setMinValue(I)V
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    if-ltz p1, :cond_3

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    if-le p1, v0, :cond_1

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mValue:I

    :cond_1
    iget p1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    sub-int/2addr p1, v0

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    array-length v0, v0

    if-le p1, v0, :cond_2

    const/4 p1, 0x1

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    :goto_0
    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->initializeSelectorWheelIndices()V

    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->updateInputTextView()Z

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->tryComputeMaxWidth()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    return-void

    :cond_3
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string v0, "minValue must be >= 0"

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public setOnLongPressUpdateInterval(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/widget/picker/NumberPicker;->mLongPressUpdateInterval:J

    return-void
.end method

.method public setOnScrollListener(Lcom/miui/widget/picker/NumberPicker$OnScrollListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mOnScrollListener:Lcom/miui/widget/picker/NumberPicker$OnScrollListener;

    return-void
.end method

.method public setOnValueChangedListener(Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker;->mOnValueChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    return-void
.end method

.method public setTextHilightSize(I)V
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    if-eqz v0, :cond_1

    int-to-float p1, p1

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setTextSize(F)V

    :cond_1
    return-void
.end method

.method public setTextSize(I)V
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    iput p1, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHint:I

    return-void
.end method

.method public setValue(I)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setValueInternal(IZ)V

    return-void
.end method

.method public setWrapSelectorWheel(Z)V
    .locals 2

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinValue:I

    sub-int/2addr v0, v1

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorIndices:[I

    array-length v1, v1

    if-lt v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-eqz p1, :cond_1

    if-eqz v0, :cond_2

    :cond_1
    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    if-eq p1, v0, :cond_2

    iput-boolean p1, p0, Lcom/miui/widget/picker/NumberPicker;->mWrapSelectorWheel:Z

    :cond_2
    invoke-direct {p0}, Lcom/miui/widget/picker/NumberPicker;->refreshWheel()V

    return-void
.end method

.method public tryComputeMaxWidth()V
    .locals 5

    iget-boolean v0, p0, Lcom/miui/widget/picker/NumberPicker;->mComputeMaxWidth:Z

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/high16 v0, -0x40800000    # -1.0f

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker;->mTextSizeHilight:I

    int-to-float v2, v2

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setTextSize(F)V

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    const/4 v2, 0x0

    if-nez v1, :cond_3

    const/4 v0, 0x0

    :goto_0
    const/16 v1, 0x9

    if-ge v2, v1, :cond_2

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v1

    cmpl-float v3, v1, v0

    if-lez v3, :cond_1

    move v0, v1

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxValue:I

    invoke-direct {p0, v1}, Lcom/miui/widget/picker/NumberPicker;->formatNumber(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v1, v0

    float-to-int v0, v1

    int-to-float v0, v0

    goto :goto_2

    :cond_3
    array-length v1, v1

    :goto_1
    if-ge v2, v1, :cond_5

    iget-object v3, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedValues:[Ljava/lang/String;

    aget-object v3, v3, v2

    iget-object v4, p0, Lcom/miui/widget/picker/NumberPicker;->mSelectorWheelPaint:Landroid/graphics/Paint;

    invoke-virtual {v4, v3}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v3

    cmpl-float v4, v3, v0

    if-lez v4, :cond_4

    move v0, v3

    :cond_4
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_5
    :goto_2
    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mDisplayedMaxTextWidth:F

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getPaddingLeft()I

    move-result v1

    iget-object v2, p0, Lcom/miui/widget/picker/NumberPicker;->mInputText:Landroid/widget/EditText;

    invoke-virtual {v2}, Landroid/widget/EditText;->getPaddingRight()I

    move-result v2

    add-int/2addr v1, v2

    int-to-float v1, v1

    add-float/2addr v0, v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getPaddingLeft()I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/NumberPicker;->getPaddingRight()I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    int-to-float v1, v1

    cmpl-float v1, v1, v0

    if-eqz v1, :cond_7

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMinWidth:I

    int-to-float v2, v1

    cmpl-float v2, v0, v2

    if-lez v2, :cond_6

    float-to-int v0, v0

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    goto :goto_3

    :cond_6
    iput v1, p0, Lcom/miui/widget/picker/NumberPicker;->mMaxWidth:I

    :cond_7
    :goto_3
    return-void
.end method
