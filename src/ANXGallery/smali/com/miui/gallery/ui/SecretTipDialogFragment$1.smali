.class Lcom/miui/gallery/ui/SecretTipDialogFragment$1;
.super Ljava/lang/Object;
.source "SecretTipDialogFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/SecretTipDialogFragment;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/SecretTipDialogFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$1;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/SecretTipDialogFragment$1;->this$0:Lcom/miui/gallery/ui/SecretTipDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->access$000(Lcom/miui/gallery/ui/SecretTipDialogFragment;)Lcom/miui/gallery/ui/SecretTipDialogFragment$SecretTipDialog;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/SecretTipDialogFragment;->access$100(Lcom/miui/gallery/ui/SecretTipDialogFragment;Landroid/content/DialogInterface;)V

    return-void
.end method
