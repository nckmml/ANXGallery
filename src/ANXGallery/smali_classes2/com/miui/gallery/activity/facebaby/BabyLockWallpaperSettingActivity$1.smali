.class Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;
.super Ljava/lang/Object;
.source "BabyLockWallpaperSettingActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->onCreate(Landroid/os/Bundle;)V
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

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$000(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->isEmpty()Z

    move-result p1

    xor-int/lit8 p1, p1, 0x1

    iget-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {v0}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$100(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    const-string v0, "com.miui.gallery.cloud.baby.wallpaper_provider"

    invoke-static {p1, v0}, Lcom/miui/keyguard/LockScreenHelper;->setLockWallpaperProvider(Landroid/content/ContentResolver;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->getInstance()Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->refresh()V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$200(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/keyguard/LockScreenHelper;->setLockWallpaperProvider(Landroid/content/ContentResolver;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    const v0, 0x7f1000b3

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    :cond_1
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->finish()V

    return-void
.end method
