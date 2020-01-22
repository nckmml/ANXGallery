.class public Lcom/miui/gallery/ui/settingmain/SettingMainActivity;
.super Lcom/miui/gallery/base_optimization/mvp/view/BaseToolBarActivity;
.source "SettingMainActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/base_optimization/mvp/view/BaseToolBarActivity;-><init>()V

    return-void
.end method

.method private loadFragment()V
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/settingmain/SettingMainActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v1, "extra_to_type"

    const/4 v2, -0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v0

    const/16 v1, 0x3e9

    const v2, 0x7f090162

    if-eq v0, v1, :cond_1

    const/16 v1, 0x3ea

    if-eq v0, v1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/ui/settingmain/SettingMainActivity;->finish()V

    goto :goto_0

    :cond_0
    const v0, 0x7f100057

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/settingmain/SettingMainActivity;->setTitle(I)V

    invoke-static {}, Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;->newInstance()Lcom/miui/gallery/ui/album/cloudalbum/CloudAlbumListFragment;

    move-result-object v0

    invoke-virtual {p0, v2, v0}, Lcom/miui/gallery/ui/settingmain/SettingMainActivity;->loadRootFragment(ILandroid/app/Fragment;)V

    goto :goto_0

    :cond_1
    const v0, 0x7f10005f

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/settingmain/SettingMainActivity;->setTitle(I)V

    invoke-static {}, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->newInstance()Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    move-result-object v0

    invoke-virtual {p0, v2, v0}, Lcom/miui/gallery/ui/settingmain/SettingMainActivity;->loadRootFragment(ILandroid/app/Fragment;)V

    :goto_0
    return-void
.end method


# virtual methods
.method public getLayoutId()I
    .locals 1

    const v0, 0x7f0b0022

    return v0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/base_optimization/mvp/view/BaseToolBarActivity;->onCreate(Landroid/os/Bundle;)V

    invoke-direct {p0}, Lcom/miui/gallery/ui/settingmain/SettingMainActivity;->loadFragment()V

    return-void
.end method
