.class public Lcom/miui/gallery/adapter/itemmodel/trans/ItemModelTransManager;
.super Ljava/lang/Object;
.source "ItemModelTransManager.java"


# direct methods
.method public static transDataToModel(Ljava/lang/Object;)Lcom/miui/epoxy/EpoxyModel;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;)",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;"
        }
    .end annotation

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    :cond_0
    instance-of v1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;

    if-eqz v1, :cond_1

    new-instance v0, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;

    check-cast p0, Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;

    invoke-direct {v0, p0}, Lcom/miui/gallery/adapter/itemmodel/HiddenAlbumItemModel;-><init>(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V

    return-object v0

    :cond_1
    instance-of v1, p0, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    if-eqz v1, :cond_2

    new-instance v0, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel;

    check-cast p0, Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;

    invoke-direct {v0, p0}, Lcom/miui/gallery/adapter/itemmodel/CloudAlbumItemModel;-><init>(Lcom/miui/gallery/ui/album/cloudalbum/viewbean/CloudAlbumItemViewBean;)V

    :cond_2
    return-object v0
.end method
