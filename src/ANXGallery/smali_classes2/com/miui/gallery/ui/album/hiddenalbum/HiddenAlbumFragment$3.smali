.class Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$3;
.super Ljava/lang/Object;
.source "HiddenAlbumFragment.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->showConfirmDialog(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

.field final synthetic val$rawModel:Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$3;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    iput-object p2, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$3;->val$rawModel:Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$3;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getPresenter()Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;

    iget-object p2, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$3;->val$rawModel:Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;

    invoke-virtual {p2}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->getItemData()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;->unAlbumHide(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V

    return-void
.end method
