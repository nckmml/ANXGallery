.class public Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;
.super Lcom/miui/gallery/editor/ui/menu/BaseMenuView;
.source "MiuiBeautyView.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method protected initBottomView()Landroid/view/View;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->mContext:Landroid/content/Context;

    const v1, 0x7f0b0130

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method protected initContentView()Landroid/view/View;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->mContext:Landroid/content/Context;

    const v1, 0x7f0b012c

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method protected initTopView()Landroid/view/View;
    .locals 1

    const/4 v0, 0x0

    return-object v0
.end method

.method protected modifyBottomGuideline(Landroidx/constraintlayout/widget/Guideline;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0601ea

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    return-void
.end method

.method protected modifyContentGuideline(Landroidx/constraintlayout/widget/Guideline;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0601ed

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    return-void
.end method

.method protected modifyTopGuideline(Landroidx/constraintlayout/widget/Guideline;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f060262

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    return-void
.end method

.method public updateGuideLine(Ljava/lang/Object;)V
    .locals 2

    const-class v0, Lcom/miui/gallery/editor/photo/app/miuibeautify/SmartBeautyFragment;

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->mContentLine:Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0601e5

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    goto :goto_0

    :cond_0
    const-class v0, Lcom/miui/gallery/editor/photo/app/miuibeautify/DoubleParameterBeautyFragment;

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->mContentLine:Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0601de

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    goto :goto_0

    :cond_1
    const-class v0, Lcom/miui/gallery/editor/photo/app/miuibeautify/SingleParameterBeautyFragment;

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->mContentLine:Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0601e4

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    goto :goto_0

    :cond_2
    const-class v0, Lcom/miui/gallery/editor/photo/app/miuibeautify/SwitchParameterBeautyFragment;

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->mContentLine:Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0601e9

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    goto :goto_0

    :cond_3
    const-class v0, Lcom/miui/gallery/editor/photo/app/miuibeautify/MiuiBeautyFragment;

    invoke-virtual {v0, p1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->mContentLine:Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/menu/MiuiBeautyView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0601ed

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroidx/constraintlayout/widget/Guideline;->setGuidelineEnd(I)V

    :cond_4
    :goto_0
    return-void
.end method
