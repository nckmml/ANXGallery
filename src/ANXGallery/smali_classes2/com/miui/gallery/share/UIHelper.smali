.class public Lcom/miui/gallery/share/UIHelper;
.super Ljava/lang/Object;
.source "UIHelper.java"


# direct methods
.method public static showAlbumShareInfo(Landroid/app/Activity;Lcom/miui/gallery/share/Path;I)V
    .locals 2

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/cloud/CloudUtils;->isActive(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_1

    const v0, 0x7f1005e2

    invoke-static {p0, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    :cond_1
    invoke-virtual {p1}, Lcom/miui/gallery/share/Path;->isOtherShared()Z

    move-result v0

    if-nez v0, :cond_3

    invoke-virtual {p1}, Lcom/miui/gallery/share/Path;->isBabyAlbum()Z

    move-result v0

    if-eqz v0, :cond_2

    const-class v0, Lcom/miui/gallery/share/baby/BabyShareAlbumOwnerActivity;

    goto :goto_0

    :cond_2
    const-class v0, Lcom/miui/gallery/share/NormalShareAlbumOwnerActivity;

    goto :goto_0

    :cond_3
    invoke-virtual {p1}, Lcom/miui/gallery/share/Path;->isBabyAlbum()Z

    move-result v0

    if-eqz v0, :cond_4

    const-class v0, Lcom/miui/gallery/share/baby/BabyShareAlbumSharerActivity;

    goto :goto_0

    :cond_4
    const-class v0, Lcom/miui/gallery/share/NormalShareAlbumSharerActivity;

    :goto_0
    new-instance v1, Landroid/content/Intent;

    invoke-direct {v1, p0, v0}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p1}, Lcom/miui/gallery/share/Path;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v0, "share_path"

    invoke-virtual {v1, v0, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    if-eqz v1, :cond_5

    :try_start_0
    invoke-virtual {p0, v1, p2}, Landroid/app/Activity;->startActivityForResult(Landroid/content/Intent;I)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :cond_5
    return-void
.end method

.method public static toast(I)V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/miui/gallery/share/UIHelper;->toast(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public static toast(Landroid/content/Context;Ljava/lang/CharSequence;)V
    .locals 0

    invoke-static {p0, p1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    return-void
.end method

.method private static toast(Ljava/lang/CharSequence;)V
    .locals 0

    return-void
.end method

.method public static toastError(I)V
    .locals 1

    const/4 v0, 0x0

    invoke-static {v0, p0}, Lcom/miui/gallery/share/UIHelper;->toastError(Landroid/content/Context;I)V

    return-void
.end method

.method public static toastError(Landroid/content/Context;I)V
    .locals 2

    const/16 v0, -0x3ea

    const v1, 0x7f100617

    if-eq p1, v0, :cond_8

    const/4 v0, -0x4

    if-eq p1, v0, :cond_7

    if-eqz p1, :cond_9

    const v0, 0xc35c

    if-eq p1, v0, :cond_6

    const v0, 0xc363

    if-eq p1, v0, :cond_5

    const v0, 0xc369

    if-eq p1, v0, :cond_4

    const v0, 0xc36e

    if-eq p1, v0, :cond_3

    const/16 v0, -0x70

    if-eq p1, v0, :cond_2

    const/16 v0, -0x6f

    if-eq p1, v0, :cond_1

    const/16 v0, -0xa

    if-eq p1, v0, :cond_9

    const/16 v0, -0x9

    if-eq p1, v0, :cond_0

    const v1, 0x7f1005ff

    goto :goto_0

    :cond_0
    const v1, 0x7f100408

    goto :goto_0

    :cond_1
    const v1, 0x7f10040b

    goto :goto_0

    :cond_2
    const v1, 0x7f100418

    goto :goto_0

    :cond_3
    const v1, 0x7f10040a

    goto :goto_0

    :cond_4
    const v1, 0x7f1003f3

    goto :goto_0

    :cond_5
    const v1, 0x7f1003f2

    goto :goto_0

    :cond_6
    const v1, 0x7f1003de

    goto :goto_0

    :cond_7
    const v1, 0x7f1003e4

    goto :goto_0

    :cond_8
    const v1, 0x7f1005d6

    :cond_9
    :goto_0
    if-eqz p0, :cond_a

    invoke-virtual {p0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/miui/gallery/share/UIHelper;->toast(Landroid/content/Context;Ljava/lang/CharSequence;)V

    :cond_a
    return-void
.end method
