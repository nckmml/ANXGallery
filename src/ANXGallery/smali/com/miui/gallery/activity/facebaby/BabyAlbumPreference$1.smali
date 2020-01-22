.class Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$1;
.super Ljava/lang/Object;
.source "BabyLockWallpaperSettingActivity.java"

# interfaces
.implements Landroid/widget/CompoundButton$OnCheckedChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->onBindView(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCheckedChanged(Landroid/widget/CompoundButton;Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;

    invoke-static {v0}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->access$000(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;)Landroid/widget/CompoundButton$OnCheckedChangeListener;

    move-result-object v0

    invoke-interface {v0, p1, p2}, Landroid/widget/CompoundButton$OnCheckedChangeListener;->onCheckedChanged(Landroid/widget/CompoundButton;Z)V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$1;->this$0:Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;

    invoke-static {p1, p2}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;->access$102(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;Z)Z

    return-void
.end method
