.class Lcom/miui/widget/picker/NumberPicker$1;
.super Ljava/lang/Object;
.source "NumberPicker.java"

# interfaces
.implements Landroid/view/View$OnFocusChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/widget/picker/NumberPicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/widget/picker/NumberPicker;


# direct methods
.method constructor <init>(Lcom/miui/widget/picker/NumberPicker;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker$1;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onFocusChange(Landroid/view/View;Z)V
    .locals 1

    if-eqz p2, :cond_0

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker$1;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-static {p1}, Lcom/miui/widget/picker/NumberPicker;->access$000(Lcom/miui/widget/picker/NumberPicker;)Landroid/widget/EditText;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/EditText;->selectAll()V

    goto :goto_0

    :cond_0
    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker$1;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-static {p2}, Lcom/miui/widget/picker/NumberPicker;->access$000(Lcom/miui/widget/picker/NumberPicker;)Landroid/widget/EditText;

    move-result-object p2

    const/4 v0, 0x0

    invoke-virtual {p2, v0, v0}, Landroid/widget/EditText;->setSelection(II)V

    iget-object p2, p0, Lcom/miui/widget/picker/NumberPicker$1;->this$0:Lcom/miui/widget/picker/NumberPicker;

    invoke-static {p2, p1}, Lcom/miui/widget/picker/NumberPicker;->access$100(Lcom/miui/widget/picker/NumberPicker;Landroid/view/View;)V

    :goto_0
    return-void
.end method
