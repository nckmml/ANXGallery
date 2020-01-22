.class Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;
.super Landroid/preference/Preference;
.source "BabyLockWallpaperSettingActivity.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryHandler;,
        Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;
    }
.end annotation


# static fields
.field public static sDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;


# instance fields
.field private mBabyAlbum:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;

.field private mChecked:Z

.field private mOnCheckedChangeListener:Landroid/widget/CompoundButton$OnCheckedChangeListener;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    invoke-direct {v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;-><init>()V

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->cacheThumbnail(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->loadFromMicroCache(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->considerExifParams(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    const v2, 0x7f070110

    invoke-virtual {v0, v2}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->showStubImage(I)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->resetViewBeforeLoading(Z)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    sget-object v1, Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;->EXACTLY:Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->imageScaleType(Lcom/nostra13/universalimageloader/core/assist/ImageScaleType;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    sget-object v1, Landroid/graphics/Bitmap$Config;->RGB_565:Landroid/graphics/Bitmap$Config;

    invoke-virtual {v0, v1}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->bitmapConfig(Landroid/graphics/Bitmap$Config;)Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;->build()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->sDisplayImageOptions:Lcom/nostra13/universalimageloader/core/DisplayImageOptions;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;Landroid/widget/CompoundButton$OnCheckedChangeListener;Z)V
    .locals 0

    invoke-direct {p0, p1}, Landroid/preference/Preference;-><init>(Landroid/content/Context;)V

    iput-object p2, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mBabyAlbum:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;

    iput-object p3, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mOnCheckedChangeListener:Landroid/widget/CompoundButton$OnCheckedChangeListener;

    iput-boolean p4, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mChecked:Z

    const p1, 0x7f0b004f

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->setLayoutResource(I)V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;)Landroid/widget/CompoundButton$OnCheckedChangeListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mOnCheckedChangeListener:Landroid/widget/CompoundButton$OnCheckedChangeListener;

    return-object p0
.end method

.method static synthetic access$102(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mChecked:Z

    return p1
.end method


# virtual methods
.method protected onBindView(Landroid/view/View;)V
    .locals 5

    invoke-super {p0, p1}, Landroid/preference/Preference;->onBindView(Landroid/view/View;)V

    const v0, 0x7f09006b

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mBabyAlbum:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;

    iget-object v1, v1, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;->name:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const v0, 0x1020001

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    new-instance v1, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$1;

    invoke-direct {v1, p0}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$1;-><init>(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;)V

    invoke-virtual {v0, v1}, Landroid/widget/CheckBox;->setOnCheckedChangeListener(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V

    iget-boolean v1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mChecked:Z

    invoke-virtual {v0, v1}, Landroid/widget/CheckBox;->setChecked(Z)V

    new-instance v1, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$2;

    invoke-direct {v1, p0, v0}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$2;-><init>(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;Landroid/widget/CheckBox;)V

    invoke-virtual {p1, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMiscPool()Lcom/miui/gallery/threadpool/ThreadPool;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;

    iget-object v2, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mBabyAlbum:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;

    iget-wide v2, v2, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;->localId:J

    iget-object v4, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->mBabyAlbum:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;

    iget-boolean v4, v4, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$BabyAlbumInfo;->isOtherShared:Z

    invoke-direct {v1, v2, v3, v4}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;-><init>(JZ)V

    new-instance v2, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryHandler;

    invoke-direct {v2, p1}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryHandler;-><init>(Landroid/view/View;)V

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/threadpool/ThreadPool;->submit(Lcom/miui/gallery/threadpool/ThreadPool$Job;Lcom/miui/gallery/threadpool/FutureListener;)Lcom/miui/gallery/threadpool/Future;

    return-void
.end method
