.class public abstract Lcom/miui/gallery/editor/ui/menu/BaseMenuView;
.super Landroid/widget/FrameLayout;
.source "BaseMenuView.java"


# instance fields
.field protected mBottomLine:Landroidx/constraintlayout/widget/Guideline;

.field protected mConfirmLayout:Landroid/widget/FrameLayout;

.field private mConfirmView:Landroid/view/View;

.field protected mContentLine:Landroidx/constraintlayout/widget/Guideline;

.field protected mContext:Landroid/content/Context;

.field protected mMenuLayout:Landroid/widget/FrameLayout;

.field private mMenuView:Landroid/view/View;

.field protected mTopLayout:Landroid/widget/FrameLayout;

.field protected mTopLine:Landroidx/constraintlayout/widget/Guideline;

.field private mTopView:Landroid/view/View;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->init(Landroid/content/Context;)V

    return-void
.end method

.method private addChildViewToParent(Landroid/view/ViewGroup;Landroid/view/View;)V
    .locals 0

    if-eqz p1, :cond_1

    if-nez p2, :cond_0

    goto :goto_0

    :cond_0
    invoke-virtual {p1, p2}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private inflateView()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mContext:Landroid/content/Context;

    sget v1, Lcom/miui/gallery/editor/R$layout;->common_editor_bottom_layout:I

    invoke-static {v0, v1, p0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    sget v0, Lcom/miui/gallery/editor/R$id;->layout_bottom_area:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mConfirmLayout:Landroid/widget/FrameLayout;

    sget v0, Lcom/miui/gallery/editor/R$id;->layout_content_area:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mMenuLayout:Landroid/widget/FrameLayout;

    sget v0, Lcom/miui/gallery/editor/R$id;->layout_top_area:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mTopLayout:Landroid/widget/FrameLayout;

    sget v0, Lcom/miui/gallery/editor/R$id;->top_guide_line:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mTopLine:Landroidx/constraintlayout/widget/Guideline;

    sget v0, Lcom/miui/gallery/editor/R$id;->content_guide_line:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mContentLine:Landroidx/constraintlayout/widget/Guideline;

    sget v0, Lcom/miui/gallery/editor/R$id;->bottom_guide_line:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mBottomLine:Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->initTopView()Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mTopView:Landroid/view/View;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->initContentView()Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mMenuView:Landroid/view/View;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->initBottomView()Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mConfirmView:Landroid/view/View;

    iget-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mTopLayout:Landroid/widget/FrameLayout;

    iget-object v1, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mTopView:Landroid/view/View;

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->addChildViewToParent(Landroid/view/ViewGroup;Landroid/view/View;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mMenuLayout:Landroid/widget/FrameLayout;

    iget-object v1, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mMenuView:Landroid/view/View;

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->addChildViewToParent(Landroid/view/ViewGroup;Landroid/view/View;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mConfirmLayout:Landroid/widget/FrameLayout;

    iget-object v1, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mConfirmView:Landroid/view/View;

    invoke-direct {p0, v0, v1}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->addChildViewToParent(Landroid/view/ViewGroup;Landroid/view/View;)V

    return-void
.end method

.method private init(Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mContext:Landroid/content/Context;

    invoke-direct {p0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->inflateView()V

    invoke-direct {p0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->modifyGuideLinePos()V

    return-void
.end method

.method private modifyGuideLinePos()V
    .locals 1

    sget v0, Lcom/miui/gallery/editor/R$id;->top_guide_line:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->modifyTopGuideline(Landroidx/constraintlayout/widget/Guideline;)V

    sget v0, Lcom/miui/gallery/editor/R$id;->content_guide_line:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->modifyContentGuideline(Landroidx/constraintlayout/widget/Guideline;)V

    sget v0, Lcom/miui/gallery/editor/R$id;->bottom_guide_line:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->modifyBottomGuideline(Landroidx/constraintlayout/widget/Guideline;)V

    return-void
.end method


# virtual methods
.method public getConfirmView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/ui/menu/BaseMenuView;->mConfirmView:Landroid/view/View;

    return-object v0
.end method

.method protected abstract initBottomView()Landroid/view/View;
.end method

.method protected abstract initContentView()Landroid/view/View;
.end method

.method protected abstract initTopView()Landroid/view/View;
.end method

.method protected abstract modifyBottomGuideline(Landroidx/constraintlayout/widget/Guideline;)V
.end method

.method protected abstract modifyContentGuideline(Landroidx/constraintlayout/widget/Guideline;)V
.end method

.method protected abstract modifyTopGuideline(Landroidx/constraintlayout/widget/Guideline;)V
.end method
