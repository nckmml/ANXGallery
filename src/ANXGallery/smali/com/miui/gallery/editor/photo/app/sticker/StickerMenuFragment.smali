.class public Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;
.super Lcom/miui/gallery/editor/photo/app/MenuFragment;
.source "StickerMenuFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/editor/photo/app/MenuFragment<",
        "Lcom/miui/gallery/editor/photo/core/common/fragment/AbstractEffectFragment;",
        "Lcom/miui/gallery/editor/photo/core/common/provider/AbstractStickerProvider;",
        ">;"
    }
.end annotation


# instance fields
.field private mCategories:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/photo/core/common/model/StickerCategory;",
            ">;"
        }
    .end annotation
.end field

.field private mHeaderAdapter:Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;

.field private mHeaderView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

.field private mItemClickListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

.field private mOnStickerSelectedListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

.field private mRecentView:Landroid/view/View;

.field private mStickerPager:Landroidx/viewpager/widget/ViewPager;

.field private mStickerPagerAdapter:Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;

.field private mTitle:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 1

    sget-object v0, Lcom/miui/gallery/editor/photo/core/Effect;->STICKER:Lcom/miui/gallery/editor/photo/core/Effect;

    invoke-direct {p0, v0}, Lcom/miui/gallery/editor/photo/app/MenuFragment;-><init>(Lcom/miui/gallery/editor/photo/core/Effect;)V

    new-instance v0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$2;-><init>(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mItemClickListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$3;-><init>(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mOnStickerSelectedListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mRecentView:Landroid/view/View;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderAdapter:Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)Landroidx/viewpager/widget/ViewPager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPager:Landroidx/viewpager/widget/ViewPager;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;
    .locals 0

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->getRenderFragment()Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mCategories:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$500(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mOnStickerSelectedListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)Lcom/miui/gallery/editor/photo/core/SdkProvider;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mSdkProvider:Lcom/miui/gallery/editor/photo/core/SdkProvider;

    return-object p0
.end method


# virtual methods
.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/editor/photo/app/MenuFragment;->onCreate(Landroid/os/Bundle;)V

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mCategories:Ljava/util/List;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mCategories:Ljava/util/List;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mSdkProvider:Lcom/miui/gallery/editor/photo/core/SdkProvider;

    check-cast v0, Lcom/miui/gallery/editor/photo/core/common/provider/AbstractStickerProvider;

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/core/common/provider/AbstractStickerProvider;->list()Ljava/util/List;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    return-void
.end method

.method public onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 0

    new-instance p1, Lcom/miui/gallery/editor/photo/app/menu/StickerView;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-direct {p1, p2}, Lcom/miui/gallery/editor/photo/app/menu/StickerView;-><init>(Landroid/content/Context;)V

    return-object p1
.end method

.method public onDestroyView()V
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/editor/photo/app/MenuFragment;->onDestroyView()V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPager:Landroidx/viewpager/widget/ViewPager;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPagerAdapter:Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;

    invoke-virtual {v0, v1}, Landroidx/viewpager/widget/ViewPager;->removeOnPageChangeListener(Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;)V

    return-void
.end method

.method public onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
    .locals 4

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/editor/photo/app/MenuFragment;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V

    new-instance p2, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;

    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mCategories:Ljava/util/List;

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    new-instance v1, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f050161

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getColor(I)I

    move-result v2

    invoke-direct {v1, v2}, Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;-><init>(I)V

    invoke-direct {p2, v0, v1}, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;-><init>(Ljava/util/List;Lcom/miui/gallery/editor/photo/widgets/recyclerview/Selectable$Selector;)V

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderAdapter:Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mCategories:Ljava/util/List;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/sticker/CategoryRecent;

    invoke-direct {v0}, Lcom/miui/gallery/editor/photo/app/sticker/CategoryRecent;-><init>()V

    const/4 v1, 0x0

    invoke-interface {p2, v1, v0}, Ljava/util/List;->add(ILjava/lang/Object;)V

    const p2, 0x7f090095

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderAdapter:Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;

    invoke-virtual {p2, v0}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    new-instance p2, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {p2, v0}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;-><init>(Landroid/content/Context;)V

    new-instance v0, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-direct {v0, v2}, Lcom/miui/gallery/ui/StartEndSmoothScrollerController;-><init>(Landroid/content/Context;)V

    invoke-virtual {p2, v0}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->setSmoothScroller(Lcom/miui/gallery/ui/SmoothScrollerController;)V

    invoke-virtual {p2, v1}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->setOrientation(I)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    invoke-virtual {v0, p2}, Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderAdapter:Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mItemClickListener:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;

    invoke-virtual {p2, v0}, Lcom/miui/gallery/editor/photo/app/sticker/HeaderAdapter;->setOnItemClickListener(Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;)V

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mHeaderView:Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView;

    invoke-static {p2}, Lcom/miui/gallery/widget/overscroll/HorizontalOverScrollBounceEffectDecorator;->setOverScrollEffect(Landroidx/recyclerview/widget/RecyclerView;)V

    new-instance p2, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;

    invoke-direct {p2, p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;-><init>(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)V

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPagerAdapter:Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;

    const p2, 0x7f0903cc

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroidx/viewpager/widget/ViewPager;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPager:Landroidx/viewpager/widget/ViewPager;

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPager:Landroidx/viewpager/widget/ViewPager;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPagerAdapter:Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;

    invoke-virtual {p2, v0}, Landroidx/viewpager/widget/ViewPager;->setAdapter(Landroidx/viewpager/widget/PagerAdapter;)V

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPager:Landroidx/viewpager/widget/ViewPager;

    const/4 v0, 0x1

    invoke-virtual {p2, v0, v1}, Landroidx/viewpager/widget/ViewPager;->setCurrentItem(IZ)V

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPager:Landroidx/viewpager/widget/ViewPager;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mStickerPagerAdapter:Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$StickerPagerAdapter;

    invoke-virtual {p2, v0}, Landroidx/viewpager/widget/ViewPager;->addOnPageChangeListener(Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;)V

    const p2, 0x7f090297

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mRecentView:Landroid/view/View;

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mRecentView:Landroid/view/View;

    new-instance v0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment$1;-><init>(Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;)V

    invoke-virtual {p2, v0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const p2, 0x7f090366

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mTitle:Landroid/widget/TextView;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/sticker/StickerMenuFragment;->mTitle:Landroid/widget/TextView;

    const p2, 0x7f10066b

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(I)V

    return-void
.end method
