.class public final Lcom/xiaomi/network/Host;
.super Ljava/lang/Object;
.source "Host.java"


# instance fields
.field private hostAddress:Ljava/lang/String;

.field private port:I


# direct methods
.method public constructor <init>(Ljava/lang/String;I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/xiaomi/network/Host;->hostAddress:Ljava/lang/String;

    iput p2, p0, Lcom/xiaomi/network/Host;->port:I

    return-void
.end method

.method public static from(Ljava/lang/String;I)Ljava/net/InetSocketAddress;
    .locals 1

    invoke-static {p0, p1}, Lcom/xiaomi/network/Host;->parse(Ljava/lang/String;I)Lcom/xiaomi/network/Host;

    move-result-object p0

    new-instance p1, Ljava/net/InetSocketAddress;

    invoke-virtual {p0}, Lcom/xiaomi/network/Host;->getHost()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0}, Lcom/xiaomi/network/Host;->getPort()I

    move-result p0

    invoke-direct {p1, v0, p0}, Ljava/net/InetSocketAddress;-><init>(Ljava/lang/String;I)V

    return-object p1
.end method

.method public static parse(Ljava/lang/String;I)Lcom/xiaomi/network/Host;
    .locals 2

    const-string v0, ":"

    invoke-virtual {p0, v0}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v0

    const/4 v1, -0x1

    if-eq v0, v1, :cond_1

    const/4 v1, 0x0

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    add-int/lit8 v0, v0, 0x1

    :try_start_0
    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    if-gtz p0, :cond_0

    goto :goto_0

    :cond_0
    move p1, p0

    goto :goto_0

    :cond_1
    move-object v1, p0

    :catch_0
    :goto_0
    new-instance p0, Lcom/xiaomi/network/Host;

    invoke-direct {p0, v1, p1}, Lcom/xiaomi/network/Host;-><init>(Ljava/lang/String;I)V

    return-object p0
.end method


# virtual methods
.method public getHost()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/network/Host;->hostAddress:Ljava/lang/String;

    return-object v0
.end method

.method public getPort()I
    .locals 1

    iget v0, p0, Lcom/xiaomi/network/Host;->port:I

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    iget v0, p0, Lcom/xiaomi/network/Host;->port:I

    if-lez v0, :cond_0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Lcom/xiaomi/network/Host;->hostAddress:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lcom/xiaomi/network/Host;->port:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/xiaomi/network/Host;->hostAddress:Ljava/lang/String;

    return-object v0
.end method
