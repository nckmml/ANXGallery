.class public Lcom/miui/gallery/collage/drawable/ColorDrawable;
.super Landroid/graphics/drawable/Drawable;
.source "ColorDrawable.java"


# instance fields
.field private mPaint:Landroid/graphics/Paint;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/collage/core/poster/SpecifyDrawableModel;)V
    .locals 2

    invoke-direct {p0}, Landroid/graphics/drawable/Drawable;-><init>()V

    new-instance v0, Landroid/graphics/Paint;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lcom/miui/gallery/collage/drawable/ColorDrawable;->mPaint:Landroid/graphics/Paint;

    iget-object v0, p0, Lcom/miui/gallery/collage/drawable/ColorDrawable;->mPaint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->FILL:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    iget-object v0, p0, Lcom/miui/gallery/collage/drawable/ColorDrawable;->mPaint:Landroid/graphics/Paint;

    iget-object p1, p1, Lcom/miui/gallery/collage/core/poster/SpecifyDrawableModel;->extras:Lcom/miui/gallery/collage/core/poster/SpecifyDrawableModel$Extras;

    iget-object p1, p1, Lcom/miui/gallery/collage/core/poster/SpecifyDrawableModel$Extras;->color:Ljava/lang/String;

    invoke-static {p1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method


# virtual methods
.method public draw(Landroid/graphics/Canvas;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/collage/drawable/ColorDrawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/collage/drawable/ColorDrawable;->mPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v1}, Landroid/graphics/Canvas;->drawRect(Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    return-void
.end method

.method public getIntrinsicHeight()I
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public getIntrinsicWidth()I
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public getOpacity()I
    .locals 1

    const/4 v0, -0x2

    return v0
.end method

.method public setAlpha(I)V
    .locals 0

    return-void
.end method

.method public setColorFilter(Landroid/graphics/ColorFilter;)V
    .locals 0

    return-void
.end method
