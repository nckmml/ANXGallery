.class public Lcom/miui/gallery/video/editor/ui/menu/TrimView;
.super Lcom/miui/gallery/editor/ui/menu/type/BaseMenuTopView;
.source "TrimView.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/ui/menu/type/BaseMenuTopView;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method protected initContentView()Landroid/view/View;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/menu/TrimView;->mContext:Landroid/content/Context;

    const v1, 0x7f0b01d2

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/video/editor/ui/menu/TrimView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method protected initTopView()Landroid/view/View;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/menu/TrimView;->mContext:Landroid/content/Context;

    const v1, 0x7f0b01d3

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/video/editor/ui/menu/TrimView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method protected modifyContentGuideline(Landroidx/constraintlayout/widget/Guideline;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/menu/TrimView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0602da

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    return-void
.end method

.method protected modifyTopGuideline(Landroidx/constraintlayout/widget/Guideline;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/ui/menu/TrimView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0602dd

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    return-void
.end method
