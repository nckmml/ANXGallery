.class public Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;
.super Ljava/lang/Object;
.source "SyncDataInfo.java"


# instance fields
.field private mAuthority:Ljava/lang/String;

.field private mSyncedCount:I

.field private mUnSyncedDataCount:I

.field private mUnSyncedSecretDataCount:I

.field private mWifiOnlyUnsyncedDataCount:I


# direct methods
.method private constructor <init>(Landroid/content/Context;Ljava/lang/String;)V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mAuthority:Ljava/lang/String;

    const/4 v0, -0x2

    :try_start_0
    invoke-static {p1, p2}, Lmiui/cloud/sync/SyncInfoHelper;->getSyncedDataCount(Landroid/content/Context;Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mSyncedCount:I
    :try_end_0
    .catch Lmiui/cloud/sync/SyncInfoUnavailableException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    iput v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mSyncedCount:I

    :goto_0
    :try_start_1
    invoke-static {p1, p2}, Lmiui/cloud/sync/SyncInfoHelper;->getUnsyncedDataCount(Landroid/content/Context;Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mUnSyncedDataCount:I
    :try_end_1
    .catch Lmiui/cloud/sync/SyncInfoUnavailableException; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    :catch_1
    iput v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mUnSyncedDataCount:I

    :goto_1
    :try_start_2
    invoke-static {p1, p2}, Lmiui/cloud/sync/SyncInfoHelper;->getUnSyncedSecretDataCount(Landroid/content/Context;Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mUnSyncedSecretDataCount:I
    :try_end_2
    .catch Lmiui/cloud/sync/SyncInfoUnavailableException; {:try_start_2 .. :try_end_2} :catch_2

    goto :goto_2

    :catch_2
    iput v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mUnSyncedSecretDataCount:I

    :goto_2
    :try_start_3
    invoke-static {p1, p2}, Lmiui/cloud/sync/SyncInfoHelper;->getWifiOnlyUnsyncedDataCount(Landroid/content/Context;Ljava/lang/String;)I

    move-result p1

    iput p1, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mWifiOnlyUnsyncedDataCount:I
    :try_end_3
    .catch Lmiui/cloud/sync/SyncInfoUnavailableException; {:try_start_3 .. :try_end_3} :catch_3

    goto :goto_3

    :catch_3
    iput v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mWifiOnlyUnsyncedDataCount:I

    :goto_3
    return-void
.end method

.method private appendSyncedDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;
    .locals 2

    const-string v0, "synced count:"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->getSyncedCount()I

    move-result v0

    const/4 v1, -0x2

    if-eq v0, v1, :cond_0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_0
    const-string v0, "null"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_0
    return-object p1
.end method

.method private appendUnSyncedDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;
    .locals 2

    const-string v0, "unsynced count:"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->getUnSyncedDataCount()I

    move-result v0

    const/4 v1, -0x2

    if-eq v0, v1, :cond_0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_0
    const-string v0, "null"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_0
    return-object p1
.end method

.method private appendUnSyncedSecretDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;
    .locals 2

    const-string v0, "unsynced secret count:"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->getUnSyncedSecretDataCount()I

    move-result v0

    const/4 v1, -0x2

    if-eq v0, v1, :cond_0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_0
    const-string v0, "null"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_0
    return-object p1
.end method

.method private appendWifiOnlyUnsyncedDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;
    .locals 2

    const-string v0, "unsynced wifi only count:"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->getWifiOnlyUnsyncedDataCount()I

    move-result v0

    const/4 v1, -0x2

    if-eq v0, v1, :cond_0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_0
    const-string v0, "null"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_0
    return-object p1
.end method

.method public static generateCurrentSyncDataInfo(Landroid/content/Context;Ljava/lang/String;)Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;
    .locals 1

    new-instance v0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;

    invoke-direct {v0, p0, p1}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    return-object v0
.end method


# virtual methods
.method public getSyncedCount()I
    .locals 1

    iget v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mSyncedCount:I

    return v0
.end method

.method public getUnSyncedDataCount()I
    .locals 1

    iget v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mUnSyncedDataCount:I

    return v0
.end method

.method public getUnSyncedSecretDataCount()I
    .locals 1

    iget v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mUnSyncedSecretDataCount:I

    return v0
.end method

.method public getWifiOnlyUnsyncedDataCount()I
    .locals 1

    iget v0, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mWifiOnlyUnsyncedDataCount:I

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "SyncDataInfo["

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->mAuthority:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "]  {"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-direct {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->appendSyncedDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-direct {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->appendUnSyncedDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-direct {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->appendUnSyncedSecretDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-direct {p0, v0}, Lcom/xiaomi/micloudsdk/sync/SyncDataInfo;->appendWifiOnlyUnsyncedDataCount(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "}"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
