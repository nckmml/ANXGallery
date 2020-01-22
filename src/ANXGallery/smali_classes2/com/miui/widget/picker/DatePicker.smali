.class public Lcom/miui/widget/picker/DatePicker;
.super Landroid/widget/FrameLayout;
.source "DatePicker.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;
    }
.end annotation


# static fields
.field private static final DEFALUT_DATE_FORMAT:[C

.field private static final DEFAULT_MIN_TIME:Ljava/lang/Long;

.field private static final LOG_TAG:Ljava/lang/String; = "DatePicker"


# instance fields
.field private is24Hour:Z

.field private isInit:Z

.field private isShowDay:Z

.field private isShowHour:Z

.field private isShowLabel:Z

.field private isShowMinute:Z

.field private isShowMonth:Z

.field private isShowSpinnersShown:Z

.field private isShowYear:Z

.field private mCurrentDate:Lcom/miui/widget/util/date/Calendar;

.field private mCurrentLocale:Ljava/util/Locale;

.field private final mDateFormat:Ljava/text/DateFormat;

.field private mDateFormatOrder:[C

.field private mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

.field private mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

.field private mIsEnabled:Z

.field private mIsLunarMode:Z

.field private mItemHilightTextSize:I

.field private mItemLabelSize:I

.field private mItemTextSize:I

.field private mJavaCalendar:Ljava/util/Calendar;

.field private mMaxDate:Lcom/miui/widget/util/date/Calendar;

.field private mMaxTime:J

.field private mMinDate:Lcom/miui/widget/util/date/Calendar;

.field private mMinTime:J

.field private mMinuteDisplayValues:[Ljava/lang/String;

.field private mMinuteInterval:I

.field private mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

.field private mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

.field private mNumberOfMonths:I

.field private mOnDateChangedListener:Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;

.field private mOnPickerCheckChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

.field private mPickers:[Lcom/miui/widget/picker/NumberPicker;

.field private mPickersContainer:Landroid/widget/LinearLayout;

.field private mRootView:Landroid/view/View;

.field private mShortMonths:[Ljava/lang/String;

.field private mSpinners:Landroid/widget/LinearLayout;

.field private mStartTime:J

.field private mTempDate:Lcom/miui/widget/util/date/Calendar;

.field private mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

.field private sChineseDays:[Ljava/lang/String;

.field private sChineseLeapMonthMark:Ljava/lang/String;

.field private sChineseLeapYearMonths:[Ljava/lang/String;

.field private sChineseMonths:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x5

    new-array v0, v0, [C

    fill-array-data v0, :array_0

    sput-object v0, Lcom/miui/widget/picker/DatePicker;->DEFALUT_DATE_FORMAT:[C

    const-wide/32 v0, 0x36ee800

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    sput-object v0, Lcom/miui/widget/picker/DatePicker;->DEFAULT_MIN_TIME:Ljava/lang/Long;

    return-void

    :array_0
    .array-data 2
        0x79s
        0x4ds
        0x64s
        0x48s
        0x6ds
    .end array-data
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/widget/picker/DatePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    sget v0, Lcom/miui/widget/R$attr;->datePickerStyle:I

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/widget/picker/DatePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 6

    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    const/4 v0, 0x1

    iput v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    const/4 v1, 0x0

    iput-object v1, p0, Lcom/miui/widget/picker/DatePicker;->sChineseDays:[Ljava/lang/String;

    iput-object v1, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    iput-object v1, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapYearMonths:[Ljava/lang/String;

    iput-object v1, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapMonthMark:Ljava/lang/String;

    new-instance v1, Ljava/text/SimpleDateFormat;

    const-string v2, "MM/dd/yyyy"

    invoke-direct {v1, v2}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    iput-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mDateFormat:Ljava/text/DateFormat;

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsEnabled:Z

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->initData()V

    sget-object v0, Lcom/miui/widget/R$styleable;->DatePicker:[I

    const/4 v1, 0x0

    invoke-virtual {p1, p2, v0, p3, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DatePicker;->setAttributes(Landroid/content/res/TypedArray;)V

    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mJavaCalendar:Ljava/util/Calendar;

    new-instance p1, Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p1}, Lcom/miui/widget/util/date/Calendar;-><init>()V

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    new-instance p1, Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p1}, Lcom/miui/widget/util/date/Calendar;-><init>()V

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    new-instance p1, Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p1}, Lcom/miui/widget/util/date/Calendar;-><init>()V

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    new-instance p1, Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p1}, Lcom/miui/widget/util/date/Calendar;-><init>()V

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowYear()Z

    move-result v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowMonth()Z

    move-result v2

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowDay()Z

    move-result v3

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowHour()Z

    move-result v4

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowMinute()Z

    move-result v5

    move-object v0, p0

    invoke-virtual/range {v0 .. v5}, Lcom/miui/widget/picker/DatePicker;->initView(ZZZZZ)V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    return-object p0
.end method

.method static synthetic access$1000(Lcom/miui/widget/picker/DatePicker;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->resetMonthsDisplayedValues()V

    return-void
.end method

.method static synthetic access$1100(Lcom/miui/widget/picker/DatePicker;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateSpinners()V

    return-void
.end method

.method static synthetic access$1200(Lcom/miui/widget/picker/DatePicker;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->notifyDateChanged()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/widget/picker/DatePicker;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    return p0
.end method

.method static synthetic access$400(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    return-object p0
.end method

.method static synthetic access$500(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    return-object p0
.end method

.method static synthetic access$800(Lcom/miui/widget/picker/DatePicker;)I
    .locals 0

    iget p0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    return p0
.end method

.method static synthetic access$900(Lcom/miui/widget/picker/DatePicker;J)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/widget/picker/DatePicker;->setDate(J)V

    return-void
.end method

.method private applyPickerStyle(Lcom/miui/widget/picker/NumberPicker;)V
    .locals 3

    iget v0, p0, Lcom/miui/widget/picker/DatePicker;->mItemHilightTextSize:I

    const/4 v1, 0x1

    if-lez v0, :cond_0

    invoke-virtual {p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setTextHilightSize(I)V

    move v0, v1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    iget v2, p0, Lcom/miui/widget/picker/DatePicker;->mItemLabelSize:I

    if-lez v2, :cond_1

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker;->setLableTextSize(I)V

    move v0, v1

    :cond_1
    iget v2, p0, Lcom/miui/widget/picker/DatePicker;->mItemTextSize:I

    if-lez v2, :cond_2

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker;->setTextSize(I)V

    move v0, v1

    :cond_2
    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->isShowLabel:Z

    invoke-virtual {p1, v1}, Lcom/miui/widget/picker/NumberPicker;->setIsShowLabel(Z)V

    if-eqz v0, :cond_3

    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker;->invalidate()V

    :cond_3
    return-void
.end method

.method private checkDisplayeValid(Lcom/miui/widget/picker/NumberPicker;II)V
    .locals 1

    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker;->getDisplayedValues()[Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    array-length v0, v0

    sub-int/2addr p3, p2

    add-int/lit8 p3, p3, 0x1

    if-ge v0, p3, :cond_0

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private checkIfTheDateIsOutOfMaxDateBounds()Z
    .locals 4

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    const/4 v0, 0x2

    goto :goto_0

    :cond_0
    move v0, v1

    :goto_0
    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v2, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v2

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v3, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    if-ne v2, v0, :cond_1

    return v1

    :cond_1
    const/4 v0, 0x0

    return v0
.end method

.method private checkIfTheDateIsOutOfMinDateBounds()Z
    .locals 4

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    const/4 v0, 0x2

    goto :goto_0

    :cond_0
    move v0, v1

    :goto_0
    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v2, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v2

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v3, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    if-ne v2, v0, :cond_1

    return v1

    :cond_1
    const/4 v0, 0x0

    return v0
.end method

.method private computeDayCount(Lcom/miui/widget/util/date/Calendar;Lcom/miui/widget/util/date/Calendar;)I
    .locals 8

    invoke-virtual {p1}, Lcom/miui/widget/util/date/Calendar;->clone()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2}, Lcom/miui/widget/util/date/Calendar;->clone()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/widget/util/date/Calendar;

    const/16 v0, 0x12

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    const/16 v2, 0x14

    invoke-virtual {p1, v2, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    const/16 v3, 0x15

    invoke-virtual {p1, v3, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    const/16 v4, 0x16

    invoke-virtual {p1, v4, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2, v0, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2, v2, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2, v3, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2, v4, v1}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p1}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    const-wide/16 v2, 0x3e8

    div-long/2addr v0, v2

    const-wide/16 v4, 0x3c

    div-long/2addr v0, v4

    div-long/2addr v0, v4

    const-wide/16 v6, 0x18

    div-long/2addr v0, v6

    invoke-virtual {p2}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide p1

    div-long/2addr p1, v2

    div-long/2addr p1, v4

    div-long/2addr p1, v4

    div-long/2addr p1, v6

    sub-long/2addr v0, p1

    long-to-int p1, v0

    return p1
.end method

.method private initData()V
    .locals 5

    invoke-static {}, Lcom/miui/widget/util/DateDataCacheUtils;->isHaveCacheData()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getDefault()Lcom/miui/widget/util/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getChineseDays(Landroid/content/Context;)[Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseDays:[Ljava/lang/String;

    invoke-static {}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getDefault()Lcom/miui/widget/util/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getChineseMonths(Landroid/content/Context;)[Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const/4 v1, 0x0

    :goto_0
    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    array-length v3, v2

    if-ge v1, v3, :cond_0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    aget-object v4, v3, v1

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v4, Lcom/miui/widget/R$string;->chinese_month:I

    invoke-virtual {v0, v4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    aput-object v2, v3, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    array-length v0, v2

    const/4 v1, 0x1

    add-int/2addr v0, v1

    new-array v0, v0, [Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapYearMonths:[Ljava/lang/String;

    invoke-static {}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getDefault()Lcom/miui/widget/util/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getChineseLeapMonths(Landroid/content/Context;)[Ljava/lang/String;

    move-result-object v0

    aget-object v0, v0, v1

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapMonthMark:Ljava/lang/String;

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseDays:[Ljava/lang/String;

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapYearMonths:[Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapMonthMark:Ljava/lang/String;

    invoke-static {v0, v1, v2, v3}, Lcom/miui/widget/util/DateDataCacheUtils;->saveDateData([Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    :cond_1
    sget-object v0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseDays:[Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseDays:[Ljava/lang/String;

    sget-object v0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseMonths:[Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    sget-object v0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseLeapYearMonths:[Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapYearMonths:[Ljava/lang/String;

    sget-object v0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseLeapMonthMark:Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapMonthMark:Ljava/lang/String;

    :goto_1
    return-void
.end method

.method private isComputerFirstYearAndMonth(Lcom/miui/widget/util/date/Calendar;)Z
    .locals 5

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x6

    goto :goto_0

    :cond_0
    const/4 v0, 0x5

    :goto_0
    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    const/4 v1, 0x2

    goto :goto_1

    :cond_1
    move v1, v2

    :goto_1
    invoke-virtual {p1, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v3

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    if-ne v3, v1, :cond_2

    invoke-virtual {p1, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result p1

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    if-ne p1, v0, :cond_2

    goto :goto_2

    :cond_2
    const/4 v2, 0x0

    :goto_2
    return v2
.end method

.method private notifyDateChanged()V
    .locals 4

    const/4 v0, 0x4

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/DatePicker;->sendAccessibilityEvent(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mOnDateChangedListener:Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getUserSelectedTime()J

    move-result-wide v1

    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    invoke-interface {v0, p0, v1, v2, v3}, Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;->onDateChanged(Lcom/miui/widget/picker/DatePicker;JZ)V

    :cond_0
    return-void
.end method

.method private reorderSpinners()V
    .locals 5

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->removeAllViews()V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDateFormatOrder:[C

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/text/format/DateFormat;->getDateFormatOrder(Landroid/content/Context;)[C

    move-result-object v0

    :cond_0
    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_7

    aget-char v3, v0, v2

    const/16 v4, 0x48

    if-eq v3, v4, :cond_5

    const/16 v4, 0x4d

    if-eq v3, v4, :cond_4

    const/16 v4, 0x64

    if-eq v3, v4, :cond_3

    const/16 v4, 0x6d

    if-eq v3, v4, :cond_2

    const/16 v4, 0x79

    if-ne v3, v4, :cond_1

    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->isShowYear:Z

    if-eqz v3, :cond_6

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0, v3, v1, v2}, Lcom/miui/widget/picker/DatePicker;->setImeOptions(Lcom/miui/widget/picker/NumberPicker;II)V

    goto :goto_1

    :cond_1
    new-instance v0, Ljava/lang/IllegalArgumentException;

    invoke-direct {v0}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw v0

    :cond_2
    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->isShowMinute:Z

    if-eqz v3, :cond_6

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0, v3, v1, v2}, Lcom/miui/widget/picker/DatePicker;->setImeOptions(Lcom/miui/widget/picker/NumberPicker;II)V

    goto :goto_1

    :cond_3
    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->isShowDay:Z

    if-eqz v3, :cond_6

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0, v3, v1, v2}, Lcom/miui/widget/picker/DatePicker;->setImeOptions(Lcom/miui/widget/picker/NumberPicker;II)V

    goto :goto_1

    :cond_4
    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->isShowMonth:Z

    if-eqz v3, :cond_6

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0, v3, v1, v2}, Lcom/miui/widget/picker/DatePicker;->setImeOptions(Lcom/miui/widget/picker/NumberPicker;II)V

    goto :goto_1

    :cond_5
    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->isShowHour:Z

    if-eqz v3, :cond_6

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0, v3, v1, v2}, Lcom/miui/widget/picker/DatePicker;->setImeOptions(Lcom/miui/widget/picker/NumberPicker;II)V

    :cond_6
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_7
    return-void
.end method

.method private resetMonthsDisplayedValues()V
    .locals 5

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0}, Lcom/miui/widget/util/date/Calendar;->getChineseLeapMonth()I

    move-result v0

    if-gez v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    goto :goto_1

    :cond_0
    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapYearMonths:[Ljava/lang/String;

    iput-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    add-int/lit8 v4, v0, 0x1

    invoke-static {v2, v1, v3, v1, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->sChineseMonths:[Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    array-length v3, v1

    sub-int/2addr v3, v0

    invoke-static {v1, v0, v2, v4, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->sChineseLeapMonthMark:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    aget-object v2, v2, v4

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    aput-object v1, v0, v4

    goto :goto_1

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentLocale:Ljava/util/Locale;

    invoke-virtual {v0}, Ljava/util/Locale;->getLanguage()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    const-string v2, "en"

    invoke-virtual {v2, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-static {}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getDefault()Lcom/miui/widget/util/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/CalendarFormatSymbols;->getShortMonths(Landroid/content/Context;)[Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    goto :goto_1

    :cond_2
    const/16 v0, 0xc

    new-array v0, v0, [Ljava/lang/String;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    :goto_0
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    array-length v2, v0

    if-ge v1, v2, :cond_3

    sget-object v2, Lcom/miui/widget/picker/NumberPicker;->TWO_DIGIT_FORMATTER:Lcom/miui/widget/picker/NumberPicker$Formatter;

    add-int/lit8 v3, v1, 0x1

    invoke-interface {v2, v3}, Lcom/miui/widget/picker/NumberPicker$Formatter;->format(I)Ljava/lang/String;

    move-result-object v2

    aput-object v2, v0, v1

    move v1, v3

    goto :goto_0

    :cond_3
    :goto_1
    return-void
.end method

.method private resetSpinnerToInitial(Z)V
    .locals 6

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/16 v0, 0xa

    const/16 v1, 0x9

    const/4 v2, 0x0

    const/4 v3, 0x6

    const/4 v4, 0x5

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v5, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v5, :cond_1

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v5

    goto :goto_0

    :cond_1
    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v4}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v5

    :goto_0
    invoke-virtual {p1, v5}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    iget-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz p1, :cond_2

    goto :goto_1

    :cond_2
    move v3, v4

    :goto_1
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p1, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result p1

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v3

    if-ne p1, v3, :cond_8

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v3, :cond_3

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    goto :goto_2

    :cond_3
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    :goto_2
    invoke-virtual {p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    goto :goto_6

    :cond_4
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v5, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v5, :cond_5

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v5

    goto :goto_3

    :cond_5
    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v4}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v5

    :goto_3
    invoke-virtual {p1, v5}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    const/4 v5, 0x0

    invoke-virtual {p1, v5}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    iget-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz p1, :cond_6

    goto :goto_4

    :cond_6
    move v3, v4

    :goto_4
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p1, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result p1

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v3

    if-ne p1, v3, :cond_8

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v3, :cond_7

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    goto :goto_5

    :cond_7
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    :goto_5
    invoke-virtual {p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    :cond_8
    :goto_6
    return-void
.end method

.method private setAttributes(Landroid/content/res/TypedArray;)V
    .locals 6

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_spinnersShown:I

    const/4 v1, 0x1

    invoke-virtual {p1, v0, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowSpinnersShown:Z

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_showYear:I

    invoke-virtual {p1, v0, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowYear:Z

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_showMonth:I

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowMonth:Z

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_showDay:I

    invoke-virtual {p1, v0, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowDay:Z

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_showHour:I

    invoke-virtual {p1, v0, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowHour:Z

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_showMinute:I

    invoke-virtual {p1, v0, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowMinute:Z

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_startTime:I

    const/4 v2, -0x1

    invoke-virtual {p1, v0, v2}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v0

    int-to-long v3, v0

    iput-wide v3, p0, Lcom/miui/widget/picker/DatePicker;->mStartTime:J

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_maxTime:I

    invoke-virtual {p1, v0, v2}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v0

    int-to-long v3, v0

    iput-wide v3, p0, Lcom/miui/widget/picker/DatePicker;->mMaxTime:J

    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_minTime:I

    invoke-virtual {p1, v0, v2}, Landroid/content/res/TypedArray;->getInteger(II)I

    move-result v0

    int-to-long v2, v0

    iput-wide v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinTime:J

    iget-wide v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinTime:J

    const-wide/16 v4, -0x1

    cmp-long v0, v4, v2

    if-nez v0, :cond_0

    sget-object v0, Lcom/miui/widget/picker/DatePicker;->DEFAULT_MIN_TIME:Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    iput-wide v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinTime:J

    :cond_0
    iget-wide v2, p0, Lcom/miui/widget/picker/DatePicker;->mMaxTime:J

    cmp-long v0, v4, v2

    if-nez v0, :cond_1

    const-wide v2, 0x3bb2b0c6400L

    iput-wide v2, p0, Lcom/miui/widget/picker/DatePicker;->mMaxTime:J

    :cond_1
    sget v0, Lcom/miui/widget/R$styleable;->DatePicker_lunarCalendar:I

    invoke-virtual {p1, v0, v1}, Landroid/content/res/TypedArray;->getBoolean(IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    invoke-virtual {p1}, Landroid/content/res/TypedArray;->recycle()V

    return-void
.end method

.method private setCurrentLocale(Ljava/util/Locale;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentLocale:Ljava/util/Locale;

    invoke-virtual {p1, v0}, Ljava/util/Locale;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentLocale:Ljava/util/Locale;

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    const/4 v0, 0x5

    invoke-virtual {p1, v0}, Lcom/miui/widget/util/date/Calendar;->getActualMaximum(I)I

    move-result p1

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/miui/widget/picker/DatePicker;->mNumberOfMonths:I

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->resetMonthsDisplayedValues()V

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateFormatters()V

    return-void
.end method

.method private setDate(J)V
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, p1, p2}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p1, p2}, Lcom/miui/widget/util/date/Calendar;->before(Lcom/miui/widget/util/date/Calendar;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p1, v0, v1}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p1, p2}, Lcom/miui/widget/util/date/Calendar;->after(Lcom/miui/widget/util/date/Calendar;)Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p1, v0, v1}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    :cond_1
    :goto_0
    return-void
.end method

.method private setImeOptions(Lcom/miui/widget/picker/NumberPicker;II)V
    .locals 0

    add-int/lit8 p2, p2, -0x1

    if-ge p3, p2, :cond_0

    const/4 p2, 0x5

    goto :goto_0

    :cond_0
    const/4 p2, 0x6

    :goto_0
    sget p3, Lcom/miui/widget/R$id;->numberpicker_input:I

    invoke-virtual {p1, p3}, Lcom/miui/widget/picker/NumberPicker;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setImeOptions(I)V

    return-void
.end method

.method private setSpinnerLableFromMode(Lcom/miui/widget/picker/NumberPicker;Z)V
    .locals 2

    if-eqz p2, :cond_0

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setLabel(Ljava/lang/String;)V

    return-void

    :cond_0
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker;->getId()I

    move-result v0

    sget v1, Lcom/miui/widget/R$id;->year:I

    if-ne v0, v1, :cond_1

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/widget/R$string;->date_picker_label_year:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker;->getId()I

    move-result v0

    sget v1, Lcom/miui/widget/R$id;->month:I

    if-ne v0, v1, :cond_2

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/widget/R$string;->date_picker_label_month:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_2
    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker;->getId()I

    move-result v0

    sget v1, Lcom/miui/widget/R$id;->day:I

    if-ne v0, v1, :cond_3

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/widget/R$string;->date_picker_label_day:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_3
    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker;->getId()I

    move-result v0

    sget v1, Lcom/miui/widget/R$id;->hour:I

    if-ne v0, v1, :cond_4

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/widget/R$string;->time_picker_label_hour:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_4
    invoke-virtual {p1}, Lcom/miui/widget/picker/NumberPicker;->getId()I

    move-result v0

    sget v1, Lcom/miui/widget/R$id;->minute:I

    if-ne v0, v1, :cond_5

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/widget/R$string;->time_picker_label_minute:I

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_5
    :goto_0
    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setLabel(Ljava/lang/String;)V

    return-void
.end method

.method private updateDaySpinner()V
    .locals 6

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isLunarMode()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/miui/widget/picker/DatePicker;->setSpinnerLableFromMode(Lcom/miui/widget/picker/NumberPicker;Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    const/16 v1, 0xa

    const/16 v2, 0x9

    if-eqz v0, :cond_1

    move v0, v1

    goto :goto_0

    :cond_1
    move v0, v2

    :goto_0
    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p0, v3}, Lcom/miui/widget/picker/DatePicker;->isComputerFirstYearAndMonth(Lcom/miui/widget/util/date/Calendar;)Z

    move-result v3

    const/4 v4, 0x1

    if-eqz v3, :cond_2

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v5

    invoke-virtual {v3, v5}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    goto :goto_1

    :cond_2
    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v3, v4}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    :goto_1
    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v0}, Lcom/miui/widget/util/date/Calendar;->getActualMaximum(I)I

    move-result v0

    invoke-virtual {v3, v0}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    goto :goto_2

    :cond_3
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v2}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    :goto_2
    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v1, v0}, Lcom/miui/widget/picker/NumberPicker;->setValue(I)V

    return-void
.end method

.method private updateFormatters()V
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_1

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    sget-object v1, Lcom/miui/widget/picker/NumberPicker;->TWO_DIGIT_FORMATTER:Lcom/miui/widget/picker/NumberPicker$Formatter;

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setFormatter(Lcom/miui/widget/picker/NumberPicker$Formatter;)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    new-instance v1, Lcom/miui/widget/picker/NumberPicker$NumberFormatter;

    invoke-direct {v1}, Lcom/miui/widget/picker/NumberPicker$NumberFormatter;-><init>()V

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setFormatter(Lcom/miui/widget/picker/NumberPicker$Formatter;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private updateHourSpinner()V
    .locals 7

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isLunarMode()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/miui/widget/picker/DatePicker;->setSpinnerLableFromMode(Lcom/miui/widget/picker/NumberPicker;Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    const/4 v1, 0x1

    const/16 v2, 0xb

    const/16 v3, 0x12

    const/4 v4, 0x0

    if-eqz v0, :cond_2

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p0, v5, v0}, Lcom/miui/widget/picker/DatePicker;->computeDayCount(Lcom/miui/widget/util/date/Calendar;Lcom/miui/widget/util/date/Calendar;)I

    move-result v0

    if-nez v0, :cond_2

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->is24Hour:Z

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mJavaCalendar:Ljava/util/Calendar;

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v5

    invoke-virtual {v0, v5, v6}, Ljava/util/Calendar;->setTimeInMillis(J)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mJavaCalendar:Ljava/util/Calendar;

    invoke-virtual {v0, v2}, Ljava/util/Calendar;->get(I)I

    move-result v0

    :goto_0
    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v5, v0}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v1

    goto :goto_1

    :cond_2
    move v0, v4

    :goto_1
    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    if-eqz v5, :cond_4

    iget-object v6, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p0, v6, v5}, Lcom/miui/widget/picker/DatePicker;->computeDayCount(Lcom/miui/widget/util/date/Calendar;Lcom/miui/widget/util/date/Calendar;)I

    move-result v5

    if-nez v5, :cond_4

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->is24Hour:Z

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mJavaCalendar:Ljava/util/Calendar;

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v5

    invoke-virtual {v0, v5, v6}, Ljava/util/Calendar;->setTimeInMillis(J)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    goto :goto_2

    :cond_3
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mJavaCalendar:Ljava/util/Calendar;

    invoke-virtual {v0, v2}, Ljava/util/Calendar;->get(I)I

    move-result v0

    :goto_2
    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v5, v0}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v1

    :cond_4
    if-nez v0, :cond_6

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v4, p0, Lcom/miui/widget/picker/DatePicker;->is24Hour:Z

    if-eqz v4, :cond_5

    const/16 v2, 0x17

    :cond_5
    invoke-virtual {v0, v2}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    :cond_6
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v3}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v1, v0}, Lcom/miui/widget/picker/NumberPicker;->setValue(I)V

    return-void
.end method

.method private updateMinuteSpinner()V
    .locals 7

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isLunarMode()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/miui/widget/picker/DatePicker;->setSpinnerLableFromMode(Lcom/miui/widget/picker/NumberPicker;Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    const/16 v1, 0x14

    const/16 v2, 0x12

    const/4 v3, 0x1

    const/4 v4, 0x0

    if-eqz v0, :cond_1

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p0, v5, v0}, Lcom/miui/widget/picker/DatePicker;->computeDayCount(Lcom/miui/widget/util/date/Calendar;Lcom/miui/widget/util/date/Calendar;)I

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v2}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v2}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v5

    if-ne v0, v5, :cond_1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v5, v4}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget v6, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    div-int/2addr v0, v6

    invoke-virtual {v5, v0}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v3

    goto :goto_0

    :cond_1
    move v0, v4

    :goto_0
    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    if-eqz v5, :cond_2

    iget-object v6, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-direct {p0, v6, v5}, Lcom/miui/widget/picker/DatePicker;->computeDayCount(Lcom/miui/widget/util/date/Calendar;Lcom/miui/widget/util/date/Calendar;)I

    move-result v5

    if-nez v5, :cond_2

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v2}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v5

    iget-object v6, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v6, v2}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v2

    if-ne v5, v2, :cond_2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    div-int/2addr v0, v5

    invoke-virtual {v2, v0}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    move v0, v3

    :cond_2
    if-nez v0, :cond_3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    const/16 v5, 0x3c

    div-int v2, v5, v2

    sub-int/2addr v2, v3

    invoke-direct {p0, v0, v4, v2}, Lcom/miui/widget/picker/DatePicker;->checkDisplayeValid(Lcom/miui/widget/picker/NumberPicker;II)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    div-int/2addr v5, v2

    sub-int/2addr v5, v3

    invoke-virtual {v0, v5}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v3}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    :cond_3
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker;->getMaxValue()I

    move-result v0

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v2}, Lcom/miui/widget/picker/NumberPicker;->getMinValue()I

    move-result v2

    sub-int/2addr v0, v2

    add-int/2addr v0, v3

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteDisplayValues:[Ljava/lang/String;

    if-eqz v2, :cond_4

    array-length v2, v2

    if-eq v2, v0, :cond_6

    :cond_4
    new-array v2, v0, [Ljava/lang/String;

    iput-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteDisplayValues:[Ljava/lang/String;

    :goto_1
    if-ge v4, v0, :cond_5

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteDisplayValues:[Ljava/lang/String;

    sget-object v3, Lcom/miui/widget/picker/NumberPicker;->TWO_DIGIT_FORMATTER:Lcom/miui/widget/picker/NumberPicker$Formatter;

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v5}, Lcom/miui/widget/picker/NumberPicker;->getMinValue()I

    move-result v5

    add-int/2addr v5, v4

    iget v6, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    mul-int/2addr v5, v6

    invoke-interface {v3, v5}, Lcom/miui/widget/picker/NumberPicker$Formatter;->format(I)Ljava/lang/String;

    move-result-object v3

    aput-object v3, v2, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    :cond_5
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteDisplayValues:[Ljava/lang/String;

    invoke-virtual {v0, v2}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    :cond_6
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget v1, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteInterval:I

    div-int/2addr v0, v1

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v1, v0}, Lcom/miui/widget/picker/NumberPicker;->setValue(I)V

    return-void
.end method

.method private updateMonthSpinner()V
    .locals 7

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isLunarMode()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/miui/widget/picker/DatePicker;->setSpinnerLableFromMode(Lcom/miui/widget/picker/NumberPicker;Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isLunarMode()Z

    move-result v0

    const/4 v3, 0x1

    if-eqz v0, :cond_1

    const/4 v0, 0x2

    goto :goto_0

    :cond_1
    move v0, v3

    :goto_0
    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v4

    iget-object v5, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v5, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    const/4 v5, 0x5

    const/4 v6, 0x6

    if-ne v4, v0, :cond_5

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v4, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v4, :cond_2

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v6}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v4

    goto :goto_1

    :cond_2
    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v5}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v4

    :goto_1
    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v2}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v0, :cond_3

    move v0, v6

    goto :goto_2

    :cond_3
    move v0, v5

    :goto_2
    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    if-ne v1, v0, :cond_7

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v1, :cond_4

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    const/16 v4, 0xa

    invoke-virtual {v1, v4}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    goto :goto_3

    :cond_4
    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    const/16 v4, 0x9

    invoke-virtual {v1, v4}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    :goto_3
    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v2}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    goto :goto_4

    :cond_5
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    const/16 v4, 0xb

    if-eqz v1, :cond_6

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1}, Lcom/miui/widget/util/date/Calendar;->getChineseLeapMonth()I

    move-result v1

    if-ltz v1, :cond_6

    const/16 v4, 0xc

    :cond_6
    invoke-virtual {v0, v4}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v0, v3}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    :cond_7
    :goto_4
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0}, Lcom/miui/widget/util/date/Calendar;->getChineseLeapMonth()I

    move-result v0

    if-ltz v0, :cond_9

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1}, Lcom/miui/widget/util/date/Calendar;->isChineseLeapMonth()Z

    move-result v1

    if-nez v1, :cond_8

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v6}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    if-le v1, v0, :cond_9

    :cond_8
    move v2, v3

    :cond_9
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v1, :cond_b

    if-eqz v2, :cond_a

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v6}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    add-int/2addr v1, v3

    goto :goto_5

    :cond_a
    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v6}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    goto :goto_5

    :cond_b
    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v5}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    :goto_5
    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setValue(I)V

    return-void
.end method

.method private updateSpinners()V
    .locals 4

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowDay:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->checkIfTheDateIsOutOfMinDateBounds()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-direct {p0, v1}, Lcom/miui/widget/picker/DatePicker;->resetSpinnerToInitial(Z)V

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateDaySpinner()V

    :goto_0
    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->checkIfTheDateIsOutOfMaxDateBounds()Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/DatePicker;->resetSpinnerToInitial(Z)V

    goto :goto_1

    :cond_1
    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateDaySpinner()V

    :cond_2
    :goto_1
    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowMonth:Z

    if-eqz v0, :cond_3

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateMonthSpinner()V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v2}, Lcom/miui/widget/picker/NumberPicker;->getMinValue()I

    move-result v2

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    array-length v3, v3

    invoke-static {v0, v2, v3}, Ljava/util/Arrays;->copyOfRange([Ljava/lang/Object;II)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v2, v0}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->sChineseDays:[Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v2}, Lcom/miui/widget/picker/NumberPicker;->getMinValue()I

    move-result v2

    sub-int/2addr v2, v1

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->sChineseDays:[Ljava/lang/String;

    array-length v1, v1

    invoke-static {v0, v2, v1}, Ljava/util/Arrays;->copyOfRange([Ljava/lang/Object;II)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {v1, v0}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    :cond_3
    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowHour:Z

    if-eqz v0, :cond_4

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateHourSpinner()V

    :cond_4
    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowMinute:Z

    if-eqz v0, :cond_5

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateMinuteSpinner()V

    :cond_5
    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateYearSpinner()V

    return-void
.end method

.method private updateYearSpinner()V
    .locals 5

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isLunarMode()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/miui/widget/picker/DatePicker;->setSpinnerLableFromMode(Lcom/miui/widget/picker/NumberPicker;Z)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isLunarMode()Z

    move-result v0

    const/4 v1, 0x2

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    move v0, v1

    goto :goto_0

    :cond_1
    move v0, v2

    :goto_0
    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v4

    invoke-virtual {v3, v4}, Lcom/miui/widget/picker/NumberPicker;->setMinValue(I)V

    iget-object v3, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v4, v0}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    invoke-virtual {v3, v0}, Lcom/miui/widget/picker/NumberPicker;->setMaxValue(I)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    const/4 v3, 0x0

    invoke-virtual {v0, v3}, Lcom/miui/widget/picker/NumberPicker;->setWrapSelectorWheel(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v3, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v3, :cond_2

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v2, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    goto :goto_1

    :cond_2
    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1, v2}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    :goto_1
    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->setValue(I)V

    return-void
.end method


# virtual methods
.method public dispatchPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)Z
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    const/4 p1, 0x1

    return p1
.end method

.method protected dispatchRestoreInstanceState(Landroid/util/SparseArray;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/util/SparseArray<",
            "Landroid/os/Parcelable;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->dispatchThawSelfOnly(Landroid/util/SparseArray;)V

    return-void
.end method

.method public getCurrentSelectDate()J
    .locals 9

    new-instance v8, Lcom/miui/widget/util/date/Calendar;

    invoke-direct {v8}, Lcom/miui/widget/util/date/Calendar;-><init>()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getYear()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getMonth()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getDayOfMonth()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getHourOfDay()I

    move-result v4

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getMinuteOfHour()I

    move-result v5

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v0, v8

    invoke-virtual/range {v0 .. v7}, Lcom/miui/widget/util/date/Calendar;->set(IIIIIII)Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v8}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    return-wide v0
.end method

.method public getDayOfMonth()I
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v1, :cond_0

    const/16 v1, 0xa

    goto :goto_0

    :cond_0
    const/16 v1, 0x9

    :goto_0
    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    return v0
.end method

.method public getDaySpinnerWidth()F
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_0

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->isShowDay:Z

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getHourOfDay()I
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v1, :cond_0

    const/16 v1, 0x13

    goto :goto_0

    :cond_0
    const/16 v1, 0x12

    :goto_0
    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    return v0
.end method

.method public getHourSpinnerWidth()F
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_0

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->isShowHour:Z

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method protected getLayoutId()I
    .locals 1

    sget v0, Lcom/miui/widget/R$layout;->date_pickerview:I

    return v0
.end method

.method public getMaxDate()J
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    return-wide v0
.end method

.method public getMinDate()J
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    return-wide v0
.end method

.method public getMinuteOfHour()I
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    const/16 v1, 0x14

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    return v0
.end method

.method public getMinuteSpinnerWidth()F
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_0

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->isShowMinute:Z

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getMonth()I
    .locals 2

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0}, Lcom/miui/widget/util/date/Calendar;->isChineseLeapMonth()Z

    move-result v0

    const/4 v1, 0x6

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    add-int/lit8 v0, v0, 0xc

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    :goto_0
    return v0
.end method

.method public getMonthSpinnerWidth()F
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_0

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->isShowMonth:Z

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getSpinnersShown()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->isShown()Z

    move-result v0

    return v0
.end method

.method public getUserSelectedTime()J
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    return-wide v0

    :cond_0
    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public getYear()I
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eqz v1, :cond_0

    const/4 v1, 0x2

    goto :goto_0

    :cond_0
    const/4 v1, 0x1

    :goto_0
    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    return v0
.end method

.method public getYearSpinnerWidth()F
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_0

    iget-boolean v1, p0, Lcom/miui/widget/picker/DatePicker;->isShowYear:Z

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public init(IIILcom/miui/widget/picker/DatePicker$OnDateChangedListener;)V
    .locals 17

    new-instance v8, Lcom/miui/widget/util/date/Calendar;

    invoke-direct {v8}, Lcom/miui/widget/util/date/Calendar;-><init>()V

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v0, v8

    move/from16 v1, p1

    move/from16 v2, p2

    move/from16 v3, p3

    invoke-virtual/range {v0 .. v7}, Lcom/miui/widget/util/date/Calendar;->set(IIIIIII)Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v8}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v10

    sget-object v0, Lcom/miui/widget/picker/DatePicker;->DEFAULT_MIN_TIME:Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v12

    const-wide v14, 0x3bb2b0c6400L

    move-object/from16 v9, p0

    move-object/from16 v16, p4

    invoke-virtual/range {v9 .. v16}, Lcom/miui/widget/picker/DatePicker;->init(JJJLcom/miui/widget/picker/DatePicker$OnDateChangedListener;)V

    return-void
.end method

.method public init(JJJLcom/miui/widget/picker/DatePicker$OnDateChangedListener;)V
    .locals 10

    move-object v6, p0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowYear()Z

    move-result v0

    const/4 v7, 0x0

    const/4 v8, 0x1

    if-eqz v0, :cond_0

    move v0, v8

    goto :goto_0

    :cond_0
    move v0, v7

    :goto_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowMonth()Z

    move-result v1

    if-eqz v1, :cond_1

    add-int/lit8 v0, v0, 0x1

    :cond_1
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowDay()Z

    move-result v1

    if-eqz v1, :cond_2

    add-int/lit8 v0, v0, 0x1

    :cond_2
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowHour()Z

    move-result v1

    if-eqz v1, :cond_3

    add-int/lit8 v0, v0, 0x1

    :cond_3
    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowMinute()Z

    move-result v1

    if-eqz v1, :cond_4

    add-int/lit8 v0, v0, 0x1

    :cond_4
    move v9, v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowYear()Z

    move-result v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowMonth()Z

    move-result v2

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowDay()Z

    move-result v3

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowHour()Z

    move-result v4

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->isShowMinute()Z

    move-result v5

    move-object v0, p0

    invoke-virtual/range {v0 .. v5}, Lcom/miui/widget/picker/DatePicker;->initView(ZZZZZ)V

    iget-object v0, v6, Lcom/miui/widget/picker/DatePicker;->mPickersContainer:Landroid/widget/LinearLayout;

    int-to-float v1, v9

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setWeightSum(F)V

    iget-object v0, v6, Lcom/miui/widget/picker/DatePicker;->mPickersContainer:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->requestLayout()V

    const-wide/16 v0, -0x1

    cmp-long v2, v0, p3

    if-nez v2, :cond_5

    iget-wide v2, v6, Lcom/miui/widget/picker/DatePicker;->mMinTime:J

    goto :goto_1

    :cond_5
    move-wide v2, p3

    :goto_1
    invoke-virtual {p0, v7, v2, v3}, Lcom/miui/widget/picker/DatePicker;->setMinDate(ZJ)V

    cmp-long v2, v0, p5

    if-nez v2, :cond_6

    iget-wide v2, v6, Lcom/miui/widget/picker/DatePicker;->mMaxTime:J

    goto :goto_2

    :cond_6
    move-wide v2, p5

    :goto_2
    invoke-virtual {p0, v7, v2, v3}, Lcom/miui/widget/picker/DatePicker;->setMaxDate(ZJ)V

    cmp-long v0, v0, p1

    if-nez v0, :cond_7

    iget-wide v0, v6, Lcom/miui/widget/picker/DatePicker;->mStartTime:J

    goto :goto_3

    :cond_7
    move-wide v0, p1

    :goto_3
    invoke-direct {p0, v0, v1}, Lcom/miui/widget/picker/DatePicker;->setDate(J)V

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/widget/picker/DatePicker;->setCurrentLocale(Ljava/util/Locale;)V

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateSpinners()V

    move-object/from16 v0, p7

    iput-object v0, v6, Lcom/miui/widget/picker/DatePicker;->mOnDateChangedListener:Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;

    sget-object v0, Lcom/miui/widget/picker/DatePicker;->DEFALUT_DATE_FORMAT:[C

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/DatePicker;->setDateFormatOrder([C)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getImportantForAccessibility()I

    move-result v0

    if-nez v0, :cond_8

    invoke-virtual {p0, v8}, Lcom/miui/widget/picker/DatePicker;->setImportantForAccessibility(I)V

    :cond_8
    return-void
.end method

.method protected initView(ZZZZZ)V
    .locals 6

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mRootView:Landroid/view/View;

    const/4 v1, 0x1

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v2, "layout_inflater"

    invoke-virtual {v0, v2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/LayoutInflater;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getLayoutId()I

    move-result v2

    invoke-virtual {v0, v2, p0, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mRootView:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mRootView:Landroid/view/View;

    sget v2, Lcom/miui/widget/R$id;->pickers:I

    invoke-virtual {v0, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mPickersContainer:Landroid/widget/LinearLayout;

    new-instance v0, Lcom/miui/widget/picker/DatePicker$1;

    invoke-direct {v0, p0}, Lcom/miui/widget/picker/DatePicker$1;-><init>(Lcom/miui/widget/picker/DatePicker;)V

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mOnPickerCheckChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    sget v0, Lcom/miui/widget/R$id;->pickers:I

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    :cond_0
    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowSpinnersShown:Z

    if-eqz v0, :cond_d

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mPickers:[Lcom/miui/widget/picker/NumberPicker;

    if-nez v0, :cond_1

    const/4 v0, 0x5

    new-array v0, v0, [Lcom/miui/widget/picker/NumberPicker;

    iput-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mPickers:[Lcom/miui/widget/picker/NumberPicker;

    :cond_1
    const-wide/16 v2, 0x64

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez p1, :cond_2

    sget p1, Lcom/miui/widget/R$id;->year:I

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/view/ViewStub;

    invoke-virtual {p1}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/widget/picker/NumberPicker;

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2, v3}, Lcom/miui/widget/picker/NumberPicker;->setOnLongPressUpdateInterval(J)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mOnPickerCheckChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    invoke-virtual {p1, v0}, Lcom/miui/widget/picker/NumberPicker;->setOnValueChangedListener(Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mPickers:[Lcom/miui/widget/picker/NumberPicker;

    const/4 v0, 0x0

    iget-object v4, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    aput-object v4, p1, v0

    :cond_2
    if-eqz p2, :cond_3

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez p1, :cond_3

    sget p1, Lcom/miui/widget/R$id;->month:I

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/view/ViewStub;

    invoke-virtual {p1}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/widget/picker/NumberPicker;

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mShortMonths:[Ljava/lang/String;

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setDisplayedValues([Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    const-wide/16 v4, 0xc8

    invoke-virtual {p1, v4, v5}, Lcom/miui/widget/picker/NumberPicker;->setOnLongPressUpdateInterval(J)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mOnPickerCheckChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setOnValueChangedListener(Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mPickers:[Lcom/miui/widget/picker/NumberPicker;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    aput-object p2, p1, v1

    :cond_3
    if-eqz p3, :cond_4

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez p1, :cond_4

    sget p1, Lcom/miui/widget/R$id;->day:I

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/view/ViewStub;

    invoke-virtual {p1}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/widget/picker/NumberPicker;

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2, v3}, Lcom/miui/widget/picker/NumberPicker;->setOnLongPressUpdateInterval(J)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mOnPickerCheckChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setOnValueChangedListener(Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mPickers:[Lcom/miui/widget/picker/NumberPicker;

    const/4 p2, 0x2

    iget-object p3, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    aput-object p3, p1, p2

    :cond_4
    if-eqz p4, :cond_5

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez p1, :cond_5

    sget p1, Lcom/miui/widget/R$id;->hour:I

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/view/ViewStub;

    invoke-virtual {p1}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/widget/picker/NumberPicker;

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2, v3}, Lcom/miui/widget/picker/NumberPicker;->setOnLongPressUpdateInterval(J)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mOnPickerCheckChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setOnValueChangedListener(Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mPickers:[Lcom/miui/widget/picker/NumberPicker;

    const/4 p2, 0x3

    iget-object p3, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    aput-object p3, p1, p2

    :cond_5
    if-eqz p5, :cond_6

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-nez p1, :cond_6

    sget p1, Lcom/miui/widget/R$id;->minute:I

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/view/ViewStub;

    invoke-virtual {p1}, Landroid/view/ViewStub;->inflate()Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/miui/widget/picker/NumberPicker;

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    invoke-virtual {p1, v2, v3}, Lcom/miui/widget/picker/NumberPicker;->setOnLongPressUpdateInterval(J)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mOnPickerCheckChangeListener:Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;

    invoke-virtual {p1, p2}, Lcom/miui/widget/picker/NumberPicker;->setOnValueChangedListener(Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;)V

    :cond_6
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz p1, :cond_7

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DatePicker;->applyPickerStyle(Lcom/miui/widget/picker/NumberPicker;)V

    :cond_7
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz p1, :cond_8

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DatePicker;->applyPickerStyle(Lcom/miui/widget/picker/NumberPicker;)V

    :cond_8
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz p1, :cond_9

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DatePicker;->applyPickerStyle(Lcom/miui/widget/picker/NumberPicker;)V

    :cond_9
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mHourSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz p1, :cond_a

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DatePicker;->applyPickerStyle(Lcom/miui/widget/picker/NumberPicker;)V

    :cond_a
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mMinuteSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz p1, :cond_b

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DatePicker;->applyPickerStyle(Lcom/miui/widget/picker/NumberPicker;)V

    :cond_b
    iget-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->isShowSpinnersShown:Z

    invoke-virtual {p0, p1}, Lcom/miui/widget/picker/DatePicker;->setSpinnersShown(Z)V

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateFormatters()V

    const-wide/16 p1, -0x1

    iget-wide p3, p0, Lcom/miui/widget/picker/DatePicker;->mStartTime:J

    cmp-long p1, p1, p3

    if-eqz p1, :cond_c

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p1, p3, p4}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    goto :goto_0

    :cond_c
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    iput-wide p1, p0, Lcom/miui/widget/picker/DatePicker;->mStartTime:J

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-wide p2, p0, Lcom/miui/widget/picker/DatePicker;->mStartTime:J

    invoke-virtual {p1, p2, p3}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    :cond_d
    :goto_0
    return-void
.end method

.method public isEnabled()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsEnabled:Z

    return v0
.end method

.method public isInit()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isInit:Z

    return v0
.end method

.method public isLunarMode()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    return v0
.end method

.method public isShowDay()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowDay:Z

    return v0
.end method

.method public isShowHour()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowHour:Z

    return v0
.end method

.method public isShowMinute()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowMinute:Z

    return v0
.end method

.method public isShowMonth()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowMonth:Z

    return v0
.end method

.method public isShowYear()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->isShowYear:Z

    return v0
.end method

.method protected onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 0

    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    iget-object p1, p1, Landroid/content/res/Configuration;->locale:Ljava/util/Locale;

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DatePicker;->setCurrentLocale(Ljava/util/Locale;)V

    return-void
.end method

.method public onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    const-class v0, Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onInitializeAccessibilityNodeInfo(Landroid/view/accessibility/AccessibilityNodeInfo;)V

    const-class v0, Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V
    .locals 4

    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/DatePicker;->getContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v1}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    const/16 v3, 0x380

    invoke-static {v0, v1, v2, v3}, Lcom/miui/widget/util/date/DateUtils;->formatDateTime(Landroid/content/Context;JI)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Landroid/view/accessibility/AccessibilityEvent;->getText()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public setDateFormatOrder([C)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker;->mDateFormatOrder:[C

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->reorderSpinners()V

    return-void
.end method

.method public setEnable24Hour(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->is24Hour:Z

    return-void
.end method

.method public setEnabled(Z)V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsEnabled:Z

    if-ne v0, p1, :cond_0

    return-void

    :cond_0
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->setEnabled(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mDaySpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_1

    invoke-virtual {v0, p1}, Lcom/miui/widget/picker/NumberPicker;->setEnabled(Z)V

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMonthSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_2

    invoke-virtual {v0, p1}, Lcom/miui/widget/picker/NumberPicker;->setEnabled(Z)V

    :cond_2
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mYearSpinner:Lcom/miui/widget/picker/NumberPicker;

    if-eqz v0, :cond_3

    invoke-virtual {v0, p1}, Lcom/miui/widget/picker/NumberPicker;->setEnabled(Z)V

    :cond_3
    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->mIsEnabled:Z

    return-void
.end method

.method public setIsShowLabel(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->isShowLabel:Z

    return-void
.end method

.method public setLunarMode(Z)V
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    if-eq p1, v0, :cond_0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->mIsLunarMode:Z

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->resetMonthsDisplayedValues()V

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateSpinners()V

    :cond_0
    return-void
.end method

.method public setMaxDate(J)V
    .locals 1

    const/4 v0, 0x1

    invoke-virtual {p0, v0, p1, p2}, Lcom/miui/widget/picker/DatePicker;->setMaxDate(ZJ)V

    return-void
.end method

.method public setMaxDate(ZJ)V
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, p2, p3}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v2, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    const/16 v1, 0xc

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v2, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    if-eq v0, v1, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, p2, p3}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p3, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2, p3}, Lcom/miui/widget/util/date/Calendar;->after(Lcom/miui/widget/util/date/Calendar;)Z

    move-result p2

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p3, p0, Lcom/miui/widget/picker/DatePicker;->mMaxDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p3}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p2, v0, v1}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    :cond_1
    if-eqz p1, :cond_2

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateSpinners()V

    :cond_2
    return-void
.end method

.method public setMinDate(J)V
    .locals 1

    const/4 v0, 0x1

    invoke-virtual {p0, v0, p1, p2}, Lcom/miui/widget/picker/DatePicker;->setMinDate(ZJ)V

    return-void
.end method

.method public setMinDate(ZJ)V
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, p2, p3}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v2, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mTempDate:Lcom/miui/widget/util/date/Calendar;

    const/16 v1, 0xc

    invoke-virtual {v0, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v0

    iget-object v2, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v2, v1}, Lcom/miui/widget/util/date/Calendar;->get(I)I

    move-result v1

    if-eq v0, v1, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {v0, p2, p3}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p3, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p2, p3}, Lcom/miui/widget/util/date/Calendar;->before(Lcom/miui/widget/util/date/Calendar;)Z

    move-result p2

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker;->mCurrentDate:Lcom/miui/widget/util/date/Calendar;

    iget-object p3, p0, Lcom/miui/widget/picker/DatePicker;->mMinDate:Lcom/miui/widget/util/date/Calendar;

    invoke-virtual {p3}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-virtual {p2, v0, v1}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    :cond_1
    if-eqz p1, :cond_2

    invoke-direct {p0}, Lcom/miui/widget/picker/DatePicker;->updateSpinners()V

    :cond_2
    return-void
.end method

.method public setPickerItemTextStyle(III)V
    .locals 0

    iput p2, p0, Lcom/miui/widget/picker/DatePicker;->mItemHilightTextSize:I

    iput p3, p0, Lcom/miui/widget/picker/DatePicker;->mItemLabelSize:I

    iput p1, p0, Lcom/miui/widget/picker/DatePicker;->mItemTextSize:I

    return-void
.end method

.method public setShowDay(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->isShowDay:Z

    return-void
.end method

.method public setShowHour(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->isShowHour:Z

    return-void
.end method

.method public setShowMinute(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->isShowMinute:Z

    return-void
.end method

.method public setShowMonth(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DatePicker;->isShowMonth:Z

    return-void
.end method

.method public setSpinnersShown(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker;->mSpinners:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    const/16 p1, 0x8

    :goto_0
    invoke-virtual {v0, p1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    :cond_1
    return-void
.end method
