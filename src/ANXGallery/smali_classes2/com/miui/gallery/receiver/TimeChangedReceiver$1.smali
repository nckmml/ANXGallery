.class Lcom/miui/gallery/receiver/TimeChangedReceiver$1;
.super Ljava/lang/Object;
.source "TimeChangedReceiver.java"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/receiver/TimeChangedReceiver;->invalidateCloudCache()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/threadpool/ThreadPool$Job<",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/receiver/TimeChangedReceiver;


# direct methods
.method constructor <init>(Lcom/miui/gallery/receiver/TimeChangedReceiver;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/receiver/TimeChangedReceiver$1;->this$0:Lcom/miui/gallery/receiver/TimeChangedReceiver;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 1

    sget-object p1, Lcom/miui/gallery/cloud/GalleryCloudUtils;->CLOUD_CACHE_URI:Landroid/net/Uri;

    const/4 v0, 0x0

    invoke-static {p1, v0, v0}, Lcom/miui/gallery/util/GalleryUtils;->safeDelete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I

    return-object v0
.end method
