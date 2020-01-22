.class public Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;
.super Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$SingleChoiceViewHolder;
.source "WaterMarkHolder.java"


# static fields
.field private static mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;


# instance fields
.field private mDownloadView:Lcom/miui/gallery/video/editor/widget/DownloadView;

.field private mIcon:Landroid/widget/ImageView;

.field private mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

.field private mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

.field private mSelectBackground:Landroid/view/View;

.field private mSelected:Landroid/view/View;

.field private mTextEditable:Landroid/widget/ImageView;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    sget-object v0, Lcom/miui/gallery/video/editor/util/ImageLoaderUtils;->mVideoEditorDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    sput-object v0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    return-void
.end method

.method public constructor <init>(Landroid/view/View;)V
    .locals 2

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$SingleChoiceViewHolder;-><init>(Landroid/view/View;)V

    const v0, 0x7f0903ba

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mIcon:Landroid/widget/ImageView;

    const v0, 0x7f0901bb

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/video/editor/widget/DownloadView;

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mDownloadView:Lcom/miui/gallery/video/editor/widget/DownloadView;

    const v0, 0x7f0902fb

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mSelected:Landroid/view/View;

    const v0, 0x7f0903af

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mTextEditable:Landroid/widget/ImageView;

    const v0, 0x7f0902f1

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mSelectBackground:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mDownloadView:Lcom/miui/gallery/video/editor/widget/DownloadView;

    invoke-static {}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->isRTLDirection()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v1, 0x7f0703d0

    invoke-virtual {p1, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v1, 0x7f0703cf

    invoke-virtual {p1, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    :goto_0
    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/widget/DownloadView;->setBackground(Landroid/graphics/drawable/Drawable;)V

    new-instance p1, Lcom/nostra13/universalimageloader/core/imageaware/ImageViewAware;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mIcon:Landroid/widget/ImageView;

    invoke-direct {p1, v0}, Lcom/nostra13/universalimageloader/core/imageaware/ImageViewAware;-><init>(Landroid/widget/ImageView;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    new-instance p1, Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mIcon:Landroid/widget/ImageView;

    invoke-direct {p1, v0}, Lcom/miui/gallery/video/editor/ImageLoadingProcess;-><init>(Landroid/widget/ImageView;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    return-void
.end method


# virtual methods
.method public setIcon(Ljava/lang/String;II)V
    .locals 2

    if-eqz p2, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mIcon:Landroid/widget/ImageView;

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setImageResource(I)V

    return-void

    :cond_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p2

    if-nez p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/video/editor/ImageLoadingProcess;->setBgColor(I)V

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object p2

    iget-object p3, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    sget-object v0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    invoke-virtual {p2, p1, p3, v0, v1}, Lcom/nostra13/universalimageloader/core/ImageLoader;->displayImage(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;)V

    return-void

    :cond_1
    if-eqz p3, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mIcon:Landroid/widget/ImageView;

    invoke-virtual {p1, p3}, Landroid/widget/ImageView;->setImageResource(I)V

    :cond_2
    return-void
.end method

.method public setSelect(Z)V
    .locals 0

    return-void
.end method

.method public setStateImage(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mDownloadView:Lcom/miui/gallery/video/editor/widget/DownloadView;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/widget/DownloadView;->setStateImage(I)V

    return-void
.end method

.method public updateSelected(ZZ)V
    .locals 0

    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mSelected:Landroid/view/View;

    invoke-static {p1}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->showView(Landroid/view/View;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mSelected:Landroid/view/View;

    invoke-static {p1}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->hideView(Landroid/view/View;)V

    :goto_0
    return-void
.end method

.method public updateTextEditable(Z)V
    .locals 0

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mTextEditable:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->showView(Landroid/view/View;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mSelectBackground:Landroid/view/View;

    invoke-static {p1}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->showView(Landroid/view/View;)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mTextEditable:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->hideView(Landroid/view/View;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/WaterMarkHolder;->mSelectBackground:Landroid/view/View;

    invoke-static {p1}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->hideView(Landroid/view/View;)V

    :goto_0
    return-void
.end method
