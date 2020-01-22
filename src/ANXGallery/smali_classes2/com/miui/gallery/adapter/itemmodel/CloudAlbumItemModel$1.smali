.class Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$1;
.super Ljava/lang/Object;
.source "CloudAlbumItemModel.java"

# interfaces
.implements Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel;->getViewHolderCreator()Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/epoxy/EpoxyAdapter$IViewHolderCreator<",
        "Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel;


# direct methods
.method constructor <init>(Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$1;->this$0:Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic create(Landroid/view/View;Landroid/view/View;)Lcom/miui/epoxy/EpoxyViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$1;->create(Landroid/view/View;Landroid/view/View;)Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;

    move-result-object p1

    return-object p1
.end method

.method public create(Landroid/view/View;Landroid/view/View;)Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;
    .locals 0

    new-instance p2, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;

    invoke-direct {p2, p1}, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel$CloudAlbumVH;-><init>(Landroid/view/View;)V

    return-object p2
.end method
