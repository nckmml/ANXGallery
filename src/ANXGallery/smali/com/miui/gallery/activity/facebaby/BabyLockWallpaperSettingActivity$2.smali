.class Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;
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

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$100(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    const/4 p2, 0x0

    invoke-static {p1, p2}, Lcom/miui/keyguard/LockScreenHelper;->setLockWallpaperProvider(Landroid/content/ContentResolver;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    const p2, 0x7f1000b3

    invoke-static {p1, p2}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$2;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->finish()V

    return-void
.end method
