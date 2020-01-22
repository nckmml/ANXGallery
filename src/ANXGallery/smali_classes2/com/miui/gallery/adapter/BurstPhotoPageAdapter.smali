.class public Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;
.super Lcom/miui/gallery/adapter/PhotoPageAdapter;
.source "BurstPhotoPageAdapter.java"


# instance fields
.field private mMaxPagerItemWidth:I

.field private mPagerView:Lcom/miui/gallery/widget/ViewPager;


# direct methods
.method public constructor <init>(ILcom/miui/gallery/model/ImageLoadParams;Lcom/miui/gallery/util/photoview/ItemViewInfo;Lcom/miui/gallery/adapter/PhotoPageAdapter$OnPreviewedListener;Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;)V
    .locals 0

    invoke-direct/range {p0 .. p5}, Lcom/miui/gallery/adapter/PhotoPageAdapter;-><init>(ILcom/miui/gallery/model/ImageLoadParams;Lcom/miui/gallery/util/photoview/ItemViewInfo;Lcom/miui/gallery/adapter/PhotoPageAdapter$OnPreviewedListener;Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;)V

    return-void
.end method


# virtual methods
.method protected getLayoutId(I)I
    .locals 0

    const p1, 0x7f0b0139

    return p1
.end method

.method public getPageWidth(I)F
    .locals 5

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->getDataItem(I)Lcom/miui/gallery/model/BaseDataItem;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->mPagerView:Lcom/miui/gallery/widget/ViewPager;

    if-eqz v1, :cond_2

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getHeight()I

    move-result v1

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getWidth()I

    move-result v2

    iget-object v3, p0, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->mPagerView:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v3}, Lcom/miui/gallery/widget/ViewPager;->getHeight()I

    move-result v3

    iget-object v4, p0, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->mPagerView:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v4}, Lcom/miui/gallery/widget/ViewPager;->getWidth()I

    move-result v4

    if-lez v3, :cond_2

    if-lez v4, :cond_2

    if-lez v1, :cond_2

    if-lez v2, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getOrientation()I

    move-result p1

    invoke-static {p1}, Lcom/miui/gallery/util/ExifUtil;->isWidthHeightRotated(I)Z

    move-result p1

    const/high16 v0, 0x3f800000    # 1.0f

    if-eqz p1, :cond_0

    int-to-float p1, v3

    mul-float/2addr p1, v0

    int-to-float v0, v1

    mul-float/2addr p1, v0

    int-to-float v0, v2

    goto :goto_0

    :cond_0
    int-to-float p1, v3

    mul-float/2addr p1, v0

    int-to-float v0, v2

    mul-float/2addr p1, v0

    int-to-float v0, v1

    :goto_0
    div-float/2addr p1, v0

    iget v0, p0, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->mMaxPagerItemWidth:I

    int-to-float v1, v0

    cmpl-float v1, p1, v1

    if-lez v1, :cond_1

    int-to-float p1, v0

    :cond_1
    int-to-float v0, v4

    div-float/2addr p1, v0

    return p1

    :cond_2
    invoke-super {p0, p1}, Lcom/miui/gallery/adapter/PhotoPageAdapter;->getPageWidth(I)F

    move-result p1

    return p1
.end method

.method protected getViewType(I)I
    .locals 0

    const/4 p1, 0x4

    return p1
.end method

.method protected getViewTypeCount()I
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/adapter/PhotoPageAdapter;->getViewTypeCount()I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    return v0
.end method

.method protected isTypeMatch(Ljava/lang/Object;I)Z
    .locals 1

    invoke-static {p1}, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->isItemValidate(Ljava/lang/Object;)Z

    move-result p2

    const/4 v0, 0x0

    if-eqz p2, :cond_0

    check-cast p1, Lcom/miui/gallery/ui/PhotoPageItem;

    const p2, 0x7f09033f

    invoke-virtual {p1, p2}, Lcom/miui/gallery/ui/PhotoPageItem;->getTag(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    const/4 p2, 0x4

    if-ne p1, p2, :cond_0

    const/4 v0, 0x1

    :cond_0
    return v0
.end method

.method public setMaxPagerItemWidth(I)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->mMaxPagerItemWidth:I

    return-void
.end method

.method public setPagerView(Lcom/miui/gallery/widget/ViewPager;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/adapter/BurstPhotoPageAdapter;->mPagerView:Lcom/miui/gallery/widget/ViewPager;

    return-void
.end method
