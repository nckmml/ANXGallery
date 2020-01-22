.class public abstract Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;
.super Lcom/miui/epoxy/EpoxyModel;
.source "BaseItemModel.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/support/IViewSupport;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<DATA:",
        "Ljava/lang/Object;",
        "VH:",
        "Lcom/miui/epoxy/EpoxyViewHolder;",
        ">",
        "Lcom/miui/epoxy/EpoxyModel<",
        "TVH;>;",
        "Lcom/miui/gallery/base_optimization/support/IViewSupport;"
    }
.end annotation


# instance fields
.field protected mImageLoaderSupport:Lcom/miui/gallery/util/imageloader/IImageLoader;

.field protected mItemData:Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TDATA;"
        }
    .end annotation
.end field

.field protected mViewSupport:Lcom/miui/gallery/base_optimization/support/IViewSupport;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/epoxy/EpoxyModel;-><init>()V

    return-void
.end method

.method public constructor <init>(JLjava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(JTDATA;)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Lcom/miui/epoxy/EpoxyModel;-><init>(J)V

    iput-object p3, p0, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->mItemData:Ljava/lang/Object;

    invoke-static {}, Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;->getInstance()Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->mViewSupport:Lcom/miui/gallery/base_optimization/support/IViewSupport;

    invoke-static {}, Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;->getInstance()Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->mImageLoaderSupport:Lcom/miui/gallery/util/imageloader/IImageLoader;

    return-void
.end method


# virtual methods
.method protected bindImage(Landroid/widget/ImageView;Ljava/lang/String;Landroid/net/Uri;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->mImageLoaderSupport:Lcom/miui/gallery/util/imageloader/IImageLoader;

    invoke-interface {v0, p1, p2, p3, p4}, Lcom/miui/gallery/util/imageloader/IImageLoader;->bindImage(Landroid/widget/ImageView;Ljava/lang/String;Landroid/net/Uri;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;)V

    return-void
.end method

.method public getItemData()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TDATA;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->mItemData:Ljava/lang/Object;

    return-object v0
.end method

.method public setText(Landroid/widget/TextView;Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/adapter/itemmodel/base/BaseItemModel;->mViewSupport:Lcom/miui/gallery/base_optimization/support/IViewSupport;

    invoke-interface {v0, p1, p2}, Lcom/miui/gallery/base_optimization/support/IViewSupport;->setText(Landroid/widget/TextView;Ljava/lang/String;)V

    return-void
.end method
