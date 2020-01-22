.class public Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;
.super Lcom/miui/filtersdk/filter/base/BaseOriginalFilter;
.source "GPUImageVignetteFilter.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/core/imports/filter/render/IFilterEmptyValidate;
.implements Lcom/miui/gallery/editor/photo/core/imports/filter/render/ISpecialProcessFilter;


# instance fields
.field private mBlockOffset:[F

.field private mBlockOffsetLocation:I

.field private mVignetteCenter:Landroid/graphics/PointF;

.field private mVignetteCenterLocation:I

.field private mVignetteColor:[F

.field private mVignetteColorLocation:I

.field private mVignetteEnd:F

.field private mVignetteEndLocation:I

.field private mVignetteStart:F

.field private mVignetteStartLocation:I


# direct methods
.method public constructor <init>()V
    .locals 2

    new-instance v0, Landroid/graphics/PointF;

    const/high16 v1, 0x3f000000    # 0.5f

    invoke-direct {v0, v1, v1}, Landroid/graphics/PointF;-><init>(FF)V

    const/4 v1, 0x3

    new-array v1, v1, [F

    fill-array-data v1, :array_0

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;-><init>(Landroid/graphics/PointF;[F)V

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x0
        0x0
    .end array-data
.end method

.method public constructor <init>(I)V
    .locals 2

    new-instance v0, Landroid/graphics/PointF;

    const/high16 v1, 0x3f000000    # 0.5f

    invoke-direct {v0, v1, v1}, Landroid/graphics/PointF;-><init>(FF)V

    const/4 v1, 0x3

    new-array v1, v1, [F

    fill-array-data v1, :array_0

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;-><init>(Landroid/graphics/PointF;[F)V

    invoke-virtual {p0, p1}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setDegree(I)V

    return-void

    :array_0
    .array-data 4
        0x0
        0x0
        0x0
    .end array-data
.end method

.method public constructor <init>(Landroid/graphics/PointF;[F)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/util/StaticContext;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    const v1, 0x7f0f0001

    invoke-static {v0, v1}, Lcom/miui/gallery/editor/photo/core/imports/filter/render/OpenGlUtils;->readShaderFromRawResource(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}"

    invoke-direct {p0, v1, v0}, Lcom/miui/filtersdk/filter/base/BaseOriginalFilter;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteCenter:Landroid/graphics/PointF;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteColor:[F

    return-void
.end method


# virtual methods
.method public getSpecialBoard()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public isDegreeAdjustSupported()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public isEmpty()Z
    .locals 1

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mDegree:I

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public onInit()V
    .locals 2

    invoke-super {p0}, Lcom/miui/filtersdk/filter/base/BaseOriginalFilter;->onInit()V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->getProgram()I

    move-result v0

    const-string v1, "vignetteCenter"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteCenterLocation:I

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->getProgram()I

    move-result v0

    const-string v1, "vignetteColor"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteColorLocation:I

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->getProgram()I

    move-result v0

    const-string v1, "vignetteStart"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteStartLocation:I

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->getProgram()I

    move-result v0

    const-string v1, "vignetteEnd"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteEndLocation:I

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->getProgram()I

    move-result v0

    const-string v1, "blockOffset"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mBlockOffsetLocation:I

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteCenter:Landroid/graphics/PointF;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setVignetteCenter(Landroid/graphics/PointF;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteColor:[F

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setVignetteColor([F)V

    const/4 v0, 0x2

    new-array v0, v0, [F

    fill-array-data v0, :array_0

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mBlockOffset:[F

    const/4 v0, 0x0

    invoke-virtual {p0, v0, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setBlockOffset(FF)V

    return-void

    :array_0
    .array-data 4
        0x0
        0x0
    .end array-data
.end method

.method public onInitialized()V
    .locals 3

    invoke-super {p0}, Lcom/miui/filtersdk/filter/base/BaseOriginalFilter;->onInitialized()V

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mDegree:I

    int-to-float v0, v0

    const v1, -0x40fd70a4    # -0.51f

    mul-float/2addr v0, v1

    const/high16 v1, 0x42c80000    # 100.0f

    div-float/2addr v0, v1

    const v2, 0x3f35c28f    # 0.71f

    add-float/2addr v0, v2

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setVignetteStart(F)V

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mDegree:I

    int-to-float v0, v0

    const v2, -0x41b33338    # -0.19999993f

    mul-float/2addr v0, v2

    div-float/2addr v0, v1

    const v1, 0x3fa66666    # 1.3f

    add-float/2addr v0, v1

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setVignetteEnd(F)V

    return-void
.end method

.method public setBlock(Lcom/miui/gallery/editor/blocksdk/Block;)V
    .locals 5

    iget-object v0, p1, Lcom/miui/gallery/editor/blocksdk/Block;->mTotalBlockInfo:Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;

    iget v0, v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mTotalRow:I

    int-to-float v0, v0

    iget-object v1, p1, Lcom/miui/gallery/editor/blocksdk/Block;->mTotalBlockInfo:Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;

    iget v1, v1, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mTotalColumn:I

    int-to-float v1, v1

    iget v2, p1, Lcom/miui/gallery/editor/blocksdk/Block;->mColumn:I

    int-to-float v2, v2

    iget p1, p1, Lcom/miui/gallery/editor/blocksdk/Block;->mRow:I

    int-to-float p1, p1

    new-instance v3, Landroid/graphics/PointF;

    const/high16 v4, 0x40000000    # 2.0f

    div-float/2addr v1, v4

    div-float/2addr v0, v4

    invoke-direct {v3, v1, v0}, Landroid/graphics/PointF;-><init>(FF)V

    invoke-virtual {p0, v3}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setVignetteCenter(Landroid/graphics/PointF;)V

    invoke-virtual {p0, v2, p1}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setBlockOffset(FF)V

    return-void
.end method

.method public setBlockOffset(FF)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mBlockOffset:[F

    const/4 v1, 0x0

    aput p1, v0, v1

    const/4 p1, 0x1

    aput p2, v0, p1

    iget p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mBlockOffsetLocation:I

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setFloatVec2(I[F)V

    return-void
.end method

.method public setVignetteCenter(Landroid/graphics/PointF;)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteCenter:Landroid/graphics/PointF;

    iget p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteCenterLocation:I

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteCenter:Landroid/graphics/PointF;

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setPoint(ILandroid/graphics/PointF;)V

    return-void
.end method

.method public setVignetteColor([F)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteColor:[F

    iget p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteColorLocation:I

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteColor:[F

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setFloatVec3(I[F)V

    return-void
.end method

.method public setVignetteEnd(F)V
    .locals 1

    iput p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteEnd:F

    iget p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteEndLocation:I

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteEnd:F

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setFloat(IF)V

    return-void
.end method

.method public setVignetteStart(F)V
    .locals 1

    iput p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteStart:F

    iget p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteStartLocation:I

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->mVignetteStart:F

    invoke-virtual {p0, p1, v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/adjust/GPUImageVignetteFilter;->setFloat(IF)V

    return-void
.end method
