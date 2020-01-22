.class Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;
.super Ljava/lang/Object;
.source "AssistantPageHeaderAdapter.java"

# interfaces
.implements Landroid/view/View$OnAttachStateChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "ViewHolder"
.end annotation


# instance fields
.field mGuideline:Landroidx/constraintlayout/widget/Guideline;

.field mIcon:Landroid/widget/ImageView;

.field mItemView:Landroid/view/View;

.field private mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

.field mRedDot:Landroid/widget/ImageView;

.field private mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

.field mTitle:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mItemView:Landroid/view/View;

    const v0, 0x7f090181

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/constraintlayout/widget/Guideline;

    iput-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mGuideline:Landroidx/constraintlayout/widget/Guideline;

    const v0, 0x7f09019d

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mIcon:Landroid/widget/ImageView;

    const v0, 0x7f090366

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mTitle:Landroid/widget/TextView;

    const v0, 0x7f0902a2

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mRedDot:Landroid/widget/ImageView;

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mItemView:Landroid/view/View;

    invoke-virtual {p1, p0}, Landroid/view/View;->addOnAttachStateChangeListener(Landroid/view/View$OnAttachStateChangeListener;)V

    return-void
.end method


# virtual methods
.method public bindView(Landroid/content/Context;Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mItemView:Landroid/view/View;

    invoke-virtual {v0, p2}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mIcon:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    iget v1, p2, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mIconRes:I

    invoke-virtual {p1, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mTitle:Landroid/widget/TextView;

    iget v0, p2, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mNameRes:I

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(I)V

    rem-int/lit8 p3, p3, 0x3

    if-eqz p3, :cond_2

    const/4 p1, 0x1

    if-eq p3, p1, :cond_1

    const/4 p1, 0x2

    if-eq p3, p1, :cond_0

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mGuideline:Landroidx/constraintlayout/widget/Guideline;

    const p3, 0x3ef0a3d7    # 0.47f

    invoke-virtual {p1, p3}, Landroidx/constraintlayout/widget/Guideline;->setGuidelinePercent(F)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mGuideline:Landroidx/constraintlayout/widget/Guideline;

    const/high16 p3, 0x3f000000    # 0.5f

    invoke-virtual {p1, p3}, Landroidx/constraintlayout/widget/Guideline;->setGuidelinePercent(F)V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mGuideline:Landroidx/constraintlayout/widget/Guideline;

    const p3, 0x3f07ae14    # 0.53f

    invoke-virtual {p1, p3}, Landroidx/constraintlayout/widget/Guideline;->setGuidelinePercent(F)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mRedDot:Landroid/widget/ImageView;

    invoke-virtual {p2}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->getFeatureName()Ljava/lang/String;

    move-result-object p3

    invoke-static {p3}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getRedDotStatus(Ljava/lang/String;)Z

    move-result p3

    if-eqz p3, :cond_3

    const/4 p3, 0x0

    goto :goto_1

    :cond_3
    const/4 p3, 0x4

    :goto_1
    invoke-virtual {p1, p3}, Landroid/widget/ImageView;->setVisibility(I)V

    instance-of p1, p2, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$PhotoMovieEntrance;

    const/4 p3, 0x0

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    if-nez p1, :cond_5

    new-instance p1, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$1;

    invoke-direct {p1, p0, p2}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$1;-><init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;)V

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    goto :goto_2

    :cond_4
    iput-object p3, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    :cond_5
    :goto_2
    invoke-static {}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->replaceRecommend()Z

    move-result p1

    if-eqz p1, :cond_6

    instance-of p1, p2, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$FilterSkyEntrance;

    if-eqz p1, :cond_6

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    if-nez p1, :cond_7

    new-instance p1, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;

    invoke-direct {p1, p0, p2}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;-><init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;)V

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    goto :goto_3

    :cond_6
    iput-object p3, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    :cond_7
    :goto_3
    return-void
.end method

.method public onViewAttachedToWindow(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    if-eqz p1, :cond_0

    invoke-static {}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/util/MovieLibraryLoaderHelper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->addDownloadStateListener(Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    if-eqz p1, :cond_1

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->addDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V

    :cond_1
    return-void
.end method

.method public onViewDetachedFromWindow(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    if-eqz p1, :cond_0

    invoke-static {}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->getInstance()Lcom/miui/gallery/util/MovieLibraryLoaderHelper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mMovieDownloadStateListener:Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/util/MovieLibraryLoaderHelper;->removeDownloadStateListener(Lcom/miui/gallery/util/MovieLibraryLoaderHelper$DownloadStateListener;)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    if-eqz p1, :cond_1

    invoke-static {}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->getInstance()Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mSkyDownloadStateListener:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyCheckHelper;->removeDownloadStateListener(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;)V

    :cond_1
    return-void
.end method
