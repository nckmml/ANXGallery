.class Lcom/miui/gallery/activity/HomePageActivity$4;
.super Ljava/lang/Object;
.source "HomePageActivity.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/HomePageActivity;->bindMTCameraRemoteService()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/activity/HomePageActivity;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/HomePageActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 5

    const-string v0, "HomePageActivity"

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    invoke-static {v1}, Lcom/miui/gallery/activity/HomePageActivity;->access$1000(Lcom/miui/gallery/activity/HomePageActivity;)Landroid/content/ServiceConnection;

    move-result-object v1

    if-eqz v1, :cond_0

    return-void

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    new-instance v2, Lcom/miui/gallery/activity/HomePageActivity$4$1;

    invoke-direct {v2, p0}, Lcom/miui/gallery/activity/HomePageActivity$4$1;-><init>(Lcom/miui/gallery/activity/HomePageActivity$4;)V

    invoke-static {v1, v2}, Lcom/miui/gallery/activity/HomePageActivity;->access$1002(Lcom/miui/gallery/activity/HomePageActivity;Landroid/content/ServiceConnection;)Landroid/content/ServiceConnection;

    new-instance v1, Landroid/content/Intent;

    invoke-direct {v1}, Landroid/content/Intent;-><init>()V

    new-instance v2, Landroid/content/ComponentName;

    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    invoke-static {v3}, Lcom/miui/gallery/activity/HomePageActivity;->access$1100(Lcom/miui/gallery/activity/HomePageActivity;)Ljava/lang/String;

    move-result-object v3

    iget-object v4, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    invoke-static {v4}, Lcom/miui/gallery/activity/HomePageActivity;->access$1200(Lcom/miui/gallery/activity/HomePageActivity;)Ljava/lang/String;

    move-result-object v4

    invoke-direct {v2, v3, v4}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v2}, Landroid/content/Intent;->setComponent(Landroid/content/ComponentName;)Landroid/content/Intent;

    :try_start_0
    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    invoke-static {v3}, Lcom/miui/gallery/activity/HomePageActivity;->access$1000(Lcom/miui/gallery/activity/HomePageActivity;)Landroid/content/ServiceConnection;

    move-result-object v3

    const/4 v4, 0x5

    invoke-virtual {v2, v1, v3, v4}, Lcom/miui/gallery/activity/HomePageActivity;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z

    const-string v1, "bind CameraRemoteService"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v1

    iget-object v2, p0, Lcom/miui/gallery/activity/HomePageActivity$4;->this$0:Lcom/miui/gallery/activity/HomePageActivity;

    const/4 v3, 0x0

    invoke-static {v2, v3}, Lcom/miui/gallery/activity/HomePageActivity;->access$1002(Lcom/miui/gallery/activity/HomePageActivity;Landroid/content/ServiceConnection;)Landroid/content/ServiceConnection;

    const-string v2, "bind CameraRemoteService failed"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_0
    return-void
.end method
