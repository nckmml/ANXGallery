.class public Lcom/miui/widget/util/DateDataCacheUtils;
.super Ljava/lang/Object;
.source "DateDataCacheUtils.java"


# static fields
.field public static sChineseDays:[Ljava/lang/String;

.field public static sChineseLeapMonthMark:Ljava/lang/String;

.field public static sChineseLeapYearMonths:[Ljava/lang/String;

.field public static sChineseMonths:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public static isHaveCacheData()Z
    .locals 1

    sget-object v0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseDays:[Ljava/lang/String;

    if-eqz v0, :cond_0

    sget-object v0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseMonths:[Ljava/lang/String;

    if-eqz v0, :cond_0

    sget-object v0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseLeapMonthMark:Ljava/lang/String;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public static saveDateData([Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    sput-object p0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseDays:[Ljava/lang/String;

    sput-object p1, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseMonths:[Ljava/lang/String;

    sget-object p0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseMonths:[Ljava/lang/String;

    array-length p0, p0

    add-int/lit8 p0, p0, 0x1

    new-array p0, p0, [Ljava/lang/String;

    sput-object p0, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseLeapYearMonths:[Ljava/lang/String;

    sput-object p3, Lcom/miui/widget/util/DateDataCacheUtils;->sChineseLeapMonthMark:Ljava/lang/String;

    return-void
.end method
