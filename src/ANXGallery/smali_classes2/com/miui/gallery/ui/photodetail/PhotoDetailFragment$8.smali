.class Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;
.super Ljava/lang/Object;
.source "PhotoDetailFragment.java"

# interfaces
.implements Lcom/miui/widget/picker/DateTimePickerDialog$OnDateSetListener;


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

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private setTimePickerDialogTitle(Lcom/miui/widget/picker/DateTimePickerDialog;Ljava/lang/String;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$500(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$600(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Landroid/widget/TextView;

    move-result-object v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/widget/picker/DateTimePickerDialog;->getTitleView()Landroid/view/View;

    move-result-object v1

    const v2, 0x7f090391

    invoke-virtual {v1, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    invoke-static {v0, v1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$502(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;Landroid/widget/TextView;)Landroid/widget/TextView;

    iget-object v0, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/widget/picker/DateTimePickerDialog;->getTitleView()Landroid/view/View;

    move-result-object p1

    const v1, 0x7f090390

    invoke-virtual {p1, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    invoke-static {v0, p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$602(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;Landroid/widget/TextView;)Landroid/widget/TextView;

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$500(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Landroid/widget/TextView;

    move-result-object p1

    const v0, 0x7f100372

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(I)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$600(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;)Landroid/widget/TextView;

    move-result-object p1

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method


# virtual methods
.method public onDateSet(Lcom/miui/widget/picker/DateTimePickerDialog;IIIIIJ)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;

    invoke-virtual {p1, p7, p8}, Lcom/miui/gallery/ui/photodetail/PhotoDetailContract$P;->updatePhotoDateTime(J)V

    return-void
.end method

.method public onSelectChange(Lcom/miui/widget/picker/DateTimePickerDialog;Ljava/lang/String;J)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$8;->setTimePickerDialogTitle(Lcom/miui/widget/picker/DateTimePickerDialog;Ljava/lang/String;)V

    return-void
.end method
