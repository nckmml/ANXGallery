.class Lcom/miui/gallery/ui/CleanerPhotoPickFragment$2;
.super Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;
.source "CleanerPhotoPickFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/CleanerPhotoPickFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$2;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-direct {p0}, Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onAllItemsCheckedStateChanged(Landroid/view/ActionMode;Z)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$2;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onItemSelectedChanged()V

    return-void
.end method

.method public onItemCheckedStateChanged(Landroid/view/ActionMode;IJZ)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$2;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onItemSelectedChanged()V

    return-void
.end method
