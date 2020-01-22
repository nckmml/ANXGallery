.class public Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;
.super Lcom/miui/gallery/util/imageloader/imageloadiotion/BaseImageLoadOption;
.source "AlbumImageLoadOptions.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions$SingletonHolder;
    }
.end annotation


# instance fields
.field private mHiddenOrCloudAlbumImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

.field private mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

.field private mHiddenOrCloudAlbumImageRoundedCornerDisplayer:Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

.field private mHiddenOrCloudAlbumRoundedCornerValue:I


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/BaseImageLoadOption;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions$SingletonHolder;->access$000()Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;

    move-result-object v0

    return-object v0
.end method

.method private initHiddenOrCloudAlbumImageOptions()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->cloneDefalutImageOptionsBuilder()Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    const v1, 0x7f070110

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->showImageOnFail(I)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06034c

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumRoundedCornerValue:I

    new-instance v0, Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

    iget v1, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumRoundedCornerValue:I

    invoke-direct {v0, v1}, Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;-><init>(I)V

    iput-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageRoundedCornerDisplayer:Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    iget-object v1, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageRoundedCornerDisplayer:Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->displayer(Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->build()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    :cond_0
    return-void
.end method


# virtual methods
.method public getHiddenAlbumImageOptions(J)Lcom/nostra13/universalimageloader/core/DisplayImageOptions;
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->initHiddenOrCloudAlbumImageOptions()V

    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-lez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->considerFileLength(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->fileLength(J)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageRoundedCornerDisplayer:Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

    invoke-virtual {p1, p2}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->displayer(Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->build()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object p1

    return-object p1

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->getHiddenOrCloudAlbumImageOptions()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object p1

    return-object p1
.end method

.method public getHiddenOrCloudAlbumImageOptions()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->initHiddenOrCloudAlbumImageOptions()V

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    return-object v0
.end method

.method public getMicroTargetSize()Lcom/nostra13/universalimageloader/core/assist/ImageSize;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/Config$ThumbConfig;->get()Lcom/miui/gallery/Config$ThumbConfig;

    move-result-object v0

    iget-object v0, v0, Lcom/miui/gallery/Config$ThumbConfig;->sMicroTargetSize:Lcom/nostra13/universalimageloader/core/assist/ImageSize;

    return-object v0
.end method

.method public initDefalutOption()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/BaseImageLoadOption;->initDefalutOption()V

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->cloneDefalutImageOptionsBuilder()Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    const v1, 0x7f070111

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->showImageOnFail(I)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f06007d

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumRoundedCornerValue:I

    new-instance v0, Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

    iget v1, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumRoundedCornerValue:I

    invoke-direct {v0, v1}, Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;-><init>(I)V

    iput-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageRoundedCornerDisplayer:Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    iget-object v1, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageRoundedCornerDisplayer:Lcom/miui/gallery/search/widget/RoundedCornerRectBitmapDisplayer;

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->displayer(Lcom/nostra13/universalimageloader/core/display/BitmapDisplayer;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    iget-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptionsBuilder:Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->build()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/util/imageloader/imageloadiotion/AlbumImageLoadOptions;->mHiddenOrCloudAlbumImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    :cond_0
    return-void
.end method
