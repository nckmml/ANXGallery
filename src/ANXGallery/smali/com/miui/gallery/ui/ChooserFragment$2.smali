.class Lcom/miui/gallery/ui/ChooserFragment$2;
.super Ljava/lang/Object;
.source "ChooserFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/ChooserFragment;->configView(Landroid/view/View;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/ChooserFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/ChooserFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$2;->this$0:Lcom/miui/gallery/ui/ChooserFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$2;->this$0:Lcom/miui/gallery/ui/ChooserFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment;->access$200(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/ChooserFragment$2;->this$0:Lcom/miui/gallery/ui/ChooserFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/ChooserFragment;->access$200(Lcom/miui/gallery/ui/ChooserFragment;)Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;

    move-result-object p1

    invoke-interface {p1}, Lcom/miui/gallery/ui/ChooserFragment$OnProjectClickedListener;->onProjectedClicked()V

    :cond_0
    return-void
.end method
