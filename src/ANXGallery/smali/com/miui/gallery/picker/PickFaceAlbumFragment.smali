.class public Lcom/miui/gallery/picker/PickFaceAlbumFragment;
.super Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;
.source "PickFaceAlbumFragment.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;,
        Lcom/miui/gallery/picker/PickFaceAlbumFragment$FacePagePhotoLoaderCallback;
    }
.end annotation


# instance fields
.field private mAdapter:Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

.field private mAlbumItemCheckListener:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$AlbumItemCheckListener;

.field private mFacePagePhotoLoaderCallback:Lcom/miui/gallery/picker/PickFaceAlbumFragment$FacePagePhotoLoaderCallback;

.field private mLocalIdOfAlbum:J

.field private mServerIdOfAlbum:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    const-string v0, "face-album"

    invoke-direct {p0, v0}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;-><init>(Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/picker/PickFaceAlbumFragment;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mServerIdOfAlbum:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/picker/PickFaceAlbumFragment;)J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mLocalIdOfAlbum:J

    return-wide v0
.end method

.method static synthetic access$200(Lcom/miui/gallery/picker/PickFaceAlbumFragment;)Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAdapter:Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/picker/PickFaceAlbumFragment;)Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$AlbumItemCheckListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumItemCheckListener:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$AlbumItemCheckListener;

    return-object p0
.end method


# virtual methods
.method protected getKey(Landroid/database/Cursor;)J
    .locals 2

    invoke-static {p1}, Lcom/miui/gallery/picker/helper/CursorUtils;->getFacePhotoId(Landroid/database/Cursor;)J

    move-result-wide v0

    return-wide v0
.end method

.method protected getLocalPath(Landroid/database/Cursor;)Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAdapter:Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;->getItemPath(Landroid/database/Cursor;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method protected getPageName()Ljava/lang/String;
    .locals 1

    const-string v0, "picker_face_album"

    return-object v0
.end method

.method protected getPreviewOrder()Ljava/lang/String;
    .locals 1

    const-string v0, "dateTaken DESC "

    return-object v0
.end method

.method protected getPreviewSelection(Landroid/database/Cursor;)Ljava/lang/String;
    .locals 0

    const/4 p1, 0x0

    return-object p1
.end method

.method protected getPreviewSelectionArgs(Landroid/database/Cursor;)[Ljava/lang/String;
    .locals 3

    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\'"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Lcom/miui/gallery/picker/helper/CursorUtils;->getFaceId(Landroid/database/Cursor;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 v1, 0x0

    aput-object p1, v0, v1

    return-object v0
.end method

.method protected getPreviewUri()Landroid/net/Uri;
    .locals 1

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$PeopleFace;->RECOMMEND_FACES_OF_ONE_PERSON_URI:Landroid/net/Uri;

    return-object v0
.end method

.method protected getUri()Landroid/net/Uri;
    .locals 1

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$PeopleFace;->ONE_PERSON_URI:Landroid/net/Uri;

    return-object v0
.end method

.method protected isPreviewFace()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public onActivityCreated(Landroid/os/Bundle;)V
    .locals 3

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;->onActivityCreated(Landroid/os/Bundle;)V

    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mActivity:Landroid/app/Activity;

    invoke-virtual {p1}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-string v0, "server_id_of_album"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mServerIdOfAlbum:Ljava/lang/String;

    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mActivity:Landroid/app/Activity;

    invoke-virtual {p1}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-string v0, "local_id_of_album"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mLocalIdOfAlbum:J

    new-instance p1, Lcom/miui/gallery/picker/PickFaceAlbumFragment$FacePagePhotoLoaderCallback;

    const/4 v0, 0x0

    invoke-direct {p1, p0, v0}, Lcom/miui/gallery/picker/PickFaceAlbumFragment$FacePagePhotoLoaderCallback;-><init>(Lcom/miui/gallery/picker/PickFaceAlbumFragment;Lcom/miui/gallery/picker/PickFaceAlbumFragment$1;)V

    iput-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mFacePagePhotoLoaderCallback:Lcom/miui/gallery/picker/PickFaceAlbumFragment$FacePagePhotoLoaderCallback;

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object p1

    iget-object v1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mFacePagePhotoLoaderCallback:Lcom/miui/gallery/picker/PickFaceAlbumFragment$FacePagePhotoLoaderCallback;

    const/4 v2, 0x1

    invoke-virtual {p1, v2, v0, v1}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumDetailGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    invoke-virtual {v0}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->getFirstVisiblePosition()I

    move-result v0

    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    const/4 v1, 0x2

    if-ne p1, v1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumDetailGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    invoke-static {}, Lcom/miui/gallery/Config$ThumbConfig;->get()Lcom/miui/gallery/Config$ThumbConfig;

    move-result-object v1

    iget v1, v1, Lcom/miui/gallery/Config$ThumbConfig;->sMicroThumbColumnsLand:I

    invoke-virtual {p1, v1}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->setNumColumns(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumDetailGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    invoke-static {}, Lcom/miui/gallery/Config$ThumbConfig;->get()Lcom/miui/gallery/Config$ThumbConfig;

    move-result-object v1

    iget v1, v1, Lcom/miui/gallery/Config$ThumbConfig;->sMicroThumbColumnsPortrait:I

    invoke-virtual {p1, v1}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->setNumColumns(I)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumDetailGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    invoke-virtual {p1, v0}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->setSelection(I)V

    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;->onCreate(Landroid/os/Bundle;)V

    new-instance p1, Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

    iget-object v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mActivity:Landroid/app/Activity;

    invoke-direct {p1, p0, v0}, Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;-><init>(Lcom/miui/gallery/picker/PickFaceAlbumFragment;Landroid/content/Context;)V

    iput-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAdapter:Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mActivity:Landroid/app/Activity;

    invoke-virtual {p1}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-string v0, "need_pick_face_id"

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAdapter:Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

    invoke-virtual {p1}, Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;->changeDisplayMode()V

    :cond_0
    return-void
.end method

.method public onDestroy()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAdapter:Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;->swapCursor(Landroid/database/Cursor;)Landroid/database/Cursor;

    :cond_0
    invoke-super {p0}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;->onDestroy()V

    return-void
.end method

.method public onInflateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 1

    const p3, 0x7f0b00a6

    const/4 v0, 0x0

    invoke-virtual {p1, p3, p2, v0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    const p2, 0x7f09017a

    invoke-virtual {p1, p2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p2

    check-cast p2, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    iput-object p2, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumDetailGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumDetailGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->getGridViewOnItemClickListener()Landroid/widget/AdapterView$OnItemClickListener;

    move-result-object p3

    invoke-virtual {p2, p3}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    iget-object p2, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumDetailGridView:Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;

    iget-object p3, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAdapter:Lcom/miui/gallery/picker/PickFaceAlbumFragment$PickAlbumDetailTimeLineAdapter;

    invoke-virtual {p2, p3}, Lcom/tonicartos/widget/stickygridheaders/StickyGridHeadersGridView;->setAdapter(Landroid/widget/ListAdapter;)V

    new-instance p2, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$AlbumItemCheckListener;

    iget-object p3, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-direct {p2, p0, p3}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$AlbumItemCheckListener;-><init>(Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;Lcom/miui/gallery/picker/helper/Picker;)V

    iput-object p2, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mAlbumItemCheckListener:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$AlbumItemCheckListener;

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->initialSelections()V

    return-object p1
.end method

.method protected onPhotoItemClick(Landroid/database/Cursor;)Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mActivity:Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v1, "need_pick_face_id"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p1}, Lcom/miui/gallery/picker/helper/CursorUtils;->getFaceId(Landroid/database/Cursor;)Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0, p1}, Lcom/miui/gallery/picker/helper/Picker;->pick(Ljava/lang/String;)Z

    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mActivity:Landroid/app/Activity;

    check-cast p1, Lcom/miui/gallery/picker/PickAlbumDetailActivityBase;

    const/4 v0, -0x1

    invoke-virtual {p1, v0}, Lcom/miui/gallery/picker/PickAlbumDetailActivityBase;->setResultCode(I)V

    iget-object p1, p0, Lcom/miui/gallery/picker/PickFaceAlbumFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {p1}, Lcom/miui/gallery/picker/helper/Picker;->done()V

    const/4 p1, 0x1

    return p1

    :cond_0
    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;->onPhotoItemClick(Landroid/database/Cursor;)Z

    move-result p1

    return p1
.end method

.method protected supportFoldBurstItems()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method
