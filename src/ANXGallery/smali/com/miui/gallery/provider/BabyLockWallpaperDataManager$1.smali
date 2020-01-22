.class Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$1;
.super Ljava/lang/Object;
.source "BabyLockWallpaperDataManager.java"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->refresh()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/threadpool/ThreadPool$Job<",
        "Ljava/lang/Void;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;


# direct methods
.method constructor <init>(Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$1;->this$0:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$1;->run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;

    move-result-object p1

    return-object p1
.end method

.method public run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager$1;->this$0:Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;

    invoke-static {p1}, Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;->access$000(Lcom/miui/gallery/provider/BabyLockWallpaperDataManager;)V

    const/4 p1, 0x0

    return-object p1
.end method
