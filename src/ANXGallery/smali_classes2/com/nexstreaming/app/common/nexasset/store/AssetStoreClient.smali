.class public Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;
.super Ljava/lang/Object;
.source "AssetStoreClient.java"


# static fields
.field private static final TAG:Ljava/lang/String; = "AssetStoreClient"

.field private static cipher:Lcom/nexstreaming/app/common/localprotocol/a; = null

.field private static m_configLock:Ljava/lang/Object; = null

.field public static final none:Ljava/lang/String; = "nonedata"


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->m_configLock:Ljava/lang/Object;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static getDataSync2([Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)I
    .locals 9

    sget-object v0, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->cipher:Lcom/nexstreaming/app/common/localprotocol/a;

    const/4 v1, -0x1

    if-nez v0, :cond_0

    return v1

    :cond_0
    const/4 v0, 0x0

    new-instance v2, Ljava/security/SecureRandom;

    invoke-direct {v2}, Ljava/security/SecureRandom;-><init>()V

    invoke-virtual {v2}, Ljava/security/SecureRandom;->nextInt()I

    move-result v2

    sget-object v3, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->cipher:Lcom/nexstreaming/app/common/localprotocol/a;

    invoke-virtual {v3}, Lcom/nexstreaming/app/common/localprotocol/a;->b()[B

    move-result-object v3

    invoke-static {v3, v2}, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreSock;->Start([BI)Lcom/nexstreaming/app/common/localprotocol/c$a;

    move-result-object v3

    const-string v4, "AssetStoreClient"

    if-nez v3, :cond_1

    const-string p0, "start is null"

    invoke-static {v4, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_1
    iget v5, v3, Lcom/nexstreaming/app/common/localprotocol/c$a;->f:I

    sget-object v6, Lcom/nexstreaming/app/common/localprotocol/nexProtoErrorCode;->OK:Lcom/nexstreaming/app/common/localprotocol/nexProtoErrorCode;

    invoke-virtual {v6}, Lcom/nexstreaming/app/common/localprotocol/nexProtoErrorCode;->getValue()I

    move-result v6

    if-eq v5, v6, :cond_2

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string p1, "start is error="

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, v3, Lcom/nexstreaming/app/common/localprotocol/c$a;->f:I

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v4, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_2
    iget-object v5, v3, Lcom/nexstreaming/app/common/localprotocol/c$a;->a:[B

    invoke-static {v5}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object v5

    invoke-virtual {v5}, Ljava/nio/ByteBuffer;->getInt()I

    move-result v6

    xor-int/2addr v2, v6

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "newSSID="

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v4, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v3, v3, Lcom/nexstreaming/app/common/localprotocol/c$a;->a:[B

    array-length v3, v3

    add-int/lit8 v3, v3, -0x4

    new-array v3, v3, [B

    invoke-virtual {v5, v3}, Ljava/nio/ByteBuffer;->get([B)Ljava/nio/ByteBuffer;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "encCommkey="

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    array-length v6, v3

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :try_start_0
    sget-object v5, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->cipher:Lcom/nexstreaming/app/common/localprotocol/a;

    invoke-virtual {v5, v3}, Lcom/nexstreaming/app/common/localprotocol/a;->b([B)[B

    move-result-object v0
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_5
    .catch Ljava/security/NoSuchProviderException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljavax/crypto/NoSuchPaddingException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/security/InvalidKeyException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljavax/crypto/IllegalBlockSizeException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljavax/crypto/BadPaddingException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v3

    invoke-virtual {v3}, Ljavax/crypto/BadPaddingException;->printStackTrace()V

    goto :goto_0

    :catch_1
    move-exception v3

    invoke-virtual {v3}, Ljavax/crypto/IllegalBlockSizeException;->printStackTrace()V

    goto :goto_0

    :catch_2
    move-exception v3

    invoke-virtual {v3}, Ljava/security/InvalidKeyException;->printStackTrace()V

    goto :goto_0

    :catch_3
    move-exception v3

    invoke-virtual {v3}, Ljavax/crypto/NoSuchPaddingException;->printStackTrace()V

    goto :goto_0

    :catch_4
    move-exception v3

    invoke-virtual {v3}, Ljava/security/NoSuchProviderException;->printStackTrace()V

    goto :goto_0

    :catch_5
    move-exception v3

    invoke-virtual {v3}, Ljava/security/NoSuchAlgorithmException;->printStackTrace()V

    :goto_0
    const/4 v3, 0x0

    move v5, v3

    :goto_1
    array-length v6, p0

    if-ge v5, v6, :cond_5

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Ljava/io/File;->separator:Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    aget-object v7, p0, v5

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v2, v6}, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreSock;->requestSECUseCommKey(ILjava/lang/String;)Lcom/nexstreaming/app/common/localprotocol/c$a;

    move-result-object v6

    if-nez v6, :cond_3

    const-string v6, "nonedata"

    aput-object v6, p1, v5

    goto :goto_2

    :cond_3
    iget v7, v6, Lcom/nexstreaming/app/common/localprotocol/c$a;->f:I

    sget-object v8, Lcom/nexstreaming/app/common/localprotocol/nexProtoErrorCode;->OK:Lcom/nexstreaming/app/common/localprotocol/nexProtoErrorCode;

    invoke-virtual {v8}, Lcom/nexstreaming/app/common/localprotocol/nexProtoErrorCode;->getValue()I

    move-result v8

    if-eq v7, v8, :cond_4

    const-string p0, "enckey is fail!"

    invoke-static {v4, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_4
    :try_start_1
    iget-object v6, v6, Lcom/nexstreaming/app/common/localprotocol/c$a;->a:[B

    invoke-static {v6, v0}, Lcom/nexstreaming/app/common/localprotocol/a;->a([B[B)[B

    move-result-object v6

    new-instance v7, Ljava/lang/String;

    invoke-direct {v7, v6}, Ljava/lang/String;-><init>([B)V

    aput-object v7, p1, v5
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_6

    goto :goto_2

    :catch_6
    move-exception v6

    invoke-virtual {v6}, Ljava/lang/Exception;->printStackTrace()V

    :goto_2
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    :cond_5
    return v3
.end method

.method public static makeConfig()V
    .locals 2

    const-string v0, "AssetStoreClient"

    const-string v1, "makeConfig in"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    sget-object v0, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->m_configLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->cipher:Lcom/nexstreaming/app/common/localprotocol/a;

    if-nez v1, :cond_0

    new-instance v1, Lcom/nexstreaming/app/common/localprotocol/a;

    invoke-direct {v1}, Lcom/nexstreaming/app/common/localprotocol/a;-><init>()V

    sput-object v1, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->cipher:Lcom/nexstreaming/app/common/localprotocol/a;

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/store/AssetStoreClient;->cipher:Lcom/nexstreaming/app/common/localprotocol/a;

    invoke-virtual {v1}, Lcom/nexstreaming/app/common/localprotocol/a;->a()V

    :cond_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const-string v0, "AssetStoreClient"

    const-string v1, "makeConfig out"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method
