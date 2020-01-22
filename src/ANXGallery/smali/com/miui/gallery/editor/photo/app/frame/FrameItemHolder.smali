.class public Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "FrameItemHolder.java"


# instance fields
.field private mBound:Landroid/graphics/Rect;

.field private mDrawable:Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;

.field private mTextView:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/view/View;)V
    .locals 1

    invoke-direct {p0, p2}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mBound:Landroid/graphics/Rect;

    const v0, 0x7f090340

    invoke-virtual {p2, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/TextView;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mTextView:Landroid/widget/TextView;

    new-instance p2, Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-direct {p2, v0}, Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;-><init>(Landroid/content/res/Resources;)V

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mDrawable:Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const p2, 0x7f06033b

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mBound:Landroid/graphics/Rect;

    const/4 v0, 0x0

    invoke-virtual {p2, v0, v0, p1, p1}, Landroid/graphics/Rect;->set(IIII)V

    return-void
.end method


# virtual methods
.method setFrameData(Lcom/miui/gallery/editor/photo/core/common/model/FrameData;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mTextView:Landroid/widget/TextView;

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/core/common/model/FrameData;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mDrawable:Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;

    iget p1, p1, Lcom/miui/gallery/editor/photo/core/common/model/FrameData;->iconRatio:F

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;->setRatio(F)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mDrawable:Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mBound:Landroid/graphics/Rect;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;->setBounds(Landroid/graphics/Rect;)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mTextView:Landroid/widget/TextView;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/frame/FrameItemHolder;->mDrawable:Lcom/miui/gallery/editor/photo/app/frame/FrameItemPreviewDrawable;

    const/4 v1, 0x0

    invoke-virtual {p1, v1, v0, v1, v1}, Landroid/widget/TextView;->setCompoundDrawables(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    return-void
.end method
