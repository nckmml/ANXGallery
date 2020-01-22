.class public Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;
.super Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$SingleChoiceViewHolder;
.source "AudioViewHolder.java"


# static fields
.field private static mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;


# instance fields
.field private mDownloadView:Lcom/miui/gallery/video/editor/widget/DownloadView;

.field private mIcon:Landroid/widget/ImageView;

.field private mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

.field private mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

.field private mNameTextView:Landroid/widget/TextView;

.field private mSelected:Landroid/view/View;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    sget-object v0, Lcom/miui/gallery/video/editor/util/ImageLoaderUtils;->mVideoEditorDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    sput-object v0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    return-void
.end method

.method public constructor <init>(Landroid/view/View;)V
    .locals 1

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/widget/SingleChoiceRecyclerView$SingleChoiceRecyclerViewAdapter$SingleChoiceViewHolder;-><init>(Landroid/view/View;)V

    const v0, 0x7f0901c1

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mNameTextView:Landroid/widget/TextView;

    const v0, 0x7f0901bd

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mIcon:Landroid/widget/ImageView;

    const v0, 0x7f0901bb

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/video/editor/widget/DownloadView;

    iput-object v0, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mDownloadView:Lcom/miui/gallery/video/editor/widget/DownloadView;

    const v0, 0x7f0901cb

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mSelected:Landroid/view/View;

    new-instance p1, Lcom/nostra13/universalimageloader/core/imageaware/ImageViewAware;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mIcon:Landroid/widget/ImageView;

    invoke-direct {p1, v0}, Lcom/nostra13/universalimageloader/core/imageaware/ImageViewAware;-><init>(Landroid/widget/ImageView;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    new-instance p1, Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mIcon:Landroid/widget/ImageView;

    invoke-direct {p1, v0}, Lcom/miui/gallery/video/editor/ImageLoadingProcess;-><init>(Landroid/widget/ImageView;)V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    return-void
.end method


# virtual methods
.method public setIcon(Ljava/lang/String;II)V
    .locals 2

    if-eqz p2, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mIcon:Landroid/widget/ImageView;

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setImageResource(I)V

    return-void

    :cond_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p2

    if-nez p2, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/video/editor/ImageLoadingProcess;->setBgColor(I)V

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object p2

    iget-object p3, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mImageAware:Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;

    sget-object v0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mImageLoadingProcess:Lcom/miui/gallery/video/editor/ImageLoadingProcess;

    invoke-virtual {p2, p1, p3, v0, v1}, Lcom/nostra13/universalimageloader/core/ImageLoader;->displayImage(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;)V

    return-void

    :cond_1
    if-eqz p3, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mIcon:Landroid/widget/ImageView;

    invoke-virtual {p1, p3}, Landroid/widget/ImageView;->setImageResource(I)V

    :cond_2
    return-void
.end method

.method public setName(I)V
    .locals 1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mNameTextView:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(I)V

    :cond_0
    return-void
.end method

.method public setSelect(Z)V
    .locals 0

    return-void
.end method

.method public setStateImage(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mDownloadView:Lcom/miui/gallery/video/editor/widget/DownloadView;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/widget/DownloadView;->setStateImage(I)V

    return-void
.end method

.method public updateSelected(ZZ)V
    .locals 0

    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mSelected:Landroid/view/View;

    invoke-static {p2}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->showView(Landroid/view/View;)V

    goto :goto_0

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mSelected:Landroid/view/View;

    invoke-static {p2}, Lcom/miui/gallery/video/editor/util/ToolsUtil;->hideView(Landroid/view/View;)V

    :goto_0
    iget-object p2, p0, Lcom/miui/gallery/video/editor/adapter/AudioViewHolder;->mNameTextView:Landroid/widget/TextView;

    invoke-virtual {p2, p1}, Landroid/widget/TextView;->setSelected(Z)V

    return-void
.end method
