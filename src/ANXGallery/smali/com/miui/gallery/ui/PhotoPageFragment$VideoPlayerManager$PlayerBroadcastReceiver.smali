.class Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;
.super Landroid/content/BroadcastReceiver;
.source "PhotoPageFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "PlayerBroadcastReceiver"
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;Lcom/miui/gallery/ui/PhotoPageFragment$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;-><init>(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;)V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 5

    if-nez p2, :cond_0

    return-void

    :cond_0
    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    const-string v1, "com.miui.gallery.action.VIDEO_PLAYER_STARTED"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    const-string v2, "VideoPlayerManager"

    const/4 v3, 0x0

    if-eqz v1, :cond_1

    const-string p1, "onReceive VideoPlayer started"

    invoke-static {v2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    invoke-static {p1, v3}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->access$8300(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;Z)V

    goto/16 :goto_1

    :cond_1
    const-string v1, "com.miui.gallery.action.VIDEO_PLAYER_RETURN"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6

    const-string v0, "onReceive VideoPlayer return"

    invoke-static {v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v0, 0x1

    const-string v1, "action_bar_visible"

    invoke-virtual {p2, v1, v0}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    iget-object v2, v2, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {v2}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$1600(Lcom/miui/gallery/ui/PhotoPageFragment;)Z

    move-result v2

    if-nez v2, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    invoke-static {v2, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->access$8400(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;Z)V

    :cond_2
    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    invoke-static {v1, v0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->access$8300(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;Z)V

    const-wide/16 v1, -0x1

    const-string v4, "seek_time"

    invoke-virtual {p2, v4, v1, v2}, Landroid/content/Intent;->getLongExtra(Ljava/lang/String;J)J

    move-result-wide v1

    const-string v4, "relative_index"

    invoke-virtual {p2, v4, v3}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result p2

    if-eqz p2, :cond_4

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    if-eqz v1, :cond_3

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    iget-object v2, v2, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    iget-object v2, v2, Lcom/miui/gallery/ui/PhotoPageFragment;->mPager:Lcom/miui/gallery/widget/ViewPager;

    invoke-virtual {v2}, Lcom/miui/gallery/widget/ViewPager;->getCurrentItem()I

    move-result v2

    add-int/2addr v2, p2

    invoke-virtual {v1, v2, v3}, Lcom/miui/gallery/widget/ViewPager;->setCurrentItem(IZ)V

    :cond_3
    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->prepareFinish(Landroid/content/Context;)V

    goto :goto_0

    :cond_4
    const-wide/16 v3, 0x0

    cmp-long p2, v1, v3

    if-ltz p2, :cond_5

    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    iget-object p2, p2, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {p2}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2500(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;

    move-result-object p2

    if-eqz p2, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->this$0:Lcom/miui/gallery/ui/PhotoPageFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/PhotoPageFragment;->access$2500(Lcom/miui/gallery/ui/PhotoPageFragment;)Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;

    move-result-object p1

    invoke-virtual {p1, v1, v2, v0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPreviewManager;->seekTo(JZ)V

    goto :goto_0

    :cond_5
    iget-object p2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->prepareFinish(Landroid/content/Context;)V

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager$PlayerBroadcastReceiver;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;

    invoke-static {p1, v0}, Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;->access$8502(Lcom/miui/gallery/ui/PhotoPageFragment$VideoPlayerManager;Z)Z

    :cond_6
    :goto_1
    return-void
.end method
