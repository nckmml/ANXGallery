.class public Lcom/miui/gallery/share/baby/BabyShareAlbumSharerActivity;
.super Lcom/miui/gallery/share/ShareAlbumSharerBaseActivity;
.source "BabyShareAlbumSharerActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/share/ShareAlbumSharerBaseActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected createShareUserAdapter()Lcom/miui/gallery/share/ShareUserAdapterBase;
    .locals 2

    new-instance v0, Lcom/miui/gallery/share/baby/BabyAlbumShareUserAdapter;

    iget-object v1, p0, Lcom/miui/gallery/share/baby/BabyShareAlbumSharerActivity;->mCreatorId:Ljava/lang/String;

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/share/baby/BabyAlbumShareUserAdapter;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object v0
.end method

.method protected getPageName()Ljava/lang/String;
    .locals 1

    const-string v0, "album_baby_share_info"

    return-object v0
.end method

.method protected getPreferencesResourceId()I
    .locals 1

    const v0, 0x7f140002

    return v0
.end method
