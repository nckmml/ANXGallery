.class abstract Lcom/miui/gallery/provider/cloudmanager/BatchTask;
.super Ljava/lang/Object;
.source "BatchTask.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        "D:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method protected describeBundle(Landroid/os/Bundle;)Ljava/lang/String;
    .locals 0

    if-nez p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Landroid/os/Bundle;->toString()Ljava/lang/String;

    move-result-object p1

    :goto_0
    return-object p1
.end method

.method protected abstract executeBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;Ljava/lang/Object;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/database/sqlite/SQLiteDatabase;",
            "Lcom/miui/gallery/provider/cache/MediaManager;",
            "Landroid/os/Bundle;",
            "TD;)V"
        }
    .end annotation
.end method

.method protected abstract getBatchBundle(IILandroid/os/Bundle;)Landroid/os/Bundle;
.end method

.method protected abstract getBatchCount()I
.end method

.method protected abstract getBatchExecuteKeys(Landroid/os/Bundle;)[Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/os/Bundle;",
            ")[TK;"
        }
    .end annotation
.end method

.method protected abstract getTotalCount()I
.end method

.method protected abstract release()V
.end method

.method protected abstract releaseBatchBundle(Landroid/os/Bundle;)V
.end method

.method protected abstract runBatch(Landroid/database/sqlite/SQLiteDatabase;Lcom/miui/gallery/provider/cache/MediaManager;Landroid/os/Bundle;)[J
.end method
