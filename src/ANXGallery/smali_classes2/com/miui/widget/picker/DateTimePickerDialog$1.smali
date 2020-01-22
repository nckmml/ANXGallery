.class Lcom/miui/widget/picker/DateTimePickerDialog$1;
.super Ljava/lang/Object;
.source "DateTimePickerDialog.java"

# interfaces
.implements Lcom/miui/widget/picker/DatePicker$OnDateChangedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/picker/DateTimePickerDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/widget/picker/DateTimePickerDialog;


# direct methods
.method constructor <init>(Lcom/miui/widget/picker/DateTimePickerDialog;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$1;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDateChanged(Lcom/miui/widget/picker/DatePicker;JZ)V
    .locals 0

    iget-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$1;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-static {p1}, Lcom/miui/widget/picker/DateTimePickerDialog;->access$000(Lcom/miui/widget/picker/DateTimePickerDialog;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$1;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-static {p1, p2, p3}, Lcom/miui/widget/picker/DateTimePickerDialog;->access$100(Lcom/miui/widget/picker/DateTimePickerDialog;J)V

    :cond_0
    return-void
.end method
