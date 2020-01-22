.class Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;
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
    name = "ChangeCurrentByOneFromLongPressCommand"
.end annotation


# instance fields
.field private mIncrement:Z

.field final synthetic this$0:Lcom/miui/widget/picker/NumberPicker;


# direct methods
.method constructor <init>(Lcom/miui/widget/picker/NumberPicker;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;->setStep(Z)V

    return-void
.end method

.method private setStep(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;->mIncrement:Z

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    iget-boolean v1, p0, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;->mIncrement:Z

    invoke-static {v0, v1}, Lcom/miui/widget/picker/NumberPicker;->access$1400(Lcom/miui/widget/picker/NumberPicker;Z)V

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$ChangeCurrentByOneFromLongPressCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-static {v0}, Lcom/miui/widget/picker/NumberPicker;->access$1500(Lcom/miui/widget/picker/NumberPicker;)J

    move-result-wide v1

    invoke-virtual {v0, p0, v1, v2}, Lcom/miui/widget/picker/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method
