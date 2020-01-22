.class Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;
.super Lcom/miui/epoxy/eventhook/EventHook;
.source "CloudAlbumListFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->addEventHook(Ljava/util/List;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/epoxy/eventhook/EventHook<",
        "Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;Ljava/lang/Class;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;->this$0:Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;

    invoke-direct {p0, p2}, Lcom/miui/epoxy/eventhook/EventHook;-><init>(Ljava/lang/Class;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic onBind(Lcom/miui/epoxy/EpoxyViewHolder;)Landroid/view/View;
    .locals 0

    check-cast p1, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;->onBind(Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;)Landroid/view/View;

    move-result-object p1

    return-object p1
.end method

.method public onBind(Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;)Landroid/view/View;
    .locals 0

    iget-object p1, p1, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->itemView:Landroid/view/View;

    return-object p1
.end method

.method public bridge synthetic onEvent(Landroid/view/View;Lcom/miui/epoxy/EpoxyViewHolder;Lcom/miui/epoxy/EpoxyAdapter;)V
    .locals 0

    check-cast p2, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;

    invoke-virtual {p0, p1, p2, p3}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;->onEvent(Landroid/view/View;Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;Lcom/miui/epoxy/EpoxyAdapter;)V

    return-void
.end method

.method public onEvent(Landroid/view/View;Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;Lcom/miui/epoxy/EpoxyAdapter;)V
    .locals 0

    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    const p3, 0x7f0901b9

    if-ne p1, p3, :cond_0

    iget-object p1, p2, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->itemView:Landroid/view/View;

    new-instance p3, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$1;

    invoke-direct {p3, p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$1;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;)V

    invoke-virtual {p1, p3}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object p1, p2, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mCbBackup:Lmiui/widget/SlidingButton;

    new-instance p3, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$2;

    invoke-direct {p3, p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$2;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;)V

    invoke-virtual {p1, p3}, Lmiui/widget/SlidingButton;->setOnCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V

    iget-object p1, p2, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;->mCbBackup:Lmiui/widget/SlidingButton;

    new-instance p2, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$3;

    invoke-direct {p2, p0}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2$3;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment$2;)V

    invoke-virtual {p1, p2}, Lmiui/widget/SlidingButton;->setOnPerformCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V

    :cond_0
    return-void
.end method
