.class public Lcom/miui/gallery/activity/AlbumDetailActivity;
.super Lcom/miui/gallery/activity/BaseActivity;
.source "AlbumDetailActivity.java"


# instance fields
.field private mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/BaseActivity;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/activity/AlbumDetailActivity;)Lcom/miui/gallery/ui/AlbumDetailFragment;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    return-object p0
.end method

.method static synthetic access$002(Lcom/miui/gallery/activity/AlbumDetailActivity;Lcom/miui/gallery/ui/AlbumDetailFragment;)Lcom/miui/gallery/ui/AlbumDetailFragment;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    return-object p1
.end method


# virtual methods
.method protected getFragmentContainerId()I
    .locals 1

    const v0, 0x1020002

    return v0
.end method

.method protected hasCustomContentView()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 1

    invoke-super {p0, p1, p2, p3}, Lcom/miui/gallery/activity/BaseActivity;->onActivityResult(IILandroid/content/Intent;)V

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1, p2, p3}, Lcom/miui/gallery/ui/AlbumDetailFragment;->onActivityResult(IILandroid/content/Intent;)V

    :cond_0
    return-void
.end method

.method public onBackPressed()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AlbumDetailFragment;->onBackPressed()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-super {p0}, Lcom/miui/gallery/activity/BaseActivity;->onBackPressed()V

    :cond_0
    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 3

    invoke-super {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/AlbumDetailActivity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    const-string v1, "android.intent.action.VIEW"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Lcom/miui/gallery/activity/AlbumDetailActivity$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/activity/AlbumDetailActivity$1;-><init>(Lcom/miui/gallery/activity/AlbumDetailActivity;)V

    invoke-virtual {p1}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object p1

    invoke-static {p0, p1, v0}, Lcom/miui/gallery/ui/JumpDialogFragment;->obtainAlbumIntent(Landroid/app/Activity;Landroid/net/Uri;Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;)V

    goto :goto_0

    :cond_0
    new-instance p1, Lcom/miui/gallery/ui/AlbumDetailFragment;

    invoke-direct {p1}, Lcom/miui/gallery/ui/AlbumDetailFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    iget-object p1, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    const/4 v0, 0x0

    const/4 v1, 0x1

    const-string v2, "albumDetail"

    invoke-virtual {p0, p1, v2, v0, v1}, Lcom/miui/gallery/activity/AlbumDetailActivity;->startFragment(Landroid/app/Fragment;Ljava/lang/String;ZZ)V

    :goto_0
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AlbumDetailFragment;->isVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    invoke-virtual {p0}, Lcom/miui/gallery/activity/AlbumDetailActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v1

    invoke-virtual {v0, p1, v1}, Lcom/miui/gallery/ui/AlbumDetailFragment;->onCreateOptionsMenu(Landroid/view/Menu;Landroid/view/MenuInflater;)V

    const/4 p1, 0x1

    return p1

    :cond_0
    const/4 p1, 0x0

    return p1
.end method

.method public onDestroy()V
    .locals 0

    invoke-super {p0}, Lcom/miui/gallery/activity/BaseActivity;->onDestroy()V

    return-void
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AlbumDetailFragment;->isVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/AlbumDetailFragment;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x1

    return p1

    :cond_0
    invoke-super {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result p1

    return p1
.end method

.method public onPrepareOptionsMenu(Landroid/view/Menu;)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AlbumDetailFragment;->isVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity;->mAlbumDetailFragment:Lcom/miui/gallery/ui/AlbumDetailFragment;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/AlbumDetailFragment;->onPrepareOptionsMenu(Landroid/view/Menu;)V

    const/4 p1, 0x1

    return p1

    :cond_0
    invoke-super {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onPrepareOptionsMenu(Landroid/view/Menu;)Z

    move-result p1

    return p1
.end method
