.class Lcom/miui/gallery/cleaner/BaseScanner$1;
.super Ljava/lang/Object;
.source "BaseScanner.java"

# interfaces
.implements Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cleaner/BaseScanner;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/cleaner/BaseScanner;


# direct methods
.method constructor <init>(Lcom/miui/gallery/cleaner/BaseScanner;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/cleaner/BaseScanner$1;->this$0:Lcom/miui/gallery/cleaner/BaseScanner;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/Context;)V
    .locals 3

    new-instance v0, Landroid/content/Intent;

    const-class v1, Lcom/miui/gallery/activity/CleanerPhotoPickActivity;

    invoke-direct {v0, p1, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    iget-object v1, p0, Lcom/miui/gallery/cleaner/BaseScanner$1;->this$0:Lcom/miui/gallery/cleaner/BaseScanner;

    iget v1, v1, Lcom/miui/gallery/cleaner/BaseScanner;->mType:I

    const-string v2, "extra_cleaner_photo_pick_type"

    invoke-virtual {v0, v2, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    invoke-virtual {p1, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V

    iget-object p1, p0, Lcom/miui/gallery/cleaner/BaseScanner$1;->this$0:Lcom/miui/gallery/cleaner/BaseScanner;

    invoke-virtual {p1}, Lcom/miui/gallery/cleaner/BaseScanner;->recordClickScanResultEvent()V

    return-void
.end method
