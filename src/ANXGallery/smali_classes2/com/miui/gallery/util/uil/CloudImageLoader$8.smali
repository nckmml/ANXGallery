.class Lcom/miui/gallery/util/uil/CloudImageLoader$8;
.super Ljava/lang/Object;
.source "CloudImageLoader.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/util/uil/CloudImageLoader;->directShowImage(Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;Landroid/graphics/drawable/Drawable;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/util/uil/CloudImageLoader;

.field final synthetic val$displayer:Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;

.field final synthetic val$drawable:Landroid/graphics/drawable/Drawable;

.field final synthetic val$imageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;


# direct methods
.method constructor <init>(Lcom/miui/gallery/util/uil/CloudImageLoader;Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;Landroid/graphics/drawable/Drawable;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/util/uil/CloudImageLoader$8;->this$0:Lcom/miui/gallery/util/uil/CloudImageLoader;

    iput-object p2, p0, Lcom/miui/gallery/util/uil/CloudImageLoader$8;->val$displayer:Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;

    iput-object p3, p0, Lcom/miui/gallery/util/uil/CloudImageLoader$8;->val$drawable:Landroid/graphics/drawable/Drawable;

    iput-object p4, p0, Lcom/miui/gallery/util/uil/CloudImageLoader$8;->val$imageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/util/uil/CloudImageLoader$8;->val$displayer:Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;

    iget-object v1, p0, Lcom/miui/gallery/util/uil/CloudImageLoader$8;->val$drawable:Landroid/graphics/drawable/Drawable;

    check-cast v1, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v1}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/util/uil/CloudImageLoader$8;->val$imageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    sget-object v3, Lcom/nostra13/universalimageloader/core/assist/LoadedFrom;->MEMORY_CACHE:Lcom/nostra13/universalimageloader/core/assist/LoadedFrom;

    invoke-interface {v0, v1, v2, v3}, Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;->display(Landroid/graphics/Bitmap;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;Lcom/nostra13/universalimageloader/core/assist/LoadedFrom;)V

    return-void
.end method
