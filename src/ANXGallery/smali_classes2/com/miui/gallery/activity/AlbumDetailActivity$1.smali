.class Lcom/miui/gallery/activity/AlbumDetailActivity$1;
.super Ljava/lang/Object;
.source "AlbumDetailActivity.java"

# interfaces
.implements Lcom/miui/gallery/ui/JumpDialogFragment$HandleIntentCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/AlbumDetailActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/activity/AlbumDetailActivity;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/AlbumDetailActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/AlbumDetailActivity$1;->this$0:Lcom/miui/gallery/activity/AlbumDetailActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onHandleFailed(Landroid/content/Context;Ljava/lang/String;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/activity/AlbumDetailActivity$1;->this$0:Lcom/miui/gallery/activity/AlbumDetailActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/activity/AlbumDetailActivity;->finish()V

    return-void
.end method

.method public onHandleIntent(Landroid/content/Intent;)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/activity/AlbumDetailActivity$1;->this$0:Lcom/miui/gallery/activity/AlbumDetailActivity;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/activity/AlbumDetailActivity;->setIntent(Landroid/content/Intent;)V

    iget-object p1, p0, Lcom/miui/gallery/activity/AlbumDetailActivity$1;->this$0:Lcom/miui/gallery/activity/AlbumDetailActivity;

    new-instance v0, Lcom/miui/gallery/ui/AlbumDetailFragment;

    invoke-direct {v0}, Lcom/miui/gallery/ui/AlbumDetailFragment;-><init>()V

    invoke-static {p1, v0}, Lcom/miui/gallery/activity/AlbumDetailActivity;->access$002(Lcom/miui/gallery/activity/AlbumDetailActivity;Lcom/miui/gallery/ui/AlbumDetailFragment;)Lcom/miui/gallery/ui/AlbumDetailFragment;

    iget-object p1, p0, Lcom/miui/gallery/activity/AlbumDetailActivity$1;->this$0:Lcom/miui/gallery/activity/AlbumDetailActivity;

    invoke-static {p1}, Lcom/miui/gallery/activity/AlbumDetailActivity;->access$000(Lcom/miui/gallery/activity/AlbumDetailActivity;)Lcom/miui/gallery/ui/AlbumDetailFragment;

    move-result-object v0

    const-string v1, "albumDetail"

    const/4 v2, 0x0

    const/4 v3, 0x1

    invoke-virtual {p1, v0, v1, v2, v3}, Lcom/miui/gallery/activity/AlbumDetailActivity;->startFragment(Landroid/app/Fragment;Ljava/lang/String;ZZ)V

    return-void
.end method
