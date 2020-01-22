.class Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;
.super Ljava/lang/Object;
.source "ExtraPhotoWrapper.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->bindService(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->this$0:Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    iput-object p2, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 6

    iget-object v0, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->this$0:Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    invoke-static {v0}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->access$000(Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;)Z

    move-result v0

    const-string v1, "ExtraPhotoWrapper"

    if-eqz v0, :cond_0

    const-string v0, "has bind service"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    const/4 v0, 0x0

    :try_start_0
    new-instance v2, Landroid/content/Intent;

    const-string v3, "com.miui.extraphoto.action.EXTRA_PHOTO"

    invoke-direct {v2, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v3, "com.miui.extraphoto"

    invoke-virtual {v2, v3}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    iget-object v3, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->val$context:Landroid/content/Context;

    iget-object v4, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->this$0:Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    const/4 v5, 0x1

    invoke-virtual {v3, v2, v4, v5}, Landroid/content/Context;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z

    move-result v2

    iget-object v3, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->this$0:Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    invoke-static {v3, v5}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->access$002(Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;Z)Z

    const-string v3, "bind %s"

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v4

    invoke-static {v1, v3, v4}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    if-nez v2, :cond_1

    iget-object v2, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->this$0:Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    invoke-static {v2, v0}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->access$100(Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;Z)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v2

    invoke-static {v1, v2}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    iget-object v1, p0, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper$1;->this$0:Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    invoke-static {v1, v0}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->access$100(Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;Z)V

    :cond_1
    :goto_0
    return-void
.end method
