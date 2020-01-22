.class Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;
.super Lcom/xiaomi/micloudsdk/sync/SyncLogInfo;
.source "SyncLogInfo.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/xiaomi/micloudsdk/sync/SyncLogInfo;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "SyncEndLogInfo"
.end annotation


# instance fields
.field private final mEndTime:J

.field private final mErrorMsg:Ljava/lang/String;

.field private final mIsSuccess:Z

.field private final mStartTime:J


# direct methods
.method constructor <init>(Ljava/lang/String;ZJJLjava/lang/String;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo;-><init>(Ljava/lang/String;)V

    iput-boolean p2, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mIsSuccess:Z

    iput-wide p3, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mStartTime:J

    iput-wide p5, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mEndTime:J

    if-nez p7, :cond_0

    const-string p1, ""

    iput-object p1, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mErrorMsg:Ljava/lang/String;

    goto :goto_0

    :cond_0
    iput-object p7, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mErrorMsg:Ljava/lang/String;

    :goto_0
    return-void
.end method


# virtual methods
.method public generateLogInfoString()Ljava/lang/String;
    .locals 6

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-boolean v1, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mIsSuccess:Z

    if-eqz v1, :cond_0

    const-string v1, "Success"

    goto :goto_0

    :cond_0
    const-string v1, "Error"

    :goto_0
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ":{"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "authority:"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->authority:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "start:"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mStartTime:J

    invoke-static {v2, v3}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->formatTime(J)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "end:"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mEndTime:J

    invoke-static {v2, v3}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->formatTime(J)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "delta:"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mStartTime:J

    iget-wide v4, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mEndTime:J

    invoke-static {v2, v3, v4, v5}, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->getDeltaTime(JJ)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "error:"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/xiaomi/micloudsdk/sync/SyncLogInfo$SyncEndLogInfo;->mErrorMsg:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "}"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
