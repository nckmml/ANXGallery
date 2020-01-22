.class Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;
.super Ljava/lang/Object;
.source "BabyLockWallpaperSettingActivity.java"

# interfaces
.implements Landroid/widget/CompoundButton$OnCheckedChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->refresh()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

.field final synthetic val$path:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    iput-object p2, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->val$path:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCheckedChanged(Landroid/widget/CompoundButton;Z)V
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$300(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Ljava/util/Map;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->val$path:Ljava/lang/String;

    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-interface {p1, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz p2, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$000(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Ljava/util/Set;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->val$path:Ljava/lang/String;

    invoke-interface {p1, p2}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->this$0:Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;->access$000(Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity;)Ljava/util/Set;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/activity/facebaby/BabyLockWallpaperSettingActivity$5;->val$path:Ljava/lang/String;

    invoke-interface {p1, p2}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    :goto_0
    return-void
.end method
