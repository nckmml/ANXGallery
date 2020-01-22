.class Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$3;
.super Ljava/lang/Object;
.source "BabyLockWallpaperSettingActivity.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->onExit()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$3;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$3;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$100(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$3;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    const-string p2, "com.miui.gallery.cloud.baby.wallpaper_provider"

    invoke-static {p1, p2}, Lcom/miui/keyguard/LockScreenHelper;->setLockWallpaperProvider(Landroid/content/ContentResolver;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->getInstance()Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->refresh()V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$3;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->finish()V

    return-void
.end method
