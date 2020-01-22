.class public Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
.super Ljava/lang/Object;
.source "DateTimePickerDialog.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/picker/DateTimePickerDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field private is24Hour:Z

.field private isShowDay:Z

.field private isShowHour:Z

.field private isShowLabel:Z

.field private isShowMinute:Z

.field private isShowMonth:Z

.field private mContext:Landroid/content/Context;

.field private mItemHilightTextSize:I

.field private mItemLabelSize:I

.field private mItemTextSize:I

.field private mListener:Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

.field private mMaxTime:J

.field private mMinTime:J

.field private mNegativeColor:I

.field private mPositiveColor:I

.field private mStartTime:J

.field private mTitleResId:I

.field private mTitleView:Landroid/view/View;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide/16 v0, -0x1

    iput-wide v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mMaxTime:J

    iput-wide v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mMinTime:J

    iput-wide v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mStartTime:J

    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mNegativeColor:I

    iput v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mPositiveColor:I

    iput-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mContext:Landroid/content/Context;

    return-void
.end method


# virtual methods
.method public create()Lcom/miui/widget/picker/DateTimePickerDialog;
    .locals 4

    new-instance v0, Lcom/miui/widget/picker/DateTimePickerDialog;

    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mContext:Landroid/content/Context;

    iget-object v2, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mListener:Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    invoke-direct {v0, v1, v2}, Lcom/miui/widget/picker/DateTimePickerDialog;-><init>(Landroid/content/Context;Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;)V

    iget-wide v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mMinTime:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/widget/picker/DateTimePickerDialog;->setMinTime(J)V

    iget-wide v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mMaxTime:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/widget/picker/DateTimePickerDialog;->setMaxTime(J)V

    iget-wide v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mStartTime:J

    invoke-virtual {v0, v1, v2}, Lcom/miui/widget/picker/DateTimePickerDialog;->setStartTime(J)V

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowDay:Z

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setShowDay(Z)V

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowHour:Z

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setShowHour(Z)V

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowMinute:Z

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setShowMinute(Z)V

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowMonth:Z

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setShowMonth(Z)V

    iget v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mTitleResId:I

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mContext:Landroid/content/Context;

    invoke-static {v1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v1

    iget v2, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mTitleResId:I

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mTitleView:Landroid/view/View;

    :cond_0
    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mTitleView:Landroid/view/View;

    if-eqz v1, :cond_1

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setCustomTitle(Landroid/view/View;)V

    :cond_1
    iget v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mNegativeColor:I

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setNegativeColor(I)V

    iget v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mPositiveColor:I

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setPositiveColor(I)V

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->is24Hour:Z

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setEnable24Hour(Z)V

    iget v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mItemTextSize:I

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setItemTextSize(I)V

    iget v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mItemHilightTextSize:I

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setItemHilightTextSize(I)V

    iget v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mItemLabelSize:I

    invoke-virtual {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->setItemLabelSize(I)V

    iget-boolean v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowLabel:Z

    invoke-static {v0, v1}, Lcom/miui/widget/picker/DateTimePickerDialog;->access$500(Lcom/miui/widget/picker/DateTimePickerDialog;Z)V

    invoke-static {v0}, Lcom/miui/widget/picker/DateTimePickerDialog;->access$600(Lcom/miui/widget/picker/DateTimePickerDialog;)V

    return-object v0
.end method

.method public enable24Hour()Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 1

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->is24Hour:Z

    return-object p0
.end method

.method public setIsShowLabel(Z)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowLabel:Z

    return-object p0
.end method

.method public setMaxTime(J)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 0

    iput-wide p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mMaxTime:J

    return-object p0
.end method

.method public setOnDateSetListener(Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mListener:Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;

    return-object p0
.end method

.method public setPickerItemLabelSize(I)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mItemLabelSize:I

    return-object p0
.end method

.method public setStartTime(J)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 0

    iput-wide p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mStartTime:J

    return-object p0
.end method

.method public setTitleView(I)Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->mTitleResId:I

    return-object p0
.end method

.method public showHour()Lcom/miui/widget/picker/DateTimePickerDialog$Builder;
    .locals 1

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowDay:Z

    iput-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowMonth:Z

    iput-boolean v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$Builder;->isShowHour:Z

    return-object p0
.end method
