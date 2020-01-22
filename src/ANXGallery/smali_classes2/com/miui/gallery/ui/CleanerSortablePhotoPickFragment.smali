.class public abstract Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;
.super Lcom/miui/gallery/ui/CleanerPhotoPickFragment;
.source "CleanerSortablePhotoPickFragment.java"


# instance fields
.field public mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

.field private mOnSortByClickListener:Landroid/view/View$OnClickListener;

.field public mSortByHeader:Lcom/miui/gallery/widget/SortByHeader;

.field public mSortOrder:Ljava/lang/String;


# direct methods
.method constructor <init>(I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;-><init>(I)V

    sget-object p1, Lcom/miui/gallery/widget/SortByHeader$SortBy;->DATE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    const-string p1, " DESC "

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortOrder:Ljava/lang/String;

    new-instance p1, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$1;-><init>(Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mOnSortByClickListener:Landroid/view/View$OnClickListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;Landroid/content/CursorLoader;Lcom/miui/gallery/widget/SortByHeader$SortBy;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->configLoader(Landroid/content/CursorLoader;Lcom/miui/gallery/widget/SortByHeader$SortBy;)V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;)I
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getSortByIndicatorResource()I

    move-result p0

    return p0
.end method

.method private configLoader(Landroid/content/CursorLoader;Lcom/miui/gallery/widget/SortByHeader$SortBy;)V
    .locals 1

    invoke-virtual {p0, p2}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getUri(Lcom/miui/gallery/widget/SortByHeader$SortBy;)Landroid/net/Uri;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/content/CursorLoader;->setUri(Landroid/net/Uri;)V

    sget-object v0, Lcom/miui/gallery/adapter/AlbumDetailTimeLineAdapter;->PROJECTION:[Ljava/lang/String;

    invoke-virtual {p1, v0}, Landroid/content/CursorLoader;->setProjection([Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getSelection()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/content/CursorLoader;->setSelection(Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getSelectionArgs()[Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/content/CursorLoader;->setSelectionArgs([Ljava/lang/String;)V

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->configOrderBy(Lcom/miui/gallery/widget/SortByHeader$SortBy;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setSortOrder(Ljava/lang/String;)V

    return-void
.end method

.method private configOrderBy(Lcom/miui/gallery/widget/SortByHeader$SortBy;)Ljava/lang/String;
    .locals 3

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getSortByString(Lcom/miui/gallery/widget/SortByHeader$SortBy;)Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    const-string v2, " DESC "

    if-ne v1, p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortOrder:Ljava/lang/String;

    invoke-static {p1, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_0

    const-string v2, " ASC "

    :cond_0
    iput-object v2, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortOrder:Ljava/lang/String;

    goto :goto_0

    :cond_1
    iput-object v2, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortOrder:Ljava/lang/String;

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    :goto_0
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortOrder:Ljava/lang/String;

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->onSortByChanged()V

    return-object p1
.end method

.method private getSortByIndicatorResource()I
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortOrder:Ljava/lang/String;

    const-string v1, " ASC "

    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const v0, 0x7f0702d8

    goto :goto_0

    :cond_0
    const v0, 0x7f0702d5

    :goto_0
    return v0
.end method

.method private getSortByString(Lcom/miui/gallery/widget/SortByHeader$SortBy;)Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment$2;->$SwitchMap$com$miui$gallery$widget$SortByHeader$SortBy:[I

    invoke-virtual {p1}, Lcom/miui/gallery/widget/SortByHeader$SortBy;->ordinal()I

    move-result p1

    aget p1, v0, p1

    const/4 v0, 0x1

    if-eq p1, v0, :cond_1

    const/4 v0, 0x2

    if-eq p1, v0, :cond_0

    const-string p1, "alias_sort_time"

    return-object p1

    :cond_0
    const-string p1, "size"

    return-object p1

    :cond_1
    const-string p1, "title"

    return-object p1
.end method

.method private onSortByChanged()V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getAdapter()Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter;->setCurrentSortBy(Lcom/miui/gallery/widget/SortByHeader$SortBy;)V

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {v0}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->clearChoices()V

    return-void
.end method


# virtual methods
.method protected getUri()Landroid/net/Uri;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getUri(Lcom/miui/gallery/widget/SortByHeader$SortBy;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method protected getUri(Lcom/miui/gallery/widget/SortByHeader$SortBy;)Landroid/net/Uri;
    .locals 2

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Media;->URI_OWNER_ALBUM_MEDIA:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    sget-object v1, Lcom/miui/gallery/widget/SortByHeader$SortBy;->DATE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    if-ne p1, v1, :cond_0

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object p1

    const/4 v0, 0x1

    invoke-static {v0}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v0

    const-string v1, "generate_headers"

    invoke-virtual {p1, v1, v0}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object p1

    invoke-virtual {p1}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    :cond_0
    return-object v0
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object p1

    const p2, 0x7f09030f

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/widget/SortByHeader;

    iput-object p2, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortByHeader:Lcom/miui/gallery/widget/SortByHeader;

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortByHeader:Lcom/miui/gallery/widget/SortByHeader;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mOnSortByClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/widget/SortByHeader;->setOnSortByClickListener(Landroid/view/View$OnClickListener;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mSortByHeader:Lcom/miui/gallery/widget/SortByHeader;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->mCurrentSortBy:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-direct {p0}, Lcom/miui/gallery/ui/CleanerSortablePhotoPickFragment;->getSortByIndicatorResource()I

    move-result v0

    invoke-virtual {p2, p3, v0}, Lcom/miui/gallery/widget/SortByHeader;->updateCurrentSortView(Lcom/miui/gallery/widget/SortByHeader$SortBy;I)V

    return-object p1
.end method
