.class Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$2;
.super Ljava/lang/Object;
.source "CloudAlbumListFragment.java"

# interfaces
.implements Landroid/widget/CompoundButton$OnCheckedChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;->onEvent(Landroid/view/View;Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;Lcom/miui/epoxy/EpoxyAdapter;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$2;->this$1:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCheckedChanged(Landroid/widget/CompoundButton;Z)V
    .locals 1

    invoke-virtual {p1}, Landroid/widget/CompoundButton;->isPressed()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p1}, Landroid/widget/CompoundButton;->isInTouchMode()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$2;->this$1:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;

    iget-object v0, v0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;->this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;

    invoke-virtual {p1}, Landroid/widget/CompoundButton;->getTag()Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {v0, p1, p2}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->doChangeAutoUpload(Ljava/lang/Object;Z)V

    :cond_1
    :goto_0
    return-void
.end method
