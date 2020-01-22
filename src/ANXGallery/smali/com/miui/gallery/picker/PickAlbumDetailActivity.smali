.class public Lcom/miui/gallery/picker/PickAlbumDetailActivity;
.super Lcom/miui/gallery/picker/PickAlbumDetailActivityBase;
.source "PickAlbumDetailActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/picker/PickAlbumDetailActivityBase;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreate(Landroid/os/Bundle;)V
    .locals 8

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickAlbumDetailActivityBase;->onCreate(Landroid/os/Bundle;)V

    iget-object p1, p0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    if-nez p1, :cond_0

    return-void

    :cond_0
    const p1, 0x7f0b0149

    invoke-virtual {p0, p1}, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->setContentView(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p1

    const v0, 0x7f090048

    invoke-virtual {p1, v0}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/picker/PickAlbumDetailFragment;

    iput-object p1, p0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-wide/16 v0, -0x1

    const-string v2, "album_id"

    invoke-virtual {p1, v2, v0, v1}, Landroid/content/Intent;->getLongExtra(Ljava/lang/String;J)J

    move-result-wide v2

    const-string v4, "album_name"

    invoke-virtual {p1, v4}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const/4 v5, 0x0

    const-string v6, "other_share_album"

    invoke-virtual {p1, v6, v5}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v6

    const-string v7, "pano_album"

    invoke-virtual {p1, v7, v5}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result p1

    cmp-long v0, v2, v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->finish()V

    return-void

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;

    iget-object v1, p0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->mItemStateListener:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$ItemStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;->setItemStateListener(Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase$ItemStateListener;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;

    check-cast v0, Lcom/miui/gallery/picker/PickAlbumDetailFragment;

    invoke-virtual {v0, v2, v3}, Lcom/miui/gallery/picker/PickAlbumDetailFragment;->setAlbumId(J)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;

    check-cast v0, Lcom/miui/gallery/picker/PickAlbumDetailFragment;

    invoke-virtual {v0, v6}, Lcom/miui/gallery/picker/PickAlbumDetailFragment;->setIsOtherSharedAlbum(Z)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/picker/PickAlbumDetailFragmentBase;

    check-cast v0, Lcom/miui/gallery/picker/PickAlbumDetailFragment;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/picker/PickAlbumDetailFragment;->setIsPanoAlbum(Z)V

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_2

    invoke-virtual {p0, v4}, Lcom/miui/gallery/picker/PickAlbumDetailActivity;->setTitle(Ljava/lang/CharSequence;)V

    :cond_2
    return-void
.end method
