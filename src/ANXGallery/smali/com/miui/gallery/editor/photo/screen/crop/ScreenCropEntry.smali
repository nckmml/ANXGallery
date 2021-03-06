.class public Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;
.super Ljava/lang/Object;
.source "ScreenCropEntry.java"


# instance fields
.field private mBitmapArea:Landroid/graphics/RectF;

.field protected mCropArea:Landroid/graphics/RectF;


# direct methods
.method public constructor <init>(Landroid/graphics/RectF;Landroid/graphics/RectF;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mCropArea:Landroid/graphics/RectF;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mCropArea:Landroid/graphics/RectF;

    invoke-virtual {v0, p1}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mBitmapArea:Landroid/graphics/RectF;

    return-void
.end method


# virtual methods
.method public apply(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 6

    new-instance v0, Landroid/graphics/Matrix;

    invoke-direct {v0}, Landroid/graphics/Matrix;-><init>()V

    new-instance v1, Landroid/graphics/RectF;

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    int-to-float v3, v3

    const/4 v4, 0x0

    invoke-direct {v1, v4, v4, v2, v3}, Landroid/graphics/RectF;-><init>(FFFF)V

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mBitmapArea:Landroid/graphics/RectF;

    sget-object v3, Landroid/graphics/Matrix$ScaleToFit;->FILL:Landroid/graphics/Matrix$ScaleToFit;

    invoke-virtual {v0, v2, v1, v3}, Landroid/graphics/Matrix;->setRectToRect(Landroid/graphics/RectF;Landroid/graphics/RectF;Landroid/graphics/Matrix$ScaleToFit;)Z

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mCropArea:Landroid/graphics/RectF;

    invoke-virtual {v0, v1}, Landroid/graphics/Matrix;->mapRect(Landroid/graphics/RectF;)Z

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mCropArea:Landroid/graphics/RectF;

    invoke-virtual {v0}, Landroid/graphics/RectF;->width()F

    move-result v0

    const/high16 v1, 0x3f000000    # 0.5f

    add-float/2addr v0, v1

    float-to-int v0, v0

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mCropArea:Landroid/graphics/RectF;

    invoke-virtual {v2}, Landroid/graphics/RectF;->height()F

    move-result v2

    add-float/2addr v2, v1

    float-to-int v2, v2

    sget-object v3, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v2, v3}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    new-instance v2, Landroid/graphics/Canvas;

    invoke-direct {v2, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    new-instance v3, Landroid/graphics/Paint;

    const/4 v4, 0x3

    invoke-direct {v3, v4}, Landroid/graphics/Paint;-><init>(I)V

    iget-object v4, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mCropArea:Landroid/graphics/RectF;

    iget v4, v4, Landroid/graphics/RectF;->top:F

    add-float/2addr v4, v1

    float-to-int v4, v4

    iget-object v5, p0, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->mCropArea:Landroid/graphics/RectF;

    iget v5, v5, Landroid/graphics/RectF;->left:F

    add-float/2addr v5, v1

    float-to-int v1, v5

    neg-int v1, v1

    int-to-float v1, v1

    neg-int v4, v4

    int-to-float v4, v4

    invoke-virtual {v2, p1, v1, v4, v3}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    return-object v0
.end method
