.class public Lcom/nostra13/universalimageloader/core/imageaware/SizeImageViewAware;
.super Lcom/nostra13/universalimageloader/core/imageaware/ImageViewAware;
.source "SizeImageViewAware.java"


# instance fields
.field private mHeight:I

.field private mWidth:I


# direct methods
.method public constructor <init>(Landroid/widget/ImageView;II)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/nostra13/universalimageloader/core/imageaware/ImageViewAware;-><init>(Landroid/widget/ImageView;)V

    iput p2, p0, Lcom/nostra13/universalimageloader/core/imageaware/SizeImageViewAware;->mWidth:I

    iput p3, p0, Lcom/nostra13/universalimageloader/core/imageaware/SizeImageViewAware;->mHeight:I

    return-void
.end method


# virtual methods
.method public getHeight()I
    .locals 1

    iget v0, p0, Lcom/nostra13/universalimageloader/core/imageaware/SizeImageViewAware;->mHeight:I

    return v0
.end method

.method public getWidth()I
    .locals 1

    iget v0, p0, Lcom/nostra13/universalimageloader/core/imageaware/SizeImageViewAware;->mWidth:I

    return v0
.end method
