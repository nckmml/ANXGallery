.class Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$2;
.super Ljava/lang/Object;
.source "BabyLockWallpaperSettingActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


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

.field final synthetic val$checkBox:Landroid/widget/CheckBox;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;Landroid/widget/CheckBox;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$2;->this$0:Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;

    iput-object p2, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$2;->val$checkBox:Landroid/widget/CheckBox;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$2;->val$checkBox:Landroid/widget/CheckBox;

    invoke-virtual {p1}, Landroid/widget/CheckBox;->isEnabled()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$2;->val$checkBox:Landroid/widget/CheckBox;

    invoke-virtual {p1}, Landroid/widget/CheckBox;->toggle()V

    :cond_0
    return-void
.end method
