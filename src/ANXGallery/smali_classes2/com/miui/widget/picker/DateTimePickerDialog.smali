.class public Lcom/miui/widget/picker/DateTimePickerDialog;
.super Landroid/app/AlertDialog;
.source "DateTimePickerDialog.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;,
        Lcom/miui/widget/picker/DateTimePickerDialog$Builder;,
        Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;
    }
.end annotation


# instance fields
.field private is24Hour:Z

.field private isShowDay:Z

.field private isShowHour:Z

.field private isShowLabel:Z

.field private isShowMinute:Z

.field private isShowMonth:Z

.field private mCallBack:Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

.field private mDatePicker:Lcom/miui/widget/picker/DatePicker;

.field private mItemHilightTextSize:I

.field private mItemLabelSize:I

.field private mItemTextSize:I

.field private mMaxTime:J

.field private mMinTime:J

.field private mNegativeColor:I

.field private mOnDateChangedListener:Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;

.field private mOnShowListener:Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;

.field private mPositiveColor:I

.field private mRootView:Landroid/view/View;

.field private mStartTime:J

.field private mTitleNeedsUpdate:Z

.field private mTitleView:Landroid/view/View;


# direct methods
.method protected constructor <init>(Landroid/content/Context;Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;)V
    .locals 9

    const-wide/16 v3, -0x1

    const-wide/16 v5, -0x1

    const-wide/16 v7, -0x1

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    invoke-direct/range {v0 .. v8}, Lcom/miui/widget/picker/DateTimePickerDialog;-><init>(Landroid/content/Context;Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;JJJ)V

    return-void
.end method

.method protected constructor <init>(Landroid/content/Context;Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;JJJ)V
    .locals 0

    const/4 p5, 0x0

    invoke-direct {p0, p1, p5}, Landroid/app/AlertDialog;-><init>(Landroid/content/Context;I)V

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mTitleNeedsUpdate:Z

    new-instance p1, Lcom/miui/widget/picker/DateTimePickerDialog$1;

    invoke-direct {p1, p0}, Lcom/miui/widget/picker/DateTimePickerDialog$1;-><init>(Lcom/miui/widget/picker/DateTimePickerDialog;)V

    iput-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mOnDateChangedListener:Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;

    iput-object p2, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mCallBack:Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getContext()Landroid/content/Context;

    move-result-object p1

    const-string p2, "layout_inflater"

    invoke-virtual {p1, p2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Landroid/view/LayoutInflater;

    sget p6, Lcom/miui/widget/R$layout;->date_pickerview_dialog:I

    const/4 p7, 0x0

    invoke-virtual {p2, p6, p7}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mRootView:Landroid/view/View;

    iget-object p2, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mRootView:Landroid/view/View;

    sget p6, Lcom/miui/widget/R$id;->datePicker:I

    invoke-virtual {p2, p6}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Lcom/miui/widget/picker/DatePicker;

    iput-object p2, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    iget-object p2, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mRootView:Landroid/view/View;

    invoke-virtual {p0, p2}, Lcom/miui/widget/picker/DateTimePickerDialog;->setView(Landroid/view/View;)V

    const p2, 0x104000a

    invoke-virtual {p1, p2}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object p1

    new-instance p2, Lcom/miui/widget/picker/DateTimePickerDialog$2;

    invoke-direct {p2, p0}, Lcom/miui/widget/picker/DateTimePickerDialog$2;-><init>(Lcom/miui/widget/picker/DateTimePickerDialog;)V

    const/4 p6, -0x1

    invoke-virtual {p0, p6, p1, p2}, Lcom/miui/widget/picker/DateTimePickerDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getContext()Landroid/content/Context;

    move-result-object p1

    const/high16 p2, 0x1040000

    invoke-virtual {p1, p2}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object p1

    check-cast p7, Landroid/content/DialogInterface$OnClickListener;

    const/4 p2, -0x2

    invoke-virtual {p0, p2, p1, p7}, Lcom/miui/widget/picker/DateTimePickerDialog;->setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V

    new-instance p1, Lcom/miui/widget/picker/DateTimePickerDialog$3;

    invoke-direct {p1, p0}, Lcom/miui/widget/picker/DateTimePickerDialog$3;-><init>(Lcom/miui/widget/picker/DateTimePickerDialog;)V

    invoke-super {p0, p1}, Landroid/app/AlertDialog;->setOnShowListener(Landroid/content/DialogInterface$OnShowListener;)V

    invoke-virtual {p0, p5}, Lcom/miui/widget/picker/DateTimePickerDialog;->setIcon(I)V

    const-wide/16 p1, -0x1

    cmp-long p1, p1, p3

    if-nez p1, :cond_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    iput-wide p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mStartTime:J

    :cond_0
    return-void
.end method

.method static synthetic access$000(Lcom/miui/widget/picker/DateTimePickerDialog;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mTitleNeedsUpdate:Z

    return p0
.end method

.method static synthetic access$100(Lcom/miui/widget/picker/DateTimePickerDialog;J)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/widget/picker/DateTimePickerDialog;->updateTitle(J)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/widget/picker/DateTimePickerDialog;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->tryNotifyDateSet()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/widget/picker/DateTimePickerDialog;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->configButtonTextColor()V

    return-void
.end method

.method static synthetic access$400(Lcom/miui/widget/picker/DateTimePickerDialog;)Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mOnShowListener:Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;

    return-object p0
.end method

.method static synthetic access$500(Lcom/miui/widget/picker/DateTimePickerDialog;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setIsShowLabel(Z)V

    return-void
.end method

.method static synthetic access$600(Lcom/miui/widget/picker/DateTimePickerDialog;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->init()V

    return-void
.end method

.method private configButtonTextColor()V
    .locals 3

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getNegativeColor()I

    move-result v0

    const/4 v1, -0x1

    if-eq v1, v0, :cond_0

    const/4 v0, -0x2

    invoke-virtual {p0, v0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getNegativeColor()I

    move-result v2

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setTextColor(I)V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getPositiveColor()I

    move-result v0

    if-eq v1, v0, :cond_1

    invoke-virtual {p0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v0

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getPositiveColor()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setTextColor(I)V

    :cond_1
    return-void
.end method

.method private init()V
    .locals 10

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->isInit()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowDay()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DatePicker;->setShowDay(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowHour()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DatePicker;->setShowHour(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowMonth()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DatePicker;->setShowMonth(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowMinute()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DatePicker;->setShowMinute(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->is24Hour:Z

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DatePicker;->setEnable24Hour(Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getItemTextSize()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getItemHilightTextSize()I

    move-result v2

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getItemLabelSize()I

    move-result v3

    invoke-virtual {v0, v1, v2, v3}, Lcom/miui/widget/picker/DatePicker;->setPickerItemTextStyle(III)V

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowLabel:Z

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DatePicker;->setIsShowLabel(Z)V

    iget-object v2, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    iget-wide v3, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mStartTime:J

    iget-wide v5, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mMinTime:J

    iget-wide v7, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mMaxTime:J

    iget-object v9, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mOnDateChangedListener:Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;

    invoke-virtual/range {v2 .. v9}, Lcom/miui/widget/picker/DatePicker;->init(JJJLcom/miui/widget/picker/DatePicker$OnDateChangedListener;)V

    :cond_0
    return-void
.end method

.method private setIsShowLabel(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowLabel:Z

    return-void
.end method

.method private tryNotifyDateSet()V
    .locals 10

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getCallBack()Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->clearFocus()V

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getCallBack()Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    move-result-object v1

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->getYear()I

    move-result v3

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->getMonth()I

    move-result v4

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->getDayOfMonth()I

    move-result v5

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->getHourOfDay()I

    move-result v6

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->getMinuteOfHour()I

    move-result v7

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->getCurrentSelectDate()J

    move-result-wide v8

    move-object v2, p0

    invoke-interface/range {v1 .. v9}, Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;->onDateSet(Lcom/miui/widget/picker/DateTimePickerDialog;IIIIIJ)V

    :cond_0
    return-void
.end method

.method private updateTitle(J)V
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mTitleView:Landroid/view/View;

    if-nez v0, :cond_0

    invoke-virtual {p0, p1, p2}, Lcom/miui/widget/picker/DateTimePickerDialog;->calculateTitle(J)Ljava/lang/String;

    move-result-object p1

    invoke-super {p0, p1}, Landroid/app/AlertDialog;->setTitle(Ljava/lang/CharSequence;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getCallBack()Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    move-result-object v0

    invoke-virtual {p0, p1, p2}, Lcom/miui/widget/picker/DateTimePickerDialog;->calculateTitle(J)Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, p0, v1, p1, p2}, Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;->onSelectChange(Lcom/miui/widget/picker/DateTimePickerDialog;Ljava/lang/String;J)V

    :cond_1
    :goto_0
    return-void
.end method


# virtual methods
.method public calculateTitle(J)Ljava/lang/String;
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v0}, Lcom/miui/widget/picker/DatePicker;->isShowHour()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x788

    goto :goto_0

    :cond_0
    const/16 v0, 0x780

    :goto_0
    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v1}, Lcom/miui/widget/picker/DatePicker;->isShowMinute()Z

    move-result v1

    if-eqz v1, :cond_1

    or-int/lit8 v0, v0, 0x4

    :cond_1
    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->is24Hour:Z

    if-eqz v1, :cond_2

    const/16 v1, 0x20

    goto :goto_1

    :cond_2
    const/16 v1, 0x10

    :goto_1
    or-int/2addr v0, v1

    invoke-virtual {p0}, Lcom/miui/widget/picker/DateTimePickerDialog;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1, p1, p2, v0}, Lcom/miui/widget/util/date/DateUtils;->formatDateTime(Landroid/content/Context;JI)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public getCallBack()Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mCallBack:Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    return-object v0
.end method

.method public getItemHilightTextSize()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mItemHilightTextSize:I

    return v0
.end method

.method public getItemLabelSize()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mItemLabelSize:I

    return v0
.end method

.method public getItemTextSize()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mItemTextSize:I

    return v0
.end method

.method public getNegativeColor()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mNegativeColor:I

    return v0
.end method

.method public getPositiveColor()I
    .locals 1

    iget v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mPositiveColor:I

    return v0
.end method

.method public getTitleView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mTitleView:Landroid/view/View;

    return-object v0
.end method

.method public isShowDay()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowDay:Z

    return v0
.end method

.method public isShowHour()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowHour:Z

    return v0
.end method

.method public isShowMinute()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowMinute:Z

    return v0
.end method

.method public isShowMonth()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowMonth:Z

    return v0
.end method

.method public onDetachedFromWindow()V
    .locals 1

    invoke-super {p0}, Landroid/app/AlertDialog;->onDetachedFromWindow()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    iput-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mCallBack:Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    return-void
.end method

.method public onRestoreInstanceState(Landroid/os/Bundle;)V
    .locals 4

    invoke-super {p0, p1}, Landroid/app/AlertDialog;->onRestoreInstanceState(Landroid/os/Bundle;)V

    const-string v0, "miui:year"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v0

    const-string v1, "miui:month"

    invoke-virtual {p1, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v1

    const-string v2, "miui:day"

    invoke-virtual {p1, v2}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result p1

    iget-object v2, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    iget-object v3, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mOnDateChangedListener:Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;

    invoke-virtual {v2, v0, v1, p1, v3}, Lcom/miui/widget/picker/DatePicker;->init(IIILcom/miui/widget/picker/DatePicker$OnDateChangedListener;)V

    return-void
.end method

.method public onSaveInstanceState()Landroid/os/Bundle;
    .locals 3

    invoke-super {p0}, Landroid/app/AlertDialog;->onSaveInstanceState()Landroid/os/Bundle;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v1}, Lcom/miui/widget/picker/DatePicker;->getYear()I

    move-result v1

    const-string v2, "miui:year"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v1}, Lcom/miui/widget/picker/DatePicker;->getMonth()I

    move-result v1

    const-string v2, "miui:month"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mDatePicker:Lcom/miui/widget/picker/DatePicker;

    invoke-virtual {v1}, Lcom/miui/widget/picker/DatePicker;->getDayOfMonth()I

    move-result v1

    const-string v2, "miui:day"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    return-object v0
.end method

.method public setCustomTitle(Landroid/view/View;)V
    .locals 0

    invoke-super {p0, p1}, Landroid/app/AlertDialog;->setCustomTitle(Landroid/view/View;)V

    iput-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mTitleView:Landroid/view/View;

    return-void
.end method

.method public setEnable24Hour(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->is24Hour:Z

    return-void
.end method

.method public setItemHilightTextSize(I)V
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mItemHilightTextSize:I

    return-void
.end method

.method public setItemLabelSize(I)V
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mItemLabelSize:I

    return-void
.end method

.method public setItemTextSize(I)V
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mItemTextSize:I

    return-void
.end method

.method public setMaxTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mMaxTime:J

    return-void
.end method

.method public setMinTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mMinTime:J

    return-void
.end method

.method public setNegativeColor(I)V
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mNegativeColor:I

    return-void
.end method

.method public setOnShowListener(Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mOnShowListener:Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;

    return-void
.end method

.method public setPositiveColor(I)V
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mPositiveColor:I

    return-void
.end method

.method public setShowDay(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowDay:Z

    return-void
.end method

.method public setShowHour(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowHour:Z

    return-void
.end method

.method public setShowMinute(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowMinute:Z

    return-void
.end method

.method public setShowMonth(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->isShowMonth:Z

    return-void
.end method

.method public setStartTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mStartTime:J

    return-void
.end method

.method public setTitle(I)V
    .locals 0

    invoke-super {p0, p1}, Landroid/app/AlertDialog;->setTitle(I)V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mTitleNeedsUpdate:Z

    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 0

    invoke-super {p0, p1}, Landroid/app/AlertDialog;->setTitle(Ljava/lang/CharSequence;)V

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mTitleNeedsUpdate:Z

    return-void
.end method

.method public show()V
    .locals 2

    invoke-super {p0}, Landroid/app/AlertDialog;->show()V

    iget-wide v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog;->mStartTime:J

    invoke-direct {p0, v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->updateTitle(J)V

    return-void
.end method
