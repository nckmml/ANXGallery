.class public Lcom/miui/gallery/editor/photo/app/menu/WaterMarkView;
.super Lcom/miui/gallery/editor/ui/menu/type/BaseMenuTopView;
.source "WaterMarkView.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/ui/menu/type/BaseMenuTopView;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method protected initBottomView()Landroid/view/View;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/menu/WaterMarkView;->mContext:Landroid/content/Context;

    const v1, 0x7f0b0136

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/editor/photo/app/menu/WaterMarkView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method

.method protected initContentView()Landroid/view/View;
    .locals 2

    new-instance v0, Lcom/miui/gallery/editor/ui/menu/content/NoScrollViewPagerContentView;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/menu/WaterMarkView;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/miui/gallery/editor/ui/menu/content/NoScrollViewPagerContentView;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method protected initTopView()Landroid/view/View;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/menu/WaterMarkView;->mContext:Landroid/content/Context;

    const v1, 0x7f0b0135

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/editor/photo/app/menu/WaterMarkView;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method
