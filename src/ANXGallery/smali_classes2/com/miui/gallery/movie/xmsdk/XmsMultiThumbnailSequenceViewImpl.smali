.class public Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;
.super Lcom/miui/gallery/xmstreaming/XmsMultiThumbnailSequenceView;
.source "XmsMultiThumbnailSequenceViewImpl.java"


# instance fields
.field private mDisplayOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

.field private mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

.field private mImageSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 3

    invoke-direct {p0, p1}, Lcom/miui/gallery/xmstreaming/XmsMultiThumbnailSequenceView;-><init>(Landroid/content/Context;)V

    new-instance p1, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    invoke-direct {p1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;-><init>()V

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->considerExifParams(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    const/4 v1, 0x0

    invoke-virtual {p1, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->resetViewBeforeLoading(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    sget-object v1, Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;->IN_SAMPLE_INT:Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;

    invoke-virtual {p1, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->imageScaleType(Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    invoke-virtual {p1, v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->cacheInMemory(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    sget-object v0, Landroid/graphics/Bitmap$Config;->RGB_565:Landroid/graphics/Bitmap$Config;

    invoke-virtual {p1, v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->bitmapConfig(Landroid/graphics/Bitmap$Config;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->build()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->mDisplayOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    new-instance p1, Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/gallery/movie/R$dimen;->photo_movie_edit_edit_item_width:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v0

    float-to-int v0, v0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/gallery/movie/R$dimen;->photo_movie_edit_edit_item_width:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v1

    float-to-int v1, v1

    invoke-direct {p1, v0, v1}, Lcom/nostra13/universalimageloader/core/assist/ImageSize;-><init>(II)V

    iput-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->mImageSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    new-instance p1, Lcom/nostra13/universalimageloader/core/imageaware/NonViewAware;

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->mImageSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    sget-object v1, Lcom/nostra13/universalimageloader/core/assist/ViewScaleType;->CROP:Lcom/nostra13/universalimageloader/core/assist/ViewScaleType;

    invoke-direct {p1, v0, v1}, Lcom/nostra13/universalimageloader/core/imageaware/NonViewAware;-><init>(Lcom/nostra13/universalimageloader/core/assist/ImageSize;Lcom/nostra13/universalimageloader/core/assist/ViewScaleType;)V

    iput-object p1, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;)V
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->notifyIconArrived()V

    return-void
.end method


# virtual methods
.method protected nativeCancelIconTask(J)V
    .locals 0

    return-void
.end method

.method protected nativeGetIcon(Ljava/lang/String;J)J
    .locals 3

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object p2

    sget-object p3, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->FILE:Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    invoke-virtual {p3, p1}, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->wrap(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    iget-object v1, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->mDisplayOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    new-instance v2, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl$1;

    invoke-direct {v2, p0, p1}, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl$1;-><init>(Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;Ljava/lang/String;)V

    invoke-virtual {p2, p3, v0, v1, v2}, Lcom/nostra13/universalimageloader/core/ImageLoader;->displayImage(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;)V

    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result p1

    int-to-long p1, p1

    return-wide p1
.end method

.method protected nativeGetIconFromCache(Ljava/lang/String;J)Landroid/graphics/Bitmap;
    .locals 2

    sget-object p2, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->FILE:Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;

    invoke-virtual {p2, p1}, Lcom/nostra13/universalimageloader/core/download/ImageDownloader$Scheme;->wrap(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object p3

    iget-object v0, p0, Lcom/miui/gallery/movie/xmsdk/XmsMultiThumbnailSequenceViewImpl;->mImageSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    invoke-virtual {p3, p2, v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->loadFromMemoryCache(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/assist/ImageSize;)Landroid/graphics/Bitmap;

    move-result-object p2

    if-nez p2, :cond_0

    const/4 p3, 0x1

    goto :goto_0

    :cond_0
    const/4 p3, 0x0

    :goto_0
    invoke-static {p3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p3

    const-string v0, "XmsMultiThumbnailSequenceViewImpl"

    const-string v1, "nativeGetIconFromCache %s :%b"

    invoke-static {v0, v1, p1, p3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-object p2
.end method
