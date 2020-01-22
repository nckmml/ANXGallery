.class public Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;
.super Lmiui/preference/PreferenceActivity;
.source "BabyLockWallpaperSettingActivity.java"


# instance fields
.field private mNewStates:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field

.field private mOldStates:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field

.field private mPreferenceGroup:Landroid/preference/PreferenceGroup;

.field private mSelectedAlbums:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lmiui/preference/PreferenceActivity;-><init>()V

    invoke-static {}, Lcom/google/common/collect/Maps;->newHashMap()Ljava/util/HashMap;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mNewStates:Ljava/util/Map;

    invoke-static {}, Lcom/google/common/collect/Maps;->newHashMap()Ljava/util/HashMap;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mOldStates:Ljava/util/Map;

    invoke-static {}, Lcom/google/common/collect/Sets;->newHashSet()Ljava/util/HashSet;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mSelectedAlbums:Ljava/util/Set;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Ljava/util/Set;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mSelectedAlbums:Ljava/util/Set;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->saveState()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->isBabyLockWallpaperInUse()Z

    move-result p0

    return p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Ljava/util/Map;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mNewStates:Ljava/util/Map;

    return-object p0
.end method

.method private isBabyLockWallpaperInUse()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/keyguard/LockScreenHelper;->getLockWallpaperProvider(Landroid/content/ContentResolver;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "com.miui.gallery.cloud.baby.wallpaper_provider"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method private onExit()V
    .locals 10

    iget-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mNewStates:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Boolean;

    iget-object v3, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mOldStates:Ljava/util/Map;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    invoke-interface {v3, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/lang/Boolean;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    :goto_0
    if-nez v0, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->finish()V

    goto :goto_2

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mSelectedAlbums:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_3

    new-instance v0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;-><init>(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V

    const v1, 0x7f10010b

    const v2, 0x104000a

    goto :goto_1

    :cond_3
    new-instance v0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$3;-><init>(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V

    const v1, 0x7f1000b4

    const v2, 0x7f100097

    :goto_1
    move-object v4, v0

    invoke-static {}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->getInstance()Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->refresh()V

    new-instance v5, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$4;

    invoke-direct {v5, p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$4;-><init>(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V

    const/4 v6, 0x0

    invoke-virtual {p0, v1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getString(I)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {p0, v2}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getString(I)Ljava/lang/String;

    move-result-object v8

    const/high16 v9, 0x1040000

    move-object v3, p0

    invoke-static/range {v3 .. v9}, Lcom/miui/gallery/util/DialogUtil;->showConfirmAlertWithCancel(Landroid/content/Context;Landroid/content/DialogInterface$OnClickListener;Landroid/content/DialogInterface$OnClickListener;Ljava/lang/String;Ljava/lang/CharSequence;Ljava/lang/String;I)Landroid/app/AlertDialog;

    :goto_2
    return-void
.end method

.method private refresh()V
    .locals 6

    invoke-static {}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->getInstance()Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->getAllBabyAlbums()Ljava/util/ArrayList;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mPreferenceGroup:Landroid/preference/PreferenceGroup;

    invoke-virtual {v1}, Landroid/preference/PreferenceGroup;->removeAll()V

    iget-object v1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mSelectedAlbums:Ljava/util/Set;

    invoke-interface {v1}, Ljava/util/Set;->clear()V

    if-eqz v0, :cond_2

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;

    iget-wide v2, v1, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;->localId:J

    iget-boolean v4, v1, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;->isOtherShared:Z

    invoke-static {v2, v3, v4}, Lcom/miui/gallery/preference/GalleryPreferences$BabyLock;->createPathSuffix(JZ)Ljava/lang/String;

    move-result-object v2

    iget-object v3, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mNewStates:Ljava/util/Map;

    invoke-interface {v3, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    iget-object v3, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mNewStates:Ljava/util/Map;

    invoke-interface {v3, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Boolean;

    invoke-virtual {v3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v3

    goto :goto_1

    :cond_0
    invoke-static {v2}, Lcom/miui/gallery/preference/GalleryPreferences$BabyLock;->isBabyAlbumForLockWallpaper(Ljava/lang/String;)Z

    move-result v3

    iget-object v4, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mOldStates:Ljava/util/Map;

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v5

    invoke-interface {v4, v2, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :goto_1
    if-eqz v3, :cond_1

    iget-object v4, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mSelectedAlbums:Ljava/util/Set;

    invoke-interface {v4, v2}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    :cond_1
    new-instance v4, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;

    new-instance v5, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;

    invoke-direct {v5, p0, v2}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;-><init>(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;Ljava/lang/String;)V

    invoke-direct {v4, p0, v1, v5, v3}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;-><init>(Landroid/content/Context;Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;Landroid/widget/CompoundButton$OnCheckedChangeListener;Z)V

    iget-object v1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mPreferenceGroup:Landroid/preference/PreferenceGroup;

    invoke-virtual {v1, v4}, Landroid/preference/PreferenceGroup;->addPreference(Landroid/preference/Preference;)Z

    goto :goto_0

    :cond_2
    return-void
.end method

.method private saveState()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mNewStates:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Boolean;

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    invoke-static {v2, v1}, Lcom/miui/gallery/preference/GalleryPreferences$BabyLock;->setBabyAlbumForLockWallpaper(Ljava/lang/String;Z)V

    goto :goto_0

    :cond_0
    return-void
.end method


# virtual methods
.method public onBackPressed()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->onExit()V

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 6

    invoke-super {p0, p1}, Lmiui/preference/PreferenceActivity;->onCreate(Landroid/os/Bundle;)V

    const p1, 0x7f0b0050

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->setContentView(I)V

    const p1, 0x7f140001

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->addPreferencesFromResource(I)V

    const p1, 0x7f090062

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->findViewById(I)Landroid/view/View;

    move-result-object p1

    new-instance v0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;-><init>(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V

    invoke-virtual {p1, v0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    const-string p1, "baby_lock_wallpaper_preference"

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->findPreference(Ljava/lang/CharSequence;)Landroid/preference/Preference;

    move-result-object p1

    check-cast p1, Landroid/preference/PreferenceGroup;

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mPreferenceGroup:Landroid/preference/PreferenceGroup;

    invoke-direct {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->isBabyLockWallpaperInUse()Z

    move-result p1

    if-nez p1, :cond_0

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$BabyLock;->cleanBabyLockWallpaperAlbumState()V

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    if-eqz p1, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-string v0, "album_checked_by_default"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const/4 v1, 0x0

    const-string v2, "is_other_shared"

    invoke-virtual {v0, v2, v1}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    invoke-static {p1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v1

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/preference/GalleryPreferences$BabyLock;->isBabyAlbumForLockWallpaper(JZ)Z

    move-result p1

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "albumid;"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v4, " is share:"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    const-string v5, "faceface"

    invoke-static {v5, v3}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    if-nez p1, :cond_1

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "chect albumid;"

    invoke-virtual {p1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v5, p1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->mNewStates:Ljava/util/Map;

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/preference/GalleryPreferences$BabyLock;->createPathSuffix(JZ)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-interface {p1, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    return-void
.end method

.method protected onDestroy()V
    .locals 0

    invoke-super {p0}, Lmiui/preference/PreferenceActivity;->onDestroy()V

    return-void
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 2

    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    const v1, 0x102002c

    if-eq v0, v1, :cond_0

    invoke-super {p0, p1}, Lmiui/preference/PreferenceActivity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result p1

    return p1

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->onExit()V

    const/4 p1, 0x1

    return p1
.end method

.method public onPause()V
    .locals 1

    invoke-super {p0}, Lmiui/preference/PreferenceActivity;->onPause()V

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->pause()V

    return-void
.end method

.method protected onResume()V
    .locals 1

    invoke-super {p0}, Lmiui/preference/PreferenceActivity;->onResume()V

    invoke-direct {p0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->refresh()V

    invoke-static {}, Lcom/nostra13/universalimageloader/core/ImageLoader;->getInstance()Lcom/nostra13/universalimageloader/core/ImageLoader;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/ImageLoader;->resume()V

    return-void
.end method
