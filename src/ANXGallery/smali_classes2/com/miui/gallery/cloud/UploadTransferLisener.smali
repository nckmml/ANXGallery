.class public Lcom/miui/gallery/cloud/UploadTransferLisener;
.super Ljava/lang/Object;
.source "UploadTransferLisener.java"

# interfaces
.implements Lcom/xiaomi/opensdk/file/model/MiCloudFileListener;


# instance fields
.field private mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;


# direct methods
.method constructor <init>(Lcom/miui/gallery/cloud/RequestCloudItem;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/cloud/UploadTransferLisener;->mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;

    return-void
.end method


# virtual methods
.method public onDataReceived(JJ)V
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "upload should not received, pos:"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", total:"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3, p4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", item:"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lcom/miui/gallery/cloud/UploadTransferLisener;->mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;

    invoke-virtual {p1}, Lcom/miui/gallery/cloud/RequestCloudItem;->getIdentity()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "CloudGalleryTransferListener"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public onDataSended(JJ)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/cloud/UploadTransferLisener;->mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;

    iget p1, p1, Lcom/miui/gallery/cloud/RequestCloudItem;->priority:I

    invoke-static {p1}, Lcom/miui/gallery/cloud/SyncConditionManager;->check(I)I

    move-result p1

    if-eqz p1, :cond_0

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "net work is changed, interrupt this thread, priority="

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p2, p0, Lcom/miui/gallery/cloud/UploadTransferLisener;->mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;

    iget p2, p2, Lcom/miui/gallery/cloud/RequestCloudItem;->priority:I

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, ", item:"

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p2, p0, Lcom/miui/gallery/cloud/UploadTransferLisener;->mRequestItem:Lcom/miui/gallery/cloud/RequestCloudItem;

    invoke-virtual {p2}, Lcom/miui/gallery/cloud/RequestCloudItem;->getIdentity()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "CloudGalleryTransferListener"

    invoke-static {p2, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Thread;->interrupt()V

    :cond_0
    return-void
.end method
