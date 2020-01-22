.class Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;
.super Ljava/lang/Object;
.source "NumberPicker.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/picker/NumberPicker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "BeginSoftInputOnLongPressCommand"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/widget/picker/NumberPicker;


# direct methods
.method constructor <init>(Lcom/miui/widget/picker/NumberPicker;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$BeginSoftInputOnLongPressCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->access$1602(Lcom/miui/widget/picker/NumberPicker;Z)Z

    return-void
.end method
