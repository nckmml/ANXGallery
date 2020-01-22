.class final Lcom/miui/widget/util/date/CalendarFormatSymbols$1;
.super Lcom/miui/widget/util/SoftReferenceSingleton;
.source "CalendarFormatSymbols.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/util/date/CalendarFormatSymbols;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/widget/util/SoftReferenceSingleton<",
        "Lcom/miui/widget/util/date/CalendarFormatSymbols;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/widget/util/SoftReferenceSingleton;-><init>()V

    return-void
.end method


# virtual methods
.method protected createInstance()Lcom/miui/widget/util/date/CalendarFormatSymbols;
    .locals 1

    new-instance v0, Lcom/miui/widget/util/date/CalendarFormatSymbols;

    invoke-direct {v0}, Lcom/miui/widget/util/date/CalendarFormatSymbols;-><init>()V

    return-object v0
.end method

.method protected bridge synthetic createInstance()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/widget/util/date/CalendarFormatSymbols$1;->createInstance()Lcom/miui/widget/util/date/CalendarFormatSymbols;

    move-result-object v0

    return-object v0
.end method
