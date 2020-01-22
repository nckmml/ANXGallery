.class Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener$1;
.super Ljava/lang/Object;
.source "BackupTitle.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener;->onCustomClick(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener$1;->this$1:Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 2

    iget-object p1, p0, Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener$1;->this$1:Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener;

    iget-object p1, p1, Lcom/miui/gallery/ui/BackupTitle$PauseSyncListener;->this$0:Lcom/miui/gallery/ui/BackupTitle;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/BackupTitle;->getContext()Landroid/content/Context;

    move-result-object p1

    const-wide/32 v0, 0x1b7740

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/SyncUtil;->pauseSync(Landroid/content/Context;J)V

    return-void
.end method
