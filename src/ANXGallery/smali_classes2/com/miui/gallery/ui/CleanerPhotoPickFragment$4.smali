.class Lcom/miui/gallery/ui/CleanerPhotoPickFragment$4;
.super Ljava/lang/Object;
.source "CleanerPhotoPickFragment.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


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

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$4;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$4;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object p1, p1, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {p1}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->isAllItemsChecked()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$4;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    iget-object v0, v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setAllItemsCheckState(Z)V

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$4;->this$0:Lcom/miui/gallery/ui/CleanerPhotoPickFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->recordCancelSelectAllEvent()V

    :cond_0
    return-void
.end method
