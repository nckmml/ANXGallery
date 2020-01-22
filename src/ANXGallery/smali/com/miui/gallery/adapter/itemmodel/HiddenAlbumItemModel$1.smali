.class Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$1;
.super Ljava/lang/Object;
.source "HiddenAlbumItemModel.java"

# interfaces
.implements Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;->getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
        "Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;


# direct methods
.method constructor <init>(Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic create(Landroid/view/View;Landroid/view/View;)Lcom/miui/epoxy/EpoxyViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$1;->create(Landroid/view/View;Landroid/view/View;)Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;

    move-result-object p1

    return-object p1
.end method

.method public create(Landroid/view/View;Landroid/view/View;)Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;
    .locals 0

    new-instance p2, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;

    invoke-direct {p2, p1}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel$HiddenAlbumVH;-><init>(Landroid/view/View;)V

    return-object p2
.end method
