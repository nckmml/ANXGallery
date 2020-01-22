.class Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;
.super Ljava/lang/Object;
.source "PhotoPageVideoItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageVideoItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "VideoThumbnailManager"
.end annotation


# instance fields
.field private mLoadThumbnailTask:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;

.field private mThumbItemHeight:I

.field private mThumbItemWidth:I

.field private mVideoThumbnail:Landroid/graphics/Bitmap;

.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPageVideoItem;)V
    .locals 2

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0606db

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mThumbItemWidth:I

    invoke-virtual {p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f0606da

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mThumbItemHeight:I

    return-void
.end method

.method private cancelLoadThumbnail()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mLoadThumbnailTask:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->release()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mLoadThumbnailTask:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;

    :cond_0
    return-void
.end method

.method private startLoadThumbnail()V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mVideoThumbnail:Landroid/graphics/Bitmap;

    if-nez v0, :cond_4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    if-nez v0, :cond_0

    goto :goto_1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageVideoItem;->mPhotoView:Luk/co/senab/photoview/PhotoView;

    invoke-virtual {v0}, Luk/co/senab/photoview/PhotoView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    instance-of v1, v0, Landroid/graphics/drawable/BitmapDrawable;

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    check-cast v0, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    goto :goto_0

    :cond_1
    move-object v0, v2

    :goto_0
    if-nez v0, :cond_2

    return-void

    :cond_2
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mLoadThumbnailTask:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;

    if-nez v1, :cond_3

    new-instance v1, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;

    invoke-direct {v1, v2}, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem$1;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mLoadThumbnailTask:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;

    :cond_3
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mLoadThumbnailTask:Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;

    iget v2, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mThumbItemWidth:I

    iget v3, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mThumbItemHeight:I

    new-instance v4, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$VideoThumbnailManager$hIM06F5t0HAkEAXSXQy_S1UzmuI;

    invoke-direct {v4, p0}, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$VideoThumbnailManager$hIM06F5t0HAkEAXSXQy_S1UzmuI;-><init>(Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;)V

    invoke-virtual {v1, v0, v2, v3, v4}, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->load(Landroid/graphics/Bitmap;IILcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;)V

    :cond_4
    :goto_1
    return-void
.end method


# virtual methods
.method getVideoThumbnail()Landroid/graphics/Bitmap;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mVideoThumbnail:Landroid/graphics/Bitmap;

    return-object v0
.end method

.method public synthetic lambda$startLoadThumbnail$20$PhotoPageVideoItem$VideoThumbnailManager(Landroid/graphics/Bitmap;)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mVideoThumbnail:Landroid/graphics/Bitmap;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->access$1400(Lcom/miui/gallery/ui/PhotoPageVideoItem;)Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->this$0:Lcom/miui/gallery/ui/PhotoPageVideoItem;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageVideoItem;->access$1400(Lcom/miui/gallery/ui/PhotoPageVideoItem;)Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;

    move-result-object v0

    invoke-interface {v0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$OnThumbnailLoadedListener;->onThumbnailLoaded(Landroid/graphics/Bitmap;)V

    :cond_0
    return-void
.end method

.method onImageLoadFinish()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->startLoadThumbnail()V

    return-void
.end method

.method onItemUpdated()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->cancelLoadThumbnail()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mVideoThumbnail:Landroid/graphics/Bitmap;

    return-void
.end method

.method release()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->cancelLoadThumbnail()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->mVideoThumbnail:Landroid/graphics/Bitmap;

    return-void
.end method
