.class Lcom/miui/widget/picker/DatePicker$1;
.super Ljava/lang/Object;
.source "DatePicker.java"

# interfaces
.implements Lcom/miui/widget/picker/NumberPicker$OnValueChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/widget/picker/DatePicker;->initView(ZZZZZ)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/widget/picker/DatePicker;


# direct methods
.method constructor <init>(Lcom/miui/widget/picker/DatePicker;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onValueChange(Lcom/miui/widget/picker/NumberPicker;II)V
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v1}, Lcom/miui/widget/picker/DatePicker;->access$000(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/miui/widget/util/date/Calendar;->setTimeInMillis(J)Lcom/miui/widget/util/date/Calendar;

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$200(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_1

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v1}, Lcom/miui/widget/picker/DatePicker;->access$300(Lcom/miui/widget/picker/DatePicker;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/16 v1, 0xa

    goto :goto_0

    :cond_0
    const/16 v1, 0x9

    :goto_0
    sub-int/2addr p3, p2

    invoke-virtual {v0, v1, p3}, Lcom/miui/widget/util/date/Calendar;->add(II)Lcom/miui/widget/util/date/Calendar;

    goto/16 :goto_4

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$400(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_3

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v1}, Lcom/miui/widget/picker/DatePicker;->access$300(Lcom/miui/widget/picker/DatePicker;)Z

    move-result v1

    if-eqz v1, :cond_2

    const/4 v1, 0x6

    goto :goto_1

    :cond_2
    const/4 v1, 0x5

    :goto_1
    sub-int/2addr p3, p2

    invoke-virtual {v0, v1, p3}, Lcom/miui/widget/util/date/Calendar;->add(II)Lcom/miui/widget/util/date/Calendar;

    goto :goto_4

    :cond_3
    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$500(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object p2

    if-ne p1, p2, :cond_5

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object p2

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$300(Lcom/miui/widget/picker/DatePicker;)Z

    move-result v0

    if-eqz v0, :cond_4

    const/4 v0, 0x2

    goto :goto_2

    :cond_4
    const/4 v0, 0x1

    :goto_2
    invoke-virtual {p2, v0, p3}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    goto :goto_4

    :cond_5
    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$600(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object p2

    if-ne p1, p2, :cond_7

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object p2

    iget-object p3, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p3}, Lcom/miui/widget/picker/DatePicker;->access$300(Lcom/miui/widget/picker/DatePicker;)Z

    move-result p3

    if-eqz p3, :cond_6

    const/16 p3, 0x13

    goto :goto_3

    :cond_6
    const/16 p3, 0x12

    :goto_3
    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$600(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/widget/picker/NumberPicker;->getValue()I

    move-result v0

    invoke-virtual {p2, p3, v0}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    goto :goto_4

    :cond_7
    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$700(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object p2

    if-ne p1, p2, :cond_9

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object p2

    const/16 p3, 0x14

    iget-object v0, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v0}, Lcom/miui/widget/picker/DatePicker;->access$800(Lcom/miui/widget/picker/DatePicker;)I

    move-result v0

    iget-object v1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {v1}, Lcom/miui/widget/picker/DatePicker;->access$700(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/widget/picker/NumberPicker;->getValue()I

    move-result v1

    mul-int/2addr v0, v1

    invoke-virtual {p2, p3, v0}, Lcom/miui/widget/util/date/Calendar;->set(II)Lcom/miui/widget/util/date/Calendar;

    :goto_4
    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$100(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/util/date/Calendar;

    move-result-object p3

    invoke-virtual {p3}, Lcom/miui/widget/util/date/Calendar;->getTimeInMillis()J

    move-result-wide v0

    invoke-static {p2, v0, v1}, Lcom/miui/widget/picker/DatePicker;->access$900(Lcom/miui/widget/picker/DatePicker;J)V

    iget-object p2, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p2}, Lcom/miui/widget/picker/DatePicker;->access$500(Lcom/miui/widget/picker/DatePicker;)Lcom/miui/widget/picker/NumberPicker;

    move-result-object p2

    if-ne p1, p2, :cond_8

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p1}, Lcom/miui/widget/picker/DatePicker;->access$1000(Lcom/miui/widget/picker/DatePicker;)V

    :cond_8
    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p1}, Lcom/miui/widget/picker/DatePicker;->access$1100(Lcom/miui/widget/picker/DatePicker;)V

    iget-object p1, p0, Lcom/miui/widget/picker/DatePicker$1;->this$0:Lcom/miui/widget/picker/DatePicker;

    invoke-static {p1}, Lcom/miui/widget/picker/DatePicker;->access$1200(Lcom/miui/widget/picker/DatePicker;)V

    return-void

    :cond_9
    new-instance p1, Ljava/lang/IllegalArgumentException;

    invoke-direct {p1}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw p1
.end method
