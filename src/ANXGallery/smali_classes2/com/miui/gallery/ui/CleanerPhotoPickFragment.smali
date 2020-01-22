.class public abstract Lcom/miui/gallery/ui/CleanerPhotoPickFragment;
.super Lcom/miui/gallery/ui/PhotoListFragmentBase;
.source "CleanerPhotoPickFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;
    }
.end annotation


# static fields
.field public static final DELETE_COUNT_STAGE:[I


# instance fields
.field public mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

.field public mDataSetObserver:Landroid/database/DataSetObserver;

.field public mDeleteButton:Landroid/widget/Button;

.field public mDeleteButtonClickListener:Landroid/view/View$OnClickListener;

.field public mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

.field public mIsFirstLoadFinish:Z

.field public mMultiChoiceModeListener:Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;

.field public mScanResultIds:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field public mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

.field public mSelectButton:Landroid/widget/Button;

.field public mSelectListener:Landroid/view/View$OnClickListener;

.field public mType:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x6

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->DELETE_COUNT_STAGE:[I

    return-void

    nop

    :array_0
    .array-data 4
        0x14
        0x32
        0x64
        0xc8
        0x1f4
        0x3e8
    .end array-data
.end method

.method constructor <init>(I)V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/ui/PhotoListFragmentBase;-><init>()V

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mIsFirstLoadFinish:Z

    new-instance v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$1;-><init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDataSetObserver:Landroid/database/DataSetObserver;

    new-instance v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$2;-><init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mMultiChoiceModeListener:Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;

    new-instance v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$3;-><init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDeleteButtonClickListener:Landroid/view/View$OnClickListener;

    new-instance v0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$4;-><init>(Lcom/miui/gallery/ui/CleanerPhotoPickFragment;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectListener:Landroid/view/View$OnClickListener;

    iput p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mType:I

    return-void
.end method


# virtual methods
.method protected createAdapter()Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;
    .locals 2

    new-instance v0, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    iget-object v1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-direct {v0, v1}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method protected getAdapter()Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    return-object v0
.end method

.method protected getDeleteMessage()Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;
    .locals 1

    const/4 v0, 0x0

    return-object v0
.end method

.method protected getSelection()Ljava/lang/String;
    .locals 3

    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    const-string v2, "_id"

    aput-object v2, v0, v1

    iget-object v1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mScanResultIds:Ljava/util/List;

    const-string v2, ","

    invoke-static {v2, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    aput-object v1, v0, v2

    const-string v1, "%s IN (%s)"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected getSelectionArgs()[Ljava/lang/String;
    .locals 1

    const/4 v0, 0x0

    return-object v0
.end method

.method protected getUri()Landroid/net/Uri;
    .locals 3

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Media;->URI_OWNER_ALBUM_MEDIA:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v0

    const/4 v1, 0x1

    invoke-static {v1}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v1

    const-string v2, "generate_headers"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/ui/PhotoListFragmentBase;->onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;

    move-result-object p1

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->createAdapter()Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    sget-object p3, Lcom/miui/gallery/widget/SortByHeader$SortBy;->DATE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->setCurrentSortBy(Lcom/miui/gallery/widget/SortByHeader$SortBy;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    sget-object p3, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->NORMAL:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->setAlbumType(Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDataSetObserver:Landroid/database/DataSetObserver;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->registerDataSetObserver(Landroid/database/DataSetObserver;)V

    new-instance p2, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAlbumDetailGridView:Landroid/widget/GridView;

    invoke-direct {p2, p3}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;-><init>(Landroid/widget/AbsListView;)V

    iput-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setAdapter(Landroid/widget/ListAdapter;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mEmptyView:Landroid/view/View;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setEmptyView(Landroid/view/View;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->getGridViewOnItemClickListener()Landroid/widget/AdapterView$OnItemClickListener;

    move-result-object p3

    invoke-virtual {p2, p3}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mMultiChoiceModeListener:Lcom/miui/gallery/widget/editwrapper/SimpleMultiChoiceModeListener;

    invoke-virtual {p2, p3}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setMultiChoiceModeListener(Lcom/miui/gallery/widget/editwrapper/MultiChoiceModeListener;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {p2}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->disableScaleImageViewAniWhenInActionMode()V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {p2}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->startChoiceMode()V

    const p2, 0x7f0900ef

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/Button;

    iput-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDeleteButton:Landroid/widget/Button;

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDeleteButton:Landroid/widget/Button;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDeleteButtonClickListener:Landroid/view/View$OnClickListener;

    invoke-virtual {p2, p3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    invoke-virtual {p2}, Lcom/miui/gallery/activity/BaseActivity;->getActionBar()Lmiui/app/ActionBar;

    move-result-object p2

    invoke-virtual {p2}, Lmiui/app/ActionBar;->getCustomView()Landroid/view/View;

    move-result-object p2

    const p3, 0x7f09010e

    invoke-virtual {p2, p3}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Landroid/widget/Button;

    iput-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectButton:Landroid/widget/Button;

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectButton:Landroid/widget/Button;

    const/4 v0, 0x0

    invoke-static {p2, p3, v0}, Lmiui/gallery/support/MiuiSdkCompat;->setEditActionModeButton(Landroid/content/Context;Landroid/widget/Button;I)V

    iget-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectButton:Landroid/widget/Button;

    iget-object p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectListener:Landroid/view/View$OnClickListener;

    invoke-virtual {p2, p3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-static {}, Lcom/miui/gallery/cleaner/ScannerManager;->getInstance()Lcom/miui/gallery/cleaner/ScannerManager;

    move-result-object p2

    iget p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mType:I

    invoke-virtual {p2, p3}, Lcom/miui/gallery/cleaner/ScannerManager;->getScanner(I)Lcom/miui/gallery/cleaner/BaseScanner;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->resetScanResult()V

    return-object p1
.end method

.method public onItemSelectedChanged()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mAdapter:Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;

    invoke-virtual {v0}, Lcom/miui/gallery/adapter/CleanerPhotoPickBaseAdapter;->getCount()I

    move-result v0

    const/4 v1, 0x0

    if-lez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDeleteButton:Landroid/widget/Button;

    iget-object v2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {v2}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->getCheckedItemCount()I

    move-result v2

    if-lez v2, :cond_0

    const/4 v2, 0x1

    goto :goto_0

    :cond_0
    move v2, v1

    :goto_0
    invoke-virtual {v0, v2}, Landroid/widget/Button;->setEnabled(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectButton:Landroid/widget/Button;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mActivity:Lcom/miui/gallery/activity/BaseActivity;

    iget-object v1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectButton:Landroid/widget/Button;

    iget-object v2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    invoke-virtual {v2}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->isAllItemsChecked()Z

    move-result v2

    invoke-static {v0, v1, v2}, Lmiui/gallery/support/MiuiSdkCompat;->setEditActionModeButton(Landroid/content/Context;Landroid/widget/Button;I)V

    goto :goto_1

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mDeleteButton:Landroid/widget/Button;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setEnabled(Z)V

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mSelectButton:Landroid/widget/Button;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setVisibility(I)V

    :goto_1
    return-void
.end method

.method protected abstract recordCancelSelectAllEvent()V
.end method

.method protected resetCheckState()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mGridViewWrapper:Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/gallery/widget/editwrapper/EditableListViewWrapperDeprecated;->setAllItemsCheckState(Z)V

    return-void
.end method

.method protected resetScanResult()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mScanner:Lcom/miui/gallery/cleaner/BaseScanner;

    invoke-virtual {v0}, Lcom/miui/gallery/cleaner/BaseScanner;->getScanResultIds()Ljava/util/List;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment;->mScanResultIds:Ljava/util/List;

    return-void
.end method
