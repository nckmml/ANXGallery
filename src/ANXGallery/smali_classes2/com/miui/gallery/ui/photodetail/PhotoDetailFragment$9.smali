.class Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$9;
.super Ljava/lang/Object;
.source "PhotoDetailFragment.java"

# interfaces
.implements Landroid/content/DialogInterface$OnDismissListener;


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

    iput-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$9;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDismiss(Landroid/content/DialogInterface;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$9;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$502(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;Landroid/widget/TextView;)Landroid/widget/TextView;

    iget-object p1, p0, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment$9;->this$0:Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;->access$602(Lcom/miui/gallery/ui/photodetail/PhotoDetailFragment;Landroid/widget/TextView;)Landroid/widget/TextView;

    return-void
.end method
