.class public Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;
.super Ljava/lang/Object;
.source "ImageLoaderSupportDelegate.java"

# interfaces
.implements Lcom/miui/gallery/util/imageloader/IImageLoader;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate$SingletonHolder;
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate$SingletonHolder;->access$000()Lcom/miui/gallery/util/imageloader/ImageLoaderSupportDelegate;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method public bindImage(Landroid/widget/ImageView;Ljava/lang/String;Landroid/net/Uri;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;)V
    .locals 6

    sget-object v2, Lcom/miui/gallery/sdk/download/DownloadType;->MICRO:Lcom/miui/gallery/sdk/download/DownloadType;

    invoke-static {}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->getInstance()Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->getMicroTargetSize()Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    move-result-object v5

    move-object v0, p2

    move-object v1, p3

    move-object v3, p1

    move-object v4, p4

    invoke-static/range {v0 .. v5}, Lcom/miui/gallery/util/BindImageHelper;->bindImage(Ljava/lang/String;Landroid/net/Uri;Lcom/miui/gallery/sdk/download/DownloadType;Landroid/widget/ImageView;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/assist/ImageSize;)V

    return-void
.end method
