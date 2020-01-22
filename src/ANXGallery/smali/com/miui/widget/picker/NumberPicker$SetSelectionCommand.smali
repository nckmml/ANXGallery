.class Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;
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
    name = "SetSelectionCommand"
.end annotation


# instance fields
.field private mSelectionEnd:I

.field private mSelectionStart:I

.field final synthetic this$0:Lcom/miui/widget/picker/NumberPicker;


# direct methods
.method constructor <init>(Lcom/miui/widget/picker/NumberPicker;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic access$302(Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;I)I
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->mSelectionStart:I

    return p1
.end method

.method static synthetic access$402(Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;I)I
    .locals 0

    iput p1, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->mSelectionEnd:I

    return p1
.end method


# virtual methods
.method public run()V
    .locals 3

    iget v0, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->mSelectionEnd:I

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-static {v1}, Lcom/miui/widget/picker/NumberPicker;->access$000(Lcom/miui/widget/picker/NumberPicker;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/EditText;->length()I

    move-result v1

    if-ge v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-static {v0}, Lcom/miui/widget/picker/NumberPicker;->access$000(Lcom/miui/widget/picker/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    iget v1, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->mSelectionStart:I

    iget v2, p0, Lcom/miui/widget/picker/NumberPicker$SetSelectionCommand;->mSelectionEnd:I

    invoke-virtual {v0, v1, v2}, Landroid/widget/EditText;->setSelection(II)V

    :cond_0
    return-void
.end method
