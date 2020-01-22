.class final Lcom/miui/widget/util/date/DateUtils$1;
.super Lcom/miui/widget/util/Pools$Manager;
.source "DateUtils.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/util/date/DateUtils;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/widget/util/Pools$Manager<",
        "Lcom/miui/widget/util/date/Calendar;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/util/Pools$Manager;-><init>()V

    return-void
.end method


# virtual methods
.method public createInstance()Lcom/miui/widget/util/date/Calendar;
    .locals 1

    new-instance v0, Lcom/miui/widget/util/date/Calendar;

    invoke-direct {v0}, Lcom/miui/widget/util/date/Calendar;-><init>()V

    return-object v0
.end method

.method public bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/widget/util/date/DateUtils$1;->createInstance()Lcom/miui/widget/util/date/Calendar;

    move-result-object v0

    return-object v0
.end method
