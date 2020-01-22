.class public Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverEngine;
.super Lcom/miui/gallery/editor/photo/core/RenderEngine;
.source "RemoverEngine.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/core/RenderEngine;-><init>()V

    return-void
.end method


# virtual methods
.method public release()V
    .locals 0

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/remover/Inpaint;->release()V

    return-void
.end method

.method public render(Landroid/graphics/Bitmap;Lcom/miui/gallery/editor/photo/core/RenderData;)Landroid/graphics/Bitmap;
    .locals 13

    new-instance v0, Lcom/miui/gallery/util/CounterUtil;

    const-string v1, "RemoverEngine"

    invoke-direct {v0, v1}, Lcom/miui/gallery/util/CounterUtil;-><init>(Ljava/lang/String;)V

    instance-of v1, p2, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverRenderData;

    if-eqz v1, :cond_4

    check-cast p2, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverRenderData;

    iget-object p2, p2, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverRenderData;->mPaintData:Ljava/util/List;

    if-eqz p2, :cond_3

    invoke-interface {p2}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_1

    :cond_0
    invoke-static {}, Lcom/miui/gallery/editor/photo/app/remover/Inpaint;->initialize()Z

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    sget-object v3, Landroid/graphics/Bitmap$Config;->ALPHA_8:Landroid/graphics/Bitmap$Config;

    invoke-static {v1, v2, v3}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v1

    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverPaintData;

    iget-object v3, v2, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverPaintData;->mCurves:Ljava/util/List;

    invoke-static {v1, v2, v3}, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverGestureView;->export(Landroid/graphics/Bitmap;Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverPaintData;Ljava/util/List;)V

    new-instance v3, Landroid/graphics/RectF;

    invoke-direct {v3}, Landroid/graphics/RectF;-><init>()V

    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v4

    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v5

    iget-object v6, v2, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverPaintData;->mCurves:Ljava/util/List;

    invoke-static {v3, v4, v5, v2, v6}, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverGestureView;->getMaskBounds(Landroid/graphics/RectF;IILcom/miui/gallery/editor/photo/core/imports/remover/RemoverPaintData;Ljava/util/List;)V

    invoke-virtual {v3}, Landroid/graphics/RectF;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v6

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v7

    iget v4, v3, Landroid/graphics/RectF;->left:F

    float-to-int v8, v4

    iget v4, v3, Landroid/graphics/RectF;->top:F

    float-to-int v9, v4

    iget v4, v3, Landroid/graphics/RectF;->right:F

    float-to-int v10, v4

    iget v3, v3, Landroid/graphics/RectF;->bottom:F

    float-to-int v11, v3

    iget-object v12, v2, Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverPaintData;->mRemoverNNFData:Lcom/miui/gallery/editor/photo/core/imports/remover/RemoverNNFData;

    move-object v4, p1

    move-object v5, v1

    invoke-static/range {v4 .. v12}, Lcom/miui/gallery/editor/photo/app/remover/Inpaint;->upsampleBmpData(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;IIIIIILcom/miui/gallery/editor/photo/core/imports/remover/RemoverNNFData;)V

    goto :goto_0

    :cond_2
    invoke-static {v1}, Lcom/miui/gallery/util/BaseBitmapUtils;->recycleSilently(Landroid/graphics/Bitmap;)V

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/remover/Inpaint;->release()V

    const-string p2, "remove render done"

    invoke-virtual {v0, p2}, Lcom/miui/gallery/util/CounterUtil;->tick(Ljava/lang/String;)V

    :cond_3
    :goto_1
    return-object p1

    :cond_4
    const/4 p1, 0x0

    return-object p1
.end method
