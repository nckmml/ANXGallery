.class Lcom/miui/widget/picker/DateTimePickerDialog$3;
.super Ljava/lang/Object;
.source "DateTimePickerDialog.java"

# interfaces
.implements Landroid/content/DialogInterface$OnShowListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/widget/picker/DateTimePickerDialog;-><init>(Landroid/content/Context;Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;JJJ)V
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

    iput-object p1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$3;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onShow(Landroid/content/DialogInterface;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$3;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-static {v0}, Lcom/miui/widget/picker/DateTimePickerDialog;->access$300(Lcom/miui/widget/picker/DateTimePickerDialog;)V

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$3;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-static {v0}, Lcom/miui/widget/picker/DateTimePickerDialog;->access$400(Lcom/miui/widget/picker/DateTimePickerDialog;)Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/widget/picker/DateTimePickerDialog$3;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-static {v0}, Lcom/miui/widget/picker/DateTimePickerDialog;->access$400(Lcom/miui/widget/picker/DateTimePickerDialog;)Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/widget/picker/DateTimePickerDialog$3;->this$0:Lcom/miui/widget/picker/DateTimePickerDialog;

    invoke-interface {v0, p1, v1}, Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;->onShow(Landroid/content/DialogInterface;Landroid/app/AlertDialog;)V

    :cond_0
    return-void
.end method
