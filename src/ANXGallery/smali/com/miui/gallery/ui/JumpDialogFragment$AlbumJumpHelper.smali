.class Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;
.super Ljava/lang/Object;
.source "JumpDialogFragment.java"

# interfaces
.implements Landroid/app/LoaderManager$LoaderCallbacks;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/JumpDialogFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "AlbumJumpHelper"
.end annotation


# instance fields
.field private mAlbum:Lcom/miui/gallery/model/Album;

.field private mAlbumLoadTime:J

.field private mFragment:Landroid/app/Fragment;

.field private mHandleIntentCallback:Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;

.field private mShareAlbumCursor:Landroid/database/Cursor;

.field private mShareAlbumsCursorHelper:Lcom/miui/gallery/util/ShareAlbumsCursorHelper;

.field private mUri:Landroid/net/Uri;

.field private mUriParameters:Landroid/os/Bundle;

.field final synthetic this$0:Lcom/miui/gallery/ui/JumpDialogFragment;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/ui/JumpDialogFragment;Landroid/app/Fragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->this$0:Lcom/miui/gallery/ui/JumpDialogFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    return-void
.end method

.method private createJumpIntent()Landroid/content/Intent;
    .locals 14

    iget-object v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return-object v0

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v0

    iget-object v2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v2}, Lcom/miui/gallery/model/Album;->getLocalPath()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, v0, v1, v2}, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->getShortCutIntent(JLjava/lang/String;)Landroid/content/Intent;

    move-result-object v2

    if-nez v2, :cond_3

    iget-object v2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v2}, Lcom/miui/gallery/model/Album;->getAttributes()J

    move-result-wide v2

    iget-object v4, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v4}, Lcom/miui/gallery/model/Album;->getServerId()Ljava/lang/String;

    move-result-object v4

    iget-object v5, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v5}, Lcom/miui/gallery/model/Album;->getLocalizedAlbumName()Ljava/lang/String;

    move-result-object v5

    iget-object v6, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v6}, Lcom/miui/gallery/model/Album;->getLocalPath()Ljava/lang/String;

    move-result-object v6

    iget-object v7, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v7}, Lcom/miui/gallery/model/Album;->getBabyInfo()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_1

    new-instance v7, Landroid/content/Intent;

    iget-object v8, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {v8}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v8

    const-class v9, Lcom/miui/gallery/activity/facebaby/BabyAlbumDetailActivity;

    invoke-direct {v7, v8, v9}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    iget-object v8, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v8}, Lcom/miui/gallery/model/Album;->getPeopleId()Ljava/lang/String;

    move-result-object v8

    const-string v9, "people_id"

    invoke-virtual {v7, v9, v8}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v8, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v8}, Lcom/miui/gallery/model/Album;->getBabyInfo()Ljava/lang/String;

    move-result-object v8

    const-string v9, "baby_info"

    invoke-virtual {v7, v9, v8}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v8, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v8}, Lcom/miui/gallery/model/Album;->getThumbnailInfoOfBaby()Ljava/lang/String;

    move-result-object v8

    const-string v9, "thumbnail_info_of_baby"

    invoke-virtual {v7, v9, v8}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v8, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v8}, Lcom/miui/gallery/model/Album;->getBabyShareInfo()Ljava/lang/String;

    move-result-object v8

    const-string v9, "baby_sharer_info"

    invoke-virtual {v7, v9, v8}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    goto :goto_0

    :cond_1
    new-instance v7, Landroid/content/Intent;

    const-string v8, "com.miui.gallery.action.VIEW_ALBUM_DETAIL"

    invoke-direct {v7, v8}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    :goto_0
    const-wide/16 v8, 0x2

    invoke-static {v8, v9}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v8, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v8

    iget-object v9, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v9}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v9

    invoke-static {v9, v10}, Lcom/miui/gallery/provider/ShareAlbumManager;->isOtherShareAlbumId(J)Z

    move-result v9

    iget-object v10, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mShareAlbumsCursorHelper:Lcom/miui/gallery/util/ShareAlbumsCursorHelper;

    invoke-virtual {v10, v0, v1}, Lcom/miui/gallery/util/ShareAlbumsCursorHelper;->isOwnerShareAlbum(J)Z

    move-result v10

    iget-object v11, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-static {v11}, Lcom/miui/gallery/adapter/AlbumPageAdapter;->isAutoUploadedAlbum(Lcom/miui/gallery/model/Album;)Z

    move-result v11

    const/4 v12, 0x1

    xor-int/2addr v11, v12

    const-string v13, "other_share_album"

    invoke-virtual {v7, v13, v9}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    const-string v9, "owner_share_album"

    invoke-virtual {v7, v9, v10}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    const-string v9, "is_local_album"

    invoke-virtual {v7, v9, v11}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    const-string v9, "screenshot_album"

    invoke-virtual {v7, v9, v8}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    invoke-static {v0, v1}, Lcom/miui/gallery/util/AlbumsCursorHelper;->isPanoAlbum(J)Z

    move-result v9

    const-string v10, "pano_album"

    invoke-virtual {v7, v10, v9}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    const-string v9, "album_id"

    invoke-virtual {v7, v9, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    const-string v0, "album_name"

    invoke-virtual {v7, v0, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v1}, Lcom/miui/gallery/model/Album;->isImmutable()Z

    move-result v1

    const-string v5, "album_unwriteable"

    invoke-virtual {v7, v5, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    if-eqz v8, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUri:Landroid/net/Uri;

    const-string v8, "screenshotAppName"

    invoke-virtual {v1, v8}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v8

    if-nez v8, :cond_2

    const-string v8, "screenshot_app_name"

    invoke-virtual {v7, v8, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    invoke-virtual {v7, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    invoke-virtual {v7, v5, v12}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    :cond_2
    const-string v0, "album_server_id"

    invoke-virtual {v7, v0, v4}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    const-string v0, "attributes"

    invoke-virtual {v7, v0, v2, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    const-string v0, "album_local_path"

    invoke-virtual {v7, v0, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    move-object v2, v7

    :cond_3
    return-object v2
.end method

.method private generateAlbumSelection(Landroid/os/Bundle;)Ljava/lang/String;
    .locals 5

    const-string v0, "id"

    const-wide/16 v1, -0x1

    invoke-virtual {p1, v0, v1, v2}, Landroid/os/Bundle;->getLong(Ljava/lang/String;J)J

    move-result-wide v1

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    const/4 v2, 0x1

    const/4 v3, 0x0

    const/4 v4, 0x2

    if-ltz v1, :cond_0

    new-array v1, v4, [Ljava/lang/Object;

    const-string v4, "_id"

    aput-object v4, v1, v3

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getLong(Ljava/lang/String;)J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    aput-object p1, v1, v2

    const-string p1, "%s=%s"

    invoke-static {p1, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const-string v0, "serverId"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_1

    new-array v1, v4, [Ljava/lang/Object;

    aput-object v0, v1, v3

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v1, v2

    const-string p1, "%s=\'%s\'"

    invoke-static {p1, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_1
    new-array v0, v4, [Ljava/lang/Object;

    const-string v1, "local_path"

    aput-object v1, v0, v3

    const-string v1, "localPath"

    invoke-virtual {p1, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v0, v2

    const-string p1, "%s=\'%s\' COLLATE NOCASE"

    invoke-static {p1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    :goto_0
    return-object p1
.end method

.method private getShortCutIntent(JLjava/lang/String;)Landroid/content/Intent;
    .locals 1

    invoke-static {p1, p2}, Lcom/miui/gallery/util/AlbumsCursorHelper;->isFaceAlbum(J)Z

    move-result p3

    if-eqz p3, :cond_0

    new-instance p1, Landroid/content/Intent;

    const-string p2, "com.miui.gallery.action.VIEW_PEOPLE"

    invoke-direct {p1, p2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    return-object p1

    :cond_0
    invoke-static {p1, p2}, Lcom/miui/gallery/util/AlbumsCursorHelper;->isRecentAlbum(J)Z

    move-result p1

    if-eqz p1, :cond_1

    new-instance p1, Landroid/content/Intent;

    const-string p2, "android.intent.action.VIEW"

    invoke-direct {p1, p2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    sget-object p2, Lcom/miui/gallery/provider/GalleryContract$RecentAlbum;->VIEW_PAGE_URI:Landroid/net/Uri;

    invoke-virtual {p2}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object p2

    const-string p3, "source"

    const-string v0, "album_page"

    invoke-virtual {p2, p3, v0}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object p2

    invoke-virtual {p2}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {p2}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-virtual {p2}, Landroid/app/Activity;->getPackageName()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    return-object p1

    :cond_1
    const/4 p1, 0x0

    return-object p1
.end method

.method private loadShareData()V
    .locals 4

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    iget-object v1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-virtual {v1}, Lcom/miui/gallery/model/Album;->getAlbumId()J

    move-result-wide v1

    const-string v3, "id"

    invoke-virtual {v0, v3, v1, v2}, Landroid/os/Bundle;->putLong(Ljava/lang/String;J)V

    iget-object v1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {v1}, Landroid/app/Fragment;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object v1

    const/4 v2, 0x2

    invoke-virtual {v1, v2, v0, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    return-void
.end method


# virtual methods
.method public onCreateLoader(ILandroid/os/Bundle;)Landroid/content/Loader;
    .locals 5

    const/4 v0, 0x1

    if-eq p1, v0, :cond_2

    const/4 v1, 0x3

    const/4 v2, 0x2

    if-eq p1, v2, :cond_1

    if-eq p1, v1, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbumLoadTime:J

    new-instance p1, Lcom/miui/gallery/loader/AlbumSnapshotLoader;

    iget-object v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {v0}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {p1, v0}, Lcom/miui/gallery/loader/AlbumSnapshotLoader;-><init>(Landroid/content/Context;)V

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->generateAlbumSelection(Landroid/os/Bundle;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/miui/gallery/loader/AlbumSnapshotLoader;->setSelection(Ljava/lang/String;)V

    return-object p1

    :cond_1
    new-instance p1, Landroid/content/CursorLoader;

    iget-object v3, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {v3}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v3

    invoke-direct {p1, v3}, Landroid/content/CursorLoader;-><init>(Landroid/content/Context;)V

    sget-object v3, Lcom/miui/gallery/provider/GalleryContract$Album;->URI_SHARE_ALL:Landroid/net/Uri;

    invoke-virtual {p1, v3}, Landroid/content/CursorLoader;->setUri(Landroid/net/Uri;)V

    sget-object v3, Lcom/miui/gallery/util/ShareAlbumsCursorHelper;->SHARED_ALBUM_PROJECTION:[Ljava/lang/String;

    invoke-virtual {p1, v3}, Landroid/content/CursorLoader;->setProjection([Ljava/lang/String;)V

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v3, 0x0

    const-string v4, "count"

    aput-object v4, v1, v3

    const-string v3, "_id"

    aput-object v3, v1, v0

    const-string v0, "id"

    invoke-virtual {p2, v0}, Landroid/os/Bundle;->getLong(Ljava/lang/String;)J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    aput-object p2, v1, v2

    const-string p2, "%s>0 AND %s=%s"

    invoke-static {p2, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setSelection(Ljava/lang/String;)V

    return-object p1

    :cond_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbumLoadTime:J

    new-instance p1, Lcom/miui/gallery/loader/AsyncContentLoader;

    iget-object v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {v0}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/loader/AlbumConvertCallback;

    invoke-direct {v1}, Lcom/miui/gallery/loader/AlbumConvertCallback;-><init>()V

    invoke-direct {p1, v0, v1}, Lcom/miui/gallery/loader/AsyncContentLoader;-><init>(Landroid/content/Context;Lcom/miui/gallery/loader/CursorConvertCallback;)V

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI_ALL_EXCEPT_DELETED:Landroid/net/Uri;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/loader/AsyncContentLoader;->setUri(Landroid/net/Uri;)V

    sget-object v0, Lcom/miui/gallery/model/AlbumConstants;->PROJECTION:[Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/loader/AsyncContentLoader;->setProjection([Ljava/lang/String;)V

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->generateAlbumSelection(Landroid/os/Bundle;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/miui/gallery/loader/AsyncContentLoader;->setSelection(Ljava/lang/String;)V

    return-object p1
.end method

.method public onLoadFinished(Landroid/content/Loader;Ljava/lang/Object;)V
    .locals 6

    invoke-virtual {p1}, Landroid/content/Loader;->getId()I

    move-result p1

    const/4 v0, 0x0

    const-string v1, "JumpDialogFragment"

    const v2, 0x7f100060

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-eq p1, v4, :cond_5

    const/4 v5, 0x2

    if-eq p1, v5, :cond_3

    const/4 v2, 0x3

    if-eq p1, v2, :cond_0

    goto/16 :goto_2

    :cond_0
    if-eqz p2, :cond_1

    move-object v3, p2

    check-cast v3, Ljava/util/List;

    :cond_1
    if-eqz v3, :cond_2

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result p1

    if-lez p1, :cond_2

    invoke-interface {v3, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/model/Album;

    iput-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-direct {p0}, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->loadShareData()V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {p1}, Landroid/app/Fragment;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUriParameters:Landroid/os/Bundle;

    invoke-virtual {p1, v4, p2, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    :goto_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    iget-wide v2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbumLoadTime:J

    sub-long/2addr p1, v2

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const-string p2, "load album from snapshot cost %d"

    invoke-static {v1, p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_2

    :cond_3
    check-cast p2, Landroid/database/Cursor;

    iput-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mShareAlbumCursor:Landroid/database/Cursor;

    new-instance p1, Lcom/miui/gallery/util/ShareAlbumsCursorHelper;

    invoke-direct {p1}, Lcom/miui/gallery/util/ShareAlbumsCursorHelper;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mShareAlbumsCursorHelper:Lcom/miui/gallery/util/ShareAlbumsCursorHelper;

    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mShareAlbumsCursorHelper:Lcom/miui/gallery/util/ShareAlbumsCursorHelper;

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mShareAlbumCursor:Landroid/database/Cursor;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/util/ShareAlbumsCursorHelper;->setSharedAlbums(Landroid/database/Cursor;)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->createJumpIntent()Landroid/content/Intent;

    move-result-object p1

    if-eqz p1, :cond_4

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mHandleIntentCallback:Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;

    invoke-interface {p2, p1}, Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;->onHandleIntent(Landroid/content/Intent;)V

    goto :goto_2

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mHandleIntentCallback:Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {p2}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    iget-object v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {v0, v2}, Landroid/app/Fragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, p2, v0}, Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;->onHandleFailed(Landroid/content/Context;Ljava/lang/String;)V

    goto :goto_2

    :cond_5
    if-eqz p2, :cond_6

    move-object v3, p2

    check-cast v3, Ljava/util/List;

    :cond_6
    if-eqz v3, :cond_7

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result p1

    if-lez p1, :cond_7

    invoke-interface {v3, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/model/Album;

    iput-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbum:Lcom/miui/gallery/model/Album;

    invoke-direct {p0}, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->loadShareData()V

    goto :goto_1

    :cond_7
    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mHandleIntentCallback:Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {p2}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    iget-object v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {v0, v2}, Landroid/app/Fragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, p2, v0}, Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;->onHandleFailed(Landroid/content/Context;Ljava/lang/String;)V

    :goto_1
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    iget-wide v2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mAlbumLoadTime:J

    sub-long/2addr p1, v2

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const-string p2, "load album from db cost %d"

    invoke-static {v1, p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_2
    return-void
.end method

.method public onLoaderReset(Landroid/content/Loader;)V
    .locals 1

    invoke-virtual {p1}, Landroid/content/Loader;->getId()I

    move-result p1

    const/4 v0, 0x1

    if-eq p1, v0, :cond_1

    const/4 v0, 0x2

    if-eq p1, v0, :cond_0

    const/4 v0, 0x3

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mShareAlbumsCursorHelper:Lcom/miui/gallery/util/ShareAlbumsCursorHelper;

    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mShareAlbumCursor:Landroid/database/Cursor;

    if-eqz p1, :cond_1

    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    :cond_1
    :goto_0
    return-void
.end method

.method public startLoading(Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;Landroid/net/Uri;)V
    .locals 8

    iput-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUri:Landroid/net/Uri;

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUri:Landroid/net/Uri;

    const-string v0, "serverId"

    invoke-virtual {p2, v0}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    iget-object v1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUri:Landroid/net/Uri;

    const-string v2, "id"

    invoke-virtual {v1, v2}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    iget-object v3, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUri:Landroid/net/Uri;

    const-string v4, "local_path"

    invoke-virtual {v3, v4}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_0

    invoke-static {v1}, Ljava/lang/Long;->valueOf(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v4

    goto :goto_0

    :cond_0
    const-wide/16 v4, -0x1

    :goto_0
    const-wide/16 v6, 0x0

    cmp-long v1, v4, v6

    if-gez v1, :cond_1

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object p2, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {p2}, Landroid/app/Fragment;->getActivity()Landroid/app/Activity;

    move-result-object p2

    iget-object v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    const v1, 0x7f100060

    invoke-virtual {v0, v1}, Landroid/app/Fragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, p2, v0}, Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;->onHandleFailed(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    :cond_1
    invoke-direct {p0, v4, v5, v3}, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->getShortCutIntent(JLjava/lang/String;)Landroid/content/Intent;

    move-result-object v1

    if-eqz v1, :cond_2

    invoke-interface {p1, v1}, Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;->onHandleIntent(Landroid/content/Intent;)V

    goto :goto_1

    :cond_2
    iput-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mHandleIntentCallback:Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;

    new-instance p1, Landroid/os/Bundle;

    invoke-direct {p1}, Landroid/os/Bundle;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUriParameters:Landroid/os/Bundle;

    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUriParameters:Landroid/os/Bundle;

    invoke-virtual {p1, v0, p2}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUriParameters:Landroid/os/Bundle;

    invoke-virtual {p1, v2, v4, v5}, Landroid/os/Bundle;->putLong(Ljava/lang/String;J)V

    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUriParameters:Landroid/os/Bundle;

    const-string p2, "localPath"

    invoke-virtual {p1, p2, v3}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mFragment:Landroid/app/Fragment;

    invoke-virtual {p1}, Landroid/app/Fragment;->getLoaderManager()Landroid/app/LoaderManager;

    move-result-object p1

    const/4 p2, 0x3

    iget-object v0, p0, Lcom/miui/gallery/ui/JumpDialogFragment$AlbumJumpHelper;->mUriParameters:Landroid/os/Bundle;

    invoke-virtual {p1, p2, v0, p0}, Landroid/app/LoaderManager;->initLoader(ILandroid/os/Bundle;Landroid/app/LoaderManager$LoaderCallbacks;)Landroid/content/Loader;

    :goto_1
    return-void
.end method
