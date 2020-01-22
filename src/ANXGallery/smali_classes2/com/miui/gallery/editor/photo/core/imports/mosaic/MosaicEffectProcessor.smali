.class Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;
.super Ljava/lang/Object;
.source "MosaicEffectProcessor.java"


# instance fields
.field private final mOriginTextureHeight:I

.field private final mOriginTextureWidth:I

.field private mTextureCood:[F

.field private final mViewHeight:I

.field private final mViewWidth:I


# direct methods
.method constructor <init>(IIII)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/16 v0, 0x8

    new-array v0, v0, [F

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mTextureCood:[F

    iput p1, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mOriginTextureWidth:I

    iput p2, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mOriginTextureHeight:I

    iput p3, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mViewWidth:I

    iput p4, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mViewHeight:I

    return-void
.end method

.method private changeBitmapSize(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 7

    new-instance v5, Landroid/graphics/Matrix;

    invoke-direct {v5}, Landroid/graphics/Matrix;-><init>()V

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mOriginTextureWidth:I

    int-to-float v0, v0

    invoke-static {}, Lcom/miui/gallery/util/ScreenUtils;->getScreenWidth()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v0, v1

    const/high16 v1, 0x3f800000    # 1.0f

    cmpg-float v1, v0, v1

    if-gez v1, :cond_0

    invoke-virtual {v5, v0, v0}, Landroid/graphics/Matrix;->postScale(FF)Z

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v4

    const/4 v6, 0x0

    move-object v0, p1

    invoke-static/range {v0 .. v6}, Landroid/graphics/Bitmap;->createBitmap(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;

    move-result-object p1

    :cond_0
    return-object p1
.end method

.method private drawEffect(Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEffectEntity;Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;IFF)V
    .locals 4

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mOriginTextureWidth:I

    iget v1, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mOriginTextureHeight:I

    iget v2, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mViewWidth:I

    iget v3, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mViewHeight:I

    invoke-virtual {p2, v0, v1, v2, v3}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEffectEntity;->generateSpecificShader(IIII)Lcom/miui/gallery/editor/photo/core/imports/mosaic/shader/GLTextureSizeShader;

    move-result-object p2

    if-eqz p2, :cond_1

    invoke-virtual {p2, p5}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/shader/GLTextureSizeShader;->setScale(F)V

    invoke-virtual {p2, p6}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/shader/GLTextureSizeShader;->setMosaicScale(F)V

    instance-of p5, p2, Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLShaderGroup;

    const/16 p6, 0x4100

    if-eqz p5, :cond_0

    move-object p5, p2

    check-cast p5, Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLShaderGroup;

    invoke-virtual {p5, p4}, Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLShaderGroup;->getEffectedTexture(I)I

    move-result p4

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->bind()V

    invoke-static {p6}, Landroid/opengl/GLES20;->glClear(I)V

    invoke-virtual {p3, p4}, Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;->draw(I)V

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->unBind()V

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->bind()V

    invoke-static {p6}, Landroid/opengl/GLES20;->glClear(I)V

    invoke-virtual {p2, p4}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/shader/GLTextureSizeShader;->drawFBO(I)V

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->unBind()V

    :goto_0
    invoke-virtual {p2}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/shader/GLTextureSizeShader;->destroy()V

    :cond_1
    return-void
.end method

.method private drawOrigin(Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;I)V
    .locals 0

    invoke-virtual {p1, p2}, Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;->draw(I)V

    return-void
.end method

.method private drawResource(Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLResourceEntity;Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;FFF)V
    .locals 4

    iget-object v0, p1, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLResourceEntity;->mTileMode:Ljava/lang/String;

    iget-object p1, p1, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLResourceEntity;->mResourcePath:Ljava/lang/String;

    const-string v1, "EDGE"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    const/16 v2, 0x2901

    if-eqz v1, :cond_0

    const v2, 0x812f

    goto :goto_0

    :cond_0
    const-string v1, "REPEAT"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    :goto_0
    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/nostra13/universalimageloader/core/ImageLoader;->loadImageSync(Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object p1

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    if-lez v0, :cond_1

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    if-lez v0, :cond_1

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->changeBitmapSize(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object p1

    iget v0, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mOriginTextureWidth:I

    int-to-float v0, v0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v0, v1

    iget v1, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mOriginTextureHeight:I

    int-to-float v1, v1

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v1, v3

    div-float/2addr v0, p3

    div-float/2addr v1, p3

    mul-float/2addr v1, p4

    div-float/2addr v1, p5

    iget-object p3, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mTextureCood:[F

    invoke-static {p3, v0, v1}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->getTextureCood([FFF)V

    invoke-static {p1, v2}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->loadTexture(Landroid/graphics/Bitmap;I)I

    move-result p1

    invoke-direct {p0, p4}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->getGLPosition(F)[F

    move-result-object p3

    iget-object p4, p0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->mTextureCood:[F

    invoke-virtual {p2, p1, p3, p4}, Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;->draw(I[F[F)V

    const/4 p2, 0x1

    new-array p3, p2, [I

    const/4 p4, 0x0

    aput p1, p3, p4

    invoke-static {p2, p3, p4}, Landroid/opengl/GLES20;->glDeleteTextures(I[II)V

    :cond_1
    return-void
.end method

.method private getGLPosition(F)[F
    .locals 5

    const/16 v0, 0x8

    new-array v0, v0, [F

    const/high16 v1, -0x40800000    # -1.0f

    const/4 v2, 0x0

    aput v1, v0, v2

    neg-float v2, p1

    const/4 v3, 0x1

    aput v2, v0, v3

    const/high16 v3, 0x3f800000    # 1.0f

    const/4 v4, 0x2

    aput v3, v0, v4

    const/4 v4, 0x3

    aput v2, v0, v4

    const/4 v2, 0x4

    aput v1, v0, v2

    const/4 v1, 0x5

    aput p1, v0, v1

    const/4 v1, 0x6

    aput v3, v0, v1

    const/4 v1, 0x7

    aput p1, v0, v1

    return-object v0
.end method

.method private static getTextureCood([FFF)V
    .locals 2

    const/4 v0, 0x0

    const/4 v1, 0x0

    aput v0, p0, v1

    const/4 v1, 0x1

    aput p2, p0, v1

    const/4 v1, 0x2

    aput p1, p0, v1

    const/4 v1, 0x3

    aput p2, p0, v1

    const/4 p2, 0x4

    aput v0, p0, p2

    const/4 p2, 0x5

    aput v0, p0, p2

    const/4 p2, 0x6

    aput p1, p0, p2

    const/4 p1, 0x7

    aput v0, p0, p1

    return-void
.end method

.method private static loadTexture(Landroid/graphics/Bitmap;I)I
    .locals 5

    const/4 v0, 0x1

    new-array v1, v0, [I

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/opengl/GLES20;->glGenTextures(I[II)V

    aget v0, v1, v2

    const/16 v3, 0xde1

    invoke-static {v3, v0}, Landroid/opengl/GLES20;->glBindTexture(II)V

    const v0, 0x46180400    # 9729.0f

    const/16 v4, 0x2800

    invoke-static {v3, v4, v0}, Landroid/opengl/GLES20;->glTexParameterf(IIF)V

    const/16 v4, 0x2801

    invoke-static {v3, v4, v0}, Landroid/opengl/GLES20;->glTexParameterf(IIF)V

    int-to-float p1, p1

    const/16 v0, 0x2802

    invoke-static {v3, v0, p1}, Landroid/opengl/GLES20;->glTexParameterf(IIF)V

    const/16 v0, 0x2803

    invoke-static {v3, v0, p1}, Landroid/opengl/GLES20;->glTexParameterf(IIF)V

    invoke-static {v3, v2, p0, v2}, Landroid/opengl/GLUtils;->texImage2D(IILandroid/graphics/Bitmap;I)V

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->recycle()V

    aget p0, v1, v2

    return p0
.end method


# virtual methods
.method public draw(Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEntity;IILcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;)V
    .locals 9

    const/high16 v6, 0x3f800000    # 1.0f

    const/high16 v7, 0x3f800000    # 1.0f

    const/high16 v8, 0x3f800000    # 1.0f

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    move v4, p4

    move-object v5, p5

    invoke-virtual/range {v0 .. v8}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->draw(Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEntity;IILcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;FFF)V

    return-void
.end method

.method public draw(Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEntity;IILcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;FFF)V
    .locals 12

    move-object v0, p2

    if-nez v0, :cond_0

    return-void

    :cond_0
    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor$1;->$SwitchMap$com$miui$gallery$editor$photo$core$imports$mosaic$MosaicGLEntity$TYPE:[I

    iget-object v2, v0, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEntity;->type:Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEntity$TYPE;

    invoke-virtual {v2}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEntity$TYPE;->ordinal()I

    move-result v2

    aget v1, v1, v2

    const/4 v2, 0x1

    const/16 v3, 0x4100

    if-eq v1, v2, :cond_3

    const/4 v2, 0x2

    if-eq v1, v2, :cond_2

    const/4 v2, 0x3

    if-eq v1, v2, :cond_1

    :goto_0
    move-object v0, p0

    goto :goto_1

    :cond_1
    move-object v5, v0

    check-cast v5, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEffectEntity;

    move-object v3, p0

    move-object v4, p1

    move-object/from16 v6, p5

    move v7, p3

    move/from16 v8, p6

    move/from16 v9, p8

    invoke-direct/range {v3 .. v9}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->drawEffect(Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLEffectEntity;Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;IFF)V

    goto :goto_0

    :cond_2
    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->bind()V

    invoke-static {v3}, Landroid/opengl/GLES20;->glClear(I)V

    move-object v7, v0

    check-cast v7, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLResourceEntity;

    move-object v6, p0

    move-object/from16 v8, p5

    move/from16 v9, p6

    move/from16 v10, p7

    move/from16 v11, p8

    invoke-direct/range {v6 .. v11}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->drawResource(Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicGLResourceEntity;Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;FFF)V

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->unBind()V

    goto :goto_0

    :cond_3
    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->bind()V

    invoke-static {v3}, Landroid/opengl/GLES20;->glClear(I)V

    move-object v0, p0

    move/from16 v1, p4

    move-object/from16 v2, p5

    invoke-direct {p0, v2, v1}, Lcom/miui/gallery/editor/photo/core/imports/mosaic/MosaicEffectProcessor;->drawOrigin(Lcom/miui/gallery/editor/photo/widgets/glview/shader/GLTextureShader;I)V

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/widgets/glview/GLFBOManager;->unBind()V

    :goto_1
    return-void
.end method
