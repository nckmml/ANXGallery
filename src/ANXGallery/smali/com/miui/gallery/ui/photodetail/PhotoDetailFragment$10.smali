.class Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$10;
.super Ljava/lang/Object;
.source "PhotoDetailFragment.java"

# interfaces
.implements Lcom/miui/widget/picker/DateTimePickerDialog$OnShowListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->showDateTimePicker()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$10;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onShow(Landroid/content/DialogInterface;Landroid/app/AlertDialog;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$10;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$700(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)I

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$10;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    const/4 v0, -0x2

    invoke-static {p2, v0}, Lcom/miui/gallery/util/DialogUtil;->getDialogButtonTextColor(Landroid/app/AlertDialog;I)I

    move-result v0

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$702(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;I)I

    :cond_0
    const/4 p1, -0x1

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$10;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$700(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)I

    move-result v0

    invoke-static {p2, p1, v0}, Lcom/miui/gallery/util/DialogUtil;->setDialogButtonTextColor(Landroid/app/AlertDialog;II)V

    return-void
.end method
